package com.creditharmony.fortune.back.money.job.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.job.entity.ModifyData;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;
import com.creditharmony.fortune.back.money.job.entity.ex.ProductProfitEx;
import com.creditharmony.fortune.common.entity.Check;

/**
 * 回款金额修正DAO<br/>
 * @Class Name BackMoneyFixDao<br/>
 * @author 陈广鹏<br/>
 * @Create In 2016年12月22日
 */
@FortuneBatisDao
public interface BackMoneyFixDao extends CrudDao<ProductProfit> {

	/**
	 * 根据收益调整信息更新数据<br/>
	 * 2016年12月22日<br/>
	 * By 陈广鹏<br/>
	 * @param profit
	 */
	public int updateByProfitConfig(ProductProfit profit);
	
	/**
	 * <p>根据乘数因子和产品编号更新回款金额，<br/>
	 * 公式：回款金额=出借金额*乘数因子/100 + 出借金额</p>
	 * 2016年12月22日<br/>
	 * By 陈广鹏<br/>
	 * @param map
	 */
	public int updateByFactor(Map<String, Object> map);

	/**
	 * 获取需要更新的数据 
	 * 2016年12月23日
	 * By 陈广鹏
	 * @param searchBean
	 * @return
	 */
	public List<ModifyData> getDataList(ProductProfitEx searchBean);

	/**
	 * 根据出借编号更新数据
	 * 2016年12月26日
	 * By 陈广鹏
	 * @param pool
	 */
	public int updateByLendCode(BackMoneyPool pool);

	/**
	 * 获取执行标识
	 * 2016年12月27日
	 * By 陈广鹏
	 * @return
	 */
	public Check getCheckFlag();
	
	/**
	 * 更新执行标识
	 * 2016年12月27日
	 * By 陈广鹏
	 * @return
	 */
	public int updateCheckFlag();
	
	

}
