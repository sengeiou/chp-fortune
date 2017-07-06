package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.fortune.contract.util.StatisticsUtil;


public class ContractStatisticsView  implements Serializable  {
	
	private static final long serialVersionUID = 368886517525018655L;
	private String contStoresId;              //门店ID
	private String orgName;					  //机构名
	private int distrNum;			  //派发数量
	private int storeNum;			  //现有库存
	private int validNum;		      //有效使用合同（已出借的为为有效合同）
	private int invalidNum;		      //无效使用合同（除去已出借的属于无效合同）
	private int onfileNum;		      //归档量
	private int uselessNum;            //作废量
	private int lossNum;              //遗失量
	private int outNum;		  //转出量
	private int inNum;		  //转入量
	private String contVersion;		//合同版本
	private Date distDate;  //派发日期
	private String progress;//时时进度
	private String start;
	private String end;
	private String createCount;// 合同生成量
	private String id;
	
	private String contractType; //合同/协议标识 1合同 2协议
	
	public String getContStoresId() {
		return contStoresId;
	}
	public void setContStoresId(String contStoresId) {
		this.contStoresId = contStoresId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getDistrNum() {
		return distrNum;
	}
	public void setDistrNum(int distrNum) {
		this.distrNum = distrNum;
	}
	public int getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}
	public int getValidNum() {
		return validNum;
	}
	public void setValidNum(int validNum) {
		this.validNum = validNum;
	}
	public int getInvalidNum() {
		return invalidNum;
	}
	public void setInvalidNum(int invalidNum) {
		this.invalidNum = invalidNum;
	}
	public int getOnfileNum() {
		return onfileNum;
	}
	public void setOnfileNum(int onfileNum) {
		this.onfileNum = onfileNum;
	}
	public int getUselessNum() {
		return uselessNum;
	}
	public void setUselessNum(int uselessNum) {
		this.uselessNum = uselessNum;
	}
	public int getLossNum() {
		return lossNum;
	}
	public void setLossNum(int lossNum) {
		this.lossNum = lossNum;
	}
	public int getOutNum() {
		return outNum;
	}
	public void setOutNum(int outNum) {
		this.outNum = outNum;
	}
	public int getInNum() {
		return inNum;
	}
	public void setInNum(int inNum) {
		this.inNum = inNum;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public Date getDistDate() {
		return distDate;
	}
	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}
	public String getProgress() {
		return StatisticsUtil.getProgress(distrNum, storeNum, inNum, outNum);
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCreateCount() {
		return createCount;
	}
	public void setCreateCount(String createCount) {
		this.createCount = createCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	
	
	
   
}
