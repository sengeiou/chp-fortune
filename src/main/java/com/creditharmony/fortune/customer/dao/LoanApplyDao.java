package com.creditharmony.fortune.customer.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.look.apply.view.PriorityResultView;

/**
 * @Class Name LoanApplyDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface LoanApplyDao extends CrudDao<LoanApply> {


	/**
	 * 根據客户编号查询笔数 2015年12月23日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return
	 */
	public int count(@Param("customerCode") String customerCode);

	/**
	 * 根据id获取产品实体 2016年1月7日
	 * 
	 * @author 孙凯文
	 * @param id
	 * @return
	 */
	public Product getProduct(@Param("code") String code);

	/**
	 * 获取出借列表 2016年2月16日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return List<LoanApply>
	 */
	public List<LoanApply> getLoanApplyList(
			@Param("customerCode") String customerCode);

	/**
	 * 获取出借申请信息 2016年2月17日
	 * 
	 * @author 孙凯文
	 * @param lendCode
	 */
	public LoanApply getLoanApplyByCode(@Param("lendCode") String lendCode);

	/**
	 * 获取产品月利率 2016年2月17日
	 * 
	 * @author 孙凯文
	 * @param Map
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductRate(Map map);

	/**
	 * 获取产品销售折扣率 2016年2月20日
	 * 
	 * @author 孙凯文
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getProductDiscountRate(Map map);

	/**
	 * 获取最近出借信息 2016年2月23日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 */
	public List<LoanApply> loadAllLendApply(
			@Param("customerCode") String customerCode);
	
	public LoanApply getLastestLend(
			@Param("customerCode") String customerCode);
	
	/**
	 * 更新出借状态
	 * 
	 * @author 朱杰
	 * @param loanApply
	 */
	public void updateLendStatus(LoanApply loanApply);
	
	public void deleteLendApply(@Param("lendCode")String lendCode);
	
	public String getProductVersion(String productCode);

	


	public void updateApplyInOutAccountId(Map<String, Object> upApplyParam);

	/**
	 * 获取合同编码
	 * 2016年4月25日
	 * By 郭才林
	 * @return
	 */
	public String getContractCode();

	/**
	 * 更新合同编号
	 * 2016年4月25日
	 * By 郭才林
	 * @param lendApply
	 */
	public void updateContractCode(LoanApply lendApply);
	
	public void updateLendCode(@Param("oldLendCode")String oldLendCode, @Param("newLendCode")String newLendCode);

	public BigDecimal getSurplusAmount(@Param("lendCodeParent")String lendCodeParent);
	
	
	/**
	 * 增加优先回款记录
	 * 2017年3月30日
	 * 郭强
	 * @param prview
	 * @return
	 */
	public int addPriorityAlone(PriorityResultView prview);

	/**
	 * 更新优先回款的状态
	 * @param prview
	 * @return 
	 */
	public int updatePriority(PriorityResultView prview);

	/**
	 * 更新附件
	 * @param map
	 */
	public void updateFile(Map<String, Object> map);
	
	/**
	 * 撤销是删除附件
	 */
	public void deleteFile(Map<String, Object> map);

}
