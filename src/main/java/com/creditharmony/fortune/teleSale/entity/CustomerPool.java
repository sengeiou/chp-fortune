package com.creditharmony.fortune.teleSale.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CustomerPool extends DataEntity<CustomerPool> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 971246443861050075L;

    private String customerCode;

    private String customerName;

    private String customerTel;

    private String productName;

    private String provinceId;

    private String cityId;

    private String cityIdTemp;
    
    private String salesDept;

    private String card;

    private String remark;

    private Date lendInputDate;

    private String dataType;

    private String provinceName;
	private String cityName;
	private String salesDeptName;
	private String managerId;
	
	protected String createBy;	// 创建者
	protected Date createTime;	// 创建时间
	protected String modifyBy;	// 更新者
	protected Date modifyTime;	// 更新时间
	
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getCityIdTemp() {
		return cityIdTemp;
	}

	public void setCityIdTemp(String cityIdTemp) {
		this.cityIdTemp = cityIdTemp;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSalesDeptName() {
		return salesDeptName;
	}

	public void setSalesDeptName(String salesDeptName) {
		this.salesDeptName = salesDeptName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel == null ? null : customerTel.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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

    public String getSalesDept() {
        return salesDept;
    }

    public void setSalesDept(String salesDept) {
        this.salesDept = salesDept == null ? null : salesDept.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLendInputDate() {
        return lendInputDate;
    }

    public void setLendInputDate(Date lendInputDate) {
        this.lendInputDate = lendInputDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}