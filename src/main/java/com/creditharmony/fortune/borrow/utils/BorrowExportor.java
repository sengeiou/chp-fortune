package com.creditharmony.fortune.borrow.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.constants.BorrowConstant;

/**
 * 导出债权Excel工具类
 * @Class Name BorrowExportor
 * @author 陈广鹏
 * @Create In 2016年10月28日
 */
public class BorrowExportor {
	
	private String filename; // 文件名

	private String sheetname = "DataList"; // 工作表名
	
	private String namespace = BorrowConstant.BORROW_EXPORT; // 查询 SQL ID

	public BorrowExportor(String filename) {
		this.filename = filename;
	}

	private static Logger logger = LoggerFactory.getLogger(BorrowExportor.class);
	
	/**
	 * 导出Excel 
	 * 2016年10月28日
	 * By 陈广鹏
	 * @param queryMap
	 * @param response
	 * @param namespace
	 */
	public void exportData(Map<String, Object> queryMap,HttpServletResponse response) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(namespace,queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			// 生成工作表
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(sheetname);
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(filename+".xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(filename+".xlsx"));
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
	
	/**
	 * 数据组装
	 * 2016年10月28日
	 * By 陈广鹏
	 * @param resultSet
	 * @param dataSheet
	 * @param token
	 * @throws SQLException
	 */
	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws SQLException {
		String loanContractNo; 			// 借款人合同号
		String loanName; 				// 借款人
		String loanIdcard; 				// 借款人身份证号
		String loanPurpose; 			// 借款用途
		String loanProduct; 			// 借款产品
		String loanMonths; 				// 借款期数
		String loanBackmoneyFirday; 	// 首次还款日
		String loanBackmoneyLastday; 	// 截止还款日期
		String loanBackmoneyDay; 		// 还款日
		String loanQuota; 				// 原始债权价值
		String loanMonthRate; 			// 月利率
		String loanCreditValue; 		// 可用债权价值
		String loanMonthsSurplus; 		// 可用期数
		
		Row dataRow;
		int row = 1;
		while (resultSet.next()) {
			dataRow = dataSheet.createRow(row);
			loanContractNo = resultSet.getString("loan_contract_no");
			loanName = resultSet.getString("loan_name");
			loanIdcard = resultSet.getString("loan_idcard");
			// 屏蔽客户姓名/手机号/身份证号
			loanName = "***";
			loanIdcard = "***";
			loanPurpose = resultSet.getString("loan_purpose");
			loanProduct = resultSet.getString("loan_product");
			loanMonths = resultSet.getString("loan_months");
			loanBackmoneyFirday = resultSet.getString("loan_backmoney_firday");
			loanBackmoneyLastday = resultSet.getString("loan_backmoney_lastday");
			loanBackmoneyDay = resultSet.getString("loan_backmoney_day");
			loanQuota = resultSet.getString("loan_quota");
			loanMonthRate = resultSet.getString("loan_month_rate");
			loanCreditValue = resultSet.getString("loan_credit_value");
			loanMonthsSurplus = resultSet.getString("loan_months_surplus");

			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(loanContractNo);
			dataRow.createCell(1).setCellValue(loanName);
			dataRow.createCell(2).setCellValue(loanIdcard);
			dataRow.createCell(3).setCellValue(loanPurpose);
			dataRow.createCell(4).setCellValue(loanProduct);
			dataRow.createCell(5).setCellValue(loanMonths);
			dataRow.createCell(6).setCellValue(loanBackmoneyFirday);
			dataRow.createCell(7).setCellValue(loanBackmoneyLastday);
			dataRow.createCell(8).setCellValue(loanBackmoneyDay);
			dataRow.createCell(9).setCellValue(FormatUtils.formatNumber(loanQuota));
			dataRow.createCell(10).setCellValue(FormatUtils.formatNumberRate(loanMonthRate));
			dataRow.createCell(11).setCellValue(FormatUtils.formatNumber(loanCreditValue));
			dataRow.createCell(12).setCellValue(loanMonthsSurplus);
			row++;
		}
	}

	/**
	 * 表头 
	 * 2016年10月28日 
	 * By 陈广鹏
	 * @param dataSheet
	 * @param token
	 */
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("借款人合同号");
		headerRow.createCell(1).setCellValue("借款人");
		headerRow.createCell(2).setCellValue("借款人身份证号");
		headerRow.createCell(3).setCellValue("借款用途");
		headerRow.createCell(4).setCellValue("借款产品");
		headerRow.createCell(5).setCellValue("借款期数");
		headerRow.createCell(6).setCellValue("首次还款日");
		headerRow.createCell(7).setCellValue("截止还款日期");
		headerRow.createCell(8).setCellValue("还款日");
		headerRow.createCell(9).setCellValue("原始债权价值");
		headerRow.createCell(10).setCellValue("月利率");
		headerRow.createCell(11).setCellValue("可用债权价值");
		headerRow.createCell(12).setCellValue("可用期数");
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	
}
