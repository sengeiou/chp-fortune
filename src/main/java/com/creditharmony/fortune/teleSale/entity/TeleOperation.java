package com.creditharmony.fortune.teleSale.entity;

import com.creditharmony.core.persistence.DataEntity;


public class TeleOperation extends DataEntity<TeleOperation> {

	private static final long serialVersionUID = -2425233967354233289L;

	private String customerId;

    private String oldManagerId;

    private String newManagerId;

    private String operatorId;

    private String operationType;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getOldManagerId() {
        return oldManagerId;
    }

    public void setOldManagerId(String oldManagerId) {
        this.oldManagerId = oldManagerId == null ? null : oldManagerId.trim();
    }

    public String getNewManagerId() {
        return newManagerId;
    }

    public void setNewManagerId(String newManagerId) {
        this.newManagerId = newManagerId == null ? null : newManagerId.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }
}