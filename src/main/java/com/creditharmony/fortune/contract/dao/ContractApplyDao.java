package com.creditharmony.fortune.contract.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.contract.entity.ContractApply;
import com.creditharmony.fortune.contract.view.ContractApplyDetailView;
import com.creditharmony.fortune.contract.view.ContractApplyExcelView;
import com.creditharmony.fortune.contract.view.ContractApplyParamView;
import com.creditharmony.fortune.contract.web.ContractApplyAlreadyList;
import com.creditharmony.fortune.contract.web.ContractApplyApprovalList;


/**
 * 合同申请
 * @Class Name ContractApplyDao
 * @author 李放
 * @Create In 2015年12月3日
 */

@FortuneBatisDao
public interface ContractApplyDao extends CrudDao<ContractApply> {

	/**
	 * 合同申请
	 * 2015年12月3日
	 * By 李放
	 * @param contractApply
	 * @return long
	 */
	public long findAllCount(ContractApply contractApply);
	
	/**
	 * 审核添加
	 * 2015年12月12日
	 * By 李放
	 * @param approval
	 */
	public void insertContractApply(ContractApply approval);
	
	/**
	 * 审核
	 * 2015年12月4日
	 * By 李放
	 * @param contractApplyApprovalList
	 * @return long
	 */
	public long findAllCount(ContractApplyApprovalList contractApplyApprovalList);
	
	/**
	 * 已办列表
	 * 2015年12月5日
	 * By 李放
	 * @param contractApplyAlreadyList
	 * @return long
	 */
	public long findAllCount(ContractApplyAlreadyList contractApplyAlreadyList);
	
	/**
	 * 查询参数
	 * 2015年12月4日
	 * By 李放
	 * @param contractApply
	 * @return List<ContractApply>
	 */
	public List<ContractApply> findAllList(ContractApply contractApply);
	
	/**
	 * 查看详细页
	 * 2015年12月4日
	 * By 李放
	 * @param contractApply
	 * @return List<ContractApply>
	 */
	public List<ContractApply> findSee(ContractApply contractApply);
	
	/**
	 * 审核添加
	 * 2015年12月8日
	 * By 李放
	 * @param apply
	 */
	public void insetContractApply(ContractApply apply);
	
	/**
	 * 查询门店合同上月的使用数量
	 * @param map 参数
	 * @return String
	 */
	public String getCountOfUsedContract(Map<String,Object> map);

	/**
	 * 查询门店合同库存数量
	 * @param map 门店id
	 * @author 王鹏飞
	 * @return String
	 */
	public String getCountOfStoredContract(Map<String, Object> map);

	/**
	 * 添加合同申请
	 * @param contractApply
	 * @author 王鹏飞
	 */
	public void addContractApply(ContractApply contractApply);

	/**
	 * 查询门店某月份的合同申请次数
	 * @param map
	 * @author 王鹏飞
	 * @return String
	 */
	public String getCountOfMonthlyApply(Map<String, Object> map);

	/**
	 * 合同申请审核列表
	 * @param contractApplyView
	 * @param pageBounds
	 * @author 王鹏飞
	 * @return PageList<ContractApplyDetailView>
	 */
	public PageList<ContractApplyDetailView> listContractApprovalPage(ContractApplyParamView contractApplyView, PageBounds pageBounds);

	/**
	 *  根据ID 查询合同申请信息
	 * @param contractId ：合同申请ID
	 * @author 王鹏飞
	 * @return ContractApply
	 */
	public ContractApply getContractApply(String contractId);

	/**
	 * 更新合同申请记录
	 * @author 王鹏飞
	 * @param ca
	 */
	public void updateContractApply(ContractApply ca);

	/**
	 * 批量更新审核状态
	 * @param map
	 * @author 王鹏飞
	 */
	public void updateContractApplyStatus(Map<String, Object> map);

	/**
	 * 批量更新审核拒绝状态合同驳回理由
	 * @param map
	 * @author 王鹏飞
	 */
	public void updateContractApplyStatusAndRefuseDemo(Map<String, Object> map);

	/**
	 * 合同申请记录列表
	 * @param contractApplyView 查询条件
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApproval(ContractApplyParamView contractApplyView);

	/**
	 * 根据ID导出合同申请记录 
	 * @param map
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalCheckout(Map<String, Object> map);

	/**
	 * 已办理合同申请列表
	 * @param contractApplyView 查询条件
	 * @param pageBounds
	 * @author 王鹏飞
	 * @return PageList<ContractApplyDetailView>
	 */
	public PageList<ContractApplyDetailView> listContractApprovalDonePage(ContractApplyParamView contractApplyView, PageBounds pageBounds);
	
	/**
	 * 合同申请已审核记录列表
	 * @param contractApplyView 查询条件
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalDone(ContractApplyParamView contractApplyView);

	/**
	 * 根据ID导出合同申请审核记录 
	 * @param map 查询条件
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalDoneCheckout(Map<String, Object> map);

	/**
	 * 根据ids查询已待审核数量
	 * @param map 查询条件
	 * @return 审核数量
	 */
	public int getYSHContractApply(Map<String, Object> map);
}