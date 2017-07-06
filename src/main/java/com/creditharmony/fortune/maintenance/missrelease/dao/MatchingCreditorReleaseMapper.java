package com.creditharmony.fortune.maintenance.missrelease.dao;

import java.util.List;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.maintenance.missrelease.entity.ReleaseCreditorInfo;

/**
 * 待债权推荐信息表
 * @Class Name MatchingCreditorReleaseMapper
 * @author
 * @Create In 2015年12月23日
 */
@FortuneBatisDao
public interface MatchingCreditorReleaseMapper {
	
    
    /**
     * 查询 出借申请表\待债权推荐信息表\债权交易表：出借状态=划扣成功,出借编号=lendCode,产品!=月满盈 的出借，
     * 开始期数<=当前期数<=截止期数，债权状态=已推荐
     * 
     * @param releaseCreditorInfo
     * @return List<ReleaseCreditorInfo>
     */
    public List<ReleaseCreditorInfo> selReleaseCreditorInfos(ReleaseCreditorInfo releaseCreditorInfo);
    
    /**
     * 查询 出借申请表\待债权推荐信息表：出借状态=划扣成功,出借编号=lendCode,产品!=月满盈 的出借，
     * 开始期数<=当前期数<=截止期数，债权状态!=撤销
     * 
     * @param releaseCreditorInfo
     * @return List<ReleaseCreditorInfo>
     */
    public List<ReleaseCreditorInfo> selRlCreditorInfos(ReleaseCreditorInfo releaseCreditorInfo);

	/**
	 * 根据ID跟新数据
	 * 2016年1月11日
	 * By
	 * @param releaseCreditorInfo
	 * @return int
	 */
	public int updateMatchingCreditorInfo(ReleaseCreditorInfo releaseCreditorInfo);
}