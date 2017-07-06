package com.creditharmony.fortune.customer.workflow.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;

@FortuneBatisDao
public interface TransferRelationDao extends CrudDao<TransferRelation> {
	int insert(TransferRelation record);

	int update(TransferRelation record);

	List<TransferRelation> findList(HashMap<?, ?> map);
	
	List<TransferRelation> getLendRelationInfo4Apply(Map<String, Object> params);
	
	/**
	 * 查找xxx出借被内转的数量（使用中）
	 * 2016年5月20日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	public int getUsingTransCount(Map<String, Object> params);
	
	/**
	 * 更改内转关系表的状态
	 * 2016年5月20日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	public int updateTransferStatus(Map<String, Object> params);
	
	/**
	 * 根据子编号，删除数据
	 * 2016年5月20日
	 * By 肖长伟
	 * @param lendCodeC
	 * @return
	 */
	public int deleteByChildLendCode(String lendCodeC);

	public int getTransCount(String lendCode);
	
	//判断自转和内转
	int countTransferRelation(Map<String, Object> map);
	BackMoneyPool getByLendCode(String lendCode);
}