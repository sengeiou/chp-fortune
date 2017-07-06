package com.creditharmony.fortune.deduct.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.ExportType;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.deduct.common.DeductExportExcel;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.OffLineDeductExportExcel;

/**
 * 划扣申请公共Controller
 * @Class Name DeductCommon
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class DeductCommon extends BaseController{
	
	/**
	 * 导出excel
	 * 2015年11月27日
	 * By 韩龙
	 * @param dataList 导出数据源
	 * @param cla 导出对象实体类
	 * @param fileName 文件名
	 * @param response HttpServletResponse对象
	 */
	public void outExcelCommon(List<?> dataList, Class<?> cla, String fileName,
			HttpServletResponse response) {
		// 构建导出excel对象
		ExportExcel exportExcel = new ExportExcel(null, cla);
		// 设置导出数据源
		exportExcel.setDataList(dataList);
		try {
			this.logger.info("写出文件到客户端" + fileName + ".xlsx");
			// 写出文件到客户端
			exportExcel.write(response, fileName + ".xlsx");
			exportExcel.dispose();
		} catch (IOException e) {
			this.logger.error("导出失败", e);
		}
	}
	
	/**
	 * 线下划扣导出
	 * 2016年3月4日
	 * By 韩龙
	 * @param dataList
	 * @param map
	 * @param response
	 */
	public void download(List<DeductExportExcel> dataList,Map<String,Object> map,
			HttpServletResponse response) {
/*		String exportPtId = (String) map.get("exportPtId");
		String exportType = (String) map.get("exportType");
		DeductSplitExcelUtil dseu = new DeductSplitExcelUtil(response,exportType,getFileName(exportPtId),exportPtId);
		try {
			dseu.split(dataList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String lendCodes="";
		// 判断是否选择要导出的记录
		if (map.get("lendCodes") == null) {
			return;
		}
		lendCodes = map.get("lendCodes").toString();
		// 分天划扣标识
		if (map.get("theDay") != null) {
			map.put("id", DeductUtils.getCodes(lendCodes));
			map.put("lendCodes", null);
		} else {
			map.put("lendCodes", DeductUtils.getCodes(lendCodes));
		}
		String exportPtId = (String) map.get("exportPtId");
		String exportType = (String) map.get("exportType");
		String sql = ConstantField.deductOffLine_sql;
		OffLineDeductExportExcel odee = new OffLineDeductExportExcel(response,exportType,getFileName(exportPtId),exportPtId);
		if(exportPtId.equals(DeductPlat.FYPT.value)){
			// 富有
			odee.exportExcelData(map, ConstantField.deductFuyouOffLine_title, 
					ConstantField.deductFuyouOffLine_field, sql);
		}else if(exportPtId.equals(DeductPlat.HYLPT.value)){
			if(ExportType.XLSX.getCode().equals(exportType)){
				// 好易联excel
				odee.exportExcelData(map, ConstantField.deductHylOffLine_title, 
						ConstantField.deductHylOffLine_field, sql);
			}else{
				// 好易联TXT
				odee.exportTxtData(map,ConstantField.deductHylOffLine_title, 
						ConstantField.deductHylOffLine_field, sql);
			}

		}else if(exportPtId.equals(DeductPlat.ZJPT.value)){
			if(ExportType.XLSX.getCode().equals(exportType)){
				// 中金excel
				odee.exportExcelData(map, ConstantField.deductZjOffLine_title, 
						ConstantField.deductZjOffLine_field, sql);
			}else{
				// 中金txt
				odee.exportTxtData(map, ConstantField.deductZjOffLine_title, 
						ConstantField.deductZjOffLine_field_txt, sql);
			}
		}else{
			// 通联
			odee.exportExcelData(map, ConstantField.deductTlOffLine_title, 
					ConstantField.deductTlOffLine_field, sql);
		}
	}
	
	/**
	 * 生成文件名
	 * 2016年1月6日
	 * By 韩龙
	 * @param modelFlag
	 * @param exportExcelType
	 * @return
	 */
	public String getFileName(String modelFlag){
		String fileName = "";
		if (DeductPlat.FYPT.value.equals(modelFlag)){
			fileName = fileName + "AC01_" + DateUtils.getDate("yyyyMMdd");
		} else if (DeductPlat.HYLPT.value.equals(modelFlag)) {
			// 文件名生成
			String no = Constant.getProperties.get("file_name_haoyl_no");
			String type = Constant.getProperties.get("file_name_haoyl_type");
			String version = Constant.getProperties
					.get("file_name_haoyl_version");
			String dateStr = Constant.getProperties
					.get("file_name_haoyl_dateString");
			String date = DateUtils.formatDate(new Date(), dateStr);
			fileName = fileName + no + "_" + type + version + date;
//			if (!currentDate.equals(date)) {
//				batchNo = 1;
//				currentDate = date;
//			}
//			fileName = fileName + StringUtils.doNumFormat(batchNo, "_00000");
		}else if(DeductPlat.ZJPT.value.equals(modelFlag)){
			fileName = fileName + "001572_S"+DateUtils.getDate("yyyyMMdd");
		}else{
			fileName = fileName + "200100000015107_S02" + DateUtils.getDate("yyyyMMdd");;
		}
		return fileName;
	}
}
