package com.creditharmony.fortune.creditor.matching.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.FileMakerJobStatus;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Result;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.entity.CreditorStatistics;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.SendCountConfig;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorStatisticsManager;
import com.creditharmony.fortune.creditor.matching.service.DistributeRecommendationManager;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 分派待推荐债权列表
 * 
 * @Class Name DistributeRecommendationController
 * @author 王鹏飞
 * @Create In 2016年02月04日
 */
@Controller
@RequestMapping("${adminPath}/matchingcreditor")
public class DistributeRecommendationController extends BaseController {

	
	@Autowired
	private DistributeRecommendationManager distributeRecommendationManager;
	
	@Autowired
	private CreditorStatisticsManager creditorStatisticsManager;
	
	@Autowired
	private CreditorAidManager creditorAidManager;

	/**
	 * 分派待推荐债权列表列表页面
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @param page
	 * @return 分派待推荐债权列表列表页面
	 */
	@RequestMapping(value = "/distributeRecommendationList")
	public String distributeRecommendationList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String[] types = {"tz_bill_state","tz_pay_type","tz_matching_flag"};
		FortuneDictUtil.addDicts(model,types);
		// 首先判断当前登录人是否存在首期的并且待匹配的记录
		User user = UserUtils.getUser(UserUtils.getUserId());
		if (user == null){
			addMessage(model, "用户不存在");
			return "creditor/matching/distributeRecommendationList";
		}			

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchingUserId", user.getId());
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("filtermatchingIds", creditorAidManager.getFiltermatchingIds());
		// 当日任务量配置
		SendCountConfig sendCountConfig = distributeRecommendationManager
				.getSendCountConfig(user.getId());		
		
		if (sendCountConfig == null) {
			//未配置过的，页面不予操作
			model.addAttribute("distCount", 0);
			addMessage(model, "请先进行文件制作配置");
			return "creditor/matching/distributeRecommendationList";
		} else {
			model.addAttribute("status", sendCountConfig.getStatus());
			// 离岗状态，页面不予操作
			if(FileMakerJobStatus.LG.value.equals(String.valueOf(sendCountConfig.getStatus()))){
				addMessage(model, "离岗状态无法操作");
				return "creditor/matching/distributeRecommendationList";
			}
		}
		// 查询待推荐/撤销重匹的首期债权数量
		int count = distributeRecommendationManager.getCountOfBond(map);

		// 不存在：给一条首期 并且更新统计量
		if (count <= 0) {
			distributeRecommendationManager.distributeFirstBond();
		}

		Page<MatchingCreditorView> pageview = distributeRecommendationManager
				.listDistributeRecommendation(new Page<MatchingCreditorView>(request, response), map);

		model.addAttribute("page", pageview);

		//统计量获取
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", UserUtils.getUserId());
		param.put("statisticsDate", new Date());
		List<CreditorStatistics> list = creditorStatisticsManager.getBySelective(param);
		CreditorStatistics current = null;
		if(list.size() == 0){
			current = creditorStatisticsManager.updateStatistic(UserUtils.getUserId(), 0, "", null, null);
		}else{
			current = list.get(0);			
		}
		// 当日剩余未匹配账单量
		map=new HashMap<String,Object>();
		map.put("matchingUserId", UserUtils.getUserId());
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag", BillState.FSQ.value);
		MatchingCreditor mor = distributeRecommendationManager.getcountMatching(map);
		int undo = mor.getCountTotal();
		model.addAttribute("unDoCount", undo > 0 ? undo : 0);
		if(current.getCreditTaskCnt() * Constant.DEF_AGAIN_COUNT_PERCENT/100 >= undo ){
			//再次派发
			model.addAttribute("distributeAgain",true);
		}
		// 当日已匹配账单量
		model.addAttribute("orderCount", current.getCreditSqDoneCnt() + current.getCreditFsqDoneCnt());
		// 当天任务量
		model.addAttribute("distCount",current.getCreditTaskCnt());
		if(current.getCreditFsqDistributedCnt() > 0){
			// 已分配的非首期任务量
			model.addAttribute("fsq",current.getCreditFsqDistributedCnt());
		}
		// 弃单后首次派发出现条件
		String drop = request.getParameter("drop");
		if(drop!=null){
			if(drop.equals("1")){
				int scpf = mor.getCountTotal();
				if(scpf==0){
					// 弃单之后出现首次派发条件
					model.addAttribute("scpf",scpf);
				}
			}
		}
		return "creditor/matching/distributeRecommendationList";
	}

	/**
	 * 换单
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @return 换单结果
	 */
	@RequestMapping(value = "/distributeRecommendationList/changeOrder")
	@ResponseBody
	public String changeOrder(String matchingId, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		
		distributeRecommendationManager.changeOrder(UserUtils.getUserId());
		return String.valueOf(Result.zero.value);
	}

	/**
	 * 立即派单
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @return 派单结果
	 */
	@RequestMapping(value = "/distributeRecommendationList/distributeOrder")
	@ResponseBody
	public String distributeOrder(HttpServletRequest request,
			HttpServletResponse response, Model m) {
		//返回的map
		Map<String,Object> result = new HashMap<String,Object>();
		SendCountConfig sendCountConfig = distributeRecommendationManager
				.getSendCountConfig(UserUtils.getUserId());
		//未配置或者离岗状态
		if (sendCountConfig != null
				&& sendCountConfig.getStatus() != null
				&& FileMakerJobStatus.LG.value.equals(sendCountConfig
						.getStatus().toString())) {
			result.put("code", Result.zero.value);
			return jsonMapper.toJson(result);
		}
		
		// 查询待推荐的首期债权数量
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchingUserId", UserUtils.getUserId());
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag", BillState.SQ.value);

		int count = distributeRecommendationManager.getCountOfBond(map);

		// 不存在：给一条首期 并且更新统计量
		if (count <= 0) {
			count = distributeRecommendationManager.distributeFirstBond();
		}

		result.put("count", count);
		return jsonMapper.toJson(result);
	}

	/**
	 * 首次派发
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @param sendCount 派发数量
	 * @return 派单结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/distributeRecommendationList/distributeOrderFirstTime")
	@ResponseBody
	public String distributeOrderFirstTime(HttpServletRequest request,
			HttpServletResponse response, Model m) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		// 根据当前登录人的设置的派发数量添加派单
		User user = UserUtils.getUser(UserUtils.getUserId());
		SendCountConfig sendCountConfig = distributeRecommendationManager
				.getSendCountConfig(user.getId());
		if (sendCountConfig == null || Integer.valueOf(FileMakerJobStatus.LG.value).equals(sendCountConfig.getStatus())){
			result.put("code", Result.zero.value);
			return jsonMapper.toJson(result);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		MatchingCreditor matching = new MatchingCreditor();
		matching.preUpdate();
		matching.setMatchingUserId(user.getId());
		List<String> notIn = creditorAidManager.getFiltermatchingIds();
		if(notIn != null && notIn.size() > 0){
			map.put("notIn", notIn);
		}
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag",BillState.FSQ.value);
		map.put("opeType", Constant.CREDIT_DISTRIBUTED);
		map.put("count", sendCountConfig.getLeaderSendCount());
		map.put("mc", matching);
		map.put("filtermatchingIds", creditorAidManager.getFiltermatchingIds());
		int updateCnt = distributeRecommendationManager.distributeOtherBond(map);
		result.put("code", Result.one.value);
		result.put("updateCnt", updateCnt);
		return jsonMapper.toJson(result);
	}

	/**
	 * 再次派发
	 * 
	 * 2016年4月8日
	 * By 刘雄武
	 * @param request
	 * @param response
	 * @param m
	 * @param againCnt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/distributeRecommendationList/distributeOrderAgain")
	@ResponseBody
	public String distributeOrderAgain(HttpServletRequest request,
			HttpServletResponse response, Model m,String againCnt) throws Exception {
		User user = UserUtils.getUser(UserUtils.getUserId());
		Map<String,Object> result = new HashMap<String,Object>();
		SendCountConfig sendCountConfig = distributeRecommendationManager
				.getSendCountConfig(user.getId());
		if (sendCountConfig == null || Integer.valueOf(FileMakerJobStatus.LG.value).equals(sendCountConfig.getStatus())){
			result.put("code", Result.zero.value);
			return jsonMapper.toJson(result);
		}
		
		int count = Constant.DEF_AGAIN_COUNT_PERCENT * sendCountConfig.getLeaderSendCount() / 100;
		if (StringUtils.isNotEmpty(againCnt)) {
		    count = Integer.parseInt(againCnt);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("count", count);
		MatchingCreditor matching = new MatchingCreditor();
		matching.preUpdate();
		matching.setMatchingUserId(user.getId());
		List<String> notIn = creditorAidManager.getFiltermatchingIds();
		if(notIn != null && notIn.size() > 0){
			map.put("notIn", notIn);
		}
		map.put("mc", matching);
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag",BillState.FSQ.value);
		map.put("opeType", Constant.CREDIT_DISTRIBUTED);
		map.put("filtermatchingIds", creditorAidManager.getFiltermatchingIds());
		int updateCnt = distributeRecommendationManager.distributeOtherBond(map);
		if(count>updateCnt){
			result.put("remind", 1);
		}
		result.put("code", Result.zero.value);
		result.put("updateCnt", updateCnt);
		return jsonMapper.toJson(result);
	}

	/**
	 * 弃单
	 * 
	 * @param matchingId 弃单id集合
	 * @param request
	 * @param response
	 * @param m
	 * @return 弃单结果
	 */
	@RequestMapping(value = "/distributeRecommendationList/dropOrder")
	@ResponseBody
	public String dropOrder(String matchingId, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		if(StringUtils.isBlank(matchingId) || matchingId.split(",").length == 0){
			return String.valueOf(Result.zero.value);
		}
		User user = UserUtils.getUser(UserUtils.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		MatchingCreditor matching = new MatchingCreditor();
		matching.preUpdate();
		matching.setMatchingUserId(user.getId());
		matching.setDropTime(matching.getModifyTime());
		map.put("mc", matching);
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag",BillState.FSQ.value);
		map.put("matchingId", matchingId.split(","));
		distributeRecommendationManager.dropOrders(map);
		return String.valueOf(Result.zero.value);
	}

	/**
	 * 获取用户派发数量
	 * 
	 * @param request
	 * @param response
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/distributeRecommendationList/getUserSendCount")
	@ResponseBody
	public String getUserSendCount(HttpServletRequest request,
			HttpServletResponse response, Model m) {
		User user = UserUtils.getUser(UserUtils.getUserId());
		//获取派发任务配置量
		SendCountConfig sendCountConfig = distributeRecommendationManager
				.getSendCountConfig(user.getId());
		if (sendCountConfig == null) {
			return String.valueOf(0);
		}
		
		return String.valueOf(sendCountConfig.getLeaderSendCount());
	}
	
}