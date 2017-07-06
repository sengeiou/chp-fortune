package com.creditharmony.fortune.callcenter.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.csh.CshSaveCustomerByCFBaseService;
import com.creditharmony.adapter.service.csh.bean.Csh_SaveCustomerByCFInBean;
import com.creditharmony.adapter.service.csh.bean.Csh_SaveCustomerOutBean;
import com.creditharmony.fortune.callcenter.service.CallCenter4WSManager;

/**
 * 呼叫中心添加客户信息
 * @Class Name CallCenterCustomerAddWS
 * @author 张鸿飞
 * @Create In 2016年6月3日
 */
@Service
public class CallCenterCustomerAddWS extends CshSaveCustomerByCFBaseService{

	@Autowired
	private CallCenter4WSManager callCenter4WSManager;
	
	@Override
	public Csh_SaveCustomerOutBean doExec(Csh_SaveCustomerByCFInBean customer) {

		Csh_SaveCustomerOutBean customerOutBean = new Csh_SaveCustomerOutBean();
		callCenter4WSManager.addCustomerInfo(customer);
		
		return customerOutBean;
	}

}
