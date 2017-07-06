package com.creditharmony.fortune.change.lend.approve.workflow;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.change.lend.approve.manager.LendChangeApproveManager;

/**
 * 从代办打开初审复审已办
 * @Class Name LendChangeInfoLoad
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service("load_cf_lendChenge")
public class LendChangeInfoLoad extends BaseService implements BusinessLoadCallBack {

	@Resource
	private LendChangeApproveManager lecservice;
	@Override
	public BaseBusinessView load(String applyId, String stepName) {
		if(stepName!=null){
			return lecservice.getLendChangeInfo(applyId) ;
		}else{
			return lecservice.openFinish(applyId);
		}
//		LendLoadView view =lecservice.getLendChangeInfo(applyId) ;
//		return view;
		
	}
}
