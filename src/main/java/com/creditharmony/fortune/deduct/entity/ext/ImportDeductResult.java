package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 线下划扣导入结果扩展类
 * @Class Name ImportDeductResult
 * @author 韩龙
 * @Create In 2015年12月19日
 */
public class ImportDeductResult implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -6403407409463335114L;
	
	// 出借编号
	private String lendCode;
	// 失败金额
	private String deductFailMoney;
	// 失败标识
	private boolean deductFailFlag = true;
	// 成功金额
	private String deductMoney;
	// 交易状态
	private String status;
	// 交易结果
	private String ConfirmResult;
	// 交易时间
	private String tradingTime;
	
	/*************好易联上传***************************/
	// 备注（出借编号）
	@ExcelField(title = "备注", type = 2,groups=1)
	private String hylApplyCode;
	// 处理状态
	@ExcelField(title = "处理状态", type = 2,groups=1)
	private String hylDictDeductStatus;
	// 交易金额(元)
	@ExcelField(title = "交易金额(元)", type = 2,groups=1)
	private String hylActualDeductMoney;
	// 交易结果
	@ExcelField(title = "交易结果", type = 2,groups=1)
	private String hylConfirmResult;
	// 原因
	@ExcelField(title = "原因", type = 2,groups=1)
	private String hylConfirmOpinion;
	
	/*************富有上传***************************/
	// 处理状态
	@ExcelField(title = "处理状态", type = 2,groups=0)
	private String fdictDeductStatus;
	// 返回附言
	@ExcelField(title = "返回附言", type = 2,groups=0)
	private String fconfirmOpinion;
	// 企业流水号（出借编号）
	@ExcelField(title = "企业流水号", type = 2,groups=0)
	private String fapplyCode;
	// 备注
	@ExcelField(title = "备注", type = 2,groups=0)
	private String fconfirmResult;
	// 金额
	@ExcelField(title = "金额", type = 2,groups=0)
	private String factualDeductMoney;
	
	/*************中金上传***************************/
	// 备注信息（出借编号）
	@ExcelField(title = "备注信息", type = 2,groups=2)
	private String zjRemks;
	// 交易状态
	@ExcelField(title = "交易状态", type = 2,groups=2)
	private String zjRradingStatus;
	// 银行响应代码
	@ExcelField(title = "银行响应代码", type = 2,groups=2)
	private String zjResponseCode;
	// 银行响应消息
	@ExcelField(title = "银行响应消息", type = 2,groups=2)
	private String zjRespInformation;
	// 金额
	@ExcelField(title = "金额", type = 2,groups=2)
	private String zjTradingMoney;
	// 交易时间
	@ExcelField(title = "交易时间", type = 2,groups=2)
	private String zjTradingTime;
	
	/*************通联上传***************************/
	// 备注（出借编号）
	@ExcelField(title = "备注", type = 2,groups=3)
	private String tlRemarks;
	// 原因
	@ExcelField(title = "原因", type = 2,groups=3)
	private String tlReason;
	// 处理状态
	@ExcelField(title = "处理状态", type = 2,groups=3)
	private String tlProcessingStatus;
	// 交易金额
	@ExcelField(title = "交易金额", type = 2,groups=3)
	private String tlTradingMoney;
	// 提交时间
	@ExcelField(title = "提交时间", type = 2,groups=3)
	private String tlTradingTime;
	
	
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmResult() {
		return ConfirmResult;
	}
	public void setConfirmResult(String confirmResult) {
		ConfirmResult = confirmResult;
	}
	public String getTradingTime() {
		return tradingTime;
	}
	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}
	public boolean getDeductFailFlag() {
		return deductFailFlag;
	}
	public void setDeductFailFlag(boolean deductFailFlag) {
		this.deductFailFlag = deductFailFlag;
	}
	public String getHylApplyCode() {
		return hylApplyCode;
	}
	public void setHylApplyCode(String hylApplyCode) {
		this.hylApplyCode = hylApplyCode;
	}
	public String getHylDictDeductStatus() {
		return hylDictDeductStatus;
	}
	public void setHylDictDeductStatus(String hylDictDeductStatus) {
		this.hylDictDeductStatus = hylDictDeductStatus;
	}
	public String getHylActualDeductMoney() {
		return hylActualDeductMoney;
	}
	public void setHylActualDeductMoney(String hylActualDeductMoney) {
		this.hylActualDeductMoney = hylActualDeductMoney;
	}
	public String getHylConfirmResult() {
		return hylConfirmResult;
	}
	public void setHylConfirmResult(String hylConfirmResult) {
		this.hylConfirmResult = hylConfirmResult;
	}
	public String getHylConfirmOpinion() {
		return hylConfirmOpinion;
	}
	public void setHylConfirmOpinion(String hylConfirmOpinion) {
		this.hylConfirmOpinion = hylConfirmOpinion;
	}
	public String getFdictDeductStatus() {
		return fdictDeductStatus;
	}
	public void setFdictDeductStatus(String fdictDeductStatus) {
		this.fdictDeductStatus = fdictDeductStatus;
	}
	public String getFconfirmOpinion() {
		return fconfirmOpinion;
	}
	public void setFconfirmOpinion(String fconfirmOpinion) {
		this.fconfirmOpinion = fconfirmOpinion;
	}
	public String getFapplyCode() {
		return fapplyCode;
	}
	public void setFapplyCode(String fapplyCode) {
		this.fapplyCode = fapplyCode;
	}
	public String getFconfirmResult() {
		return fconfirmResult;
	}
	public void setFconfirmResult(String fconfirmResult) {
		this.fconfirmResult = fconfirmResult;
	}
	public String getFactualDeductMoney() {
		return factualDeductMoney;
	}
	public void setFactualDeductMoney(String factualDeductMoney) {
		this.factualDeductMoney = factualDeductMoney;
	}
	public String getDeductFailMoney() {
		return deductFailMoney;
	}
	public void setDeductFailMoney(String deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}
	public String getZjRemks() {
		return zjRemks.split("_")[1];
	}
	public void setZjRemks(String zjRemks) {
		this.zjRemks = zjRemks;
	}
	public String getZjRradingStatus() {
		return zjRradingStatus;
	}
	public void setZjRradingStatus(String zjRradingStatus) {
		this.zjRradingStatus = zjRradingStatus;
	}
	public String getZjResponseCode() {
		return zjResponseCode;
	}
	public void setZjResponseCode(String zjResponseCode) {
		this.zjResponseCode = zjResponseCode;
	}
	public String getZjRespInformation() {
		return zjRespInformation;
	}
	public void setZjRespInformation(String zjRespInformation) {
		this.zjRespInformation = zjRespInformation;
	}
	public String getZjTradingMoney() {
		return zjTradingMoney;
	}
	public void setZjTradingMoney(String zjTradingMoney) {
		this.zjTradingMoney = zjTradingMoney;
	}
	public String getZjTradingTime() {
		return zjTradingTime;
	}
	public void setZjTradingTime(String zjTradingTime) {
		this.zjTradingTime = zjTradingTime;
	}
	public String getTlRemarks() {
		return tlRemarks;
	}
	public void setTlRemarks(String tlRemarks) {
		this.tlRemarks = tlRemarks;
	}
	public String getTlReason() {
		return tlReason;
	}
	public void setTlReason(String tlReason) {
		this.tlReason = tlReason;
	}
	public String getTlProcessingStatus() {
		return tlProcessingStatus;
	}
	public void setTlProcessingStatus(String tlProcessingStatus) {
		this.tlProcessingStatus = tlProcessingStatus;
	}
	public String getTlTradingMoney() {
		return tlTradingMoney;
	}
	public void setTlTradingMoney(String tlTradingMoney) {
		this.tlTradingMoney = tlTradingMoney;
	}
	public String getTlTradingTime() {
		return tlTradingTime;
	}
	public void setTlTradingTime(String tlTradingTime) {
		this.tlTradingTime = tlTradingTime;
	}
}
