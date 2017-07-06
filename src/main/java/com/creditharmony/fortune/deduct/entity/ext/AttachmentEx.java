package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;

/**
 * 附件表扩展类
 * @Class Name AttachmentExt
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class AttachmentEx implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1142751088664969873L;
	// 主键dd
	private String attaId;
	// 文件所属表
    private String attaFileOwner;
    // 所属表ID
    private String attaTableId;
    // 出借编号
    private String loanCode;
    // 文件名
    private String attaFilename;
    // 新文件名
    private String attaNewfilename;
    // 文件路径
    private String attaFilepath;
    // cjxy：出借合同；bg：变更申请；zr：赎回申请；hkxy：委托划扣；zrsq：债权转让
    private String attaFlag;
    // 客户编号
    private String custCode;
    // 客户姓名
    private String custName;
    // 文件制作状态（制作中；制作失败；制作成功）
    private String matchingFileStatus;
    // 文件发送状态（未发送；发送成功；发送失败）
    private String matchingFilesendStatus;
    // 客户地址ID
    private String addId;
    // 电子邮箱
    private String custEmail;
    // email模板名称
    private String templateName;
    // 模板类型0：首期；1：非首期
    private String templateType;
    // 模板内容
    private String templateContent;
    // 模板主题
    private String mailTheme;
    // 模板ID
    private String id;
    // 
    private String applyDeductDay;
    //账单日
    private Integer matchingBillDay;
    
    // 收款确认书文件制作状态（制作中；制作失败；制作成功）
    private String makeFlieStatus;
    // 收款确认书文件发送状态（未发送；发送成功；发送失败）
    private String sendEmailStatus;
    public String getAttaId() {
        return attaId;
    }

    public void setAttaId(String attaId) {
        this.attaId = attaId;
    }

    public String getAttaFileOwner() {
        return attaFileOwner;
    }

    public void setAttaFileOwner(String attaFileOwner) {
        this.attaFileOwner = attaFileOwner == null ? null : attaFileOwner.trim();
    }

    public String getAttaTableId() {
        return attaTableId;
    }

    public void setAttaTableId(String attaTableId) {
        this.attaTableId = attaTableId;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getAttaFilename() {
        return attaFilename;
    }

    public void setAttaFilename(String attaFilename) {
        this.attaFilename = attaFilename == null ? null : attaFilename.trim();
    }

    public String getAttaNewfilename() {
        return attaNewfilename;
    }

    public void setAttaNewfilename(String attaNewfilename) {
        this.attaNewfilename = attaNewfilename == null ? null : attaNewfilename.trim();
    }

    public String getAttaFilepath() {
        return attaFilepath;
    }

    public void setAttaFilepath(String attaFilepath) {
        this.attaFilepath = attaFilepath == null ? null : attaFilepath.trim();
    }

    public String getAttaFlag() {
        return attaFlag;
    }

    public void setAttaFlag(String attaFlag) {
        this.attaFlag = attaFlag == null ? null : attaFlag.trim();
    }

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getMatchingFileStatus() {
		return matchingFileStatus;
	}

	public void setMatchingFileStatus(String matchingFileStatus) {
		this.matchingFileStatus = matchingFileStatus;
	}

	public String getMatchingFilesendStatus() {
		return matchingFilesendStatus;
	}

	public void setMatchingFilesendStatus(String matchingFilesendStatus) {
		this.matchingFilesendStatus = matchingFilesendStatus;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getAddId() {
		return addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getMatchingBillDay() {
		return matchingBillDay;
	}

	public void setMatchingBillDay(Integer matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}

	public String getMailTheme() {
		return mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public String getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(String applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public String getMakeFlieStatus() {
		return makeFlieStatus;
	}

	public void setMakeFlieStatus(String makeFlieStatus) {
		this.makeFlieStatus = makeFlieStatus;
	}

	public String getSendEmailStatus() {
		return sendEmailStatus;
	}

	public void setSendEmailStatus(String sendEmailStatus) {
		this.sendEmailStatus = sendEmailStatus;
	}
	
}