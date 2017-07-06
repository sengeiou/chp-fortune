
package com.creditharmony.fortune.creditor.matching.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CreditorConfig
 * @author 胡体勇
 * @Create In 2015年12月21日
 */
public class CreditorConfig extends DataEntity<CreditorConfig>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;// 主键
    private Integer configCreditDay;// 账单日
    private Integer configRepayDay;// 还款日
//    private String configIsFirst;// 首期、非首期
//    private String configIsUse;// 可用、不可用
    private String dictConfigStatus;// 账单类型
    private String dictConfigZdr;// 状态
    private String createrBy;// 创建人
    private Date createTime;// 创建时间
    private String modifyBy;// 最后修改人
    private Date modifyTime;// 最后修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getConfigCreditDay() {
		return configCreditDay;
	}
	public void setConfigCreditDay(Integer configCreditDay) {
		this.configCreditDay = configCreditDay;
	}
	public Integer getConfigRepayDay() {
		return configRepayDay;
	}
	public void setConfigRepayDay(Integer configRepayDay) {
		this.configRepayDay = configRepayDay;
	}
//	public String getConfigIsFirst() {
//		return configIsFirst;
//	}
//	public void setConfigIsFirst(String configIsFirst) {
//		this.configIsFirst = configIsFirst;
//	}
//	public String getConfigIsUse() {
//		return configIsUse;
//	}
//	public void setConfigIsUse(String configIsUse) {
//		this.configIsUse = configIsUse;
//	}
	public String getDictConfigStatus() {
		return dictConfigStatus;
	}
	public void setDictConfigStatus(String dictConfigStatus) {
		this.dictConfigStatus = dictConfigStatus;
	}
	public String getDictConfigZdr() {
		return dictConfigZdr;
	}
	public void setDictConfigZdr(String dictConfigZdr) {
		this.dictConfigZdr = dictConfigZdr;
	}
	public String getCreaterBy() {
		return createrBy;
	}
	public void setCreaterBy(String createrBy) {
		this.createrBy = createrBy;
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
}
