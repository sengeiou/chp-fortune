package com.creditharmony.fortune.borrow.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.persistence.DataEntity;

public class BorrowMonthable extends DataEntity<BorrowMonthable>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String creditMonableId;

    private String creditMonId;

    private String loanCode;

    private String loanId;

    private String loanName;

    private String loanIdcard;

    private String loanJob;

    private String loanProduct;

    private String loanPurpose;

    private String dictLoanType;

    private Date loanOutmoneyDay;

    private Date loanBackmoneyFirday;
    
    private String loanBackmoneyFirdayStr;
    
    private String  borrowBackmoneyFirday;

    private Date loanCreditDayUsable; // 债权可用日期

    private Integer loanDay;// 借款天数

    private Integer loanAvailableDays;// 可用天数

    private Date loanBackmoneyDay;
    
    private Date loanEndmoneyDay;
    
    private String loanBackmoneyDayStr;
    
    private BigDecimal loanMonthRate;

    private BigDecimal loanCreditValue;

    private BigDecimal loanAvailabeValue;

    private BigDecimal loanValueYear;

    private String dictLoanFreeFlag;

    private Date loanModifiedDay;

    private Date loanFreezeDay;

    private String loanCarNumber;
    
    private String dicLoanDistinguish; // 债权区分

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private String borrowMonthVerTime;
    
    private List<String> LoanIds;
    
    private Map<String,BigDecimal> ppxy; // 匹配限额
    
    private String loanMiddleMan;
     
    private List<String> dictLoanTypes;
    
    private BigDecimal tjMomey;
    
    private List<String> filterLoanIdCards;//待过滤借款人code 
    
    private String trusteeFlag; // 资金托管标识
    
    private  String trusteeFlagOrderby;
    
    private String dictLoanTypeStr;
    
    private String loanJobStr;
    
    private List<String> oldLoadIdCard; // 已经匹配的债权人身份证集合
    
    private BigDecimal ppxeMoney;

    private List<String> creditMonableIds; // 已匹配的债权编号
    
    private BigDecimal upperMoney; // 限额设置

	public String getLoanBackmoneyFirdayStr() {
		if(loanBackmoneyFirday!=null)
			return DateUtils.formatDate(loanBackmoneyFirday, "yyyy-MM-dd");
			else{
				return "";
			}
	}



	public String getLoanBackmoneyDayStr() {
		if(loanBackmoneyDay!=null)
			return DateUtils.formatDate(loanBackmoneyDay, "yyyy-MM-dd");
			else{
				return "";
			}
	}

	

	public BigDecimal getTjMomey() {
		return tjMomey;
	}

	public void setTjMomey(BigDecimal tjMomey) {
		this.tjMomey = tjMomey;
	}

	public List<String> getDictLoanTypes() {
		return dictLoanTypes;
	}

	public void setDictLoanTypes(List<String> dictLoanTypes) {
		this.dictLoanTypes = dictLoanTypes;
	}

	public Map<String, BigDecimal> getPpxy() {
		return ppxy;
	}

	public void setPpxy(Map<String, BigDecimal> ppxy) {
		this.ppxy = ppxy;
	}

	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}

	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}

	public List<String> getLoanIds() {
		return LoanIds;
	}

	public void setLoanIds(List<String> loanIds) {
		LoanIds = loanIds;
	}

	public String getCreditMonableId() {
        return creditMonableId;
    }

    public void setCreditMonableId(String creditMonableId) {
        this.creditMonableId = creditMonableId == null ? null : creditMonableId.trim();
    }

    public String getCreditMonId() {
        return creditMonId;
    }

    public void setCreditMonId(String creditMonId) {
        this.creditMonId = creditMonId == null ? null : creditMonId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId == null ? null : loanId.trim();
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName == null ? null : loanName.trim();
    }

    public String getLoanIdcard() {
        return loanIdcard;
    }

    public void setLoanIdcard(String loanIdcard) {
        this.loanIdcard = loanIdcard == null ? null : loanIdcard.trim();
    }

    public String getLoanJob() {
        return loanJob;
    }

    public void setLoanJob(String loanJob) {
        this.loanJob = loanJob == null ? null : loanJob.trim();
    }

    public String getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProduct(String loanProduct) {
        this.loanProduct = loanProduct == null ? null : loanProduct.trim();
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose == null ? null : loanPurpose.trim();
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public Date getLoanOutmoneyDay() {
        return loanOutmoneyDay;
    }

    public void setLoanOutmoneyDay(Date loanOutmoneyDay) {
        this.loanOutmoneyDay = loanOutmoneyDay;
    }

    public Date getLoanBackmoneyFirday() {
        return loanBackmoneyFirday;
    }

    public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
        this.loanBackmoneyFirday = loanBackmoneyFirday;
    }

    public Date getLoanCreditDayUsable() {
        return loanCreditDayUsable;
    }

    public void setLoanCreditDayUsable(Date loanCreditDayUsable) {
        this.loanCreditDayUsable = loanCreditDayUsable;
    }

    public Integer getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(Integer loanDay) {
        this.loanDay = loanDay;
    }

    public Integer getLoanAvailableDays() {
        return loanAvailableDays;
    }

    public void setLoanAvailableDays(Integer loanAvailableDays) {
        this.loanAvailableDays = loanAvailableDays;
    }

    public Date getLoanBackmoneyDay() {
        return loanBackmoneyDay;
    }

    public void setLoanBackmoneyDay(Date loanBackmoneyDay) {
        this.loanBackmoneyDay = loanBackmoneyDay;
    }

    public BigDecimal getLoanMonthRate() {
        return loanMonthRate;
    }

    public void setLoanMonthRate(BigDecimal loanMonthRate) {
        this.loanMonthRate = loanMonthRate;
    }

    public BigDecimal getLoanCreditValue() {
        return loanCreditValue;
    }

    public void setLoanCreditValue(BigDecimal loanCreditValue) {
        this.loanCreditValue = loanCreditValue;
    }

    public BigDecimal getLoanAvailabeValue() {
        return loanAvailabeValue;
    }

    public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
        this.loanAvailabeValue = loanAvailabeValue;
    }

    public BigDecimal getLoanValueYear() {
        return loanValueYear;
    }

    public void setLoanValueYear(BigDecimal loanValueYear) {
        this.loanValueYear = loanValueYear;
    }

    public String getDictLoanFreeFlag() {
        return dictLoanFreeFlag;
    }

    public void setDictLoanFreeFlag(String dictLoanFreeFlag) {
        this.dictLoanFreeFlag = dictLoanFreeFlag == null ? null : dictLoanFreeFlag.trim();
    }

    public Date getLoanModifiedDay() {
        return loanModifiedDay;
    }

    public void setLoanModifiedDay(Date loanModifiedDay) {
        this.loanModifiedDay = loanModifiedDay;
    }

    public Date getLoanFreezeDay() {
        return loanFreezeDay;
    }

    public void setLoanFreezeDay(Date loanFreezeDay) {
        this.loanFreezeDay = loanFreezeDay;
    }

    public String getLoanCarNumber() {
        return loanCarNumber;
    }

    public void setLoanCarNumber(String loanCarNumber) {
        this.loanCarNumber = loanCarNumber == null ? null : loanCarNumber.trim();
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

	public Date getLoanEndmoneyDay() {
		return loanEndmoneyDay;
	}

	public void setLoanEndmoneyDay(Date loanEndmoneyDay) {
		this.loanEndmoneyDay = loanEndmoneyDay;
	}
	


	public List<String> getFilterLoanIdCards() {
		return filterLoanIdCards;
	}



	public void setFilterLoanIdCards(List<String> filterLoanIdCards) {
		this.filterLoanIdCards = filterLoanIdCards;
	}



	public String getTrusteeFlag() {
		return trusteeFlag;
	}



	public void setTrusteeFlag(String trusteeFlag) {
		this.trusteeFlag = trusteeFlag;
	}



	public String getDictLoanTypeStr() {
		if(dictLoanType!=null){
			try {
				return LoanType.parseByCode(dictLoanType).getName();
			} catch (Exception e) {
				return  "-";
			}
		}
		return "-";
	}



	public void setDictLoanTypeStr(String dictLoanTypeStr) {
		this.dictLoanTypeStr = dictLoanTypeStr;
	}



	public String getLoanJobStr() {
		if(loanJob!=null){
			String retStr = "-";
			try {
				retStr = ProfType.parseByCode(loanJob).getName();
			} catch (Exception e) {
				return "-";
			}
			return retStr;
		}
		return "-";
	}



	public void setLoanJobStr(String loanJobStr) {
		this.loanJobStr = loanJobStr;
	}



	public List<String> getOldLoadIdCard() {
		return oldLoadIdCard;
	}



	public void setOldLoadIdCard(List<String> oldLoadIdCard) {
		this.oldLoadIdCard = oldLoadIdCard;
	}



	public BigDecimal getPpxeMoney() {
		return ppxeMoney;
	}



	public void setPpxeMoney(BigDecimal ppxeMoney) {
		this.ppxeMoney = ppxeMoney;
	}



	public List<String> getCreditMonableIds() {
		return creditMonableIds;
	}



	public void setCreditMonableIds(List<String> creditMonableIds) {
		this.creditMonableIds = creditMonableIds;
	}



	public String getBorrowBackmoneyFirday() {
		return borrowBackmoneyFirday;
	}



	public void setBorrowBackmoneyFirday(String borrowBackmoneyFirday) {
		this.borrowBackmoneyFirday = borrowBackmoneyFirday;
	}



	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}



	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}



	public String getTrusteeFlagOrderby() {
		return trusteeFlagOrderby;
	}



	public void setTrusteeFlagOrderby(String trusteeFlagOrderby) {
		this.trusteeFlagOrderby = trusteeFlagOrderby;
	}



	public String getBorrowMonthVerTime() {
		return borrowMonthVerTime;
	}



	public void setBorrowMonthVerTime(String borrowMonthVerTime) {
		this.borrowMonthVerTime = borrowMonthVerTime;
	}



	public BigDecimal getUpperMoney() {
		return upperMoney;
	}



	public void setUpperMoney(BigDecimal upperMoney) {
		this.upperMoney = upperMoney;
	}
	
	
}