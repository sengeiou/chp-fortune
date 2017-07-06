package com.creditharmony.fortune.donation.approve.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.approve.entity.CustomertransferTaskView;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;
import com.query.ProcessQueryBuilder;

/**
 * 转赠流程Manager
 * @Class Name DonationApproveFlowManager
 * @author 刘雄武
 * @Create In 2016年3月7日
 */
@Service
public class DonationApproveFlowManager {

	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(DonationApproveFlowManager.class);

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Resource
	private CustomertransferDao ctdao;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	@Autowired
	private TripleCheckInfoService tripleCheckInfoService;
	@Autowired
	private IntKeyLogService intKeyLogService;
	
	/**
	 * 获取初审任务
	 * 2016年3月7日
	 * By 刘雄武
	 * @param workItem
	 * @param queryView
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomertransferTaskView> fetchTaskItemsFirst(
			WorkItemView workItem, CustomerQueryView queryView,Page<CustomertransferTaskView> page, FlowPage flowPage) {
		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		if(queryView.getCustName()!=null&&!"".equals(queryView.getCustName())){
			pq.put("customerName@like", "%"+queryView.getCustName()+"%");
		}
		if(queryView.getCustCode()!=null&&!"".equals(queryView.getCustCode())){
			pq.put("customerCode@like", "%"+queryView.getCustCode()+"%");
		}
		if(queryView.getManagerCode()!=null&&!"".equals(queryView.getManagerCode())){
			pq.put("managerCode@like", "%"+queryView.getManagerCode()+"%");
		}
		if(queryView.getManagerName()!=null&&!"".equals(queryView.getManagerName())){
			pq.put("managerName@like", "%"+queryView.getManagerName()+"%");
		}
		if(queryView.getDepartmentId()!=null&&!"".equals(queryView.getDepartmentId())){
			pq.put("department", queryView.getDepartmentId());
		}
		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_SINGLE, pq, CustomertransferTaskView.class);
			List<CustomertransferTaskView> listview = (List<CustomertransferTaskView>) taskBean.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_SINGLE, pq, flowPage, null, CustomertransferTaskView.class);
			
			List<BaseTaskItemView> dataList = flowPage.getList();
			List<CustomertransferTaskView> listview = new ArrayList<CustomertransferTaskView>();
			if(ArrayHelper.isNotEmpty(dataList)){
				for(BaseTaskItemView b : dataList){
					listview.add((CustomertransferTaskView)b);
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
	public void dispatch(WorkItemView workItem, CustomerManagerinfo cminfo) {
		CustomertransferLaunchView bv = new CustomertransferLaunchView();
		if (YoN.SHI.value.equals(cminfo.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPROVE);
		} else if (YoN.FOU.value.equals(cminfo.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
		}
		bv.setCTManagerinfo(cminfo);
		workItem.setBv(bv);
		this.fs.dispatch(workItem);
	}

	/**
	 * 通过customerCode对转赠流程进行不通过
	 * 2016年10月20日
	 * By 陈晓强
	 * Param custCode
	 */
	public void endAudit(String custCode) {
		// 查看初审中是否有此流程
		boolean flag = endAuditApproach(custCode, WorkflowConstant.FLOW_CF_GRAP_SINGLE);
		// 如果初审没有查看复审
		if (!flag) {
			endAuditApproach(custCode, WorkflowConstant.FLOW_CF_GRAP_APPROVE);
		}
	}

	private boolean endAuditApproach(String custCode, String flowCfGrapSingle) {
		try {
			ProcessQueryBuilder pq = new ProcessQueryBuilder();
			pq.put("customerCode", custCode);
			// 获取此customerCode的待办流程
			TaskBean fetchTaskItems = fs.fetchTaskItems(flowCfGrapSingle, pq, CustomertransferTaskView.class);
			List<? extends BaseTaskItemView> itemList = fetchTaskItems.getItemList();
			if (ArrayHelper.isNotEmpty(itemList)) {
				CustomerManagerinfo cminfo = ctdao.getCustomerManagerbyCode(custCode);
				for (BaseTaskItemView baseTaskItemView : itemList) {
					dispatch(baseTaskItemView.getApplyId(), baseTaskItemView.getWobNum(), baseTaskItemView.getToken(), cminfo);
				}
				return true;
			}
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "通过客户编号获取并结束转赠流程异常，客户编号为：" + custCode);
		}
		return false;
	}

	/**
	 * 初审和复审判断是否成单并查看有没有复审人
	 * 2016年10月20日
	 * By 陈晓强
	 */
	public String checkHaveReview(String applyId, String wobNum, String token, String custCode) {
		// 判断是否未成单
		CustomerManagerinfo cminfo = ctdao.getCustomerManagerbyCode(custCode);
		List<IntCard> cards = tripleCheckInfoService.checkCardIsSingle(cminfo.getCustMobilephone());
		if (ArrayHelper.isNotEmpty(cards)) {
			intKeyLogService.log(null, DeliveryType.ZZ.value, custCode + " 已成单！");
			dispatch(applyId, wobNum, token, cminfo);
			return "IS_SINGLE";
		}
		List<String> orgs = new ArrayList<String>();
		// 获取支公司
		FortuneOrg org = RelationShipUtil.getCityOrg(cminfo.getOldmanagerId());
		if (org != null) {
			orgs.add(org.getId());
		}
		// 获取分公司
		FortuneOrg orgd = RelationShipUtil.getDistrictOrg(cminfo.getOldmanagerId());
		if (orgd != null) {
			orgs.add(orgd.getId());
		}
		// 查看支公司或者分公司是否有复审人
		List<UserRoleOrg> list = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
		if (ArrayHelper.isEmpty(list)) {
			intKeyLogService.log(null, DeliveryType.ZZ.value, custCode + " 无支分公司审核人！");
			dispatch(applyId, wobNum, token, cminfo);
			return "NOT_REVIEW";
		}
		intKeyLogService.log(null, DeliveryType.ZZ.value, custCode + " 该客户未成单，并且有支分公司审核人！");
		return custCode;
	}

	// 转赠流程不通过
	public void dispatch(String applyId, String wobNum, String token, CustomerManagerinfo cminfo) {
		try {
			WorkItemView workItem = fs.loadWorkItemView(applyId, wobNum, token);
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
			if (cminfo != null) {
				CustomertransferLaunchView bv = new CustomertransferLaunchView();
				bv.setCTManagerinfo(cminfo);
				workItem.setBv(bv);
			}
			this.fs.dispatch(workItem);
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "转赠流程直接结束异常");
		}
	}
}
