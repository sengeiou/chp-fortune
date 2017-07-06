package com.creditharmony.fortune.sync.data.proxy;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.DeleteFlagType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.sync.data.entity.SyncDict;
import com.creditharmony.core.sync.data.entity.SyncOrg;
import com.creditharmony.core.sync.data.entity.SyncRole;
import com.creditharmony.core.sync.data.entity.SyncUser;
import com.creditharmony.core.sync.data.entity.SyncUserRoleOrg;
import com.creditharmony.core.sync.data.type.SyncType;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.fortune.dict.entity.DictInfo;
import com.creditharmony.fortune.dict.service.DictInfoManager;
import com.creditharmony.fortune.exituserorg.entity.UserOrgInfo;
import com.creditharmony.fortune.exituserorg.service.UserOrgInfoService;
import com.creditharmony.fortune.sync.data.remote.FortuneSyncDataService;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.entity.IntRoleBean;
import com.creditharmony.fortune.triple.system.service.TripleRoleSyncService;
import com.creditharmony.fortune.users.entity.OrgInfo;
import com.creditharmony.fortune.users.entity.RoleInfo;
import com.creditharmony.fortune.users.entity.UserInfo;
import com.creditharmony.fortune.users.service.OrgInfoService;
import com.creditharmony.fortune.users.service.RoleInfoService;
import com.creditharmony.fortune.users.service.UserInfoService;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * 财富端同步从系统管理模块同步数据接口代理类
 * @Class Name FortuneSyncDataProxy
 * @author 张永生
 * @Create In 2015年12月7日
 */
@Component
public class FortuneSyncDataProxy implements FortuneSyncDataService{
	
	protected Logger logger = LoggerFactory.getLogger(FortuneSyncDataProxy.class);

	private UserInfoService userInfoService = SpringContextHolder.getBean(UserInfoService.class);
	
	private OrgInfoService orgInfoService = SpringContextHolder.getBean(OrgInfoService.class);
	
	private RoleInfoService roleInfoService = SpringContextHolder.getBean(RoleInfoService.class);
	
	private DictInfoManager dictInfoManager = SpringContextHolder.getBean(DictInfoManager.class);
	
	private TripleRoleSyncService tripleRoleSyncService = SpringContextHolder.getBean(TripleRoleSyncService.class);

	private UserOrgInfoService userOrgInfoService = SpringContextHolder.getBean(UserOrgInfoService.class);

	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncUser(SyncUser syncUser) {
		boolean success = true;
		try {
			if(SyncType.TYPE_ADD.value.equals(syncUser.getSyncType())){
				UserInfo userInfo = userInfoService.get(syncUser.getUserId());
				if(ObjectHelper.isEmpty(userInfo)){
					userInfo = new UserInfo();
					BeanUtils.copyProperties(syncUser, userInfo);
					userInfo.setId(syncUser.getUserId());
					userInfoService.saveUser(userInfo);
				}
			}else if(SyncType.TYPE_MODIFY.value.equals(syncUser.getSyncType())){
				UserInfo userInfo = userInfoService.get(syncUser.getUserId());
				if(ObjectHelper.isEmpty(userInfo)){
					userInfo = new UserInfo();
					BeanUtils.copyProperties(syncUser, userInfo);
					userInfo.setId(syncUser.getUserId());
					userInfoService.saveUser(userInfo);
				}else{
					BeanUtils.copyProperties(syncUser, userInfo);
					userInfo.setId(syncUser.getUserId());
					userInfo.setPosPwd(null); //系统管理sms更新用户时，不再更新posPwd
					userInfoService.update(userInfo);
					// 账号删除停用时查看是否有转赠流程
					if ("1".equals(userInfo.getDelFlag()) || "0".equals(userInfo.getStatus())) {
						UserOrgInfo userOrgInfo = new UserOrgInfo();
						userOrgInfo.setId(IdGen.uuid());
						userOrgInfo.setOrgId(userInfo.getDepartmentId());
						userOrgInfoService.insert(userOrgInfo);
					}
				}
			}else if(SyncType.TYPE_DELETE.value.equals(syncUser.getSyncType())){
				UserInfo userInfo = userInfoService.get(syncUser.getUserId());
				if(ObjectHelper.isEmpty(userInfo)){
					userInfo = new UserInfo();
					BeanUtils.copyProperties(syncUser, userInfo);
					userInfo.setId(syncUser.getUserId());
					userInfo.setDelFlag(DeleteFlagType.DELETE);
					userInfoService.saveUser(userInfo);
				}else{
					userInfoService.delete(userInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("executeSyncUser exception, syncUserId={}, userId={}"+e.getMessage(),
					new Object[] { syncUser.getId(), syncUser.getUserId() });
			success = false;
			throw new ServiceException("executeSyncUser exception");
		}finally{
			return success;
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncUserRoleOrg(SyncUserRoleOrg syncUserRoleOrg) {
		boolean success = true;
		String syncType = syncUserRoleOrg.getSyncType();
		try {
			if(SyncType.TYPE_DELETE.value.equals(syncType)){
				userInfoService.deleteUserRoleOrg(syncUserRoleOrg.getUserId());
			}else if(SyncType.TYPE_ADD.value.equals(syncType)){
				UserRoleOrg  userRoleOrg = new UserRoleOrg();
				BeanUtils.copyProperties(syncUserRoleOrg, userRoleOrg);
				userInfoService.insertUserRoleOrg(userRoleOrg);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("executeSyncUserRoleOrg exception, syncUserId={}, orgId={},roleId={}",
					new Object[] { syncUserRoleOrg.getUserId(),syncUserRoleOrg.getOrgId(),syncUserRoleOrg.getRoleId()});
			success = false;
			throw new ServiceException("executeSyncUserRoleOrg exception");
		}finally{
			return success;
		}
	}

	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncOrg(SyncOrg syncOrg) {
		boolean success = true;
		try {
			if(SyncType.TYPE_ADD.value.equals(syncOrg.getSyncType())){
				OrgInfo orgInfo = orgInfoService.get(syncOrg.getOrgId());
				if(ObjectHelper.isEmpty(orgInfo)){
					orgInfo = new OrgInfo();
					BeanUtils.copyProperties(syncOrg, orgInfo);
					orgInfo.setId(syncOrg.getOrgId());
					orgInfoService.saveOrg(orgInfo);
				}
			}else if(SyncType.TYPE_MODIFY.value.equals(syncOrg.getSyncType())){
				OrgInfo orgInfo = orgInfoService.get(syncOrg.getOrgId());
				if(ObjectHelper.isEmpty(orgInfo)){
					orgInfo = new OrgInfo();
					BeanUtils.copyProperties(syncOrg, orgInfo);
					orgInfo.setId(syncOrg.getOrgId());
					orgInfoService.saveOrg(orgInfo);
				}else{
					BeanUtils.copyProperties(syncOrg, orgInfo);
					orgInfo.setId(syncOrg.getOrgId());
					orgInfoService.update(orgInfo);
				}
			}else if(SyncType.TYPE_DELETE.value.equals(syncOrg.getSyncType())){
				OrgInfo orgInfo = orgInfoService.get(syncOrg.getOrgId());
				if(ObjectHelper.isEmpty(orgInfo)){
					orgInfo = new OrgInfo();
					BeanUtils.copyProperties(syncOrg, orgInfo);
					orgInfo.setId(syncOrg.getOrgId());
					orgInfo.setDelFlag(DeleteFlagType.DELETE);
					orgInfoService.saveOrg(orgInfo);
				}else{
					orgInfoService.delete(orgInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("executeSyncOrg exception, syncOrgId={}, orgId={}",
					new Object[] { syncOrg.getId(), syncOrg.getOrgId() });
			success = false;
			throw new ServiceException("executeSyncOrg exception");
		}finally{
			return success;
		}
	}

	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncRole(SyncRole syncRole) {
		boolean success = true;
		try {
			//插入三网同步表 开始
			IntRoleBean intRole=this.getIntRole(syncRole);
			if(null!=intRole){
				tripleRoleSyncService.insertIntRole(intRole);
			}
			//插入三网同步表 结束
			if(SyncType.TYPE_ADD.value.equals(syncRole.getSyncType())){
				RoleInfo roleInfo = roleInfoService.get(syncRole.getRoleId());
				if(ObjectHelper.isEmpty(roleInfo)){
					roleInfo = new RoleInfo();
					BeanUtils.copyProperties(syncRole, roleInfo);
					roleInfo.setId(syncRole.getRoleId());
					roleInfoService.saveRole(roleInfo);
				}
			}else if(SyncType.TYPE_MODIFY.value.equals(syncRole.getSyncType())){
				RoleInfo roleInfo = roleInfoService.get(syncRole.getRoleId());
				if(ObjectHelper.isEmpty(roleInfo)){
					roleInfo = new RoleInfo();
					BeanUtils.copyProperties(syncRole, roleInfo);
					roleInfo.setId(syncRole.getRoleId());
					roleInfoService.saveRole(roleInfo);
				}else{
					BeanUtils.copyProperties(syncRole, roleInfo);
					roleInfo.setId(syncRole.getRoleId());
					roleInfoService.update(roleInfo);
				}
			}else if(SyncType.TYPE_DELETE.value.equals(syncRole.getSyncType())){
				RoleInfo roleInfo = roleInfoService.get(syncRole.getRoleId());
				if(ObjectHelper.isEmpty(roleInfo)){
					roleInfo = new RoleInfo();
					BeanUtils.copyProperties(syncRole, roleInfo);
					roleInfo.setId(syncRole.getRoleId());
					roleInfo.setDelFlag(DeleteFlagType.DELETE);
					roleInfoService.saveRole(roleInfo);
				}else{
					roleInfoService.delete(roleInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("executeSyncRole exception, syncRoleId={}, RoleId={}"+e.getMessage(),
					new Object[] { syncRole.getId(), syncRole.getRoleId() });
			success = false;
			throw new ServiceException("executeSyncRole exception");
		}finally{
			return success;
		}
	}
	
	/**
	 * 执行同步数据字典接口
	 * 2015年12月28日
	 * By 陈伟东
	 * @param syncDict
	 * @return
	 */
	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncDict(SyncDict syncDict){
		boolean success = true;
		try {
			if(SyncType.TYPE_ADD.value.equals(syncDict.getSyncType())){
				DictInfo dictInfo = dictInfoManager.get(syncDict.getDictId());
				if(ObjectHelper.isEmpty(dictInfo)){
					dictInfo = new DictInfo();
					BeanUtils.copyProperties(syncDict, dictInfo);
					dictInfo.setId(syncDict.getDictId());
					dictInfoManager.saveDictInfo(dictInfo);
				}
			}else if(SyncType.TYPE_MODIFY.value.equals(syncDict.getSyncType())){
				DictInfo dictInfo = dictInfoManager.get(syncDict.getDictId());
				if(ObjectHelper.isEmpty(dictInfo)){
					dictInfo = new DictInfo();
					BeanUtils.copyProperties(syncDict, dictInfo);
					dictInfo.setId(syncDict.getDictId());
					dictInfoManager.saveDictInfo(dictInfo);
				}else{
					BeanUtils.copyProperties(syncDict, dictInfo);
					dictInfo.setId(syncDict.getDictId());
					dictInfoManager.update(dictInfo);
				}
			}else if(SyncType.TYPE_DELETE.value.equals(syncDict.getSyncType())){
				DictInfo dictInfo = dictInfoManager.get(syncDict.getDictId());
				if(ObjectHelper.isEmpty(dictInfo)){
					dictInfo = new DictInfo();
					BeanUtils.copyProperties(syncDict, dictInfo);
					dictInfo.setId(syncDict.getDictId());
					dictInfo.setDelFlag(DeleteFlagType.DELETE);
					dictInfoManager.saveDictInfo(dictInfo);
				}else{
					dictInfoManager.delete(dictInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("executeSyncDict exception, syncDictId={}, dictId={}",
					new Object[] { syncDict.getId(), syncDict.getDictId() });
			success = false;
			throw new ServiceException("executeSyncDict exception");
		}finally{
			return success;
		}
	}
	
	/**
	 * 根据同步角色对象获取intRole角色对象
	 * @param sRole
	 */
	private IntRoleBean getIntRole(SyncRole role){
		if (null==role){
			return null;
		}
		// 设置备份信息
		IntRoleBean intRole = new IntRoleBean();
		if("1".equals(role.getUseable())){
			intRole.setUseable(Constant.USE_ABLE);
		}else if("0".equals(role.getUseable())){
			intRole.setUseable(Constant.USE_UNABLE);
		}
		if(SyncType.TYPE_ADD.value.equals(role.getSyncType())){
			intRole.setOperation(Constant.OPERATION_TYPE_C);
		}else if(SyncType.TYPE_MODIFY.value.equals(role.getSyncType())){
			intRole.setOperation(Constant.OPERATION_TYPE_M);
		}else if(SyncType.TYPE_DELETE.value.equals(role.getSyncType())){
			intRole.setOperation(Constant.OPERATION_TYPE_M);//如果是删除则修改为不可用
			intRole.setUseable(Constant.USE_UNABLE);
		}
		
		intRole.setSendStatus(Constant.SEND_STATUS_FAIL);
		intRole.setId(IdGen.uuid());
		intRole.setEnname(role.getEnName());
		intRole.setName(role.getName());
		intRole.setIsSys(role.getSysData());
		intRole.setRoleId(role.getRoleId());
		intRole.setRoleType(role.getRoleType());
		intRole.setDataScope(role.getDataScope());
		intRole.setDescription(role.getRemarks());
		intRole.setRoleLevel(role.getOrgType());
		intRole.setDataScope(role.getDataScope());
		intRole.setSendType(Constant.SYSNCNUM_JSXXTB);//角色信息同步
		intRole.setSendTime(new Date());
		intRole.setUniqueNum(SerialNum.getSerialNum());
		
		return intRole;
	}

	
}
