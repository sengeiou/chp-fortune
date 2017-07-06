package com.creditharmony.fortune.lend.apply.workflow;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;

@Service
public class LendApplyFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Resource(name = "ApplyIdServiceImpl")
	private ApplyIdService applyIdService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LendApplyManager lendApplyManager;
	@Autowired
	private CustomerAccountDao customerAccountDao;

	/**
	 * 发起出借申请流程 2015年12月24日
	 * 
	 * @param workItem
	 * @param view
	 * @return none
	 */
	public String launchFlow(WorkItemView workItem, LendApplyView view) {

		String applyId = applyIdService.builderApplyId(workItem.getFlowType());
		view.setApplyId(applyId);
		this.setFlowInfoExtra(view);

		Customer customer = customerDao.getCustomerbyCode(view.getCustomer());
		LoanApply lendApply = view.getLendApply();

		if (!PayMent.ZJTG.value.equals(lendApply.getPayType())
				|| TrustState.KHCG.value
						.equals(customer.getApplyHostedStatus())) {
			// 非资金托管或者是金账户开户成功的情况
			lendApplyManager.saveLaunchForm(view);
			
			if(PayMent.ZZ.value.equals(lendApply.getPayType())){ //自转 的出借 不生成合同
				// 非资金托管,提交工作流
				this.toNextFlow(workItem, view);
			}else{
				// 生成出借合同
				boolean r = lendApplyManager.makingFile(lendApply, customer);
				
				if (r) {
					// 非资金托管,提交工作流
					this.toNextFlow(workItem, view);
				} else {
					return "合同申请书合成失败,请在已办列表重新合成!";
				}
			}
		} else {
			if (TrustState.WKH.value.equals(customer.getApplyHostedStatus())
					|| TrustState.KHSB.value.equals(customer
							.getApplyHostedStatus())) {
				// 未开户,保存出借信息 不提交工作流
				lendApplyManager.saveLaunchForm(view);

			} else {
				return "托管状态为"
						+ TrustState.getTrustState(customer
								.getApplyHostedStatus()) + ",不能提交资金托管的出借申请";
			}
		}
		return "";
	}

	/**
	 * 工作流字段补充 2016年5月13日 By 朱杰
	 * 
	 * @param view
	 */
	public void setFlowInfoExtra(LendApplyView view) {
		view.setApplyDate(new Date());
		User userInfo = (User) UserUtils.getSession().getAttribute(
				SystemConfigConstant.SESSION_USER_INFO);
		// 划扣银行
		CustomerAccount account = customerAccountDao.get(view
				.getPaymentBankId());
		// 保存理财经理
		view.setFinancialManager(userInfo.getId());
		view.setDeductBank(account.getAccountBankId());
		view.setOrg(new Org());
		view.setCreateDate(new Date());
		view.setLendMoney(Double.valueOf(view.getLendApply().getLendMoney()
				.toString()));
		FortuneOrg storeOrg = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		if (null != storeOrg) {
			view.setProvince(storeOrg.getProvinceId());
			view.setCity(storeOrg.getCityId());
		}
	}

	/**
	 * 进入下一个工作流 2016年5月13日 By 朱杰
	 * 
	 * @param workItem
	 * @param view
	 */
	public void toNextFlow(WorkItemView workItem, LendApplyView view) {
		workItem.setResponse(CustomerConstants.WorkFlow.CONTRACT_FLOW);
		workItem.setBv(view);
		this.fs.launchFlow(workItem);
	}
}
