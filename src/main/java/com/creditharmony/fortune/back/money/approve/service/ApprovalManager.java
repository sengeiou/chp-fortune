package com.creditharmony.fortune.back.money.approve.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.SmsRemindType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackReturnInterestCommonDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ext.SmsInfoEx;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.sms.manager.SmsManager;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * 回款审批Service
 * @Class Name ApprovalManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class ApprovalManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SmsDao smsDao;
	@Autowired
	private SmsManager smsManager;
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private BackReturnInterestCommonDao backReturnInterestCommonDao;
	
	/**
	 * 处理回款审批结果
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void applyApproval(ResultView view) {
		// 是否需要插入留痕
		boolean checkNeeded = true;
		// 是否需要发送短信
		boolean smsNeeded = true;
		
		String userId = UserUtils.getUserId();
		Date date = new Date();
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setVerTime(view.getVerTime());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		pool.preUpdate();
		
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(view.getBackmId());
		log.setCheckExaminetype(view.getCheckExaminetype());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		BackMoneyPool p = detailDao.getBackMoneyPoolById(pool.getBackmId());
		if (YoN.SHI.value.equals(view.getCheckExaminetype())) {
			// 通过
			pool.setDictBackStatus(BackState.DHK.value);
			pool.setDictBackMoneyError(""); // 清空退回原因 
			
			log.setDictBackmStatus(BackState.DHK.value);
			
			//gaoxu  2017-3-25 09:24:22   回款审批通过  查找是否有到期回息数据  在待回息状态下的  有则变更为冻结状态 Start
			BackInterestPool backInterestPool = new BackInterestPool(); 
			backInterestPool.setLendCode(view.getLendCode());
			backInterestPool.setBackMoneyStatus(BacksmsState.DJ.value);
			backInterestPool.preUpdate();
			backReturnInterestCommonDao.updateFrozenInterest(backInterestPool);
			//gaoxu  2017-3-25 09:24:22   回款审批通过  查找是否有到期回息数据  在待回息状态下的  有则变更为冻结状态 end
			//回款申请审批通过后，向CRM系统同步成单信息
			try {
				LoanApply laTemp = new LoanApply();
				laTemp.setApplyCode(view.getLendCode());
				LoanApply laResult = loanApplyDao.get(laTemp);
				if( !ForApplyStatus.TQSH.value.equals( laResult.getStatus() )){
					tripleInvestSuccService.investSucc("",view.getLendCode(), "O");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("回款申请审批通过后往CRM同步成单信息失败！！");
			}
			//渠道标识是否为金信
			if (BigDecimal.ZERO.compareTo(p.getBackActualbackMoney())==0
					|| FortuneChannelFlag.DJR.value.equals(p.getDictFortunechannelflag())
					|| FortuneChannelFlag.JX.value.equals(p.getDictFortunechannelflag())){
				// 回款金额为0，或者渠道标识为大金融，不发送短信   渠道标识为金信，不发送短信
				smsNeeded = false;
				// 2.回款金额为0，或者渠道标识为大金融，直接流转到已回款列表   渠道标识为金信 ,直接流转到已回款列表 
				pool.setDictBackStatus(BackState.YHK.value);
				pool.setDictBackMoneyError("");
				
				log.setDictBackmStatus(BackState.YHK.value);
				
				// 更新出借申请表完结状态
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(view.getLendCode());
				loanApply.setApplyEndStatus(FinishState.YWJ.value);
				loanApply.preUpdate();
				
				detailDao.updateLoanApply(loanApply);
				
				// 更新客户出借状态
				LoanApply la = detailDao.getLoanApply(loanApply);
				la.setApplyEndStatus(FinishState.WWJ.value);
				int count = detailDao.countLendingApply(la);
				if (count<1) {
					// 如果未完成出借数量小于1，客户出借状态更新为已投
					Customer customer = new Customer();
					customer.setCustCode(la.getCustCode());
					customer.setCustLending(InvestState.YT.value);
					customerDao.updateApplyLending(customer);
				}
				
				String lendCode = view.getLendCode();
				String operatorInfo = "回款状态变更为[已回款]";
				String operatorType = "回款审批";;
				String operateAffiliated = pool.getBackmId();
				checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, FortuneLogNode.MONEY_APPROVE);
				checkNeeded = false;
				
			}
		} else if (YoN.FOU.value.equals(view.getCheckExaminetype())) {
			// 不通过
			smsNeeded = false; 
			pool.setDictBackStatus(BackState.DHKSPTH.value);
			pool.setDictBackMoneyError(view.getCheckExamine());
			
			if (YoN.SHI.value.equals(p.getIsSupplemented())) {
				// 补息数据流转，交换金额字段
				pool.setBackActualbackMoney(p.getSupplementedMoney());
				pool.setSupplementedMoney(p.getBackActualbackMoney());
			}
			
			log.setDictBackmStatus(BackState.DHKSPTH.value);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckReason(view.getCheckReason());
		} else {
			return;
		}
		int count = poolDao.update(pool);
		if (count==0) {
			BmUtils.throwDataOutOfDateException();
		}

		// 2.增加审批记录
		logDao.insert(log);
		
		// 3.审批通过，发送短信
		if (smsNeeded) {
//			pool.setLendCode(view.getLendCode());
			sendSms(view.getLendCode());
		}
		
		// 4.全程留痕
		if (checkNeeded) {
			pool.setLendCode(p.getLendCode());
			bmManager.insertCheck(pool, log);
		}
	}
	
	/**
	 * 发送短信
	 * 2016年1月20日
	 * By 陈广鹏
	 * @param pool
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void sendSms(String lendCode) {
		BackMoneyPool pool = poolDao.getByLendCode(lendCode);
//		pool.setDictBackStatus(BackState.DHK.value);
//		int count = detailDao.countSendRecord(pool);
//		if (1<count) {
		if (YoN.SHI.value.equals(pool.getRebackFlag())) {
			// 1.不是第一次审批通过，不发送短信
			return;
		}
		if (BigDecimal.ZERO.compareTo(pool.getBackActualbackMoney())==0) {
			// 2.回款金额为0，不发送短信
			return;
		}
		if (BackType.CJSB.value.equals(pool.getBackMoneyType())) {
			// 3.出借失败，不发送短信
			return;
		}
		if (FortuneChannelFlag.DJR.value.equals(pool.getDictFortunechannelflag())) {
			// 4.渠道标识为大金融，不发送短信
			return;
		}
		if (FortuneChannelFlag.JX.value.equals(pool.getDictFortunechannelflag())) {
			// 4.渠道标识为金信，不发送短信
			return;
		}
		SmsInfoEx info = detailDao.getSmsInfoEx(pool.getBackmId());
		if (PayMent.ZJTG.value.equals(info.getApplyPay())) {
			// 5.资金托管的数据，不发送短信
			return;
		}
		String templateCode = "";
		if (BackType.TQSH.value.equals(info.getBackMoneyType())) {
			templateCode = SmsType.TQSH.value;
		}else {
			templateCode = SmsType.DQHK.value;
		}
		SmsTemplate template = smsManager.getSmsTemplate(templateCode);
		String content = template.getTemplateContent();
		content=content.replace("{#Name#}", info.getCustomerName())
				.replace("{#custom_text_6#}", info.getApplyLendDay())
				.replace("{#custom_text_4#}", info.getApplyLendMoney())
				.replace("{#custom_text_3#}", info.getProductName());
		
		// 短信代发列表插入数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lendCode", lendCode);
		SmsSendList smsSendList = smsDao.getSmsSend(map);
		smsSendList.setDictDeductStatus(LendState.HKCG.value);
		smsSendList.setSendStatus(SmsState.DFS.value);
		smsSendList.setPushDate(new Date());
		smsSendList.setSmsId(templateCode);
		smsSendList.setSmsMsg(content);
		smsSendList.setBankName(OpenBank.getOpenBank(info.getBankName()));
		smsSendList.setBankId(info.getBankName());
		smsSendList.setBankNo(info.getBankNo());
		smsSendList.setBackMoneyDay(info.getBackMoneyDay());
		smsSendList.setDictRepayType(info.getApplyPay());
		smsSendList.setProductCode(info.getProductCode());
		smsSendList.setDictBackStatus(BackState.DHK.value); // 回款状态
		smsSendList.setDictRemindType(SmsRemindType.HKDXTQ.value); // 提醒类型
		smsSendList.preInsert();
		smsDao.insert(smsSendList);
	}

}
