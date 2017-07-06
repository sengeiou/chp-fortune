package com.creditharmony.fortune.redemption.reapply.workflow;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.redemption.common.dao.RedemptionApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionLogDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.entity.RedemptionLog;
import com.creditharmony.fortune.redemption.common.service.RedeemManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.utils.AttachmentUtil;

/**
 * 重新发起赎回申请
 * @Class Name RedemptionFlowRelaunchEx
 * @author 陈广鹏
 * @Create In 2015年12月22日
 */
@Service("ex_cf_redemptionFlow_relaunch")
public class RedemptionFlowRelaunchEx extends BaseService implements ExEvent {

	@Autowired
	private RedemptionApplyDao applyDao;
	@Autowired
	private RedemptionLogDao logDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private RedeemManager commonManager;

	@Override
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void invoke(WorkItemView itemView) {
		RedemptionApplyEntity entity = (RedemptionApplyEntity) itemView.getBv();

		// 1.赎回申请表更新一条数据
		RedemptionApply apply = fillRedemptionApply(entity);
		applyDao.updateRedemptionApply(apply);

		// 2.赎回审批记录表插入一条记录
		RedemptionLog log = fillRedemptionLog(entity, apply);
		logDao.insertRedemptionLog(log);
		
		// 附件添加删除
		AttachmentUtil.updateAndDeleteAttchment(entity.getAddAttachmentId(),
				entity.getDeleteAttachmentId(), apply.getLendCode(), apply.getRedemptionId(),
				"t_tz_redemption");
		
		// 记录全程留痕
		String lendCode = apply.getLendCode();
		String operatorInfo = "重新发起提前赎回申请";
		String operatorType = "提前赎回申请";
		checkManager.addCheck(lendCode, operatorInfo, operatorType, apply.getRedemptionId(), FortuneLogNode.REDEMPTION_REAPPLY);
	}
	
	/**
	 * 填充log Bean
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param itemView
	 * @param apply
	 * @return
	 */
	private RedemptionLog fillRedemptionLog(RedemptionApplyEntity entity,
			RedemptionApply apply) {

		RedemptionLog log = new RedemptionLog();// 记录表对象
		log.setApplyId(entity.getApplyId());
		log.setCheckExamine(entity.getCheckExamine());
		log.setRedemptionId(entity.getRedemptionId());
		log.setDictRedemptionStatus(RedeemState.DSP.value);
		log.setApplyBy(UserUtils.getUserId());
		log.setApplyTime(new Date());
		log.preInsert();
		return log;
	}
	
	/**
	 * 填充apply Bean
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	private RedemptionApply fillRedemptionApply(RedemptionApplyEntity entity) {
		RedemptionApply apply = new RedemptionApply();// 申请表对象
		apply.setLendCode(entity.getLendCode());
		apply.setRedemptionId(entity.getRedemptionId());
		apply.setRedemptionTime(entity.getRedemptionTime());
		apply.setRedemptionType(entity.getRedemptionType());
		apply.setRedemptionReceType(entity.getRedemptionReceType());
		apply.setLinitDay(entity.getLinitDay());
		apply.setFeedback(entity.getFeedback());
		if (YoN.FOU.value.equals(entity.getFeedback())) {
			apply.setFeedbackMoney(BigDecimal.ZERO);
		} else {
			apply.setFeedbackMoney(entity.getFeedbackMoney());
		}
		apply.setFeedbackRemark(entity.getFeedbackRemark());
		
		// 更新数据库
		if (RedeemType.QBSH.value.equals(entity.getRedemptionType())) {
			// 全部赎回，则赎回金额为出借金额
			apply.setRedemptionMoney(new BigDecimal(entity.getApplyLendMoney()));
			apply.setResidualAmount(BigDecimal.ZERO);
		} else {
			apply.setRedemptionMoney(entity.getRedemptionMoney());
			apply.setResidualAmount(entity.getApplyLendMoneyd().subtract(apply.getRedemptionMoney()));
		}

		if (!RedeemConstant.SPECIAL_FLOW_EDITION.contains(entity.getApplyAgreementEdition())) {
			// 合同版本不是特殊版本时，计算
			commonManager.calculateMoney(entity, apply);
		} else {
			apply.setRedemptionBmoney(BigDecimal.ZERO);
			apply.setRedemptionDemoney(BigDecimal.ZERO);
		}
		apply.setRedemptionStatus(RedeemState.DSP.value);
		apply.preUpdate();
		return apply;
	}

}
