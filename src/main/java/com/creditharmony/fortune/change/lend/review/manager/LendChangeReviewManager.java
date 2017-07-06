package com.creditharmony.fortune.change.lend.review.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipAccountManager;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeLoadView;

/**
 * 出借信息修改service层
 * @Class Name LendChangeManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendChangeReviewManager extends CoreManager<LendChangeDao, LendLoanApplyEx> {
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private TrusteeshipAccountManager trusteeshipAccountManager;
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 更新客户账户信息
	 * 2015年12月9日
	 * By 刘雄武
	 * @param customerAccount
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateCustomerAccount(CustomerAccount customerAccount) {

		lecDao.updateCustomerAccount(customerAccount);
	}

	/**
	 * 资金托管下的银行变更
	 * 2016年3月10日
	 * By 郭才林
	 * @param lendLoanApply
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateZjAccount(LendLoanApply lendLoanApply){
		
		lecDao.updateZjAccount(lendLoanApply);
	}
	
	/**
	 * 更新出借申请回款银行
	 * 2015年12月10日
	 * By 刘雄武
	 * @param apply
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	private void updateLoanApplyAccount(LendLoanApply apply) {

		lecDao.updateLoanApplyAccount(apply);

	}
	
	/**
	 * 更新变更状态信息
	 * 2015年12月11日
	 * By 刘雄武
	 * @param changeInfo
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateChangeState(LendChangeInfo changeInfo){
		
		lecDao.updateChangeState(changeInfo);
	}
	
	/**
	 * 复审保存数据
	 * 2015年12月24日
	 * By 刘雄武
	 * @param changeLog
	 * @param response
	 */
	public void saveApproveInfoReview(LendChangeLog changeLog, String response) {
		changeLog.preInsert();

		String operName="";
		LendChangeInfo changeInfo = new LendChangeInfo();
		//LenderchgState.initLenderchgState();
		changeLog.setDictChangeState(LenderchgState.DDJRSH.value);
		changeInfo.setDictChangeType(changeLog.getChangerTypeVal());
		if(response.equals(WorkflowConstant.FLOW_TO_APPLYUSER)){   // 审批不通过
			changeInfo.setDictChangeState(LenderchgState.SHBTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
			
		}else if(response.equals(WorkflowConstant.FLOW_TO_END)){ 
			
			changeInfo = lecDao.getLendChangeInfo(changeLog.getApplyId());
			LendLaunchView afteraccount = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
			LendLaunchView beginaccount = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LendLaunchView.class);
			LendLoanApply apply = new LendLoanApply();
			apply.setApplyCode(changeInfo.getChangeCode());
			CustomerAccount payAccount = lecDao.getCustomerPayAccount(apply.getApplyCode());
			if(afteraccount.getCountAfter().getId().equals(payAccount.getId())){
			    
			}else{	
				afteraccount.getCountAfter().setModifyTime(new Date());
				afteraccount.getCountAfter().setModifyBy(UserUtils.getUserId());
				afteraccount.getCountAfter().setCreateBy(UserUtils.getUserId());
				afteraccount.getCountAfter().setCreateTime(new Date());
				afteraccount.getCountAfter().setCustomerCode(afteraccount.getLendloanapply().getCustCode());
				// 设置匹配标识
				if (StringUtils.isEmpty(afteraccount.getCountAfter().getBankCode())) {
					afteraccount.getCountAfter().setFindFlag(YoN.FOU.value);
				} else {
					afteraccount.getCountAfter().setFindFlag(YoN.SHI.value);
				}
				this.updateCustomerAccount(afteraccount.getCountAfter());   //新增
			// 更新客户账户信息
			}
			apply.setReceivingId(afteraccount.getCountAfter().getId());// 如果有变更回款银行ID则变更出借信息表的回款银行ID
			apply.setModifyBy(UserUtils.getUserId());
			apply.setModifyTime(new Date());
			
			// 资金托管需要变更回款银行和付款银行（同一个账号）如果其他出借用的是此银行那也需改变
			if(PayMent.ZJTG.value.equals(beginaccount.getLendloanapply().getApplyPay())){
				
				apply.setReceivingIdAfter(beginaccount.getLendloanapply().getReceivingId());
				updateZjAccount(apply);
			}else{
				this.updateLoanApplyAccount(apply);
			}
			
			changeInfo.setDictChangeState(LenderchgState.SHTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
		// 走金账户银行卡变更
		}else if(response.equals(WorkflowConstant.FLOW_TO_THIRD_APPROVAL)){
			
			changeInfo.setDictChangeState(LenderchgState.DJZHBG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
			
		}
		changeInfo.setId(changeLog.getChangeId());
		changeInfo.preUpdate();
		lecDao.saveApproveInfo(changeLog);
		this.updateChangeState(changeInfo);
		
		// 全程留痕
		String operInfo=LendchgType.getLendchgType(LendchgType.LEND_CHG.value)+
				"[业务对接人]"+
				operName;
		checkManager.addCheck(changeLog.getApplyId(),operInfo
				, "审核");
	}
	
	/**
	 * 金账户卡号变更保存数据
	 * 2015年12月24日
	 * By 郭才林
	 * @param changeLog
	 * @param response
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void saveCardChange( String response,TrusteeshipChangeLoadView bv) {
	
		
		LendChangeInfo changeInfo =lecDao.getLendChangeInfo(bv.getApplyId());
		
		changeInfo.setId(changeInfo.getChangeId());
		String operName="";
		//LenderchgState.initLenderchgState();
		//LendchgType.initLendchgState();
		if(response.equals(WorkflowConstant.FLOW_TO_SECOND_APPROVAL)){   // 审批不通过
			
			changeInfo.setDictChangeState(LenderchgState.DJZHTH.value);
			LendChangeLog changelog=new LendChangeLog();
			changelog.setAuditCheckExamine(bv.getLog().getAuditCheckExamine());
			changelog.setDictChangeState(LenderchgState.DJZHBG.value);
			changelog.preInsert();
			changelog.setChangeId(changeInfo.getId());
			changelog.setApplyId(changeInfo.getApplyId());
			changelog.setAuditResult(YoN.FOU.value);
			lecDao.saveApproveInfo(changelog);// 保存审批信息
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
			
		}else if(response.equals(WorkflowConstant.FLOW_TO_END)){         // 数据管理部审通过
			
			LendLaunchView afteraccount = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
			LendLaunchView beginaccount = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LendLaunchView.class);
			LendLoanApply apply = new LendLoanApply();
			apply.setApplyCode(changeInfo.getChangeCode());
			CustomerAccount payAccount = lecDao.getCustomerPayAccount(apply.getApplyCode());
			if(afteraccount.getCountAfter().getId().equals(payAccount.getId())){
			    
			}else{	
			afteraccount.getCountAfter().setModifyTime(new Date());
			afteraccount.getCountAfter().setModifyBy(UserUtils.getUserId());
			afteraccount.getCountAfter().setCreateBy(UserUtils.getUserId());
			afteraccount.getCountAfter().setCreateTime(new Date());
			afteraccount.getCountAfter().setCustomerCode(afteraccount.getLendloanapply().getCustCode());
			this.updateCustomerAccount(afteraccount.getCountAfter());
			// 更新客户账户信息
			}
			apply.setReceivingId(afteraccount.getCountAfter().getId());// 如果有变更回款银行ID则变更出借信息表的回款银行ID
			apply.setModifyBy(UserUtils.getUserId());
			apply.setModifyTime(new Date());
			// 资金托管需要变更回款银行和付款银行（同一个账号）如果其他出借用的是此银行那也需改变
			if(PayMent.ZJTG.value.equals(beginaccount.getLendloanapply().getApplyPay())){
				
				apply.setReceivingIdAfter(beginaccount.getLendloanapply().getReceivingId());
				updateZjAccount(apply);
			}else{
				this.updateLoanApplyAccount(apply);
			}
			changeInfo.setDictChangeState(LenderchgState.SHTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
			
			// 更新金账户银行卡号
			CustomerEx cust=new CustomerEx();
			cust.setTrusteeshipAccountId(apply.getReceivingId());
			cust.preUpdate();
			cust.setCustCode(afteraccount.getLendloanapply().getCustCode());
			updateTrusteeshipCard(cust);
			// 开户成功则生成富友专用协议
			Attachment att = new Attachment();
			att.setAttaFileOwner("t_tz_changer");
			att.setAttaTableId(changeInfo.getId());
			
			// 客户信息
			Customer cus = new Customer(afteraccount.getLendloanapply().getCustCode());
			cus = customerDao.getCustomerbyCode(cus);
			if(!trusteeshipAccountManager.makeFyxy(afteraccount.getLendloanapply().getCustCode(), 
					changeInfo.getChangeCode(), cus.getTrusteeshipNo(), FortuneLogNode.TRUSTEESHIP_CHANGE.getCode(),att)){
				throw new ServiceException(afteraccount.getLendloanapply().getCustCode()+":富友专用协议生成失败");
			}
		}
	
		changeInfo.preUpdate();
		this.updateChangeState(changeInfo);
		
		String operInfo=LendchgType.getLendchgType(LendchgType.LEND_CHG.value)+
				"[数据管理部审核]"+
				operName;
		checkManager.addCheck(bv.getApplyId(),operInfo
				, "审核");
	}
	
	/**
	 * 更新金账户银行卡号
	 * 2016年3月10日
	 * By 郭才林
	 * @param apply
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	private void updateTrusteeshipCard(CustomerEx customer) {

		lecDao.updateTrusteeshipCard(customer);

	}
    
}
