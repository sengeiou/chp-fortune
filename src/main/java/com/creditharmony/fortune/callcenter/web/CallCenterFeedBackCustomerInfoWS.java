package com.creditharmony.fortune.callcenter.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.csh.CshCallOnCustomerByCFBaseService;
import com.creditharmony.adapter.service.csh.bean.Csh_CallOnCustomerByCFInBean;
import com.creditharmony.adapter.service.csh.bean.Csh_CallOnCustomerByCFOutBean;
import com.creditharmony.fortune.callcenter.service.CallCenter4WSManager;

/**
 * 呼叫中心客户回访
 * 
 * 回访针对新客户，不区分哪个渠道过来的客户
 * 
 * 出借日期
 * 
 * @Class Name CallCenterFeedBackCustomerInfoWS
 * @author 肖长伟
 * @Create In 2016年3月3日
 */
@Service
public class CallCenterFeedBackCustomerInfoWS extends	CshCallOnCustomerByCFBaseService {
	@Autowired
	private CallCenter4WSManager callCenter4WSManager;
	/**
	 * 呼叫中心客户信息回访
	 */
	@Override
	public List<Csh_CallOnCustomerByCFOutBean> doExec(Csh_CallOnCustomerByCFInBean feedBackConditionBean) {
		//TODO  -- 回访
		String timeCon = feedBackConditionBean.getCallOnDate();
		String startDateTime = timeCon.trim() + " 00:00:00";
		String endDateTime = timeCon.trim() + " 23:59:59";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDateTime", startDateTime);
		params.put("endDateTime", endDateTime);
		return callCenter4WSManager.feedBackCustomerInfo(params);
	}
	

}
