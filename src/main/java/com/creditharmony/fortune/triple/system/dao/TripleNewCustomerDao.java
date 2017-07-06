
package com.creditharmony.fortune.triple.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.CustName;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntLogBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 客户注册咨询
 * 
 * @Class Name TripleNewCustomerDao
 * @author 胡体勇
 * @Create In 2016年1月23日
 */
@FortuneBatisDao
public interface TripleNewCustomerDao {

	/**
	 * 根据客户手机号查询客户 2016年1月25日 By 胡体勇
	 * 
	 * @param customer
	 */
	public List<IntPhone> searchCustomerByPhoneNum(IntPhone intPhone);

	/**
	 * 根据证件号查询客户 2016年1月25日 By 胡体勇
	 * 
	 * @param customer
	 */
	public List<IntCard> searchCustomerByCertNum(IntCard IntCard);

	/**
	 * 查询各系统推送过来的理财经理是否是信合内部理财经理 2016年1月27日 By 胡体勇
	 * 
	 * @param userInfo
	 */
	public List<IntPhone> checkManager(IntPhone intPhone);

	/**
	 * 理财经理离职判断理财经理 2016年5月30日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public List<IntPhone> checkStopManager(IntPhone intPhone);

	/**
	 * 向三网客户表插入数据 2016年2月18日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int insertIntPhone(IntPhone intPhone);

	/**
	 * 根据手机号修改对应理财经理 2016年2月18日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int updatePhone(IntPhone intPhone);

	/**
	 * 根据手机号修改对应的理财经理 2016年2月19日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int updateEmpCodeByPhone(IntPhone intPhone);

	/**
	 * 根据手机号和系统类型修改手机号对应的理财经理 2016年3月24日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int updateEmpCodeByOsTypePhone(IntPhone intPhone);

	/**
	 * 根据证件类型和证件号修改对应理财经理 2016年2月19日 By 胡体勇
	 * 
	 * @param intCard
	 * @return
	 */
	public int updateEmpCodeByCertNum(IntCard intCard);

	/**
	 * 插入数据到证件号表 2016年2月19日 By 胡体勇
	 * 
	 * @param intCard
	 * @return
	 */
	public int insertIntCard(IntCard intCard);

	/**
	 * 插入中间表数据 2016年2月19日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public int insertIntPhoneCard(IntPhoneCard intPhoneCard);

	/**
	 * 根据证件号查询对应的手机号 及根据手机号查询对应的证件号 2016年2月20日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public List<IntPhoneCard> findPhoneByCertNum(IntPhoneCard intPhoneCard);

	/**
	 * 修改手机号 2016年2月20日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int updatePhoneCardByPhone(IntPhone intPhone);

	/**
	 * 根据旧手机号修改手机号和理财经理 2016年2月26日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public int updateOldPhone(IntPhone intPhone);

	/**
	 * 查询是否是chp的手机号 2016年3月2日 By 胡体勇
	 * 
	 * @param intDeliveryEx
	 * @return
	 */
	public List<IntPhone> checkIsChpPhone(IntDeliveryEx intDeliveryEx);

	/**
	 * 备份客户信息发送 2016年3月3日 By 胡体勇
	 * 
	 * @param intCustomerBean
	 * @return
	 */
	public int insertTripleCustomer(IntCustomerBean intCustomerBean);

	/**
	 * 插入三网日志表 2016年3月3日 By 胡体勇
	 * 
	 * @param intLogBean
	 * @return
	 */
	public int insertTripleLog(IntLogBean intLogBean);

	/**
	 * 根据证件号查询证件号下的手机号对应的信息 2016年3月8日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public List<IntDeliveryEx> findCertNumPhoneList(IntPhoneCard intPhoneCard);

	/**
	 * 根据手机号查询相应客户在三网中所有信息 2016年3月12日 By 胡体勇
	 * 
	 * @param intDeliveryEx
	 * @return
	 */
	public List<IntDeliveryEx> findAllInfoByPhoneList(IntDeliveryEx intDeliveryEx);

	/**
	 * 根据三网消息反馈更改消息状态 2016年3月17日 By 胡体勇
	 * 
	 * @param bean
	 * @return
	 */
	public int updateSendStatus(IntCustomerBean bean);
	
	/**
	 * 根据三网消息反馈批量更改消息状态为成功 2016年3月17日 By 胡体勇
	 * 
	 * @param bean
	 * @return
	 */
	public int updateSendStatusSucBatch(List beans);
	/**
	 * 根据三网消息反馈批量更改消息状态为失败 2016年3月17日 By 胡体勇
	 * 
	 * @param bean
	 * @return
	 */
	public int updateSendStatusFailBatch(List beans);

	/**
	 * 根据理财经理id查询编号 2016年4月5日 By 胡体勇
	 * 
	 * @param intPhone
	 * @return
	 */
	public IntPhone findEmpCodeById(IntPhone intPhone);

	/**
	 * 分组时根据证件号查询对应的手机号 2016年5月8日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public List<IntPhoneCard> findPhoneForGroup(IntPhoneCard intPhoneCard);


	/**
	 * 分组时根据手机号查询对应的证件号 2016年5月8日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public List<IntPhoneCard> findCardForGroup(IntPhoneCard intPhoneCard);

	/**
	 * 查询创建时间最早理财经理 2016年6月10日 By 胡体勇
	 * 
	 * @param phone
	 * @return
	 */
	public IntPhone findManagerByCreateTime(IntPhone phone);

	/**
	 * 根据手机号查询成单时间最早 2016年6月10日 By 胡体勇
	 * 
	 * @param intDeliveryEx
	 * @return
	 */
	public IntDeliveryEx findManagerByOrderTime(IntDeliveryEx intDeliveryEx);

	/**
	 * 根据出借编号查询出借成功次数 2016年6月15日 By 胡体勇
	 * 
	 * @return
	 */
	public List<IntDeliveryEx> findLendApply(IntDeliveryEx intDeliveryEx);

	/**
	 * 解除手机号证件号关联关系 2016年6月24日 By 胡体勇
	 * 
	 * @param intPhoneCard
	 * @return
	 */
	public int deletePhoneCard(IntPhoneCard intPhoneCard);

	/**
	 * 根据唯一编号查询发送次数 2016年7月1日 By 胡体勇
	 * 
	 * @param bean
	 * @return
	 */
	public List<IntLogBean> countSendNum(IntLogBean bean);

	/**
	 * 
	 * 2016年7月5日 By 胡体勇
	 * 
	 * @param intDeliveryEx
	 * @return
	 */
	public int updateCustomerEmpByManagerId(IntDeliveryEx intDeliveryEx);

	/**
	 * 根据手机号查询EmpCode 
	 * 2016年9月12日 
	 * By 陈晓强
	 * @param ip
	 * @return
	 */
	public List<String> findEmpCodeByPhone(String phone);
	
	/**
	 * 根据同组客户的手机号码列表查询出 首单最早的 证件信息（排除理财经理为空的记录）
	 * @param pc
	 * @return
	 */
	public List<IntCard>  findCardFirstOrderTimeByPhone(IntPhoneCard pc);
	
	/**
	 * 根据手机号列表查询注册时间最早的客户 （排除理财经理为空的记录）
	 * @param pc
	 * @return
	 */
	public List<IntPhone>  findFirstRegTimeByPhone(@Param("phoneList") List phoneList  );

	public List<IntCard> findCardByPhone(IntPhoneCard pc);

	public List<IntCard> checkCardIsSingle(IntPhoneCard ipc);

	/**
	 * 根据客户姓名和证件号获取出借编号
	 * @param model
	 * @return
	 */
	public List<String> selectCustomer(CustName model);
	/**
	 * 更新chp中的手机号
	 * @param newPhone
	 * @param oldPhone
	 * @return
	 */
	public int updateChpPhone(@Param("newPhone") String newPhone ,@Param("oldPhone")String oldPhone); 
}