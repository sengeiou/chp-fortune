package com.creditharmony.fortune.back.priority.back.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.priority.apply.service.PriorityApplyManager;
import com.creditharmony.fortune.back.priority.back.dao.PriorityBackDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityBackMoneyListDao;
import com.creditharmony.fortune.back.priority.common.dao.PriorityDetailDao;
import com.creditharmony.fortune.back.priority.common.service.PriorityLogManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.back.priority.constant.PbmConstant;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;


/**
 * 优先回款申请（被退回）相关Manager
 * @author 郭强
 * 2017年4月7日
 *
 */
@Service
public class PriorityBackManager extends CoreManager<PriorityBackMoneyListDao, PriorityListItemView>{

	@Autowired
	private PriorityBackMoneyListDao listDao;
	@Autowired
	private PriorityDetailDao detailDao;
	@Autowired
	private PriorityBackDao backDao;
	@Autowired
	private PriorityLogManager priorityLogManager;
	@Autowired
	private CheckManager  checkmanager;
	@Autowired
	private PriorityApplyManager applyManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	/**
	 * 获取列表页对象 
	 * 2017年3月30日
	 * By 郭强
	 * @param page
	 * @param view
	 * @return
	 */
	public Page<PriorityListItemView> findItemList(Page<PriorityListItemView> page,
			PriorityListItemView view) {
		String  orderstr=PbmConstant.THCF;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(), Order.formString(orderstr));
		pageBounds.setCountBy("priorityId");
		//获取数据权限
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		logger.info("加载出借申请查看列表，所赋予权限是：" + dataRights);
		if(StringUtils.isNotEmpty(dataRights)){
			view.setDataRights(dataRights);
		}
		User currentUser = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		view.setUserId(currentUser.getId());
		PageList<PriorityListItemView> dataList = (PageList<PriorityListItemView>) backDao.findByParams(view, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}


	/**
	 * 获取被退回详情页
	 * @param priorityId
	 * @return
	 */
	public PriorityDetailItem getDetail(String priorityId) {
		// TODO Auto-generated method stub
		return detailDao.getDetail(priorityId);
	}


	/**
	 * 提交
	 * 2017年4月9日
	 * 郭强
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String applyConfirm(PriorityDetailItem view) {
		StringBuffer sb = new StringBuffer();
		User currentUser = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		String userId = currentUser.getId();
		view.setPriorityBackState(PriorityBackState.DSP.value);
		view.setRebackFlag("0");
		
		int i = backDao.updateBack(view);
		if(i>0){
			
			PriorityBackLog log = new PriorityBackLog();
			log.setPriorityId(view.getPriorityId());
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckExaminetype(view.getCheckExaminetype());
			log.setPriorityBackState(PriorityBackState.DSP.value);
			log.setApplyBy(view.getApplyBy());
			log.setApplyTime(view.getApplyTime());
			log.setCreateBy(view.getCreateBy());
			log.setModifyBy(userId);
			priorityLogManager.addPriorityLog(log);
			
			checkmanager.addCheck(view.getLendCode(), "待审批", "优先回款申请",userId );
			if(StringUtils.isNotEmpty(view.getAddAttachmentId())){
				//同时更新附件表
				List<String>  IdList = Arrays.asList(view.getAddAttachmentId().split(","));
				Map<String ,Object> map = new HashMap<String, Object>();
				map.put("atta_file_owner", "tz.t_tz_priority_back_pool");
				map.put("atta_table_id", view.getPriorityId());
				map.put("loan_code", view.getLendCode());
				
				map.put("IdList", IdList);
				
				loanApplyDao.updateFile(map);
			}
			if(StringUtils.isNotEmpty(view.getDeleteAttachmentId())){
				//删除附件
				List<String>  ddeleteIdList = Arrays.asList(view.getDeleteAttachmentId().split(","));
				Map<String ,Object> map = new HashMap<String, Object>();
				map.put("ddeleteIdList", ddeleteIdList);
				
				loanApplyDao.deleteFile(map);
			}
			
		}else{
			sb.append("操作失败");
		}
		return sb.toString();
	}

	/**
	 * 撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	public String revocationApply(PriorityListItemView view) {
		return applyManager.revocationApply(view);
	}
}
