package com.creditharmony.fortune.common.util;

import java.util.Date;

import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.EmergencyContactEx;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;

public class BeanUtil {
/**
 * TripleNewCustomerInBean 转为  IntCustomerBean
 * @param customerInBean
 * @return
 */
public static IntCustomerBean conv2IntCustomerBean(TripleNewCustomerInBean customerInBean,Customer cus){
	if(null==customerInBean){
		return  null;
	}
    IntCustomerBean cust = new IntCustomerBean();
    //org.apache.commons.beanutils.BeanUtils.copyProperties(cust, paramBean);
    cust.setOsId(customerInBean.getOsId());
    cust.setLogName(customerInBean.getLoginName());
    if("XH_CHP".equals(customerInBean.getOsType())){
    	cust.setUserNumber(customerInBean.getOsId());
    	cust.setLogName("");
    }
    cust.setOsType(customerInBean.getOsType());
    cust.setNewPhone(customerInBean.getPhone());
    cust.setEmpCode(customerInBean.getEmpCode());
    cust.setCardType(customerInBean.getCardType());
    cust.setCardId(customerInBean.getCardId());
    cust.setUserName(customerInBean.getUserName());
    cust.setOperation(customerInBean.getOperation());
   // cust.setUniqueNum(customerInBean.getSerialNum());
    cust.setFaileName(customerInBean.getFaileName());
    cust.setBirthdayStr(customerInBean.getBirthday());
    if(null!=customerInBean.getBirthday()&&!"".equals(customerInBean.getBirthday())){
    	cust.setBirthday(DateUtils.convertDate(customerInBean.getBirthday()));
    }
    //如果cus 不为空 则 设置缺少的信息
    if(null!=cus){
    	cust.setMail(cus.getCustEmail());
    	cust.setSex(cus.getDictCustSex());
    	cust.setCusSource(cus.getDictCustSource());
    	cust.setBirthday(cus.getCustBirthday());
    	cust.setUserName(cus.getCustName());
    	if("XH_CHP".equals(customerInBean.getOsType())){
        	cust.setUserNumber(customerInBean.getOsId());
        }
    	cust.setLogName(customerInBean.getLoginName());
    	cust.setBirthdayStr(null==cus.getCustBirthday()?"":DateUtils.formatDate(cus.getCustBirthday(), "yyyy-MM-dd"));
    }
    
	return cust;
}

/**
 * Customer 转为  IntCustomerBean
 * @param customerInBean
 * @return
 */
public static IntCustomerBean conv2IntCustomerBean(Customer customerInBean){
	if(null==customerInBean){
		return  null;
	}
    IntCustomerBean cust = new IntCustomerBean();
    //org.apache.commons.beanutils.BeanUtils.copyProperties(cust, paramBean);
    cust.setOsId(customerInBean.getCustCode());
    cust.setUserNumber(customerInBean.getCustCode());
    cust.setOsType("XH_CHP");
    cust.setNewPhone(customerInBean.getCustMobilephone());
    cust.setEmpCode(customerInBean.getManagerCode());
    cust.setCardType(customerInBean.getCustCertType());
    cust.setCardId(customerInBean.getCustCertNum());
    cust.setUserName(customerInBean.getCustName());
    cust.setLogName(customerInBean.getCustName());
    cust.setOperation(Constant.OPERATION_TYPE_R);//客户信息修改
    cust.setMail(customerInBean.getCustEmail());
    cust.setSex(customerInBean.getDictCustSex());
    cust.setCusSource(customerInBean.getDictCustSource());
   // cust.setUniqueNum(.getSerialNum());
    cust.setFaileName("");
    cust.setBirthdayStr(
    		null==customerInBean.getCustBirthday()?"":DateUtils.date2Str(customerInBean.getCustBirthday(), "yyyy-MM-dd"));
    cust.setBirthday(customerInBean.getCustBirthday());
	return cust;
}

/**
 * 可能被修改的用户信息
 * @param customerInBean
 * @return
 */
public static void setChangeInfo2IntCustomerBean(
		CustomerEx customerInBean,IntCustomerBean intBean){
	if(null==customerInBean||null==intBean){
		return ;
	}
	//目前只有这两个字段改动会影响crm
	intBean.setNewPhone(customerInBean.getCustMobilephone());
	intBean.setMail(customerInBean.getCustEmail());
}

/**
 * 对象转为字符串
 * @param obj
 * @return
 */
public static String obj2Str(Object obj){
	return  null==obj?"":obj.toString();
}

/**
 * 设置 IntCustomerBean 紧急联系人信息
 * @param customerInBean
 * @return
 */
public static void setEmerInfo2IntCustomerBean(IntCustomerBean intBean, EmergencyContact emergencyContact){
	if(null==intBean||null==emergencyContact){
		return ;
	}
	intBean.setICEAddress(obj2Str(emergencyContact.getAddress().getAddr()));
	intBean.setICEArea(obj2Str(emergencyContact.getAddress().getAddrDistrict()));
	intBean.setICECardId(emergencyContact.getEmerCertNum());
	intBean.setICECardType(emergencyContact.getEmerType());
	intBean.setICEEmail(emergencyContact.getEmerEmail());
	intBean.setICEName(emergencyContact.getEmerName());
	intBean.setICEPhone(emergencyContact.getEmerMobilephone());
	intBean.setICERelation(emergencyContact.getEmerRelationship());
}
/**
 * 设置 IntCustomerBean 紧急联系人信息
 * @param customerInBean
 * @return
 */
public static void setEmerInfo2IntCustomerBean(IntCustomerBean intBean, EmergencyContactEx emergencyContact){
	if(null==intBean||null==emergencyContact){
		return ;
	}
	intBean.setICEAddress(obj2Str(emergencyContact.getAddress().getAddr()));
	intBean.setICEArea(obj2Str(emergencyContact.getAddress().getAddrDistrict()));
	intBean.setICECardId(emergencyContact.getEmerCertNum());
	intBean.setICECardType(emergencyContact.getEmerType());
	intBean.setICEEmail(emergencyContact.getEmerEmail());
	intBean.setICEName(emergencyContact.getEmerName());
	intBean.setICEPhone(emergencyContact.getEmerPhone());
	intBean.setICERelation(emergencyContact.getEmerRelationship());
}

/**
 * 将属性从复制到另外一个对象
 * @param customerInBean
 * @return
 */
public static void copy2newIntCustomerBean(IntCustomerBean fromBean, IntCustomerBean toBean){
	if(null==fromBean||null==toBean){
		return ;
	}
	toBean.setBirthday(fromBean.getBirthday());
	toBean.setBirthdayStr(fromBean.getBirthdayStr());
	toBean.setCardId(fromBean.getCardId());
	toBean.setCardType(fromBean.getCardType());
	toBean.setCreateBy(fromBean.getCreateBy());
	toBean.setCreateTime(fromBean.getCreateTime());
	toBean.setCrmSendStatus(fromBean.getCrmSendStatus());
	toBean.setCurrentUser(fromBean.getCurrentUser());
	toBean.setCusSource(fromBean.getCusSource());
	toBean.setDelFlag(fromBean.getDelFlag());
	toBean.setEmpCode(fromBean.getEmpCode());
	toBean.setFaileName(fromBean.getFaileName());
	toBean.setICEAddress(fromBean.getICEAddress());
	toBean.setICEArea(fromBean.getICEArea());
	toBean.setICECardId(fromBean.getICECardId());
	toBean.setICECardType(fromBean.getICECardType());
	toBean.setICEEmail(fromBean.getICEEmail());
	toBean.setICEName(fromBean.getICEName());
	toBean.setICEPhone(fromBean.getICEPhone());
	toBean.setICERelation(fromBean.getICERelation());
	toBean.setId(fromBean.getId());
	toBean.setIsNewRecord(fromBean.getIsNewRecord());
	toBean.setLogName(fromBean.getLogName());
	toBean.setMail(fromBean.getMail());
	toBean.setModifyBy(fromBean.getModifyBy());
	toBean.setModifyTime(fromBean.getModifyTime());
	toBean.setNewPhone(fromBean.getNewPhone());
	toBean.setOldPhone(fromBean.getOldPhone());
	toBean.setOperation(fromBean.getOperation());
	toBean.setOrderBy(fromBean.getOrderBy());
	toBean.setOsId(fromBean.getOsId());
	toBean.setOsType(fromBean.getOsType());
	toBean.setPage(fromBean.getPage());
	toBean.setRemarks(fromBean.getRemarks());
	toBean.setSendTime(fromBean.getSendTime());
	toBean.setSendType(fromBean.getSendType());
	toBean.setSex(fromBean.getSex());
	toBean.setSendStatus(fromBean.getSendStatus());
	toBean.setUniqueNum(fromBean.getUniqueNum());
	toBean.setUserNumber(fromBean.getUserNumber());
	toBean.setUserName(fromBean.getUserName());
	toBean.setVerTime(fromBean.getVerTime());
}
}
