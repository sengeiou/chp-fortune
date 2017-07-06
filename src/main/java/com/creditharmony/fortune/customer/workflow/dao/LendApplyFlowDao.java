package com.creditharmony.fortune.customer.workflow.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyFlow;

/**
 * 出借流程实体数据操作类
 * 
 * @Class Name LendApplyFlowDao
 * @author 孙凯文
 * @Create In 2016年1月4日
 */
@FortuneBatisDao
public interface LendApplyFlowDao extends CrudDao<LendApplyFlow> {
	
	/**
	 * 2016年6月29日
	 * @author 孙凯文
	 * @param params
	 * @return
	 */
	public LendApplyFlow getFlow(Map<String, Object> params);
	/**
	 * 删除 2016年1月4日
	 * 
	 * @author 孙凯文
	 * @param applyId
	 * @return int
	 */
	public int deleteByPrimaryKey(String applyId);

	/**
	 * 插入 2016年1月4日
	 * 
	 * @author 孙凯文
	 * @param record
	 * @return int
	 */
	public int insertSelective(LendApplyFlow record);

	/**
	 * 更新 2016年1月4日
	 * 
	 * @author 孙凯文
	 * @param record
	 * @return int
	 */
	public int updateByPrimaryKeySelective(LendApplyFlow record);
	
	public void updateLendCode(@Param("oldLendCode")String oldLendCode, @Param("newLendCode")String newLendCode);

}