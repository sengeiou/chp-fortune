package com.creditharmony.fortune.appweishang.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.appweishang.bean.EmpUser;
import com.creditharmony.fortune.appweishang.server.EmpUserServer;

/**
 * 我的微服接口controller
 * @author colin
 *
 */
@Controller
@RequestMapping("${adminPath}/empUserController")
public class EmpUserController extends BaseController {
	
	@Autowired
	private EmpUserServer empUserServer;
	
	@RequestMapping("/getEmpUserInfo")
	@ResponseBody
	public String  getEmpUserInfo(HttpServletRequest request, HttpServletResponse response, Model model){
		//查询数据
		String userCode = request.getParameter("managerId");
		HashMap<String,String> hashmap = new HashMap<String,String>();
		EmpUser empuser = empUserServer.getEMPuserInfo(userCode);
		if(empuser!=null){
			String str = jsonMapper.toJson(empuser);
			hashmap.put("code", "200");
			hashmap.put("message", "请求成功");
			hashmap.put("managerInfo",str);
		}else{
			hashmap.put("code", "500");
			hashmap.put("message", "请求失败");
		}
		return jsonMapper.toJson(hashmap);
	}

}
