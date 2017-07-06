
package com.creditharmony.fortune.delivery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网客户交割
 * @Class Name TripleCustomerDeliveryDao
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@FortuneBatisDao
public interface TripleCustomerDeliveryDao extends CrudDao<IntDeliveryEx>{
	
	/**
	 * 三网客户交割列表
	 * 2016年2月22日
	 * By 胡体勇
	 * @return
	 */
    public List<IntDeliveryEx> findList(IntDeliveryEx intDeliveryEx,PageBounds pageBounds);
    /**
     * 根据客户姓名查询手机号
     * 2016年3月30日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findPhoneListByName(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据手机号查询id
     * 2016年3月30日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findIdByPhone(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据id修改对应理财经理
     * 2016年3月30日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public int updateEmpCodeById(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据理财经理工号查询理财经理信息
     * 2016年2月23日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findEmpInfoByCode(Map<String,Object> map);
    
    /**
     * 查询离职理财经理信息
     * 2016年5月30日
     * By 胡体勇
     * @param map
     * @return
     */
    public List<IntDeliveryEx> findStopEmpInfoByCode(Map<String,Object> map);
    /**
     * 插入交割表信息
     * 2016年2月23日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public int insertIntDelivery(IntDeliveryEx intDeliveryEx);
    
    /**
     * 修改手机号对应的理财经理
     * 2016年2月25日
     * By 胡体勇
     * @param intPhone
     * @return
     */
    public int updatePhoneEmpCode(IntDeliveryEx intDeliveryEx);
    
    /**
     * 修改证件号对应的理财经理
     * 2016年2月25日
     * By 胡体勇
     * @param intCard
     * @return
     */
    public int updateCardEmpCode(IntDeliveryEx intDeliveryEx);
    
    /**
     * 查询理财经理对应的客户
     * 2016年2月25日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findManagerCustomerList(IntDeliveryEx intDeliveryEx);
    
    /**
     * 查询理财经理对应的业绩
     * 2016年2月25日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> tripleManagerAchievementList(IntDeliveryEx intDeliveryEx);
    
    /**
     * 修改理财经理对应的出借 
     * 2016年2月25日
     * By 胡体勇
     * @return
     */
    public int updateTripleLendManager(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据客户编号查询对应三网客户信息 
     * 2016年3月1日
     * By 胡体勇
     * @param intPhone
     */
    public IntPhone findInfoByCustCode(IntPhone intPhone);
    
    /**
     * 查询三网客户对应信息list
     * 2016年3月1日
     * By 胡体勇
     * @param intPhone
     * @return
     */
    public List<IntPhone> findPhoneList(IntPhone intPhone);
    
    /**
     * 根据手机号和系统类型查询是否成单
     * 2016年3月1日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findOrderStatusList(IntDeliveryEx intDeliveryEx);
    
    /**
     * 修改chp客户表客户对应的理财经理
     * 2016年3月9日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public int updateCustomerEmp(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据手机号查询chp是否存在
     * 2016年3月17日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findChpPhone(IntDeliveryEx intDeliveryEx);
    
    /**
     * 理财经理交割时修改客户对应的理财经理
     * 2016年4月28日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public int updateEmpCode(IntDeliveryEx intDeliveryEx);
    
    /**
     * 根据理财经理查询需要交割的客户
     * 2016年5月3日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> searchAutoDeliveryCustomer(IntDeliveryEx intDeliveryEx);
    
    /**
     * 查询用户的组织机构
     * 2016年5月12日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public IntDeliveryEx findOrgName(IntDeliveryEx intDeliveryEx);

	public List<IntDeliveryEx> findDeliveryByPhone(@Param("phoneList") List<IntPhoneCard> phoneList, @Param("empCodes") String[] empCodes);
}
