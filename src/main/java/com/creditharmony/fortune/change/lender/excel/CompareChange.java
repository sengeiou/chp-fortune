package com.creditharmony.fortune.change.lender.excel;

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

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.utils.FormatUtils;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 
 * @Class Name CompareChange
 * @author 刘熊武
 * @Create In 2016年4月27日
 */
public class CompareChange {
	
	private int row = 1;
	
	/**
	 * 2016年4月18日
	 * By 刘熊武
	 * @param queryMap 查询条件的值
	 * @param response 响应对象
	 * @param title    导出excel表格的表头信息
	 * @param field	       查询语句所查的字段名字
	 */
	public void exportLenderData (Object deductPoolExt,
			HttpServletResponse response,String title,String sql,String fileName){
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(sql,deductPoolExt, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("ExportList");
			titleHeader(dataSheet,title);
			headerExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(fileName + ".xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(fileName + ".xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void headerExcelCell(ResultSet resultSet, Sheet dataSheet) throws SQLException {
		while (resultSet.next()) {
			// 创建行
			setRowOrCell(resultSet, dataSheet);
		}
	}

	/**
	 * 表头
	 * 2016年4月27日
	 * By 刘熊武
	 * @param dataSheet
	 * @param title
	 */
	private void titleHeader(Sheet dataSheet, String title) {
		String [] titles = title.split(",");
		Row headerRow = dataSheet.createRow(0);
		for (int i = 0; i < titles.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(titles[i]);
		}		
	}

	/**
	 * 设置单元格
	 * 2016年4月27日
	 * By 刘熊武
	 * @param resultSet
	 * @param field
	 * @param dataRow
	 * @throws SQLException 
	 */
	private void setRowOrCell(ResultSet resultSet,Sheet dataSheet) throws SQLException{
		LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(resultSet.getString("lenderAfter"), LenderInitView.class);
		LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(resultSet.getString("lenderBegin"), LenderInitView.class);
		//客户英文名
		if(begin.getCustomer().getCustEname()!=null && 
				!begin.getCustomer().getCustEname().equals(after.getCustomer().getCustEname())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"客户英文名："+begin.getCustomer().getCustEname(),"客户英文名："+after.getCustomer().getCustEname());
			//setCell(resultSet,dateRow,"客户英文名："+begin.getCustomer().getCustEname(),"客户英文名："+after.getCustomer().getCustEname());
			row ++;
		}		
		//客户固定电话custPhone
		if(begin.getCustomer().getCustPhone()!=null && 
				!begin.getCustomer().getCustPhone().equals(after.getCustomer().getCustPhone())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"客户固定电话："+begin.getCustomer().getCustPhone(),"客户固定电话："+after.getCustomer().getCustPhone());
			row ++;
		}
		//客户电子邮箱custEmail
		if(begin.getCustomer().getCustEmail()!=null && 
				!begin.getCustomer().getCustEmail().equals(after.getCustomer().getCustEmail())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"客户电子邮箱："+begin.getCustomer().getCustEmail(),"客户电子邮箱："+after.getCustomer().getCustEmail());
			row ++;
		}
		//签发日期custCertIssuedate
		if(begin.getCustomer().getCustCertIssuedate()!=null && 
				!(begin.getCustomer().getCustCertIssuedate()==after.getCustomer().getCustCertIssuedate())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"签发日期："+FormatUtils.getFormatDate(begin.getCustomer().getCustCertIssuedate(), "YYYY-MM-dd") ,"签发日期："+FormatUtils.getFormatDate(after.getCustomer().getCustCertIssuedate(), "YYYY-MM-dd"));
			row ++;
		}
		//婚姻状况tz_marital_state
		if(begin.getCustomer().getCustMarriage()!=null && 
				!begin.getCustomer().getCustMarriage().equals(after.getCustomer().getCustMarriage())){
			Row dateRow = dataSheet.createRow(row);
			String amarital = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_marital_state"), after.getCustomer().getCustMarriage(), "");
			String bmarital = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_marital_state"), begin.getCustomer().getCustMarriage(), "");
			setCell(resultSet,dateRow,"婚姻状况："+bmarital,"婚姻状况："+amarital);
			row ++;
		}
		//联系人英文名称
		if(begin.getEmer().getEmerEname()!=null && 
				!begin.getEmer().getEmerEname().equals(after.getEmer().getEmerEname())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"联系人英文名称："+begin.getEmer().getEmerEname(),"联系人英文名称："+after.getEmer().getEmerEname());
			row ++;
		}
		//联系人固定电话
		if(begin.getEmer().getEmerPhone()!=null && 
				!begin.getEmer().getEmerPhone().equals(after.getEmer().getEmerPhone())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"联系人固定电话："+begin.getEmer().getEmerPhone(),"联系人固定电话："+after.getEmer().getEmerPhone());
			row ++;
		}
		//联系人邮政编码
/*		if(begin.getEmer().getAddress()!=null){
	 		if(begin.getEmer().getAddress().getAddrPostalCode()!=null && 
					!(begin.getEmer().getAddress().getAddrPostalCode()==after.getEmer().getAddress().getAddrPostalCode())){
				Row dateRow = dataSheet.createRow(row);
				setCell(resultSet,dateRow,"联系人邮政编码："+begin.getEmer().getAddress().getAddrPostalCode(),"联系人邮政编码："+after.getEmer().getAddress().getAddrPostalCode());
				row ++;
			} 
		}*/
		//客户手机号码
		if(begin.getCustomer().getCustMobilephone()!=null && 
				!begin.getCustomer().getCustMobilephone().equals(after.getCustomer().getCustMobilephone())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"客户手机号码：******","客户手机号码：******");
			row ++;
		}
		if(begin.getCustomer().getCustCertOrg()!=null&&
				!begin.getCustomer().getCustCertOrg().equals(after.getCustomer().getCustCertOrg())){	
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"发证机关："+begin.getCustomer().getCustCertOrg(),"发证机关："+after.getCustomer().getCustCertOrg());
			row ++;
		}
		if(begin.getCustomer().getCustEmail()!=null&&
				!begin.getCustomer().getCustEmail().equals(after.getCustomer().getCustEmail())){	
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"客户邮箱地址：**@**.com","客户邮箱地址：**@**.com");
			row ++;
		}
		if(begin.getCustomer().getAddress()!=null){
			if(begin.getCustomer().getAddress().getAddrProvince()!=null
					&&begin.getCustomer().getAddress().getAddrCity()!=null
					&&begin.getCustomer().getAddress().getAddrDistrict()!=null
					&&((!begin.getCustomer().getAddress().getAddrProvince().equals(after.getCustomer().getAddress().getAddrProvince()))
					||(!begin.getCustomer().getAddress().getAddrCity().equals(after.getCustomer().getAddress().getAddrCity()))
					||(!begin.getCustomer().getAddress().getAddrDistrict().equals(after.getCustomer().getAddress().getAddrDistrict())))){
				String addrs = "邮编："+after.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
				OptionUtil.getProvinceLabel(after.getCustomer().getAddress().getAddrProvince())+" "+
				OptionUtil.getCityLabel(after.getCustomer().getAddress().getAddrCity())+" "+
				OptionUtil.getDistrictLabel(after.getCustomer().getAddress().getAddrDistrict());

				
				String bddrs = "邮编："+begin.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
						OptionUtil.getProvinceLabel(begin.getCustomer().getAddress().getAddrProvince())+" "+
						OptionUtil.getCityLabel(begin.getCustomer().getAddress().getAddrCity())+" "+
						OptionUtil.getDistrictLabel(begin.getCustomer().getAddress().getAddrDistrict());
				Row dateRow = dataSheet.createRow(row);
				setCell(resultSet,dateRow,bddrs,addrs);
				row ++;
			}
		}else{
			String bddrs="";
			String addrs = "邮编："+after.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
					OptionUtil.getProvinceLabel(after.getCustomer().getAddress().getAddrProvince())+" "+
					OptionUtil.getCityLabel(after.getCustomer().getAddress().getAddrCity())+" "+
					OptionUtil.getDistrictLabel(after.getCustomer().getAddress().getAddrDistrict());
			if(begin.getCustomer().getAddress()!=null){
			 bddrs = "邮编："+begin.getCustomer().getAddress().getAddrPostalCode()+" 省市："+
					OptionUtil.getProvinceLabel(begin.getCustomer().getAddress().getAddrProvince())+" "+
					OptionUtil.getCityLabel(begin.getCustomer().getAddress().getAddrCity())+" "+
					OptionUtil.getDistrictLabel(begin.getCustomer().getAddress().getAddrDistrict());
			}
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,bddrs,addrs);
			row ++;
		}
		if(begin.getEmer().getEmerName()!=null&&
				!begin.getEmer().getEmerName().equals(after.getEmer().getEmerName())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"紧急联系人姓名："+begin.getEmer().getEmerName(),"紧急联系人姓名："+after.getEmer().getEmerName());
			row ++;
		}
		if(begin.getEmer().getEmerSex()!=null&&
				!begin.getEmer().getEmerSex().equals(after.getEmer().getEmerSex())){
			Row dateRow = dataSheet.createRow(row);
			String sex = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("sex"), after.getEmer().getEmerSex(), "");
			String bsex = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("sex"), begin.getEmer().getEmerSex(), "");
			setCell(resultSet,dateRow,"紧急联系人性别："+bsex,"紧急联系人性别："+sex);
			row ++;
		}
		if(begin.getEmer().getEmerMobilephone()!=null&&
				!begin.getEmer().getEmerMobilephone().equals(after.getEmer().getEmerMobilephone())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"紧急联系人手机号码：******","紧急联系人手机号码：******");
			row ++;
		}
		if(begin.getEmer().getEmerRelationship()!=null&&
				!begin.getEmer().getEmerRelationship().equals(after.getEmer().getEmerRelationship())){
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,"与紧急联系人关系："+after.getEmer().getEmerRelationship(),"与紧急联系人关系："+after.getEmer().getEmerRelationship());
			row ++;
		}
		if(begin.getEmer() != null && begin.getEmer().getAddress()!=null){
			if(begin.getEmer().getAddress().getAddrProvince() != null 
					&&begin.getEmer().getAddress().getAddrCity()!=null
					&&begin.getEmer().getAddress().getAddrDistrict()!=null
					&&((!begin.getEmer().getAddress().getAddrProvince().equals(after.getEmer().getAddress().getAddrProvince()))
					||(!begin.getEmer().getAddress().getAddrCity().equals(after.getEmer().getAddress().getAddrCity()))
					||(!begin.getEmer().getAddress().getAddrDistrict().equals(after.getEmer().getAddress().getAddrDistrict())))){
				String emeraddrs = "紧急联系人通讯地址："+after.getEmer().getAddress().getAddr()+" 省市："+
						OptionUtil.getProvinceLabel(after.getEmer().getAddress().getAddrProvince())+" "+
						OptionUtil.getCityLabel(after.getEmer().getAddress().getAddrCity())+" "+
						OptionUtil.getDistrictLabel(after.getEmer().getAddress().getAddrDistrict());
				String emerbddrs="";
				if(begin.getEmer().getAddress()!=null){
					emerbddrs = "紧急联系人通讯地址："+begin.getEmer().getAddress().getAddr()+" 省市："+
						OptionUtil.getProvinceLabel(begin.getEmer().getAddress().getAddrProvince())+" "+
						OptionUtil.getCityLabel(begin.getEmer().getAddress().getAddrCity())+" "+
						OptionUtil.getDistrictLabel(begin.getEmer().getAddress().getAddrDistrict());
				}
				Row dateRow = dataSheet.createRow(row);
				setCell(resultSet,dateRow,emerbddrs,emeraddrs);
				row ++;
			}
		}else{
			String emeraddrs="";
			if(after.getEmer().getAddress()!=null){
			emeraddrs = "紧急联系人通讯地址："+after.getEmer().getAddress().getAddr()+" 省市："+
					OptionUtil.getProvinceLabel(after.getEmer().getAddress().getAddrProvince())+" "+
					OptionUtil.getCityLabel(after.getEmer().getAddress().getAddrCity())+" "+
					OptionUtil.getDistrictLabel(after.getEmer().getAddress().getAddrDistrict());
			}
			String emerbddrs="";
			if(begin.getEmer().getAddress()!=null){
			 emerbddrs = "紧急联系人通讯地址："+begin.getEmer().getAddress().getAddr()+" 省市："+
					OptionUtil.getProvinceLabel(begin.getEmer().getAddress().getAddrProvince())+" "+
					OptionUtil.getCityLabel(begin.getEmer().getAddress().getAddrCity())+" "+
					OptionUtil.getDistrictLabel(begin.getEmer().getAddress().getAddrDistrict());
			}
			Row dateRow = dataSheet.createRow(row);
			setCell(resultSet,dateRow,emerbddrs,emeraddrs);
			row ++;
		}
		
		if(begin.getLoanInfo().getLoanBillRecv()!=null&&
				!begin.getLoanInfo().getLoanBillRecv().equals(after.getLoanInfo().getLoanBillRecv())){
			Row dateRow = dataSheet.createRow(row);
			String billtakenMode = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_billtaken_mode"), after.getLoanInfo().getLoanBillRecv(), "");
			String bbilltakenMode = FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_billtaken_mode"), begin.getLoanInfo().getLoanBillRecv(), "");
			
			setCell(resultSet,dateRow,"账单收取方式:"+bbilltakenMode,"账单收取方式:"+billtakenMode);
			row ++;
		}
	}

	/**
	 * 设置单元格
	 * 2016年4月27日
	 * By 刘熊武
	 * @param resultSet
	 * @param dateRow
	 * @param after
	 * @throws SQLException
	 */
	private void setCell(ResultSet resultSet,Row dateRow,String begin,String after)
			throws SQLException {
		Cell dictApprovalStartDate = dateRow.createCell(0);
		dictApprovalStartDate.setCellValue(resultSet.getString("dictApprovalStartDate"));
		Cell custName = dateRow.createCell(1);
		//屏蔽客户姓名/手机号/身份证号
		//custName.setCellValue(resultSet.getString("custName"));
		custName.setCellValue("***");
		Cell custCode = dateRow.createCell(2);
		Cell lenderBegin = dateRow.createCell(3);
		lenderBegin.setCellValue(begin);
		Cell lenderAfter = dateRow.createCell(4);
		lenderAfter.setCellValue(after);
		custCode.setCellValue(resultSet.getString("custCode"));
		Cell managerName = dateRow.createCell(5);
		managerName.setCellValue(resultSet.getString("managerName"));
		Cell departmentName = dateRow.createCell(6);
		departmentName.setCellValue(resultSet.getString("departmentName"));
	}
	
	/**
	 * 2016年4月18日
	 * By 刘熊武
	 * @param queryMap 查询条件的值
	 * @param response 响应对象
	 * @param title    导出excel表格的表头信息
	 * @param field	       查询语句所查的字段名字
	 */
	public void exportLendData (Object deductPoolExt,
			HttpServletResponse response,String title,String sql,String fileName){
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(sql,deductPoolExt, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("ExportList");
			titleHeader(dataSheet,title);
			headerLendExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(fileName + ".xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(fileName + ".xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 处理
	 * 2016年4月27日
	 * By 刘熊武
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	private void headerLendExcelCell(ResultSet resultSet, Sheet dataSheet) throws SQLException {
			int row = 1;
			Row dataRow;
			while (resultSet.next()) {
				LendLaunchView after =  (LendLaunchView) JsonMapper.fromJsonString(resultSet.getString("lendAfter"), LendLaunchView.class);
				LendLaunchView begin =  (LendLaunchView) JsonMapper.fromJsonString(resultSet.getString("lendBegin"), LendLaunchView.class);
				dataRow = dataSheet.createRow(row);
				// 审核日期
				Cell dictApprovalStartDate = dataRow.createCell(0);
				dictApprovalStartDate.setCellValue(resultSet.getString("dictApprovalStartDate"));
				// 客户姓名
//				Cell custName = dataRow.createCell(1);
//				custName.setCellValue(resultSet.getString("custName"));
				// 屏蔽客户姓名/手机号/身份证号
				Cell custName = dataRow.createCell(1);
				custName.setCellValue("***");
				// 出借编号
				Cell applyCode = dataRow.createCell(2);
				applyCode.setCellValue(resultSet.getString("applyCode"));
				// 首次出借日期
				Cell applyLendDate = dataRow.createCell(3);
				applyLendDate.setCellValue(resultSet.getString("applyLendDate"));
				// 初始出借金额
				Cell applyLendMoney = dataRow.createCell(4);
				applyLendMoney.setCellValue(resultSet.getString("applyLendMoney"));
				// 出借模式
				Cell productName = dataRow.createCell(5);
				productName.setCellValue(resultSet.getString("productName"));
				// 账单日
				Cell applyBillday = dataRow.createCell(6);
				applyBillday.setCellValue(resultSet.getString("applyBillday"));
				// 变更前行别
				Cell changeBeginAccountBankId = dataRow.createCell(7);
				changeBeginAccountBankId.setCellValue(FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_open_bank"), begin.getPayAccount().getAccountBankId(), ""));
				// 变更前开户行
				Cell changeBeginAccountBranch = dataRow.createCell(8);
				changeBeginAccountBranch.setCellValue(begin.getPayAccount().getAccountBranch());
				// 变更前账号
				Cell changeBeginAccountNo = dataRow.createCell(9);
				changeBeginAccountNo.setCellValue(begin.getPayAccount().getAccountNo());
				// 变更后行别
				Cell changeAfterAccountBankId = dataRow.createCell(10);
				changeAfterAccountBankId.setCellValue(FortuneDictUtil.dictName(ConstantField.deductDictMap.get("tz_open_bank"), after.getCountAfter().getAccountBankId(), ""));
				// 变更后开户行
				Cell changeAfterAccountBranch = dataRow.createCell(11);
				changeAfterAccountBranch.setCellValue(after.getCountAfter().getAccountBranch());
				// 变更后账号
				Cell changeAfterAccountNo = dataRow.createCell(12);
				changeAfterAccountNo.setCellValue(after.getCountAfter().getAccountNo());
				// 理财经理
				Cell managerName = dataRow.createCell(13);
				managerName.setCellValue(resultSet.getString("managerName"));
				// 营业部
				Cell departmentName = dataRow.createCell(14);
				departmentName.setCellValue(resultSet.getString("departmentName"));
				row ++;
			}
	}
}
