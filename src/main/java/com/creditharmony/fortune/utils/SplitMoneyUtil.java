package com.creditharmony.fortune.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.fortune.deduct.common.PlatformRuleCache;
import com.creditharmony.fortune.deduct.entity.PlatformRule;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteTlExportModel;
import com.google.common.collect.Lists;

/**
 * 通联导出拆分
 * @Class Name SplitMoneyUtil
 * @author 韩龙
 * @Create In 2016年2月17日
 */
public class SplitMoneyUtil {
	
	// 文件序号
	private int fileIndex = 1; 
	// 文件名字
	private String flieName;
	// 
	private OutputStream out;
	
	private HttpServletRequest request;
	
	private List<String> fileNames;
	
	/**
	 * 构造方法
	 * @param response
	 * @param fileName
	 */
	public SplitMoneyUtil(HttpServletRequest request,HttpServletResponse response,String fileName){
		this.flieName = fileName;
		this.request = request;
		try {
			// 设置Header信息
			response.setContentType("APPLICATION/OCTET-STREAM");  
			response.setHeader("Content-Disposition",
					"attachment; filename=" + fileName
					+ ";filename*=UTF-8''" + fileName);
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件名
	 * 2016年2月20日
	 * By 韩龙
	 * @return
	 */
	public String getFileName(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String f = flieName + "-" + format.format(date);
		this.flieName = f;
		return "";
	}
	
	/**
	 * 通联拆分
	 * 2016年2月17日
	 * By 韩龙
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public void splitTl(List<ExecuteTlExportModel> dateList) throws IOException{
		getFileName();
		// 用于存放生成的文件名称s
		fileNames = Lists.newArrayList();
		File zip = new File(request.getRealPath("/excel") + flieName + ".zip");// 压缩文件
		boolean b1 = true;
		// 是否有记录
		if (!ArrayHelper.isNotEmpty(dateList)) {
			return;
		}
		List<ExecuteTlExportModel> newList = Lists.newArrayList();
		int index = 1;
		BigDecimal sumMoney = new BigDecimal(0);
		for (ExecuteTlExportModel executeTlExportModel : dateList) {
			PlatformRule pfr = PlatformRuleCache.getBatch(DeductPlat.TL.value,
					executeTlExportModel.getAccountBank());
			// 金額
			BigDecimal applyLendMoney = new BigDecimal(executeTlExportModel.getBackActualbackMoney());
			// 单笔限额
			BigDecimal singleLimitMoney = pfr.getSingleLimitMoney();
			// 单日限额
			BigDecimal dayLimitMoney = pfr.getDayLimitMoney();
			if (applyLendMoney.compareTo(dayLimitMoney)>1) {
				continue;
			} else {
				int reIndex = 0;
				BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
				if(big[1].intValue() == 0){
					reIndex = index + big[0].intValue();
				}else{
					reIndex = index + big[0].intValue()+1;
				}
				// 判断导出数据不能大于500条导出excel
				if(reIndex >= 500){
					b1 = false;
					exportExcel(newList,getTitle(sumMoney.toString(),index + ""));
					fileIndex++;
					index = 1;
					newList = Lists.newArrayList();
					sumMoney = new BigDecimal(0);
				}
				// 导出总金额
				sumMoney = sumMoney.add(applyLendMoney);
				if (singleLimitMoney.compareTo(applyLendMoney) == -1) {
					b1 = true;
					// 拆分金额
					do {
						ExecuteTlExportModel tem = new ExecuteTlExportModel();
						BeanUtils.copyProperties(executeTlExportModel, tem);
						index++;
						// 如果金额小于单笔限额退出拆分
						if (applyLendMoney.compareTo(singleLimitMoney) == -1 ) {
							tem.setBackActualbackMoney(applyLendMoney.doubleValue());
							newList.add(tem);
							break;
						}
						// 设置拆分金额
						tem.setBackActualbackMoney(singleLimitMoney.doubleValue());
						newList.add(tem);
						applyLendMoney = applyLendMoney.add(singleLimitMoney) ;

					} while (true);
					continue;
				}
				index++;
				newList.add(executeTlExportModel);
			}
		}
		// 判断是否有拆分数据，有就导出
		if(b1){
			// 导出excel
			exportExcel(newList,getTitle(sumMoney.toString(),index + ""));
		}
		// 获取文件集合
		File srcfile[] = new File[fileNames.size()];
		for (int i = 0, n = fileNames.size(); i < n; i++) {
			srcfile[i] = new File(fileNames.get(i));
		}
		// 生成压缩文件
		FileZip.ZipFiles(srcfile, zip);
		FileInputStream inStream = new FileInputStream(zip);
		byte[] buf = new byte[4096];
		int readLength;
		while (((readLength = inStream.read(buf)) != -1)) {
			out.write(buf, 0, readLength);
		}
		inStream.close();
		// 删除
		zip.delete();
	}
	
	/**
	 * 导出
	 * 2016年2月17日
	 * By 韩龙
	 * @param dateList
	 * @param title
	 */
	private void exportExcel(List<ExecuteTlExportModel> dateList,List<String> title){
		String reFlieName = flieName+"_" + fileIndex + ".xlsx";
		fileNames.add(reFlieName);
		ExportExcel ee = new ExportExcel("", title);
		ee.reExportExcel(null, ExecuteTlExportModel.class,1);
		ee.setDataList(dateList);
		try {
			ee.writeFile(reFlieName);
			ee.dispose();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 标题
	 * 2016年2月17日
	 * By 韩龙
	 * @param sumMoney
	 * @param total
	 * @return
	 */
	private List<String> getTitle(String sumMoney,String total){
		List<String> title = Lists.newArrayList();
		title.add("S");
		title.add("200604000000445");
		title.add(DateUtils.getDate("yyyyMMdd"));
		title.add(total);
		title.add(sumMoney);
		title.add("09900");
		return title;
	}
}
