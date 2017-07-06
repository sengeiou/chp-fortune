package com.creditharmony.fortune.common.entity;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 异常实体类
 * 
 * @Class Name CHP3Exception
 * @author 孙凯文
 * @Create In 2016年5月3日
 */
public class FortuneException extends DataEntity<FortuneException> {
	private static final long serialVersionUID = 1L;

	// 出借编号
	private String loanCode;
	// 异常描述信息
	private String message;
	// 异常堆栈信息
	private String stackTrace;
	// 节点
	private String node;
	// 紧急程度
	private String importantLevel;
	// 表ID
	private String tableId;
	// 备注
	private String remark;

	public FortuneException() {
		super();
	}

	public FortuneException(Exception e) {
		if (null != e) {
			this.message = e.getMessage();
			this.stackTrace = ExceptionUtils.getStackTrace(e);
		}
	}

	public FortuneException(String loanCode, Exception e, String node,
			String importantLevel, String tableId, String remark) {
		this.loanCode = loanCode;
		if (null != e) {
			this.message = e.getMessage();
			this.stackTrace = ExceptionUtils.getStackTrace(e);
		}

		this.node = node;
		this.importantLevel = importantLevel;
		this.tableId = tableId;
		this.remark = remark;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
