package com.creditharmony.fortune.delivery.entity;



import java.sql.Timestamp;

public class Delivery {
	
	private String delId;// 交割ID
    private String delType;// 交割类型
    private String custCode;// 客户编号
    private String lendCode;// 出借编号
    private String fManagerCode;// 原理财经理编号
    private String teamManagerCode;// 原团队经理编号
    private String cityManagerCode;// 原城市经理编号
    private String storeManagerCode;// 原门店经理编号
    private String orgCode;// 原营业部编号
    private String nfManagerCode;// 新理财经理编
    private String nTeamManagerCode;// 新团队经理编号
    private String nCityManagerCode;// 新城市经理编号
    private String nStoreManagerCode;// 新门店经理编号
    private String nOrgCode;// 新营业部编号
    private String dictDelStatus;// 交割状态
    private Timestamp delDate;// 交割生效时间
    private String createBy;// 操作人
    private Timestamp createTime;// 创建时间
    private String modifyBy;// 最后修改人
    private Timestamp modifyTime;// 最后修改时间
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
	public String getNfManagerCode() {
		return nfManagerCode;
	}
	public void setNfManagerCode(String nfManagerCode) {
		this.nfManagerCode = nfManagerCode;
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
	public String getfManagerCode() {
		return fManagerCode;
	}
	public void setfManagerCode(String fManagerCode) {
		this.fManagerCode = fManagerCode;
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
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
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
}