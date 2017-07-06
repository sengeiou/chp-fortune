package com.creditharmony.fortune.appweishang.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.appweishang.bean.UserConsultation;
import com.creditharmony.fortune.appweishang.dao.UserConsultationDao;

/**
 * 我的微服接口服务
 * @author colin
 *
 */
@Service
public class UserConsultationServer {
	
	private Logger logger = LoggerFactory.getLogger(UserConsultationServer.class);
	
	@Autowired
	private UserConsultationDao userConsultationDao;
	
	public int insertConsultationUserInfo(UserConsultation userConsultation){
		int i = 0;
		try {
			logger.info("我的微服务接口新增咨询内容--开始--"+userConsultation.toString());
			userConsultationDao.insertConsultationUserInfo(userConsultation);
			logger.info("我的微服务接口新增咨询内容--结束--"+userConsultation.toString());
			i = 1;
		} catch (Exception e) {
			logger.info("我的微服务接口新增咨询内容--失败--"+userConsultation.toString());
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 我的微服务查询所有的咨询内容
	 * @param page
	 * @param userConsultation
	 * @return
	 */
	public Page<UserConsultation> findConsultationList(Page<UserConsultation> page,UserConsultation userConsultation){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		pageBounds.setCountBy("manager_id");
		try {
			logger.info("我的微服查询所有咨询内容--开始--"+userConsultation.toString());
			PageList<UserConsultation> pageList = (PageList<UserConsultation>)userConsultationDao
					.findConsultationList(userConsultation, pageBounds);
			logger.info("我的微服查询所有咨询内容--结束--"+userConsultation.toString());
			PageUtil.convertPage(pageList, page);
		} catch (Exception e) {
			logger.info("我的微服查询所有咨询内容--失败--"+userConsultation.toString());
			e.printStackTrace();
		}
		return page;
	}

}
