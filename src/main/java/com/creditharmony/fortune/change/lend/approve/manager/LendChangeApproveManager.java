package com.creditharmony.fortune.change.lend.approve.manager;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoadView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借信息修改service层
 * @Class Name LendChangeManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendChangeApproveManager extends CoreManager<LendChangeDao, LendLoanApplyEx> {
	@Resource
	private LendChangeDao lecDao;
	@Autowired
	private CheckManager checkManager;
	

	/**
	 * 根据申请ID获取申请变更信息(初审，复审表单数据)
	 * 2015年12月25日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public LendLoadView getLendChangeInfo(String applyId) {
		LendLoadView bv = new LendLoadView();
		LendChangeInfo changeInfo = lecDao.getLendChangeInfo(applyId);
		LendChangeLog changeLog = new LendChangeLog();
		String dictChangeState = LenderchgState.DMDSH.value;
		String dictChangeState2 = LenderchgState.DDJRSH.value;
		String dictChangeState3 = LenderchgState.DJZHBG.value;
		changeLog.setApplyId(applyId);
		changeLog.setDictChangeState(dictChangeState);
		List<LendChangeLog> LogList = this.getApproveInfoList(changeLog);
		if(LogList.size()>0){
			changeLog = lecDao.getLendChangeLog(changeLog);// 获取门店初审	
		}
		
		LendChangeLog changeLog1 = new LendChangeLog();
		changeLog1.setDictChangeState(dictChangeState2);
		changeLog1.setApplyId(applyId);
		List<LendChangeLog> LogList1 = this.getApproveInfoList(changeLog1);
		if(LogList1.size()>0){
		    LendChangeLog changInfoRiew = lecDao.getLendChangeLog(changeLog1);// 获取业务专员复审
		    bv.setChangInfoRiew(changInfoRiew);// 业务专员
		}
		
		LendChangeLog changeLog2 = new LendChangeLog();
		changeLog2.setDictChangeState(dictChangeState3);
		changeLog2.setAuditResult(YoN.FOU.value);
		changeLog2.setApplyId(applyId);
		List<LendChangeLog> LogList2 = this.getApproveInfoList(changeLog2);
		if(LogList2.size()>0){
			LendChangeLog changInfoRes = lecDao.getLendChangeLog(changeLog2);// 获取数据管理部退回意见
		    bv.setChangInfoRes(changInfoRes);// 获取数据管理部退回意见
		}
		
		if (changeInfo != null) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			LendLaunchView begin = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LendLaunchView.class);
			LendLaunchView after = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
			begin.getLendloanapply().setTrusteeshipNo("******");
			after.getCountAfter().setId(uuid);
			bv.setChangeBegin(begin);
			bv.setChangeAfter(after);
			bv.setChangeInfo(changeInfo);
		}
		bv.setChangeLog(changeLog);// 门店
		List<ProvinceCity> provinceList = null;
		if(PayMent.ZJTG.value.equals(bv.getChangeBegin().getLendloanapply().getApplyPay())){
			provinceList=OptionUtil.findFYProvince(null);
		}else{
			provinceList=OptionUtil.getProvinceList();
		}
	
     
		bv.getChangeAfter().setProvinceList(provinceList);
		
		 Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_trust_state","tz_open_bank","tz_open_bank_kl","com_card_type","tz_pay_type","tz_contract_vesion"});
		 bv.setDicts(dicts);
		return bv;
	}

	/**
	 * 初审保存审批
	 * 2015年12月9日
	 * By 刘雄武
	 * @param changeLog
	 * @param response
	 */
	public void saveApproveInfo(LendChangeLog changeLog, String response) {
		changeLog.preInsert();
		
		LendChangeInfo changeInfo = new LendChangeInfo();
		String operName="";
		changeLog.setDictChangeState(LenderchgState.DMDSH.value);// 审批记录表保存变更状态
		//LenderchgState.initLenderchgState();
		//LendchgType.initLendchgState();
		// 金账户银行卡变更
		if(LendchgType.TRUSTESSHIP_CARD_CHA.value.equals(changeLog.getChangerTypeVal())){
			changeInfo.setDictChangeType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		}else{
			// 出借信息变更流程
			changeInfo.setDictChangeType(LendchgType.LEND_CHG.value);
		}
		if(response.equals(WorkflowConstant.FLOW_TO_REAUDIT)){           // 初审通过
			
			changeInfo.setDictChangeState(LenderchgState.DDJRSH.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHTG.value);
			
		}else if(response.equals(WorkflowConstant.FLOW_TO_APPLYUSER)){   // 审批不通过
			
			changeInfo.setDictChangeState(LenderchgState.SHBTG.value);
			operName=LenderchgState.getLenderchgState(LenderchgState.SHBTG.value);
		}
		changeInfo.setId(changeLog.getChangeId());
	
		changeInfo.preUpdate();
		lecDao.saveApproveInfo(changeLog);
		this.updateChangeState(changeInfo);
		
		// 全程留痕
		String operInfo=LendchgType.getLendchgType(LendchgType.LEND_CHG.value)+
				"[门店经理]"+
				operName;
		checkManager.addCheck(changeLog.getApplyId(),operInfo
				, "审核");
	}
	
	
	

	/**
	 * 查询审批信息列表
	 * 2015年12月28日
	 * By 刘雄武
	 * @param changeLog
	 * @return
	 */
	public List<LendChangeLog> getApproveInfoList(LendChangeLog changeLog) {

		return lecDao.getApproveInfoList(changeLog);
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
	 * 获取已办详细
	 * 2015年12月28日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public LendLoadView openFinish(String applyId) {
		LendChangeInfo changeInfo = lecDao.getLendChangeInfo(applyId);
		LendLoadView view = new LendLoadView();
		// 变更后
		LendLaunchView after = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
		// 变更前
		LendLaunchView begin = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeBegin(), LendLaunchView.class);
		changeInfo.setChangeAfter(null);
		changeInfo.setChangeBegin(null);
		view.setChangeBegin(begin);
		;
		view.setChangeAfter(after);
		view.setChangeInfo(changeInfo);
		
		LendChangeLog changeLog = new LendChangeLog();
		String dictChangeState = LenderchgState.DMDSH.value;
		String dictChangeState2 = LenderchgState.DDJRSH.value;
		changeLog.setApplyId(applyId);
		changeLog.setDictChangeState(dictChangeState);
		List<LendChangeLog> LogList = this.getApproveInfoList(changeLog);
		if(LogList.size()>0){
			changeLog = lecDao.getLendChangeLog(changeLog);// 获取门店初审
			view.setChangeLog(changeLog);// 门店
		}
		LendChangeLog changeLog1 = new LendChangeLog();
		changeLog1.setDictChangeState(dictChangeState2);
		changeLog1.setApplyId(applyId);
		List<LendChangeLog> LogList1 = this.getApproveInfoList(changeLog1);
		if(LogList1.size()>0){
		    LendChangeLog changInfoRiew = lecDao.getLendChangeLog(changeLog1);// 获取业务专员复审
		    view.setChangInfoRiew(changInfoRiew);// 业务专员
		}

		Map<String, List<Dict>> dicts = FortuneDictUtil.getDictMap(new String[]{"tz_trust_state","tz_open_bank","com_card_type","tz_pay_type","tz_contract_vesion"});
        view.setDicts(dicts);
		return view;
	}
    
}
