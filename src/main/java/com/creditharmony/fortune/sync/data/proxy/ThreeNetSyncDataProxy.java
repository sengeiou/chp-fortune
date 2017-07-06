package com.creditharmony.fortune.sync.data.proxy;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.sync.data.type.SyncType;
import com.creditharmony.core.threenet.entity.ThreeNetOrg;
import com.creditharmony.core.threenet.entity.ThreeNetUser;
import com.creditharmony.core.threenet.service.SyncUser2ThreeNetService;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.sync.data.remote.ThreeNetSyncDataService;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;
import com.creditharmony.fortune.triple.system.facade.AutoDeliveryCustomerFacade;
import com.creditharmony.fortune.triple.system.service.TripleCommonService;
import com.creditharmony.fortune.triple.system.service.TripleEmployeeSyncService;
import com.creditharmony.fortune.triple.system.service.TripleOrgSyncService;
import com.creditharmony.fortune.triple.system.service.TripleUserSyncService;
import com.creditharmony.fortune.users.entity.UserInfo;
import com.creditharmony.fortune.users.service.UserInfoService;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * 三网同步数据接口
 * 数据由系统管理sms模块同步到财富系统
 * @Class Name ThreeNetSyncDataProxy
 * @author 张永生
 * @Create In 2016年3月1日
 */
@Component
public class ThreeNetSyncDataProxy implements ThreeNetSyncDataService {
	
	// 日志
	protected Logger logger = LoggerFactory.getLogger(ThreeNetSyncDataProxy.class);
	// 用户信息同步
	private TripleUserSyncService tripleUserSyncService = SpringContextHolder.getBean(TripleUserSyncService.class);
	// 理财经理信息同步
	private TripleEmployeeSyncService tripleEmployeeSyncService = SpringContextHolder.getBean(TripleEmployeeSyncService.class);
	// 组织机构信息同步
	private TripleOrgSyncService tripleOrgSyncService = SpringContextHolder.getBean(TripleOrgSyncService.class);
    // 理财离职客户自动交割
	private AutoDeliveryCustomerFacade autoDeliveryCustomerFacade = SpringContextHolder.getBean(AutoDeliveryCustomerFacade.class);
	private TripleCommonService tripleCommonService =  SpringContextHolder.getBean(TripleCommonService.class);
	//用户信息业务实例
	private UserInfoService  userInfoService =  SpringContextHolder.getBean(UserInfoService.class);
	//查询管理系统的用户数据
	private SyncUser2ThreeNetService  syncUser2ThreeNetService =  SpringContextHolder.getBean(SyncUser2ThreeNetService.class);
	//查询管理系统的用户数据
	private IntKeyLogService  intKeyLogService =  SpringContextHolder.getBean(IntKeyLogService.class);
	
	
	/**
	 * 三网组织获取sms系统推送的组织机构信息
	 */
	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean syncThreeNetOrg(ThreeNetOrg org) {
		boolean success = true;
		try{
			// 设置备份信息
			IntOrgBean intOrg = new IntOrgBean();
			if(SyncType.TYPE_ADD.value.equals(org.getSyncType())){
				intOrg.setOperation(Constant.OPERATION_TYPE_C);
			}else if(SyncType.TYPE_MODIFY.value.equals(org.getSyncType())){
				intOrg.setOperation(Constant.OPERATION_TYPE_M);
			}else if(SyncType.TYPE_DELETE.value.equals(org.getSyncType())){
				intOrg.setOperation(Constant.OPERATION_TYPE_M);
			}
			if("1".equals(org.getUseable())){
				intOrg.setStatus(Constant.USE_ABLE);
			}else if("0".equals(org.getUseable())){
				intOrg.setStatus(Constant.USE_UNABLE);
			}
			intOrg.setSendStatus(Constant.SEND_STATUS_FAIL);
			intOrg.setId(IdGen.uuid());
			intOrg.setParentId(org.getParentId());
			intOrg.setOrgId(org.getOrgId());
			intOrg.setOrgName(org.getName());
			intOrg.setProvinceId(org.getProvinceId());
			intOrg.setProvinceName(org.getProvinceName());
			intOrg.setCityId(org.getCityId());
			intOrg.setCityName(org.getCityName());
			intOrg.setOrgType(org.getType());
			intOrg.setOrgCode(org.getCode());
			intOrg.setOrgAddr(org.getAddress());
			intOrg.setContracts(org.getMaster());
			intOrg.setDescription(org.getRemarks());
			intOrg.setContractNum(org.getPhone());
			intOrg.setLastModifyTime(new Date());
			intOrg.setSendType("A010");
			intOrg.setSendTime(new Date());
			intOrg.setUniqueNum(SerialNum.getSerialNum());
			tripleOrgSyncService.insertIntOrg(intOrg);
		}catch (Exception e) {
			logger.error("组织机构信息同步三网异常！",e);
			success = false;
		}finally{
			return success;
		}
	}

	 /**
	  * 三网获取sms推送的用户信息
	  */
	@SuppressWarnings("finally")
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean syncThreeNetUser(ThreeNetUser user) {
		boolean success = true;
		try{
			// 设置备份信息
			IntEmployeeBean bean = new IntEmployeeBean();
			
			if("1".equals(user.getStatusFlag())){
				// 理财经理离职客户自动交割
				//20161009 注释掉自动交割代码
//				try {
//					autoDeliveryCustomerFacade.autoDelivery(user);
//				} catch (Exception e) {
//					logger.error("理财经理:"+user.getName()+"离职自动交割异常",e);
//					success = false;
//				}
			}else{
				if(SyncType.TYPE_ADD.value.equals(user.getSyncType())){
					bean.setOperation(Constant.OPERATION_TYPE_C);
				}else if(SyncType.TYPE_MODIFY.value.equals(user.getSyncType())){
					bean.setOperation(Constant.OPERATION_TYPE_M);
				}else if(SyncType.TYPE_DELETE.value.equals(user.getSyncType())){
					bean.setOperation(Constant.OPERATION_TYPE_M);
				}
				if(UserStatus.ON.equals(user.getStatus())){
					// 在职
					bean.setStatus(UserStatus.ON);
					bean.setUstatus(UserStatus.ON);
				}else if(UserStatus.OFF.equals(user.getStatus())){
					// 离职
					bean.setStatus(UserStatus.OFF);
					bean.setUstatus(UserStatus.OFF);
				}
				bean.setSendStatus(Constant.SEND_STATUS_FAIL);
				bean.setId(IdGen.uuid());
				
				bean.setUserId(user.getUserId());				//CX
				bean.setLoginName(user.getLoginName());			//CX
				bean.setPassword(user.getPassword());			//CX
				bean.setLeaderId(user.getLeaderId());			//CX
				bean.setDepartmentId(user.getDepartmentId());	//CX
				
				bean.setEmpCode(user.getUserCode());
				bean.setEmpName(user.getName());
				bean.setTeamId(user.getTeamId());
				bean.setBusinessId(user.getBusinessId());
				bean.setSex(user.getSex());
				bean.setMobilePhone(user.getMobile());
				bean.setMail(user.getEmail());
				bean.setEmpPosition(user.getPosition());
				bean.setParentId(user.getLeaderId());
				bean.setSendType("A011");
				bean.setSendTime(new Date());
				bean.setLastModifyTime(user.getModifyTime());
				bean.setCreateTime(user.getCreateTime());
				bean.setUniqueNum(SerialNum.getSerialNum());
				tripleUserSyncService.insertIntUser(bean);	//同步到 CX和CRM
				
				bean = getEmpBean(user);
				if(null!=bean){
					tripleEmployeeSyncService.insertIntEmployee(bean);
					logger.info("同步user_id为"+ bean.getUserId() +"的用户信息成功");
					try {
						if(SyncType.TYPE_MODIFY.value.equals(user.getSyncType())){
							tripleCommonService.updateManagerInfo(user.getUserId(), user.getUserCode());
						}
					} catch (Exception e) {
						logger.error("理财信息变更修改出借和用户表信息异常！");
					}
				}else{
					logger.info("同步user_id为"+ bean.getUserId() +"的用户信息失败");
				}
			}
			
		}catch (Exception e) {
			logger.error("理财经理信息同步三网异常！",e);
			success = false;
		}finally{
			return success;
		}
	}
	
	/**
	 * 由于同步到crm和创新 系统之外的系统时 优先同步理财经理信息所
	 * 需要根据理财经理编码查询在职理财经理信息
	 * @return
	 */
	private IntEmployeeBean  getEmpBean(ThreeNetUser u){
		ThreeNetUser user = syncUser2ThreeNetService.syncUser2ThreeNet(u);
		
		if(null==user){
			intKeyLogService.log(null, "A011员工信息", "根据收到的用户信息查询待发送三网其他系统用户信息为空："+JSONObject.toJSONString(u));
		}
		IntEmployeeBean bean= new IntEmployeeBean();
		
		if(SyncType.TYPE_ADD.value.equals(user.getSyncType())){
			bean.setOperation(Constant.OPERATION_TYPE_C);
		}else if(SyncType.TYPE_MODIFY.value.equals(user.getSyncType())){
			bean.setOperation(Constant.OPERATION_TYPE_M);
		}else if(SyncType.TYPE_DELETE.value.equals(user.getSyncType())){
			bean.setOperation(Constant.OPERATION_TYPE_M);
		}
		if(UserStatus.ON.equals(user.getStatus())){
			// 在职
			bean.setStatus(UserStatus.ON);
			bean.setUstatus(UserStatus.ON);
		}else if(UserStatus.OFF.equals(user.getStatus())){
			// 离职
			bean.setStatus(UserStatus.OFF);
			bean.setUstatus(UserStatus.OFF);
		}
		bean.setSendStatus(Constant.SEND_STATUS_FAIL);
		bean.setId(IdGen.uuid());
		
		bean.setUserId(user.getUserId());				//CX
		bean.setLoginName(user.getLoginName());			//CX
		bean.setPassword(user.getPassword());			//CX
		bean.setLeaderId(user.getLeaderId());			//CX
		bean.setDepartmentId(user.getDepartmentId());	//CX
		
		bean.setEmpCode(user.getUserCode());
		bean.setEmpName(user.getName());
		bean.setTeamId(user.getTeamId());
		bean.setBusinessId(user.getBusinessId());
		bean.setSex(user.getSex());
		bean.setMobilePhone(user.getMobile());
		bean.setMail(user.getEmail());
		bean.setEmpPosition(user.getPosition());
		bean.setParentId(user.getLeaderId());
		bean.setSendType("A011");
		bean.setSendTime(new Date());
		bean.setLastModifyTime(user.getModifyTime());
		bean.setCreateTime(user.getCreateTime());
		bean.setUniqueNum(SerialNum.getSerialNum());
		return bean;
	}
}