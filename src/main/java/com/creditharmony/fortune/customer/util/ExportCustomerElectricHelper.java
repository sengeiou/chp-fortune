package com.creditharmony.fortune.customer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.creditharmony.core.fortune.type.CustomerScource;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.DataSource;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.customer.entity.Customer;


public class ExportCustomerElectricHelper {
	
	private String filename; // 文件名

	private String sheetname = "DataList"; // 工作表名
	
	private String namespace = "com.creditharmony.fortune.customer.dao.CustomerDao.findInvestElectricExcel"; // 查询 SQL ID

	public ExportCustomerElectricHelper(String filename) {
		this.filename = filename;
	}

	private static Logger logger = LoggerFactory.getLogger(ExportCustomerElectricHelper.class);
	

	public void exportData(Customer view,HttpServletResponse response) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(namespace,view, sqlSessionFactory);
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
		String customerName; 			// 姓名
		String lendCode; 				// 出借编号
		String lendDate; 				// 计划出借日期
		String applyLendMoney; 			// 计划出借金额
		String productName; 			// 产品名称
		String applyExpireDay; 				// 产品到期日
		String manager; 	// 理财经理
		String orgName; 	// 营业部
		String provinceName; 		// 账户所在省
		String cityName; 				// 账户所在城市
		String accountBank; 			// 回款开户行	
		String accountBranch; 		// 回款支行名称
		String accountNo; 		// 账号
		String applyDeductMoney; 		// 应回款金额
		String backActualbackMoney; 		// 实际回款金额
		String backMoneytype; 		// 回款类型
		
		Row dataRow;
		int row = 1;
		while (resultSet.next()) {
			dataRow = dataSheet.createRow(row);
			//customerName = resultSet.getString("customer_name");
			customerName = "***";
			lendCode = resultSet.getString("customer_code");
			lendDate = resultSet.getString("customer_mobilephone");
			applyLendMoney = resultSet.getString("old_province_name");
			productName = resultSet.getString("old_city_name");
			applyExpireDay = resultSet.getString("old_org_name");
			manager = resultSet.getString("storesName");
			orgName = resultSet.getString("team_manager_name");
			provinceName = resultSet.getString("manager_name");
			cityName = resultSet.getString("team_org_name");
			accountBank = resultSet.getString("dict_customer_source")==null?"":CustomerScource.customerScourceMap.get(resultSet.getString("dict_customer_source"));
			accountBranch = resultSet.getString("create_time");
			accountNo = resultSet.getString("customer_terminal")==null?"":DataSource.dataSourceMap.get(resultSet.getString("customer_terminal"));
			applyDeductMoney = resultSet.getString("dict_customer_state")==null?"":CustomerState.customerStateMap.get(resultSet.getString("dict_customer_state"));
			backActualbackMoney = resultSet.getString("invest_state_label");
			backMoneytype = resultSet.getString("transfer_state_label");

			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			//屏蔽客户姓名/手机号/身份证号
//			dataRow.createCell(2).setCellValue(lendDate);
			dataRow.createCell(2).setCellValue("*******");
			dataRow.createCell(3).setCellValue(applyLendMoney);
			dataRow.createCell(4).setCellValue(productName);
			dataRow.createCell(5).setCellValue(applyExpireDay);
			dataRow.createCell(6).setCellValue(manager);
			dataRow.createCell(7).setCellValue(orgName);
			dataRow.createCell(8).setCellValue(provinceName);
			dataRow.createCell(9).setCellValue(cityName);
			dataRow.createCell(10).setCellValue(accountBank);
			dataRow.createCell(11).setCellValue(accountBranch);
			dataRow.createCell(12).setCellValue(accountNo);
			dataRow.createCell(13).setCellValue(applyDeductMoney);
			dataRow.createCell(14).setCellValue(backActualbackMoney);
			dataRow.createCell(15).setCellValue(backMoneytype);
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
		headerRow.createCell(0).setCellValue("客户姓名");
		headerRow.createCell(1).setCellValue("客户编号");
		headerRow.createCell(2).setCellValue("手机号");
		headerRow.createCell(3).setCellValue("原省份");
		headerRow.createCell(4).setCellValue("原城市");
		headerRow.createCell(5).setCellValue("原营业部");
		headerRow.createCell(6).setCellValue("营业部");
		headerRow.createCell(7).setCellValue("团队经理");
		headerRow.createCell(8).setCellValue("理财经理");
		headerRow.createCell(9).setCellValue("开发团队");
		headerRow.createCell(10).setCellValue("客户来源");
		headerRow.createCell(11).setCellValue("创建日期");
		headerRow.createCell(12).setCellValue("数据来源");
		headerRow.createCell(13).setCellValue("开户状态");
		headerRow.createCell(14).setCellValue("投资状态");
		headerRow.createCell(15).setCellValue("转赠状态");
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
