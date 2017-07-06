package com.creditharmony.fortune.triple.system.facade;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.threenet.entity.ThreeNetUser;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.AutoDeliveryCustomerService;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * @Class Name AutoDeliveryCustomerFacade
 * @author 胡体勇
 * @Create In 2016年5月4日
 */
@Service
public class AutoDeliveryCustomerFacade {
	
	@Autowired
	private AutoDeliveryCustomerService autoDeliveryCustomerService;
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(AutoDeliveryCustomerFacade.class);
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	
	/**
	 * 理财经理离职自动交割
	 * 2016年5月3日
	 * By 胡体勇
	 * @param user
	 */
	public void autoDelivery(ThreeNetUser user){
		// 理财经理离职自动交割其对应的客户
		if(UserStatus.OFF.equals(user.getStatus())){
			if(StringUtils.isNotEmpty(user.getUserCode())){
				IntPhone phone = new IntPhone();
				phone.setEmpCode(user.getUserCode());
				// 判断用户是否是离职理财经理
				List<IntPhone> list = tripleNewCustomerDao.checkStopManager(phone);
				if(list.size() > 0){
					// 获取符合条件的理财经理
					List<FortuneUser> managerList = autoDeliveryCustomerService.findManager(user.getUserId());
					// 获取离职理财经理下的例子
					List<IntDeliveryEx> customerList = autoDeliveryCustomerService.findCustomer(user.getUserCode());
					if (managerList.size() > 0 && customerList.size() > 0) {
						boolean flag = validation(customerList);
						if (flag) {
							Collections.shuffle(managerList);
							int index = 0;
							int size = managerList.size();
							for (IntDeliveryEx elem : customerList) {
								try {
									this.autoDeliveryCusotmer(managerList, elem, index, size);
									this.sendInfo(managerList, elem, index, size);
								} catch (Exception e) {
									this.logger.error("理财经理离职交割异常", e);
								}
								index++;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 校验离职理财经理下是否有大金融和金信的客户 2016年09月23日 By 陈晓强
	 * 
	 * @param managerList
	 * @param customerList
	 */
	private boolean validation(List<IntDeliveryEx> customerList) {
		for (IntDeliveryEx intDeliveryEx : customerList) {
			if (StringUtils.isNotEmpty(intDeliveryEx.getOsType())) {
				if (OsType.DJR.value.equals(intDeliveryEx.getOsType())
						|| OsType.JX.value.equals(intDeliveryEx.getOsType())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 理财经理离职自动交割其下的客户
	 * 2016年5月4日
	 * By 胡体勇
	 * @param managerList
	 * @param customerList
	 */
	public void autoDeliveryCusotmer(List<FortuneUser> managerList,IntDeliveryEx elem,int index,int size){
		// 查询新理财经理信息
		List<IntDeliveryEx> newEmpInfo = tripleCustomerDeliveryManager.findEmpInfoByCode(managerList.get(index % size).getCode());
    	// 查询旧理财经理
		List<IntDeliveryEx> empInfo = tripleCustomerDeliveryManager.findStopEmpInfoByCode(elem.getEmpCode());
		
		try {
			
        	IntPhone p = new IntPhone();
        	p.setPhone(elem.getPhone());
        	p.setEmpCode(managerList.get(index % size).getCode());
        	p.setOsType(OsType.CHP.value);
        	// 修改三网手机号对应的理财经理
        	autoDeliveryCustomerService.updateEmpCodeByOsTypePhoneDao(p);
        	// 修改chp客户对应理财经理
        	IntDeliveryEx ex = new IntDeliveryEx();
        	ex.setPhone(elem.getPhone());
        	ex.setEmpCode(managerList.get(index % size).getCode());
        	IntDeliveryEx d = new IntDeliveryEx();
        	
        	if(newEmpInfo.size() > 0){
        		ex.setNewEmpCode(newEmpInfo.get(0).getNewEmpId());
            	ex.setNewTeamId(newEmpInfo.get(0).getNewTeamId());
        		ex.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
        		ex.setNewTeamManagerId(newEmpInfo.get(0).getNewTeamManagerId());
        		ex.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
        		
        	    d.setNewEmpName(newEmpInfo.get(0).getNewEmpName());
        		d.setNewEmpCode(newEmpInfo.get(0).getNewEmpCode());
            	d.setNewTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerCode());
        		d.setNewTeamManagerName(newEmpInfo.get(0).getNewTeamManagerName());
        		d.setNewOrgName(newEmpInfo.get(0).getNewOrgName());
        		d.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
        		
        	}
    		ex.setModifyBy(UserUtils.getUserId());
    	    ex.setModifyTime(new Date());
    	    autoDeliveryCustomerService.updateCustomerEmpDao(ex);
    	          	
        	if(ArrayHelper.isNotEmpty(empInfo)){
        		d.setEmpName(empInfo.get(0).getNewEmpName());
    			d.setTeamManagerCode(empInfo.get(0).getNewTeamManagerCode());
    			d.setTeamManagerName(empInfo.get(0).getNewTeamManagerName());
    			d.setOrgId(empInfo.get(0).getNewOrgId());
    			d.setOrgName(empInfo.get(0).getNewOrgName());
        	}
        	
        	d.setPhone(elem.getPhone());
        	d.setEmpCode(elem.getEmpCode());
        	d.setId(IdGen.uuid());
        	d.setDeliveryType(DeliveryType.LZZDJG.value);
        	d.setLoginName(elem.getLoginName());
        	d.setCreateTime(new Date());
        	d.setOperator("离职自动交割");
			d.setChangeReason("离职自动交割");
        	d.setOsType(elem.getOsType());
        	d.setCustomerCode(elem.getCustomerCode());
        	autoDeliveryCustomerService.insertIntDeliveryDao(d);
		} catch (Exception e) {
			logger.error("客户："+elem.getLoginName()+"所属理财经理工号为："+elem.getEmpCode()+"离职交割异常！",e);
			IntDeliveryEx intEx = new IntDeliveryEx();
			intEx.setDeliveryType(DeliveryType.SB.value);
			intEx.setPhone(elem.getPhone());
			intEx.setCustomerCode(elem.getCustomerCode());
			intEx.setOsType(elem.getOsType());
			intEx.setRemark("离职自动交割");
			intEx.setEmpCode(elem.getEmpCode());
			if(empInfo.size() > 0){
				intEx.setEmpName(empInfo.get(0).getNewEmpName());
				intEx.setOrgName(empInfo.get(0).getNewOrgName());
			}
			intEx.setNewEmpCode(managerList.get(index % size).getCode());
			if(newEmpInfo.size() > 0){
				intEx.setNewEmpName(newEmpInfo.get(0).getNewEmpName());
				intEx.setNewOrgName(newEmpInfo.get(0).getNewOrgName());
			}
			intEx.setCreateTime(new Date());
			autoDeliveryCustomerService.insertIntDeliveryDao(intEx);
		}
		
	}

	/**
	 * 理财经理离职推送客户信息 2016年9月20日 By 陈晓强
	 * 
	 * @param elem
	 */
	private void sendInfo(List<FortuneUser> managerList, IntDeliveryEx elem, int index, int size) {
		try {
			List<IntDeliveryEx> newEmpInfo = tripleCustomerDeliveryManager
					.findEmpInfoByCode(managerList.get(index % size).getCode());
			IntCustomerBean cust = new IntCustomerBean();
			cust.setUniqueNum(SerialNum.getSerialNum());
			cust.setSendStatus(Constant.SEND_STATUS_FAIL);
			cust.setId(IdGen.uuid());
			cust.setOsType(OsType.CHP.value);
			cust.setNewPhone(elem.getPhone());
			if (newEmpInfo.size() > 0) {
				cust.setOsId(newEmpInfo.get(0).getOsId());
				cust.setEmpCode(newEmpInfo.get(0).getNewEmpCode());
			}
			cust.setCardType("");
			cust.setCardId("");
			cust.setLogName(elem.getLoginName());
			cust.setUserName(elem.getLoginName());
			cust.setCreateTime(new Date());
			cust.setSendTime(new Date());
			cust.setSendType("A014");
			cust.setOperation("FBG");
			cust.setICEAddress("test");
			tripleNewCustomerDao.insertTripleCustomer(cust);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("理财经理离职推送客户信息异常！");
			throw new ServiceException("sendInfo exception");
		}
	}
}