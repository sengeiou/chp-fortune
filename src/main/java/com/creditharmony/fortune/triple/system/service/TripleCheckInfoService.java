package com.creditharmony.fortune.triple.system.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.triple.bean.TripleFirstOrderInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 用于三网信息校验
 * @Class Name TripleCheckInfo
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@Service
public class TripleCheckInfoService {
	
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private TripleFirstOrderService tripleFirstOrderService;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	@Autowired
	private InfoGroupService infoGroupService;
	
	/**
	 * 验证手机号是否在金信或者大金融存在
	 * 2016年3月2日
	 * By 胡体勇
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone){
		boolean success = true;
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setPhone(phone);
		List<IntPhone> ip = tripleNewCustomerDao.checkIsChpPhone(ex);
		if(ArrayHelper.isNotEmpty(ip)){
			success = false;
		}
		return success;
	}
	
	/**
	 * 根据手机号查询三网客户所有信息
	 * 2016年3月3日
	 * By 胡体勇
	 * @param phone
	 * @param empCode
	 */
	public List<IntDeliveryEx> findAllInfoByPhoneList(IntPhone intPhone){
		IntDeliveryEx delivery = new IntDeliveryEx();
		delivery.setPhone(intPhone.getPhone());
		List<IntDeliveryEx> list = tripleNewCustomerDao.findAllInfoByPhoneList(delivery);
		// 排除员工编号为空的
		List<IntDeliveryEx> rtnList = new ArrayList<IntDeliveryEx>();
		List<String> empCode = new ArrayList<String>();
		for (IntDeliveryEx intDeliveryEx : list) {
			if(!empCode.contains(intDeliveryEx.getEmpCode())){
				rtnList.add(intDeliveryEx);
				empCode.add(intDeliveryEx.getEmpCode());
			}
		}
		return rtnList;
	}
	
	/**
	 * 划扣完成发送三网
	 * 2016年5月16日
	 * By 胡体勇
	 * @param customerCode
	 */
	public void sendTripleInfo(String customerCode){
	    if(StringUtils.isNotEmpty(customerCode)){
	    	String code = "";
	    	// 根据客户编号获取客户信息
			Customer customer = new Customer();
			customer.setCustCode(customerCode);
			Customer cust = customerDao.getCustomer(customer);
			// 划扣成功三网首单验证
			TripleFirstOrderInBean paramBean = new TripleFirstOrderInBean();
			paramBean.setPhone(cust.getCustMobilephone());	//客户手机号码
			paramBean.setCardType(cust.getCustCertType());	//客户证件类型
			paramBean.setCardId(cust.getCustCertNum());		//客户证件号码
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				paramBean.setFirstOrderTime(sdf.format(new Date()));
			} catch (Exception e) {
			}
			if(StringUtils.isNotEmpty(cust.getManagerCode())){	//客户理财经理员工号
				IntPhone phone = new IntPhone();
				phone.setId(cust.getManagerCode());		//理财经理ID
				IntPhone ip = tripleNewCustomerDao.findEmpCodeById(phone);	//从t_gl_user 表里根据理财经理id查出理财经理empCode
				paramBean.setEmpCode(ip.getEmpCode());
				code = ip.getEmpCode();
			}
			if(StringUtils.isNotEmpty(code)){
				if(!"600413".equals(code) && !"400800".equals(code)){
					tripleFirstOrderService.firstOrder(paramBean);		//paramBean 首单信息里有 客户手机号码、证件类型、证件号码、理财经理员工号、待首单时间（取当前系统时间）
				}
			}else{
				tripleFirstOrderService.firstOrder(paramBean);			//
			}
			
	    }
	}

	/**
	 * 判断转赠手机号对应的证件号是否成单
	 * 2016年10月18日
	 * By 陈晓强
	 */
	public List<IntCard> checkCardIsSingle(String custMobilephone) {
		if (StringUtils.isNotEmpty(custMobilephone)) {
			List<IntPhoneCard> phoneList = infoGroupService.groupByPhone(custMobilephone);
			if(ArrayHelper.isNotEmpty(phoneList)){
				List<String> phones = new ArrayList<String>();
				for (IntPhoneCard pc : phoneList) {
					phones.add(pc.getPhone());
				}
				IntPhoneCard ipc = new IntPhoneCard();
				ipc.setPhoneList(phones);
				return tripleNewCustomerDao.checkCardIsSingle(ipc);
			}
		}
		return Collections.emptyList();
	}

	/**
	 * 校验是否有审核人
	 * 2016年10月19日
	 * By 陈晓强
	 */
	public String checkHaveAuditor(CustomerManagerinfo cManagerinfo, String custCode) {
		List<String> orgs = new ArrayList<String>();
		orgs.add(cManagerinfo.getOlddepartmentId());
		// 获取是否有初审人
		List<UserRoleOrg> list = userRoleOrgDao.checkHaveAuditor(orgs, "f9a04014fd3d4feb99917e37384ba015");
		if (ArrayHelper.isEmpty(list)) {
			return "NOT_FIRST_INSTANCE";
		}
		orgs.clear();
		FortuneOrg org = RelationShipUtil.getCityOrg(cManagerinfo.getOldmanagerId());
		if (org != null) {
			orgs.add(org.getId());
		}
		FortuneOrg orgd = RelationShipUtil.getDistrictOrg(cManagerinfo.getOldmanagerId());
		if (orgd != null) {
			orgs.add(orgd.getId());
		}
		// 是否是复审人
		list = userRoleOrgDao.checkHaveAuditor(orgs, "093b79f449334036bc92f6b902efbd94");
		if (ArrayHelper.isEmpty(list)) {
			return "NOT_REVIEW";
		}
		return custCode;
	}
}
