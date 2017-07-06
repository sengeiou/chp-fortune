package com.creditharmony.fortune.donation.finalapprove.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.delivery.facade.TripleCustomerDeliveryFacade;
import com.creditharmony.fortune.donation.apply.dao.CustomertransferDao;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;

/**
 * 转赠复审servise
 * @Class Name DonationFinalApproveManager
 * @author 刘雄武
 * @Create In 2016年4月19日
 */
@Service
public class DonationFinalApproveManager {

	@Resource
	private CustomertransferDao ctdao;
	// 三网接口交割客户
	@Resource
	TripleNewCustomerService tripleNewCustomerService;
	@Autowired
	private TripleCustomerDeliveryFacade tripleCustomerDeliveryFacade;
	@Autowired
	private IntKeyLogService intKeyLogService;
	
	/**
	 * 审批完成更新交割表和客户表数据
	 * 2016年3月7日
	 * By 刘雄武
	 * @param view
	 * @param response
	 */
	public void saveApproveInfoReview(CustomertransferLaunchView view, String response) {
		CustomerManagerinfo ctManagerinfo = view.getCTManagerinfo();
		ctManagerinfo.setModifyBy(UserUtils.getUserId());
		ctManagerinfo.setModifyTime(new Date());
		String remark = "";
		// 审批不同意
		if(WorkflowConstant.FLOW_TO_APPLYUSER.equals(response)){
			ctManagerinfo.setTransferState("2");
			ctManagerinfo.preUpdate();
			ctdao.updatecustomerinfo(ctManagerinfo);
			ctdao.updatedelivery(ctManagerinfo);
			remark = ctManagerinfo.getCustCode() + " 复审驳回！";
		// 审批通过
		}else if(WorkflowConstant.FLOW_TO_END.equals(response)){
			ctManagerinfo.setTransferState("3");
			ctManagerinfo.preUpdate();
			ctdao.updatecustomer(ctManagerinfo);// 修改客户表
			ctdao.updatedelivery(ctManagerinfo);// 修改交割表
			
			CustomerManagerinfo cmi=ctdao.getManagerinfobyID(ctManagerinfo);
			tripleCustomerDeliveryFacade.upadateEmpCodeById(ctManagerinfo.getCustMobilephone(), cmi.getManagerCode(),"转赠");// 进行三网交割
			remark = ctManagerinfo.getCustCode() + " 复审通过！";
		}
		intKeyLogService.log(null, DeliveryType.ZZ.value, remark);
	}
}