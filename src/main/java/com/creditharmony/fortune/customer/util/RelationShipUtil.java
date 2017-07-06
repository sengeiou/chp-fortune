package com.creditharmony.fortune.customer.util;

import java.util.List;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.dao.CommonDao;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;

/**
 * @Class Name RelationShipUtil
 * @author 孙凯文
 * @Create In 2016年1月20日
 */
public class RelationShipUtil {
	private static CommonDao commonDao = SpringContextHolder
			.getBean(CommonDao.class);
	private static OrgDao orgDao = SpringContextHolder.getBean(OrgDao.class);
	private static UserRoleOrgDao userRoleOrgDao =  SpringContextHolder.getBean(UserRoleOrgDao.class);


	/**
	 * 获取用户关联机构 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @param orgType
	 * @return
	 */
	public static List<FortuneOrg> getUserOrg(String userId, String orgType,
			String roleId) {
		return commonDao.getUserOrg(userId, orgType, roleId);
	}

	/**
	 * 获取机构成员 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param roleTypes
	 * @param UserStatus 
	 * @return
	 */
	public static List<FortuneUser> getOrgMember(String orgId,
			List<String> roleTypes,String userStatus) {
		return commonDao.getOrgMember(orgId, roleTypes, userStatus);
	}

	/**
	 * 获取子机构 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param orgTypes
	 * @return
	 */
	public static List<FortuneOrg> getSubOrg(String orgId, List<String> orgTypes) {
		return commonDao.getSubOrg(orgId, orgTypes);
	}

	/**
	 * 获取上层机构 2016年1月20日
	 * 
	 * @author 孙凯文
	 * @param orgId
	 * @param orgTypes
	 * @return
	 */
	public static List<FortuneOrg> getHigherOrg(String orgId,
			List<String> orgTypes) {
		return commonDao.getHigherOrg(orgId, orgTypes);
	}

	/**
	 * 获取团队机构 2016年1月25日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @return
	 */
	public static FortuneOrg getTeamOrg(String userId) {
		List<FortuneOrg> list = getUserOrg(userId, FortuneOrgType.TEAM.key,
				null);
		if (ArrayHelper.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取门店机构 2016年1月25日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @return
	 */
	public static FortuneOrg getStoresOrg(String userId) {
		List<FortuneOrg> list = getUserOrg(userId, FortuneOrgType.STORE.key,
				null);
		if (ArrayHelper.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 取支公司 2016年1月26日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @return
	 */
	public static FortuneOrg getCityOrg(String userId) {
		List<FortuneOrg> list = getUserOrg(userId, FortuneOrgType.CITY.key,
				null);
		if (ArrayHelper.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 取分公司 2016年1月26日
	 * 
	 * @author 孙凯文
	 * @param userId
	 * @return
	 */
	public static FortuneOrg getDistrictOrg(String userId) {
		List<FortuneOrg> list = getUserOrg(userId, FortuneOrgType.DISTRICT.key,
				null);
		if (ArrayHelper.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取当前登录客户
	 * 2016年5月27日
	 * @return 
	 */
	public static User getCurrentUser(){
		return (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
	}
	
	public static String getCurrentUserRoleId(){
		User user = getCurrentUser();
		UserRoleOrg uro = new UserRoleOrg();
		uro.setUserId(user.getId());
		uro.setOrgId(user.getDepartment().getId());
		List<UserRoleOrg> uroList = userRoleOrgDao.selUserRoleOrg(uro);
		if(ArrayHelper.isNotEmpty(uroList) && uroList.size() ==1){
			return uroList.get(0).getRoleId();
		}
		return null;
	}
	
	public static Org getCurrentOrg(){
		User user = getCurrentUser();
		return orgDao.get(user.getDepartment().getId());
	}

}
