package com.creditharmony.fortune.creditor.config.rate.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.DataEntity;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;

public class ProductMatchingRate extends DataEntity<ProductMatchingRate	>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -5380099707872846176L;

	private String productCode;

    private Date applyLenddayUpper;

    private Date applyLenddayLower;

    private BigDecimal matchingRateUpper;

    private BigDecimal matchingRateLower;
    
    private BigDecimal applyLendMoneyUpper;

    private BigDecimal applyLendMoneyLower;
    
    private Integer matchingBillDay;
    
    private Date matchingInterestStart;
    
    private String matchingBillDayStr;
    
    private String useFlag;
    
    private BigDecimal expectedYearRateUpper; //预计债权收益率上限

    private BigDecimal expectedYearRateLower; //预计债权收益率下限
    

    
    private String billType;
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }


    public Date getApplyLenddayUpper() {
		return applyLenddayUpper;
	}

	public void setApplyLenddayUpper(Date applyLenddayUpper) {
		this.applyLenddayUpper = applyLenddayUpper;
	}

	public Date getApplyLenddayLower() {
		return applyLenddayLower;
	}

	public void setApplyLenddayLower(Date applyLenddayLower) {
		this.applyLenddayLower = applyLenddayLower;
	}

	public BigDecimal getMatchingRateUpper() {
		return matchingRateUpper;
	}

	public void setMatchingRateUpper(BigDecimal matchingRateUpper) {
		this.matchingRateUpper = matchingRateUpper;
	}

	public BigDecimal getMatchingRateLower() {
		return matchingRateLower;
	}

	public void setMatchingRateLower(BigDecimal matchingRateLower) {
		this.matchingRateLower = matchingRateLower;
	}

	public BigDecimal getApplyLendMoneyUpper() {
		return applyLendMoneyUpper;
	}

	public void setApplyLendMoneyUpper(BigDecimal applyLendMoneyUpper) {
		this.applyLendMoneyUpper = applyLendMoneyUpper;
	}

	public BigDecimal getApplyLendMoneyLower() {
		return applyLendMoneyLower;
	}

	public void setApplyLendMoneyLower(BigDecimal applyLendMoneyLower) {
		this.applyLendMoneyLower = applyLendMoneyLower;
	}

	

	public Integer getMatchingBillDay() {
		return matchingBillDay;
	}

	public void setMatchingBillDay(Integer matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}


	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}

	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}

	

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getMatchingBillDayStr() {
		return matchingBillDayStr;
	}

	public void setMatchingBillDayStr(String matchingBillDayStr) {
		this.matchingBillDayStr = matchingBillDayStr;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public BigDecimal getExpectedYearRateUpper() {
		return getYearRate(matchingRateUpper);
	}

	public BigDecimal getExpectedYearRateLower() {
		return getYearRate(matchingRateLower);
	}

	//通过 月利率 算出 年化收益
	public BigDecimal getYearRate(BigDecimal rate){
		if(productCode != null && rate != null){
			 BigDecimal ret =  BigDecimal.ZERO;
			 if(ProductCode.YMY.value.equals(productCode)){
				 ret = new BigDecimal(String.valueOf(rate.doubleValue())); 
				 ret = ret.multiply(new BigDecimal(12));
			 }else{
				 ret = new BigDecimal(String.valueOf((Math.pow((Double.valueOf(String.valueOf(rate.doubleValue())) / 100 + 1),12) - 1) * 100));
			 }
			 ret = new BigDecimal(String.valueOf(BigDecimalUtil.round(ret.doubleValue(), 2)));
			return ret;
		}else{
			return null;
		}

	}
}