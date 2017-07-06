package com.creditharmony.fortune.lend.apply.manager;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.PropertyUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.service.PlatformBankService;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.loan.type.YESNO;
// import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CEContextHolder;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.common.ContractCodeGenerator;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyFlowDao;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyFlow;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.customer.workflow.util.LendCodeGenerateUtil;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.lend.apply.contants.LendContants;
import com.creditharmony.fortune.lend.apply.dao.LendPerformanceDao;
import com.creditharmony.fortune.lend.apply.entity.LendPerformance;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.helper.LendHelper;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.triple.system.service.TripleFirstOrderService;
import com.creditharmony.fortune.utils.AttachmentUtil;
import com.creditharmony.fortune.utils.CaUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借申请管理类
 * 
 * @Class Name LendApplyManager
 * @author 孙凯文
 * @Create In 2015年12月22日
 */
@Service
public class LendApplyManager extends CoreManager<LoanApplyDao, LoanApply> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerAccountDao accountDao;
	@Autowired
	private LendApplyFlowDao lendApplyFlowDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private PlatformBankService platformBankService;
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	@Autowired
	private TripleFirstOrderService tripleFirstOrderService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private LendPerformanceDao lendPerformanceDao;

	/**
	 * 出借申请页面初始化 2015年12月22日
	 * 
	 * @param param
	 * @return LendApplyView
	 */
	public LendApplyView openLaunchForm(LoanApply param) {
		String customerCode = param.getCustCode();
		Customer customer = new Customer();
		customer.setCustCode(customerCode);
		customer = customerDao.getCustomer(customer);
		LendApplyView lendApplyView = new LendApplyView();
		List<CustomerAccount> goldAccountList = new ArrayList<CustomerAccount>();
		CustomerAccount account = new CustomerAccount();
		account.setCustomerCode(customerCode);
		List<CustomerAccount> banks = accountDao.findList(account);
		// 去重复的， 根据：相同卡号的只能出现一个。并且金账户账号
		removeDuplicateBankInfo(banks, goldAccountList,
				customer.getTrusteeshipAccountId());

		if (goldAccountList.size() > 0) { // 将金账户信息放到 lendApplyView
			lendApplyView.setGoldAccount(goldAccountList);
		}
		lendApplyView.setCustomer(customer);
		lendApplyView.setProvinceList(OptionUtil.getProvinceList());
		if (ArrayHelper.isNotEmpty(banks)) { // 设置银行的 省、市、县信息
			lendApplyView.setCityList(new ArrayList<List<ProvinceCity>>());
			lendApplyView.setDistrictList(new ArrayList<List<ProvinceCity>>());
			for (CustomerAccount item : banks) {
				lendApplyView.getCityList().add(
						OptionUtil.getCityByProvinceId(item
								.getAccountAddrProvince()));
				lendApplyView.getDistrictList().add(
						OptionUtil.getDistrictByCityId(item
								.getAccountAddrCity()));
			}
		}
		lendApplyView.setGoldAccountProvinceList(OptionUtil
				.findFYProvince(null));
		if (ArrayHelper.isNotEmpty(goldAccountList)) {
			for (CustomerAccount item : goldAccountList) {
				lendApplyView.getGoldAccountCityList().add(
						OptionUtil.findFYCity(item.getAccountAddrProvince(),
								null));
			}
		}
		String autoFlag = PropertyUtil.getStrValue(
				"trusteeship_config.properties", "auto_docking", "0");
		lendApplyView.setAutoOpenAccount(autoFlag);
		lendApplyView.setMinApplyAmount(LendContants.minApplyAmount);
		lendApplyView.setEnableProduct(Arrays.asList(LendContants.ENABLE_PRODUCT));
		return lendApplyView;
	}

	/**
	 * 去掉重复的银行卡,整理金账户开户行id 2016年3月13日 By 肖长伟
	 * 
	 * @param banks
	 * @param goldAccount
	 *            金账户信息
	 * @param goldAccountId
	 *            金账户id
	 */
	private void removeDuplicateBankInfo(List<CustomerAccount> banks,
			List<CustomerAccount> goldAccount, String goldAccountId) {
		// 去掉重复的银行卡
		if (banks != null) {

			// 整理金账户开户行id
			if (ArrayHelper.isNotEmpty(banks)) {
				Iterator<CustomerAccount> it = banks.iterator();
				while (it.hasNext()) {
					CustomerAccount bank = it.next();
					if (bank.getId().equals(goldAccountId)) {
						// 对应金账户银行ID
						goldAccount.add(bank);
						it.remove();
					}
				}
			}

			// 去重
			Map<String, CustomerAccount> tempMap = new HashMap<String, CustomerAccount>();
			for (Iterator<CustomerAccount> iterator = banks.iterator(); iterator
					.hasNext();) {
				CustomerAccount account = iterator.next();
				String cardNo = account.getAccountNo();
				if (StringUtils.isBlank(cardNo)) { // 卡号判空
					continue;
				}
				if (!tempMap.containsKey(cardNo)) {
					tempMap.put(cardNo, account);
				} else {
					iterator.remove();
				}
			}
		}

	}

	/**
	 * 出借申请保存信息 2015年12月23日
	 * 
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveLaunchForm(LendApplyView view) {

		Customer customer = view.getCustomer();
		LoanApply lendApply = view.getLendApply();

		// 设置出借申请页面的合同选项 check1_check2
		if (StringUtils.isBlank(lendApply.getCheck1())
				|| StringUtils.isBlank(lendApply.getCheck2())) {
			throw new ServiceException("请选择合同选项");
		}
		StringBuilder sbOption = new StringBuilder();
		sbOption.append(lendApply.getCheck1()).append("_")
				.append(lendApply.getCheck2());
		lendApply.setOptions(sbOption.toString());
		// 锁住该客户的申请
		customer = customerDao.getCustomerbyCodeForUpdate(customer);
		if (StringUtils.isEmpty(lendApply.getApplyCode())) {
			// 如果页面参数没有lendCode参数，需要设置新的出借编号
			String newLendCode = LendCodeGenerateUtil.generate(
					customer.getCustCode(), lendApply.getProductCode());
			lendApply.setApplyCode(newLendCode);
		}

		// 内转金额设置为0
		lendApply.setTransferMoney(new BigDecimal(0.0));

		LoanApply lendApplyDB = dao.get(lendApply);
		if (lendApplyDB == null
				|| StringUtils.isEmpty(lendApplyDB.getApplyCode())) {
			// *** 新的出借申请操作 ***

			// 新增银行账户信息，付款账户 = 收款账户; 业务需要，每次出借申请都会新增一个银行账户（金账户除外）
			// CustomerAccount account =
			// accountDao.get(view.getPaymentBankId());
			// if (StringUtils.isNotBlank(account.getId())) { //不为金账户，则新增
			// if (! account.getId().equals(customer.getTrusteeshipAccountId()))
			// { //金账户
			// account.preInsert();
			// account.setLendCode(lendApply.getApplyCode());
			// accountDao.insert(account);
			// }
			//
			// } else {
			// account.preInsert();
			// account.setLendCode(lendApply.getApplyCode());
			// accountDao.insert(account);
			// }

			// 设置出借申请信息
			this.setLoanApplyInfoOutPage(lendApply, customer,
					view.getPaymentBankId());
			// 设置产品月利率
			this.setProductRate(lendApply);
			String productCode = lendApply.getProductCode();
			if (!ProductCode.YMY.value.equals(productCode)) {
				// 计算出借的到期日期
				Product product = dao.getProduct(productCode);
				lendApply.setExpireDate(BillDateUtil.getExpireDate(
						lendApply.getLendDate(), product.getProductPeriods()));
			}
			// 合同版本
			// String
			// contractVersion=AppPropertiesUtil.getString("contract_version") ;
			// lendApply.setProtocoEdition(contractVersion);
			lendApply.preInsert();
			super.dao.insert(lendApply);
//			String contractCode = getContractCode(lendApply.getProductCode());
//			lendApply.setContractNumber(contractCode);
//			updateContractCode(lendApply);
			
			if(!PayMent.ZZ.value.equals(lendApply.getPayType())){ //非 自转
				// 插入合同表
				Contract contract = new Contract();
				contract.preInsert();
				contract.setContCode(lendApply.getContractNumber());
				contract.setContStoresId(lendApply.getStoreOrgId());
				contract.setContVersion(lendApply.getProtocoEdition());
				contract.setContSignStatus(YESNO.YES.getCode());
				contract.setDictContStatus(ContractState.KC.value);
				contract.setDictContFileStatus(YESNO.NO.getCode());
				contract.setContFileTime(new Date());
				contract.setDictContSource(YESNO.NO.getCode());// 门店转借
				contract.setDictContBelong(YoN.FOU.value);// 合同归属 理财经理
				contract.setContBelongEmpid(lendApply.getManagerCode());
				contract.setApplyDay(new Date());
				contract.setContUseDay(new Date());
				contract.setContractType(LendContants.CONTRACT);
				contractDao.insertContract(contract);
			}
			

			// 资金托管的出借,并且客户托管状态为未开户
			if (PayMent.ZJTG.value.equals(lendApply.getPayType())
					&& TrustState.WKH.value.equals(customer
							.getApplyHostedStatus())) {
				// 修改客户的托管状态为申请中
				customer.setApplyHostedStatus(TrustState.SQZ.value);
				customer.preUpdate();
				customerDao.updateTrusteeship(customer);

			}

			// 流程表
			LendApplyFlow flow = new LendApplyFlow();
			flow.setApplyId(view.getApplyId());
			flow.setApplyType(CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
			flow.setCustomerCode(view.getCustomer().getCustCode());
			flow.setManagerCode(UserUtils.getUserId());
			flow.setTeamManagerCode(customer.getTeamManagerCode());
			FortuneOrg storeOrg = RelationShipUtil.getStoresOrg(flow.getManagerCode());
			if(null != storeOrg){
				flow.setStoreCode(storeOrg.getId());
			}
			flow.setLendCode(lendApply.getApplyCode());
			flow.preInsert();
			lendApplyFlowDao.insert(flow);
			
			LendPerformance t = getLendPerformance(lendApply);
			t.preInsert();
			logger.info("往业绩表录入数据：" + t.getId());
			lendPerformanceDao.insert(t);
		}
		//

		checkManager.addCheck(lendApply.getApplyCode(), "申请出借", "提交");

		// 保存被内转出借信息
		String transferInfo = lendApply.getTransferLendId();
		logger.info("被选中的内转信息为：" + transferInfo);
		List<TransferRelation> list = LendHelper.settransferInfo(transferInfo,
				lendApply.getApplyCode(), lendApply.getPayType());
		for (TransferRelation transferRelation : list) {
			transferRelationDao.insert(transferRelation);
		}

		// 附件表更新
		AttachmentUtil.updateAndDeleteAttchment(view.getAddAttachmentId(),
				view.getDeleteAttachmentId(), lendApply.getApplyCode(),
				lendApply.getId(), "t_tz_loan_apply");

		// CE附件移动
		DmService dmService = DmService.getInstance();
		List<String> addAttachmentIdList = view.getAddAttachmentId();
		if (null != addAttachmentIdList) {
			for (String addAttachmentId : addAttachmentIdList) {
				Attachment attachment = attachmentManager.get(addAttachmentId);
				if (attachment != null) {
					dmService.moveDocument(
							attachment.getAttaFilepath(),
							view.getCustomer().getCustCode() + "/"
									+ lendApply.getApplyCode(),
							DmService.BUSI_TYPE_FORTUNE);
				}
			}
		}
	}
	
	private LendPerformance getLendPerformance(LoanApply lendApply){
		LendPerformance t = new LendPerformance();
		t.setLendCode(lendApply.getApplyCode());
		t.setManagerCode(lendApply.getManagerCode());
		t.setTeamManagerCode(lendApply.getTeamManagerCode());
		t.setCityManagerCode(lendApply.getCityManagerCode());
		t.setStoreManagerCode(lendApply.getStoreManagerCode());
		
		t.setTeamOrgId(lendApply.getTeamOrgId());
		t.setStoreOrgId(lendApply.getStoreOrgId());
		t.setCityOrgId(lendApply.getCityOrgId());
		
		return t;
	}

	/**
	 * 获取合同编码 2016年4月25日 By 郭才林
	 */
	public String getContractCode(String productCode) {
		return ContractCodeGenerator.getNextCode(productCode);
	}

	/**
	 * 更新出借申请合同编号 2016年4月25日 By 郭才林
	 * 
	 * @param lendApply
	 */
	public void updateContractCode(LoanApply lendApply) {
		dao.updateContractCode(lendApply);
	}

	/**
	 * 如果文件合成成功，则提交工作流程，更新文件制作状态 2016年4月15日 By 郭才林
	 * 
	 * @param loanApply
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateMakeFileStatus(LendApplyView view) {
		LoanApply lendApply = view.getLendApply();
		if(LendState.CX.value.equals(lendApply.getLendStatus()) && 
				FinishState.KHFQ.value.equals(lendApply.getApplyEndStatus())){
			// 如果出借状态为：撤销并且完结状态为：客户放弃，则出借流程直接结束
			checkManager.addCheck(lendApply.getApplyCode(), "出借撤销", "出借被退回");
			return;
		}else{
			// 待出借审批
			LoanApply loanApply = dao.getLoanApplyByCode(view.getLendApply()
					.getApplyCode());
			loanApply.setLendStatus(LendState.DCJSP.value);
			loanApply.preUpdate();
			dao.update(loanApply);

			// 更新合同状态（已出借）
			Contract contract = new Contract();
			contract.setContCode(loanApply.getContractNumber());
			contract.setDictContStatus(ContractState.CJSPZ.value);
			contractManager.updateContractState(contract);

			if(PayMent.CGBFNZ.value.equals(loanApply.getPayType()) ||
					PayMent.NBZZ.value.equals(loanApply.getPayType())||
					PayMent.SHNZ.value.equals(loanApply.getPayType())){
				// 更新内转状态
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("transferState", TransferState.DSH.getKey());
				params.put("modifyBy", UserUtils.getUserId());
				params.put("lendCodeC", loanApply.getApplyCode());
				transferRelationDao.updateTransferStatus(params);
			}
			
		}
	}

	/**
	 * ajax动态获得计划出借日期 2016年3月2日 By 周俊
	 * 
	 * @param platId
	 * @param bankId
	 * @param deductDate
	 *            计划划扣日期
	 * @param lendMoney
	 *            计划出借金额
	 * @return
	 */
	public Map<String, Object> ajaxApplyDeduct(String platId,
			String provinceCode, String bankId, Date deductDate,
			Double lendMoney) {
		/*
		 * PlatformBankEntity platformBank = new PlatformBankEntity();
		 * platformBank.setPlatformId(platId); platformBank.setBankId(bankId);
		 * platformBank.setDeductFlag(DeductFlagType.COLLECTION.getCode());
		 * platformBank.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
		 * platformBank.setStatus(UseFlag.QY.value); platformBank =
		 * platformBankService.getPlatformBank(platformBank); if
		 * (ObjectHelper.isEmpty
		 * (platformBank)||ObjectHelper.isEmpty(platformBank
		 * .getDayLimitMoney())) { throw new ServiceException("该笔出借不支持此划扣平台"); }
		 */
		String deductRule = platformGotoRuleManager.getDeductRule(platId,
				provinceCode, bankId);
		if (StringUtils.isBlank(deductRule)) {
			throw new ServiceException("该笔出借不支持此平台划扣规则,请确认后再操作");
		}
		long dayLimitMoneyAll = 0;
		for (String ruleTemp : deductRule.split(",")) {
			dayLimitMoneyAll = Long.valueOf(ruleTemp.split(":")[2])
					+ dayLimitMoneyAll;
		}
		if (dayLimitMoneyAll == 0) {
			throw new ServiceException("该笔出借的平台划扣限额异常,请确认后再操作");
		}
		// 计算需要划扣天数
		// Long lendMoneyLong = Long.valueOf(String.valueOf(lendMoney));
		lendMoney = lendMoney * 100;
		Integer deductDayS = (int) (lendMoney % dayLimitMoneyAll == 0 ? lendMoney
				/ dayLimitMoneyAll
				: lendMoney / dayLimitMoneyAll + 1);
		Date lendDateTemp = ReckonUtils.getDateAfter(deductDate, deductDayS);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lendDateTemp);
		Date lendDate = null;
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			lendDate = ReckonUtils.getDateAfter(deductDate, deductDayS + 2);
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			lendDate = ReckonUtils.getDateAfter(deductDate, deductDayS + 1);
		} else {
			lendDate = lendDateTemp;
		}
		if (bankId.equals(OpenBank.JTYH.value)) {
			lendDate = ReckonUtils.getDateAfter(lendDate, 1);
			calendar.setTime(lendDate);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				lendDate = ReckonUtils.getDateAfter(deductDate, deductDayS + 2);
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				lendDate = ReckonUtils.getDateAfter(deductDate, deductDayS + 1);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendDate", DateUtils.formatDate(lendDate, "yyyy-MM-dd"));
		map.put("applyDeductDays", deductDayS);
		return map;
	}

	/**
	 * 设置页面以外的出借信息 2016年4月11日 By 朱杰
	 * 
	 * @param lendApply
	 * @param customer
	 *            客户信息
	 * @param bankId
	 *            银行id
	 */
	private void setLoanApplyInfoOutPage(LoanApply lendApply,
			Customer customer, String bankId) {
		// 客户编号
		lendApply.setCustCode(customer.getCustCode());
		// 回款收款id
		lendApply.setPaymentBankId(bankId);
		lendApply.setReceiveBankId(bankId);
		// 出借状态
		if (!PayMent.ZJTG.value.equals(lendApply.getPayType())) {
			if(PayMent.ZZ.value.equals(lendApply.getPayType())){ 
				//自转,出借状态为文件合成中
				lendApply.setLendStatus(LendState.DCJSP.value);
			}else{
				// 非资金托管,出借状态为文件合成中
				lendApply.setLendStatus(LendState.WJHCZ.value);
			}
			
		} else {
			// 资金托管
			if (TrustState.KHCG.value.equals(customer.getApplyHostedStatus())) {
				// 已开户，,出借状态为文件合成中
				lendApply.setLendStatus(LendState.WJHCZ.value);
			} else if (TrustState.WKH.value.equals(customer
					.getApplyHostedStatus())
					|| TrustState.KHSB.value.equals(customer
							.getApplyHostedStatus())) {
				// 未开户,开户失败,出借状态为金账户待开户
				lendApply.setLendStatus(LendState.JZHDKH.value);
			}
		}

		lendApply.setBillDay(BillDateUtil.getBillDate(lendApply.getLendDate()));
		lendApply.setApplyEndStatus(FinishState.WWJ.value);

		lendApply.setManagerCode(UserUtils.getUserId());
		lendApply.setTeamOrgId(customer.getTeamOrgId());
		lendApply.setTeamManagerCode(customer.getTeamManagerCode());
		FortuneOrg storeOrg = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		FortuneOrg cityOrg = RelationShipUtil.getCityOrg(UserUtils.getUserId());
		if (null != storeOrg) {
			List<FortuneUser> list = RelationShipUtil.getOrgMember(storeOrg
					.getId(), Arrays
					.asList(new String[] { FortuneRole.STORE_MANAGER.id }),UserStatus.ON);
			if (ArrayHelper.isNotEmpty(list)) {
				lendApply.setStoreManagerCode(list.get(0).getId());
			}
			lendApply.setStoreOrgId(storeOrg.getId());
		}
		if (null != cityOrg) {
			List<FortuneUser> list = RelationShipUtil
					.getOrgMember(
							cityOrg.getId(),
							Arrays.asList(new String[] { FortuneRole.CITY_MANAGER.id }),UserStatus.ON);
			if (ArrayHelper.isNotEmpty(list)) {
				lendApply.setCityManagerCode(list.get(0).getId());
			}
			lendApply.setCityOrgId(cityOrg.getId());
		}
	}

	/**
	 * 设置产品月利率
	 * 
	 * 2016年4月11日 By 孙凯文
	 * 
	 * @param lendApply
	 */
	private void setProductRate(LoanApply lendApply) {
		BigDecimal productRate = getRate(lendApply);
		lendApply.setProductRate(productRate);
	}

	/**
	 * 获取产品利率
	 * 
	 * 2016年4月11日 By 孙凯文
	 * 
	 * @param lendApply
	 */
	@SuppressWarnings("rawtypes")
	public BigDecimal getRate(LoanApply lendApply) {
		BigDecimal productRate = BigDecimal.ZERO;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCode", lendApply.getProductCode());
		map.put("lendDay", lendApply.getLendDate());
		map.put("lendMoney", lendApply.getLendMoney().doubleValue());
		List<Map> result = dao.getProductRate(map);
		Date benchmarkDay = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
		boolean flag = false;
		if (ArrayHelper.isNotEmpty(result)) {
			// 收益调整中存在且不为空，取收益调整后的产品利率，否则取产品表利率
			Map m = result.get(0);
			Object realProductRate = m.get("real_product_rate");
			if (null != realProductRate && !"".equals(realProductRate)) {
				flag = true;
				productRate = new BigDecimal(realProductRate.toString());
			} else {
				if (benchmarkDay.after(lendApply.getLendDate())) {
					Object oldYearprofit = m.get("oldYearprofit");
					if (null != oldYearprofit && !"".equals(oldYearprofit)) {
						productRate = new BigDecimal(oldYearprofit.toString());
					}
				} else {
					Object newYearprofit = m.get("newYearprofit");
					if (null != newYearprofit && !"".equals(newYearprofit)) {
						productRate = new BigDecimal(newYearprofit.toString());
					}
				}
			}
		} else {
			// 取产品表利率
			Product p = dao.getProduct(lendApply.getProductCode());
			if (null != p) {
				if (benchmarkDay.after(lendApply.getLendDate())) {
					productRate = p.getOldYearprofit() == null ? BigDecimal.ZERO
							: p.getOldYearprofit();
				} else {
					productRate = p.getNewYearprofit() == null ? BigDecimal.ZERO
							: p.getNewYearprofit();
				}
			}
		}
		if ((ProductCode.YXT.value.equals(lendApply.getProductCode()) || ProductCode.XHYZ.value
				.equals(lendApply.getProductCode())) && !flag) {
			productRate = productRate.divide(new BigDecimal("12"), 5,
					BigDecimal.ROUND_HALF_UP);
		}
		return productRate;
	}

	/**
	 * 获得单日划扣限额 2016年3月2日 By 周俊
	 * 
	 * @param platId
	 * @return
	 */
	/*
	 * public Long getDayLimitMoney(String platId, String bankId) { //
	 * 划扣方式为实时的单日划扣限额 Long dayLimitMoneyRealtime =
	 * platformBankService.getPlatformBank( platId, bankId,
	 * DeductFlagType.COLLECTION.getCode(),
	 * DeductType.REALTIME.getCode()).getDayLimitMoney(); // 批量划扣时单日划扣限额 Long
	 * dayLimitMoneyBatch = platformBankService.getPlatformBank(platId, bankId,
	 * DeductFlagType.COLLECTION.getCode(),
	 * DeductType.BATCH.getCode()).getDayLimitMoney(); // 单日划扣限额 Long
	 * dayLimitMoney = null; if (!ObjectHelper.isEmpty(dayLimitMoneyRealtime) &&
	 * !ObjectHelper.isEmpty(dayLimitMoneyBatch)) { if (dayLimitMoneyRealtime >
	 * dayLimitMoneyBatch) { dayLimitMoney = dayLimitMoneyRealtime; } else {
	 * dayLimitMoney = dayLimitMoneyBatch; } } else if
	 * (ObjectHelper.isEmpty(dayLimitMoneyRealtime)) { dayLimitMoney =
	 * dayLimitMoneyBatch; } else if (ObjectHelper.isEmpty(dayLimitMoneyBatch))
	 * { dayLimitMoney = dayLimitMoneyRealtime; }
	 * 
	 * return dayLimitMoney; }
	 */

	/**
	 * 生成出借合同 2016年4月12日 By 朱杰
	 * 
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public boolean makingFile(LoanApply loanApply, Customer customer) {
		boolean r = false;

		try {
			// 确认出借存在
			LoanApply elem = dao.getLoanApplyByCode(loanApply.getApplyCode());
			if (elem == null) {
				throw new ServiceException("文件合成失败，出借不存在");
			}

			// 模板地址
			String url = Constant.getProperties.get("template_cpt_url");
			// 模板类型
			String typeStr = "";
			if (ProductCode.YMY.getValue().equals(elem.getProductCode())) {
				// 月满盈合同
				typeStr = Constant.getProperties.get(ReportType.CJ_SQ_YMY
						.getCode());
			} else if (ProductCode.NNJ.getValue().equals(elem.getProductCode())) {
				// 年年金合同
				typeStr = Constant.getProperties.get(ReportType.CJ_SQ_NNJ
						.getCode());
			} else {
				// 月满盈、年年金以外的合同
				typeStr = Constant.getProperties
						.get(ReportType.CJ_SQ.getCode());
			}

			StringBuffer sb = new StringBuffer(url);
			sb.append(typeStr);
			sb.append("&lend_code=" + loanApply.getApplyCode());
			sb.append("&format=pdf");

			// CE认证
			CERequestContext ceRequestContext = CEContextHelper.loginWithUP(
					Constant.getProperties.get("ceuser"),
					Constant.getProperties.get("cepwd"));
			CEContextHolder.setContext(ceRequestContext);
			// 创建连接
			// 构建url
			URL uri = null;
			uri = new URL(sb.toString());

			URLConnection conn = uri.openConnection();
			InputStream inStream = conn.getInputStream();
			DmService dmService = DmService.getInstance();
			// 把生成的文档上传到文件服务器
			DocumentBean doc = dmService.createDocument(
					ReportType.CJ_SQ.getName() + ".pdf", inStream,
					DmService.BUSI_TYPE_FORTUNE, customer.getCustCode(),
					loanApply.getApplyCode(), UserUtils.getUser(UserUtils.getUserId()).getName());
			if (doc != null && StringUtils.isNotEmpty(doc.getDocId())) {
				// 附件表中保存出借申请书，出借状态改为待审批
				Attachment att = new Attachment();
				att.preInsert();
				att.setAttaId(att.getId());
				att.setAttaFilepath(doc.getDocId());
				att.setAttaFilename(ReportType.CJ_SQ.getName()
						+ DateUtils.getDate("yyyyMMddHHmmss") + ".pdf");// 文件名
				att.setAttaNewfilename(ReportType.CJ_SQ.getName()
						+ DateUtils.getDate("yyyyMMddHHmmss") + ".pdf");// 新文件名
				att.setLoanCode(loanApply.getApplyCode());
				att.setAttaFileOwner("t_tz_loan_apply");
				att.setDictAttaFlag(FileConstants.FILE_TYPE_CJHTSQS);
				att.setAttaTableId(loanApply.getId());
				attachmentManager.saveFile(att);
				r = true;

			} else {
				// 出借状态改为文件合成失败
				LoanApply update = dao.get(loanApply);
				// 文件合成失败
				update.setLendStatus(LendState.WJHCSB.value);
				update.preUpdate();
				dao.update(update);
			}

		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(loanApply.getApplyCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.LEND_APPLY.getCode());
			forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
			forException.setRemark("生成合同文件失败");

			forException.preInsert();
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(
					FortuneExceptionDao.class);
			forDao.insert(forException);

			// 出借状态改为文件合成失败
			LoanApply update = dao.get(loanApply);
			if (update != null) {
				// 文件合成失败
				update.setLendStatus(LendState.WJHCSB.value);
				update.preUpdate();
				dao.update(update);
			}

		}
		return r;
	}

	/**
	 * 出借合同加盖作废章 2016年5月11日 By 朱杰
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void caInvalidLendApply(String lendCode) {
		LoanApply loanApply = dao.getLoanApplyByCode(lendCode);

		Attachment att = new Attachment();
		att.setLoanCode(lendCode);
		att.setAttaFileOwner("t_tz_loan_apply");
		att.setDictAttaFlag(FileConstants.FILE_TYPE_CJHTSQS);
		att.setAttaTableId(loanApply.getId());
		List<Attachment> attachment = attachmentManager.findList(att);
		if (attachment.size() > 0) {
			DmService dmService = DmService.getInstance();
			// 出借合同作废签章
			CaCustomerSign param = new CaCustomerSign();
			param.setBatchNo(loanApply.getCustCode());
			param.setSubType(loanApply.getApplyCode());
			Ca_SignOutBean out = CaUtil.sign(CASignType.ZF_CONTRACT.value,
					attachment.get(0).getAttaFilepath(), param);
			if (!ReturnConstant.SUCCESS.equals(out.getRetCode())) {
				throw new ServiceException(out.getRetCode() + ":"
						+ out.getRetMsg());
			}
			// 将文件移动至固定文件夹内
			/*dmService.moveDocument(out.getDocId(), loanApply.getCustCode()
					+ "/" + loanApply.getApplyCode(),
					DmService.BUSI_TYPE_FORTUNE);*/
			// 更新成已经签章的附件
			att.setAttaId(attachment.get(0).getAttaId());
			att.setAttaFilepath(out.getDocId());
			att.preUpdate();
			attachmentManager.updateAttachment(att);
		}

		// 修改合同为作废状态
		Contract contract = new Contract();
		contract.setContCode(loanApply.getContractNumber());
		contract.setDictContStatus(ContractState.QCZF.value);
		contract.setModifyTime(new Date());
		contract.setModifyBy(UserUtils.getUserId());
		contractDao.updateContractState(contract);

	}
}
