package com.creditharmony.fortune.back.money.excute.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.money.common.entity.ThirdPlatform;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.excute.facade.BmExecuteForeachManager;
import com.creditharmony.fortune.back.money.excute.service.BmFyExportor;
import com.creditharmony.fortune.back.money.excute.service.BmJzhExportor;
import com.creditharmony.fortune.back.money.excute.service.BmTlExportor;
import com.creditharmony.fortune.back.money.excute.service.BmWyExportor;
import com.creditharmony.fortune.back.money.excute.service.BmWySplitExportor;
import com.creditharmony.fortune.back.money.excute.service.BmZjExportor;
import com.creditharmony.fortune.back.money.excute.service.BmZjTxtExportor;
import com.creditharmony.fortune.back.money.excute.service.ThirdPlatformManager;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.ExportType;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 执行回款相关Controller
 * @Class Name BackMoneyExecuteController
 * @author 陈广鹏
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping("${adminPath}/myTodo/backMoney/")
public class BackMoneyExecuteController extends BaseController {

	@Autowired
	private BmExecuteForeachManager executeForeachManager;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ThirdPlatformManager pManager;

	/**
	 * 执行回款列表
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("executeList")
	public String executeList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		BmUtils.initExecuteSearch(view);
		
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalAcutalbackmoney = bmManager.getTotalAcutalbackmoney(view);
		BigDecimal successMoney = bmManager.getTotalSuccessMoney(view);
		totalAcutalbackmoney = totalAcutalbackmoney.subtract(successMoney);
		
		String rebackYes = YoN.SHI.value;
		String backStatusOption = BackState.DHK.value + ","
				+ BackState.BFHKCG.value;

		model.addAttribute("page", pageview)
			.addAttribute("rebackYes", rebackYes)
			.addAttribute("backStatusOption", backStatusOption)
			.addAttribute("totalABM", totalAcutalbackmoney)
			.addAttribute("view", view);
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_open_bank","tz_backMoney_plat","tz_back_state",
				"tz_contract_vesion","tz_channel_flag","tz_yes_no",
				"tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/execute/executeList";
	}

	/**
	 * 跳转至执行回款详细页面
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("executeDetail")
	public String executeDetail(String backmId, Model model) {
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);

		List<ThirdPlatform> platformList = pManager.findAllList(null);

		model.addAttribute("view", view);
		model.addAttribute("platformList", platformList);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						"tz_working_state"};
		FortuneDictUtil.addDicts(model,types);

		return "backMoney/execute/execute";
	}

	/**
	 * 提交执行回款结果
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("execute")
	@ResponseBody
	public String execute(ResultView view){
		String message = executeForeachManager.execute(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 弹出批量回款弹窗
	 * 2015年12月17日
	 * By 陈广鹏
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("toMultiBackMoney")
	public String toMultiBackMoney(ListItemView view, Model model) {
		List<ThirdPlatform> platformList = pManager.findAllList(null);
		model.addAttribute("platformList", platformList);
		String[] types = {"tz_back_reason","tz_open_bank"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/popMultiBackMoney";
	}

	/**
	 * 批量回款修改回款状态
	 * 2015年12月17日
	 * By 陈广鹏
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("multiExecute")
	@ResponseBody
	public String multiExecute(ListItemView view, Model model) {
		BmUtils.initExecuteSearch(view);
		
		String rtn = executeForeachManager.multiExecute(view);
		return jsonMapper.toJson(new ReturnMsg(rtn));
	}
	
	/**
	 * 弹出线上回款弹窗
	 * 2015年12月28日
	 * By 陈广鹏
	 * @param jzhFlag
	 * @param model
	 * @return
	 */
	@RequestMapping("toOnlineBackMoney")
	public String toOnlineBackMoney(String jzhFlag,Model model) {
		String jzhValue = BackMoneyPlat.JZH.value;
		String notJzhValue = BackMoneyPlat.ZJPT.value + ","
				+ BackMoneyPlat.TL.value + ","
				+ BackMoneyPlat.KL.value;
		
		model.addAttribute("jzhFlag", jzhFlag)
			.addAttribute("jzhValue", jzhValue)
			.addAttribute("notJzhValue", notJzhValue);
		String[] types = {"tz_backMoney_plat"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/execute/popOnlineBackMoney";
	}
	
	/**
	 * 线上回款
	 * 2015年12月28日
	 * By 陈广鹏
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("onlineBackMoney")
	@ResponseBody
	public String onlineBackMoney(ListItemView view, Model model) {
		BmUtils.initExecuteSearch(view);
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		String message = "处理成功";
		String count = "0";
		if (!BackMoneyPlat.JZH.value.equals(view.getPlatformId())) {
			// 中金、通联平台线上回款
			view.setIsJZH(YoN.FOU.value);
			Map<String, String> resultMap = executeForeachManager.onlineBack(view);
			String msg = resultMap.get("message");
			String cnt = resultMap.get("number");
			if (!ObjectHelper.isEmpty(msg)) {
				message = msg;
			}
			if (!ObjectHelper.isEmpty(cnt)) {
				count = cnt;
			}
		} else if (BackMoneyPlat.JZH.value.equals(view.getPlatformId())) {
			// 金账户线上回款
			view.setIsJZH(YoN.SHI.value);
			ReturnMsg rtn = new ReturnMsg();
			int successCnt = executeForeachManager.onlineJZHBack(view,rtn);
			count=successCnt+"";
			if(StringUtils.isNotBlank(rtn.getMessage())){
				message = rtn.getMessage();
			}
		} else {
			message = "所选平台不支持回款操作";
		}
		message = message.replaceAll("/r/n", "<br/>");
		message = message.replaceAll("业务编号", "出借编号");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("count", count);

		return jsonMapper.toJson(map);
	}
	
	/**
	 * 弹出线下回款弹窗
	 * 2015年12月17日
	 * By 陈广鹏
	 * @param jzhFlag
	 * @param model
	 * @return
	 */
	@RequestMapping("toOfflineBackMoney")
	public String toOfflineBackMoney(String jzhFlag,Model model) {
		String jzhValue = BackMoneyPlat.JZH.value;
		model.addAttribute("jzhFlag", jzhFlag);
		model.addAttribute("jzhValue", jzhValue);
		String[] types = {"tz_backMoney_plat"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/execute/popOfflineBackMoney";
	}
	
	/**
	 * 线下回款导出
	 * 2015年12月29日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("offlineBackMoney")
	public void offlineBackMoney(HttpServletRequest request,HttpServletResponse response, ListItemView view, 
			Model model) {
		BmUtils.initExecuteSearch(view);
		
		if (BackMoneyPlat.FYPT.value.equals(view.getPlatformId())) {
			// 富友导出
			view.setPlatformId(null);
			// 富友代付导出模版对文件名的要求：AC01_导出时的年月日_四位数字从0001开始，例如：AC01_20160303_0001
			String filename = BmConstant.FYDC +"_"+ DateUtils.date2Str(new Date(),"yyyyMMdd");
			BmFyExportor exportor = new BmFyExportor(filename);
			exportor.exportData(view, response);
		} else if (BackMoneyPlat.JZH.value.equals(view.getPlatformId())) {
			// 金账户导出
			view.setPlatformId(null);
			String filename = BmConstant.JZHDC + "_"+ DateUtils.date2Str(new Date(),"yyyyMMdd")+"_0001";
			BmJzhExportor exportor = new BmJzhExportor(filename);
			exportor.exportData(view, response);
		} else if (BackMoneyPlat.WY.value.equals(view.getPlatformId())) {
			// 网银导出
			view.setPlatformId(null);
			String filename = BmConstant.WYDC;
			if (YoN.SHI.value.equals(view.getIsSplit())) {
				BmWySplitExportor exportor = new BmWySplitExportor(filename);
				exportor.exportData(view, response);
			} else {
				BmWyExportor exportor = new BmWyExportor(filename);
				exportor.exportData(view, response);
			}
		} else if (BackMoneyPlat.ZJPT.value.equals(view.getPlatformId())) {
			// 中金导出
			view.setPlatformId(null);
			// 中金代付导出模版对文件名的要求：001572_F年月日四位数字，例如：001572_F201602030001
			String filename = BmConstant.ZJDC +DateUtils.date2Str(new Date(),"yyyyMMdd") + "0001";
			if (ExportType.XLSX.getCode().equals(view.getExportType())) {
				BmZjExportor exportor = new BmZjExportor(filename);
				exportor.exportData(view, response);
			} else if (ExportType.TXT.getCode().equals(view.getExportType())) {
				BmZjTxtExportor exportor = new BmZjTxtExportor(filename);
				exportor.exportData(view, response);
			}
		} else if (BackMoneyPlat.TL.value.equals(view.getPlatformId())) {
			// 通联导出
			view.setPlatformId(null);
			String filename = ExportConstant.TL_EXPORT + DateUtils.date2Str(new Date(),"yyyyMMdd");
			BmTlExportor exportor = new BmTlExportor(filename);
			exportor.exportData(view, response);
		}
	}
	
	/**
	 * 上传回执
	 * 2015年12月29日
	 * By 陈广鹏
	 * @param file
	 * @param view
	 * @return
	 */
	@RequestMapping("uploadResult")
	@ResponseBody
	public String uploadResult(MultipartFile file,  ListItemView view) {
		
		String msg = executeForeachManager.executeUpload(file, view);
		return msg;
	}

}
