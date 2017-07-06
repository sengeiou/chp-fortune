package com.creditharmony.fortune.creditor.matching.entity.ext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;




/**
 * 
 * @Class 待债权推荐信息实体
 * @author 柳慧
 * @Create In 2015年12月11日
 */
public class MatchingCreditorEx  implements Serializable {                     
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 6850228792261051346L;
	private String matchingId;                       //   债权推荐ID自增ID记录出借每期情况
    private String lendCode;                         //   出借编号
    private String productCode;                      //   产品编号
    private String matchingFirstdayFlag;             //   首期非首期标志位：1首期，2非首期
    private Date matchingInterestStart;              //   起息日期
    private Integer matchingBillDay;                 //   账单日
    private Integer matchingBillState;               //   账单类型
	private BigDecimal matchingBorrowQuota;          //   出借金额
    private String matchingStatus;                  //   债权状态
    private Date matchingEndday;                     //   结束日期
    private BigDecimal matchingMatchMoney;           //   已匹配金额
    private String dictMatchingFileStatus;           //   文件制作状态（制作中；制作失败；制作成功）
    private String dictMatchingFilesendStatus;       //   文件发送状态（未发送；发送成功；发送失败）
    private Integer matchingTotal;                   //   共有几期
    private Integer matchingNow;                     //   当前期数
    private Date matchingExpireDay;                  //   本期到期日期
    private Date matchingCossTime;                   //   匹配审批通过时间
    private Date matchingMakeDay;                    //   制作协议时间
    private Date matchingHkDay;                      //   划扣时间
    private Date matchingFirstbillDay;               //   第一个账单日
    private Integer matchingOrderStatus;             //   债权订购流程状态标识
    private String matchingPayStatus;                //   0-未结算，1-已结算
    private String createBy;                         //   创建人
    private Date createTime;                         //   创建时间
    private String modifyBy;                         //   修改人
    private Date modifyTime;                         //   修改时间
	private String customerName;// 客户名称
	 private String customerCode;// 客户编号
	 private String orgName;// 客户所属机构(营业部)
	 private String orgCode; // 营业部code
	 private String productTypeName;// 产品类型
	 private String applyPay;// 付款方式
	 private Date startApplyLendDay;// 初始出借日期
	 private String startApplyLendDayStart;// 初始出借日期 开始
	 private String startApplyLendDayEnd;// 初始出借日期 结束
	 private BigDecimal startApplyLendMoney;// 初始出借金额
	 private BigDecimal startApplyLendMoneyFrom;// 初始出借金额
	 private BigDecimal startApplyLendMoneyTo;// 初始出借金额
	 private Date applyLend;// 出借日期
	 private BigDecimal tradeMateMoney;// 匹配金额
	 private Date applyExpireDay;// 到期日期
	 private String tradeCreditStatus;// 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
	 private String productName; // 产品名称
	 private List<String> productCodes;                        //   产品Codes
	 private List<String> matchingFirstdayFlags;             //   首期非首期标志位：1首期，2非首期
	 private List<Integer> matchingBillDays;                 //   账单日
	 private String matchingBillDayStr;                 //   账单日
	 private List<String> applyPays;// 付款方式
	 private List<String> filtermatchingIds;
	 private List<String> matchingStatusLst;                  //   债权状态集合
	 private String matchingUserName; // 匹配人姓名
	 private List<String> listMatchingId; 
	 private BigDecimal matchingBorrowQuotaFrom; // 本期出借金额 开始
	 private BigDecimal matchingBorrowQuotaTo; // 本期出借金额  结束
	 private String matchingInterestStartStart;// 本期出借日期  开始
	 private String matchingInterestStartEnd; // 本期出借日期 结束
	 private String accountBank; //银行
	 private String city; // 城市
	 private String dictApplyDeductType; // 划扣平台
	 private String deductStart;   // 计划划扣日 开始
	 private String deductEnd; // 计划划扣日 结束
	 private List<String> accountBankList; // 银行集合
	 private List<String> dictApplyDeductTypes; // 划扣平台
	 private String matchingUserId; // 匹配人ID
	 private String verTime;   
	 private List<String> lendStatusLst;// 出借状态
	 private String updatematchingStatus;    //   更新债权状态
	 private String dictMatchingFlagType; // 匹配标识
	 private List<String> dictMatchingFlagTypes; // 匹配标识集合
	 private String beforMatchingStatus;        //修改前债权状态
	 
	public String getBeforMatchingStatus() {
		return beforMatchingStatus;
	}

	public void setBeforMatchingStatus(String beforMatchingStatus) {
		this.beforMatchingStatus = beforMatchingStatus;
	}

	public String getMatchingUserId() {
		return matchingUserId;
	}

	public void setMatchingUserId(String matchingUserId) {
		this.matchingUserId = matchingUserId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId == null ? null : matchingId.trim();
    }

    public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getMatchingFirstdayFlag() {
        return matchingFirstdayFlag;
    }

    public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
        this.matchingFirstdayFlag = matchingFirstdayFlag == null ? null : matchingFirstdayFlag.trim();
    }

    public Date getMatchingInterestStart() {
        return matchingInterestStart;
    }

    public void setMatchingInterestStart(Date matchingInterestStart) {
        this.matchingInterestStart = matchingInterestStart;
    }

    public Integer getMatchingBillDay() {
        return matchingBillDay;
    }

    public void setMatchingBillDay(Integer matchingBillDay) {
        this.matchingBillDay = matchingBillDay;
    }

    public BigDecimal getMatchingBorrowQuota() {
        return matchingBorrowQuota;
    }

    public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
        this.matchingBorrowQuota = matchingBorrowQuota;
    }

    public String getMatchingStatus() {
		return matchingStatus;
	}

	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}

	public Date getMatchingEndday() {
        return matchingEndday;
    }

    public void setMatchingEndday(Date matchingEndday) {
        this.matchingEndday = matchingEndday;
    }

    public BigDecimal getMatchingMatchMoney() {
        return matchingMatchMoney;
    }

    public void setMatchingMatchMoney(BigDecimal matchingMatchMoney) {
        this.matchingMatchMoney = matchingMatchMoney;
    }

    public Integer getMatchingBillState() {
		return matchingBillState;
	}

	public void setMatchingBillState(Integer matchingBillState) {
		this.matchingBillState = matchingBillState;
	}

    public String getDictMatchingFileStatus() {
        return dictMatchingFileStatus;
    }

    public void setDictMatchingFileStatus(String dictMatchingFileStatus) {
        this.dictMatchingFileStatus = dictMatchingFileStatus == null ? null : dictMatchingFileStatus.trim();
    }

    public String getDictMatchingFilesendStatus() {
        return dictMatchingFilesendStatus;
    }

    public void setDictMatchingFilesendStatus(String dictMatchingFilesendStatus) {
        this.dictMatchingFilesendStatus = dictMatchingFilesendStatus == null ? null : dictMatchingFilesendStatus.trim();
    }

    public Integer getMatchingTotal() {
        return matchingTotal;
    }

    public void setMatchingTotal(Integer matchingTotal) {
        this.matchingTotal = matchingTotal;
    }

    public Integer getMatchingNow() {
        return matchingNow;
    }

    public void setMatchingNow(Integer matchingNow) {
        this.matchingNow = matchingNow;
    }

    public Date getMatchingExpireDay() {
        return matchingExpireDay;
    }

    public void setMatchingExpireDay(Date matchingExpireDay) {
        this.matchingExpireDay = matchingExpireDay;
    }

    public Date getMatchingCossTime() {
        return matchingCossTime;
    }

    public void setMatchingCossTime(Date matchingCossTime) {
        this.matchingCossTime = matchingCossTime;
    }

    public Date getMatchingMakeDay() {
        return matchingMakeDay;
    }

    public void setMatchingMakeDay(Date matchingMakeDay) {
        this.matchingMakeDay = matchingMakeDay;
    }

    public Date getMatchingHkDay() {
        return matchingHkDay;
    }

    public void setMatchingHkDay(Date matchingHkDay) {
        this.matchingHkDay = matchingHkDay;
    }

    public Date getMatchingFirstbillDay() {
        return matchingFirstbillDay;
    }

    public void setMatchingFirstbillDay(Date matchingFirstbillDay) {
        this.matchingFirstbillDay = matchingFirstbillDay;
    }

    public Integer getMatchingOrderStatus() {
        return matchingOrderStatus;
    }

    public void setMatchingOrderStatus(Integer matchingOrderStatus) {
        this.matchingOrderStatus = matchingOrderStatus;
    }

    public String getMatchingPayStatus() {
        return matchingPayStatus;
    }

    public void setMatchingPayStatus(String matchingPayStatus) {
        this.matchingPayStatus = matchingPayStatus == null ? null : matchingPayStatus.trim();
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public Date getStartApplyLendDay() {
		return startApplyLendDay;
	}

	public void setStartApplyLendDay(Date startApplyLendDay) {
		this.startApplyLendDay = startApplyLendDay;
	}

	public BigDecimal getStartApplyLendMoney() {
		return startApplyLendMoney;
	}

	public void setStartApplyLendMoney(BigDecimal startApplyLendMoney) {
		this.startApplyLendMoney = startApplyLendMoney;
	}

	public Date getApplyLend() {
		return applyLend;
	}

	public void setApplyLend(Date applyLend) {
		this.applyLend = applyLend;
	}

	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}

	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getTradeCreditStatus() {
		return tradeCreditStatus;
	}

	public void setTradeCreditStatus(String tradeCreditStatus) {
		this.tradeCreditStatus = tradeCreditStatus;
	}

	public String getStartApplyLendDayStart() {
		return startApplyLendDayStart;
	}

	public void setStartApplyLendDayStart(String startApplyLendDayStart) {
		this.startApplyLendDayStart = startApplyLendDayStart;
	}

	public String getStartApplyLendDayEnd() {
		return startApplyLendDayEnd;
	}

	public void setStartApplyLendDayEnd(String startApplyLendDayEnd) {
		this.startApplyLendDayEnd = startApplyLendDayEnd;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public BigDecimal getStartApplyLendMoneyFrom() {
		return startApplyLendMoneyFrom;
	}

	public void setStartApplyLendMoneyFrom(BigDecimal startApplyLendMoneyFrom) {
		this.startApplyLendMoneyFrom = startApplyLendMoneyFrom;
	}

	public BigDecimal getStartApplyLendMoneyTo() {
		return startApplyLendMoneyTo;
	}

	public void setStartApplyLendMoneyTo(BigDecimal startApplyLendMoneyTo) {
		this.startApplyLendMoneyTo = startApplyLendMoneyTo;
	}

	public List<String> getProductCodes() {
		if(!StringUtils.isEmpty(productCode)){
			productCodes = new ArrayList<String>();
			String [] pidArr =  productCode.split(",");
			for(int i = 0 ; i<pidArr.length; i++){
				productCodes.add(pidArr[i]);
			}
			return productCodes;
		}
		return null;
	}

	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}

	public List<String> getMatchingFirstdayFlags() {
		if(!StringUtils.isEmpty(matchingFirstdayFlag)){
			matchingFirstdayFlags = new ArrayList<String>();
		String [] flg =  matchingFirstdayFlag.split(",");
		for(int i = 0 ; i<flg.length; i++){
			matchingFirstdayFlags.add(flg[i]);
		}
		return matchingFirstdayFlags;
	}
	return null;
	}

	public void setMatchingFirstdayFlags(List<String> matchingFirstdayFlags) {
		this.matchingFirstdayFlags = matchingFirstdayFlags;
	}

	public List<Integer> getMatchingBillDays() {
		if(!StringUtils.isEmpty(matchingBillDayStr)){
			matchingBillDays = new ArrayList<Integer>();
		String [] billDay =  matchingBillDayStr.split(",");
		for(int i = 0 ; i<billDay.length; i++){
			matchingBillDays.add(Integer.valueOf(billDay[i]));
		}
		}
		return matchingBillDays;
	}

	public void setMatchingBillDays(List<Integer> matchingBillDays) {
		this.matchingBillDays = matchingBillDays;
	}

	public String getMatchingBillDayStr() {
		return matchingBillDayStr;
	}

	public void setMatchingBillDayStr(String matchingBillDayStr) {
		this.matchingBillDayStr = matchingBillDayStr;
	}

	public List<String> getApplyPays() {
		if (!StringUtils.isEmpty(applyPay)) {
			applyPays = new ArrayList<String>();
			String[] apply = applyPay.split(",");
			for (int i = 0; i < apply.length; i++) {
				applyPays.add(apply[i]);
			}
			return applyPays;
		}
		return null;
	}

	public void setApplyPays(List<String> applyPays) {
		this.applyPays = applyPays;
	}

	public List<String> getFiltermatchingIds() {
		return filtermatchingIds;
	}

	public void setFiltermatchingIds(List<String> filtermatchingIds) {
		this.filtermatchingIds = filtermatchingIds;
	}

	public List<String> getMatchingStatusLst() {
		return matchingStatusLst;
	}

	public void setMatchingStatusLst(List<String> matchingStatusLst) {
		this.matchingStatusLst = matchingStatusLst;
	}

	public List<String> getListMatchingId() {
		return listMatchingId;
	}

	public void setListMatchingId(List<String> listMatchingId) {
		this.listMatchingId = listMatchingId;
	}

	public String getMatchingUserName() {
		return matchingUserName;
	}

	public void setMatchingUserName(String matchingUserName) {
		this.matchingUserName = matchingUserName;
	}

	public BigDecimal getMatchingBorrowQuotaFrom() {
		return matchingBorrowQuotaFrom;
	}

	public void setMatchingBorrowQuotaFrom(BigDecimal matchingBorrowQuotaFrom) {
		this.matchingBorrowQuotaFrom = matchingBorrowQuotaFrom;
	}

	public BigDecimal getMatchingBorrowQuotaTo() {
		return matchingBorrowQuotaTo;
	}

	public void setMatchingBorrowQuotaTo(BigDecimal matchingBorrowQuotaTo) {
		this.matchingBorrowQuotaTo = matchingBorrowQuotaTo;
	}

	public String getMatchingInterestStartStart() {
		return matchingInterestStartStart;
	}

	public void setMatchingInterestStartStart(String matchingInterestStartStart) {
		this.matchingInterestStartStart = matchingInterestStartStart;
	}

	public String getMatchingInterestStartEnd() {
		return matchingInterestStartEnd;
	}

	public void setMatchingInterestStartEnd(String matchingInterestStartEnd) {
		this.matchingInterestStartEnd = matchingInterestStartEnd;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getDeductStart() {
		return deductStart;
	}

	public void setDeductStart(String deductStart) {
		this.deductStart = deductStart;
	}

	public String getDeductEnd() {
		return deductEnd;
	}

	public void setDeductEnd(String deductEnd) {
		this.deductEnd = deductEnd;
	}

	public List<String> getAccountBankList() {
		return accountBankList;
	}

	public void setAccountBankList(List<String> accountBankList) {
		this.accountBankList = accountBankList;
	}

	public List<String> getDictApplyDeductTypes() {
		return dictApplyDeductTypes;
	}

	public void setDictApplyDeductTypes(List<String> dictApplyDeductTypes) {
		this.dictApplyDeductTypes = dictApplyDeductTypes;
	}

	public String getVerTime() {
		return verTime;
	}

	public void setVerTime(String verTime) {
		this.verTime = verTime;
	}

	public List<String> getLendStatusLst() {
		return lendStatusLst;
	}

	public void setLendStatusLst(List<String> lendStatusLst) {
		this.lendStatusLst = lendStatusLst;
	}

	public String getUpdatematchingStatus() {
		return updatematchingStatus;
	}

	public void setUpdatematchingStatus(String updatematchingStatus) {
		this.updatematchingStatus = updatematchingStatus;
	}

	public String getDictMatchingFlagType() {
		return dictMatchingFlagType;
	}

	public void setDictMatchingFlagType(String dictMatchingFlagType) {
		this.dictMatchingFlagType = dictMatchingFlagType;
	}

	public List<String> getDictMatchingFlagTypes() {
		return dictMatchingFlagTypes;
	}

	public void setDictMatchingFlagTypes(List<String> dictMatchingFlagTypes) {
		this.dictMatchingFlagTypes = dictMatchingFlagTypes;
	}
	
	
}