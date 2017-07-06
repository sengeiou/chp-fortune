package com.creditharmony.fortune.maintenance.settles.dao;

import java.util.List;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.maintenance.settles.entity.LoanphaseInfo;

/**
 * 分期收益-Dao
 * 
 * @Class Name LoanphaseForMtMapper
 * @author
 * @Create In 2015年12月22日
 */
@FortuneBatisDao
public interface LoanphaseForMtMapper {
    
    /**
     * 取得分期收益
     * 
     * @param loanphaseInfo
     * @return List<LoanphaseInfo>
     */
    public List<LoanphaseInfo> selLoanPhase(LoanphaseInfo key);
    
    /**
     * 取得分期收益(group by推荐ID 计算本期应还本金的和及本期应还本息的和)
     * 
     * @param loanphaseInfo
     * @return LoanphaseInfo
     */
    public LoanphaseInfo selSumLoanPhaseInfo(LoanphaseInfo loanphaseInfo);
    
    /**
     * 更新分期收益
     * 
     * @param loanphaseInfo
     * @return
     */
    public int updLoanPhase(LoanphaseInfo loanphaseInfo);
    
    /**
     * 更新分期收益
     * 
     * @param matchingId
     * @return
     */
    public int delLoanPhase(String matchingId);
}