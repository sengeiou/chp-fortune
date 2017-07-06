/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.entityMatchingCreditor.java
 * @Create By 胡体勇
 * @Create In 2015年12月24日 下午3:51:59
 */
package com.creditharmony.fortune.creditor.matching.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name MatchingCreditor
 * @author 胡体勇
 * @Create In 2015年12月24日
 */
public class MatchingCreditor extends DataEntity<MatchingCreditor> {
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String matchingId;                       //   债权推荐ID自增ID记录出借每期情况
    private String lendCode;                         //   出借编号
    private String productCode;                      //   产品编号
    private String matchingFirstdayFlag;             //   首期非首期标志位：0首期，1非首期
    private Date matchingInterestStart;              //   起息日期
    private Integer matchingBillDay;                 //   账单日
    private BigDecimal matchingBorrowQuota;          //   出借金额
    private String matchingStatus;                  //   债权状态
    private Date matchingEndday;                     //   结束日期
    private BigDecimal matchingMatchMoney;           //   已匹配金额
    private String dictMatchingFileStatus;           //   文件制作状态（制作中；制作失败；制作成功）
    private String dictMatchingFilesendStatus;       //   文件发送状态（未发送；发送成功；发送失败）
    private Integer matchingTotal;                   //   共有几期
    private Integer matchingNow;                     //   当前期数
    private Date matchingExpireDay;                  //   本期到期日期
    private Date matchingCossTime;                   //   匹配审批通过时间
    private Date matchingMakeDay;                    //   制作协议时间
    private Date matchingHkDay;                      //   划扣时间
    private Date matchingFirstbillDay;               //   第一个账单日
    private Integer matchingOrderStatus;             //   债权订购流程状态标识
    private String matchingPayStatus;                //   0-未结算，1-已结算
    private String createBy;                         //   创建人
    private Date createTime;                         //   创建时间
    private String modifyBy;                         //   修改人
    private Date modifyTime;                         //   修改时间
    private String matchingUserId;                 //   匹配人ID
    private Date dropTime;                         //   换单/弃单时间
    private Date distDay;                          //   分配日期
    private int countTotal;                          //非首期待推荐债权数量
    
    
	public int getCountTotal() {
		return countTotal;
	}
	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}
	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}
	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}
	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}
	public Integer getMatchingBillDay() {
		return matchingBillDay;
	}
	public void setMatchingBillDay(Integer matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}
	public BigDecimal getMatchingBorrowQuota() {
		return matchingBorrowQuota;
	}
	public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
		this.matchingBorrowQuota = matchingBorrowQuota;
	}
	public String getMatchingStatus() {
		return matchingStatus;
	}
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	public Date getMatchingEndday() {
		return matchingEndday;
	}
	public void setMatchingEndday(Date matchingEndday) {
		this.matchingEndday = matchingEndday;
	}
	public BigDecimal getMatchingMatchMoney() {
		return matchingMatchMoney;
	}
	public void setMatchingMatchMoney(BigDecimal matchingMatchMoney) {
		this.matchingMatchMoney = matchingMatchMoney;
	}
	public String getDictMatchingFileStatus() {
		return dictMatchingFileStatus;
	}
	public void setDictMatchingFileStatus(String dictMatchingFileStatus) {
		this.dictMatchingFileStatus = dictMatchingFileStatus;
	}
	public String getDictMatchingFilesendStatus() {
		return dictMatchingFilesendStatus;
	}
	public void setDictMatchingFilesendStatus(String dictMatchingFilesendStatus) {
		this.dictMatchingFilesendStatus = dictMatchingFilesendStatus;
	}
	public Integer getMatchingTotal() {
		return matchingTotal;
	}
	public void setMatchingTotal(Integer matchingTotal) {
		this.matchingTotal = matchingTotal;
	}
	public Integer getMatchingNow() {
		return matchingNow;
	}
	public void setMatchingNow(Integer matchingNow) {
		this.matchingNow = matchingNow;
	}
	public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}
	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}
	public Date getMatchingCossTime() {
		return matchingCossTime;
	}
	public void setMatchingCossTime(Date matchingCossTime) {
		this.matchingCossTime = matchingCossTime;
	}
	public Date getMatchingMakeDay() {
		return matchingMakeDay;
	}
	public void setMatchingMakeDay(Date matchingMakeDay) {
		this.matchingMakeDay = matchingMakeDay;
	}
	public Date getMatchingHkDay() {
		return matchingHkDay;
	}
	public void setMatchingHkDay(Date matchingHkDay) {
		this.matchingHkDay = matchingHkDay;
	}
	public Date getMatchingFirstbillDay() {
		return matchingFirstbillDay;
	}
	public void setMatchingFirstbillDay(Date matchingFirstbillDay) {
		this.matchingFirstbillDay = matchingFirstbillDay;
	}
	public Integer getMatchingOrderStatus() {
		return matchingOrderStatus;
	}
	public void setMatchingOrderStatus(Integer matchingOrderStatus) {
		this.matchingOrderStatus = matchingOrderStatus;
	}
	public String getMatchingPayStatus() {
		return matchingPayStatus;
	}
	public void setMatchingPayStatus(String matchingPayStatus) {
		this.matchingPayStatus = matchingPayStatus;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getMatchingUserId() {
		return matchingUserId;
	}
	public void setMatchingUserId(String matchingUserId) {
		this.matchingUserId = matchingUserId;
	}
	public Date getDropTime() {
		return dropTime;
	}
	public void setDropTime(Date dropTime) {
		this.dropTime = dropTime;
	}
	public Date getDistDay() {
		return distDay;
	}
	public void setDistDay(Date distDay) {
		this.distDay = distDay;
	}
	
}
