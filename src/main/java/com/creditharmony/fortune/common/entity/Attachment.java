package com.creditharmony.fortune.common.entity;

import java.util.Date;

import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.persistence.DataEntity;

public class Attachment extends DataEntity<Attachment> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 6358881215874019407L;

	private String attaId;
   
    private String attaFileOwner;

    private String attaTableId;

    private String loanCode;

    private String attaFilename;

    private String attaNewfilename;

    private String attaFilepath;

    private String dictAttaFlag;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private String attaFileType;
    
    private String sendFlag; // 发送标志
    private String templateType;// 模板类型
    private String isDiscard;
    private String from;

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
        this.attaTableId = attaTableId == null ? null : attaTableId.trim();
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

    public String getDictAttaFlag() {
        return dictAttaFlag;
    }

    public void setDictAttaFlag(String dictAttaFlag) {
        this.dictAttaFlag = dictAttaFlag == null ? null : dictAttaFlag.trim();
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

    public String getAttaFileType() {
        return attaFileType;
    }

    public void setAttaFileType(String attaFileType) {
        this.attaFileType = attaFileType == null ? null : attaFileType.trim();
    }
    public String getAttaId() {
        return attaId;
    }

    public void setAttaId(String attaId) {
        this.attaId = attaId == null ? null : attaId.trim();
    }

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getIsDiscard() {
		return isDiscard;
	}

	public void setIsDiscard(String isDiscard) {
		this.isDiscard = isDiscard;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "Attachment [attaId=" + attaId + ", attaFileOwner="
				+ attaFileOwner + ", attaTableId=" + attaTableId
				+ ", loanCode=" + loanCode + ", attaFilename=" + attaFilename
				+ ", attaNewfilename=" + attaNewfilename + ", attaFilepath="
				+ attaFilepath + ", dictAttaFlag=" + dictAttaFlag
				+ ", createBy=" + createBy + ", createTime=" + createTime
				+ ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime
				+ ", attaFileType=" + attaFileType + ", sendFlag=" + sendFlag
				+ ", templateType=" + templateType + ", isDiscard=" + isDiscard
				+ ", from=" + from + "]";
	}
    
}