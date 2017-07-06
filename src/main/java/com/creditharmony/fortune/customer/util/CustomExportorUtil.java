package com.creditharmony.fortune.customer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.customer.entity.Customer;

/**
 * 我的客户导出公用类
 * @Class Name CustomExportor
 * @author liusl
 * @Create In 2016年10月10日
 */
public abstract class CustomExportorUtil {
	
	private String filename;
	
	private String sheetname;
	
	public CustomExportorUtil(String filename){
		this.filename = filename;
		this.sheetname = "DataList";
	}
	
	public CustomExportorUtil(String filename, String sheetname){
		this.filename = filename;
		this.sheetname = sheetname;
	}
	
	private static Logger logger = LoggerFactory.getLogger(CustomExportorUtil.class);
	
	/**
	 * 导出Excel方法
	 * 2016年10月10日
	 * By liusl
	 * @param customerView
	 * @param response
	 */
	public void exportData(Customer customerView,	HttpServletResponse response) {
		
		// 设置搜索条件
		if (customerView == null) {
			customerView = new Customer();
		}
		String city = customerView.getAddrCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			customerView.setAddrCity(c);
		}
		
		String dataRights = DataScopeUtil.getDataScope("ci",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			logger.info("findInvestList权限：" + dataRights);
			customerView.setSqlMap(sqlMap);
		}
		
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil.getMyBatisSql(
							"com.creditharmony.fortune.customer.dao.CustomerDao.findCustomExportList",
							customerView, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(sheetname);
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
							+ Encodes.urlEncode(filename+".xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(filename+".xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(filename+"导出数据出现异常");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 填充单元格内容
	 * 2016年10月10日
	 * By liusl
	 * @param resultSet
	 * @param dataSheet
	 * @throws Exception
	 */
	public abstract void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws Exception;
	
	/**
	 * 设置表头
	 * 2016年10月10日
	 * By liusl
	 * @param dataSheet
	 */
	public abstract void wrapperHeader(Sheet dataSheet);

}
