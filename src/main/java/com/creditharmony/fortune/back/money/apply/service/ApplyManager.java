package com.creditharmony.fortune.back.money.apply.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.redemption.common.dao.RedemptionApplyDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionDao;
import com.creditharmony.fortune.redemption.common.dao.RedemptionHistoryDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;
import com.creditharmony.fortune.redemption.common.service.RedeemManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.constant.RedeemState;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * 带事务Service，用于处理单条数据
 * @Class Name BmApplyForeachManager
 * @author 陈广鹏
 * @Create In 2016年4月27日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class ApplyManager {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private RedeemManager redeemManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private RedemptionHistoryDao rHisDao;
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private RedemptionDao redDao;
	@Autowired
	private RedemptionApplyDao applyDao;
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(RedeemManager.class);
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 发起回款申请 
	 * 2015年12月3日 
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void apply(ResultView view) {
		BackMoneyPool bmp = poolDao.getByLendCode(view.getLendCode());
		String exPlatfrom = bmManager.getPreviousBackPlatform(view.getLendCode());
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		pool.setDictBackStatus(BackState.DHKSQQR.value);
		pool.setDictBackMoneyError("");
		pool.setExPlatform(exPlatfrom);
		pool.setVerTime(view.getVerTime());
		pool.preUpdate();
		
		if (BackType.TQSH.value.equals(bmp.getBackMoneyType())) {
			// 回款类型为提前赎回需要增加判断
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(view.getLendCode());
			loanApply = loanApplyDao.get(loanApply);
			if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(loanApply.getProtocoEdition())) {
				// 特殊合同版本需特殊处理
				RedemptionApplyEntity entity = rHisDao.getRedemptionByLendCode(view.getLendCode());
				RedemptionApply apply = applyDao.getRedemptionApplyById(entity.getRedemptionId());
				
				apply.setBackMoneyDay(DateUtils.getNextday(new Date()));
				if (loanApply.getLendDate().after(new Date())) {
					apply.setLinitDay(loanApply.getLendDate());
				} else {
					apply.setLinitDay(new Date());
				}
				apply.setRedemptionStatus(RedeemState.YTG.value);
				apply.preUpdate();
				
				entity.setLinitDay(apply.getLinitDay());
				
				if (RedeemType.QBSH.value.equals(apply.getRedemptionType())) {
					// 全部赎回则重新计算
					redeemManager.calculateMoney(entity, apply);
					// 计算后覆盖剩余金额
					apply.setResidualAmount(entity.getResidualAmount());
					// 两个判断，顺序不可更改
					if (YoN.SHI.value.equals(apply.getCheckSp())) {
						apply.setRedemptionBmoney(apply.getCheckSpmoney());
						apply.setRedemptionDemoney(BigDecimal.ZERO);
					}
					if (YoN.SHI.value.equals(apply.getFeedback())) {
						apply.setRedemptionBmoney(apply.getRedemptionBmoney().subtract(apply.getFeedbackMoney()));
					}
				}
				applyDao.updateRedemptionApply(apply);
				
				// 3.在回款申请表更新一条数据,回款金额为应回金额
				pool.setBackMoney(apply.getRedemptionBmoney());
				pool.setBackActualbackMoney(apply.getRedemptionBmoney());
				pool.setBackMoneyType(BackType.TQSH.value);
				pool.setFinalLinitDate(apply.getLinitDay());
				pool.setBackMoneyDay(apply.getBackMoneyDay());
				
				// 2.释放全部债权
				redeemManager.releaseAllCredit(entity);
				
				// 4.冻结最后还没有回息的回息记录
				BackInterestPool iPool= redDao.getLastBackInterest(entity.getLendCode());
				if (null != iPool && iPool.getCurrentBillday().after(entity.getLinitDay())) {
					// 如果回息的本期到期日在赎回的到期日之后
					iPool.setBackMoneyStatus(BacksmsState.DJ.value);
					redDao.updateInterestPool(iPool);
				}
				
				// 5.删除非首期未发送的债权文件邮件
				redeemManager.deleteEmail(entity.getLendCode(), entity.getLinitDay());
				
				// 先生成新出借，再更新旧出借到期日期
				if (RedeemType.BFSH.value.equals(apply.getRedemptionType())) {
					redeemManager.createNewLoanApply(entity, apply);
				}
				
				// 更新状态和到期日期
				LoanApply la = new LoanApply();
				la.setApplyCode(view.getLendCode());
				la.setStatus(ForApplyStatus.TQSH.value);
				la.setExpireDate(apply.getLinitDay());
				la.preUpdate();
				redDao.updateLoanApply(la);
		
			}
		}
		
		// 初始化字段
		pool.setSupplementedMoney(bmp.getBackActualbackMoney());
		pool.setIsSupplemented(YoN.FOU.value);
		pool.setSupplementedDays(BigDecimal.ZERO);
		
		int count = poolDao.update(pool);
		if (count == 0) {
			BmUtils.throwDataOutOfDateException();
		}

		// 2.增加审批记录
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(view.getBackmId());
		log.setApplyBy(UserUtils.getUser(UserUtils.getUserId()).getId());
		log.setApplyTime(new Date());
		log.setDictBackmStatus(BackState.DHKSQQR.value);
		log.preInsert();
		logDao.insert(log);

		// 3.全程留痕
		pool.setLendCode(view.getLendCode());
		bmManager.insertCheck(pool, log);
	}

	/**
	 * 更新渠道标识
	 * 2016年6月20日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateChannel(ListItemView view) {
		
		
		BackMoneyPool bmp = poolDao.getUpdateChannelPool(view);
		
		if (null==bmp) {
			throw new ServiceException("待回款申请列表未找到对应回款数据");
		}
		BackMoneyPool pool = new BackMoneyPool();
		pool.setLendCode(view.getLendCode());
		//更新渠道标识
		String listFlag =  view.getListFlag();
		if(FortuneChannelFlag.DJR.value.equals(listFlag)){
			pool.setDictFortunechannelflag(FortuneChannelFlag.DJR.value);
		}else if(FortuneChannelFlag.JX.value.equals(listFlag)){
			pool.setDictFortunechannelflag(FortuneChannelFlag.JX.value);
		}
		
		pool.preUpdate();
		poolDao.updateByLendCode(pool);
		
		String lendCode = bmp.getLendCode();
		String operatorInfo="";
		if(FortuneChannelFlag.DJR.value.equals(listFlag)){
			operatorInfo = "渠道标识修改为[" + FortuneChannelFlag.DJR.name + "]";
		}else if(FortuneChannelFlag.JX.value.equals(listFlag)){
			operatorInfo = "渠道标识修改为[" + FortuneChannelFlag.JX.name + "]";
		}
		String operatorType = "上传文件";;
		String operateAffiliated = bmp.getBackmId();
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, FortuneLogNode.MONEY_APPLY);
	}

}
