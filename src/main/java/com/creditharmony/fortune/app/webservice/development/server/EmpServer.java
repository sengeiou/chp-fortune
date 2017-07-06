package com.creditharmony.fortune.app.webservice.development.server;

import javax.jws.WebService;

/**
 * 员工展业财富接口服务
 * @author 王俊杰
 * @date 2016-5-10
 */
@WebService
public interface EmpServer {

	/**
	 * 财富登录
	 * @author 王俊杰
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	String login(String jsonStr);
	
	/**
	 * 财富修改密码
	 * @author 南金陵
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	String modifyPassword(String jsonStr);
	
	/**
	 * 财富业绩分配列表查询
	 * @author 周怀富
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	String queryAchievementList(String jsonStr);
	
	
	
}
