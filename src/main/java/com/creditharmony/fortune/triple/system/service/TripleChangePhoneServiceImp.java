
package com.creditharmony.fortune.triple.system.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.adapter.service.triple.bean.TripleChangePhoneInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 手机号变更接口调用
 * 
 * @Class Name TripleChangePhoneService
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleChangePhoneServiceImp {

	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(TripleChangePhoneServiceImp.class);
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;// 三网客户查询
	@Autowired
	private TripleCommonService tripleCommonService;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleOwnershipChangeService tripleOwnershipService;
	@Autowired
	private IntKeyLogService intKeyLogService;

	/**
	 * 手机号变更 2016年2月26日 By 胡体勇
	 * 
	 * @param paramBean
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public boolean changePhone(TripleChangePhoneInBean paramBean) {
		// 插入日志
		tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_KHSJHBGXXTB, "L");
		try {
			return doChangePhoneN( paramBean);
		} catch (Exception e) {
			intKeyLogService.log(e,  "手机号变更"+Constant.SYSNCNUM_KHSJHBGXXTB,JSONObject.toJSONString(paramBean) );
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 手机号变更新逻辑
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	private boolean doChangePhoneN(TripleChangePhoneInBean paramBean) throws Exception{
		IntPhone oldPhone = null;
		//验证输入参数是否完整
		if(StringUtils.isEmpty(paramBean.getOldPhone())||StringUtils.isEmpty(paramBean.getNewPhone())){
			throw new Exception("更改手机号的新旧手机号不能为空，新手机号【"+paramBean.getNewPhone()+"】旧手机号【"+paramBean.getOldPhone()+"】");
		}
		if(StringUtils.isEmpty(paramBean.getOsType())){
			throw new Exception("更改手机号的系统类型不能为空，系统类型【"+paramBean.getOsType()+"】");
		}
		IntPhone phoneParam = new IntPhone();
		phoneParam.setPhone(paramBean.getNewPhone());
		phoneParam.setOsType(paramBean.getOsType());
//		List<IntPhone> list = tripleNewCustomerDao.searchCustomerByPhoneNum(phoneParam);
//		if(null!=list &&list.size()>0){
//			throw new Exception("新手机号【"+paramBean.getNewPhone()+"】在系统【"+paramBean.getOsType()+"】中已经存在");
//		}
		//查询旧手机号证件号
		phoneParam.setPhone(paramBean.getOldPhone());
		List<IntPhone> list = tripleNewCustomerDao.searchCustomerByPhoneNum(phoneParam);
		if(null==list ||list.size()==0){
			throw new Exception("旧手机号【"+paramBean.getOldPhone()+"】在系统【"+paramBean.getOsType()+"】中不存在");
		}
		oldPhone=list.get(0);
		
		//更新旧手机号为新手机号
		updOldPhone(paramBean.getOldPhone(), paramBean.getNewPhone(), paramBean.getOsType());
		
		IntPhoneCard ipcParam=new IntPhoneCard();
		ipcParam.setPhone(paramBean.getOldPhone());
		List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipcParam);
		//插入新手机号和旧手机号的关联的证件号 的关联关系
		if(null!=ipcList&&ipcList.size()>0){
			for(int i=0;i<ipcList.size();i++){
				IntPhoneCard ipc=ipcList.get(i);
				if(!validPCExist(paramBean.getNewPhone(),ipc.getCardId())){
					addPC(paramBean.getNewPhone(), ipc.getCardId());
				}
			}
			//删除旧手机号和证件号关联 （如果 旧手机号在其他系统也存在则不删除 ）
			if(!phoneExists(paramBean.getOldPhone())){
				for(int i=0;i<ipcList.size();i++){
					IntPhoneCard ipc=ipcList.get(i);
						delPC(ipc.getPhone(), ipc.getCardId());
				}
			}
		}		
		//查询变更手机号后 新手机号 同组客户列表 ，确定理财经理归属 并更新理财经理
		List<IntPhoneCard> newPhoneList = infoGroupService.groupByPhone(paramBean.getNewPhone());
		List<IntCard> cardList=tripleCommonService.getCardListByPhones(newPhoneList);
		String empCode=null;
		//如果有成单最早的且理财经理不为空的证件号 则取该 理财经理作为该组的理财经理
		if(cardList!=null&&cardList.size()>0){
			empCode=cardList.get(0).getEmpCode();
		}else{
		//否则查询注册时间最早的客户的理财经理 作为 理财经理
			List<IntPhone> phoneList=tripleCommonService.getCustListByPhones(newPhoneList);
			if(null!=phoneList&&phoneList.size()>0){
				empCode=phoneList.get(0).getEmpCode();
			}
		}
		//如果成单最早 和 注册时间早的 都没有理财经理则理财经理为空 
		empCode = empCode==null?"":empCode;
		
		
		if (null!=newPhoneList&&newPhoneList.size() > 0) {
			//插入理财经理变更记录 （因为插入理财经理变更历史记录时会对比理财经理所以应该在 更新理财经理之前插入）
			sjhBgN(paramBean, empCode);
			// 修改同组的手机号对应的理财经理
			for (int i = 0; i < newPhoneList.size(); i++) {
				IntPhone auP = new IntPhone();
				auP.setPhone(newPhoneList.get(i).getPhone());
				auP.setEmpCode(empCode);
				tripleNewCustomerDao.updateEmpCodeByPhone(auP);
				try {
					// 修改chp手机号对应理财经理
					tripleCustomerDeliveryManager.updateChpEmpCode(newPhoneList.get(i).getPhone(), empCode, OsType.CHP.value);
				} catch (Exception e) {
					this.logger.error("同步手机号：" + newPhoneList.get(i).getPhone() + "对应理财经理到CHP异常！", e);
				}
				
				// 发送三网信息如果是新变更的手机号则插入变更记录  否则插入变更记录但是 旧手机号为空
				if(paramBean.getNewPhone().equals(newPhoneList.get(i).getPhone())){
					tripleCommonService.sendChangePhoneInfo(paramBean.getNewPhone(), paramBean.getOldPhone(), empCode, paramBean.getOsType());
				}else{
					tripleCommonService.sendChangePhoneInfo(newPhoneList.get(i).getPhone(), "", empCode, paramBean.getOsType());
				}
			}
		}
		//修改证件号对应的理财经理
		 cardList=tripleCommonService.getCardList(newPhoneList);
		 if(null!=cardList&&cardList.size()>0){
			 for(int i=0;i<cardList.size();i++){
				 IntCard ic=cardList.get(i);
				 if(StringUtils.isNotEmpty(ic.getCardId())){
					 IntCard icp=new IntCard();
					 icp.setCardId(ic.getCardId());
					 icp.setEmpCode(empCode);
					 tripleNewCustomerDao.updateEmpCodeByCertNum(icp);
				 }
			 }
		 }
		
		return true;
	}
	//验证关联关系是否存在
	private boolean validPCExist (String phone,String card_id){
		IntPhoneCard ipc1 = new IntPhoneCard();
		ipc1.setPhone(phone);
		ipc1.setCardId(card_id);
		List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc1);
		return ipcList!=null&&ipcList.size() > 0;
	}
	//插入证件号和手机号关联关系	
	private void addPC (String phone,String card_id){
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(card_id))
			return;
		IntPhoneCard ipc = new IntPhoneCard();
		ipc.setId(IdGen.uuid());
		ipc.setPhone(phone);
		ipc.setCreateTime(new Date());
		ipc.setCardId(card_id);
		tripleNewCustomerDao.insertIntPhoneCard(ipc);
	}
	
	//判断手机号是否存在
	private boolean phoneExists (String phone){
		IntPhone iphone=new IntPhone();
		iphone.setPhone(phone);
		List<IntPhone> phones=tripleNewCustomerDao.searchCustomerByPhoneNum(iphone);
		return null!=phones&&phones.size()>0;
	}
	
	//删除手机号证件号关联关系  只删除非空的
	private void delPC (String	 phone,String card){
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(card)){
			return;
		}
		IntPhoneCard c = new IntPhoneCard();
		c.setPhone(phone);
		c.setCardId(card);
		tripleNewCustomerDao.deletePhoneCard(c);
	}
	
	//旧手机号更新成新手机号
	private void updOldPhone(String oldPhone,String newPhone,String osType){
		// 新手机号在三网系统中不存在,没有理财经理
		IntPhone p = new IntPhone();
		p.setPhone(oldPhone);
		p.setNewPhone(newPhone);
		p.setOsType(osType);
		p.setEmpCode(null);//为空 则不更新
		tripleNewCustomerDao.updatePhone(p);
		//如果是chp 的手机号变更还需要更新chp客户表的手机号
		if(OsType.CHP.value.equals(osType)){
			tripleNewCustomerDao.updateChpPhone(newPhone, oldPhone);
		}
	}
	
	private void sjhBgN(TripleChangePhoneInBean paramBean, String newEmpCode) {
		IntDeliveryEx deliveryEx = new IntDeliveryEx();
		// 查询同组手机号
		List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(paramBean.getNewPhone());
		if (ArrayHelper.isNotEmpty(listPhone)) {
			// 如果同组手机号中有交割电销的交割记录 取最新的备用
			IntDeliveryEx dxDelivery = null;
			List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(listPhone);
			if (ArrayHelper.isNotEmpty(deliveryList)) {
				dxDelivery = deliveryList.get(0);
			}
			for (IntPhoneCard ipc : listPhone) {
				IntPhone intPhone = new IntPhone();
				intPhone.setPhone(ipc.getPhone());
				List<IntPhone> phoneList = tripleNewCustomerDao.searchCustomerByPhoneNum(intPhone);
				if (ArrayHelper.isNotEmpty(phoneList)) {
					for (IntPhone ip : phoneList) {
						//if ((newEmpCode!=null&&!newEmpCode.equals(ip.getEmpCode()))||(newEmpCode==null&&ip.getEmpCode()!=null)) {
						//if (!newEmpCode.equals(ip.getEmpCode())) {
							deliveryEx.setEmpCode(ip.getEmpCode());
							deliveryEx.setNewEmpCode(newEmpCode);
							deliveryEx.setLoginName(ip.getLoginName());
							deliveryEx.setOsType(ip.getOsType());
							deliveryEx.setPhone(ip.getPhone());
							deliveryEx.setCustomerCode(ip.getCustomerCode());
							if (!tripleOwnershipService.isSatisfy(newEmpCode)) {
							tripleOwnershipService.sjhBg(paramBean, "1", deliveryEx);
							} else {
								//如果原来的理财经理是 电销则 原理财经理不查询交割给400801 的理财经理
							tripleOwnershipService.sjhBg(paramBean, tripleOwnershipService.isSatisfy(ip.getEmpCode()) ? "1" : "2", deliveryEx, dxDelivery);
							}
						//}
					}
				}
			}
		}
	}
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//以下为手机号变更原逻辑 入口方法为 doChangePhone 
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	/**
//	 * 
//	 * 
//	 * 手机号变更原逻辑
//	 * @param paramBean
//	 * @return
//	 * @throws Exception
//	 */
//	private boolean doChangePhone(TripleChangePhoneInBean paramBean) throws Exception{
//		// 查询旧手机号对应的理财经理
//		IntPhone oldP = new IntPhone();
//		oldP.setPhone(paramBean.getOldPhone());
//		IntPhone oldPhone = tripleNewCustomerDao.findManagerByCreateTime(oldP);
//
//		// 查询新手机号对应的理财经理
//		IntPhone newP = new IntPhone();
//		newP.setPhone(paramBean.getNewPhone());
//		IntPhone newPhone = tripleNewCustomerDao.findManagerByCreateTime(newP);
//
//		// ------成单时间获取修改为获取同组最早成单时间 张新民 20161021-----------
//		List<IntPhoneCard> oldPhoneList = infoGroupService.groupByPhone(paramBean.getOldPhone());
//		List<IntCard> oldCardList = tripleCommonService.getCardList(oldPhoneList);
//		IntCard oldOrder = null;
//		IntCard newOrder = null;
//		List<IntPhoneCard> newPhoneList = infoGroupService.groupByPhone(paramBean.getNewPhone());
//		List<IntCard> newCardList = tripleCommonService.getCardList(newPhoneList);
//		// 如果旧手机号同组中已经有成单的证件号 则 设置成单信息
//		if (!ArrayHelper.isEmpty(oldCardList) && oldCardList.get(0).getOrderTime() != null) {
//			oldOrder = oldCardList.get(0);
//		}
//		// 如果新手机号同组中已经有成单的证件号 则 设置成单信息
//		if (!ArrayHelper.isEmpty(newCardList) && newCardList.get(0).getOrderTime() != null) {
//			newOrder = newCardList.get(0);
//		}
//
//		if (oldPhone != null) {
//			// 新手机号在系统中存在
//			if (newPhone != null) {
//				// 新手机号在系统中存在理财经理
//				if (StringUtils.isNotEmpty(newPhone.getEmpCode())) {
//					// 新旧手机号都成单
//					if (oldOrder != null && newOrder != null) {
//						try {
//							Date oldOrderTime = oldOrder.getOrderTime();
//							Date newOrderTime = newOrder.getOrderTime();
//							if (newOrderTime.before(oldOrderTime)) {
//								// 客户归属变更记录
//								sjhBg(paramBean, newOrder.getEmpCode());
//								nonExistent(paramBean, newOrder.getEmpCode());
//								findCardGroup(paramBean, oldOrder.getCardId(), newOrder.getEmpCode());
//							} else {
//								// 客户归属变更记录
//								sjhBg(paramBean, oldOrder.getEmpCode());
//								nonExistent(paramBean, oldOrder.getEmpCode());
//								findCardGroup(paramBean, newOrder.getCardId(), oldOrder.getEmpCode());
//							}
//						} catch (Exception e) {
//							logger.error("手机号：" + paramBean.getOldPhone() + "变更为：" + paramBean.getNewPhone() + "异常", e);
//							return false;
//						}
//					} else if (oldOrder != null && newOrder == null) {
//						// 客户归属变更记录
//						sjhBg(paramBean, oldOrder.getEmpCode());
//						nonExistent(paramBean, oldOrder.getEmpCode());
//					} else if (oldOrder == null && newOrder != null) {
//						// 客户归属变更记录
//						sjhBg(paramBean, newOrder.getEmpCode());
//						nonExistent(paramBean, newOrder.getEmpCode());
//					} else {
//						// 新旧手机号都未成单
//						try {
//							Date newCreateTime = newPhone.getCreateTime();
//							Date oldCreateTime = oldPhone.getCreateTime();
//							if (newCreateTime.before(oldCreateTime)) {
//								// 客户归属变更记录
//								sjhBg(paramBean, newPhone.getEmpCode());
//								nonExistent(paramBean, newPhone.getEmpCode());
//							} else {
//								// 客户归属变更记录
//								sjhBg(paramBean, oldPhone.getEmpCode());
//								nonExistent(paramBean, oldPhone.getEmpCode());
//							}
//						} catch (Exception e) {
//							logger.error("手机号：" + paramBean.getOldPhone() + "变更为：" + paramBean.getNewPhone() + "异常", e);
//							return false;
//						}
//					}
//				} else {
//					// 客户归属变更记录
//					sjhBg(paramBean, oldPhone.getEmpCode());
//					nonExistent(paramBean, oldPhone.getEmpCode());
//				}
//			} else {
//				// 客户归属变更记录
//				sjhBg(paramBean, oldPhone.getEmpCode());
//				nonExistent(paramBean, oldPhone.getEmpCode());
//			}
//		}
//		return true;
//	}
//	
//
//	private void sjhBg(TripleChangePhoneInBean paramBean, String newEmpCode) {
//		IntDeliveryEx deliveryEx = new IntDeliveryEx();
//		// 查询同组手机号
//		List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(paramBean.getNewPhone());
//		if (ArrayHelper.isNotEmpty(listPhone)) {
//			// 如果新理财经理是电销，查询交割前的理财经理
//			IntDeliveryEx dxDelivery = null;
//			List<IntDeliveryEx> deliveryList = tripleCustomerDeliveryManager.findDeliveryByPhone(listPhone);
//			if (ArrayHelper.isNotEmpty(deliveryList)) {
//				dxDelivery = deliveryList.get(0);
//			}
//			for (IntPhoneCard ipc : listPhone) {
//				IntPhone intPhone = new IntPhone();
//				intPhone.setPhone(ipc.getPhone());
//				List<IntPhone> phoneList = tripleNewCustomerDao.searchCustomerByPhoneNum(intPhone);
//				if (ArrayHelper.isNotEmpty(phoneList)) {
//					for (IntPhone ip : phoneList) {
//						if ((newEmpCode!=null&&!newEmpCode.equals(ip.getEmpCode()))||(newEmpCode==null&&ip.getEmpCode()!=null)) {
//						//if (!newEmpCode.equals(ip.getEmpCode())) {
//							deliveryEx.setEmpCode(ip.getEmpCode());
//							deliveryEx.setNewEmpCode(newEmpCode);
//							deliveryEx.setLoginName(ip.getLoginName());
//							deliveryEx.setOsType(ip.getOsType());
//							deliveryEx.setPhone(paramBean.getNewPhone());
//							deliveryEx.setCustomerCode(ip.getCustomerCode());
//							if (!tripleOwnershipService.isSatisfy(newEmpCode)) {
//								tripleOwnershipService.sjhBg(paramBean, deliveryEx, dxDelivery, "1");
//							} else {
//								tripleOwnershipService.sjhBg(paramBean, deliveryEx, dxDelivery, "2");
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	private void nonExistent(TripleChangePhoneInBean paramBean, String empCode) {
//		// 新手机号在三网系统中不存在,没有理财经理
//		IntPhone p = new IntPhone();
//		p.setPhone(paramBean.getOldPhone());
//		p.setNewPhone(paramBean.getNewPhone());
//		p.setOsType(paramBean.getOsType());
//		p.setEmpCode(empCode);
//		tripleNewCustomerDao.updatePhone(p);
//		// 插入中间表
//		if (StringUtils.isNotEmpty(paramBean.getCardId())) {
//
////			IntPhoneCard c = new IntPhoneCard();
////			c.setPhone(paramBean.getOldPhone());
////			c.setCardId(paramBean.getCardId());
////			tripleNewCustomerDao.deletePhoneCard(c);
//			deletePhoneCard(paramBean);
//			
//			// 查询证件号和手机号是否在中间表存在
//			IntPhoneCard ipc1 = new IntPhoneCard();
//			ipc1.setPhone(paramBean.getNewPhone());
//			ipc1.setCardId(paramBean.getCardId());
//			List<IntPhoneCard> ipcList = tripleNewCustomerDao.findPhoneByCertNum(ipc1);
//			if (ipcList.size() == 0) {
//				IntPhoneCard ipc = new IntPhoneCard();
//				ipc.setId(IdGen.uuid());
//				ipc.setPhone(paramBean.getNewPhone());
//				ipc.setCreateTime(new Date());
//				ipc.setCardId(paramBean.getCardId());
//				tripleNewCustomerDao.insertIntPhoneCard(ipc);
//			}
//		}
//		// 推送到各系统
//		tripleCommonService.sendChangePhoneInfo(paramBean.getNewPhone(), paramBean.getOldPhone(), empCode, paramBean.getOsType());
//	}
//
//	private void findCardGroup(TripleChangePhoneInBean paramBean, String cardId, String empCode) {
//		// 修改新手机号对应证件号对应的理财经理
//		IntCard ic = new IntCard();
//		ic.setCardId(cardId);
//		ic.setEmpCode(empCode);
//		tripleNewCustomerDao.updateEmpCodeByCertNum(ic);
//
//		List<IntPhoneCard> listPhone = infoGroupService.groupByCard(cardId);
//		if (listPhone.size() > 0) {
//			// 修改证件号同组的手机号对应的理财经理
//			for (int i = 0; i < listPhone.size(); i++) {
//				IntPhone auP = new IntPhone();
//				auP.setPhone(listPhone.get(i).getPhone());
//				auP.setEmpCode(empCode);
//				tripleNewCustomerDao.updateEmpCodeByPhone(auP);
//				try {
//					// 修改chp手机号对应理财经理
//					tripleCustomerDeliveryManager.updateChpEmpCode(listPhone.get(i).getPhone(), empCode, OsType.CHP.value);
//				} catch (Exception e) {
//					this.logger.error("同步手机号：" + listPhone.get(i).getPhone() + "对应理财经理到CHP异常！", e);
//				}
//				// 发送三网对应信息
//				if (!paramBean.getNewPhone().equals(listPhone.get(i).getPhone())) {
//					tripleCommonService.sendChangePhoneInfo(listPhone.get(i).getPhone(), paramBean.getOldPhone(), empCode, paramBean.getOsType());
//				}
//			}
//		}
//	}
//	
//	private void deletePhoneCard (TripleChangePhoneInBean paramBean){
//		IntPhone iphone=new IntPhone();
//		iphone.setPhone(paramBean.getOldPhone());
//		List<IntPhone> phones=tripleNewCustomerDao.searchCustomerByPhoneNum(iphone);
//		for(int i = 0;i<phones.size();i++){
//			IntPhone item= phones.get(i);
//			if(null!=item.getOsType()&&!item.getOsType().equals(paramBean.getOsType())){
//				//如果存在其他系统也存在该手机号的情况则 直接返回不进行删除
//				return;
//			}
//		}
//		
//		IntPhoneCard c = new IntPhoneCard();
//		c.setPhone(paramBean.getOldPhone());
//		c.setCardId(paramBean.getCardId());
//		tripleNewCustomerDao.deletePhoneCard(c);
//	}
}
