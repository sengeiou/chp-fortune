package com.creditharmony.fortune.deduct.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.utils.FormatUtils;
import com.creditharmony.fortune.utils.FortuneDictUtil;

public abstract class BaseExportExcel {

	private static Logger logger = LoggerFactory.getLogger(BaseExportExcel.class);
	
	/**
	 * 样式列表
	 */
	public Map<String, CellStyle> styles;
	
	/**
	 * 
	 * 2016年4月18日
	 * By 韩龙
	 * @param queryMap 查询条件的值
	 * @param response 响应对象
	 * @param title    导出excel表格的表头信息
	 * @param field	       查询语句所查的字段名字
	 */
	public void exportData (Object deductPoolExt,
			HttpServletResponse response,String title,String[][] field,
			String sql,String fileName){
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
			this.styles = createStyles(wb);
			Sheet dataSheet = wb.createSheet("ExportList");
			wrapperHeader(dataSheet,title);
			assembleExcelCell(resultSet, dataSheet,field);
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
	 * 获取样式
	 * 2016年4月19日
	 * By 韩龙
	 * @param wb
	 * @return
	 */
	private Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 14);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);
		return styles;
	}
	
	/**
	 * 设置单元格值
	 * 2016年4月21日
	 * By 韩龙
	 * @param resultSet
	 * @param fields
	 * @param dataRow
	 * @throws SQLException
	 */
	public void setCell(ResultSet resultSet, String[][] fields, Row dataRow)
			throws SQLException {
		for (int i = 0; i < fields.length; i++) {
			// 创建单元格
			Cell cell = dataRow.createCell(i);
			String value = fields[i][1];
			// 字符串不需要格式
			if(value == null){
				cell.setCellValue(resultSet.getString(fields[i][0]));
			}else{
				// 格式金额
				if(value.startsWith("fm_")){
					String[] format = value.split("_");
					String pare = DeductUtils.isNullMoney(resultSet.getString(fields[i][0]));
					cell.setCellValue(FormatUtils.getFormatNumber(DeductUtils
							.isNullBigDecimal(new BigDecimal(pare)),format[1]));
				}// 组合字段
				else if(value.startsWith("comb_")){
					String[] comb = value.split("_");
					String [] format = comb[1].split("#");
					String celValue = format[0] + "_" + 
							resultSet.getString(format[1]); /*+ 
							"_" + IdGen.uuid();*/
					cell.setCellValue(celValue);
				}
				// 格式化日期
				else if(value.startsWith("fd_")){
//						String[] format = value.split("_");
				}// 静态文本文件
				else if(value.startsWith("label_")){
					String[] format = value.split("_");
					cell.setCellValue(format[1]);
				}// 获取共共方法团队经理
				else if(value.startsWith("team")){
					FortuneOrg team = RelationShipUtil.getTeamOrg(resultSet.getString(fields[i][0]));
					if (team != null) {
						List<String> type = new ArrayList<String>();
						type.add(FortuneRole.TEAM_MANAGER.id);
						List<FortuneUser> fuser = RelationShipUtil
								.getOrgMember(team.getId(), type,null);
						if (fuser.size() > 0) {
							cell.setCellValue(fuser.get(0).getName());
						}
					}
				}
				// 取数据字典项
				else{
					List<Dict> dicts = ConstantField.deductDictMap.get(value);
					String cellValue = FortuneDictUtil.dictName(dicts, resultSet.getString(fields[i][0]), "");
					cell.setCellValue(cellValue);
				}
			}
		}
	}
	
	/**
	 * 设置标题
	 * 2016年4月21日
	 * By 韩龙
	 * @param dataSheet
	 * @param title
	 */
	public abstract void wrapperHeader(Sheet dataSheet, String title);

	/**
	 * 遍历结果
	 * 2016年4月21日
	 * By 韩龙
	 * @param resultSet
	 * @param dataSheet
	 * @param field
	 * @throws SQLException
	 */
	public abstract void assembleExcelCell(ResultSet resultSet, Sheet dataSheet,
			String[][] field) throws SQLException;

}
