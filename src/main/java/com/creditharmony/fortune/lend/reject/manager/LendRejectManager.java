package com.creditharmony.fortune.lend.reject.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyFlowDao;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyLogDao;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyQueryView;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.helper.LendHelper;
import com.creditharmony.fortune.lend.reject.view.LendApplyApprovalReturnedItemView;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.query.ProcessQueryBuilder;
// import com.creditharmony.core.fortune.type.TransferState;

/**
 * 出借退回管理类
 * 
 * @Class Name LendRejectManager
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@Service
public class LendRejectManager extends CoreManager<LoanApplyDao, LoanApply> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private LendApplyFlowDao lendApplyFlowDao;
	@Autowired
	private LendApplyLogDao lendApplyLogDao;
	@Autowired
	private AttachmentDao attachmentDao;
	
	/**
	 * 获取合同审批被退回列表 2015年12月31日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @param view
	 * @return List<LendApplyApprovalReturnedItemView>
	 */
	public List<LendApplyApprovalReturnedItemView> fetchReturnedTaskItems(WorkItemView workItem, LendApplyQueryView view,
			Page<LendApplyApprovalReturnedItemView> page, FlowPage flowPage) {
		ProcessQueryBuilder queryBuilder = new ProcessQueryBuilder();
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		queryBuilder.put("managerCode", userInfo.getId());
		if (StringUtils.isNotBlank(view.getCode())) {
			queryBuilder.put("customerCode@like", "%" + view.getCode() + "%");
		}
		if (StringUtils.isNotBlank(view.getName())) {
			queryBuilder.put("customerName@like", "%" + view.getName() + "%");
		}
		if (StringUtils.isNotBlank(view.getLendCode())) {
			queryBuilder.put("applyCode@like", "%" + view.getLendCode() + "%");
		}
		if (StringUtils.isNotBlank(view.getProductCode())) {
			queryBuilder.put("lendProduct", view.getProductCode());
		}

		if (null != view.getLendDate()) {
			queryBuilder.put("lendDate", view.getLendDate().getTime() / 1000);
		}
		if (null != view.getLendStart()) {
			queryBuilder.put("lendDate@>=",
					view.getLendStart().getTime() / 1000);
		}
		if (null != view.getLendEnd()) {
			queryBuilder.put("lendDate@<=", view.getLendEnd().getTime() / 1000);
		}

		if (null != view.getDeductStart()) {
			queryBuilder.put("deductDate@>=",
					view.getDeductStart().getTime() / 1000);
		}
		if (null != view.getDeductEnd()) {
			queryBuilder.put("deductDate@<=",
					view.getDeductEnd().getTime() / 1000);
		}

		if(null == page){
			TaskBean taskBean = fs.fetchTaskItems(
					CustomerConstants.WorkFlow.QUENE_OF_LENDAPPLY_RETURNED,
					queryBuilder, LendApplyApprovalReturnedItemView.class);
			
			@SuppressWarnings("unchecked")
			List<LendApplyApprovalReturnedItemView> list = (List<LendApplyApprovalReturnedItemView>) taskBean
			.getItemList();
			
			return ArrayHelper.isNotEmpty(list) ? list : null;
		} else {
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(	CustomerConstants.WorkFlow.QUENE_OF_LENDAPPLY_RETURNED, queryBuilder, 
					flowPage, null, LendApplyApprovalReturnedItemView.class);
			List<BaseTaskItemView> dataList = flowPage.getList();
			List<LendApplyApprovalReturnedItemView> list = new ArrayList<LendApplyApprovalReturnedItemView>();
			if(ArrayHelper.isNotEmpty(dataList)){
				for(BaseTaskItemView b : dataList){
					list.add((LendApplyApprovalReturnedItemView)b);
				}
			}
			return ArrayHelper.isNotEmpty(list) ? list : null;
		}
	}
	
	/**
	 * 出借退回，重新发起申请 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param view
	 * @param response
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveReLaunchForm(LendApplyView view, String response) {
		LoanApply lendApply = view.getLendApply();
		
		LoanApply lendApplyDB = dao.get(lendApply);
		LendHelper.updatePageProperties(lendApply, lendApplyDB);
		
		// 重新发起流程生成新的出借编号,合同编号
		String newLendCode = lendApply.getApplyCode();
		String productCode = lendApply.getProductCode();
		String contractCode = lendApply.getContractNumber();
		newLendCode = newLendCode.replaceFirst("^\\d{2}", productCode);
		String newContractCode = contractCode;
		if(!PayMent.ZZ.value.equals(lendApplyDB.getPayType())){ //非 自转
			newContractCode = contractCode.replaceFirst("^\\d{2}", productCode);
			lendApplyDB.setContractNumber(newContractCode);
		}
		
		if (!ProductCode.YMY.value.equals(productCode)) {
			// 计算出借的到期日期
			Product product = dao.getProduct(productCode);
			lendApplyDB.setExpireDate(BillDateUtil.getExpireDate(
					lendApplyDB.getLendDate(), product.getProductPeriods()));
		}else{
			lendApplyDB.setExpireDate(null);
		}
		
		//设置出借申请页面的合同选项 check1_check2
		if (StringUtils.isBlank(lendApply.getCheck1()) || StringUtils.isBlank(lendApply.getCheck2())) {
			throw new ServiceException("请选择合同选项");
		}
		StringBuilder sbOptions = new StringBuilder();
		sbOptions.append(lendApply.getCheck1()).append("_").append(lendApply.getCheck2());
		lendApplyDB.setOptions(sbOptions.toString());
		
		lendApplyDB.setPaymentBankId(view.getPaymentBankId());
		lendApplyDB.setReceiveBankId(view.getReceiveBankId());
		lendApplyDB.setLendStatus(LendState.WJHCZ.value);

		lendApplyDB.preUpdate();
		super.dao.update(lendApplyDB);
		contractManager.updateContractCode(contractCode, newContractCode);
		checkManager.addCheck(newLendCode, "申请出借", "重新发起申请");
		
		//清除对应的内转表数据
		String payTypeT = lendApplyDB.getPayType();
		if (PayMent.NBZZ.value.equals(payTypeT) || PayMent.CGBFNZ.value.equals(payTypeT)) {  //内部转账、成功部分内转时，清楚内转关系表对应数据
			transferRelationDao.deleteByChildLendCode(lendApplyDB.getApplyCode());
		}
		
		// 保存被内转出借信息
		String transferInfo = lendApply.getTransferLendId();
		logger.info("被选中的内转信息为：" + transferInfo);		
		List<TransferRelation> list = LendHelper.settransferInfo(transferInfo, newLendCode, lendApply.getPayType());
		for (TransferRelation transferRelation : list) {
			//判断被内转的数据是否有效（被引用，且引用者不是划扣成功或撤销状态）
			String lendCodeP = transferRelation.getLendCodeParent();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("lendCodeP", lendCodeP);
			params.put("HKCG", LendState.HKCG.value);
			params.put("CX", LendState.CX.value);
			params.put("lendCodeC", lendApplyDB.getApplyCode());
			int usingCount = transferRelationDao.getUsingTransCount(params);
			if (usingCount > 0) {
				throw new ServiceException(lendCodeP + "内转的数据已被占用");
			}
			//新增
			transferRelationDao.insert(transferRelation);
		}
		
		AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(),
				view.getDeleteAttachmentId(), lendApplyDB.getApplyCode(),
				lendApplyDB.getId(), "t_tz_loan_apply");
		
		// 重新发起申请，更新系统所有关联的出借编号
		updateLendCodeOfRelaunchFlow(lendApplyDB.getApplyCode(), newLendCode);
		
	}
	
	public void updateLendCodeOfRelaunchFlow(String oldLendCode, String newLendCode){
		super.dao.updateLendCode(oldLendCode, newLendCode);
		lendApplyFlowDao.updateLendCode(oldLendCode, newLendCode);
		lendApplyLogDao.updateLendCode(oldLendCode, newLendCode);
		checkManager.updateLendCode(oldLendCode, newLendCode);
		attachmentDao.updateLendCode(oldLendCode, newLendCode);
	}

}
