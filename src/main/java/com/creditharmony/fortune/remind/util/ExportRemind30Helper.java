package com.creditharmony.fortune.remind.util;

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
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;

/**
 * 导出回息数据帮助类
 * @Class Name ExportRemind30Helper
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExportRemind30Helper {

	private static Logger logger = LoggerFactory.getLogger(ExportRemind30Helper.class);
	
	
	/**
	 * 导出工具
	 * 2016年4月15日
	 * by 李志伟
	 * @param queryMap 查询条件
	 * @param response	响应对象
	 * @param fileName	导出文件名字
	 * @param sqlNameSpace	查询数据用的sql名称及语句路劲
	 * @param sheetName	sheet名称
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
							"com.creditharmony.fortune.remind.dao.RemindDao.exportExlRemind30",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(FileType.Q30TDQ_TXLB.getName());
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(FileType.Q30TDQ_TXLB.getName()+" .xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(FileType.Q30TDQ_TXLB.getName()+".xlsx"));
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
		 
		Row dataRow;
		// 客户姓名	 
		 String customerName;
		// 手机号	 
		 String tel;
		// 出借日期	 
		 String lendDay;
		// 出借金额	 
		 String lendMoney;
		// 出借方式 
		 String dictLendType;
		// 封闭期满日	 
		 String productClosedate; 
		 
		// 客户姓名
		Cell customerNameCell;
		// 手机号
		Cell telCell;
		// 出借日期
		Cell lendDayCell;
		// 出借金额
		Cell lendMoneyCell;
		// 出借方式
		Cell dictLendTypeCell;
		// 封闭期满日
		Cell productClosedateCell;

		while (resultSet.next()) {
			customerName = resultSet.getString("customerName");
			tel = resultSet.getString("tel");
			lendDay = resultSet.getString("lendDay");
			lendMoney = resultSet.getString("lendMoney");
			dictLendType = resultSet.getString("dictLendType");
			productClosedate = resultSet.getString("productClosedate");

			dataRow = dataSheet.createRow(row);
			
			customerNameCell = dataRow.createCell(0);
			customerNameCell.setCellValue(customerName);
			
			telCell = dataRow.createCell(1);
			telCell.setCellValue(tel);
			
			lendDayCell = dataRow.createCell(2);
			lendDayCell.setCellValue(lendDay);
			
			lendMoneyCell = dataRow.createCell(3);
			lendMoneyCell.setCellValue(lendMoney);
			
			dictLendTypeCell = dataRow.createCell(4);
			dictLendTypeCell.setCellValue(dictLendType);
			
			productClosedateCell = dataRow.createCell(5);
			productClosedateCell.setCellValue(productClosedate);
			
			row = row + 1;
		}
	}
	

	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		Cell customerNameHeader = headerRow.createCell(0);
		customerNameHeader.setCellValue("客户姓名");
		
		Cell telHeader = headerRow.createCell(1);
		telHeader.setCellValue("手机号");
		
		Cell lendDayHeader = headerRow.createCell(2);
		lendDayHeader.setCellValue("出借日期");
		
		Cell lendMoneyHeader = headerRow.createCell(3);
		lendMoneyHeader.setCellValue("出借金额");
		
		Cell dictLendTypeHeader = headerRow.createCell(4);
		dictLendTypeHeader.setCellValue("出借方式");
		
		Cell productClosedateHeader = headerRow.createCell(5);
		productClosedateHeader.setCellValue("封闭期满日");
		
	}

}
