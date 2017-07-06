package com.creditharmony.fortune.back.priority.apply.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.fortune.back.priority.apply.service.PriorityApplyManager;
import com.creditharmony.fortune.back.priority.common.service.PbmManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.utils.StringExUtil;

/**
 * 不带事务service
 * @author 郭强
 * 2017年4月1日
 */
@Service
public class MultiManager {

	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(PriorityApplyManager.class);
	@Autowired
	private  PriorityApplyManager  pam;
	@Autowired
	private  PbmManager  pbm;
	
	/**
	 * 批量撤销申请
	 * 2017年3月31日
	 * 郭强 
	 * @param view
	 * @return
	 */
	public String multiRevocation(PriorityListItemView view) {
		String rtn = "操作成功";//返回内容
		String rtnLendCode="";
		String transferCheck="";
		//获取要操作的数据
		if (StringUtils.isNotEmpty(view.getPrioIds())) {
			String[] ids = view.getPrioIds().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			view.setPriorityIds(idList);
		}
		
		List<PriorityDetailItem>  dataList = pbm.getPriortyList(view);
		
		if (ObjectHelper.isEmpty(dataList)) {
			rtn = "批量撤销失败";
		}
		
		PriorityListItemView  resultView = new PriorityListItemView();
		
		for (PriorityDetailItem pdim : dataList) {
			try {
				
				resultView.setPriorityId(pdim.getPriorityId());
				resultView.setPriorityBackState(view.getPriorityBackState());
				resultView.setLendCode(pdim.getLendCode());
				resultView.setCustomerCode(pdim.getCustomerCode());
				transferCheck += pam.revocationApply(resultView);

			} catch (Exception e) {
				
				logger.debug(e.getMessage());
				logger.error("申请ID为" + pdim.getPriorityId() + "的撤销处理失败，原因：" + e.getMessage());
				rtnLendCode += pdim.getLendCode() +",";
			}
		}
		
		if (StringUtils.isNotEmpty(rtnLendCode)) {
			rtnLendCode = StringExUtil.trimLast(rtnLendCode, ",");
			rtnLendCode += "处理失败";
		}
		if (StringUtils.isNotEmpty(transferCheck) || StringUtils.isNotEmpty(rtnLendCode)) {
			rtn = transferCheck + rtnLendCode;
		}
		return rtn;
	}

	/**
	 * 对数据的状态进行校验
	 * @param view
	 * @return
	 */
	public String checkoutState(PriorityListItemView view) {
		StringBuffer  sb = new StringBuffer();
		//获取要操作的数据
		if (StringUtils.isNotEmpty(view.getPrioIds())) {
			String[] ids = view.getPrioIds().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			view.setPriorityIds(idList);
		}
		
		List<PriorityDetailItem>  dataList = pbm.getPriortyList(view);
		for (PriorityDetailItem pdit : dataList) {
			if(PriorityBackState.DSP.value.equals(pdit.getPriorityBackState())){
				sb.append("包含待审批的数据");
			}
			if(PriorityBackState.YCX.value.equals(pdit.getPriorityBackState())){
				sb.append("包含撤销的数据");
			}
			if(PriorityBackState.SPWTG.value.equals(pdit.getPriorityBackState())){
				sb.append("包含审批未通过的数据");
			}
			boolean  backStatelogic = BackState.DHKSQ.value.equals(pdit.getDictBackStatus()) ||
					  BackState.DHKSQQRTH.value.equals(pdit.getDictBackStatus());
			if(!backStatelogic){
				sb.append("回款状态发不是 待回款申请 或者 待回款申请确认退回");
			}
		}
		return sb.toString();
	}
	
}
