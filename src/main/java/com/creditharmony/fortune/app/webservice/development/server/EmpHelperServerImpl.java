package com.creditharmony.fortune.app.webservice.development.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.PasswordUtil;
import com.creditharmony.fortune.app.webservice.development.dao.EmpDao;
import com.creditharmony.fortune.app.webservice.development.utils.Util;
import com.creditharmony.fortune.app.webservice.ocr.entity.ResultBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.UserBean;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;

/**
 * 员工展业财富接口服务
 * @author 王俊杰
 * @date 2016-5-10
 */
@Transactional(readOnly = true,value="fortuneTransactionManager")
public class EmpHelperServerImpl implements EmpHelperServer {

	private Logger logger = LoggerFactory.getLogger(EmpHelperServerImpl.class);
	
	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private UserManager userManager;
	
	// 三网接口
	@Resource
	TripleCheckInfoService tripleCheckInfoService;
	
	/**
	 * 查出登陆者信息，如果登陆者为营业部经理则待会门店旗下所有理财经理的信息
	 * 2016年5月27日
	 * By zhanghu
	 * @param json
	 * @return
	 */
	@Override
	public String login(String jsonStr) {
		logger.info("APP员工展业财富登录用户信息:\t" + jsonStr);
		
		UserBean userBean = JsonMapper.nonDefaultMapper().fromJson(jsonStr, UserBean.class);
		String json = "";
		ResultBean rb = null;
		if (StringUtils.isNotEmpty(userBean.getLoginName()) && StringUtils.isNotEmpty(userBean.getPassword())){
			if (PasswordUtil.validPassword(userBean.getLoginName(), userBean.getPassword())){
				//验证通过，判断是否为客户经理、团队经理、门店经理
				List<String> roleList = empDao.selectRoleIdList(userBean.getLoginName());
				for (String roleId : roleList){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("result", true);

					//如果是门店经理，登陆成功
					if (FortuneRole.STORE_MANAGER.id.equals(roleId)){
						Map<String,Object> userInfo = empDao.getStoreUserMessage(userBean.getLoginName());
						userInfo.put("role", FortuneRole.STORE_MANAGER.name);
						
						json = getLoginReturnMessage(userInfo, map);
						return json;
					}
				}
				rb = new ResultBean(false, "登录用户不是门店经理，不允许登录");
				json = JsonMapper.nonDefaultMapper().toJson(rb);
				return json;
			}else{
				rb = new ResultBean(false, "用户名或密码不正确");
				json = JsonMapper.nonDefaultMapper().toJson(rb);
				return json;
			}
		} else {
			rb = new ResultBean(false, "用户名和密码不能为空");
			json = JsonMapper.nonDefaultMapper().toJson(rb);
			return json;
		}
	}
	
	/**
	 * 省市营业部查询接口,三级联动的一个接口
	 * 2016年5月23日
	 * By zhanghu
	 * @param json
	 * @return
	 */
	public String getPCAndOrg(String json){
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		String type = jsonObject.getString("type");
		String provinceId = jsonObject.getString("provinceId");
		String cityId = jsonObject.getString("cityId");
		
		List<Map<String, Object>> areas = null;
		List<Map<String, Object>> orgs = null;
		
		Map<String, Object> qry = new HashMap<String, Object>();
		if("3".equals(type)){
			if(!"".equals(cityId)&&cityId!=null){
				qry.put("cityId", cityId);
			} else {
				qry.put("provinceId", provinceId);
			}
			orgs = this.getOrgs(qry);
			return JsonMapper.nonDefaultMapper().toJson(Util.keyToUpperCase(orgs));
		} else {
			qry.put("areaType", type);
			qry.put("parentId", provinceId);
			areas = this.getAreas(qry);
			return JsonMapper.nonDefaultMapper().toJson(Util.keyToUpperCase(areas));
		}
		
	}
	
	/**
	 * 根据用户名查询出用户信息
	 * 2016年5月24日
	 * By zhanghu
	 * @param json
	 * @return
	 */
	public String authUser(String json){
		
		JSONObject jsonObj = JSONObject.parseObject( json );
		String userName = jsonObj.getString("userName");
		
		Map<String, Object> map = this.getUserByUsername(userName);
		return JsonMapper.nonDefaultMapper().toJson(map);
		
	}
	
	/**
	 * 根据用户名查询出用户信息
	 * 2016年5月24日
	 * By zhanghu
	 * @param userName
	 * @return
	 */
	public Map<String, Object> getUserByUsername(String userName) {
		Map<String, Object> map = (Map<String, Object>) empDao.getUserByUsername(userName);
		return map;
	}
	
	/**
	 * 获取省市数据
	 * 2016年5月24日
	 * By zhanghu
	 * @param qry
	 * @return
	 */
	public List<Map<String, Object>> getAreas(Map<String, Object> qry) {
		List<Map<String, Object>> areas = empDao.getAreas(qry);
		return areas;
	}

	/**
	 * 获取营业部数据
	 * 2016年5月24日
	 * By zhanghu
	 * @param qry
	 * @return
	 */
	public List<Map<String, Object>> getOrgs(Map<String, Object> qry) {
		List<Map<String, Object>> orgs = empDao.getOrgs(qry);
		return orgs;
	}
	
	/**
	 * 登录成功后，获取返回的数据
	 * @author 王俊杰
	 * @date 2016-5-10
	 * @param userInfo
	 * @param map
	 * @param roleId
	 * @return
	 */
	private String getLoginReturnMessage(Map<String,Object> userInfo, Map<String,Object> map){
		userInfo.put("userId", userInfo.get("userid"));
		userInfo.put("loginName", userInfo.get("loginname"));
		userInfo.put("userName", userInfo.get("username"));
		userInfo.put("mobileNo", userInfo.get("mobileno"));
		userInfo.put("officeTime", userInfo.get("officeiime"));
		
		//查询直属下属员工号需要三个参数
		List<String> empCodeList = empDao.geUserCodeList((String)userInfo.get("userId"));
		userInfo.put("staffs", empCodeList);
		
		//移除不需要返回的信息
		userInfo.remove("userid");
		userInfo.remove("loginname");
		userInfo.remove("username");
		userInfo.remove("mobileno");
		userInfo.remove("officeiime");

		map.put("userInfo", userInfo);
		String json = JsonMapper.nonDefaultMapper().toJson(map);
		logger.info("APP员工展业登陆成功，返回信息：" + json);
		return json;
	}
	
	

	/**
	 * 财富业绩进度列表查询
	 * @author 周怀富
	 * @date 2016-5-10
	 * @param jsonStr
	 * @return
	 */
	public String queryAchievementScheduleList(String jsonStr) {
		return null;
	}
	
}
