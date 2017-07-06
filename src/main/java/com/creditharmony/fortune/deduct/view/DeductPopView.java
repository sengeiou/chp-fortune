package com.creditharmony.fortune.deduct.view;

import java.io.Serializable;
import java.util.List;

/**
 * 线下划扣导出view
 * @Class Name DeductPopView
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductPopView implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -2449950565824229107L;
	
	// 出借编号
	private List<String> lendCodes;
	
	// 要导出平台id
	private String exportPtId;
	
	// 导出方式excel或txt
	private String exportType;

	public List<String> getLendCodes() {
		return lendCodes;
	}

	public void setLendCodes(List<String> lendCodes) {
		this.lendCodes = lendCodes;
	}

	public String getExportPtId() {
		return exportPtId;
	}

	public void setExportPtId(String exportPtId) {
		this.exportPtId = exportPtId;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
}
