package com.creditharmony.fortune.borrow.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.claim.dto.SyncClaim;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.CreditState;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.dao.BorrowMonthDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.dao.CreditorTradesDao;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthableLookEx;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowMonthSplitView;
import com.creditharmony.fortune.borrow.view.BorrowMonthableBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthableView;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;
import com.creditharmony.fortune.template.entity.BorrowMonthableOutExcel;

/**
 * 月满盈可用债权
 * @Class Name BorrowMonthableManager
 * @author 周俊
 * @Create In 2015年12月2日
 */
@Service
public class BorrowMonthableManager extends CoreManager<BorrowMonthableDao,BorrowMonthable>{

	@Autowired 
	private BorrowMonthableDao borrowMonthableDao;
	@Autowired
	private BorrowMonthDao borrowMonthDao;
	@Autowired
	private CreditorHisManager creditorHisManager;
	@Autowired
	private BorrowManager borrowManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private CreditorTradesDao creditorTradeDao;

	/**
	 * 月满盈可用债权池保存月满盈债权池拆分的债权
	 * 2015年12月7日
	 * By 周俊
	 * @param split
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveBorrowMonthSplit(BorrowMonthSplitView split){
		    BorrowMonth borrowMonth = new BorrowMonth();
			borrowMonth.setCreditMonId(split.getCreditMonId());
			borrowMonth = borrowMonthDao.get(borrowMonth);
			borrowMonth.setLoanAvailabeValue(split.getSurplusSplitMoney()); 
			borrowMonth.preUpdate();
			borrowMonth.setVerTime(split.getVerTime());
			int update = borrowMonthDao.update(borrowMonth);
			if(update == 0){
				throw new ServiceException("月满盈债权池:"+FormatUtils.messageTemplates(borrowMonth.getLoanName(), borrowMonth.getLoanIdcard(), null,null));
			}
			BorrowMonthable borrowMonthable = new BorrowMonthable();
			// 添加主键
			borrowMonthable.setCreditMonableId(IdGen.uuid());
			borrowMonthable.setCreditMonId(borrowMonth.getCreditMonId());
			borrowMonthable.setLoanCode(borrowMonth.getLoanCode());
			borrowMonthable.setLoanId(borrowMonth.getLoanId());
			borrowMonthable.setLoanName(borrowMonth.getLoanName());
			borrowMonthable.setLoanIdcard(borrowMonth.getLoanIdcard());
			borrowMonthable.setLoanJob(borrowMonth.getLoanJob());
			borrowMonthable.setLoanProduct(borrowMonth.getLoanProduct());
			borrowMonthable.setLoanPurpose(borrowMonth.getLoanPurpose());
			borrowMonthable.setDictLoanType(borrowMonth.getDictLoanType());
			borrowMonthable.setLoanOutmoneyDay(borrowMonth.getLoanOutmoneyDay());
			borrowMonthable.setLoanBackmoneyFirday(borrowMonth.getLoanBackmoneyFirday());
			borrowMonthable.setLoanBackmoneyDay(borrowMonth.getLoanEndmoneyDay());
			// 保存月利率
			borrowMonthable.setLoanMonthRate(split.getSplitRate());
			// 获得首次还款日
			Date borrowBackmoneyFirday = borrowMonthable.getLoanBackmoneyFirday();
			// 债权可用日期
			Date borrowCreditDateUsable = ReckonUtils.getBorrowCreditDateUsable(borrowBackmoneyFirday);
			borrowMonthable.setLoanCreditDayUsable(borrowCreditDateUsable);
			// 借款天数 = 末期还款日期 – 债权可用日期 + 1
			Date date = borrowMonthable.getLoanBackmoneyDay();
			if (ObjectHelper.isEmpty(date)) {
				throw new ServiceException("请核查所拆分的债权信息");
			}
			Integer loanDay = (int) (DateUtils.getDistanceOfTwoDate(borrowCreditDateUsable,date)+1);
			borrowMonthable.setLoanDay(loanDay);
		    //可用天数 = 末期还款日期 – 系统当前日期 + 1
			Integer loanAvailableDays = (int) (DateUtils.getDistanceOfTwoDate(new Date(),date)+1);
			borrowMonthable.setLoanAvailableDays(loanAvailableDays);
			borrowMonthable.setLoanCreditValue(split.getSplitMoney());
			borrowMonthable.setLoanAvailabeValue(split.getSplitMoney());
			if(borrowMonth.getDictLoanType().equals(CreditSrc.XJ.value)){
				borrowMonthable.setLoanValueYear(borrowMonth.getLoanValueYear());
			}else{
				borrowMonthable.setLoanValueYear(borrowMonthable.getLoanMonthRate().multiply(new BigDecimal(12)));
			}
			borrowMonthable.setLoanMiddleMan(borrowMonth.getLoanMiddleMan());
			borrowMonthable.setDictLoanFreeFlag(borrowMonth.getDictLoanFreeFlag());
			borrowMonthable.setLoanModifiedDay(new Date());
			borrowMonthable.setTrusteeFlag(borrowMonth.getLoanTrusteeFlag());
			borrowMonthable.setDicLoanDistinguish(borrowMonth.getDicLoanDistinguish());
			borrowMonthable.setCreateTime(new Date());
			borrowMonthable.setCreateBy(UserUtils.getUser(UserUtils.getUserId()).getName());
			borrowMonthable.setModifyTime(new Date());
			borrowMonthable.setModifyBy(UserUtils.getUser(UserUtils.getUserId()).getName());
			borrowMonthableDao.insert(borrowMonthable);
			creditorHisManager.saveBorrowMonthSplitLog(split);
			checkManager.addCheck(split.getCreditMonId(),
					"向月满盈可用债权池拆分"+StringUtils.toDouble(split.getSplitMoney())
					, "月满盈债权拆分");
	}
	
	/**
	 * 获取月满盈可用债权列表
	 * 2016年1月6日
	 * By 周俊
	 * @param page
	 * @param borrowMonthableView
	 * @return
	 */
	public Page<BorrowMonthable> findBorrowMonthable(Page<BorrowMonthable> page,BorrowMonthableView borrowMonthableView){
		Map<String, Object> map = objectToMap(borrowMonthableView);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("credit_monable_id");
        PageList<BorrowMonthable> pageList = (PageList<BorrowMonthable>)borrowMonthableDao.findBorrowMonthable(map, pageBounds);
        for (BorrowMonthable borrowMonthable : pageList) {
			String loanIdcard = FormatUtils.formatLoanIdcard(borrowMonthable.getLoanIdcard());
			borrowMonthable.setLoanIdcard(loanIdcard);
		}
        PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查询总金额
	 * 2016年1月6日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public BigDecimal findAllMoney(BorrowMonthableView borrowMonthableView){
		Map<String, Object> map = objectToMap(borrowMonthableView);
		if(borrowMonthableView.getCreditMonableIdList()!=null && borrowMonthableView.getCreditMonableIdList().size()>0){
			map.put("creditMonableIdList", borrowMonthableView.getCreditMonableIdList());
		}
		BigDecimal money = borrowMonthableDao.findAllMoney(map);
		return money;
	}
	
	/**
	 * 查询总金额
	 * 2016年5月4日
	 * By 高士芳
	 * @param borrowMonthableView
	 * @return
	 */
	public BigDecimal findAllMoney2(BorrowMonthableView borrowMonthableView){
		Map<String, Object> map = objectToMap(borrowMonthableView);
		if(borrowMonthableView.getCreditMonableIdList()!=null && borrowMonthableView.getCreditMonableIdList().size()>0){
			map.put("creditMonableIdList", borrowMonthableView.getCreditMonableIdList());
		}
		BigDecimal money = borrowMonthableDao.findAllMoney2(map);
		money = new BigDecimal(String.valueOf(BigDecimalUtil.round(money.doubleValue(), 2)));
		return money;
	}
	/**
	 * 查看月满盈可用债权信息
	 * 2015年12月3日
	 * By 周俊
 	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public Page<BorrowMonthableLookEx> borrowMonthableLook(Page<BorrowMonthableLookEx> page,String creditCode ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("creditNode", Node.YMYKY.value);
		map.put("creditCode", creditCode);
		String[] array = {CreditTradestate.ZCWKS.value,CreditTradestate.WKSBGB.value};
		map.put("dictTradeCreditStatus", array);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<BorrowMonthableLookEx> pageList = (PageList<BorrowMonthableLookEx>) borrowMonthableDao.borrowMonthableLook(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 获得月满盈可用债权回池信息
	 * 2015年12月7日
	 * By 周俊
	 * @param creditMonableId
	 * @return
	 */
	public BorrowMonthableBackToolView getBorrowMonthableBackTool(String creditMonableId){
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("creditMonableId", creditMonableId);
		BorrowMonthableBackToolView borrowMonthableBackToolView = borrowMonthableDao.getBorrowMonthableBackTool(map);
		BigDecimal afterReleaseBorrow = ReckonUtils.getAfterReleaseBorrow(borrowMonthableBackToolView, null, null);
		borrowMonthableBackToolView.setAfterReleaseBorrow(afterReleaseBorrow);
		return borrowMonthableBackToolView;
	}
	
	/**
	 * 月满盈可用债权回池
	 * 2015年12月7日
	 * By 周俊
	 * @param toolView
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void borrowMonthableBackTool(BorrowMonthableBackToolView toolView,BorrowMonth borrowMonth,BorrowMonthable borrowMonthable){
		// 更新新月满盈可用债权信息
		if (ObjectHelper.isEmpty(borrowMonthable)) {
			borrowMonthable = new BorrowMonthable();
			borrowMonthable.setCreditMonableId(toolView.getCreditMonableId());
			borrowMonthable = borrowMonthableDao.get(borrowMonthable);
		}
		borrowMonthable.setLoanAvailabeValue(borrowMonthable.getLoanAvailabeValue().subtract(toolView.getBeforeReleaseBorrow()));
		borrowMonthable.setVerTime(toolView.getBorrowMonthableVerTime());
		borrowMonthable.preUpdate();
		int borrowMonthableUpdate = borrowMonthableDao.update(borrowMonthable);
		if (borrowMonthableUpdate == 0) {
			throw new ServiceException("月满盈可用债权池:"+FormatUtils.messageTemplates(borrowMonthable.getLoanName(), borrowMonthable.getLoanIdcard(), null,null));
		}
		// 跟新月满盈债权
		if (ObjectHelper.isEmpty(borrowMonth)) {
			borrowMonth = new BorrowMonth();
			borrowMonth.setCreditMonId(toolView.getCreditMonId());
			borrowMonth = borrowMonthDao.get(borrowMonth);
			if (ObjectHelper.isEmpty(borrowMonth)) {
				return;
			}
		}
		borrowMonth.setLoanAvailabeValue(borrowMonth.getLoanAvailabeValue().add(toolView.getAfterReleaseBorrow()));
		borrowMonth.setVerTime(toolView.getBorrowMonthVerTime());
		borrowMonth.preUpdate();
		int borrowMonthUpdate = borrowMonthDao.update(borrowMonth);
		if (borrowMonthUpdate == 0) {
			throw new ServiceException("月满盈债权池:"+FormatUtils.messageTemplates(borrowMonth.getLoanName(), borrowMonth.getLoanIdcard(), null,null));
		}
		checkManager.addCheck(toolView.getCreditMonableId(),
				"向月满盈债权池回池"+StringUtils.toDouble(toolView.getAfterReleaseBorrow())
				, "月满盈可用债权回池");
	}
	
	/**
	 * 更新提前结清的月满盈可用债权
	 * 2016年2月29日
	 * By 周俊
	 * @param tradeId
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void updateMatchingBorrowMonthable(SyncClaim loanSync){
		/*
		List<String> borrowMonthableIdList = borrowMonthableDao.getCreditMonableIdByJoin(creditValueId);
		for (String creditMonableId : borrowMonthableIdList) {
				BorrowMonthable borrowMonthable = new BorrowMonthable();
				borrowMonthable.setCreditMonableId(creditMonableId);
				borrowMonthable.setDictLoanFreeFlag(CreditState.DJ.value);
				borrowMonthable.preUpdate();
				borrowMonthableDao.update(borrowMonthable);
			}
		*/
		BorrowMonthable borrowMonthable = new BorrowMonthable();
		borrowMonthable.setLoanCode(loanSync.getLoanCode());
		borrowMonthable.setDictLoanFreeFlag(loanSync.getDictLoanFreeFlag());
		borrowMonthable.setLoanFreezeDay(loanSync.getLoanFreezeDay());
		borrowMonthable.setLoanModifiedDay(new Date());
		borrowMonthable.preUpdate();
		borrowMonthableDao.update(borrowMonthable);
		throw new ServiceException();
	}
	/*public void updateMatchingBorrowMonthable(String tradeId,String creditValueId){
		Map<String, Object> creditorTradeMap = new HashMap<String, Object>();
		String creditCode = null;
		if (!ObjectHelper.isEmpty(tradeId)) {
			creditorTradeMap.put("tradeId",tradeId);
			CreditorTrade creditorTrade = creditorTradeDao.get(creditorTradeMap);
			creditCode = creditorTrade.getCreditCode();	
		}else {
			creditCode = creditValueId;
		}
		List<String> borrowMonthableIdList = borrowMonthableDao.getCreditMonableIdByJoin(creditCode);
		for (String creditMonableId : borrowMonthableIdList) {
			creditorTradeMap.put("creditNode", Node.YMYKY);
			creditorTradeMap.put("creditCode", creditMonableId);
			creditorTradeMap.put("tradeId", null);
			CreditorTrade creditorTradeResult = creditorTradeDao.get(creditorTradeMap);
			if (!ObjectHelper.isEmpty(creditorTradeResult)) {
				BorrowMonthable borrowMonthable = new BorrowMonthable();
				borrowMonthable.setCreditMonableId(creditMonableId);
				borrowMonthable.setLoanAvailableDays((Integer)BorrowConstant.ZERO);
				borrowMonthable.setDictLoanFreeFlag(CreditState.DJ.value);
				borrowMonthable.setModifyBy(UserUtils.getUser().getName());
				borrowMonthable.setModifyTime(new Date());
				creditorTradeMap.put("status",CreditTradestate.JKTQDQGB.value);
				creditorTradeDao.updateStatus(creditorTradeMap);
				borrowMonthableDao.update(borrowMonthable);
				
			}else {
				BorrowMonthable borrowMonthable = new BorrowMonthable();
				borrowMonthable.setCreditMonableId(creditMonableId);
				borrowMonthable.setLoanAvailableDays((Integer)BorrowConstant.ZERO);
				borrowMonthable.setDictLoanFreeFlag(CreditState.DJ.value);
				borrowMonthable.setModifyBy(UserUtils.getUser().getName());
				borrowMonthable.setModifyTime(new Date());
				borrowMonthableDao.update(borrowMonthable);
			}
		}
	}*/
	/**
	 * 导Excel
	 * 2015年12月21日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public List<BorrowMonthableOutExcel> outExcel(BorrowMonthableView borrowMonthableView) {
		List<BorrowMonthableOutExcel> list = new ArrayList<BorrowMonthableOutExcel>();
		if(ArrayHelper.isNotEmpty(borrowMonthableView.getCreditMonableIdList())){
			List<BorrowMonthable> borrowMonthableList = getBorrowMonthablesByCreditMonableIds(borrowMonthableView.getCreditMonableIdList());
			for (BorrowMonthable borrowMonthable : borrowMonthableList) {
				BorrowMonthableOutExcel borrowMonthableOutExcel = new BorrowMonthableOutExcel();
				try {
					BeanUtils.copyProperties(borrowMonthableOutExcel,borrowMonthable);
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(borrowMonthableOutExcel);
			}
			return list;
		}
		Map<String, Object> map = objectToMap(borrowMonthableView);
		List<BorrowMonthable> outExcel = borrowMonthableDao.outExcel(map);
	      if (!ArrayHelper.isNotEmpty(outExcel)) {
			return list;
		}
	      for (BorrowMonthable borrowMonthable : outExcel) {
			BorrowMonthableOutExcel borrowMonthableOutExcel = new BorrowMonthableOutExcel();
			try {
				BeanUtils.copyProperties(borrowMonthableOutExcel,borrowMonthable);
				list.add(borrowMonthableOutExcel);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	      return list;
		
	}
	
	/**
	 * 获取满足匹配债权的月满盈数据
	 * 2015年12月29日
	 * By 柳慧
	 * @param borrowMonthable
	 * @return
	 */
	public List<BorrowMonthable> getPpBorrowMonthable(
			BorrowMonthable borrowMonthable) {
		return borrowMonthableDao.getPpBorrowMonthable(borrowMonthable);
	}
	
	/**
	 * 查询满足条件的的月满盈可用债权池集合信息
	 * @param page
	 * @param borrowMonthable
	 * @return
	 */
	public Page<BorrowMonthable> findBorrowMonthableDetail(
			Page<BorrowMonthable> page, BorrowMonthableView borrowMonthable) {
		String creditMonableIds = borrowMonthable.getCreditMonableIds();
		 if(!StringUtils.isEmpty(creditMonableIds)){
				String [] creditMonableIdsArry = creditMonableIds.split("-");
				List<String> tempcreditMonableIds = new ArrayList<String>();
				for(String creditMonableId : creditMonableIdsArry ){
					tempcreditMonableIds.add(creditMonableId);
				}
				borrowMonthable.setCreditMonableIdLs(tempcreditMonableIds);
		}
		 if(!StringUtils.isEmpty(borrowMonthable.getBorrowerJob())){
				String [] borrowJobArr = borrowMonthable.getBorrowerJob().split(",");
				List<String> borrowerJobLs = new ArrayList<String>();
				for(String borrowJob : borrowJobArr ){
					borrowerJobLs.add(borrowJob);
				}
				borrowMonthable.setBorrowerJobls(borrowerJobLs);
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("credit_monable_id");
        PageList<BorrowMonthable> pageList = (PageList<BorrowMonthable>)borrowMonthableDao.findBorrowMonthableDetail(borrowMonthable, pageBounds);
      for (BorrowMonthable borrowMonthable1 : pageList) {
			String loanIdcard = FormatUtils.formatLoanIdcard(borrowMonthable1.getLoanIdcard());
			borrowMonthable1.setLoanIdcard(loanIdcard);
		}
        PageUtil.convertPage(pageList, page);
		return page;
	

	}
	
	/**
	 * 通过债权ID集合 获取月满盈可用债权
	 * by 柳慧
	 * @param creditMonableIdsLst
	 * @return
	 */
	public List<BorrowMonthable> getBorrowMonthablesByCreditMonableIds(
			List<String> creditMonableIdsLst) {
		return borrowMonthableDao.getBorrowMonthablesByCreditMonableIds(creditMonableIdsLst);
	}

	/**
	 * 将对象转成map
	 * 2015年12月28日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public Map<String, Object> objectToMap(BorrowMonthableView borrowMonthableView){
		Map<String,Object> map = new HashMap<String,Object>();
		if (ObjectHelper.isEmpty(borrowMonthableView)) {
			return map;
		}
		map.put("borrowFreeFlag", CreditState.KY.value);// 债权状态
		map.put("borrowerName",borrowMonthableView.getBorrowerName());// 借款人
		if (StringHelper.isNotEmpty(borrowMonthableView.getBorrowerJob())) {
			
			map.put("borrowerJob",borrowMonthableView.getBorrowerJob().split(","));// 借款人职业
		}
		if (!ObjectHelper.isEmpty(borrowMonthableView.getBorrowCreditValueFrom())) {
			
			map.put("borrowCreditValueFrom",String.valueOf(borrowMonthableView.getBorrowCreditValueFrom()));// 借款金额 
		}
		if (!ObjectHelper.isEmpty(borrowMonthableView.getBorrowCreditValueTo())) {
			
			map.put("borrowCreditValueTo",String.valueOf(borrowMonthableView.getBorrowCreditValueTo()));// 借款金额 
		}
		if (!ObjectHelper.isEmpty(borrowMonthableView.getBorrowDaysSurplusFrom())) {
			
			map.put("borrowDaysSurplusFrom", String.valueOf(borrowMonthableView.getBorrowDaysSurplusFrom()));// 可用天数
		}
		if (!ObjectHelper.isEmpty(borrowMonthableView.getBorrowDaysSurplusTo())) {
			
			map.put("borrowDaysSurplusTo", String.valueOf(borrowMonthableView.getBorrowDaysSurplusTo()));// 可用天数
		}
		map.put("borrowCreditDateUsableFrom",borrowMonthableView.getBorrowCreditDateUsableFrom());// 债权可用日期
		map.put("borrowCreditDateUsableTo",borrowMonthableView.getBorrowCreditDateUsableTo());// 债权可用日期
		if (!ObjectHelper.isEmpty(borrowMonthableView.getBorrowMonthRate())) {
			map.put("borrowMonthRate",String.valueOf(borrowMonthableView.getBorrowMonthRate()));// 月利率
		}
		map.put("borrowBackmoneyFirdayFrom",borrowMonthableView.getBorrowBackmoneyFirdayFrom());// 首次还款日
		map.put("borrowBackmoneyFirdayTo",borrowMonthableView.getBorrowBackmoneyFirdayTo());// 首次还款日
		if (StringHelper.isNotEmpty(borrowMonthableView.getBorrowType())) {
			
			map.put("dictLoanType",borrowMonthableView.getBorrowType().split(","));// 借款类型
		}
		// 债权区分
		if(StringHelper.isNotEmpty(borrowMonthableView.getDicLoanDistinguish())){
			map.put("dicLoanDistinguish",borrowMonthableView.getDicLoanDistinguish().split(","));
		}
		map.put("ppxy", borrowMonthableView.getPpxy());
		return map;
	}

}
