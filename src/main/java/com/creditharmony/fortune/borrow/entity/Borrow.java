package com.creditharmony.fortune.borrow.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.persistence.DataEntity;

public class Borrow extends DataEntity<Borrow>{
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

		private String creditValueId;
		
		private String creditMonId;

	    private String loanCode;

	    private String loanId;

	    private String loanName;

	    private String loanIdcard;

	    private String loanJob;

	    private String dictLoanType;

	    private String loanProduct;

	    private String loanPurpose;

	    private Date loanOutmoneyDay;

	    private Date loanBackmoneyFirday;
	    
	    private String loanBackmoneyFirdayFrom;
	    
	    private String loanBackmoneyFirdayTo;
	    
	    private Integer loanBackmoneyDay;

	    private Date loanBackmoneyLastday;

	    private Integer loanMonths;

	    private Integer loanMonthsSurplus;
	    
	    private Integer loanMonthsSurplusFrom;

	    private Integer loanMonthsSurplusTo;
	   
	    private BigDecimal loanQuota;

	    private BigDecimal loanCreditValue;
	    
	    private BigDecimal loanCreditValueFrom;
	    
	    private BigDecimal loanCreditValueTo;

	    private BigDecimal loanMonthRate;

	    private BigDecimal loanValueYear;

	    private String dictLoanFreeFlag;

	    private Date loanModifiedDay;

	    private String loanPledge;

	    private Integer loanDaySurplus;

	    private String loanMiddleMan;

	    private Date loanFreezeDay;

	    private String loanMonthgainFlag;

	    private String loanTrusteeFlag;
	    
	    private String dicLoanDistinguish; // 债权区分
	    
	    private String createBy;

	    private Date createTime;

	    private String modifyBy;

	    private Date modifyTime;
	    
	    private String borrowMonthVerTime;
	    
	    private  Integer hkqs;//还款期数
	    
	    private String loanBackmoneyFirdayStr;//还款起始日期
	    private String loanBackmoneyLastdayStr;//还款截止日期
	   
	    private List<String> loanIds;
	    
	    private Map<String,String> loanIdMap;
		
	    private List<String> creditValueIds;
	    
	    private List<Integer> loanBackmoneyDays;//还款日集合
	    
	    private String trusteeFlag; // 资金托管标识
	    
	    private String trusteeFlagOrderby;
	    
	    private String dictLoanTypeStr;
	    
	    private String loanJobStr;
	    
	    private String loanContractNo; // 借款人合同编号
	    
	    private  List<String> filterLoanIdCrards;
		private Map<String,BigDecimal> ppxy; // 匹配限额
		
		private List<String> loanIdCrards; // 身份证集合
		
		private BigDecimal upperMoney;
		
		private Date loanFreezeDayFrom;  //冻结时间开始
		
		private Date loanFreezeDayTo;  //冻结时间结束
		
		public Date getLoanFreezeDayFrom() {
			return loanFreezeDayFrom;
		}

		public void setLoanFreezeDayFrom(Date loanFreezeDayFrom) {
			this.loanFreezeDayFrom = loanFreezeDayFrom;
		}

		public Date getLoanFreezeDayTo() {
			return loanFreezeDayTo;
		}

		public void setLoanFreezeDayTo(Date loanFreezeDayTo) {
			this.loanFreezeDayTo = loanFreezeDayTo;
		}

		private String yxzz;
		private String yxzsums;

		//推送债权平台
		private String pushPlatForm;
		//推送大金融状态
		private String pushStatus;

		private String borrowPushId;
		private BigDecimal loanCreditValueYS;
		
		/** 债权情况 */
		private String zqState;

		public String getPushPlatForm() {
			return pushPlatForm;
		}

		public void setPushPlatForm(String pushPlatForm) {
			this.pushPlatForm = pushPlatForm;
		}


		public String getYxzz() {
			return yxzz;
		}

		public void setYxzz(String yxzz) {
			this.yxzz = yxzz;
		}

		public String getYxzsums() {
			return yxzsums;
		}

		public void setYxzsums(String yxzsums) {
			this.yxzsums = yxzsums;
		}

		public List<String> getLoanIds() {
			return loanIds;
		}

		public void setLoanIds(List<String> loanIds) {
			this.loanIds = loanIds;
		}

		private BigDecimal tjmoney;
	    public BigDecimal getTjmoney() {
			return tjmoney;
		}

		public void setTjmoney(BigDecimal tjmoney) {
			this.tjmoney = tjmoney;
		}

		public Integer getHkqs() {
	    	if(loanMonths!=null && loanMonthsSurplus!=null){
	    		return loanMonths-loanMonthsSurplus;
	    	}else{
	    		return 0;
	    	}
		}

		public void setHkqs(Integer hkqs) {
			this.hkqs = hkqs;
		}

		public String getLoanBackmoneyFirdayStr() {
			if(loanBackmoneyFirday!=null)
			return DateUtils.formatDate(loanBackmoneyFirday, "yyyy-MM-dd");
			else{
				return "";
			}
		}

		//债权最后一次还款日
		public String getLoanBackmoneyLastdayStr() {
			if(loanBackmoneyLastday!=null)
			return DateUtils.formatDate(loanBackmoneyLastday, "yyyy-MM-dd");
			else{
				return "";
			}
		}

		
		
		public String getCreditValueId() {
	        return creditValueId;
	    }

	    public void setCreditValueId(String creditValueId) {
	        this.creditValueId = creditValueId == null ? null : creditValueId.trim();
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

	    public String getDictLoanType() {
	        return dictLoanType;
	    }

	    public void setDictLoanType(String dictLoanType) {
	        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
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

	  

	    public Integer getLoanBackmoneyDay() {
			return loanBackmoneyDay;
		}

		public void setLoanBackmoneyDay(Integer loanBackmoneyDay) {
			this.loanBackmoneyDay = loanBackmoneyDay;
		}

		public Date getLoanBackmoneyLastday() {
	        return loanBackmoneyLastday;
	    }

	    public void setLoanBackmoneyLastday(Date loanBackmoneyLastday) {
	        this.loanBackmoneyLastday = loanBackmoneyLastday;
	    }

	    public Integer getLoanMonths() {
	        return loanMonths;
	    }

	    public void setLoanMonths(Integer loanMonths) {
	        this.loanMonths = loanMonths;
	    }

	    public Integer getLoanMonthsSurplus() {
	        return loanMonthsSurplus;
	    }

	    public void setLoanMonthsSurplus(Integer loanMonthsSurplus) {
	        this.loanMonthsSurplus = loanMonthsSurplus;
	    }

	    public BigDecimal getLoanQuota() {
	        return loanQuota;
	    }

	    public void setLoanQuota(BigDecimal loanQuota) {
	        this.loanQuota = loanQuota;
	    }

	    public BigDecimal getLoanCreditValue() {
	        return loanCreditValue;
	    }

	    public void setLoanCreditValue(BigDecimal loanCreditValue) {
	        this.loanCreditValue = loanCreditValue;
	    }

	    public BigDecimal getLoanMonthRate() {
	        return loanMonthRate;
	    }

	    public void setLoanMonthRate(BigDecimal loanMonthRate) {
	        this.loanMonthRate = loanMonthRate;
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

	    public String getLoanPledge() {
	        return loanPledge;
	    }

	    public void setLoanPledge(String loanPledge) {
	        this.loanPledge = loanPledge == null ? null : loanPledge.trim();
	    }

	    public Integer getLoanDaySurplus() {
	        return loanDaySurplus;
	    }

	    public void setLoanDaySurplus(Integer loanDaySurplus) {
	        this.loanDaySurplus = loanDaySurplus;
	    }

	    public String getLoanMiddleMan() {
	        return loanMiddleMan;
	    }

	    public void setLoanMiddleMan(String loanMiddleMan) {
	        this.loanMiddleMan = loanMiddleMan == null ? null : loanMiddleMan.trim();
	    }

	    public Date getLoanFreezeDay() {
	        return loanFreezeDay;
	    }

	    public void setLoanFreezeDay(Date loanFreezeDay) {
	        this.loanFreezeDay = loanFreezeDay;
	    }

	    public String getLoanMonthgainFlag() {
	        return loanMonthgainFlag;
	    }

	    public void setLoanMonthgainFlag(String loanMonthgainFlag) {
	        this.loanMonthgainFlag = loanMonthgainFlag == null ? null : loanMonthgainFlag.trim();
	    }

	    public String getLoanTrusteeFlag() {
	        return loanTrusteeFlag;
	    }

	    public void setLoanTrusteeFlag(String loanTrusteeFlag) {
	        this.loanTrusteeFlag = loanTrusteeFlag == null ? null : loanTrusteeFlag.trim();
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

		public BigDecimal getLoanCreditValueFrom() {
			return loanCreditValueFrom;
		}

		public void setLoanCreditValueFrom(BigDecimal loanCreditValueFrom) {
			this.loanCreditValueFrom = loanCreditValueFrom;
		}

		public BigDecimal getLoanCreditValueTo() {
			return loanCreditValueTo;
		}

		public void setLoanCreditValueTo(BigDecimal loanCreditValueTo) {
			this.loanCreditValueTo = loanCreditValueTo;
		}

		public Integer getLoanMonthsSurplusFrom() {
			return loanMonthsSurplusFrom;
		}

		public void setLoanMonthsSurplusFrom(Integer loanMonthsSurplusFrom) {
			this.loanMonthsSurplusFrom = loanMonthsSurplusFrom;
		}

		public Integer getLoanMonthsSurplusTo() {
			return loanMonthsSurplusTo;
		}

		public void setLoanMonthsSurplusTo(Integer loanMonthsSurplusTo) {
			this.loanMonthsSurplusTo = loanMonthsSurplusTo;
		}

		public String getLoanBackmoneyFirdayFrom() {
			return loanBackmoneyFirdayFrom;
		}

		public void setLoanBackmoneyFirdayFrom(String loanBackmoneyFirdayFrom) {
			this.loanBackmoneyFirdayFrom = loanBackmoneyFirdayFrom;
		}

		public String getLoanBackmoneyFirdayTo() {
			return loanBackmoneyFirdayTo;
		}

		public void setLoanBackmoneyFirdayTo(String loanBackmoneyFirdayTo) {
			this.loanBackmoneyFirdayTo = loanBackmoneyFirdayTo;
		}

		public Map<String, String> getLoanIdMap() {
			return loanIdMap;
		}

		public void setLoanIdMap(Map<String, String> loanIdMap) {
			this.loanIdMap = loanIdMap;
		}

		public List<String> getCreditValueIds() {
			return creditValueIds;
		}

		public void setCreditValueIds(List<String> creditValueIds) {
			this.creditValueIds = creditValueIds;
		}

		public List<Integer> getLoanBackmoneyDays() {
			return loanBackmoneyDays;
		}

		public void setLoanBackmoneyDays(List<Integer> loanBackmoneyDays) {
			this.loanBackmoneyDays = loanBackmoneyDays;
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

		
		public List<String> getFilterLoanIdCrards() {
			return filterLoanIdCrards;
		}

		public void setFilterLoanIdCrards(List<String> filterLoanIdCrards) {
			this.filterLoanIdCrards = filterLoanIdCrards;
		}

		public Map<String, BigDecimal> getPpxy() {
			return ppxy;
		}

		public void setPpxy(Map<String, BigDecimal> ppxy) {
			this.ppxy = ppxy;
		}

		public List<String> getLoanIdCrards() {
			return loanIdCrards;
		}

		public void setLoanIdCrards(List<String> loanIdCrards) {
			this.loanIdCrards = loanIdCrards;
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

		public String getCreditMonId() {
			return creditMonId;
		}

		public void setCreditMonId(String creditMonId) {
			this.creditMonId = creditMonId;
		}

		public BigDecimal getUpperMoney() {
			return upperMoney;
		}

		public void setUpperMoney(BigDecimal upperMoney) {
			this.upperMoney = upperMoney;
		}

		public String getLoanContractNo() {
			return loanContractNo;
		}

		public void setLoanContractNo(String loanContractNo) {
			this.loanContractNo = loanContractNo;
		}

		public String getPushStatus() {
			return pushStatus;
		}

		public void setPushStatus(String pushStatus) {
			this.pushStatus = pushStatus;
		}

		public String getBorrowPushId() {
			return borrowPushId;
		}

		public void setBorrowPushId(String borrowPushId) {
			this.borrowPushId = borrowPushId;
		}

		public BigDecimal getLoanCreditValueYS() {
			return loanCreditValueYS;
		}

		public void setLoanCreditValueYS(BigDecimal loanCreditValueYS) {
			this.loanCreditValueYS = loanCreditValueYS;
		}

		public String getZqState() {
			return zqState;
		}

		public void setZqState(String zqState) {
			this.zqState = zqState;
		}
		
		
		
	    
	} 