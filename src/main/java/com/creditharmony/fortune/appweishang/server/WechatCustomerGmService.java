package com.creditharmony.fortune.appweishang.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.wechat.WechatCustomerGmBaseService;
import com.creditharmony.adapter.service.wechat.bean.WechatCustomerGmInParam;
import com.creditharmony.adapter.service.wechat.bean.WechatCustomerGmOutParam;
import com.creditharmony.fortune.appweishang.bean.EmpUser;

@Service
@Transactional(value = "fortuneTransactionManager")
public class WechatCustomerGmService extends WechatCustomerGmBaseService  {
	
	@Autowired
	private EmpUserServer empUserServer;

	@Override
	public WechatCustomerGmOutParam doExec(WechatCustomerGmInParam arg0) {
		WechatCustomerGmOutParam wechatCustomerGmOutParam = null;
		String userCode = arg0.getManagerId();
		try {
			EmpUser empuser = empUserServer.getEMPuserInfo(userCode);
			wechatCustomerGmOutParam = new WechatCustomerGmOutParam();
			if(empuser!=null){
				wechatCustomerGmOutParam.setName(empuser.getName());
				wechatCustomerGmOutParam.setMobile(empuser.getMobile());
				if(empuser.getStatus().equals("1") && empuser.getDelFlag().equals("0")){
					wechatCustomerGmOutParam.setRetMsg("员工在职");
					wechatCustomerGmOutParam.setRetCode(ReturnConstant.SUCCESS);
				}else if(empuser.getStatus().equals("0") && empuser.getDelFlag().equals("1")){
					wechatCustomerGmOutParam.setRetMsg("员工离职");
					wechatCustomerGmOutParam.setRetCode(ReturnConstant.FAIL);
				}
			}else{
				wechatCustomerGmOutParam.setRetMsg("员工不存在");
				wechatCustomerGmOutParam.setRetCode(ReturnConstant.FAIL);
			}
		} catch (Exception e) {
			wechatCustomerGmOutParam.setRetCode(ReturnConstant.ERROR);
			wechatCustomerGmOutParam.setRetMsg("DB操作系统例外.");
			e.printStackTrace();
		}
		return wechatCustomerGmOutParam;
	}

}
