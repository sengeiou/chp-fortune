package com.creditharmony.fortune.sync.data.proxy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.donation.approve.entity.CustomertransferTaskView;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveFlowManager;
import com.creditharmony.fortune.donation.finalapprove.manager.DonationFinalApproveFlowManager;
import com.creditharmony.fortune.exituserorg.entity.UserOrgInfo;
import com.creditharmony.fortune.exituserorg.service.UserOrgInfoService;
import com.creditharmony.fortune.users.entity.OrgInfo;
import com.creditharmony.fortune.users.service.OrgInfoService;
import com.query.ProcessQueryBuilder;

@Component
public class FortuneSyncDataEndAudit {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private DonationApproveFlowManager donationApproveFlowManager;
	@Autowired
	private DonationFinalApproveFlowManager donationFinalApproveFlowManager;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	@Autowired
	private OrgInfoService orgInfoService;
	@Autowired
	private UserOrgInfoService userOrgInfoService;

	public void endAuditApproach() {
		UserOrgInfo info = new UserOrgInfo();
		List<UserOrgInfo> list = userOrgInfoService.findList(info);
		if (ArrayHelper.isNotEmpty(list)) {
			for (UserOrgInfo userOrgInfo : list) {
				if (StringUtils.isNotEmpty(userOrgInfo.getOrgId())) {
					end(userOrgInfo.getOrgId());
					info.setId(userOrgInfo.getId());
					userOrgInfoService.delete(info);
				}
			}
		}
	}

	private void end(String orgId) {
		// 获取修改用户所在机构
		OrgInfo orgInfo = orgInfoService.get(orgId);
		if (orgInfo != null) {
			List<String> orgs = new ArrayList<String>();
			if (FortuneOrgType.STORE.key.equals(orgInfo.getType())) {
				orgs.add(orgInfo.getId());
				List<UserRoleOrg> firstList = userRoleOrgDao.checkHaveAuditor(orgs, "f9a04014fd3d4feb99917e37384ba015");
				if (ArrayHelper.isEmpty(firstList)) {
					// 获取营业厅所有初审流程
					List<? extends BaseTaskItemView> voList = getApproceProcess(orgInfo.getId());
					if (ArrayHelper.isNotEmpty(voList)) {
						for (BaseTaskItemView vo : voList) {
							donationApproveFlowManager.dispatch(vo.getApplyId(), vo.getWobNum(), vo.getToken(), null);
						}
					}
				}
			} else if (FortuneOrgType.CITY.key.equals(orgInfo.getType())) {
				// 获取支公司
				orgs.add(orgInfo.getId());
				List<UserRoleOrg> reviewListZ = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
				// 如果支公司复审只有自己，判断分公司
				if (ArrayHelper.isEmpty(reviewListZ)) {
					orgs.clear();
					// 获取分公司
					orgs.add(orgInfo.getParentId());
					List<UserRoleOrg> reviewListF = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
					// 如果分公司为空，结束所有分公司转赠流程
					if (ArrayHelper.isEmpty(reviewListF)) {
						List<? extends BaseTaskItemView> voList = getFinalApproveProcess(orgInfo.getParentId());
						if (ArrayHelper.isNotEmpty(voList)) {
							for (BaseTaskItemView vo : voList) {
								donationApproveFlowManager.dispatch(vo.getApplyId(), vo.getWobNum(), vo.getToken(), null);
							}
						}
					}
				}
			} else if (FortuneOrgType.DISTRICT.key.equals(orgInfo.getType())) {
				orgs.add(orgInfo.getId());
				List<UserRoleOrg> reviewListF = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
				if (ArrayHelper.isEmpty(reviewListF)) {
					// 获取分公司所有初审流程
					List<? extends BaseTaskItemView> voList = getFinalApproveProcess(orgInfo.getId());
					if (ArrayHelper.isNotEmpty(voList)) {
						for (BaseTaskItemView vo : voList) {
							donationApproveFlowManager.dispatch(vo.getApplyId(), vo.getWobNum(), vo.getToken(), null);
						}
					}
				}
			}
		}
	}

	private List<? extends BaseTaskItemView> getApproceProcess(String orgId) {
		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		pq.put("department", orgId);
		return fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_SINGLE, pq, CustomertransferTaskView.class).getItemList();
	}

	private List<? extends BaseTaskItemView> getFinalApproveProcess(String orgId) {
		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		pq.put("branchCompanyId", donationFinalApproveFlowManager.getBranchCompanyIds(orgId));
		return fs.fetchTaskItems(WorkflowConstant.FLOW_CF_GRAP_APPROVE, pq, CustomertransferTaskView.class).getItemList();
	}
}