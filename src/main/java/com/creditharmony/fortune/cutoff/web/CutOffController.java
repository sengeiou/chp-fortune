package com.creditharmony.fortune.cutoff.web;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.Area;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.service.OrgManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.cutoff.entity.CutOff;
import com.creditharmony.fortune.cutoff.service.CutOffManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.google.common.collect.Lists;


/**
 * 截单控制器
 * @Class Name OrgController
 * @author 周树华
 * @Create In 2016年2月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/cutOff")
public class CutOffController extends BaseController {
	
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private CutOffManager cutOffManager;

    @RequestMapping(value="list")
	public String list(CutOff cutOff, HttpServletRequest request,
			HttpServletResponse response, Model model){
    	//获取所有门店
//		List<Org> allStores = orgManager.findAllStores();
//		model.addAttribute("allStores", allStores);
		//获取所有已分派的组
		Page<CutOff> page = cutOffManager.findList(new Page<CutOff>(request, response),cutOff);
		model.addAttribute("page", page);
		model.addAttribute("cutOff", cutOff);
		FortuneDictUtil.addDicts(model, new String[]{"com_use_flag"});
		return "cutOffApply/cutOffList";
	}
    
	@RequestMapping(value="save")
	public String save(HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException{
		String storesIds = request.getParameter("storesIdList");
		String endTime = request.getParameter("endTime");
		String id = request.getParameter("id");
		String delFlag = request.getParameter("delFlag");
		if(StringUtils.isNotEmpty(storesIds) && StringUtils.isNotEmpty(storesIds)){
			List<CutOff> cutOffList = Lists.newArrayList();
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endTimeN = sdf.parse(endTime);*/
			String[] storesStr = storesIds.split(",");
			for(String storesId:storesStr){
				if(StringUtils.isNotEmpty(storesId)){
					CutOff cutOffN = new CutOff();
					cutOffN.setId(id);
					cutOffN.setOrgId(storesId);
					cutOffN.setEndTime(endTime);
					cutOffN.setDelFlag(delFlag);
					cutOffList.add(cutOffN);
				}
			}
			cutOffManager.saveList(cutOffList);
			addMessage(redirectAttributes, "设置截单时间成功");
		}
		return "redirect:" + adminPath + "/cutOff/list";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id")String id,RedirectAttributes redirectAttributes){
		String[] ids = id.split(",");
		for (String tempId : ids) {
			CutOff cutOff = new CutOff();
			cutOff.setId(tempId);
			cutOffManager.delete(cutOff);
		}
		addMessage(redirectAttributes, "删除数据成功");
		return "redirect:" + adminPath + "/cutOff/list";
	}
	
	/**
	 * 跳转转到修改页面
	 * 2016年3月5日
	 * By 周俊
	 * @param id
	 * @return
	 */
	@RequestMapping("/goEdit")
	public String goEdit(String id,Model model){
		CutOff cutOff = new CutOff();
		cutOff.setId(id);
		model.addAttribute("cutOff", cutOffManager.get(cutOff));
		return "/cutOffApply/cutOffEdit";
	}
	
	/**
	 * 修改门店截单信息
	 * 2016年3月5日
	 * By 周俊
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="edit")
	public String edit(CutOff cutOff ,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException{
				cutOffManager.updateEdit(cutOff);
				addMessage(redirectAttributes, "修改截单信息成功");
			return "redirect:" + adminPath + "/cutOff/list";
	}
	
	/**
	 * 获取营业部列表
	 * 2016年4月14日
	 * By 周俊
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "selectOrgList")
	public String selectStorePage(Org org,HttpServletRequest request,HttpServletResponse response, Model model) {
		if(null==org.getArea()){
			Area area = new Area();
			org.setArea(area);
		}
		Page<Org> orgs = orgManager.findStoresPage(new Page<Org>(request, response),org, FortuneOrgType.STORE.key);
		List<String> orgIdList = cutOffManager.findOrgIdList();
		for(Org orgN:orgs.getList()){
			if(null!=orgIdList && orgIdList.contains(orgN.getId())){
				orgN.setStoresSelected("true");
			}
		}
		model.addAttribute("page", orgs);
		model.addAttribute("storesSelected", org.getStoresSelected());
		model.addAttribute("queryURL", "selectOrgList");
		return "cutOffApply/storesList";
	}

	/**
	 * ajax获得当前截单信息
	 * 2016年2月24日
	 * By 周俊
	 * @param applyDeductDay
	 * @return
	 */
	@RequestMapping("/ajaxCutoffInfo")	
	@ResponseBody
	public String findCutOffInfo(String applyDeductDay,String applyDate){
		try {
			cutOffManager.findCutOffInfo(applyDeductDay,applyDate);
		} catch (Exception e) {
			return jsonMapper.toJson(e.getMessage());
		}
		return jsonMapper.toJson("");
		
	}
}
