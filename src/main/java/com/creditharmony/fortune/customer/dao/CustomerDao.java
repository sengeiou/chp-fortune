package com.creditharmony.fortune.customer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.remind.entity.ext.CustomerBirthdayEx;

/**
 * 客户操作类
 * 
 * @Class Name CustomerDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface CustomerDao extends CrudDao<Customer> {

	/**
	 * 根据属性获取实体对象 2015年12月2日 By 孙凯文
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomer(Customer customer);

	/**
	 * 分页查询数据列表 2015年12月2日 By 孙凯文
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> findInvestList(Customer customer,
			PageBounds pageBounds);

	public List<Customer> findInvestList(Customer customer);

	/**
	 * 2015年12月2日 By 韩龙
	 * 
	 * @param customerBirthdayEx
	 * @return
	 */
	public List<CustomerBirthdayEx> findList(
			CustomerBirthdayEx customerBirthdayEx);

	/**
	 * 根据客户手机号码校验客户信息进行抢单 2015年12月25日 By 刘雄武
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerbyphone(Customer customer);

	/**
	 * 根据客户Code获取客户信息 2015年12月25日 By 刘雄武
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomerInfo(Customer customer);

	/**
	 * 根据客户Code获取客户信息2 2015年12月25日 By 刘雄武
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomerbyCode(Customer customer);
	/**
	 * 根据客户Code获取客户信息For Update
	 * 2016年5月21日
	 * By 朱杰
	 * @param customer
	 * @return
	 */
	public Customer getCustomerbyCodeForUpdate(Customer customer);	

	/**
	 * 抢单成功后根据客户编号更新客户理财经理 2015年12月25日 By 刘雄武
	 * 
	 * @param customer
	 */
	public void updateManage(Customer customer);

	/**
	 * 更新客户出借状态 2016年2月22日 By 陈广鹏
	 * 
	 * @param customer
	 */
	public void updateApplyLending(Customer customer);

	/**
	 * 更新金账户 2016年2月29日 By 朱杰
	 * 
	 * @param customer
	 */
	public int updateTrusteeship(Customer customer);

	/**
	 * 2016年3月1日
	 * 
	 * @param param
	 */
	public List<Customer> queryCustomer(Map<?, ?> param);

	public void updateTrusteeAccountId(@Param("code") String customerCode,
			@Param("accountId") String accountId);
	
//	public void insertAgreementLib(HashMap<String, Object> map);
	
	/**
	 * 查身份证号出现的次数
	 * 2016年3月21日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	public int getCustomerCertNumCnt(Customer customer);
	
	/**
	 * 查询客户信息，用于出借申请查询列表
	 * 2016年4月26日
	 * By 肖长伟
	 * @param customer
	 * @param pageBounds
	 * @return
	 */
	public PageList<Customer> getCustomer4LendApply(Map<String, Object> params, PageBounds pageBounds);
	
	/**
	 * 客户表锁行用
	 * 2016年4月28日
	 * By 朱杰
	 * @param customer
	 * @return
	 */
	public Customer forUpdate(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	/**
	 * 根据code查询客户信息
	 * @param customer
	 * @return
	 */
	public List<Customer> getCusByCode(Customer customer);
	
	/**应对电销我的客户列表
	 * 
	 * gaoxu
	 * 
	 * **/
	public PageList<Customer> findInvestElectricList(Customer customer, PageBounds pageBounds);
	
}