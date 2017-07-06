package com.creditharmony.fortune.look.apply.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

/**
 * 导出回息数据帮助类
 * 
 * @Class Name ExportDeductExcel
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExportDeductExcel {

	private static Logger logger = LoggerFactory
			.getLogger(ExportDeductExcel.class);

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
							"com.creditharmony.fortune.look.apply.dao.LendApplyLookDao.getExportLendExcelList",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("出借申请查看（划扣）");
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode("出借申请查看（划扣）.xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode("出借申请查看（划扣）.xlsx"));
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
		// 财富中心
		String fortunCenter;
		// 客户姓名
		String custName;
		// 出借编号
		String lendCode;
		// 划扣日期
		String huakou;
		// 首次出借日期
		String applyLendDay;
		// 出借金额
		String applyLendMoney;
		// 划扣金额
		String applyDeductMoney;
		// 出借产品
		String productName;
		// 理财经理
		String managerCode;
		// 门店
		String storeName;
		// 划扣行别
		String openBank;
		// 付款方式
		String payType; // apply_pay
		// 反馈结果
		String feedBack;
		// 划扣平台
		String dictApplyDeductType;
		Row dataRow;
		// 财富中心
		Cell fortunCenterCell;
		// 客户姓名
		Cell custNameCell;
		// 出借编号
		Cell lendCodeCell;
		// 划扣日期
		Cell huakouCell;
		// 首次出借日期
		Cell applyLendDayCell;
		// 出借金额
		Cell applyLendMoneyCell;
		// 划扣金额
		Cell applyDeductMoneyCell;
		// 出借产品
		Cell productNameCell;
		// 理财经理
		Cell managerCodeCell;
		// 门店
		Cell storeNameCell;
		// 划扣行别
		Cell openBankCell;
		// 付款方式
		Cell payTypeCell; // apply_pay
		// 反馈结果
		Cell feedBackCell;
		// 划扣平台
		Cell dictApplyDeductTypeCell;
		while (resultSet.next()) {
			// 财富中心
			fortunCenter = resultSet.getString("fortunCenter");
			// 客户姓名
			custName = resultSet.getString("custName");
			// 屏蔽客户姓名/手机号/身份证号
			custName = "***";
			// 出借编号
			lendCode = resultSet.getString("lendCode");
			// 划扣日期
			huakou = resultSet.getString("huakou");
			// 首次出借日期
			applyLendDay = resultSet.getString("applyLendDay");
			// 出借金额
			applyLendMoney = resultSet.getString("applyLendMoney");
			// 划扣金额
			applyDeductMoney = resultSet.getString("applyDeductMoney");
			// 出借产品
			productName = resultSet.getString("productName");
			// 理财经理
			managerCode = resultSet.getString("managerCode");
			// 门店
			storeName = resultSet.getString("storeName");
			// 划扣行别
			openBank = resultSet.getString("openBank");
			// 付款方式
			payType = resultSet.getString("payType"); // apply_pay
			// 反馈结果
			feedBack = resultSet.getString("feedBack");
			// 划扣平台
			dictApplyDeductType = resultSet.getString("dictApplyDeductType");

			dataRow = dataSheet.createRow(row);
			// 财富中心
			fortunCenterCell = dataRow.createCell(0);
			fortunCenterCell.setCellValue(fortunCenter);
			// 客户姓名
			custNameCell = dataRow.createCell(1);
			custNameCell.setCellValue(custName);

			// 出借编号
			lendCodeCell = dataRow.createCell(2);
			lendCodeCell.setCellValue(lendCode);
			// 划扣日期

			huakouCell = dataRow.createCell(3);
			huakouCell.setCellValue(huakou);
			// 首次出借日期

			applyLendDayCell = dataRow.createCell(4);
			applyLendDayCell.setCellValue(applyLendDay);
			// 出借金额

			applyLendMoneyCell = dataRow.createCell(5);
			applyLendMoneyCell.setCellValue(getRound2(applyLendMoney));
			// 划扣金额

			applyDeductMoneyCell = dataRow.createCell(6);
			applyDeductMoneyCell.setCellValue(getRound2(applyDeductMoney));
			// 出借产品

			productNameCell = dataRow.createCell(7);
			productNameCell.setCellValue(productName);
			// 理财经理

			managerCodeCell = dataRow.createCell(8);
			managerCodeCell.setCellValue(managerCode);
			// 门店

			storeNameCell = dataRow.createCell(9);
			storeNameCell.setCellValue(storeName);
			// 划扣行别

			openBankCell = dataRow.createCell(10);
			openBankCell.setCellValue(openBank);
			// 付款方式
			// apply_pay
			payTypeCell = dataRow.createCell(11);
			payTypeCell.setCellValue(payType);
			// 反馈结果

			feedBackCell = dataRow.createCell(12);
			feedBackCell.setCellValue(feedBack);
			// 划扣平台

			dictApplyDeductTypeCell = dataRow.createCell(13);
			dictApplyDeductTypeCell.setCellValue(dictApplyDeductType);
			row = row + 1;
		}
	}

	/**
	 * 获取2位精度
	 * 2016年5月31日
	 * By 肖长伟
	 * @param numberStr
	 * @return
	 */
	private static String getRound2(String numberStr) {
		if (StringUtils.isBlank(numberStr)) {
			return "";
		} else {
			BigDecimal bigDecimal = new BigDecimal(numberStr).setScale(2, BigDecimal.ROUND_HALF_UP);
			return bigDecimal.toString();
		}
	}
	
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		// 财富中心
		Cell fortunCenterHeader = headerRow.createCell(0);
		fortunCenterHeader.setCellValue("财富中心");
		// 客户姓名

		Cell custNameHeader = headerRow.createCell(1);
		custNameHeader.setCellValue("客户姓名");
		// 出借编号

		Cell lendCodeHeader = headerRow.createCell(2);
		lendCodeHeader.setCellValue("出借编号");
		// 划扣日期

		Cell huakouHeader = headerRow.createCell(3);
		huakouHeader.setCellValue("划扣日期");
		// 首次出借日期

		Cell applyLendDayHeader = headerRow.createCell(4);
		applyLendDayHeader.setCellValue("首次出借日期");
		// 出借金额

		Cell applyLendMoneyHeader = headerRow.createCell(5);
		applyLendMoneyHeader.setCellValue("出借金额");
		// 划扣金额

		Cell applyDeductMoneyHeader = headerRow.createCell(6);
		applyDeductMoneyHeader.setCellValue("划扣金额");
		// 出借产品

		Cell productNameHeader = headerRow.createCell(7);
		productNameHeader.setCellValue("出借产品");
		// 理财经理

		Cell managerCodeHeader = headerRow.createCell(8);
		managerCodeHeader.setCellValue("理财经理");
		// 门店

		Cell storeNameHeader = headerRow.createCell(9);
		storeNameHeader.setCellValue("门店");
		// 划扣行别

		Cell openBankHeader = headerRow.createCell(10);
		openBankHeader.setCellValue("划扣行别");
		// 付款方式
		// apply_pay
		Cell payTypeHeader = headerRow.createCell(11);
		payTypeHeader.setCellValue("付款方式");
		// 反馈结果

		Cell feedBackHeader = headerRow.createCell(12);
		feedBackHeader.setCellValue("反馈结果");
		// 划扣平台

		Cell dictApplyDeductTypeHeader = headerRow.createCell(13);
		dictApplyDeductTypeHeader.setCellValue("划扣平台");

	}

}
