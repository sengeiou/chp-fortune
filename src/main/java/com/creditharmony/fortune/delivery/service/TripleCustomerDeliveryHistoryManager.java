
package com.creditharmony.fortune.delivery.service;


import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryHistoryDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网客户交割历史查询
 * @Class Name TripleCustomerDeliveryHistoryManager
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@Service
public class TripleCustomerDeliveryHistoryManager extends CoreManager<TripleCustomerDeliveryHistoryDao,IntDeliveryEx>{
		 
	/**
	 * 分页查询
	 * 2015年12月2日
	 * By 胡体勇
	 * @param page
	 * @param intDeliveryEx
	 * @return
	 */
	public Page<IntDeliveryEx> findPage(Page<IntDeliveryEx> page,IntDeliveryEx intDeliveryEx){
		
		// 设置查询条件
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if (!StringUtils.isEmpty(intDeliveryEx.getNewOrgId())) {
			String[] ids = intDeliveryEx.getNewOrgId().split(",");
			intDeliveryEx.setNewOrgIdList(Arrays.asList(ids));
		}
		if(StringUtils.isNotEmpty(intDeliveryEx.getOrgId())) {
			String[] ids = intDeliveryEx.getOrgId().split(",");
			intDeliveryEx.setOrgIdList(Arrays.asList(ids));
		}
		if(!StringUtils.isEmpty(intDeliveryEx.getOsType())){
			String[] types = intDeliveryEx.getOsType().split(",");
			intDeliveryEx.setOsTypeList(Arrays.asList(types));
		}
		if(StringUtils.isNotEmpty(intDeliveryEx.getDeliveryType())){
			String[] types = intDeliveryEx.getDeliveryType().split(",");
			intDeliveryEx.setDeliveryTypeList(Arrays.asList(types));
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<IntDeliveryEx> pageList = (PageList<IntDeliveryEx>) super.dao.findList(intDeliveryEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		
		return page;
	}
	
	/**
	 * 导出Excel
	 * 2016年2月27日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public IntDeliveryEx outExcel(IntDeliveryEx intDeliveryEx){
		intDeliveryEx.setOrgType(FortuneOrgType.STORE.key);
		intDeliveryEx.setManagerRole(FortuneRole.FINANCING_MANAGER.id);
		intDeliveryEx.setTeamManagerRole(FortuneRole.TEAM_MANAGER.id);
		if (!StringUtils.isEmpty(intDeliveryEx.getNewOrgId())) {
			String[] ids = intDeliveryEx.getNewOrgId().split(",");
			intDeliveryEx.setNewOrgIdList(Arrays.asList(ids));
		}
		if(StringUtils.isNotEmpty(intDeliveryEx.getOrgId())) {
			String[] ids = intDeliveryEx.getOrgId().split(",");
			intDeliveryEx.setOrgIdList(Arrays.asList(ids));
		}
		if(!StringUtils.isEmpty(intDeliveryEx.getOsType())){
			String[] types = intDeliveryEx.getOsType().split(",");
			intDeliveryEx.setOsTypeList(Arrays.asList(types));
		}
		if(StringUtils.isNotEmpty(intDeliveryEx.getDeliveryType())){
			String[] types = intDeliveryEx.getDeliveryType().split(",");
			intDeliveryEx.setDeliveryTypeList(Arrays.asList(types));
		}
		return intDeliveryEx;
	}
}
