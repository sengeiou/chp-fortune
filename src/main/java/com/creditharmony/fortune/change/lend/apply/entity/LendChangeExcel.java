
package com.creditharmony.fortune.change.lend.apply.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借信息变更查询列表导出excel模板类model
 * @Class Name LendChangerOutExcel
 * @author 刘雄武
 * @Create In 2016年3月1日
 */
public class LendChangeExcel extends DataEntity<LendChangeExcel> {


	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 5221114461911334560L;
	// 审核日期
	private Date dictApprovalStartDate;
	// 客户姓名
	private String custName;
	// 出借编号
	private String applyCode;
	// 首次出借日期
	private String applyLendDate;
	// 初始出借金额
	private String applyLendMoney;
	// 出借模式
	private String productName;	
	// 账单日
	private String applyBillday;
	// 变更前行别
	private String changeBeginAccountBankId;		
	// 变更前开户行
	private String changeBeginAccountBranch;	
	// 变更前账号
	private String changeBeginAccountNo;	
	// 变更后行别
	private String changeAfterAccountBankId;	
	// 变更后开户行
	private String changeAfterAccountBranch;	
	// 变更后账号
	private String changeAfterAccountNo;
	// 理财经理
	private String managerName;
	// 营业部
	private String departmentName;

    
	public Date getDictApprovalStartDate() {
		return dictApprovalStartDate;
	}

	public void setDictApprovalStartDate(Date dictApprovalStartDate) {
		this.dictApprovalStartDate = dictApprovalStartDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(String applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public String getChangeBeginAccountBankId() {
		return changeBeginAccountBankId;
	}

	public void setChangeBeginAccountBankId(String changeBeginAccountBankId) {
		this.changeBeginAccountBankId = changeBeginAccountBankId;
	}

	public String getChangeBeginAccountBranch() {
		return changeBeginAccountBranch;
	}

	public void setChangeBeginAccountBranch(String changeBeginAccountBranch) {
		this.changeBeginAccountBranch = changeBeginAccountBranch;
	}

	public String getChangeBeginAccountNo() {
		return changeBeginAccountNo;
	}

	public void setChangeBeginAccountNo(String changeBeginAccountNo) {
		this.changeBeginAccountNo = changeBeginAccountNo;
	}

	public String getChangeAfterAccountBankId() {
		return changeAfterAccountBankId;
	}

	public void setChangeAfterAccountBankId(String changeAfterAccountBankId) {
		this.changeAfterAccountBankId = changeAfterAccountBankId;
	}

	public String getChangeAfterAccountBranch() {
		return changeAfterAccountBranch;
	}

	public void setChangeAfterAccountBranch(String changeAfterAccountBranch) {
		this.changeAfterAccountBranch = changeAfterAccountBranch;
	}

	public String getChangeAfterAccountNo() {
		return changeAfterAccountNo;
	}

	public void setChangeAfterAccountNo(String changeAfterAccountNo) {
		this.changeAfterAccountNo = changeAfterAccountNo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

}