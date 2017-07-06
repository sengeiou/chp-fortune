package com.creditharmony.fortune.creditor.matching.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.LoanphaseCur;

@FortuneBatisDao
public interface LoanphaseCurDao extends CrudDao<LoanphaseCur>{
	public int deleteByPrimaryKey(String phaseId);

    public int insert(LoanphaseCur record);

    public int insertSelective(LoanphaseCur record);

    public LoanphaseCur selectByPrimaryKey(String phaseId);

    public int updateByPrimaryKeySelective(LoanphaseCur record);

    public int updateByPrimaryKey(LoanphaseCur record);
    
    public int deleteByLendCode(String lendCode);
}