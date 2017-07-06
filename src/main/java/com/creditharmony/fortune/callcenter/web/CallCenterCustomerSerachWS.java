package com.creditharmony.fortune.callcenter.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.csh.CshSearchCustomerByCFBaseService;
import com.creditharmony.adapter.service.csh.bean.Csh_SearchCustomerByCFInBean;
import com.creditharmony.adapter.service.csh.bean.Csh_SearchCustomerDeatilByCFOutBean;
import com.creditharmony.fortune.callcenter.service.CallCenter4WSManager;

/**
 * 呼叫中心查询客户信息
 * @Class Name CallCenterCustomerSerachWS
 * @author 肖长伟
 * @Create In 2016年3月3日
 */
@Service
public class CallCenterCustomerSerachWS extends	CshSearchCustomerByCFBaseService {
	
	@Autowired
	private CallCenter4WSManager callCenter4WSManager;
	
	/**
	 * 呼叫中心查询客户
	 */
	@Override
	public List<Csh_SearchCustomerDeatilByCFOutBean> doExec(Csh_SearchCustomerByCFInBean seachConditionBean) {
		//TODO  --呼叫中心查询
		Map<String, Object> params = new HashMap<String, Object>();
		//customerName、phone、certNum、accountNo、storeName
		params.put("customerName", seachConditionBean.getCustomerName());
		params.put("phone", seachConditionBean.getCustomerPhone());
		params.put("certNum", seachConditionBean.getCustomerCertNum());
		params.put("accountNo", seachConditionBean.getCustomerBankCardNo());
		params.put("storeName", seachConditionBean.getStoresName());
		
		return callCenter4WSManager.searchCustomerInfor( params);
	}

	


}
