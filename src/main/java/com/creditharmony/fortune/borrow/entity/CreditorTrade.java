/**
 * @Path: com.creditharmony.fortune.creditor.matching.entitycreditorTrade.java
 * @Create By 胡体勇
 * @Create In 2015年12月9日 下午3:42:06
 */
package com.creditharmony.fortune.borrow.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name creditorTrade
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
public class CreditorTrade extends DataEntity<CreditorTrade>{
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String tradeId;// 交易id
	private String matchingId;// 债权推荐ID
	private String lendCode;// 出借编号
	private String creditNode;// 债权节点；债权池；月满盈可用债权池
	private String creditCode;// 债权ID 是两个表中的ID
	private Timestamp tradeExpectDay;// 债权交易预计到期时间
	
	private Timestamp tradeActualDay;// 债权交易实际到期时间
	private BigDecimal tradeMateMoney;// 匹配金额
    private String tradeMateMoneyPercent;// 匹配金额所占百分比
	private String dictTradeCreditStatus;// 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
	private Timestamp tradePassDate;// 审核通过时间
	private Timestamp tradeDeductDay;// 划扣成功时间
	private String tradeBorrowdaysActual;// 实际出借天数
	private String createBy;// 创建人
	private String modifyBy;// 最后修改人
	  
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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
	public String getCreditNode() {
		return creditNode;
	}
	public void setCreditNode(String creditNode) {
		this.creditNode = creditNode;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public Timestamp getTradeExpectDay() {
		return tradeExpectDay;
	}
	public void setTradeExpectDay(Timestamp tradeExpectDay) {
		this.tradeExpectDay = tradeExpectDay;
	}
	public Timestamp getTradeActualDay() {
		return tradeActualDay;
	}
	public void setTradeActualDay(Timestamp tradeActualDay) {
		this.tradeActualDay = tradeActualDay;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}
	public String getTradeMateMoneyPercent() {
		return tradeMateMoneyPercent;
	}
	public void setTradeMateMoneyPercent(String tradeMateMoneyPercent) {
		this.tradeMateMoneyPercent = tradeMateMoneyPercent;
	}
	public Timestamp getTradePassDate() {
		return tradePassDate;
	}
	public void setTradePassDate(Timestamp tradePassDate) {
		this.tradePassDate = tradePassDate;
	}
	public Timestamp getTradeDeductDay() {
		return tradeDeductDay;
	}
	public void setTradeDeductDay(Timestamp tradeDeductDay) {
		this.tradeDeductDay = tradeDeductDay;
	}
	public String getTradeBorrowdaysActual() {
		return tradeBorrowdaysActual;
	}
	public void setTradeBorrowdaysActual(String tradeBorrowdaysActual) {
		this.tradeBorrowdaysActual = tradeBorrowdaysActual;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	public String getDictTradeCreditStatus() {
		return dictTradeCreditStatus;
	}
	public void setDictTradeCreditStatus(String dictTradeCreditStatus) {
		this.dictTradeCreditStatus = dictTradeCreditStatus;
	}
	
}
