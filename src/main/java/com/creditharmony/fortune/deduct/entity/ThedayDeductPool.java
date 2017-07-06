package com.creditharmony.fortune.deduct.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 分天划扣实体
 * @Class Name ThedayDeductPool
 * @author 韩龙
 * @Create In 2016年1月26日
 */
public class ThedayDeductPool extends DataEntity<ThedayDeductPool>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -7007035777071700473L;

	// ID主键
	private String id;
	// 划扣申请ID
    private String deductApplyId;
    // 划扣状态
    private String dictDeductStatus;
    // 分天划扣金额
    private BigDecimal actualDeductMoney;
    // 出借金额
    private BigDecimal loanMoney;
    // 划扣成功金额
    private BigDecimal deductSucceedMoney;
    // 划扣失败金额
    private BigDecimal deductFailMoney;
    // 划扣时间
    private Date deductTime;
    // 划扣有效期
    private Date deductValidityDate;
    // 失败原因
    private String failReason;
    
    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    // 未划扣金额
    private String noDeductMoney;
    
    // 跳转金额
    private BigDecimal jumpAmount;
    
    // 跳转平台规则
    private String jumpRule;
    
    // 当日是否跳转，是或否
    private String thedayJumpFlag;
    
    // 累计失败金额
    private BigDecimal allDeductFailMoney;

    // 划扣状态列表
    private List<String> dictDeductStatusList;
    

	public List<String> getDictDeductStatusList() {
		return dictDeductStatusList;
	}

	public void setDictDeductStatusList(List<String> dictDeductStatusList) {
		this.dictDeductStatusList = dictDeductStatusList;
	}

	public String getNoDeductMoney() {
		return noDeductMoney;
	}

	public void setNoDeductMoney(String noDeductMoney) {
		this.noDeductMoney = noDeductMoney;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeductApplyId() {
        return deductApplyId;
    }

    public void setDeductApplyId(String deductApplyId) {
        this.deductApplyId = deductApplyId == null ? null : deductApplyId.trim();
    }

    public String getDictDeductStatus() {
        return dictDeductStatus;
    }

    public void setDictDeductStatus(String dictDeductStatus) {
        this.dictDeductStatus = dictDeductStatus == null ? null : dictDeductStatus.trim();
    }

    public BigDecimal getActualDeductMoney() {
        return actualDeductMoney;
    }

    public void setActualDeductMoney(BigDecimal actualDeductMoney) {
        this.actualDeductMoney = actualDeductMoney;
    }

    public Date getDeductValidityDate() {
        return deductValidityDate;
    }

    public void setDeductValidityDate(Date deductValidityDate) {
        this.deductValidityDate = deductValidityDate;
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

	public BigDecimal getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(BigDecimal loanMoney) {
		this.loanMoney = loanMoney;
	}

	public BigDecimal getDeductSucceedMoney() {
		return deductSucceedMoney;
	}

	public void setDeductSucceedMoney(BigDecimal deductSucceedMoney) {
		this.deductSucceedMoney = deductSucceedMoney;
	}

	public BigDecimal getDeductFailMoney() {
		return deductFailMoney;
	}

	public void setDeductFailMoney(BigDecimal deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}

	public Date getDeductTime() {
		return deductTime;
	}

	public void setDeductTime(Date deductTime) {
		this.deductTime = deductTime;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public BigDecimal getJumpAmount() {
		return jumpAmount;
	}

	public void setJumpAmount(BigDecimal jumpAmount) {
		this.jumpAmount = jumpAmount;
	}

	public String getJumpRule() {
		return jumpRule;
	}

	public void setJumpRule(String jumpRule) {
		this.jumpRule = jumpRule;
	}

	public String getThedayJumpFlag() {
		return thedayJumpFlag;
	}

	public void setThedayJumpFlag(String thedayJumpFlag) {
		this.thedayJumpFlag = thedayJumpFlag;
	}

	public BigDecimal getAllDeductFailMoney() {
		return allDeductFailMoney;
	}

	public void setAllDeductFailMoney(BigDecimal allDeductFailMoney) {
		this.allDeductFailMoney = allDeductFailMoney;
	}
}