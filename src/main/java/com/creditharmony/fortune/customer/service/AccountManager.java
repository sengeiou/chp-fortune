package com.creditharmony.fortune.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.change.lend.apply.dao.BranchBankCodeDao;
import com.creditharmony.fortune.change.lend.apply.entity.BranchBankCode;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.entity.CustomerAccount;

/**
 * 客户账户管理类
 * @Class Name AccountManager
 * @author 孙凯文
 * @Create In 2015年12月23日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class AccountManager extends
		CoreManager<CustomerAccountDao, CustomerAccount> {
	@Autowired
	private BranchBankCodeDao branchBankCodeDao;

	/**
	 * 保存账户信息
	 * 2016年2月16日
	 * @author 孙凯文
	 * @param entity 
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void save(CustomerAccount entity) {
		entity.preInsert();
		dao.insert(entity);
	}

	/**
	 * 修改账户信息
	 * 2016年2月16日
	 * @author 孙凯文
	 * @param entity 
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void update(CustomerAccount entity) {
		entity.preUpdate();
		dao.update(entity);
	}

	/**
	 * 获取账户数量
	 * 2016年2月16日
	 * @author 孙凯文
	 * @param bankId
	 * @param status
	 * @return 
	 */
	public int countStatus(String bankId, String status) {
		return dao.countStatus(bankId, status);
	}
	
	/**
	 * 根据客户、卡号，查询是否已存在此卡信息
	 * 2016年3月9日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	public int getCardNoCunt(Map<String, Object> params) {
		return dao.getCardNoCunt(params);
	}
	
	/**
	 * 根据银行卡号查找银行账户
	 * 2016年5月10日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	public List<CustomerAccount> getAccountByCardNo(Map<String, Object> params) {
		return dao.getAccountByCardNo(params);
	}
	
	/**
	 * 逻辑删除银行账户
	 * 2016年5月10日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int logicDelete(Map<String, Object> params) {
		return dao.logicDelete(params);
	}
	
	
	public Page<BranchBankCode> selectBranchCode(Page<BranchBankCode> page, Map<String, Object> params){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<BranchBankCode> pageList = (PageList<BranchBankCode>) branchBankCodeDao
				.selectBranchCode(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

}
