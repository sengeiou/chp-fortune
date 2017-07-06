package com.creditharmony.fortune.borrow.web;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.ReplaceStatus;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.ex.BorrowCanceEx;
import com.creditharmony.fortune.borrow.entity.ex.LoanApplyEx;
import com.creditharmony.fortune.borrow.service.BorrowCancelManager;
import com.creditharmony.fortune.borrow.view.BorrowCancelSearchView;
import com.creditharmony.fortune.borrow.view.BorrowCancelView;
import com.creditharmony.fortune.borrow.view.MatchingBorrowLookView;
import com.creditharmony.fortune.borrow.view.ReplaceView;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 债权撤销
 * @Class Name BorrowCancelController
 * @author 周俊
 * @Create In 2015年12月9日
 */
@RequestMapping("${adminPath}/borrowCancel")
@Controller
public class BorrowCancelController extends BaseController{

	@Autowired
	private BorrowCancelManager borrowCancelManager;
	@Autowired
	private LoanApplyManager loanApplyManager;
	private static final ObjectMapper MAPPER=new ObjectMapper();
	
	/**
	 * 查询债权撤销列表
	 * 2015年12月16日
	 * By 周俊
	 * @param borrowCancelView
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/list")
	public String findBorrowCancel(BorrowCancelView borrowCancelView,HttpServletRequest request , HttpServletResponse response,Model model,String search) throws IOException{

		if(StringUtils.isBlank(borrowCancelView.getReplaceStatus())){
			borrowCancelView.setReplaceStatus(ReplaceStatus.DTH.value);
		}
		if((borrowCancelView.getApplyBillday()==null || borrowCancelView.getApplyBillday().equals("")) && (search == null || "".equals(search))){
			borrowCancelView.setApplyBillday("4");
		}

		BorrowCancelSearchView borrowCancelSearchView=new BorrowCancelSearchView();
		if ((null != search && !"".equals(search))) {
			System.out.println(search);
			search =StringEscapeUtils.unescapeHtml(search);
			borrowCancelSearchView=MAPPER.readValue(search,BorrowCancelSearchView.class);
			borrowCancelView.setCustomerName(borrowCancelSearchView.getCustomerName());
			borrowCancelView.setAddrCity(borrowCancelSearchView.getAddrCity());
			borrowCancelView.setApplyBillday(borrowCancelSearchView.getApplyBillday());
			borrowCancelView.setLendCode(borrowCancelSearchView.getLendCode());
			borrowCancelView.setLendDateFrom(borrowCancelSearchView.getLendDateFrom());
			borrowCancelView.setLendDateTo(borrowCancelSearchView.getLendDateTo());
			borrowCancelView.setMatchingFirstdayFlag(borrowCancelSearchView.getMatchingFirstdayFlag());
			borrowCancelView.setOrgCode(borrowCancelSearchView.getOrgCode());
			borrowCancelView.setProcuctId(borrowCancelSearchView.getProcuctId());
			borrowCancelView.setReplaceStatus(borrowCancelSearchView.getReplaceStatus());
			borrowCancelView.setReplaceDayFrom(borrowCancelSearchView.getReplaceDayFrom());
			borrowCancelView.setReplaceDayTo(borrowCancelSearchView.getReplaceDayTo());
		}
		
		borrowCancelSearchView.setCustomerName(borrowCancelView.getCustomerName());
		borrowCancelSearchView.setAddrCity(borrowCancelView.getAddrCity());
		borrowCancelSearchView.setApplyBillday(borrowCancelView.getApplyBillday());
		borrowCancelSearchView.setLendCode(borrowCancelView.getLendCode());
		borrowCancelSearchView.setLendDateFrom(borrowCancelView.getLendDateFrom());
		borrowCancelSearchView.setLendDateTo(borrowCancelView.getLendDateTo());
		borrowCancelSearchView.setMatchingFirstdayFlag(borrowCancelView.getMatchingFirstdayFlag());
		borrowCancelSearchView.setOrgCode(borrowCancelView.getOrgCode());
		borrowCancelSearchView.setProcuctId(borrowCancelView.getProcuctId());
		borrowCancelSearchView.setReplaceStatus(borrowCancelView.getReplaceStatus());
		borrowCancelSearchView.setReplaceDayFrom(borrowCancelView.getReplaceDayFrom());
		borrowCancelSearchView.setReplaceDayTo(borrowCancelView.getReplaceDayTo());
		

		Page<BorrowCancelView> page = borrowCancelManager.findBorrowCancel(borrowCancelView, new Page<BorrowCancelView>(request, response));
		//BigDecimal money = borrowCancelManager.findCountMoney(borrowCancelView);
		if (ArrayHelper.isNotEmpty(page.getList())) {
			model.addAttribute("money", page.getList().get(0).getSumMoney());
			borrowCancelView.setSumCurrentCreditLinesMoney(page.getList().get(0).getSumCurrentCreditLinesMoney());
		}else {
			model.addAttribute("money", 0);
		}
		model.addAttribute("page",page);
		/*if(StringUtils.isBlank(borrowCancelView.getToPageReplaceStatus())){
			borrowCancelView.setReplaceStatus(ReplaceStatus.DTH.value);
		}*/
		//borrowCancelView = (BorrowCancelView) request.getSession().getAttribute("borrowCancelView");
		String writeValueAsString = MAPPER.writeValueAsString(borrowCancelSearchView);
		model.addAttribute("search",writeValueAsString);
//		request.setAttribute("search", writeValueAsString);
//		System.out.println(request.getAttribute("search"));
		//model.addAttribute("aaa", "4");
		String[] types ={"tz_bill_day","tz_bill_state","tz_replace_status"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/borrowCancelList";
	}
	
	/**
	 * ajax请求出借总金额
	 * 2015年12月24日
	 * By 周俊
	 * @param lendCode
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ajaxMoney",method=RequestMethod.GET)
	@ResponseBody
	public String ajaxMoney(String lendCode,HttpServletResponse response){
		BigDecimal money = loanApplyManager.getLoanApplyByLendMoney(lendCode);
		return this.renderString(response, money);
	}
	
	/**
	 * 导出Excel
	 * 2017年3月22日
	 * By 常亚运
	 * @param view
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/outExcel")
	public void outExcel(BorrowCancelView view,HttpServletRequest request,HttpServletResponse response){
		BorrowCancelView search_cxlb = (BorrowCancelView)SerializationUtils.clone(view);
		//判断页面是通过搜索栏导出，还是通过复选框导出
		search_cxlb = borrowCancelManager.judgeIds(search_cxlb);
		String fileName = FileType.ZQCXLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		String namespace = "";
		if(search_cxlb.getReplaceStatus().equals("1")){
			namespace = "com.creditharmony.fortune.borrow.dao.BorrowCancelDao.outExcelForNo";
		}else if(search_cxlb.getReplaceStatus().equals("2")){
			namespace = "com.creditharmony.fortune.borrow.dao.BorrowCancelDao.outExcelForYes";
		}else{
			namespace = "com.creditharmony.fortune.borrow.dao.BorrowCancelDao.outExcelForAll";
		}
		//导出
		borrowCancelManager.outExcel(search_cxlb,response,namespace,fileName,borrowCancelManager);
		
	} 

	/**
	 * 查询替换债权
	 * 2015年12月16日
	 * By 周俊
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/replace/{lendCode_matchingId}")
	public String borrowReplace(@PathVariable("lendCode_matchingId") String lendCode_matchingId,Model model,HttpServletRequest request , HttpServletResponse response,String search){
		/*BorrowCancelView borrowCancelView = new BorrowCancelView();
		borrowCancelView.setMatchingId(lendCode_matchingId);
		borrowCancelManager.findBorrowCancel(borrowCancelView, new Page<BorrowCancelView>(request, response));*/
		//request.getSession().setAttribute("borrowCancelView", borrowCancelView);
		try {
			BorrowCanceEx borrowCanceEx = borrowCancelManager.getBorrowCanceEx(lendCode_matchingId);
			model.addAttribute("borrowCanceEx",borrowCanceEx);
			model.addAttribute("search",search);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "borrow/replaceLook";
	}
	
	/**
	 * 跳转到查看带推荐可用债权
	 * 2016年1月8日
	 * By 周俊
	 * @param lendCode
	 * @param matchingId
	 * @param money
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goMatchingBorrowLook")
	public String goMatchingBorrowLook(String lendCode,String matchingId,String dictLoanType,String creditValueIds,BigDecimal money,Model model,HttpServletRequest request,HttpServletResponse response){
		MatchingBorrowLookView matchingBorrowLookView = new MatchingBorrowLookView();
		Page<Borrow> page = borrowCancelManager.findMatchingBorrowLook(new Page<Borrow>(request, response),matchingBorrowLookView,lendCode,matchingId,dictLoanType,creditValueIds);
		LoanApplyEx loanApplyEx = borrowCancelManager.findLoanApplyEx(lendCode);
		matchingBorrowLookView.setMatchingId(matchingId);
		matchingBorrowLookView.setCreditLines(money);
		matchingBorrowLookView.setLendDate(loanApplyEx.getLendDate());
		matchingBorrowLookView.setProcuctName(loanApplyEx.getProcuctName());
		matchingBorrowLookView.setLendCode(lendCode);
		matchingBorrowLookView.setCreditValueIds(creditValueIds);
		//如果本期推荐债权列表为同一债权来源类型，就按此债权来源类型在搜索框回显
		if(StringUtils.isNotBlank(dictLoanType)){
			matchingBorrowLookView.setBorrowType(dictLoanType);
		}
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("productMatchingRateUpper", matchingBorrowLookView.getProductMatchingRateUpper());
		model.addAttribute("productMatchingRateLower", matchingBorrowLookView.getProductMatchingRateLower());
		model.addAttribute("matchingBorrowLookView", matchingBorrowLookView);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/matchingBorrowLook";
	}
	
	/**
	 * 查询带推荐可用债权
	 * 2016年1月8日
	 * By 周俊
	 * @param matchingBorrowLookView
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/matchingBorrowLook")
	public String findMatchingBorrowLook(MatchingBorrowLookView matchingBorrowLookView ,HttpServletRequest request , HttpServletResponse response,Model model){
		Page<Borrow> page = borrowCancelManager.findMatchingBorrowLook(new Page<Borrow>(request, response), matchingBorrowLookView,matchingBorrowLookView.getLendCode(),matchingBorrowLookView.getMatchingId(),null, matchingBorrowLookView.getCreditValueIds());
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("creditValueIds", matchingBorrowLookView.getCreditValueIds());
		model.addAttribute("matchingBorrowLookView", matchingBorrowLookView);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/matchingBorrowLookList";
	}
	
	/**
	 * 查询出借下已匹配债权
	 * 2015年12月31日
	 * By 周俊
	 * @param matchingId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/findExistingBorrow")
	public String findExistingBorrow(String lendCode,HttpServletRequest request , HttpServletResponse response,Model model){
		Page<Borrow> page = borrowCancelManager.findExistingBorrow(lendCode, new Page<Borrow>(request, response));
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		String[] types ={"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day"};
		FortuneDictUtil.addDicts(model, types);
		return "borrow/matchingBorrowLookList";
	}
	
	/**
	 * 移除冻结债权
	 * 2015年12月17日
	 * By 周俊
	 * @param lendCode
	 * @param ids
	 * @return
	 *//*
	@RequestMapping("/goremove")
	@ResponseBody
	public String removeBorrowFlag(String lendCode ,String ids){
		List<String> list = borrowCancelManager.removeBorrowFlag(lendCode, ids);
		return jsonMapper.toJson(list);
	}*/
	
	/**
	 * 根据选择的creditValueIds获得列表
	 * 2016年1月8日
	 * By 周俊
	 * @param creditValueIds
	 * @param creditLines
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/creditValueIds")
	@ResponseBody
	public String getBorrowList(String creditValueIds,BigDecimal creditLines,HttpServletResponse response){
		BorrowCanceEx borrowCanceEx = borrowCancelManager.findMatchingBorrowList(creditValueIds,creditLines);
		return jsonMapper.toJson(borrowCanceEx.getList());
	}
	
	/**
	 * 保存新的匹配
	 * 2016年1月8日
	 * By 周俊
	 * @param replaceView
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String saveNewAllot(@RequestBody ReplaceView replaceView,HttpServletRequest request){
		try {
			CERequestContext ceContext = CEContextHelper.getCEContext(request);
			borrowCancelManager.save(replaceView,ceContext);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson(null);
	}
}
