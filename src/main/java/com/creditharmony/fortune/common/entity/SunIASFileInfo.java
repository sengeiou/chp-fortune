package com.creditharmony.fortune.common.entity;

public class SunIASFileInfo {

	// 业务流水号（长度可以自定义，此为唯一标识）
	private String busiSerialNo;

	/**
	 * 影像上传的时间（此时间为第一次跟影像服务器交互时间为准， 不管后期为修改还是上传都要传第一次的时间，传入其他时间 点无效）
	 */
	private String queryTime;

	/**
	 * 0100100
	 * 针对与该用户对系统的操作权限 新增权限、查看权限、删除权限、修改权限、
	 * 打印权限、批注权限、管理员权限。0无权,1有权。删除、修改、打印、批注针对的是服务器上的文件。如果不包含 管理员权限，只能操作自己上传的文件
	 */
	private String right;

	public String getBusiSerialNo() {
		return busiSerialNo;
	}

	public void setBusiSerialNo(String busiSerialNo) {
		this.busiSerialNo = busiSerialNo;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}
	
}
