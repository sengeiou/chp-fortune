package com.creditharmony.fortune.delivery.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**业绩交割
 * @Class Name AchievementDeliveryDao
 * @author hutiyong
 * @Create In 2015年12月1日
 */
@FortuneBatisDao
public interface AchievementDeliveryDao extends CrudDao<DeliveryEx> {
	
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
	 * 修改出借客户出借申请表对应的理财经理信息
	 * 2015年12月8日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
    public int updateLendApply(DeliveryEx deliveryEx);
    
    /**
     * 根据用户id查询用户组织机构信息
     * 2016年1月7日
     * By 胡体勇
     * @param id
     * @return
     */
    public DeliveryEx findInfoById(Map<String,Object> map);
    
    /**
     * 统计页面显示总金额
     * 2016年1月18日
     * By 胡体勇
     * @param deliveryEx
     * @return
     */
    public DeliveryEx countMoney(DeliveryEx deliveryEx);
}
