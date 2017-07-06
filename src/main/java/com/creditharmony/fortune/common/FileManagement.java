package com.creditharmony.fortune.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CEContextHolder;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.utils.CaUtil;
import com.creditharmony.fortune.utils.FileUtil;

/**
 * 文件合成类
 * @Class Name AbstractHttpDownload
 * @author 韩龙
 * @Create In 2015年12月11日
 */
public class FileManagement implements Runnable{

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	// 文件id
	private List<String> fileIdLst = new ArrayList<String>();
	//URL参数
	List<HashMap<String, String>> rehttpList ;
	//签名类型
	private String signType;
	//代理签名用参数
	private CaCustomerSign customerParam;
	//客户编号
	private String custNo;
	//子目录
	private String subType;
	
	private CallInterface callInterface;
	
	/**
	 * 初始化
	 * 
	 * 2016年3月31日
	 * By 韩龙
	 * @param rehttpList
	 * @param signType
	 * @param customerSignParams
	 * @param callInterface
	 * @param ceRequestContext
	 */
	public void before(List<HashMap<String, String>> rehttpList,String signType, CaCustomerSign customerParam, String custNo, String subType, CallInterface callInterface){
		this.rehttpList =rehttpList;
		this.signType=signType;
		this.custNo=custNo;
		this.subType=subType;
		if(null == customerParam){
			this.customerParam=new CaCustomerSign();
		}else{
			this.customerParam=customerParam;
		}
		if(callInterface != null){
			this.callInterface = callInterface;
		}
	}
	
	public void run() {
		// 获取fileName
		try {
			call(httpDownload());
		} catch (Exception e) {
			FortuneExceptionUtil.insertExceptionBatch(FortuneExceptionUtil.newException(e, "ZZ999999999-001",
					FortuneLogNode.MATCHING_CREDITOR.getCode(),					
					FortuneLogLevel.YELLOW.value, "制作文件成功但插入邮件发送表失败"));
			logger.error("制作文件成功但插入邮件发送表失败，失败信息："+e.getMessage(),e);
		}
	}
	
	/**
	 * 报表服务器生成文件
	 * 2015年12月11日
	 * By 韩龙
	 * @return 返回true:制作成功,false:制作失败
	 */
	@SuppressWarnings("unused")
	public boolean httpDownload() {
		
		if(rehttpList!=null && rehttpList.size()>0){
			for(HashMap<String, String> rehttp: rehttpList){
				logger.info("请求报表服务器url:"+rehttp.get("rehttpUrl")+";生成文件路径:"+rehttp.get("fileName"));
				// 下载网络文件
				int byteread = 0;
				// 构建url
				URL url = null;
				try {
					url = new URL(rehttp.get("rehttpUrl"));
				} catch (MalformedURLException e1) {
					logger.error("生成文件构建url:"+rehttp.get("rehttpUrl")+"错误",e1);
					return false;
				}
				try {
					//延迟执行
					Thread.sleep(666);  
					
					//CE认证
					CERequestContext ceRequestContext = CEContextHelper.loginWithUP(
							Constant.getProperties.get("ceuser"),
							Constant.getProperties.get("cepwd"));
					CEContextHolder.setContext(ceRequestContext);
					// 创建连接
					URLConnection conn = url.openConnection();
					InputStream inStream = conn.getInputStream();
					DmService dmService = DmService.getInstance();
					// 把生成的文档上传到文件服务器
					logger.info("客户号CUSTNO:"+this.custNo+",    子目录："+this.subType+",    获取用户ID："+UserUtils.getUserId());
					DocumentBean doc = dmService.createDocument(rehttp.get("fileName"), inStream,
							DmService.BUSI_TYPE_FORTUNE, this.custNo, this.subType,
							UserUtils.getUserId());
					
					logger.info("获取的DOC信息："+doc+",  生成的文档DOCID:"+doc.getDocId()+",   代理签名用参数"+this.customerParam);
					// 判断是不否是pdf，是pdf制作签章
					
					if(rehttp.get("fileName").endsWith(".pdf")){
						
						// 空则不需要签章
						if("".equals(this.signType)||this.signType==null){
					
							fileIdLst.add(doc.getDocId());
							return true;
						}
						// pdf制作签章
						this.customerParam.setBatchNo(this.custNo);
						this.customerParam.setSubType(this.subType);
						Ca_SignOutBean info = CaUtil.sign(this.signType, doc.getDocId(),this.customerParam);
						logger.info("签章相关信息:"+info);
						if(info == null||info.getDocId()==null){
							logger.info("CA签章调用出错，返回code："+info.getRetCode()+"返回消息："+info.getRetMsg() +"文件id"+info.getDocId()+"客户编号" + this.custNo );
							return false;
						}else{
							//将文件移动至固定文件夹内
							//dmService.moveDocument(info.getDocId(), this.custNo+"/"+this.subType, DmService.BUSI_TYPE_FORTUNE,rehttp.get("fileName"));
							if(StringUtils.isNotEmpty(info.getDocId())){
								fileIdLst.add(info.getDocId());
							}	
						}
											
					}else{
						fileIdLst.add(doc.getDocId());
					}
				} catch (FileNotFoundException e) {
					logger.error("没有找到报表文件",e);
					return false;
				} catch (IOException e) {
					logger.error("读取文件错误",e);
					return false;
				} catch (InterruptedException e) {
					logger.error("调用线程睡眠错误",e);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 回调方法
	 * 2016年1月5日
	 * By 韩龙
	 * @param b
	 */
	public void call(boolean b){  
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bool", b);
		map.put("docId", fileIdLst);
	    this.callInterface.method(map);  
	} 
}
