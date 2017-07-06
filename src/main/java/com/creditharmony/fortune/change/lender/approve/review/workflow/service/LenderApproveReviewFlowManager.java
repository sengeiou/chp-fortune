package com.creditharmony.fortune.change.lender.approve.review.workflow.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderChangeTaskView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.query.ProcessQueryBuilder;

/**
 * 出借人信息变更复审流程管理类
 * @Class Name LenderApproveReviewFlowManager
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Service
public class LenderApproveReviewFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private LendChangeDao lecDao;
	
	
	/**
	 * 处理流程，复审
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param changelog
	 */
	public void review(WorkItemView workItem, LenderChangeLog changelog,CustomerEx cust) {

		LenderInitView bv = new LenderInitView();
		bv.setChangeLog(changelog);
		workItem.setBv(bv);
		// 根据审批结果设置对应的response值控制路由走向
		if (YoN.SHI.value.equals(changelog.getAuditResult())) {

			workItem.setResponse(WorkflowConstant.FLOW_TO_END);
			// 金账户手机号与金账户销户流程
			if(LendchgType.TRUSTESSHIP_PHONE_CHA.value.equals(changelog.getChangerTypeVal())||LendchgType.TRUSTESSHIP_CANCELLATION.value.equals(changelog.getChangerTypeVal())){
				
				//LendchgType.initLendchgState();
				cust.setChangerTypeVal(changelog.getChangerTypeVal());
				cust.setChangerTypeName(LendchgType.getLendchgType(changelog.getChangerTypeVal()));
				//LenderchgState.initLenderchgState();
				cust.setChangerState(LenderchgState.getLenderchgState(LenderchgState.DJZHBG.value));
				bv.setCustomer(cust);
				LendLoanApply lendLoanApply= lecDao.getLendByCustCode(cust.getCustCode());// 获取出借编号
				if(lendLoanApply!=null){
					// 付款银行
					CustomerAccount account=lecDao.getCustomerPayAccount(lendLoanApply.getApplyCode());
					bv.setAccount(account);
		
				}
				workItem.setResponse(WorkflowConstant.FLOW_TO_THIRD_APPROVAL);
			}

		} else if (YoN.FOU.value.equals(changelog.getAuditResult())) {
			
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
			bv.setCustomer(cust);
		
		}

		this.fs.dispatch(workItem);

	}
	
	/**
	 * 获取复审审任务
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param customerEx
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List fetchTaskItemsReview(WorkItemView workItem, CustomerEx customerEx,Page<LenderChangeTaskView> page, FlowPage flowPage) {

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
		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					WorkflowConstant.FLOW_CF_LENDER_INFO_CHANGE_APPROVE, pq,
					LenderChangeTaskView.class);
			@SuppressWarnings("unchecked")
			List<LenderChangeTaskView> listview = (List<LenderChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_LENDER_INFO_CHANGE_APPROVE, pq, flowPage,
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
