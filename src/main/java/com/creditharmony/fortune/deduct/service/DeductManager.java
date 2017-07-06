package com.creditharmony.fortune.deduct.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.in.thirdpay.ProtocolLibraryInfo;
import com.creditharmony.adapter.bean.out.thirdpay.ProtocolLibraryReturnInfo;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.SmsRemindType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.borrow.dao.CreditorHisDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.LoanphasePeriodDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductExportExcel;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.FormatUtils;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductManager extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private CheckManager checkManager;

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private LoanApplyDao loanApplyDao;

	@Autowired
	private CreditorSendDao creditorSendDao;

	@Autowired
	private LoanphaseDao loanphaseDao;

	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;

	@Autowired
	private SmsDao smsDao;

	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private ContractManager contractManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	@Autowired
	private LendApplyManager lendApplyManager;

	@Autowired
	private CreditorHisDao creditorHisDao;
	
	@Autowired
	private LoanphasePeriodDao loanphasePeriodDao;
	
//	@Autowired
//	private CommonFacade commonFacade;
//	
//	@Autowired
//	private SendTripleInfoFacade sendTripleInfoFacade;
	/**
	 * 分页查询 2015年11月27日 By 韩龙
	 * 
	 * @param page
	 * @param deductPoolEx
	 * @return
	 */
	public Page<DeductPoolEx> findPage(Page<DeductPoolEx> page,
			DeductPoolEx deductPoolEx) {
		// 判断是否为空
		if (deductPoolEx == null) {
			deductPoolEx = new DeductPoolEx();
		}
		// 设置首期标识
		deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		pageBounds.setCountBy("deduct_apply_id");
		PageList<DeductPoolEx> pageList = (PageList<DeductPoolEx>) super.dao
				.findList(deductPoolEx, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}


	/**
	 * 当前条件下的出借金额与划扣金额 2016年2月15日 By 韩龙
	 * 
	 * @param deductPoolEx
	 * @return
	 */
	public Map<String, String> getDeductSumMoney(DeductPoolEx deductPoolEx) {
		Map<String, String> map = dao.getDeductSumMoney(deductPoolEx);
		return map;
	}

	/**
	 * 办理详细 2015年12月24日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public DeductPoolEx conduct(Map<String, Object> map) {
		// 检索债权价值信息
		// Map<String,Object>map=new HashMap<String,Object>();
		// map.put("applyCode", applyCode);
		String applyCode = (String) map.get("applyCode");
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		DeductPoolEx deductPoolExt = super.dao.getDeductPoolExt(map);
		if (deductPoolExt == null) {
			return new DeductPoolEx();
		}
		// 债权推荐列表 creditNode　债权交易表
		List<CreditorTradeEx> creditorTradeExtList = super.dao
				.getListOfClaims(applyCode);

		// 预借出借收益列表查询待推荐债权表
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(applyCode);
		matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
		Map<String, Object> loanphaseMap = new HashMap<String, Object>();
		loanphaseMap.put("matchingId", matchingCreditorEx.getMatchingId());
		loanphaseMap.put("lendCode", matchingCreditorEx.getLendCode());
		Map<String, Object> loanphase = super.dao.getLoanphase(loanphaseMap);
		deductPoolExt.setNextBorrowMoney(StringUtils.toString(loanphase
				.get("phaseamount")));
		String sumMoney = new BigDecimal(deductPoolExt.getApplyDeductMoney())
				.add(new BigDecimal(StringUtils.toString(loanphase
						.get("asphaseinterestcur")))).toString();
		deductPoolExt.setNextSumMoney(sumMoney);
		// deductPoolExt.setNextBillDate(StringUtils.toString(loanphase.get("billDay")));
		// 根据状态：债权节点；债权池；月满盈可用债权池
		for (CreditorTradeEx creditorTradeExt : creditorTradeExtList) {
			if (creditorTradeExt != null
					&& Node.KYZQ.value.equals(creditorTradeExt
							.getCreditNode())) {
				if (creditorTradeExt.getTradeId() != null) {
					// 检索债权节点；债权池表
					CreditorTradeEx cext = super.dao.getBorrow(creditorTradeExt
							.getCreditCode());
					if (cext != null) {
						// 设置属性
						creditorTradeExt
								.setBorrowerName(cext.getBorrowerName());
						creditorTradeExt.setBorrowerIdcard(cext
								.getBorrowerIdcard());
						creditorTradeExt.setBorrowerJob(cext.getBorrowerJob());
						creditorTradeExt.setBorrowPurpose(cext
								.getBorrowPurpose());
						creditorTradeExt.setBorrowBackmoneyFirday(cext
								.getBorrowBackmoneyFirday());
						creditorTradeExt
								.setBorrowMonths(cext.getBorrowMonths());
						creditorTradeExt.setBorrowMonthsSurplus(cext
								.getBorrowMonthsSurplus());
						creditorTradeExt.setBorrowValueYear(cext
								.getBorrowValueYear());
						creditorTradeExt.setBorrowCreditValue(cext
								.getBorrowCreditValue());
						continue;
					}
				}
			}
			CreditorTradeEx cext = super.dao
					.getBorrowMonthable(creditorTradeExt.getCreditCode());
			if (cext != null) {
				// 设置属性
				creditorTradeExt.setBorrowerName(cext.getBorrowerName());
				creditorTradeExt.setBorrowerIdcard(cext.getBorrowerIdcard());
				creditorTradeExt.setBorrowerJob(cext.getBorrowerJob());
				creditorTradeExt.setBorrowPurpose(cext.getBorrowPurpose());
				creditorTradeExt.setBorrowBackmoneyFirday(cext
						.getBorrowBackmoneyFirday());
				creditorTradeExt.setBorrowDays(cext.getBorrowDays());
				creditorTradeExt.setBorrowAvailableDays(cext
						.getBorrowAvailableDays());
				creditorTradeExt.setBorrowValueYear(cext.getBorrowValueYear());
				creditorTradeExt.setBorrowCreditValue(cext
						.getBorrowCreditValue());
				// creditorTradeExt.setBorrowValueYear(yearRate(cext,CreditNode.YMYKYZQC.value));
			}
		}
		// 债权推荐列表
		deductPoolExt.setCreditorTradeExtList(creditorTradeExtList);
		return deductPoolExt;
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

	/**
	 * 批量或单个债权释放 2015年12月8日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int batchUpdate(DeductPool dp,Map<String, Object> map,StringBuffer message) {
		int result = 0;
		String code = dp.getApplyCode();
		dp = dao.getForUpdate(dp);
		if(dp == null){
			message.append("业务编号【"+code+"】已被其它业务人员处理");
			return result;
		}
//		String code = dp.getApplyCode();
		// 待推荐债权
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(code);
		matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditorEx = matchingCreditorDao.getForUpdate(matchingCreditorEx);
		// 债权交易
		CreditorTrade creditorTrade = new CreditorTrade();
		creditorTrade.setMatchingId(matchingCreditorEx.getMatchingId());
		List<CreditorTrade> list = creditorSendDao
				.findCreditorTradeInfo(creditorTrade);
		// 可用债权池或月满盈可用债权池
		for (CreditorTrade ct : list) {
			
			// 设置写入债权管理记录表公共数据
			CreditorHis creditorHis = new CreditorHis();
			creditorHis.preInsert();
			creditorHis.setMatchingId(creditorTrade.getMatchingId());
			creditorHis.setOperator(UserUtils.getUserId());
			
			// 根据不同的节点修改可用债权表和月满盈可用债权表
			if (Node.KYZQ.value.equals(ct.getCreditNode())) {
				Borrow borrow = new Borrow();
				borrow.setCreditValueId(ct.getCreditCode());
				// 查询可用债权表里的可用债权价值
				Borrow bw = creditorSendDao.findCreditValue(borrow);
				borrow.setLoanCreditValue(bw.getLoanCreditValue().add(
						ct.getTradeMateMoney()));
				borrow.setModifyBy(UserUtils.getUserId());
				borrow.preUpdate();
				// 修改可用债权表可用债权
				creditorSendDao.updateBorrow(borrow);
				// 设置不同节点写入债权管理记录表数据
				creditorHis.setDictCheckNode(Node.KYZQ.value);
				creditorHis.setNodeId(ct.getCreditCode());
				if (map.get("approve") != null && "approve".equals(map.get("approve"))) {
					creditorHis.setOperateType(OperateType.CX.value);
				} else {
					// 划扣失败释放债权
					creditorHis.setOperateType(OperateType.HKSBZQSF.value);
				}
				creditorHis.setBeforMoney(bw.getLoanCreditValue());
				creditorHis.setOperateMoney(ct.getTradeMateMoney());
				creditorHis.setAfterMoney(bw.getLoanCreditValue().add(ct.getTradeMateMoney()));
				creditorHisDao.insert(creditorHis);
			} else if (Node.YMYKY.value.equals(ct.getCreditNode())) {
				BorrowMonthable borrowMonthable = new BorrowMonthable();
				borrowMonthable.setCreditMonableId(ct.getCreditCode());
				// 查询月满盈可用债权表里的可用债权价值
				BorrowMonthable bm = creditorSendDao
						.findAvailabevValue(borrowMonthable);
				borrowMonthable.setLoanAvailabeValue(bm.getLoanAvailabeValue()
						.add(ct.getTradeMateMoney()));
				borrowMonthable.setModifyBy(UserUtils.getUserId());
				borrowMonthable.preUpdate();
				creditorSendDao.updateBorrowMonthable(borrowMonthable);
				
				// 设置不同节点写入债权管理记录表数据
				creditorHis.setId(IdGen.uuid());
				creditorHis.setDictCheckNode(Node.YMYKY.value);
				creditorHis.setNodeId(ct.getCreditCode());
				if (map.get("approve") != null && "approve".equals(map.get("approve"))) {
					creditorHis.setOperateType(OperateType.CX.value);
				} else {
					// 划扣失败释放债权
					creditorHis.setOperateType(OperateType.HKSBZQSF.value);
				}
				creditorHis.setBeforMoney(bm.getLoanAvailabeValue());
				creditorHis.setOperateMoney(ct.getTradeMateMoney());
				creditorHis.setAfterMoney(bm.getLoanAvailabeValue().add(ct.getTradeMateMoney()));
				creditorHisDao.insert(creditorHis);
			}
		}
		// 释放债权时修改待推荐列表里的匹配金额和匹配债权状态 approve
		MatchingCreditor matchingCreditor = new MatchingCreditor();
		BeanUtils.copyProperties(matchingCreditorEx, matchingCreditor);
		matchingCreditor.setMatchingId(creditorTrade.getMatchingId());
		// 更新已匹配金额为零
		matchingCreditor.setMatchingMatchMoney(new BigDecimal(0));
		// 划扣审批、划扣结算撤销
		if (map.get("approve") != null && "approve".equals(map.get("approve"))) {
			matchingCreditor.setMatchingStatus(MatchingStatus.CX.value);
//			dp.setConfirmResult((String) map.get("approveResult"));
		} else {
			// 划扣失败释放债权
			matchingCreditor.setMatchingStatus(MatchingStatus.HKSBZQSF.value);
		}
		matchingCreditor.setModifyBy(UserUtils.getUserId());
		matchingCreditor.preUpdate();
		creditorSendDao.updateMatchingCreditor(matchingCreditor);
		loanphasePeriodDao.deleteByMatchingId(creditorTrade.getMatchingId());
		// 释放债权时修改分期收益表中的废止状态
		Loanphase loanphase = new Loanphase();
		loanphase.setMatchingId(matchingCreditor.getMatchingId());
		loanphase.setLendCode(code);
//		loanphaseDao.getForUpdate(loanphase);
//		loanphase.setPhaseDiscardStatus(CreditRelease.YSF.value);
//		loanphase.setPhaseDiscardStatus(ScrapState.FQ.value);
//		loanphase.setModifyBy(UserUtils.getUser().getId());
//		loanphase.preUpdate();
		loanphaseDao.delete(loanphase);
		// 释放债权时修改债权交易表里的债权交易状态
		creditorTrade.setDictTradeCreditStatus(CreditTradestate.WKSBGB.value);
		creditorTrade.setModifyBy(UserUtils.getUserId());;
		creditorTrade.preUpdate();
		creditorSendDao.updateTradeCreditStatus(creditorTrade);
		// 出借申请表
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(code);
		loanApply = loanApplyDao.get(loanApply);
		// 如果是失败列表的释放债权我的已办出借申请列表为划扣失败，反之则是撤销
		if (map.get(LendState.HKSB.value) != null
				&& map.get(LendState.HKSB.value).equals(LendState.HKSB.value)) {
			loanApply.setLendStatus(LendState.HKSB.value);
			// 划扣失败列表释放债权
			dp.setDictDeductStatus(DeductState.CX.value);
		} else {
			loanApply.setLendStatus(LendState.CX.value);
			// 划扣结算前撤销
			dp.setDictDeductStatus(DeductState.HKJSQCX.value);
		}
		// 判断是否划扣成功，如果划扣成功需回款
//		DeductPool dp = new DeductPool();
//		dp.setApplyCode(code);
		// 获取划扣申请表
//		dp = super.dao.get(dp);
		
		String status = "";
		BigDecimal actualDeductMoney = new BigDecimal(
				DeductUtils.isNullMoney(dp.getActualDeductMoney()));
		// 部分失败或全部失败
		if (actualDeductMoney.compareTo(BigDecimal.ZERO) == 1) {
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.preInsert();
			backMoneyPool.setBackmId(backMoneyPool.getId());
			backMoneyPool.setLendCode(code);
			backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
			backMoneyPool.setBackActualbackMoney(actualDeductMoney);
			backMoneyPool.setBackMoney(actualDeductMoney);
			backMoneyPool.setBackMoneyType(BackType.CJSB.value);
			backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
			backMoneyPool.setBackMoneyDay(DeductUtils
					.getNextWorkingDay(loanApply.getExpireDate()));
			backMoneyPool.setRebackFlag(YoN.FOU.value);
			backMoneyPoolDao.insert(backMoneyPool);
			// 划扣部分成功
			status = DeductState.HKBFCG.value;
			loanApply.setApplyEndStatus(FinishState.KHFQ.value);
			loanApply.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(dp.getLoanMoney()))
			.subtract(actualDeductMoney).toString());
		} else {
			// 划扣失败
			status = DeductState.HKSB.value;
			loanApply.setApplyEndStatus(FinishState.YWJ.value);
		}
		loanApply.preUpdate();
		loanApplyDao.update(loanApply);
		// 修改划扣状态
		dp.preUpdate();
		dao.update(dp);
		/** ----------------发送短信--------------------------- */
		try {
			// 除资金托管释放债权不发短信，其它都发短信
			if(!PayMent.ZJTG.value.equals(loanApply.getPayType())){
				sendSms(code,status,loanApply);
			}
		} catch (Exception e) {
			logger.error("债权释放发送短信失败",e);
		}
		// 出借合同加盖作废章
		lendApplyManager.caInvalidLendApply(code);
		// 债权协议加盖作废章
		creditorAidManager.caInvalidCreditorFile(matchingCreditorEx.getMatchingId());
		result ++ ;
		return result;
	}
	
	/**
	 * 导入线下划扣结果 2015年12月21日 By 韩龙
	 * 
	 * @param list
	 * @param map
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void importSaveExcel(DeductPool dp,LoanApply loanApply) {
		String key = dp.getApplyCode();
//		// 循环更新
//		for (String key : disMap.keySet()) {
//			DeductPool dp = disMap.get(key);
//			// 获取划扣申请
//			DeductPool pool = new DeductPool();
//			pool.setApplyCode(dp.getApplyCode());
//			pool = dao.get(pool);
//			if(pool == null){
//				return;
//			}
//			// 累加成功金额
//			dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(pool.getActualDeductMoney()))
//				.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
//			// 累加失败金额
//			dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney())).toString());
//			// 出借申请表
//			LoanApply loanApply = new LoanApply();
//			loanApply.setApplyCode(key);
//			loanApply = loanApplyDao.get(loanApply);
			if (dp.getDictDeductStatus().equals(DeductState.HKCG.value)) {
				// 计算回款金额
				 MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
				 matchingCreditorEx.setLendCode(key);
				 matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
				 matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
				 BigDecimal backMoney =
						 creditorAidManager.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
				// 成功更新出借申请表
				loanApply.setLendStatus(LendState.HKCG.value);
				loanApply.setStatus(ForApplyStatus.CJZ.value);
				// 插入回款申请表
				BackMoneyPool backMoneyPool = new BackMoneyPool();
				backMoneyPool.preInsert();
				backMoneyPool.setBackmId(backMoneyPool.getId());
				backMoneyPool.setLendCode(key);
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
				contract.setLendCode(key);
				contract.setContUseDay(new Date());
				contract.preUpdate();
				contractManager.updateContractUseDay(contract);
				dp.setDictDeductStatus(DeductState.HKCG.value);
//				// 发送短信
//				commonFacade.smsInfo(key,dp.getDictDeductStatus(),loanApply);
//				// 制作文件
//				commonFacade.makeFileInfo(loanApply.getApplyCode());
//				// 划扣成功三网校验首单
//				sendTripleInfoFacade.tripleInfo(loanApply.getCustCode());
			}else{
				loanApply.setRealDeductTime(DateUtils.parseDate(dp.getDealTime()));
				dp.setDictDeductStatus(DeductState.HKSB.value);
				loanApply.setLendStatus(LendState.HKSB.value);
			}
			// 更新数据库结果
			dp.preUpdate();
			super.dao.update(dp);
			loanApply.setRealDeductTime(DateUtils.parseDate(dp.getDealTime()));
			loanApply.preUpdate();
			loanApplyDao.update(loanApply);
			// 全程流痕
			checkManager.addCheck(dp.getApplyCode(), "导入线下划扣结果", "线下划扣状态修改",
					dp.getApplyCode(),FortuneLogNode.DEDUCT_BALANCE);
//		}
	}

	/**
	 * 获取一批次出借对应的收款确认书列表 2015年12月9日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public List<Attachment> getAttachment(Map<String, Object> map) {
		return super.dao.getAttachment(map);
	}
	
	/**
	 * 协议库对接 2015年12月22日 By 韩龙
	 * 
	 * @param codes
	 */
	public List<String> protocolLibrary(String[] codes) {
		List<String> massage = Lists.newArrayList();
		for (String code : codes) {
			// 参数条件
			Map<String, Object> filter = new HashMap<String, Object>();
			filter.put("lendCode", code);
			filter.put("dictApplyDeductType", DeductPlat.FYPT.value);
			// 查询协议库对接数据
			List<ProtocolLibraryInfo> listPlInfo = super.dao
					.getProtocolLibraryInfo(filter);
			ClientPoxy service = new ClientPoxy(ServiceType.Type.FY_SIGN_REQ);
			for (ProtocolLibraryInfo protocolLibraryInfo : listPlInfo) {
				protocolLibraryInfo.setCredtTp(protocolLibraryInfo.getCredtTp());
				protocolLibraryInfo.setAcntType("01");
//				platformBankService.getBankCd(DeductSysIdType.CF_01.getCode(), 
//						DeductPlat.FYPT.value,protocolLibraryInfo.getBankCode(),
//						deductFlag, deductType)
//				Map<String, Object> pare = new HashMap<String, Object>();
//				pare.put("bankCode", protocolLibraryInfo.getBankCode());
//				FyCode fyCode = fyCodeDao.findBankCodeBySysCode(pare);
//				protocolLibraryInfo.setBankCode(fyCode.getFyCode());
				try {
					ProtocolLibraryReturnInfo plrInfo = (ProtocolLibraryReturnInfo) service
							.callService(protocolLibraryInfo);
					if (!plrInfo.getRetCode().equals(ResultType.POXY_SUCCESS.getCode())) {
						massage.add("出借编号【" + code + "】:" + plrInfo.getRetMsg());
					}
				} catch (Exception e) {
					massage.add("出借编号【" + code + "】:" + e.getMessage());
					FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, code, FortuneLogNode.DEDUCT_BALANCE.getCode(),
							FortuneLogLevel.RED.value, e.getMessage()));
				}
			}
		}
		return massage;
	}

	/**
	 * 线下划扣-->导出第三方平台excel 2015年12月17日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public List<DeductExportExcel> getDeductExportExcel(Map<String, Object> map) {
		String[] lendCodes = null;
		// 判断是否选择要导出的记录
		if (map.get("lendCodes") == null) {
			return Lists.newArrayList();
		}
		lendCodes = map.get("lendCodes").toString().split(",");
		// 分天划扣标识
		if (map.get("theDay") != null) {
			map.put("id", lendCodes);
			map.put("lendCodes", null);
		} else {
			map.put("lendCodes", lendCodes);
		}
		List<DeductExportExcel> deductExportExcel = super.dao
				.getDeductExportExcel(map);
		return deductExportExcel;
	}

	/**
	 * 批量修改状态
	 * 2016年3月31日
	 * By 韩龙
	 * @param dp
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveBackMoney(DeductPool dp) {
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(dp.getApplyCode());
		// 查询对应出借信息
		loanApply = loanApplyDao.get(loanApply);
		// TODO 更新划扣申请的划扣平台与划扣状态
		if (dp.getDictDeductStatus().equals(DeductState.HKCG.value)) {
			// 计算回款金额
			MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			matchingCreditorEx.setLendCode(dp.getApplyCode());
			matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
			matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			BigDecimal backMoney = creditorAidManager
					.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
			// 回款池插入数据
			BackMoneyPool backMoneyPool = new BackMoneyPool();
			backMoneyPool.preInsert();
			backMoneyPool.setBackmId(backMoneyPool.getId());
			backMoneyPool.setLendCode(dp.getApplyCode());
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
			Contract contract = new Contract();
			contract.setLendCode(dp.getApplyCode());
			contract.setContUseDay(new Date());
			contract.preUpdate();
			contractManager.updateContractUseDay(contract);
		} else {
			// 更新出借状态
			loanApply.setLendStatus(LendState.HKSB.value);
		}
		loanApply.setRealDeductTime(new Date());
		loanApply.preUpdate();
		loanApplyDao.update(loanApply);
		// 全程流痕
		checkManager.addCheck(dp.getApplyCode(), "划扣结算与划扣失败批量修改状态",
				"划扣结算与划扣失败批量");
	}
	
	
	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void update(DeductPool entity) {
		dao.update(entity);
		String content = DeductState.getDeductState(entity
				.getDictDeductStatus());
		// 全程流痕
		checkManager.addCheck(entity.getApplyCode(), "状态修改" + content, content,
				entity.getApplyCode(),FortuneLogNode.DEDUCT_BALANCE);
	}

	/**
	 * 发送短信 2016年2月2日 By 韩龙
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false,
			propagation=Propagation.REQUIRES_NEW)
	public void sendSms(String lendCode, String deductStatus,LoanApply loanApply) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		SmsSendList smsSendList = smsDao.getSmsSend(map);
//		smsSendList.preInsert();
		smsSendList.setBankName(OpenBank.getOpenBank(smsSendList
				.getAccountBank()));
		String type = "";
		String remindType = "";
		if (DeductState.HKCG.value.equals(deductStatus)) {
			remindType = SmsRemindType.HKCGXXTQ.value;
			type = SmsType.HKCG.value;
			smsSendList.setDictDeductStatus(LendState.HKCG.value);
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(type);
			smsSendList.setSmsId(smsTemplate.getTemplateCode());
			String templateContent = smsTemplate.getTemplateContent();
			templateContent = templateContent.replace("{#Name#}",
					smsSendList.getCustomerName());
			templateContent = templateContent.replace("{#custom_text_4#}",
					FormatUtils.getFormatNumber(smsSendList.getLendMoney(),"#000.00"));
			smsSendList.setSmsMsg(templateContent);
		} else if (DeductState.HKSB.value.equals(deductStatus)) {
			remindType = SmsRemindType.HKSBXXTQ.value;
			type = SmsType.HKSB.value;
			smsSendList.setDictDeductStatus(LendState.HKSB.value);
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(type);
			smsSendList.setSmsId(smsTemplate.getTemplateCode());
			String templateContent = smsTemplate.getTemplateContent();
			templateContent = templateContent.replace("{#Name#}",
					smsSendList.getCustomerName());
			templateContent = templateContent.replace("{#custom_text_4#}",
					FormatUtils.getFormatNumber(smsSendList.getLendMoney(),"#000.00"));
			templateContent = templateContent.replace("{#custom_text_2#}",
					smsSendList.getBankName());
			templateContent = templateContent.replace("{#custom_text_5#}",
					smsSendList.getBankNo().substring(smsSendList.getBankNo().length()-4));
			smsSendList.setSmsMsg(templateContent);
		} else {
			type = SmsType.BF_HKSB.value;
			remindType = SmsRemindType.HKSBXXTQ.value;
			smsSendList.setDictDeductStatus(LendState.HKSB.value);
			SmsTemplate smsTemplate = smsDao.getSmsTemplate(type);
			smsSendList.setSmsId(smsTemplate.getTemplateCode());
			String templateContent = smsTemplate.getTemplateContent();
			templateContent = templateContent.replace("{#Name#}",
					smsSendList.getCustomerName());
			templateContent = templateContent.replace("{#custom_text_4#}",
					FormatUtils.getFormatNumber(smsSendList.getLendMoney(),"#000.00"));
			templateContent = templateContent.replace("{#custom_text_2#}",
					smsSendList.getBankName());
			templateContent = templateContent.replace("{#custom_text_5#}",
					smsSendList.getBankNo().substring(smsSendList.getBankNo().length()-4));
			smsSendList.setSmsMsg(templateContent);
		}
		smsSendList.setDictRemindType(type);
//		smsSendList.setDictRemindType(remindType);
		smsSendList.setSendStatus(SmsState.DFS.value);
		smsSendList.setProductCode(loanApply.getProductCode());
		smsSendList.setDictRepayType(loanApply.getPayType());
		smsSendList.setBackMoneyDay(DeductUtils
					.getNextWorkingDay(loanApply.getExpireDate()));
		// smsSendList.setOnlyFlag(IdGen.uuid());
		// smsSendList.设置短信待发送状态
		smsSendList.preInsert();
		if(smsSendList.getCreateBy()==null){
			smsSendList.setCreateBy("MQ");
		}
		smsDao.insert(smsSendList);
		// 更新待推荐债权表中的文件发送状态
		/*MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(lendCode);
		matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
		matchingCreditorEx.setDictMatchingFilesendStatus("3");// 已发送
		matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);*/
	}
	
	/**
	 * 更据出借编号获取客户编号
	 * 2016年4月8日
	 * By 郭才林
	 * @param applyCode
	 * @return
	 */
	public LoanApply getCustCodeByApplyCode(String applyCode){
	  return dao.getCustCodeByApplyCode(applyCode);
	}
	
	/**
	 * 单线程
	 * 提交划扣任务并保存划扣记录
	 * 2016年4月13日
	 * By 韩龙
	 * @param type
	 * @param massge
	 * @param code
	 * @param pool
	 * @param recReq
	 */
	/*@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void addTask(String type, List<String> massge, String code,
			DeductPool pool, DeductReq recReq) {
			pool = dao.getForUpdate(pool);
			// 排它并锁该条记录
			if(pool == null){
				massge.add("业务编号【"+code+"】已被人操作");
				logger.debug("业务编号【"+code+"】已被人操作");
				return;
			}
			logger.debug("addTask增加任务--------开始");
			// 划扣记录表
			FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
			// 保存划扣记录
			deductReqManager.addDeductReq(recReq, fortuneDeductReq);
			// 添加划扣任务
			DeResult deResult = TaskService.addTask(recReq);
			if (!deResult.getReCode().equals(ResultType.ADD_SUCCESS.getCode())) {
				logger.debug("线上划扣失败【" + code + "】,失败信息" + deResult.getReMge());
				massge.add(deResult.getReMge());
			} else {
				// 修改状态
				if(type.equals(DeductState.XSHK.value)){
					pool.setDictDeductStatus(DeductState.XSHK.value);
				}else{
					pool.setDictDeductStatus(DeductState.ECXSHK.value);
				}
				pool.preUpdate();
				dao.update(pool);
				String reString = "";
				try {
					reString = deductReqManager.commit(recReq, fortuneDeductReq, deResult, code);
				} catch (Exception e) {
					logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
					deductReqManager.updateStatus(recReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
					throw new ServiceException("提交划扣任务commit失败：" + e.getMessage());
				}
				if(StringUtils.isNotEmpty(reString) ){
					logger.debug("提交划扣任务commit失败,失败信息为：" + reString);
					deductReqManager.updateStatus(recReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
					throw new ServiceException(reString);
				}
				logger.debug("addTask增加任务--------结束");
			}
	}*/
	
	/**
	 * 多线程
	 * 提交划扣任务并保存划扣记录
	 * 2016年4月13日
	 * By 韩龙
	 * @param type
	 * @param massge
	 * @param code
	 * @param pool
	 * @param recReq
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void addTask(String type, StringBuffer massge, String code,
			DeductPool pool, DeductReq recReq) {
			pool = dao.getForUpdate(pool);
			// 排它并锁该条记录
			if(pool == null){
				massge.append("业务编号【"+code+"】已被人操作");
				logger.debug("业务编号【"+code+"】已被人操作");
				return;
			}
			logger.debug("addTask增加任务--------开始");
			// 划扣记录表
			FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
			// 保存划扣记录
			deductReqManager.addDeductReq(recReq, fortuneDeductReq);
			// 添加划扣任务
			DeResult deResult = TaskService.addTask(recReq);
			if (!deResult.getReCode().equals(ResultType.ADD_SUCCESS.getCode())) {
				logger.debug("线上划扣失败【" + code + "】,失败信息" + deResult.getReMge());
				massge.append(deResult.getReMge());
			} else {
				// 修改状态
				if(type.equals(DeductState.XSHK.value)){
					pool.setDictDeductStatus(DeductState.XSHK.value);
				}else{
					pool.setDictDeductStatus(DeductState.ECXSHK.value);
				}
				pool.preUpdate();
				dao.update(pool);
				String reString = "";
				try {
					reString = deductReqManager.commit(recReq, fortuneDeductReq, deResult, code);
				} catch (Exception e) {
					logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
					deductReqManager.updateStatus(recReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
					throw new ServiceException("提交划扣任务commit失败：" + e.getMessage());
				}
				if(StringUtils.isNotEmpty(reString) ){
					logger.debug("提交划扣任务commit失败,失败信息为：" + reString);
					deductReqManager.updateStatus(recReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
					throw new ServiceException(reString);
				}
				logger.debug("addTask增加任务--------结束");
			}
	}
	
	/**
	 * 制作收款确认书
	 * 2016年2月2日
	 * By 韩龙
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false , 
			propagation = Propagation.REQUIRES_NEW)
	public void makeFile(String lendCode){
		Map<String, Object> filters = new HashMap<String,Object>();
		MatchingCreditorEx mc = new MatchingCreditorEx();
		mc.setLendCode(lendCode);
		// 首期标识
		mc.setMatchingFirstdayFlag(BillState.SQ.value);
		mc = matchingCreditorDao.get(mc);
		LoanApply apply = getCustCodeByApplyCode(lendCode);
		mc.setCustomerCode(apply.getCustCode());
		filters.put("attaTableId", mc.getMatchingId());
		filters.put("lendCode", lendCode);
		filters.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
		filters.put("parameter", "matching_id="+mc.getMatchingId());
		filters.put("customerCode", mc.getCustomerCode());
		filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
		// 合成文件
		CERequestContext cERequestContext = CEContextHelper.login(
				Constant.getProperties.get("ceuser"),
				Constant.getProperties.get("cepwd"));
		// ce认证
		filters.put(CERequestContext.DM_FILENET_CONTEXT, cERequestContext);
		FileUtil.compositeFile(filters);
	}
}
