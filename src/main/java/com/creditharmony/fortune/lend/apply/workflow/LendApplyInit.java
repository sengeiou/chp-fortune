package com.creditharmony.fortune.lend.apply.workflow;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.InitViewData;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;

/**
 * 出借申请加载事件
 * 
 * @Class Name LendApplyInit
 * @author 孙凯文
 * @Create In 2015年12月22日
 */
@Service("init_cf_lendApply_applyForm")
public class LendApplyInit extends BaseService implements InitViewData {
	@Autowired
	private LendApplyManager lendApplyManager;

	/**
	 * 2015年12月22日
	 * 
	 * @author 孙凯文
	 * @param parameterMap
	 * @return BaseBusinessView
	 */
	@SuppressWarnings("rawtypes")
	public BaseBusinessView initViewData(Map parameterMap) {
		String[] arr = (String[]) parameterMap.get("customerCode");
		String customerCode = arr[0];
		LoanApply apply = new LoanApply();
		apply.setCustCode(customerCode);
		return lendApplyManager.openLaunchForm(apply);
	}

}
