package com.creditharmony.fortune.back.money.excute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.ThirdPlatformDao;
import com.creditharmony.fortune.back.money.common.entity.ThirdPlatform;

/**
 * 第三方平台操作Service
 * @Class Name PlanformManager
 * @author 陈广鹏
 * @Create In 2015年12月3日
 */
@Service
public class ThirdPlatformManager extends CoreManager<ThirdPlatformDao, ThirdPlatform> {
	
	@Autowired
	private ThirdPlatformDao platformDao;
	/**
	 * 获取第三方平台列表
	 * 2015年12月15日
	 * By 陈广鹏
	 * @param platform
	 * @return
	 */
	public List<ThirdPlatform> findAllList(ThirdPlatform platform){
		return platformDao.findAllList(platform);
	}

}
