package com.creditharmony.fortune.delivery.entity.ext;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 交割
 * @Class Name Delivery
 * @author hutiyong
 * @Create In 2015年11月17日
 */
public class DeliveryEx extends DataEntity<DeliveryEx>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String delId;// 交割ID
    private String delType;// 交割类型
    private Timestamp delDate;// 交割时间
    private String managerType;// 经理类型
    private String custCode;// 客户编号
    private String lendCode;// 出借编号
    private String fManagerId;// 原理财经理对应的id
    private String fManagerCode;// 原理财经理编号
    private String teamManagerCode;// 原团队经理编号
    private String cityManagerCode;// 原城市经理编号
    private String storeManagerCode;// 原门店经理编号
    private String orgCode;// 原营业部编号
    private String nfManagerId;// 新理财经理对应的id
    private String nfManagerCode;// 新理财经理编号
    private String nTeamManagerCode;// 新团队经理编号
    private String nCityManagerCode;// 新城市经理编号
    private String nStoreManagerCode;// 新门店经理编号
    private String nOrgCode;// 新营业部编号
    private String dictDelStatus;// 交割状态
    private Timestamp delTimestamp;// 交割生效时间
    private String createBy;// 操作人
    private Timestamp createTime;// 创建时间
    private String modifyBy;// 最后修改人
    private Timestamp modifyTime;// 最后修改时间
    /*扩展字段*/
	private String custName;// 客户姓名
	private String fManagerName;// 原理财经理
    private String teamManagerName;// 原团队经理
    private String cityManagerName;// 原城市经理
    private String storeManagerName;// 原门店经理
    private String orgName;// 原营业部
    private String nfManagerName;// 新理财经理
    private String nTeamManagerName;// 新团队经理
    private String nCityManagerName;// 新城市经理
    private String nStoreManagerName;//新门店经理
    private String nOrgName;// 新营业部
    private String createByName;// 操作人姓名
    private String modifyByName;// 最后修改人姓名
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间
    private String loginName;// 登录名
    private String teamName;// 团队名称
    private String isDelivery;//交割标识
    private Timestamp toDelTime;// 交割过的时间
    private String productName;// 出借产品名称
    private List<String> productCodeList;// 产品编号
    private String productCode;// 产品编号
    private Date applyLendDay;// 计划出借日期
    private BigDecimal applyLendMoney;// 计划出借金额
    private String applyPay;// 付款方式
    private Date applyExpireDay;// 到期日期
    private String lendStatus;// 出借状态
    private String applyEndStatus;// 完结状态
    private BigDecimal totalMoney;// 页面显示总金额
    private String orgType;// 机构类型
    private String managerRole;// 理财经理角色
    private String teamManagerRole;// 团队经理角色
    private String[] orgId;
    private List<String> code;// 导表时用来存多个客户编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDelId() {
		return delId;
	}
	public void setDelId(String delId) {
		this.delId = delId;
	}
	public String getDelType() {
		return delType;
	}
	public void setDelType(String delType) {
		this.delType = delType;
	}
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getfManagerId() {
		return fManagerId;
	}
	public void setfManagerId(String fManagerId) {
		this.fManagerId = fManagerId;
	}
	public String getfManagerCode() {
		return fManagerCode;
	}
	public void setfManagerCode(String fManagerCode) {
		this.fManagerCode = fManagerCode;
	}
	public String getTeamManagerCode() {
		return teamManagerCode;
	}
	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}
	public String getCityManagerCode() {
		return cityManagerCode;
	}
	public void setCityManagerCode(String cityManagerCode) {
		this.cityManagerCode = cityManagerCode;
	}
	public String getStoreManagerCode() {
		return storeManagerCode;
	}
	public void setStoreManagerCode(String storeManagerCode) {
		this.storeManagerCode = storeManagerCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getNfManagerId() {
		return nfManagerId;
	}
	public void setNfManagerId(String nfManagerId) {
		this.nfManagerId = nfManagerId;
	}
	public String getNfManagerCode() {
		return nfManagerCode;
	}
	public void setNfManagerCode(String nfManagerCode) {
		this.nfManagerCode = nfManagerCode;
	}
	public String getnTeamManagerCode() {
		return nTeamManagerCode;
	}
	public void setnTeamManagerCode(String nTeamManagerCode) {
		this.nTeamManagerCode = nTeamManagerCode;
	}
	public String getnCityManagerCode() {
		return nCityManagerCode;
	}
	public void setnCityManagerCode(String nCityManagerCode) {
		this.nCityManagerCode = nCityManagerCode;
	}
	public String getnStoreManagerCode() {
		return nStoreManagerCode;
	}
	public void setnStoreManagerCode(String nStoreManagerCode) {
		this.nStoreManagerCode = nStoreManagerCode;
	}
	public String getnOrgCode() {
		return nOrgCode;
	}
	public void setnOrgCode(String nOrgCode) {
		this.nOrgCode = nOrgCode;
	}
	public String getDictDelStatus() {
		return dictDelStatus;
	}
	public void setDictDelStatus(String dictDelStatus) {
		this.dictDelStatus = dictDelStatus;
	}
	public Timestamp getDelTimestamp() {
		return delTimestamp;
	}
	public void setDelTimestamp(Timestamp delTimestamp) {
		this.delTimestamp = delTimestamp;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getfManagerName() {
		return fManagerName;
	}
	public void setfManagerName(String fManagerName) {
		this.fManagerName = fManagerName;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getCityManagerName() {
		return cityManagerName;
	}
	public void setCityManagerName(String cityManagerName) {
		this.cityManagerName = cityManagerName;
	}
	public String getStoreManagerName() {
		return storeManagerName;
	}
	public void setStoreManagerName(String storeManagerName) {
		this.storeManagerName = storeManagerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getNfManagerName() {
		return nfManagerName;
	}
	public void setNfManagerName(String nfManagerName) {
		this.nfManagerName = nfManagerName;
	}
	public String getnTeamManagerName() {
		return nTeamManagerName;
	}
	public void setnTeamManagerName(String nTeamManagerName) {
		this.nTeamManagerName = nTeamManagerName;
	}
	public String getnCityManagerName() {
		return nCityManagerName;
	}
	public void setnCityManagerName(String nCityManagerName) {
		this.nCityManagerName = nCityManagerName;
	}
	public String getnStoreManagerName() {
		return nStoreManagerName;
	}
	public void setnStoreManagerName(String nStoreManagerName) {
		this.nStoreManagerName = nStoreManagerName;
	}
	public String getnOrgName() {
		return nOrgName;
	}
	public void setnOrgName(String nOrgName) {
		this.nOrgName = nOrgName;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getModifyByName() {
		return modifyByName;
	}
	public void setModifyByName(String modifyByName) {
		this.modifyByName = modifyByName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}
	public Timestamp getToDelTime() {
		return toDelTime;
	}
	public void setToDelTime(Timestamp toDelTime) {
		this.toDelTime = toDelTime;
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
	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
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
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
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
	public String[] getOrgId() {
		return orgId;
	}
	public void setOrgId(String[] orgId) {
		this.orgId = orgId;
	}
	public List<String> getCode() {
		return code;
	}
	public void setCode(List<String> code) {
		this.code = code;
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
}