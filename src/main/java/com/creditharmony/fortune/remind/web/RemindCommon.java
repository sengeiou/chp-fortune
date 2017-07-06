package com.creditharmony.fortune.remind.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.web.BaseController;

/**
 * 消息提醒公共Controller
 * @Class Name RemindCommon
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class RemindCommon extends BaseController{
	
	// 前30天封闭期提醒标识---默认值是前10天到期提醒
	public final String remind30Flag="30das";
	
	// 到期提醒标识---默认值是前10天到期提醒
	public final String remindFlag="das";

	/**
	 * 导出excel
	 * 2015年11月27日
	 * By 韩龙
	 * @param dataList 导出数据源
	 * @param cla 导出对象实体类
	 * @param fileName 文件名
	 * @param response HttpServletResponse对象
	 */
	public void outExcelCommon(List<?> dataList, Class<?> cla,String fileName,HttpServletResponse response) {
		// 构建导出excel对象
		ExportExcel exportExcel=new ExportExcel(null,cla);
		// 设置导出数据源
		exportExcel.setDataList(dataList);
		try {
			this.logger.info("写出文件到客户端"+fileName+".xlsx");
			// 写出文件到客户端
			exportExcel.write(response, fileName+".xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			this.logger.error("前10天到期提醒列表导出失败", e);
		}
	}
}
