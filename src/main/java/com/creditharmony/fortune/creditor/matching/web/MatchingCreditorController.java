package com.creditharmony.fortune.creditor.matching.web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.CreditState;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.LoanDistinguish;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.TracesType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.service.BorrowManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthableManager;
import com.creditharmony.fortune.borrow.service.LoanphaseManager;
import com.creditharmony.fortune.borrow.view.BorrowMonthableView;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.config.rule.dao.MatchingRuleDao;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleConfig;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleLower;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleProporti;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;
import com.creditharmony.fortune.creditor.matching.entity.CreditorperUpper;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingBorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorConfigManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorStatisticsManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorTradeManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorperUpperManager;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;
import com.creditharmony.fortune.creditor.matching.utils.ExportExcelUtils;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeBorrowView;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeMonthAbleView;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.obligatory.manager.CreditLocationListManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;
/**
 * 待债权推荐信息控制层
 * @Class Name MatchingCreditorController
 * @author 柳慧
 * @Create In 2015年12月15日
 */
@Controller
@RequestMapping(value  =  "${adminPath}/matchingcreditor")
public class MatchingCreditorController extends BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MatchingCreditorManager matchingCreditorManager; //  待债权推荐信息表
	
	@Autowired 
	private LoanphaseManager loanphaseManager; //  分期收益
	
	@Autowired 
	private BorrowManager borrowManager; //  可用债权
	
	@Autowired 
	private CreditorTradeManager creditorTradeManager; //  可用债权交易
	
	@Autowired 
	private CreditorConfigManager creditorConfigManager; //  可用债权交易
	
	@Autowired
	private BorrowMonthableManager borrowMonthableManager; //月满盈可用债权池
	
	@Autowired 
	private LoanApplyManager loanApplyManager; // 出借申请
	
	@Autowired 
	private MatchingRuleDao matchingRuleDao; // 债权匹配规则管理Dao
	
	@Autowired 
	private CreditorperUpperManager creditorperUpperManager; // 借款人匹配上限;
	
	@Autowired 
	private ProductMatchingRateManager productMatchingRateManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private CreditLocationListManager creditLocationListManager;
	
	@Autowired
	private CreditorStatisticsManager creditorStatisticManager;
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	
	/**
	 *  * 待债权推荐信息主页面
	 * 2015年12月28日
	 * By 柳慧
	 * @param model
	 * @param ex 实体
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"list",""})
	public String findList(Model model,MatchingCreditorEx ex,HttpServletRequest request,HttpServletResponse response,String returnFlag){
		if(!StringUtils.isEmpty(returnFlag)){
			ex=(MatchingCreditorEx) request.getSession().getAttribute("ex");
		}else{
			request.getSession().setAttribute("ex", ex);
		}
		MatchingCreditorEx search = (MatchingCreditorEx)SerializationUtils.clone(ex);
		Page<MatchingCreditorView> page  =  new Page<MatchingCreditorView>(request,response);
		List<String> matchingStatusLst = new ArrayList<String>();
		matchingStatusLst.add(MatchingStatus.DTJ.value);
		matchingStatusLst.add(MatchingStatus.CXCP.value);
		search.setMatchingStatusLst(matchingStatusLst); // 设置推荐状态
		List<String> lendStatusLst = new ArrayList<String>();
		lendStatusLst.add(LendState.SPTG.value);
		lendStatusLst.add(LendState.DHK.value);
		lendStatusLst.add(LendState.HKCLZ.value);
		lendStatusLst.add(LendState.HKCG.value);
		search.setLendStatusLst(lendStatusLst);
		page = matchingCreditorManager.findPage(page, search);
		
		String[] types = {"tz_bill_day","tz_bill_state","tz_pay_type","tz_matching_status","tz_deduct_plat","tz_open_bank","tz_matching_flag"};
		FortuneDictUtil.addDicts(model,types);
		Map<String,Object> matchingTotal =  matchingCreditorManager.findTotal(search);
		model.addAttribute("numberTotal",matchingTotal.get("numberTotal")==null?"0":matchingTotal.get("numberTotal"));
		model.addAttribute("lendMoneyTotal",matchingTotal.get("lendMoneyTotal")==null?"0":matchingTotal.get("lendMoneyTotal"));
		model.addAttribute("borrowQuotaTotal",matchingTotal.get("borrowQuotaTotal")==null?"0":matchingTotal.get("borrowQuotaTotal"));
		model.addAttribute("page",page);
		model.addAttribute("matchingCreditorEx",ex);
		return "/creditor/matching/matchingCreditor";
	}
	
	/**
	 * 导出功能
	 * 2016年4月25日
	 * By 高士芳
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/outExcel")
	public void outExcel(MatchingCreditorEx ex,String matchingUserflag,HttpServletRequest request,HttpServletResponse response){
		MatchingCreditorEx search = (MatchingCreditorEx)SerializationUtils.clone(ex);
		List<String> matchingStatusLst = new ArrayList<String>();
		matchingStatusLst.add(MatchingStatus.DTJ.value);
		matchingStatusLst.add(MatchingStatus.CXCP.value);
		search.setMatchingStatusLst(matchingStatusLst); // 设置推荐状态
		if(matchingUserflag!=null){
			if(matchingUserflag.equals("1")){
				search.setMatchingUserId(UserUtils.getUserId());
			}
		}
		List<String> lendStatusLst = new ArrayList<String>();
		lendStatusLst.add(LendState.SPTG.value);
		lendStatusLst.add(LendState.DHK.value);
		lendStatusLst.add(LendState.HKCLZ.value);
		lendStatusLst.add(LendState.HKCG.value);
		search.setLendStatusLst(lendStatusLst);
		//判断页面是通过搜索栏导出，还是通过复选框导出
		search = matchingCreditorManager.judgeIds(search);
		String fileName = FileType.DTJZQXXLB.getName()+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		String namespace = "com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao.outExcel";
		ExportExcelUtils.exportData(search, response, namespace, fileName);	
	}
	
	/**
	 *  进入待推荐债权推荐详细页面
	 * 2015年12月28日
	 * By 柳慧
	 * @param from
	 * @param matchingId
	 * @param model
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"toHandleFrist"})
	public String toHandleFrist(String matchingId,String distributedFlag,Model model,HttpServletRequest request,HttpServletResponse response){
		String[] types = {"tz_bill_state","tz_credit_src","jk_prof_type","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model,types);
		String pagePath = "/creditor/matching/matchingCreditorHandleFrist"; //  非月满盈首期页面
		int  ylnumber = 0; // 已选记录数
		BigDecimal ylmoney =new BigDecimal("0"); // 已选债权总金额
		String matchingBeforeDifferent = "0";	//全是信借
		 //  获取出借信息
		MatchingCreditorView mc  =  creditorAidManager.getMatchingCreditorViewByMatchingId(matchingId);
		String productCode  =  mc.getProductCode();
		BigDecimal MatchingBorrowQuota = new BigDecimal(String.valueOf(BigDecimalUtil.round(mc.getMatchingBorrowQuota().doubleValue(), 2)));
		model.addAttribute("MatchingBorrowQuota", MatchingBorrowQuota);
		 //  设置月利率
		BigDecimal  loanMonthRate = getppRate(mc.getProductCode(),mc.getStartApplyLendMoney(),
				DateUtils.formatDate(mc.getApplyLendDay(), "yyyy-MM-dd"),mc.getMatchingFirstdayFlag(),mc.getMatchingBillDay(),DateUtils.formatDate(mc.getMatchingInterestStart(), "yyyy-MM-dd")); 
		List<CreditorTradeBorrowView> creditorTradeBorrowViews = new ArrayList<CreditorTradeBorrowView>();
		List<CreditorTradeMonthAbleView> creditorTradeMonthAbleViews = new ArrayList<CreditorTradeMonthAbleView>();
		if(!StringUtils.isEmpty(productCode) &&!ProductCode.YMY.value.equals(productCode)){ //  非月满盈产品
			 //  可用债权列表变量
			List<Borrow> borrows = new ArrayList<Borrow>();
			MatchingBorrowEx mbEx  =  new MatchingBorrowEx();
			
			mbEx.setLoanMonthRate(loanMonthRate);
			 /** 首期自动匹配规则 **/
			if(mc.getMatchingFirstdayFlag().equals(BillState.SQ.value)){
				//  获取既有及历史债权列表 (可用债权列表)
				Map<String,String > hisListParam = new HashMap<String, String>();
				hisListParam.put("customerCode", mc.getCustomerCode());
				hisListParam.put("creditNode", Node.KYZQ.value);
				hisListParam.put("dictTradeCreditStatus", CreditTradestate.WKSBGB.value);
				creditorTradeBorrowViews  =  creditorAidManager.getCreditorTradeBorrowView(hisListParam);
				//  获取既有及历史债权列表 (月满盈可用债权列表)
				hisListParam.put("creditNode", Node.YMYKY.value);
				creditorTradeMonthAbleViews = creditorAidManager.getCreditorTradeMonthAbleViews(hisListParam);
				// 只匹配首次还款日期早于或等于出借人的首期账单日的债权
				mbEx.setLoanBackmoneyFirday(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));
				model.addAttribute("creditorTradeMonthAbleViews", creditorTradeMonthAbleViews);
				model.addAttribute("creditorTradeBorrowViews", creditorTradeBorrowViews);
				/** 非首期自动匹配规则 **/
			}else{  
				 //  获取既有及历史债权列表(显示历史既有债权列表中使用) ，非首期历史既有债权只显示 本次出借人员
				Map<String, Object> ctbvMap = setHisParam(mc);
				List<CreditorTradeBorrowView>  creditorTradeBorrowViewsShow  =  creditorAidManager.getCreditorTradeBorrowViewNOFrist(ctbvMap);
				
				if(null != ctbvMap.get("matchingNowFrom")){
					ctbvMap.remove("matchingNowFrom");
				}
				if(null != ctbvMap.get("matchingNowTo")){
					ctbvMap.remove("matchingNowTo");
				}
				
				List<CreditorTradeBorrowView>  creditorTradeBorrowViewsShowTmp  =  creditorAidManager.getCreditorTradeBorrowViewNOFrist(ctbvMap);
				
				creditorTradeBorrowViews = creditorTradeBorrowViewsShow;// 非首期只过滤本笔出借的债权
				// 如果历史记录有信借，则该期待推荐只能匹配信借债权
				if(creditorTradeBorrowViewsShow !=null && creditorTradeBorrowViewsShow.size()>0){
					boolean isOnlyXinjieFlag = true;
					for(CreditorTradeBorrowView bv :creditorTradeBorrowViewsShow){
						if(!bv.getDictLoanType().equals(LoanType.HONOUR_LOAN.getCode())){
							isOnlyXinjieFlag = false;
							break;
						}
					}
					if(isOnlyXinjieFlag){
						mbEx.setLoanType(LoanType.HONOUR_LOAN.getCode());
						 model.addAttribute("isOnlyXinjieFlag", LoanType.HONOUR_LOAN.getCode());
					}else{
						 model.addAttribute("isOnlyXinjieFlag", "");
					}
				}
				if(creditorTradeBorrowViewsShowTmp !=null && creditorTradeBorrowViewsShowTmp.size()>0){
					for(CreditorTradeBorrowView bv :creditorTradeBorrowViewsShowTmp){
						if(!bv.getDictLoanType().equals(LoanType.HONOUR_LOAN.getCode())){	//历史中存在非信借数据
							matchingBeforeDifferent = "1";
							break;
						}
					}
					 model.addAttribute("matchingBeforeDifferent", matchingBeforeDifferent);
				}
				mbEx.setLoanBackmoneyFirday(mc.getMatchingExpireDay());
				// 非首期历史既有债权只显示 本次出借人员
				 model.addAttribute("creditorTradeBorrowViews", creditorTradeBorrowViewsShow);
				 pagePath = "/creditor/matching/matchingCreditorHandle"; //  非月满盈非首期页面
			}
			// 公共匹配规则
		    fymyppCommon(mbEx,mc,creditorTradeBorrowViews,creditorTradeMonthAbleViews);
			 //  获取匹配符合条件的可用债权
			borrows = this.getppBorrow(mc, mbEx);		
			ylnumber = borrows.size();
			if(ylnumber==0){
				ylmoney = BigDecimal.ZERO;
			}else{
				ylmoney = MatchingBorrowQuota;
			}
			model.addAttribute("borrows", borrows);
			
		}else{ 
			//  获取既有及历史债权列表 (可用债权列表)
			Map<String,String > hisListParam = new HashMap<String, String>();
			hisListParam.put("customerCode", mc.getCustomerCode());
			hisListParam.put("creditNode", Node.KYZQ.value);
			hisListParam.put("dictTradeCreditStatus", CreditTradestate.WKSBGB.value);
			creditorTradeBorrowViews  =  creditorAidManager.getCreditorTradeBorrowView(hisListParam);
			 //  获取既有及历史债权列表 (月满盈可用债权列表)
			hisListParam.put("creditNode", Node.YMYKY.value);
			creditorTradeMonthAbleViews = creditorAidManager.getCreditorTradeMonthAbleViews(hisListParam);
			//  月满盈产品
			List<BorrowMonthable> borrowMonthables = new ArrayList<BorrowMonthable>();  
			BorrowMonthable borrowMonthable  = new BorrowMonthable();
			borrowMonthable.setDictLoanFreeFlag(CreditState.KY.value);// 状态可用
			if(mc.getApplyPay().equals(PayMent.ZJTG.value)){
				borrowMonthable.setTrusteeFlagOrderby("asc");
			}else{
				borrowMonthable.setTrusteeFlagOrderby("desc");
			}
			 /** 过滤本笔出借的既有历史出借中匹配过的借款人**/
			List<String> loanIdCrards =getHisListLoanCradId(mc,creditorTradeBorrowViews,creditorTradeMonthAbleViews);
			if(loanIdCrards.size()>0){
				borrowMonthable.setOldLoadIdCard(loanIdCrards);
			}
			//  债权单次匹配限额
			 borrowMonthable.setPpxy(this.getCreditorperUpper());
			 borrowMonthable.setLoanMonthRate(loanMonthRate); // 债权匹配月利率
			 /** 可用债权配置列表 **/
			 Map<String ,Object> configParamMap = new HashMap<String,Object>();
			 configParamMap.put("configCity", mc.getCityOrgId());
			 configParamMap.put("configType", mc.getMatchingFirstdayFlag());
			 configParamMap.put("configYy", mc.getStoreOrgId());
			 List<String> creditLocationList =  creditLocationListManager.getloanIdCards(configParamMap);
			 if(creditLocationList!=null && creditLocationList.size()>0){
				 borrowMonthable.setFilterLoanIdCards(creditLocationList);// 设置债权配置列表
			 }
			// 初始借款日期（首次还款日）必须早于或等于本笔出借的到期日。
			borrowMonthable.setBorrowBackmoneyFirday(DateUtils.formatDate(mc.getApplyExpireDay(), "yyyy-MM-dd"));
			// 月满盈匹配
			 borrowMonthables = getppBorrowMonth(mc, borrowMonthable);
			 ylnumber = borrowMonthables.size();
			 if(ylnumber==0){
				ylmoney = BigDecimal.ZERO;
			}else{
				ylmoney = MatchingBorrowQuota;
			}
			 
			model.addAttribute("borrowMonthables", borrowMonthables);
			model.addAttribute("creditorTradeMonthAbleViews", creditorTradeMonthAbleViews);
			model.addAttribute("creditorTradeBorrowViews", creditorTradeBorrowViews);
			pagePath = "/creditor/matching/matchingCreditorHandleBorrow"; // 月满盈匹配页面
		}
		model.addAttribute("mc", mc);
		model.addAttribute("ylnumber", ylnumber);
		model.addAttribute("ylmoney", ylmoney);
		//分派债权页面标识
		model.addAttribute("distributedFlag", distributedFlag);
		return pagePath;

	}
	
	/**
	 * 通过产品类型   设置非首期的历史记录
	 * 2016年5月30日
	 * By 柳慧
	 * @param mc
	 */
	private Map<String, Object> setHisParam(MatchingCreditorView mc) {
		Map<String, Object> paramShowMap = new HashMap<String, Object>();
		paramShowMap.put("lendCode", mc.getLendCode());
		paramShowMap.put("creditStatus", CreditTradestate.WKSBGB.value);
		paramShowMap.put("matchingNow",String.valueOf(mc.getMatchingNow()));
		// 信和宝 并且12月一回息
		if(mc.getProductCode().equals(com.creditharmony.core.fortune.type.ProductCode.XHB.value)){
			if(mc.getBackType()!=null && mc.getBackType().equals(com.creditharmony.core.fortune.type.XinhebaoType.XHB12.value)){
				if(mc.getMatchingNow()<=12){
					paramShowMap.put("matchingNowFrom",1);
					paramShowMap.put("matchingNowTo",12);
				}else{
					paramShowMap.put("matchingNowFrom",13);
				}
				
			}
		}else if(mc.getProductCode().equals(com.creditharmony.core.fortune.type.ProductCode.XHBA.value)){// 信和宝A
			if(mc.getMatchingNow()<=12){
				paramShowMap.put("matchingNowFrom",1);
				paramShowMap.put("matchingNowTo",12);
			}else{
				paramShowMap.put("matchingNowFrom",13);
			}
		}else if(mc.getProductCode().equals(com.creditharmony.core.fortune.type.ProductCode.XHBC.value)){
			if(mc.getMatchingNow()<=6){
				paramShowMap.put("matchingNowFrom",1);
				paramShowMap.put("matchingNowTo",6);
			}else if(mc.getMatchingNow()>=7 && mc.getMatchingNow()<=12){
				paramShowMap.put("matchingNowFrom",7);
				paramShowMap.put("matchingNowTo",12);
			}else if(mc.getMatchingNow()>=13 && mc.getMatchingNow()<=18){
				paramShowMap.put("matchingNowFrom",13);
				paramShowMap.put("matchingNowTo",18);
			}else if(mc.getMatchingNow()>=18 ){
				paramShowMap.put("matchingNowFrom",18);
			}
			
		}
		return paramShowMap;
	}

	/**
	 * 非月满盈匹配公共方法
	 * 2016年4月13日
	 * By 柳慧
	 * @param mbEx
	 * @param mc
	 * @return
	 */
	private void fymyppCommon(MatchingBorrowEx mbEx,MatchingCreditorView mc, List<CreditorTradeBorrowView> creditorTradeBorrowViews,List<CreditorTradeMonthAbleView> creditorTradeMonthAbleViews){
		 mbEx.setDictLoanFreeFlag(CreditState.KY.value);
		 // 获取既有省份证号
		 List<String> loanIdCrards =getHisListLoanCradId(mc,creditorTradeBorrowViews,creditorTradeMonthAbleViews);
		if(loanIdCrards.size()>0){
			mbEx.setLoanIdCards(loanIdCrards);
		}
		 List<Integer>  loanBackmoneyDay = new ArrayList<Integer>(); //  还款日
		    int matchingBillDay  =  mc.getMatchingBillDay(); //  帐单日
		    List<CreditorConfig> creditorConfigs  =  matchingCreditorManager.getfindByMatchingBillDay(matchingBillDay,mc.getMatchingFirstdayFlag()); //  根据还款日查询错期配置
		if(creditorConfigs!= null && creditorConfigs.size()>0){
			for(CreditorConfig cc:creditorConfigs){
				loanBackmoneyDay.add(cc.getConfigRepayDay());
			}
			mbEx.setLoanBackmoneyDays(loanBackmoneyDay);
		 }
		//  债权单次匹配限额
		 mbEx.setPpxy(this.getCreditorperUpper());
		 List<String> creditLocationList = matchingCreditorManager.getUseableLoanInfos(mc);
		 if(creditLocationList!=null && creditLocationList.size()>0){
			 mbEx.setFilterLoanIdCards(creditLocationList);// 设置债权配置列表
		 }
		 if(mc.getApplyPay().equals(PayMent.ZJTG.value)){
				mbEx.setTrusteeFlagOrderBy("asc");
		}else{
			mbEx.setTrusteeFlagOrderBy("desc");
		}	
	}


	
	/**
	 * 通过待推荐ID获取待推荐债权列表  非月满盈
	 * 2015年12月28日
	 * By 柳慧
	 * @param matchingId
	 * @param borrow
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getBorrowDetail"})
	public String getBorrowDetail(String ids, String creditValueIds,String matchingId,Borrow borrow,Model model,HttpServletRequest request,HttpServletResponse response){
	    //  分页查询
		Page<Borrow> page  =  new Page<Borrow>(request,response);
		Map<String,BigDecimal> loanMonthRateMap = getBorrowDetailInfo(creditValueIds, matchingId, borrow, model, Constant.SHOW_HIS_FLG_YES,true);
		page = borrowManager.findBorrowByEx(page, borrow,ids);
		MatchingCreditorView matchingCreditorView  = matchingCreditorManager.getMatchingCreditorViewByMatchingId(matchingId);
		int day = matchingCreditorView.getMatchingBillDay();
		String flag = matchingCreditorView.getMatchingFirstdayFlag();
		CreditorConfig creditorConfig = new CreditorConfig();
		creditorConfig.setConfigCreditDay(day);
		creditorConfig.setDictConfigStatus(flag);
		creditorConfig.setDictConfigZdr("1");
		List<CreditorConfig> creditorConfigList = creditorConfigManager.findByMatchingBillDay(creditorConfig);
		model.addAttribute("matchingId",matchingId);
		model.addAttribute("creditValueIds",creditValueIds);
		model.addAttribute("mc", matchingCreditorView);
		model.addAttribute("lower",loanMonthRateMap.get("lower"));
		model.addAttribute("upper",loanMonthRateMap.get("upper"));
		model.addAttribute("borrow",borrow);
		model.addAttribute("creditorConfigList", creditorConfigList);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		return "/creditor/matching/borrowDetail";
	}
	
	/**
	 * 通过待推荐ID获取待推荐债权列表  非月满盈  数据列表
	 * 2015年12月28日
	 * By 柳慧
	 * @param matchingId
	 * @param borrow
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getBorrowDetailList"})
	public String getBorrowDetailList(String ids, String creditValueIds,String matchingId,String xsjylszq,Borrow borrow,Model model,HttpServletRequest request,HttpServletResponse response){
		//  分页查询
		Page<Borrow> page  =  new Page<Borrow>(request,response);
		getBorrowDetailInfo(creditValueIds, matchingId, borrow, model, xsjylszq,false);
		borrow.setLoanMonthsSurplus(1); 
		borrow.setLoanCreditValue(BigDecimal.ZERO);
		model.addAttribute("matchingId",matchingId);
		model.addAttribute("creditValueIds",creditValueIds);
		page = borrowManager.findBorrowByEx(page, borrow,ids);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("id",ids);
		List<String> list = new ArrayList<String>();
		if(ids != null && !("".equals(ids))){
			String[] idList = ids.split(",");
			for(int i=0;i<idList.length;i++){
				list.add(idList[i]);
			}
			model.addAttribute("ids", list);
		}
		return "/creditor/matching/borrowDetailList";
	}
	
	/**
	 * 通过待推荐ID获取待推荐债权列表  非月满盈 信息
	 * 2015年12月28日
	 * By 柳慧
	 * @param matchingId
	 * @param borrow
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	private Map<String,BigDecimal>  getBorrowDetailInfo(String creditValueIds,String matchingId,Borrow borrow,Model model, String xsjylszq, boolean flg){
		String[] types = {"tz_repay_day","jk_prof_type","tz_credit_src","tz_zjtr_mark","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model,types);
		//  获取出借信息
		MatchingCreditorView mc  =  creditorAidManager.getMatchingCreditorViewByMatchingId(matchingId);
		List<Integer>  loanBackmoneyDay = new ArrayList<Integer>(); //  还款日
		int matchingBillDay  =  mc.getMatchingBillDay(); //  帐单日
		List<CreditorConfig> creditorConfigs  =  matchingCreditorManager.getfindByMatchingBillDay(matchingBillDay,mc.getMatchingFirstdayFlag()); //  根据还款日查询错期配置
		if(creditorConfigs!= null && creditorConfigs.size()>0){
			for(CreditorConfig cc:creditorConfigs){
				loanBackmoneyDay.add(cc.getConfigRepayDay());
			}
			borrow.setLoanBackmoneyDays(loanBackmoneyDay);
		}
		Map<String,BigDecimal> loanMonthRateMap  = getppRateToMap(mc.getProductCode(),
				mc.getStartApplyLendMoney(), DateUtils.formatDate(
						mc.getApplyLendDay(), "yyyy-MM-dd"),
				mc.getMatchingFirstdayFlag(), mc.getMatchingBillDay(),
				DateUtils.formatDate(mc.getMatchingInterestStart(),
						"yyyy-MM-dd"));
		if (flg) {
			borrow.setLoanMonthRate(new BigDecimal(String.valueOf(BigDecimalUtil.round(loanMonthRateMap.get("lower").doubleValue(), 5))));
			borrow.setLoanCreditValueFrom(new BigDecimal("1"));
			borrow.setLoanMonthsSurplus(1);
			borrow.setLoanMonthsSurplusFrom(1);
		}
		/*	// 只匹配首次还款日期早于或等于出借人的本期账单日的债权
		 Map<String,String> param = new HashMap<String, String>();
		 param.put("lendCode", mc.getLendCode());
		 param.put("fristDayFalg", BillState.SQ.value);
		 Date sqqxrq = creditorAidManager.getFristLendDay(param);//获取首期起息日期*/	
		borrow.setLoanBackmoneyFirday(mc.getMatchingExpireDay());
		if (!StringUtils.isEmpty(xsjylszq) && !Constant.SHOW_HIS_FLG_NO.equals(xsjylszq)) {
		if (mc.getMatchingFirstdayFlag().equals(BillState.SQ.value)) {
				// 获取既有及历史债权列表
				Map<String, String> hisListParam = new HashMap<String, String>();
				hisListParam.put("customerCode", mc.getCustomerCode());
				hisListParam.put("dictTradeCreditStatus",
						CreditTradestate.WKSBGB.value);
				List<CreditorTradeBorrowView> creditorTradeBorrowViews = creditorAidManager
						.getCreditorTradeBorrowView(hisListParam);
				if (creditorTradeBorrowViews != null
						&& creditorTradeBorrowViews.size() > 0) {// 过滤本笔出借的既有历史出借中匹配过的借款人
					List<String> loanIdCrards = new ArrayList<String>();
					for (CreditorTradeBorrowView creditorTradeBorrowView : creditorTradeBorrowViews) {
						if (creditorTradeBorrowView.getLoanIdcard() != null)
							loanIdCrards.add(creditorTradeBorrowView.getLoanIdcard());
					}
					borrow.setLoanIdCrards(loanIdCrards);
				}
			} else {
				 //  获取既有及历史债权列表(显示历史既有债权列表中使用) ，非首期历史既有债权只显示 本次出借人员
				
				List<CreditorTradeBorrowView> creditorTradeBorrowViews = creditorAidManager.getCreditorTradeBorrowViewNOFrist(setHisParam(mc));
				if (creditorTradeBorrowViews != null
						&& creditorTradeBorrowViews.size() > 0) {// 过滤本笔出借的既有历史出借中匹配过的借款人
					List<String> loanIdCrards = new ArrayList<String>();
					for (CreditorTradeBorrowView creditorTradeBorrowView : creditorTradeBorrowViews) {
						if (creditorTradeBorrowView.getLoanIdcard() != null)
							loanIdCrards.add(creditorTradeBorrowView.getLoanIdcard());
					}
					borrow.setLoanIdCrards(loanIdCrards);
				}
			}
		}
		 
		 borrow.setDictLoanFreeFlag(CreditState.KY.value);
		 // 已经匹配的数据不再显示在查看债权里边
		 if(!StringUtils.isEmpty(creditValueIds)){
				String [] creditValueIdArry = creditValueIds.split("-");
				List<String> tempcreditValues = new ArrayList<String>();
				for(String creditValue : creditValueIdArry ){
					tempcreditValues.add(creditValue);
				}
				borrow.setCreditValueIds(tempcreditValues);
			}
			if(mc.getApplyPay().equals(PayMent.ZJTG.value)){
				borrow.setTrusteeFlagOrderby("asc");
			}else{
				borrow.setTrusteeFlagOrderby("desc");
			}
		  
		 List<String> creditLocationList = matchingCreditorManager.getUseableLoanInfos(mc);
		 if(creditLocationList!=null && creditLocationList.size()>0)
			 borrow.setFilterLoanIdCrards(creditLocationList);// 设置债权配置列表
		//  债权单次匹配限额
		//borrow.setPpxy(this.getCreditorperUpper());
		return loanMonthRateMap;
	}
	
	/**
	 * 通过待推荐ID获取待推荐债权列表  月满盈
	 * @param matchingId
	 * @param borrowMonthable
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getborrowMonthAbleDetail"})
	public String getborrowMonthAbleDetail(String creditMonableIds,String matchingId,BorrowMonthableView borrowMonthable,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, BigDecimal> loanMonthRateMap = getBorrowMonthDetailInfo(
				creditMonableIds, matchingId, borrowMonthable, model, Constant.SHOW_HIS_FLG_YES, true);
		borrowMonthable.setBorrowDaysSurplusFrom(Constant.BORROWDAYSSURPLU_DEFALUT);
		//  分页查询
		Page<BorrowMonthable> page  =  new Page<BorrowMonthable>(request,response);	
		page = borrowMonthableManager.findBorrowMonthableDetail(page, borrowMonthable);
		page.setFuncName("subPage");
		model.addAttribute("creditMonableIds",creditMonableIds);
		model.addAttribute("matchingId",matchingId);
		model.addAttribute("lower",loanMonthRateMap.get("lower"));
		model.addAttribute("upper",loanMonthRateMap.get("upper"));
		model.addAttribute("page",page);
		return "/creditor/matching/borrowMonthAbleDetail";
	}

	/**
	 * 通过待推荐ID获取待推荐债权列表  月满盈 数据列表
	 * @param matchingId
	 * @param borrowMonthable
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getborrowMonthAbleDetailList"})
	public String getborrowMonthAbleDetailList(String creditMonableIds,String matchingId,String xsjylszq,BorrowMonthableView borrowMonthable,Model model,HttpServletRequest request,HttpServletResponse response){
		getBorrowMonthDetailInfo(creditMonableIds, matchingId, borrowMonthable, model, xsjylszq, false);
		//  分页查询
		Page<BorrowMonthable> page  =  new Page<BorrowMonthable>(request,response);	
		page = borrowMonthableManager.findBorrowMonthableDetail(page, borrowMonthable);
		model.addAttribute("creditMonableIds",creditMonableIds);
		model.addAttribute("matchingId",matchingId);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		
		return "/creditor/matching/borrowMonthAbleDetailList";
	} 
	
	/**
	 * 通过待推荐ID获取待推荐债权（月满盈信息）
	 * @param matchingId
	 * @param borrowMonthable
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	private Map<String, BigDecimal> getBorrowMonthDetailInfo(
			String creditMonableIds, String matchingId,BorrowMonthableView borrowMonthable, 
			Model model, String xsjylszq, boolean flg) {
		String[] types = {"tz_repay_day","jk_prof_type","tz_credit_src","tz_zjtr_mark","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model,types);
		//  获取出借信息
		MatchingCreditorView mc  =  creditorAidManager.getMatchingCreditorViewByMatchingId(matchingId);
		borrowMonthable.setDictLoanFreeFlag(CreditState.KY.value);// 状态可用
		Map<String,BigDecimal>  loanMonthRateMap = new HashMap<String,BigDecimal>();
		if (flg) {
			// 设置月利率
			loanMonthRateMap = getppRateToMap(
					mc.getProductCode(), mc.getStartApplyLendMoney(),
					DateUtils.formatDate(mc.getApplyLendDay(), "yyyy-MM-dd"),
					mc.getMatchingFirstdayFlag(), mc.getMatchingBillDay(),
					DateUtils.formatDate(mc.getMatchingInterestStart(),
							"yyyy-MM-dd"));
			borrowMonthable.setBorrowMonthRate(new BigDecimal(String.valueOf(BigDecimalUtil.round(loanMonthRateMap.get("lower").doubleValue(), 5))));
			borrowMonthable.setBorrowCreditValueFrom(new BigDecimal("1"));
		}
		// 初始借款日期（首次还款日）必须早于或等于本笔出借的到期日
		borrowMonthable.setBorrowBackmoneyFirday(DateUtils.formatDate(mc.getApplyExpireDay(), "yyyy-MM-dd"));
		// 设置匹配上限
		//borrowMonthable.setPpxy(this.getCreditorperUpper());
		if(!StringUtils.isEmpty(xsjylszq) && !Constant.SHOW_HIS_FLG_NO.equals(xsjylszq)){ // 显示历史既有
			 /** 过滤本笔出借的既有历史出借中匹配过的借款人**/
			 List<String> oldLoadIdCard = new ArrayList<String>();
				Map<String,String > hisListParam = new HashMap<String, String>();
				hisListParam.put("customerCode", mc.getCustomerCode());
				hisListParam.put("dictTradeCreditStatus", CreditTradestate.WKSBGB.value);
			List<CreditorTradeMonthAbleView> creditorTradeMonthAbleViews = creditorAidManager.getCreditorTradeMonthAbleViews(hisListParam);
			if(creditorTradeMonthAbleViews!=null &&creditorTradeMonthAbleViews.size()>0){//	过滤本笔出借的既有历史出借中匹配过的借款人
				for(CreditorTradeMonthAbleView creditorTradeMonthAbleView : creditorTradeMonthAbleViews){
					if(creditorTradeMonthAbleView.getLoanIdcard()!=null)
						oldLoadIdCard.add(creditorTradeMonthAbleView.getLoanIdcard());
				}
				borrowMonthable.setOldLoadIdCard(oldLoadIdCard);
			}
		}
		 // 已经匹配的不再查看列表中显示
		 if(!StringUtils.isEmpty(creditMonableIds)){
				borrowMonthable.setCreditMonableIds(creditMonableIds);
		}
		 if(mc.getApplyPay().equals(PayMent.ZJTG.value)){
				borrowMonthable.setTrusteeFlagOrderby("asc");
			}else{
				borrowMonthable.setTrusteeFlagOrderby("desc");
		}
		List<String> creditLocationList = matchingCreditorManager.getUseableLoanInfos(mc);
		if (creditLocationList != null && creditLocationList.size() > 0) {
			borrowMonthable.setFilterLoanIdCrards(creditLocationList);// 设置债权配置列表
		}
		return loanMonthRateMap;
	} 
	
	/**
	 *  通过债权ID集合 获取符合条件的可用债权集合
	 * 2015年12月28日
	 * By 柳慧
	 * @param creditValueIds
	 * @param response
	 */
	@RequestMapping(value  =  {"getBorrowByCreditValueIds"})
	@ResponseBody
	public String getBorrowByCreditValueIds(String creditValueIds,String ylmoney,String matchingBorrowQuota,HttpServletResponse response){
		if(!StringUtils.isEmpty(creditValueIds)){
			Map<String,BigDecimal> creditorperUpperMap = this.getCreditorperUpper();
			String creditValueIdArry []  =  creditValueIds.split(",");
			List<String> creditValueIdLst  =  new ArrayList<String>();
				for (int i  = 0; i<creditValueIdArry.length;i++ ){
					creditValueIdLst.add(creditValueIdArry[i]);
				}
			List<Borrow> borrows   =  borrowManager.getBorrowByCreditValueIds(creditValueIdLst);
			List<Borrow> borrowsBefor = new ArrayList<Borrow>();
			
			String[] types = {"tz_loan_distinguish"};
			Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
			map = FortuneDictUtil.getDicts(types);
			List<Dict> myDict = map.get("tz_loan_distinguish");
			String distinguishName = "";
			
			//对检索的数据进行排序（按照页面钩选的先后顺序）
			for (int j  = 0; j<creditValueIdArry.length;j++ ){
				for(Borrow borrow :borrows){
					//设置借款产品格式
					if(borrow.getLoanProduct()!=null){
						
					}else{
						borrow.setLoanProduct("");
					}
					if(borrow.getCreditValueId().equals(creditValueIdArry[j])){
						
						distinguishName = FortuneDictUtil.dictName(myDict,borrow.getDicLoanDistinguish(),"-");
						borrow.setDicLoanDistinguish(distinguishName);
						
						borrowsBefor.add(borrow);
						break;
					}
				}
			}
			List<Borrow> borrowstemp = new ArrayList<Borrow>();// 待推荐债权集合
			BigDecimal ylmoneyNum = new BigDecimal(ylmoney);//已匹配金额
			BigDecimal matchingBorrowQuotaNum =new BigDecimal(matchingBorrowQuota);//待匹配金额
			matchingBorrowQuotaNum = matchingBorrowQuotaNum.subtract(ylmoneyNum);
			for(Borrow borrow :borrowsBefor){
				BigDecimal loanCreditValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(borrow.getLoanCreditValue().doubleValue(), 2)));
				if(creditorperUpperMap!=null){
					String borrKey =borrow.getDictLoanType()+"-"+borrow.getLoanJob();
					if(creditorperUpperMap.containsKey(borrKey)){
						BigDecimal upperMoney= creditorperUpperMap.get(borrKey);
						borrow.setUpperMoney(upperMoney);
						if(loanCreditValue.compareTo(upperMoney)>=0){
							loanCreditValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(upperMoney.doubleValue(), 2)));
						}
					}
				}
				if(matchingBorrowQuotaNum.compareTo(loanCreditValue)<=0){ // 如果待匹配金额小于等于可用债权
					borrow.setTjmoney(matchingBorrowQuotaNum);
					borrowstemp.add(borrow);
					break;
				}else{
					borrow.setTjmoney(loanCreditValue);
					borrowstemp.add(borrow);
					matchingBorrowQuotaNum = matchingBorrowQuotaNum.subtract(loanCreditValue);
				}
			}
			return jsonMapper.toJson(borrowstemp);
		}
		return jsonMapper.toJson(null);
	}
	
	/**
	 *  通过债权ID集合 获取符合条件的可用债权集合   月满盈
	 * 2015年12月28日
	 * By 柳慧
	 * @param creditValueIds
	 * @param response
	 */
	@RequestMapping(value  =  {"getBorrowByCreditMonableId"})
	@ResponseBody
	public String getBorrowByCreditMonableId(String creditMonableIds,String ylmoney,String matchingBorrowQuota,HttpServletResponse response){
		if(!StringUtils.isEmpty(creditMonableIds)){
			Map<String,BigDecimal> creditorperUpperMap = this.getCreditorperUpper();
			String creditMonableIdArry []  =  creditMonableIds.split(",");
			List<String> creditMonableIdsLst  =  new ArrayList<String>();
				for (int i  = 0; i<creditMonableIdArry.length;i++ ){
					creditMonableIdsLst.add(creditMonableIdArry[i]);
				}
			List<BorrowMonthable> borrowMonthables   =  borrowMonthableManager.getBorrowMonthablesByCreditMonableIds(creditMonableIdsLst);
			List<BorrowMonthable> borrowMonthablesBefor = new ArrayList<BorrowMonthable>();
			//对检索的数据进行排序（按照页面钩选的先后顺序）
			for (int j  = 0; j<creditMonableIdArry.length;j++ ){
				for(BorrowMonthable borrowMonthable :borrowMonthables){
					//设置债权区分格式
					if(borrowMonthable.getDicLoanDistinguish()!=null){
						try {
							borrowMonthable.setDicLoanDistinguish(LoanDistinguish.parseByCode(borrowMonthable.getDicLoanDistinguish()).getName());
						} catch (Exception e) {
							borrowMonthable.setDicLoanDistinguish("-");
						}
					}else{
						borrowMonthable.setDicLoanDistinguish("-");
					}
					if(borrowMonthable.getCreditMonableId().equals(creditMonableIdArry[j])){
						borrowMonthablesBefor.add(borrowMonthable);
						break;
					}
				}
			}
			List<BorrowMonthable> borrowMonthabletemp = new ArrayList<BorrowMonthable>();
			BigDecimal ylmoneyNum = new BigDecimal(ylmoney);//已匹配金额
			BigDecimal matchingBorrowQuotaNum = new BigDecimal(matchingBorrowQuota);//待匹配金额
			matchingBorrowQuotaNum = matchingBorrowQuotaNum.subtract(ylmoneyNum);
			for(BorrowMonthable borrowMonthable: borrowMonthablesBefor ){
				BigDecimal loanAvailabeValue = new BigDecimal(String.valueOf(BigDecimalUtil.round( borrowMonthable.getLoanAvailabeValue().doubleValue(), 2)));// 债权人可用债权
				if(creditorperUpperMap!=null){
					String borrKey =borrowMonthable.getDictLoanType()+"-"+borrowMonthable.getLoanJob();
					if(creditorperUpperMap.containsKey(borrKey)){
						BigDecimal upperMoney= creditorperUpperMap.get(borrKey);
						borrowMonthable.setUpperMoney(upperMoney);
						if(loanAvailabeValue.compareTo(upperMoney)>=0){
							loanAvailabeValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(upperMoney.doubleValue(), 2)));
						}
					}
				}
				if(matchingBorrowQuotaNum.compareTo(loanAvailabeValue)<=0){ // 如果待匹配金额小于等于可用债权
					borrowMonthable.setTjMomey(matchingBorrowQuotaNum);
					borrowMonthabletemp.add(borrowMonthable);
					break;
				}else{
					borrowMonthable.setTjMomey(loanAvailabeValue);
					borrowMonthabletemp.add(borrowMonthable);
					
					matchingBorrowQuotaNum = matchingBorrowQuotaNum.subtract(loanAvailabeValue);
				}
			}
			return jsonMapper.toJson(borrowMonthabletemp);
		}
		return jsonMapper.toJson(null);
		
	} 
	
	/**
	 * 通过产品编号 获得产品匹配规则
	 * 2016年1月18日
	 * By 柳慧
	 * @param matchingFirstdayFlag
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getCreditorperRuleConfig"})
	@ResponseBody
	public String getCreditorperRuleConfig(String matchingFirstdayFlag,HttpServletResponse response){
		Map<String,Object> ruleConfigMap = new HashMap<String, Object>();
		ruleConfigMap.put("billType", matchingFirstdayFlag);
		ruleConfigMap.put("useFlag", UseFlag.QY.value);
		List<CreditorperRuleConfig> ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配匹配规则
		if(ruleConfigLs!=null &&ruleConfigLs.size()>0){
			ruleConfigMap.put("defaultFlag", YoN.SHI.value);
			List<CreditorperRuleConfig> ruleConfigLl2 = matchingRuleDao.find(ruleConfigMap);// 查询匹配默认匹配规则
			if(ruleConfigLl2!=null &&ruleConfigLl2.size()>0){
				return jsonMapper.toJson(2);
			}else{
				return jsonMapper.toJson(1);
			}
		}
		return jsonMapper.toJson(0);
		
	}
	
	/**
	 * 非月满盈-债权匹配提交
	 * 2015年12月22日
	 * By 柳慧
	 * @param from 请求来源 1 来自分派待推荐平台列表
	 * @param ymyType 产品月满盈标识   1 表示非月满盈  首期   2表示非月满非首期
	 * @param matchingId 待推荐债权ID
	 * @param lendCode 出借编号
	 * @param creditValueIds 可用债权ID集合
	 * @param tjmoneys 推荐额度集合
	 * @param distributedFlag 分派债权标识
	 * @param borrowVerTimes 债权更新时间
	 * @return
	 */
	@RequestMapping(value  =  {"matchingSubmit"})
	@ResponseBody
	public String  matchingSubmit(String from,String ymyType,String matchingId,String lendCode,String creditValueIds,String tjmoneys,String distributedFlag,String verTime,String borrowVerTimes, HttpServletRequest request,HttpServletResponse response){
		if(!StringUtils.isEmpty(creditValueIds)){
			creditValueIds  =  creditValueIds.substring(0,creditValueIds.length()-1); //  可用债权ID集合
			
			tjmoneys  =  tjmoneys.substring(0,tjmoneys.length()-1); //  推荐额度集合
			CERequestContext ceContext = CEContextHelper.getCEContext(request);
			try {
				if(BillState.SQ.value.equals(ymyType)){
					matchingCreditorManager.fymySqMatchingSubmit(matchingId, lendCode, creditValueIds, tjmoneys, ceContext, verTime, borrowVerTimes);
				}else{
					matchingCreditorManager.fymyFsqMatchingSubmit(matchingId, lendCode, creditValueIds, tjmoneys, ceContext, verTime, borrowVerTimes);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("待推荐编号为："+matchingId+"提交失败，具体原因为"+e.getMessage());
				return  jsonMapper.toJson(e.getMessage());
			}
			return jsonMapper.toJson(0);
		}
		return jsonMapper.toJson(0);
		/*if (!StringUtils.isEmpty(distributedFlag) && distributedFlag.equals("1")) {
			// 更新统计
			creditorStatisticManager.updateStatistic(
					UserUtils.getUser().getId(), 
					1,
					Constant.CREDIT_DONE, 
					ymyType,
					BillState.SQ.value.equals(ymyType) ? lendCode : null
			);
			return "redirect:" + this.adminPath
					+ "/matchingcreditor/distributeRecommendationList";
		}*/
		//return "redirect:"+this.adminPath+"/matchingcreditor/list";

	}
	
	/**
	 * 待推荐债权撤销
	 * 2016年2月18日
	 * By 柳慧
	 * @param matchingId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"matchingCancel"})
	@ResponseBody
	public String  matchingCancel(String matchingId,String note,String lendCode,HttpServletRequest request,HttpServletResponse response){
		checkManager.addCheck(lendCode, note, TracesType.ZQPP_CXCJ.getName() );
		try{
			matchingCreditorManager.matchingCancel(matchingId);
		}catch(Exception e){
			return jsonMapper.toJson(e.getMessage());
		}
		
		String su = "success";
		return jsonMapper.toJson(su);
	}
	/**
	 * 月满盈产品债权匹配提交 
	 * @param from 请求来源 1 来自分派待推荐平台列表
	 * @param matchingId 待推荐债权ID
	 * @param lendCode  出借编号
	 * @param creditMonableIds 可用债权ID集合
	 * @param tjmoneys 推荐额度集合
	 * @param tjdays 实际出借天数
	 * @param distributedFlag 分派债权标识
	 * @return
	 */
	@RequestMapping(value  =  {"matchingBorrowMonthSubmit"})
	@ResponseBody
	public String  matchingBorrowMonthSubmit(String from,String matchingId,String lendCode,String creditMonableIds,String tjmoneys,String tjdays,String distributedFlag,String verTime,String borrowVerTimes,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtils.isEmpty(creditMonableIds)&& !creditMonableIds.equals("")){
			creditMonableIds  =  creditMonableIds.substring(0,creditMonableIds.length()-1); //  可用债权ID集合
			tjmoneys  =  tjmoneys.substring(0,tjmoneys.length()-1); //  推荐额度集合
			tjdays = tjdays.substring(0,tjdays.length()-1); // 实际出借天数
			CERequestContext ceContext = CEContextHelper.getCEContext(request);
			try {
				matchingCreditorManager.matchingBorrowMonthSubmit(matchingId,lendCode,creditMonableIds,tjmoneys,tjdays,ceContext,verTime,borrowVerTimes);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("待推荐编号为："+matchingId+"提交失败，具体原因为"+e.getMessage());
				return  jsonMapper.toJson(e.getMessage());
			}
			checkManager.addCheck(lendCode, Constant.INSERT_ADD +matchingId, TracesType.ZQ_PP.getName());
			
		}
		return jsonMapper.toJson(0);
	/*	MatchingCreditorView matchingCreditorView = matchingCreditorManager
				.getMatchingCreditorViewByMatchingId(matchingId);
		if (matchingCreditorView != null) {
			if (!StringUtils.isEmpty(distributedFlag) && distributedFlag.equals("1")) {
				// 更新统计
				creditorStatisticManager.updateStatistic(
						UserUtils.getUser().getId(), 
						1,
						Constant.CREDIT_DONE, 
						matchingCreditorView.getMatchingFirstdayFlag(),
						BillState.SQ.value.equals(matchingCreditorView.getMatchingFirstdayFlag()) ? 
								lendCode : null
				);
				return "redirect:" + this.adminPath
						+ "/matchingcreditor/distributeRecommendationList";
			}
		}
		
		return "redirect:"+this.adminPath+"/matchingcreditor/list";*/

	}
	
	/** 
	 * 通过产品编号 获取匹配利率
	 * 2016年2月29日
	 * By 柳慧
	 * @param productCode
	 * @param applyLendMoney
	 * @param applyLendDay
	 * @param billType
	 * @param matchingBillDay 账单日
	 * @param matchingInterestStart 本期出借日期
	 * @return
	 */
	private BigDecimal getppRate(String productCode,BigDecimal applyLendMoney,String applyLendDay,String billType,int matchingBillDay,String matchingInterestStart){
		Map<String,BigDecimal> retMap = getppRateToMap(productCode, applyLendMoney, applyLendDay, billType, matchingBillDay, matchingInterestStart);
		if(retMap != null){
			return retMap.get("lower");
		}else{
			return retMap.put("lower",BigDecimal.ZERO) ;
		}
	}
	
	/**
	 * 获取利率区间
	 * 2016年4月18日
	 * By 柳慧
	 * @param productCode
	 * @param applyLendMoney
	 * @param applyLendDay
	 * @param billType
	 * @param matchingBillDay
	 * @param matchingInterestStart
	 * @return
	 */
	private Map<String,BigDecimal> getppRateToMap(String productCode,BigDecimal applyLendMoney,String applyLendDay,String billType,int matchingBillDay,String matchingInterestStart){
		Map<String,BigDecimal> retMap =matchingCreditorManager.getppRateToMap(productCode, applyLendMoney, applyLendDay, billType, matchingBillDay, matchingInterestStart);
		if (retMap != null && retMap.get("lower") == null) {
			retMap.put("lower", BigDecimal.ZERO);
		}
		return retMap;
	}
	
	/**
	 * 获取非月满盈待推荐信息
	 * 2016年1月18日
	 * By 柳慧
	 * @param mc
	 * @param mbEx
	 * @return
	 */
	private List<Borrow> getppBorrow(MatchingCreditorView mc,MatchingBorrowEx mbEx){
		List<Borrow> borrows = new ArrayList<Borrow>();
		BigDecimal MatchingBorrowQuota = new BigDecimal(String.valueOf(BigDecimalUtil.round(mc.getMatchingBorrowQuota().doubleValue(), 5)));
		if(MatchingBorrowQuota.compareTo( new BigDecimal("5000"))<=0){
			for(int i=5;i>1;i--){
				mbEx.setPpxeMoney(MatchingBorrowQuota.multiply(new BigDecimal(String.valueOf(i))));
				List<Borrow> borrowLst  =  creditorAidManager.getsqppByMbEx(mbEx);
				if(borrowLst!=null && borrowLst.size()>0){
					Borrow borrow = borrowLst.get(0);
					borrow.setTjmoney(MatchingBorrowQuota);
					borrows.add(borrow);
					break;
				}
			}
			return borrows;
		}
		Map<String,Object> ruleConfigMap = new HashMap<String, Object>();
		ruleConfigMap.put("billType", mc.getMatchingFirstdayFlag());
		ruleConfigMap.put("useFlag", UseFlag.QY.value);
		ruleConfigMap.put("matchingBorrowQuota", MatchingBorrowQuota);
		List<CreditorperRuleConfig> ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配规则
		if (ListUtils.isEmptyList(ruleConfigLs)) {
			// 如果没查到本期推荐金额范围相关配置，则去掉金额限制，重新查询
			ruleConfigMap.remove("matchingBorrowQuota");
			ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配规则
		}
	
		if(ruleConfigLs != null && ruleConfigLs.size() >= 1 ){
			List<Borrow> tempborrows = new ArrayList<Borrow>();// 临时匹配集
			CreditorperRuleConfig ruleConfig = ruleConfigLs.get(0);
			 List<CreditorperRuleProporti> ruleproportis = ruleConfig.getProporti();  // 获取二级推荐金额比例信息
			 if(ruleproportis != null && ruleproportis.size() > 0){
				 List<String> oldCreditValueIds = new ArrayList<String>();// 已匹配的债权的
				 Map<String,String>  oldCreditValueIdCard= new HashMap<String,String>();//  已匹配的债权的省份证
				 BigDecimal tempTotalMoney  =  BigDecimal.ZERO; // 临时匹配总额
				for(CreditorperRuleProporti  creditorperRuleProporti: ruleproportis){
					// 获取二级推荐总金额
					BigDecimal proporti = creditorperRuleProporti.getProportion().divide(new BigDecimal("100"));
					BigDecimal FrisrtTotalMoney = MatchingBorrowQuota.multiply(proporti);
					FrisrtTotalMoney = new BigDecimal(String.valueOf(BigDecimalUtil.round(FrisrtTotalMoney.doubleValue(), 5)));
					List<CreditorperRuleLower> lowers = creditorperRuleProporti.getLower();// 获取三级推荐金额比例信息
					if(lowers != null && lowers.size() >0){
						for(CreditorperRuleLower lower : lowers ){
							BigDecimal proportion = creditorperRuleProporti.getProportion().divide(new BigDecimal("100"));
							BigDecimal lowerNumber  = lower.getLower().divide(new BigDecimal("100"));
							//  推荐金额≤3000时没有直接采用推荐金额*5为下限
							/*if(MatchingBorrowQuota.compareTo(new BigDecimal("3000"))<=0){
								 mbEx.setPpxeMoney(MatchingBorrowQuota.multiply(proportion. multiply(lowerNumber)).multiply(new BigDecimal("5")));
							}else{
								mbEx.setPpxeMoney(MatchingBorrowQuota.multiply(proportion. multiply(lowerNumber)));
							}*/
							mbEx.setPpxeMoney(MatchingBorrowQuota.multiply(proportion. multiply(lowerNumber)));
							mbEx.setOldCreditValueId(oldCreditValueIds);
							List<Borrow> borrowsFrisrt   =  creditorAidManager.getsqppByMbEx(mbEx);
							if(borrowsFrisrt != null && borrowsFrisrt.size()>0){
								for( Borrow borrow :borrowsFrisrt){
									if(oldCreditValueIdCard.containsKey(borrow.getLoanIdcard())){
										continue;
									}
									BigDecimal LoanCreditValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(borrow.getLoanCreditValue().doubleValue(), 5))); 
									if(LoanCreditValue.compareTo(FrisrtTotalMoney) != -1){ //  获取二级推荐金额 小于 借款人可用债权
										borrow.setTjmoney(FrisrtTotalMoney);
										tempborrows.add(borrow);
										tempTotalMoney = tempTotalMoney.add(FrisrtTotalMoney);
										FrisrtTotalMoney = BigDecimal.ZERO;
										oldCreditValueIds.add(borrow.getCreditValueId()); 
									}else { // 获取二级推荐金额 大于 借款人可用债权
										borrow.setTjmoney(LoanCreditValue);
										tempborrows.add(borrow);
										BigDecimal b1 = new BigDecimal(String.valueOf(FrisrtTotalMoney.doubleValue())); 
										tempTotalMoney = tempTotalMoney.add(LoanCreditValue);
										FrisrtTotalMoney = b1.subtract(LoanCreditValue);
										oldCreditValueIds.add(borrow.getCreditValueId()); 
									}
									oldCreditValueIdCard.put(borrow.getLoanIdcard(), borrow.getLoanIdcard());
									// 如果匹配满 不再继续
									if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO) == 0){
										break;
									}
								}

							}
							// 如果匹配满 不再继续
							if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO) == 0){
								break;
							} 
						}
						
					}
					// 如果没有匹配满 撤销
					if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO)!=0){
						break;
					}
				}
				if(tempTotalMoney.compareTo(MatchingBorrowQuota) ==0){
					borrows = tempborrows;
				}
			 }
		}
		return borrows;
	}
	
	/**
	 * 月满盈自动匹配  
	 * 2016年4月5日
	 * By 柳慧
	 * @param mc
	 * @param borrowMonthable
	 * @return
	 */
	private List<BorrowMonthable> getppBorrowMonth(MatchingCreditorView mc,BorrowMonthable borrowMonthable){
		List<BorrowMonthable> borrowMonthables = new ArrayList<BorrowMonthable>(); 
		
		List<String> dictLoanTypes = new ArrayList<String>(); // 债权类型
		/** 车借不能跟别的类型混匹,车借不足的话,就重新匹配信借和房借的债权,信借优先于房借,按照首期匹配的匹配比例规则进行匹配 **/
		 dictLoanTypes.add(CreditSrc.CJ.value);
		 borrowMonthable.setDictLoanTypes(dictLoanTypes);
		 borrowMonthables = getMonthBorrowMonthByLoanType(mc,borrowMonthable);
		 if(borrowMonthables.size()<1){
			dictLoanTypes = new ArrayList<String>(); // 债权类型
			dictLoanTypes.add(CreditSrc.XJ.value);
			dictLoanTypes.add(CreditSrc.FJ.value);
			borrowMonthable.setDictLoanTypes(dictLoanTypes);
			borrowMonthables = getMonthBorrowMonthByLoanType(mc,borrowMonthable);
		 }
		return borrowMonthables;
	}
	
	/**
	 * 根据 借款类型不同 获取自动匹配信息（月满盈）
	 * 2016年4月5日
	 * By 柳慧
	 * @param mc
	 * @param borrowMonthable
	 * @return
	 */
	private List<BorrowMonthable> getMonthBorrowMonthByLoanType(MatchingCreditorView mc,BorrowMonthable borrowMonthable){
		List<BorrowMonthable> borrowMonthables = new ArrayList<BorrowMonthable>(); 
		Map<String,Object> ruleConfigMap = new HashMap<String, Object>();
		// 待匹配金额
		BigDecimal MatchingBorrowQuota = new BigDecimal(String.valueOf(BigDecimalUtil.round(mc.getMatchingBorrowQuota().doubleValue(), 5)));
		ruleConfigMap.put("defaultFlag", YoN.SHI.value);
		ruleConfigMap.put("useFlag", UseFlag.QY.value);
		ruleConfigMap.put("billType", BillState.SQ.value);
		ruleConfigMap.put("matchingBorrowQuota", MatchingBorrowQuota);
		List<CreditorperRuleConfig> ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配默认匹配规则
		if (ListUtils.isEmptyList(ruleConfigLs)) {
			// 如果没查到本期推荐金额范围相关配置，则去掉金额限制，重新查询
			ruleConfigMap.remove("matchingBorrowQuota");
			ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配规则
		}
		
		if(ruleConfigLs != null && ruleConfigLs.size() >= 1 ){
			List<BorrowMonthable> tempBorrowMonthable = new ArrayList<BorrowMonthable>();// 临时匹配集
			CreditorperRuleConfig ruleConfig = ruleConfigLs.get(0);
			List<CreditorperRuleProporti> ruleproportis = ruleConfig.getProporti();  // 获取二级推荐金额比例信息
			 if(ruleproportis != null && ruleproportis.size() > 0){
				 List<String> creditMonableIds = new ArrayList<String>();// 已匹配的月满盈债权
				 Map<String,String> creditMonableCoked = new HashMap<String, String>();// 已匹配的月满盈债权
				 BigDecimal tempTotalMoney  =  BigDecimal.ZERO; // 临时匹配总额
				for(CreditorperRuleProporti  creditorperRuleProporti: ruleproportis){
					// 获取二级推荐总金额
					BigDecimal proporti = creditorperRuleProporti.getProportion().divide(new BigDecimal("100"));
					BigDecimal FrisrtTotalMoney = MatchingBorrowQuota.multiply(proporti);
					FrisrtTotalMoney = new BigDecimal(String.valueOf(BigDecimalUtil.round(FrisrtTotalMoney.doubleValue(), 5)));
					List<CreditorperRuleLower> lowers = creditorperRuleProporti.getLower();// 获取三级推荐金额比例信息
					if(lowers != null && lowers.size() >0){
						for(CreditorperRuleLower lower : lowers ){
							 BigDecimal proportion = creditorperRuleProporti.getProportion().divide(new BigDecimal("100"));
							 BigDecimal lowerNumber  = lower.getLower().divide(new BigDecimal("100"));
							 borrowMonthable.setPpxeMoney(MatchingBorrowQuota.multiply(proportion. multiply(lowerNumber)));
							 borrowMonthable.setCreditMonableIds(creditMonableIds);
							 List<BorrowMonthable> cjborrowMonthables =  borrowMonthableManager.getPpBorrowMonthable(borrowMonthable);// 查出车借可用债权
							 if(cjborrowMonthables != null && cjborrowMonthables.size()>0){
								 for( BorrowMonthable borrowMonthable1 :cjborrowMonthables){
									 if(creditMonableCoked.containsKey(borrowMonthable1.getLoanIdcard())){
										 continue;
									 }
									 BigDecimal loanAvailabeValue = new BigDecimal(String.valueOf(BigDecimalUtil.round(borrowMonthable1.getLoanAvailabeValue().doubleValue(), 5))); 
									 if(loanAvailabeValue.compareTo(FrisrtTotalMoney) != -1){ //  获取二级推荐金额 小于 借款人可用债权
										 borrowMonthable1.setTjMomey(FrisrtTotalMoney);
										 tempBorrowMonthable.add(borrowMonthable1);
										 tempTotalMoney = tempTotalMoney.add(FrisrtTotalMoney);
										 FrisrtTotalMoney =   BigDecimal.ZERO;
										 creditMonableIds.add(borrowMonthable1.getCreditMonableId()); 
									}else { // 获取二级推荐金额 大于 借款人可用债权
									 	borrowMonthable1.setTjMomey(loanAvailabeValue);
										 tempBorrowMonthable.add(borrowMonthable1);
										 BigDecimal b1 = new BigDecimal(String.valueOf(FrisrtTotalMoney.doubleValue())); 
										 tempTotalMoney = tempTotalMoney.add(loanAvailabeValue);
										 FrisrtTotalMoney = b1.subtract(loanAvailabeValue);
										 creditMonableIds.add(borrowMonthable1.getCreditMonableId()); 
									 }
									 creditMonableCoked.put(borrowMonthable1.getLoanIdcard(),borrowMonthable1.getLoanIdcard());
										// 如果匹配满 不再继续
									if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO) == 0){
										break;
									}
								 }
								
							 }
							// 如果匹配满 不再继续
							if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO) == 0){
								break;
							}
						}
					}
					// 如果没有匹配满 撤销
					if(FrisrtTotalMoney.compareTo( BigDecimal.ZERO)!=0){
						break;
					}
				}
				if(tempTotalMoney.compareTo(MatchingBorrowQuota) ==0){
					borrowMonthables = tempBorrowMonthable;
				}
			 }
			 
		}
		return borrowMonthables;
	}
	/**
	 *  获取匹配记录
	 * 2016年1月20日
	 * By 柳慧
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getppll"})
	public String getppll(Model model,HttpServletRequest request,HttpServletResponse response){
		Calendar t  = Calendar.getInstance();
		String formatDate = DateFormatUtils.format(t.getTime(), "yyyy-MM-dd");
		Map<String,String> param = new HashMap<String,String>();
		param.put("start",formatDate.replaceAll(" ", ""));
		Calendar t1 = Calendar.getInstance();
		 t1.add(Calendar.DAY_OF_MONTH, 1);
		 java.util.Date endt = t1.getTime();
		String end = DateFormatUtils.format(endt.getTime(), "yyyy-MM-dd");
		param.put("end", end.replaceAll(" ", ""));
		param.put("sqFlag",BillState.SQ.value);
		param.put("fsqFlag",BillState.FSQ.value);
		param.put("ytjFlag",MatchingStatus.YTJ.value);
		param.put("dtjFlag",MatchingStatus.DTJ.value);
		Map<String,Long> retMap = creditorAidManager.getppll(param);
		model.addAttribute("day", formatDate);
		model.addAttribute("retMap", retMap);
		return "/creditor/matching/ppllDetailList";
	} 
	
	/**
	 * 查询出匹配上限
	 * 2016年1月28日
	 * By 柳慧
	 * @return
	 */
	private Map<String ,BigDecimal> getCreditorperUpper(){
		 Map<String ,BigDecimal> retMap = null ;
		 Map<String ,Object> param = new HashMap<String, Object>();
		// param.put("freeFlag", Constant.CREDITORPER_UPPER_KY);
		 param.put("useFlag",UseFlag.QY.value );
		 List<CreditorperUpper> creditorperUppers = creditorperUpperManager.findList(param);
		 if(creditorperUppers!=null && creditorperUppers.size()>0){
			 retMap = new HashMap<String, BigDecimal>();
			 for(CreditorperUpper cu :creditorperUppers){
				 retMap.put(cu.getDictLoanType()+"-"+cu.getProofType(), cu.getUpperMoney());
			 }
		 }
		 return retMap;
	}
	
	/**
	 * 判断是否已经设置错期匹配信息
	 * 2016年3月10日
	 * By 柳慧
	 * @param matchingId
	 * @param response
	 * @return
	 */
	@RequestMapping(value  =  {"getCreditorConfig"})
	@ResponseBody
	public String getCreditorConfig(String matchingId,HttpServletResponse response){
		MatchingCreditorEx mex = new MatchingCreditorEx();
		mex.setMatchingId(matchingId);
		MatchingCreditorEx mc  =  creditorAidManager.get(mex);
		String productCode  =  mc.getProductCode();
		//如果是"月满盈"，则不进行错期匹配
		if(!StringUtils.isEmpty(productCode) && ProductCode.YMY.value.equals(productCode)){
			return jsonMapper.toJson(1);
		}else{
			List<CreditorConfig> creditorConfigLs = matchingCreditorManager.getfindByMatchingBillDay(mc.getMatchingBillDay(),mc.getMatchingFirstdayFlag());
			if(creditorConfigLs!=null && creditorConfigLs.size()>0){
				return jsonMapper.toJson(1);
			}else{
				return jsonMapper.toJson(0);
			}
		}
	}
	
	/**
	 * 通过债权ID 判断债权是否属于同类债权 ( 车借不能跟别的类型混匹,车借不足的话,就重新匹配信借和房借的债权,信借优先于房借)
	 * 2016年3月30日
	 * By 柳慧
	 * @param creditMonableIds
	 * @return 如果是同一类  返回0    反之，返回 1
	 */
	@RequestMapping(value  =  {"getDictLoanTypeByCreditMonableIds"})
	@ResponseBody
	public String getDictLoanTypeByCreditMonableIds(String creditMonableIds){
		List<String> paramLst = new ArrayList<String>();
		String []   creditMonableIdArray = creditMonableIds.split("-");
		for(int i = 0; i < creditMonableIdArray.length; i++ ){
			paramLst.add(creditMonableIdArray[i]);
		}
		if(paramLst.size()==1){
			return jsonMapper.toJson(0);
		}
		List<String> ret = creditorAidManager.getDictLoanTypeByCreditMonableIds(creditMonableIdArray);
		if(ret!= null && ret.size()==1){
			return jsonMapper.toJson(0);
		}else{
			// 车借不能跟别的类型混匹,车借不足的话,就重新匹配信借和房借的债权,信借优先于房借。
			for(String s :ret){
				if(CreditSrc.CJ.value.equals(s)){
					return jsonMapper.toJson(1);
				}
			}
			return jsonMapper.toJson(0);
		}
	}
	
	/**
	 * 获取既有历史
	 * 2016年4月13日
	 * By 柳慧
	 * @param mc
	 * @param creditorTradeBorrowViews 可用债权列表
	 * @param creditorTradeMonthAbleViews 月满盈可用债权列表
	 * @return 身份证号
	 */
	private List<String > getHisListLoanCradId(MatchingCreditorView mc,List<CreditorTradeBorrowView>  creditorTradeBorrowViews,List<CreditorTradeMonthAbleView> creditorTradeMonthAbleViews){
		List<String> loanIdCrards = new ArrayList<String>();
		if(creditorTradeBorrowViews!= null && creditorTradeBorrowViews.size()>0){//	过滤本笔出借的既有历史出借中匹配过的借款人
			for(CreditorTradeBorrowView creditorTradeBorrowView : creditorTradeBorrowViews){
				if(creditorTradeBorrowView.getLoanIdcard()!=null)
					loanIdCrards.add(creditorTradeBorrowView.getLoanIdcard());
			}
		}
		if(creditorTradeMonthAbleViews!= null && creditorTradeMonthAbleViews.size()>0){//	过滤本笔出借的既有历史出借中匹配过的借款人
			for(CreditorTradeMonthAbleView creditorTradeMonthAbleView : creditorTradeMonthAbleViews){
				if(creditorTradeMonthAbleView.getLoanIdcard()!=null)
					loanIdCrards.add(creditorTradeMonthAbleView.getLoanIdcard());
			}
		}
		return loanIdCrards;
	}	
	/**
	 * 月满盈请求金额
	 * 2016年4月19日
	 * By 柳慧
	 * @param creditMonableId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ajaxMoney",method=RequestMethod.GET)
	@ResponseBody 
	public String ajaxMoney(String creditMonableId,HttpServletResponse response){
		if(!StringUtils.isEmpty(creditMonableId)){
			String[] creditValueIdArr = creditMonableId.split(",");
			List<String> creditValueIds = new ArrayList<String>();
			for(int i=0; i<creditValueIdArr.length;i++){
				creditValueIds.add(creditValueIdArr[i]);
			}
			BorrowMonthableView borrowMonthableView  = new BorrowMonthableView ();
			borrowMonthableView.setCreditMonableIdList(creditValueIds);
			BigDecimal money = borrowMonthableManager.findAllMoney2(borrowMonthableView);
			return this.renderString(response, money);
		}else{
			return this.renderString(response, 0);
		}
		
		
	}
}
