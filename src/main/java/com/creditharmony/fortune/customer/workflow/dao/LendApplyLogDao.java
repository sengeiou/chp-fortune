package com.creditharmony.fortune.customer.workflow.dao;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;

/**
 * 流程日志数据操作类
 * 
 * @Class Name LendApplyLogDao
 * @author 孙凯文
 * @Create In 2016年1月8日
 */
@FortuneBatisDao
public interface LendApplyLogDao extends CrudDao<LendApplyLog> {
	/**
	 * 根据ID删除 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 插入日志 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param record
	 * @return
	 */
	public int insertSelective(LendApplyLog record);

	/**
	 * 更新日志 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(LendApplyLog record);
	
	public void updateLendCode(@Param("oldLendCode")String oldLendCode, @Param("newLendCode")String newLendCode);

}