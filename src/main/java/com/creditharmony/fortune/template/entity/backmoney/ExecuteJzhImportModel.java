package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 执行回款，金账户导入Model
 * 
 * @Class Name ExecuteJzhImportModel
 * @author 陈广鹏
 * @Create In 2015年12月26日
 */
public class ExecuteJzhImportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -2993606252873446532L;
	/**
	 * 序号
	 */
	@ExcelField(title = "序号")
	private Integer serialNum;
	/**
	 * 付款方登录名
	 */
	@ExcelField(title = "付款方登录名")
	private String outLoginName;
	/**
	 * 付款方中文名称
	 */
	@ExcelField(title = "付款方中文名称")
	private String outChineseName;
	/**
	 * 付款资金来自冻结
	 */
	@ExcelField(title = "付款资金来自冻结")
	private String outFreeze;
	/**
	 * 收款方登录名
	 */
	@ExcelField(title = "收款方登录名")
	private String inLoginName;
	/**
	 * 收款方中文名称
	 */
	@ExcelField(title = "收款方中文名称")
	private String inChineseName;
	/**
	 * 收款后立即冻结
	 */
	@ExcelField(title = "收款后立即冻结")
	private String inFreeze;
	/**
	 * 实际回款金额
	 */
	@ExcelField(title = "交易金额")
	private Double backActualbackMoney;
	/**
	 * 备注 = 出借编号 + "_" + 回款类型
	 */
	@ExcelField(title = "备注信息")
	private String remark;
	/**
	 * 预授权合同号
	 */
	@ExcelField(title = "预授权合同号")
	private String preContractNo;
	/**
	 * 返回码
	 */
	@ExcelField(title = "返回码")
	private String returnCode;
	/**
	 * 返回描述
	 */
	@ExcelField(title = "返回描述")
	private String returnDescription;
	/**
	 * 收款方证件号
	 */
	@ExcelField(title = "收款方证件号")
	private String inCerNo;
	/**
	 * 付款方证件号
	 */
	@ExcelField(title = "付款方证件号")
	private String outCerNo;

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public String getOutLoginName() {
		return outLoginName;
	}

	public void setOutLoginName(String outLoginName) {
		this.outLoginName = outLoginName;
	}

	public String getOutChineseName() {
		return outChineseName;
	}

	public void setOutChineseName(String outChineseName) {
		this.outChineseName = outChineseName;
	}

	public String getOutFreeze() {
		return outFreeze;
	}

	public void setOutFreeze(String outFreeze) {
		this.outFreeze = outFreeze;
	}

	public String getInLoginName() {
		return inLoginName;
	}

	public void setInLoginName(String inLoginName) {
		this.inLoginName = inLoginName;
	}

	public String getInChineseName() {
		return inChineseName;
	}

	public void setInChineseName(String inChineseName) {
		this.inChineseName = inChineseName;
	}

	public String getInFreeze() {
		return inFreeze;
	}

	public void setInFreeze(String inFreeze) {
		this.inFreeze = inFreeze;
	}

	public Double getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPreContractNo() {
		return preContractNo;
	}

	public void setPreContractNo(String preContractNo) {
		this.preContractNo = preContractNo;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnDescription() {
		return returnDescription;
	}

	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}

	public String getInCerNo() {
		return inCerNo;
	}

	public void setInCerNo(String inCerNo) {
		this.inCerNo = inCerNo;
	}

	public String getOutCerNo() {
		return outCerNo;
	}

	public void setOutCerNo(String outCerNo) {
		this.outCerNo = outCerNo;
	}

}
