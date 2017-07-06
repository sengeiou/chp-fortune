package com.creditharmony.fortune.maintenance.triple.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.maintenance.triple.dao.TripleMaintainDao;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.InfoGroupService;
import com.creditharmony.fortune.triple.system.service.TripleCallBackService;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerServiceImp;

/**
 * 三网维护交割维护
 * @Class Name TripleMaintainManager
 * @author 周俊
 * @Create In 2016年5月21日
 */
@Service
public class TripleMaintainManager {

	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleMaintainManager.class);
	@Autowired
	private TripleMaintainDao tripleMaintainDao;
	
	@Autowired
	private InfoGroupService infoGroupService;
	
	@Autowired
	private TripleNewCustomerService tripleNewCustomerService;
	
	@Autowired
	private TripleCallBackService tripleCallBackService;
	
	/**
	 * 查询交割维护列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public Page<IntDeliveryEx> findList(Page<IntDeliveryEx> page,IntDeliveryEx intDeliveryEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if(!StringUtils.isEmpty(intDeliveryEx.getPhone()) || !StringUtils.isEmpty(intDeliveryEx.getCardId())){
			List<String> phoneList = new ArrayList<String>();
			// 根据手机号查询同组手机号
			if(!StringUtils.isEmpty(intDeliveryEx.getPhone())){
				List<IntPhoneCard> listPhone = infoGroupService.groupByPhone(intDeliveryEx.getPhone());
				for(int j = 0;j<listPhone.size();j++){
					phoneList.add(listPhone.get(j).getPhone());
				}	
				}else{
					phoneList.add("00000000000");
					intDeliveryEx.setPhoneList(phoneList);
				}
			// 证件号查询同组手机号
			if(!StringUtils.isEmpty(intDeliveryEx.getCardId())){
				List<IntPhoneCard> listPhone = infoGroupService.groupByCard(intDeliveryEx.getCardId());
				for(int j = 0;j<listPhone.size();j++){
					phoneList.add(listPhone.get(j).getPhone());
				}
			}
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
		
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) tripleMaintainDao.findList(intDeliveryEx, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查询交割履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public Page<IntDeliveryEx> findDeliveryRecord(Page<IntDeliveryEx> page, String phone){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) tripleMaintainDao.findDeliveryRecord(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查询发送履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public Page<IntDeliveryEx> findSendRecord(Page<IntDeliveryEx> page, String phone){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) tripleMaintainDao.findSendRecord(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查询操作履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public Page<IntDeliveryEx> findOperateRecord(Page<IntDeliveryEx> page, String phone){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) tripleMaintainDao.findOperateRecord(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	public int syncDate(){
		int result = 0;
		List<IntDeliveryEx> list = tripleMaintainDao.findSyncDate();
		if(list.size() > 0){
			for(int i = 0;i<list.size();i++){
				if(list.get(i) != null){
					TripleNewCustomerInBean paramBean = new TripleNewCustomerInBean();
					paramBean.setOsType("XH_CHP");
					paramBean.setLoginName(list.get(i).getLoginName());
					paramBean.setPhone(list.get(i).getPhone());
					paramBean.setOsId(list.get(i).getCustomerCode());
					IntDeliveryEx ex = tripleMaintainDao.findEmpCode(list.get(i).getEmpId());
					if(ex != null){
						paramBean.setEmpCode(ex.getEmpCode());
					}else{
						paramBean.setEmpCode("");
					}
					try {
						tripleNewCustomerService.registerCustomer(BeanUtil.conv2IntCustomerBean(paramBean,null));
					} catch (Exception e) {
						this.logger.error("同步APP遗漏数据出错",e);
					}
					result++;
				}
			}
			
		}
		IntDeliveryEx ex = new IntDeliveryEx();
		ex.setOsType("XH_DJR");
		tripleMaintainDao.updateOsType(ex);
		return result;
	}
	
	public int updateUnm(String osId,String uniqueNum,String sendStatus){
		int result = 0;
		// A014: int_customer A010:int_org A011:int_employee
		if("A010".equals(osId)){
			IntOrgBean bean = new IntOrgBean();
			bean.setUniqueNum(uniqueNum.trim());
			bean.setSendStatus(sendStatus);
			result = tripleCallBackService.updateOrgSendStatus(bean);
		}else if("A011".equals(osId)){
			IntEmployeeBean bean = new IntEmployeeBean();
			bean.setUniqueNum(uniqueNum.trim());
			bean.setSendStatus(sendStatus);
			result = tripleCallBackService.updateEmployeeSendStatus(bean);
		}else if("A014".equals(osId)){
			IntCustomerBean bean = new IntCustomerBean();
			bean.setUniqueNum(uniqueNum.trim());
			bean.setSendStatus(sendStatus);
			result = tripleCallBackService.updateSendStatusDao(bean);
		}
		return result;
	}
}
