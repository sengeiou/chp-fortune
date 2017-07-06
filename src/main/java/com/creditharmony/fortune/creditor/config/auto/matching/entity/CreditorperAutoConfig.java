package com.creditharmony.fortune.creditor.config.auto.matching.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

public class CreditorperAutoConfig extends DataEntity<CreditorperAutoConfig	>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;


    private Integer billDay;
    
    private String billDayEx;
   
    private String billType;

    private String provinceCity;
    
    private String provinceId;

    private String cityId;

    private String productCode;

    private BigDecimal productRate;

    private String status;
    
    private String businessDepartment;
    
    private String businessDepartmentName;
    
    private  Integer inTotalNum;
    
    private  Integer toMatchingNum;
    
    private  Integer notMatchingNum;
    
    private  String  progress;
    private Integer surplusNum;

    public Integer getBillDay() {
        return billDay;
    }

    public void setBillDay(Integer billDay) {
        this.billDay = billDay;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public BigDecimal getProductRate() {
        return productRate;
    }

    public void setProductRate(BigDecimal productRate) {
        this.productRate = productRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getBusinessDepartment() {
		return businessDepartment;
	}

	public void setBusinessDepartment(String businessDepartment) {
		this.businessDepartment = businessDepartment;
	}

	public String getBusinessDepartmentName() {
		return businessDepartmentName;
	}

	public void setBusinessDepartmentName(String businessDepartmentName) {
		this.businessDepartmentName = businessDepartmentName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getInTotalNum() {
		return inTotalNum;
	}

	public void setInTotalNum(Integer inTotalNum) {
		this.inTotalNum = inTotalNum;
	}

	public Integer getToMatchingNum() {
		return toMatchingNum;
	}

	public void setToMatchingNum(Integer toMatchingNum) {
		this.toMatchingNum = toMatchingNum;
	}

	public Integer getNotMatchingNum() {
		return notMatchingNum;
	}

	public void setNotMatchingNum(Integer notMatchingNum) {
		this.notMatchingNum = notMatchingNum;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Integer getSurplusNum() {
		return this.inTotalNum-this.toMatchingNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public String getBillDayEx() {
		return billDayEx;
	}

	public void setBillDayEx(String billDayEx) {
		this.billDayEx = billDayEx;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}
	
}