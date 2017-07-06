package com.creditharmony.fortune.customer.service;

import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.fortune.customer.util.CustomExportorUtil;

/**
 * 导出客户明细
 * @Class Name CustomerExportor
 * @author liusl
 * @Create In 2016年10月10日
 */
public class CustomerExportor extends CustomExportorUtil {

	public CustomerExportor(String filename) {
		super(filename);
	}

	/**
	 * 填充单元格
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws Exception {
		int row = 1;
		String customerName;
		String customerCode;
		String tellPhone;
		String provinceName;
		String cityName;
		String storesName;
		String teamManagerName;
		String managerName;
		String teamName;
		String dictCustSource;
		String createTime;
		String dataTerminal;
		String custLending;
		
		Row dataRow;
		while (resultSet.next()) {
			//客户姓名、客户编号、手机号（不加密）、省份、城市、营业部、团队经理、理财经理、开发团队、客户来源、创建日期、数据来源、投资状态
			//屏蔽客户姓名/手机号/身份证号
			//客户姓名
			customerName = resultSet.getString("customer_name");
			customerName = "***";
			//客户编号
			customerCode = resultSet.getString("customer_code");
			//手机号
			tellPhone = resultSet.getString("customer_mobilephone");
			tellPhone = "***";
			//省份			
			provinceName = resultSet.getString("provinceName");
			//城市
			cityName = resultSet.getString("cityName");
			//营业部
			storesName = resultSet.getString("storesName");
			//团队经理
			teamManagerName = resultSet.getString("team_manager_name");
			//理财经理
			managerName = resultSet.getString("manager_name");
			//开发团队
			teamName = resultSet.getString("team_org_name");
			//客户来源
			dictCustSource = resultSet.getString("dict_customer_source_name");
			//创建日期
			createTime = resultSet.getString("create_time");
			
			//数据来源
			dataTerminal = resultSet.getString("customer_terminal_name");
			//投资状态
			custLending = resultSet.getString("customer_lending_name");
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(customerCode);
			dataRow.createCell(2).setCellValue(tellPhone);
			dataRow.createCell(3).setCellValue(provinceName);
			dataRow.createCell(4).setCellValue(cityName);
			dataRow.createCell(5).setCellValue(storesName);
			dataRow.createCell(6).setCellValue(teamManagerName);
			dataRow.createCell(7).setCellValue(managerName);
			dataRow.createCell(8).setCellValue(teamName);
			dataRow.createCell(9).setCellValue(dictCustSource);
			dataRow.createCell(10).setCellValue(createTime);
			dataRow.createCell(11).setCellValue(dataTerminal);
			dataRow.createCell(12).setCellValue(custLending);
			row++;
		}

	}

	/**
	 * 设置表头
	 * 导出EXCEL列：客户姓名、客户编号、手机号（不加密）、省份、城市、营业部、
	 * 团队经理、理财经理、开发团队、客户来源、创建日期、数据来源、投资状态
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("客户姓名");
		headerRow.createCell(1).setCellValue("客户编号");
		headerRow.createCell(2).setCellValue("手机号");
		headerRow.createCell(3).setCellValue("省份");
		headerRow.createCell(4).setCellValue("城市");
		headerRow.createCell(5).setCellValue("营业部");
		headerRow.createCell(6).setCellValue("团队经理");
		headerRow.createCell(7).setCellValue("理财经理");
		headerRow.createCell(8).setCellValue("开发团队");
		headerRow.createCell(9).setCellValue("客户来源");
		headerRow.createCell(10).setCellValue("创建日期");
		headerRow.createCell(11).setCellValue("数据来源");
		headerRow.createCell(12).setCellValue("投资状态");
	}
}