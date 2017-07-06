package com.creditharmony.fortune.borrow.utils;

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
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.LoanDistinguish;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.ZjtrMark;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.constants.BorrowConstant;

/**
 * 债权管理导出Excel工具类
 * @Class Name ExportExcelUtils
 * @author 周俊
 * @Create In 2016年4月15日
 */
public class ExportExcelUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ExportExcelUtils.class);
	
	/**
	 * 导出Excel 
	 * 2016年4月15日
	 * By 周俊
	 * @param queryMap
	 * @param response
	 * @param namespace
	 * @param fileName
	 * @param token
	 */
	public static void exportData(Map<String, Object> queryMap,HttpServletResponse response,String namespace,String fileName,String token) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("ExportList");
			wrapperHeader(dataSheet,token);
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(namespace,queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			assembleExcelCell(resultSet, dataSheet,token);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(fileName)
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(fileName));
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
	 * 获取资源
	 * 2016年4月15日
	 * By 周俊
	 * @param resultSet
	 * @param dataSheet
	 * @param token
	 * @throws SQLException
	 */
	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet,String token)throws SQLException {
		int row = 1;
	    String loanName; // 借款人
	    String loanIdcard;// 借款人身份证号
	    String dictLoanType;// 借款类型
	    String loanPurpose; // 借款用途
	    String loanTrusteeFlag;// 借款标识
	    String loanProduct;// 借款产品
	    String loanJob;// 借款人职业
	    String loanBackmoneyFirday;// 首次还款日
	    String loanBackmoneyDay; // 还款日
	    String loanMonths;// 借款期数
	    String loanMonthsSurplus;// 可用期数
	    String loanBackmoneyLastday;// 截至还款日
	    String loanMonthRate;// 月利率
	    String loanQuota;// 原始可用债权
	    String loanCreditValue;// 可用债权价值
	    String loanValueYear;// 年预计债权收益
    	@SuppressWarnings("unused")
    	String occupyLoanCreditValue;// 占用债权价值
    	@SuppressWarnings("unused")
    	String borrowRatio;// 债权持有比例
    	@SuppressWarnings("unused")
    	String attornBorrowRatio;// 债权转让比例
    	String loanCreditDayUsable; // 债权可用日期
    	String loanFreezeDay;// 债权冻结时间
    	String dicLoanDistinguish; //债权区分
		Row dataRow;
		Cell loanCreditDayUsableCell;
		Cell loanBackmoneyDayCell;
		Cell loanNameCell;
		Cell loanIdcardCell;
		Cell dictLoanTypeCell;
		Cell loanPurposeCell;
		Cell loanTrusteeFlagCell;
		Cell loanProductCell;
		Cell loanJobCell;
		Cell loanBackmoneyFirdayCell;
		Cell loanMonthsCell;
		Cell loanMonthsSurplusCell;
		Cell loanBackmoneyLastdayCell;
		Cell loanMonthRateCell;
		Cell loanQuotaCell;
		Cell loanCreditValueCell;
		Cell occupyLoanCreditValueCell;
		Cell loanValueYearCell;
		Cell borrowRatioCell;
		Cell attornBorrowRatioCell;
		Cell loanFreezeDayCell;
		Cell dicLoanDistinguishCell;
			while(resultSet.next()) {
				dataRow = dataSheet.createRow(row);
				// 借款人姓名
				loanName = resultSet.getString("loan_name");
				// 屏蔽客户姓名/手机号/身份证号
				loanName = "***";
				loanNameCell = dataRow.createCell(0);
				loanNameCell.setCellValue(loanName);
				// 借款人身份号
				loanIdcard = resultSet.getString("loan_idcard");
				// 屏蔽客户姓名/手机号/身份证号
				loanIdcard = "***";
				loanIdcardCell = dataRow.createCell(1);
				loanIdcardCell.setCellValue(FormatUtils.formatLoanIdcard(loanIdcard));
				if (token.equals(Node.YMYKY.value)) {
					// 债权来源
					dictLoanType = resultSet.getString("dict_loan_type");
					dictLoanTypeCell = dataRow.createCell(2);
					try {
						dictLoanTypeCell.setCellValue(CreditSrc.creditSrcMap.get(dictLoanType));
					} catch (Exception e) {
						dictLoanTypeCell.setCellValue("-");
					}
					// 借款用途
					loanPurpose = resultSet.getString("loan_purpose");
					loanPurposeCell = dataRow.createCell(3);
					loanPurposeCell.setCellValue(loanPurpose);
					// 借款产品
					loanProduct = resultSet.getString("loan_product");
					loanProductCell = dataRow.createCell(4);
					loanProductCell.setCellValue(loanProduct);
					// 借款人职业情况
					loanJob = resultSet.getString("loan_job");
					loanJobCell = dataRow.createCell(5);
					try {
						loanJobCell.setCellValue(ProfType.parseByCode(loanJob).getName());
					} catch (Exception e) {
						loanJobCell.setCellValue("-");
					}
					// 首次还款日
					loanBackmoneyFirday = resultSet.getString("loan_backmoney_firday");
					loanBackmoneyFirdayCell = dataRow.createCell(6);
					loanBackmoneyFirdayCell.setCellValue(loanBackmoneyFirday);
					// 债权可用日期
					loanCreditDayUsable = resultSet.getString("loan_credit_day_usable");
					loanCreditDayUsableCell = dataRow.createCell(7);
					loanCreditDayUsableCell.setCellValue(loanCreditDayUsable);
					// 借款天数
					loanMonths = resultSet.getString("loan_months");
					loanMonthsCell = dataRow.createCell(8);
					loanMonthsCell.setCellValue(loanMonths);
					// 可用天数
					loanMonthsSurplus = resultSet.getString("loan_months_surplus"); 
					loanMonthsSurplusCell = dataRow.createCell(9);
					loanMonthsSurplusCell.setCellValue(loanMonthsSurplus);
					// 截至还款日期
					loanBackmoneyLastday = resultSet.getString("loan_backmoney_lastday");
					loanBackmoneyLastdayCell = dataRow.createCell(10);
					loanBackmoneyLastdayCell.setCellValue(loanBackmoneyLastday);
					// 月利率
					loanMonthRate = resultSet.getString("loan_month_rate");
					loanMonthRateCell = dataRow.createCell(11);
					loanMonthRateCell.setCellValue(FormatUtils.formatNumberRate(loanMonthRate));
					// 原始债权价值
					loanQuota = resultSet.getString("loan_quota");
					loanQuotaCell = dataRow.createCell(12);
					loanQuotaCell.setCellValue(FormatUtils.formatNumber(loanQuota));
					// 可用债权价值
					loanCreditValue = resultSet.getString("loan_credit_value");
					loanCreditValueCell = dataRow.createCell(13);
				    loanCreditValueCell.setCellValue(FormatUtils.formatNumber(loanCreditValue));
				    // 年预计债权收益
					loanValueYear = resultSet.getString("loan_value_year");
					loanValueYearCell = dataRow.createCell(14);
				    loanValueYearCell.setCellValue(FormatUtils.formatNumberRate(loanValueYear));
				    dicLoanDistinguish = resultSet.getString("dic_loan_distinguish");
				    dicLoanDistinguishCell = dataRow.createCell(15);
				    try {
				    	dicLoanDistinguishCell.setCellValue(LoanDistinguish.parseByCode(dicLoanDistinguish).getName());
					} catch (Exception e) {
						dicLoanDistinguishCell.setCellValue("-");
					}
					}else{
						// 原始债权价值
						loanQuota = resultSet.getString("loan_quota");
						loanQuotaCell = dataRow.createCell(12);
						loanQuotaCell.setCellValue(FormatUtils.formatNumber(loanQuota));
						// 可用债权价值
						loanCreditValue = resultSet.getString("loan_credit_value");
						loanCreditValueCell = dataRow.createCell(13);
					    loanCreditValueCell.setCellValue(FormatUtils.formatNumber(loanCreditValue));
						if(token.equals(Node.YMY.value)){
							// 借款产品
							loanProduct = resultSet.getString("loan_product");
							loanProductCell = dataRow.createCell(2);
							loanProductCell.setCellValue(loanProduct);
							// 债权标识
							loanTrusteeFlag = resultSet.getString("loan_trustee_flag");
							loanTrusteeFlagCell = dataRow.createCell(3);
							try {
								loanTrusteeFlagCell.setCellValue(ZjtrMark.zjtrMarkMap.get(loanTrusteeFlag));
							} catch (Exception e) {
								loanTrusteeFlagCell.setCellValue("-");
							}
							// 借款用途
							loanPurpose = resultSet.getString("loan_purpose");
							loanPurposeCell = dataRow.createCell(4);
							loanPurposeCell.setCellValue(loanPurpose);
						}else if (token.equals(Node.KYZQ.value)) {
							// 债权来源
							dictLoanType = resultSet.getString("dict_loan_type");
							dictLoanTypeCell = dataRow.createCell(2);
							try {
								dictLoanTypeCell.setCellValue(CreditSrc.creditSrcMap.get(dictLoanType));
							} catch (Exception e) {
								dictLoanTypeCell.setCellValue("-");
							}
							// 债权标识
							loanTrusteeFlag = resultSet.getString("loan_trustee_flag");
							loanTrusteeFlagCell = dataRow.createCell(3);
							try {
								loanTrusteeFlagCell.setCellValue(ZjtrMark.zjtrMarkMap.get(loanTrusteeFlag));
							} catch (Exception e) {
								loanTrusteeFlagCell.setCellValue("-");
							}
							// 借款产品
							loanProduct = resultSet.getString("loan_product");
							loanProductCell = dataRow.createCell(4);
							loanProductCell.setCellValue(loanProduct);
							// 占用债权价值
							occupyLoanCreditValueCell = dataRow.createCell(15);
							occupyLoanCreditValueCell.setCellValue(ReckonUtils.reckonByBigDecimal(loanQuota,"SUBTRACT",loanCreditValue));
							// 债权持有比例
							borrowRatioCell = dataRow.createCell(16);
							borrowRatioCell.setCellValue(ReckonUtils.reckonByBigDecimal(loanQuota,"PERCENTAGE",loanCreditValue));
							// 债权转让比例
							attornBorrowRatioCell = dataRow.createCell(17);
							attornBorrowRatioCell.setCellValue(ReckonUtils.reckonByBigDecimal(loanQuota,"PERCENTAGE",ReckonUtils.reckonByBigDecimal(loanQuota,"SUBTRACT",loanCreditValue)));
							dicLoanDistinguish = resultSet.getString("dic_loan_distinguish");
							dicLoanDistinguishCell = dataRow.createCell(18);
						    try {
						    	dicLoanDistinguishCell.setCellValue(LoanDistinguish.parseByCode(dicLoanDistinguish).getName());
							} catch (Exception e) {
								dicLoanDistinguishCell.setCellValue("-");
							}
						}else if(token.equals(BorrowConstant.BORROWFREEZE_NODE)){
							// 债权来源
							dictLoanType = resultSet.getString("dict_loan_type");
							dictLoanTypeCell = dataRow.createCell(2);
							try {
								dictLoanTypeCell.setCellValue(CreditSrc.creditSrcMap.get(dictLoanType));
							} catch (Exception e) {
								dictLoanTypeCell.setCellValue("-");
							}
							// 借款产品
							loanProduct = resultSet.getString("loan_product");
							loanProductCell = dataRow.createCell(3);
							loanProductCell.setCellValue(loanProduct);
							// 借款用途
							loanPurpose = resultSet.getString("loan_purpose");
							loanPurposeCell = dataRow.createCell(4);
							loanPurposeCell.setCellValue(loanPurpose);
							// 债权冻结时间
							loanFreezeDay = resultSet.getString("loan_freeze_day");
							loanFreezeDayCell = dataRow.createCell(15);
							loanFreezeDayCell.setCellValue(loanFreezeDay);
							// 债权持有比例
							borrowRatioCell = dataRow.createCell(16);
							borrowRatioCell.setCellValue(ReckonUtils.reckonByBigDecimal(loanQuota,"PERCENTAGE",loanCreditValue));
						}
						// 借款人职业情况
						loanJob = resultSet.getString("loan_job");
						loanJobCell = dataRow.createCell(5);
						try {
							loanJobCell.setCellValue(ProfType.parseByCode(loanJob).getName());
						} catch (Exception e) {
							loanJobCell.setCellValue("-");
						}
						// 首次还款日
						loanBackmoneyFirday = resultSet.getString("loan_backmoney_firday");
						loanBackmoneyFirdayCell = dataRow.createCell(6);
						loanBackmoneyFirdayCell.setCellValue(loanBackmoneyFirday);
						// 还款日
						loanBackmoneyDay = resultSet.getString("loan_backmoney_day");
						loanBackmoneyDayCell = dataRow.createCell(7);
						loanBackmoneyDayCell.setCellValue(loanBackmoneyDay);
						// 借款期数
						loanMonths = resultSet.getString("loan_months");
						loanMonthsCell = dataRow.createCell(8);
						loanMonthsCell.setCellValue(loanMonths);
						// 可用期数
						loanMonthsSurplus = resultSet.getString("loan_months_surplus"); 
						loanMonthsSurplusCell = dataRow.createCell(9);
						loanMonthsSurplusCell.setCellValue(loanMonthsSurplus);
						// 截至还款日期
						loanBackmoneyLastday = resultSet.getString("loan_backmoney_lastday");
						loanBackmoneyLastdayCell = dataRow.createCell(10);
						loanBackmoneyLastdayCell.setCellValue(loanBackmoneyLastday);
						// 月利率
						loanMonthRate = resultSet.getString("loan_month_rate");
						loanMonthRateCell = dataRow.createCell(11);
						loanMonthRateCell.setCellValue(FormatUtils.formatNumberRate(loanMonthRate));
					    // 年预计债权收益
						loanValueYear = resultSet.getString("loan_value_year");
						loanValueYearCell = dataRow.createCell(14);
					    loanValueYearCell.setCellValue(FormatUtils.formatNumberRate(loanValueYear));
				    }
			    row = row + 1;
			}
		}

	/**
	 * 添加表头
	 * 2016年4月15日
	 * By 周俊
	 * @param dataSheet
	 * @param token
	 */
	private static void wrapperHeader(Sheet dataSheet,String token) {
		
		Row headerRow = dataSheet.createRow(0);
		Cell loanNameHeader = headerRow.createCell(0);
		loanNameHeader.setCellValue("借款人");
		Cell loanIdcardHeader = headerRow.createCell(1);
		loanIdcardHeader.setCellValue("借款人身份证号");
		if (token.equals(Node.YMYKY.value)) {
			// 债权来源
			Cell dictLoanTypeHeader = headerRow.createCell(2);
			dictLoanTypeHeader.setCellValue("债权来源");
			// 借款用途
			Cell loanPurposeHeader = headerRow.createCell(3);
			loanPurposeHeader.setCellValue("借款用途");
			// 借款产品
			Cell loanProductHeader = headerRow.createCell(4);
			loanProductHeader.setCellValue("借款产品");
			// 借款人职业情况
			Cell loanJobHeader = headerRow.createCell(5);
			loanJobHeader.setCellValue("职业情况");
			Cell loanBackmoneyFirdayHeader = headerRow.createCell(6);
			loanBackmoneyFirdayHeader.setCellValue("首次还款日");
			Cell loanCreditDayUsableHeader = headerRow.createCell(7);
			loanCreditDayUsableHeader.setCellValue("债权可用日期");
			Cell loanMonthsHeader = headerRow.createCell(8);
			loanMonthsHeader.setCellValue("借款天数");
			Cell loanMonthsSurplusHeader = headerRow.createCell(9);
			loanMonthsSurplusHeader.setCellValue("可用天数");
			Cell loanBackmoneyLastdayHeader = headerRow.createCell(10);
			loanBackmoneyLastdayHeader.setCellValue("截止还款日期");
			Cell loanMonthRateHeader = headerRow.createCell(11);
			loanMonthRateHeader.setCellValue("月利率");
			Cell loanQuotaHeader = headerRow.createCell(12);
			loanQuotaHeader.setCellValue("原始债权价值");
			Cell loanCreditValueHeader = headerRow.createCell(13);
			loanCreditValueHeader.setCellValue("可用债权价值");
			Cell loanValueYearHeader = headerRow.createCell(14);
			loanValueYearHeader.setCellValue("年预计债权收益");
			Cell dicLoanDistinguishrHeader = headerRow.createCell(15);
			dicLoanDistinguishrHeader.setCellValue("债权区分");
			}else{
				if(token.equals(Node.YMY.value)){
					// 借款产品
					Cell loanProductHeader = headerRow.createCell(2);
					loanProductHeader.setCellValue("借款产品");
					// 债权标识
					Cell loanTrusteeFlagHeader = headerRow.createCell(3);
					loanTrusteeFlagHeader.setCellValue("债权标识");
					// 借款用途
					Cell loanPurposeHeader = headerRow.createCell(4);
					loanPurposeHeader.setCellValue("借款用途");
				}else if (token.equals(Node.KYZQ.value)) {
					// 债权来源
					Cell dictLoanTypeHeader = headerRow.createCell(2);
					dictLoanTypeHeader.setCellValue("债权来源");
					// 债权标识
					Cell loanTrusteeFlagHeader = headerRow.createCell(3);
					loanTrusteeFlagHeader.setCellValue("债权标识");
					// 借款产品
					Cell loanProductHeader = headerRow.createCell(4);
					loanProductHeader.setCellValue("借款产品");
					// 占用债权价值
					Cell occupyLoanCreditValueHeader = headerRow.createCell(15);
					occupyLoanCreditValueHeader.setCellValue("占用债权价值");
					// 债权持有比例
					Cell borrowRatioHeader = headerRow.createCell(16);
					borrowRatioHeader.setCellValue("债权持有比例");
					// 债权转让比例
					Cell attornBorrowRatioHeader = headerRow.createCell(17);
					attornBorrowRatioHeader.setCellValue("债权转让比例");
					Cell dicLoanDistinguishrHeader = headerRow.createCell(18);
					dicLoanDistinguishrHeader.setCellValue("债权区分");
				}else if(token.equals(BorrowConstant.BORROWFREEZE_NODE)){
					// 债权来源
					Cell dictLoanTypeHeader = headerRow.createCell(2);
					dictLoanTypeHeader.setCellValue("债权来源");
					// 借款产品
					Cell loanProductHeader = headerRow.createCell(3);
					loanProductHeader.setCellValue("借款产品");
					// 借款用途
					Cell loanPurposeHeader = headerRow.createCell(4);
					loanPurposeHeader.setCellValue("借款用途");
					// 债权冻结时间
					Cell loanFreezeDayHeader = headerRow.createCell(15);
					loanFreezeDayHeader.setCellValue("债权冻结时间");
					// 债权持有比例
					Cell borrowRatioHeader = headerRow.createCell(16);
					borrowRatioHeader.setCellValue("债权持有比例");
				}
				// 借款人职业情况
				Cell loanJobHeader = headerRow.createCell(5);
				loanJobHeader.setCellValue("职业情况");
				// 首次还款日
				Cell loanBackmoneyFirdayHeader = headerRow.createCell(6);
				loanBackmoneyFirdayHeader.setCellValue("首次还款日");
				Cell loanBackmoneyDayHeader = headerRow.createCell(7);
				loanBackmoneyDayHeader.setCellValue("还款日");
				Cell loanMonthsHeader = headerRow.createCell(8);
				Cell loanMonthsSurplusHeader = headerRow.createCell(9);
				loanMonthsHeader.setCellValue("借款期数");
				loanMonthsSurplusHeader.setCellValue("可用期数");
				Cell loanBackmoneyLastdayHeader = headerRow.createCell(10);
				loanBackmoneyLastdayHeader.setCellValue("截止还款日期");
				Cell loanMonthRateHeader = headerRow.createCell(11);
				loanMonthRateHeader.setCellValue("月利率");
				Cell loanQuotaHeader = headerRow.createCell(12);
				Cell loanCreditValueHeader = headerRow.createCell(13);
				if (token.equals(Node.KYZQ.value)||token.equals(Node.YMYKY.value)) {
					loanQuotaHeader.setCellValue("原始债权价值");
					loanCreditValueHeader.setCellValue("可用债权价值");
				}else {
					loanQuotaHeader.setCellValue("分配金额");
					loanCreditValueHeader.setCellValue("可拆分金额");
				}
				Cell loanValueYearHeader = headerRow.createCell(14);
				loanValueYearHeader.setCellValue("年预计债权收益");
		    }
	}
}
