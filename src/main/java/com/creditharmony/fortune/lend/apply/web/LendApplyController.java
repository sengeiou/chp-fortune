package com.creditharmony.fortune.lend.apply.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.AccountManager;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.apply.workflow.LendApplyFlowManager;
import com.creditharmony.fortune.lend.finish.manager.LendFinishManager;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipAccount;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipAccountManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借申请类
 * 
 * @Class Name LendApplyController
 * @author 朱杰
 * @Create In 2016年4月10日
 */
@Controller
@RequestMapping("${adminPath}/lend/apply")
public class LendApplyController extends BaseController {
	@Autowired
	private CustomerManager customerService;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private LendFinishManager lendFinishManager;
	@Autowired
	private ContractManager contractManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private TrusteeshipAccountManager trusteeshipAccountManager;
	@Autowired
	private CustomerAccountDao accountDao;
	@Autowired
	private LendApplyFlowManager lendApplyFlowManager;

	/**
	 * 出借申请一览 2016年4月10日 By 朱杰
	 * 
	 * @param model
	 * @param customer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String findCustomerList(@ModelAttribute Customer customerParam,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Page<Customer> page = new Page<Customer>(request, response);
		customerService.getOpenAccountCustomer(page, customerParam);
		// List<Customer> list = page.getList();
		// FortuneOrg teamOrg = null;
		// List<FortuneUser> members = null;
		// if (ArrayHelper.isNotEmpty(list)) {
		// for (Customer c : list) {
		// teamOrg = RelationShipUtil.getTeamOrg(c.getManagerCode());
		// if (null != teamOrg) {
		// c.setTeamName(teamOrg.getName());
		// members = RelationShipUtil.getOrgMember(teamOrg.getId(),
		// Arrays.asList(new String[] { FortuneRole.TEAM_MANAGER.id }));
		// if (ArrayHelper.isNotEmpty(members)) {
		// c.setTeamManagerName(members.get(0).getName());
		// }
		// }
		// }
		// }

		model.addAttribute("customer", customerParam);
		model.addAttribute("page", page);

		FortuneDictUtil.addDicts(model, new String[] { "tz_customer_src",
				"tz_customer_state", "tz_invest_state", "tz_trust_state",
				"tz_data_source" });

		return "lend/apply/lendApplyList";
	}

	/**
	 * 提交出借申请
	 * 
	 * 2016年4月10日 By 朱杰
	 * 
	 * @param redirectAttributes
	 * @param model
	 * @param workItem
	 * @param view
	 * @return
	 */
	@RequestMapping({ "launchFlow" })
	public String launchFlow(RedirectAttributes redirectAttributes,
			Model model, WorkItemView workItem, LendApplyView view) {
		String rtnMag = lendApplyFlowManager.launchFlow(workItem, view);
		if (StringUtils.isNotEmpty(rtnMag)) {
			// 有返回信息
			redirectAttributes.addFlashAttribute("content", "客户【"
					+ view.getCustomer().getCustName() + "】" + rtnMag);
		} else {
			redirectAttributes.addFlashAttribute("content", "客户【"
					+ view.getCustomer().getCustName() + "】提交出借申请成功");
		}

		return "redirect:" + this.adminPath + "/lend/apply/list";
	}

	/**
	 * 生成文件 2016年4月10日 By 朱杰
	 * 
	 * @param lendCode
	 * @return
	 */
	@RequestMapping({ "makingFile" })
	@ResponseBody
	public String makingFile(String lendCode, String applyId) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = loanApplyDao.get(loanApply);

			Customer customer = new Customer();
			customer.setCustCode(loanApply.getCustCode());
			customer = customerDao.getCustomer(customer);
			WorkItemView workItem = new WorkItemView();
			workItem.setFlowCode("lendApply");
			workItem.setFlowName("出借申请流程");
			workItem.setFlowType("CF0007");
			workItem.setStepName("@launch");
			LendApplyView view = new LendApplyView();
			view.setApplyId(applyId);
			view.setLendApply(loanApply);
			view.setPaymentBankId(loanApply.getPaymentBankId());
			view.setReceiveBankId(loanApply.getReceiveBankId());
			view.setCustomer(customer);
			lendApplyFlowManager.setFlowInfoExtra(view);
			boolean r = lendApplyManager.makingFile(loanApply, customer);
			if (r) {
				checkManager.addCheck(loanApply.getApplyCode(), "重新合成合同", "合成");
				// 非资金托管,提交工作流
				lendApplyFlowManager.toNextFlow(workItem, view);
				map.put("result", "true");
				map.put("message", "合成成功");
			} else {
				map.put("result", "false");
				map.put("message", "文件合成失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			map.put("message", e.getMessage());
		}
		return jsonMapper.toJson(map);
	}
	
	/**
	 * 获取出借信息
	 * @author xurongsheng
	 * @date 2017年2月4日 下午2:29:09
	 * @param lendCode
	 * @return
	 */
	@RequestMapping({ "getLoanInfo" })
	@ResponseBody
	public String getLoanInfo(String lendCode) {
		Map<String, String> map = new HashMap<String, String>();
		LoanApply paramObj = new LoanApply();
		paramObj.setApplyCode(lendCode);
		LoanApply la = lendApplyManager.get(paramObj);
		if(la != null){
			map.put("isExist", "true");
			map.put("deductDate", DateUtils.formatDate(la.getDeductDate(), "yyyy-MM-dd")); //计划划扣日期
			map.put("lendDate", DateUtils.formatDate(la.getLendDate(), "yyyy-MM-dd")); //计划出借日期
			map.put("productCode", la.getProductCode());//出借模式
			map.put("lendMoney", la.getLendMoney().toString());//出借金额
			map.put("contractNumber", la.getContractNumber());//合同编号
			map.put("protocoEdition", la.getProtocoEdition());//协议版本
		}else{
			map.put("isExist", "false");
		}
		
		return jsonMapper.toJson(map);
	}

	/**
	 * 根据客户编号获取出借列表 内转 2016年4月10日 By 朱杰
	 * 
	 * @param customerCode
	 * @param applyCode
	 * @param payType
	 * @param model
	 * @return
	 */
	@RequestMapping("selectLend")
	public String selectLend(String customerCode, String applyCode,
			String payType, Model model) {
		List<LoanApply> list = lendFinishManager.getLoanApplyList(customerCode);

		List<LoanApply> listfind = new ArrayList<LoanApply>();
		// 过滤数据
		if (ArrayHelper.isNotEmpty(list)) {
			for (LoanApply loan : list) {
				if (!loan.getApplyCode().equals(applyCode)) { // 非自身
					String backStatus = loan.getBackMoneyStatus();
					String backType = loan.getBackMoneyType();
					String switchApproveStatus = loan.getSwitchApproveStatus();
					if (PayMent.NBZZ.value.equals(payType)) { // 内部转账
						if (!(BackState.DHK.value.equals(backStatus) // 不是以下条件的
																		// //待回款
								|| BackState.DHKQR.value.equals(backStatus) // 待回款确认
								|| BackState.YHK.value.equals(backStatus) // 已回款
								|| BackState.BFHKCG.value.equals(backStatus) // 部分回款成功
								|| BackType.CJSB.value.equals(backType) // 出借失败
								|| BackType.QBBJNBZZ.value.equals(backType) // 全部本金内部转账
								|| BackType.TQSH.value.equals(backType)
								||FortuneSwitchApproveStatus.DSP.value.equals(switchApproveStatus)
								||FortuneSwitchApproveStatus.SPCG.value.equals(switchApproveStatus)
								||FortuneSwitchApproveStatus.ZTCG.value.equals(switchApproveStatus))) { // 提前赎回
							logger.info("新增内转出借编号" + loan.getApplyCode());
							listfind.add(loan);
						}
					} else if (PayMent.CGBFNZ.value.equals(payType)) { // 成功部分内转
						if ((!BackState.DHK.value.equals(backStatus) // 非 待回款
								&& !BackState.DHKQR.value.equals(backStatus) // 非
																				// 待回款确认
								&& !BackState.YHK.value.equals(backStatus) // 非
																			// 已回款
						&& !BackState.BFHKCG.value.equals(backStatus)) // 非
																		// 部分回款成功
								&& BackType.CJSB.value.equals(backType) // 出借失败
								&& loan.getActualBackMoney().doubleValue() > 0.0) { // 实际回款金额
																					// >
																					// 0
							// 状态：待回款、待回款确认、已回款并且类型为：出借失败、全部本金内部转账、提前赎回
							logger.info("新增内转出借编号" + loan.getApplyCode());
							listfind.add(loan);
						}
					}
				}
			}
		}

		// 查看是否内转，及内转出借申请单的状态； 内转状态不为（划扣成功/撤销）状态时，需要被过滤掉
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerCode", customerCode);
		List<TransferRelation> transferRelations = transferRelationDao
				.getLendRelationInfo4Apply(map);

		if (ArrayHelper.isNotEmpty(transferRelations)
				&& ArrayHelper.isNotEmpty(listfind)) {
			Iterator<LoanApply> it = listfind.iterator(); // 出借信息
			LoanApply apply = null;
			while (it.hasNext()) {
				apply = it.next();
				String itemApplyCode = apply.getApplyCode();
				if (StringUtils.isBlank(itemApplyCode)) {
					it.remove();
					continue;
				}
				// 内部转账时，过滤掉“非划扣成功”的数据
				if (PayMent.NBZZ.value.equals(payType)
						&& !LendState.HKCG.value.equals(apply.getLendStatus())) {
					logger.info("删除内转出借编号" + apply.getApplyCode()
							+ ",原因为出借状态不是划扣成功");
					it.remove();
					continue;
				}

				for (TransferRelation relation : transferRelations) {
					if (itemApplyCode.equals(relation.getLendCodeParent())) { // 找到
																				// apply
																				// 对应的
																				// relation
						if (StringUtils.isNotBlank(applyCode)) { //
							if (LendState.HKCG.value.equals(relation
									.getChildLendStatus())
									|| LendState.CX.value.equals(relation
											.getChildLendStatus())
									|| applyCode.equals(relation
											.getLendCodeChilde())) { // 划扣成功、撤销时不移除
								continue;
							} else { // 内转列表中移除此数据
								logger.info("删除内转出借编号" + apply.getApplyCode()
										+ ",原因为出借状态不是划扣成功或撤销");
								it.remove();
								break;
							}
						} else {
							if (LendState.HKCG.value.equals(relation
									.getChildLendStatus())
									|| LendState.CX.value.equals(relation
											.getChildLendStatus())) { // 划扣成功、撤销时不移除
								continue;
							} else { // 内转列表中移除此数据
								logger.info("删除内转出借编号" + apply.getApplyCode()
										+ ",原因为出借状态不是划扣成功或撤销");
								it.remove();
								break;
							}
						}
					}
				}
			}
		}

		model.addAttribute("loanApplys", listfind);

		FortuneDictUtil.addDicts(model, new String[] { "tz_pay_type",
				"tz_lend_state", "tz_back_state", "tz_back_type" });
		return "lend/apply/selectLendApplyList";
	}
	
	/**
	 * 选择出借[自转]
	 * @author xurongsheng
	 * @date 2017年2月5日 上午11:11:39
	 * @param customerCode
	 * @param applyCode
	 * @param certNum
	 * @param payType
	 * @param model
	 * @return
	 */
	@RequestMapping("toLend4ZZ")
	public String toLend4ZZ(String customerCode, String applyCode, String certNum,
			String payType, Model model) {
		/*Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("customerCode", customerCode);
		paramMap.put("applyCode", applyCode);
		paramMap.put("certNum", certNum);
		List<LoanApply> list = lendFinishManager.getLoanApplyList4ZZ(paramMap);*/
		List<LoanApply> listfind = new ArrayList<LoanApply>();
		/*// 过滤数据
		if (ArrayHelper.isNotEmpty(list)) {
			for (LoanApply loan : list) {
				listfind.add(loan);
			}
		}*/
		model.addAttribute("loanApplys", listfind);
		model.addAttribute("customerCode", customerCode);
		model.addAttribute("applyCode", applyCode);
		model.addAttribute("certNum", certNum);
		model.addAttribute("payType", payType);
		FortuneDictUtil.addDicts(model, new String[] { "tz_pay_type",
				"tz_lend_state", "tz_back_state", "tz_back_type" });
		return "lend/apply/selectLendApplyList4ZZ";
	
	}
	
	/**
	 * 选择出借[自转]
	 * @author xurongsheng
	 * @date 2017年2月4日 上午10:02:03
	 * @param customerCode
	 * @param applyCode
	 * @param payType
	 * @param model
	 * @return
	 */
	@RequestMapping("selectLend4ZZ")
	public String selectLend4ZZ(String customerCode, String applyCode, String certNum,
			String payType, Model model) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("customerCode", customerCode);
		paramMap.put("applyCode", applyCode);
		paramMap.put("certNum", certNum);
		List<LoanApply> list = lendFinishManager.getLoanApplyList4ZZ(paramMap);
		List<LoanApply> listfind = new ArrayList<LoanApply>();
		// 过滤数据
		if (ArrayHelper.isNotEmpty(list)) {
			for (LoanApply loan : list) {
				listfind.add(loan);
			}
		}
		model.addAttribute("loanApplys", listfind);
		model.addAttribute("customerCode", customerCode);
		model.addAttribute("applyCode", applyCode);
		model.addAttribute("certNum", certNum);
		model.addAttribute("payType", payType);
		FortuneDictUtil.addDicts(model, new String[] { "tz_pay_type",
				"tz_lend_state", "tz_back_state", "tz_back_type" });
		return "lend/apply/selectLendApplyList";
	}

	@RequestMapping("/autoOpenAccount")
	@ResponseBody
	public String autoOpenAccount(String custCode) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			TrusteeshipAccount account = trusteeshipAccountManager
					.getAccountByCustomerCode(custCode);
			String returnMessage = trusteeshipAccountManager.openAccount(
					account, false);
			map.put("message", returnMessage);
		} catch (Exception e) {
			map.put("message", "金账户开户出现异常：" + e.getMessage());
		}

		return jsonMapper.toJson(map);
	}

}
