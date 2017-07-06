package com.creditharmony.fortune.donation.apply.workflow;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.InitViewData;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.donation.apply.manager.CustomerTransferManager;

/**
 * 初始化申请信息
 * @Class Name LaunchLendChangeInit
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service("init_cf_customertransfer_applyForm")
public class LaunchCustomertransferInit extends BaseService implements InitViewData {
	
	@Resource
	private CustomerTransferManager ctservice;

	@Override
	@SuppressWarnings("rawtypes")
	public BaseBusinessView initViewData( Map parameterMap) {
		
		String[] ptm= (String[])parameterMap.get("custCode");
		String custCode=ptm[0];
		return ctservice.getCustomerManagerbyCode(custCode);
	}
}
