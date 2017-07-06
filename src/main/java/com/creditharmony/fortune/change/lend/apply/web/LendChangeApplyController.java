package com.creditharmony.fortune.change.lend.apply.web;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.LenderchgState;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoadView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.apply.manager.LendChangeApplyManager;
import com.creditharmony.fortune.change.lend.apply.manager.LendFlowApplyManager;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.utils.FortuneDictUtil;



/**
 * 出借信息修改Controller层
 * @Class Name LendChangeController
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChange" })
public class LendChangeApplyController extends BaseController {

	
	@Resource
	private LendChangeApplyManager lcmService;
	@Resource
	private LendFlowApplyManager flowManager;
	
	/**
	 * 查询出借信息
	 * 2015年12月28日
	 * By 刘雄武
	 * @param queryView
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(LendQueryView queryView, boolean flag, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		queryView.setDictDeductStatus(DeductState.HKCG.value);
		queryView.setApplyState(LendState.HKCG.value);
		queryView.setDictApplyEndState(FinishState.YWJ.value);
		queryView.setDictChangeState(LenderchgState.SHTG.value);
		queryView.setManagerId(UserUtils.getUserId());
		queryView.setManagerName(UserUtils.getUser(UserUtils.getUserId()).getName());
		
		Page<LendLoanApplyEx> page = lcmService.findPage(new Page<LendLoanApplyEx>(request, response), queryView);
		List<Role> orgcodetype = UserUtils.getUser().getRoleList();
		for (Role role : orgcodetype) {
			if(role.getId().equals(FortuneRole.FINANCING_MANAGER.id)){
				queryView.setRoleFlag("1");
			}
		}
		
		FortuneOrg orgID = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		if(orgID!=null){
			queryView.setOrgCode(orgID.getId());
		}
		
		List<LendLoanApplyEx> list = page.getList();
		FortuneOrg org = null;
		List<FortuneOrg> orgList = null;
		List<FortuneUser> members = null;
		if (ArrayHelper.isNotEmpty(list)) {
			for (LendLoanApplyEx c : list) {
//				org = RelationShipUtil.getUserOrg(c.getManagerId(),
//						FortuneOrgType.TEAM.key);
				org = RelationShipUtil.getTeamOrg(c.getManagerId());
				if(c.getTrusteeshipNo()==null||"".equals(c.getTrusteeshipNo())){
					c.setIsGoldAccount(YESNO.NO.toString());
				}else{
					c.setIsGoldAccount(YESNO.YES.toString());
				}
			}
		}
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state","tz_lend_state","tz_customer_state","tz_customer_src"});
		model.addAttribute("page", page).addAttribute("queryView", queryView);
		return "/lendChange/lendChangeList";
	}
	
	
	/**
	 * 发起流程
	 * 2015年12月28日
	 * By 刘雄武
	 * @param model
	 * @param workItem
	 * @param bv
	 * @param lendloanapply
	 * @param response
	 * @return
	 */
	@RequestMapping({ "launchFlow" })
	public String launchFlow(Model model, WorkItemView workItem,
			LendLaunchView bv,LendLoanApply lendloanapply, HttpServletResponse response) {

		flowManager.launchFlow(workItem,bv);
	
		addMessage(model, "流程发起成功");
		model.addAttribute("lendloanapply", lendloanapply);
		return "redirect:" + this.adminPath + "/lendChange/list";
	}
	
	/**
	 * 获取出借信息变更历史
	 * 2015年12月10日
	 * By 刘雄武
	 * @param lendloanapply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "goLendChangeHistory" })
	public String goLendChangeHistory(LendLoanApply lendloanapply, Model model, HttpServletRequest request, HttpServletResponse response) {

		Page<LendLoanApply> page = lcmService.getLendChangeHistory(new Page<LendLoanApply>(request, response), lendloanapply);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state","tz_lend_state","tz_customer_state","tz_customer_src"});
		model.addAttribute("page", page);
		return "/lendChange/lendChangeHistory";
	}
	
	/**
	 * 获取变更历史详细
	 * 2015年12月11日
	 * By 刘雄武
	 * @param applyId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "openLendChangeHistoryDetail" })
	public String openLendChangeHistoryDetail(String applyId, Model model, HttpServletRequest request, HttpServletResponse response) {

		LendLoadView bv = lcmService.historyDetail(applyId);
		FortuneDictUtil.addDicts(model, new String[]{"tz_open_bank","com_card_type"});
		model.addAttribute("bv", bv);
		return "/lendChange/lendChangeHistoryDetail";
	}
	
	/**
	 * 导出模板
	 * 2016年1月27日
	 * By 刘雄武
	 * @param custCode
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "exportExcel" })
	public String exportExcel(String custCode, Model model, HttpServletRequest request, HttpServletResponse response) {

		String filename = "出借信息变更申请书.pdf";
		Map<String, Object> filters = new HashMap<String, Object>();
		String cptName = Constant.getProperties.get(ReportType.CJ_BG.getCode());
		filters.put("templateName",cptName);
		lcmService.downloadTemplate(response,filters,filename);
		return null;
	}
    
	/**
	 * 申请书预览
	 * 2016年3月29日
	 * By 郭才林
	 * @param model
	 * @param type
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping({ "sqsPreview"})
	public void sqsPreview(Model model, HttpServletResponse response,LendLoanApply lendloanapply,LendLaunchView bv) {
		
		OutputStream os;
		HttpURLConnection httpConn = null;  
		
		try {
			os = response.getOutputStream();
			String url= lcmService.sqsPreview(bv);
			URL uri = new URL(url);
			httpConn = (HttpURLConnection) uri.openConnection();  
			httpConn.setConnectTimeout(15 * 1000);// 联通最长时间
			httpConn.setReadTimeout( 60 * 1000); // 获取数据最长时间
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("connection", "close");
			httpConn.setRequestProperty("Charsert", "UTF-8");
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			InputStream in = httpConn.getInputStream();  
			com.creditharmony.dm.file.util.FileUtil.writeFile(os, in);
			os.flush();  
			os.close();  
			os = null;  
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httpConn!=null){
				httpConn.disconnect();
			}
		
		}
}
	}
