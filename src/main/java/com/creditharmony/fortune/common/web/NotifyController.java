package com.creditharmony.fortune.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.trusteeship.entity.ChangeCount;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipChangeManager;

@Controller
@RequestMapping("${adminPath}/notifyController")
public class NotifyController extends BaseController{
	@Autowired 
	private TrusteeshipChangeManager trusteeshipChangeManager;
	@Autowired 
	private UserRoleOrgDao userRoleOrgDao;
	
	@RequestMapping("/findNotify")
	@ResponseBody
	public String getNewDate(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);		
		String userId = user.getId();
		String orgId = user.getDepartment().getId();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", userId);
		queryMap.put("orgId", orgId);
		List<UserRoleOrg> list = userRoleOrgDao.query(queryMap);
		if(ArrayHelper.isNotEmpty(list)){
			List<String> roleList = new ArrayList<String>();
			for (UserRoleOrg userRoleOrg : list) {
				roleList.add(userRoleOrg.getRoleId());
			}
			if(!roleList.contains("6130000015")){
				map.put("auth", BooleanType.FALSE);
				return JsonMapper.toJsonString(map);
				
			}
		}
		ChangeCount changeCount = trusteeshipChangeManager.getChangeCount();
		int cardChangeCount = changeCount.getCardChangeCount(); // 银行卡变更数目
		int phoneChangeCount = changeCount.getPhoneChangeCount(); // 手机变更数目
		if (cardChangeCount == 0 && phoneChangeCount == 0) {
			return null;
		}
		String body = "";
		String phoneUrl = "";
		String cardUrl = "";
		if(phoneChangeCount!=0 && cardChangeCount!=0){
			body="金账户手机号变更列表：存在("+phoneChangeCount+")条手机号码带变更数据\r\n\r\n"+
			"金账户卡号变更列表：存在("+cardChangeCount+")条银行卡号待变更数据";
			phoneUrl = "/trusteeship/change";
			cardUrl = "/trusteeship/change";
		}else if(cardChangeCount!=0){
			body = "金账户卡号变更列表：存在("+cardChangeCount+")条银行卡号待变更数据";
			cardUrl = "/trusteeship/change";
		}else if(phoneChangeCount!=0){
			body = "金账户手机号变更列表：存在("+phoneChangeCount+")条手机号码带变更数据";
			phoneUrl = "/trusteeship/change";
		}
		map.put("body", body);
		map.put("cardUrl",cardUrl);
		map.put("phoneUrl",phoneUrl);
		return JsonMapper.toJsonString(map);
	}
}
