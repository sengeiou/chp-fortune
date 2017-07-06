package com.creditharmony.fortune.creditor.matching.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.TempAutoMatchingCreditor;

/**
 * 自动匹配待债权推荐信息表
 * @Class Name TempAutoMatchingCreditorDao
 * @author 柳慧
 * @Create In 2016年5月7日
 */
@FortuneBatisDao
public interface TempAutoMatchingCreditorDao extends CrudDao<TempAutoMatchingCreditor>  {
	public int deleteByPrimaryKey(String matchingId);

	public int insert(TempAutoMatchingCreditor record);

	public int insertSelective(TempAutoMatchingCreditor record);

	public TempAutoMatchingCreditor selectByPrimaryKey(String matchingId);

	public int updateByPrimaryKeySelective(TempAutoMatchingCreditor record);

	public  int updateByPrimaryKey(TempAutoMatchingCreditor record);
	 public int deleteAll();
	 
	 public Integer getcount();
}