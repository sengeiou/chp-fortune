package com.creditharmony.fortune.app.webservice.development.server;

import java.text.DecimalFormat;
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
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.PasswordUtil;
import com.creditharmony.fortune.app.webservice.development.dao.EmpDao;
import com.creditharmony.fortune.app.webservice.ocr.entity.Achievement;
import com.creditharmony.fortune.app.webservice.ocr.entity.ResultBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.UserBean;
import com.creditharmony.fortune.app.webservice.ocr.utils.DataTypeUtils;
import com.creditharmony.fortune.app.webservice.ocr.utils.ExceptionUtil;
import com.creditharmony.fortune.app.webservice.ocr.utils.Utils;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;

/**
 * 员工展业财富接口服务
 * @author 王俊杰
 * @date 2016-5-10
 */
@Transactional(readOnly = true,value="fortuneTransactionManager")
public class EmpServerImpl implements EmpServer {

	private Logger logger = LoggerFactory.getLogger(EmpServerImpl.class);
	
	@Autowired
	private EmpDao empDao;
	
	@Autowired
	private UserManager userManager;
	
	// 三网接口
	@Resource
	TripleCheckInfoService tripleCheckInfoService;
	
	/**
	 * @author 王俊杰
	 * @date 2016-5-10
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
					//如果是客户经理，登陆成功
					if (FortuneRole.FINANCING_MANAGER.id.equals(roleId)){
						Map<String,Object> userInfo = empDao.getUserMessage(userBean.getLoginName());
						userInfo.put("role", FortuneRole.FINANCING_MANAGER.name);
						json = getLoginReturnMessage(userInfo, map);
						return json;
					}
					//如果是团队经理，登陆成功
					if (FortuneRole.TEAM_MANAGER.id.equals(roleId)){
						Map<String,Object> userInfo = empDao.getUserMessage(userBean.getLoginName());
						userInfo.put("role", FortuneRole.TEAM_MANAGER.name);
						json = getLoginReturnMessage(userInfo, map);
						return json;
					}
					//如果是门店经理，登陆成功
					if (FortuneRole.STORE_MANAGER.id.equals(roleId)){
						Map<String,Object> userInfo = empDao.getStoreUserMessage(userBean.getLoginName());
						userInfo.put("role", FortuneRole.STORE_MANAGER.name);
						json = getLoginReturnMessage(userInfo, map);
						return json;
					}
				}
				rb = new ResultBean(false, "登录用户不是客户经理、团队经理、门店经理，不允许登录");
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
	 * @author NJl
	 * @date 2016-5-10
	 */
	@Override
	public String modifyPassword(String jsonStr) {
		String retJson = "";
		String oldPassword = "";
		String newPassword = "";
		String loginName = "";
		try {
			if (StringUtils.isBlank(jsonStr)) {
				 logger.info("【APP员工展页-传入修改登录密码参数为空！】");
				 return Utils.returnMessage(false, "传入参数不能为空!",DataTypeUtils.ERROR_JSONSTR_BLANK);
			} else {
					JSONObject jsStr = JSONObject.parseObject(jsonStr);
					oldPassword = jsStr.getString("password");
					newPassword = jsStr.getString("newPassword");
					loginName = jsStr.getString("loginName");
					if (StringUtils.isBlank(oldPassword)
							|| StringUtils.isBlank(newPassword)
							|| StringUtils.isBlank(loginName)) {
						 logger.info("【APP员工展页-用户名密码或新密码不能为空！】");
						 return Utils.returnMessage(false, "用户名密码或新密码不能为空",DataTypeUtils.ERROR_NECESSARY_PARAM_BLANK);
					}
					else if (StringUtils.isNotBlank(oldPassword)
							&& StringUtils.isNotBlank(newPassword)) {
						if (PasswordUtil.validPassword(loginName, oldPassword)) {
							userManager.updatePasswordById(loginName, loginName,
									newPassword);
							logger.info(retJson);
							return Utils.returnMessage(true, "密码修改成功");
						} else {
							logger.info(retJson);
							return Utils.returnMessage(false, "用户名或旧密码不正确");
						}
					}
			}
		}catch (Exception e) {
			logger.error("【APP员工展页修改密码失败:】\n" +ExceptionUtil.getStackTrace(e));
			e.getStackTrace();
			return Utils.returnMessage(false, "服务器异常",DataTypeUtils.ERROR_SERVER_ERROR);
		}
		return null;
	}
	
	/**
	 * @author 周怀富
	 * @date 2016-5-10
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public String queryAchievementList(String jsonStr) {
		logger.info("OCR财富业绩分配列表查询:\t" + jsonStr);
		List<Achievement> list = null;
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			Map<String, String> map = JsonMapper.nonDefaultMapper().fromJson(
					jsonStr, Map.class);
			if (map == null) {
				return Utils.returnMessage(false, "传入参数格式错误");
			}

			String empCode = (String) map.get("empCode");
			String month = (String) map.get("month");

			Map<String, String> qry = new HashMap<String, String>();
			Map<String, String> info = empDao.getInfoByEmpCode(empCode);
			String orgId = info.get("org_id");
			String roleId = info.get("role_id").trim();

			String type = "";

			if (FortuneRole.STORE_MANAGER.id.equals(roleId)) {
				type = "1"; // 门店经理

			} else if (FortuneRole.TEAM_MANAGER.id.equals(roleId)) {
				type = "2"; // 团队经理
			} else if (FortuneRole.FINANCING_MANAGER.id.equals(roleId)) {
				type = "3"; // 理财经理
			} else {
				return Utils.returnMessage(false, "身份不符合...");
			}

			qry.put("empCode", empCode);
			qry.put("month", month);
			qry.put("orgId", orgId);
			qry.put("type", type);
			list = computeAchievement(qry);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OCR--------queryAchievementList--------报错\n"
					+ ExceptionUtil.getStackTrace(e));
		}
		logger.error("OCR==========queryAchievementList结果:"
				+ JsonMapper.nonDefaultMapper().toJson(list));
		return JsonMapper.nonDefaultMapper().toJson(list);
	}
	


	
    /**
     * 计算业绩
     * 2016年5月11日
     * By 周怀富
     * @param qry
     * @return
     */
	public List<Achievement> computeAchievement(Map<String, String> qry) {
		int type = Integer.parseInt(qry.get("type"));
		List<Achievement> achievements = null;
		List<Map<String, String>> teamManagerInfo = null;
		DecimalFormat df = new DecimalFormat("#");
		qry.put("teamRoleId", FortuneRole.TEAM_MANAGER.id);
		qry.put("finaceRoleId", FortuneRole.FINANCING_MANAGER.id);
		qry.put("deductState", DeductState.HKCG.value);
		if (type == 1) {
			// 门店经理
			teamManagerInfo = empDao.getTeamManagerInfo(qry); // 查询门店经理下的所有团队Org
			achievements = empDao.getAchievement1(qry); // 营业部经理旗下所有理财经理业绩数据
			for (int i = 0; i < teamManagerInfo.size(); i++) {
				Map<String, String> temp = teamManagerInfo.get(i);
				Achievement achievement = new Achievement();
				String orgId = temp.get("ORGID");
				Double lendMoney = 0.0; // 团队经理业绩
                // 根据orgId将每个理财经理的出借金额合并
				for (int j = 0; j < achievements.size(); j++) {
					String orgId2 = achievements.get(j).getOrgId().toString();
					if (orgId2.equals(orgId)) {
						lendMoney += Double.parseDouble(achievements.get(j)
								.getFinishedAchievement().toString());
					}
				}
				// 添加团队经理的业绩信息
				achievement.setUserId(temp.get("USERID"));
				achievement.setUserName(temp.get("USERNAME"));
				achievement.setEmpCode(temp.get("EMPCODE"));
				achievement.setUserType("2");
				achievement.setRole("团队经理");
				achievement.setOrgId(temp.get("ORGID"));
				achievement.setFinishedAchievement(df.format(lendMoney));

				achievements.add(achievement);
			}

		} else if (type == 2) {
			// 团队经理
			achievements = empDao.getAchievement2(qry);
			for (int ii = 0; ii < achievements.size(); ii++) {
				Achievement achievementTeamManager = new Achievement();
				achievementTeamManager.setUserId(achievements.get(ii).getUserId());
				achievementTeamManager.setUserName(achievements.get(ii).getUserName());
				achievementTeamManager.setEmpCode(achievements.get(ii).getEmpCode());
				achievementTeamManager.setUserType("3");
				achievementTeamManager.setRole("理财经理");
				achievementTeamManager
						.setOrgId(achievements.get(ii).getOrgId());
				achievementTeamManager.setFinishedAchievement(df.format(achievements.get(ii).getFinishedAchievement().toString()));
				achievements.add(achievementTeamManager);
			}
		
		} else {
			// 理财经理
			achievements = empDao.getAchievement3(qry);
			for (int iii = 0; iii < achievements.size(); iii++) {
				Achievement achievementTeamManager = new Achievement();
				achievementTeamManager.setUserId(achievements.get(iii).getUserId());
				achievementTeamManager.setUserName(achievements.get(iii).getUserName());
				achievementTeamManager.setEmpCode(achievements.get(iii).getEmpCode());
				achievementTeamManager.setUserType("3");
				achievementTeamManager.setRole("理财经理");
				achievementTeamManager
						.setOrgId(achievements.get(iii).getOrgId());
				achievementTeamManager.setFinishedAchievement(df.format(achievements.get(iii).getFinishedAchievement().toString()));
				achievements.add(achievementTeamManager);
			}
		}
		return achievements;
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
		userInfo.put("empCodeList", empCodeList);
		
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
	
}
