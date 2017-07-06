package com.creditharmony.fortune.deduct.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportDeductHelper extends BaseExportExcel{

	private static Logger logger = LoggerFactory.getLogger(ExportDeductHelper.class);
	
	/**
	 * 遍历集合
	 * 2016年4月18日
	 * By 韩龙
	 * @param resultSet 数据集合
	 * @param dataSheet excel Sheet对象
	 * @param fields    数据库字段名
	 * @throws SQLException 
	 */
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet,String [][] fields) throws SQLException {
		int row = 1;
		Row dataRow;
		while (resultSet.next()) {
			// 创建行
			dataRow = dataSheet.createRow(row);
			setCell(resultSet, fields, dataRow);
			row = row + 1;
		}
	}

	
	
	/**
	 * 遍历集合
	 * 2016年4月18日
	 * By 韩龙
	 * @param resultSet 数据集合
	 * @param dataSheet excel Sheet对象
	 * @param fields    数据库字段名
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		// 获取查询表头信息
		ResultSetMetaData rsmd = resultSet.getMetaData();
		// 获取查询的字段总数
		int fields = rsmd.getColumnCount();
		int row = 1;
		Row dataRow;
		while (resultSet.next()) {
			dataRow = dataSheet.createRow(row);
			for (int i = 0; i < fields; i++) {
				Cell cell = dataRow.createCell(i);
				// 获取字段名字
				String columnLabel = rsmd.getColumnLabel(i);
				// 获取对应字段的值
				String value = resultSet.getString(columnLabel);
				cell.setCellValue(value);
			}
			row = row + 1;
		}
	}
	
	/**
	 * excel表头
	 * 2016年4月18日
	 * By 韩龙
	 * @param dataSheet
	 * @param title
	 */
	public void wrapperHeader(Sheet dataSheet,String title) {
		String [] titles = title.split(",");
		Row headerRow = dataSheet.createRow(0);
		for (int i = 0; i < titles.length; i++) {
			Cell cell = headerRow.createCell(i);
//			cell.setCellStyle(styles.get("title"));
			cell.setCellValue(titles[i]);
		}
	}

}
