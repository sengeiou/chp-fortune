/**
 * 
 */
package com.creditharmony.fortune.delivery.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**
 * 理财经理交割
 * @Class Name ManagerDeliveryDao
 * @author hutiyong
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface ManagerDeliveryDao extends CrudDao<DeliveryEx> {
	
	/**
	 * 查询理财经理对应的客户
	 * 2015年12月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
	public List<DeliveryEx> getCustListByManagerCode(DeliveryEx deliveryEx);
	
	/**
	 * 理财经理交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
    public int updateCustomerManager(DeliveryEx deliveryEx);
    
    /**
     * 查询理财经理对应的业绩
     * 2016年1月19日
     * By 胡体勇
     * @param deliveryEx
     * @return
     */
    public List<DeliveryEx> managerAchievementList(DeliveryEx deliveryEx);
    
    /**
     * 修改理财经理对应的出借 
     * 2016年1月19日
     * By 胡体勇
     * @param deliveryEx
     * @return
     */
    public int updateLendManager(DeliveryEx deliveryEx);
}
