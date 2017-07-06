package com.creditharmony.fortune.lend.approve.manager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.CbDeductStatus;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.loan.type.DeductStatus;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyFlowDao;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyLogDao;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyFlow;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.lend.apply.contants.LendContants;
import com.creditharmony.fortune.lend.apply.view.LendApplyQueryView;
import com.creditharmony.fortune.lend.approve.view.LendApplyApprovalItemView;
import com.creditharmony.fortune.lend.approve.view.LendApplyApprovalView;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.sms.manager.SmsManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.utils.CaUtil;
import com.creditharmony.fortune.utils.OptionUtil;
// import com.creditharmony.core.fortune.type.TransferState;
import com.query.ProcessQueryBuilder;

/**
 * 出借申请管理类
 * 
 * @Class Name LendApplyManager
 * @author 孙凯文
 * @Create In 2015年12月22日
 */
@Service
public class LendApproveManager extends CoreManager<LoanApplyDao, LoanApply> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerAccountDao accountDao;
	@Autowired
	private LendApplyFlowDao lendApplyFlowDao;
	@Autowired
	private LendApplyLogDao lendApplyLogDao;
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private SmsDao smsDao;
	@Autowired
	private SmsManager smsManager;
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private MatchingCreditorManager matchingCreditorManager;
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao; // 回款池Dao
	@Autowired
	private DetailDao detailDao;

	/**
	 * 查询出借审批待办列表 2015年12月24日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @param view
	 * @return List<LendApplyApprovalItemView>
	 */
	public List<LendApplyApprovalItemView> fetchTaskItems(
			WorkItemView workItem, LendApplyQueryView view,
			Page<LendApplyApprovalItemView> page, FlowPage flowPage) {
		ProcessQueryBuilder queryBuilder = new ProcessQueryBuilder();

		if (StringUtils.isNotBlank(view.getApplyIds())) {
			String[] appIds = view.getApplyIds().split(",");
			queryBuilder.put("applyId", appIds);
		}

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
		if (null != view.getStoreId() && !"".equals(view.getStoreId())) {
			String[] q = view.getStoreId().split(",");
			queryBuilder.put("department", q);
		}

		if (null != view.getDeductStart()) {
			queryBuilder.put("deductDate@>=",
					view.getDeductStart().getTime() / 1000);
		}
		if (null != view.getDeductEnd()) {
			queryBuilder.put("deductDate@<=",
					view.getDeductEnd().getTime() / 1000);
		}
		// 付款方式
		if (null != view.getPayType() && !"".equals(view.getPayType())) {
			queryBuilder.put("payState", view.getPayType());
		}
		// 划扣平台
		if (null != view.getDictApplyDeductType()) {
			queryBuilder.put("deductType",
					view.getDictApplyDeductType().split(","));
		}
		// 出借银行
		if (null != view.getAccountBank()) {
			queryBuilder.put("deductBank", view.getAccountBank().split(","));
		}
		// 审批人
		if (null != view.getAuditor()) {
			queryBuilder.put("auditor@like", "%" + view.getAuditor() + "%");
		}
		if (null == page) {
			TaskBean taskBean = fs.fetchTaskItems(
					CustomerConstants.WorkFlow.QUENE_OF_LENDAPPLY,
					queryBuilder,
					// Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_CAIFU_GENERAL_QUEUING,
					LendApplyApprovalItemView.class);
			@SuppressWarnings("unchecked")
			List<LendApplyApprovalItemView> list = (List<LendApplyApprovalItemView>) taskBean
					.getItemList();
			return ArrayHelper.isNotEmpty(list) ? list : null;

		} else {
			flowPage.setPageSize(page.getPageSize());
			flowPage.setPageNo(page.getPageNo());
			fs.fetchTaskItems(CustomerConstants.WorkFlow.QUENE_OF_LENDAPPLY,
					queryBuilder, flowPage, null,
					LendApplyApprovalItemView.class);

			List<BaseTaskItemView> dataList = flowPage.getList();
			List<LendApplyApprovalItemView> list = new ArrayList<LendApplyApprovalItemView>();
			if (ArrayHelper.isNotEmpty(dataList)) {
				for (BaseTaskItemView b : dataList) {
					list.add((LendApplyApprovalItemView) b);
				}
			}
			return ArrayHelper.isNotEmpty(list) ? list : null;
		}

	}

	/**
	 * 加载出借审批视图 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param applyId
	 * @return BaseBusinessView
	 */
	public BaseBusinessView loadLendApplyApprovalView(String applyId) {

		LendApplyFlow flow = new LendApplyFlow();
		flow.setApplyId(applyId);
		flow = lendApplyFlowDao.get(flow);
		
		// 获取客户编号
		String custCode = flow.getCustomerCode();
		String lendCode = flow.getLendCode();
		// 查询客户
		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerDao.getCustomer(customer);

		LoanApply apply = new LoanApply();
		apply.setApplyCode(lendCode);
		apply = dao.get(apply);

		// 获取产品对应的协议版本列表
		String protocolList = dao.getProductVersion(apply.getProductCode());
		String[] protocolArray = null;
		String protocals = null;
		if (null != protocolList && !"".equals(protocolList)) {
			protocolArray = protocolList.split(",");
			protocals = protocolList;
		}

		CustomerAccount payBank = accountDao.get(apply.getPaymentBankId());
		CustomerAccount receiveBank = accountDao.get(apply.getReceiveBankId());

		if (payBank == null) {
			throw new ServiceException("付款银行不存在");
		}
		if (receiveBank == null) {
			throw new ServiceException("回款银行不存在");
		}

		List<CustomerAccount> banks = new ArrayList<CustomerAccount>();
		banks.add(payBank);
		if (payBank != null && !payBank.getId().equals(receiveBank.getId())) {
			banks.add(receiveBank);
		}
		// 实体化审批日志
		LendApplyLog log = new LendApplyLog();
		log.setApplyId(applyId);
		log.setLendCode(lendCode);

		LendApplyApprovalView view = new LendApplyApprovalView();

		view.setProtocolList(protocolArray);
		view.setProtocals(protocals);

		List<LendApplyLog> list = this.findLendApplyLog(log);
		if (ArrayHelper.isNotEmpty(list)) {
			log = list.get(0);
			view.setLendApplyLog(log);
		}

		List<CustomerAccount> goldAccountList = new ArrayList<CustomerAccount>();
		if (payBank.getId().equals(customer.getTrusteeshipAccountId())
				|| PayMent.ZJTG.value.equals(apply.getPayType())) {
			goldAccountList.add(payBank);
			banks.clear();
			view.setGoldAccount(goldAccountList);
		}
		view.setGoldAccountProvinceList(OptionUtil.findFYProvince(null));
		if (ArrayHelper.isNotEmpty(goldAccountList)) {
			for (CustomerAccount item : goldAccountList) {
				view.getGoldAccountCityList().add(
						OptionUtil.findFYCity(item.getAccountAddrProvince(),
								null));
			}
		}

		// 出借页面的合同选项
		if (StringUtils.isNotBlank(apply.getOptions())) {
			String[] options = apply.getOptions().split("_");
			if (options.length == 2) {
				apply.setCheck1(options[0]);
				apply.setCheck2(options[1]);
			}
		}

		view.setCustomer(customer);
		view.setLendApply(apply);
		view.setBanks(banks);
		view.setApplyId(applyId);

		String payType = apply.getPayType();
		List<LoanApply> transferLoanApplyList = new ArrayList<LoanApply>();
		if (PayMent.NBZZ.value.equals(payType)
				|| PayMent.CGBFNZ.value.equals(payType)
				|| PayMent.ZZ.value.equals(payType)) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("childLendCode", lendCode);
			List<TransferRelation> transferList = transferRelationDao
					.findList(map);
			if (ArrayHelper.isNotEmpty(transferList)) {
				LoanApply a = null;
				BigDecimal totalTransferMoney = new BigDecimal("0.0");
				for (TransferRelation item : transferList) {
					a = dao.getLoanApplyByCode(item.getLendCodeParent());
					if (null != a) {
						BigDecimal validateMoney = dao.getSurplusAmount(a.getApplyCode());
						a.setTransferMoney(item.getTransferMoney());
						BigDecimal transMoney = item.getTransferMoney();
						totalTransferMoney = totalTransferMoney.add(transMoney);
						BigDecimal deductFailMoney = a.getDeductFailMoney()==null ? new BigDecimal(0):a.getDeductFailMoney();
						a.setValidateMoney(String.valueOf(a.getLendMoney().subtract( deductFailMoney ).subtract(validateMoney)));
						a.setBackMoney(item.getBackMoney());
						transferLoanApplyList.add(a);
					}
				}
				apply.setTransferMoney(totalTransferMoney);
				view.setTransferLoanApplyList(transferLoanApplyList);
			}
		}

		view.setProvinceList(OptionUtil.getProvinceList());
		if (ArrayHelper.isNotEmpty(banks)) {
			view.setCityList(new ArrayList<List<ProvinceCity>>());
			view.setDistrictList(new ArrayList<List<ProvinceCity>>());
			for (CustomerAccount item : banks) {
				view.getCityList().add(
						OptionUtil.getCityByProvinceId(item
								.getAccountAddrProvince()));
				view.getDistrictList().add(
						OptionUtil.getDistrictByCityId(item
								.getAccountAddrCity()));
			}
		}

		Date createDate = flow.getCreateTime();
		Date temp = DateUtils.parseDate("2016-07-01");
		if(createDate.before(temp)){
			view.setCanceled("false");
		}else{
			view.setCanceled("true");
		}
		
		view.setEnableProduct(Arrays.asList(LendContants.ENABLE_PRODUCT));
		return view;
	}

	/**
	 * 保存审批结果，同时更新业务数据 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @param view
	 * @param response
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveApprovalForm(LendApplyApprovalView view, String response) {
		try{
			LendApplyLog log = view.getLendApplyLog();
			// 根据ApplyId获取流程对象
			LendApplyFlow flow = new LendApplyFlow();
			flow.setApplyId(log.getApplyId());
			flow = lendApplyFlowDao.get(flow);
	
			// String lendCode = flow.getLendCode();
			LoanApply apply = view.getLendApply();
			// apply.setApplyCode(lendCode);
			// apply = dao.get(apply);
	
			Customer customer = new Customer();
			customer.setCustCode(log.getCustomerCode());
			customer = customerDao.getCustomer(customer);
	
			if (CustomerConstants.WorkFlow.LENDAPPLY_FLOW_YES.equals(response)) {
				// 审批通过
				log.setDictApplyStatus(LendState.SPTG.value);
				apply.setLendStatus(LendState.SPTG.value);
				// apply.setStatus(ForApplyStatus.CJZ.value);
				// 获取产品期数
				Product product = dao.getProduct(apply.getProductCode());
				// apply.setExpireDate(BillDateUtil.getExpireDate(apply.getLendDate(),
				// product.getProductPeriods()));
	
				if ((ProductCode.XHB.value.equals(apply.getProductCode()) && CustomerConstants.XINHEBAO_12
						.equals(apply.getXinhebaoType()))
						|| ProductCode.XHBA.value.equals(apply.getProductCode())) {
					// 如果为信和宝(12月回息)/信和宝A
					Date d = BillDateUtil.addMonth(apply.getLendDate(), 12);
					d = BillDateUtil.addDays(d, -1);
					apply.setMatchingBackinterestDay(d);
					apply.setMatchingBackinterestStatus(BacksmsState.DHX.value);
				} else if (ProductCode.XHBC.value.equals(apply.getProductCode())) {
					// 如果为信和宝C
					Date d = BillDateUtil.addMonth(apply.getLendDate(), 6);
					d = BillDateUtil.addDays(d, -1);
					apply.setMatchingBackinterestDay(d);
					apply.setMatchingBackinterestStatus(BacksmsState.DHX.value);
				}
	
				customer.setCustLending(InvestState.ZT.value);
				
				if(!PayMent.ZZ.value.equals(apply.getPayType())){ //非 自转
					MatchingCreditorEx matchEntity = insertMatchingCreditor(customer,
							apply, product);
				}else{
					//自转 更新状态
					apply.setLendStatus(LendState.HKCG.value);//出借状态:划扣成功
					apply.setDictDeductStatus(DeductStatus.DEDUCT_SUCCEED.getCode());//划扣状态:划扣成功
					apply.setStatus("3"); //状态:已到期
					
					//自转 新增 回款信息 & 更新原出借回款信息
					backMoneyPoolAddAndUpdate(apply.getApplyCode());
					
				}
				
	
				if (!ProductCode.YMY.value.equals(apply.getProductCode())) {
					// 如果不是月满盈
					/*matchingCreditorManager.insertTempMatchingCreditor(matchEntity
							.getMatchingId());*/
				}
	
				checkManager.addCheck(apply.getApplyCode(), "审批通过", "出借合同审核");
	
				// 发送划扣短信
				if (PayMent.HK.value.equals(apply.getPayType())) {
					DecimalFormat f = new DecimalFormat("#0.00");
					SmsSendList smsObject = new SmsSendList();
					smsObject.setLendCode(apply.getApplyCode());
					smsObject.setCustomerName(customer.getCustRealname());
					smsObject.setProductCode(apply.getProductCode());
					smsObject.setProductName(product.getProductName());
					smsObject.setCustomerCode(customer.getCustCode());
					smsObject.setCustomerPhone(customer.getCustMobilephone());
					smsObject.setManagerCode(customer.getManagerCode());
					smsObject.setDeductDate(apply.getDeductDate());
					smsObject.setBillDay(apply.getBillDay());
	
					String lendMoney = f.format(apply.getLendMoney());
					smsObject.setLendMoney(new BigDecimal(lendMoney));
	
					smsObject.setLendDay(apply.getLendDate());
					smsObject.setDueDay(apply.getExpireDate());
					if (null != apply.getExpireDate()) {
						smsObject.setBackMoneyDay(DeductUtils
								.getNextWorkingDay(apply.getExpireDate()));
					}
					smsObject.setDictRepayType(apply.getPayType());
					smsObject.setDictDeductStatus(CbDeductStatus.dhk.value);
					smsObject.setSendStatus(SmsState.DFS.value);
					CustomerAccount payBank = accountDao.get(apply
							.getPaymentBankId());
					if (null != payBank) {
						smsObject.setBankNo(payBank.getAccountNo());
						String bankCode = payBank.getAccountBankId();
						smsObject.setBankName(OpenBank.getOpenBank(bankCode));
						smsObject.setBankId(bankCode);
					}
	
					SmsTemplate template = smsManager
							.getSmsTemplate(SmsType.HKTX.value);
					String templateContent = template.getTemplateContent();
					templateContent = templateContent.replace("{#Name#}",
							customer.getCustName());
					templateContent = templateContent.replace("{#custom_text_4#}", lendMoney);
	
					if (null != template) {
						smsObject.setSmsId(template.getTemplateCode());
					}
					smsObject.setSmsMsg(templateContent);
					smsObject.setSendDay(null);
					smsObject.setPushDate(null);
					smsObject.setDeductSucceedMoney(new BigDecimal(lendMoney));
					smsObject.setDeductFailMoney(new BigDecimal("0.00"));
					String managerId = customer.getManagerCode();
					smsObject.setOrgCode(apply.getStoreOrgId());
					smsObject.setPlatformId(apply.getDeductTypeId());
					User user = UserUtils.get(managerId);
					if (null != user) {
						smsObject.setManagerName(user.getName());
					}
					FortuneOrg org = RelationShipUtil.getStoresOrg(managerId);
					if (null != org) {
						smsObject.setOrgName(org.getName());
						smsObject.setAreaName(org.getCityId());
					}
					smsObject.setDictBackStatus(null);
					smsObject.setProductCloseTerm(product.getProductClosedate());
					smsObject.setDictRemindType(SmsType.HKTX.value);
					smsObject.setApplyDay(apply.getApplyDate());
					smsObject.preInsert();
					smsDao.insert(smsObject);
				}
	
				// 更新合同状态（已出借）
				Contract contract = new Contract();
				contract.setContCode(apply.getContractNumber());
				contract.setDictContStatus(ContractState.YCJ.value);
				contractManager.updateContractState(contract);
	
				// 修改内转关系表
				String lendCode = apply.getApplyCode();
				Map<String, Object> params = new HashMap<String, Object>();
				if(PayMent.ZZ.value.equals(apply.getPayType())){ //自转  更新状态为 已内转
					params.put("transferState", TransferState.YNZ.getKey());
				}else{ //非自转  更新状态为 已审核
					params.put("transferState", TransferState.YSH.getKey());
				}
				params.put("modifyBy", UserUtils.getUserId());
				params.put("lendCodeC", lendCode);
				transferRelationDao.updateTransferStatus(params);
	
			} else if (CustomerConstants.WorkFlow.LENDAPPLY_FLOW_NO
					.equals(response)) {
				// 审批不通过
				log.setDictApplyStatus(LendState.SPBTG.value);
				apply.setLendStatus(LendState.SPBTG.value);
				if (!InvestState.ZT.value.equals(customer.getCustLending())
						&& !InvestState.YT.value.equals(customer.getCustLending())) {
					customer.setCustLending(InvestState.WT.value);
				}
				checkManager.addCheck(apply.getApplyCode(), "审批不通过", "出借合同审核");
				// 合同附件变为无效
				Attachment attachment = new Attachment();
				attachment.setLoanCode(apply.getApplyCode());
				attachment.setDictAttaFlag(FileConstants.FILE_TYPE_CJHTSQS);
				attachmentManager
						.invalidAttachment(attachment, apply.getCustCode());
	
				// 修改内转关系表  【审批不通过，不修改内转状态 】 朱杰
//				String lendCode = apply.getApplyCode();
//				Map<String, Object> params = new HashMap<String, Object>();
//				params.put("transferState", TransferState.YTH.getKey());
//				params.put("modifyBy", UserUtils.getUserId());
//				params.put("lendCodeC", lendCode);
//				transferRelationDao.updateTransferStatus(params);
	
			}
			log.setLendCode(apply.getApplyCode());
			log.setCheckById(UserUtils.getUserId());
			log.setCheckTime(new Date());
			log.setApplyBy(apply.getManagerCode());
			log.setApplyTime(flow.getCreateTime());
	
			customer.preUpdate();
			customerDao.update(customer);
			apply.preUpdate();
			dao.update(apply);
			log.preInsert();
			lendApplyLogDao.insert(log);
		}catch(Exception e){
			logger.error("出借申请审批",e);
			String lendCode = view.getLendApply().getApplyCode();
			
			FortuneException forException = new FortuneException(lendCode, e, 
					FortuneLogNode.LEND_APPROVE.getCode(), FortuneLogLevel.YELLOW.value, "", "审批出借[" + lendCode + "]错误");
			FortuneExceptionUtil.insertException(forException );
			
			throw new RuntimeException("审批出借[" + lendCode + "]错误", e);
		}

	}
	
	public void backMoneyPoolAddAndUpdate(String lendCode) {
		//根据出借编号,查询父出借
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("childLendCode", lendCode);
		List<TransferRelation> list = transferRelationDao.findList(paramMap);
		if(list == null || list.size() <= 0){
			logger.error("出借编号:"+lendCode+"的自转出借,未查询到内转信息!");
			return;
		}
		String parentLendCode = list.get(0).getLendCodeParent();
		if(StringUtils.isEmpty(parentLendCode)){
			logger.error("出借编号:"+lendCode+"的自转出借,未查询到父出借信息!");
			return;
		}
		//获取 父出借的回款信息
		BackMoneyPool parentBmp = backMoneyPoolDao.getByLendCode(parentLendCode);
		if(parentBmp == null){
			logger.error("出借编号:"+lendCode+"的自转出借,未查询到父出借的回款信息!");
			return;
		}
		//新增 新出借的回款信息
		BackMoneyPool backMoneyPoolnew = new BackMoneyPool();
		backMoneyPoolnew.setBackmId(IdGen.uuid());
		backMoneyPoolnew.setLendCode(lendCode);
		backMoneyPoolnew.setBackMoney(parentBmp.getBackMoney());
		backMoneyPoolnew.setBackActualbackMoney(parentBmp.getBackMoney());
		backMoneyPoolnew.setFinalLinitDate(parentBmp.getFinalLinitDate());// 到期日期
		backMoneyPoolnew.setBackMoneyDay(parentBmp.getBackMoneyDay());// 回款日期
		backMoneyPoolnew.setDictBackStatus(BackState.DHKSQ.value);
		backMoneyPoolnew.setBackMoneyType(BackType.DQHK.value);
		backMoneyPoolnew.setModifyBy(UserUtils.getUserId());
		backMoneyPoolnew.setModifyTime(new Date());
		backMoneyPoolnew.setCreateBy(UserUtils.getUserId());
		backMoneyPoolnew.setCreateTime(new Date());
		backMoneyPoolnew.setRebackFlag(YoN.FOU.value);
		backMoneyPoolnew.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
		backMoneyPoolnew.setWorkingState(parentBmp.getWorkingState());
		backMoneyPoolDao.insert(backMoneyPoolnew);
		
		//更新原出借回款信息
		parentBmp.setDictBackStatus(BackState.YHK.value);
		parentBmp.setBackMoneyType(BackType.QBBJZZ.value);
		parentBmp.setIsSupplemented(YoN.FOU.value);
		parentBmp.setVerTime(null);
		parentBmp.setBackMoneyDay(new Date());
		backMoneyPoolDao.updateByLendCode(parentBmp);
		
		//更新原出借申请的完结状态
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(parentLendCode);
		loanApply.setApplyEndStatus(FinishState.YWJ.value);
		loanApply.preUpdate();
		detailDao.updateLoanApply(loanApply);
		
		//更新原出借客户的出借状态
		LoanApply la = detailDao.getLoanApply(loanApply);
		la.setApplyEndStatus(FinishState.WWJ.value);
		int count = detailDao.countLendingApply(la);
		if (count<1) {
			// 如果未完成出借数量小于1，客户出借状态更新为已投
			Customer customer = new Customer();
			customer.setCustCode(la.getCustCode());
			customer.setCustLending(InvestState.YT.value);
			customer.preUpdate();
			customerDao.updateApplyLending(customer);
		}
		
		//原出借增加留痕
		checkManager.addCheck(parentLendCode, "全部本金自转,审核通过", "提交");
	}

	/**
	 * 保存出借日志 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param log
	 * @return
	 */
	public List<LendApplyLog> findLendApplyLog(LendApplyLog log) {
		return lendApplyLogDao.findList(log);
	}

	/**
	 * 插入债权匹配表 2016年1月7日
	 * 
	 * @author 孙凯文
	 * @param customer
	 * @param apply
	 * @param product
	 */
	private MatchingCreditorEx insertMatchingCreditor(Customer customer,
			LoanApply apply, Product product) {
		MatchingCreditorEx matchingCreditor = new MatchingCreditorEx();
		matchingCreditor.setCustomerCode(customer.getCustCode());
		matchingCreditor.setLendCode(apply.getApplyCode());
		matchingCreditor.setProductCode(apply.getProductCode());
		matchingCreditor.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditor.setMatchingBorrowQuota(apply.getLendMoney());
		matchingCreditor.setMatchingNow(CustomerConstants.FIRSTPHASE);
		matchingCreditor.setMatchingInterestStart(apply.getLendDate());
		matchingCreditor.setMatchingStatus(MatchingStatus.DTJ.value);
		matchingCreditor.setMatchingTotal(product.getProductPeriods());
		matchingCreditor.setMatchingBillDay(BillDateUtil.getBillDate(apply
				.getLendDate()));
		matchingCreditor.setCreateBy(UserUtils.getUserId());
		matchingCreditor.setCreateTime(new Date());
		matchingCreditor.setModifyBy(UserUtils.getUserId());
		matchingCreditor.setModifyTime(new Date());
		matchingCreditor.setMatchingId(IdGen.uuid());

		matchingCreditor.setMatchingPayStatus(SettleStats.WJS.value);
		matchingCreditor.setDictMatchingFilesendStatus(EmailState.WFS.value);
		matchingCreditor.setDictMatchingFileStatus(FilecpState.WHC.value);
		if (!ProductCode.YMY.value.equals(apply.getProductCode())) {// 非月满盈的需要添加结束日期和本期到期日期
			matchingCreditor.setMatchingExpireDay(CreditorUtils
					.getCreditDaybyLendday(apply.getLendDate()));
			matchingCreditor.setMatchingEndday(BillDateUtil.getExpireDate(
					apply.getLendDate(), product.getProductPeriods()));
		}
		matchingCreditor.setMatchingFirstbillDay(CreditorUtils
				.getCreditDaybyLendday(apply.getLendDate()));
		matchingCreditorDao.insert(matchingCreditor);
		return matchingCreditor;
	}

	/**
	 * 出借申请书签章 2016年4月6日
	 * 
	 * @author 孙凯文
	 * @param loanApply
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String makingFileCAS(LoanApply loanApply, Customer customer) {
		try {

			Attachment att = new Attachment();
			att.setLoanCode(loanApply.getApplyCode());
			att.setAttaFileOwner("t_tz_loan_apply");
			att.setDictAttaFlag(FileConstants.FILE_TYPE_CJHTSQS);
			att.setAttaTableId(loanApply.getId());
			List<Attachment> attachment = attachmentManager.findList(att);
			if (attachment.size() > 0) {

				DmService dmService = DmService.getInstance();
				CaCustomerSign signParam = new CaCustomerSign(
						customer.getCustName(), "甲方：",
						customer.getCustCertNum());
				signParam.setBatchNo(customer.getCustCode());
				signParam.setSubType(loanApply.getApplyCode());
				// pdf制作签章
				Ca_SignOutBean info = CaUtil.sign(CASignType.CONTRACT.value,
						attachment.get(0).getAttaFilepath(), signParam);
				if (!ReturnConstant.SUCCESS.equals(info.getRetCode())) {
					String msg = "";
					if (info != null) {
						msg = info.getRetCode() + "," + info.getRetMsg();
					}
					return "合同申请书签章失败！" + msg;
				} else {
					// 将文件移动至固定文件夹内
					/*dmService.moveDocument(
							info.getDocId(),
							customer.getCustCode() + "/"
									+ loanApply.getApplyCode(),
							DmService.BUSI_TYPE_FORTUNE,
							ReportType.CJ_SQ.getName()+ DateUtils.getDate("yyyyMMddHHmmss") + ".pdf");*/
					// 更新成已经签章的附件
					att.setAttaId(attachment.get(0).getAttaId());
					att.setAttaFilepath(info.getDocId());
					attachmentManager.updateAttachment(att);
					return "true";
				}
			} else {
				return "未找到合同申请书,无法签章";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("生成电子签章失败", e);
			return "合同申请书签章失败！";
		}
		

	}

}
