package com.creditharmony.fortune.contract.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.ContractOwner;
import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.DistributeState;
import com.creditharmony.core.fortune.type.PigeonholeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.dao.ContractDistributeDao;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.entity.ContractDistribute;
import com.creditharmony.fortune.contract.util.ContractNUMUtil;
import com.creditharmony.fortune.contract.view.ContractDistributeExcelView;
import com.creditharmony.fortune.contract.view.ContractDistributeParamView;
import com.creditharmony.fortune.contract.view.ContractDistributeView;

/**
 * 合同派发
 * 
 * @Class Name ContractDistributeManager
 * @author 李放
 * @Create In 2015年12月16日
 */
@Service
public class ContractDistributeManager extends
		CoreManager<ContractDistributeDao, ContractDistribute> {

	@Autowired
	private ContractDistributeDao contractDistributeDao;

	@Autowired
	private ContractDao contractDao;

	/**
	 * 派发记录列表 分页
	 * 
	 * @param page
	 * @param contractDistribute
	 * @return Page<ContractDistributeView>
	 */
	public Page<ContractDistributeView> listContractDistribute(
			Page<ContractDistributeView> page,
			ContractDistributeParamView contractDistribute) {
		String sortString = Constant.CONTRACT_DISTRIBUTE_ORDER_RULE;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize(), Order.formString(sortString));
		PageList<ContractDistributeView> dataList = (PageList<ContractDistributeView>) contractDistributeDao
				.listContractDistribute(contractDistribute, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 导出合同派发记录
	 * 
	 * @param contractDistribute
	 * @return List<ContractDistributeExcelView>
	 */
	public List<ContractDistributeExcelView> listDistribute(
			ContractDistribute contractDistribute) {
		return contractDistributeDao.listDistribute(contractDistribute);
	}

	/**
	 * 根据ID导出合同派发记录
	 * 
	 * @param ids
	 * @return List<ContractDistributeExcelView>
	 */
	public List<ContractDistributeExcelView> listContractDistributeCheckout(
			String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ids.split(","));
		map.put("distStatus", DistributeState.YPF.value);
		map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
		return contractDistributeDao.listContractDistributeCheckout(map);
	}

	/**
	 * 根据ID查询合同派发记录
	 * 
	 * @param id
	 * @return ContractDistribute
	 */
	public ContractDistribute getContractDistribute(String id) {
		return contractDistributeDao.getContractDistribute(id);
	}

	/**
	 * 修改新派发信息
	 * 
	 * @param contractDistribute
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateContractDistribute(ContractDistribute contractDistribute) {
		contractDistributeDao.updateContractDistribute(contractDistribute);
	}

	/**
	 * 更新派发信息
	 * 
	 * @param contractDistribute
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateDistribute(ContractDistribute contractDistribute) {
		contractDistributeDao.updateDistribute(contractDistribute);
	}

	/**
	 * 合同派发
	 * 
	 * @param user
	 *            ：当前用户
	 * @param list
	 *            ： 派发记录
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String distributeContract(User user, String[] list) {
		String result = BooleanType.TRUE;
		for (String id : list) {

			ContractDistribute contractDistribute = contractDistributeDao
					.getContractDistribute(id);
			if (contractDistribute.getDistContractNo() == null) {
				continue;
			}

			if (!DistributeState.WPF.value.equals(contractDistribute
					.getDistStatus())) {
				result = BooleanType.FALSE;
				break;
			}

			List<String> range = ContractNUMUtil.range(
					contractDistribute.getDistStartNo(),
					contractDistribute.getDistEndNo());
			if (range == null) {
				result = BooleanType.FALSE;
				break;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cont_code", range);
			int i = contractDao.checkContractNumRange(map);
			if (i > 0) {
				result = BooleanType.FALSE;
				break;
			}
			if (Integer.valueOf(contractDistribute.getApplyNo()).compareTo(
					contractDistribute.getDistContractNo()) == -1) {
				contractDistribute.setDistStatus(DistributeState.YPF.value);
			}
			if (Integer.valueOf(contractDistribute.getApplyNo()).compareTo(
					contractDistribute.getDistContractNo()) == 0) {
				contractDistribute.setDistStatus(DistributeState.YPF.value);
			}
			if (Integer.valueOf(contractDistribute.getApplyNo()).compareTo(
					contractDistribute.getDistContractNo()) > 0) {
				contractDistribute.setDistStatus(DistributeState.BFPF.value);
			}
			contractDistribute.setAssignedId(user.getId());
			contractDistribute.setDistDate(new Date());

			for (String code : range) {
				Contract contract = new Contract();
				contract.setContCode(code);
				contract.setContStoresId(contractDistribute.getContStoresId());
				contract.setContVersion(contractDistribute.getContVersion());
				contract.setContSignStatus(SingnState.WQS.value);
				contract.setDictContSource(Constant.DICT_CONT_SOURCE);
				contract.setDictContFileStatus(PigeonholeState.WGD.value);
				contract.setDisContractId(id);
				contract.setApplyContractId(contractDistribute.getContractId());
				contract.preInsert();
				contractDao.insertContract(contract);
			}
			contractDistributeDao.updateContractDistribute(contractDistribute);
		}
		return result;
	}

	/**
	 * 根据合同申请id查询派发信息
	 * 
	 * @param contractId
	 *            派发记录id
	 * @return ContractDistribute
	 */
	public ContractDistribute getContractDistributeByContractId(
			String contractId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", contractId);
		map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
		return contractDistributeDao.getContractDistributeByContractId(map);
	}

	/**
	 * 签收合同派发
	 * 
	 * @param contractDistribute
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void signContractDistribute(ContractDistribute contractDistribute) {
		List<String> range = ContractNUMUtil.range(
				contractDistribute.getDistStartNo(),
				contractDistribute.getDistEndNo());
		contractDistribute.setSignStatus(SingnState.YQS.value);
		contractDistribute.preUpdate();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cont_code", range);
		Contract c = new Contract();
		c.setContSignStatus(SingnState.YQS.value);
		c.setDictContStatus(ContractState.KC.value);
		c.setDictContBelong(ContractOwner.MDKC.value);
		c.setContIncomeDay(new Date());
		c.preUpdate();
		map.put("c", c);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("id", contractDistribute.getContractId());
		pmap.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
		ContractDistribute db = contractDistributeDao
				.getContractDistributeByContractId(pmap);
		if (db != null) {
			if (db.getApplyNo() != null
					&& db.getDistContractNo() != null
					&& db.getApplyNo().equals(db.getDistContractNo())
					&& !SingnState.YQS.value.equals(db.getSignStatus())) {
				db.setSignStatus(SingnState.YQS.value);
				db.preUpdate();
				contractDistributeDao.updateContractDistribute(db);
			}
			contractDistributeDao.updateContractDistribute(contractDistribute);
			contractDao.signContracts(map);
		}
	}

	/**
	 * 获取单条派发信息
	 * 
	 * @param id
	 *            派发id
	 * @return ContractDistributeView
	 */
	public ContractDistributeView getContractDistributeView(String id) {
		return contractDistributeDao.getContractDistributeView(id);
	}

	/**
	 * 批量添加合同
	 * 
	 * @param list
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void addContracts(List<ContractDistributeExcelView> list) {
		for (ContractDistributeExcelView contractDistributeExcelView : list) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", contractDistributeExcelView.getContractId());
			map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
			ContractDistribute disDb = contractDistributeDao
					.getContractDistributeByContractId(map);
			if (disDb == null) {
				continue;
			}
			ContractDistribute newd = new ContractDistribute();
			newd.setId(IdGen.uuid());
			newd.setContractId(disDb.getContractId());
			newd.setContStoresId(disDb.getContStoresId());
			newd.setContVersion(disDb.getContVersion());
			newd.setDistStatus(DistributeState.YPF.value);
			newd.setSignStatus(SingnState.WQS.value);
			newd.setDistType(Constant.DISTRIBUT_SUBSET_ORDER);
			newd.setDistExpressNo(contractDistributeExcelView
					.getDistExpressNo().trim());
			newd.setDistStartNo(contractDistributeExcelView.getDistStartNo()
					.trim());
			newd.setDistEndNo(contractDistributeExcelView.getDistEndNo().trim());
			newd.setDistDate(contractDistributeExcelView.getDistDate());
			int distBox = Integer.valueOf(contractDistributeExcelView
					.getDistBox().trim());
			newd.setDistBox(distBox);
			newd.setDistContractNo(Integer.valueOf(contractDistributeExcelView
					.getDistContractNo().trim()));
			newd.preInsert();
			newd.setAssignedId(UserUtils.getUserId());
			disDb.setDistBox(disDb.getDistBox() == null ? distBox : disDb
					.getDistBox() + distBox);
			disDb.setDistContractNo(disDb.getDistContractNo() == null ? newd
					.getDistContractNo() : disDb.getDistContractNo()
					+ newd.getDistContractNo());
			if (disDb.getDistStatus().equals(DistributeState.WPF.value))
				disDb.setDistDate(newd.getDistDate());
			if (disDb.getDistContractNo() >= disDb.getApplyNo())
				disDb.setDistStatus(DistributeState.YPF.value);
			else
				disDb.setDistStatus(DistributeState.BFPF.value);
			disDb.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
			disDb.preUpdate();

			List<String> range = ContractNUMUtil.range(newd.getDistStartNo(),
					newd.getDistEndNo());
			if (range == null) {
				continue;
			}
			Map<String, Object> cmap = new HashMap<String, Object>();
			cmap.put("cont_code", range);
			int i = contractDao.checkContractNumRange(cmap);
			if (i > 0) {
				continue;
			}
			for (String code : range) {
				Contract contract = new Contract();
				contract.setContCode(code);
				contract.setContStoresId(disDb.getContStoresId());
				contract.setContVersion(disDb.getContVersion());
				contract.setContSignStatus(SingnState.WQS.value);
				contract.setDictContSource(Constant.DICT_CONT_SOURCE);
				contract.setDictContFileStatus(PigeonholeState.WGD.value);
				contract.setDisContractId(newd.getId());
				contract.setApplyContractId(disDb.getContractId());
				contract.preInsert();
				contractDao.insertContract(contract);
			}
			contractDistributeDao.insertContractDistribute(newd);
			contractDistributeDao.updateDistribute(disDb);
		}
	}

	/**
	 * 根据申请id查询派发记录
	 * 
	 * @param contractId
	 *            合同申请id
	 * @param distType
	 *            派发类型
	 * @return
	 */
	public List<ContractDistributeView> listDistributeSubSet(String contractId,
			String distType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractId", contractId);
		map.put("distType", distType);
		return contractDistributeDao.listDistributeSubSet(map);
	}

	/**
	 * 获取住派发记录信息
	 * 
	 * @param map
	 *            查询参数
	 * @return 派发信息
	 */
	public ContractDistribute getContractDistributeByContractId(
			Map<String, Object> map) {
		return contractDistributeDao.getContractDistributeByContractId(map);
	}

}
