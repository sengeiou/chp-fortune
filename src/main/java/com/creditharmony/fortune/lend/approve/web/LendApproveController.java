package com.creditharmony.fortune.lend.approve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.AccountManager;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyQueryView;
import com.creditharmony.fortune.lend.approve.manager.LendApproveManager;
import com.creditharmony.fortune.lend.approve.view.LendApplyApprovalItemView;
import com.creditharmony.fortune.lend.approve.workflow.LendApproveFlowManager;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipAccountManager;
import com.creditharmony.fortune.users.service.UserInfoService;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借审批类
 * 
 * @Class Name LendApproveController
 * @author 朱杰
 * @Create In 2016年4月10日
 */
@Controller
@RequestMapping("${adminPath}/lend/approve")
public class LendApproveController extends BaseController {
	@Autowired
	private CustomerManager customerService;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private LendApproveManager lendApproveManager;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private LoanApplyManager loanApplyManager;
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
	@Resource
	private LendApproveFlowManager lendApproveFlowManager;
	@Autowired
	private UserInfoService userInfoService;

	
	/**
	 * 获取审批待办列表
	 * 
	 * 2016年4月10日
	 * By 朱杰
	 * @param view
	 * @param model
	 * @param workItem
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "fetchTaskItems" })
	public String fetchTaskItems(LendApplyQueryView view, Model model, WorkItemView workItem
			,HttpServletRequest request, HttpServletResponse response) {
		//查询审核人
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> auditors  = userInfoService.getAuditor(map);
		
		Page<LendApplyApprovalItemView> page = new Page<LendApplyApprovalItemView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<LendApplyApprovalItemView> list = lendApproveManager.fetchTaskItems(workItem, view, page, flowPage);
		model.addAttribute("lendApply", view);
		model.addAttribute("items", list);
		model.addAttribute("page", flowPage);
		model.addAttribute("auditors", auditors);
		
		if(ArrayHelper.isNotEmpty(list)){
			model.addAttribute("totalCount", list.size());
			Double sum = new Double("0");
			for(LendApplyApprovalItemView v : list){
				sum = sum + v.getLendMoney();
			}
			model.addAttribute("totalMoney", sum);
		}else{
			model.addAttribute("totalCount", 0);
			model.addAttribute("totalMoney", "0.0");
		}
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_lend_state","tz_deduct_plat","tz_open_bank","tz_pay_type"});
		return "/lend/approve/lendApply_fetch_list";
	}
	
	/**
	 * 出借审批
	 * 2015年12月31日
	 * 
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param log
	 * @return
	 */
	@RequestMapping({ "dispatch" })
	public String dispatchFlow(WorkItemView workItem,
			RedirectAttributes redirectAttributes,
			HttpServletResponse response, LendApplyLog log) {
		try{
			lendApproveFlowManager.dispatchFlow(workItem, log);
			/**
			 * 出借申请审批详细页面选择审核通过或审核不通过，点击确定按钮后弹出提示框：
					(1)点击审批通过或者不通过都会提示“编号XXX审核批成功”
   					1.点击审核不通过提示“编号XXX审核成功”
   					2.点击审核不通过提示“编号XXX审核未通过”

			 */
			if (YoN.SHI.value.equals(log.getCheckExaminetype())) {
				redirectAttributes.addFlashAttribute("content", "编号【"
						+ log.getLendCode() + "】审核成功");
			}else{
				redirectAttributes.addFlashAttribute("content", "编号【"
						+ log.getLendCode() + "】审核未通过");
			}
			/*redirectAttributes.addFlashAttribute("content", "编号【"
					+ log.getLendCode() + "】审核成功");*/
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("content", "编号【"
					+ log.getLendCode() + "】审核失败:" + e.getMessage());
			logger.error("编号【" + log.getLendCode() + "】审核失败:", e);
		}
		
		return "redirect:" + this.adminPath + "/lend/approve/fetchTaskItems";
	}
	
	/**
	 * 生成电子签章文件
	 * 2016年4月10日
	 * By 朱杰
	 * @param lendCode
	 * @return
	 */
	@RequestMapping({ "makingFileCAS" })
	@ResponseBody
	public String makingFileCAS(String lendCode){
		
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		Customer customer = new Customer();
		customer.setCustCode(loanApply.getCustCode());
		customer = customerDao.getCustomer(customer);
		return jsonMapper.toJson(lendApproveManager.makingFileCAS(loanApply, customer));
		
	}
	
	/**
	 * 重新合成电子签章文件
	 * 2016年4月10日
	 * By 朱杰
	 * @param lendCode
	 * @return
	 */
	@RequestMapping({ "caAgain" })
	@ResponseBody
	public String caAgain(String[] lendCodes){
		String trnMsg = "";
		if(lendCodes!=null && lendCodes.length > 0){
			for (String lendCode : lendCodes) {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = loanApplyDao.get(loanApply);
				Customer customer = new Customer();
				customer.setCustCode(loanApply.getCustCode());
				customer = customerDao.getCustomer(customer);
				trnMsg += lendCode +":" + lendApproveManager.makingFileCAS(loanApply, customer);
			}
		}
		
		return jsonMapper.toJson(trnMsg);
		
	}
	
}
