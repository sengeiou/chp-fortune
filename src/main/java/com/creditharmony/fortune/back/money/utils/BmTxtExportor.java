package com.creditharmony.fortune.back.money.utils;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.money.common.view.ListItemView;

public abstract class BmTxtExportor {
	
	private String filename;
	
	public BmTxtExportor(String filename){
		this.filename = filename;
	}
	
	private static Logger logger = LoggerFactory.getLogger(BmTxtExportor.class);
	
	/**
	 * 导出TXT方法
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param view
	 * @param response
	 */
	public void exportData(ListItemView view,
			HttpServletResponse response) {
		
		// 设置搜索条件
		view = BmUtils.assembleExportCondition(view);
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							"com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao.findExportList",
							view, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			StringBuffer sb = new StringBuffer();
			assembleContent(resultSet, sb);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(filename+".txt")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(filename+".txt"));
			OutputStream outputStream = response.getOutputStream();
			// 输出txt文本
			outputStream.write(sb.toString().getBytes());
			// 关闭数据流
			outputStream.flush();
			outputStream.close();
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
	 * 组装TXT内容
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param resultSet
	 * @param stringBuffer
	 * @throws Exception
	 */
	public abstract void assembleContent(ResultSet resultSet, StringBuffer stringBuffer) throws Exception;

}
