package com.creditharmony.fortune.back.money.common.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ext.SmsInfoEx;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 处理详情页的Dao
 * @Class Name DetailDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface DetailDao extends CrudDao<ItemView> {
	
	/**
	 * 更新出借申请的完结状态
	 * 2015年12月14日
	 * By 陈广鹏
	 * @param loanApply
	 */
	public void updateLoanApply(LoanApply loanApply);

	/**
	 * 根据回款ID获取出借编号
	 * 2016年1月11日
	 * By 陈广鹏
	 * @param backmId
	 * @return
	 */
	public BackMoneyPool getBackMoneyPoolById(String backmId);

	/**
	 * 根据回款ID获取短信发送相关信息
	 * 2016年1月20日
	 * By 陈广鹏
	 * @param backmId
	 * @return
	 */
	public SmsInfoEx getSmsInfoEx(String backmId);

	/**
	 * 查询回款审批通过次数
	 * 2016年1月20日
	 * By 陈广鹏
	 * @param pool
	 * @return
	 */
	public int countSendRecord(BackMoneyPool pool);

	/**
	 * 获取客户状态为出借中的出借数量
	 * 2016年2月22日
	 * By 陈广鹏
	 * @param custCode
	 * @return
	 */
	public int countLendingApply(LoanApply loanApply);

	/**
	 * 根据出借编号获取客户Code
	 * 2016年2月22日
	 * By 陈广鹏
	 * @param loanApply
	 * @return
	 */
	public LoanApply getLoanApply(LoanApply loanApply);

	/**
	 * 获取指定出借编号内转关系不为某状态的内转数量
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param map
	 * @return
	 */
	public int countTransferRelation(Map<String, Object> map);
	
	/**
	 * 获取 指定出借申请 内转关系的子出借是 自转还是内转
	 * @author xurongsheng
	 * @date 2017年2月9日 下午4:51:11
	 * @param map
	 * @return
	 */
	public Map<String, Object> lendPayTransferRelation(Map<String, Object> map);
	
	/**
	 * 获取客户上次回款的回款平台ID
	 * 2016年5月16日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public String getPreviousBackPlatform(Map<String, String> map);

}
