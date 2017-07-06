package com.creditharmony.fortune.donation.approve.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveManager;
/**
 * 从代办打开初审复审已办
 * @Class Name LendChangeInfoLoad
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service("load_cf_customertransfer")
public class CustomertransferLoad extends BaseService implements BusinessLoadCallBack {

	@Resource
	private DonationApproveManager ctservice;
	@Override
	public BaseBusinessView load(String applyId, String stepName) {
		
			return ctservice.loadCustomerInfo(applyId) ;
	}
}
