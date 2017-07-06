package com.creditharmony.fortune.deduct.balance.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductBalanceManager extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private CheckManager checkManager;

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private LoanApplyDao loanApplyDao;

	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;

	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private ContractManager contractManager;
	
//	@Autowired
//	private CommonFacade commonFacade;
//	
//	@Autowired
//	private SendTripleInfoFacade sendTripleInfoFacade;

	/**
	 * 划扣结算-->办理提交 2015年12月9日 By 韩龙
	 * 
	 * @param filter
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int approve(Map<String, Object> filter) {
		Contract contract = new Contract();
		int result = 0;
		// 根据出借编号获取对像
		String lendCode = (String) filter.get("applyCode");
		String verTime = (String) filter.get("verTime");
		DeductPool deductPool = new DeductPool();
		deductPool.setApplyCode(lendCode);
		deductPool.setVerTime(verTime);
		deductPool = super.dao.getForUpdate(deductPool);
		if(deductPool == null){
			logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理");
			return -999;
		}
		String statusLog = "";
		// 划扣结算与划扣失败办理提交功能
		// 划扣失败原因 failMoney
		deductPool.setFailReason((String) filter.get("confirmOpinion"));
		deductPool.setDictDeductStatus(filter.get("dictDeductStatus") + "");
		statusLog = DeductState.getDeductState(filter.get("dictDeductStatus")
				+ "");

		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		// 查询对应出借信息
		loanApply = loanApplyDao.get(loanApply);
		// 计算实际划扣金额
		String money = filter.get("actualDeductMoney") != null ? filter.get(
				"actualDeductMoney").toString() : "0";
		if (filter.get("dictDeductStatus").equals(DeductState.HKCG.value)) {
			// 计算实际划扣金额
			deductPool.setActualDeductMoney(deductPool.getLoanMoney());
			// 失败金额
			deductPool.setFailMoney(loanApply.getLendMoney()
					.subtract(new BigDecimal(money)).toString());
			deductPool.setDealTime(DateUtils.getDate("yyyy-MM-dd"));
//			deductPool.preUpdate();
//			result = super.dao.update(deductPool);
			// 计算回款金额
			MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			matchingCreditorEx.setLendCode(lendCode);
			matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
			matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			BigDecimal backMoney = creditorAidManager
					.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
			// 回款池插入数据
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.preInsert();
			backMoneyPool.setBackmId(backMoneyPool.getId());
			backMoneyPool.setLendCode(lendCode);
			backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
			backMoneyPool.setBackMoneyType(BackType.DQHK.value);
			backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
			backMoneyPool.setBackMoneyDay(DeductUtils
					.getNextWorkingDay(loanApply.getExpireDate()));
			backMoneyPool.setBackMoney(backMoney);
			backMoneyPool.setBackActualbackMoney(backMoney);
			backMoneyPool.setRebackFlag(YoN.FOU.value);
			backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
			// 更新出借状态
			loanApply.setLendStatus(LendState.HKCG.value);
			loanApply.setStatus(ForApplyStatus.CJZ.value);
			// 插入
			backMoneyPoolDao.insert(backMoneyPool);
			// 合同使用时间
			contract.setLendCode(lendCode);
			contract.setContUseDay(new Date());
			contract.preUpdate();
			contractManager.updateContractUseDay(contract);
			
		} else {
			BigDecimal actualDeductMoney = loanApply.getLendMoney().subtract(
					new BigDecimal(money));
			deductPool.setActualDeductMoney(actualDeductMoney.toString());
			// 失败金额
			deductPool.setFailMoney(money);
			deductPool.setDealTime(DateUtils.getDate("yyyy-MM-dd"));
			// 更新出借状态
			loanApply.setLendStatus(LendState.HKSB.value);
		}
		loanApply.setRealDeductTime(new Date());
		loanApply.preUpdate();
		loanApplyDao.update(loanApply);
		deductPool.preUpdate();
		result = super.dao.update(deductPool);
		// 全程流痕
//		checkManager.addCheck(deductPool.getApplyCode(), "划扣结算与划扣失败办理提交功能",
//				statusLog);
		checkManager.addCheck(deductPool.getApplyCode(), "划扣结算与划扣失败办理提交功能",
				statusLog,deductPool.getDeductApplyId(),FortuneLogNode.DEDUCT_APPROVE);
		return result;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param dp
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void batchDeductUpdate(DeductPool dp) {
		String lendCode = dp.getApplyCode();
		DeductPool pool= new DeductPool();
		pool = dao.getForUpdate(dp);
		if(pool == null){
			logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理");
			throw new ServiceException("业务编号【"+lendCode+"】已被其它业务人员处理");
		}
		BigDecimal sMoney = new BigDecimal(DeductUtils.isNullMoney(dp.getLoanMoney()));
		if(DeductState.HKCG.value.equals(dp.getDictDeductStatus())){
			dp.setFailMoney("0000");
			dp.setActualDeductMoney(sMoney.toString());
			deductManager.saveBackMoney(dp);
		}else{
			String content = DeductState.getDeductState(dp
					.getDictDeductStatus());
			// 如果是失败则更新失败金额
			dp.setFailMoney(sMoney.toString());
			dp.setActualDeductMoney("0000");
			// 全程流痕
//			checkManager.addCheck(dp.getApplyCode(), "状态修改"+content+"成功", content);
			checkManager.addCheck(dp.getApplyCode(), "状态修改"+content+"成功", content,
					pool.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
		}
		dp.preUpdate();
		dao.update(dp);
	}

	/**
	 * 获取划扣申请对象 2015年12月8日 By 韩龙
	 * 
	 * @param deductPool
	 * @return
	 */
	public DeductPool getDeductPool(DeductPool deductPool) {
		return dao.getDeductPool(deductPool);
	}
}
