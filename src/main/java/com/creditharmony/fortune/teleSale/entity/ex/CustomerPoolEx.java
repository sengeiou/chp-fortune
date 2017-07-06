package com.creditharmony.fortune.teleSale.entity.ex;

import java.util.Date;

/**
 * 公共池扩展
 * @Class Name CustomerPoolEx
 * @author 肖长伟
 * @Create In 2016年2月16日
 */
public class CustomerPoolEx {
	private String id;  //t_tz_Customer_Pool 表主键id
	private String managerId;  //理财经理Id
	private String managerName; //理财经理姓名
	private String customerCode;  //客户Code
	private String oldManagerId; //原理财经理id
	private String customerName; //客户姓名
    private String customerTel;  		//联系电话
    private String provinceId;   		//省份id
    private String cityId;					//城市id
    private String salesDept;			//营业部ID
    private String card;					//身份证号
    private String dataType;			//数据的类型，1进入公共池、2分配给理财经理、3退回到电销
    
    private String teleManagerCode;	//电销专员编号
    private String teleManagerName;		//电销专员姓名
    private String teamManagerCode;	//团队经理code
    private String teamManagerName; 	//团队经理姓名
    private String storeManagerCode;	//部门经理code
    private String storeManagerName;	//部门经理姓名
    private String storeName;				//营业部名称
    private Date modifyTime; 				//操作时间
    private String teleRoleType;       // 电销角色类型  0无电销角色、1 电销理财专员、2电销其他角色
    private String userId;		//电销专员id
    private String userName;  //电销专员姓名
    private String isListFlag; //是否为电销客户查询列表中的分配
    
	
    
	public String getIsListFlag() {
		return isListFlag;
	}
	public void setIsListFlag(String isListFlag) {
		this.isListFlag = isListFlag;
	}
	public String getTeleRoleType() {
		return teleRoleType;
	}
	public void setTeleRoleType(String teleRoleType) {
		this.teleRoleType = teleRoleType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getOldManagerId() {
		return oldManagerId;
	}
	public void setOldManagerId(String oldManagerId) {
		this.oldManagerId = oldManagerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getSalesDept() {
		return salesDept;
	}
	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getTeleManagerCode() {
		return teleManagerCode;
	}
	public void setTeleManagerCode(String teleManagerCode) {
		this.teleManagerCode = teleManagerCode;
	}
	public String getTeleManagerName() {
		return teleManagerName;
	}
	public void setTeleManagerName(String teleManagerName) {
		this.teleManagerName = teleManagerName;
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
	public String getStoreManagerCode() {
		return storeManagerCode;
	}
	public void setStoreManagerCode(String storeManagerCode) {
		this.storeManagerCode = storeManagerCode;
	}
	public String getStoreManagerName() {
		return storeManagerName;
	}
	public void setStoreManagerName(String storeManagerName) {
		this.storeManagerName = storeManagerName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
    
	
	
	
}
