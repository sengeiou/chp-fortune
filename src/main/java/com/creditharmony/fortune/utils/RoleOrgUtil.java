package com.creditharmony.fortune.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.fortune.type.OrgTypeRange;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.dao.RoleOrgDao;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;

/**
 * 角色，机构工具类
 * 
 * @Class Name RoleOrgUtil
 * @author 朱杰
 * @Create In 2016年1月6日
 */
public class RoleOrgUtil {
	
	private static RoleOrgDao roleOrgDao = SpringContextHolder.getBean(RoleOrgDao.class);
	
	/**
	 * 组织机构级别大小判断
	 * 
	 * 2016年1月7日
	 * By 朱杰
	 * @param val 比较源
	 * @param compareTo 比较目标
	 * @return 1：比较源包含比较目标，  0：相等， -1：   比较目标包含比较源
	 */
	public static int compareOrgType(String val,String compareTo){
		if(val.equals(compareTo)){
			//相等
			return 0;
		}
		if(Arrays.asList(OrgTypeRange.parseByCode(val).getRange()).contains(compareTo)){
			return 1;
		}
		return -1;
	}
	
	/**
	 * 查询所有营业部信息
	 * 2016年3月23日
	 * By 周俊
	 * @param orgTye
	 * @return
	 */
	public static List<UserRoleOrgEx> findAllRangeOrgType(String orgTye){
		Map<String, Object> param = new HashMap<String,Object>();
		// 获取财富中心下辖的组织机构
		List<String> typeList = ArrayHelper.toList(OrgTypeRange.parseByCode(OrgTypeRange.BUSINESS_DEPT.getCode()).getRange());
		// 获取营业部下辖的组织机构
		List<String> minTypeList = ArrayHelper.toList(OrgTypeRange.parseByCode(orgTye).getRange());
		typeList.removeAll(minTypeList);
		typeList.add(OrgTypeRange.BUSINESS_DEPT.getCode());
		param.put("all",true);
		param.put("typeList",typeList);
		return roleOrgDao.findRoleOrgRange(param);
	}
	
	
	
	/**
	 * 查找自身关联的指定机构类型的组织机构
	 * 
	 * 2016年1月7日
	 * By 朱杰
	 * @param orgType 查询的机构类型
	 * @param roleIds 查询的用户类型 。 null的情况，查询组织机构
	 * @return
	 */
	public static List<UserRoleOrgEx> findOrgType(String orgType,List<String> roleIds){
		if(StringUtils.isBlank(orgType)){
			return new ArrayList<UserRoleOrgEx>();
		}
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		if(user == null){
			return new ArrayList<UserRoleOrgEx>();
		}
		Org userOrg = user.getDepartment();
		Map<String, Object> param = new HashMap<String,Object>();
		if(RoleOrgUtil.compareOrgType(userOrg.getType(), orgType) > 0){
			//当前登录机构比查询的机构范围大
			param.put("finddown",true);
		}else{
			//当前登录机构范围小于等于查询的机构
			param.put("findup",true);
		}
		//用户机构id
		param.put("org_id",userOrg.getId());
		//查询的机构类型
		param.put("org_types",Arrays.asList(orgType));
		if(roleIds != null && !roleIds.isEmpty()){
			//查询的角色类型
			param.put("role_ids",roleIds);
		}
		return roleOrgDao.findRoleOrg(param);
	}
	
	/**
	 * 查找指定机构类型之间的的组织机构
	 * 2016年1月8日
	 * By 朱杰
	 * @param orgTypeMinParam 查询的机构类型
	 * @param orgTypeMaxParam 查询的用户类型 。 null的情况，查询组织机构
	 * @param roleIds
	 * @return
	 */
	public static List<UserRoleOrgEx> findRangeOrgType(String orgTypeMinParam,String orgTypeMaxParam,List<String> roleIds){
		if(StringUtils.isBlank(orgTypeMinParam) || StringUtils.isBlank(orgTypeMinParam)){
			//任何一个是空的情况返回空list
			return new ArrayList<UserRoleOrgEx>();
		}
		String orgTypeMin = orgTypeMinParam;
		String orgTypeMax = orgTypeMaxParam;
		
		if(RoleOrgUtil.compareOrgType(orgTypeMinParam, orgTypeMaxParam) > 0 ){
			//调整min，max顺序
			orgTypeMin = orgTypeMaxParam;
			orgTypeMax = orgTypeMinParam;
		}
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		if(user==null){
			return new ArrayList<UserRoleOrgEx>();
		}
		Org userOrg = user.getDepartment();
		List<String> userRangeForMin = ArrayHelper.toList(OrgTypeRange.parseByCode(userOrg.getType()).getRange());
		List<String> userRangeForMax = ArrayHelper.toList(OrgTypeRange.parseByCode(userOrg.getType()).getRange());
		List<String> minRange = ArrayHelper.toList(OrgTypeRange.parseByCode(orgTypeMin).getRange());
		List<String> maxRange = ArrayHelper.toList(OrgTypeRange.parseByCode(orgTypeMax).getRange());
		
		Map<String, Object> param = new HashMap<String,Object>();
		if(RoleOrgUtil.compareOrgType(userOrg.getType(), orgTypeMin) > 0
			&& RoleOrgUtil.compareOrgType(userOrg.getType(), orgTypeMax) < 0){
			//min < 用户机构 < max
			userRangeForMin.removeAll(minRange);
			maxRange.removeAll(userRangeForMax);
			//当前登录机构比查询的机构范围大
			param.put("finddown",userRangeForMin);
			//当前登录机构范围小于等于查询的机构
			maxRange.add(orgTypeMax);
			param.put("findup",maxRange);
			//当前登录机构
			param.put("findequal",true);
		}else if(RoleOrgUtil.compareOrgType(userOrg.getType(), orgTypeMin) <= 0){
			
			maxRange.removeAll(minRange);
			//用户机构 <= min
			if(RoleOrgUtil.compareOrgType(userOrg.getType(), orgTypeMin) == 0){
				//用户机构 == min
				param.put("findequal",true);
			}			
			maxRange.add(orgTypeMax);
			param.put("findup",maxRange);
		}else{
			//max <= 用户机构
			maxRange.removeAll(minRange);
			if(RoleOrgUtil.compareOrgType(userOrg.getType(), orgTypeMax) == 0){
				//max == 用户机构
				param.put("findequal",true);
			}else{
				maxRange.add(orgTypeMax);
			}			
			param.put("finddown",maxRange);
		}
		
		//用户机构id
		param.put("org_id",userOrg.getId());
		if(roleIds != null && !roleIds.isEmpty()){
			//查询的角色类型
			param.put("role_ids",roleIds);
		}
		return roleOrgDao.findRoleOrgRange(param);
	}
	
	/**
	 * 通过用户id，角色id查询指定的角色，机构
	 * 2016年1月25日
	 * By 朱杰
	 * @param userId 用户id
	 * @param role_id 角色id
	 * @param orgType 查询的机构类型
	 * @param roleIds 查询的用户类型 。 null的情况，查询组织机构
	 * @return
	 */
	public static List<UserRoleOrgEx> findOrgByUserAndRole(String userId,String role_id,String orgTypeMinParam,String orgTypeMaxParam,List<String> roleIds){
		if(StringUtils.isBlank(userId) || StringUtils.isBlank(role_id)){
			//理财经理id为空的情况，返回空
			return new ArrayList<UserRoleOrgEx>();
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("user_id", userId);
		param.put("role_id", role_id);
		com.creditharmony.fortune.common.entity.Org org = roleOrgDao.getRoleTypeByUserAndRole(param);
		if(org == null || StringUtils.isBlank(org.getId())){
			//机构为空的话，返回空
			return new ArrayList<UserRoleOrgEx>();
		}
		String orgTypeMin = orgTypeMinParam;
		String orgTypeMax = orgTypeMaxParam;
		
		if(RoleOrgUtil.compareOrgType(orgTypeMinParam, orgTypeMaxParam) > 0 ){
			//调整min，max顺序
			orgTypeMin = orgTypeMaxParam;
			orgTypeMax = orgTypeMinParam;
		}
		List<String> userRangeForMin = ArrayHelper.toList(OrgTypeRange.parseByCode(org.getType()).getRange());
		List<String> userRangeForMax = ArrayHelper.toList(OrgTypeRange.parseByCode(org.getType()).getRange());
		List<String> minRange = ArrayHelper.toList(OrgTypeRange.parseByCode(orgTypeMin).getRange());
		List<String> maxRange = ArrayHelper.toList(OrgTypeRange.parseByCode(orgTypeMax).getRange());
		
		param = new HashMap<String,Object>();
		if(RoleOrgUtil.compareOrgType(org.getType(), orgTypeMin) > 0
			&& RoleOrgUtil.compareOrgType(org.getType(), orgTypeMax) < 0){
			//min < 用户机构 < max
			userRangeForMin.removeAll(minRange);
			maxRange.removeAll(userRangeForMax);
			//当前登录机构比查询的机构范围大
			param.put("finddown",userRangeForMin);
			//当前登录机构范围小于等于查询的机构
			maxRange.add(orgTypeMax);
			param.put("findup",maxRange);
			//当前登录机构
			param.put("findequal",true);
		}else if(RoleOrgUtil.compareOrgType(org.getType(), orgTypeMin) <= 0){
			
			maxRange.removeAll(minRange);
			//用户机构 <= min
			if(RoleOrgUtil.compareOrgType(org.getType(), orgTypeMin) == 0){
				//用户机构 == min
				param.put("findequal",true);
			}			
			maxRange.add(orgTypeMax);
			param.put("findup",maxRange);
		}else{
			//max <= 用户机构
			maxRange.removeAll(minRange);
			if(RoleOrgUtil.compareOrgType(org.getType(), orgTypeMax) == 0){
				//max == 用户机构
				param.put("findequal",true);
			}else{
				maxRange.add(orgTypeMax);
			}			
			param.put("finddown",maxRange);
		}
		
		//用户机构id
		param.put("org_id",org.getId());
		if(roleIds != null && !roleIds.isEmpty()){
			//查询的角色类型
			param.put("role_ids",roleIds);
		}
		return roleOrgDao.findRoleOrgRange(param);
	}
	
	/**
	 * 根据理财经理获取营业部信息
	 * 2016年1月25日
	 * By 朱杰
	 * @param userId
	 * @return
	 */
	public static UserRoleOrgEx findStoreOrgByManager(String userId){
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgByUserAndRole(
				userId, FortuneRole.FINANCING_MANAGER.id, FortuneOrgType.STORE.key, FortuneOrgType.STORE.key, null);
		if(list.size() > 0){
			return  list.get(0);
		}
		return new UserRoleOrgEx();
	}
}
