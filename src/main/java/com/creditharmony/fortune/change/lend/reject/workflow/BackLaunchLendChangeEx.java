package com.creditharmony.fortune.change.lend.reject.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.reject.manager.LendChangeRejectManager;

/**
 * 重新发起流程
 * @Class Name BackLaunchLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月16日
 */
@Service("ex_cf_lendChange_backlaunch")
public class BackLaunchLendChangeEx  implements ExEvent{
    
	@Autowired
	private LendChangeRejectManager lcmService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		
		LendLaunchView view= (LendLaunchView) workItem.getBv();
		lcmService.updateChangeInfo(view);// 修改变更信息
		
	}

}
