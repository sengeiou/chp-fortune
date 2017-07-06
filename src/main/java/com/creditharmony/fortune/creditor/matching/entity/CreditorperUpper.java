package com.creditharmony.fortune.creditor.matching.entity;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 债权人借款上限实体
 * @Class Name CreditorperUpper
 * @author 柳慧
 * @Create In 2016年1月27日
 */
public class CreditorperUpper extends DataEntity<CreditorperUpper> {

	private static final long serialVersionUID = 1L;
	
    private String dictLoanType;

    private String proofType;

    private BigDecimal upperMoney;
    
    private String proofTypeStr;
    
    private String dictLoanTypeStr;
    
    private List<String> dictLoanTypes ;
    
    private List<String> proofTypes;
    
    private String useFlag;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType == null ? null : proofType.trim();
    }

    public BigDecimal getUpperMoney() {
        return upperMoney;
    }

    public void setUpperMoney(BigDecimal upperMoney) {
        this.upperMoney = upperMoney;
    }

	public String getProofTypeStr() {
		return ProfType.parseByCode(proofType).getName();
	}

	public void setProofTypeStr(String proofTypeStr) {
		this.proofTypeStr = proofTypeStr;
	}

	public String getDictLoanTypeStr() {
		return LoanType.parseByCode(dictLoanType).getName();
	}

	public void setDictLoanTypeStr(String dictLoanTypeStr) {
		this.dictLoanTypeStr = dictLoanTypeStr;
	}

	public List<String> getDictLoanTypes() {
		return dictLoanTypes;
	}

	public void setDictLoanTypes(List<String> dictLoanTypes) {
		this.dictLoanTypes = dictLoanTypes;
	}

	public List<String> getProofTypes() {
		return proofTypes;
	}

	public void setProofTypes(List<String> proofTypes) {
		this.proofTypes = proofTypes;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
    
    
   
}