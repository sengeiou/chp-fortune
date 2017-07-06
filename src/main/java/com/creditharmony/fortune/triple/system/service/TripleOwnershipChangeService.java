package com.creditharmony.fortune.triple.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.triple.bean.TripleChangePhoneInBean;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.fortune.type.OwnershipChange;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 
 * @author chenxiaoiang
 *三网客户归属变更
 */
@Service
public class TripleOwnershipChangeService {

	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(TripleNewCustomerServiceImp.class);
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private InfoGroupService infoGroupService;

	public void khXz(IntCustomerBean paramBean, String type) {
		try {
			IntDeliveryEx intDeliveryEx = new IntDeliveryEx();
			intDeliveryEx.setPhone(paramBean.getNewPhone());
			if (OsType.CHP.value.equals(paramBean.getOsType())) {
				intDeliveryEx.setCustomerCode(paramBean.getOsId());
			}

			// 如果新理财经理为电销
			if ("2".equals(type)) {
				List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(paramBean.getNewPhone());
				List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(listPhone);
				if (ArrayHelper.isNotEmpty(deliveryList)) {
					setDxEmp(intDeliveryEx,deliveryList.get(0));
				}
			}
			// 新理财经理
			setNewEmp(intDeliveryEx, paramBean.getEmpCode());

			intDeliveryEx.setCreateTime(new Date());
			intDeliveryEx.setChangeReason("客户新增");
			intDeliveryEx.setOsType(paramBean.getOsType());
			if (OsType.CHP.value.equals(paramBean.getOsType())) {
				intDeliveryEx.setLoginName(paramBean.getUserName());
			} else {
				intDeliveryEx.setLoginName(paramBean.getLogName());
			}
			intDeliveryEx.setDeliveryType(DeliveryType.KHXZ.value);
			intDeliveryEx.setOperator(DeliveryType.getDeliveryType(DeliveryType.KHXZ.value));
			tripleCustomerDeliveryManager.insertIntDelivery(intDeliveryEx);
		} catch (Exception e) {
			logger.error("客户新增异常", e);
		}
	}

	public void khRz(IntCustomerBean paramBean, String type, IntDeliveryEx... delivery) {
		try {
			IntDeliveryEx deliveryEx = new IntDeliveryEx();
			identical(deliveryEx, type, delivery);
			setChangeReason(deliveryEx, paramBean.getNewPhone(), paramBean.getOsType(), DeliveryType.KHRZ.value);
			tripleCustomerDeliveryManager.insertIntDelivery(deliveryEx);
		} catch (Exception e) {
			logger.error("客户认证异常", e);
		}
	}

	public void sjhBg(TripleChangePhoneInBean paramBean, String type, IntDeliveryEx... delivery) {
		try {
			IntDeliveryEx deliveryEx = new IntDeliveryEx();
			identical(deliveryEx, type, delivery);
			setChangeReason(deliveryEx, paramBean.getNewPhone(), paramBean.getOsType(), DeliveryType.SJHBG.value);
			tripleCustomerDeliveryManager.insertIntDelivery(deliveryEx);
		} catch (Exception e) {
			logger.error("手机号变更异常", e);
		}
	}

	public void xfLj(TripleNewCustomerInBean paramBean, String type, IntDeliveryEx... delivery) {
		try {
			IntDeliveryEx deliveryEx = new IntDeliveryEx();
			identical(deliveryEx, type, delivery);
			setChangeReason(deliveryEx, paramBean.getPhone(), paramBean.getOsType(), DeliveryType.XFLJ.value);
			tripleCustomerDeliveryManager.insertIntDelivery(deliveryEx);
		} catch (Exception e) {
			logger.error("修复链接异常", e);
		}
	}

	private void setChangeReason(IntDeliveryEx deliveryEx, String phone, String osType, String value) {
		IntPhone ip = new IntPhone();
		ip.setPhone(phone);
		ip.setOsType(osType);
		List<IntPhone> phoneList = tripleNewCustomerDao.searchCustomerByPhoneNum(ip);
		String changeReason = "";
		if (ArrayHelper.isNotEmpty(phoneList)) {
			changeReason += "(";
			if (StringUtils.isNotEmpty(phoneList.get(0).getCustomerCode())) {
				changeReason += phoneList.get(0).getCustomerCode();
			} else {
				changeReason += phoneList.get(0).getLoginName();
			}
			changeReason += ")" + DeliveryType.getDeliveryType(value);
		}
		deliveryEx.setChangeReason(changeReason);
		deliveryEx.setDeliveryType(value);
		deliveryEx.setOperator(DeliveryType.getDeliveryType(value));
	}

	private void identical(IntDeliveryEx deliveryEx, String dxType, IntDeliveryEx... delivery) {
		// 原理财经理
		if ("2".equals(dxType)) {
			setDxEmp(deliveryEx, delivery[1]);
		} else {
			setOldEmp(deliveryEx, delivery[0].getEmpCode());
		}
		// 新理财经理
		setNewEmp(deliveryEx, delivery[0].getNewEmpCode());

		deliveryEx.setCreateTime(new Date());
		deliveryEx.setPhone(delivery[0].getPhone());
		deliveryEx.setOsType(delivery[0].getOsType());
		deliveryEx.setLoginName(delivery[0].getLoginName());
		deliveryEx.setCustomerCode(delivery[0].getCustomerCode());
	}

	private void setDxEmp(IntDeliveryEx intDeliveryEx, IntDeliveryEx dxDelivery) {
		if (dxDelivery != null) {
			intDeliveryEx.setEmpId(dxDelivery.getEmpId());
			intDeliveryEx.setEmpCode(dxDelivery.getEmpCode());
			intDeliveryEx.setEmpName(dxDelivery.getEmpName());
			intDeliveryEx.setOrgId(dxDelivery.getOrgId());
			intDeliveryEx.setOrgName(dxDelivery.getOrgName());
			intDeliveryEx.setTeamManagerId(dxDelivery.getTeamManagerId());
			intDeliveryEx.setTeamManagerCode(dxDelivery.getTeamManagerCode());
			intDeliveryEx.setTeamManagerName(dxDelivery.getTeamManagerName());
		}
	}

	private void setNewEmp(IntDeliveryEx deliveryEx, String empCode) {
		List<IntDeliveryEx> newEmpList = tripleCustomerDeliveryManager.findEmpInfoByCode(empCode);
		if (ArrayHelper.isNotEmpty(newEmpList)) {
			deliveryEx.setNewEmpId(newEmpList.get(0).getNewEmpId());
			deliveryEx.setNewEmpCode(newEmpList.get(0).getNewEmpCode());
			deliveryEx.setNewEmpName(newEmpList.get(0).getNewEmpName());
			deliveryEx.setNewOrgId(newEmpList.get(0).getNewOrgId());
			deliveryEx.setNewOrgName(newEmpList.get(0).getNewOrgName());
			deliveryEx.setNewTeamManagerId(newEmpList.get(0).getNewTeamManagerId());
			deliveryEx.setNewTeamManagerCode(newEmpList.get(0).getNewTeamManagerCode());
			deliveryEx.setNewTeamManagerName(newEmpList.get(0).getNewTeamManagerName());
		}
	}

	private void setOldEmp(IntDeliveryEx deliveryEx, String empCode) {
		List<IntDeliveryEx> oldEmpList = tripleCustomerDeliveryManager.findEmpInfoByCode(empCode);
		if (ArrayHelper.isNotEmpty(oldEmpList)) {
			deliveryEx.setEmpId(oldEmpList.get(0).getNewEmpId());
			deliveryEx.setEmpCode(oldEmpList.get(0).getNewEmpCode());
			deliveryEx.setEmpName(oldEmpList.get(0).getNewEmpName());
			deliveryEx.setOrgId(oldEmpList.get(0).getNewOrgId());
			deliveryEx.setOrgName(oldEmpList.get(0).getNewOrgName());
			deliveryEx.setTeamManagerId(oldEmpList.get(0).getNewTeamManagerId());
			deliveryEx.setTeamManagerCode(oldEmpList.get(0).getNewTeamManagerCode());
			deliveryEx.setTeamManagerName(oldEmpList.get(0).getNewTeamManagerName());
		}
	}

	public boolean isSatisfy(String empCode) {
		List<String> list = new ArrayList<String>();
		OwnershipChange[] values = OwnershipChange.values();
		for (OwnershipChange ownershipChange : values) {
			list.add(ownershipChange.value);
		}
		return list.contains(empCode);
	}
}