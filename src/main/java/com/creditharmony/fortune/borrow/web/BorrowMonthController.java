package com.creditharmony.fortune.borrow.web;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.service.BorrowMonthManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthableManager;
import com.creditharmony.fortune.borrow.utils.ExportExcelUtils;
import com.creditharmony.fortune.borrow.view.BorrowMonthBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthSplitView;
import com.creditharmony.fortune.borrow.view.BorrowMonthView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 月满盈债权池
 * @Class Name BorrowMonthController
 * @author 周俊
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("/${adminPath}/borrowMonth")
public class BorrowMonthController extends BaseController{

	@Autowired
	private BorrowMonthManager borrowMonthManager;
	@Autowired 
	private BorrowMonthableManager borrowMonthableManager;
	
	/**
	 * 获得月满盈债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param borrowMonth
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, BorrowMonthView borrowMonthView,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowMonth> page =  borrowMonthManager.findBorrowMonth(new Page<BorrowMonth>(request, response),borrowMonthView);
		BigDecimal allMoney = borrowMonthManager.findAllMoney(borrowMonthView);
		model.addAttribute("page", page);
		model.addAttribute("allMoney", allMoney);
		model.addAttribute("borrowMonthView", borrowMonthView);
		String[] types ={"tz_zjtr_mark","jk_prof_type","tz_repay_day","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model, types);
		request.getSession().setAttribute("borrowMonthViewTake",borrowMonthView);
		return "borrow/borrowMonthList";
	}
	
	/**
	 * 查询月满盈债权信息
	 * 2016年2月16日
	 * By 周俊
	 * @param model
	 * @param creditMonId
	 * @param borrowMonthView
	 * @return
	 */
	@RequestMapping("/goBorrowMonthLook")
	public String borrowMonthLook(Model model,String creditMonId,BorrowMonthView borrowMonthView){
		BorrowMonth borrowMonth = borrowMonthManager.findBorrowMonth(creditMonId);
		BorrowMonthSplitView borrowMonthSplitView = borrowMonthManager.findBorrowMonthSplitView(creditMonId,null);
		model.addAttribute("borrowMonth",borrowMonth);
		model.addAttribute("borrowMonthSplitView", borrowMonthSplitView);
		String[] types ={"tz_credit_src","jk_prof_type","tz_repay_day"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/borrowMonthLook";
	}
	
	
	/**
	 * 计算月满盈债权的拆分
	 * 2015年12月5日
	 * By 周俊
	 * @param creditMonId
	 * @param splitRate
	 * @return
	 */
	/*
	@RequestMapping("/borrowMonthSplitReckon")
	@ResponseBody
	public BorrowMonthSplitView reckonSplitBorrowMonth(BorrowMonthSplitView borrowMonthSplitView){
		BorrowMonthSplitView resultBorrowMonthSplitView = null;
		try {
			resultBorrowMonthSplitView = borrowMonthManager.findBorrowMonthSplitView(borrowMonthSplitView.getCreditMonId(), borrowMonthSplitView);
			return resultBorrowMonthSplitView;
		} catch (Exception e) {
			return resultBorrowMonthSplitView;
		}
		
	}*/
	
	/**
	 * 离焦事件计算拆分信息
	 * 2016年1月14日
	 * By 周俊
	 * @param borrowMonthSplitView
	 */
	@RequestMapping("/borrowMonthSplitReckonByBlur")
	@ResponseBody
	public BorrowMonthSplitView borrowMonthSplitReckonByBlur(BorrowMonthSplitView borrowMonthSplitView){
		BorrowMonthSplitView resultBorrowMonthSplitView = borrowMonthManager.findBorrowMonthSplitView(borrowMonthSplitView.getCreditMonId(),borrowMonthSplitView);
		return resultBorrowMonthSplitView;
	}
	
	/**
	 * 月满盈债权拆分后保存到月满盈可用债权
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowMonthSplitView
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveBorrowMonthSplit")
	@ResponseBody
	private String saveBorrowMonthSplit(BorrowMonthSplitView borrowMonthSplitView,HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			borrowMonthableManager.saveBorrowMonthSplit(borrowMonthSplitView);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
	/**
	 * 跳转到月满盈债权回池页面
	 * 2015年12月2日
	 * By 周俊
	 * @param model
	 * @param creditMonId
	 * @return
	 */
	@RequestMapping("/goBorrowMonthBackTool")
	public String goBorrowMonthBackTool(Model model,String creditMonId){
		 BorrowMonthBackToolView borrowMonthBackToolView = borrowMonthManager.findBorrowMonthBackTool(creditMonId);
		 model.addAttribute("borrowMonthBackToolView", borrowMonthBackToolView);
		 return "borrow/borrowMonthBackTool";
	}
	
	/**
	 * 月满盈回池
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowMonthBackToolView
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrowMonthBackTool")
	@ResponseBody
	public String borrowMonthBackTool(BorrowMonthBackToolView borrowMonthBackToolView){
		try {
			borrowMonthManager.borrowMonthBackTool(borrowMonthBackToolView);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
	
	/**
	 * 月满盈债权回池取消
	 * 2016年1月18日
	 * By 周俊
	 * @param creditMonId
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrowMonthBackToolCancel")
	public String borrowMonthBackToolCancel(String creditMonId,Model model){
		BorrowMonthView borrowMonthView = new BorrowMonthView();
		return borrowMonthLook(model,creditMonId ,borrowMonthView);
	}
	
	/**
	 * 返回到月满盈债权列表
	 * 2016年1月18日
	 * By 周俊
	 * @param model
	 * @param borrowMonthView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/backBorrowMonthList")
	public String backBorrowMonthList(Model model,HttpServletRequest request,HttpServletResponse response){
		BorrowMonthView borrowMonthView = (BorrowMonthView) request.getSession().getAttribute("borrowMonthViewTake");
		return list(model, borrowMonthView, request, response);
	}
	
	/**
	 * 导出Excel
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowMonthView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/outExcel")
	public void outExcel(BorrowMonthView borrowMonthView,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			/*List<BorrowMonthOutExcel> list = borrowMonthManager.outExcel(borrowMonthView);
			String fileName = FileType.YMYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(FileType.YMYZQLB.getName(),BorrowMonthOutExcel.class); 
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();*/
			Map<String, Object> map = borrowMonthManager.objectToMap(borrowMonthView);
			String fileName = FileType.YMYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcelUtils.exportData(map, response,BorrowConstant.BORROWMONTH_EXPORT,fileName,Node.YMY.value);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出Excel失败! 失败信息"+e.getMessage());
		}
	}
	
	
}
