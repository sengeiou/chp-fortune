package com.creditharmony.fortune.look.apply.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.CustomerConstants;

/**
 * 导出回息数据帮助类
 * 
 * @Class Name ExporApprovalExcel
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExportDJRExcel {

	private static Logger logger = LoggerFactory
			.getLogger(ExportDJRExcel.class);

	/**
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
	public static void exportData(Map<String, Object> queryMap, HttpServletResponse response) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			queryMap.put("login_user", UserUtils.getUserId());
			queryMap.put("defaultOrderSql", CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
			String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
			if (StringUtils.isNotEmpty(dataRights)) {
				queryMap.put("dataRights", dataRights);
			}
			MyBatisSql batisSql = MyBatisSqlUtil.getMyBatisSql("com.creditharmony.fortune.lend.finish.dao.LendFinishDao.getLendApplyToDJRChkExcel", queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();

			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("债权到期转投大金融明细");
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode("债权到期转投大金融明细.xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode("债权到期转投大金融明细.xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exportData()导出数据出现异常");
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
		// 客户姓名
		String custName;
		// 客户编号
		String custCode;
		// 出借编号
		String applyCode;
		// 计划出借日期
		String lendDate;
		// 计划出借金额
		String lendMoney;
		// 出借方式
		String productName;
		// 到期日期
		String expireDate;
		// 付款方式
		String applyPay;
		// 理财经理
		String managerName;
		// 营业部
		String storeName;
		// 财富中心
		String fortuneCentre;
		// 渠道标识
		String channelMarking;
		// 实际回款金额
		String actualback_money;
		// 审核通过日期
		String approveDate;
		// 补息天数
		String interestDay;
		// 完结状态
		String applyEndStatus;
		
		Row dataRow;
		// 客户姓名
		Cell custNameCell;
		// 客户编号
		Cell custCodeCell;
		// 到期日期
		Cell expireDateCell;
		// 出借编号
		Cell applyCodeCell;
		// 计划出借日期
		Cell lendDateCell;
		// 计划出借金额
		Cell lendMoneyCell;
		// 出借方式
		Cell productNameCell;
		// 付款方式
		Cell applyPayCell;
		// 理财经理
		Cell managerNameCell;
		// 营业部
		Cell storeNameCell;
		// 财富中心
		Cell fortuneCentreCell;
		// 实际回款金额
		Cell actualback_moneyCell;
		// 渠道标识
		Cell channelMarkingCell;
		// 审批通过时间
		Cell approveDateCell;
		// 补息天数
		Cell interestDayCell;
		// 完结状态
		Cell applyEndStatusCell;

		while (resultSet.next()) {
			// 客户姓名
			custName = resultSet.getString("customerName");
			// 屏蔽客户姓名/手机号/身份证号
			custName = "***";
			// 客户编号
			custCode = resultSet.getString("customerCode");
			// 出借编号
			applyCode = resultSet.getString("lend_code");
			// 计划出借日期
			lendDate = resultSet.getString("apply_lend_day");
			// 计划出借金额
			lendMoney = resultSet.getString("apply_lend_money");
			if(StringUtils.isNotEmpty(lendMoney)){
				DecimalFormat df = new DecimalFormat("######0.00");
				lendMoney = df.format(Double.valueOf(lendMoney));
			} else {
				lendMoney = "0";
			}
			// 出借产品
			if (resultSet.getString("product_code") != null) {
				productName = ProductCode.parseByValue(resultSet.getString("product_code")).name;
			} else {
				productName = "";
			}
			// 到期日期
			expireDate = resultSet.getString("apply_expire_day");
			// 付款方式
			if (resultSet.getString("apply_pay") != null) {
				applyPay = resultSet.getString("apply_pay");
				applyPay = PayMent.payMentMap.get(applyPay);
			}else{
				applyPay = "";
			}
			// 理财经理
			managerName = resultSet.getString("managerName");
			// 营业部
			storeName = resultSet.getString("storesName");
			// 财富中心
			fortuneCentre = resultSet.getString("fortuneCentre");
			// 渠道标识
			if (resultSet.getString("dict_channel_marking") != null) {
				channelMarking = FortuneChannelFlag.getChannalFlag(resultSet.getString("dict_channel_marking")).name;
			}else{
				channelMarking = "";
			}
			// 实际回款金额
			actualback_money = resultSet.getString("actualback_money");
			if(StringUtils.isNotEmpty(actualback_money)){
				DecimalFormat df = new DecimalFormat("######0.00");
				actualback_money = df.format(Double.valueOf(actualback_money));
			} else {
				actualback_money = "0";
			}
			// 审批通过时间
			approveDate = resultSet.getString("approveDate");
			interestDay = resultSet.getString("interestDay");
			FinishState.initFinishState();
			String endStatus = resultSet.getString("applyEndStatus");
			if (StringUtils.isNotEmpty(endStatus)) {
				applyEndStatus = FinishState.getFinishState(endStatus);
			} else {
				applyEndStatus = "";
			}
			
			dataRow = dataSheet.createRow(row);
			
			// 客户姓名
			custNameCell = dataRow.createCell(0);
			custNameCell.setCellValue(custName);
			// 客户编号
			custCodeCell = dataRow.createCell(1);
			custCodeCell.setCellValue(custCode);
			// 出借编号
			applyCodeCell = dataRow.createCell(2);
			applyCodeCell.setCellValue(applyCode);
			// 计划出借日期
			lendDateCell = dataRow.createCell(3);
			lendDateCell.setCellValue(lendDate);
			// 计划出借金额
			lendMoneyCell = dataRow.createCell(4);
			lendMoneyCell.setCellValue(lendMoney);
			// 出借产品
			productNameCell = dataRow.createCell(5);
			productNameCell.setCellValue(productName);
			// 到期日期
			expireDateCell = dataRow.createCell(6);
			expireDateCell.setCellValue(expireDate);
			// 付款方式
			applyPayCell = dataRow.createCell(7);
			applyPayCell.setCellValue(applyPay);
			// 理财经理
			managerNameCell = dataRow.createCell(8);
			managerNameCell.setCellValue(managerName);
			// 营业部
			storeNameCell = dataRow.createCell(9);
			storeNameCell.setCellValue(storeName);
			// 财富中心
			fortuneCentreCell = dataRow.createCell(10);
			fortuneCentreCell.setCellValue(fortuneCentre);
			// 渠道标识
			channelMarkingCell = dataRow.createCell(11);
			channelMarkingCell.setCellValue(channelMarking);
			// 实际回款金额
			actualback_moneyCell = dataRow.createCell(12);
			actualback_moneyCell.setCellValue(actualback_money);
			// 审批通过时间
			approveDateCell = dataRow.createCell(13);
			approveDateCell.setCellValue(approveDate);
			// 补息天数
			interestDayCell = dataRow.createCell(14);
			interestDayCell.setCellValue(interestDay);
			// 完结状态
			applyEndStatusCell = dataRow.createCell(15);
			applyEndStatusCell.setCellValue(applyEndStatus);
			row++;
		}
	}
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		// 客户姓名
		Cell custNameHeader = headerRow.createCell(0);
		custNameHeader.setCellValue("客户姓名");
		// 客户编号
		Cell custCodeHeader = headerRow.createCell(1);
		custCodeHeader.setCellValue("客户编号");
		// 出借编号
		Cell applyCodeHeader = headerRow.createCell(2);
		applyCodeHeader.setCellValue("出借编号");
		// 计划出借日期
		Cell lendDateHeader = headerRow.createCell(3);
		lendDateHeader.setCellValue("计划出借日期");
		// 计划出借金额
		Cell lendMoneyHeader = headerRow.createCell(4);
		lendMoneyHeader.setCellValue("计划出借金额");
		// 出借产品
		Cell productNameHeader = headerRow.createCell(5);
		productNameHeader.setCellValue("出借产品");
		// 到期日期
		Cell expireDateHeader = headerRow.createCell(6);
		expireDateHeader.setCellValue("到期日期");
		// 付款方式
		Cell applyPayHeader = headerRow.createCell(7);
		applyPayHeader.setCellValue("付款方式");
		// 理财经理
		Cell managerNameHeader = headerRow.createCell(8);
		managerNameHeader.setCellValue("理财经理");
		// 营业部
		Cell storeNameHeader = headerRow.createCell(9);
		storeNameHeader.setCellValue("营业部");
		// 财富中心
		Cell fortuneCentreHeader = headerRow.createCell(10);
		fortuneCentreHeader.setCellValue("财富中心");
		// 渠道标识
		Cell channelMarkingHeader = headerRow.createCell(11);
		channelMarkingHeader.setCellValue("渠道标识");
		// 实际回款金额
		Cell actualback_moneyHeader = headerRow.createCell(12);
		actualback_moneyHeader.setCellValue("实际回款金额");
		// 审批通过时间
		Cell approveDateHeader = headerRow.createCell(13);
		approveDateHeader.setCellValue("大金融审核日期");
		// 补息天数
		Cell interestDayHeader = headerRow.createCell(14);
		interestDayHeader.setCellValue("补息天数");
		// 完结状态
		Cell applyEndStatusHeader = headerRow.createCell(15);
		applyEndStatusHeader.setCellValue("完结状态");
	}
}