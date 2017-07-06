package com.creditharmony.fortune.maintenance.settles.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 历史
 * @Class Name CreditorHis
 * @author 周俊
 * @Create In 2015年12月4日
 */
public class CreditorHisInfo extends DataEntity<CreditorHisInfo>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键

    private String dictCheckNode;//-- 节点1可用债权2月满盈3月满盈可用

    private String nodeId;//节点id

    private String matchingId;//债权推荐id

    private String operater;//操作人

    private String operateType;//操作类型:债权推荐、债权管理、匹配提交、撤销、提前赎回债权释放、债权转让债权释放、提前结清释放债权人匹配金额、结算释放当期月还本、债权拆分、债权回池、月满盈到期债权释放、划扣失败债权释放

    private BigDecimal beforMoney;//操作前金额

    private BigDecimal operateMoney;//操作金额

    private BigDecimal afterMoney;//操作后的金额

    private Date operateTime;//操作时间

    private String mac;

    private String ip;
    
    private String createBy;
    
    private Date createTime;
    
    private String modifyBy;
    
    private Date modifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDictCheckNode() {
		return dictCheckNode;
	}

	public void setDictCheckNode(String dictCheckNode) {
		this.dictCheckNode = dictCheckNode;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public BigDecimal getBeforMoney() {
		return beforMoney;
	}

	public void setBeforMoney(BigDecimal beforMoney) {
		this.beforMoney = beforMoney;
	}

	public BigDecimal getOperateMoney() {
		return operateMoney;
	}

	public void setOperateMoney(BigDecimal operateMoney) {
		this.operateMoney = operateMoney;
	}

	public BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}