package com.creditharmony.fortune.delivery.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * @Class Name ExportExcel
 * @author 胡体勇
 * @Create In 2016年4月19日
 */
public class TripleDeliveryExportExcelUtils {
	// 日志类
	private static Logger logger = LoggerFactory.getLogger(TripleDeliveryExportExcelUtils.class);
	
	/**
	 * 导出Excel 2016年4月19日 By 胡体勇
	 * 
	 * @param queryMap
	 * @param response
	 * @param namespace
	 * @param fileName
	 * @param difference
	 */
	public static void exportData(IntDeliveryEx intDeliveryEx, HttpServletResponse response, String namespace, String fileName, String code, String difference) {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("ExportList");
			wrapperHeader(dataSheet, code, difference);
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(namespace,intDeliveryEx, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			assembleExcelCell(resultSet, dataSheet, code, difference);
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
	 * 获取导出数据 2016年4月19日 By 胡体勇
	 * 
	 * @param resultSet
	 * @param dataSheet
	 * @param difference
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet, String code, String difference) throws SQLException {
		int row = 1;
		String customerCode;// 客户编号
		String loginName;// 客户姓名
		String empCode;// 理财经理工号
		String empName;// 理财经理
		String orgName;// 营业部
		String osType;// 系统类型
		String newOrgName;// 新营业部
		String newTeamManagerName;// 新团队经理
		String newEmpCode;//　新理财经理工号
		String newEmpName;// 新理财经理
		String teamManagerName;// 原团队经理
		String delDate;// 交割时间
		String operator;// 操作人
		String batchNumber;// 批次号
		String remark;// 备注
		String createTime;// 操作时间
		String changeReason;
		String cardId;
		Row dataRow;
		Cell customerCodeCell;
		Cell loginNameCell;
		Cell empCodeCell;
		Cell empNameCell;
		Cell orgNameCell;
		Cell osTypeCell;
		Cell newOrgNameCell;
		Cell newTeamManagerNameCell;
		Cell newEmpCodeCell;
		Cell newEmpNameCell;
		Cell teamManagerNameCell;
		Cell delDateCell;
		Cell operatorCell;
		Cell batchNumberCell;
		Cell remarkCell;
		Cell createTimeCell;
		Cell changeReasonCell;
		Cell cardIdCell;
		while(resultSet.next()){
			dataRow = dataSheet.createRow(row);
			if("0".equals(code)){
				// 客户编号
				customerCode = resultSet.getString("customerCode");
				customerCodeCell = dataRow.createCell(0);
				customerCodeCell.setCellValue(customerCode);
				// 客户姓名
				//loginName = resultSet.getString("loginName");
				//屏蔽客户姓名/手机号/身份证号
				loginName = "***";
				loginNameCell = dataRow.createCell(1);
				loginNameCell.setCellValue(loginName);
				// 理财经理工号
				empCode = resultSet.getString("empCode");
				empCodeCell = dataRow.createCell(2);
				empCodeCell.setCellValue(empCode);
				// 理财经理
				empName = resultSet.getString("empName");
				empNameCell = dataRow.createCell(3);
				empNameCell.setCellValue(empName);
				// 营业部
				orgName = resultSet.getString("orgName");
				orgNameCell = dataRow.createCell(4);
				orgNameCell.setCellValue(orgName);
				// 系统类型
				osType = resultSet.getString("osType");
				osTypeCell = dataRow.createCell(5);
				osTypeCell.setCellValue(osType);
			}else if("1".equals(code)){
				// 客户编号
				customerCode = resultSet.getString("customerCode");
				customerCodeCell = dataRow.createCell(0);
				customerCodeCell.setCellValue(customerCode);
				// 客户姓名
				loginName = resultSet.getString("loginName");
				//屏蔽客户姓名/手机号/身份证号
				loginName = "***";
				loginNameCell = dataRow.createCell(1);
				loginNameCell.setCellValue(loginName);
				// 新营业部
				newOrgName = resultSet.getString("newOrgName");
				newOrgNameCell = dataRow.createCell(2);
				newOrgNameCell.setCellValue(newOrgName);
				// 新团队经理
				newTeamManagerName = resultSet.getString("newTeamManagerName");
				newTeamManagerNameCell = dataRow.createCell(3);
				newTeamManagerNameCell.setCellValue(newTeamManagerName);
				// 新理财经理
				newEmpName = resultSet.getString("newEmpName");
				newEmpNameCell = dataRow.createCell(4);
				newEmpNameCell.setCellValue(newEmpName);
				// 新理财经理工号
				newEmpCode = resultSet.getString("newEmpCode");
				newEmpCodeCell = dataRow.createCell(5);
				newEmpCodeCell.setCellValue(newEmpCode);
				// 营业部
				orgName = resultSet.getString("orgName");
				orgNameCell = dataRow.createCell(6);
				orgNameCell.setCellValue(orgName);
				// 原团队经理
				teamManagerName = resultSet.getString("teamManagerName");
				teamManagerNameCell = dataRow.createCell(7);
				teamManagerNameCell.setCellValue(teamManagerName);
				// 理财经理
				empName = resultSet.getString("empName");
				empNameCell = dataRow.createCell(8);
				empNameCell.setCellValue(empName);
				// 理财经理工号
				empCode = resultSet.getString("empCode");
				empCodeCell = dataRow.createCell(9);
				empCodeCell.setCellValue(empCode);
				// 系统类型
				osType = resultSet.getString("osType");
				osTypeCell = dataRow.createCell(10);
				if("XH_CHP".equals(osType)){
					osTypeCell.setCellValue("CHP");
				}else if("XH_JX".equals(osType)){
					osTypeCell.setCellValue("金信");
				}else if("XH_DJR".equals(osType)){
					osTypeCell.setCellValue("大金融");
				} else if ("XH_ZCJ".equals(osType)) {
					osTypeCell.setCellValue("资产家");
				}
				
				// 交割时间
				delDate = resultSet.getString("delDate");
				delDateCell = dataRow.createCell(11);
				delDateCell.setCellValue(delDate);
				// 操作人
				operator = resultSet.getString("operator");
				operatorCell = dataRow.createCell(12);
				changeReason = resultSet.getString("changeReason");
				if (StringUtils.isNotEmpty(operator) && "手动交割".equals(changeReason)) {
					operatorCell.setCellValue(operator);
				} else {
					operatorCell.setCellValue("");
				}
				changeReasonCell = dataRow.createCell(13);
				changeReasonCell.setCellValue(changeReason);
				if ("cardExcel".equals(difference)) {
					cardId = resultSet.getString("cardId");
					//屏蔽客户姓名/手机号/身份证号
					cardId = "***";
					cardIdCell = dataRow.createCell(14);
					cardIdCell.setCellValue(cardId);
				}
			}else if("2".equals(code)){
				// 批次号
				batchNumber = resultSet.getString("batchNumber");
				batchNumberCell = dataRow.createCell(0);
				batchNumberCell.setCellValue(batchNumber);
				// 客户编号
				customerCode = resultSet.getString("customerCode");
				customerCodeCell = dataRow.createCell(1);
				customerCodeCell.setCellValue(customerCode);
				// 客户姓名
				loginName = resultSet.getString("loginName");
				//屏蔽客户姓名/手机号/身份证号
				loginName = "***";
				loginNameCell = dataRow.createCell(2);
				loginNameCell.setCellValue(loginName);
				// 理财经理工号
				empCode = resultSet.getString("empCode");
				empCodeCell = dataRow.createCell(3);
				empCodeCell.setCellValue(empCode);
				// 理财经理
				empName = resultSet.getString("empName");
				empNameCell = dataRow.createCell(4);
				empNameCell.setCellValue(empName);
				// 营业部
				orgName = resultSet.getString("orgName");
				orgNameCell = dataRow.createCell(5);
				orgNameCell.setCellValue(orgName);
				// 理财经理工号（新）
				newEmpCode = resultSet.getString("newEmpCode");
				newEmpCodeCell = dataRow.createCell(6);
				newEmpCodeCell.setCellValue(newEmpCode);
				// 理财经理（新）
				newEmpName = resultSet.getString("newEmpName");
				newEmpNameCell = dataRow.createCell(7);
				newEmpNameCell.setCellValue(newEmpName);
				// 系统类型
				osType = resultSet.getString("osType");
				osTypeCell = dataRow.createCell(8);
				if("XH_CHP".equals(osType)){
					osTypeCell.setCellValue("CHP");
				}else if("XH_JX".equals(osType)){
					osTypeCell.setCellValue("金信");
				}else if("XH_DJR".equals(osType)){
					osTypeCell.setCellValue("大金融");
				} else if ("XH_ZCJ".equals(osType)) {
					osTypeCell.setCellValue("资产家");
				}
				// 操作时间
				createTime = resultSet.getString("createTime");
				createTimeCell = dataRow.createCell(9);
				createTimeCell.setCellValue(createTime);
				// 备注
				remark = resultSet.getString("remark");
				remarkCell = dataRow.createCell(10);
				remarkCell.setCellValue(remark);
			}
			row = row + 1;
		}
	}
	
	/**
	 * 设置表头
	 * 2016年4月19日
	 * By 胡体勇
	 * @param dataSheet
	 */
	public static void wrapperHeader(Sheet dataSheet, String code, String difference) {
		Row headerRow = dataSheet.createRow(0);
		if("0".equals(code)){
			Cell customerCodeHeader = headerRow.createCell(0);
			customerCodeHeader.setCellValue("客户编号");
			Cell loginNameHeader = headerRow.createCell(1);
			loginNameHeader.setCellValue("客户姓名");
			Cell empCodeHeader = headerRow.createCell(2);
			empCodeHeader.setCellValue("理财经理工号");
			Cell empNameHeader = headerRow.createCell(3);
			empNameHeader.setCellValue("理财经理");
			Cell orgNameHeader = headerRow.createCell(4);
			orgNameHeader.setCellValue("营业部");
			Cell osTypeHeader = headerRow.createCell(5);
			osTypeHeader.setCellValue("系统类型");
			Cell newEmpCodeHeader = headerRow.createCell(6);
			newEmpCodeHeader.setCellValue("理财经理工号（新）");
			Cell newEmpNameHeader = headerRow.createCell(7);
			newEmpNameHeader.setCellValue("理财经理（新）");
		}else if("1".equals(code)){
			Cell customerCodeHeader = headerRow.createCell(0);
			customerCodeHeader.setCellValue("客户编号");
			Cell loginNameHeader = headerRow.createCell(1);
			loginNameHeader.setCellValue("客户姓名");
			Cell newOrgNameHeader = headerRow.createCell(2);
			newOrgNameHeader.setCellValue("营业部");
			Cell newTeamManagerNameHeader = headerRow.createCell(3);
			newTeamManagerNameHeader.setCellValue("团队经理");
			Cell newEmpNameHeader = headerRow.createCell(4);
			newEmpNameHeader.setCellValue("新理财经理");
			Cell newEmpCodeHeader = headerRow.createCell(5);
			newEmpCodeHeader.setCellValue("新理财经理工号");
			Cell orgNameHeader = headerRow.createCell(6);
			orgNameHeader.setCellValue("原营业部");
			Cell teamManagerNameHeader = headerRow.createCell(7);
			teamManagerNameHeader.setCellValue("原理团队经理");
			Cell empCodeHeader = headerRow.createCell(8);
			empCodeHeader.setCellValue("原理财经理");
			Cell empNameHeader = headerRow.createCell(9);
			empNameHeader.setCellValue("原理财经理工号");
			Cell osTypeHeader = headerRow.createCell(10);
			osTypeHeader.setCellValue("客户平台");
			Cell delDateHeader = headerRow.createCell(11);
			delDateHeader.setCellValue("交割时间");
			Cell operatorHeader = headerRow.createCell(12);
			operatorHeader.setCellValue("操作人");
			Cell changeReasonHeader = headerRow.createCell(13);
			changeReasonHeader.setCellValue("变更原因");
			if ("cardExcel".equals(difference)) {
				Cell cardIdHeader = headerRow.createCell(14);
				cardIdHeader.setCellValue("证件号");
			}
		}else if("2".equals(code)){
			Cell batchNumberHeader = headerRow.createCell(0);
			batchNumberHeader.setCellValue("导入批次号");
			Cell customerCodeHeader = headerRow.createCell(1);
			customerCodeHeader.setCellValue("客户编号");
			Cell loginNameHeader = headerRow.createCell(2);
			loginNameHeader.setCellValue("客户姓名");
			Cell empCodeHeader = headerRow.createCell(3);
			empCodeHeader.setCellValue("理财经理工号");
			Cell empNameHeader = headerRow.createCell(4);
			empNameHeader.setCellValue("理财经理");
			Cell orgNameHeader = headerRow.createCell(5);
			orgNameHeader.setCellValue("营业部");
			Cell newEmpCodeHeader = headerRow.createCell(6);
			newEmpCodeHeader.setCellValue("理财经理工号（新）");
			Cell newEmpNameHeader= headerRow.createCell(7);
			newEmpNameHeader.setCellValue("理财经理（新）");
			Cell osTypeHeader = headerRow.createCell(8);
			osTypeHeader.setCellValue("客户平台");
			Cell createTimeHeader = headerRow.createCell(9);
			createTimeHeader.setCellValue("操作时间");
			Cell remarkHeader = headerRow.createCell(10);
			remarkHeader.setCellValue("备注");
		}
		
	}

}
