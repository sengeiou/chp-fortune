package com.creditharmony.fortune.donation.approve.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.Customertransfe;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;

/**
 * 转赠初审servise
 * @Class Name DonationApproveManager
 * @author 刘雄武
 * @Create In 2016年4月19日
 */
@Service
public class DonationApproveManager extends CoreManager<CustomertransferDao, Customertransfe>{

	@Resource
	private CustomertransferDao ctdao;
	@Autowired
	private IntKeyLogService intKeyLogService;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	
	/**
	 * 获取初审复审表单数据
	 * 2016年3月7日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public BaseBusinessView loadCustomerInfo(String applyId) {
		CustomertransferLaunchView view = new CustomertransferLaunchView();
		try {
			CustomerManagerinfo customerinfo = ctdao.getDeliveryinfobyID(applyId);
			customerinfo.setApplyId(applyId);
			view.setCTManagerinfo(customerinfo);
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "获取初审复审表单数据异常");
		}
		return view;
	}
	
	/**
	 * 初审操作
	 * 2016年4月11日
	 * By 刘雄武
	 * @param view
	 * @param response
	 */
	public void saveApproveInfofirst(CustomertransferLaunchView view, String response) {
		CustomerManagerinfo ctManagerinfo = view.getCTManagerinfo();
		ctManagerinfo.setModifyBy(UserUtils.getUserId());
		ctManagerinfo.setModifyTime(new Date());
		// 审批不同意
		String remark = "";
		if(WorkflowConstant.FLOW_TO_APPLYUSER.equals(response)){
			ctManagerinfo.setTransferState("2");
			ctManagerinfo.preUpdate();
			ctdao.updatecustomerinfo(ctManagerinfo);
			ctdao.updatedelivery(ctManagerinfo);
			remark = ctManagerinfo.getCustCode() + " 营业部初审驳回！";
		// 复审操作
		}else if(WorkflowConstant.FLOW_TO_APPROVE.equals(response)){
			remark = getRemark(ctManagerinfo.getCustCode());
		}
		intKeyLogService.log(null, DeliveryType.ZZ.value, remark);
	}

	private String getRemark(String customerCode) {
		CustomerManagerinfo cminfo = ctdao.getCustomerManagerbyCode(customerCode);
		String remark = cminfo.getCustCode() + " 营业部初审成功，继续复审操作，";
		List<String> orgs = new ArrayList<String>();
		// 获取支公司
		FortuneOrg org = RelationShipUtil.getCityOrg(cminfo.getOldmanagerId());
		if (org != null) {
			orgs.add(org.getId());
		}
		List<UserRoleOrg> list = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
		if (ArrayHelper.isNotEmpty(list)) {
			remark+="支公司复审操作人如下：";
			for (UserRoleOrg userRoleOrg : list) {
				remark += "<" + userRoleOrg.getUserId() + ">,";
			}
		} else {
			// 获取分公司
			orgs.clear();
			FortuneOrg orgd = RelationShipUtil.getDistrictOrg(cminfo.getOldmanagerId());
			if (orgd != null) {
				orgs.add(orgd.getId());
			}
			List<UserRoleOrg> listF = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
			if (ArrayHelper.isNotEmpty(listF)) {
				remark+="分公司复审操作人如下：";
				for (UserRoleOrg userRoleOrg : listF) {
					remark += "<" + userRoleOrg.getUserId() + ">,";
				}
			} else {
				remark += "支分公司无审核人！";
			}
		}
		return remark;
	}
}