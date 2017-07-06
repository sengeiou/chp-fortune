
package com.creditharmony.fortune.delivery.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**
 * 客户交割
 * @Class Name CustomerDeliveryDao
 * @Author hutiyong
 * @Create 2015年11月20日
 */
@FortuneBatisDao
public interface CustomerDeliveryDao extends CrudDao<DeliveryEx> {
	
	/**
	 * 分页查询
	 * 2015年12月3日
	 * By 胡体勇
	 * @param deliveryEx
	 * @param pageBounds
	 * @return
	 */
	public List<DeliveryEx> findList(DeliveryEx deliveryEx,PageBounds pageBounds);
	
	/**
	 * 写入交割数据到交割表
	 * 2015年12月2日
	 * By hutiyong
	 * @param deliveryEx
	 * @return
	 */
	public int insertDelivery(DeliveryEx deliveryEx);
	
	/**
	 * 修改客户的理财经理
	 * 2015年12月2日
	 * By hutiyong
	 * @param deliveryEx
	 * @return
	 */
	public int updateCustomer(DeliveryEx deliveryEx);
	
	/**
	 * 在插入待交割数据时修改客户的交割标识
	 * 2016年1月7日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
	public int updateCustomerIsDelivery(DeliveryEx deliveryEx);
	 
	/**
	 * 根据理财经理工号查询团队经理及营业部信息
	 * 2015年12月2日
	 * By hutiyong
	 * @param fmanagerCode
	 * @return
	 */
	public List<DeliveryEx> findInfoByCode(Map<String,Object> map);
	
	/**
	 * 修改交割信息
	 * 2015年12月7日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
    public int updateDelivery(DeliveryEx deliveryEx);
    
    /**
     * 导入交割信息表时查询对应的客户编号下的信息是否重复
     * 2016年1月11日
     * By 胡体勇
     * @param customerCode
     * @return
     */
    public int countCustomer(String customerCode);
    
    /**
     * 交割提醒列表
     * 2016年1月20日
     * By 胡体勇
     * @param deliveryEx
     * @param pageBounds
     * @return
     */
    public List<DeliveryEx> deliveryRemindList(DeliveryEx deliveryEx,PageBounds pageBounds);
}
