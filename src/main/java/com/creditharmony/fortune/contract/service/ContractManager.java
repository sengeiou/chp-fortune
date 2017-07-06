package com.creditharmony.fortune.contract.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.ContractOwner;
import com.creditharmony.core.fortune.type.ContractState;
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
import com.creditharmony.fortune.contract.dao.ContractDao;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.view.ContractExcelView;
import com.creditharmony.fortune.contract.view.ContractParamView;
import com.creditharmony.fortune.contract.view.ContractView;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 合同信息详情
 * @Class Name ContractManager
 * @author 王鹏飞
 * @Create In 2015年12月30日
 */
@Service
public class ContractManager  extends CoreManager<ContractDao,Contract>{

	@Autowired
	private ContractDao contractDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private   OrgDao  orgDao;
	
	/**
	 * 检测区间合同编号数量
	 * @param range 区间集合
	 * @return int
	 */
	public int checkContractNumRange(List<String> range) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_code", range);
		return contractDao.checkContractNumRange(map);
	}
	
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContractCode(String oldCode, String newCode){
		contractDao.updateContractCode(oldCode, newCode);
	}

	/**
	 * 合同列表页面
	 * @param page 分页
	 * @param contractInformation 查询条件
	 * @return Page<ContractInformation>
	 */
	public Page<ContractView> listContract(
			Page<ContractView> page,
			ContractParamView contractInformation) {
		String sortString = Constant.CONTRACT_ORDER_RULE;
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize(), Order.formString(sortString ));
		pageBounds.setCountBy("cont_code");
		
		//权限控制
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		Org org = orgDao.get(userInfo.getDepartment().getId());
		String nameIds = contractInformation.getNameId();
		if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
			if (StringUtils.isEmpty(nameIds)) {
				nameIds = userInfo.getDepartment().getId();
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != nameIds && !"".equals(nameIds)){
			map.put("nameIds",nameIds.split(","));
		}
		map.put("contract", contractInformation);
		
		
		
		PageList<ContractView> dataList = (PageList<ContractView>) contractDao.listContract(map, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 合同查询及状态变更导出 导出数据
	 * @param contractInformation 合同信息
	 * @return List<ContractExcelView>
	 */
	public List<ContractExcelView> listContractChange(
			ContractParamView contractInformation) {
		return contractDao.listContractChange(contractInformation);
	}

	/**
	 *  根据选择导出 导出数据
	 * @param ids 合同编号
	 * @return List<ContractExcelView>
	 */
	public List<ContractExcelView> listContractChangeCheckout(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_code", ids.split(","));
		return contractDao.listContractChangeCheckout(map);
	}

	/**
	 * 获取门店理财经理
	 * @param departmentId 门店id
	 * @param roleId 用户角色
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> listFinacialManager(String departmentId, String roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("departmentId",departmentId);
		map.put("roleId",roleId);
		return contractDao.listFinacialManager(map);
	}

	/**
	 * 派发合同归属
	 * @param ids 合同编辑
	 * @param userId 用户id
	 * @return 无返回
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void distrubuteToFinacialManager(String ids, String userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_code", ids.split(","));
		Contract c = new Contract();
		c.preUpdate();
		c.setContBelongEmpid(userId);
        c.setDictContBelong(ContractOwner.LCJL.value);
        c.setDictContStatus(ContractState.YFP.value);
        c.setContBestorageDay(new Date());
        map.put("c", c);
        contractDao.updateContractBelong(map);
	}

	/**
	 * 合同调回
	 * @param ids
	 * @return 无返回
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void recallContractFormFinacialManager(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cont_code", ids.split(","));
		Contract c = new Contract();
		c.preUpdate();
		c.setContBelongEmpid(null);
        c.setDictContBelong(ContractOwner.MDKC.value);
        c.setDictContStatus(ContractState.KC.value);
        c.setContBestorageDay(null);
        map.put("c", c);
        contractDao.updateContractBelong(map);
	}

	/**
	 * 获取合同信息
	 * @param contCode 合同编号
	 * @return 无返回
	 */
	public Contract getContract(String contCode) {
		return contractDao.getContract(contCode);
	}

	/**
	 * 合同调拨
	 * @param contract 原合同信息
	 * @param contractInformation 信息合同挑拨信息
	 * @return 无返回
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void transferToFinacialManager(Contract contract,
			ContractParamView contractInformation) {
		 contract.setContBelongEmpid(contractInformation.getContBelongEmpid());
		 contract.setContTranferDay(new Date());
		 contract.setDictContBelong(ContractOwner.LCJL.value);
		 contract.setDictContStatus(ContractState.YFP.value);
		 contract.preUpdate();
		 contractDao.transferToFinacialManager(contract);
	}

	
	/**
	 * 修改合同归档状态
	 * @param contract 合同信息
	 * @return 无返回
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContractFileStatus(Contract contract) {
		contractDao.updateContractFileStatus(contract);
	}
	
	/**
	 * 根据参数获取合同信息
	 * 2016年1月12日
	 * @author 孙凯文
	 * @param param
	 * @return 无返回
	 */
	public Contract queryContract(Map<String, Object> param){
		return contractDao.queryContract(param);
	}

	/**
	 * 查询合同是否已经存在
	 * @param range 合同编号集合
	 * @return 无返回
	 */
	public boolean isExistContract(List<String> range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cont_code", range);
		int i = contractDao.isExistContract(map);
		return i > 0 ? true : false;
	}

	/**
	 * 修改合同文件保存参数
	 * @param contract 合同信息
	 * @return 无返回
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContFilepath(Contract contract) {
		contractDao.updateContFilepath(contract);
	}
	
	/**
	 * 修改合同使用时间（划扣成功，内转成功）
	 * 2016年4月22日
	 * By 
	 * @param contract
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContractUseDay(Contract contract) {
		LoanApply apply=new LoanApply();
		apply.setApplyCode(contract.getLendCode());
		apply= loanApplyDao.get(apply);
		if(apply!=null){
			contract.setContCode(apply.getContractNumber());
			contract.setContUseDay(new Date());
			// 更新合同使用日期
			contractDao.updateContractUseDay(contract);
		}
		
	}
	
	/**
	 * 更新合同状态
	 * 2016年4月28日
	 * By 郭才林
	 * @param contract
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void updateContractState(Contract contract) {
		
		contract.preUpdate();
		if(StringUtils.isEmpty(contract.getContCode())){
			LoanApply apply=new LoanApply();
			apply.setApplyCode(contract.getLendCode());
			apply= loanApplyDao.get(apply);
			if(apply!=null){
				
				contract.setContCode(apply.getContractNumber());
				contractDao.updateContractState(contract);
			}
			
		}else{
			contractDao.updateContractState(contract);
		}
		
	}

	/**
	 * 插入合同
	 * 2016年4月28日
	 * By 郭才林
	 * @param contract
	 */
	public void insertContract(Contract contract) {
		contractDao.insertContract(contract);
		
	}
}

