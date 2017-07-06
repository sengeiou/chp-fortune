package com.creditharmony.fortune.redemption.approval.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.redemption.common.dao.LoanWorkflowDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionLogDao;
import com.creditharmony.fortune.redemption.common.entity.LoanWorkflow;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.entity.RedemptionLog;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.service.RedeemManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.common.view.RedemptionFlowTaskItemView;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;
import com.query.ProcessQueryBuilder;

/**
 * 赎回审批Service
 * @Class Name ApprovalManager
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service
public class RedeemApprovalManager extends CoreManager<RedemptionDao, Redemptionex> {
	
	@Autowired
	private RedemptionDao reDao;
	@Autowired
	private RedemptionApplyDao applyDao;
	@Autowired
	private RedemptionLogDao logDao;
	@Autowired
	private LoanWorkflowDao workflowDao;
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	@Autowired
	private FlowService flowService;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private RedeemManager redeemManager;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 提交审批结果前数据校验
	 * 2016年3月10日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	public String approvalCheck(RedemptionApplyEntity entity) {
		String result = "再出借金额不可低于5万，请重新输入剩余金额"; // 赎回再出借金额不可低于5万
		BigDecimal residualAmount = entity.getResidualAmount();
		if (residualAmount.compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
			// 条件1：低于最低出借金额时，判断
			RedemptionApplyEntity e = reDao.getRedemptionByLendCode(entity.getLendCode());
			Date applyLendDay = e.getApplyLendDay(); // 出借日期
			String productCode = e.getProductCode(); // 产品编号
			String contractVersion = e.getApplyAgreementEdition(); // 合同版本
			if (RedeemConstant.CONTRACT_EDT_LIST.contains(contractVersion)) {
				result="true";
			}
			// 条件2：
			Date sd = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
			if (sd.after(applyLendDay)) {
				if (ProductCode.NNY.value.equals(productCode)) {
					result="true";
				}
				if (ProductCode.NNJ.value.equals(productCode)) {
					result="true";
				}
			}
			// 条件3：
			if (ProductCode.NNJ.value.equals(productCode)) {
				Date NNJsd = DateUtils.parseDate("2016-01-01");
				if (NNJsd.after(applyLendDay)) {
					// 出借日期是2016-01-01以前
					if (residualAmount.compareTo(new BigDecimal("10000")) == -1) {
						// 剩余金额 < 1W
						result = "提交失败，年年金部分赎回剩余金额不能小于1万";
					} else {
						result = "true";
					}
				} else {
					// 出借日期是2016-01-01以后（包括2016-01-01）
					if (residualAmount.compareTo(new BigDecimal(RedeemConstant.LOW_LIMIT)) == -1) {
						// 剩余金额 < 5W
						result = "提交失败，年年金部分赎回剩余金额不能小于5万";
					} else {
						result = "true";
					}
				}
			}
		} else {
			result="true";
		}
		return result;
	}
	
	/**
	 * 描述：获取待审批列表，不带分页
	 * 2015年12月1日
	 * By 陈广鹏
	 * @param entity
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<RedemptionFlowTaskItemView> fetchTaskItems( RedemptionApplyEntity entity) {
		String queueName = RedeemConstant.CF_EARLY_REDEMPTION;
		
		ProcessQueryBuilder queryBuilder = RedeemUtils.getQueryBuilder(entity);
		
		TaskBean taskBean = flowService.fetchTaskItems(queueName,
				queryBuilder, RedemptionFlowTaskItemView.class);

		List<RedemptionFlowTaskItemView> itemList = new ArrayList<RedemptionFlowTaskItemView>();
		if (taskBean.getItemList() != null) {
			itemList = (List<RedemptionFlowTaskItemView>) taskBean.getItemList();
		}
		return itemList;
	}
	
	/**
	 * 描述：获取待审批列表，带分页
	 * 2016年3月24日
	 * By 陈广鹏
	 * @param page
	 * @param entity
	 * @return
	 */
	public void fetchTaskItems(FlowPage page, RedemptionApplyEntity entity) {
		String queueName = RedeemConstant.CF_EARLY_REDEMPTION;
		ProcessQueryBuilder queryBuilder = RedeemUtils.getQueryBuilder(entity);
		
		flowService.fetchTaskItems(queueName, queryBuilder, page, null, RedemptionFlowTaskItemView.class);
	}
	
	/**
	 * 描述：处理审批结果
	 * 2015年11月30日
	 * By 陈广鹏
	 * @param entity
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void dispatch(RedemptionApplyEntity entity) {
		
		String userId = UserUtils.getUserId();
		Date date = new Date();
		
		// 1.增加一条审批记录
		RedemptionApply apply = applyDao.getRedemptionApplyById(entity.getRedemptionId());
		if (!RedeemState.DSP.value.equals(apply.getRedemptionStatus())) {
			throw new ServiceException("该数据已审批，请勿重复操作");
		}
		RedemptionLog log = new RedemptionLog();
		log.setRedemptionId(entity.getRedemptionId());
		log.setCheckExamine(entity.getCheckExamine());
		log.setCheckExaminetype(entity.getCheckExaminetype());
		log.setApplyId(entity.getApplyId());
		log.setApplyBy(apply.getCreateBy());
		log.setApplyTime(apply.getCreateTime());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		// 更新申请表数据
		apply.preUpdate();
		
		if (YoN.SHI.value.equals(entity.getCheckExaminetype())) {
			// 审批通过
			
			apply.setCheckSp(entity.getCheckSp());
			apply.setCheckSpmoney(entity.getCheckSpmoney());
			apply.setCheckSpremarks(entity.getCheckSpremarks());
			apply.setRedemptionStatus(RedeemState.YTG.value);
			if (RedeemType.BFSH.value.equals(apply.getRedemptionType())) {
				// 更新应回金额
				apply.setRedemptionBmoney(entity.getRedemptionBMoney());
				// 更新应扣金额
				apply.setRedemptionDemoney(entity.getRedemptionDeMoney());
				// 更新剩余金额
				apply.setResidualAmount(entity.getResidualAmount()); 
			}
			// 回款日期
			apply.setBackMoneyDay(entity.getBackMoneyDay());
			
			log.setDictRedemptionStatus(RedeemState.YTG.value);
			log.setCheckExamine(null);
			
			String edition = entity.getApplyAgreementEdition();
			if (null != edition && RedeemConstant.SPECIAL_FLOW_EDITION.contains(edition)) {
				// 申请状态更新为待推送
				apply.setRedemptionStatus(RedeemState.DTS.value);
				apply.setLinitDay(RedeemUtils.getPreviousWorkday(apply.getBackMoneyDay()));
				
				entity.setLinitDay(apply.getLinitDay());
				
				if (RedeemType.QBSH.value.equals(apply.getRedemptionType())) {
					// 全部赎回时重新计算
					redeemManager.calculateMoney(entity, apply);
				}
				
				// 两个判断，顺序不可更改
				if (YoN.SHI.value.equals(entity.getCheckSp())) {
					apply.setRedemptionBmoney(entity.getCheckSpmoney());
					apply.setRedemptionDemoney(BigDecimal.ZERO);
				}
				if (YoN.SHI.value.equals(apply.getFeedback())) {
					apply.setRedemptionBmoney(apply.getRedemptionBmoney().subtract(apply.getFeedbackMoney()));
				}
				
				// 更新回款表
				BackMoneyPool pool = backMoneyPoolDao.getByLendCode(apply.getLendCode());
				pool.setBackMoney(apply.getRedemptionBmoney());
				pool.setBackActualbackMoney(apply.getRedemptionBmoney());
				pool.setBackMoneyType(BackType.TQSH.value);
				pool.setFinalLinitDate(apply.getLinitDay());
				pool.setBackMoneyDay(apply.getBackMoneyDay());
				pool.preUpdate();
				backMoneyPoolDao.update(pool);
			} else {
				apply.setLinitDay(entity.getLinitDay());
				
				// 2.释放全部债权
				redeemManager.releaseAllCredit(entity);
				
				if (YoN.SHI.value.equals(entity.getCheckSp())) {
					// 如果特批，应回金额为特批金额,应扣金额为0。
					apply.setRedemptionBmoney(entity.getCheckSpmoney());
					apply.setRedemptionDemoney(BigDecimal.ZERO);
				}
				
				// 3.在回款申请表更新一条数据,回款金额为应回金额
				BackMoneyPool pool = backMoneyPoolDao.getByLendCode(apply.getLendCode());
				if (null != pool) {
					pool.setBackMoney(apply.getRedemptionBmoney());
					pool.setBackActualbackMoney(apply.getRedemptionBmoney());
					pool.setBackMoneyType(BackType.TQSH.value);
					pool.setFinalLinitDate(apply.getLinitDay());
					pool.setBackMoneyDay(apply.getBackMoneyDay());
					pool.preUpdate();
					
					backMoneyPoolDao.update(pool);
				}
				
				// 4.冻结最后还没有回息的回息记录
				BackInterestPool iPool= dao.getLastBackInterest(entity.getLendCode());
				if (null != iPool && iPool.getCurrentBillday().after(entity.getLinitDay())) {
					// 如果回息的本期到期日在赎回的到期日之后
					iPool.setBackMoneyStatus(BacksmsState.DJ.value);
					dao.updateInterestPool(iPool);
				}
				
				// 5.删除非首期未发送的债权文件邮件
				redeemManager.deleteEmail(entity.getLendCode(), entity.getLinitDay());
				
				if (RedeemType.BFSH.value.equals(apply.getRedemptionType())) {
					// 部分赎回，生成新出借
					redeemManager.createNewLoanApply(entity, apply);
				}
				
				// 6.更新出借申请表中对应状态
				LoanApply la = new LoanApply();
				la.setApplyCode(entity.getLendCode());
				la.setStatus(ForApplyStatus.TQSH.value);
				la.setExpireDate(apply.getLinitDay());
				la.preUpdate();
				dao.updateLoanApply(la);
			}
			try {
				//提前赎回审批通过后，向CRM系统同步成单信息
				tripleInvestSuccService.investSucc("",entity.getLendCode(), "O");
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("提前赎回审批通过后往CRM同步成单信息失败！！");
			}

			// 5.更新出借流程状态信息表
			LoanWorkflow workflow = new LoanWorkflow();
			workflow.setApplyId(entity.getApplyId());
			workflow.setDictStatus(RedeemState.YTG.value);
			workflow.preUpdate();
			workflowDao.update(workflow);
		}else {
			// 审批不通过
			apply.setRedemptionStatus(RedeemState.WTG.value);
			log.setDictRedemptionStatus(RedeemState.WTG.value);
		}
		
		applyDao.updateRedemptionApply(apply);
		logDao.insertRedemptionLog(log);
		
		// 记录全程留痕
		String lendCode = entity.getLendCode();
		String operatorInfo = "提前赎回审批：";
		if (YoN.SHI.value.equals(entity.getCheckExaminetype())) {
			operatorInfo = operatorInfo + "审批通过";
		} else {
			operatorInfo = operatorInfo + "审批不通过，原因：" + entity.getCheckExamine();
		}
		String operatorType = "提前赎回审批";
		checkManager.addCheck(lendCode, operatorInfo, operatorType, apply.getRedemptionId(), FortuneLogNode.REDEMPTION_APPROVAL);
	}

}
