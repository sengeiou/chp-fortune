package com.creditharmony.fortune.back.interest.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.template.entity.backInterest.BackInterestApplyExport;
import com.creditharmony.fortune.template.entity.backInterest.BackInterestApplyGoldAcc;
import com.creditharmony.fortune.template.entity.backInterest.BackInterestApprovalExport;
import com.creditharmony.fortune.template.entity.backInterest.BackInterestConfrimExport;
import com.creditharmony.fortune.template.entity.backInterest.BackReturnInterestApplyExport;
import com.creditharmony.fortune.template.entity.backInterest.ChinaFinanceExport;
import com.creditharmony.fortune.template.entity.backInterest.ExcuteBackInterestExport;
import com.creditharmony.fortune.template.entity.backInterest.ExcuteBackInterestGoldExport;
import com.creditharmony.fortune.template.entity.backInterest.FinishBackInterestExport;
import com.creditharmony.fortune.template.entity.backInterest.ZJExport;

/**
 * 导出回息数据帮助类
 * @Class Name ExportBackInterestHelper
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExportBackInterestHelper {

	private static Logger logger = LoggerFactory.getLogger(ExportBackInterestHelper.class);
	
	
	/**
	 * 导出工具
	 * 2016年4月15日
	 * by 李志伟
	 * @param queryMap 查询条件
	 * @param response	响应对象
	 * @param fileName	导出文件名字
	 * @param sqlNameSpace	查询数据用的sql名称及语句路劲
	 * @param sheetName	sheet名称
	 * @param exportFlag 模块导出标识
	 */
	public static void exportData(Map<String, Object> queryMap,
			HttpServletResponse response, String fileName, String sqlNameSpace, String sheetName, String exportFlag, String exportType) {
		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		StringBuffer sb = new StringBuffer();
		try {
			
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							sqlNameSpace,
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet(sheetName);
			
			if(exportFlag.equals(ExportConstant.applyFlag)){// 待回息申请导出Excel
				wrapperHeader(dataSheet, wb, ExportConstant.DHXSQ_TITLE);
				BackInterestApplyExport.assembleExcelCell(resultSet, dataSheet);
			
			}else if(exportFlag.equals(ExportConstant.returnApplyFlag)){// 到期待回息申请导出Excel gaoxu 2017-3-23 14:27:23
				wrapperHeader(dataSheet, wb, ExportConstant.RETURN_DHXSQ_TITLE);
				BackReturnInterestApplyExport.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.applyAccFlag)){// 待回息申请金账户导出
				
				wrapperHeader(dataSheet, wb, ExportConstant.DHXSQ_jzh_TITLE);
				BackInterestApplyGoldAcc.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.returnApplyAccFlag)){// 到期待回息申请金账户导出  gaoxu 2017-3-23 14:27:23
				
				wrapperHeader(dataSheet, wb, ExportConstant.RETURN_DHXSQ_jzh_TITLE);
				BackInterestApplyGoldAcc.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.approvalFlag)){// 待回息审批列表导出Excel
				
				wrapperHeader(dataSheet, wb, ExportConstant.DHXSP_TITLE);
				BackInterestApprovalExport.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.excuteFlag)){// 执行回息列表导出
				
				wrapperHeader(dataSheet, wb, ExportConstant.DHX_TITLE);
				ExcuteBackInterestExport.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.confrimFlag)){// 回息确认列表导出
				
				wrapperHeader(dataSheet, wb, ExportConstant.DHXQR_TITLE);
				BackInterestConfrimExport.assembleExcelCell(resultSet, dataSheet);
			}else if(exportFlag.equals(ExportConstant.finishFlag)){// 已回息列表导出
				
				wrapperHeader(dataSheet, wb, ExportConstant.YHX_TITLE);
				FinishBackInterestExport.assembleExcelCell(resultSet, dataSheet);
				
			}else if(exportFlag.equals(ExportConstant.excuteAccFlag)){// 待回息列表金账户导出
				
				wrapperHeader(dataSheet, wb, ExportConstant.DHX_JZH_TITLE);
				ExcuteBackInterestGoldExport.assembleExcelCell(resultSet, dataSheet);
			
			}else if(exportFlag.equals(ExportConstant.zjFlag)){// 中金导出
				
				if(exportType.equals(".txt")){
					
					ChinaFinanceExport.exportTxt(resultSet, sb);
				}else{
					
					wrapperHeader(dataSheet, wb, ExportConstant.ZJ_TITLE);
					ZJExport.assembleExcelCell(resultSet, dataSheet);
				}
			}
			
			if(exportType.equals(".txt")){// 导出各种方式
				
				response.reset();
				response.setContentType("application/octet-stream; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ Encodes.urlEncode(fileName + exportType)
								+ ";filename*=UTF-8''"
								+ Encodes.urlEncode(fileName+ exportType));
				OutputStream outputStream = response.getOutputStream();
				// 输出txt文本
				outputStream.write(sb.toString().getBytes());
				// 关闭数据流
				outputStream.flush();
				outputStream.close();
				
			}else{
				
				response.reset();
				response.setContentType("application/octet-stream; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ Encodes.urlEncode(fileName + exportType)
								+ ";filename*=UTF-8''"
								+ Encodes.urlEncode(fileName + exportType));
				wb.write(response.getOutputStream());
				wb.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("exportData()导出数据出现异常",e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置标题
	 * 2016年4月16日
	 * by 李志伟
	 * @param dataSheet
	 */
	public static void wrapperHeader(Sheet dataSheet, SXSSFWorkbook swb, String title) {
		
		Row headerRow = dataSheet.createRow(0);
		CellStyle cellStyle = swb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		
		String[] tit = title.split(",");
		for (int i = 0; i < tit.length; i++) {
			Cell lendCodeHeader = headerRow.createCell(i);
			lendCodeHeader.setCellValue(tit[i]);
			lendCodeHeader.setCellStyle(cellStyle);
		}
	}
}
