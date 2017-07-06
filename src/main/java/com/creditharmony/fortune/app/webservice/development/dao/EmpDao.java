package com.creditharmony.fortune.app.webservice.development.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.app.webservice.ocr.entity.Achievement;

/**
 * 员工展业dao
 * @author 王俊杰
 * @date 2016-5-10
 */
@FortuneBatisDao
public interface EmpDao {
	

	
	/**
	 * 获得财富用户的角色
	 * 2016年5月11日
	 * By 周怀富
	 * @param empCode
	 * @return
	 */
	Map<String, String> getInfoByEmpCode(String empCode);
	
	/**
	 * 获得营业部经理信息
	 */
	Map<String, String> getDepartManagerInfo(String empCode);
	
	/**
	 * 获得团队经理信息
	 */
	List<Map<String, String>> getTeamManagerInfo(Map<String,String> qry);
	
	/**
	 * 获得团队经理旗下的团队经理业绩信息
	 */
	List<Achievement> getAchievement1(Map<String, String> qry);
   
	/**
	 * 获得营业部经理旗下的理财经理业绩信息
	 */
	List<Achievement> getAchievement2(Map<String, String> qry);
	
	/**
	 * 获得理财经理业绩信息
	 */
	List<Achievement> getAchievement3(Map<String, String> qry);
	
	/**
	 * 根据登录名查询角色集合
	 * 2016年5月10日
	 * By 王俊杰
	 * @param loginName
	 * @return
	 */
	List<String> selectRoleIdList(String loginName);
	
	/**
	 * 根据登录名查询客户经理或团队经理信息
	 * 2016年5月6日
	 * By 王俊杰
	 * @param loginName
	 * @return
	 */
	Map<String, Object> getUserMessage(String loginName);
	
	/**
	 * 根据登录名查询门店经理信息
	 * 2016年5月11日
	 * By 王俊杰
	 * @param loginName
	 * @return
	 */
	Map<String, Object> getStoreUserMessage(String loginName);
	
	/**
	 * 根据用户id查询直属下属的员工号
	 * 2016年5月12日
	 * By 王俊杰
	 * @param userId
	 * @return
	 */
	List<String> geUserCodeList(String userId);
	
	/**
	 * 根据用户名查询出用户信息
	 * 2016年5月24日
	 * By zhanghu
	 * @param userName
	 * @return
	 */
	public Map<String, Object> getUserByUsername(String userName);
	
	/**
	 * 获取全国省市
	 * 2016年5月24日
	 * By zhanghu
	 * @param qry
	 * @return
	 */
	public List<Map<String, Object>> getAreas(Map<String, Object> qry);

	/**
	 * 获取省市下的营业部
	 * 2016年5月24日
	 * By zhanghu
	 * @param qry
	 * @return
	 */
	public List<Map<String, Object>> getOrgs(Map<String, Object> qry);

}
