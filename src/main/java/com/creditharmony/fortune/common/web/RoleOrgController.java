package com.creditharmony.fortune.common.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.utils.RoleOrgUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 角色机构
 * 
 * @Class Name RoleOrgController
 * @author 韩龙
 * @Create In 2016年1月8日
 */
@Controller
@RequestMapping("${adminPath}/common/roleOrg/")
public class RoleOrgController extends BaseController {

	/**
	 * 营业部树机构 2016年1月8日 By 韩龙
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); // 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); // 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module")); // 过滤栏目模型（仅针对CMS的Category树）
		model.addAttribute("id", request.getParameter("id")); // 组件id
		return "modules/sys/selectTreePage";
	}

	/**
	 * 获取当前用户机构及下属营业部机构(tree)
	 * 
	 * @param orgType
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> treeUserSubOrg(String orgType) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		if(user!=null){
			String userType = user.getDepartment().getType();
			// 最小显示机构为营业部
			if (RoleOrgUtil.compareOrgType(userType, orgType) == -1) {
				userType = orgType;
			}
			List<UserRoleOrgEx> roleList = RoleOrgUtil.findRangeOrgType(orgType,
					userType, null);
			for (int i = 0; i < roleList.size(); i++) {
				UserRoleOrgEx rec = roleList.get(i);
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", rec.getOrgId());
				map.put("name", rec.getOrgName());
				map.put("type", FortuneOrgType.STORE.key.equals(rec.getOrgType()));
				map.put("pId", rec.getOrgParentId());
				mapList.add(map);
			}
		}
		
		return mapList;
	}

	/**
	 * 获取全部营业部机构(tree)
	 * 
	 * @param orgType
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> treeOrg(String orgType) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// List<UserRoleOrgEx> roleList =
		// RoleOrgUtil.findRangeOrgType(orgType,FortuneOrgType.BUSINESS_DEPT.key,null);
		List<UserRoleOrgEx> roleList = RoleOrgUtil.findAllRangeOrgType(orgType);
		for (int i = 0; i < roleList.size(); i++) {
			UserRoleOrgEx rec = roleList.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", rec.getOrgId());
			map.put("name", rec.getOrgName());
			map.put("type", FortuneOrgType.STORE.key.equals(rec.getOrgType()));
			map.put("pId", rec.getOrgParentId());
			mapList.add(map);
		}
		return mapList;
	}
	/**
	 * 判断当前登录用户要的权限
	 * @param orgType
	 */
	@ResponseBody
	@RequestMapping(value = "treeOrgjudge")
	public List<Map<String, Object>> treeOrgjudge(@RequestParam(required = false) String orgType) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = (User) UserUtils.getSession().getAttribute(
				SystemConfigConstant.SESSION_USER_INFO);
		String orgTypes = user.getDepartment().getType();
		if (orgTypes.contains("640000000")) {
			mapList=treeUserSubOrg(orgType);
		} else {
			mapList=treeOrg(orgType);
		}
		return mapList;
	}
	/**
	 * 判断当前登录用户要的权限
	 * @param orgType
	 */
	@ResponseBody
	@RequestMapping(value = "treeOrgjudgeAll")
	public List<Map<String, Object>> treeOrgjudgeAll() {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		mapList=treeOrg("6400000004");
		return mapList;
	}
}