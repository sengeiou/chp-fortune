package com.creditharmony.fortune.back.priority.apply.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.priority.common.dao.PriorityDetailDao;
import com.creditharmony.fortune.back.priority.common.service.PbmManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;

/**
 * 带事务Service，用于处理单条数据
 * @Class Name PriorityApplyManager
 * @author 郭强
 * @Create In 2017年3月31日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class PriorityApplyManager {
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(PriorityApplyManager.class);
	
	@Autowired
	private  PriorityDetailDao  pdao;
	@Autowired
	private PbmManager  pbm;
	@Autowired
	private CheckManager  checkmanager;
	@Autowired
	private LoanApplyDao  loanApply;
	
	/**
	 * 撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String revocationApply(PriorityListItemView view) {
		view.setPriorityBackState(PriorityBackState.YCX.value);
		PriorityBackLog  log = new PriorityBackLog();
		log.setApplyBy(view.getCustomerCode());
		if(StringUtils.isEmpty(view.getCustomerCode())||StringUtils.isEmpty(view.getLendCode())){
			PriorityDetailItem  item = pbm.getDetail(view.getPriorityId());
			log.setApplyBy(item.getCustomerCode());
			view.setLendCode(item.getLendCode());
		}
		
		String  str="";
		String userId = UserUtils.getUserId();
		view.setCreateBy(userId);
		int  count = pdao.updateApply(view);
		if (count == 0) {
			str=view.getLendCode()+"撤销失败";
			//BmUtils.throwDataOutOfDateException();
		}else{
			PriorityBackLog  plog =pdao.searchLog(view);
			
			checkmanager.addCheck(view.getLendCode(), "优先回款撤销", "优先回款撤销",userId );
			Date  date = new Date();
			log.setId(IdGen.uuid());
			log.setCreateBy(userId);
			log.setCreateTime(date);
			log.setApplyTime(date);
			log.setModifyTime(date);
			log.setCheckById(plog.getCheckById());
			log.setCheckTime(plog.getCheckTime());
			log.setPriorityId(view.getPriorityId());
			pdao.insertLog(log);
			
			//修改回款表, 将优先回款状态和 实际回款金额修改
			pbm.updateRepealBackMoney(view.getPriorityId());
			Map<String , Object> map =new HashMap<String, Object>();
			map.put("priorityId", view.getPriorityId());
			map.put("lendCode", view.getLendCode());
			map.put("attaFileOwner", "tz.t_tz_priority_back_pool");
			
			//更新附件
			loanApply.deleteFile(map);
		}
		return str;
	}

}
