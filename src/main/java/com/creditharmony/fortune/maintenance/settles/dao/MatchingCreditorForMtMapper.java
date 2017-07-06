package com.creditharmony.fortune.maintenance.settles.dao;

import java.util.List;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.maintenance.settles.entity.MatchingCreditorInfo;

/**
 * 待债权推荐信息表
 * @Class Name MatchingCreditorForMtMapper
 * @author
 * @Create In 2015年12月23日
 */
@FortuneBatisDao
public interface MatchingCreditorForMtMapper {
	
    
    /**
     * 查询 出借申请表：出借状态=划扣成功,完结状态=未完结,状态!=提前赎回，产品!=月满盈 
     * 查询 待债权推荐信息表：结算状态=未结算,本期期数=最大期数,当前期数<=共有几期,债权状态=已推荐 的出借
     * 
     * @param key
     * @return List<MatchingCreditorInfo>
     */
    public List<MatchingCreditorInfo> selMatchingInfos(MatchingCreditorInfo key);
    
    
    /**
     * 添加数据
     * 2016年1月13日
     * By
     * @param MatchingCreditorInfo
     * @return none
     */
    public void insMatchingCreditorInfo(MatchingCreditorInfo key);


	/**
	 * 根据ID跟新数据
	 * 2016年1月11日
	 * By
	 * @param MatchingCreditorInfo
	 * @return int
	 */
	public int updateMatchingCreditorInfo(MatchingCreditorInfo matchingCreditor);
}