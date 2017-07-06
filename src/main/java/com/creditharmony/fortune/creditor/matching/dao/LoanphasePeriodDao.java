package com.creditharmony.fortune.creditor.matching.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.LoanphasePeriod;

/**
 * 分期收益期数Dao
 * @Class Name LoanphasePeriodDao
 * @author 柳慧
 * @Create In 2016年6月11日
 */
@FortuneBatisDao
public interface LoanphasePeriodDao  extends CrudDao<LoanphasePeriod> {
    int deleteByPrimaryKey(String id);

    int insert(LoanphasePeriod record);

    int insertSelective(LoanphasePeriod record);

    LoanphasePeriod selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanphasePeriod record);

    int updateByPrimaryKey(LoanphasePeriod record);
    
    /**
     * 通过待推荐编号获取该实体
     * 2016年6月11日
     * By 柳慧
     * @param matchingId
     * @return
     */
    LoanphasePeriod selectByMatchingId(String matchingId);
    
    /**
     * 通过出借编号获取该笔出借的最大期数实体
     * 2016年6月11日
     * By 柳慧
     * @param lendCode
     * @return
     */
    
    LoanphasePeriod getLastPeriodByLendCode (String lendCode);

	void deleteByMatchingId(String matchingId);
    
}