package com.creditharmony.fortune.change.lender.approve.first.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderLoadView;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 出借人信息变更初审manager类
 * @Class Name LenderChangeApplyApply
 * @author 郭才林
 * @Create In 2016年4月12日
 */
@Service
public class LenderChangeApproveFirstManager extends CoreManager<LenderChangeDao, CustomerEx> {
	
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
	
	
	/**
	 * 初审批操作
	 * 2015年12月2日
	 * By 郭才林
	 * @param view
	 * @param string 
	 */
	public void saveApproveInfo(LenderInitView view, String response) {
		
		LenderChangeInfo changeInfo=new LenderChangeInfo();
		LenderChangeLog changelog = view.getChangeLog();
		changeInfo.setId(changelog.getChangeId());
		changelog.setDictChangeState(LenderchgState.DMDSH.value);
		changelog.preInsert();
		lcDao.saveApproveInfo(changelog);// 保存审批信息
		String operName="";
		// 审批不同意
		if(WorkflowConstant.FLOW_TO_APPLYUSER.equals(response)){
			
			changeInfo.setDictChangeState(LenderchgState.SHBTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
			
			// 销户操作退回，重新发起不再销户（撤销销户）托管状态重新变成已开户
			if(LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(changelog.getChangerTypeVal())){
				
				CustomerEx cust=new CustomerEx();
				cust.preUpdate();
				cust.setCustCode(view.getCustomer().getCustCode());
				cust.setApplyHostedStatus(TrustState.KHCG.value);
				applyManager.updateApplyHostedStatus(cust);
				
			}
		// 初审操作，状态变成待复审
		}else if(WorkflowConstant.FLOW_TO_REAUDIT.equals(response)){
			
			changeInfo.setDictChangeState(LenderchgState.DDJRSH.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
		// 流程结束，状态变成审核通过
		}
		changeInfo.setDictChangeType(changelog.getChangerTypeVal());
		changeInfo.preUpdate();
		updateChangeState(changeInfo);// 更新状态
		
		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"[门店经理审核]"+
				operName;
		checkManager.addCheck(changelog.getApplyId(),operInfo
				, "审核");
	}
	
	/**
	 * 更新变更步骤
	 * 2015年12月10日
	 * By 郭才林
	 * @param changeInfo
	 */
	@Transactional(value = "fortuneTransactionManager" , readOnly = false)
	public void updateChangeState(LenderChangeInfo changeInfo){
		
		lcDao.updateChangeState(changeInfo);
	}
	
	/**
	 * 查询审批信息列表
	 * 2015年12月3日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public List<LenderChangeLog> getApproveInfoList(LenderChangeLog changeLog) {

		return lcDao.getApproveInfoList(changeLog);
	}
	
	/**
	 * 获取复审，初审表单信息
	 * 2015年12月8日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public BaseBusinessView loadCustInfo(String applyId) {

		LenderLoadView view = new LenderLoadView();
		LenderChangeInfo changeInfo = applyManager.getChangeInfo(applyId);// 获取变更信息
		
		LenderChangeLog changeLog = new LenderChangeLog();
		String dictChangeState = LenderchgState.DMDSH.value;
		String dictChangeState2 = LenderchgState.DDJRSH.value;
		String dictChangeState3 = LenderchgState.DJZHBG.value;
		changeLog.setApplyId(applyId);
		changeLog.setDictChangeState(dictChangeState);
		List<LenderChangeLog> LogList = this.getApproveInfoList(changeLog);
		if(LogList.size()>0){
			changeLog = lcDao.getApproveInfo(changeLog);// 获取门店初审
			view.setChangeLog(changeLog);// 门店
		}
		LenderChangeLog changeLog1 = new LenderChangeLog();
		changeLog1.setDictChangeState(dictChangeState2);
		changeLog1.setApplyId(applyId);
		List<LenderChangeLog> LogList1 = this.getApproveInfoList(changeLog1);
		if(LogList1.size()>0){
		    LenderChangeLog changInfoRiew = lcDao.getApproveInfo(changeLog1);// 获取业务专员复审
		    view.setChangInfoRiew(changInfoRiew);// 业务专员
		}
		
		LenderChangeLog changeLog2 = new LenderChangeLog();
		changeLog2.setDictChangeState(dictChangeState3);
		changeLog2.setAuditResult(YoN.FOU.value);
		changeLog2.setApplyId(applyId);
		List<LenderChangeLog> LogList2 = this.getApproveInfoList(changeLog2);
		if(LogList2.size()>0){
		    LenderChangeLog changInfoRes = lcDao.getApproveInfo(changeLog2);// 获取数据管理部退回意见
		    view.setChangInfoRes(changInfoRes);// 获取数据管理部退回意见
		}
		
		
		if(changeInfo==null){
			
			return view;
		}
		// 变更后
		LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LenderInitView.class);
		// 变更前
		LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
		
		if(!LenderchgState.SHBTG.value.equals(changeInfo.getDictChangeState())&&LendchgType.LENDER_CHG.value.equals(changeInfo.getDictChangeType())&&after.getCustomer().getCustMobilephone().equals(begin.getCustomer().getCustMobilephone())){
			// 屏蔽隐私
			after.getCustomer().setTrusteeshipNo("******");
			begin.getCustomer().setTrusteeshipNo("******");
			begin.getCustomer().setCustMobilephone("******");
			after.getCustomer().setCustMobilephone("******");
			
		}
		begin.getCustomer().setCustCertNum("******");
		CustomerEx customer = this.get(changeInfo.getChangeCode());
		if(LenderchgState.SHBTG.value.equals(changeInfo.getDictChangeState())){
			
			LendLoanApply loanapp=new LendLoanApply();
			loanapp.setCustCode(customer.getCustCode());
			String off=applyManager.isAccountOff(loanapp);
			int r=0;
			if(off!=null||!"".equals(off)){
				r=Integer.parseInt(off);
			}
			if(r==0&&TrustState.KHCG.value.equals(customer.getApplyHostedStatus())){
				after.getCustomer().setIsGoldAccount(YESNO.YES.getCode());
			}
			
		}
		after.getCustomer().setApplyHostedStatus(customer.getApplyHostedStatus());;
		changeInfo.setChangeAfter(null);
		changeInfo.setChangeBegin(null);
		view.setLenderBegin(begin);
		view.setLenderAfter(after);
		view.setChangInfo(changeInfo);
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
        view.getLenderAfter().setProvinceList(provinceList);// 城市级联
        
        Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_unit_size","tz_prof","com_certificate_type","tz_customer_src","tz_marital_state","tz_billtaken_mode","tz_protocol_type","tz_protocol_version","tz_trust_state"});
        view.setDicts(dicts);
		return view;
	}
}
