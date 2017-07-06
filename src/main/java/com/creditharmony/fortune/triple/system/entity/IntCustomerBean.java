package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name IntCustomerBean
 * @author 胡体勇
 * @Create In 2016年3月3日
 */
public class IntCustomerBean extends DataEntity<IntCustomerBean>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;// 主键
    private String osId;// 系统id
    private String empCode;// 理财经理
    private String newPhone;// 新手机号
    private String oldPhone;// 旧手机号
    private String userName;// 客户姓名
    private String logName;// 登录名称
    private String cardType;// 证件类型
    private String cardId;// 证件号码
    private String mail;// 邮箱
    private String sex;// 性别
    private String osType;// 系统类型
    private Date birthday;// 生日
    private String birthdayStr;// 生日 字符串
    private Date createTime;// 创建时间
    private String sendStatus;// 发送状态
    private String crmSendStatus;// CRM系统发送状态
    private String sendType;// 发送类型
    private Date sendTime;// 发送时间
    private String operation;// 操作类型
    private String uniqueNum;// 发送消息唯一编号
    private String faileName;//伪名(大金融使用).
    
    /**客户来源1-商超,2-老客户介绍,3-陌call,4-客户服务节,5其他*/
    private String cusSource ;
    
    /** 客户编号 */
    private String userNumber ;
    
    /**	紧急联系人中文姓名 */
    private String ICEName ;
    
    /** 紧急联系人证件类型 */
    private String ICECardType ;
    
	/** 紧急联系人证件号码 */
    private String ICECardId ;
    
    /** 紧急联系人手机号码 */
    private String ICEPhone	;
    
    /** 紧急联系人地区 */
    private String ICEArea ;
    
	/** 紧急联系人通讯地址 */
    private String ICEAddress ;
    
    /** 紧急联系人电子邮箱 */
    private String ICEEmail	;
    
    /** 紧急联系人与客户关系 */
    private String ICERelation ;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOsId() {
		return osId;
	}
	public String getFaileName() {
		return faileName;
	}
	public void setFaileName(String faileName) {
		this.faileName = faileName;
	}
	public void setOsId(String osId) {
		this.osId = osId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getOldPhone() {
		return oldPhone;
	}
	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUniqueNum() {
		return uniqueNum;
	}
	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}
	public String getCusSource() {
		return cusSource;
	}
	public void setCusSource(String cusSource) {
		this.cusSource = cusSource;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getICEName() {
		return ICEName;
	}
	public void setICEName(String iCEName) {
		ICEName = iCEName;
	}
	public String getICECardType() {
		return ICECardType;
	}
	public void setICECardType(String iCECardType) {
		ICECardType = iCECardType;
	}
	public String getICECardId() {
		return ICECardId;
	}
	public String getCrmSendStatus() {
		return crmSendStatus;
	}
	public void setCrmSendStatus(String crmSendStatus) {
		this.crmSendStatus = crmSendStatus;
	}
	public void setICECardId(String iCECardId) {
		ICECardId = iCECardId;
	}
	public String getICEPhone() {
		return ICEPhone;
	}
	public void setICEPhone(String iCEPhone) {
		ICEPhone = iCEPhone;
	}
	public String getICEArea() {
		return ICEArea;
	}
	public void setICEArea(String iCEArea) {
		ICEArea = iCEArea;
	}
	public String getICEAddress() {
		return ICEAddress;
	}
	public void setICEAddress(String iCEAddress) {
		ICEAddress = iCEAddress;
	}
	public String getICERelation() {
		return ICERelation;
	}
	public void setICERelation(String iCERelation) {
		ICERelation = iCERelation;
	}
	public String getICEEmail() {
		return ICEEmail;
	}
	public void setICEEmail(String iCEEmail) {
		ICEEmail = iCEEmail;
	}
	
	
}
