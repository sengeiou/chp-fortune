package com.creditharmony.fortune.borrow.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthableLookEx;
import com.creditharmony.fortune.borrow.facade.BorrowFacade;
import com.creditharmony.fortune.borrow.service.BorrowMonthableManager;
import com.creditharmony.fortune.borrow.utils.ExportExcelUtils;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.view.BorrowMonthableBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthableView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 月满盈可用债权
 * @Class Name BorrowMonthableController
 * @author 周俊
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping("${adminPath}/borrowMonthable")
public class BorrowMonthableController extends BaseController{

	@Autowired
	private BorrowMonthableManager borrowMonthableManager;
	@Autowired
	private BorrowFacade borrowFacade;

	/**
	 * 查询月满盈可用债权列表
	 * 2015年12月2日
	 * By 周俊
	 * @param borrowMonthableView
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	private String list(BorrowMonthableView borrowMonthableView,HttpServletRequest request,HttpServletResponse response, Model model){
		Page<BorrowMonthable> page = borrowMonthableManager.findBorrowMonthable(new Page<BorrowMonthable>(request, response),borrowMonthableView);
		BigDecimal allMoney = borrowMonthableManager.findAllMoney(borrowMonthableView);
		model.addAttribute("page",page);
	    model.addAttribute("allMoney",allMoney);
	    model.addAttribute("borrowMonthableView", borrowMonthableView);
	    String[] types ={"tz_credit_src","jk_prof_type","tz_loan_distinguish"};
	    FormatUtils.addDicts(model, types);
	    request.getSession().setAttribute("borrowMonthableViewToke",borrowMonthableView);
		 return "borrow/borrowMonthableList";
	}
	
	/**
	 * 查看月满盈可用债权详情
	 * 2016年2月16日
	 * By 周俊
	 * @param creditMonableId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/goBorrowMonthableLook")
	private String borrowMonthableLook(String creditMonableId,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<BorrowMonthableLookEx> page = borrowMonthableManager.borrowMonthableLook(new Page<BorrowMonthableLookEx>(request, response), creditMonableId);
		page.setFuncName("subPage");
		BorrowMonthable borrowMonthable = new BorrowMonthable();
		borrowMonthable.setCreditMonableId(creditMonableId);
		borrowMonthable = borrowMonthableManager.get(borrowMonthable);
		model.addAttribute("page", page);
		model.addAttribute("borrowMonthable",borrowMonthable);
		return "borrow/borrowMonthableLook";
	}
	
	/**
	 * 跳转到月满盈可用债权回池页面
	 * 2016年2月16日
	 * By 周俊
	 * @param creditMonableId
	 * @param model
	 * @return
	 */
	@RequestMapping("/goBorrowMonthableBackTool")
	public String getBorrowMonthableBackTool(String creditMonableId,Model model){
		BorrowMonthableBackToolView borrowMonthableBackTool = borrowMonthableManager.getBorrowMonthableBackTool(creditMonableId);
		model.addAttribute("borrowMonthableBackTool",borrowMonthableBackTool);
		return "borrow/borrowMonthableBackTool";
	}
	
	/**
	 * 校验月满盈可用债权回池信息
	 * 2016年4月11日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	@RequestMapping("/checkBorrowMonthableBackTool")
	@ResponseBody
	public String checkBorrowAllot(String creditMonableId){
		BorrowMonthable borrowMonthable = new BorrowMonthable();
		borrowMonthable.setCreditMonableId(creditMonableId);
	    borrowMonthable = borrowMonthableManager.get(borrowMonthable);
		return jsonMapper.toJson(borrowMonthable.getLoanAvailabeValue());
	}
	
	/**
	 * 月满盈可用债权回池
	 * 2016年1月25日
	 * By 周俊
	 * @param backToolView
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrowMonthableBackTool")
	@ResponseBody
	public String borrowMonthableBackTool(BorrowMonthableBackToolView backToolView,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			borrowMonthableManager.borrowMonthableBackTool(backToolView,null,null);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
	
	/**
	 * 月满盈可用债权批量回池
	 * 2016年1月13日
	 * By 周俊
	 * @param creditMonableIdBatch
	 * @return
	 */
	@RequestMapping("/borrowMonthableBatchBackTool")
	@ResponseBody
	public String borrowMonthableBatchBackTool(String batchBorrowMonthableInfo){
		try {
			borrowFacade.borrowMonthableBatchBackTool(batchBorrowMonthableInfo);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
	
	/**
	 * 月满盈可用债权回池取消
	 * 2016年1月25日
	 * By 周俊
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/backBorrowMonthableList")
	public String backBorrowMonthableList(HttpServletRequest request,HttpServletResponse response,Model model){
		BorrowMonthableView borrowMonthableView = (BorrowMonthableView) request.getSession().getAttribute("borrowMonthableViewToke");
		return list(borrowMonthableView, request, response, model);
	}
	
	/**
	 * 导出Excel
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowMonthableView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/outExcel")
	public void outExcel(BorrowMonthableView borrowMonthableView,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			/*List<BorrowMonthableOutExcel> list = borrowMonthableManager.outExcel(borrowMonthableView);
			String fileName = FileType.YMYKYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(FileType.YMYKYZQLB.getName(),BorrowMonthableOutExcel.class); 
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();*/
			Map<String, Object> map = borrowMonthableManager.objectToMap(borrowMonthableView);
			List<String> borrowMonthableInfoList = borrowMonthableView.getCreditMonableIdList();
			if (ArrayHelper.isNotEmpty(borrowMonthableInfoList)) {
				List<String> creditMonableIdArray =	new ArrayList<String>();
				for (String borrowMonthableInfo : borrowMonthableInfoList) {
					creditMonableIdArray.add(borrowMonthableInfo.split(":")[0]);
				}
				map.put("creditMonableIdArray", creditMonableIdArray);
			}
			String fileName = FileType.YMYKYZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcelUtils.exportData(map, response,BorrowConstant.BORROWMONTHABLE_EXPORT,fileName,Node.YMYKY.value);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出Excel失败! 失败信息"+e.getMessage());
		}
	}
	
	
	
}
