package com.creditharmony.fortune.contract.dao;


import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.contract.entity.ContractDistribute;
import com.creditharmony.fortune.contract.view.ContractDistributeExcelView;
import com.creditharmony.fortune.contract.view.ContractDistributeParamView;
import com.creditharmony.fortune.contract.view.ContractDistributeView;

/** 合同派发
 * @Class Name ContractDistributeDao
 * @Author lifang
 * @Create 2015年11月27日
 */
@FortuneBatisDao
public interface ContractDistributeDao extends CrudDao<ContractDistribute>{
	
	/**
	 * 初始化数据
	 * 2015年12月5日
	 * By 李放
	 * @param contractDistribute
	 * @return List<ContractDistribute>
	 */
	public List<ContractDistribute> findAllList(ContractDistribute contractDistribute);
	
	/**
	 * 根据对象条件检索列表数据
	 * 2015年12月5日
	 * By 李放
	 * @return List<ContractDistribute>
	 */
	public List<ContractDistribute> findAllContractDistribute();
	
	/**
	 * 查询数据条数
	 * 2015年12月5日
	 * By 李放
	 * @param contractDistribute
	 * @return List<ContractDistribute>
	 */
	public List<ContractDistribute> findState(ContractDistribute contractDistribute);

	/**
	 * 添加合同派发信息
	 * @param dis ：派发明细
	 * @author 王鹏飞
	 */
	public void insertContractDistribute(ContractDistribute dis);

	/**
	 * 添加批量派发记录 
	 * @param map
	 */
	public void insertContractDistributeList(Map<String, Object> map);

	/**
	 * 派发记录列表 分页
	 * @param contractDistribute
	 * @param pageBounds
	 * @return PageList<ContractDistributeView>
	 */
	public PageList<ContractDistributeView> listContractDistribute( ContractDistributeParamView contractDistribute, PageBounds pageBounds);

	/**
	 * 导出合同派发记录 
	 * @param contractDistribute
	 * @return List<ContractDistributeExcelView>
	 */
	public List<ContractDistributeExcelView> listDistribute(
			ContractDistribute contractDistribute);

	/**
	 * 根据ID导出合同派发记录 
	 * @param map
	 * @return List<ContractDistributeExcelView>
	 */
	public List<ContractDistributeExcelView> listContractDistributeCheckout(
			Map<String, Object> map);

	/**
	 * 根据ID查询合同派发记录
	 * @param id
	 * @return ContractDistribute
	 */
	public ContractDistribute getContractDistribute(String id);

	/**
	 * 修改新派发信息
	 * @param contractDistribute
	 */
	public void updateContractDistribute(ContractDistribute contractDistribute);

	/**
	 * 更新派发信息
	 * @param contractDistribute
	 */
	public void updateDistribute(ContractDistribute contractDistribute);

	/**
	 * 根据合同申请id查询派发信息
	 * @param map
	 * @return
	 */
	public ContractDistribute getContractDistributeByContractId(Map<String, Object> map);

	/**
	 * 获取单条派发信息
	 * @param id 派发id
	 * @return ContractDistributeView
	 */
	public ContractDistributeView getContractDistributeView(String id);

	/**
	 * 根据申请id查询派发记录
	 * @param map
	 * @return
	 */
	public List<ContractDistributeView> listDistributeSubSet(
			Map<String, Object> map);
}
