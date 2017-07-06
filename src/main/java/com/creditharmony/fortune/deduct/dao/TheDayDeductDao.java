package com.creditharmony.fortune.deduct.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;

/**
 * 划扣申请dao
 * @Class Name DeductApplyDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface TheDayDeductDao extends CrudDao<ThedayDeductPool> {

	/**
	 * 带条件分页查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param entity
	 * @param pageBounds
	 * @return
	 */
	public List<DeductPoolEx> findList(DeductPoolEx entity, PageBounds pageBounds);
	
	/**
	 * 获取划扣申请对象
	 * 2015年12月8日
	 * By 韩龙
	 * @param deductPool
	 * @return
	 */
	public DeductPool getDeductPool(DeductPool deductPool);
	
	/**
	 * 获取划扣分天列表
	 * 2015年12月8日
	 * By 韩龙
	 * @param ThedayDeductPool
	 * @return
	 */
	public  List<ThedayDeductPool> findAllList(ThedayDeductPool tdp);
	
	/**
	 * 获得今天划扣的金额和
	 * 2016年2月3日
	 * By 周俊
	 * @param deductApplyIdArray
	 * @return
	 */
	public BigDecimal getActualDeductMoneySum(String[] deductApplyIdArray);
	
	/**
	 * 计算今天划扣总金额和出借总金额
	 * 2016年2月4日
	 * By 周俊
	 * @param deductPoolEx
	 * @return
	 */
	public DeductPoolEx getApplyLendMoneyAndActualDeductMoney(DeductPoolEx deductPoolEx);
	
	/**
	 * checkbox 计算金额
	 * 2016年2月4日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public DeductPoolEx checkboxReckonMoney(String[] array);
	
	/**
	 * 根据分天划扣id获得要填充的预约伐扣信息
	 * 2016年2月16日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public DeductBespoke findDeductBespokeByDayDeductId(String id);
	
	/**
	 * 判断是当前划扣是否有划扣处理中和
	 * 二次划扣中的状态（即没有回盘结果）
	 * 2016年2月2日
	 * By 韩龙	
	 * @param tdp
	 * @return
	 */
	public List<ThedayDeductPool> checkProcessingStatus(Map<String,Object> map);

	/**
	 * 分天线上划扣
	 * 2016年2月25日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public BaseExportModel getBaseExportModel(Map<String, Object> map);

	/**
	 * 获取出借编号
	 * 2016年3月17日
	 * By 韩龙
	 * @param map1
	 * @return
	 */
	public Map<String, String> getTheDaysById(Map<String, Object> map);
	
	/**
	 * 分天划扣排它
	 * 2016年4月29日
	 * By 韩龙
	 * @param thedayDeductPool
	 * @return
	 */
	public ThedayDeductPool getForUpdate(ThedayDeductPool thedayDeductPool);
	
	/**
	 * 分天划扣确认失败
	 * 2016年4月29日
	 * By 韩龙
	 * @param thedayDeductPool
	 * @return
	 */
	public List<ThedayDeductPool> findListForUpdate(ThedayDeductPool thedayDeductPool);
	
	/**
	 * 查询最后一天分天划扣记录
	 * 2016年6月4日
	 * By 韩龙
	 * @param thedayDeductPool
	 * @return
	 */
	public ThedayDeductPool getLastThedayDeductPool(ThedayDeductPool thedayDeductPool);
}