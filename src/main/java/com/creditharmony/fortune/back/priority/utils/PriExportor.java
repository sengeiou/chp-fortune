package com.creditharmony.fortune.back.priority.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;

/**
 * 回款导出公用类，导出时需继承此类
 * @Class Name BmExportor
 * @author 陈广鹏
 * @Create In 2016年4月15日
 */
public abstract class PriExportor {
	
	private String filename;
	
	private String sheetname;
	
	public PriExportor(String filename){
		this.filename = filename;
		this.sheetname = "DataList";
	}
	
	public PriExportor(String filename, String sheetname){
		this.filename = filename;
		this.sheetname = sheetname;
	}
	
	private static Logger logger = LoggerFactory.getLogger(PriExportor.class);
	
	/**
	 * 导出Excel方法
	 * 2016年4月15日
	 * By 陈广鹏
	 * @param view
	 * @param response
	 */
	public void exportData(PriorityListItemView view,
			HttpServletResponse response) {
		
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			view.setDataRights(dataRights);
		}
		
		// 设置搜索条件
		view = PmUtils.assembleExportCondition(view);
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							"com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyListDao.findExportList",
							view, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
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
	 * 2016年4月15日
	 * By 陈广鹏
	 * @param resultSet
	 * @param dataSheet
	 * @throws Exception
	 */
	public abstract void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws Exception;
	
	/**
	 * 设置表头
	 * 2016年4月15日
	 * By 陈广鹏
	 * @param dataSheet
	 */
	public abstract void wrapperHeader(Sheet dataSheet);

}
