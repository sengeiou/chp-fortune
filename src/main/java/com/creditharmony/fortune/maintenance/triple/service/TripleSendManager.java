package com.creditharmony.fortune.maintenance.triple.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.maintenance.triple.dao.TripleSendDao;
import com.creditharmony.fortune.maintenance.triple.view.TripleView;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;

/**
 * 三网维护发送历史维护
 * @Class Name TripleSendManager
 * @author 周俊
 * @Create In 2016年5月20日
 */
@Service
public class TripleSendManager {

	@Autowired
	private TripleSendDao tripleSendDao;
	
	/**
	 * 发送历史____客户理财经理变更查询
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public Page<IntCustomerBean> findManagerChange(Page<IntCustomerBean> page,TripleView tripleView){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sendTimeFrom",tripleView.getSendTimeFrom());
		map.put("sendTimeTo",tripleView.getSendTimeTo());
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntCustomerBean> pageList = tripleSendDao.findManagerChange(map, pageBounds);
		for (IntCustomerBean intCustomerBean : pageList) {
			intCustomerBean.setRemarks(JsonMapper.toJsonString(intCustomerBean));
		}
		PageUtil.convertPage(pageList, page);
		return page;
	}
	  
	/**
	 * 发送历史____组织机构
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public Page<IntOrgBean> findOrgChange(Page<IntOrgBean> page,TripleView tripleView){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sendTimeFrom",tripleView.getSendTimeFrom());
		map.put("sendTimeTo",tripleView.getSendTimeTo());
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntOrgBean> pageList = tripleSendDao.findOrgChange(map, pageBounds);
		for (IntOrgBean intOrgBean : pageList) {
			intOrgBean.setRemarks(JsonMapper.toJsonString(intOrgBean));
		}
		PageUtil.convertPage(pageList, page);
		return page;
	}
	 
	/**
	 * 发送历史____用户同步
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public Page<IntEmployeeBean> findUserSynchro(Page<IntEmployeeBean> page,TripleView tripleView){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sendTimeFrom",tripleView.getSendTimeFrom());
		map.put("sendTimeTo",tripleView.getSendTimeTo());
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<IntEmployeeBean> pageList = tripleSendDao.findUserSynchro(map, pageBounds);
		for (IntEmployeeBean intEmployeeBean : pageList) {
			intEmployeeBean.setRemarks(JsonMapper.toJsonString(intEmployeeBean));
		}
		PageUtil.convertPage(pageList, page);
		return page;
	}
}
