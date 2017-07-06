package com.creditharmony.fortune.lend.finish.manager;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.utils.AttachmentUtil;

@Service
public class LendFinishFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Resource(name = "ApplyIdServiceImpl")
	private ApplyIdService applyIdService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private LoanApplyDao dao;
	@Autowired
	private AttachmentManager attachmentManager;
	
	/**
	 * 资金托管金账户发起流程
	 * 2016年4月19日
	 * By 郭才林
	 * @param workItem
	 * @param loanApply
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String launchFlow(WorkItemView workItem, LoanApply loanApply,LendApplyView view) {

		if(view==null){
			view=new LendApplyView();
		}
		view.setApplyId(loanApply.getApplyId());
		Customer cust=new Customer();
		cust.setCustCode(loanApply.getCustCode());
		Customer customer = customerDao.getCustomerbyCode(cust);
		LoanApply lendApplyDB = dao.get(loanApply);
		view.setCustomer(customer);
		view.setLendApply(lendApplyDB);
		view.setApplyDate(new Date());
		view.setOrg(new Org());
		view.setCreateDate(new Date());
		view.setLendMoney(Double.valueOf(view.getLendApply().getLendMoney()
				.toString()));
		FortuneOrg storeOrg = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		if (null != storeOrg) {
			view.setProvince(storeOrg.getProvinceId());
			view.setCity(storeOrg.getCityId());
		}
		view.setFinancialManager(lendApplyDB.getManagerCode());

		// 生成出借合同
		boolean r=lendApplyManager.makingFile(loanApply, customer);
		if(r){
	
			// 附件表更新
			AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(),
					view.getDeleteAttachmentId(), loanApply.getApplyCode(), loanApply.getId(),
					"t_tz_loan_apply");
			// CE附件移动
			DmService dmService = DmService.getInstance();
			List<String> addAttachmentIdList = view.getAddAttachmentId();
			if(null != addAttachmentIdList){
				for (String addAttachmentId : addAttachmentIdList) {
					Attachment attachment = attachmentManager.get(addAttachmentId);
					if(attachment!=null){
						dmService.moveDocument(attachment.getAttaFilepath(),customer.getCustCode()+"/"+loanApply.getApplyCode(),DmService.BUSI_TYPE_FORTUNE);
					}					
				}
			}
			
			// 提交流程
			workItem.setResponse(CustomerConstants.WorkFlow.CONTRACT_FLOW);
			workItem.setBv(view);
			this.fs.launchFlow(workItem);
		}else{
			return "合同申请书合成失败,请在已办列表重新合成!";
		}
			
		return "";
	}
}
