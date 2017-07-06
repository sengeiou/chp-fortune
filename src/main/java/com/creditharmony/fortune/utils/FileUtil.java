package com.creditharmony.fortune.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.FileUtils;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.file.util.Zip;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.FileManagement;
import com.creditharmony.fortune.common.ThreadPool;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.service.DocumentSynthesis;
import com.google.common.collect.Lists;

/**
 * 文件操作工具类
 * @Class Name FileUtil
 * @author zhujie
 * @Create In 2015年11月27日
 */
public class FileUtil {
	
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 保存文件
	 * 
	 * @param file
	 *            上传的文件
	 * @param fileType
	 *            上传的文件类型
	 * @return Attachment:文件名,新文件名,存放根路径
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static Attachment saveFiles(MultipartFile file,String customerFile) throws IllegalStateException, IOException {
		Attachment attachment = FileUtil.saveFiles(file, customerFile, "tmp");
		return attachment;
	}
	/**
	 * 保存文件
	 * 
	 * @param file
	 *            上传的文件
	 * @param fileType
	 *            上传的文件类型
	 * @return Attachment:文件名,新文件名,存放根路径
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static Attachment saveFiles(MultipartFile file,String customerFile, String subFile) throws IllegalStateException, IOException {

		// 返回值，记录文件的原文件名和新文件名
		Attachment savedFile = new Attachment();
	
		// 上传文件名
		String fileName = file.getOriginalFilename();
		InputStream is = file.getInputStream();
		savedFile.setAttaFilename(fileName);// 文件名
		savedFile.setAttaNewfilename(fileName);// 新文件名
		try {
			DocumentBean docbean = null;
		    String user = UserUtils.getUserId();
			DmService dmService = DmService.getInstance();
			docbean = dmService.createDocument(fileName,is , DmService.BUSI_TYPE_FORTUNE, customerFile, subFile,user );
			savedFile.setAttaFilepath(docbean.getDocId());// 存放根路径
		}
		catch(RuntimeException e){
			logger.error("[Fortune---->FileUtil:saveFiles()] exception: "+e.getMessage());
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return savedFile;
		}
				
		return savedFile;
	}
	
	/**
	 * 
	 * 2016年2月24日
	 * By 刘雄武
	 * @param response
	 * @param seletive
	 * @param type
	 */
	public static void fileDownloadby(HttpServletResponse response,Attachment seletive,String type) {
		// 根据附件ID检索附件信息
		logger.info("fileDownload文件下载......");
		DmService dmService = DmService.getInstance();
		 OutputStream os;
		try {

			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + new String( seletive.getAttaFilename().getBytes("gb2312"), "ISO8859-1" ) +"\""));
			os = response.getOutputStream();
			dmService.download(os, seletive.getAttaFilepath());
			os.flush();
			os.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * 从网盘读取并下载
	 * 2016年5月16日
	 * By 周俊
	 * @param response
	 * @param strZipPath
	 * @param strZipname
	 */
	public  static void downFileToNetdisk(HttpServletResponse response, String filePath,String fileName) {
		try {
			// 放到缓冲流里面
			InputStream ins = new FileInputStream(filePath);
			BufferedInputStream bins = new BufferedInputStream(ins);
			// 获取文件输出IO流
			OutputStream outs = response.getOutputStream();
			BufferedOutputStream bouts = new BufferedOutputStream(outs);
			// 设置response内容的类型
			response.setContentType("application/x-msdownload");
			// 设置头部信息
			response.setHeader("Content-disposition","attachment;filename="+ 
					new String( fileName.getBytes("GBK"), "ISO8859-1" ));
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			// 开始向网络传输文件流
			while ((bytesRead = bins.read(buffer, 0, 1024)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			//调用flush()方法
			bouts.flush();
			ins.close();
			bins.close();
			outs.close();
			bouts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 文件下载
	 * 2015年12月9日
	 * By 韩龙
	 * @param response
	 * @param seletive Attachment实体类
	 * @param type 预览:preview 下载:download
	 */
	public static void fileDownload(HttpServletResponse response,Attachment seletive,String type) {
		// 根据附件ID检索附件信息
		logger.info("fileDownload文件下载......");
		File file = null;
		file = new File(FileUtils.path(Global.getConfig("uploadFilePath")+ "/" + seletive.getAttaFilepath()));
		if(!file.exists()){
			String errorMessage;
			if(type.equalsIgnoreCase("preview")){
				logger.info("没有文件预览");
				errorMessage = "没有文件预览";
			}else{
				logger.info("没有文件下载");
				errorMessage = "没有文件下载";
			}		
            try {
				// 文字回写
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-16")));
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				logger.error("文件读定错误"+e.getLocalizedMessage(),e);
			}
            return;
        }
		// 确定下载文件类型
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            // 默认类型
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        
        if(type.equalsIgnoreCase("preview")){
        	// 预览模式
        	response.setHeader("Content-Disposition", String.format("inline; filename=\"" + seletive.getAttaFilename() +"\""));
        }else{
        	// 其他模式：下载
        	response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + seletive.getAttaFilename() +"\""));
        }
        
        response.setContentLength((int)file.length());
        try {
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			// 文件转换成数据流输出，最后关闭输入，输出数据流
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			logger.error("文件转换数据流出错"+e.getLocalizedMessage(),e);
		}
	}
	
	/**
	 * 导出excel
	 * 2015年12月9日
	 * By 韩龙
	 * @param dataList 导出数据列表
	 * @param cla 导出模板实体类
	 * @param fileName 导出文件名字
	 * @param response
	 */
	public static void exportExcel(List<?> dataList, Class<?> cla, String fileName,
			HttpServletResponse response) {
		// 构建导出excel对象
		ExportExcel exportExcel = new ExportExcel(null, cla);
		// 设置导出数据源
		exportExcel.setDataList(dataList);
		try {
			logger.info("写出文件到客户端" + fileName + ".xlsx");
			// 写出文件到客户端
			exportExcel.write(response, fileName + ".xlsx");
			exportExcel.dispose();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("列表导出失败", e);
		}
	}
	
	/**
	 * 批量下载文件zip
	 * 2015年12月9日
	 * By 韩龙
	 * @param subs 文件列表
	 * @param fileName Global.USERFILES_BASE_URL路径
	 * @param type 文件类型
	 * @param response
	 */
	public static void zipFileDownload(File[] subs, String fileName,HttpServletResponse response){
        ZipOutputStream zos=null;
        FileInputStream fis=null;
		try {
			// 设置Header信息
			response.setContentType("APPLICATION/OCTET-STREAM");  
			response.setHeader("Content-Disposition",
					"attachment; filename=" + getZipFilename(fileName)
					+ ";filename*=UTF-8''" + getZipFilename(fileName));
			zos = new ZipOutputStream(response.getOutputStream());
		// 遍历文件
        for (int i=0;i<subs.length;i++) {    
	         File f=subs[i];    
	         try {
				zos.putNextEntry(new ZipEntry(f.getName()));
		         fis = new FileInputStream(f);       
		         byte[] buffer = new byte[1024];       
		         int r = 0;       
		         while ((r = fis.read(buffer)) != -1) {       
		             zos.write(buffer, 0, r);       
		         }
		     fis.close();
	         } catch (IOException e) {
	        	 logger.error("压缩文件"+f.getName()+"错误："+e.getLocalizedMessage(),e);
			 }  
        }
			zos.flush();
		    zos.close();
		} catch (IOException e1) {
			logger.error("压缩文件错误："+e1.getLocalizedMessage(),e1);
		}
   }
	
	/**
	 * 批量从文件服务器下载zip文件
	 * 2015年12月29日
	 * By 韩龙
	 * @param fileName
	 * @param response
	 * @param listAttachment
	 */
	public static void zipFileDownload(String fileName,HttpServletResponse response,List<Attachment> listAttachment){
		OutputStream os = null;
		// 封装
		List<String> docIds = Lists.newArrayList();
		List<Attachment> docIdFiles = Lists.newArrayList();
		for (Attachment a : listAttachment) {
			// 斜杠开头的是chp2.0的数据,
			if(a.getAttaFilepath().startsWith("/")){
				docIdFiles.add(a);
			}else{
				docIds.add(a.getAttaFilepath());
			}
		}
		try {
//			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + seletive.getAttaFilename() +"\""));
			// 设置Header信息
//			response.setContentType("application/zip");  
			response.setHeader("Content-Disposition",
					"attachment; filename=" + getZipFilename(fileName)
					+ ";filename*=UTF-8''" + getZipFilename(fileName));
			os = response.getOutputStream();
			Map<String,InputStream> map = new HashMap<String,InputStream>();
			Map<String,InputStream> fileMap = new HashMap<String,InputStream>();
			// 2.0文件是从网盘上下载
			if(docIdFiles != null && docIdFiles.size() > 0){
				for (Attachment attachment : docIdFiles) {
					// 放到缓冲流里面
					InputStream ins = new FileInputStream(attachment.getAttaFilepath() + 
							"."+attachment.getAttaFileType());
					fileMap.put(attachment.getAttaNewfilename(), ins);
				}
			}
			if(docIds != null && docIds.size() > 0){
				DmService dmService = DmService.getInstance();
//				dmService.download(os, docIds);
				map = dmService.downloadDocuments(docIds);	
			}
			map.putAll(fileMap);
			Zip.zipFiles(os, map);
		} catch (IOException e1) {
			logger.error("压缩文件错误："+e1.getLocalizedMessage(),e1);
		}finally{
			// 关闭流
//			try {
//				os.flush();
//				os.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
   }
	
	/**
	 * 下载压缩包文件名
	 * 2015年12月10日
	 * By 韩龙
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String getZipFilename(String baseName)
			throws UnsupportedEncodingException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH-mm-dd");
		Date date = new Date();
		String s = baseName +"_"+ sdf.format(date) + ".zip";
		return java.net.URLEncoder.encode(s, "utf-8");
	}
	
	/**
	 * 合成文件
	 * 2016年1月11日
	 * By 韩龙
	 * @param filters 
	 * Map key:templateName模板名称
	 * 		  :parameter 需要参数
	 * 		  :lendCode 出借编号
	 * 		  :attaTableId 所属表ID
	 * 		  :attaFlag 文件标识(cjxy：出借合同；bg：变更申请；zr：赎回申请；hkxy：委托划扣；zrsq：债权转让等等)
	 * 		  :attaFileOwner 所属表表名[可选]
	 * 		  :fileName 文件名[可选]
	 * @return
	 * @throws IOException
	 */
	public static int compositeFile(Map<String, Object> filters){
		int result = 0;
		String makeFileName = filters.get("fileName")==null?"1":filters.get("fileName").toString();
		String httpUrl = Constant.getProperties.get("template_cpt_url");
		String signType = filters.get("signType")==null?"":filters.get("signType").toString();
		// 获取参数模板Url
		if(filters.get("templateName") !=null ){
			String fileName = Constant.getProperties.get(filters.get("templateName"));
			httpUrl = httpUrl.replace("reportlet=", "reportlet="+fileName);
		}else{
			// 默认值：收款确认书
			String fileName = Constant.getProperties.get(ReportType.SKQRS_HK.getCode());
			httpUrl = httpUrl.replace("reportlet=", "reportlet="+fileName);
		}
		// 获取每个模板需要的参数，并拼接
		String parameter = (String) (filters.get("parameter")==null ? "":filters.get("parameter"));
		httpUrl = httpUrl +"&"+parameter;
		//文件类型
		String[] flieTypes;
		if(StringUtils.isNotBlank(StringExUtil.getTrimString(filters.get("flieType")))){
			flieTypes = new String[]{filters.get("flieType").toString()};
		}else{
			// 获取配置要生成文件类型
			flieTypes = Constant.getProperties.get("file_type").split(",");
		}		
		ThreadPool threadPool =ThreadPool.getInstance();
		List<Attachment> attachmentLst = new ArrayList<Attachment>();
		List<HashMap<String, String>> rehttpList = new ArrayList<HashMap<String, String>>();
		for (String flieType : flieTypes) {
			String rehttpUrl ="";
			// 获取线程池
			String fileName = makeFileName + "." + flieType;
			Attachment attachment = new Attachment();
			attachment.preInsert();
			attachment.setAttaId(attachment.getId());
			attachment.setAttaFilename(fileName);
			attachment.setAttaNewfilename(fileName);
			//出借编号或者客户编号，都存在此字段
			attachment.setLoanCode(StringExUtil.getTrimString(filters.get("lendCode")));
			attachment.setAttaFileType(flieType);
			attachment.setDictAttaFlag(StringExUtil.getTrimString(filters.get("attaFlag")));
			attachment.setAttaTableId(StringExUtil.getTrimString(filters.get("attaTableId")));
			attachment.setAttaFileOwner(StringExUtil.getTrimString(filters.get("attaFileOwner")));
			//attachment.setFrom(StringExUtil.getTrimString(filters.get("from")));
			attachment.setCreateTime(new Date());
			attachment.setCreateBy(UserUtils.getUserId());
			String reType = flieType;
			if(reType.equals("pdf")){
				if(filters.containsKey("sendFlag") && filters.get("sendFlag").equals(com.creditharmony.fortune.creditor.matching.constant.Constant.SEND_FLAG_YES)){
					attachment.setSendFlag(com.creditharmony.fortune.creditor.matching.constant.Constant.SEND_FLAG_YES);
					attachment.setTemplateType(filters.get("templateType").toString());
				}
			}
			if(reType.equals("doc")){
				reType = "word";
			}
			attachmentLst.add(attachment);
			rehttpUrl = httpUrl + "&format="+reType;
			HashMap<String, String> fileParam = new HashMap<String, String>();
			fileParam.put("rehttpUrl", rehttpUrl);
			fileParam.put("fileName", fileName);
			rehttpList.add(fileParam);
			result++;
		}
		
		// 文件合成用,
		Attachment discardAttachment = filters.get("attachment")==null ? null : (Attachment)filters.get("attachment");
		
		// 放入线程
		FileManagement fileManagement = new FileManagement();
		DocumentSynthesis DocumentSynthesis = new DocumentSynthesis(attachmentLst,StringExUtil.getTrimString(filters.get("attaTableId")),discardAttachment,StringExUtil.getTrimString(filters.get("from")) );
		fileManagement.before(rehttpList,signType,null,filters.get("customerCode").toString(),filters.get("lendCode").toString(), DocumentSynthesis);
		threadPool.addTask(fileManagement);
		return result;
	}
}
