package com.creditharmony.fortune.change.lender.finish.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderLoadView;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.approve.first.service.LenderChangeApproveFirstManager;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.lend.finish.dao.LendFinishDao;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借人信息变更已办管理类
 * @Class Name LenderChangeFinishManager
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Service
public class LenderChangeFinishManager extends CoreManager<LendFinishDao, LoanApply> {

	@Resource
	private LenderChangeDao lcDao;
	@Autowired
	private LenderChangeApplyManager applyManager;
	@Autowired
	private LenderChangeApproveFirstManager firstManager;
	
	
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
//		query.setDictChangeState(LenderchgState.SHTG.value);
		query.setDictChangeType(LendchgType.LENDER_CHG.value);
		query.setChangeTypePhone(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		query.setChangeTypeAccOff(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		query.setCreateBy(UserUtils.getUserId());
		pageBounds.setCountBy("applyId");
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
		after.getCustomer().setManagerId((customer.getManagerId()));
		// 变更前
		LenderInitView begin = (LenderInitView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LenderInitView.class);
		if(LendchgType.LENDER_CHG.value.equals(changeInfo.getDictChangeType())&&after.getCustomer().getCustMobilephone().equals(begin.getCustomer().getCustMobilephone())){
			// 屏蔽隐私
		}
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
		 Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_unit_size","tz_prof","com_certificate_type","tz_customer_src","tz_marital_state","tz_billtaken_mode","tz_protocol_type","tz_protocol_version","tz_trust_state"});
	     view.setDicts(dicts);
	   //判断手机号与变更前是否相同
	     view.setMeginAndAfterMob(!view.getLenderAfter().getCustomer().getCustMobilephone().equals(view.getLenderBegin().getCustomer().getCustMobilephone()));
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


}
