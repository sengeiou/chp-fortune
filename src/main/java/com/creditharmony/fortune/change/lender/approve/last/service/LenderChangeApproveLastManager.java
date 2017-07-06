package com.creditharmony.fortune.change.lender.approve.last.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.EmergencyContactEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.approve.review.service.LenderChangeApproveReviewManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.service.TripleChangePhoneService;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeLoadView;

/**
 * 出借人信息变更终审(金账户变更)类
 * @Class Name LenderChangeApplyApply
 * @author 郭才林
 * @Create In 2016年4月12日
 */
@Service
public class LenderChangeApproveLastManager extends CoreManager<LenderChangeDao, CustomerEx> {
	
	@Autowired
	private LenderChangeDao lcDao;
	
	@Autowired
	private CheckManager checkManager;
	
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	private LenderChangeApproveReviewManager reviewManager;
	@Autowired
	private LenderChangeApplyManager applyManager;
	@Resource
	private CustomertransferDao ctdao;
	// 三网接口交割客户
	@Resource
	TripleChangePhoneService tipleChangePhoneService;

	/**
	 * 金账户手机变更
	 * 2016年2月25日
	 * By 郭才林
	 * @param response
	 * @param applyId
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void savePhoneChanger( String response,TrusteeshipChangeLoadView bv) {
		
		LenderChangeInfo changeInfo= applyManager.getChangeInfo(bv.getApplyId());
		LenderChangeLog changelog = bv.getLog();
		String operName="";
		// 审批不同意
		if(WorkflowConstant.FLOW_TO_SECOND_APPROVAL.equals(response)){
			
			changeInfo.setDictChangeState(LenderchgState.DJZHTH.value);
			changelog.setDictChangeState(LenderchgState.DJZHBG.value);
			changelog.preInsert();
			changelog.setChangeId(changeInfo.getId());
			changelog.setApplyId(changeInfo.getApplyId());
			changelog.setAuditResult(YoN.FOU.value);
			lcDao.saveApproveInfo(changelog);// 保存审批信息
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
		
		// 金账户手机变更操作，
		}else if(WorkflowConstant.FLOW_TO_END.equals(response)){

			LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LenderInitView.class);
			LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
			CustomerEx cust = after.getCustomer();
			CustomerEx custbegin = begin.getCustomer();
			if(!cust.getCustMobilephone().equals(custbegin.getCustMobilephone())){
				Customer customer = new Customer();
				customer.setCustMobilephone(cust.getCustMobilephone());
				List<Customer> list =lcDao.findListbyphone(customer);
				for (Customer cc : list) {
					LenderChangeInfo change = new LenderChangeInfo();
					change.preInsert();
					change.setChangeCode(cc.getCustCode());
					change.setApplyId(cc.getCustCode());
					change.setDictChangeType("7");
					// 变更前json信息
					String changeBegin = JsonMapper.toJsonString(cc);
					change.setChangeBegin(changeBegin);
					lcDao.saveLenderChangeInfo(change);
					//lcDao.deletecustmoerbyphone(cc);
				}
				IntPhone intphone = new IntPhone();
				intphone.setOsType(OsType.CHP.value);
				intphone.setNewPhone(cust.getCustMobilephone());
				intphone.setPhone(custbegin.getCustMobilephone());
				CustomerManagerinfo info = new CustomerManagerinfo();
				info.setManagerId(cust.getManagerId());
				CustomerManagerinfo cmi = ctdao.getManagerinfobyID(info);
				intphone.setEmpCode(cmi.getManagerCode());
				intphone.setCardId(custbegin.getCustCertNum());
				tipleChangePhoneService.chpChangerPhone(intphone);
			}
			EmergencyContactEx emer = after.getEmer();
			Address cAddr = cust.getAddress();
			Address eAddr = emer.getAddress();

			reviewManager.updateAddr(cAddr);// 更新客户地址
			reviewManager.updateAddr(eAddr);// 更新紧急人地址
			reviewManager.updateCust(cust); // 更新客户信息
			reviewManager.updateEmer(emer);// 更新紧急联系人
			changeInfo.setDictChangeState(LenderchgState.SHTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);

		}
		changeInfo.preUpdate();
		//lcDao.saveApproveInfo(changelog);// 保存审批信息
		applyManager.updateChangeState(changeInfo);// 更新状态
		
		
		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"[数据管理部审核]"+
				operName;
		checkManager.addCheck(bv.getApplyId(),operInfo
				, "审核");
	}
	
	/**
	 * 金账户销户
	 * 2016年2月28日
	 * By 郭才林
	 * @param cusCode
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateTrusteeship(String response,TrusteeshipChangeLoadView bv) {

		LenderChangeInfo changeInfo= applyManager.getChangeInfo(bv.getApplyId());
		String operName="";
		// 审批不同意
		if(WorkflowConstant.FLOW_TO_SECOND_APPROVAL.equals(response)){
			
			LenderChangeLog changelog = bv.getLog();
			changeInfo.setDictChangeState(LenderchgState.DJZHTH.value);
			changelog.setDictChangeState(LenderchgState.DJZHBG.value);
			changelog.preInsert();
			changelog.setChangeId(changeInfo.getId());
			changelog.setApplyId(changeInfo.getApplyId());
			changelog.setAuditResult(YoN.FOU.value);
			lcDao.saveApproveInfo(changelog);// 保存审批信息
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
		
		// 金账户销户变更操作，
		}else if(WorkflowConstant.FLOW_TO_END.equals(response)){
			
			CustomerEx customer = this.get(changeInfo.getChangeCode());// 根据客户编号获取客户信息	
			changeInfo.setDictChangeState(LenderchgState.SHTG.value);
			applyManager.updateChangeState(changeInfo);// 更新状态
			CustomerEx cust=new CustomerEx();
			cust.preUpdate();
			cust.setCustCode(changeInfo.getChangeCode());
			cust.setApplyHostedStatus(TrustState.YXH.value);
			if(!customer.getCustMobilephone().equals(customer.getTrusteeshipNo())){
				cust.setApplyHostedStatus(TrustState.WKH.value);
			}
			
			lcDao.updateTrusteeship(cust);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);

		}
		changeInfo.preUpdate();
		applyManager.updateChangeState(changeInfo);// 更新状态
		
		
		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"[数据管理部审核]"+
				operName;
		checkManager.addCheck(bv.getApplyId(),operInfo
				, "审核");
		
		
		
		
	}
	
}
