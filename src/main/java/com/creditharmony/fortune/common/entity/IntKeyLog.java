package com.creditharmony.fortune.common.entity;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 三网主要日志
 */
public class IntKeyLog extends DataEntity<IntKeyLog> {

	private static final long serialVersionUID = 1L;

	// 异常描述信息
	private String message;
	// 异常堆栈信息
	private String stackTrace;
	// 节点
	private String node;
	// 备注
	private String remark;

	public IntKeyLog() {
		super();
	}

	public IntKeyLog(Exception e) {
		if (null != e) {
			this.message = e.getMessage();
			this.stackTrace = ExceptionUtils.getStackTrace(e);
		}
	}

	public IntKeyLog(Exception e, String node, String remark) {
		if (null != e) {
			this.message = e.getMessage();
			this.stackTrace = ExceptionUtils.getStackTrace(e);
		}

		this.node = node;
		this.remark = remark;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}