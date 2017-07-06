package com.creditharmony.fortune.change.lender.approve.first.workflow.initdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.change.lender.approve.first.service.LenderChangeApproveFirstManager;
import com.creditharmony.fortune.change.lender.finish.service.LenderChangeFinishManager;

/**
 * 从待办中打开表单
 * @Class Name LenderChangeInfoLoad
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service("load_cf_lenderChenge")
public class LenderApproveFirstFlowLoad extends BaseService implements
		BusinessLoadCallBack {
	
	@Autowired
	private LenderChangeApproveFirstManager applyManager;
	@Autowired
	private LenderChangeFinishManager finishManager;

	/**
	   * 读取业务数据
	   * 2015年12月2日
	   * By 郭才林
	   * @param applyId
	   * @param stepName
	   * @return
	   */
	public BaseBusinessView load(String applyId, String stepName) {
		
		if(stepName!=null){
			return applyManager.loadCustInfo(applyId);// 根据申请单号获取客户信息
		}else{
			return finishManager.openFinish(applyId);// 已办表单
		}
		
	}
}