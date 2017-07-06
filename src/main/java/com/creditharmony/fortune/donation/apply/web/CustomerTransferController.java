package com.creditharmony.fortune.donation.apply.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.customer.web.CustomerController;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.apply.entity.Customertransfe;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.apply.manager.CustomerTransferFlowManager;
import com.creditharmony.fortune.donation.apply.manager.CustomerTransferManager;
import com.creditharmony.fortune.utils.RoleOrgUtil;

/**
 * 转赠业务Controller
 * @Class Name CustomerTransferController
 * @author 刘雄武
 * @Create In 2016年3月3日
 */
@Controller
@RequestMapping("${adminPath}/customerTransfer")
public class CustomerTransferController extends BaseController{

	@Resource
	private CustomerTransferManager ctmService;
	@Resource
	private CustomerTransferFlowManager flowManager;
	@Autowired
	private CustomerController customerController;
	@Autowired
	private IntKeyLogService intKeyLogService;

	/**
	 * 添加客户手机号存在弹出
	 * 2016年3月4日
	 * By 刘雄武
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "goCustomerTransfer" })
	public String goCustomerTransfer( Model model,String empCode,HttpServletRequest request, HttpServletResponse response) {
		
		List<Customertransfe> customertrsfer =ctmService.getCustomerInfo(empCode);
		model.addAttribute("customertlist", customertrsfer);
		return "/donation/customerTransferList";
	}
	
	/**
	 * 转赠验证三网条件
	 * 2016年3月4日
	 * By 刘雄武
	 * @param model
	 * @param custCode
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping({ "checkCustomerTransfer" })
	public String checkCustomerTransfer( Model model,String custCode,HttpServletRequest request, HttpServletResponse response) {
		String customertrsfer = ctmService.checkCustomerTransfer(custCode);
		return customertrsfer;
	}
	
	/**
	 * 查询理财经理信息
	 * 2016年3月5日
	 * By 刘雄武
	 * @param model
	 * @param custCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "searchCustomerManager" })
	public String searchCustomerManager( Model model,CustomerQueryView query,HttpServletRequest request, HttpServletResponse response) {
		
	   Page<CustomerManagerinfo> listct = ctmService.getManagerinfobyCode(new Page<CustomerManagerinfo>(request, response),query);
	   List<CustomerManagerinfo> list = listct.getList();
	   UserRoleOrgEx org = null;
	   for (CustomerManagerinfo c : list) {
		org = RoleOrgUtil.findStoreOrgByManager(c.getManagerId());
		c.setDepartmentNameNew(org.getOrgName());
		c.setDepartmentId(org.getOrgId());
		FortuneOrg team = RelationShipUtil.getTeamOrg(c.getManagerId());
		if(team!=null){
			List<String> type = new ArrayList<String>();
			type.add(FortuneRole.TEAM_MANAGER.id);
			List<FortuneUser> fuser=RelationShipUtil.getOrgMember(team.getId(),type, UserStatus.ON);
			if(fuser.size()>0){
				c.setTeamManagerCode(fuser.get(0).getCode());
				c.setTeamManagerName(fuser.get(0).getName());
			}
			
		}
	}
	   String remind = null;
	   if(list.size()==0){
		   remind = "0";
	   }else{
		   remind = "1";
	   }
	   model.addAttribute("page", listct);
	   model.addAttribute("remind", remind);
	   model.addAttribute("query", query);
		return "/donation/customerTransferLaunchSub";
	}
	
	/**
	 * 转赠发起流程
	 * 2016年3月7日
	 * By 刘雄武
	 * @param model
	 * @param workItem
	 * @param bv
	 * @param response
	 * @return
	 */
	@RequestMapping({ "launchFlow" })
	public String launchFlow(Model model, WorkItemView workItem, CustomertransferLaunchView bv,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			flowManager.launchFlow(workItem, bv);
			addMessage(model, "流程发起成功");
			customerController.list(model, new Customer(), request, response);
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "流程发起异常!");
			addMessage(model, "流程发起异常，请联系相关人员进行处理！");
		}
		return "/customer/customerList";
	}
	
	/**
	 * 手机校验判断三网
	 * 2016年3月9日
	 * By 刘雄武
	 * @param customer
	 * @return
	 */
	@ResponseBody
	@RequestMapping("check")
	public String check(@ModelAttribute LenderInitView view) {
		String results = ctmService.checkCustomer(view);
		// return "redirect:" + adminPath + "/customer/investment";
		return results;
	}
}
