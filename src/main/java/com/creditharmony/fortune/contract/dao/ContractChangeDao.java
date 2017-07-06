package com.creditharmony.fortune.contract.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.contract.entity.ContractChange;
import com.creditharmony.fortune.contract.view.ContractChangeExcelView;
import com.creditharmony.fortune.contract.view.ContractChangeParamView;
import com.creditharmony.fortune.contract.view.ContractChangeView;
import com.creditharmony.fortune.contract.view.ContractView;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 合同变更处理DAO
 * @Class Name ContractChangeDao
 * @author 李放
 * @Create In 2015年12月4日
 */
@FortuneBatisDao
public interface ContractChangeDao extends CrudDao<ContractChange>{
	
	/**
	 * 根据ID 获取合同变更信息
	 * 2015年12月21日
	 * update By 王鹏飞
	 * 
	 * 变更已办带参数进入详细页
	 * 2015年12月10日
	 * By 李放
	 * @param id
	 * @return ContractChangeView
	 */
	public ContractChangeView getContractChangeById(String id);
	
	/**
	 * 根据合同编号获取合同信息
	 * 2015年12月21日
	 * update By 王鹏飞
	 * 
	 * 带参数进入变更申请详细页面
	 * 2015年12月11日
	 * By 李放
	 * @param contCode
	 * @return ContractView
	 */
	public ContractView getContractInformation(String contCode);

	/**
	 * 添加变更申请
	 * @param contractChange
	 */
	public void insertContractChange(ContractChange contractChange);

	/**
	 * 根据合同编号查询合同信息
	 * @param map
	 * @return ContractChange
	 */
	public ContractChange getContractChange(Map<String, Object> map);

	/**
	 * 分页审核列表信息
	 * @param contractChange 查询条件
	 * @param pageBounds 
	 * @return PageList<ContractChangeView>
	 */
	public PageList<ContractChangeView> listContractChange(
			ContractChangeParamView contractChange, PageBounds pageBounds);

	/**
	 * 变更已办列表
	 * @param contractChange 查询条件
	 * @param pageBounds 
	 * @return List<ContractChangeView>
	 */
	public List<ContractChangeView> listChangeApplyDone(
			ContractChangeParamView contractChange, PageBounds pageBounds);

	/**
	 * 导出合同变更记录
	 * @param contractChange 查询条件
	 * @return List<ContractChangeExcelView>
	 */
	public List<ContractChangeExcelView> listContractChangeExcel(
			ContractChangeParamView contractChange);

	/**
	 * 根据ID导出合同变更记录 
	 * @param map
	 * @return List<ContractChangeExcelView>
	 */
	public List<ContractChangeExcelView> listContractChangeExcelCheckout(
			Map<String, Object> map);

	/**
	 * 查询变更申请记录
	 * @param id 主键
	 * @return ContractChange
	 */
	public ContractChange getApplyChange(String id);

	/**
	 * 更新变更申请信息
	 * @param contractChange 变更信息
	 */
	public void updateContractChange(ContractChange contractChange);

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
	public LoanApply lendQueryContract(LoanApply loanApply);
}
