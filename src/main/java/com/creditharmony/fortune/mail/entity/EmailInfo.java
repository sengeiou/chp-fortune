package com.creditharmony.fortune.mail.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 邮件发送实体
 * @Class Name EmailInfo1
 * @author 韩龙
 * @Create In 2015年12月15日
 */
public class EmailInfo extends DataEntity<EmailInfo>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -3212842957365398268L;

	private String emailSendId;

    private String lendCode;

    private String creditId;

    private String emailTemplateId;

    private String emailSender;

    private Date emailSenderTime;

    private String emailRecpName;

    private String emailRecpEmail;

    private String emailSubject;

    private String emailMsg;

    private String emailType;

    private String attachmentPath;

    private String emailSendStatus;

    private String remark;

    private String pdfType;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    //账单日
    private Integer matchingBillDay;
     

    public String getEmailSendId() {
        return emailSendId;
    }

    public void setEmailSendId(String emailSendId) {
        this.emailSendId = emailSendId == null ? null : emailSendId.trim();
    }

    public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId == null ? null : creditId.trim();
    }

    public String getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(String emailTemplateId) {
        this.emailTemplateId = emailTemplateId == null ? null : emailTemplateId.trim();
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender == null ? null : emailSender.trim();
    }

    public Date getEmailSenderTime() {
        return emailSenderTime;
    }

    public void setEmailSenderTime(Date emailSenderTime) {
        this.emailSenderTime = emailSenderTime;
    }

    public String getEmailRecpName() {
        return emailRecpName;
    }

    public void setEmailRecpName(String emailRecpName) {
        this.emailRecpName = emailRecpName == null ? null : emailRecpName.trim();
    }

    public String getEmailRecpEmail() {
        return emailRecpEmail;
    }

    public void setEmailRecpEmail(String emailRecpEmail) {
        this.emailRecpEmail = emailRecpEmail == null ? null : emailRecpEmail.trim();
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject == null ? null : emailSubject.trim();
    }

    public String getEmailMsg() {
        return emailMsg;
    }

    public void setEmailMsg(String emailMsg) {
        this.emailMsg = emailMsg == null ? null : emailMsg.trim();
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType == null ? null : emailType.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public String getEmailSendStatus() {
        return emailSendStatus;
    }

    public void setEmailSendStatus(String emailSendStatus) {
        this.emailSendStatus = emailSendStatus == null ? null : emailSendStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPdfType() {
        return pdfType;
    }

    public void setPdfType(String pdfType) {
        this.pdfType = pdfType == null ? null : pdfType.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public Integer getMatchingBillDay() {
		return matchingBillDay;
	}

	public void setMatchingBillDay(Integer matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}
    
}