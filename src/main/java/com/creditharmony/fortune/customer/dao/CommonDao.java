package com.creditharmony.fortune.customer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;

/**
 * 公用数据操作类
 * 
 * @Class Name CommonDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface CommonDao {

	/**
	 * 2015年12月2日 By 孙凯文
	 * 
	 * @return
	 */
	public String getCurrentCustCode();

	/**
	 * 2015年12月2日 By 孙凯文
	 * 
	 * @return
	 */
	public String getNextCustCode();

	/**
	 * 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @param orgType
	 * @return
	 */
	public List<FortuneOrg> getUserOrg(@Param("userId") String userId,
			@Param("orgType") String orgType, @Param("roleId") String roleId);

	/**
	 * 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param roleTypes
	 * @param userStatus
	 * @return
	 */
	public List<FortuneUser> getOrgMember(@Param("orgId") String orgId,
			@Param("roleTypes") List<String> roleTypes,@Param("userStatus") String userStatus);

	/**
	 * 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param orgTypes
	 * @return
	 */
	public List<FortuneOrg> getSubOrg(@Param("orgId") String orgId,
			@Param("orgTypes") List<String> orgTypes);

	/**
	 * 获取上层机构 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param orgTypes
	 * @return
	 */
	public List<FortuneOrg> getHigherOrg(@Param("orgId") String orgId,
			@Param("orgTypes") List<String> orgTypes);

	/**
	 * 获取用户关联的角色列表 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @return
	 */
	public List<String> getRoles(String userId);

}
