package com.creditharmony.fortune.appweishang.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.fortune.appweishang.bean.EmpUser;
import com.creditharmony.fortune.appweishang.dao.EmpUserDao;

/**
 * 我的微服接口服务
 * @author colin
 *
 */
@Service
public class EmpUserServer {
	
	private Logger logger = LoggerFactory.getLogger(EmpUserServer.class);
	
	@Autowired
	private EmpUserDao empUserDao;
	
	public EmpUser getEMPuserInfo(String userCode){
		EmpUser empuser = null;
		try {
			logger.info("我的微服务接口查询用户的Code--开始--"+userCode);
			empuser = empUserDao.getInfoByUserCode(userCode);
			logger.info("我的微服务接口查询用户的Code--结束--"+userCode);
		} catch (Exception e) {
			logger.info("我的微服务接口查询用户的Code--失败--"+userCode);
			e.printStackTrace();
		}
		return empuser;
		
	}

}
