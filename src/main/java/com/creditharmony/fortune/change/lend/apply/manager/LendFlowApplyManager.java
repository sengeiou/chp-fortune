package com.creditharmony.fortune.change.lend.apply.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;

/**
 * 出借信息变更申请流程Service层
 * @Class Name LendFlowManager
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@Service
public class LendFlowApplyManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	
	@Resource(name = "ApplyIdServiceImpl")
	private ApplyIdService apid;
	
	/**
	 * 发起流程
	 * 2015年12月3日
	 * By 刘雄武
	 * @param workItem
	 * @param bv
	 */
	public void launchFlow(WorkItemView workItem,LendLaunchView bv){
		String applyId = apid.builderApplyId(workItem.getFlowType());
		bv.setApplyDate(new Date());
		bv.setApplyId(applyId);	
		bv.getLendchangeinfo().setApplyId(applyId);
		bv.getLendloanapply().setCreateTime(new Date());
		workItem.setBv(bv);
		this.fs.launchFlow(workItem);
	}
}
