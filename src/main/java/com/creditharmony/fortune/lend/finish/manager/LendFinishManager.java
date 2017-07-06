package com.creditharmony.fortune.lend.finish.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendUserInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.lend.finish.dao.LendFinishDao;

/**
 * 我的客户-查看投资信息
 * 
 * @Class Name LoanApplyManager
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Service
public class LendFinishManager extends CoreManager<LendFinishDao, LoanApply> {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private TransferRelationDao transferRelationDao;

	/**
	 * 获取出借列表 2016年2月16日
	 * 
	 * @author 孙凯文
	 * @param page
	 * @param map
	 * @return Page<LoanApply>
	 */
	public Page<LoanApply> findLendApply(Page<LoanApply> page, Map<String, Object> map) {
		map.put("login_user", UserUtils.getUserId());
		map.put("defaultOrderSql", CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		try {
			PageList<LoanApply> pageList = (PageList<LoanApply>) this.dao.findLendApply(map, pageBounds);
			PageUtil.convertPage(pageList, page);
		} catch (Exception e) {
			logger.error("获取数据异常", e);
		}
		return page;
	}

	/**
	 * 获取转投大金融查看列表
	 */
	public Page<LoanApply> findLendApplyToDJRChk(Page<LoanApply> page, Map<String, Object> map) {
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			map.put("dataRights", dataRights);
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		PageList<LoanApply> pageList = (PageList<LoanApply>) this.dao.findLendApplyToDJRChk(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取出借列表，用于出借申请中，内部转账列表查询 2016年2月16日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return
	 */
	public List<LoanApply> getLoanApplyList(String customerCode) {
		if ("".equals(customerCode) || null == customerCode) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerCode", customerCode);
		params.put("ZJTG", PayMent.ZJTG.value);
		params.put("HKCG", LendState.HKCG.value);
		return dao.getLoanApplyList(params);
	}
	
	/**
	 * 新增出借申请-付款方式为 自转的选择项列表
	 * @author xurongsheng
	 * @date 2017年2月4日 上午10:42:28
	 * @param customerCode
	 * @return
	 */
	public List<LoanApply> getLoanApplyList4ZZ(Map<String,Object> paramMap){
		return dao.getLoanApplyList4ZZ(paramMap);
	}

	/**
	 * 根据出借编号获得单个出借 2015年12月24日 By 周俊
	 * 
	 * @param lendCode
	 * @return
	 */
	public BigDecimal getLoanApplyByLendMoney(String lendCode) {
		String[] array = lendCode.split(",");
		if (ArrayHelper.isNotNull(array)) {
			if (array.length == 1) {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = dao.get(loanApply);
				return loanApply.getLendMoney();
			}
			BigDecimal lendMoney = new BigDecimal(0);
			for (String lendCode1 : array) {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode1);
				loanApply = dao.get(loanApply);
				lendMoney = lendMoney.add(loanApply.getLendMoney());
			}
			return lendMoney;
		}
		return null;
	}

	/**
	 * 统计总金额
	 * 
	 * @param map
	 * @return
	 */
	public String findLendApplyTotalMoney(Map<String, Object> map) {
		Boolean authFilter = (Boolean) map.get("authFilter");
		if (authFilter) {
			String dataRights = DataScopeUtil.getDataScope("a",
					SystemFlag.FORTUNE.value);
			if (StringUtils.isNotEmpty(dataRights)) {
				map.put("dataRights", dataRights);
			}
		}
		return this.dao.findLendApplyTotalMoney(map);
	}

	/**
	 * 金账户开户失败修改(托管状态变成申请中) 2016年4月19日 By 郭才林
	 * 
	 * @param apply
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int update(LoanApply apply) {
		Customer customer = new Customer();
		customer.setCustCode(apply.getCustCode());
		customer.setApplyHostedStatus(TrustState.SQZ.value);
		customer.setTrusteeshipRetMsg(":");
		customer.preUpdate();
		customer.setVerTime(apply.getVerTime());
		int r = customerDao.updateTrusteeship(customer);

		// 修改 t_tz_loan_apply表的lend_status 为0 金账户待开户
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(apply.getApplyCode());
		loanApply.setLendStatus(LendState.JZHDKH.value);
		loanApplyDao.updateLendStatus(loanApply);

		if (r > 0) {
			checkManager.addCheck(apply.getApplyCode(), "金账户开户失败！重新修改！", "提交");
		}
		return r;

	}

	/**
	 * 获取内转出借的父出借列表 2016年3月8日 By 陈广鹏
	 * 
	 * @param lendCode
	 * @return
	 */
	public List<LoanApply> findTransfers(String lendCode) {
		List<LoanApply> transferLoanApplyList = new ArrayList<LoanApply>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("childLendCode", lendCode);
		List<TransferRelation> transferList = transferRelationDao.findList(map);
		if (ArrayHelper.isNotEmpty(transferList)) {
			LoanApply a = null;
			for (TransferRelation item : transferList) {
				a = loanApplyDao.getLoanApplyByCode(item.getLendCodeParent());
				if (null != a) {
					a.setTransferMoney(item.getTransferMoney());
					transferLoanApplyList.add(a);
				}
			}
		}
		return transferLoanApplyList;
	}

	public DjrSwitchSendUserInBean getLoanApplyDJR(String lendCode) {
		if (StringUtils.isEmpty(lendCode)) {
			return null;
		}
		return dao.getLendApplyDJR(lendCode);
	}

	public void updateSwitchApproveStatus(LoanApply la) {
		this.dao.updateSwitchApproveStatus(la);
	}
	
	public String getLoanApplySwitchApproveStatus(String lendCode) {
		if (StringUtils.isEmpty(lendCode)) {
			return null;
		}
		return dao.getLendApplySwitchApproveStatus(lendCode);
	}
}
