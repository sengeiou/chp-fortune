package com.creditharmony.fortune.back.interest.common.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.HistoryFull;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息通用Controller
 * @Class Name BackInterestCommonController 
 * @author 李志伟
 * @Create In 2016年2月20日
 */
@Controller
@RequestMapping("${adminPath}/backInterestCommon/")
public class BackInterestCommonController extends BaseController{
	
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	/**
	 * 弹出批量操作窗口
	 * 2015年12月29日
	 * by 李志伟
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "batchWindow")
	public String batchWindow(Model m){
		
		 List<PlatformMsg> list = backInterestCommonService.searchThirdPlat();
		 FortuneDictUtil.addDicts(m, new String[]{"tz_open_bank","tz_backsms_reason"});
		 m.addAttribute("list", list);
		 m.addAttribute("backMoneyDay", new Date());
		return "backInterest/common/branchBackInterestWindow";
	}

	/**
	 * 累计实际回息金额
	 * 2016年2月1日
	 * by 李志伟
	 * @param m
	 * @param so
	 */
	@RequestMapping(value = "selectSumMoney")
	@ResponseBody
	public String selectSumMoney(Model m, SearchObject so){
		
		String cou = backInterestCommonService.sumMoney(so);
		return jsonMapper.toJson(cou);
	}
	
	/**
	 * 上传回盘结果
	 * 2016年3月12日
	 * by 李志伟
	 * @return
	 */
	@RequestMapping(value = "uploadResultWinow")
	public String uploadResultWinow(){
		return "backInterest/confrim/uploadResultWindow";
	}
	
	/**
	 * 回息全程留痕翻页
	 * 2016年3月2日
	 * by 李志伟
	 * @param backiId
	 * @param req
	 * @param resp
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "goHistory")
	public String goHistory(BackInterestPool bip, HttpServletRequest req, HttpServletResponse resp, Model m){
		
		Page<HistoryFull> page = new Page<HistoryFull>(req, resp);
		page.setFuncName("his_page");
		page = backInterestCommonService.toHistory(page, bip.getLendCode());
		m.addAttribute("page", page);
		m.addAttribute("lendCode", bip.getLendCode());
		return "fullMark/fullMarkPage";
	}
	
	/**
	 * 去历史留痕页面
	 * 2016年1月22日
	 * by 李志伟
	 * @param mo
	 * @param bip
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "toHistory")
	public String toHistory(Model mo, BackInterestPool bip, HttpServletRequest req, HttpServletResponse resp){
		
		String lendCode = bip.getLendCode();
		if(bip.getLendCode() != null && !bip.getLendCode().equals("")){
			lendCode = bip.getLendCode();
		}
		Page<HistoryFull> page = new Page<HistoryFull>(req, resp);
		page = backInterestCommonService.toHistory(page, lendCode);
		page.setFuncName("his_page");
		mo.addAttribute("page", page);
		mo.addAttribute("lendCode", lendCode);
		return "fullMark/fullMark";
	}
}
