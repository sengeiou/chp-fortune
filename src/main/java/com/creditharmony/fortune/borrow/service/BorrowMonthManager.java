package com.creditharmony.fortune.borrow.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.BigDecimalTools;
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
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowAllotView;
import com.creditharmony.fortune.borrow.view.BorrowMonthBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthSplitView;
import com.creditharmony.fortune.borrow.view.BorrowMonthView;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.BorrowConstant;

/**
 * 月满盈债权池
 * @Class Name BorrowMonthManager
 * @author 周俊
 * @Create In 2015年12月14日
 */
@Service
public class BorrowMonthManager extends CoreManager<BorrowMonthDao,BorrowMonth>{

	// 获得拆分利率
	private BigDecimal splitRate = BigDecimal.valueOf(BorrowConstant.SPLIT_RATE);
	@Autowired
	private BorrowMonthDao borrowMonthDao;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private CreditorHisManager creditorHisManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private BorrowMonthableManager borrowMonthableManager;
	@Autowired
	private BorrowMonthableDao borrowMonthableDao;
	
	/**
	 * 信借 批量分配并按月利率拆分可用债权
	 * 2016年4月23日
	 * By 周俊
	 * @param borrow
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void borrowAllotAndSplit(Borrow borrow){
		BorrowMonth borrowMonth = new BorrowMonth();
		borrowMonth.setCreditValueId(borrow.getCreditValueId());
		borrowMonth = borrowMonthDao.get(borrowMonth);
		if (!ObjectHelper.isEmpty(borrowMonth)) {
			borrowMonth.setLoanCreditValue(BigDecimalTools.add(borrowMonth.getLoanCreditValue(),borrow.getLoanCreditValue()));
			borrowMonth.setVerTime(borrow.getBorrowMonthVerTime());
			borrowMonth.preUpdate();
			int borrowMonthUpdate = borrowMonthDao.update(borrowMonth);
			if(borrowMonthUpdate == 0){
				throw new ServiceException("月满盈债权池:"+FormatUtils.messageTemplates(borrowMonth.getLoanName(), borrowMonth.getLoanIdcard(),null,null));
			}
		}else{
			// 不存在插入数据
			borrowMonth = new BorrowMonth();
			BeanUtils.copyProperties(borrow, borrowMonth);
			borrowMonth.setCreditMonId(IdGen.uuid());
			// 债权可用日期
			Date borrowCreditDateUsable = ReckonUtils.getBorrowCreditDateUsable(borrow.getLoanBackmoneyFirday());
			borrowMonth.setLoanCreditDayUsable(borrowCreditDateUsable);
			// 借款天数 = 末期还款日期 – 债权可用日期 + 1
			Date loanBackmoneyLastday = borrow.getLoanBackmoneyLastday();
			if (ObjectHelper.isEmpty(loanBackmoneyLastday)) {
				throw new ServiceException("请核查所拆分的债权信息");
			}
			Integer loanDay = (int) (DateUtils.getDistanceOfTwoDate(borrowCreditDateUsable,loanBackmoneyLastday)+1);
			borrowMonth.setLoanDay(loanDay);
		    //可用天数 = 末期还款日期 – 系统当前日期 + 1
			Integer loanAvailableDays = (int) (DateUtils.getDistanceOfTwoDate(new Date(),loanBackmoneyLastday)+1);
			borrowMonth.setLoanAvailableDays(loanAvailableDays);
			borrowMonth.setLoanAvailabeValue(BigDecimal.valueOf(0));
			borrowMonth.setLoanEndmoneyDay(loanBackmoneyLastday);
			//borrowMonth.setLoanValueYear(borrow.getLoanMonthRate().multiply(new BigDecimal(12)));
			borrowMonth.setLoanValueYear(borrow.getLoanValueYear());
			borrowMonth.preInsert();
			borrowMonthDao.insert(borrowMonth);
		}
		// 向月满盈可用债权中拆分债权
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
		borrowMonthable.setLoanMonthRate(borrow.getLoanMonthRate());
		// 获得首次还款日
		Date borrowBackmoneyFirday = borrowMonthable.getLoanBackmoneyFirday();
		// 债权可用日期
		Date borrowCreditDateUsable = ReckonUtils.getBorrowCreditDateUsable(borrowBackmoneyFirday);
		borrowMonthable.setLoanCreditDayUsable(borrowCreditDateUsable);
		// 借款天数 = 末期还款日期 – 债权可用日期 + 1
		Date date = borrowMonthable.getLoanBackmoneyDay();
		if (ObjectHelper.isEmpty(date)) {
			date=new Date();
		}
		Integer loanDay = (int) (DateUtils.getDistanceOfTwoDate(borrowCreditDateUsable,date)+1);
		borrowMonthable.setLoanDay(loanDay);
	    //可用天数 = 末期还款日期 – 系统当前日期 + 1
		Integer loanAvailableDays = (int) (DateUtils.getDistanceOfTwoDate(new Date(),date)+1);
		borrowMonthable.setLoanAvailableDays(loanAvailableDays);
		// 将拆分金额添加到月满盈可用债权列表的原始债权价值
		BigDecimal spiltMoney = borrow.getLoanCreditValue().multiply(borrow.getLoanMonthRate()).divide(borrow.getLoanMonthRate(), 5, RoundingMode.HALF_DOWN);
		borrowMonthable.setLoanCreditValue(spiltMoney);
		borrowMonthable.setLoanAvailabeValue(spiltMoney);
		//borrowMonthable.setLoanValueYear(borrowMonthable.getLoanMonthRate().multiply(new BigDecimal(12)));
		borrowMonthable.setLoanValueYear(borrowMonth.getLoanValueYear());
		borrowMonthable.setLoanMiddleMan(borrowMonth.getLoanMiddleMan());
		borrowMonthable.setDictLoanFreeFlag(borrowMonth.getDictLoanFreeFlag());
		borrowMonthable.setLoanModifiedDay(new Date());
		borrowMonthable.setTrusteeFlag(borrowMonth.getLoanTrusteeFlag());
		borrowMonthable.setDicLoanDistinguish(borrowMonth.getDicLoanDistinguish());
		borrowMonthable.preInsert();
		borrowMonthableDao.insert(borrowMonthable);
		// 保存拆分记录
		BorrowMonthSplitView splitView = new BorrowMonthSplitView();
		splitView.setCreditMonId(borrowMonth.getCreditMonId());
		splitView.setLoanAvailabeValue(BigDecimalTools.add(borrowMonth.getLoanAvailabeValue(),borrow.getLoanCreditValue()));
		splitView.setSplitMoney(spiltMoney);
		splitView.setSurplusSplitMoney(borrowMonth.getLoanAvailabeValue());
		creditorHisManager.saveBorrowMonthSplitLog(splitView);
		// 保存分配记录
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setNodeId(borrow.getCreditValueId());
		creditorHis.setBeforMoney(borrow.getLoanCreditValue());
		creditorHis.setOperateMoney(borrow.getLoanCreditValue());
		creditorHis.setAfterMoney(creditorHis.getBeforMoney().subtract(creditorHis.getOperateMoney()));
		creditorHisManager.saveBorrowAllotLog(creditorHis);
		// 修改可用债权
		borrow.setLoanCreditValue(BigDecimal.valueOf(0));
		borrow.preUpdate();
		int borrowUpdate = borrowDao.update(borrow);
		if (borrowUpdate == 0) {
			throw new ServiceException("可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(), borrow.getLoanIdcard(), null,null));
		}
	}
	
	/**
	 * 单条分配数据的保存操作
	 * 2016年4月20日
	 * By 周俊
	 * @param borrowAllotView
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveBorrowAllot(BorrowAllotView borrowAllotView){
		Borrow borrow = borrowDao.get(borrowAllotView.getCreditValueId());
		if (StringUtils.isNotBlank(borrowAllotView.getCreditMonId())) {
			BorrowMonth borrowMonth = new BorrowMonth();
			borrowMonth.setCreditValueId(borrowAllotView.getCreditValueId());
			borrowMonth = borrowMonthDao.get(borrowMonth);
			// 存在就修改月满盈原始债权价值,可用债权价值
			borrowMonth.setLoanCreditValue(borrowMonth.getLoanCreditValue().add(borrowAllotView.getAllotMoney()));
			borrowMonth.setLoanAvailabeValue(borrowMonth.getLoanAvailabeValue().add(borrowAllotView.getAllotMoney()));
			borrowMonth.preUpdate();
			borrowMonth.setVerTime(borrowAllotView.getBorrowMonthVerTime());
			int update = borrowMonthDao.update(borrowMonth);
			if(update==0){
				throw new ServiceException("月满盈债权池:"+FormatUtils.messageTemplates(borrowMonth.getLoanName(), borrowMonth.getLoanIdcard(),null,null));
			}
		}else{
			BorrowMonth borrowMonth = new BorrowMonth();
			// 不存在插入数据
			if (borrow.getLoanValueYear()==null) {
				borrow.setLoanValueYear(new BigDecimal(0));
			}
			BeanUtils.copyProperties(borrow,borrowMonth);
			borrowMonth.setCreditMonId(IdGen.uuid());
			borrowMonth.setLoanCreditValue(borrowAllotView.getAllotMoney());
			borrowMonth.setLoanAvailabeValue(borrowAllotView.getAllotMoney());
			borrowMonth.setLoanEndmoneyDay(borrow.getLoanBackmoneyLastday());
			if(borrow.getDictLoanType().equals(CreditSrc.XJ.value)){
				borrowMonth.setLoanValueYear(borrow.getLoanValueYear());
			}else{
				borrowMonth.setLoanValueYear(borrow.getLoanMonthRate().multiply(new BigDecimal(12)));
			}
			borrowMonth.preInsert();
			borrowMonthDao.insert(borrowMonth);
		}
		// 保存分配记录
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setNodeId(borrowAllotView.getCreditValueId());
		creditorHis.setBeforMoney(borrowAllotView.getBorrowCreditValue());
		creditorHis.setOperateMoney(borrowAllotView.getAllotMoney());
		creditorHis.setAfterMoney(borrowAllotView.getSurplusMoney());
		creditorHisManager.saveBorrowAllotLog(creditorHis);
		// 修改可用债权
		borrow.setLoanCreditValue(borrowAllotView.getSurplusMoney());
		borrow.preUpdate();
		borrow.setVerTime(borrowAllotView.getBorrowVerTime());
		int update = borrowDao.update(borrow);
		if (update == 0) {
			throw new ServiceException("可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(), borrow.getLoanIdcard(), null,null));
		}
	}
	
	/**
	 * 获得月满盈债权列表
	 * 2016年1月13日
	 * By 周俊
	 * @param page
	 * @param borrowMonthView
	 * @return
	 */
	public Page<BorrowMonth> findBorrowMonth(Page<BorrowMonth> page,BorrowMonthView borrowMonthView) {
		  Map<String, Object> map = objectToMap(borrowMonthView);
		  PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		  pageBounds.setCountBy("credit_mon_id");
		  PageList<BorrowMonth> pageList = borrowMonthDao.findBorrowMonth(map, pageBounds);
		  for (BorrowMonth borrowMonth : pageList) {
			if (ObjectHelper.isEmpty(borrowMonth.getLoanMonthRate())) {
				borrowMonth.setLoanValueYear(new BigDecimal(0));
			}//else{
				//borrowMonth.setLoanValueYear(BigDecimalTools.mul(borrowMonth.getLoanMonthRate(),new BigDecimal(12)));
			//}
		}
	      PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查询总金额
	 * @param borrowMonth
	 * @return
	 */
	public BigDecimal findAllMoney(BorrowMonthView borrowMonthView){
		Map<String, Object> map = objectToMap(borrowMonthView);
		BigDecimal allMoney = borrowMonthDao.findAllMoney(map);
		return allMoney;
	}
	
	/**
	 * 查询月满盈债权信息
	 * 2016年1月6日
	 * By 周俊
	 * @param creditMonId
	 * @return
	 */
	public BorrowMonth findBorrowMonth(String creditMonId){
		BorrowMonth borrowMonthParam = new BorrowMonth();
		borrowMonthParam.setCreditMonId(creditMonId);
		BorrowMonth borrowMonth = borrowMonthDao.get(borrowMonthParam);
		borrowMonth.setLoanIdcard(FormatUtils.formatLoanIdcard(borrowMonth.getLoanIdcard()));
		return borrowMonth;
	}
	
	/**
	 * 月满盈债权的拆分
	 * 2015年12月5日
	 * By 周俊
	 * @param creditMonId
	 * @return
	 */
	public BorrowMonthSplitView findBorrowMonthSplitView(String creditMonId,BorrowMonthSplitView borrowMonthSplitViewParam){
		BorrowMonth borrowMonth = findBorrowMonth(creditMonId);
		BigDecimal splitRate = null;
		if (ObjectHelper.isEmpty(borrowMonthSplitViewParam)) {
			splitRate = this.splitRate;
		}
		BorrowMonthSplitView borrowMonthSplitView = ReckonUtils.splitBorrowMonthReckon(borrowMonth ,splitRate, borrowMonthSplitViewParam);
		borrowMonthSplitView.setVerTime(borrowMonth.getVerTime());
		return borrowMonthSplitView;
	} 
	
	/**
	 * 离焦事件计算拆分信息
	 * 2016年1月14日
	 * By 周俊
	 * @param borrowMonthSplitView
	 * @return
	 *//*
	@Transactional(readOnly = true)
	public BorrowMonthSplitView borrowMonthSplitReckonByBlur(BorrowMonthSplitView borrowMonthSplitView){
		BorrowMonth borrowMonth = findBorrowMonth(borrowMonthSplitView.getCreditMonId());
		BigDecimal splitRate = null;
		if (ObjectHelper.isEmpty(borrowMonthSplitViewParam)) {
			splitRate = this.splitRate;
		}
		BorrowMonthSplitView borrowMonthSplitView = ReckonUtils.splitBorrowMonthReckon(borrowMonth ,splitRate, borrowMonthSplitViewParam);
	}*/
	
	/**
	 * 获取回池金额
	 * 2015年12月5日
	 * By 周俊
	 * @param creditMonId
	 * @return
	 */
	public BorrowMonthBackToolView findBorrowMonthBackTool(String creditMonId){
		 /*BorrowMonth borrowMonth = findBorrowMonth(creditMonId);
		 BorrowMonthBackToolView borrowMonthBackToolView = new BorrowMonthBackToolView();
		 borrowMonthBackToolView.setCreditValueId(borrowMonth.getCreditValueId());
		 borrowMonthBackToolView.setCreditMonId(creditMonId);
		 borrowMonthBackToolView.setLoanAvailabeValue(borrowMonth.getLoanAvailabeValue());
		 borrowMonthBackToolView.setVerTime(borrowMonth.getVerTime());*/
		BorrowMonthBackToolView borrowMonthBackToolView = borrowMonthDao.findBorrowMonthBackTool(creditMonId);
		return borrowMonthBackToolView;
	}
	
	/**
	 * 月满盈债权回池
	 * 2015年12月2日
	 * By 周俊
	 * @param backTool
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void borrowMonthBackTool(BorrowMonthBackToolView backTool){
		// 修改月满盈债权的可用债权价值,分配金额
		BorrowMonth borrowMonth = findBorrowMonth(backTool.getCreditMonId());
		borrowMonth.setLoanCreditValue(borrowMonth.getLoanCreditValue().subtract(backTool.getBackToolMoney()));
		borrowMonth.setLoanAvailabeValue(backTool.getSurplusBorrowCreditValue());
		borrowMonth.setVerTime(backTool.getBorrowMonthVerTime());
		borrowMonth.preUpdate();
		int borrowMonthUpdate = borrowMonthDao.update(borrowMonth);
		if(borrowMonthUpdate == 0){
			throw new ServiceException("月满盈债权池:"+FormatUtils.messageTemplates(borrowMonth.getLoanName(), borrowMonth.getLoanIdcard(), null, null));
		}
		// 修改可用债权的可用债权价值
		Borrow borrow = new Borrow();
		// 获得可用债权
		String creditValueId = backTool.getCreditValueId();
	    borrow = borrowDao.get(creditValueId);
		BigDecimal backToolMoney = backTool.getBackToolMoney();
		if (ObjectHelper.isEmpty(backToolMoney)) {
			backToolMoney = new BigDecimal(0);
		}
		borrow.setLoanCreditValue(borrow.getLoanCreditValue().add(backToolMoney));
		borrow.setVerTime(backTool.getBorrowVerTime());
		borrow.preUpdate();
		int borrowUpdate = borrowDao.update(borrow);
		if(borrowUpdate == 0){
			throw new ServiceException("可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(), borrow.getLoanIdcard(), null, null));
		}
		// 保存月满盈债权回池记录
		creditorHisManager.saveBorrowBackTool(backTool);
	}
	
	/**
	 * 修改提前结清月满盈债权
	 * 2016年3月1日
	 * By 周俊
	 * @param creditValueId
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void updateFreezeBorrowMonth(SyncClaim loanSync){
		BorrowMonth borrowMonth = new BorrowMonth();
		borrowMonth.setLoanCode(loanSync.getLoanCode());
		borrowMonth.setDictLoanFreeFlag(loanSync.getDictLoanFreeFlag());
		borrowMonth.setLoanModifiedDay(new Date());
		borrowMonth.setLoanFreezeDay(loanSync.getLoanFreezeDay());
		borrowMonth.preUpdate();
		borrowMonthDao.update(borrowMonth);
	}
	
	/**
	 * 导出Excel
	 * 2016年2月16日
	 * By 周俊
	 * @param borrowMonthView
	 * @return
	 */
	/*public List<BorrowMonthOutExcel> outExcel(BorrowMonthView borrowMonthView) {
		List<BorrowMonthOutExcel> list = new ArrayList<BorrowMonthOutExcel>();
		Map<String, Object> map = objectToMap(borrowMonthView);
	      List<BorrowMonthOutExcel> outExcel = borrowMonthDao.outExcel(map);
	      if (!ArrayHelper.isNotEmpty(outExcel)) {
			return list;
		}
	      for (BorrowMonth borrowMonth : outExcel) {
			BorrowMonthOutExcel borrowMonthOutExcel = new BorrowMonthOutExcel();
			try {
				//BeanUtils.copyProperties(borrowMonthOutExcel,borrowMonth);
				borrowMonthOutExcel.setLoanName(borrowMonth.getLoanName());
				
				borrowMonthOutExcel.setLoanMonths(StringUtils.toString(borrowMonth.getLoanMonths()));
				borrowMonthOutExcel.setLoanMonthsSurplus(StringUtils.toString(borrowMonth.getLoanMonthsSurplus()));
				borrowMonthOutExcel.setLoanBakcmoneyDay(StringUtils.toString(borrowMonth.getLoanBackmoneyDay()));
				list.add(borrowMonthOutExcel);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	      return outExcel;
	}*/
	
	/**
	 * 将对象转成map
	 * 2015年12月28日
	 * By 周俊
	 * @param borrowMonthableView
	 * @return
	 */
	public Map<String, Object> objectToMap(BorrowMonthView borrowMonthView){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("borrowFreeFlag", CreditState.KY.value);// 债权状态
		map.put("borrowerName",borrowMonthView.getBorrowerName());// 借款人
		if (StringHelper.isNotEmpty(borrowMonthView.getBorrowerJob())) {
			
			map.put("borrowerJob",borrowMonthView.getBorrowerJob().split(","));// 借款人职业
		}
		if (!ObjectHelper.isEmpty(borrowMonthView.getBorrowAvailabeValueFrom())) {
			
			map.put("borrowAvailabeValueFrom",String.valueOf(borrowMonthView.getBorrowAvailabeValueFrom()));// 可用金额 
		}
		if (!ObjectHelper.isEmpty(borrowMonthView.getBorrowAvailabeValueTo())) {
			
			map.put("borrowAvailabeValueTo",String.valueOf(borrowMonthView.getBorrowAvailabeValueTo()));// 可用金额 
		}
		map.put("borrowDaysSurplusFrom", borrowMonthView.getBorrowDaysSurplusFrom());// 可用期数
		map.put("borrowDaysSurplusTo", borrowMonthView.getBorrowDaysSurplusTo());// 可用期数数
		if (!ObjectHelper.isEmpty(borrowMonthView.getBorrowMonthRate())) {
			
			map.put("borrowMonthRate",String.valueOf(borrowMonthView.getBorrowMonthRate()));// 月利率
		}
		map.put("borrowBackmoneyFirdayFrom",borrowMonthView.getBorrowBackmoneyFirdayFrom());// 首次还款日
		map.put("borrowBackmoneyFirdayTo",borrowMonthView.getBorrowBackmoneyFirdayTo());// 首次还款日
		if (StringHelper.isNotEmpty(borrowMonthView.getBorrowTrusteeFlag())) {
			
			map.put("borrowTrusteeFlag",borrowMonthView.getBorrowTrusteeFlag().split(","));// 债权标识
		}
		// 债权区分
		if(StringHelper.isNotEmpty(borrowMonthView.getDicLoanDistinguish())){
			map.put("dicLoanDistinguish",borrowMonthView.getDicLoanDistinguish().split(","));
		}
		return map;
	}
}
