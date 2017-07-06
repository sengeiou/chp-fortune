package com.creditharmony.fortune.back.money.history.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 回款已办Service
 * @Class Name HistoryManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class HistoryManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BmManager bmManager;
	
	
	/**
	 * 已回款退回
	 * 2016年1月11日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void returnBackmoney(ResultView view) {
		String userId = UserUtils.getUserId();
		Date date = new Date();

		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setLendCode(view.getLendCode());
		pool.setVerTime(view.getVerTime());
		pool.setDictBackStatus(BackState.YHKTH.value);
		pool.setDictBackMoneyError(view.getCheckExamine());
		pool.setRebackFlag(YoN.SHI.value);
		pool.setSuccessMoney(BigDecimal.ZERO);
		pool.setFailMoney(BigDecimal.ZERO);
		
		BackMoneyPool p = detailDao.getBackMoneyPoolById(pool.getBackmId());
		if (YoN.SHI.value.equals(p.getIsSupplemented())) {
			// 补息数据流转，交换金额字段
			pool.setBackActualbackMoney(p.getSupplementedMoney());
			pool.setSupplementedMoney(p.getBackActualbackMoney());
		}
		pool.preUpdate();
		
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(view.getBackmId());
		log.setDictBackmStatus(BackState.YHKTH.value);
		log.setCheckExaminetype(YoN.FOU.value);
		log.setCheckExamine(view.getCheckExamine());
		log.setCheckReason(view.getCheckReason());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		// 1.更新回款状态
		int uCount = poolDao.update(pool);
		if (uCount==0) {
			BmUtils.throwDataOutOfDateException();
		}
		
		// 2.插入审批记录
		logDao.insert(log);
		
		// 3.更新出借申请表完结状态
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(view.getLendCode());
		loanApply.setApplyEndStatus(FinishState.WWJ.value);
		loanApply.preUpdate();
		detailDao.updateLoanApply(loanApply);
		
		// 更新客户出借状态
		LoanApply la = detailDao.getLoanApply(loanApply);
		la.setApplyEndStatus(FinishState.WWJ.value);
		int count = detailDao.countLendingApply(la);
		if (count>0) {
			// 如果未完成出借数量大于0，客户出借状态更新为在投
			Customer customer = new Customer();
			customer.setCustCode(la.getCustCode());
			customer.setCustLending(InvestState.ZT.value);
			customer.preUpdate();
			customerDao.updateApplyLending(customer);
		}
		
		// 4.全程留痕
		bmManager.insertCheck(pool, log);
	}

}
