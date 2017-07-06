package com.creditharmony.fortune.appweishang.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.wechat.WechatConsultBaseService;
import com.creditharmony.adapter.service.wechat.bean.WechatConsultInParam;
import com.creditharmony.adapter.service.wechat.bean.WechatConsultOutParam;
import com.creditharmony.fortune.appweishang.bean.UserConsultation;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;

@Service
@Transactional(value = "fortuneTransactionManager")
public class WechatConsultService extends WechatConsultBaseService {
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private UserConsultationServer userConsultationServer;

	@Override
	public WechatConsultOutParam doExec(WechatConsultInParam paramBean) {
		WechatConsultOutParam wechatConsultOutParam = null;
		try {
			String myname = paramBean.getMyname();
			String mobile = paramBean.getMobile();
			String consultContent = paramBean.getConsultContent();
			String managerId = paramBean.getManagerId();
			
			UserConsultation userConsultation  = new UserConsultation();	
			userConsultation.setName(myname);
			userConsultation.setMobile(mobile);
			userConsultation.setConsultContent(consultContent);
			String empCode = sendTripleInfoFacade.getEmpCode(mobile);
			if(empCode == null || "".equals(empCode)){
				userConsultation.setManagerId(managerId);
			}else if(!"".equals(empCode) && empCode != null && !empCode.equals(managerId)){
				userConsultation.setManagerId(empCode);
			}
			int issucessful = userConsultationServer.insertConsultationUserInfo(userConsultation);
			
			wechatConsultOutParam = new WechatConsultOutParam();
			if(issucessful > 0){
				wechatConsultOutParam.setRetCode(ReturnConstant.SUCCESS);
			}else{
				wechatConsultOutParam.setRetCode(ReturnConstant.FAIL);
			}
		} catch (Exception e) {
			wechatConsultOutParam.setRetCode(ReturnConstant.ERROR);
			wechatConsultOutParam.setRetMsg("DB操作系统例外.");
			e.printStackTrace();
		}
		return wechatConsultOutParam;
	}
	

}
