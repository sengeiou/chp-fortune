package com.creditharmony.fortune.lend.reject.workflow;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyFlowDao;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyFlow;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.helper.LendHelper;
import com.creditharmony.fortune.lend.reject.manager.LendRejectManager;

@Service
public class LendRejectFlowManager {

	private Logger logger = LoggerFactory.getLogger(LendRejectFlowManager.class);
	
	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private LendRejectManager lendRejectManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private LendApplyFlowDao lendApplyFlowDao;

	/**
	 * 重新发起流程 2015年12月31日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @param bv
	 * @return none
	 */
	public String reLaunchFlow(WorkItemView workItem, LendApplyView view) {
		
		LoanApply lendApply = view.getLendApply();
		LoanApply lendApplyDB = loanApplyDao.get(lendApply);
		
		String lendCode = lendApply.getApplyCode();
		String productCode = lendApply.getProductCode();
		lendCode = lendCode.replaceFirst("^\\d{2}", productCode);
		String protocoEdition = lendApply.getProtocoEdition().replaceAll(",", "");
		if(protocoEdition.length() > 1){
			lendApply.setProtocoEdition(lendApply.getProtocoEdition().replaceAll(",", "").substring(0,2));
		}else{
			lendApply.setProtocoEdition(protocoEdition);
		}
		
		LendHelper.updatePageProperties(lendApply, lendApplyDB);
		
		lendRejectManager.saveReLaunchForm(view, workItem.getResponse());   	//保存出借信息
		
		Customer customer = new Customer(lendApplyDB.getCustCode());
		customer = customerDao.getCustomerbyCode(customer);
		
		lendApplyDB.setApplyCode(lendCode);
		boolean sucRs = Boolean.TRUE;
		if(!PayMent.ZZ.value.equals(lendApplyDB.getPayType())){ //非 自转
			sucRs = lendApplyManager.makingFile(lendApplyDB, customer);  	//生成合同文件
		}
		if (sucRs) {   
			//发起工作流
			view.setLendMoney(Double.valueOf(view.getLendApply().getLendMoney().toString()));
			workItem.setResponse(CustomerConstants.WorkFlow.CONTRACT_FLOW);
			view.getLendApply().setApplyCode(lendCode);
			workItem.setBv(view);
			this.fs.dispatch(workItem);
		} else {  //生成失败
			return "合同申请书合成失败,请重新提交!";
		}
		return "";
	}
	
	/**
	 * 撤销出借申请
	 * 2016年6月29日
	 * @param lendCode
	 */
	public String canCelLendApply(String lendCode, String wobNum, String token){
		try{
			logger.info("canCelLendApply---撤销出借：" + lendCode);
			LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
			loanApply.setLendStatus(LendState.CX.value);
			// 客户放弃
			loanApply.setApplyEndStatus(FinishState.KHFQ.value);
			loanApply.setModifyBy(UserUtils.getUserId());
			loanApply.setModifyTime(new Date());
			loanApplyDao.update(loanApply);
			String payType = loanApply.getPayType();
			if (payType.equals(PayMent.NBZZ.value)|| payType.equals(PayMent.CGBFNZ.value) || payType.equals(PayMent.SHNZ.value) || payType.equals(PayMent.ZZ.value)) { 
				HashMap<String,Object> transMap = new HashMap<String,Object>();
				transMap.put("lendCodeC", lendCode);
				transMap.put("modifyBy", UserUtils.getUserId());
				transMap.put("transferState", TransferState.YTH.getKey());
				transferRelationDao.updateTransferStatus(transMap);
				logger.info("canCelLendApply---撤销出借，更新内转关系表：" + lendCode);
			}
			// 出借合同作废
			logger.info("canCelLendApply---撤销出借，合同签章作废：" + lendCode);
			lendApplyManager.caInvalidLendApply(lendCode);
			
			WorkItemView workItemView = new WorkItemView();
			// to_end流程结束
			workItemView.setResponse("to_end");
			
			workItemView.setFlowCode("lendApply");
			workItemView.setFlowName("出借申请流程");
			workItemView.setFlowType("CF0007");
			workItemView.setStepName("申请人");
			workItemView.setWobNum(wobNum);
			workItemView.setToken(token);
			
			LendApplyView view = new LendApplyView();
			Customer customer = new Customer();
			customer.setCustCode(loanApply.getCustCode());
			customer = customerDao.getCustomer(customer);
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("lendCode", lendCode);
			LendApplyFlow flow = lendApplyFlowDao.getFlow(params);
			if(null != flow){
				logger.info("canCelLendApply---撤销出借流程applyid:" + flow.getApplyId());
				view.setApplyId(flow.getApplyId());
				view.setLendApply(loanApply);
				view.setPaymentBankId(loanApply.getPaymentBankId());
				view.setReceiveBankId(loanApply.getReceiveBankId());
				view.setCustomer(customer);
			}
			workItemView.setBv(view);
			// 发起撤销流程
			fs.dispatch(workItemView);
			
			return null;
		}catch(Exception e){
			logger.info("canCelLendApply---撤销出借异常" + lendCode, e);
			throw e;
		}
		
	}
}
