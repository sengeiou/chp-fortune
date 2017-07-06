package com.creditharmony.fortune.change.lend.approve.workflow;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.approve.manager.LendChangeApproveManager;

/**
 * 门店经理初审
 * @Class Name ApproveFirstLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月4日
 */
@Service("ex_cf_lendChange_approve_first")
public class ApproveFirstLendChangeEx  implements ExEvent{
    
	@Resource
	private LendChangeApproveManager lcmService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		
		LendLaunchView view=(LendLaunchView) workItem.getBv();
		
		lcmService.saveApproveInfo(view.getChangeLog(),workItem.getResponse());// 保存变更信息
		
	}

}
