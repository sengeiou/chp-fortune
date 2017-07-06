
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 出借人信息变更查询列表导出excel模板类model
 * @Class Name LendChangerOutExcel
 * @author 刘雄武
 * @Create In 2016年3月1日
 */
public class LenderChangerOutExcel implements Serializable {

	private static final long serialVersionUID = 6389744963857576135L;

	// 审核日期
	@ExcelField(title = "审核日期")
	private Date dictApprovalStartDate;

	// 客户姓名
	@ExcelField(title = "客户姓名")
	private String custName;

	// 客户编号
	@ExcelField(title = "客户编号")
	private String custCode;

	// 变更后的信息
	@ExcelField(title = "变更后的信息")
	private String lenderAfter;

	// 理财经理
	@ExcelField(title = "理财经理")
	private String managerName;

	// 营业部
	@ExcelField(title = "营业部")
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

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getLenderAfter() {
		return lenderAfter;
	}

	public void setLenderAfter(String lenderAfter) {
		this.lenderAfter = lenderAfter;
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