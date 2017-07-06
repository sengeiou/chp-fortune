package com.creditharmony.fortune.sms.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 财富短信发送履历表
 * 
 * @Class Name SmsHis
 * @author 陈广鹏
 * @Create In 2016年1月20日
 */
public class SmsHis extends DataEntity<SmsHis> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -3943124417186071190L;
	/**
	 * 借款编码
	 */
	private String lendCode;
	/**
	 * 客户编号
	 */
	private String customerCode;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 短信内容
	 */
	private String smsMsg;
	/**
	 * 短信模板名称
	 */
	private String smsTempletId;
	/**
	 * 发送状态
	 */
	private String sendStatus;
	/**
	 * 唯一标识
	 */
	private String onlyFlag;

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public String getSmsTempletId() {
		return smsTempletId;
	}

	public void setSmsTempletId(String smsTempletId) {
		this.smsTempletId = smsTempletId;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getOnlyFlag() {
		return onlyFlag;
	}

	public void setOnlyFlag(String onlyFlag) {
		this.onlyFlag = onlyFlag;
	}

}
