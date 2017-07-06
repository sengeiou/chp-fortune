package com.creditharmony.fortune.trusteeship.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.reject.service.LenderChangeRejectManager;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.constants.WorkflowConstant;
import com.creditharmony.fortune.trusteeship.dao.TrusteeshipAccountDao;
import com.creditharmony.fortune.trusteeship.entity.ChangeCount;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipAccount;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeLoadView;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeTaskView;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipQueryView;
import com.query.ProcessQueryBuilder;

/**
 * 金账户变更
 * @Class Name TrusteeshipChangeManager
 * @author 郭才林
 * @Create In 2016年2月24日
 */
@Service
public class TrusteeshipChangeManager extends CoreManager<TrusteeshipAccountDao, TrusteeshipAccount>{
	
	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;	
	@Autowired
	protected LenderChangeRejectManager rejectManager;
	
	/**
	 * 金账户处理
	 * 2016年4月13日
	 * By 郭才林
	 * @param flowInfo
	 * @param oper
	 * @param log
	 */
	public void dispatch(String flowInfo, String oper,LenderChangeLog log) {

		 flowInfo=flowInfo.replaceAll("&quot;", "\"");
		 JsonMapper jsonMapper= new JsonMapper();
		 List<WorkItemView> workItems=jsonMapper.fromJson(flowInfo, jsonMapper.createCollectionType(List.class, WorkItemView.class));
		
		 for (WorkItemView workItem : workItems) {
			 
			  TrusteeshipChangeLoadView bv=new TrusteeshipChangeLoadView();
			  bv.setApplyId(workItem.getFlowId());
			  bv.setChangerType(workItem.getFlowType());
			  workItem.setFlowId(null);
			  workItem.setFlowType(null);
			  workItem.setBv(bv);
			  bv.setLog(log);
			 	// 根据审批结果设置对应的response值控制路由走向
				if (YoN.SHI.value.equals(oper)) {
					
					workItem.setResponse(WorkflowConstant.FLOW_TO_END);
				} else if (YoN.FOU.value.equals(oper)) {
					workItem.setResponse(WorkflowConstant.FLOW_TO_SECOND_APPROVAL);
				}

				this.fs.dispatch(workItem);
		}
		
	}
	
	/**
	 * 金账户变更列表
	 * 2016年4月13日
	 * By 郭才林
	 * @param search
	 * @param flowPage 
	 * @param page 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TrusteeshipChangeTaskView> getList(TrusteeshipQueryView search, Page<TrusteeshipQueryView> page, FlowPage flowPage){
		ProcessQueryBuilder pq = new ProcessQueryBuilder();
		if(search.getCustomerCode()!=null&&!"".equals(search.getCustomerCode())){	
			pq.put("customerCode@like", "%"+search.getCustomerCode()+"%");
		}
		if(search.getCustomerName()!=null&&!"".equals(search.getCustomerName())){
			pq.put("customerName@like", "%"+search.getCustomerName()+"%");	
		}
		if(search.getBankId()!=null&&!"".equals(search.getBankId())){
			pq.put("accountBankId",search.getBankId().split(",") );
		}
		if(search.getChangeType()!=null&&!"".equals(search.getChangeType())){
			pq.put("changerTypeVal", search.getChangeType());
		}
		if(null == page){
			
			TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_TRUSTEESHIP_CHANGE_APPROVE, pq, TrusteeshipChangeTaskView.class);
			List<TrusteeshipChangeTaskView> listview =(List<TrusteeshipChangeTaskView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(listview) ? listview : null;
		}else{
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(WorkflowConstant.FLOW_CF_TRUSTEESHIP_CHANGE_APPROVE, pq, flowPage,
					null,TrusteeshipChangeTaskView.class);
			@SuppressWarnings("rawtypes")
			List dataList = flowPage.getList();
			return ArrayHelper.isNotEmpty(dataList) ? dataList : null;
		}
		

	}
	

	/**
	 * 获取数据管理部金账户待审批银行卡和手机数目
	 * 2016年3月16日
	 * By 郭才林
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ChangeCount getChangeCount(){
		
		ChangeCount count=new ChangeCount();
		TaskBean taskBean = fs.fetchTaskItems(WorkflowConstant.FLOW_CF_TRUSTEESHIP_CHANGE_APPROVE, null, TrusteeshipChangeTaskView.class);
		List<TrusteeshipChangeTaskView> itemList = new ArrayList<TrusteeshipChangeTaskView>();
		if (taskBean.getItemList() != null) {
			itemList = (List<TrusteeshipChangeTaskView>) taskBean.getItemList();
		}
		int cardChangeCount=0;
		int phoneChangeCount=0;
		for (TrusteeshipChangeTaskView v : itemList) {
			if(LendchgType.TRUSTESSHIP_CARD_CHA.value.equals(v.getChangerTypeVal())){
				cardChangeCount++;
			}else if(LendchgType.TRUSTESSHIP_PHONE_CHA.value.equals(v.getChangerTypeVal())){
				phoneChangeCount++;
			}
		}
		
		count.setPhoneChangeCount(phoneChangeCount);
		count.setCardChangeCount(cardChangeCount);
		return count;
	}
	
	/**
	 * 获取附件
	 * 2016年4月13日
	 * By 郭才林
	 * @param applyIds
	 * @param changeType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Attachment> getAttachmentByApplyIds(String applyIds,String changeType) {
		Map<String, Object> p=new HashedMap();
		String applyIdsArr[]=applyIds.split(",");
		p.put("ids", applyIdsArr);
		p.put("AttaFileOwner", "t_tz_changer");
		p.put("dictAttaFlag", changeType);
		List<Attachment> list = rejectManager.getAttachmentByApplyIds(p);
		return list;
	}
	
}


