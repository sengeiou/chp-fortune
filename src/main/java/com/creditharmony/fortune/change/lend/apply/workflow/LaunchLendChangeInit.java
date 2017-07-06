package com.creditharmony.fortune.change.lend.apply.workflow;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.InitViewData;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.change.lend.apply.manager.LendChangeApplyManager;

/**
 * 初始化申请信息
 * @Class Name LaunchLendChangeInit
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service("init_cf_lendChange_applyForm")
public class LaunchLendChangeInit extends BaseService implements InitViewData {
	
	@Resource
	private LendChangeApplyManager lecservice;

	@Override
	@SuppressWarnings("rawtypes")
	public BaseBusinessView initViewData( Map parameterMap) {
		
		String[] ptm= (String[])parameterMap.get("applyCode");
		String applyCode=ptm[0];
		return lecservice.getLendLoanApply(applyCode);
	}
}
