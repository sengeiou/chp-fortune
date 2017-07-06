package com.creditharmony.fortune.deduct.approve.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.DeductUtils.TheDayEnum;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.creditharmony.fortune.template.entity.DeductPoolExportModel;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductApproveManager extends CoreManager<DeductApplyDao, DeductPool> {
	
	@Autowired
	private DeductManager deductManager;

	@Autowired
	private CheckManager checkManager;

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private LoanApplyDao loanApplyDao;

	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;

	@Autowired
	private CustomerAccountDao customerAccountDao;

	@Autowired
	private TheDayDeductDao theDayDeductDao;

	/**
	 * 批量划扣状态修改 2015年12月7日 By 韩龙
	 * 
	 * @param applyCodes
	 * @param deductFalg
	 * @param batchId
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int batchDeductApplyUpdate(DeductPool dp) {
		logger.info("批量划扣审批------开始");
		String code = dp.getApplyCode();
		int result = 0;
		// 循环处理更新
		logger.info("获取deductPool对象出借编号：" + code);
		// 获取deductPool对象
		DeductPool deductPool = new DeductPool();
		deductPool.setApplyCode(code);
		deductPool = super.dao.get(deductPool);
		// 获取出借对象
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(code);
		loanApply = loanApplyDao.get(loanApply);
		loanApply.setLendStatus(LendState.HKCLZ.value);
		// 如果为空则是默认一天
		int conunt = loanApply.getApplyDeductDays() == null ? 1 : loanApply
				.getApplyDeductDays();
		if (conunt > 1) {
			// 获取开户银行信息
			CustomerAccount customerAccount = customerAccountDao.get(loanApply
					.getPaymentBankId());
			String ruleTemp = platformGotoRuleManager.getDeductRule(
					loanApply.getDeductTypeId(),
					customerAccount.getAccountAddrProvince(),
					customerAccount.getAccountBankId());
			// 分天划扣划规则
			String theDayRule = DeductUtils.getDeductRule(ruleTemp);
			// 所要跳转划扣平台的单日限额
			BigDecimal sumMoney = DeductUtils.getMoney(ruleTemp).divide(
					new BigDecimal(100));
			// 划扣金额
			BigDecimal lendMoney = loanApply.getLendMoney();
			BigDecimal loanMoney = loanApply.getLendMoney();
			// 如果大于或小于单天限额，说明一天可以划扣完成不需要分天划扣
			if (sumMoney.compareTo(lendMoney) == 1
					|| sumMoney.compareTo(lendMoney) == 0) {
				deductPool.setDictDeductStatus(DeductState.DHKJS.value);
				// 不分天划扣
				deductPool.setDayDeductFlag(TheDayEnum.DBHK.value);
			} else {
				// 分天划扣标识
				deductPool.setDayDeductFlag(TheDayEnum.FYHK.value);
				deductPool.setDictDeductStatus(DeductState.DFTHK.value);
				int index = 0;
				Calendar c = Calendar.getInstance();
				do {
					c.setTime(new Date());
					c.add(Calendar.DAY_OF_MONTH, index);
					ThedayDeductPool tdp = new ThedayDeductPool();
					tdp.preInsert();
					tdp.setDeductApplyId(deductPool.getDeductApplyId());
					tdp.setDictDeductStatus(DeductState.DFTHK.value);
					tdp.setJumpAmount(BigDecimal.ZERO);
					tdp.setJumpRule(theDayRule);
					tdp.setLoanMoney(loanMoney);
					if (sumMoney.compareTo(lendMoney) == 1
							|| sumMoney.compareTo(lendMoney) == 0) {
						tdp.setActualDeductMoney(lendMoney);
						tdp.setDeductValidityDate(c.getTime());
						theDayDeductDao.insert(tdp);
						break;
					} else {
						tdp.setActualDeductMoney(sumMoney);
						tdp.setDeductValidityDate(c.getTime());
						lendMoney = lendMoney.subtract(sumMoney);
						theDayDeductDao.insert(tdp);
					}
					index++;
				} while (true);
			}
		} else {
			deductPool.setDictDeductStatus(DeductState.DHKJS.value);
			// 不分天划扣
			deductPool.setDayDeductFlag(TheDayEnum.DBHK.value);
		}
		// 审批通过更改出借申请状态为划扣处理中
		loanApply.preUpdate();
		loanApplyDao.update(loanApply);
		// 保存deductPool对象
		deductPool.setVerTime(dp.getVerTime());
		deductPool.setConfirmResult(DeductState.DHKJS.value);
		deductPool.preUpdate();
		result = result + super.dao.update(deductPool);
		if(result==0){
			throw new ServiceException("该批量数据更新出错，请详查个别数据是否已有人操作，请勿重复！");
		}
		// 全程流痕
//		checkManager.addCheck(code, "批量" +
//				DeductState.getDeductState(DeductState.DHKSP.value),
//				DeductState.getDeductState(DeductState.DHKSP.value));
		checkManager.addCheck(code, "批量" +
				DeductState.getDeductState(DeductState.DHKSP.value),
				DeductState.getDeductState(DeductState.DHKSP.value),
				deductPool.getDeductApplyId(),FortuneLogNode.DEDUCT_APPROVE);
		logger.info("批量划扣申请<--->批量划扣审批<--->划扣结算==结束");
		return result;
	}

	/**
	 * 导出excel 2015年11月30日 By 韩龙
	 * 
	 * @param deductPoolExt
	 * @return
	 */
	public List<DeductPoolExportModel> findListExportModel(
			DeductPoolEx deductPoolExt) {
		if (deductPoolExt == null) {
			deductPoolExt = new DeductPoolEx();
		}
		// 设置首期标识
		deductPoolExt.setMatchingFirstdayFlag(BillState.SQ.value);
		// 检索出列表
		List<DeductPoolExportModel> deductPoolExportModelList = super.dao
				.getDeductPoolExportModel(deductPoolExt);
		// 判断是否有记录
		if (deductPoolExportModelList == null) {
			return Lists.newArrayList();
		}
		return deductPoolExportModelList;
	}
	
	/**
	 * 划扣审批、划扣结算、划扣失败-->办理提交 2015年12月9日 By 韩龙
	 * 
	 * @param filter
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int approve(Map<String, Object> filter) {
		int result = 0;
		DeductPool deductPool = new DeductPool();
		// 根据出借编号获取对像
		String lendCode = (String) filter.get("applyCode");
		if(lendCode.lastIndexOf("_")>0){
			String applyCodevertime[] = lendCode.split("_");
			 lendCode = applyCodevertime[0];
			 String verTime = applyCodevertime[1];
			 deductPool.setApplyCode(lendCode);
			 deductPool.setVerTime(verTime);
			 deductPool = super.dao.get(deductPool);
			 if(deductPool == null){
				logger.debug("划扣审批失败，该数据是否有人已操作，请详查！");
				return result; 
			 }
		}
		
		
		String statusLog = "";
		deductPool.setConfirmResult((String) filter.get("approveResult"));
		deductPool.setConfirmOpinion((String) filter.get("approveAdvice"));
		// 划扣审批撤消
		if (deductPool.getConfirmResult().equals(DeductState.CX.value)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", lendCode);
			map.put("approve", Constant.APPLY_OR_APPROVE_FALG);
			result = deductManager.batchUpdate(deductPool,map,new StringBuffer());
			statusLog = DeductState.getDeductState(DeductState.CX.value);
			if(result > 0){
				// 全程流痕
				checkManager.addCheck(deductPool.getApplyCode(), "划扣办理提交划扣审批状态",
						statusLog);
			}
			return result;
		} else if (deductPool.getConfirmResult()
				.equals(DeductState.DHKJS.value)) {
			// 留程状态
			statusLog = DeductState.getDeductState(DeductState.DHKSP.value);
			// 获取出借对象
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = loanApplyDao.get(loanApply);
			loanApply.setLendStatus(LendState.HKCLZ.value);
			// 如果为空则是默认一天
			int conunt = loanApply.getApplyDeductDays() == null ? 1 : loanApply
					.getApplyDeductDays();
			if (conunt > 1) {
				// 获取开户银行信息
				CustomerAccount customerAccount = customerAccountDao
						.get(loanApply.getPaymentBankId());
				String ruleTemp = platformGotoRuleManager.getDeductRule(
						loanApply.getDeductTypeId(),
						customerAccount.getAccountAddrProvince(),
						customerAccount.getAccountBankId());
				// 分天划扣划规则
				String theDayRule = DeductUtils.getDeductRule(ruleTemp);
				// 所要跳转划扣平台的单日限额
				BigDecimal sumMoney = DeductUtils.getMoney(ruleTemp).divide(
						new BigDecimal(100));
				// 划扣金额
				BigDecimal lendMoney = loanApply.getLendMoney();
				BigDecimal loanMoney = loanApply.getLendMoney();
				// 如果大于或小于单天限额，说明一天可以划扣完成不需要分天划扣
				if (sumMoney.compareTo(lendMoney) == 1
						|| sumMoney.compareTo(lendMoney) == 0) {
					statusLog = DeductState.getDeductState(DeductState.DHKSP.value);
					// 不分天划扣
					deductPool.setDayDeductFlag(TheDayEnum.DBHK.value);
				} else {
					// 分天划扣标识
					deductPool.setDayDeductFlag(TheDayEnum.FYHK.value);
					deductPool.setDictDeductStatus(DeductState.DFTHK.value);
					// 留程状态
					statusLog = DeductState.getDeductState(DeductState.DFTHK.value);
					int index = 0;
					Calendar c = Calendar.getInstance();
					do {
						c.setTime(new Date());
						c.add(Calendar.DAY_OF_MONTH, index);
						ThedayDeductPool tdp = new ThedayDeductPool();
						tdp.preInsert();
						tdp.setDeductApplyId(deductPool.getDeductApplyId());
						tdp.setDictDeductStatus(DeductState.DFTHK.value);
						tdp.setLoanMoney(loanMoney);
						// 设置跳转金额
						tdp.setJumpAmount(BigDecimal.ZERO);
						// 跳转规则
						tdp.setJumpRule(theDayRule);
						if (sumMoney.compareTo(lendMoney) == 1
								|| sumMoney.compareTo(lendMoney) == 0) {
							tdp.setActualDeductMoney(lendMoney);
							tdp.setDeductValidityDate(c.getTime());
							theDayDeductDao.insert(tdp);
							break;
						} else {
							tdp.setActualDeductMoney(sumMoney);
							tdp.setDeductValidityDate(c.getTime());
							lendMoney = lendMoney.subtract(sumMoney);
							theDayDeductDao.insert(tdp);
						}
						index++;
					} while (true);
				}
			} else {
				statusLog = DeductState.getDeductState(DeductState.DHKSP.value);
				// 不分天划扣
				deductPool.setDayDeductFlag(TheDayEnum.DBHK.value);
			}
			// 审批通过更改出借申请状态为划扣处理中
			loanApply.preUpdate();
			loanApplyDao.update(loanApply);
		} else {
			statusLog = DeductState.getDeductState(DeductState.HKSPTH.value);
		}
		// 设置划扣审批状态
//		String lendCodeA = (String) filter.get("applyCode");
//		String applyCodevertimeA[] = lendCodeA.split("_");
		deductPool.setDictDeductStatus((String) filter.get("approveResult"));
//		deductPool.setVerTime(applyCodevertimeA[1]);
		// 更新状态
		deductPool.preUpdate();
		result = super.dao.update(deductPool);
		if(result == 0){
			logger.debug("划扣审批失败，该数据是否有人已操作，请详查！");
			throw new ServiceException("划扣审批失败，该数据是否有人已操作，请详查！");
		}
		// 全程流痕
		checkManager.addCheck(deductPool.getApplyCode(), "划扣办理提交划扣审批状态",
				statusLog,deductPool.getDeductApplyId(),FortuneLogNode.DEDUCT_APPROVE);
		// 审批记录表
		return result;
	}
	
	public List<DeductPoolEx> findList(DeductPoolEx deductPoolEx) {
		return dao.findList(deductPoolEx);
	}
}
