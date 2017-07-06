package com.creditharmony.fortune.change.lender.approve.first.workflow.service;

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
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderChangeTaskView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.utils.RoleOrgUtil;
import com.query.ProcessQueryBuilder;

/**
 * 出借人信息变更初审流程manager类
 * @Class Name LenderApproveFirstFlowManager
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Service
public class LenderApproveFirstFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	/**
	 * 获取初审任务
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param customerEx
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LenderChangeTaskView> fetchTaskItemsFirst(WorkItemView workItem, CustomerEx customerEx,Page<LenderChangeTaskView> page, FlowPage flowPage) {

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
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgByUserAndRole(
				UserUtils.getUserId(), FortuneRole.STORE_MANAGER.id, FortuneOrgType.STORE.key, FortuneOrgType.STORE.key, null);
		if(list.size() > 0){
			customerEx.setNorg(list.get(0).getOrgId());
		}
		if(customerEx.getNorg() != null && !"".equals(customerEx.getNorg())){
			pq.put("department", customerEx.getNorg());
		}else{
			// 初审 营业部为必须字段
			pq.put("department", "-1");
		}
		
		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					WorkflowConstant.FLOW_CF_LENDER_INFO_CHANGE, pq,
					LenderChangeTaskView.class);
			List<LenderChangeTaskView> listview = (List<LenderChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LENDER_INFO_CHANGE, pq, flowPage,
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

	/**
	 * 处理流程，初审
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param changelog
	 * @param cust 
	 */
	public void dispatch(WorkItemView workItem, LenderChangeLog changelog, CustomerEx cust) {

		LenderInitView bv = new LenderInitView();
		bv.setChangeLog(changelog);
		workItem.setBv(bv);
		// 根据审批结果设置对应的response值控制路由走向
		if (YoN.SHI.value.equals(changelog.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_REAUDIT);
		} else if (YoN.FOU.value.equals(changelog.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
			bv.setCustomer(cust);
		}

		this.fs.dispatch(workItem);

	}

}
