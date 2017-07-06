package com.creditharmony.fortune.contract.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;


public class ContractApply extends DataEntity<ContractApply>{
	
	private static final long serialVersionUID = -7961309385153469973L;
	
	private String contractId;						//申请ID
	private String contStoresId;					//门店ID
    private Integer applyNo;						//申请数量
    private Integer applyDistNo;					//推荐数量
    private String contVersion;						//合同版本
    private Integer applyAlreadyuse;				//上月使用
    private Integer applyInventory;					//现有库存
    private String applyBy;							//申请人
    private String applyContacts;					//联系人
    private String applyTel;						//联系电话
    private String applyShippingAddr;				//收货地址
    private String applyExplain;					//申请说明
    private Date applyDay;							//申请日期
    private String applyStatus;						//审核状态
    private String applyCheckById;					// 审核人ID
    private String applyCheckDesc;					//审核意见
    private Date applyCheckDate;					//审核日期
    private String applyCheckResult;				//审核结果 0 通过 1 拒绝
    private String dictApplyRefuseDemo;				//批量驳回理由(1 审批不通过 2 )
    private String orgName;							//orgID机构表ID
    private Date distDate;							//派发日期
    
    
    public Date getDistDate() {
		return distDate;
	}

	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}

	public Date getApplyDay() {
		return applyDay;
	}

	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}

	public String getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}

	public Integer getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}

	public Integer getApplyDistNo() {
		return applyDistNo;
	}

	public void setApplyDistNo(Integer applyDistNo) {
		this.applyDistNo = applyDistNo;
	}

	public String getApplyCheckResult() {
		return applyCheckResult;
	}

	public void setApplyCheckResult(String applyCheckResult) {
		this.applyCheckResult = applyCheckResult;
	}

	public String getDictApplyRefuseDemo() {
		return dictApplyRefuseDemo;
	}

	public void setDictApplyRefuseDemo(String dictApplyRefuseDemo) {
		this.dictApplyRefuseDemo = dictApplyRefuseDemo;
	}

	public String getDistContractNo() {
		return distContractNo;
	}

	public void setDistContractNo(String distContractNo) {
		this.distContractNo = distContractNo;
	}

	private String distContractNo;					//派发数量
    
    private String loginName;						//登录名
        

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getContStoresId() {
        return contStoresId;
    }

    public void setContStoresId(String contStoresId) {
        this.contStoresId = contStoresId == null ? null : contStoresId.trim();
    }

    public String getContVersion() {
        return contVersion;
    }

    public void setContVersion(String contVersion) {
        this.contVersion = contVersion == null ? null : contVersion.trim();
    }

    public Integer getApplyAlreadyuse() {
        return applyAlreadyuse;
    }

    public void setApplyAlreadyuse(Integer applyAlreadyuse) {
        this.applyAlreadyuse = applyAlreadyuse;
    }

    public Integer getApplyInventory() {
        return applyInventory;
    }

    public void setApplyInventory(Integer applyInventory) {
        this.applyInventory = applyInventory;
    }
    
    public String getApplyContacts() {
        return applyContacts;
    }

    public void setApplyContacts(String applyContacts) {
        this.applyContacts = applyContacts == null ? null : applyContacts.trim();
    }

    public String getApplyTel() {
        return applyTel;
    }

    public void setApplyTel(String applyTel) {
        this.applyTel = applyTel == null ? null : applyTel.trim();
    }

    public String getApplyShippingAddr() {
        return applyShippingAddr;
    }

    public void setApplyShippingAddr(String applyShippingAddr) {
        this.applyShippingAddr = applyShippingAddr == null ? null : applyShippingAddr.trim();
    }

    public String getApplyExplain() {
        return applyExplain;
    }

    public void setApplyExplain(String applyExplain) {
        this.applyExplain = applyExplain == null ? null : applyExplain.trim();
    }
    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    public String getApplyCheckById() {
		return applyCheckById;
	}

	public void setApplyCheckById(String applyCheckById) {
		this.applyCheckById = applyCheckById;
	}

	public String getApplyCheckDesc() {
        return applyCheckDesc;
    }

    public void setApplyCheckDesc(String applyCheckDesc) {
        this.applyCheckDesc = applyCheckDesc == null ? null : applyCheckDesc.trim();
    }

    public Date getApplyCheckDate() {
        return applyCheckDate;
    }

    public void setApplyCheckDate(Date applyCheckDate) {
        this.applyCheckDate = applyCheckDate;
    }
}