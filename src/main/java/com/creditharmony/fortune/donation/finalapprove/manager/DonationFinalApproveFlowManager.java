package com.creditharmony.fortune.donation.finalapprove.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.approve.entity.CustomertransferTaskView;
import com.creditharmony.fortune.utils.RoleOrgUtil;
import com.query.ProcessQueryBuilder;

@Service
public class DonationFinalApproveFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	@Autowired
	private IntKeyLogService intKeyLogService;

	/**
	 * 获取复审待办 2016年3月12日 By 刘雄武
	 * 
	 * @param workItem
	 * @param queryView
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CustomertransferTaskView> fetchTaskItemsReview(WorkItemView workItem, CustomerQueryView queryView, Page<CustomertransferTaskView> page, FlowPage flowPage) {
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
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.CITY_MANAGER.id, FortuneOrgType.CITY.key, FortuneOrgType.CITY.key, null);
		if(list.size() > 0){
			queryView.setBranchCompanyId(list.get(0).getOrgId());
		} else {
			list = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.CITY_ASSISTANT_MANAGER.id, FortuneOrgType.CITY.key, FortuneOrgType.CITY.key, null);
			if (list.size() > 0) {
				queryView.setBranchCompanyId(list.get(0).getOrgId());
			}
		}
		List<UserRoleOrgEx> listd = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.SUB_MANAGER.id, FortuneOrgType.CITY.key, FortuneOrgType.DISTRICT.key, null);
		if(listd.size() > 0){
			queryView.setDistrictCompanyId(listd.get(0).getOrgId());
		} else {
			listd = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.SUB_ASSISTANT_MANAGER.id, FortuneOrgType.CITY.key, FortuneOrgType.DISTRICT.key, null);
			if (listd.size() > 0) {
				queryView.setDistrictCompanyId(listd.get(0).getOrgId());
			}
		}
		if (StringUtils.isNotEmpty(queryView.getBranchCompanyId())) {
			pq.put("branchCompanyId", queryView.getBranchCompanyId());
		}
		if (StringUtils.isNotEmpty(queryView.getDistrictCompanyId())) {
			makeUpBranchCompany(queryView.getDistrictCompanyId(), pq);
		}
		if(queryView.getDepartmentId()!=null&&!"".equals(queryView.getDepartmentId())){
			String orgid[] = queryView.getDepartmentId().split(",");
			pq.put("department", orgid);
		}

		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_APPROVE, pq, CustomertransferTaskView.class);
			List<CustomertransferTaskView> listview = (List<CustomertransferTaskView>) taskBean.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
			
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_APPROVE, pq, flowPage, null, CustomertransferTaskView.class);
			
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

	// 组建分公司查询条件
	private void makeUpBranchCompany(String districtCompanyId, ProcessQueryBuilder pq) {
		String[] branchCompanyIds = getBranchCompanyIds(districtCompanyId);
		List<String> orgs = new ArrayList<String>();
		List<String> companyIdList = new ArrayList<String>();
		for (String banchCompanyId : branchCompanyIds) {
			orgs.add(banchCompanyId);
			List<UserRoleOrg> userRoleOrgs = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
			if (ArrayHelper.isEmpty(userRoleOrgs)) {
				companyIdList.add(banchCompanyId);
			}
			orgs.clear();
		}
		String remark = "分公司<" + districtCompanyId + ">下有：";
		if (ArrayHelper.isNotEmpty(companyIdList)) {
			remark += companyIdList.size() + "个支公司无审核人,如下：";
			String[] companyIds = new String[companyIdList.size()];
			for (int i = 0; i < companyIdList.size(); i++) {
				companyIds[i] = companyIdList.get(i);
				remark += "<" + companyIdList.get(i) + ">,";
			}
			pq.put("branchCompanyId", companyIds);
		} else {
			pq.put("branchCompanyId", "");
			remark += "0个支公司无审核人，可能无支公司！";
		}
		intKeyLogService.log(null, DeliveryType.ZZ.value, remark);
	}

	// 查询分公司下所有支公司
	public String[] getBranchCompanyIds(String districtCompanyId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentId", districtCompanyId);
			params.put("deleteFlag", "0");
			List<Org> orgs = orgDao.loadByParams(params);
			if (ArrayHelper.isNotEmpty(orgs)) {
				String companyId[] = new String[orgs.size()];
				String remark = "查询分公司<" + districtCompanyId + ">下所有支公司，共" + orgs.size() + "个：";
				for (int i = 0; i < orgs.size(); i++) {
					companyId[i] = orgs.get(i).getId();
					remark += "<" + orgs.get(i).getId() + ">,";
				}
				intKeyLogService.log(null, DeliveryType.ZZ.value, remark);
				return companyId;
			}
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "查询分公司下所有支公司异常，分公司Id为：" + districtCompanyId);
		}
		return null;
	}

	/**
	 * 复审流程操作
	 * 2016年3月8日
	 * By 刘雄武
	 * @param workItem
	 * @param cminfo
	 */
	public void review(WorkItemView workItem, CustomerManagerinfo cminfo) {
		CustomertransferLaunchView bv = new CustomertransferLaunchView();
		if (YoN.SHI.value.equals(cminfo.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_END);
		} else if (YoN.FOU.value.equals(cminfo.getAuditResult())) {
			workItem.setResponse(WorkflowConstant.FLOW_TO_APPLYUSER);
		}
		bv.setCTManagerinfo(cminfo);
		workItem.setBv(bv);
		this.fs.dispatch(workItem);
	}
}