package com.creditharmony.fortune.cutoff.entity;

import java.util.List;

import com.creditharmony.core.persistence.DataEntity;
import com.creditharmony.core.users.entity.Org;

/**
 * @Class Name CutOffView
 * @author 周树华
 * @Create In 2016年2月1日
 */
public class CutOff extends DataEntity<CutOff> {
	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// private Date endTime;
	private String endTime;
	private String dealIp;
	private String systemFlag;
	private String orgId;
	private String orgName;
	private String lastBy;
	private String delFlag;
	private List<String> array;
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	private List<Org> orgList;
	
	public CutOff(){
		
	}

	/*public Date getEndTime() {
		return endTime;
	}*/

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLastBy() {
		return lastBy;
	}

	public void setLastBy(String lastBy) {
		this.lastBy = lastBy;
	}

	/*public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}*/

	public String getDealIp() {
		return dealIp;
	}

	public void setDealIp(String dealIp) {
		this.dealIp = dealIp;
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<Org> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String> getArray() {
		return array;
	}

	public void setArray(List<String> array) {
		this.array = array;
	}


}
