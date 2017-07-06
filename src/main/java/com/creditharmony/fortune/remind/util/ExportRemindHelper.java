package com.creditharmony.fortune.remind.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 导出回息数据帮助类
 * @Class Name ExportRemindHelper
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExportRemindHelper {

	private static Logger logger = LoggerFactory.getLogger(ExportRemindHelper.class);
	
	
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
							"com.creditharmony.fortune.remind.dao.RemindDao.exportExlRemind",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(FileType.DQ_TXLB.getName());
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(FileType.DQ_TXLB.getName()+" .xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(FileType.DQ_TXLB.getName()+".xlsx"));
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
		// 出借编号
		String loanCode;
		// 客户姓名	 
		String customerName;
		// 出借日期
		String lendDay;
		// 出借金额
		String lendMoney;
		// 出借方式
		String dictLendType;
		// 到期日期
		String dueDay;
		//联系方式
		String tel;
		//门店名称
		String menDianMingCheng;
		//所在城市
		String souZaiCity;
		// 开户行
		String accountBank;
		// 具体支行
		String accountBranch;
		// 银行账号
		String bankCode;
		//付款方式
		String payType;
		Row dataRow;
		Cell lendCodeCell;
		Cell customerNameCell;
		Cell lendDayCell;
		Cell lendMoneyCell;
		Cell dictLendTypeCell;
		Cell dueDayCell;
		Cell telCell;
		Cell menDianMingChengCell;
		Cell souZaiCityCell;
		Cell accountBankCell;
		Cell accountBranchCell;
		Cell bankCodeCell;
		Cell payTypeCell;
		while (resultSet.next()) {
			loanCode = resultSet.getString("loanCode");
			customerName = resultSet.getString("customer_name");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendDay = resultSet.getString("lend_day");
			lendMoney = resultSet.getString("lend_money");
			dictLendType = resultSet.getString("dictLendType");
			dueDay = resultSet.getString("due_day");
			tel = resultSet.getString("tel");
			// 屏蔽客户姓名/手机号/身份证号
			tel = "***";
 			menDianMingCheng = resultSet.getString("menDianMingCheng");
			souZaiCity = resultSet.getString("areaName");
//			accountBank = resultSet.getString("bank_name");
			List<Dict> tz_open_bank = ConstantField.deductDictMap.get("tz_open_bank");
			accountBank = FortuneDictUtil.dictName(tz_open_bank, resultSet.getString("bank_name"), "");
			accountBranch = resultSet.getString("accountBranch");
			bankCode = resultSet.getString("bankCode");
			List<Dict> dicts = ConstantField.deductDictMap.get("tz_pay_type");
			payType = FortuneDictUtil.dictName(dicts, resultSet.getString("dictPayType"), "");

			dataRow = dataSheet.createRow(row);
			lendCodeCell = dataRow.createCell(0);
			lendCodeCell.setCellValue(loanCode);
			
			customerNameCell=dataRow.createCell(1);
			customerNameCell.setCellValue(customerName);
			
			lendDayCell=dataRow.createCell(2);
			lendDayCell.setCellValue(lendDay);
			
			
			lendMoneyCell=dataRow.createCell(3);
			lendMoneyCell.setCellValue(lendMoney);
			
			dictLendTypeCell=dataRow.createCell(4);
			dictLendTypeCell.setCellValue(dictLendType);
			
			dueDayCell=dataRow.createCell(5);
			dueDayCell.setCellValue(dueDay);
			
			telCell=dataRow.createCell(6);
			telCell.setCellValue(tel);
			
			 menDianMingChengCell=dataRow.createCell(7);
			menDianMingChengCell.setCellValue(menDianMingCheng); 
			
			souZaiCityCell=dataRow.createCell(8);
			souZaiCityCell.setCellValue(souZaiCity);
			
			
			accountBankCell=dataRow.createCell(9);
			accountBankCell.setCellValue(accountBank);
			
			
			
		 	accountBranchCell=dataRow.createCell(10);
			accountBranchCell.setCellValue(accountBranch); 
			
			
			bankCodeCell=dataRow.createCell(11);
			bankCodeCell.setCellValue(bankCode);
			
			
			payTypeCell=dataRow.createCell(12);
			payTypeCell.setCellValue(payType);
			
			
			row = row + 1;
		}
	}
	

	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		Cell lendCodeHeader = headerRow.createCell(0);
		lendCodeHeader.setCellValue("出借编号");
		
		Cell customerNameHeader = headerRow.createCell(1);
		customerNameHeader.setCellValue("客户姓名");
		
		Cell lendDayHeader = headerRow.createCell(2);
		lendDayHeader.setCellValue("出借日期");
		
		Cell lendMoneyHeader = headerRow.createCell(3);
		lendMoneyHeader.setCellValue("出借金额");
		
		Cell dictLendTypeHeader = headerRow.createCell(4);
		dictLendTypeHeader.setCellValue("出借方式");
		
		Cell dueDayHeader = headerRow.createCell(5);
		dueDayHeader.setCellValue("到期日期");
		
		Cell telHeader = headerRow.createCell(6);
		telHeader.setCellValue("联系方式");
		
 		Cell menDianMingChengHeader = headerRow.createCell(7);
 		menDianMingChengHeader.setCellValue("门店名称");
		
		Cell souZaiCityHeader = headerRow.createCell(8);
		souZaiCityHeader.setCellValue("所在城市");
		
		Cell accountBankHeader = headerRow.createCell(9);
		accountBankHeader.setCellValue("开户行");
		
 		Cell laccountBranchHeader = headerRow.createCell(10);
 		laccountBranchHeader.setCellValue("具体支行");
		
		Cell bankCodeHeader = headerRow.createCell(11);
		bankCodeHeader.setCellValue("银行账号");
		
		Cell payTypeHeader = headerRow.createCell(12);
		payTypeHeader.setCellValue("付款方式");
		
	}

}
