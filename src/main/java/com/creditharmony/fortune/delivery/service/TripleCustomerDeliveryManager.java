
package com.creditharmony.fortune.delivery.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.fortune.type.OwnershipChange;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.dao.CustomerDeliveryDao;
import com.creditharmony.fortune.delivery.dao.TripleAchievementDeliveryDao;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveFlowManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.InfoGroupService;
import com.creditharmony.fortune.utils.SerialNum;
/**
 * 三网客户交割
 * @Class Name TripleCustomerDeliveryManager
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@Service
public class TripleCustomerDeliveryManager extends CoreManager<TripleCustomerDeliveryDao,IntDeliveryEx>{
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleCustomerDeliveryManager.class);
    @Autowired
	private  TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private CustomerDeliveryDao customerDeliveryDao;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleCustomerDeliveryDao tripleCustomerDeliveryDao;
	@Autowired
	private TripleAchievementDeliveryDao tripleAchievementDeliveryDao;
	@Autowired
	private DonationApproveFlowManager donationApproveFlowManager;
	@Autowired
	private CustomerDao customerDao;


	private ExecutorService executor = Executors.newFixedThreadPool(10);// 给定线程池数量
	private static final int MAX_DEAL = 100;// 对多数据进行分组，100条一组，一组使用一个线程进行执行

	/**
	 * 分页查询
	 * 2015年12月2日
	 * By 胡体勇
	 * @param page
	 * @param intDeliveryEx
	 * @return
	 */
	public Page<IntDeliveryEx> findPage(Page<IntDeliveryEx> page,IntDeliveryEx intDeliveryEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		
		if(!StringUtils.isEmpty(intDeliveryEx.getOrgId())){
			String[] ids = intDeliveryEx.getOrgId().split(",");
			intDeliveryEx.setOrgIdList(Arrays.asList(ids));
		}
		if (!StringUtils.isEmpty(intDeliveryEx.getOsType())) {
			String[] types = intDeliveryEx.getOsType().split(",");
			intDeliveryEx.setOsTypeList(Arrays.asList(types));
		}
		
	    List<String> phoneList = new ArrayList<String>();

		if(StringUtils.isNotEmpty(intDeliveryEx.getLoginName()) && StringUtils.isEmpty(intDeliveryEx.getCustomerCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpName())){
			// 根据客户姓名查询同组手机号
			List<IntDeliveryEx> list = super.dao.findPhoneListByName(intDeliveryEx);
			if(list.size() > 0){
				for(int i = 0;i<list.size();i++){
					List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(list.get(i).getPhone());
					if(listPhone.size() > 0){
						for(int j = 0;j<listPhone.size();j++){
							phoneList.add(listPhone.get(j).getPhone());
						}
					}else{
						phoneList.add(list.get(0).getPhone());
					}
				}
			}else{
				phoneList.add("0000000000000");
			}
			intDeliveryEx.setLoginName("");
		}
		// 根据客户编号查询同组手机号
		if(StringUtils.isNotEmpty(intDeliveryEx.getCustomerCode()) && StringUtils.isEmpty(intDeliveryEx.getLoginName()) && StringUtils.isEmpty(intDeliveryEx.getEmpCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpName())){
			IntPhone phone = new IntPhone();
			phone.setCustomerCode(intDeliveryEx.getCustomerCode());
			IntPhone ip = tripleCustomerDeliveryDao.findInfoByCustCode(phone);
			if(ip != null){
				List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(ip.getPhone());
				if(listPhone.size() > 0){
					for(int j = 0;j<listPhone.size();j++){
						phoneList.add(listPhone.get(j).getPhone());
					}
				}else{
					phoneList.add(ip.getPhone());
				}
				
			}else{
				phoneList.add("0000000000000");
			}
			intDeliveryEx.setCustomerCode("");
		}
		
		if(phoneList.size() >0){
			// 去除手机号重复
			for(int i = 0; i < phoneList.size()-1; i++) {
				for (int j = phoneList.size()-1; j > i; j--) {
					if (phoneList.get(j).equals(phoneList.get(i))) {
						phoneList.remove(j);
					}
				}
			}  
			intDeliveryEx.setPhoneList(phoneList);
		}
		
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) super.dao.findList(intDeliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	 /**
     * 根据理财经理工号查询理财经理信息
     * 2016年2月23日
     * By 胡体勇
     * @param intDeliveryEx
     * @return
     */
    public List<IntDeliveryEx> findEmpInfoByCode(String code){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code",code);
		map.put("orgType",FortuneOrgType.STORE.key);
		map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
		map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
		return super.dao.findEmpInfoByCode(map);
    }
    
    /**
     * 查询离职理财经理信息
     * 2016年5月30日
     * By 胡体勇
     * @return
     */
    public List<IntDeliveryEx> findStopEmpInfoByCode(String code){
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("code",code);
		map.put("orgType",FortuneOrgType.STORE.key);
		map.put("managerRole", FortuneRole.FINANCING_MANAGER.id);
		map.put("teamManagerRole", FortuneRole.TEAM_MANAGER.id);
		return super.dao.findStopEmpInfoByCode(map);
    }
	/**
	 * 导出三网交割列表
	 * 2016年2月27日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public IntDeliveryEx outExcel(IntDeliveryEx intDeliveryEx){
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		
		if(!StringUtils.isEmpty(intDeliveryEx.getOrgId())){
			String[] ids = intDeliveryEx.getOrgId().split(",");
			intDeliveryEx.setOrgIdList(Arrays.asList(ids));
		}
		
		List<String> phoneList = new ArrayList<String>();

		if(StringUtils.isNotEmpty(intDeliveryEx.getLoginName()) && StringUtils.isEmpty(intDeliveryEx.getCustomerCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpName())){
			// 根据客户姓名查询同组手机号
			List<IntDeliveryEx> list = super.dao.findPhoneListByName(intDeliveryEx);
			if(list.size() > 0){
				for(int i = 0;i<list.size();i++){
					List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(list.get(i).getPhone());
					if(listPhone.size() > 0){
						for(int j = 0;j<listPhone.size();j++){
							phoneList.add(listPhone.get(j).getPhone());
						}
					}else{
						phoneList.add(list.get(0).getPhone());
					}
				}
			}else{
				phoneList.add("0000000000000");
			}
			intDeliveryEx.setLoginName("");
		}
		// 根据客户编号查询同组手机号
		if(StringUtils.isNotEmpty(intDeliveryEx.getCustomerCode()) && StringUtils.isEmpty(intDeliveryEx.getLoginName()) && StringUtils.isEmpty(intDeliveryEx.getEmpCode()) && StringUtils.isEmpty(intDeliveryEx.getEmpName())){
			IntPhone phone = new IntPhone();
			phone.setCustomerCode(intDeliveryEx.getCustomerCode());
			IntPhone ip = tripleCustomerDeliveryDao.findInfoByCustCode(phone);
			if(ip != null){
				List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(ip.getPhone());
				if(listPhone.size() > 0){
					for(int j = 0;j<listPhone.size();j++){
						phoneList.add(listPhone.get(j).getPhone());
					}
				}else{
					phoneList.add(ip.getPhone());
				}
			}else{
				phoneList.add("0000000000000");
			}
			intDeliveryEx.setCustomerCode("");
		}
		
		if(phoneList.size() >0){
			// 去除手机号重复
			for(int i = 0; i < phoneList.size()-1; i++) {
				for (int j = phoneList.size()-1; j > i; j--) {
					if (phoneList.get(j).equals(phoneList.get(i))) {
						phoneList.remove(j);
					}
				}
			}  
			intDeliveryEx.setPhoneList(phoneList);
		}
		
		return intDeliveryEx;
	}
	
	/**
	 * 插入三网错误信息
	 * 2016年3月1日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int insertErrorInfo(IntDeliveryEx intDeliveryEx){
		intDeliveryEx.setId(IdGen.uuid());
		intDeliveryEx.setDeliveryType(Constant.DELIVERY_TYPE_ERROR);
		intDeliveryEx.setCreateTime(new Date());
		intDeliveryEx.setOperator(UserUtils.getUserId());
		return super.dao.insertIntDelivery(intDeliveryEx);
	}
	
	/**
	 * 推送三网消息
	 * 2016年3月3日
	 * By 胡体勇
	 * @param phone
	 * @param empCode
	 * @param cardType
	 * @param cardId
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void sendInfo(String phone,String empCode,String cardType,String cardId,String osId,String osType,String userName){
		try{
	        // 设置插入备份表数据
	        IntCustomerBean cust = new IntCustomerBean();
	        cust.setUniqueNum(SerialNum.getSerialNum());
		    cust.setSendStatus(Constant.SEND_STATUS_FAIL);
	        cust.setOsId(osId);
	        cust.setOsType(osType);
	        cust.setNewPhone(phone);
	        cust.setEmpCode(empCode);
	        cust.setCardType(cardType);
	        cust.setCardId(cardId);
	        cust.setLogName(userName);
	        cust.setUserName(userName);
	        cust.setCreateTime(new Date());
	        cust.setSendTime(new Date());
	        cust.setSendType("A014");
	        cust.setOperation("FBG");
			insertTripleCustomer(cust);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("三网客户交割发送客户信息异常！");
			throw new ServiceException("sendInfo exception");
		}
	}
	 
	private synchronized void insertTripleCustomer(IntCustomerBean cust) {
		cust.setId(IdGen.uuid());
		tripleNewCustomerDao.insertTripleCustomer(cust);
	}

	/**
	 * 修改chp对应的理财经理
	 * 2016年3月17日
	 * By 胡体勇
	 * @param phone
	 * @param osType
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void updateChpEmpCode(String phone,String newEmpCode,String osType){
		if(osType.equals(OsType.CHP.value)){
			IntDeliveryEx d = new IntDeliveryEx();
			d.setPhone(phone);
			List<IntDeliveryEx> ex = super.dao.findChpPhone(d);
			if(ex.size() > 0&&StringUtils.isNotEmpty(newEmpCode)){
			    try {
			    	// 查询新理财经理对应信息
					List<IntDeliveryEx> newEmpInfo = this.findEmpInfoByCode(newEmpCode);
					// 新理财经理id
					d.setNewEmpCode(newEmpInfo.get(0).getNewEmpId());
					// 新团队id
					d.setNewTeamId(newEmpInfo.get(0).getNewTeamId());
					d.setTeamId(newEmpInfo.get(0).getNewTeamId());
					// 新团队经理id
					d.setNewTeamManagerId(newEmpInfo.get(0).getNewTeamManagerId());
					d.setNewTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerId());
					// 营业部经理
					List<String> strList2 = new ArrayList<String>();
					strList2.add(FortuneRole.STORE_MANAGER.id);
					 List<FortuneUser> listUser2 = RelationShipUtil.getOrgMember(newEmpInfo.get(0).getNewOrgId(),strList2,"1");
					 if(listUser2.size() > 0){
						 // 营业部经理id
						 d.setBussManager(listUser2.get(0).getId());
					 }
					// 支公司id
					FortuneOrg org =  RelationShipUtil.getCityOrg(newEmpInfo.get(0).getNewEmpId());
					if(org != null){
						d.setCityOrg(org.getId());
						List<String> strList = new ArrayList<String>();
						strList.add(FortuneRole.CITY_MANAGER.id);
						 List<FortuneUser> listUser = RelationShipUtil.getOrgMember(org.getId(),strList,"1");
						 if(listUser.size() > 0){
							 // 支公司经理id
							 d.setCityManager(listUser.get(0).getId());
						 }
					}
					// 新营业部id
					d.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
				    d.setModifyBy(UserUtils.getUserId());
				    d.setModifyTime(new Date());
			    	// 修改客户对应的理财经理
			    	 this.updateCustomerEmpDao(d);
			    	 
			    	 for(int i = 0;i<ex.size();i++){
			    		 // 修改出借对应的理财经理
			    		 d.setCustomerCode(ex.get(i).getCustomerCode());
			    		 updateTripleLendManagerDao(d);
					}
				} catch (Exception e) {
					this.logger.error("修改chp客户手机号为："+phone+"的理财经理为："+newEmpCode+"异常！", e);
				}
			   
			}
		}
	}
	
	/**
	 * 修改理财经理
	 * 2016年4月19日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmpCodeByIdDao(IntDeliveryEx intDeliveryEx){
		return super.dao.updateEmpCodeById(intDeliveryEx);
	}
	
	/**
	 * 插入三网数据
	 * 2016年4月19日
	 * By 胡体勇
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public synchronized int insertIntDelivery(IntDeliveryEx intDeliveryEx) {
		intDeliveryEx.setId(IdGen.uuid());
		return super.dao.insertIntDelivery(intDeliveryEx);
	}
	
	/**
	 *  修改手机号对应的理财经理
	 * 2016年4月20日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updatePhoneEmpCodeDao(IntDeliveryEx intDeliveryEx){
		return super.dao.updateEmpCode(intDeliveryEx);
	}
	
	/**
	 * 修改证件号对应的理财经理
	 * 2016年4月20日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateCardEmpCodeDao(IntDeliveryEx intDeliveryEx){
		return super.dao.updateCardEmpCode(intDeliveryEx);
	}
	
	/**
	 * 修改chp客户表客户对应的理财经理
	 * 2016年4月20日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateCustomerEmpDao(IntDeliveryEx intDeliveryEx){
		return super.dao.updateCustomerEmp(intDeliveryEx);
	}
	
	/**
	 * 根据证件类型和证件号修改对应理财经理
	 * 2016年4月21日
	 * By 胡体勇
	 * @param intCard
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmpCodeByCertNumDao(IntCard intCard){
		return tripleNewCustomerDao.updateEmpCodeByCertNum(intCard);
	}
	
	/**
	 * 根据手机号修改对应的理财经理
	 * 2016年4月21日
	 * By 胡体勇
	 * @param intPhone
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmpCodeByPhoneDao(IntPhone intPhone){
		return tripleNewCustomerDao.updateEmpCodeByPhone(intPhone);
	}
	
	/**
	 * 修改出借表对应的理财经理
	 * 2016年4月28日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateTripleLendManagerDao(IntDeliveryEx intDeliveryEx){
		 return super.dao.updateTripleLendManager(intDeliveryEx);
	}
	
	/**
	 * 业绩交割时插入交割表
	 * 2016年4月28日
	 * By 胡体勇
	 * @param d
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int insertDelivery(DeliveryEx d){
		return customerDeliveryDao.insertDelivery(d);
	}
	
	/**
	 * 交割时修改对应理财经理 2016年3月30日 By 胡体勇
	 * 
	 * @param phone
	 * @param endCustomerCodeList
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int upadateEmpCodeById(String phone, String newEmpCode, String flag, String userName, List<String> endCustomerCodeList) {
		int result = 0;
		if (StringUtils.isNotEmpty(newEmpCode)) {
			List<IntDeliveryEx> newEmpInfo = this.findEmpInfoByCode(newEmpCode);
			if (ArrayHelper.isNotEmpty(newEmpInfo)) {
				List<String> strList = new ArrayList<String>();
				// 查询相同组类的客户
				List<IntPhoneCard> phoneList = infoGroupService.groupByPhone(phone);
			
				for (int j = 0; j < phoneList.size(); j++) {
					strList.add(phoneList.get(j).getPhone());
					// 设置查询参数信息
					IntDeliveryEx ex = new IntDeliveryEx();
					ex.setPhone(phoneList.get(j).getPhone());
					ex.setEmpCode(newEmpCode);
					ex.setModifyBy(UserUtils.getUserId());
					ex.setModifyTime(new Date());
					// 设置插入交割表信息
					IntDeliveryEx delivery = new IntDeliveryEx();
					// 查询新理财经理对应信息
					delivery.setNewEmpName(newEmpInfo.get(0).getNewEmpName());
					delivery.setNewTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerCode());
					delivery.setNewTeamManagerName(newEmpInfo.get(0).getNewTeamManagerName());
					delivery.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
					delivery.setNewOrgName(newEmpInfo.get(0).getNewOrgName());

					List<IntDeliveryEx> idList = super.dao.findIdByPhone(ex);
					for (int i = 0; i < idList.size(); i++) {

						ex.setId(idList.get(i).getId());
						// 修改三网手机号对应的理财经理
						updateEmpCodeByIdDao(ex);
						// 如果手机号为chp系统的手机号则修改chp客户表客户对应的理财经理
						updateChpEmpCode(idList.get(i).getPhone(), newEmpCode, idList.get(i).getOsType());
						// 查询旧理财经理对应信息
						if (!StringUtils.isEmpty(idList.get(i).getEmpCode())) {
							List<IntDeliveryEx> empInfo = findStopEmpInfoByCode(idList.get(i).getEmpCode());
							if (ArrayHelper.isNotEmpty(empInfo)) {
								delivery.setEmpName(empInfo.get(0).getNewEmpName());
								delivery.setTeamManagerCode(empInfo.get(0).getNewTeamManagerCode());
								delivery.setTeamManagerName(empInfo.get(0).getNewTeamManagerName());
								delivery.setOrgId(empInfo.get(0).getNewOrgId());
								delivery.setOrgName(empInfo.get(0).getNewOrgName());
							}
						}
						// 插入交割信息到交割表
						delivery.setId(IdGen.uuid());
						delivery.setCreateTime(new Date());
						if ("转赠".equals(flag)) {
							delivery.setOperator(flag);
							delivery.setChangeReason("转赠");
							delivery.setDeliveryType(DeliveryType.ZZ.value);
						} else {
							delivery.setOperator(userName);
							delivery.setChangeReason("手动交割");
							delivery.setDeliveryType(DeliveryType.KHJG.value);
						}
						String customerCode = idList.get(i).getCustomerCode();
						delivery.setPhone(idList.get(i).getPhone());
						delivery.setEmpCode(idList.get(i).getEmpCode());
						delivery.setNewEmpCode(newEmpCode);
						delivery.setLoginName(idList.get(i).getLoginName());
						delivery.setOsType(idList.get(i).getOsType());
						delivery.setCustomerCode(customerCode);
						insertIntDelivery(delivery);
						// 交割客户如果有转赠流程直接结束流程
						boolean checkFlag = checkCustCode(customerCode);
						if (checkFlag) {
							if (endCustomerCodeList != null) {
								endCustomerCodeList.add(customerCode);
							} else {
								donationApproveFlowManager.endAudit(customerCode);
							}
						}
						// 查询手机号对应的证件号
						IntPhoneCard ipc = new IntPhoneCard();
						ipc.setPhoneList(strList);
						// 根据手机号查询对应的证件号
						List<IntPhoneCard> cardList = tripleNewCustomerDao.findCardForGroup(ipc);
						// 修改证件号对应的理财经理
						if (cardList.size() > 0) {
							for (int n = 0; n < cardList.size(); n++) {
								if (cardList.get(n) != null) {
									IntDeliveryEx intDeliveryEx = new IntDeliveryEx();
									intDeliveryEx.setCardId(cardList.get(n).getCardId());
									intDeliveryEx.setNewEmpCode(newEmpCode);
									updateCardEmpCodeDao(intDeliveryEx);
								}
							}
						}
						// 插入待发送表
						sendInfo(idList.get(i).getPhone(), newEmpCode, "", "", idList.get(i).getOsId(), idList.get(i).getOsType(), idList.get(i).getLoginName());
					}
				}
				result++;
			}
		}
		return result;
	}

	/**
	 * 三网理财经理客户交割
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int tripleManagerCustomerDelivery(IntDeliveryEx intDeliveryEx) {
		int result = 0;
		User userInfo = UserUtils.getUser();
		// 查询理财经理对应的客户
		final List<IntDeliveryEx> phoneList = super.dao.findManagerCustomerList(intDeliveryEx);
	    if(ArrayHelper.isNotEmpty(phoneList)){
			// 修改三网客户对应理财经理
			intDeliveryEx.setModifyBy(UserUtils.getUserId());
			intDeliveryEx.setModifyTime(new Date());
			result = updatePhoneEmpCodeDao(intDeliveryEx);

			// 修改三网证件号对应理财经理
			updateCardEmpCodeDao(intDeliveryEx);

			// 查询旧理财经理对应信息
			if (!StringUtils.isEmpty(intDeliveryEx.getEmpCode())) {
				List<IntDeliveryEx> empInfo = findStopEmpInfoByCode(intDeliveryEx.getEmpCode());
				if (ArrayHelper.isNotEmpty(empInfo)) {
					intDeliveryEx.setEmpName(empInfo.get(0).getNewEmpName());
					intDeliveryEx.setTeamManagerCode(empInfo.get(0).getNewTeamManagerCode());
					intDeliveryEx.setTeamManagerName(empInfo.get(0).getNewTeamManagerName());
					intDeliveryEx.setOrgId(empInfo.get(0).getNewOrgId());
					intDeliveryEx.setOrgName(empInfo.get(0).getNewOrgName());
				}
			}
			// 查询新理财经理对应信息
			if(!StringUtils.isEmpty(intDeliveryEx.getNewEmpCode())){
				List<IntDeliveryEx> newEmpInfo = findEmpInfoByCode(intDeliveryEx.getNewEmpCode());
				if(ArrayHelper.isNotEmpty(newEmpInfo)){
					intDeliveryEx.setNewEmpName(newEmpInfo.get(0).getNewEmpName());
					intDeliveryEx.setNewTeamManagerCode(newEmpInfo.get(0).getNewTeamManagerCode());
					intDeliveryEx.setNewTeamManagerName(newEmpInfo.get(0).getNewTeamManagerName());
					intDeliveryEx.setNewOrgId(newEmpInfo.get(0).getNewOrgId());
					intDeliveryEx.setNewOrgName(newEmpInfo.get(0).getNewOrgName());
				}
			}
			// 插入交割表数据
			List<String> customerList = new ArrayList<String>();
			customerDelivery(phoneList, intDeliveryEx, userInfo, customerList);
			endAuditByCust(customerList);
	    }else{
	    	result = -1;
	    }
		return result;
	}

	/**
	 * 线程跑交割
	 * 2016年09月25日
	 * By 陈晓强
	 * @param intDeliveryEx
	 * @param customerList 
	 */
	private void customerDelivery(List<IntDeliveryEx> list, IntDeliveryEx intDeliveryEx, User userInfo, List<String> customerList) {
		// 判断数据是否为空
		if (list == null || list.isEmpty()) {
			return;
		}
		int times = (list.size() + MAX_DEAL - 1) / MAX_DEAL;
		CountDownLatch countDownLatch = new CountDownLatch(times);// 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
		try {
			for (int i = 0; i < times; i++) {
				if (i == times - 1) {
					executor.execute(new customerDeliveryRunnable(list.subList(i * MAX_DEAL, list.size()), countDownLatch,intDeliveryEx,userInfo,customerList));// 调用业务逻辑
				} else {
					executor.execute(new customerDeliveryRunnable(list.subList(i * MAX_DEAL, (i + 1) * MAX_DEAL), countDownLatch,intDeliveryEx,userInfo,customerList));
				}
			}
			countDownLatch.await();// 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行
		} catch (Exception e) {
			logger.error("使用线程批量交割异常!" + e);
		}
	}

	private class customerDeliveryRunnable implements Runnable {

		private List<IntDeliveryEx> phoneList;
		private CountDownLatch countDownLatch;
		private IntDeliveryEx intDeliveryEx;
		private User userInfo;
		private List<String> customerList;

		public customerDeliveryRunnable(List<IntDeliveryEx> phoneList, CountDownLatch countDownLatch,IntDeliveryEx intDeliveryEx, User userInfo, List<String> customerList) {
			this.phoneList = phoneList;
			this.countDownLatch = countDownLatch;
			this.intDeliveryEx = intDeliveryEx;
			this.userInfo = userInfo;
			this.customerList = customerList;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < phoneList.size(); i++) {
					// 交割客户如果有转赠流程直接结束流程
					String customerCode = phoneList.get(i).getCustomerCode();
					updateChpEmpCode(phoneList.get(i).getPhone(), intDeliveryEx.getNewEmpCode(),phoneList.get(i).getOsType());
					intDeliveryEx.setDeliveryType(DeliveryType.KHJG.value);
					intDeliveryEx.setCreateTime(new Date());
					intDeliveryEx.setOperator(userInfo.getName());
					intDeliveryEx.setLoginName(phoneList.get(i).getLoginName());
					intDeliveryEx.setPhone(phoneList.get(i).getPhone());
					intDeliveryEx.setOsType(phoneList.get(i).getOsType());
					intDeliveryEx.setCustomerCode(customerCode);
					intDeliveryEx.setChangeReason("手动交割");
					insertIntDelivery(intDeliveryEx);
					boolean flag = checkCustCode(customerCode);
					if (flag) {
						customerList.add(customerCode);
					}
					// 推送到三网
					sendInfo(phoneList.get(i).getPhone(), intDeliveryEx.getNewEmpCode(), phoneList.get(i).getCardType(),
							phoneList.get(i).getCardId(), phoneList.get(i).getOsId(), phoneList.get(i).getOsType(),
							phoneList.get(i).getLoginName());
				}
			} catch (Exception e) {
				logger.error("三网理财经理交割异常!" + e);
			} finally {
				countDownLatch.countDown();
			}
		}
	}

	// 校验customer是否在进行转赠
	private boolean checkCustCode(String customerCode) {
		if (StringUtils.isNotEmpty(customerCode)) {
			Customer customer = new Customer();
			customer.setCustCode(customerCode);
			Customer cust = customerDao.getCustomerbyCode(customer);
			if (cust != null && "1".equals(cust.getTransferState())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 终止正在交割的转赠流程客户
	 * 2016年10月221日
	 * By 陈晓强
	 */
	private void endAuditByCust(List<String> customerList) {
		if (ArrayHelper.isNotEmpty(customerList)) {
			for (String custCode : customerList) {
				donationApproveFlowManager.endAudit(custCode);
			}
		}
	}

	/**
	 * 三网理财经理业绩交割 
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int tripleManagerAchievementDelivery(IntDeliveryEx intDeliveryEx){
		int result = 0;
		User userInfo = UserUtils.getUser();
		DeliveryEx d = new DeliveryEx();
		// 查询旧理财经理对应信息
		List<IntDeliveryEx> empInfo = findStopEmpInfoByCode(intDeliveryEx.getEmpCode());
		if(ArrayHelper.isNotEmpty(empInfo)){
			d.setTeamManagerCode(empInfo.get(0).getNewTeamManagerId());
			d.setOrgCode(empInfo.get(0).getNewOrgId());
			d.setfManagerCode(empInfo.get(0).getNewEmpId());
		}
		// 查询理财经理对应的业绩
		IntDeliveryEx intEx = new IntDeliveryEx();
		intEx.setEmpId(empInfo.get(0).getNewEmpId());
		List<IntDeliveryEx> list = super.dao.tripleManagerAchievementList(intEx);
		
		if(ArrayHelper.isNotEmpty(list)){
			// 查询新理财经理对应信息 
			List<IntDeliveryEx> newEmpInfo2 = findEmpInfoByCode(intDeliveryEx.getNewEmpCode());
			if(ArrayHelper.isNotEmpty(newEmpInfo2)){
				// 理财经理id
				intDeliveryEx.setNewEmpCode(newEmpInfo2.get(0).getNewEmpId());
				// 团队经理id
				intDeliveryEx.setNewTeamManagerCode(newEmpInfo2.get(0).getNewTeamManagerId());
				// 营业部经理
				List<String> strList2 = new ArrayList<String>();
				strList2.add(FortuneRole.STORE_MANAGER.id);
				 List<FortuneUser> listUser2 = RelationShipUtil.getOrgMember(newEmpInfo2.get(0).getNewOrgId(),strList2,"1");
				 if(listUser2.size() > 0){
					 // 营业部经理id
					 intDeliveryEx.setBussManager(listUser2.get(0).getId());;
				 }
				// 团队id
				 intDeliveryEx.setTeamId(newEmpInfo2.get(0).getNewTeamId());
				// 营业部id
				intDeliveryEx.setNewOrgId(newEmpInfo2.get(0).getNewOrgId());
				// 支公司id
				FortuneOrg org =  RelationShipUtil.getCityOrg(newEmpInfo2.get(0).getNewEmpId());
				if(org != null){
					intDeliveryEx.setCityOrg(org.getId());
					List<String> strList = new ArrayList<String>();
					strList.add(FortuneRole.CITY_MANAGER.id);
					 List<FortuneUser> listUser = RelationShipUtil.getOrgMember(org.getId(),strList,"1");
					 if(listUser.size() > 0){
						 // 支公司经理id
						 intDeliveryEx.setCityManager(listUser.get(0).getId());
					 }
				}
				
				d.setNfManagerCode(newEmpInfo2.get(0).getNewEmpId());
				d.setnOrgCode(newEmpInfo2.get(0).getNewOrgId());
				d.setnTeamManagerCode(newEmpInfo2.get(0).getNewTeamManagerId());
			}
			
			intDeliveryEx.setEmpId(empInfo.get(0).getNewEmpId());
			// 修改业绩表对应理财经理
			result = tripleAchievementDeliveryDao.updateAchievement(intDeliveryEx);
			
			for(int i = 0;i<list.size();i++){
				// 财富交割表实体类
				d.setCustCode(list.get(i).getCustomerCode());
				d.setLendCode(list.get(i).getLendCode());
				d.setDelId(IdGen.uuid());
				d.setDelType(DeliveryType.YWJG.value);
				String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
				// 获取当前时间，并转为timestamp格式
				Timestamp time = Timestamp.valueOf(date); 
				d.setDelDate(time);
				d.setCreateBy(userInfo.getName());
				d.setModifyBy(userInfo.getName());
				d.setModifyTime(time);
				insertDelivery(d);
			}
		}else{
			result = -1;
		}
		return result;
	}

	public List<IntDeliveryEx> findDeliveryByPhone(List<IntPhoneCard> phoneList) {
		OwnershipChange[] values = OwnershipChange.values();
		String[] empCodes = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			empCodes[i] = values[i].value;
		}
		return super.dao.findDeliveryByPhone(phoneList, empCodes);
	}
}
