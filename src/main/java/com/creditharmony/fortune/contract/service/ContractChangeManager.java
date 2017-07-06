package com.creditharmony.fortune.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.ContractChangeType;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.BaseDeptOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.dao.ContractChangeDao;
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.entity.ContractChange;
import com.creditharmony.fortune.contract.view.ContractChangeExcelView;
import com.creditharmony.fortune.contract.view.ContractChangeParamView;
import com.creditharmony.fortune.contract.view.ContractChangeView;
import com.creditharmony.fortune.contract.view.ContractView;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.utils.AttachmentUtil;

/**
 * 状态变更
 * @Class Name ContractChangeManager
 * @author 李放
 * @Create In 2015年12月4日
 */
@Service
public class ContractChangeManager extends CoreManager<ContractChangeDao,ContractChange> {
	
	@Autowired
	private ContractChangeDao contractChangeDao;
	
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private   OrgDao  orgDao;
	/**
	 * 根据Id把参数带入详细页
	 * 2015年12月10日
	 * By 李放
	 * @param id
	 * @return ContractChangeView
	 */
	public ContractChangeView getContractChangeById(String id) {
		ContractChangeView cb = contractChangeDao.getContractChangeById(id);
		return cb;
	}
	
	/**
	 * 根据合同编号进入变更申请详细页面
	 * 2015年12月11日
	 * By 李放
	 * @param contCode
	 * @return ContractView
	 */
	public ContractView getContractInformation(String contCode) {
		return contractChangeDao.getContractInformation(contCode);
	}

	/**
	 * 添加变更申请
	 * @param contractChange 变更申请
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void insertContractChange(ContractChange contractChange) {
		
		contractChange.preInsert();
		contractChangeDao.insertContractChange(contractChange);
		
		//更新上传附件的信息
		if(ContractChangeType.ZF.value.equals(contractChange.getDictChangeType())) { //作废
			AttachmentUtil.updateAndDeleteAttchment(contractChange.getAddAttachmentId(), 
					  null, contractChange.getContCode(), contractChange.getId(), "t_tz_contract_changest");
		}
	}
	
	/**
	 * 根据合同编号查询合同信息
	 * @param contCode 合同编号
	 * @param dictChangeStatus 申请状态
	 * @return ContractChange
	 */
	public ContractChange getContractChange(String contCode,String dictChangeStatus ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contCode", contCode);
		map.put("dictChangeStatus", dictChangeStatus);
		return contractChangeDao.getContractChange(map);
	}
	
	/**
	 * 分页审核列表信息
	 * @param page 分页
	 * @param contractChange 查询条件
	 * @return  Page<ContractChangeView>
	 */
	public Page<ContractChangeView> listContractChange(
			Page<ContractChangeView> page,
			ContractChangeParamView contractChange) {
		String sortString = Constant.CONTRACT_CHANAGE_ORDER_RULE;
		
		//权限控制
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		Org org = orgDao.get(userInfo.getDepartment().getId());
		if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
			contractChange.setContStoresId(userInfo.getDepartment().getId());
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		PageList<ContractChangeView> dataList = (PageList<ContractChangeView>) contractChangeDao.listContractChange(contractChange, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 分页已办审核列表信息
	 * @param page
	 * @param contractChange
	 * @return Page<ContractChangeView>
	 */
	public Page<ContractChangeView> listChangeApplyDone(
			Page<ContractChangeView> page,
			ContractChangeParamView contractChange) {
		String sortString = Constant.CONTRACT_CHANAGE_ORDER_RULE;
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		PageList<ContractChangeView> dataList = (PageList<ContractChangeView>) contractChangeDao.listChangeApplyDone(contractChange, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 导出合同变更记录
	 * @param contractChange 查询条件
	 * @return List<ContractChangeExcelView>
	 */
	public List<ContractChangeExcelView> listContractChangeExcel(ContractChangeParamView contractChange) {
		return contractChangeDao.listContractChangeExcel(contractChange);
	}

	/**
	 * 根据ID导出合同变更记录 
	 * @param ids 记录id
	 * @return List<ContractChangeExcelView>
	 */
	public List<ContractChangeExcelView> listContractChangeExcelCheckout(
			String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", ids.split(","));
	    return contractChangeDao.listContractChangeExcelCheckout(map);
	}

	/**
	 * 查询变更申请记录
	 * @param id 申请id
	 * @return ContractChange
	 */
	public ContractChange getApplyChange(String id) {
		return contractChangeDao.getApplyChange(id);
	}

	/**
	 * 更新变更申请信息
	 * @param contractChange 变更信息
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContractChange(ContractChange contractChange) {
		contractChangeDao.updateContractChange(contractChange);
	}

	/**
	 * 更新合同状态和申请状态
	 * @param contract 合同信息
	 * @param contractChange 合同变更信息
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContract(Contract contract, ContractChange contractChange) {
		contractChangeDao.updateContractChange(contractChange);
		contractDao.updateContract(contract);
	}

	/**
	 * 合同明细
	 * 2016年4月21日
	 * By 郭才林
	 * @param loanApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public LoanApply lendQueryContract(LoanApply loanApply) {
		return contractChangeDao.lendQueryContract(loanApply);
		
	}
	
}
