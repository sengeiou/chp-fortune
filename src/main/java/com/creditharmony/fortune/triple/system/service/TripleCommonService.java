package com.creditharmony.fortune.triple.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntLogBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.TripleFirstOrderFacade;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * @Class Name TripleCommontService
 * @author 胡体勇
 * @Create In 2016年5月31日
 */
@Service
public class TripleCommonService {
	
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private TripleCustomerDeliveryDao tripleCustomerDeliveryDao;
	@Autowired
	private TripleCustomerDeliveryManager manager;
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleCommonService.class);
	@Autowired
	private TripleFirstOrderFacade tripleFirstOrderFacade;
	/**jsonMapper对象*/
	public static JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
	
	/**
	 * 校验理财经理是否是信合内部理财经理
	 * 2016年5月31日
	 * By 胡体勇
	 * @param intPhone
	 * @return
	 */
	public List<IntPhone> checkManager(IntPhone intPhone){		//intPhone 理财经理工号
		if(!StringUtils.isEmpty(intPhone.getEmpCode())){
			List<IntPhone> userInfo = tripleNewCustomerDao.checkManager(intPhone);
			return userInfo;
		}else{
			List<IntPhone> list = new ArrayList<IntPhone>();
			return list;
		}
	}
	
	/**
	 * 插入发送表信息
	 * 2016年08月09日
	 * By 张新民
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false) 
	public void sendCustomerInfo(IntCustomerBean paramBean){
		try{
			
			paramBean.setSendStatus(Constant.SEND_STATUS_FAIL);
			paramBean.setCrmSendStatus(Constant.SEND_STATUS_FAIL);
			paramBean.setId(IdGen.uuid());
			paramBean.setCreateTime(new Date());
			paramBean.setSendTime(new Date());
			paramBean.setSendType(Constant.SYSNCNUM_XZKHXXTB);
			paramBean.setUniqueNum(SerialNum.getSerialNum());
	        
//	        // 设置插入备份表数据
//	        IntCustomerBean cust = new IntCustomerBean();
//	        //org.apache.commons.beanutils.BeanUtils.copyProperties(cust, paramBean);
//		    cust.setSendStatus(Constant.SEND_STATUS_FAIL);
//		    cust.setCrmSendStatus(Constant.SEND_STATUS_FAIL);
//	        cust.setId(IdGen.uuid());
//	        cust.setOsId(paramBean.getOsId());
//	        cust.setOsType(paramBean.getOsType());
//	        cust.setNewPhone(paramBean.getNewPhone());
//	        cust.setEmpCode(paramBean.getEmpCode());
//	        cust.setCardType(paramBean.getCardType());
//	        cust.setCardId(paramBean.getCardId());
//	        cust.setUserName(paramBean.getUserName());
//	        cust.setLogName(paramBean.getLogName());
//	        cust.setCreateTime(new Date());
//	        cust.setSendTime(new Date());
//	        cust.setSendType(Constant.SYSNCNUM_XZKHXXTB);
//	        cust.setOperation(paramBean.getOperation());
//	        cust.setUniqueNum(SerialNum.getSerialNum());
//	        
//	        cust.setUserNumber(paramBean.getUserNumber());
//	        cust.setICEAddress(paramBean.getICEAddress());
//	        cust.setICEArea(paramBean.getICEArea());
//	        cust.setICECardId(paramBean.getICECardId());
//	        cust.setICECardType(paramBean.getICECardType());
//	        cust.setICEEmail(paramBean.getICEEmail());
//	        cust.setICEName(paramBean.getICEName());
//	        cust.setICEPhone(paramBean.getICEPhone());
//	        cust.setICERelation(paramBean.getICERelation());
	        
	        tripleNewCustomerDao.insertTripleCustomer(paramBean);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("手机号为："+ paramBean.getNewPhone() +"插入发送表异常！",e);
		}
	}
	
	/**
	 * 插入发送表信息
	 * 2016年3月3日
	 * By 张新民
	 * @param phone
	 * @param empCode
	 * @param cardType
	 * @param cardId
	 * @param osId
	 * @param osType
	 * @param userName
	 * @param operation
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void sendCustomerInfo(String phone,String empCode,String cardType,String cardId,
			String osId,String osType,String loginName,String operation,IntCustomerBean paramBean){
		try{
			IntCustomerBean nbean =new IntCustomerBean();
			BeanUtil.copy2newIntCustomerBean(paramBean, nbean);
			nbean.setNewPhone(phone);
			nbean.setEmpCode(empCode);
			nbean.setCardType(cardType);
			nbean.setCardId(cardId);
			nbean.setOsId(osId);
			nbean.setOsType(osType);
			nbean.setLogName(loginName);
			nbean.setOperation(operation);
			this.sendCustomerInfo(nbean);
		}catch(Exception e){
			logger.error("手机号为："+phone+"插入发送表异常！",e);
		}
	}
	
	/**
	 * 首单插入发送信息表
	 * 2016年3月11日
	 * By 胡体勇
	 * @param phone
	 * @param empCode
	 * @param cardId
	 * @param cardType
	 * @param operation
	 */
    public void sendFistOrderInfo(String phone,String empCode,String cardId,String cardType){
    	try{
	        // 设置插入备份表数据
	        IntCustomerBean cust = new IntCustomerBean();
		    cust.setSendStatus(Constant.SEND_STATUS_FAIL);
	        cust.setId(IdGen.uuid());
	        cust.setNewPhone(phone);
	        cust.setEmpCode(empCode);
	        cust.setCardId(cardId);
	        cust.setCardType(cardType);
	        cust.setCreateTime(new Date());
	        cust.setSendTime(new Date());
	        cust.setSendType(Constant.SYSNCNUM_KHSDXXTB);
	        cust.setOperation("SD");
	        cust.setUniqueNum(SerialNum.getSerialNum());
	        tripleNewCustomerDao.insertTripleCustomer(cust);
		}catch(Exception e){
			logger.error("三网首单信息插入发送表异常！",e);
		}
    }
	
    /**
	 * 手机号变更发送三网信息
	 * 2016年3月3日
	 * By 胡体勇
	 * @param phone
	 * @param empCode
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void sendChangePhoneInfo(String phone,String oldPhone,String empCode, String osType){
		try{
	        // 设置插入备份表数据
	        IntCustomerBean cust = new IntCustomerBean();
		    cust.setSendStatus(Constant.SEND_STATUS_FAIL);
	        cust.setId(IdGen.uuid());
	        cust.setNewPhone(phone);
	        cust.setOldPhone(oldPhone);
	        cust.setEmpCode(empCode);
	        cust.setCreateTime(new Date());
	        cust.setSendTime(new Date());
	        cust.setSendType(Constant.SYSNCNUM_KHSJHBGXXTB);
	        cust.setUniqueNum(SerialNum.getSerialNum());
	        cust.setOperation("PBG");
	        cust.setOsType( osType );
	        if(Constant.SYSTEM_TYPE_CHP.equals(osType)){
	        	cust.setCrmSendStatus(Constant.SEND_STATUS_FAIL);
	        }
	        tripleNewCustomerDao.insertTripleCustomer(cust);
		}catch(Exception e){
			logger.error("手机号为："+phone+"插入三网发送信息表异常！",e);
		}
	}
	
	/**
	 * 插入三网日志表
	 * 2016年3月10日
	 * By 胡体勇
	 * @param object
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void insertLog(Object object,String typeId,String opType){
		// 插入日志到三网
		IntLogBean log = new IntLogBean();
		log.setId(IdGen.uuid());
		log.setOpType(opType);
		log.setTypeId(typeId);
		String content =jsonMapper.toJson(object);
		log.setContent(content==null?"":(content.length()>90000?content.substring(0,90000):content));
		log.setCreateTime(new Date());
		tripleNewCustomerDao.insertTripleLog(log);
	}
	
	/**
	 * 理财经理组织机构发生变更修改客户表和出借表相应的变更信息
	 * 2016年7月5日
	 * By 胡体勇
	 * @param userId
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void updateManagerInfo(String userId,String userCode){
		// 查询用户是否是理财经理
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setEmpId(userId);
		IntDeliveryEx intEx = tripleCustomerDeliveryDao.findOrgName(ex);
		if(intEx != null){
			// 查询理财经理对应信息
			List<IntDeliveryEx> empInfo = manager.findStopEmpInfoByCode(userCode);
			if(ArrayHelper.isNotEmpty(empInfo)){
				ex.setEmpId(userId);
				ex.setEmpCode(userId);
				// 团队经理id
				ex.setNewTeamManagerCode(empInfo.get(0).getNewTeamManagerId());
				ex.setNewTeamManagerId(empInfo.get(0).getNewTeamManagerId());
				// 营业部经理
				List<String> strList2 = new ArrayList<String>();
				strList2.add(FortuneRole.STORE_MANAGER.id);
				 List<FortuneUser> listUser2 = RelationShipUtil.getOrgMember(empInfo.get(0).getNewOrgId(),strList2,"1");
				 if(listUser2.size() > 0){
					 // 营业部经理id
					 ex.setBussManager(listUser2.get(0).getId());;
				 }
				// 团队id
				 ex.setTeamId(empInfo.get(0).getNewTeamId());
				 ex.setNewTeamId(empInfo.get(0).getNewTeamId());
				// 营业部id
				ex.setNewOrgId(empInfo.get(0).getNewOrgId());
				// 支公司id
				FortuneOrg org =  RelationShipUtil.getCityOrg(empInfo.get(0).getNewEmpId());
				if(org != null){
					ex.setCityOrg(org.getId());
					List<String> strList = new ArrayList<String>();
					strList.add(FortuneRole.CITY_MANAGER.id);
					 List<FortuneUser> listUser = RelationShipUtil.getOrgMember(org.getId(),strList,"1");
					 if(listUser.size() > 0){
						 // 支公司经理id
						 ex.setCityManager(listUser.get(0).getId());
					 }
				}
				
			// 修改客户表对应理财经理信息
			tripleNewCustomerDao.updateCustomerEmpByManagerId(ex);
			// 修改出借表对应理财经理信息
			tripleCustomerDeliveryDao.updateTripleLendManager(ex);
		}
		
	 }
	}
	
	/**
	 * 根据同组手机列表 查询 客户列表 排除 归属为空的 记录 并按注册时间 升序 
	 * @param phoneList
	 * @return
	 */
    public List<IntPhone> getCustListByPhones(List<IntPhoneCard> phoneList){
    	List<IntPhone> custList=null;
    	if(phoneList.size() > 0){
			List<String> strs = new ArrayList<String>();
			for (IntPhoneCard intPhoneCard : phoneList) {
				strs.add(intPhoneCard.getPhone());
			}
			List<IntPhone> icList = tripleNewCustomerDao.findFirstRegTimeByPhone(strs);
			if (ArrayHelper.isNotEmpty(icList)) {
				custList = icList;
			}
		}	
    	return custList;
    }
	
	
	/**
	 * 根据同组手机列表 查询 同组证件列表 排除 理财经理为空的记录 
	 * @param phoneList
	 * @return
	 */
    public List<IntCard> getCardListByPhones(List<IntPhoneCard> phoneList){
    	List<IntCard> cardList=null;
    	if(phoneList.size() > 0){
			IntPhoneCard pc = new IntPhoneCard();
			List<String> strs = new ArrayList<String>();
			for (IntPhoneCard intPhoneCard : phoneList) {
				strs.add(intPhoneCard.getPhone());
			}
			pc.setPhoneList(strs);
			List<IntCard> icList = tripleNewCustomerDao.findCardFirstOrderTimeByPhone(pc);
			if (ArrayHelper.isNotEmpty(icList)) {
				cardList = icList;
			}
		}	
    	return cardList;
    }
	
	/**
	 * 根据同组手机列表 查询 同组证件列表 案成单时间正序排序
	 * @param phoneList
	 * @return
	 */
    public List<IntCard> getCardList(List<IntPhoneCard> phoneList){
    	List<IntCard> cardList=null;
    	if(phoneList.size() > 0){
			IntPhoneCard pc = new IntPhoneCard();
			List<String> strs = new ArrayList<String>();
			for (IntPhoneCard intPhoneCard : phoneList) {
				strs.add(intPhoneCard.getPhone());
			}
			pc.setPhoneList(strs);
			List<IntCard> icList = tripleNewCustomerDao.findCardByPhone(pc);
			if (ArrayHelper.isNotEmpty(icList)) {
				cardList = icList;
			}
		}	
    	return cardList;
    }
    /**
     *  查询证件号和手机号是否在中间表存在  如果不存在则插入
     */
    public void insertPhoneCard(String phone,String cardId){
    	IntPhoneCard ipc = new IntPhoneCard();
		ipc.setPhone(phone);
		ipc.setCardId(cardId);
		List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc);
		if(ipcList.size() == 0){
			// 插入手机号对应证件号中间表
			IntPhoneCard intPhoneCard = new IntPhoneCard();
			intPhoneCard.setId(IdGen.uuid());
			intPhoneCard.setPhone(phone);
			intPhoneCard.setCardId(cardId);
			intPhoneCard.setCreateTime(new Date());
			tripleFirstOrderFacade.insertIntPhoneCard(intPhoneCard);
		}
    }
    
    /**
     * 根据证件理财经理
     * @param cardList
     * @param pempCode
     * @return
     */
    public String getEmpCode (List<IntCard> cardList,String pempCode){
    	String empCode="";
	    if (ArrayHelper.isNotEmpty(cardList)&&null!=cardList.get(0).getEmpCode()&&!"".equals(cardList.get(0).getEmpCode())) {
			empCode=cardList.get(0).getEmpCode();
		}else{
			// 判断理财经理是否是线下理财经理
			IntPhone p = new IntPhone();
			p.setEmpCode(pempCode);
			List<IntPhone> list = checkManager(p);	//在CHP系统里查询 是否有该员工号的理财经理
			if(list.size() > 0){
				empCode=pempCode;
			}else{
				empCode="";
			}
		}
	    return empCode;
    }
	
}
