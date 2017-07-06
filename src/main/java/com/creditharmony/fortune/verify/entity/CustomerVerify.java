package com.creditharmony.fortune.verify.entity;

import com.creditharmony.core.persistence.DataEntity;

public class CustomerVerify extends DataEntity<CustomerVerify>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

    private String customerId;

    private String verifyType;

    private String verifyPin;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType == null ? null : verifyType.trim();
    }

    public String getVerifyPin() {
        return verifyPin;
    }

    public void setVerifyPin(String verifyPin) {
        this.verifyPin = verifyPin == null ? null : verifyPin.trim();
    }
}