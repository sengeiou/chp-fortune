package com.creditharmony.fortune.contract.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.AppalyState;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.dao.ContractApplyDao;
import com.creditharmony.fortune.contract.dao.ContractDistributeDao;
import com.creditharmony.fortune.contract.entity.ContractApply;
import com.creditharmony.fortune.contract.entity.ContractDistribute;
import com.creditharmony.fortune.contract.view.ContractApplyDetailView;
import com.creditharmony.fortune.contract.view.ContractApplyExcelView;
import com.creditharmony.fortune.contract.view.ContractApplyParamView;

/**
 * 合同申请-审核
 * Update By 王鹏飞
 * @Class Name ContractApplyManager
 * @author 李放
 * @Create In 2015年12月4日
 */
@Service
public class ContractApplyManager extends CoreManager<ContractApplyDao,ContractApply>{
	
	@Autowired
	private ContractApplyDao contractApplyDao;
	
	@Autowired
	private ContractDistributeDao contractDistributeDao;
	
	/**
	 * 合同申请审核列表
	 * 2015年12月22日
	 * By 王鹏飞
	 * @param page
	 * @param contractApplyView
	 * @return Page<ContractApplyDetailView>
	 */
	public Page<ContractApplyDetailView> listContractApprovalPage(Page<ContractApplyDetailView> page, ContractApplyParamView contractApplyView) {
		String sortString = Constant.CONTRACT_APPLY_ORDER_RULE;
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("contract_id");
		PageList<ContractApplyDetailView> dataList = (PageList<ContractApplyDetailView>) contractApplyDao.listContractApprovalPage(contractApplyView, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 查询门店合同上月的使用数量
	 * @param storeId 门店id
	 * @param status  合同状态
	 * @param start  开始日期
	 * @param end 结束日期
	 * @param contVersion 合同版本
	 * @author 王鹏飞
	 * @return String
	 */
	public String getCountOfUsedContract(String storeId,String status,Date start, Date end,String contVersion) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_stores_id", storeId);
		map.put("dict_cont_status", status);
		map.put("cont_version", contVersion);
		map.put("start", start);
		map.put("end", end);
		return  contractApplyDao.getCountOfUsedContract(map);
	}

	/**
	 * 查询门店合同库存数量
	 * @param storeId 门店id
	 * @param status 合同状态
	 * @param contVersion 合同版本
	 * @author 王鹏飞
	 * @return String
	 */
	public String getCountOfStoredContract(String storeId,String status,String contVersion) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_stores_id", storeId);
		map.put("dict_cont_status", status);
		map.put("cont_version", contVersion);
		return contractApplyDao.getCountOfStoredContract(map);
	}

	/**
	 * 添加合同申请
	 * @author 王鹏飞
	 * @param contractApply
	 * @return 无返回结果
	 */
	@Transactional(value="fortuneTransactionManager",readOnly=false)
	public void addContractApply(ContractApply contractApply) {
		contractApply.preInsert();
		contractApplyDao.addContractApply(contractApply);
	}

	/**
	 * 查询门店某月份的合同申请次数
	 * @param contStoresId  门店id
	 * @param contVersion 合同版本
	 * @param end 当前月份的第一天
	 * @author 王鹏飞
	 * @return String
	 */
	public String getCountOfMonthlyApply(String contStoresId, String contVersion, Date end) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_stores_id", contStoresId);
		map.put("cont_version", contVersion);
		map.put("end", end);
		return contractApplyDao.getCountOfMonthlyApply(map);
	}

	/**
	 * 根据ID 查询合同申请信息
	 * @param contractId  合同申请主键ID
	 * @author 王鹏飞
	 * @return ContractApply
	 */
	public ContractApply getContractApply(String contractId) {
		return contractApplyDao.getContractApply(contractId);
	}

	/**
	 * 更新合同申请记录
	 * @param ca 合同申请记录
	 * @author 王鹏飞
	 */
	@Transactional(value="fortuneTransactionManager",readOnly=false)
	public void updateContractApply(ContractApply ca) {
		ca.preUpdate();
		contractApplyDao.updateContractApply(ca);
	}

	/**
	 * 批量更新审核状态
	 * @param ids  合同申请ids
	 * @param applyStatus  申请状态
	 * @param applyCheckResult  审核结果
	 * @param distStatus  派发状态
	 * @param signStatus  签收状态
	 * @author 王鹏飞
	 * @return 无返回结果
	 */
	@Transactional(value="fortuneTransactionManager",readOnly=false)
	public void updateContractApplyStatus(String ids, String applyStatus, String applyCheckResult, String distStatus, String signStatus) {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractApply ca = new ContractApply();
		ca.setApplyStatus(applyStatus);
		ca.setApplyCheckResult(applyCheckResult);
		ca.setApplyCheckDate(new Date());
		ca.preUpdate();
		map.put("ca", ca);
		map.put("contract_id", ids.split(","));
		ContractDistribute d = new ContractDistribute();
		d.setDistStatus(distStatus);
		d.setSignStatus(signStatus);
		d.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
		d.preInsert();
		map.put("dist", d);
		contractApplyDao.updateContractApplyStatus(map);
		contractDistributeDao.insertContractDistributeList(map);
	}

	/**
	 * 批量更新审核拒绝状态合同驳回理由
	 * @param ids  合同申请ids
	 * @param applyStatus  申请状态
	 * @param applyCheckResult 审核结果
	 * @param dictApplyRefuseDemo  批量驳回理由
	 * @author 王鹏飞
	 * @return 无返回结果
	 */
	@Transactional(value="fortuneTransactionManager",readOnly=false)
	public void updateContractApplyStatus(String ids, String applyStatus, String applyCheckResult, String dictApplyRefuseDemo) {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractApply ca = new ContractApply();
		ca.setApplyStatus(applyStatus);
		ca.setApplyCheckDate(new Date());
		ca.setApplyCheckResult(applyCheckResult);
		ca.setDictApplyRefuseDemo(dictApplyRefuseDemo);
		ca.preUpdate();
		map.put("ca", ca);
		map.put("contract_id", ids.split(","));
		contractApplyDao.updateContractApplyStatusAndRefuseDemo(map);
	}

	/**
	 * 合同申请记录列表
	 * @param contractApplyView 查询条件
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApproval(
			ContractApplyParamView contractApplyView) {
		return contractApplyDao.listContractApproval(contractApplyView);
	}

	/**
	 * 根据ID导出合同申请记录 
	 * @param ids 记录id集合
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalCheckout(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contract_id", ids.split(","));
		map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
	    return contractApplyDao.listContractApprovalCheckout(map);
	}

	/**
	 * 审核通过并且添加派发记录
	 * @param dis 派发记录
	 * @param ca 申请记录
	 * @author 王鹏飞
	 * @return 无返回结果
	 */
	@Transactional(value="fortuneTransactionManager",readOnly=false)
	public void insertContractDistribute(ContractDistribute dis,
			ContractApply ca) {
		ca.preUpdate();
		contractApplyDao.updateContractApply(ca);
		dis.preUpdate();
		contractDistributeDao.insertContractDistribute(dis);
	}

	/**
	 * 已办理合同申请列表
	 * @param page
	 * @param contractApplyView
	 * @author 王鹏飞
	 * @return  Page<ContractApplyDetailView>
	 */
	public Page<ContractApplyDetailView> listContractApprovalDonePage(
			Page<ContractApplyDetailView> page,
			ContractApplyParamView contractApplyView) {
		String sortString = Constant.CONTRACT_APPLY_ORDER_RULE;
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("contract_id");
		PageList<ContractApplyDetailView> dataList = (PageList<ContractApplyDetailView>) contractApplyDao.listContractApprovalDonePage(contractApplyView, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 合同申请已审核记录列表
	 * @param contractApplyView
	 * @author 王鹏飞
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalDone(ContractApplyParamView contractApplyView) {
		return contractApplyDao.listContractApprovalDone(contractApplyView);
	}

	/**
	 * 根据ID导出合同申请审核记录 
	 * @param ids
	 * @return List<ContractApplyExcelView>
	 */
	public List<ContractApplyExcelView> listContractApprovalDoneCheckout(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contract_id", ids.split(","));
		map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
	    return contractApplyDao.listContractApprovalDoneCheckout(map);
	}

	/**
	 * 根据ids查询已待审核数量
	 * @param ids 需要申请申请ids集合
	 * @return 审核数量
	 */
	public int getYSHContractApply(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("contract_id", ids.split(","));
		map.put("applyStatus", AppalyState.DSH.value);
		return contractApplyDao.getYSHContractApply(map);
	}
	
}

