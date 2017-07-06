/**
 * 
 */
package com.creditharmony.fortune.delivery.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryDao;
import com.creditharmony.fortune.delivery.dao.ManagerDeliveryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**
 * 理财经理交割
 * @Class Name ManagerDeliveryManager
 * @author 胡体勇
 * @Create In 2015年12月2日
 */
@Service
public class ManagerDeliveryManager extends CoreManager<ManagerDeliveryDao,DeliveryEx>{
	
	@Autowired
	private CustomerDeliveryDao customerDelivryDao;
	
	/**
	 * 理财经理交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param fManagerCode
	 * @param nfManagerCode
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int managerDelivery(Map<String,String> map){
		int result = 0;
		DeliveryEx deliveryEx = new DeliveryEx();
		// 设置客户交割信息
		deliveryEx.setfManagerCode(map.get("fManagerCode"));
		deliveryEx.setNfManagerCode(map.get("nfManagerCode"));
		//deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_CUSTOMERDELIVERY);
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		Timestamp time = Timestamp.valueOf(date); // 获取当前时间，并转为timestamp格式
		deliveryEx.setDelDate(time);
		//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		deliveryEx.setCreateBy(UserUtils.getUserId());
		deliveryEx.setCreateTime(time);
		deliveryEx.setModifyBy(UserUtils.getUserId());
		deliveryEx.setModifyTime(time);
		List<DeliveryEx> list = super.dao.getCustListByManagerCode(deliveryEx);// 查询理财经理下的所有客户
		result = super.dao.updateCustomerManager(deliveryEx);
		for(int i = 0;i < list.size();i++){
			deliveryEx.setDelId(IdGen.uuid());
			//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
			deliveryEx.setCustCode(list.get(i).getCustCode());
			customerDelivryDao.insertDelivery(deliveryEx);
		}
		
		return result;
	}
	
	/**
	 * 理财经理业绩交割
	 * 2016年1月19日
	 * By 胡体勇
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int mangerAchievementDelivery(DeliveryEx deliveryEx){
		int result = 0;
		// 设置客户交割信息
		deliveryEx.setfManagerCode(deliveryEx.getfManagerCode());
		deliveryEx.setNfManagerCode(deliveryEx.getNfManagerCode());
		//deliveryEx.setDelType(Constant.DELIVERY_DELTYPE_ACHIEVEMENTDELIVERY);
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		Timestamp time = Timestamp.valueOf(date); // 获取当前时间，并转为timestamp格式
		deliveryEx.setDelDate(time);
		//deliveryEx.setIsDelivery(Constant.DELIVERY_DELSTATUS_DELIVERYED);
		deliveryEx.setCreateBy(UserUtils.getUserId());
		deliveryEx.setCreateTime(time);
		deliveryEx.setModifyBy(UserUtils.getUserId());
		deliveryEx.setModifyTime(time);
		List<DeliveryEx> list = super.dao.managerAchievementList(deliveryEx);
		result = super.dao.updateLendManager(deliveryEx);
		for(int i = 0;i < list.size();i++){
			deliveryEx.setDelId(IdGen.uuid());
			//deliveryEx.setDictDelStatus(Constant.DELIVERY_DELSTATUS_DELIVERYED);
			deliveryEx.setCustCode(list.get(i).getCustCode());
			deliveryEx.setLendCode(list.get(i).getLendCode());
			customerDelivryDao.insertDelivery(deliveryEx);
		}
		return result;
	}
}
