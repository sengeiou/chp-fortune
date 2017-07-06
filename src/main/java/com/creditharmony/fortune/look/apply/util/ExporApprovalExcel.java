package com.creditharmony.fortune.look.apply.util;

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

/**
 * 导出回息数据帮助类
 * 
 * @Class Name ExporApprovalExcel
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExporApprovalExcel {

	private static Logger logger = LoggerFactory
			.getLogger(ExporApprovalExcel.class);

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
							"com.creditharmony.fortune.look.approve.dao.LendApplyApprovalLookDao.getExportExcelList",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("导出审核通过明细");
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode("导出审核通过明细.xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode("导出审核通过明细.xlsx"));
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
		// 营业部
		String storeName;
		// 客户姓名
		String custName;
		// 划扣日期
		String huakou;
		// 首次出借日期
		String applyLendDay;
		// 初始出借金额
		String firstAmount;
		// 出借模式
		String productName;
		// 理财经理
		String managerCode;
		// 团队经理
		String teamMangerCode;
		// 综合内勤
		String zongHeNeiQin;
		// 审核人
		String checkUserName;
		// 审核不通过次数
		String notPassTimes;
		// 审批通过时间
		String passTime;
		// 审批意见
		String checkMark;
		// 错误类型
		String errorType;
		Row dataRow;
		// 营业部
		Cell storeNameCell;
		// 客户姓名
		Cell custNameCell;
		// 划扣日期
		Cell huakouCell;
		// 首次出借日期
		Cell applyLendDayCell;
		// 初始出借金额
		Cell firstAmountCell;
		// 出借模式
		Cell productNameCell;
		// 理财经理
		Cell managerCodeCell;
		// 团队经理
		Cell teamMangerCodeCell;
		// 综合内勤
		Cell zongHeNeiQinCell;
		// 审核人
		Cell checkUserNameCell;
		// 审核不通过次数
		Cell notPassTimesCell;
		// 审批通过时间
		Cell passTimeCell;
		// 审批意见
		Cell checkMarkCell;
		// 错误类型
		Cell errorTypeCell;
		while (resultSet.next()) {
			// 营业部
			storeName = resultSet.getString("storeName");
			// 客户姓名
			custName = resultSet.getString("custName");
			// 屏蔽客户姓名/手机号/身份证号
			custName = "***";
			// 划扣日期
			huakou = resultSet.getString("huakou");
			// 首次出借日期
			applyLendDay = resultSet.getString("applyLendDay");
			// 初始出借金额
			firstAmount = resultSet.getString("firstAmount");
			// 出借模式
			productName = resultSet.getString("productName");
			// 理财经理
			managerCode = resultSet.getString("managerCode");
			// 团队经理
			teamMangerCode = resultSet.getString("teamMangerCode");
			// 综合内勤
			zongHeNeiQin = resultSet.getString("zongHeNeiQin");
			// 审核人
			checkUserName = resultSet.getString("checkUserName");
			// 审核不通过次数
			notPassTimes = resultSet.getString("notPassTimes");
			// 审批通过时间
			passTime = resultSet.getString("passTime");
			// 审批意见
			checkMark = resultSet.getString("checkMark");
			// 错误类型
			errorType = resultSet.getString("errorType");
			dataRow = dataSheet.createRow(row);
			// 营业部
			storeNameCell = dataRow.createCell(0);
			storeNameCell.setCellValue(storeName);
			// 客户姓名
			custNameCell = dataRow.createCell(1);
			custNameCell.setCellValue(custName);
			// 划扣日期
			huakouCell = dataRow.createCell(2);
			huakouCell.setCellValue(huakou);
			// 首次出借日期
			applyLendDayCell = dataRow.createCell(3);
			applyLendDayCell.setCellValue(applyLendDay);
			// 初始出借金额
			firstAmountCell = dataRow.createCell(4);
			firstAmountCell.setCellValue(firstAmount);
			// 出借模式
			productNameCell = dataRow.createCell(5);
			productNameCell.setCellValue(productName);
			// 理财经理
			managerCodeCell = dataRow.createCell(6);
			managerCodeCell.setCellValue(managerCode);
			// 团队经理
			teamMangerCodeCell = dataRow.createCell(7);
			teamMangerCodeCell.setCellValue(teamMangerCode);
			// 综合内勤
			zongHeNeiQinCell = dataRow.createCell(8);
			zongHeNeiQinCell.setCellValue(zongHeNeiQin);
			// 审核人
			checkUserNameCell = dataRow.createCell(9);
			checkUserNameCell.setCellValue(checkUserName);
			// 审核不通过次数
			notPassTimesCell = dataRow.createCell(10);
			notPassTimesCell.setCellValue(notPassTimes);
			// 审批通过时间
			passTimeCell = dataRow.createCell(11);
			passTimeCell.setCellValue(passTime);
			// 审批意见
			checkMarkCell = dataRow.createCell(12);
			checkMarkCell.setCellValue(checkMark);
			// 错误类型
			errorTypeCell = dataRow.createCell(13);
			errorTypeCell.setCellValue(errorType);
			row = row + 1;
		}
	}
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		// 营业部
		Cell storeNameHeader = headerRow.createCell(0);
		storeNameHeader.setCellValue("营业部");
		// 客户姓名
		Cell custNameHeader = headerRow.createCell(1);
		custNameHeader.setCellValue("客户姓名");
		// 划扣日期
		Cell huakouHeader = headerRow.createCell(2);
		huakouHeader.setCellValue("划扣日期");
		// 首次出借日期
		Cell applyLendDayHeader = headerRow.createCell(3);
		applyLendDayHeader.setCellValue("首次出借日期");
		// 初始出借金额
		Cell firstAmountHeader = headerRow.createCell(4);
		firstAmountHeader.setCellValue("初始出借金额");
		// 出借模式
		Cell productNameHeader = headerRow.createCell(5);
		productNameHeader.setCellValue("出借模式");
		// 理财经理
		Cell managerCodeHeader = headerRow.createCell(6);
		managerCodeHeader.setCellValue("理财经理");
		// 团队经理
		Cell teamMangerCodeHeader = headerRow.createCell(7);
		teamMangerCodeHeader.setCellValue("团队经理");
		// 综合内勤
		Cell zongHeNeiQinHeader = headerRow.createCell(8);
		zongHeNeiQinHeader.setCellValue("综合内勤");
		// 审核人
		Cell checkUserNameHeader = headerRow.createCell(9);
		checkUserNameHeader.setCellValue("审核人");
		// 审核不通过次数
		Cell notPassTimesHeader = headerRow.createCell(10);
		notPassTimesHeader.setCellValue("审核不通过次数");
		// 审批通过时间
		Cell passTimeHeader = headerRow.createCell(11);
		passTimeHeader.setCellValue("审批通过时间");
		// 审批意见
		Cell checkMarkHeader = headerRow.createCell(12);
		checkMarkHeader.setCellValue("审批意见");
		// 错误类型
		Cell errorTypeHeader = headerRow.createCell(13);
		errorTypeHeader.setCellValue("错误类型");
	}

}
