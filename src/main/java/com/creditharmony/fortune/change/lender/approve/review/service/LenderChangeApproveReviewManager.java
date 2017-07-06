package com.creditharmony.fortune.change.lender.approve.review.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.fortune.type.TrustState;
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
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.service.TripleChangePhoneService;
import com.creditharmony.fortune.triple.system.service.TripleCommonService;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 出借人信息变更复审manager类
 * @Class Name LenderChangeApplyApply
 * @author 郭才林
 * @Create In 2016年4月12日
 */
@Service
public class LenderChangeApproveReviewManager extends CoreManager<LenderChangeDao, CustomerEx> {
	
	@Autowired
	private LenderChangeDao lcDao;
	
	@Autowired
	private CheckManager checkManager;
	
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	protected AttachmentManager attachmentService;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	@Autowired
	private LenderChangeApplyManager applyManager;
	@Resource
	private CustomertransferDao ctdao;
	// 三网接口交割客户
	@Resource
	TripleChangePhoneService tipleChangePhoneService;
	
	@Autowired
	private TripleCommonService tripleCommonService;
	
	@Autowired
	private CustomerDao customerDao;

	/**
	 * 复审
	 * 2015年12月24日
	 * By 刘雄武
	 * @param view
	 * @param response
	 */
	public void saveApproveInfoReview(LenderInitView view, String response) {
		
		LenderChangeInfo changeInfo=new LenderChangeInfo();
		LenderChangeLog changelog = view.getChangeLog();
		changeInfo.setId(changelog.getChangeId());
		changelog.setDictChangeState(LenderchgState.DDJRSH.value);
		changeInfo.setDictChangeType(changelog.getChangerTypeVal());
		//LenderchgState.initLenderchgState();
		String operName="";
		
		// 审批不同意
		if(WorkflowConstant.FLOW_TO_APPLYUSER.equals(response)){
			
			changeInfo.setDictChangeState(LenderchgState.SHBTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
			
			// 销户操作退回，重新发起不再销户（撤销销户）托管状态重新变成已开户
			if(LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(changeInfo.getDictChangeType())){
				
				CustomerEx cust=new CustomerEx();
				cust.preUpdate();
				cust.setCustCode(view.getCustomer().getCustCode());
				cust.setApplyHostedStatus(TrustState.KHCG.value);
				applyManager.updateApplyHostedStatus(cust);
				
			}
			
		// 复审操作
		}else if(WorkflowConstant.FLOW_TO_END.equals(response)){
			//查询原有客户信息 
			changeInfo = applyManager.getChangeInfo(changelog.getApplyId());
			LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LenderInitView.class);
			LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
			
			CustomerEx cust = after.getCustomer();
			CustomerEx custbegin = begin.getCustomer();
			//查询客户旧信息
			Customer ccus=new Customer();
			ccus.setCustCode(cust.getCustCode());
			List<Customer> cusList=customerDao.getCusByCode(ccus);
			Customer oldCus = cusList.get(0);
			IntCustomerBean paramBean = BeanUtil.conv2IntCustomerBean(oldCus);
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
				
				
				//设置旧手机号 --20160810 zxm
				paramBean.setOldPhone(custbegin.getCustMobilephone());
				//设置旧手机号 --20160810 zxm
			}
			EmergencyContactEx emer = after.getEmer();
			Address cAddr = cust.getAddress();
			Address eAddr = emer.getAddress();

			this.updateAddr(cAddr);// 更新客户地址
			this.updateAddr(eAddr);// 更新紧急人地址
			this.updateCust(cust); // 更新客户信息
			this.updateEmer(emer);// 更新紧急联系人
			changeInfo.setDictChangeState(LenderchgState.SHTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
			
			
			//将客户变更信息插入到 变更表
			BeanUtil.setChangeInfo2IntCustomerBean(cust, paramBean);
			BeanUtil.setEmerInfo2IntCustomerBean(paramBean, emer);
			tripleCommonService.sendCustomerInfo(paramBean);

		// 金账户手机号变更 金账户销户
		}else if(WorkflowConstant.FLOW_TO_THIRD_APPROVAL.equals(response)){
			
			changeInfo.setDictChangeState(LenderchgState.DJZHBG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
		}
	
		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"[对接人审核]"+
				operName;
		checkManager.addCheck(changelog.getApplyId(),operInfo
				, "审核");
		changelog.preInsert();
		lcDao.saveApproveInfo(changelog);// 保存审批信息
		changeInfo.preUpdate();
		applyManager.updateChangeState(changeInfo);// 更新状态
	}
	
	/**
	 * 更新地址
	 * 2015年12月7日
	 * By 郭才林
	 * @param cAddr
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateAddr(Address cAddr) {

		lcDao.updateAddr(cAddr);
	}
	
	/**
	 * 更新客户信息
	 * 2015年12月3日
	 * By 郭才林
	 * @param CustomerEx
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateCust(CustomerEx CustomerEx) {

		lcDao.updateCust(CustomerEx);
	}

	/**
	 * 更新紧急联系人
	 * 2015年12月4日
	 * By 郭才林
	 * @param emer
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateEmer(EmergencyContactEx emer) {

		lcDao.updateEmer(emer);
	}
	
}
