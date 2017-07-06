package com.creditharmony.fortune.remind.util;

import java.math.BigDecimal;
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
import com.creditharmony.fortune.utils.FormatUtils;

/**
 * 导出数据
 * @Class Name ExportRemindThreeMonthHelper
 * @author liusl
 * @Create In 2016年10月12日
 */
public class ExportRemindThreeMonthHelper {

	private static Logger logger = LoggerFactory.getLogger(ExportRemindThreeMonthHelper.class);
	
	/**
	 * 导出工具
	 * 2016年10月12日
	 * by liusl
	 * @param queryMap 查询条件
	 * @param response	响应对象
	 * @param fileName	导出文件名字
	 * @param sqlNameSpace	查询数据用的sql名称及语句路劲
	 * @param sheetName	sheet名称
	 */
	public static void exportData(Map<String, Object> queryMap,HttpServletResponse response) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil.getMyBatisSql(
							"com.creditharmony.fortune.remind.dao.RemindDao.exportExlRemindThreeMonth",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(FileType.Q3MONTHTDQ_TXLB.getName());
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(FileType.Q3MONTHTDQ_TXLB.getName()+" .xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(FileType.Q3MONTHTDQ_TXLB.getName()+".xlsx"));
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
	 * 填充单元格
	 */
	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws SQLException {
		int row = 1;
		//客户编号、客户姓名、手机号（不加密）、出借编号、计划出借日期、计划出借金额、出借方式、
		//付款方式、所在城市、营业部、到期日期、创建时间
		Row dataRow;
		String customerCode;	//客户编号
		String customerName;	//客户姓名	 
		String tel;				//手机号
		String loanCode;		//出借编号
		String lendDay;			//计划出借日期 
		String lendMoney;		//计划出借金额 
		String dictLendType;	//出借方式 
		String dictPayType;			//付款方式
		String areaName;		//所在城市
		String orgName;			//营业部
		String dueDay; 			//到期日期
		String createTime;		//创建时间

		while (resultSet.next()) {
			customerCode = resultSet.getString("customerCode");
			customerName = resultSet.getString("customerName");
			//屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			tel = resultSet.getString("tel");
			tel = "***";
			loanCode = resultSet.getString("loanCode");
			lendDay = resultSet.getString("lendDay");
//			lendMoney = resultSet.getString("lendMoney");
			lendMoney = FormatUtils.getFormatNumber(new BigDecimal(resultSet.getString("lendMoney")),"￥#,##0.00");
			
			dictLendType = resultSet.getString("dictLendType");
			dictPayType = resultSet.getString("dictPayType");
			areaName = resultSet.getString("areaName");
			orgName = resultSet.getString("orgName");
			dueDay = resultSet.getString("dueDay");
			createTime = resultSet.getString("createTime");

			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerCode);
			dataRow.createCell(1).setCellValue(customerName);
			dataRow.createCell(2).setCellValue(tel);
			dataRow.createCell(3).setCellValue(loanCode);
			dataRow.createCell(4).setCellValue(lendDay);
			dataRow.createCell(5).setCellValue(lendMoney);
			dataRow.createCell(6).setCellValue(dictLendType);
			dataRow.createCell(7).setCellValue(dictPayType);
			dataRow.createCell(8).setCellValue(areaName);
			dataRow.createCell(9).setCellValue(orgName);
			dataRow.createCell(10).setCellValue(dueDay);
			dataRow.createCell(11).setCellValue(createTime.substring(0,10));
			
			row = row + 1;
		}
	}
	
	/**
	 * 导出表头
	 * 2016年10月12日
	 * By liusl
	 * @param dataSheet
	 */
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		//客户编号、客户姓名、手机号（不加密）、出借编号、计划出借日期、计划出借金额、出借方式、付款方式、所在城市、营业部、到期日期、创建时间
		Cell customerCodeHeader = headerRow.createCell(0);
		customerCodeHeader.setCellValue("客户编号");
		Cell customerNameHeader = headerRow.createCell(1);
		customerNameHeader.setCellValue("客户姓名");
		Cell telHeader = headerRow.createCell(2);
		telHeader.setCellValue("手机号");
		Cell loanCodeHeader = headerRow.createCell(3);
		loanCodeHeader.setCellValue("出借编号");
		Cell lendDayHeader = headerRow.createCell(4);
		lendDayHeader.setCellValue("计划出借日期");
		Cell lendMoneyHeader = headerRow.createCell(5);
		lendMoneyHeader.setCellValue("计划出借金额");
		Cell dictLendTypeHeader = headerRow.createCell(6);
		dictLendTypeHeader.setCellValue("出借方式");
		Cell payTypeHeader = headerRow.createCell(7);
		payTypeHeader.setCellValue("付款方式");
		Cell areaNameHeader = headerRow.createCell(8);
		areaNameHeader.setCellValue("所在城市");
		Cell orgNameHeader = headerRow.createCell(9);
		orgNameHeader.setCellValue("营业部");
		Cell dueDayHeader = headerRow.createCell(10);
		dueDayHeader.setCellValue("到期日期");
		Cell createTimeHeader = headerRow.createCell(11);
		createTimeHeader.setCellValue("创建时间");
	}
}