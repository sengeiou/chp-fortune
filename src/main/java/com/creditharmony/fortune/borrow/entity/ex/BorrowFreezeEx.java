package com.creditharmony.fortune.borrow.entity.ex;

import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;

/**
 * 封装债权冻结实体
 * @Class Name BorrowFreezeEx
 * @author 周俊
 * @Create In 2015年12月10日
 */
public class BorrowFreezeEx extends Borrow{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	// 债权持有比例
	private String ratio;
	public String getRatio() {
		ratio = ReckonUtils.percentage(getLoanCreditValue(),getLoanQuota());
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	
}
