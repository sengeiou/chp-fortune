package com.creditharmony.fortune.callcenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.service.csh.bean.Csh_CallOnCustomerByCFOutBean;
import com.creditharmony.adapter.service.csh.bean.Csh_SaveCustomerByCFInBean;
import com.creditharmony.adapter.service.csh.bean.Csh_SearchCustomerDeatilByCFOutBean;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.fortune.type.CallCenterStatus;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.fortune.callcenter.dao.CallCenterDao;
import com.creditharmony.fortune.callcenter.entity.CallCenterCustomer;

/**
 * 呼叫中心接口 manager
 * @Class Name CallCenter4WSManager
 * @author 肖长伟
 * @Create In 2016年3月3日
 */
@Service
public class CallCenter4WSManager {
	@Autowired
	private CallCenterDao callCenterDao;

	
	
	/**
	 * 呼叫中心添加客户
	 * 2016年6月3日
	 * By 张鸿飞
	 * @param customer
	 * @return
	 */
	public void addCustomerInfo(Csh_SaveCustomerByCFInBean customer) {
		
		if(null != customer){
			CallCenterCustomer newCustomer = new CallCenterCustomer();
			newCustomer.setId(IdGen.uuid());
			newCustomer.setCustomerName(customer.getCustomerName());
			newCustomer.setDictCustomerSex(customer.getCustomerSex());
			newCustomer.setCustomerMobilephone(customer.getCustomerMobilePhone());
			newCustomer.setCustomerTel(customer.getCustomerPhone());
			newCustomer.setProvince(customer.getCustomerLiveProvince());
			newCustomer.setCity(customer.getCustomerLiveCity());
			newCustomer.setStatus(CallCenterStatus.IN_CITY.id);
			newCustomer.setCreateTime(new Date());
			newCustomer.setModifyTime(new Date());
			callCenterDao.addCustomerInfo4CallCenter(newCustomer);
		}
		
		
	}
	
	/**
	 * 呼叫中心查询客户
	 * 2016年3月3日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	public List<Csh_SearchCustomerDeatilByCFOutBean> searchCustomerInfor(Map<String, Object> params) {
		//判断是否传参数，若参数为null，则返回空list
		boolean hasParamFlag = false;
		for(Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			if(params.get(key) != null) {
				hasParamFlag = true;
				break;
			}
		}
		if (hasParamFlag == false) { //没有传递参数, 直接返回空
			return new ArrayList<Csh_SearchCustomerDeatilByCFOutBean>();
		}
		List<Csh_SearchCustomerDeatilByCFOutBean> rsList = callCenterDao.getCustomerInfo4CallCenter(params);
		return rsList;
	}

	/**
	 * 回访信息查询
	 * 2016年3月3日
	 * By 肖长伟
	 * @param params    时间格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<Csh_CallOnCustomerByCFOutBean> feedBackCustomerInfo(Map<String, Object> params) {
		params.put("lendStatus", LendState.SPTG.value);
		List<Map<String, Object>> rsList = callCenterDao.getFeedBackList(params);
		List<Csh_CallOnCustomerByCFOutBean> list = getList(rsList);
		return list;
	}

	/**
	 * 将Map转化成 Csh_CallOnCustomerByCFOutBean
	 * 2016年3月3日
	 * By 肖长伟
	 * @param rsList
	 * @return
	 */
	private List<Csh_CallOnCustomerByCFOutBean> getList(	List<Map<String, Object>> rsList) {
		List<Csh_CallOnCustomerByCFOutBean> list = new ArrayList<Csh_CallOnCustomerByCFOutBean>();
		if (rsList != null) {
			for (Map<String, Object> item : rsList) {
				Csh_CallOnCustomerByCFOutBean bean = new Csh_CallOnCustomerByCFOutBean();
				bean.setCustomerName(String.valueOf(item.get("customer_name")));
				bean.setCustomerSex(String.valueOf(item.get("sex_name")));
				bean.setCustomerCertNum(String.valueOf(item.get("customer_cert_num")));
				bean.setCustomerEmail(String.valueOf(item.get("customer_email")));
				bean.setCustomerPhone(String.valueOf(item.get("customer_mobilephone")));
				bean.setProductName(String.valueOf(item.get("product_name")));
				bean.setApplyLendMoney(String.valueOf(item.get("apply_lend_money")));
				bean.setApplyDeductDay(String.valueOf(item.get("apply_deduct_day")));
				bean.setApplyLendDay(String.valueOf(item.get("apply_lend_day")));
				bean.setActualDay(String.valueOf(item.get("apply_expire_day")));
				bean.setManagerName(String.valueOf(item.get("manager_name")));
				bean.setTeamManagerName(String.valueOf(item.get("team_user_name")));
				bean.setStoresName(String.valueOf(item.get("store_name")));
				list.add(bean);
			}
		}
		return list;
	}
	
}
