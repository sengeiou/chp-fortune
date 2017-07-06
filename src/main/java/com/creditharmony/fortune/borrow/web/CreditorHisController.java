package com.creditharmony.fortune.borrow.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthSplitHisEx;
import com.creditharmony.fortune.borrow.service.CreditorHisManager;

/**
 * 操作记录
 * @Class Name CreditorHisController
 * @author 周俊
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/his")
public class CreditorHisController {

	@Autowired
	private CreditorHisManager creditorHisManager;
	
	/**
	 * 可用债权分配记录
	 * 2016年1月12日
	 * By 周俊
	 * @param model
	 * @param creditValueId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/borrowAolltHis")
	public String findBorrowAllotLog(Model model,String creditValueId,HttpServletRequest request,HttpServletResponse response){
		Page<CreditorHis> page = creditorHisManager.findBorrowAllotLog(new Page<CreditorHis>(request, response),creditValueId);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("creditValueId", creditValueId);
		return "borrow/allotBorrowLog";
	
	}
	
	/**
	 * 查询拆分记录
	 * 2016年1月12日
	 * By 周俊
	 * @param model
	 * @param creditMonId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/borrowMonthSplitHis")
	public String findBorrowMonthSplitLog(Model model,String creditMonId,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowMonthSplitHisEx> page = creditorHisManager.findBorrowMonthSplitLog(new Page<BorrowMonthSplitHisEx>(request, response),creditMonId);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("creditMonId", creditMonId);
		return "borrow/splitBorrowMonthLog";
	}
}
