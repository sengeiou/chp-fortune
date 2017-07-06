package com.creditharmony.fortune.deduct.remote;

import java.util.List;

import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;


/**
 * 财富划扣结果返回service
 * @Class Name DeductResultService
 * @author 韩龙
 * @Create In 2016年1月31日
 */
public interface DeductResultServiceMq {
	
	/**
	 * 划扣结果返回单个结果接口
	 * 2016年1月31日
	 * By 韩龙
	 * @param fortuneDeductResult
	 * @return
	 */
	public boolean execute(FortuneDeductResult fortuneDeductResult);
	
	/**
	 * 划扣结果返回结果集合
	 * 2016年1月31日
	 * By 韩龙
	 * @param fortuneDeductResultList
	 * @return
	 */
	public void executeBatch(List<FortuneDeductResult> fortuneDeductResultList);
	
}
