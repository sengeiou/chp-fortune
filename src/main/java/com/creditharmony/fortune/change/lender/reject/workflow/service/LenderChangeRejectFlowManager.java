package com.creditharmony.fortune.change.lender.reject.workflow.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderChangeTaskView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.query.ProcessQueryBuilder;

/**
 * 出借人信息变更重新发起流程管理类
 * @Class Name LenderChangeRejectFlowManager
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Service
public class LenderChangeRejectFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	
	/**
	 * 重新发起流程
	 * 2015年12月11日
	 * By 郭才林
	 * @param redirectAttributes
	 * @param workItem
	 * @param bv
	 * @param cust
	 * @param response
	 * @return
	 */
	public void newLaunchFlow(WorkItemView workItem, LenderInitView bv) {

		workItem.setBv(bv);
		this.fs.dispatch(workItem);
		
	}
	
	/**
	 * 获取退回
	 * 2015年12月11日
	 * By 郭才林
	 * @param workItem
	 * @param customer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LenderChangeTaskView> backList(WorkItemView workItem, CustomerEx customerEx,Page<LenderChangeTaskView> page, FlowPage flowPage) {

		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		if (customerEx.getCustName() != null && !"".equals(customerEx.getCustName())) {
			pq.put("customerName@like", "%"+customerEx.getCustName()+"%");// 客户姓名查询
		}
		if (customerEx.getCustCode() != null && !"".equals(customerEx.getCustCode())) {
			pq.put("customerCode@like", "%"+customerEx.getCustCode()+"%");// 客户编码查询
		}
		if (customerEx.getAddrCity() != null && !"".equals(customerEx.getAddrCity())) {
			String arr[] = customerEx.getAddrCity().split("_");
			if(arr.length>=2){
				pq.put("city", arr[1]);// 城市查询
			}else{
				pq.put("province", arr[0]);// 省份查询
			}
		}
		if(StringUtils.isNotEmpty(UserUtils.getUserId())){
			pq.put("managerId", UserUtils.getUserId());
		}

		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					WorkflowConstant.FLOW_CF_LENDER_APPLY, pq,
					// Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_CAIFU_GENERAL_QUEUING,
					LenderChangeTaskView.class);
			List<LenderChangeTaskView> listview = (List<LenderChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LENDER_APPLY, pq, flowPage,
					null,LenderChangeTaskView.class);
			List<BaseTaskItemView> dataList = flowPage.getList();
			List<LenderChangeTaskView> listview = new ArrayList<LenderChangeTaskView>();
			if(ArrayHelper.isNotEmpty(dataList)){
				for(BaseTaskItemView b : dataList){
					listview.add((LenderChangeTaskView)b);
				}
			}
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
		}
	}

}
