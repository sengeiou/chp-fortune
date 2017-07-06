package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.DistributeState;
import com.creditharmony.core.fortune.type.PigeonholeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.fortune.contract.constant.Constant;


public class ContractStatisticsParamView  implements Serializable {
	
	private static final long serialVersionUID = 8400587121081342926L;
	private String contStoresId;              //门店ID
	private String orgName;					  //机构名
	private String contVersion;		//合同版本
	private Date distDate;  //派发日期
	
	private String distStatus = DistributeState.WPF.value;//派发状态
	private String signStatus = SingnState.YQS.value;//签收状态
	private String kc = ContractState.KC.value; //库存
	private String yfp = ContractState.YFP.value; //已分配
	private String ycj = ContractState.YCJ.value; //出借
	private String ygd = PigeonholeState.YGD.value;//已归档
	private String yszf = ContractState.YSZF.value; //遗失作废
	private String qczf = ContractState.QCZF.value; //签错作废
	private String bbzf = ContractState.BBZF.value; //版本作废
	private String zbxf = Constant.DICT_CONT_SOURCE;//总部下发
	
	private String orgId; //营业部ID
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	private Date start;  //统计日期
	private Date end;  //统计日期
	
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
	public String getKc() {
		return kc;
	}
	public void setKc(String kc) {
		this.kc = kc;
	}
	public String getYcj() {
		return ycj;
	}
	public void setYcj(String ycj) {
		this.ycj = ycj;
	}
	public String getYgd() {
		return ygd;
	}
	public void setYgd(String ygd) {
		this.ygd = ygd;
	}
	public String getYszf() {
		return yszf;
	}
	public void setYszf(String yszf) {
		this.yszf = yszf;
	}
	public String getQczf() {
		return qczf;
	}
	public void setQczf(String qczf) {
		this.qczf = qczf;
	}
	public String getBbzf() {
		return bbzf;
	}
	public void setBbzf(String bbzf) {
		this.bbzf = bbzf;
	}
	public String getZbxf() {
		return zbxf;
	}
	public void setZbxf(String zbxf) {
		this.zbxf = zbxf;
	}
	public String getDistStatus() {
		return distStatus;
	}
	public void setDistStatus(String distStatus) {
		this.distStatus = distStatus;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		if(end!=null){
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(end);
		    calendar.set(Calendar.HOUR_OF_DAY, 23);
		    calendar.set(Calendar.MINUTE, 59);
		    calendar.set(Calendar.SECOND, 59);
		    calendar.set(Calendar.MILLISECOND, 999);
		    this.end = calendar.getTime();
		}
		
	}
	public String getYfp() {
		return yfp;
	}
	public void setYfp(String yfp) {
		this.yfp = yfp;
	}
   
}
