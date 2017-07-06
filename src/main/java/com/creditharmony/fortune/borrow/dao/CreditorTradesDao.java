package com.creditharmony.fortune.borrow.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;

/**
 * 债权交易
 * @Class Name CreditorTradeDao
 * @author 周俊
 * @Create In 2015年12月17日
 */
@FortuneBatisDao
public interface CreditorTradesDao {
	
	/**
	 * 更新冻结债权的交易
	 * 2015年12月17日
	 * By 周俊
	 * @param map
	 */
	public void updateStatus(Map<String, Object> map);
	
	/**
	 * 保存新的债权交易
	 * 2015年12月18日
	 * By 周俊
	 * @param creditorTrade
	 */
	public void insert(CreditorTrade creditorTrade);
	
	/**
	 * 获得债权id
	 * 2015年12月22日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<String> findCreditValueId(Map<String, Object> map);
	
	/**
	 * 根据交易id获得交易情况
	 * 2015年12月23日
	 * By 周俊
	 * @param tradeId
	 * @return
	 */
	public CreditorTrade get(Map<String, Object> map);
	
	/**
	 * 根据主键更新
	 * 2015年12月23日
	 * By 周俊
	 * @param creditorTrade
	 */
	public void updateByPrimaryKey(CreditorTrade creditorTrade);
	
	/**
	 * 获得主键集合
	 * 2015年12月24日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<CreditorTrade> getListBytradeId(Map<String, Object> map);
	
}
