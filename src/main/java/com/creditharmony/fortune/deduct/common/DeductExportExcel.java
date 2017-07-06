package com.creditharmony.fortune.deduct.common;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;


/**
 * 线下划扣查询实体类
 * @Class Name DeductExportExcel
 * @author 韩龙
 * @Create In 2016年2月21日
 */
public class DeductExportExcel extends BaseExportModel{
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -338560453405779961L;
	
	// 序号
	private String index;
	
	/** 中金代收  */
	/* 明细流水号	 	结算标识	备注（财富划扣_出借编号_唯一识别码）		电子邮箱	协议用户编号*/
	// 结算标识(中金)
	private String settlementMark;
	// 电子邮箱(中金)可为空
	private String emailAddress;
	// 备注(中金)
	private String zhongRemarks;
	
	/**通联代收*/
	/* 序号*	用户编号	货币类型	协议号	协议用户编号	自定义用户号	备注	反馈码	原因*/
	// 反馈码(通联)可为空
	private String feedbackCode;
	// 原因(通联)可为空
	private String reason;
	// 用户编号(通联)可为空
	private String userNumber;
	// 备注(通联)
	private String tongRemarks;
	
	/**富有代收*/
	/*序号	企业流水账号	备注*/
	// 企业流水账号（富有）
	private String enterpriseFlowAccount;
	// 备注 可为空
	private String fuRemarks;
	
	/**好易联代收*/
	/*序号	银联网络用户编号	货币类型	协议号	协议用户编号	自定义用户名	备注1	备注2	备注	反馈码	原因*/
	// 银联网络用户编号(好易联)
	private String unionNetworkCode;
	// 备注(好易联)
	private String haoRemarks;
	
	/**公共可以为空*/
	// 协议用户编号
	private String protocolUserNumber;
	// 自定义用户号
	private String customUserNumber;
	// 协议号
	private String protocolNumber;
	// 货币类型
	private String typeOfCurrency;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSettlementMark() {
		return settlementMark;
	}
	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getZhongRemarks() {
		// 财富划扣_出借编号_唯一识别码
		return "财富划扣_"+this.getLendCode()+"_"+IdGen.uuid();
	}
	public void setZhongRemarks(String zhongRemarks) {
		this.zhongRemarks = zhongRemarks;
	}
	public String getFeedbackCode() {
		return feedbackCode;
	}
	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getTongRemarks() {
		return this.getLendCode();
	}
	public void setTongRemarks(String tongRemarks) {
		this.tongRemarks = tongRemarks;
	}
	public String getEnterpriseFlowAccount() {
		return enterpriseFlowAccount;
	}
	public void setEnterpriseFlowAccount(String enterpriseFlowAccount) {
		this.enterpriseFlowAccount = enterpriseFlowAccount;
	}
	public String getFuRemarks() {
		return fuRemarks;
	}
	public void setFuRemarks(String fuRemarks) {
		this.fuRemarks = fuRemarks;
	}
	public String getUnionNetworkCode() {
		return unionNetworkCode;
	}
	public void setUnionNetworkCode(String unionNetworkCode) {
		this.unionNetworkCode = unionNetworkCode;
	}
	public String getHaoRemarks() {
		return this.getLendCode();
	}
	public void setHaoRemarks(String haoRemarks) {
		this.haoRemarks = haoRemarks;
	}
	public String getProtocolUserNumber() {
		return protocolUserNumber;
	}
	public void setProtocolUserNumber(String protocolUserNumber) {
		this.protocolUserNumber = protocolUserNumber;
	}
	public String getCustomUserNumber() {
		return customUserNumber;
	}
	public void setCustomUserNumber(String customUserNumber) {
		this.customUserNumber = customUserNumber;
	}
	public String getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public String getTypeOfCurrency() {
		return typeOfCurrency;
	}
	public void setTypeOfCurrency(String typeOfCurrency) {
		this.typeOfCurrency = typeOfCurrency;
	}
	
	
}
