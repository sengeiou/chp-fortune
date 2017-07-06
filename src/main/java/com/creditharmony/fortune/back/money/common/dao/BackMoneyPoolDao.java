package com.creditharmony.fortune.back.money.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;

/**
 * 回款池对应Dao
 * @Class Name BackMoneyPoolDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface BackMoneyPoolDao extends CrudDao<BackMoneyPool> {
	
	/**
	 * 根据出借编号获取回款ID（封装在对象中）
	 * 2015年12月19日
	 * By 陈广鹏
	 * @param lendCode
	 * @return 
	 */
	public BackMoneyPool getByLendCode(String lendCode);
	
	/**
	 * 根据回款ID获取出借编号（封装在对象中）
	 * 2016年2月18日
	 * By 陈广鹏
	 * @param backmId
	 * @return
	 */
	public BackMoneyPool getByBackmId(String backmId);

	/**
	 * 获取回款ID对应的当前状态
	 * 2015年12月28日
	 * By 陈广鹏
	 * @param backmId
	 * @return
	 */
	public String getStatus(String backmId);

	/**
	 * 根据出借编号更新
	 * 2016年1月6日
	 * By 陈广鹏
	 * @param pool
	 */
	public int updateByLendCode(BackMoneyPool pool);

	/**
	 * 查询当前回盘结果
	 * 2016年2月4日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public String getBackResult(String lendCode);

	/**
	 * 查询出借的赎回状态
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public String getRedeemStatus(String lendCode);

	/**
	 * 回款表锁行用
	 * 2016年4月28日
	 * By 陈广鹏
	 * @param pool
	 * @return
	 */
	public BackMoneyPool forUpdate(BackMoneyPool pool);

	/**
	 * 获取需要更新渠道标识的数据
	 * 2016年6月20日
	 * By 陈广鹏
	 * @param im
	 * @return
	 */
	public BackMoneyPool getUpdateChannelPool(ListItemView view);

	/**
	 * 获取回款对应的Bean
	 */
	public  BackMoneyPool  getSuplementList(String   backmID);

	/**
	 * 修改回款在职状态
	 */
	public int updateWorkingState(ListItemView  list);

	/**
	 * 检验优先回款申请的状态
	 * @param backmId
	 * @return
	 */
	public PriorityDetailItem searchPriorityItem(String backmId);
}
