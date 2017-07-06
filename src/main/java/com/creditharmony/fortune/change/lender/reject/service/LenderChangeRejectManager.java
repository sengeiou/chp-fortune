package com.creditharmony.fortune.change.lender.reject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.VerifyPinType;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderLoadView;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.approve.first.service.LenderChangeApproveFirstManager;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.lend.finish.dao.LendFinishDao;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 我的客户-查看投资信息
 * @Class Name LoanApplyManager
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service
public class LenderChangeRejectManager extends CoreManager<LendFinishDao, LoanApply> {

	@Resource
	private LenderChangeDao lcDao;
	@Autowired
	private LenderChangeApplyManager applyManager;
	@Autowired
	private LenderChangeApproveFirstManager firstManager;
	@Autowired
	protected AttachmentManager attachmentService;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	@Autowired
	private CheckManager checkManager;
	/**
	 * 获取出借人变更申请已办
	 * 2015年12月2日
	 * By 郭才林
	 * @param page
	 * @param customerEx
	 * @return
	 */
	public Page<CustomerEx> getLenderChangeFinish(Page<CustomerEx> page, LenderQueryView query) {
		
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		query.setDictChangeState(LenderchgState.SHTG.value);
		query.setDictChangeType(LendchgType.LENDER_CHG.value);
		query.setChangeTypePhone(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		query.setChangeTypeAccOff(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		query.setCreateBy(UserUtils.getUserId());
		PageList<CustomerEx> pageList =(PageList<CustomerEx>) lcDao.getLenderChangeFinish(query,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 获取已办详细
	 * 2015年12月8日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	public LenderLoadView openFinish(String applyId) {

		LenderChangeInfo changeInfo = applyManager.getChangeInfo(applyId);
		CustomerEx customer = applyManager.get(changeInfo.getChangeCode());// 根据客户编号获取客户信息	
		LenderLoadView view = new LenderLoadView();
		// 变更后
		LenderInitView after = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LenderInitView.class);
		// 显示最新托管状态,金账户信息
		after.getCustomer().setApplyHostedStatus(customer.getApplyHostedStatus());
		after.getCustomer().setTrusteeshipNo(customer.getTrusteeshipNo());
		
		// 变更前
		LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
		if(LendchgType.LENDER_CHG.value.equals(changeInfo.getDictChangeType())&&after.getCustomer().getCustMobilephone().equals(begin.getCustomer().getCustMobilephone())){
			// 屏蔽隐私
			begin.getCustomer().setTrusteeshipNo("******");
			after.getCustomer().setTrusteeshipNo("******");
			after.getCustomer().setCustMobilephone("******");
			begin.getCustomer().setCustMobilephone("******");
		}
		begin.getCustomer().setCustCertNum("******");
		changeInfo.setChangeAfter(null);
		changeInfo.setChangeBegin(null);
		view.setLenderBegin(begin);
		view.setLenderAfter(after);
		view.setChangInfo(changeInfo);
		
		LenderChangeLog changeLog = new LenderChangeLog();
		String dictChangeState = LenderchgState.DMDSH.value;
		String dictChangeState2 = LenderchgState.DDJRSH.value;
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
		 Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_certificate_type","tz_customer_src","tz_marital_state","tz_billtaken_mode","tz_protocol_type","tz_protocol_version","tz_trust_state"});
	     view.setDicts(dicts);
		return view;
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
	 * 重新发起流程保存数据
	 * 2015年12月15日
	 * By 刘雄武
	 * @param view
	 */
	public void updateChangeInfo(LenderInitView view){
		String changeID=view.getChangInfo().getId();
		LenderChangeInfo change = lcDao.getChangeInfoById( view.getChangInfo().getId());
		//String operName = "";
		List<String> delList=view.getDeleteAttachmentId();
		if(LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(view.getCustomer().getChangerTypeVal())){
			// 金账户销户	
			change.setDictChangeType(LendchgType.TRUSTESSHIP_CANCELLATION.value);
			CustomerEx cust=new CustomerEx();
			cust.preUpdate();
			cust.setCustCode(view.getCustomer().getCustCode());
			cust.setApplyHostedStatus(TrustState.XHZ.value);
			applyManager.updateApplyHostedStatus(cust);// 托管状态变成销户中
			view.getCustomer().setApplyHostedStatus(TrustState.XHZ.value);

		}else if(view.getCustomer().getTrusteeshipNo()!=null&&!view.getCustomer().getCustMobilephone().equals(view.getCustomer().getCustMobilephoneChanger())){
			// 金账户手机号变更	
			change.setDictChangeType(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
			// 重新生成手机变更附件
			@SuppressWarnings("unchecked")
			Map<String, Object> p=new HashedMap();
			List<String> applyId=new ArrayList<String>();
			if("".equals(change.getApplyId())||change.getApplyId()!=null){
				if(delList==null){
					delList=new ArrayList<String>();
				}
				applyId.add(change.getApplyId());
				p.put("ids", applyId);
				p.put("AttaFileOwner", "t_tz_changer");
				p.put("dictAttaFlag", LendchgType.TRUSTESSHIP_PHONE_CHA.value);
				List<Attachment> list = getAttachmentByApplyIds(p);
				for (Attachment attachment : list) {
					delList.add(attachment.getAttaId());
				}
			}
			applyManager.changePhoneFile(view, changeID);
		}else{
			// 出借人信息变更
			change.setDictChangeType(LendchgType.LENDER_CHG.value);
		}
		
		// 变更后json信息
		String changeAfter = JsonMapper.toJsonString(view);
		change.setChangeAfter(changeAfter);
		change.setDictChangeState(LenderchgState.DMDSH.value);
		change.setChangeId(changeID);
		lcDao.updateChangeInfo(change);
		AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(),delList,view.getCustomer().getCustCode(), changeID, "t_tz_changer");
		// 手机号变更 验证码更新
		if(!view.getCustomer().getCustMobilephone().equals(view.getCustomer().getCustMobilephoneChanger())){
			String pin=customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.SMS.value, view.getCustomer().getCustCode(), pin);
		}
		// 邮箱变更验证码更新
		if(!view.getCustomer().getCustEmail().equals(view.getCustomer().getEmailBegin())){
			String pin=customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.EMAIL.value, view.getCustomer().getCustCode(), pin);
		}

		String operInfo=LendchgType.getLendchgType(LendchgType.LENDER_CHG.value)+
				"[重新发起]";
		checkManager.addCheck(change.getApplyId(),operInfo
				, "提交");
		// 将附件重temp目录下保存在正确的路径下
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = view.getAddAttachmentId();
		if(addAttachmentIdList!=null){
			for (String addAttachmentId : addAttachmentIdList) {
				Attachment attachment = attachmentService.get(addAttachmentId);
				if(attachment!=null){
					String attaFilepath = attachment.getAttaFilepath();
					if (StringUtils.isNotBlank(attaFilepath)) {
						dmService.moveDocument(attaFilepath,view.getCustomer().getCustCode()+"/"+FileConstants.KHXI,DmService.BUSI_TYPE_FORTUNE);
					}
				}				
			}
		}
	}
	
	/**
	 * 更据多个编号查询
	 * 2016年3月2日
	 * By 郭才林
	 * @param p
	 * @return
	 */
	public List<Attachment> getAttachmentByApplyIds(Map<String, Object> p) {
		
		return lcDao.getAttachmentByApplyIds(p);
	}

}
