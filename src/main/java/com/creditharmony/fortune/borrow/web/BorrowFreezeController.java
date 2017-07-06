package com.creditharmony.fortune.borrow.web;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeLookEx;
import com.creditharmony.fortune.borrow.service.BorrowFreezManager;
import com.creditharmony.fortune.borrow.utils.ExportExcelUtils;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.view.BorrowView;
import com.creditharmony.fortune.constants.BorrowConstant;

/**
 * 债权冻结
 * @Class Name BorrowFreezeController
 * @author 周俊
 * @Create In 2015年12月10日
 */
@Controller
@RequestMapping("${adminPath}/borrowFreeze")
public class BorrowFreezeController extends BaseController{

	@Autowired
	private BorrowFreezManager borrowFreezManager;
	
	/**
	 * 获得冻结债权列表
	 * 2015年12月10日
	 * By 周俊
	 * @param borrowView
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(BorrowView borrowView,Model model,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowFreezeEx> page = borrowFreezManager.findBorrowFreeze(borrowView, new Page<BorrowFreezeEx>(request, response));
		BigDecimal money = borrowFreezManager.findCountMoney(borrowView);
		model.addAttribute("page",page);
		model.addAttribute("money",money);
		String[] types ={"tz_credit_src","jk_prof_type","tz_repay_day"};
		FormatUtils.addDicts(model, types);
		model.addAttribute("borrowView", borrowView);
		return "borrow/borrowFreezList";
	}
	
	/**
	 * 冻结债权查看
	 * 2015年12月10日
	 * By 周俊
	 * @param code
	 * @param model
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping("/goBorrowFreezeLook")
	public String goBorrowFreezeLook(String creditValueId,Model model,HttpServletRequest request,HttpServletResponse response){
		Page<BorrowFreezeLookEx> page = borrowFreezManager.borrowFreezeLook(creditValueId, new Page<BorrowFreezeLookEx>(request, response));
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("creditValueId", creditValueId);
		return "borrow/borrowFreezeLook";
	}
	
	/**
	 * 导Excel
	 * 2015年12月24日
	 * By 周俊
	 * @param borrowView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/outExcel")
	public void outExcel(BorrowView borrowView,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		try {
			/*List<BorrowFreezeExcel> list = borrowFreezManager.outExcel(borrowView);
			ExportExcel exportExcel = new ExportExcel(FileType.DJZQLB.getName(),BorrowFreezeExcel.class); 
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();
			return null;*/
			String fileName = FileType.DJZQLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Map<String, Object> map = borrowFreezManager.objectToMap(borrowView);
			ExportExcelUtils.exportData(map, response, BorrowConstant.BORROWFREEZE_EXPORT, fileName, BorrowConstant.BORROWFREEZE_NODE);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出Excel失败! 失败信息"+e.getMessage());
		}
	}
}
