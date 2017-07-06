
package com.creditharmony.fortune.triple.system.entity.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;
import com.creditharmony.fortune.utils.FormatUtils;


/**
 * @Class Name deliveryEx
 * @author 胡体勇
 * @Create In 2016年2月22日
 */
public class IntDeliveryEx extends DataEntity<IntDeliveryEx>{
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String deliveryType;
	private List<String> deliveryTypeList;
	private String phone;
	private String lendCode;// 出借编号
	private String empId;// 理财经理id
	private String empCode;// 理财经理编号
    private String empName;// 理财经理姓名
	private String teamManagerId;// 团队经理Id
    private String teamManagerCode;// 团队经理code
	private String teamManagerName;// 团队经理姓名
	private String orgId;// 营业部id
	private String orgName;// 营业部名称
	private String newEmpId;// 新理财经理id
	private String newEmpCode;// 新理财经理Code
	private String newEmpName;// 新理财经理姓名
	private String newTeamManagerId;// 新团队经理Id
	private String newTeamManagerCode;// 新团队经理编号
	private String newTeamManagerName;// 新团队经理名称
	private String newOrgId;// 新营业部id
    private String newOrgName;// 新营业部名称
	private Date createTime;// 创建时间
	private String operator;// 操作人
	private String batchNumber;
	private String remark;
	private String loginName;
	//扩展类
	private String customerName;// 客户姓名
	private String customerCode;// 客户编号
	private String osType;// 系统类型
	private List<String> osTypeList;
	private String osId;// 系统id
	private String orderStatus;// 成单状态
	private Date delDate; // 交割时间
	private String teamName;// 团队名称
	private String newTeamName; // 新团队名称
	private String teamId;
	private String newTeamId;
	private String cardType; // 证件类型
	private String cardId; // 证件id
	private String orgType; // 机构类型
	private String managerRole; // 理财经理角色
	private String teamManagerRole; // 团队经理角色
	private String productCode; // 产品code
	private List<String> productCodeList;
	private String productName; // 产品名称
	private Date applyLendDay; // 出借日期
	private Date applyExpireDay; // 到期日期
	private String applyPay; // 付款方式
	private BigDecimal applyLendMoney; // 出借金额
	private String lendStatus; // 出借状态
	private String applyEndStatus; // 完结状态
	private Date startTime; // 开始日期
	private Date endTime; // 结束日期
	private Date orderTime;// 成单时间
	private String modifyBy;
	private Date modifyTime;
	private String isDelivery;
	private BigDecimal totalMoney; // 总金额
	private List<String> orgIdList;// 营业部id集合
	private List<String> newOrgIdList;// 营业部id集合
	private List<String> phoneList;// 手机号集合
	private String phoneExcel;// 导表存手机号
	private String oldPhone;// 旧手机号
	private List<String> phoneExcelList;// 导表存手机号集合
	private String sendType;// 发送类型
    private String sendStatus;// 发送状态
    private Date sendTime;// 发送时间
	private String uniqueNum;// 发送唯一编号
	private String cityManager;// 城市经理
	private String cityOrg;// 城市机构
	private String bussManager;// 营业部经理
	private String changeReason;// 变更原因

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public List<String> getDeliveryTypeList() {
		return deliveryTypeList;
	}

	public void setDeliveryTypeList(List<String> deliveryTypeList) {
		this.deliveryTypeList = deliveryTypeList;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTeamManagerId() {
		return teamManagerId;
	}
	public void setTeamManagerId(String teamManagerId) {
		this.teamManagerId = teamManagerId;
	}
	public String getTeamManagerCode() {
		return teamManagerCode;
	}
	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getNewEmpId() {
		return newEmpId;
	}
	public void setNewEmpId(String newEmpId) {
		this.newEmpId = newEmpId;
	}
	public String getNewEmpCode() {
		return newEmpCode;
	}
	public void setNewEmpCode(String newEmpCode) {
		this.newEmpCode = newEmpCode;
	}
	public String getNewEmpName() {
		return newEmpName;
	}
	public void setNewEmpName(String newEmpName) {
		this.newEmpName = newEmpName;
	}
	public String getNewTeamManagerId() {
		return newTeamManagerId;
	}
	public void setNewTeamManagerId(String newTeamManagerId) {
		this.newTeamManagerId = newTeamManagerId;
	}
	public String getNewTeamManagerCode() {
		return newTeamManagerCode;
	}
	public void setNewTeamManagerCode(String newTeamManagerCode) {
		this.newTeamManagerCode = newTeamManagerCode;
	}
	public String getNewTeamManagerName() {
		return newTeamManagerName;
	}
	public void setNewTeamManagerName(String newTeamManagerName) {
		this.newTeamManagerName = newTeamManagerName;
	}
	public String getNewOrgId() {
		return newOrgId;
	}
	public void setNewOrgId(String newOrgId) {
		this.newOrgId = newOrgId;
	}
	public String getNewOrgName() {
		return newOrgName;
	}
	public void setNewOrgName(String newOrgName) {
		this.newOrgName = newOrgName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getNewTeamName() {
		return newTeamName;
	}
	public void setNewTeamName(String newTeamName) {
		this.newTeamName = newTeamName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getManagerRole() {
		return managerRole;
	}
	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
	public String getTeamManagerRole() {
		return teamManagerRole;
	}
	public void setTeamManagerRole(String teamManagerRole) {
		this.teamManagerRole = teamManagerRole;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public List<String> getProductCodeList() {
		return productCodeList;
	}
	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getLendStatus() {
		return lendStatus;
	}
	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}
	public String getApplyEndStatus() {
		return applyEndStatus;
	}
	public void setApplyEndStatus(String applyEndStatus) {
		this.applyEndStatus = applyEndStatus;
	}
	public String getStartTime() {
		return FormatUtils.getFormatDate(this.startTime, "yyyy-MM-dd");
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return FormatUtils.getFormatDate(this.endTime, "yyyy-MM-dd");
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public List<String> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}

	public List<String> getNewOrgIdList() {
		return newOrgIdList;
	}

	public void setNewOrgIdList(List<String> newOrgIdList) {
		this.newOrgIdList = newOrgIdList;
	}
	public String getOsId() {
		return osId;
	}
	public void setOsId(String osId) {
		this.osId = osId;
	}
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getNewTeamId() {
		return newTeamId;
	}
	public void setNewTeamId(String newTeamId) {
		this.newTeamId = newTeamId;
	}
	public String getPhoneExcel() {
		return phoneExcel;
	}
	public void setPhoneExcel(String phoneExcel) {
		this.phoneExcel = phoneExcel;
	}
	public List<String> getPhoneExcelList() {
		return phoneExcelList;
	}
	public void setPhoneExcelList(List<String> phoneExcelList) {
		this.phoneExcelList = phoneExcelList;
	}
	public List<String> getOsTypeList() {
		return osTypeList;
	}
	public void setOsTypeList(List<String> osTypeList) {
		this.osTypeList = osTypeList;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getOldPhone() {
		return oldPhone;
	}
	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getUniqueNum() {
		return uniqueNum;
	}
	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}
	public String getCityManager() {
		return cityManager;
	}
	public void setCityManager(String cityManager) {
		this.cityManager = cityManager;
	}
	public String getCityOrg() {
		return cityOrg;
	}
	public void setCityOrg(String cityOrg) {
		this.cityOrg = cityOrg;
	}
	public String getBussManager() {
		return bussManager;
	}
	public void setBussManager(String bussManager) {
		this.bussManager = bussManager;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
}
