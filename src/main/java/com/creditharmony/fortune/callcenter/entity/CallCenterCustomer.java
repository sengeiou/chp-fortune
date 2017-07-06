package com.creditharmony.fortune.callcenter.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 呼叫中心客户信息表
 * @Class Name CallCenterCustomer
 * @author 肖长伟
 * @Create In 2016年3月1日
 */
public class CallCenterCustomer extends DataEntity<CallCenterCustomer>  {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String	id	; 							//	主键ID
	private String	customerName	;	 //	客户姓名
	private String	dictCustomerSex	; 	//	性别
	private String	customerMobilephone	; 	//	移动电话
	private String	customerTel	; 			//	固定电话
	private String	dictCustomerCertType	; //	证件类型
	private String	customerCertNum	; 		//	证件号码
	private String	province;				 //	省id
	private String	city; 						//	市id
	private String	status; 					//	客户状态：0新增、1城市、2门店、3团队、4理财经理
	private String	cityManagerId;	 //	城市经理id
	private String	storeOrgId;			 //	门店id
	private String	storeManagerId; 	//	门店经理id
	private String	teamId	; 				//	团队id
	private String	teamManagerId	; 	//	团队经理id
	private String	managerId	; 			//	理财经理ID（归属人）
	private String	createBy	; 				//	创建人
	private Date	createTime	; 			//	创建时间
	private String	modifyBy	; 				//	最后修改人
	private Date	modifyTime	; 			//	最后修改时间 
	
//	附加信息
	private String dictCustomerSexName;
	private String provinceName;
	private String cityName;
	private String storeOrgName;
	private String teamName;
	private String managerName;
	
	public String getDictCustomerSexName() {
		return dictCustomerSexName;
	}
	public void setDictCustomerSexName(String dictCustomerSexName) {
		this.dictCustomerSexName = dictCustomerSexName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStoreOrgName() {
		return storeOrgName;
	}
	public void setStoreOrgName(String storeOrgName) {
		this.storeOrgName = storeOrgName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDictCustomerSex() {
		return dictCustomerSex;
	}
	public void setDictCustomerSex(String dictCustomerSex) {
		this.dictCustomerSex = dictCustomerSex;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getDictCustomerCertType() {
		return dictCustomerCertType;
	}
	public void setDictCustomerCertType(String dictCustomerCertType) {
		this.dictCustomerCertType = dictCustomerCertType;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCityManagerId() {
		return cityManagerId;
	}
	public void setCityManagerId(String cityManagerId) {
		this.cityManagerId = cityManagerId;
	}
	public String getStoreOrgId() {
		return storeOrgId;
	}
	public void setStoreOrgId(String storeOrgId) {
		this.storeOrgId = storeOrgId;
	}
	public String getStoreManagerId() {
		return storeManagerId;
	}
	public void setStoreManagerId(String storeManagerId) {
		this.storeManagerId = storeManagerId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamManagerId() {
		return teamManagerId;
	}
	public void setTeamManagerId(String teamManagerId) {
		this.teamManagerId = teamManagerId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	

	
	
}
