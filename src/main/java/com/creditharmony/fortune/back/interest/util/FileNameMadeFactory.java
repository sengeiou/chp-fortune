package com.creditharmony.fortune.back.interest.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.creditharmony.fortune.back.interest.contants.ExportConstant;

/**
 * 生成导出文件的文件名
 * @Class Name FileNameMadeFactory 
 * @author 李志伟
 * @Create In 2016年3月19日
 */
public class FileNameMadeFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fileNameIndex;
	private String index;
	public FileNameMadeFactory() {
		super();
	}
	
	/**
	 * @param fileNameIndex 文件名前缀
	 * @param index 文件导出序号
	 */
	public FileNameMadeFactory(String fileNameIndex, String index){
		this.fileNameIndex = fileNameIndex;
		this.index = index;
	}
	
	public String getFileName(){
		return fileNameIndex;
	}
	public String getIndex(){
		return index;
	}
	
	/**
	 * 富有、中金、通联生成文件名(初始化对象的时候，把参数给够了，调用这个方法就可以了，)
	 * 2016年3月19日
	 * by 李志伟
	 * @return
	 */
	public String zjAndFyMadeFileName(){
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(date);
		String name = getFileName() + "" + str + "_" + strJoin(getIndex(), getFileName());
		return name;
	}
	
	/**
	 * 拼接序号
	 * 2016年3月19日
	 * by 李志伟
	 * @param index 第多少号文件编号
	 * @param ptMc	平台导出文件名前缀
	 * @return
	 */
	public String strJoin(String index, String ptMc){
		
		 if(ptMc.equals(ExportConstant.ZJ_EXPORT) || ptMc.equals(ExportConstant.FY_EXPORT)){
			 if(index.length() == 1){
				 index = "000" + index;
			 }else if(index.length() == 2){
				 index = "00" + index;
			 }else if(index.length() == 3){
				 index = "0" + index;
			 }else if(index.length() == 4){
				 // 不做任何处理
			 }else if(index.length() > 4){
	            System.out.println("index is out of range");
			 }
		 }else{
			 
			 if(index.length() == 1){
				 index = "0000" + index;
			 }else if(index.length() == 2){
				 index = "000" + index;
			 }else if(index.length() == 3){
				 index = "00" + index;
			 }else if(index.length() == 4){
				 index = "0" + index;
			 }else if(index.length() > 4){
	            System.out.println("index is out of range");
			 }
		 }
		 return index;
	}
}