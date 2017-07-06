package com.creditharmony.fortune.contract.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.contract.constant.Constant;

/**
 * 导出回息数据帮助类
 * 
 * @Class Name ExporContractExcel
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExporContractExcel {

	private static Logger logger = LoggerFactory
			.getLogger(ExporContractExcel.class);

	/**
	 * 导出工具 2016年4月15日 by 李志伟
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param response
	 *            响应对象
	 * @param fileName
	 *            导出文件名字
	 * @param sqlNameSpace
	 *            查询数据用的sql名称及语句路劲
	 * @param sheetName
	 *            sheet名称
	 */
	public static void exportData(Map<String, Object> queryMap,
			HttpServletResponse response) {

		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {

			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							"com.creditharmony.fortune.contract.dao.ContractDao.getlistContractChange",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(Constant.CONTRACT_CHANGE_PREFIX);
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(Constant.CONTRACT_CHANGE_PREFIX+".xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(Constant.CONTRACT_CHANGE_PREFIX+".xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("exportData()导出数据出现异常");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		 //门店
		 String name; 
		 //合同编号
		 String contCode;		
		//合同版本 
		 String contVersion;	
		//申请日期 
		 String applyDay;       
		//使用日期 
		 String useDay;
		 //合同归属
		 String contBelong;	
		//合同状态 
		 String dictContStatus;
		Row dataRow;
		 //门店
		 Cell nameCell; 
		 //合同编号
		 Cell contCodeCell;		
		//合同版本 
		 Cell contVersionCell;	
		//申请日期 
		 Cell applyDayCell;       
		//使用日期 
		 Cell useDayCell;
		 //合同归属
		 Cell contBelongCell;	
		//合同状态 
		 Cell dictContStatusCell;
		while (resultSet.next()) {
			// 门店
			name = resultSet.getString("name");
			// 合同编号
			contCode = resultSet.getString("contCode");
			// 合同版本
			contVersion = resultSet.getString("contVersion");
			// 申请日期
			applyDay = resultSet.getString("applyDay");
			// 使用日期
			useDay = resultSet.getString("useDay");
			// 合同归属
			contBelong = resultSet.getString("contBelong");
			// 合同状态
			dictContStatus = resultSet.getString("dictContStatus");
			dataRow = dataSheet.createRow(row);
			// 门店
			nameCell = dataRow.createCell(0);
			nameCell.setCellValue(name);
			// 合同编号
			contCodeCell = dataRow.createCell(1);
			contCodeCell.setCellValue(contCode);
			// 合同版本
			contVersionCell = dataRow.createCell(2);
			contVersionCell.setCellValue(contVersion);
			// 申请日期
			applyDayCell = dataRow.createCell(3);
			applyDayCell.setCellValue(applyDay);
			// 使用日期
			useDayCell = dataRow.createCell(4);
			useDayCell.setCellValue(useDay);
			// 合同归属
			contBelongCell = dataRow.createCell(5);
			contBelongCell.setCellValue(contBelong);
			// 合同状态
			dictContStatusCell = dataRow.createCell(6);
			dictContStatusCell.setCellValue(dictContStatus);
			row = row + 1;
		}
	}

	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		// 门店
		Cell nameHeader = headerRow.createCell(0);
		nameHeader.setCellValue("门店");
		// 合同编号
		Cell contCodeHeader = headerRow.createCell(1);
		contCodeHeader.setCellValue("合同编号");
		// 合同版本
		Cell contVersionHeader = headerRow.createCell(2);
		contVersionHeader.setCellValue("合同版本");
		// 申请日期
		Cell applyDayHeader = headerRow.createCell(3);
		applyDayHeader.setCellValue("申请日期");
		// 使用日期
		Cell useDayHeader = headerRow.createCell(4);
		useDayHeader.setCellValue("使用日期");
		// 合同归属
		Cell contBelongHeader = headerRow.createCell(5);
		contBelongHeader.setCellValue("合同归属");
		// 合同状态
		Cell dictContStatusHeader = headerRow.createCell(6);
		dictContStatusHeader.setCellValue("合同状态");
	}

}
