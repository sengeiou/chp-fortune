package com.creditharmony.fortune.contract.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.view.ContractExcelView;
import com.creditharmony.fortune.contract.view.ContractParamView;
import com.creditharmony.fortune.contract.view.ContractView;

/**
 * 合同信息
 * 
 * @Class Name ContractDao
 * @author 王鹏飞
 * @Create In 2015年12月30日
 */

@FortuneBatisDao
public interface ContractDao extends CrudDao<Contract> {

	/**
	 * 检测区间合同编号数量
	 * 
	 * @param map
	 *            条件参数
	 * @return int 合同数量
	 */
	public int checkContractNumRange(Map<String, Object> map);

	/**
	 * 添加单笔合同信息
	 * 
	 * @param contract
	 *            合同信息
	 * @return 无返回
	 */
	public void insertContract(Contract contract);

	/**
	 * 批量签收合同信息
	 * 
	 * @param map
	 *            条件参数
	 * @return 无返回
	 */
	public void signContracts(Map<String, Object> map);

	/**
	 * 合同列表页面
	 * 
	 * @param contractInformation
	 *            合同信息
	 * @param pageBounds
	 *            分页信息
	 * @return 分页集合
	 */
	public PageList<ContractView> listContract(
			Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 合同查询及状态变更导出 导出数据
	 * 
	 * @param contractInformation
	 *            合同信息
	 * @return List<ContractExcelView>
	 */
	public List<ContractExcelView> listContractChange(
			ContractParamView contractInformation);

	/**
	 * 根据选择导出 导出数据
	 * 
	 * @param map
	 *            条件参数
	 * @return List<ContractExcelView>
	 */
	public List<ContractExcelView> listContractChangeCheckout(
			Map<String, Object> map);

	/**
	 * 获取门店理财经理
	 * 
	 * @param map
	 *            条件参数
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> listFinacialManager(Map<String, Object> map);

	/**
	 * 修改合同归属
	 * 
	 * @param map
	 *            参数条件
	 * @return 无返回           
	 */
	public void updateContractBelong(Map<String, Object> map);

	/**
	 * 获取合同信息
	 * 
	 * @param contCode
	 *            合同编号
	 * @return 无返回
	 */
	public Contract getContract(String contCode);

	/**
	 * 合同调拨
	 * 
	 * @param contract
	 *            合同信息
	 * @return 无返回
	 */
	public void transferToFinacialManager(Contract contract);

	/**
	 * 更新合同信息
	 * 
	 * @param contract
	 *            合同信息
	 * @return 无返回           
	 */
	public void updateContract(Contract contract);

	/**
	 * 修改合同归档状态
	 * 
	 * @param contract
	 *            合同信息
	 * @return 无返回
	 */
	public void updateContractFileStatus(Contract contract);

	/**
	 * 根据参数获取合同信息 2016年1月12日
	 * 
	 * @author 孙凯文
	 * @param param
	 *            参数条件
	 * @return
	 */
	public Contract queryContract(Map<String, Object> param);

	/**
	 * 查询合同是否已经存在
	 * 
	 * @param map
	 *            合同编号集合
	 * @return 合同数量
	 */
	public int isExistContract(Map<String, Object> map);

	/**
	 * 修改合同文件保存参数
	 * 
	 * @param contract
	 *            合同信息
	 * @return 无返回
	 */
	public void updateContFilepath(Contract contract);

	/**
	 * 修改合同使用时间
	 * 2016年4月22日
	 * By 郭才林
	 * @param contract
	 */
	public void updateContractUseDay(Contract contract);

	/**
	 * 更新合同状态
	 * 2016年4月28日
	 * By 郭才林
	 * @param contract
	 */
	public void updateContractState(Contract contract);
	
	public void updateContractCode(@Param("oldCode")String oldCode, @Param("newCode")String newCode);

}