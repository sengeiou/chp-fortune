package com.creditharmony.fortune.triple.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntInvestBean;

@FortuneBatisDao
public interface TripleInvestMapper extends CrudDao<IntInvestBean>{
	
	public int insertIntInvest(IntInvestBean bean);
	
	public IntInvestBean findLoanInfo(@Param("lendCode")String lendCode);
	
	public String getFirstOrderStatus(@Param("customerCode")String customerCode);
	
	public String getRealBackInterest(@Param("lendCode")String lendCode);
	
	public String getExpBackMoney(@Param("lendCode")String lendCode);
	
	public Map<String, String> getManagerInfo1(@Param("loginName")String loginName);
	
	public Map<String, String> getManagerInfo2(@Param("userCode")String userCode);
	
	public List<IntInvestBean> getInvestByIdOpOs(@Param("lendCode")String lendCode,@Param("operation")String operation
			,@Param("osType")String osType,@Param("status")String status);
}
