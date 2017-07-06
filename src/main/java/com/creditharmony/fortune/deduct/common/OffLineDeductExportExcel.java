package com.creditharmony.fortune.deduct.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.deduct.service.PlatformBankService;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.common.ExportType;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.utils.FileZip;
import com.creditharmony.fortune.utils.FormatUtils;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 线下划扣拆分工具类
 * @Class Name DeductExportExcelUtil
 * @author 韩龙
 * @Create In 2016年3月2日
 */
public class OffLineDeductExportExcel{
	
	// 导出笔数
	private int currentSplitNum;
	// 当前平台
	private String currentDeductPlatId;
	// 文件序号
	private int fileIndex = 1; 
	// 文件名字
	private String flieName;
	// 输出流
	private OutputStream out;
	// 文件流集合
	private Map<String,InputStream> mapFile;
	
	private HttpServletResponse response;
	// 导出类型
	private String exportType;
	
	// 查询字段
	private String[][] field;
	
	private String title;
	
	/**
	 * 获取platformRuleDao对象
	 */
	private static PlatformBankService platformBankService = SpringContextHolder.getBean(PlatformBankService.class);

	protected Logger logger = LoggerFactory.getLogger(OffLineDeductExportExcel.class);
	
	/**
	 * 构造方法
	 * @param response
	 * @param fileName
	 */
	public OffLineDeductExportExcel(HttpServletResponse response,String exportType,String fileName,String deductPlatId){
		this.flieName = fileName;
		this.currentDeductPlatId = deductPlatId;
		this.currentSplitNum = Constant.splitNum.get(deductPlatId);
		this.response = response;
		this.exportType = exportType;
		mapFile = new HashMap<String,InputStream>();
	}
	
	/**
	 * 文件名
	 * 2016年2月20日
	 * By 韩龙
	 * @return
	 */
	private String getFileName(){
		if(currentDeductPlatId.equals(DeductPlat.ZJPT.value)){
			logger.debug("线下划扣中金导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "0000"));
			return flieName + StringUtils.doNumFormat(fileIndex, "0000");
		}else if(currentDeductPlatId.equals(DeductPlat.FYPT.value)){
			logger.debug("线下划扣富友导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "_0000"));
			return flieName + StringUtils.doNumFormat(fileIndex, "_0000");
		}
		logger.debug("线下划扣导出文件名：" + flieName + StringUtils.doNumFormat(fileIndex, "_00000"));
		return flieName + StringUtils.doNumFormat(fileIndex, "_00000");
	}
	
	/**
	 * 下载
	 * 2016年3月29日
	 * By 韩龙
	 */
	private void download() {
		// 下载到客户端
		if (mapFile.size() > 1) {
			try {
				// 设置Header信息
				response.setHeader("Content-Disposition",
						"attachment; filename=" + flieName + ".zip"
								+ ";filename*=UTF-8''" + flieName + ".zip");
				out = response.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			FileZip.zipFiles(out, mapFile);
		} else {
			try {
				flieName = getFileName();
				// 设置Header信息
				response.setHeader("Content-Disposition",
						"attachment; filename=" + Encodes.urlEncode(flieName)
								+ getExtension() + ";filename*=UTF-8''"
								+ Encodes.urlEncode(flieName) + getExtension());
				out = response.getOutputStream();
				for (String key : mapFile.keySet()) {
					FileCopyUtils.copy(mapFile.get(key), out);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据类型判断导出类型
	 * 2016年3月4日
	 * By 韩龙
	 * @return
	 */
	private String getExtension(){
		String str = "";
		// 富有、通联只能导出excel
		if(currentDeductPlatId.equals(DeductPlat.FYPT.value) || currentDeductPlatId.equals(DeductPlat.TL.value)){
			if(currentDeductPlatId.equals(DeductPlat.TL.value)){
				str = ".xls";
			}else{
				str = ".xlsx";
			}
		}else{
			// 好易联、中金可以导出excel与txt
			if(exportType.equals(ExportType.XLSX.getCode())){
				str = ".xlsx";
			}else{
				str = ".txt";
			}
		}
		return str;
	}
	
	/**
	 * 导出excel
	 * 2016年4月18日
	 * By 韩龙
	 * @param queryMap 查询条件的值
	 * @param response 响应对象
	 * @param title    导出excel表格的表头信息
	 * @param field	       查询语句所查的字段名字
	 */
	public void exportExcelData (Object deductPoolExt,String title,String[][] field,
			String sql){
		this.title = title;
		this.field = field;
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
			// 处理操作
			handleExcel(resultSet);
			// 推送到客户端
			if(mapFile.size()==0){
				SXSSFWorkbook wb = new SXSSFWorkbook();
				wb.createSheet("DataList")
				.createRow(0)
				.createCell(0)
				.setCellValue("导出该空文件原因：数据超过平台限额或没有符合条件的数据");
				String reFileName = getFileName() + getExtension();
				addInputStream(wb, mapFile,reFileName);
			}
			download();
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
	 * 处理(原始处理方法)
	 * 2016年4月22日
	 * By 韩龙
	 * @param resultSet
	 * @throws SQLException
	 */
	private void handleExcel_bak(ResultSet resultSet) throws SQLException {
		Workbook wb = createWorkbook();
//		this.styles = createStyles(wb);
		Sheet dataSheet = wb.createSheet("ExportList");
		int row = getRowNumber();
		int index = 0;
		Row dataRow;
		// 总金额
		BigDecimal sumMoney = new BigDecimal(0);
		while(resultSet.next()){
			dataRow = dataSheet.createRow(row);
			
			PlatformBankEntity pfr = new PlatformBankEntity(); 
			pfr.setBankId(resultSet.getString("account_bank"));
			pfr.setPlatformId(currentDeductPlatId);
			pfr.setDeductFlag(DeductFlagType.COLLECTION.getCode());
			pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
			pfr = platformBankService.getPlatformBank(pfr);
			// 出借编号
			String lendCode = resultSet.getString("lendCode");
			// 出借金额
			BigDecimal applyLendMoney = new BigDecimal(DeductUtils.isNullMoney(resultSet.getString("deductMoney")));
			if(pfr.getSingleLimitMoney() == null){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因为限额配置表对应平台没有相应的银行信息。");
				continue;
			}
			// 单笔限额
			BigDecimal singleLimitMoney = new BigDecimal(pfr.getSingleLimitMoney()).divide(new BigDecimal(100));
			// 单日限额
//			BigDecimal dayLimitMoney = new BigDecimal(pfr.getDayLimitMoney()).divide(new BigDecimal(100));
			if(singleLimitMoney.compareTo(new BigDecimal(0)) == 0){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因银行单笔限额为0。");
				continue;
			}
/*			if (applyLendMoney.compareTo(dayLimitMoney) == 1) {
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因出借金额大于单日限额。");
				continue;
			}else{*/
				int reIndex = 0;
				BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
				if(big[1].intValue() == 0){
					reIndex = index + big[0].intValue();
				}else{
					reIndex = index + big[0].intValue()+1;
				}
				// 总金额
				BigDecimal resumMoney = new BigDecimal(0);
				// 判断导出数据不能大于500条导出excel
				if((reIndex >= currentSplitNum && index != 0) || isQuotaMoney(resumMoney)){
					String reFileName = getFileName() + getExtension();
					titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
//					setCell(resultSet, dataRow);
					addInputStream(wb, mapFile,reFileName);
					wb = createWorkbook();
					dataSheet = wb.createSheet("ExportList");
					row = getRowNumber();
					dataRow = dataSheet.createRow(row);
					index = 0;
					fileIndex ++;
					sumMoney = new BigDecimal(0);
				}
				
				// 导出总金额
				sumMoney = sumMoney.add(applyLendMoney);
				if (singleLimitMoney.compareTo(applyLendMoney) == -1) {
//					b1 = true;
					// 拆分金额
					do {
						// 判断导出数据不能大于500条导出excel
						if(index >= currentSplitNum || isQuotaMoney(sumMoney)){
							String reFileName = getFileName() + getExtension();
							titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
//							setCell(resultSet, dataRow);
							addInputStream(wb, mapFile,reFileName);
							wb = createWorkbook();
							dataSheet = wb.createSheet("ExportList");
							row = getRowNumber();
							dataRow = dataSheet.createRow(row);
							index = 0;
							fileIndex ++;
							sumMoney = new BigDecimal(0);
						}
						// 如果金额小于单笔限额退出拆分
						if (applyLendMoney.compareTo(singleLimitMoney) == -1 
								/*&& applyLendMoney.compareTo(BigDecimal.ZERO) != 0*/) {
							String reFileName = getFileName() + getExtension();
							titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
							// 如果不等于0就输出
							if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
								setCell(resultSet, dataRow,applyLendMoney,(index+1)+"");
							}
							addInputStream(wb, mapFile,reFileName);
							wb = createWorkbook();
							dataSheet = wb.createSheet("ExportList");
							dataRow = dataSheet.createRow(row);
							row = getRowNumber();
							fileIndex ++;
							index = 0;
							break;
						}
						// 设置拆分金额
						setCell(resultSet, dataRow,singleLimitMoney,(index+1)+"");
						applyLendMoney = applyLendMoney.subtract(singleLimitMoney) ;
						index ++;
						row ++;
						dataRow = dataSheet.createRow(row);

					} while (true);
					// 如果平台要拆分条数小于单条拆分数时
					if(currentSplitNum < reIndex ){
						String reFileName = getFileName() + getExtension();
						titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
						if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
							setCell(resultSet, dataRow,applyLendMoney,(index+1)+"");
						}
						addInputStream(wb, mapFile,reFileName);
						wb = createWorkbook();
						dataSheet = wb.createSheet("ExportList");
						dataRow = dataSheet.createRow(row);
						row = getRowNumber();
						index = 0;
						fileIndex ++;
					}
					continue;
				}
				String reFileName = getFileName() + getExtension();
				titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
				setCell(resultSet, dataRow,applyLendMoney,(index+1)+"");
				addInputStream(wb, mapFile,reFileName);
				wb = createWorkbook();
				dataSheet = wb.createSheet("ExportList");
				dataRow = dataSheet.createRow(row);
				fileIndex ++;
				row = getRowNumber();
				index = 0;
//			}
			
		}
	}
	
	/**
	 * 处理（新处理方法）
	 * 2016年4月22日
	 * By 韩龙
	 * @param resultSet
	 * @throws SQLException
	 */
	private void handleExcel(ResultSet resultSet) throws SQLException {
		Workbook wb = createWorkbook();
//		this.styles = createStyles(wb);
		Sheet dataSheet = wb.createSheet("ExportList");
		int row = getRowNumber();
		int index = 0;
		Row dataRow;
		// 总金额
		BigDecimal sumMoney = new BigDecimal(0);
		dataRow = dataSheet.createRow(row);
		while(resultSet.next()){
			PlatformBankEntity pfr = new PlatformBankEntity(); 
			pfr.setBankId(resultSet.getString("account_bank"));
			pfr.setPlatformId(currentDeductPlatId);
			pfr.setDeductFlag(DeductFlagType.COLLECTION.getCode());
			pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
			pfr = platformBankService.getPlatformBank(pfr);
			// 出借编号
			String lendCode = resultSet.getString("lendCode");
			// 出借金额
			BigDecimal applyLendMoney = new BigDecimal(DeductUtils.isNullMoney(resultSet.getString("deductMoney")));
			if(pfr.getSingleLimitMoney() == null){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因为限额配置表对应平台没有相应的银行信息。");
				continue;
			}
			// 单笔限额
			BigDecimal singleLimitMoney = new BigDecimal(pfr.getSingleLimitMoney()).divide(new BigDecimal(100));
			// 单日限额
//			BigDecimal dayLimitMoney = new BigDecimal(pfr.getDayLimitMoney()).divide(new BigDecimal(100));
			if(singleLimitMoney.compareTo(new BigDecimal(0)) == 0){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因银行单笔限额为0。");
				continue;
			}
			int reIndex = 0;
			int count = 0;
			BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
			if(big[1].intValue() == 0){
				reIndex = index + big[0].intValue();
				count = big[0].intValue();
			}else{
				reIndex = index + big[0].intValue()+1;
				count = big[0].intValue()+1;
			}
			// 总金额
			BigDecimal resumMoney = new BigDecimal(0);
			// 判断导出数据不能大于500条导出excel
			if((reIndex >= currentSplitNum && index != 0) || isQuotaMoney(resumMoney)){
				String reFileName = getFileName() + getExtension();
				titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
//					setCell(resultSet, dataRow);
				addInputStream(wb, mapFile,reFileName);
				wb = createWorkbook();
				dataSheet = wb.createSheet("ExportList");
				row = getRowNumber();
				dataRow = dataSheet.createRow(row);
				index = 0;
				fileIndex ++;
				sumMoney = new BigDecimal(0);
			}
			for(int i = 0; i< count; i++){
				// 判断导出数据不能大于500条导出excel
				if(index >= currentSplitNum || isQuotaMoney(sumMoney)){
					String reFileName = getFileName() + getExtension();
					titleHandle(dataSheet,title,sumMoney.toString(),(index+1)+"");
//					setCell(resultSet, dataRow);
					addInputStream(wb, mapFile,reFileName);
					wb = createWorkbook();
					dataSheet = wb.createSheet("ExportList");
					row = getRowNumber();
					dataRow = dataSheet.createRow(row);
					index = 0;
					fileIndex ++;
					sumMoney = new BigDecimal(0);
				}
				// 如果金额小于单笔限额退出拆分
				if (applyLendMoney.compareTo(singleLimitMoney) == -1) {
					// 如果不等于0就输出
					if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
						sumMoney = sumMoney.add(applyLendMoney);
						setCell(resultSet, dataRow,applyLendMoney,(index+1)+"");
						index ++;
						row ++;
						dataRow = dataSheet.createRow(row);
					}
				}else{
					// 设置拆分金额
					setCell(resultSet, dataRow,singleLimitMoney,(index+1)+"");
					sumMoney = sumMoney.add(singleLimitMoney);
					applyLendMoney = applyLendMoney.subtract(singleLimitMoney) ;
					index ++;
					row ++;
					dataRow = dataSheet.createRow(row);
				}
			}
		}
		String reFileName = getFileName() + getExtension();
		titleHandle(dataSheet,title,sumMoney.toString(),index+"");
		addInputStream(wb, mapFile,reFileName);
	}
	
	/**
	 * 表头信息的拼接
	 * 2016年4月22日
	 * By 韩龙
	 * @param dataSheet
	 * @param title
	 * @param sumMoney
	 * @param total
	 */
	private void titleHandle(Sheet dataSheet,String title,String sumMoney,String total) {
		// 正表头
		String [] titles = title.split(",");
		Row headerRow;
		// 好易联表头
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
			// 第一表头
			Row headerRow1 = dataSheet.createRow(0);
			String[] tip = ConstantField.deductHylOffLine_title1.split(",");
			for (int i = 0; i < tip.length; i++) {
				Cell cell = headerRow1.createCell(i);
				cell.setCellValue(tip[i]);
			}
			// 第一表头内容
			// 总金额转换成分
			String money = FormatUtils.getFormatNumber(new BigDecimal(sumMoney).multiply(new BigDecimal(100)),"#000");
			getTitle(dataSheet, money, total,1);
			headerRow = dataSheet.createRow(2);
		}else if(this.currentDeductPlatId.equals(DeductPlat.TL.value)){
			// 总金额转换成分
			String money = FormatUtils.getFormatNumber(new BigDecimal(sumMoney).multiply(new BigDecimal(100)),"#000");
			getTitle(dataSheet, money, total,0);
			headerRow = dataSheet.createRow(1);
		}
		else{
			headerRow = dataSheet.createRow(0);
		}
		for (int i = 0; i < titles.length; i++) {
			Cell cell = headerRow.createCell(i);
//			cell.setCellStyle(styles.get("title"));
			cell.setCellValue(titles[i]);
		}
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
	private void setCell(ResultSet resultSet, Row dataRow,BigDecimal defaultMoney,String index)
			throws SQLException {
		for (int i = 0; i < this.field.length; i++) {
			// 创建单元格
			Cell cell = dataRow.createCell(i);
			String value = field[i][1];
			// 字符串不需要格式
			if(value == null){
				cell.setCellValue(resultSet.getString(field[i][0]));
			}else{
				// 格式金额
				if(value.startsWith("fm_")){
					String[] format = value.split("_");
					String pare = DeductUtils.isNullMoney(resultSet.getString(field[i][0]));
					cell.setCellValue(FormatUtils.getFormatNumber(DeductUtils
							.isNullBigDecimal(new BigDecimal(pare)),format[1]));
				}// 格式化日期
				else if(value.startsWith("fd_")){
//					String[] format = value.split("_");
				}// 序号
				else if(value.startsWith("index")){
					cell.setCellValue(Long.parseLong(index));
				}// 转成分
				else if(value.startsWith("defaultMoney")){
					cell.setCellValue(getDeductMoneyExcel(defaultMoney));
				}// 省转换
				else if(value.startsWith("province")){
					String province = OptionUtil.getProvinceLabel(resultSet.getString(field[i][0]));
					cell.setCellValue(province);
				}// 城市转换
				else if(value.startsWith("city")){
					String city = OptionUtil.getCityLabel(resultSet.getString(field[i][0]));
					cell.setCellValue(city);
				}
				// 静态文本文件
				else if(value.startsWith("label_")){
					String[] format = value.split("_");
					cell.setCellValue(format[1]);
				}// 组合字段
				else if(value.startsWith("comb_")){
					String[] comb = value.split("_");
					String [] format = comb[1].split("#");
					String celValue = format[0] + "_" + 
							resultSet.getString(format[1]) + getUuid();
					cell.setCellValue(celValue);
				}// 获取共共方法团队经理
				else if(value.startsWith("team")){
					FortuneOrg team = RelationShipUtil.getTeamOrg(resultSet.getString(field[i][0]));
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
					String cellValue = FortuneDictUtil.dictName(dicts, resultSet.getString(field[i][0]), "");
					cell.setCellValue(cellValue);
				}
			}
		}
	}
	
	/**
	 * 封装压缩excel对象
	 * 2016年3月3日
	 * By 韩龙
	 * @param ee
	 */
	private void addInputStream(Workbook wb,Map<String,InputStream> mapFile,String fileName){
		try {
			// 初始化内存流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
//			wb.dispose();
			// 转换成内存输出流
			InputStream input = new ByteArrayInputStream(out.toByteArray());
			mapFile.put(fileName, input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 标题
	 * 2016年2月17日
	 * By 韩龙
	 * @param sumMoney
	 * @param total
	 * @return
	 */
	private void getTitle(Sheet dataSheet,String sumMoney,String total,int row){
		Row titleRow = dataSheet.createRow(row);
		titleRow.createCell(0).setCellValue("S");
		titleRow.createCell(1).setCellValue("200100000015107");
		titleRow.createCell(2).setCellValue(DateUtils.getDate("yyyyMMdd"));
		titleRow.createCell(3).setCellValue(total);
		titleRow.createCell(4).setCellValue(sumMoney);
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
			// 好易联
			titleRow.createCell(5).setCellValue("14900");
		}else{
			// 通联
			titleRow.createCell(5).setCellValue("19900");
		}
	}

	/**
	 * 获取行数
	 * 2016年4月22日
	 * By 韩龙
	 * @return
	 */
	private int getRowNumber(){
		int row = 1;
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
			row = 3;
		}else if(this.currentDeductPlatId.equals(DeductPlat.TL.value)){
			row = 2;
		}
		return row;
	}
	
	/**
	 * 获取金额
	 * 2016年4月22日
	 * By 韩龙
	 * @param defaultMoney
	 * @return
	 */
	private String getDeductMoneyExcel(BigDecimal defaultMoney){
		String money = "";
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value) ||
				this.currentDeductPlatId.equals(DeductPlat.TL.value)){
			money = FormatUtils.getFormatNumber(defaultMoney.multiply(new BigDecimal(100)),"#000");
		}else{
			money = FormatUtils.getFormatNumber(defaultMoney,"#000");
		}
		return money;
	}
	
	/**
	 * 获取金额
	 * 2016年4月22日
	 * By 韩龙
	 * @param defaultMoney
	 * @return
	 */
	private String getDeductMoneyTxt(BigDecimal defaultMoney){
		return FormatUtils.getFormatNumber(defaultMoney.multiply(new BigDecimal(100)),"#000");
	}
	
	
	/**
	 * 导出TXT
	 * 2016年4月18日
	 * By 韩龙
	 * @param queryMap 查询条件的值
	 * @param response 响应对象
	 * @param title    导出excel表格的表头信息
	 * @param field	       查询语句所查的字段名字
	 */
	public void exportTxtData (Object deductPoolExt,String title,String[][] field,
			String sql){
		this.title = title;
		this.field = field;
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
			// 处理操作
			handleTxt(resultSet);
			// 推送到客户端
			if(mapFile.size()==0){
				String reFileName = getFileName() + getExtension();
				String dateRow  = "导出该空文件原因：数据超过平台限额或没有符合条件的数据";
				addInputStreamTxt(dateRow, mapFile,reFileName);
			}
			download();
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
	 * 处理(原始处理方法)
	 * 2016年4月22日
	 * By 韩龙
	 * @param resultSet
	 * @throws SQLException
	 */
	private void handleTxt_bak(ResultSet resultSet) throws SQLException {
		int index = 0;
		// 总金额
		BigDecimal sumMoney = new BigDecimal(0);
		StringBuffer dateRow = new StringBuffer();
		while(resultSet.next()){
			PlatformBankEntity pfr = new PlatformBankEntity(); 
			pfr.setBankId(resultSet.getString("account_bank"));
			pfr.setPlatformId(currentDeductPlatId);
			pfr.setDeductFlag(DeductFlagType.COLLECTION.getCode());
			pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
			pfr = platformBankService.getPlatformBank(pfr);
			// 出借编号
			String lendCode = resultSet.getString("lendCode");
			// 出借金额
			BigDecimal applyLendMoney = new BigDecimal(resultSet.getString("deductMoney"));
			if(pfr.getSingleLimitMoney() == null){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因为限额配置表对应平台没有相应的银行信息。");
				continue;
			}
			// 单笔限额
			BigDecimal singleLimitMoney = new BigDecimal(pfr.getSingleLimitMoney()).divide(new BigDecimal(100));
			// 单日限额
//			BigDecimal dayLimitMoney = new BigDecimal(pfr.getDayLimitMoney()).divide(new BigDecimal(100));
			if(singleLimitMoney.compareTo(new BigDecimal(0)) == 0){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因银行单笔限额为0。");
				continue;
			}
	/*		if (applyLendMoney.compareTo(dayLimitMoney) == 1) {
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因出借金额大于单日限额。");
				continue;
			}else{*/
				int reIndex = 0;
				BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
				if(big[1].intValue() == 0){
					reIndex = index + big[0].intValue();
				}else{
					reIndex = index + big[0].intValue()+1;
				}
				BigDecimal resumMoney = sumMoney.add(applyLendMoney);
				// 判断导出数据不能大于500条导出excel
				if((reIndex >= currentSplitNum && index != 0) || isQuotaMoney(resumMoney)){
					String reFileName = getFileName() + getExtension();
					if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
						dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
					}
//					setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
					addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
					index = 0;
					fileIndex ++;
					sumMoney = new BigDecimal(0);
					dateRow = new StringBuffer();
				}
				
				// 导出总金额
				sumMoney = sumMoney.add(applyLendMoney);
				if (singleLimitMoney.compareTo(applyLendMoney) == -1) {
//					b1 = true;
					// 拆分金额
					do {
						// 判断导出数据不能大于500条导出excel
						if(index >= currentSplitNum || isQuotaMoney(sumMoney)){
							String reFileName = getFileName() + getExtension();
							if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
								dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
							}
							addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
							index = 0;
							fileIndex ++;
							sumMoney = new BigDecimal(0);
							dateRow = new StringBuffer();
						}
						// 如果金额小于单笔限额退出拆分
						if (applyLendMoney.compareTo(singleLimitMoney) == -1 
								/*&& applyLendMoney.compareTo(BigDecimal.ZERO) != 0*/) {
							// 如果不等于0就输出
							if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
								String reFileName = getFileName() + getExtension();
								if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
									dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
								}
								setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
								addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
								fileIndex ++;
								index = 0;
								dateRow = new StringBuffer();
							}
							break;
						}
						// 设置拆分金额
						setTxt(resultSet,singleLimitMoney,(index+1)+"",dateRow);
						applyLendMoney = applyLendMoney.subtract(singleLimitMoney) ;
						index ++;

					} while (true);
					// 如果平台要拆分条数小于单条拆分数时
					if(currentSplitNum < reIndex ){
						String reFileName = getFileName() + getExtension();
						if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
							dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
						}
						if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
							setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
						}
						addInputStreamTxt("", mapFile,reFileName);
						index = 0;
						fileIndex ++;
						dateRow = new StringBuffer();
					}
					continue;
				}
				String reFileName = getFileName() + getExtension();
				if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
					dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
				}
				setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
				addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
				index = 0;
				fileIndex ++;
				sumMoney = new BigDecimal(0);
//				dateRow = new StringBuffer();
//			}
			
		}
	}
	
	/**
	 * 处理（新处理方法）
	 * 2016年4月22日
	 * By 韩龙
	 * @param resultSet
	 * @throws SQLException
	 */
	private void handleTxt(ResultSet resultSet) throws SQLException {
		int index = 0;
		// 总金额
		BigDecimal sumMoney = new BigDecimal(0);
		StringBuffer dateRow = new StringBuffer();
		while(resultSet.next()){
			PlatformBankEntity pfr = new PlatformBankEntity(); 
			pfr.setBankId(resultSet.getString("account_bank"));
			pfr.setPlatformId(currentDeductPlatId);
			pfr.setDeductFlag(DeductFlagType.COLLECTION.getCode());
			pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
			pfr = platformBankService.getPlatformBank(pfr);
			// 出借编号
			String lendCode = resultSet.getString("lendCode");
			// 出借金额
			BigDecimal applyLendMoney = new BigDecimal(resultSet.getString("deductMoney"));
			if(pfr.getSingleLimitMoney() == null){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因为限额配置表对应平台没有相应的银行信息。");
				continue;
			}
			// 单笔限额
			BigDecimal singleLimitMoney = new BigDecimal(pfr.getSingleLimitMoney()).divide(new BigDecimal(100));
			// 单日限额
//			BigDecimal dayLimitMoney = new BigDecimal(pfr.getDayLimitMoney()).divide(new BigDecimal(100));
			if(singleLimitMoney.compareTo(new BigDecimal(0)) == 0){
				logger.debug("线下划扣导出失败出借编号为" + lendCode +
						";失败原因银行单笔限额为0。");
				continue;
			}
			int reIndex = 0;
			int count = 0;
			BigDecimal [] big = applyLendMoney.divideAndRemainder(singleLimitMoney);
			if(big[1].intValue() == 0){
				reIndex = index + big[0].intValue();
				count =  big[0].intValue();
			}else{
				reIndex = index + big[0].intValue()+1;
				count =  big[0].intValue()+1;
			}
			BigDecimal resumMoney = sumMoney.add(applyLendMoney);
			// 判断导出数据不能大于500条导出excel
			if((reIndex >= currentSplitNum && index != 0) || isQuotaMoney(resumMoney)){
				String reFileName = getFileName() + getExtension();
				if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
					dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
				}
//					setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
				addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
				index = 0;
				fileIndex ++;
				sumMoney = new BigDecimal(0);
				dateRow = new StringBuffer();
			}
			// 导出总金额
			sumMoney = sumMoney.add(applyLendMoney);
			for(int i = 0; i < count; i++){
				// 判断导出数据不能大于500条导出excel
				if(index >= currentSplitNum || isQuotaMoney(sumMoney)){
					String reFileName = getFileName() + getExtension();
					if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
						dateRow.insert(0,getTitleTex(sumMoney.toString(), (index +1) + ""));
					}
					addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
					index = 0;
					fileIndex ++;
					sumMoney = new BigDecimal(0);
					dateRow = new StringBuffer();
				}
				
				// 如果金额小于单笔限额退出拆分
				if (applyLendMoney.compareTo(singleLimitMoney) == -1) {
					// 如果不等于0就输出
					if(applyLendMoney.compareTo(BigDecimal.ZERO) != 0){
						setTxt(resultSet,applyLendMoney,(index+1)+"",dateRow);
					}
					continue;
				}
				// 设置拆分金额
				setTxt(resultSet,singleLimitMoney,(index+1)+"",dateRow);
				applyLendMoney = applyLendMoney.subtract(singleLimitMoney) ;
				index ++;
			}
		}
		String reFileName = getFileName() + getExtension();
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
			dateRow.insert(0,getTitleTex(sumMoney.toString(), index + ""));
		}
		addInputStreamTxt(dateRow.toString(), mapFile,reFileName);
	}
	
	/**
	 * 生成txt流
	 * 2016年4月22日
	 * By 韩龙
	 * @param string
	 * @param mapFile2
	 * @param reFileName
	 */
	private void addInputStreamTxt(String string,
			Map<String, InputStream> mapFile2, String reFileName) {
		InputStream input = new ByteArrayInputStream(string.getBytes());
		mapFile.put(flieName, input);
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
	private void setTxt(ResultSet resultSet,BigDecimal defaultMoney,String index,StringBuffer cellString)
			throws SQLException {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < this.field.length; i++) {
			// 创建单元格
			String value = field[i][1];
			// 字符串不需要格式
			if(value == null){
				buff.append(resultSet.getString(field[i][0])).append(getTxtSeparator());
			}else{
				// 格式金额
				if(value.startsWith("fm_")){
					String[] format = value.split("_");
					String pare = DeductUtils.isNullMoney(resultSet.getString(field[i][0]));
					buff.append(FormatUtils.getFormatNumber(DeductUtils
							.isNullBigDecimal(new BigDecimal(pare)),format[1])).append(getTxtSeparator());
				}// 格式化日期
				else if(value.startsWith("fd_")){
//					String[] format = value.split("_");
				}// 序号
				else if(value.startsWith("index")){
					buff.append(getTxtIndex(index)).append(getTxtSeparator());
				}// 转成分
				else if(value.startsWith("defaultMoney")){
					buff.append(getDeductMoneyTxt(defaultMoney)).append(getTxtSeparator());
				}// 省转换
				else if(value.startsWith("province")){
					String province = OptionUtil.getProvinceLabel(resultSet.getString(field[i][0]));
					buff.append(province).append(getTxtSeparator());
				}// 城市转换
				else if(value.startsWith("city")){
					String city = OptionUtil.getCityLabel(resultSet.getString(field[i][0]));
					buff.append(city).append(getTxtSeparator());
				}
				// 静态文本文件
				else if(value.startsWith("label_")){
					String[] format = value.split("_");
					buff.append(format[1]).append(getTxtSeparator());
				}// 组合字段
				else if(value.startsWith("comb_")){
					String[] comb = value.split("_");
					String [] format = comb[1].split("#");
					String celValue = format[0] + "_" + 
							resultSet.getString(format[1]) + getUuid();
					buff.append(celValue).append(getTxtSeparator());
				}// 获取共共方法团队经理
				else if(value.startsWith("team")){
					FortuneOrg team = RelationShipUtil.getTeamOrg(resultSet.getString(field[i][0]));
					if (team != null) {
						List<String> type = new ArrayList<String>();
						type.add(FortuneRole.TEAM_MANAGER.id);
						List<FortuneUser> fuser = RelationShipUtil
								.getOrgMember(team.getId(), type,null);
						if (fuser.size() > 0) {
							buff.append(fuser.get(0).getName()).append(getTxtSeparator());
						}
					}
				}
				// 取数据字典项
				else{
					List<Dict> dicts = ConstantField.deductDictMap.get(value);
					String cellValue = FortuneDictUtil.dictName(dicts, resultSet.getString(field[i][0]), "");
					buff.append(cellValue).append(getTxtSeparator());
				}
			}
		}
		cellString.append(buff.substring(0, buff.length()-1)).append("\r\n");
	}
	/**
	 * txt标题
	 * 2016年2月17日
	 * By 韩龙
	 * @param sumMoney
	 * @param total
	 * @return
	 */
	private String getTitleTex(String sumMoney,String total){
		StringBuffer title = new StringBuffer();
		title.append("S").append(",");
		title.append("200604000000445").append(",");
		title.append(DateUtils.getDate("yyyyMMdd")).append(",");
		title.append(total).append(",");
		String money = FormatUtils.getFormatNumber(new BigDecimal(sumMoney).multiply(new BigDecimal(100)),"#000");
		title.append(money).append(",");
		
		title.append("14900").append("\r\n");
		return title.toString();
	}
	
	/**
	 * 中金加uuid
	 * 2016年4月27日
	 * By 韩龙
	 * @return
	 */
	private String getUuid(){
		if(this.currentDeductPlatId.equals(DeductPlat.ZJPT.value)){
			return "_"+IdGen.uuid();
		}
		return "";
	}
	
	/**
	 * 根据不同的平台返回分隔符
	 * 2016年5月11日
	 * By 韩龙
	 * @return
	 */
	private String getTxtSeparator(){
		if(this.currentDeductPlatId.equals(DeductPlat.ZJPT.value)){
			return "|";
		}else{
			return ",";
		}
	}
	
	/**
	 * 每个文件的限额
	 * 2016年5月7日
	 * By 韩龙
	 * @param money
	 * @return
	 */
	private boolean isQuotaMoney(BigDecimal money){
		if(this.currentDeductPlatId.equals(DeductPlat.FYPT.value)){
			// 当前一个文件的总金额大于
			if(money.compareTo(new BigDecimal("10000000")) == 1){
				return true;
			}
		}
		if(this.currentDeductPlatId.equals(DeductPlat.HYLPT.value)){
			if(money.compareTo(new BigDecimal("150000000")) == 1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取index序号
	 * 2016年5月12日
	 * By 韩龙
	 * @return
	 */
	private String getTxtIndex(String index){
		// 中金
		if(this.currentDeductPlatId.equals(DeductPlat.ZJPT.value)){
			return StringUtils.doNumFormat(Integer.valueOf(index), "00000");
		}
		return index;
	}
	
	private Workbook createWorkbook(){
		if(this.currentDeductPlatId.equals(DeductPlat.TL.value)){
			return new HSSFWorkbook();
		}
		return new SXSSFWorkbook(this.currentSplitNum*5);
	}
}
