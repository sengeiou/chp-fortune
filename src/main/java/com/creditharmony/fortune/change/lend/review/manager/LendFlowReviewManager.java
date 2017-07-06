package com.creditharmony.fortune.change.lend.review.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.approve.entity.LendChangeTaskView;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.query.ProcessQueryBuilder;

/**
 * 出借信息变更申请流程Service层
 * @Class Name LendFlowManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendFlowReviewManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	
	@Resource
	private LendChangeDao lecDao;
	
	/**
	 * 获取复审任务
	 * 2015年12月4日
	 * By 刘雄武
	 * @param workItem
	 * @param lendloanapply
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LendChangeTaskView> fetchTaskItemsReview(
			WorkItemView workItem, LendQueryView queryView,Page<LendChangeTaskView> page, FlowPage flowPage) {

		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		if(queryView.getCustName()!=null&&!"".equals(queryView.getCustName())){
			pq.put("customerName@like", "%"+queryView.getCustName()+"%");
		}
		if(queryView.getCustCode()!=null&&!"".equals(queryView.getCustCode())){
			pq.put("customerCode@like", "%"+queryView.getCustCode()+"%");
		}
		if(queryView.getCity()!=null&&!"".equals(queryView.getCity())){
			String arr[] = queryView.getCity().split("_");
			if(arr.length>=2){
				pq.put("city", arr[1]);// 城市查询
			}else{
				pq.put("province", arr[0]);// 省份查询
			}
		}
		if(queryView.getCustState()!=null&&!"".equals(queryView.getCustState())){
			pq.put("dictCustomerStatus", queryView.getCustState());
		}
		if(queryView.getLendCode()!=null&&!"".equals(queryView.getLendCode())){
			pq.put("lendCode@like", "%"+queryView.getLendCode()+"%");
		}
		if(queryView.getProductCode()!=null&&!"".equals(queryView.getProductCode())){
			pq.put("productCode", queryView.getProductCode().split(","));
		}
		if(queryView.getApplyPay()!=null&&!"".equals(queryView.getApplyPay())){
			pq.put("applyPay", queryView.getApplyPay().split(","));
		}
		if(queryView.getManagerName()!=null&&!"".equals(queryView.getManagerName())){
			pq.put("financing", queryView.getManagerName());
		}
		if(queryView.getOrgCode()!=null&&!"".equals(queryView.getOrgCode())){
			String orgid[] = queryView.getOrgCode().split(",");
			pq.put("department", orgid);
		}
		if(queryView.getCustMobilephone()!=null&&!"".equals(queryView.getCustMobilephone())){
			pq.put("custMobilephone@like", "%"+queryView.getCustMobilephone()+"%");
		}
//		TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE_APPROVE, pq,
//				LendChangeTaskView.class);
		
//		@SuppressWarnings("rawtypes")
//		List itemList = new ArrayList();
//		if (taskBean.getItemList() != null) {
//			itemList = taskBean.getItemList();
//		}
//		
//		return itemList;
		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE_APPROVE, pq,
					// Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_CAIFU_GENERAL_QUEUING,
					LendChangeTaskView.class);

			List<LendChangeTaskView> listview = (List<LendChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE_APPROVE, pq, flowPage,
					null,LendChangeTaskView.class);
			
			List<BaseTaskItemView> dataList = flowPage.getList();
			List<LendChangeTaskView> listview = new ArrayList<LendChangeTaskView>();
			if(ArrayHelper.isNotEmpty(dataList)){
				for(BaseTaskItemView b : dataList){
					listview.add((LendChangeTaskView)b);
				}
			}
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
		}
	}
	
	/**
	 * 业务对接员复审
	 * 2015年12月8日
	 * By 刘雄武
	 * @param workItem
	 * @param changelog
	 */
	public void review(WorkItemView workItem, LendChangeLog changelog,LendLoanApply loanApp) {

		changelog.setDictChangeState(LenderchgState.DDJRSH.value);
		LendLaunchView bv=new LendLaunchView();
		bv.setChangeLog(changelog);
		if(YoN.SHI.value.equals(changelog.getAuditResult())){  //通过
			workItem.setResponse(WorkflowConstant.FLOW_TO_END);
			// 金账户流程
			if(LendchgType.TRUSTESSHIP_CARD_CHA.value.equals(loanApp.getChangerTypeVal())){
				
				//LendchgType.initLendchgState();
				loanApp.setChangerTypeName(LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_CARD_CHA.value));
				loanApp.setChangerTypeVal(LendchgType.TRUSTESSHIP_CARD_CHA.value);
				//LenderchgState.initLenderchgState();
				loanApp.setChangerState(LenderchgState.getLenderchgState(LenderchgState.DJZHBG.value));
				bv.setLendloanapply(loanApp);
				
				LendChangeInfo changeInfo = lecDao.getLendChangeInfo(changelog.getApplyId());
				LendLaunchView afteraccount = (LendLaunchView) JsonMapper.fromJsonString(changeInfo.getChangeAfter(), LendLaunchView.class);
				// 设置付款银行卡号
	
				bv.setPayAccount(afteraccount.getCountAfter());
				workItem.setResponse(WorkflowConstant.FLOW_TO_THIRD_APPROVAL);
			}
		}else if(YoN.FOU.value.equals(changelog.getAuditResult()))  //不通过
		{
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
		}
		workItem.setBv(bv);
		this.fs.dispatch(workItem);
		
	}

}
