package com.creditharmony.fortune.deduct.gold.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.out.jzh.JzhTransferBuOutInfo;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.moneyaccount.entity.MoneyAccountInfo;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.dm.filenet.CERequestContext;
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
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.service.DeductFileManager;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.sync.data.remote.FyMoneyAccountService;
import com.creditharmony.fortune.template.entity.DeductSuccessExportGoldModel;
import com.creditharmony.fortune.template.entity.GoldAccounExportModel;
import com.creditharmony.fortune.template.entity.GoldAccountResultModel;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.creditharmony.fortune.utils.AppPropertiesUtil;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductGoldManager extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private LoanApplyDao loanApplyDao;

	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;

	@Autowired
	private FyMoneyAccountService fyMoneyAccountService;

	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductFileManager deductFileManager;
	
	@Autowired
	private ContractManager contractManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private CommonFacade commonFacade;

	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	/**
	 * 导出金帐户 2016年2月22日 By 韩龙
	 * 
	 * @param deductPoolExt
	 * @return
	 */
	public List<GoldAccounExportModel> getGoldAccounExportModel(
			DeductPoolEx deductPoolExt) {
		List<GoldAccounExportModel> list = super.dao
				.getGoldAccounExportModel(deductPoolExt);
		return list;
	}

	/**
	 * 导入金帐户 2016年2月22日 By 韩龙
	 * 
	 * @param goldAccounExportModelList
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int updateGoldAccounExportModel(
			List<GoldAccountResultModel> goldAccountResultModelList) {
		int result = 0;
		if (goldAccountResultModelList != null) {
			for (GoldAccountResultModel goldAccountResultModel : goldAccountResultModelList) {
				if (goldAccountResultModel.getRemarks() == null
						|| "".equals(goldAccountResultModel.getRemarks())) {
					continue;
				}
				// 修改划扣状态
				DeductPool dp = new DeductPool();
				// 出借编号
				String lendCode = goldAccountResultModel.getRemarks()
						.split("_")[1];
				dp.setApplyCode(lendCode);
				dp.setActualDeductMoney(goldAccountResultModel
						.getTransactionMoney());
				dp.setConfirmOpinion(goldAccountResultModel
						.getReturnDescription());
				String returnCode = goldAccountResultModel.getReturnCode();
				// 出借申请表
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = loanApplyDao.get(loanApply);
				// 成功则更新状态
				if (returnCode.equals(ResultType.POXY_SUCCESS.getCode())) {
					// 计算回款金额
					MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
					matchingCreditorEx.setLendCode(lendCode);
					matchingCreditorEx
							.setMatchingFirstdayFlag(BillState.SQ.value);
					matchingCreditorEx = matchingCreditorDao
							.get(matchingCreditorEx);
					BigDecimal backMoney = creditorAidManager
							.getBackMoneyCommon(matchingCreditorEx
									.getMatchingId());
					// 更新状态
					loanApply.setLendStatus(LendState.HKCG.value);
					loanApply.setStatus(ForApplyStatus.CJZ.value);
					loanApplyDao.update(loanApply);
					// 合同使用时间
					Contract contract = new Contract();
					contract.setLendCode(lendCode);
					contract.setContUseDay(new Date());
					contract.preUpdate();
					contractManager.updateContractUseDay(contract);
					// 插入回款申请表
					BackMoneyPool backMoneyPool = new BackMoneyPool();
					backMoneyPool.preInsert();
					backMoneyPool.setBackmId(backMoneyPool.getId());
					backMoneyPool.setLendCode(lendCode);
					backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
					// 设置回款类型为到期回款
					backMoneyPool.setBackMoneyType(BackType.DQHK.value);
					// 设置回款状态为待回款申请
					backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
					backMoneyPool.setBackMoneyDay(DeductUtils
							.getNextWorkingDay(loanApply.getExpireDate()));
					// 回款金额
					backMoneyPool.setBackMoney(backMoney);
					// 实际回款金额
					backMoneyPool.setBackActualbackMoney(backMoney);
					backMoneyPool.setRebackFlag(YoN.FOU.value);
					backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
					backMoneyPoolDao.insert(backMoneyPool);
					dp.setDictDeductStatus(DeductState.HKCG.value);
					/*// 发送短信  资金托管不发短信
					commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);*/
					// 制作文件
					commonFacade.makeFileInfo(lendCode);
					// 三网首单
					// 划扣成功三网校验首单
					sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(), loanApply.getApplyCode());
				} else {
					dp.setDictDeductStatus(DeductState.HKSB.value);
					loanApply.setLendStatus(LendState.HKSB.value);
//					loanApply.setStatus(ForApplyStatus.CJZ.value);
				}
				dp.preUpdate();
				super.dao.update(dp);
				loanApply.setRealDeductTime(new Date());
				loanApply.preUpdate();
				loanApplyDao.update(loanApply);
				result++;
			}
		}
		return result;
	}

	/**
	 * 划拨 2016年2月23日 By 韩龙
	 * 
	 * @param deductPoolExt
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int transfer(CERequestContext ce,
			DeductPool dp, StringBuffer message) {
		int result = 0;
		String lendCode = dp.getApplyCode();
		dp = dao.getForUpdate(dp);
		if(dp == null){
			logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理过");
			message.append("业务编号【"+lendCode+"】已被其它业务人员处理过").append("</br>");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		// 获取划扣金额与付款帐户
		MoneyAccountInfo moneyAccountInfo = dao.getMoneyAccountInfo(map);
		// 流水号
		moneyAccountInfo.setMchntTxnSsn(IdGen.uuid().substring(5));
		// 收款人帐户 inCustNo
		moneyAccountInfo.setInCustNo(AppPropertiesUtil
				.getString("jzh_fk_account"));
		moneyAccountInfo.setFlag("JzhTransferBuInfo");
		moneyAccountInfo.setRemark("财富划扣_" + lendCode);
		// 金额整形（只能到分，并且没有小数）
		String amt;
		try {
			amt = new BigDecimal(moneyAccountInfo.getAmt())
					.multiply(new BigDecimal("100")).toBigInteger().toString();
		} catch (Exception ex) {
			amt = "0";
		}
		moneyAccountInfo.setAmt(amt);
		JzhTransferBuOutInfo jzht;
		try {
			jzht = (JzhTransferBuOutInfo) fyMoneyAccountService
					.chooseInterface(moneyAccountInfo);
		} catch (Exception e) {
			logger.error("金帐户划拨失败,失败信息为：" + e.getMessage(), e);
			message.append("出借编号【" + lendCode + "】:").append(e.getMessage())
					.append("<br>");
			return result;
		}
		// 成功更新出借申请表
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		if (jzht.getRetCode().equals(ResultType.POXY_SUCCESS.getCode())) {
			// 计算回款金额
			MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			matchingCreditorEx.setLendCode(lendCode);
			matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
			matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			BigDecimal backMoney = creditorAidManager
					.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
			// 划扣申请表更新为成功
			dp.setDictDeductStatus(DeductState.HKCG.value);
			dp.setActualDeductMoney(DeductUtils.isNullBigDecimal(
					loanApply.getLendMoney()).toString());
			dp.setFailMoney("0");
			// 出借申请表更新成功
			loanApply.setLendStatus(LendState.HKCG.value);
			loanApply.setStatus(ForApplyStatus.CJZ.value);
			// 插入回款申请表
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.preInsert();
			backMoneyPool.setBackmId(backMoneyPool.getId());
			backMoneyPool.setLendCode(lendCode);
			backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
			// 设置回款类型为到期回款
			backMoneyPool.setBackMoneyType(BackType.DQHK.value);
			// 设置回款状态为待回款申请
			backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
			backMoneyPool.setBackMoneyDay(DeductUtils
					.getNextWorkingDay(loanApply.getExpireDate()));
			// 回款金额
			backMoneyPool.setBackMoney(backMoney);
			// 实际回款金额
			backMoneyPool.setBackActualbackMoney(backMoney);
			backMoneyPool.setRebackFlag(YoN.FOU.value);
			backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
			backMoneyPoolDao.insert(backMoneyPool);

			// 合同使用时间
			Contract contract = new Contract();
			contract.setLendCode(lendCode);
			contract.setContUseDay(new Date());
			contract.preUpdate();
			contractManager.updateContractUseDay(contract);
			// 生成收款确认书
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("applyCode", lendCode);
			filters.put(CERequestContext.DM_FILENET_CONTEXT, ce);
			deductFileManager.compositeFile(filters);

//			Map<String, Object> smsMap = new HashMap<String, Object>();
//			smsMap.put("lendCode", lendCode);
//			// 发送短信
//			commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);
//			// 制作文件
//			commonFacade.makeFileInfo(lendCode);
			
			result = 999;
		} else {
			message.append("出借编号" + lendCode + ":").append(jzht.getRetMsg())
					.append("<br>");
			// 划扣申请表更新为失败
			dp.setDictDeductStatus(DeductState.HKSB.value);
			// 失败原因
			dp.setFailReason(jzht.getRetMsg());
			// 失败金额
			String failMoney = new BigDecimal(moneyAccountInfo.getAmt())
					.divide(new BigDecimal("100")).toString();
			dp.setFailMoney(failMoney);
			// 出借申请表更新为失败
			loanApply.setLendStatus(LendState.HKSB.value);
			result ++;
		}
		loanApply.setRealDeductTime(new Date());
		loanApply.preUpdate();
		loanApplyDao.update(loanApply);
		dp.preUpdate();
		dao.update(dp);
		// 全程流痕
		checkManager.addCheck(lendCode, "资金托管划拨","资金托管提交划拨",
				dp.getDeductApplyId(),FortuneLogNode.GOLD_ACCOUNT_DEDUCT);
		return result;
	}

	/**
	 * 金帐户提交办理 2016年2月23日 By 韩龙
	 * 
	 * @param filters
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int conductSubmit(Map<String, Object> filters) {
		int result = 0;
		// 出借编号
		String lendCode = DeductUtils.isNull((String) filters.get("applyCode"));
		String failMoney = DeductUtils
				.isNull((String) filters.get("failMoney"));
		String dictDeductStatus = DeductUtils.isNull((String) filters
				.get("dictDeductStatus"));
		String confirmOpinion = DeductUtils.isNull((String) filters
				.get("confirmOpinion"));
		String verTime = DeductUtils.isNull((String) filters
				.get("verTime"));
		// 修改划扣状态
		DeductPool dp = new DeductPool();
		dp.setApplyCode(lendCode);
		dp.setVerTime(verTime);
		dp = dao.getForUpdate(dp);
		if(dp == null){
			logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理过");
			result = -999;
			return result;
		}
		dp.setFailMoney(failMoney);
		dp.setFailReason(confirmOpinion);
		// 成功更新出借申请表
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		// 成功则更新状态
		if (dictDeductStatus.equals(DeductState.HKCG.value)) {
			// 合同使用时间
			Contract contract = new Contract();
			contract.setLendCode(lendCode);
			contract.setContUseDay(new Date());
			contract.preUpdate();
			contractManager.updateContractUseDay(contract);
			// 计算回款金额
			 MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			 matchingCreditorEx.setLendCode(lendCode);
			 matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
			 matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			 BigDecimal backMoney =
					 creditorAidManager.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
			// 成功更新出借申请表
			loanApply.setLendStatus(LendState.HKCG.value);
			loanApply.setStatus(ForApplyStatus.CJZ.value);

			// 更新划扣状态为成功
			dp.setDictDeductStatus(dictDeductStatus);
			dp.setActualDeductMoney(loanApply.getLendMoney().toString());
			dp.setFailMoney("0");

			// 插入回款申请表
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.preInsert();
			backMoneyPool.setBackmId(backMoneyPool.getId());
			backMoneyPool.setLendCode(lendCode);
			backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
			// 设置回款类型为到期回款
			backMoneyPool.setBackMoneyType(BackType.DQHK.value);
			// 设置回款状态为待回款申请
			backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
			backMoneyPool.setBackMoneyDay(DeductUtils
					.getNextWorkingDay(loanApply.getExpireDate()));
			// 回款金额
			backMoneyPool.setBackMoney(backMoney);
			// 实际回款金额
			backMoneyPool.setBackActualbackMoney(backMoney);
			backMoneyPool.setRebackFlag(YoN.FOU.value);
			backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
			backMoneyPoolDao.insert(backMoneyPool);
			dp.setDictDeductStatus(DeductState.HKCG.value);
//			// 发送短信
//			commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);
//			// 制作文件
//			commonFacade.makeFileInfo(lendCode);
			result = 999;
		} else {
			dp.setDictDeductStatus(DeductState.HKSB.value);
			String actualDeductMoney = new BigDecimal(DeductUtils.isNullMoney(dp.getLoanMoney())).
					subtract(new BigDecimal(DeductUtils.isNullMoney(failMoney))).toString();
			dp.setActualDeductMoney(actualDeductMoney);
			loanApply.setLendStatus(LendState.HKSB.value);
			result ++;
		}
		loanApply.setRealDeductTime(new Date());
		loanApply.preUpdate();
		loanApply.setRealDeductTime(new Date());
		loanApplyDao.update(loanApply);
		dp.setDealTime(DateUtils.getDate());
		dp.preUpdate();
		super.dao.update(dp);
		// 全程流痕
		checkManager.addCheck(lendCode, "待资金托管办理提交","资金托管提交",
				dp.getDeductApplyId(),FortuneLogNode.GOLD_ACCOUNT_DEDUCT);
		return result;
	}

	/**
	 * 导出金帐户 2016年3月14日 By 韩龙
	 * 
	 * @param filters
	 * @return
	 */
	public List<DeductSuccessExportGoldModel> getDeductSuccessExportGoldModel(
			Map<String, Object> filter) {
		// 参数是否为空
		if (filter.get("ids") != null) {
			String ids = filter.get("ids").toString();
			// 设置参数集合
			filter.put("lendCodes", ids.split(","));
		}
		return super.dao.getDeductSuccessExportGoldModel(filter);
	}
}
