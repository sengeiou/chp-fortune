package com.creditharmony.fortune.deduct.apply.web;

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

import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.deduct.apply.facade.DeductApplyFacade;
import com.creditharmony.fortune.deduct.apply.service.DeductApplyManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.google.common.collect.Lists;

/**
 * 划扣申请Controller
 * @Class Name DeductApplyController
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/deductApply")
public class DeductApplyController extends DeductCommon {

	@Autowired
	private DeductApplyManager deductApplyManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductApplyFacade deductApplyFacade;

	/**
	 * 划扣申请-->划扣申请列表
	 * 2015年11月30日
	 * By 韩龙
	 * @param deductPoolEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/applyList")
	public String applyList(DeductPoolEx deductPoolEx, HttpServletRequest request,
			HttpServletResponse response,Model model) {
		List<String> dictDeductStatusList = Lists.newArrayList();
		// 待划扣申请
		dictDeductStatusList.add(DeductState.DHKSQ.value);
		// 划扣审批退回
		dictDeductStatusList.add(DeductState.HKSPTH.value);
		// 设置查询划扣状态
		deductPoolEx.setDeductStatusList(dictDeductStatusList);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
		// 分页查询
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = deductManager.findPage(page, deductPoolEx);
		// 获取字典项
		DeductUtils.getDictList(model);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt",deductPoolEx);
		// 当前条件下的出借金额与划扣金额
		Map<String,String> sumMoney = deductManager.getDeductSumMoney(deductPoolEx);
		model.addAttribute("sumMoney",sumMoney);
		return "deduct/deductApplyList";
	}

	/**
	 * 划扣申请-->批量首期债权文件
	 * 2015年11月30日
	 * By 韩龙
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/batchSendFile")
	@ResponseBody
	public String batchSendFile(DeductPoolEx deductPoolEx,String ids,
			HttpServletRequest request,HttpServletResponse response) {
		StringBuffer message = new StringBuffer();
		// 判断是否选中批量操作
		if(ids != null && !"".equals(ids)){
//			String[] applyCodes = ids.split(",");
			String[] applyCodes = DeductUtils.getCodes(ids);
			for (String applyCode : applyCodes) {
				try {
					// 插入邮件待发送列表
//					int result = deductApplyManager.batchSendFile(applyCode, UserUtils.getUser(UserUtils.getUserId()).getName());
					// 实时发送邮件
					int result = deductApplyManager.batchSendFileConstantly(applyCode, UserUtils.getUser(UserUtils.getUserId()).getName());
					if(result == 0){
						message.append("出借编号【"+applyCode+"】发送失败").append("</br>");
					}
				} catch (Exception e) {
					message.append("出借编号【"+applyCode+"】发送失败").append("</br>");
					logger.error("出借编号【"+applyCode+"】发送首期债权文件失败,失败信息:"+e.getMessage(),e);
					FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, applyCode,
						FortuneLogNode.DEDUCT_APPLY.getCode(), FortuneLogLevel.YELLOW.value, 
							"出借编号【"+applyCode+"】发送首期债权文件失败"));
				}
			}
		}else{
			List<DeductPoolEx> dateList = deductApplyManager.findList(deductPoolEx);
			if(dateList != null && dateList.size() > 0){
				for (DeductPoolEx ex : dateList) {
					try {
						int result = deductApplyManager.batchSendFile(ex.getApplyCode(), UserUtils.getUser(UserUtils.getUserId()).getName());
						if(result == 0){
							message.append("出借编号【"+ex.getApplyCode()+"】发送失败").append("</br>");
						}
					} catch (Exception e) {
						message.append("出借编号【"+ex.getApplyCode()+"】发送首期债权文件失败").append("</br>");
						logger.error("出借编号【"+ex.getApplyCode()+"】发送首期债权文件失败,失败信息:"+e.getMessage(),e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, ex.getApplyCode(), 
							FortuneLogNode.DEDUCT_APPLY.getCode(),FortuneLogLevel.YELLOW.value, 
							"出借编号【"+ex.getApplyCode()+"】发送首期债权文件失败"));
					}
				}
			}
		}
		// 返回结果
		return jsonMapper.toJson(message.length() > 0?message.toString():Constant.SUCCESS);
	}

	/**
	 * 划扣申请-->批量划扣申请
	 * 2015年12月24日
	 * By 韩龙
	 * @param ids
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/batchDeductApply")
	@ResponseBody
	public String batchDeductApply(DeductPoolEx deductPoolEx,String ids,HttpServletRequest request,
			HttpServletResponse response) {
		StringBuffer message = new StringBuffer();
		// 判断是否选中批量操作
		deductApplyFacade.batch(deductPoolEx, ids, message);
		// 判断是否成功
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
		// return null;
	}

	/**
	 * 划扣申请-->办理
	 * 2015年12月24日
	 * By 韩龙
	 * @param model
	 * @param applyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conduct")
	public String conduct(Model model,String applyCode,HttpServletRequest request) {
		// TODO 初始化下拉列表信息
		QueryFilter filter = new QueryFilter(request);
		// 获取deductPoolExt对象
		DeductPoolEx deductPoolExt = deductManager.conduct(filter.getFilters());
		model.addAttribute("deductPoolExt", deductPoolExt);
		DeductUtils.getDictList(model);
		return "deduct/deductDetailed";
	}

	/**
	 * 划扣申请-->申请办理
	 * 2015年12月24日
	 * By 韩龙
	 * @param model
	 * @param applyCode
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/applyConduct")
	public String applyConduct(Model model, String applyCode,
			RedirectAttributes redirectAttributes) {
		StringBuffer message = new StringBuffer();
		// 更新状态
		deductApplyFacade.update(message,applyCode);
		if (message.length() == 0) {
			addMessage(redirectAttributes, "划扣申请成功");
			return "redirect:" + super.adminPath + "/deductApply/applyList";
		}
		addMessage(redirectAttributes, "划扣申请失败,该条数据可能已有人操作，请详查！");
		// 判断是否修改成功
//		return "redirect:" + super.adminPath + "/deductApply/conduct?applyCode="
//				+ applyCode;
		return "redirect:" + super.adminPath + "/deductApply/applyList";
	}
}
