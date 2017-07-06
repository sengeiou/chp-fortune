package com.creditharmony.fortune.app.webservice.development.server;

import javax.jws.WebService;

/**
 * 员工展业财富接口服务
 * @author 王俊杰
 * @date 2016-5-10
 */
@WebService
public interface EmpHelperServer {

	/**
	 * 财富登录
	 * @author 王俊杰
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	String login(String jsonStr);
	
	
	
	/**
	 * 省市营业部查询接口,三级联动的一个接口
	 * 2016年5月23日
	 * By zhanghu
	 * @param json
	 * @return
	 */
	public String getPCAndOrg(String json);
	
	
	/**
	 * 财富业绩进度列表查询
	 * @author 周怀富
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	String queryAchievementScheduleList(String jsonStr);

	
}
