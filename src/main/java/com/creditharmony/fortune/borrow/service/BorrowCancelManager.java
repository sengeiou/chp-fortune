package com.creditharmony.fortune.borrow.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.bcel.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.fortune.type.CreditRelease;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.CreditState;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.NextcreditorFrozen;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.borrow.dao.BorrowCancelDao;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.dao.CreditorTradesDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.borrow.entity.ex.BorrowCanceEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowReplaceEx;
import com.creditharmony.fortune.borrow.entity.ex.LoanApplyEx;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowCancelView;
import com.creditharmony.fortune.borrow.view.MatchingBorrowLookView;
import com.creditharmony.fortune.borrow.view.ReplaceBorrowView;
import com.creditharmony.fortune.borrow.view.ReplaceView;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;
import com.creditharmony.fortune.creditor.matching.entity.CreditorperUpper;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorConfigManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorStatisticsManager;
import com.creditharmony.fortune.creditor.matching.service.CreditorperUpperManager;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.creditor.matching.utils.ExportCXLBExcelUtils;
import com.creditharmony.fortune.creditor.matching.utils.MatchingUtils;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeBorrowView;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.utils.BackMoneyUtil;

/**
 * 债权撤销Manager
 * @Class Name BorrowCancelManager
 * @author 周俊
 * @Create In 2015年12月31日
 */
@Service
public class BorrowCancelManager {

	@Autowired
	private BorrowCancelDao borrowCancelDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private LoanphaseDao loanphaseDao;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private CreditorTradesDao creditorTradeDao;
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	@Autowired
	private BorrowManager borrowManager;
	@Autowired
	private BorrowMonthableDao borrowMonthableDao;
	@Autowired
	private BorrowMonthableManager borrowMonthableManager;
	@Autowired
	private CreditorHisManager creditorHisManager;
	@Autowired
	private CreditorAidManager creditorAidManager;
	@Autowired
	private CreditorConfigManager creditorConfigManager;
	@Autowired
	private ProductMatchingRateManager productMatchingRateManager;
	@Autowired
	private CreditorperUpperManager creditorperUpperManager;
	@Autowired
	private MatchingCreditorManager matchingCreditorManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private CreditorStatisticsManager creditorStatisticsManager;
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 查询撤销债权列表
	 * 2015年12月31日
	 * By 周俊
	 * @param borrowCancelViewParam
	 * @param page
	 * @return
	 */
	public Page<BorrowCancelView> findBorrowCancel(BorrowCancelView borrowCancelView,Page<BorrowCancelView> page){
		 Map<String, Object> map = objectToMap(borrowCancelView);
		/*// 查询出最大期数带推荐
		Map<String, Object> maxMatchingCreditorMap = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(borrowCancelViewParam.getApplyCode())) {
			maxMatchingCreditorMap.put("lendCode", borrowCancelViewParam.getApplyCode());
		}
		maxMatchingCreditorMap.put("matchingStatus",MatchingStatus.CX.value);
		List<MatchingCreditorEx> list = borrowCancelDao.findMaxMatchingCreditor(maxMatchingCreditorMap);
		List<String> matchingIds = new ArrayList<String>();
		if (ArrayHelper.isNotEmpty(list)) {
			for (MatchingCreditorEx matchingCreditorEx : list) {
				matchingIds.add(matchingCreditorEx.getMatchingId());
			}
		}
		map.put("matchingIds", matchingIds);
		map.put("flag",true);*/
		// 查询总记录数
		//long totalCount = borrowCancelDao.findCount(map);
		
		//Map<String, Object> map1 = objectToMap(borrowCancelViewParam);
		BigDecimal oneCurrentCreditLinesMoney = new BigDecimal(0);
		List<BorrowCancelView> listNew = null;
		//判断页面选择的替换状态
		if(map.containsKey("replaceStatus")){
			if(map.get("replaceStatus").equals("1")){
				listNew = borrowCancelDao.findSumCCLMoneyForNo(map);//优化后的sql
			}else{
				listNew = borrowCancelDao.findSumCCLMoneyForYes(map);//优化后的sql
			}	
		}else{
			listNew = borrowCancelDao.findSumCCLMoneyForAll(map);//优化后的sql
		}
		
		if (ArrayHelper.isNotEmpty(listNew)) {
			for(int i=0;i<listNew.size();i++){ 
				String lendCode_matchingId = listNew.get(i).getLendCode()+":"+listNew.get(i).getMatchingId(); 
				BorrowCanceEx borrowCanceEx = getBorrowCanceEx(lendCode_matchingId); 
				oneCurrentCreditLinesMoney = oneCurrentCreditLinesMoney.add(borrowCanceEx.getCurrentCreditLines()); 
			}
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		int offset = pageBounds.getOffset();
		int limit = pageBounds.getLimit();
		int pageNo = pageBounds.getPage();
		map.put("offset", offset);
		map.put("limit", limit);
		borrowCancelDao.updateTopageReplaceStatus();
		borrowCancelDao.updateReplaceDay(map);
		List<BorrowCancelView> list = null;
		//判断页面选择的替换状态
		if(map.containsKey("replaceStatus")){
			if(map.get("replaceStatus").equals("1")){
				list = borrowCancelDao.findBorrowCancelForNo(map);//优化后的sql
			}else{
				list = borrowCancelDao.findBorrowCancelForYes(map);//优化后的sql
			}	
		}else{
			list = borrowCancelDao.findBorrowCancelForAll(map);//优化后的sql
		}
		
		//优化前的sql
		//list = borrowCancelDao.findBorrowCancel(map);
		

		if (ArrayHelper.isNotEmpty(list)) {
			for(int i=0;i<list.size();i++){ 
				String lendCode_matchingId = list.get(i).getLendCode()+":"+list.get(i).getMatchingId(); 
				BorrowCanceEx borrowCanceEx = getBorrowCanceEx(lendCode_matchingId); 
				list.get(i).setCurrentCreditLinesMoney(borrowCanceEx.getCurrentCreditLines()); 
				list.get(i).setSumCurrentCreditLinesMoney(oneCurrentCreditLinesMoney);
			}
			/*for (BorrowCancelView borrowCancelView : list) {
				if (borrowCancelView.getTradeCreditStatus().equals(CreditTradestate.ZCWKS.value) || borrowCancelView.getTradeCreditStatus().equals(CreditTradestate.KSHKZ.value)) {
					borrowCancelView.setToPageReplaceStatus(ReplaceStatus.DTH.value);
				}else if (borrowCancelView.getTradeCreditStatus().equals(CreditTradestate.JKTQDQGB.value)) {
					borrowCancelView.setToPageReplaceStatus(ReplaceStatus.YTH.value);
				}
			}*/
			page.setCount(list.get(0).getTotalCount());
			page.setList(list);
		}
		return page;
	}
	
	/**
	 * 查询总金额数
	 * 2015年12月31日
	 * By 周俊
	 * @param borrowCancelView
	 * @return
	 */
	public BigDecimal findCountMoney(BorrowCancelView borrowCancelView){
		Map<String, Object> map = objectToMap(borrowCancelView);
		BigDecimal money = borrowCancelDao.findCountMoney(map);
		return money;
	}
	
	/**
	 * 债权替换出借申请信息和待替换债权信息
	 * 2015年12月15日
	 * By 周俊
	 * @param lendCode
	 * @return
	 */
	public BorrowCanceEx getBorrowCanceEx(String lendCode_matchingId){
		
		if (StringUtils.isBlank(lendCode_matchingId)) {
			return null;
		}
		String[] lendCode_matchingIdArray = lendCode_matchingId.split(":");
		if (ArrayHelper.isNull(lendCode_matchingIdArray)||lendCode_matchingIdArray.length<2) {
			return null;
		}
		String lendCode = lendCode_matchingIdArray[0];
		String matchingId = lendCode_matchingIdArray[1];
		LoanApplyEx loanApplyEx = findLoanApplyEx(lendCode);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("lendCode",lendCode);
		BorrowCanceEx borrowCanceEx = new BorrowCanceEx();
		borrowCanceEx.setLoanApply(loanApplyEx);
		// 获得当前带推荐信息
		MatchingCreditorEx matchingCreditorEx = borrowCancelDao.findMatchingCreditorByMaxMatchingNow(map);
		if(!matchingId.equals(matchingCreditorEx.getMatchingId())){
			MatchingCreditorEx matchingCreditor = matchingCreditorDao.selectByPrimaryKey(matchingId);
			borrowCanceEx.setCurrentCreditLines(BigDecimal.valueOf(0));
			borrowCanceEx.setPrimaryCreditLines(matchingCreditor.getMatchingBorrowQuota());
			return borrowCanceEx;
		}
		BigDecimal currentCreditLines = new BigDecimal(0);
		BigDecimal primaryCreditLines = matchingCreditorEx.getMatchingBorrowQuota();
		borrowCanceEx.setMatchingId(matchingCreditorEx.getMatchingId());
		/*if (!DateUtils.formatDate(matchingCreditorEx.getMatchingInterestStart(), "yyyy-MM-dd").
				equals(DateUtils.formatDate(new Date(), "yyyy-MM-dd"))) {
			borrowCanceEx.setCurrentCreditLines(currentCreditLines);
			borrowCanceEx.setPrimaryCreditLines(primaryCreditLines);
			borrowCanceEx.setList(null);
			return borrowCanceEx;
		}*/
		// 获得该出借下所有的冻结债权
		Map<String, Object> mapFreez = new HashMap<String,Object>();
		mapFreez.put("lendCode",lendCode);
		mapFreez.put("matchingId",matchingCreditorEx.getMatchingId());
		mapFreez.put("creditNode",Node.KYZQ.value);
		mapFreez.put("flag",CreditState.DJ.value);
		mapFreez.put("statusZcwks", CreditTradestate.ZCWKS.value);
		mapFreez.put("statusKshkz", CreditTradestate.KSHKZ.value);
		mapFreez.put("phaseDiscardStatus",ScrapState.BFQ.value);
		List<BorrowReplaceEx> borrowReplaceList = borrowCancelDao.findBorrowReplace(mapFreez);
		for (BorrowReplaceEx borrowReplaceEx : borrowReplaceList) {
			/*Map<String, Object> mapMaxNum = new HashMap<String,Object>();
			mapMaxNum.put("lendCode",lendCode);
			//mapMaxNum.put("loanCode",borrowReplaceEx.getLoanCode());
			mapMaxNum.put("loanCode",borrowReplaceEx.getCreditValueId());
			Integer maxPhaseNumber = loanphaseDao.findMaxPhaseNumber(mapMaxNum);*/
			
			if (StringUtils.isNotBlank(borrowReplaceEx.getMatchingId())) {
				if (borrowReplaceEx.getMatchingId().equals(matchingCreditorEx.getMatchingId())) {
					currentCreditLines = currentCreditLines.add(borrowReplaceEx.getCreditLines());
				}else {
					// 往期匹配做提前结清,需要重新计算本期带推荐金额  = 最大期数倒数第二期的剩余未还本金
					Map<String,Object>	mapLoanphase = new HashMap<String,Object>();
					mapLoanphase.put("loanCode", borrowReplaceEx.getCreditValueId());
					mapLoanphase.put("lendCode", lendCode);
					mapLoanphase.put("phaseNumber",borrowReplaceEx.getPhaseNumber()-1);
					Loanphase loanphase = loanphaseDao.findLoanphase(mapLoanphase);
					if (!ObjectHelper.isEmpty(loanphase)) {
						currentCreditLines = currentCreditLines.add(loanphase.getPhasePrincipalSurplus());
						primaryCreditLines = primaryCreditLines.add(loanphase.getPhasePrincipalSurplus());
					}
				}
			}
		}
		borrowCanceEx.setCurrentCreditLines(currentCreditLines);
		borrowCanceEx.setPrimaryCreditLines(primaryCreditLines);
		borrowCanceEx.setList(borrowReplaceList);
		return borrowCanceEx;
	}
	
	/**
	 * 查询带推荐债权回显出借信息
	 * 2015年12月25日
	 * By 周俊
	 * @param lendCode
	 */
	public LoanApplyEx findLoanApplyEx(String lendCode){
		Map<String, Object> mapLoanApply = new HashMap<String,Object>();
		mapLoanApply.put("lendCode", lendCode);
		LoanApplyEx loanApplyEx = borrowCancelDao.findLoanApplyEx(mapLoanApply);
		return loanApplyEx;
	}
	
	/**
	 * 查询带推荐可用债权
	 * 2016年1月8日
	 * By 周俊
	 * @param page
	 * @param matchingBorrowLookView
	 * @param lendCode
	 * @return
	 */
	public Page<Borrow> findMatchingBorrowLook(Page<Borrow> page,MatchingBorrowLookView matchingBorrowLookView,String lendCode,String matchingId,String dictLoanType,String creditValueIds){
		Borrow borrow = new Borrow();
		// 根据带推荐Id获取带推荐信息
		MatchingCreditorView matchingCreditorView  =  creditorAidManager.getMatchingCreditorViewByMatchingId(matchingId);
		//  初始化还款日集合
		List<Integer>  loanBackmoneyDay = new ArrayList<Integer>(); 
		//  获取本期带推荐帐单日
		int matchingBillDay  =  matchingCreditorView.getMatchingBillDay();
		CreditorConfig creditorConfigParam = new CreditorConfig();
		creditorConfigParam.setDictConfigStatus(matchingCreditorView.getMatchingFirstdayFlag());
		creditorConfigParam.setConfigCreditDay(matchingBillDay);
		creditorConfigParam.setDictConfigZdr(ConfigStatus.QY.value);
		// 获取错期匹配信息
		List<CreditorConfig> creditorConfigList = creditorConfigManager.findByMatchingBillDay(creditorConfigParam);
		if(ArrayHelper.isNotEmpty(creditorConfigList)){
			for(CreditorConfig creditorConfig:creditorConfigList){
				loanBackmoneyDay.add(creditorConfig.getConfigRepayDay());
			}
			borrow.setLoanBackmoneyDays(loanBackmoneyDay);
		}
		// 获取匹配利率
		if(ObjectHelper.isEmpty(matchingBorrowLookView.getBorrowMonthRate())){
			//获取产品匹配利率配置后的匹配利率
			BigDecimal productMatchingRate = null;
			ProductMatchingRate productMatchingRateObj = productMatchingRateManager
					.getProductMchRate(matchingCreditorView.getProductCode(),
							matchingCreditorView.getStartApplyLendMoney(),
							DateUtils.formatDate(matchingCreditorView.getApplyLendDay(),"yyyy-MM-dd"),
							matchingCreditorView.getMatchingFirstdayFlag(),
							matchingBillDay,
							DateUtils.formatDate(matchingCreditorView.getMatchingInterestStart(),"yyyy-MM-dd"));
			if(!ObjectHelper.isEmpty(productMatchingRateObj)){
				productMatchingRate = productMatchingRateObj.getMatchingRateLower();
				matchingBorrowLookView.setProductMatchingRateUpper(productMatchingRateObj.getMatchingRateUpper());
				matchingBorrowLookView.setProductMatchingRateLower(productMatchingRateObj.getMatchingRateLower());
			}else{
				//获取产品默认匹配利率
				Map<String, BigDecimal> productMatchingRateMap = creditorAidManager.getProductDefaultMchRateByCode(matchingCreditorView.getProductCode());
				productMatchingRate = productMatchingRateMap.get("lower");
				matchingBorrowLookView.setProductMatchingRateUpper(productMatchingRateMap.get("upper"));
				matchingBorrowLookView.setProductMatchingRateLower(productMatchingRate);
			}
			borrow.setLoanMonthRate(productMatchingRate);
			matchingBorrowLookView.setBorrowMonthRate(productMatchingRate);
			
		}else {
			borrow.setLoanMonthRate(matchingBorrowLookView.getBorrowMonthRate());
		}
			borrow.setLoanCreditValueFrom(new BigDecimal("1"));
			borrow.setLoanMonthsSurplus(1);
			borrow.setLoanMonthsSurplusFrom(1);
					
/*		// 只匹配首次还款日期早于或等于出借人的首期账单日的债权
		 Map<String,String> param = new HashMap<String, String>();
		 param.put("lendCode", matchingCreditorView.getLendCode());
		 param.put("fristDayFalg", BillState.SQ.value);
		// 获取首期起息日期
		 Date firstMatchingInterestStart = creditorAidManager.getFristLendDay(param);
		 borrow.setLoanBackmoneyFirday(CreditorUtils.getCreditDaybyLendday(firstMatchingInterestStart));*/
		//只匹配首次还款日期早于或等于出借人的本期账单日的债权	
		 borrow.setLoanBackmoneyFirday(matchingCreditorView.getMatchingExpireDay());
		 borrow.setDictLoanFreeFlag(CreditState.KY.value);
		//  债权单次匹配限额
		 Map<String ,BigDecimal> retMap = null ;
		 Map<String ,Object> paramMap = new HashMap<String, Object>();
		 paramMap.put("dictLoanFreeFlag", CreditState.KY.value);
		 List<CreditorperUpper> creditorperUppers = creditorperUpperManager.findList(paramMap);
		 if(creditorperUppers!=null && creditorperUppers.size()>0){
			 retMap = new HashMap<String, BigDecimal>();
			 for(CreditorperUpper cu :creditorperUppers){
				 retMap.put(cu.getDictLoanType()+"-"+cu.getProofType(), cu.getUpperMoney());
			 }
		 }
		borrow.setPpxy(retMap);
		borrow.setLoanName(matchingBorrowLookView.getBorrowerName());
		borrow.setLoanMonthsSurplusFrom(matchingBorrowLookView.getBorrowDaysSurplusFrom());
		borrow.setLoanMonthsSurplusTo(matchingBorrowLookView.getBorrowDaysSurplusTo());
		//如果dictLoanType为空，按照搜索框条件过滤
		if(StringUtils.isBlank(dictLoanType)){
			borrow.setDictLoanType(matchingBorrowLookView.getBorrowType());
		}else{
			//如果本期推荐债权列表为同一债权来源类型，就按此债权来源类型过滤，否则，不过滤
			borrow.setDictLoanType(dictLoanType);
		}
		borrow.setLoanCreditValueFrom(matchingBorrowLookView.getBorrowCreditValueFrom());
		borrow.setLoanCreditValueTo(matchingBorrowLookView.getBorrowCreditValueTo());
		borrow.setLoanCreditValue(BigDecimal.ZERO);
		borrow.setLoanBackmoneyLastday(matchingBorrowLookView.getLoanBackmoneyDay());	
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("lendCode", lendCode);
//		borrow.setCreditValueIds(creditorTradeDao.findCreditValueId(map));
		
		//过滤既有历史债权
		if (matchingCreditorView.getMatchingFirstdayFlag().equals(BillState.SQ.value)) {
			// 获取既有及历史债权列表
			Map<String, String> hisListParam = new HashMap<String, String>();
			hisListParam.put("customerCode", matchingCreditorView.getCustomerCode());
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
		}else{
			//  获取既有及历史债权列表(显示历史既有债权列表中使用) ，非首期历史既有债权只显示 本次出借人员
			List<CreditorTradeBorrowView> creditorTradeBorrowViews = creditorAidManager.getCreditorTradeBorrowViewNOFrist(setHisParam(matchingCreditorView));
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
		
		 // 已经匹配的数据不再显示在查看债权里边
		 if(!StringUtils.isEmpty(creditValueIds)){
				String [] creditValueIdArry = creditValueIds.split("-");
				List<String> tempcreditValues = new ArrayList<String>();
				for(String creditValue : creditValueIdArry ){
					String [] creditValueIdArryTwo = creditValue.split(":");
					tempcreditValues.add(creditValueIdArryTwo[0]);
				}
				borrow.setCreditValueIds(tempcreditValues);
			}

        page = borrowManager.findBorrowByEx(page,borrow);
		return page;
		
	}
	
	
	/**
	 * 通过产品类型   设置非首期的历史记录
	 * 2016年7月15日
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
	 * 移除冻结债权
	 * 2015年12月31日
	 * By 周俊
	 * @param lendCode
	 * @param ids
	 */
	/*@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public List<String> removeBorrowFlag(String lendCode ,String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String[] array = ids.split(",");
		map.put("array", array);
		map.put("lendCode", lendCode);
		map.put("creditNode",Node.KYZQ.value);
		map.put("status", CreditTradestate.JKTQDQGB.value);
		// 获得交易表主键集合
		List<CreditorTrade> list = creditorTradeDao.getListBytradeId(map);
		creditorTradeDao.updateStatus(map);
		List<String> resultList = new ArrayList<String>();
		if (ArrayHelper.isNotEmpty(list)) {
			for (CreditorTrade  creditorTrade: list) {
				// 保存提前结清释放债权人匹配金额记录
				creditorHisManager.saveFreezeBorrowReleaseLog(creditorTrade);
				Loanphase loanphase = new Loanphase();
				//loanphase.setPhaseReleaseStatus(CreditRelease.YSF.value);
				loanphase.setPhaseMateId(creditorTrade.getTradeId());
				Map<String, Object> mapPhaseMateId = new HashMap<String, Object>();
				mapPhaseMateId.put("phaseMateId",creditorTrade.getTradeId());
				Integer maxPhaseNumber = loanphaseDao.findMaxPhaseNumber(mapPhaseMateId);
				loanphase.setPhaseNumber(maxPhaseNumber);
				loanphase.setPhaseFreezeNextstatus(NextcreditorFrozen.YDJ.value);
				loanphaseDao.update(loanphase);
				resultList.add(creditorTrade.getTradeId());
			}
		}
		return resultList;
	}*/
	
	/**
	 * 根据选择的creditValueIds获得列表
	 * 2015年12月31日
	 * By 周俊
	 * @param creditValueIds
	 * @param creditLine
	 * @return
	 */
	public BorrowCanceEx findMatchingBorrowList(String creditValueIds,BigDecimal creditLines){
		String[] array = creditValueIds.split(",");
		BorrowCanceEx borrowCanceEx = new BorrowCanceEx();
		List<BorrowReplaceEx> list = borrowCancelDao.getBorrowList(array);
		BigDecimal sum = new BigDecimal(0);
		List<BorrowReplaceEx> resultList = new ArrayList<BorrowReplaceEx>();
		for (BorrowReplaceEx borrowReplaceEx : list) {
			if (sum.add(borrowReplaceEx.getLoanCreditValue()) .compareTo(creditLines) == BorrowConstant.NEGATIVE_ONE) {
				borrowReplaceEx.setCreditLines(borrowReplaceEx.getLoanCreditValue());
				borrowReplaceEx.setLoanBackmoneyLastdayString(DateUtils.formatDate(borrowReplaceEx.getLoanBackmoneyLastday(), "yyyy-MM-dd"));
				borrowReplaceEx.setLoanBackmoneyFirdayString(DateUtils.formatDate(borrowReplaceEx.getLoanBackmoneyFirday(),"yyyy-MM-dd"));
				if (StringUtils.isBlank(borrowReplaceEx.getDictLoanType())) {
					borrowReplaceEx.setDictLoanType("-");
				} else {
					borrowReplaceEx.setDictLoanType(CreditSrc.creditSrcMap.get(borrowReplaceEx.getDictLoanType()));
				}
				if (StringUtils.isBlank(borrowReplaceEx.getLoanJob())) {
					borrowReplaceEx.setLoanJob("-");
				}else {
				  borrowReplaceEx.setLoanJob(ProfType.parseByCode(borrowReplaceEx.getLoanJob()).getName());
				}
				borrowReplaceEx.setCreditLinesString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getCreditLines(),"￥#,##0.00"));
				borrowReplaceEx.setLoanQuotaString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getLoanQuota(),"￥#,##0.00"));
				borrowReplaceEx.setLoanCreditValueString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getLoanCreditValue(),"￥#,##0.00"));
				borrowReplaceEx.setLoanMonthRateString(String.valueOf(FormatUtils.formatNumber(borrowReplaceEx.getLoanMonthRate())));
			}else {
				borrowReplaceEx.setCreditLines(creditLines.subtract(sum));
				borrowReplaceEx.setLoanBackmoneyLastdayString(DateUtils.formatDate(borrowReplaceEx.getLoanBackmoneyLastday(), "yyyy-MM-dd"));
				borrowReplaceEx.setLoanBackmoneyFirdayString(DateUtils.formatDate(borrowReplaceEx.getLoanBackmoneyFirday(),"yyyy-MM-dd"));
				if (StringUtils.isBlank(borrowReplaceEx.getDictLoanType())) {
					borrowReplaceEx.setDictLoanType("-");
				} else {
					borrowReplaceEx.setDictLoanType(CreditSrc.creditSrcMap.get(borrowReplaceEx.getDictLoanType()));
				}
				if (StringUtils.isBlank(borrowReplaceEx.getLoanJob())) {
					borrowReplaceEx.setLoanJob("-");
				}else {
				  borrowReplaceEx.setLoanJob(ProfType.parseByCode(borrowReplaceEx.getLoanJob()).getName());
				}
				borrowReplaceEx.setCreditLinesString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getCreditLines(),"￥#,##0.00"));
				borrowReplaceEx.setLoanQuotaString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getLoanQuota(),"￥#,##0.00"));
				borrowReplaceEx.setLoanCreditValueString(com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(borrowReplaceEx.getLoanCreditValue(),"￥#,##0.00"));
				borrowReplaceEx.setLoanMonthRateString(String.valueOf(FormatUtils.formatNumber(borrowReplaceEx.getLoanMonthRate())));
				resultList.add(borrowReplaceEx);
				borrowCanceEx.setList(resultList);
				return borrowCanceEx;
			}
			resultList.add(borrowReplaceEx);
			sum=borrowReplaceEx.getLoanCreditValue().add(sum);
		}
		borrowCanceEx.setList(resultList);
		return borrowCanceEx;
	}
	
	/**
	 * 债权撤销后保存新的匹配
	 * 2015年12月23日
	 * By 周俊
	 * @param replaceView
	 * @throws ParseException 
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void save(ReplaceView replaceView,CERequestContext ceContext) throws ParseException{
		logger.info("BorrowCancelManager.save()保存新的匹配   开始");
		String serviceException = "";
		// 本期带推荐Id
		String matchingId = replaceView.getMatchingId();
		logger.info("本期带推荐matchingId="+matchingId);
		// 获得带债权推荐信息
		logger.info("获得带债权推荐信息");
		MatchingCreditorEx matchingCreditor = matchingCreditorDao.selectByPrimaryKey(matchingId);
		String lendCode = replaceView.getLendCode();
		logger.info("出借编号lendCode="+lendCode);
		/*// 释放已冻结的债权
		String[] array = replaceView.getPhaseMateId().split(",");
		for (String phaseMateId : array) {
			Loanphase loanphaseTemp = new Loanphase();
			loanphaseTemp.setPhaseReleaseStatus(CreditRelease.YSF.value);
			loanphaseTemp.setPhaseDiscardStatus(ScrapState.FQ.value);
			loanphaseTemp.setPhaseMateId(phaseMateId);
			loanphaseDao.update(loanphaseTemp);
			// 修改释放的月满盈可用债权的可用天数
			//borrowMonthableManager.updateMatchingBorrowMonthable(phaseMateId,null);
		}*/
		
		// 跟新该笔出借下的冻结债权 
		logger.info("更新该笔出借下的冻结债权   开始 ");
		Map<String, Object> creditorTradeMap = new HashMap<String, Object>();
		String[] freezeBorrowArray = replaceView.getOldCreditValueIds().split(",");
		logger.info("replaceView.getOldCreditValueIds()= "+replaceView.getOldCreditValueIds());
		logger.info("freezeBorrowArray= "+Arrays.toString(freezeBorrowArray));
		List<String> array = new ArrayList<String>();
		if (ArrayHelper.isNotNull(freezeBorrowArray)) {
			logger.info("freezeBorrowArray循环 ");
			for (String freezeBorrow : freezeBorrowArray) {
				array.add(freezeBorrow.split(":")[0]);
				logger.info("freezeBorrow.split="+freezeBorrow.split(":")[0]);
			}
			if (ArrayHelper.isNotEmpty(array)) {
				logger.info("ArrayHelper.isNotEmpty(array) 判断开始");
				try {
					creditorTradeMap.put("array", array);
					creditorTradeMap.put("lendCode", lendCode);
					creditorTradeMap.put("creditNode",Node.KYZQ.value);
					creditorTradeMap.put("status", CreditTradestate.JKTQDQGB.value);
					creditorTradeMap.put("modifyBy",UserUtils.getUserId());
					logger.info("modifyBy="+UserUtils.getUserId());
					creditorTradeMap.put("modifyTime", new Date());
					logger.info("modifyTime="+new Date());
					logger.info("creditorTradeDao.updateStatus 开始");
					creditorTradeDao.updateStatus(creditorTradeMap);
					logger.info("creditorTradeDao.updateStatus 结束");
				} catch (Exception e) {
					logger.info("更新冻结债权校验信息失败");
					throw new ServiceException("更新冻结债权校验信息失败\r\n");
				}
				logger.info("ArrayHelper.isNotEmpty(array) 判断结束");
			}
		}
		logger.info("更新该笔出借下的冻结债权   结束 ");
		// 获得交易表主键集合
       // List<CreditorTrade> creditorTradeList = creditorTradeDao.getListBytradeId(creditorTradeMap);
		
		String freeCreditValueId = "";
		logger.info("ArrayHelper.isNotEmpty(array) 判断开始2");
		if (ArrayHelper.isNotEmpty(array)) {
		try {
			logger.info("String  freezeCreditValueId: array 循环开始");
			for (String  freezeCreditValueId: array) {
				logger.info("freezeCreditValueId="+freezeCreditValueId);
					// 查询当前期的债权分期收益
					Map<String,Object>	mapLoanphase = new HashMap<String,Object>();
					mapLoanphase.put("loanCode", freezeCreditValueId);
					mapLoanphase.put("matchingId", matchingId);
					mapLoanphase.put("lendCode", lendCode);
					logger.info("查询当前期的债权分期收益:loanCode="+freezeCreditValueId+";matchingId="+matchingId+";lendCode="+lendCode);
					logger.info("loanphaseDao.findLoanphase(mapLoanphase) 开始");
					Loanphase loanphase = loanphaseDao.findLoanphase(mapLoanphase);
					logger.info("loanphaseDao.findLoanphase(mapLoanphase) 结束");
					// 如果是提前结清删除当前期下的分期收益记录
					Loanphase deleteLoanphase = new Loanphase();
					deleteLoanphase.setMatchingId(matchingId);
					deleteLoanphase.setLendCode(lendCode);
					deleteLoanphase.setLoanCode(freezeCreditValueId);
					deleteLoanphase.setPhaseNumber(loanphase.getPhaseNumber());
					logger.info("如果是提前结清删除当前期下的分期收益记录:matchingId="+matchingId+";lendCode="+lendCode+";freezeCreditValueId="+freezeCreditValueId+";loanphase.getPhaseNumber()="+loanphase.getPhaseNumber());
					logger.info("loanphaseDao.delete(deleteLoanphase) 开始");
					loanphaseDao.delete(deleteLoanphase);
					logger.info("loanphaseDao.delete(deleteLoanphase) 结束");
					// 保存提前结清释放债权人匹配金额记录
					//creditorHisManager.saveFreezeBorrowReleaseLog(creditorTrade);
					// 向债权管理表中添加
					logger.info("向债权管理表中添加  开始");
					CreditorHis creditorHis = new CreditorHis();
					creditorHis.setNodeId(freezeCreditValueId);
					logger.info("creditorHis.setNodeId="+freezeCreditValueId);
					creditorHis.setMatchingId(matchingId);
					logger.info("creditorHis.setMatchingId="+matchingId);
					// 查询债权冻结时释放金额
					Map<String,Object> freezeLoanphaseMap = new HashMap<String, Object>();
					freezeLoanphaseMap.put("loanCode", freezeCreditValueId);
					logger.info("freezeLoanphaseMap.put('loanCode', freezeCreditValueId)="+freezeCreditValueId);
					freezeLoanphaseMap.put("lendCode", lendCode);
					logger.info("freezeLoanphaseMap.put('lendCode', lendCode)="+lendCode);
					freezeLoanphaseMap.put("phaseNumber",loanphase.getPhaseNumber()-1);
					logger.info("freezeLoanphaseMap.put('phaseNumber',loanphase.getPhaseNumber()-1)="+(loanphase.getPhaseNumber()-1));
					logger.info("loanphaseDao.findLoanphase(freezeLoanphaseMap) 开始");
					Loanphase freezeLoanphase = loanphaseDao.findLoanphase(freezeLoanphaseMap);
					logger.info("loanphaseDao.findLoanphase(freezeLoanphaseMap) 结束");
					if (ObjectHelper.isEmpty(freezeLoanphase)) {
						logger.info("ObjectHelper.isEmpty(freezeLoanphase) 开始");
						// 出借与债权首次匹配,不存在上期分期收益情况,就应该释放匹配金额
						Map<String,Object> freezeCreditorTradeMap = new HashMap<String, Object>();
						freezeCreditorTradeMap.put("loanCode", freezeCreditValueId);
						freezeCreditorTradeMap.put("lendCode", lendCode);
						freezeCreditorTradeMap.put("creditNode", Node.KYZQ.value);
						logger.info("loanCode="+freezeCreditValueId+";lendCode="+lendCode+";creditNode="+Node.KYZQ.value);
						List<CreditorTrade> list = creditorTradeDao.getListBytradeId(freezeCreditorTradeMap);
						if (ArrayHelper.isNotEmpty(list)) {
							creditorHis.setBeforMoney(list.get(0).getTradeMateMoney());
							logger.info("creditorHis.setBeforMoney="+list.get(0).getTradeMateMoney());
							creditorHis.setOperateMoney(list.get(0).getTradeMateMoney());
							logger.info("creditorHis.setOperateMoney="+list.get(0).getTradeMateMoney());
							creditorHis.setAfterMoney(BigDecimal.valueOf(0));
							logger.info("creditorHis.setAfterMoney="+BigDecimal.valueOf(0));
						}
						logger.info("ObjectHelper.isEmpty(freezeLoanphase) 结束");
					}else {
						logger.info("ObjectHelper.isNotEmpty(freezeLoanphase) 开始");
						creditorHis.setBeforMoney(freezeLoanphase.getPhasePrincipalSurplus());
						logger.info("creditorHis.setBeforMoney="+freezeLoanphase.getPhasePrincipalSurplus());
						creditorHis.setOperateMoney(freezeLoanphase.getPhasePrincipalSurplus());
						logger.info("creditorHis.setOperateMoney="+freezeLoanphase.getPhasePrincipalSurplus());
						creditorHis.setAfterMoney(BigDecimal.valueOf(0));
						logger.info("creditorHis.setAfterMoney="+BigDecimal.valueOf(0));
						logger.info("ObjectHelper.isNotEmpty(freezeLoanphase) 结束");
					}					
					//BigDecimal operateMoney = creditorHis.getOperateMoney();		               
					logger.info("creditorHisManager.saveFreezeBorrowReleaseLog(creditorHis)  开始");
					creditorHisManager.saveFreezeBorrowReleaseLog(creditorHis);
					logger.info("creditorHisManager.saveFreezeBorrowReleaseLog(creditorHis)  结束");
					logger.info("向债权管理表中添加  结束");
					Loanphase updateLoanphase = new Loanphase();
					//updateLoanphase.setPhaseReleaseStatus(CreditRelease.YSF.value);
					//updateLoanphase.setPhaseDiscardStatus(ScrapState.FQ.value);
					updateLoanphase.setPhasePrincipalSurplus(BigDecimal.valueOf(0));
					logger.info("updateLoanphase.setPhasePrincipalSurplus="+BigDecimal.valueOf(0));
					/*//本期应还本金
					updateLoanphase.setPhasePrincipalCur(loanphase.getPhasePrincipalCur().add(operateMoney));
					//本期应还利息
					updateLoanphase.setPhaseAmount(loanphase.getPhaseAmount().add(operateMoney));
					//截止本期已还本金
					updateLoanphase.setPhaseBackPrincipal(loanphase.getPhaseBackPrincipal().add(operateMoney));
					//截止本期已还本息
					updateLoanphase.setPhaseBackCount(loanphase.getPhaseBackCount().add(operateMoney));*/
					
					updateLoanphase.setLoanCode(freezeCreditValueId);
					logger.info("updateLoanphase.setLoanCode="+freezeCreditValueId);
					updateLoanphase.setLendCode(lendCode);
					logger.info("updateLoanphase.setLendCode="+lendCode);
					//updateLoanphase.setPhaseMateId(creditorTrade.getTradeId());
					// Map<String, Object> mapPhaseMateId = new HashMap<String, Object>();
					// mapPhaseMateId.put("phaseMateId",creditorTrade.getTradeId());
					// Integer maxPhaseNumber = loanphaseDao.findMaxPhaseNumber(mapPhaseMateId);
					updateLoanphase.setPhaseNumber(loanphase.getPhaseNumber()-1);
					logger.info("updateLoanphase.setPhaseNumber="+(loanphase.getPhaseNumber()-1));
					updateLoanphase.setPhaseNumberSurplus(0);
					logger.info("updateLoanphase.setPhaseNumberSurplus="+0);
					updateLoanphase.setPhaseFreezeNextstatus(NextcreditorFrozen.YDJ.value);
					logger.info("updateLoanphase.setPhaseFreezeNextstatus="+NextcreditorFrozen.YDJ.value);
					updateLoanphase.preUpdate();
					logger.info("loanphaseDao.update(updateLoanphase)  开始");
					loanphaseDao.update(updateLoanphase);
					logger.info("loanphaseDao.update(updateLoanphase)  结束");
					// 获得匹配的冻结债权
					Borrow borrow = borrowDao.get(freezeCreditValueId);
					logger.info("获得匹配的冻结债权freezeCreditValueId="+freezeCreditValueId);
					//释放债权
					borrow.setLoanCreditValue(borrow.getLoanCreditValue().add(creditorHis.getOperateMoney()));
					logger.info("borrow.setLoanCreditValue="+borrow.getLoanCreditValue());
					borrow.setVerTime(borrow.getVerTime());
					logger.info("borrow.setVerTime="+borrow.getVerTime());
					borrow.preUpdate();
					int update = borrowDao.updateBorrow(borrow);
					logger.info("update="+update);
					if(update == 0){
						serviceException = serviceException + "可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(), borrow.getLoanIdcard(),null,null);
						logger.info("serviceException="+serviceException);
					}
					logger.info("前freeCreditValueId="+borrow.getCreditValueId()+","+freeCreditValueId);
					freeCreditValueId=borrow.getCreditValueId()+","+freeCreditValueId;
					logger.info("后freeCreditValueId="+freeCreditValueId);
				} 
			logger.info("String  freezeCreditValueId: array 循环结束");
			}catch (Exception e) {
				logger.info("更新冻结债权分期收益失败");
				throw new ServiceException("更新冻结债权分期收益失败");
			}
		}
		logger.info("ArrayHelper.isNotEmpty(array) 判断结束2");
		// 债权协议加盖作废章
		try {
			logger.info("creditorAidManager.caInvalidCreditorFile 开始");
			creditorAidManager.caInvalidCreditorFile(matchingId);
			logger.info("creditorAidManager.caInvalidCreditorFile 结束");
		} catch (Exception e) {
			logger.info("债权协议加盖作废章失败");
			throw new ServiceException("债权协议加盖作废章失败");
		}
		// 添加债权交易表
		// 获得出借到期日期
		logger.info("添加债权交易表      获得出借到期日期");
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		logger.info("loanApply.setApplyCode="+lendCode);
		loanApply = loanApplyDao.get(loanApply);
		Date expireDate = loanApply.getExpireDate();
		logger.info("expireDate="+expireDate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		List<String> listCreditValueId = creditorTradeDao.findCreditValueId(map);
		logger.info("listCreditValueId.size()="+listCreditValueId.size());
		List<ReplaceBorrowView> list = replaceView.getData();
		logger.info("list.size()="+list.size());
		String creditValueId = "";
		logger.info("ReplaceBorrowView replaceBorrowView : list for循环  开始");
		for (ReplaceBorrowView replaceBorrowView : list) {
			if(StringUtils.isNotBlank(replaceBorrowView.getCreditValueId())){
				logger.info("replaceBorrowView.getCreditValueId()="+replaceBorrowView.getCreditValueId());
				String[] arrayTemp = replaceBorrowView.getCreditValueId().split(":");
				logger.info("arrayTemp="+Arrays.toString(arrayTemp));
				// 获得匹配的可用债权
				Borrow borrow = borrowDao.get(arrayTemp[0]);
				logger.info("arrayTemp[0]="+arrayTemp[0]);
				logger.info("前creditValueId="+borrow.getCreditValueId()+","+creditValueId);
				creditValueId=borrow.getCreditValueId()+","+creditValueId;
				logger.info("后creditValueId="+creditValueId);
				logger.info("!ArrayHelper.isNotEmpty(listCreditValueId)="+!ArrayHelper.isNotEmpty(listCreditValueId));
				logger.info("!listCreditValueId.contains(replaceBorrowView.getCreditValueId())="+!listCreditValueId.contains(replaceBorrowView.getCreditValueId()));
				if (!ArrayHelper.isNotEmpty(listCreditValueId) || 
				 !listCreditValueId.contains(replaceBorrowView.getCreditValueId())){
					logger.info("第一次匹配");
					// 第一次匹配
					CreditorTrade creditorTrade = new CreditorTrade();
					creditorTrade.setCreditCode(borrow.getCreditValueId());
					logger.info("creditorTrade.setCreditCode="+borrow.getCreditValueId());
					creditorTrade.setCreditNode(Node.KYZQ.value);
					creditorTrade.setLendCode(lendCode);
					logger.info("creditorTrade.setLendCode="+lendCode);
					// 债权交易预计到期时间  债权交易实际到期时间
					Date loanBackmoneyLastday = borrow.getLoanBackmoneyLastday();
					logger.info("loanBackmoneyLastday="+loanBackmoneyLastday);
					if(expireDate != null){
						logger.info("expireDate != null"+expireDate);
						if (DateUtils.dateBefore(loanBackmoneyLastday, expireDate)) {
							logger.info("!DateUtils.dateBefore(loanBackmoneyLastday, expireDate)");
							creditorTrade.setTradeExpectDay(new Timestamp(loanBackmoneyLastday.getTime()));
							logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(loanBackmoneyLastday.getTime()));
							creditorTrade.setTradeActualDay(new Timestamp(loanBackmoneyLastday.getTime()));
							logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(loanBackmoneyLastday.getTime()));
						}else{
							logger.info("!DateUtils.dateBefore(loanBackmoneyLastday, expireDate)");
							creditorTrade.setTradeExpectDay(new Timestamp(expireDate.getTime()));
							logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(expireDate.getTime()));
							creditorTrade.setTradeActualDay(new Timestamp(expireDate.getTime()));
							logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(expireDate.getTime()));
						}
					}else{
						logger.info("expireDate == null"+expireDate);
						logger.info("DateUtils.dateBefore(loanBackmoneyLastday, expireDate)");
						creditorTrade.setTradeExpectDay(new Timestamp(loanBackmoneyLastday.getTime()));
						logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(loanBackmoneyLastday.getTime()));
						creditorTrade.setTradeActualDay(new Timestamp(loanBackmoneyLastday.getTime()));
						logger.info("creditorTrade.setTradeExpectDay="+new Timestamp(loanBackmoneyLastday.getTime()));
					}
					creditorTrade.setTradeMateMoney(replaceBorrowView.getCreditLines());
					logger.info("creditorTrade.setTradeMateMoney="+replaceBorrowView.getCreditLines());
					creditorTrade.setTradeMateMoneyPercent(ReckonUtils.percentage(replaceBorrowView.getCreditLines(),borrow.getLoanCreditValue()));
					logger.info("creditorTrade.setTradeMateMoneyPercent="+creditorTrade.getTradeMateMoneyPercent());
					creditorTrade.setTradePassDate(new Timestamp(new Date().getTime()));
					logger.info("creditorTrade.setTradePassDate="+creditorTrade.getTradePassDate());
					creditorTrade.setDictTradeCreditStatus(CreditTradestate.KSHKZ.value);
					creditorTrade.preInsert();
					creditorTrade.setTradeId(IdGen.uuid());
					logger.info("creditorTrade.setTradeId="+creditorTrade.getTradeId());
					if (ObjectHelper.isEmpty(replaceView.getMatchingId()) || replaceView.getMatchingId().length()==(Integer)BorrowConstant.ZERO) {
						logger.info("不存在你所匹配的出借");
						throw new ServiceException("不存在你所匹配的出借");
					}
						creditorTrade.setMatchingId(replaceView.getMatchingId());
						logger.info("creditorTrade.setMatchingId="+replaceView.getMatchingId());
						logger.info("creditorTradeDao.insert(creditorTrade) 开始");
						creditorTradeDao.insert(creditorTrade);
						logger.info("creditorTradeDao.insert(creditorTrade) 结束");
						// 获得交易id
						String tradeId = creditorTrade.getTradeId();
						logger.info("tradeId="+tradeId);
						// 保存债权分期收益
							// 获得当带推荐信息
						Map<String, Object> mapMatchingCreditor = new HashMap<String,Object>();
						mapMatchingCreditor.put("lendCode", lendCode);
						String matchingFirstdayFlag = matchingCreditor.getMatchingFirstdayFlag();
						logger.info("matchingFirstdayFlag="+matchingFirstdayFlag);
						Loanphase loanphase = new Loanphase();
						loanphase.setPhaseId(IdGen.uuid());
						logger.info("loanphase.setPhaseId="+loanphase.getPhaseId());
						loanphase.setMatchingId(replaceView.getMatchingId());
						logger.info("loanphase.setMatchingId="+loanphase.getMatchingId());
						//loanphase.setLoanCode(borrow.getLoanCode());
						loanphase.setLoanCode(borrow.getCreditValueId());
						logger.info("loanphase.setLoanCode="+loanphase.getLoanCode());
						loanphase.setLendCode(lendCode);
						logger.info("loanphase.setLendCode="+loanphase.getLendCode());
						// 是否已还款 默认已还款
						loanphase.setPhaseRepaySign(Constant.PHASE_REPAY_SIGN);
						logger.info("loanphase.setPhaseRepaySign="+loanphase.getPhaseRepaySign());
						// 真正还款时间 首期还款日 月加（借款期数-剩余借款期数）
						Calendar  calendar = Calendar.getInstance();
						calendar.setTime(borrow.getLoanBackmoneyFirday());
						calendar.add(Calendar.MONTH, borrow.getHkqs());
						loanphase.setPhaseRepaydateActual(calendar.getTime());
						logger.info("loanphase.setPhaseRepaydateActual="+loanphase.getPhaseRepaydateActual());
						loanphase.setPhaseBegindayCur(matchingCreditor.getMatchingInterestStart());
						logger.info("loanphase.setPhaseBegindayCur="+loanphase.getPhaseBegindayCur());
						loanphase.setPhaseEnddayCur(matchingCreditor.getMatchingExpireDay());
						logger.info("loanphase.setPhaseEnddayCur="+loanphase.getPhaseEnddayCur());
						loanphase.setPhaseNumber(matchingCreditor.getMatchingNow());
						logger.info("loanphase.setPhaseNumber="+loanphase.getPhaseNumber());
						loanphase.setPhaseMateId(tradeId);
						logger.info("loanphase.setPhaseMateId="+loanphase.getPhaseMateId());
						// 出借剩余期数
						Integer lendDaySurplus = matchingCreditor.getMatchingTotal() - matchingCreditor.getMatchingNow() + 1;
						logger.info("lendDaySurplus="+lendDaySurplus);
						// 借款剩余期数
						Integer loanDaySurplus = borrow.getLoanMonthsSurplus();
						logger.info("loanDaySurplus="+loanDaySurplus);
						if (!ObjectHelper.isEmpty(loanDaySurplus) && !ObjectHelper.isEmpty(lendDaySurplus)) {
							if (loanDaySurplus > lendDaySurplus) {
								loanphase.setPhaseMateNumber(lendDaySurplus);
							}else{
								loanphase.setPhaseMateNumber(loanDaySurplus);
							}
						}else {
							if (ObjectHelper.isEmpty(loanDaySurplus)) {
								loanphase.setPhaseMateNumber(lendDaySurplus);
							}else {
								loanphase.setPhaseMateNumber(loanDaySurplus);
							}
						}
						loanphase.setPhaseNumberSurplus(BackMoneyUtil.getMonthSpace(matchingCreditor.getMatchingInterestStart(), borrow.getLoanBackmoneyFirday(),borrow.getLoanMonths())-1);
						logger.info("loanphase.setPhaseNumberSurplus="+loanphase.getPhaseNumberSurplus());
						loanphase.setPhaseDiscardStatus(ScrapState.BFQ.value);
						loanphase.setPhaseReleaseStatus(CreditRelease.MSF.value);
						if (ObjectHelper.isEmpty(matchingFirstdayFlag)) {
							logger.info("债权标识异常");
							throw new ServiceException("债权标识异常");
						}
						// 计算还款情况
						logger.info("matchingFirstdayFlag="+matchingFirstdayFlag);
						if (matchingFirstdayFlag.equals(BillState.SQ.value)) {
							loanphase = ReckonUtils.getLoanphaseToFirst(borrow, matchingCreditor,creditorTrade,loanphase);
						} else {
							ReckonUtils.getLoanphaseNotFirst(matchingCreditor, borrow,creditorTrade,loanphase);
						}
						loanphase.setPhaseFreezeNextstatus(NextcreditorFrozen.MDJ.value);
						// 添加本期账单日
						loanphase.setBillDay(CreditorUtils.getCreditDaybyLendday(matchingCreditor.getMatchingInterestStart()));
						logger.info("loanphase.setBillDay="+loanphase.getBillDay());
						loanphase.preInsert();
						logger.info("loanphaseDao.insert(loanphase) 开始");
						loanphaseDao.insert(loanphase);
						logger.info("loanphaseDao.insert(loanphase) 结束");
						// 修改本期债权带推荐信息表
						Map<String,Object> mapUpdateMatchingCreditor = new HashMap<String,Object>();
						// 本期推荐金额
						BigDecimal matchingBorrowQuota = replaceView.getPrimaryCreditLines();
						logger.info("matchingBorrowQuota="+matchingBorrowQuota);
						mapUpdateMatchingCreditor.put("matchingBorrowQuota", matchingBorrowQuota);// 本期出借金额
						// 本期已匹配金额
						BigDecimal matchingMatchMoney = matchingCreditor.getMatchingMatchMoney().add(matchingBorrowQuota.subtract(matchingCreditor.getMatchingBorrowQuota()));
						logger.info("matchingMatchMoney="+matchingMatchMoney);
						mapUpdateMatchingCreditor.put("matchingMatchMoney",matchingMatchMoney);// 本期已匹配金额
						mapUpdateMatchingCreditor.put("matchingId",replaceView.getMatchingId());// 本期带推荐id
						logger.info("matchingId="+replaceView.getMatchingId());
						mapUpdateMatchingCreditor.put("dictMatchingFilesendStatus",EmailState.DFS.value);
						mapUpdateMatchingCreditor.put("replaceDay", new Date());
						logger.info("updateMatchingCreditorByMatchingId  开始");
						borrowCancelDao.updateMatchingCreditorByMatchingId(mapUpdateMatchingCreditor);
						logger.info("updateMatchingCreditorByMatchingId  结束");
						// 修改可用债权的可用金额
						borrow.setLoanCreditValue(borrow.getLoanCreditValue().subtract(replaceBorrowView.getCreditLines()));
						logger.info("borrow.setLoanCreditValue="+borrow.getLoanCreditValue());
						borrow.setVerTime(arrayTemp[1]);
						logger.info("borrow.setVerTime="+borrow.getVerTime());
						borrow.preUpdate();
						logger.info("borrowDao.update(borrow)  开始");
						int update = borrowDao.update(borrow);
						logger.info("borrowDao.update(borrow)  开始");
						logger.info("update="+update);
						if(update == 0){
							serviceException = serviceException + "可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(), borrow.getLoanIdcard(),null,null);
							logger.info("serviceException="+serviceException);
						}
				}
			}
		}
		logger.info("ReplaceBorrowView replaceBorrowView : list for循环  结束");
		/** 更新待债权推荐统计 **/
		logger.info("creditorStatisticsManager.updateStatistic  开始");
		creditorStatisticsManager.updateStatistic(
				UserUtils.getUserId(), 1, Constant.CREDIT_DONE, matchingCreditor.getMatchingFirstdayFlag()
				,lendCode);
		logger.info("creditorStatisticsManager.updateStatistic  结束");
		// 重新生成债权文件生成
		logger.info("重新生成债权文件生成  开始 matchingId="+matchingId);
		MatchingCreditorView matchingCreditorView = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		logger.info("重新生成债权文件生成  结束");
		
		// 删除该期过往的债权文件
		logger.info("删除该期过往的债权文件  开始 matchingId="+matchingId);
		Attachment disAttachment = new Attachment();
		disAttachment.setAttaTableId(matchingId);
		disAttachment.setAttaFileOwner("t_tz_matching_creditor");
		disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
		matchingCreditorManager.makeFile(matchingCreditorView, ceContext,disAttachment,Constant.FILE_MAKE_FROM_HC);
		logger.info("删除该期过往的债权文件  结束");
		//matchingCreditorManager.makeFile(matchingCreditorView, ceContext,null,Constant.FILE_MAKE_FROM_HC);
		
		logger.info("updatetLoanphasePeriodBymatchingId  开始");
		logger.info("checkManager.addCheck"+lendCode+";"+freeCreditValueId+";"+creditValueId);
		checkManager.addCheck(lendCode,freeCreditValueId+"提前结清被"+creditValueId+"替换","提前结清替换债权");
		creditorAidManager.updatetLoanphasePeriodBymatchingId(matchingCreditorView);
		logger.info("updatetLoanphasePeriodBymatchingId  结束");
		if(StringUtils.isNotBlank(serviceException)){
			logger.info("StringUtils.isNotBlank(serviceException)");
			throw new ServiceException(serviceException);
		}
		logger.info("BorrowCancelManager.save()保存新的匹配   结束");
	}
	
	/**
	 * 根据编号获得交易消息
	 * 2015年12月25日
	 * By 周俊
	 * @param tradeId
	 * @param lendCode
	 * @param creditCode
	 * @return
	 */
	public CreditorTrade getCreditorTrade(String tradeId,String lendCode,String creditCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeId", tradeId);
		map.put("lendCode", lendCode);
		map.put("creditCode", creditCode);
		CreditorTrade creditorTrade = creditorTradeDao.get(map);
		return creditorTrade;
	}
	
	/**
	 * 根据带推荐id获得既有债权人
	 * 2015年12月31日
	 * By 周俊
	 * @param matching
	 * @param page
	 * @return
	 */
	public Page<Borrow> findExistingBorrow(String lendCode,Page<Borrow> page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		map.put("borrowFreeFlag",CreditState.KY.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("creditValueId");
		PageList<Borrow> pageList = (PageList<Borrow>)borrowCancelDao.findExistingBorrow(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 将对象转成map
	 * 2015年12月28日
	 * By 周俊
	 * @param borrowCancelView
	 * @return
	 */
	public Map<String, Object> objectToMap(BorrowCancelView borrowCancelView){
		Map<String,Object> map = new HashMap<String,Object>();
		/*map.put("borrowFreeFlag", CreditState.DJ.value);// 债权状态
		map.put("creditNode",Node.KYZQ.value);// 债权节点
		map.put("dictApplyEndState",FinishState.WWJ.value);
		// 初始化替换状态
		String[] replaceStatusArray = {CreditTradestate.ZCWKS.value,CreditTradestate.KSHKZ.value,CreditTradestate.JKTQDQGB.value};
		map.put("replaceStatus",replaceStatusArray);*/
		map.put("lendCode",borrowCancelView.getLendCode());// 出借编号
		map.put("customerName",borrowCancelView.getCustomerName());// 客户姓名
		map.put("lendDateFrom",borrowCancelView.getLendDateFrom());// 计划出借日期 
		map.put("lendDateTo",borrowCancelView.getLendDateTo());// 计划出借日期 
		map.put("replaceDayFrom", borrowCancelView.getReplaceDayFrom());// 替换日期
		map.put("replaceDayTo", borrowCancelView.getReplaceDayTo());// 替换日期
		map.put("replaceDay", new Date());// 替换日期

		String orgCode = borrowCancelView.getOrgCode();
		if (!ObjectHelper.isEmpty(orgCode)) {
			map.put("orgCode",orgCode.split(",")); // 部门编号 
		}
		String applyBillday = borrowCancelView.getApplyBillday();// 账单日
		if (StringHelper.isNotEmpty(applyBillday)) {
			List<Integer> list = new ArrayList<Integer>();
			String[] applyBilldayArr = applyBillday.split(",");
			for (String applyBilldayTemp : applyBilldayArr) {
				list.add(StringUtils.toInteger(applyBilldayTemp));
			}
			map.put("applyBillday",list);
		}
		if (StringHelper.isNotEmpty(borrowCancelView.getMatchingFirstdayFlag())) {
			
			map.put("matchingFirstdayFlag", borrowCancelView.getMatchingFirstdayFlag().split(","));// 账单类型
		}
		if (StringHelper.isNotEmpty(borrowCancelView.getProcuctId())) {
			map.put("procuctId", borrowCancelView.getProcuctId().split(",")); //出借产品
		}
		if (StringHelper.isNotEmpty(borrowCancelView.getReplaceStatus())) {
			
			String[] array = borrowCancelView.getReplaceStatus().split(",");
			/*List<String> list = new ArrayList<String>();
			if (ArrayHelper.isNotNull(array) && array.length == 1 && array[0].equals(ReplaceStatus.DTH.value)) {
				list.add(CreditTradestate.ZCWKS.value);
				list.add(CreditTradestate.KSHKZ.value);
			}else if (ArrayHelper.isNotNull(array)&& array.length == 1&&array[0].equals(ReplaceStatus.YTH.value)) {
				list.add(CreditTradestate.JKTQDQGB.value);
			}else {
				list.add(CreditTradestate.ZCWKS.value);
				list.add(CreditTradestate.KSHKZ.value);
				list.add(CreditTradestate.JKTQDQGB.value);
			}*/
			if(array.length == 1){
				map.put("replaceStatus",array[0]);// 替换状态
			}
		}
		if (StringHelper.isNotEmpty(borrowCancelView.getAddrCity())) {
			map.put("addrCity", "%"+borrowCancelView.getAddrCity().replaceAll(",","%|%")+"%");// 所在城市
		}
		return map;
	}

	/**
	 * 判断页面是通过搜索栏条件导出，还是通过复选框导出
	 * 2017年3月22日
	 * By 常亚运
	 * @param search_cxlb
	 * @return
	 */
	public BorrowCancelView judgeIds(BorrowCancelView search_cxlb) {
		String matchingId = search_cxlb.getMatchingId();
		if(!StringUtils.isEmpty(matchingId)){
			search_cxlb.setListLendCode(MatchingUtils.mulityStringOptionForSearch(matchingId, ";"));
		}
		return  search_cxlb;
	}

	/**
	 * 调用导出工具类
	 * 2017年3月23日
	 * By 常亚运
	 * @param borrowCancelView
	 * @param response
	 * @param namespace
	 * @param fileName
	 * @param bcm
	 */
	
	public void outExcel(BorrowCancelView borrowCancelView,HttpServletResponse response,String namespace,String fileName,BorrowCancelManager bcm) {
		borrowCancelView = judgeIds(borrowCancelView);
		Map<String, Object> map = objectToMap(borrowCancelView);
		map.put("listLendCode", borrowCancelView.getListLendCode());
		ExportCXLBExcelUtils.exportData(map, response, namespace, fileName,bcm);	
	}

}
