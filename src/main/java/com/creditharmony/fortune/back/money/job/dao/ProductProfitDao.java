package com.creditharmony.fortune.back.money.job.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 收益调整对应Dao 
 * @Class Name ProductProfitDao
 * @author 陈广鹏
 * @Create In 2016年12月28日
 */
@FortuneBatisDao
public interface ProductProfitDao extends CrudDao<ProductProfit> {

	ProductProfit getByLoanApply(LoanApply loanApply);

}
