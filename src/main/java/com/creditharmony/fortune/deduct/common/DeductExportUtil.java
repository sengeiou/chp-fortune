package com.creditharmony.fortune.deduct.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.fortune.common.ExportType;
import com.creditharmony.fortune.template.entity.DeductFuyouExportModel;
import com.creditharmony.fortune.template.entity.DeductHaoylExportModel;
import com.creditharmony.fortune.template.entity.DeductTongLianExportModel;
import com.creditharmony.fortune.template.entity.DeductZhongJinExportModel;
import com.creditharmony.fortune.utils.FormatUtils;
import com.google.common.collect.Lists;

/**
 * 导出类
 * @Class Name DeductExportUtil
 * @author 韩龙
 * @Create In 2016年3月3日
 */
public class DeductExportUtil {
	
	private String flieName;
	
	protected Logger logger = LoggerFactory.getLogger(DeductSplitExcelUtil.class);

	/**
	 * 根据不同平台导出excel
	 * 2016年2月17日
	 * By 韩龙
	 * @param response
	 * @throws IOException 
	 */
	public void outExcel(List<DeductExportExcel> list,String deductPlatId,String exportType,String fileName,Map<String,InputStream> map) throws IOException{
		this.flieName = fileName;
		int index = 1;
		BigDecimal sumMoney = new BigDecimal("0");
		if(deductPlatId.equals(DeductPlat.FYPT.value)){
			// 富有
			List<DeductFuyouExportModel> newList = Lists.newArrayList();
			// 循环赋值FormatUtils
			for (DeductExportExcel deductExportExcel : list) {
				DeductFuyouExportModel deductFuyouExportModel1 = new DeductFuyouExportModel();
				BigDecimal deductMoney = new BigDecimal(deductExportExcel.getDeductMoney());
				sumMoney = sumMoney.add(deductMoney);
				BeanUtils.copyProperties(deductExportExcel, deductFuyouExportModel1);
				deductFuyouExportModel1.setDeductMoney(FormatUtils.getFormatNumber(deductMoney,"#000.00"));
				deductFuyouExportModel1.setIndex(index + "");
				deductFuyouExportModel1.setFuRemarks("代收");
				String lendCode = DeductUtils.isNull("财富划扣_" + deductExportExcel.getLendCode() + "_"
						+ IdGen.uuid());
				deductFuyouExportModel1.setLendCode(lendCode);
				index++;
				newList.add(deductFuyouExportModel1);
			}
			exportFyExcel(newList,map);
		}else if(deductPlatId.equals(DeductPlat.HYLPT.value)){
			// 好易联
			StringBuffer buf = new StringBuffer();
			List<DeductHaoylExportModel> newList = Lists.newArrayList();
			for (DeductExportExcel deductExportExcel : list) {
				DeductHaoylExportModel deductHaoylExportModel1 = new DeductHaoylExportModel();
				sumMoney = sumMoney.add(new BigDecimal(deductExportExcel.getDeductMoney()));
				BeanUtils.copyProperties(deductExportExcel, deductHaoylExportModel1);
				deductHaoylExportModel1.setIndex(index + "");
				BigDecimal deductMoney = new BigDecimal(deductHaoylExportModel1.getDeductMoney()).multiply(new BigDecimal(100));
				deductHaoylExportModel1.setDeductMoney(FormatUtils.getFormatNumber(deductMoney,"#000"));
				buf.append(deductHaoylExportModel1.toString());
				index++;
				newList.add(deductHaoylExportModel1);
			}
			if(ExportType.XLSX.getCode().equals(exportType)){
				String sum = FormatUtils.getFormatNumber(sumMoney.multiply(new BigDecimal(100)),"#000");
				// 导出excel
				exportHylExcel(newList,getTitle(sum, list.size() + "",deductPlatId),map);
			}else{
				// 导出txt
				String sum = FormatUtils.getFormatNumber(sumMoney.multiply(new BigDecimal(100)),"#000");
				String title = getTitleTex(sum,newList.size() + "");
				buf.insert(0,title + "\r\n");
				exportTxt(buf,map);
			}
			
		}else if(deductPlatId.equals(DeductPlat.ZJPT.value)){
			// 中金 
			StringBuffer buf = new StringBuffer();
			List<DeductZhongJinExportModel> newList = Lists.newArrayList();
			for (DeductExportExcel deductExportExcel : list) {
				DeductZhongJinExportModel deductZhongJinExportModel = new DeductZhongJinExportModel();
				sumMoney = sumMoney.add(new BigDecimal(deductExportExcel.getDeductMoney()));
				BeanUtils.copyProperties(deductExportExcel, deductZhongJinExportModel);
				deductZhongJinExportModel.setBankName(deductExportExcel.getAccountBank());
				deductZhongJinExportModel.setIndex(index + "");
				BigDecimal sum = new BigDecimal(deductZhongJinExportModel.getDeductMoney());
				deductZhongJinExportModel.setDeductMoney(FormatUtils.getFormatNumber(sum,"#000.00"));
				deductZhongJinExportModel.setSettlementMark("0001");
				deductZhongJinExportModel.setAccountType("个人");
				String remark = "财富划扣_" + deductExportExcel.getLendCode() + "_" + IdGen.uuid();
				deductZhongJinExportModel.setZhongRemarks(remark);
				buf.append(deductZhongJinExportModel.toString());
				index++;
				newList.add(deductZhongJinExportModel);
			}
			if(ExportType.XLSX.getCode().equals(exportType)){
				// 导出excel
				exportZjExcel(newList,map);
			}else{
				// 导出txt
				exportTxt(buf,map);
			}
		}else{
			// 通联
			List<DeductTongLianExportModel> newList = Lists.newArrayList();
			for (DeductExportExcel deductExportExcel : list) {
				DeductTongLianExportModel deductTongLianExportModel = new DeductTongLianExportModel();
				sumMoney = sumMoney.add(new BigDecimal(deductExportExcel.getDeductMoney()));
				BeanUtils.copyProperties(deductExportExcel, deductTongLianExportModel);
				deductTongLianExportModel.setIndex(index + "");
				BigDecimal deductMoney = new BigDecimal(deductTongLianExportModel.getDeductMoney()).multiply(new BigDecimal(100));
				deductTongLianExportModel.setDeductMoney(FormatUtils.getFormatNumber(deductMoney,"#000"));
				String remark = "财富划扣_" + deductExportExcel.getLendCode() + "_" + IdGen.uuid();
				deductTongLianExportModel.setTongRemarks(remark);
				index++;
				newList.add(deductTongLianExportModel);
			}
			exportTlExcel(newList,getTitle(FormatUtils.getFormatNumber(sumMoney.multiply(new BigDecimal(100)),"#000"), list.size() + "" ,deductPlatId),map);
		}
	}
	
	/**
	 * 导出通联
	 * 2016年2月17日
	 * By 韩龙
	 * @param dateList
	 * @param title
	 */
	private void exportTlExcel(List<DeductTongLianExportModel> dateList,List<String> title,Map<String,InputStream> mapFile){
		ExportExcel ee = new ExportExcel("", title);
		ee.reExportExcel(null, DeductTongLianExportModel.class,1);
		ee.setDataList(dateList);
		addInputStream(ee,mapFile);
	}
	
	/**
	 * 导出好易联
	 * 2016年2月17日
	 * By 韩龙
	 * @param dateList
	 * @param title
	 */
	private void exportHylExcel(List<DeductHaoylExportModel> dateList,List<String> title,Map<String,InputStream> mapFile){
		String[] heads = "代收付类型,商户ID,提交日期,总记录数,总金额,业务类型".split(",");
		ExportExcel ee = new ExportExcel(null, heads);
		if(dateList.size() > 0){
			for (int i = 0; i < 2; i++) {
				// 第一表头信息
				if (i == 0) {
					Row row = ee.addRow();
					for (int j = 0; j < heads.length; j++) {
						ee.addCell(row, j, title.get(j), 2, null);
					}
					continue;
				}
				// 设置第二表头与数据
				ee.reExportExcel(null,DeductHaoylExportModel.class, 1);
				ee.setDataList(dateList);
			}
		}
		addInputStream(ee,mapFile);
	}
	
	/**
	 * 导出中金
	 * 2016年2月17日
	 * By 韩龙
	 * @param dateList
	 * @param title
	 */
	private void exportZjExcel(List<DeductZhongJinExportModel>  dateList,Map<String,InputStream> mapFile){
		ExportExcel ee = new ExportExcel(null, DeductZhongJinExportModel.class);
		ee.setDataList(dateList);
		addInputStream(ee,mapFile);
	}
	
	/**
	 * 导出富有
	 * 2016年2月17日
	 * By 韩龙
	 * @param dateList
	 * @param title
	 */
	private void exportFyExcel(List<DeductFuyouExportModel> dateList,Map<String,InputStream> mapFile){
		ExportExcel ee = new ExportExcel(null, DeductFuyouExportModel.class);
		ee.setDataList(dateList);
		addInputStream(ee,mapFile);
	}
	
	/**
	 * 导出txt
	 * 2016年3月4日
	 * By 韩龙
	 * @param bufer
	 * @param mapFile
	 */
	private void exportTxt(StringBuffer bufer,Map<String,InputStream> mapFile){
		InputStream input = new ByteArrayInputStream(bufer.toString().getBytes());
		mapFile.put(flieName, input);
	}
	
	/**
	 * 标题
	 * 2016年2月17日
	 * By 韩龙
	 * @param sumMoney
	 * @param total
	 * @return
	 */
	private List<String> getTitle(String sumMoney,String total,String type){
		List<String> title = Lists.newArrayList();
		title.add("S");
		title.add("200100000015107");
		title.add(DateUtils.getDate("yyyyMMdd"));
		title.add(total);
		title.add(sumMoney);
		if(type.equals(DeductPlat.HYLPT.value)){
			// 好易联
			title.add("14900");
		}else{
			// 通联
			title.add("10401");
		}
		
		return title;
	}
	
	/**
	 * 标题
	 * 2016年2月17日
	 * By 韩龙
	 * @param sumMoney
	 * @param total
	 * @return
	 */
	private String getTitleTex(String sumMoney,String total){
		StringBuffer title = new StringBuffer();
		title.append("S").append(",");
		title.append("200604000000445").append(",");
		title.append(DateUtils.getDate("yyyyMMdd")).append(",");
		title.append(total).append(",");
		title.append(sumMoney).append(",");
		title.append("14900").append(",");
		return title.toString();
	}
	
	/**
	 * 封装压缩excel对象
	 * 2016年3月3日
	 * By 韩龙
	 * @param ee
	 */
	private void addInputStream(ExportExcel ee,Map<String,InputStream> mapFile){
		try {
			// 初始化内存流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ee.write(out);
			ee.dispose();
			// 转换成内存输出流
			InputStream input = new ByteArrayInputStream(out.toByteArray());
			mapFile.put(flieName, input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
