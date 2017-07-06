package com.creditharmony.fortune.look.apply.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.Reflections;
import com.creditharmony.core.dict.util.DictUtils;
import com.google.common.collect.Lists;


/**
 * 导出Excel
 * @Class Name ExportExcel
 * @Create In 2016年4月24日
 */
public class ExportExcel4BD {
	
	private static Logger log = LoggerFactory.getLogger(ExportExcel4BD.class);
			
	//工作薄对象
	private SXSSFWorkbook wb;
	// 工作表对象
	private Sheet sheet;
	//样式列表
	private Map<String, CellStyle> styles;
	//当前行号
	private int rownum;
	//注解列表（Object[]{ ExcelField, Field/Method }）
	List<Object[]> annotationList = Lists.newArrayList();
	private Map<String, CellStyle> stylesTempMap;
	
	//需要特色处理的单元格
	private Map<String,Object> cellBackGroundColorMap = null;

	public ExportExcel4BD(Class<?> cls) {
		this(null, cls);
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 */
	public ExportExcel4BD(String title, Class<?> cls){
		this(title, cls, 1);
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 * @param type 导出类型（1:导出数据；2：导出模板）
	 * @param groups 导入分组
	 */
	public ExportExcel4BD(String title, Class<?> cls, int type, int... groups){
		// Get annotation field 
		Field[] fieldArray = cls.getDeclaredFields();
		for (Field itemField : fieldArray){
			ExcelField4BD excelAnno = itemField.getAnnotation(ExcelField4BD.class);
			if (excelAnno != null && (excelAnno.type()==0 || excelAnno.type()==type)){
				if (groups!=null && groups.length>0){
					boolean inGroup = false;
					for (int g : groups){
						if (inGroup){
							break;
						}
						for (int efg : excelAnno.groups()){
							if (g == efg){
								inGroup = true;
								annotationList.add(new Object[]{excelAnno, itemField});
								break;
							}
						}
					}
				}else{
					annotationList.add(new Object[]{excelAnno, itemField});
				}
			}
		}
	
		// Initialize
		List<String> headerList = Lists.newArrayList();
		for (Object[] os : annotationList){
			String t = ((ExcelField4BD)os[0]).title();
			// 如果是导出，则去掉注释
			if (type==1){
				String[] ss = StringUtils.split(t, "**", 2);
				if (ss.length==2){
					t = ss[0];
				}
			}
			headerList.add(t);
		}
		initialize(title, headerList);
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头数组
	 */
	public ExportExcel4BD(String title, String[] headers) {
		initialize(title, Lists.newArrayList(headers));
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	public ExportExcel4BD(String title, List<String> headerList) {
		initialize(title, headerList);
	}
	
	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headerList 表头列表
	 */
	private void initialize(String title, List<String> headerList) {
		this.wb = new SXSSFWorkbook(-1);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		this.stylesTempMap = new HashMap<String, CellStyle>();
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}
	
	/**
	 * 创建表格样式
	 * @param wb 工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		styles.put("data3", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
//		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);
		
		return styles;
	}

	/**
	 * 添加一行
	 * @return 行对象
	 */
	public Row addRow(){
		return sheet.createRow(rownum++);
	}

	/**
	 * 添加一个单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @param align 对齐方式（1：靠左；2：居中；3：靠右）
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val, int align,	Class<?> fieldType, ExcelField4BD excelField4, String fieldName) {
		Cell cell = row.createCell(column);
		CellStyle style = styles.get("data" + (align >= 1 && align <= 3 ? align : ""));
		CellStyle tempStyle;
		
		if (StringUtils.isNotBlank(excelField4.format())) {  // 有特殊格式，格式化
			tempStyle = stylesTempMap.get(fieldName);
			if (tempStyle == null) {
				CellStyle cpStyle = wb.createCellStyle();
				cpStyle.cloneStyleFrom(style);
				stylesTempMap.put(fieldName, cpStyle);
				tempStyle = cpStyle;
				DataFormat fmt = wb.createDataFormat();
				tempStyle.setDataFormat(fmt.getFormat(excelField4.format()));
			}
			cell.setCellValue("");
			cell.setCellStyle(tempStyle);
		} else {  //无特殊格式
			cell.setCellStyle(style);
		}
		try {
			if (val == null) {
				CellStyle cellStyleNull = stylesTempMap.get("cellStyleNull");
				if (cellStyleNull == null) {
					CellStyle cpStyle = wb.createCellStyle();
					cpStyle.cloneStyleFrom(style);
					stylesTempMap.put("cellStyleNull", cpStyle);
					cellStyleNull = cpStyle;
					cell.setCellValue("");
					DataFormat fmt = wb.createDataFormat();
					cellStyleNull.setDataFormat(fmt.getFormat("@"));
				}
				cell.setCellStyle(cellStyleNull);
			} else if (val instanceof String) {
				if (excelField4.dataType() == Double.class) {
					cell.setCellValue(Double.parseDouble(String.valueOf(val)));
				} else {
					cell.setCellValue((String) val);
				}
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				CellStyle cellStyleTemp = stylesTempMap.get("dateType");
				if (cellStyleTemp == null) {
					CellStyle cpStyle = wb.createCellStyle();
					cpStyle.cloneStyleFrom(style);
					stylesTempMap.put("dateType", cpStyle);
					cellStyleTemp = cpStyle;
					DataFormat dateFormat = wb.createDataFormat();
					cellStyleTemp.setDataFormat(dateFormat.getFormat("yyyy-MM-dd"));
				}
				cell.setCellStyle(cellStyleTemp);
				cell.setCellValue((Date) val);
			} else {
				if (fieldType != Class.class) {
					cell.setCellValue((String) fieldType.getMethod("setValue",
							Object.class).invoke(null, val));
				} else {
					cell.setCellValue((String) Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),"fieldtype."+ val.getClass().getSimpleName()+ "Type"))
							.getMethod("setValue", Object.class)
							.invoke(null, val));
				}
			}
		} catch (Exception ex) {
			log.info("Set cell value [" + row.getRowNum() + "," + column
					+ "] error: " + ex.toString());
			cell.setCellValue(val != null ? val.toString() : "");
		}
		return cell;
	}
	
	/**
	 *  设置颜色
	 * 2016年3月31日
	 * By 肖长伟
	 * @param row
	 * @param column
	 * @param val
	 * @param align
	 * @param fieldType
	 * @return
	 */
	public Cell addCell(Row row, int column, Object val, int align, 	Class<?> fieldType, ExcelField4BD ef, String fieldName, Font font) {
		Cell cell  = addCell(row, column, val, align, fieldType, ef, fieldName);
		cell.getCellStyle().setFont(font);
		return cell;
	}

	/**
	 * 添加数据（通过annotation.ExportField添加数据）
	 * @return list 数据列表
	 */
	public <E> ExportExcel4BD setDataList(List<E> list){
		for (E e : list) {
			int colunm = 0;
			Row row = this.addRow();
			for (Object[] itemAnno : annotationList) {
				ExcelField4BD ef = (ExcelField4BD)itemAnno[0];
				Object val = null;
				Field field = (Field)itemAnno[1];
				try {
					field.setAccessible(true);
					val = field.get(e);
					field.setAccessible(false);
				}catch(Exception ex) {
					log.info(ex.toString());
					val = "";
				}
				this.addCell(row, colunm++, val, ef.align(), ef.fieldType(), ef, field.getName());
			}
			if (rownum % 100 == 0) {
				try {
					((SXSSFSheet)sheet).flushRows();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return this;
	}
	
	public void flush() throws IOException {
		((SXSSFSheet)sheet).flushRows();
	}
	
	/**
	 * 设置文字的颜色，满足条件的正行被设置为指定的颜色
	 * 2016年3月31日
	 * By 肖长伟
	 * @param list
	 * @param filedName
	 * @param filedValue
	 * @param fontColor
	 * @return
	 */
	public <E> ExportExcel4BD setDataListWithRowColor(List<E> list, String filedName, String filedValue, short fontColor){
		for (E e : list){
			int colunm = 0;
			Row row = this.addRow();
			StringBuilder sb = new StringBuilder();
			
			Font font1 = wb.createFont();
			Font font2 = wb.createFont();
			font2.setColor(IndexedColors.RED.index);
			Font curent;
			
			//获取设置颜色的行
			boolean isSetColor = false;
			
			for (Object[] os : annotationList){
				ExcelField4BD ef = (ExcelField4BD)os[0];
				Object val = null;
				// Get entity value
				try{
					if (StringUtils.isNotBlank(ef.value())){
						val = Reflections.invokeGetter(e, ef.value());
					}else{
						if (os[1] instanceof Field){
							val = Reflections.invokeGetter(e, ((Field)os[1]).getName());
						}else if (os[1] instanceof Method){
							val = Reflections.invokeMethod(e, ((Method)os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// If is dict, get dict label
					if (StringUtils.isNotBlank(ef.dictType())){
						val = DictUtils.getDictLabel(val==null?"":val.toString(), ef.dictType(), "");
					}
				}catch(Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				
				if (filedName.equals(((Field)os[1]).getName())) {
					if (filedValue.equals(val)) {
						isSetColor = true;
						break;
					}
				}
			}
			
			for (Object[] os : annotationList){
				ExcelField4BD ef = (ExcelField4BD)os[0];
				Object val = null;
				// Get entity value
				try{
					if (StringUtils.isNotBlank(ef.value())){
						val = Reflections.invokeGetter(e, ef.value());
					}else{
						if (os[1] instanceof Field){
							val = Reflections.invokeGetter(e, ((Field)os[1]).getName());
						}else if (os[1] instanceof Method){
							val = Reflections.invokeMethod(e, ((Method)os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// If is dict, get dict label
					if (StringUtils.isNotBlank(ef.dictType())){
						val = DictUtils.getDictLabel(val==null?"":val.toString(), ef.dictType(), "");
					}
				}catch(Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				
				if (isSetColor) {
					curent = font2;
				} else {
					curent = font1;
				}
				this.addCell(row, colunm++, val, ef.align(), ef.fieldType(), ef, filedName, curent);
				sb.append(val + ", ");
			}
			
			if (rownum % 100 == 0) {
				try {
					((SXSSFSheet)sheet).flushRows();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			log.debug("Write success: ["+row.getRowNum()+"] "+sb.toString());
		}
		return this;
	}
	
	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportExcel4BD write(OutputStream os) throws IOException{
		wb.write(os);
		return this;
	}
	
	/**
	 * 输出到客户端
	 * @param fileName 输出文件名
	 */
	public ExportExcel4BD write(HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName) + ";filename*=UTF-8''" + Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public ExportExcel4BD writeFile(String name) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}
	
	/**
	 * 清理临时文件
	 */
	public ExportExcel4BD dispose(){
		wb.dispose();
		return this;
	}
	
	/**
	 * 构造函数
	 * 满足多个header的情况
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param cls 实体对象，通过annotation.ExportField获取标题
	 * @param type 导出类型（1:导出数据；2：导出模板）
	 * @param groups 导入分组
	 */
	public void reExportExcel(String title, Class<?> cls, int type, int... groups){
		// Get annotation field 
		annotationList = Lists.newArrayList();
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs){
			ExcelField4BD ef = f.getAnnotation(ExcelField4BD.class);
			if (ef != null && (ef.type()==0 || ef.type()==type)){
				if (groups!=null && groups.length>0){
					boolean inGroup = false;
					for (int g : groups){
						if (inGroup){
							break;
						}
						for (int efg : ef.groups()){
							if (g == efg){
								inGroup = true;
								annotationList.add(new Object[]{ef, f});
								break;
							}
						}
					}
				}else{
					annotationList.add(new Object[]{ef, f});
				}
			}
		}
		// Get annotation method
		Method[] ms = cls.getDeclaredMethods();
		for (Method m : ms){
			ExcelField4BD ef = m.getAnnotation(ExcelField4BD.class);
			if (ef != null && (ef.type()==0 || ef.type()==type)){
				if (groups!=null && groups.length>0){
					boolean inGroup = false;
					for (int g : groups){
						if (inGroup){
							break;
						}
						for (int efg : ef.groups()){
							if (g == efg){
								inGroup = true;
								annotationList.add(new Object[]{ef, m});
								break;
							}
						}
					}
				}else{
					annotationList.add(new Object[]{ef, m});
				}
			}
		}
		// Field sorting
		Collections.sort(annotationList, new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				return new Integer(((ExcelField4BD)o1[0]).sort()).compareTo(
						new Integer(((ExcelField4BD)o2[0]).sort()));
			};
		});
		// Initialize
		List<String> headerList = Lists.newArrayList();
		for (Object[] os : annotationList){
			String t = ((ExcelField4BD)os[0]).title();
			// 如果是导出，则去掉注释
			if (type==1){
				String[] ss = StringUtils.split(t, "**", 2);
				if (ss.length==2){
					t = ss[0];
				}
			}
			headerList.add(t);
		}
		reInitialize(title, headerList);
	}
	
	/**
	 * 满足多个header的初始化
	 * 2015年12月19日
	 * By 张永生
	 * @param title
	 * @param headerList
	 */
	private void reInitialize(String title, List<String> headerList) {
		this.styles = createStyles(wb);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
		}
		// Create header
		if (headerList == null){
			throw new RuntimeException("headerList not null!");
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			String[] ss = StringUtils.split(headerList.get(i), "**", 2);
			if (ss.length==2){
				cell.setCellValue(ss[0]);
				Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
						new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
				comment.setString(new XSSFRichTextString(ss[1]));
				cell.setCellComment(comment);
			}else{
				cell.setCellValue(headerList.get(i));
			}
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < headerList.size(); i++) {  
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
		log.debug("Initialize success.");
	}

	/**
	 * 设置单元格背景颜色
	 * @param row 行数-下标在 1开始
	 * @param column 列数-下标在0开始
	 * @param bg 颜色 exp：IndexedColors.ROSE.getIndex
	 */
	public void setCellBackGroundColor(int row, int column, short bg) {
		if(cellBackGroundColorMap == null){
			cellBackGroundColorMap = new HashMap<String,Object>();
		}
		cellBackGroundColorMap.put(row+"-"+column, bg);
	};
	
//	/**
//	 * 导出测试
//	 */
//	public static void main(String[] args) throws Throwable {
//		
//		List<String> headerList = Lists.newArrayList();
//		for (int i = 1; i <= 10; i++) {
//			headerList.add("表头"+i);
//		}
//		
//		List<String> dataRowList = Lists.newArrayList();
//		for (int i = 1; i <= headerList.size(); i++) {
//			dataRowList.add("数据"+i);
//		}
//		
//		List<List<String>> dataList = Lists.newArrayList();
//		for (int i = 1; i <=1000000; i++) {
//			dataList.add(dataRowList);
//		}
//
//		ExportExcel ee = new ExportExcel("表格标题", headerList);
//		
//		for (int i = 0; i < dataList.size(); i++) {
//			Row row = ee.addRow();
//			for (int j = 0; j < dataList.get(i).size(); j++) {
//				ee.addCell(row, j, dataList.get(i).get(j));
//			}
//		}
//		
//		ee.writeFile("target/export.xlsx");
//
//		ee.dispose();
//		
//		log.debug("Export success.");
//		
//	}

}
