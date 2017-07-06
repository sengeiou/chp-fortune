
package com.creditharmony.fortune.triple.system.service;
import java.text.SimpleDateFormat;
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
import com.creditharmony.core.fortune.type.OrderStatus;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网新增客户接口调用
 * @Class Name TripleNewCustomerService
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleNewCustomerServiceImp  {
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleNewCustomerServiceImp.class);
	/**jsonMapper对象*/
	public static JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private TripleCommonService tripleCommonService;
	@Autowired
	private TripleOwnershipChangeService tripleOwnershipService;

	/**
	 * 客户操作类型为注册接口实现
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 * @throws Exception 
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public IntPhone registerCustomer(IntCustomerBean paramBean) throws Exception{
		// 用于返回信息
		IntPhone backInfo = new IntPhone();
			
		backInfo.setPhone(paramBean.getNewPhone());
		if(StringUtils.isEmpty(paramBean.getNewPhone())){
			logger.error("调用三网注册接口，手机号不能为空");
			throw new Exception("调用三网注册接口，手机号不能为空");
		}
		// 查询手机号是否在三网中已存在
		IntPhone phone = new IntPhone();
		phone.setPhone(paramBean.getNewPhone());
		List<IntPhone> intPhone = tripleNewCustomerDao.searchCustomerByPhoneNum(phone);
		
		if(intPhone.size() == 0){
			// 判断推送的理财经理是否是信合内部的理财经理
			IntPhone p = new IntPhone();
			p.setEmpCode(paramBean.getEmpCode());
			List<IntPhone> userInfo = tripleCommonService.checkManager(p);
			
			if(userInfo.size() > 0){
				// 插入数据到三网
				IntPhone ph = new IntPhone();
				ph.setId(IdGen.uuid());
				ph.setLoginName(paramBean.getLogName());
				ph.setPhone(paramBean.getNewPhone());
				ph.setOsId(paramBean.getOsId());
				if(OsType.CHP.value.equals(paramBean.getOsType())){
					ph.setCustomerCode(paramBean.getOsId());
				}
				ph.setOsType(paramBean.getOsType());
				ph.setEmpCode(paramBean.getEmpCode());
				ph.setCreateTime(new Date());
				ph.setFaileName(paramBean.getFaileName());
				tripleNewCustomerDao.insertIntPhone(ph);
				// 推送到三网
				tripleCommonService.sendCustomerInfo(paramBean);

				// 插入到三网客户归属变更
				if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
					tripleOwnershipService.khXz(paramBean, "1");
				} else {
					tripleOwnershipService.khXz(paramBean, "2");
				}

				// 设置返回信息
				backInfo.setEmpCode(paramBean.getEmpCode());
				return backInfo;
			}else{
				// 当理财经理为线上时，三网系统保存的信息为手机号
				// 插入数据到三网
				IntPhone ph = new IntPhone();
				ph.setId(IdGen.uuid());
				ph.setLoginName(paramBean.getLogName());
				ph.setPhone(paramBean.getNewPhone());
				ph.setOsId(paramBean.getOsId());
				if(OsType.CHP.value.equals(paramBean.getOsType())){
					ph.setCustomerCode(paramBean.getOsId());
				}
				ph.setOsType(paramBean.getOsType());
				ph.setCreateTime(new Date());
				ph.setFaileName(paramBean.getFaileName());
				tripleNewCustomerDao.insertIntPhone(ph);
				// 插入到三网客户归属变更
				if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
					tripleOwnershipService.khXz(paramBean, "1");
				} else {
					tripleOwnershipService.khXz(paramBean, "2");
				}
				// 发送三网对应信息
				paramBean.setEmpCode("");
				tripleCommonService.sendCustomerInfo(paramBean);
				return backInfo;
			}
		} else {
			// 判断三网中存在的手机号是否有对应的理财经理
			String empCode = "";
			for (int i = 0; i < intPhone.size(); i++) {
				if (!StringUtils.isEmpty(intPhone.get(i).getEmpCode())) {
					empCode = intPhone.get(i).getEmpCode();
					break;
				}
			}

			if (StringUtils.isEmpty(empCode)) {
				// 如果三网中存在的手机号没有对应的理财经理，则用新理财经理，
				// 判断推送的理财经理是否是信合内部的理财经理
				IntPhone p3 = new IntPhone();
				p3.setEmpCode(paramBean.getEmpCode());
				List<IntPhone> userInfo = tripleCommonService.checkManager(p3);

				if (userInfo.size() > 0) {
					// 并更新所有的三网中存在的相同的手机号的理财经理为新理财经理
					// 查询相同系统内是否存在此手机号
					phone.setOsType(paramBean.getOsType());
					phone.setEmpCode(paramBean.getEmpCode());
					List<IntPhone> list = tripleNewCustomerDao.searchCustomerByPhoneNum(phone);
					if (list.size() == 0) {
						IntPhone p = new IntPhone();
						p.setPhone(paramBean.getNewPhone());
						p.setEmpCode(paramBean.getEmpCode());
						tripleNewCustomerDao.updateEmpCodeByPhone(p);
						// 插入数据到三网
						IntPhone ph = new IntPhone();
						ph.setId(IdGen.uuid());
						ph.setLoginName(paramBean.getLogName());
						ph.setPhone(paramBean.getNewPhone());
						ph.setEmpCode(paramBean.getEmpCode());
						ph.setOsId(paramBean.getOsId());
						ph.setOsType(paramBean.getOsType());
						if (OsType.CHP.value.equals(paramBean.getOsType())) {
							ph.setCustomerCode(paramBean.getOsId());
						}
						ph.setCreateTime(new Date());
						ph.setFaileName(paramBean.getFaileName());
						tripleNewCustomerDao.insertIntPhone(ph);
					} else {
						IntPhone p = new IntPhone();
						p.setEmpCode(paramBean.getEmpCode());
						p.setPhone(paramBean.getNewPhone());
						p.setOsType(paramBean.getOsType());
						p.setLoginName(paramBean.getLogName());
						tripleNewCustomerDao.updateEmpCodeByPhone(p);
					}
					// 发送三网对应信息
					tripleCommonService.sendCustomerInfo(paramBean);
					// 插入到三网客户归属变更
					if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
						tripleOwnershipService.khXz(paramBean, "1");
					} else {
						tripleOwnershipService.khXz(paramBean, "2");
					}

					// 设置返回信息
					backInfo.setEmpCode(paramBean.getEmpCode());
					return backInfo;

				} else {
					// 插入到三网客户归属变更
					if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
						tripleOwnershipService.khXz(paramBean, "1");
					} else {
						tripleOwnershipService.khXz(paramBean, "2");
					}
					// 发送三网对应信息
					paramBean.setEmpCode("");
					tripleCommonService.sendCustomerInfo(paramBean);
					return backInfo;
				}
			} else {
				// 三网中存在的手机号有对应的理财经理
				phone.setOsType(paramBean.getOsType());
				phone.setEmpCode(empCode);
				List<IntPhone> list = tripleNewCustomerDao.searchCustomerByPhoneNum(phone);

				if (list.size() == 0) {
					// 插入数据到三网
					IntPhone ph = new IntPhone();
					ph.setId(IdGen.uuid());
					ph.setLoginName(paramBean.getLogName());
					ph.setPhone(paramBean.getNewPhone());
					ph.setEmpCode(empCode);
					ph.setOsId(paramBean.getOsId());
					if (OsType.CHP.value.equals(paramBean.getOsType())) {
						ph.setCustomerCode(paramBean.getOsId());
					}
					ph.setOsType(paramBean.getOsType());
					ph.setCreateTime(new Date());
					ph.setFaileName(paramBean.getFaileName());
					tripleNewCustomerDao.insertIntPhone(ph);
				}
				// 发送三网对应信息
				paramBean.setEmpCode(empCode);
				tripleCommonService.sendCustomerInfo(paramBean);
				// 插入到三网客户归属变更

				if (!tripleOwnershipService.isSatisfy(empCode)) {
					tripleOwnershipService.khXz(paramBean, "1");
				} else {
					tripleOwnershipService.khXz(paramBean, "2");
				}
				// 设置返回信息
				backInfo.setEmpCode(empCode);
				return backInfo;
			}
		}
	}

	/**
	 * 客户操作类型为身份认证
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 * @throws Exception 
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public synchronized IntCard authenticateCustomer(IntCustomerBean paramBean) throws Exception{
		// 设置返回信息
		IntCard c = new IntCard();
		c.setCardId(paramBean.getCardId());
		
		if(StringUtils.isEmpty(paramBean.getNewPhone())){
			logger.error("调用三网认证接口，手机号不能为空");
			throw new Exception("调用三网认证接口，手机号不能为空");
		}
		
		if(StringUtils.isEmpty(paramBean.getCardId())){
			logger.error("调用三网认证接口，证件号不能为空");
			throw new Exception("调用三网认证接口，证件号不能为空");
		}
		
		
		// 查询手机号是否在手机号客户表是否存在不存在则插入,用于注册时其他系统发送三网系统注册失败又开始身份验证，提高容错性。
		IntPhone p = new IntPhone();
		p.setPhone(paramBean.getNewPhone());
		p.setOsType(paramBean.getOsType());
		List<IntPhone> pList = tripleNewCustomerDao.searchCustomerByPhoneNum(p);
		
		if(pList.size() == 0){
			this.registerCustomer(paramBean);
		}
		
		// 查询证件号是否存在于三网
		IntCard intCard = new IntCard();
		intCard.setCardId(paramBean.getCardId());
		List<IntCard> card = tripleNewCustomerDao.searchCustomerByCertNum(intCard);
       
		// 证件号在三网中不存在		
		if(card.size() == 0){
			// 证件号不存在于三网，插入证件号信息到三网
			// 设置证件号信息插入证件号理财经理对应表
			IntCard id = new IntCard();
			id.setId(IdGen.uuid());
			id.setUserName(paramBean.getUserName());
			id.setCardType(paramBean.getCardType());
			id.setCardId(paramBean.getCardId());
			try {
				if(!StringUtils.isEmpty(paramBean.getBirthdayStr())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					id.setBirthday(sdf.parse(paramBean.getBirthdayStr()));
				}
			} catch (Exception e) {
				logger.error("三网证件号验证时间转换出错",e);
			}
			
			id.setSex(paramBean.getSex());
			id.setMail(paramBean.getMail());
			
			IntPhone phone = new IntPhone();
			phone.setPhone(paramBean.getNewPhone());
			phone.setOsType(paramBean.getOsType());
			List<IntPhone> intPhone = tripleNewCustomerDao.searchCustomerByPhoneNum(phone);
			String code = "";
			//手机号存在并且理财经理不为空 ，使用手机号的理财经理  否则 使用传入的理财经理
			if(intPhone.size() > 0&&StringUtils.isNotEmpty(intPhone.get(0).getEmpCode())){
				id.setEmpCode(intPhone.get(0).getEmpCode());
				c.setEmpCode(intPhone.get(0).getEmpCode());
				code = intPhone.get(0).getEmpCode();
			}else{
				// 理财经理验证
				IntPhone pe = new IntPhone();
				pe.setEmpCode(paramBean.getEmpCode());
				List<IntPhone> userInfo = tripleCommonService.checkManager(pe);
				if(userInfo.size() > 0){
				    id.setEmpCode(paramBean.getEmpCode());
					c.setEmpCode(paramBean.getEmpCode());
					code = paramBean.getEmpCode();
				}
			}
			
			id.setOrderStatus(OrderStatus.WCD.value);
			id.setCreateTime(new Date());
			tripleNewCustomerDao.insertIntCard(id);
			
			// 插入手机号对应证件号中间表
			IntPhoneCard intPhoneCard = new IntPhoneCard();
			intPhoneCard.setId(IdGen.uuid());
			intPhoneCard.setPhone(paramBean.getNewPhone());
			intPhoneCard.setCardId(paramBean.getCardId());
			intPhoneCard.setCreateTime(new Date());
			tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
			
			paramBean.setEmpCode(code);
			tripleCommonService.sendCustomerInfo(paramBean);
			c.setEmpCode(code);
			return c;
		}else{
			// 判断证件号码是否存在对应的理财经理
			if(StringUtils.isEmpty(card.get(0).getEmpCode())){
				// 证件号没有对应的理财经理，延用手机号对应的理财经理
				if(!StringUtils.isEmpty(paramBean.getEmpCode())){
					// 理财经理验证
					IntPhone p2 = new IntPhone();
					p2.setEmpCode(paramBean.getEmpCode());
					List<IntPhone> userInfo = tripleCommonService.checkManager(p2);
					
					// 理财经理如果为线下更新证件号对应的理财经理为手机号对应的理财经理，同时更新证件号对应手机号对应的理财经理
					if(userInfo.size() > 0){
						
						intCard.setEmpCode(paramBean.getEmpCode());
						tripleNewCustomerDao.updateEmpCodeByCertNum(intCard);
						List<IntPhoneCard> phoneList = infoGroupService.groupByCard(paramBean.getCardId());
						List<IntCard> cardList = null;
						
						IntDeliveryEx intDeliveryEx = new IntDeliveryEx();
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

							IntDeliveryEx dxDelivery = null;
							List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(phoneList);
							if (ArrayHelper.isNotEmpty(deliveryList)) {
								dxDelivery = deliveryList.get(0);
							}

							for(int i = 0;i<phoneList.size();i++){
								IntPhone auP = new IntPhone();
								auP.setPhone(phoneList.get(i).getPhone());
								List<IntPhone> phones = tripleNewCustomerDao.searchCustomerByPhoneNum(auP);
								if (ArrayHelper.isNotEmpty(phones)) {
									for (IntPhone intPhone : phones) {
										if (!paramBean.getEmpCode().equals(intPhone.getEmpCode())) {
											intDeliveryEx.setEmpCode(intPhone.getEmpCode());
											intDeliveryEx.setNewEmpCode(paramBean.getEmpCode());
											intDeliveryEx.setPhone(intPhone.getPhone());
											intDeliveryEx.setOsType(intPhone.getOsType());
											intDeliveryEx.setCustomerCode(intPhone.getCustomerCode());
											intDeliveryEx.setLoginName(intPhone.getLoginName());
											if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
												tripleOwnershipService.khRz(paramBean, "1", intDeliveryEx);
											} else {
												tripleOwnershipService.khRz(paramBean, "2", intDeliveryEx, dxDelivery);
											}
										}
									}
								}
								auP.setEmpCode(paramBean.getEmpCode());
								tripleNewCustomerDao.updateEmpCodeByPhone(auP);
								// 修改chp手机号对应理财经理
								tripleCustomerDeliveryManager.updateChpEmpCode(phoneList.get(i).getPhone(),paramBean.getEmpCode(),OsType.CHP.value);
								
								// 发送三网对应信息
								paramBean.setNewPhone(phoneList.get(i).getPhone());
								tripleCommonService.sendCustomerInfo(phoneList.get(i).getPhone(),
										paramBean.getEmpCode(),
										paramBean.getCardType(),
										paramBean.getCardId(), "", "", "",
										paramBean.getOperation(),paramBean);
							}
						}
						if (ArrayHelper.isNotEmpty(cardList)) {
							for (IntCard ic: cardList) {
								IntCard auC = new IntCard();
								auC.setCardId(ic.getCardId());
								auC.setEmpCode(paramBean.getEmpCode());
								tripleNewCustomerDao.updateEmpCodeByCertNum(auC);
								paramBean.setCardId(ic.getCardId());
								tripleCommonService.sendCustomerInfo(paramBean.getNewPhone(),
										paramBean.getEmpCode(),
										paramBean.getCardType(),
										ic.getCardId(), "", "", "",
										paramBean.getOperation(),paramBean);
							}
						}
						// 查询证件号和手机号是否在中间表存在
						IntPhoneCard ipc = new IntPhoneCard();
						ipc.setPhone(paramBean.getNewPhone());
						ipc.setCardId(paramBean.getCardId());
						List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc);
						if(ipcList.size() == 0){
							// 插入手机号对应证件号中间表
							IntPhoneCard intPhoneCard = new IntPhoneCard();
							intPhoneCard.setId(IdGen.uuid());
							intPhoneCard.setPhone(paramBean.getNewPhone());
							intPhoneCard.setCardId(paramBean.getCardId());
							intPhoneCard.setCreateTime(new Date());
							tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
						}
						// 发送三网对应信息
						tripleCommonService.sendCustomerInfo(paramBean);
						
						c.setEmpCode(paramBean.getEmpCode());
						
					}else{
						// 查询证件号和手机号是否在中间表存在
						IntPhoneCard ipc = new IntPhoneCard();
						ipc.setPhone(paramBean.getNewPhone());
						ipc.setCardId(paramBean.getCardId());
						List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc);
						if(ipcList.size() == 0){
							// 插入手机号对应证件号中间表
							IntPhoneCard intPhoneCard = new IntPhoneCard();
							intPhoneCard.setId(IdGen.uuid());
							intPhoneCard.setPhone(paramBean.getNewPhone());
							intPhoneCard.setCardId(paramBean.getCardId());
							intPhoneCard.setCreateTime(new Date());
							tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
						}
						// 发送三网对应信息
						paramBean.setEmpCode("");
						tripleCommonService.sendCustomerInfo(paramBean);
					}
				}else{
					// 查询证件号和手机号是否在中间表存在
					IntPhoneCard ipc = new IntPhoneCard();
					ipc.setPhone(paramBean.getNewPhone());
					ipc.setCardId(paramBean.getCardId());
					List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc);
					if(ipcList.size() == 0){
						// 插入手机号对应证件号中间表
						IntPhoneCard intPhoneCard = new IntPhoneCard();
						intPhoneCard.setId(IdGen.uuid());
						intPhoneCard.setPhone(paramBean.getNewPhone());
						intPhoneCard.setCardId(paramBean.getCardId());
						intPhoneCard.setCreateTime(new Date());
						tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
					}
					// 发送三网对应信息
					paramBean.setEmpCode("");
					tripleCommonService.sendCustomerInfo(paramBean);
				}
				return c;
			}else{
				// 查询证件号和手机号是否在中间表存在
				IntPhoneCard ipc = new IntPhoneCard();
				ipc.setPhone(paramBean.getNewPhone());
				ipc.setCardId(paramBean.getCardId());
				List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc);
				if (ipcList.size() == 0) {
					// 插入手机号对应证件号中间表
					IntPhoneCard intPhoneCard = new IntPhoneCard();
					intPhoneCard.setId(IdGen.uuid());
					intPhoneCard.setPhone(paramBean.getNewPhone());
					intPhoneCard.setCardId(paramBean.getCardId());
					intPhoneCard.setCreateTime(new Date());
					tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
				}
				// 证件号对应的理财经理和三网中存在的理财经理不一致延用三网中存在的证件号对应的理财经理，并推送到各系统
				List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(paramBean.getNewPhone());
				List<IntCard> listCard = null;

				String empCode = "";
				IntDeliveryEx intDeliveryEx = new IntDeliveryEx();
				if (ArrayHelper.isNotEmpty(listPhone)) {
					IntPhoneCard pc = new IntPhoneCard();
					List<String> strs = new ArrayList<String>();
					for (IntPhoneCard intPhoneCard : listPhone) {
						strs.add(intPhoneCard.getPhone());
					}
					pc.setPhoneList(strs);
					List<IntCard> icList = tripleNewCustomerDao.findCardByPhone(pc);
					if (ArrayHelper.isNotEmpty(icList)) {
						listCard = icList;
						empCode = icList.get(0).getEmpCode();
					}

					IntDeliveryEx dxDelivery = null;
					List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(listPhone);
					if (ArrayHelper.isNotEmpty(deliveryList)) {
						dxDelivery = deliveryList.get(0);
					}

					for (int i = 0; i < listPhone.size(); i++) {
						IntPhone auP = new IntPhone();
						auP.setPhone(listPhone.get(i).getPhone());
						List<IntPhone> phones = tripleNewCustomerDao.searchCustomerByPhoneNum(auP);
						if (ArrayHelper.isNotEmpty(phones)) {
							for (IntPhone intPhone : phones) {
								if (!empCode.equals(intPhone.getEmpCode())) {
									intDeliveryEx.setEmpCode(intPhone.getEmpCode());
									intDeliveryEx.setNewEmpCode(empCode);
									intDeliveryEx.setPhone(intPhone.getPhone());
									intDeliveryEx.setOsType(intPhone.getOsType());
									intDeliveryEx.setCustomerCode(intPhone.getCustomerCode());
									intDeliveryEx.setLoginName(intPhone.getLoginName());
									if (!tripleOwnershipService.isSatisfy(empCode)) {
										tripleOwnershipService.khRz(paramBean, "1", intDeliveryEx);
									}else{
										tripleOwnershipService.khRz(paramBean, "2", intDeliveryEx, dxDelivery);
									}
								}
							}
						}
						auP.setEmpCode(empCode);
						tripleNewCustomerDao.updateEmpCodeByPhone(auP);
						// 修改chp手机号对应理财经理
						tripleCustomerDeliveryManager.updateChpEmpCode(listPhone.get(i).getPhone(), empCode, OsType.CHP.value);
						// 发送三网对应信息
						paramBean.setNewPhone(listPhone.get(i).getPhone());
						tripleCommonService.sendCustomerInfo(listPhone.get(0).getPhone(), empCode, "", "", "", "", "", "", paramBean);
					}
				}
				if (ArrayHelper.isNotEmpty(listCard)) {
					for (IntCard ic : listCard) {
						IntCard auC = new IntCard();
						auC.setCardId(ic.getCardId());
						auC.setEmpCode(empCode);
						tripleNewCustomerDao.updateEmpCodeByCertNum(auC);
						// 发送三网对应信息
						paramBean.setCardId(ic.getCardId());
						tripleCommonService.sendCustomerInfo("", empCode, "", ic.getCardId(), "", "", "", "", paramBean);
					}
				}
				// 发送三网对应信息
				paramBean.setEmpCode(empCode);
				tripleCommonService.sendCustomerInfo(paramBean);
				c.setEmpCode(empCode);
				return c;
			}
		}
	}

	/**
	 * 客户操作类型为无理财经理添加理财经理
	 * 2016年1月23日
	 * By 胡体勇
	 * @param paramBean
	 * @return 
	 * @throws Exception 
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean addCustomerManager(TripleNewCustomerInBean paramBean) throws Exception{
		// 查询手机号是否存在对应的线下理财经理；如果存在则不允许修改理财经理，不存在则可以修改
		IntPhone phone = new IntPhone();
		phone.setPhone(paramBean.getPhone());
		
		if(StringUtils.isEmpty(paramBean.getPhone())){
			logger.error("调用三网添加理财经理接口，手机号不能为空");
			throw new Exception("调用三网添加理财经理接口，手机号不能为空");
		}
		if(StringUtils.isEmpty(paramBean.getEmpCode())){
			logger.error("调用三网添加理财经理接口，理财经理不能为空");
			throw new Exception("调用三网添加理财经理接口，理财经理不能为空");
		}

		List<IntPhone> intPhone = tripleNewCustomerDao.searchCustomerByPhoneNum(phone);
		String empCode = "";
		if(intPhone.size() > 0 ){
			for(int i = 0;i<intPhone.size();i++){
				if (StringUtils.isNotEmpty(intPhone.get(i).getEmpCode())) {
					empCode = intPhone.get(i).getEmpCode(); 
					break;
				}
			}
			if (StringUtils.isNotEmpty(empCode)) {
				paramBean.setEmpCode(empCode);
				tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(paramBean,null));
			}else{
				IntPhone in = new IntPhone();
				in.setEmpCode(paramBean.getEmpCode());
				// 判断推送的理财经理是否是信合内部的理财经理
				List<IntPhone> userInfo = tripleCommonService.checkManager(in);
				 if(userInfo.size() == 0){
					// 当推送的理财经理不是信和内部的理财经理时返回对应理财经理为空
					// 发送三网对应信息
					paramBean.setEmpCode("");
					tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(paramBean, null));
				} else {
					// 推送的理财经理是信和内部理财经理
					// 修改三网中存在的手机号对应的理财经理
					in.setPhone(paramBean.getPhone());

					IntDeliveryEx ide = new IntDeliveryEx();
					IntDeliveryEx dxIde = null;
					List<IntPhoneCard> ipcList = new ArrayList<IntPhoneCard>();
					IntPhoneCard ipc = new IntPhoneCard();
					ipc.setPhone(paramBean.getPhone());
					ipcList.add(ipc);
					List<IntDeliveryEx> ideList = tripleCustomerDeliveryManager.findDeliveryByPhone(ipcList);
					if (ArrayHelper.isNotEmpty(ideList)) {
						dxIde = ideList.get(0);
					}
					xfLjGroupPhone(paramBean, dxIde, ide, in);

					tripleNewCustomerDao.updateEmpCodeByPhone(in);
					// 如果手机号有对应的证件号则修改证件号对应的理财经理
					if (StringUtils.isNotEmpty(paramBean.getCardId())) {
						IntCard card = new IntCard();
						card.setEmpCode(paramBean.getEmpCode());
						card.setCardId(paramBean.getCardId());
						tripleNewCustomerDao.updateEmpCodeByCertNum(card);
						// 修改相同组内的手机号对应的理财经理
						List<IntPhoneCard> phoneList = infoGroupService.groupByCard(paramBean.getCardId());
						if (phoneList.size() > 0) {
							IntDeliveryEx dxDelivery = null;
							List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(phoneList);
							if (ArrayHelper.isNotEmpty(deliveryList)) {
								dxDelivery = deliveryList.get(0);
							}

							IntDeliveryEx intDeliveryEx = new IntDeliveryEx();
							for (int i = 0; i < phoneList.size(); i++) {
								IntPhone auP = new IntPhone();
								auP.setPhone(phoneList.get(i).getPhone());
								xfLjGroupPhone(paramBean, dxDelivery, intDeliveryEx, auP);
								auP.setEmpCode(paramBean.getEmpCode());
								tripleNewCustomerDao.updateEmpCodeByPhone(auP);
								// 修改chp手机号对应理财经理
								tripleCustomerDeliveryManager.updateChpEmpCode(phoneList.get(i).getPhone(), paramBean.getEmpCode(), OsType.CHP.value);
								paramBean.setPhone(phoneList.get(i).getPhone());
								// 发送三网对应信息
								// tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(paramBean,null));
								tripleCommonService.sendCustomerInfo(phoneList.get(i).getPhone(), paramBean.getEmpCode(), paramBean.getCardType(), paramBean.getCardId(), "", "", "", paramBean.getOperation(), BeanUtil.conv2IntCustomerBean(paramBean, null));
							}
						}
					}
					// 发送三网对应信息
					tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(paramBean, null));
				}
			}
		}else{
			// 发送三网对应信息
			paramBean.setEmpCode("");
			tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(paramBean,null));
		}
		return true;
	}

	private void xfLjGroupPhone(TripleNewCustomerInBean paramBean, IntDeliveryEx dxDelivery, IntDeliveryEx intDeliveryEx, IntPhone auP) {
		List<IntPhone> phones = tripleNewCustomerDao.searchCustomerByPhoneNum(auP);
		if (ArrayHelper.isNotEmpty(phones)) {
			for (IntPhone ip : phones) {
				if (!paramBean.getEmpCode().equals(ip.getEmpCode())) {
					intDeliveryEx.setEmpCode(ip.getEmpCode());
					intDeliveryEx.setNewEmpCode(paramBean.getEmpCode());
					intDeliveryEx.setPhone(ip.getPhone());
					intDeliveryEx.setOsType(ip.getOsType());
					intDeliveryEx.setCustomerCode(ip.getCustomerCode());
					intDeliveryEx.setLoginName(ip.getLoginName());
					if (!tripleOwnershipService.isSatisfy(paramBean.getEmpCode())) {
						tripleOwnershipService.xfLj(paramBean, "1", intDeliveryEx);
					} else {
						tripleOwnershipService.xfLj(paramBean, "2", intDeliveryEx, dxDelivery);
					}
				}
			}
		}
	}
}
