package com.creditharmony.fortune.deduct.entity.ext;
import java.io.Serializable;
import java.util.Date;

/**
 * 债权model扩展类
 * 扩展的属性有注释
 * @Class Name CreditorTradeExt
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class CreditorTradeEx implements Serializable{

	private static final long serialVersionUID = -7362676773818522864L;
	// 债权交易id
	private String tradeId;
	// 债权id
	private String matchingId;
	// 出借编号
    private String applyCode;
    // 债权节点；债权池；月满盈可用债权池
    private String creditNode;
    // 债权ID 是两个表中的ID
    private String creditCode;
    // 债权交易预计到期时间
    private Date tradeExpectDate;
    // 债权交易实际到期时间
    private Date tradeActualDate;
    // 匹配金额
    private String tradeMateMoney;
    // 匹配金额所占百分比
    private String tradeMateMoneyPercent;
    // 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，
    // 3:借款正常到期关闭，4:出借提前到期关闭，
    // 5:借款提前到期关闭，6:未开始被关闭)
    private Integer tradeCreditStatus;
    // 审核通过时间
    private Date tradePassDate;
    // 划扣成功时间
    private Date tradeDeductDate;
    // 实际出借天数
    private String tradeBorrowdaysActual;
    // 借款人姓名
    private String borrowerName;
    // 借款人身份证
    private String borrowerIdcard;
    // 借款人职业
    private String borrowerJob;
    // 借款用途
    private String borrowPurpose;
    // 还款起始日期
    private String borrowBackmoneyFirday;
    // 还款期数
    private String borrowMonths;
    // 月利率
    private String borrowMonthRate;
    // 剩余期数
    private String borrowMonthsSurplus;
    // 预计债权收益率
    private String loanPhase;
    // 借款天数
    private String borrowDays;
    // 可用天数
    private String borrowAvailableDays;
    // 可用债权价值
    private String borrowCreditValue;
    // 借款抵押物，只有借款类型为 房借和车借的时候有值 房借为：抵押房，车借为：车牌号
    private String borrowPledge;
    // 年预计债权收益 chp30.t_tz_borrow_monthable
    private String borrowValueYear;
    // 借款类型 信用 抵押
    private String borrowType;
    // 借款方式 借款产品类型
    private String borrowProduct;
    // 债权可用日期
    private String borrowCreditDateUsable;
    
    public String getBorrowCreditDateUsable() {
		return borrowCreditDateUsable;
	}

	public void setBorrowCreditDateUsable(String borrowCreditDateUsable) {
		this.borrowCreditDateUsable = borrowCreditDateUsable;
	}

	public String getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode == null ? null : applyCode.trim();
    }

    public String getCreditNode() {
        return creditNode;
    }

    public void setCreditNode(String creditNode) {
        this.creditNode = creditNode == null ? null : creditNode.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Date getTradeExpectDate() {
        return tradeExpectDate;
    }

    public void setTradeExpectDate(Date tradeExpectDate) {
        this.tradeExpectDate = tradeExpectDate;
    }

    public Date getTradeActualDate() {
        return tradeActualDate;
    }

    public void setTradeActualDate(Date tradeActualDate) {
        this.tradeActualDate = tradeActualDate;
    }

    public String getTradeMateMoney() {
        return tradeMateMoney;
    }

    public void setTradeMateMoney(String tradeMateMoney) {
        this.tradeMateMoney = tradeMateMoney == null ? null : tradeMateMoney.trim();
    }

    public String getTradeMateMoneyPercent() {
        return tradeMateMoneyPercent;
    }

    public void setTradeMateMoneyPercent(String tradeMateMoneyPercent) {
        this.tradeMateMoneyPercent = tradeMateMoneyPercent == null ? null : tradeMateMoneyPercent.trim();
    }

    public Integer getTradeCreditStatus() {
        return tradeCreditStatus;
    }

    public void setTradeCreditStatus(Integer tradeCreditStatus) {
        this.tradeCreditStatus = tradeCreditStatus;
    }

    public Date getTradePassDate() {
        return tradePassDate;
    }

    public void setTradePassDate(Date tradePassDate) {
        this.tradePassDate = tradePassDate;
    }

    public Date getTradeDeductDate() {
        return tradeDeductDate;
    }

    public void setTradeDeductDate(Date tradeDeductDate) {
        this.tradeDeductDate = tradeDeductDate;
    }

    public String getTradeBorrowdaysActual() {
        return tradeBorrowdaysActual;
    }

    public void setTradeBorrowdaysActual(String tradeBorrowdaysActual) {
        this.tradeBorrowdaysActual = tradeBorrowdaysActual == null ? null : tradeBorrowdaysActual.trim();
    }

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerIdcard() {
		return borrowerIdcard;
	}

	public void setBorrowerIdcard(String borrowerIdcard) {
		this.borrowerIdcard = borrowerIdcard;
	}

	public String getBorrowerJob() {
		return borrowerJob;
	}

	public void setBorrowerJob(String borrowerJob) {
		this.borrowerJob = borrowerJob;
	}

	public String getBorrowPurpose() {
		return borrowPurpose;
	}

	public void setBorrowPurpose(String borrowPurpose) {
		this.borrowPurpose = borrowPurpose;
	}

	public String getBorrowBackmoneyFirday() {
		return borrowBackmoneyFirday;
	}

	public void setBorrowBackmoneyFirday(String borrowBackmoneyFirday) {
		this.borrowBackmoneyFirday = borrowBackmoneyFirday;
	}

	public String getLoanPhase() {
		return loanPhase;
	}

	public void setLoanPhase(String loanPhase) {
		this.loanPhase = loanPhase;
	}

	public String getBorrowMonths() {
		return borrowMonths;
	}

	public void setBorrowMonths(String borrowMonths) {
		this.borrowMonths = borrowMonths;
	}

	public String getBorrowMonthRate() {
		return borrowMonthRate;
	}

	public void setBorrowMonthRate(String borrowMonthRate) {
		this.borrowMonthRate = borrowMonthRate;
	}

	public String getBorrowMonthsSurplus() {
		return borrowMonthsSurplus;
	}

	public void setBorrowMonthsSurplus(String borrowMonthsSurplus) {
		this.borrowMonthsSurplus = borrowMonthsSurplus;
	}

	public String getBorrowDays() {
		return borrowDays;
	}

	public void setBorrowDays(String borrowDays) {
		this.borrowDays = borrowDays;
	}

	public String getBorrowAvailableDays() {
		return borrowAvailableDays;
	}

	public void setBorrowAvailableDays(String borrowAvailableDays) {
		this.borrowAvailableDays = borrowAvailableDays;
	}

	public String getBorrowCreditValue() {
		return borrowCreditValue;
	}

	public void setBorrowCreditValue(String borrowCreditValue) {
		this.borrowCreditValue = borrowCreditValue;
	}

	public String getBorrowPledge() {
		return borrowPledge;
	}

	public void setBorrowPledge(String borrowPledge) {
		this.borrowPledge = borrowPledge;
	}

	public String getBorrowValueYear() {
		return borrowValueYear;
	}

	public void setBorrowValueYear(String borrowValueYear) {
		this.borrowValueYear = borrowValueYear;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public String getBorrowProduct() {
		return borrowProduct;
	}

	public void setBorrowProduct(String borrowProduct) {
		this.borrowProduct = borrowProduct;
	}
}