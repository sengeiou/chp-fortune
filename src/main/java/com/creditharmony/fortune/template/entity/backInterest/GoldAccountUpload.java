package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 执行回息金账户上传
 * @Class Name GoldAccountUpload 
 * @author 李志伟
 * @Create In 2016年3月25日
 */
public class GoldAccountUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ExcelField(title = "序号")
	private String num;
	
	@ExcelField(title = "付款方登录名")
	private String payLoginName;

	@ExcelField(title = "付款方中文名称")
	private String payChnName;
	
	@ExcelField(title = "付款资金来自冻结")
	private String payMoneyFromFrozen;
	
	@ExcelField(title = "收款方登录名")
	private String getMoneyLoginName;
	
	@ExcelField(title = "收款方中文名称")
	private String getMoneyChnName;
	
	@ExcelField(title = "收款后立即冻结")
	private String getMoneyFrozen;
	
	@ExcelField(title = "交易金额")
	private String backRealMoney;
	
	@ExcelField(title = "备注信息")
	private String backiId;
	
	@ExcelField(title = "预授权合同号")
	private String contractNo;
	
	@ExcelField(title = "返回码")
	private String returnNo;
	
	@ExcelField(title = "返回描述")
	private String returnMesg;
	
	@ExcelField(title = "收款方证件号")
	private String getMoneyCertificate;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPayLoginName() {
		return payLoginName;
	}

	public void setPayLoginName(String payLoginName) {
		this.payLoginName = payLoginName;
	}

	public String getPayChnName() {
		return payChnName;
	}

	public void setPayChnName(String payChnName) {
		this.payChnName = payChnName;
	}

	public String getPayMoneyFromFrozen() {
		return payMoneyFromFrozen;
	}

	public void setPayMoneyFromFrozen(String payMoneyFromFrozen) {
		this.payMoneyFromFrozen = payMoneyFromFrozen;
	}

	public String getGetMoneyLoginName() {
		return getMoneyLoginName;
	}

	public void setGetMoneyLoginName(String getMoneyLoginName) {
		this.getMoneyLoginName = getMoneyLoginName;
	}

	public String getGetMoneyChnName() {
		return getMoneyChnName;
	}

	public void setGetMoneyChnName(String getMoneyChnName) {
		this.getMoneyChnName = getMoneyChnName;
	}

	public String getGetMoneyFrozen() {
		return getMoneyFrozen;
	}

	public void setGetMoneyFrozen(String getMoneyFrozen) {
		this.getMoneyFrozen = getMoneyFrozen;
	}

	public String getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(String backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getReturnNo() {
		return returnNo;
	}

	public void setReturnNo(String returnNo) {
		this.returnNo = returnNo;
	}

	public String getReturnMesg() {
		return returnMesg;
	}

	public void setReturnMesg(String returnMesg) {
		this.returnMesg = returnMesg;
	}

	public String getGetMoneyCertificate() {
		return getMoneyCertificate;
	}

	public void setGetMoneyCertificate(String getMoneyCertificate) {
		this.getMoneyCertificate = getMoneyCertificate;
	}

}