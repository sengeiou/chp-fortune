package com.creditharmony.fortune.change.lend.approve.manager;

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
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
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
public class LendFlowApproveManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	
	/**
	 * 获取初审任务
	 * 2015年12月3日
	 * By 刘雄武
	 * @param workItem
	 * @param lendloanapply
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LendChangeTaskView> fetchTaskItemsFirst(
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
			pq.put("department", queryView.getOrgCode());
		}
		if(queryView.getCustMobilephone()!=null&&!"".equals(queryView.getCustMobilephone())){
			pq.put("custMobilephone@like", "%"+queryView.getCustMobilephone()+"%");
		}
		if(queryView.getApplyHostedState()!=null&&!"".equals(queryView.getApplyHostedState())){
			pq.put("applyHostedState",queryView.getApplyHostedState());
		}
//		TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE, pq,
//				LendChangeTaskView.class);
//		
//		@SuppressWarnings("rawtypes")
//		List itemList = new ArrayList();
//		if (taskBean.getItemList() != null) {
//			itemList = taskBean.getItemList();
//		}
//		
//		return itemList;
		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE, pq,
					// Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_CAIFU_GENERAL_QUEUING,
					LendChangeTaskView.class);

			List<LendChangeTaskView> listview = (List<LendChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LEND_APPLY_CHANGE, pq, flowPage,
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
	 * 门店经理初审
	 * 2015年12月8日
	 * By 刘雄武
	 * @param workItem
	 * @param changelog
	 */
	public void dispatch(WorkItemView workItem, LendChangeLog changelog) {
		//BaseStepDealInfo bsInfo = new BaseStepDealInfo();
		changelog.setDictChangeState(LenderchgState.DMDSH.value);
		LendLaunchView bv=new LendLaunchView();
		bv.setChangeLog(changelog);
		workItem.setBv(bv);
		if(YoN.SHI.value.equals(changelog.getAuditResult())){
			workItem.setResponse(WorkflowConstant.FLOW_TO_REAUDIT);
		}else if(YoN.FOU.value.equals(changelog.getAuditResult()))
		{
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
		}
		
		this.fs.dispatch(workItem);
		
	}

}
