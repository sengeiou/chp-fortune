package com.creditharmony.fortune.back.interest.excute.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.LineBackInterestObj;
import com.creditharmony.fortune.back.interest.entity.LineGoldBackInterestObj;

/**
 * 执行回息
 * @Class Name ExcuteBackInterestDao 
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@FortuneBatisDao
public interface ExcuteBackInterestDao extends CrudDao<BackInterestPool> {
	
	/**
	 * 查找非金账户数据列表
	 * 2016年2月16日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LineBackInterestObj> findList(Map<String, Object> map);
	
	/**
	 * 金账户回息数据
	 * 2016年3月5日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LineGoldBackInterestObj> findGoldAccList(Map<String, Object> map);
	
}