
package com.creditharmony.fortune.triple.system.service;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.service.triple.bean.TripleFirstOrderInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.OrderStatus;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.facade.TripleFirstOrderFacade;

/**
 * 客户首单接口调用
 * @Class Name TripleFirstOrderServiceImp
 * @author 胡体勇
 * @Create In 2016年1月22日
 */
@Service
public class TripleFirstOrderServiceImp{
	
	/** 日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleFirstOrderServiceImp.class);
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;// 三网客户查询
	@Autowired
	private TripleFirstOrderFacade tripleFirstOrderFacade;
	@Autowired
	private InfoGroupService infoGroupService;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private TripleCommonService tripleCommonService;
	
	
	/**
	 * 
	 * 2016年3月11日
	 * By 胡体勇
	 * @param paramBean
	 *  paramBean 首单信息里有 客户手机号码、证件类型、证件号码、理财经理员工号、待首单时间（取当前系统时间）
	 * @return
	 * @throws Exception 
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
    public boolean firstOrder(TripleFirstOrderInBean paramBean) throws Exception{
		// 插入日志
		tripleCommonService.insertLog(paramBean, Constant.SYSNCNUM_KHSDXXTB, "L");
		// 转换时间格式
		Date orderTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotEmpty(paramBean.getFirstOrderTime())){	//首单时间判断
			try {
				orderTime = sdf.parse(paramBean.getFirstOrderTime());
			} catch (ParseException e) {
				this.logger.error("时间转换出错", e);
			}
		}
		if (StringUtils.isEmpty(paramBean.getCardId())) {
			logger.error("调用三网首单接口，证件号不能为空！");
			throw new Exception("调用三网首单接口，证件号不能为空！");
		}
		
        // 查询证件号是否存在于三网
 		IntCard intCard = new IntCard();
 		intCard.setCardId(paramBean.getCardId());
 		List<IntCard> card = tripleNewCustomerDao.searchCustomerByCertNum(intCard);	//根据客户证件号查出客户证件信息
 		String empCode=null;
        if(card.size() == 0){
        	// 查询证件号和手机号是否在中间表存在  如果不存在则插入 （先插入方便查询同组）
        	tripleCommonService.insertPhoneCard(paramBean.getPhone(), paramBean.getCardId());
    		// 查询证件号对应的手机号
    		List<IntPhoneCard> phoneList = infoGroupService.groupByCard(paramBean.getCardId());
    		List<IntCard> cardList = tripleCommonService.getCardList(phoneList);
    		empCode=tripleCommonService.getEmpCode(cardList, paramBean.getEmpCode());

        	// 证件号不存在三网
        	// 设置证件号信息插入证件号理财经理对应表
			IntCard id = new IntCard();
			id.setId(IdGen.uuid()); 
			id.setCardType(paramBean.getCardType());
			id.setCardId(paramBean.getCardId());
			id.setEmpCode(empCode);
			// 消息推送到三网
			tripleCommonService.sendFistOrderInfo(paramBean.getPhone(), empCode,
					paramBean.getCardId(), paramBean.getCardType());
			
			id.setOrderStatus(OrderStatus.YCD.value);
			id.setOrderTime(orderTime);
			id.setCreateTime(new Date());
			tripleFirstOrderFacade.insertIntCard(id);
			
        }else{
        	tripleCommonService.insertPhoneCard(paramBean.getPhone(), paramBean.getCardId());
        	// 推送的证件号在三网中存在,判断是否已成单
        	if(StringUtils.isEmpty(card.get(0).getOrderStatus()) || OrderStatus.WCD.value.equals(card.get(0).getOrderStatus())
        			||StringUtils.isEmpty(card.get(0).getEmpCode())
        			||( StringUtils.isNotEmpty(paramBean.getEmpCode())&&!card.get(0).getEmpCode().equals(paramBean.getEmpCode()))){
        		//-------------查询归属的理财经理  同组已成单则使用同组成单最早的理财经理（开始）----------------------------------------
        		// 查询证件号对应的手机号
        		List<IntPhoneCard> phoneList = infoGroupService.groupByCard(paramBean.getCardId());
        		List<IntCard> cardList = tripleCommonService.getCardList(phoneList);
        		empCode=tripleCommonService.getEmpCode(cardList, paramBean.getEmpCode());
        		//-------------查询归属的理财经理  同组已成单则使用同组成单最早的理财经理（结束）----------------------------------------
        		
        		//未成单根据理财经理  更新 成单状态和理财经理 信息
    			IntCard cardId = new IntCard();
    			cardId.setCardId(paramBean.getCardId());
    			cardId.setEmpCode(empCode);
    			//如果证件号已成单则 不更新成单时间和成单状态 只更新理财经理
    			if(!OrderStatus.YCD.value.equals(card.get(0).getOrderStatus())){
    				cardId.setOrderTime(orderTime);
    				cardId.setOrderStatus(OrderStatus.YCD.value);
    			}
				tripleFirstOrderFacade.updateEmpCodeByCertNum(cardId);
				if(phoneList.size() > 0){
					for(int i = 0;i<phoneList.size();i++){
						IntPhone p = new IntPhone();
						p.setPhone(phoneList.get(i).getPhone());
						p.setEmpCode(empCode);
						// 修改手机号对应的理财经理
						tripleFirstOrderFacade.updateEmpCodeByPhone(p);
						try {
							// 修改chp手机号对应理财经理
							tripleCustomerDeliveryManager.updateChpEmpCode(phoneList.get(i).getPhone(),empCode,OsType.CHP.value);
						} catch (Exception e) {
							this.logger.error("首单理财经理发生变更，同步手机号："+phoneList.get(i).getPhone()+"到chp异常！", e);
						}
						// 推送信息到三网
						tripleCommonService.sendFistOrderInfo(phoneList.get(i).getPhone(),
								empCode,
								paramBean.getCardId(),
								paramBean.getCardType());
					}
				}
				if (ArrayHelper.isNotEmpty(cardList)) {
					for (IntCard ic: cardList) {
						IntCard auC = new IntCard();
						auC.setCardId(ic.getCardId());
						auC.setEmpCode(empCode);
						tripleNewCustomerDao.updateEmpCodeByCertNum(auC);
					}
				}
        	}else if(OrderStatus.YCD.value.equals(card.get(0).getOrderStatus())){
        		// 如果已成单则使用原来的理财经理，推送信息到三网
				tripleCommonService.sendFistOrderInfo(paramBean.getPhone(), card.get(0).getEmpCode(), paramBean.getCardId(),
						paramBean.getCardType());
        	}
        }
		return true;
    }
	
}
