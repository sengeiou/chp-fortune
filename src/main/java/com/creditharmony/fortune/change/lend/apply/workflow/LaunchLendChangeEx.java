package com.creditharmony.fortune.change.lend.apply.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.manager.LendChangeApplyManager;

/**
 * 出借信息变更申请实现ExEven接口流程
 * @Class Name LaunchLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月2日
 */
@Service("ex_cf_lendChange_launch")
public class LaunchLendChangeEx  implements ExEvent{

	@Resource
	private LendChangeApplyManager lecService;
	@Override
	public String getBeanName() {
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		LendLaunchView view = (LendLaunchView) workItem.getBv();
	
		lecService.saveLendChangeInfo(view);//保存变更信息
		
	}

}
