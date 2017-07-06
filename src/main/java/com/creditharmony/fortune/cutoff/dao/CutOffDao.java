package com.creditharmony.fortune.cutoff.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.cutoff.entity.CutOff;

/**
 * 截单DAO
 * 
 * @Class Name CutOffDao
 * @author 周树华
 * @Create In 2016年2月1日
 */
@FortuneBatisDao
public interface CutOffDao extends CrudDao<CutOff> {
	/**
	 * 删除 2016年1月4日
	 * 
	 * @author 周树华
	 * @param applyId
	 * @return int
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 插入 2016年1月4日
	 * 
	 * @author 周树华
	 * @param record
	 * @return int
	 */
	public int insertCutOff(CutOff record);

	/**
	 * 更新 2016年1月4日
	 * 
	 * @author 周树华
	 * @param record
	 * @return int
	 */
	public int updateByPrimaryKeySelective(CutOff record);
	
	/**
	 * 获取所有选择的门店
	 * 2016年2月3日
	 * By 何军
	 * @param record
	 * @return
	 */
	public List<String> findOrgIdList();
	
	/**
	 * 跟新
	 * 2016年3月5日
	 * By 周俊
	 * @param cutOff
	 */
	public void updateEdit(CutOff cutOff);

}