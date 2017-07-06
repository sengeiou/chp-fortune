package com.creditharmony.fortune.back.money.supplement.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.supplement.facade.BmSupplementForeachManager;
import com.creditharmony.fortune.back.money.supplement.service.BmSupplementExportor;
import com.creditharmony.fortune.back.money.supplement.service.BmSupplementJzhExportor;
import com.creditharmony.fortune.back.money.supplement.service.BmSupplementManager;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款补息Controller
 * @Class Name BackMoneyApplySupplementController
 * @author 陈广鹏
 * @Create In 2016年11月7日
 */
@Controller
@RequestMapping("${adminPath}/myTodo/backMoney/")
public class BackMoneyApplySupplementController extends BaseController {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private BmSupplementForeachManager supplementForeachManager;
	@Autowired
	private BmSupplementManager supplementManager;
	
	/**
	 * 加载列表
	 * 2016年11月7日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("supplementList")
	public String supplementList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		view.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalSupplementedMoney = bmManager.getTotalSupplementedMoney(view);
		
		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("totalBXHM", totalSupplementedMoney)
			.addAttribute("eUrl", "exportSupplement")
			.addAttribute("ejUrl", "exportJzhSupplement");
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_back_reason","tz_contract_vesion","tz_channel_flag",
				"tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/supplement/supplementList";
	}
	
	
	/**
	 * 加载回款补息详情
	 * 2016年11月7日
	 * By 陈广鹏
	 * @param model
	 * @param backmId
	 * @return
	 */
	@RequestMapping("supplementDetail")
	public String supplementDetail(Model model, String backmId){
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						  "tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/supplement/supplement";
	}
	
	/**
	 * 回款补息
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("supplementSubmit")
	@ResponseBody
	public String supplementSubmit(ResultView view){
		String rtn = supplementForeachManager.supplement(view);
		return jsonMapper.toJson(rtn);
	}
	
	/**
	 * 显示批量回款补息确认弹窗
	 * 2016年11月8日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toMultiSupplement")
	public String toMultiSupplement(Model model){
		String[] types = {"tz_back_reason"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/popMultOperate";
	}
	
	/**
	 * 批量回款补息确认
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param model
	 * @return
	 */
	@RequestMapping("multiSupplement")
	@ResponseBody
	public String multiSupplement(ListItemView view){
		view.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		
		String msg = supplementForeachManager.multiSupplement(view);
		return jsonMapper.toJson(msg);
	}
	
	/**
	 * 显示上传弹窗
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param model
	 * @return
	 */
	@RequestMapping("toUploadSupplementedMoney")
	public String toUploadSupplementedMoney(){
		return "backMoney/common/fileUpload";
	}
	
	/**
	 * 上传补息后回款金额
	 * 2016年11月8日
	 * By 陈广鹏
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadSupplementedMoney")
	@ResponseBody
	public String uploadSupplementedMoney(MultipartFile file){
		
		String msg = supplementForeachManager.uploadSupplementedMoney(file);
		
		return msg;
	}
	
	/**
	 * 非金账户导出
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportSupplement")
	public void exportSupplement(HttpServletResponse response, ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		BmSupplementExportor exportor = new BmSupplementExportor(
				BmConstant.TO_APPLYCONFIRM + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}

	/**
	 * 获取回款明细要导出的数据量
	 * 2016年11月10日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportSupplementCheck")
	@ResponseBody
	public String exportSupplementCheck(ListItemView listItemView){
		listItemView.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 金账户导出
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportJzhSupplement")
	public void exportJzhSupplement(HttpServletResponse response, ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		listItemView.setIsJZH(YoN.SHI.value);
		
		BmSupplementJzhExportor exportor = new BmSupplementJzhExportor(
				BmConstant.TO_JZH_APPLYCONFIRM + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取金账户回款明细要导出的数据量
	 * 2016年11月10日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportJzhSupplementCheck")
	@ResponseBody
	public String exportJzhSupplementCheck(ListItemView listItemView){
		
		listItemView.setStatusList(BmConstant.SUPPLEMENT_STATUS_LIST);
		listItemView.setIsJZH(YoN.SHI.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 显示修改债转日弹窗
	 * 2016年11月9日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toModifyDebtTransferDate")
	public String toModifyDebtTransferDate(){
		return "backMoney/supplement/popSupplement";
	}
	
	/**
	 * 修改债转日(批量)
	 * 2016年11月9日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("modifyDebtTransferDate")
	@ResponseBody
	public String modifyDebtTransferDate(ListItemView itemView){
		String rtn = supplementForeachManager.modifyDebtTransferDate(itemView);
		return jsonMapper.toJson(rtn);
	}
	
	/**
	 * 修改债转日(单条)
	 * 2016年11月9日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("updatePageInfo")
	@ResponseBody
	public String updatePageInfo(ResultView view){
		ResultView rtnView = supplementManager.calculateSupplement(view);
		return jsonMapper.toJson(rtnView);
	}
	
	

}
