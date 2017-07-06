package com.creditharmony.fortune.mail.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 邮件模板
 * @Class Name EmailTemplate
 * @author 朱杰
 * @Create In 2016年3月7日
 */
public class EmailTemplate extends DataEntity<EmailTemplate>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -3212842957365398268L;

	private String templateName;

    private String templateType;

    private String templateContent;

    private String dictStatus;

    private String description;

    private String url;

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

	public String getDictStatus() {
		return dictStatus;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    
}