package com.creditharmony.fortune.back.money.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackReason;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.dao.InterestRateConfigDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.InterestRateConfig;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 回款共通Service
 * @Class Name BmManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class BmManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BackMoneyListDao listDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private InterestRateConfigDao confDao;
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 获取列表页对象 
	 * 2015年12月3日 
	 * By 陈广鹏
	 * @param page
	 * @param view
	 * @return
	 */
	public Page<ListItemView> findItemList(Page<ListItemView> page,
			ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		String sortString = BmConstant.BACK_MONEY_DAY_ASC;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(), Order.formString(sortString));
		pageBounds.setCountBy("backm_id");
		PageList<ListItemView> dataList = (PageList<ListItemView>) listDao.findByParams(view, pageBounds);
		PageUtil.convertPage(dataList, page);
		view.setCity(city);
		return page;
	}
	
	/**
	 * 获取要操作的回款ID列表
	 * 2016年2月2日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public List<String> getBackmIdList(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		List<String> idList =  new ArrayList<String>();
		if (ObjectHelper.isEmpty(view.getBackmId())) {
			idList = listDao.findIdList(view);
		}else {
			String[] ids = view.getBackmId().split(",");
			idList = Arrays.asList(ids);
		}
		return idList;
	}
	
	/**
	 * 获取要操作的数据信息，包括backmId和verTime
	 * 2016年4月26日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public List<BackMoneyPool> getDataList(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		List<BackMoneyPool> dataList =  new ArrayList<BackMoneyPool>();
		if (StringUtils.isNotEmpty(view.getBackmId())) {
			String[] ids = view.getBackmId().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			view.setBackmIds(idList);
		}
		dataList = listDao.findDataList(view);
		return dataList;
	}
	
	/**
	 * 增加全程留痕
	 * 2016年1月22日
	 * By 陈广鹏
	 * @param pool
	 * @param log
	 */
	public void insertCheck(BackMoneyPool pool, BackMoneyLog log) {
		String dictBackStatus = pool.getDictBackStatus();
		String lendCode = pool.getLendCode();
		String str = BackState.backStateMap.get(dictBackStatus);
		if (YoN.FOU.value.equals(log.getCheckExaminetype())) {
			if (BackReason.QT.value.equals(log.getCheckExamine())) {
				str = str + "，原因：" + log.getCheckReason();
			}else {
				str = str + "，原因：" + BackReason.backReasonMap.get(log.getCheckExamine());
			}
		}
		String operatorInfo = "回款状态变更为[" + str + "]";
		String operatorType = "";
		String operateAffiliated = pool.getBackmId(); 
		FortuneLogNode operateNode = null;
		if (BackState.DHKSQQR.value.equals(dictBackStatus)) {
			operatorType = "回款申请";
			operateNode = FortuneLogNode.MONEY_APPLY;
		}else if (BackState.DHKSP.value.equals(dictBackStatus)
				||BackState.DHKSQQRTH.value.equals(dictBackStatus)) {
			operatorType = "回款申请确认";
			operateNode = FortuneLogNode.MONEY_APPLYCONFIRM;
		}else if (BackState.DHK.value.equals(dictBackStatus) 
				||BackState.DHKSPTH.value.equals(dictBackStatus)) {
			operatorType = "回款审批";
			operateNode = FortuneLogNode.MONEY_APPROVE;
		}else if (BackState.DHKQR.value.equals(dictBackStatus)
				||BackState.DHKTH.value.equals(dictBackStatus)) {
			operatorType = "执行回款";
			operateNode = FortuneLogNode.MONEY_EXCUTE;
		}else if (BackState.YHK.value.equals(dictBackStatus)
				||BackState.DHKQRTH.value.equals(dictBackStatus)) {
			operatorType = "回款确认";
			operateNode = FortuneLogNode.MONEY_CONFIRM;
		}else if (BackState.YHKTH.value.equals(dictBackStatus)) {
			operatorType = "回款退回";
			operateNode = FortuneLogNode.MONEY_HISTORY;
		}else if (BackState.HKBX.value.equals(dictBackStatus)) {
			operatorType = "回款申请确认";
			operateNode = FortuneLogNode.MONEY_APPLYCONFIRM;
		} else {
			operatorType = "提交";
		}
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, operateNode);
	}
	
	/**
	 * 获取符合条件的累计实际回款金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public BigDecimal getTotalAcutalbackmoney(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		BigDecimal totalAcutalbackmoney = listDao.getTotalAcutalbackmoney(view);
		if (null == totalAcutalbackmoney) {
			totalAcutalbackmoney = BigDecimal.ZERO;
		}
		view.setCity(city);
		return totalAcutalbackmoney;
	}

	/**
	 * 获取符合条件的累计出借金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public BigDecimal getTotalLendMoney(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		BigDecimal totallendMoney = listDao.getTotalLendMoney(view);
		if (null == totallendMoney) {
			totallendMoney = BigDecimal.ZERO;
		}
		view.setCity(city);
		return totallendMoney;
	}

	/**
	 * 获取符合条件的累计应回款金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public BigDecimal getTotalBackmoney(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		BigDecimal totalBackmoney = listDao.getTotalBackmoney(view);
		if (null == totalBackmoney) {
			totalBackmoney = BigDecimal.ZERO;
		}
		view.setCity(city);
		return totalBackmoney;
	}
	
	/**
	 * 获取符合条件的累计成功金额
	 * 2016年5月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public BigDecimal getTotalSuccessMoney(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		BigDecimal successMoney = listDao.getTotalSuccessMoney(view);
		if (null == successMoney) {
			successMoney = BigDecimal.ZERO;
		}
		view.setCity(city);
		return successMoney;
	}

	/**
	 * 获取符合条件的累计补息后回款金额
	 * 2016年11月7日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public BigDecimal getTotalSupplementedMoney(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		BigDecimal totalSupplementedMoney = listDao.getTotalSupplementedMoney(view);
		if (null == totalSupplementedMoney) {
			totalSupplementedMoney = BigDecimal.ZERO;
		}
		view.setCity(city);
		return totalSupplementedMoney;
	}

	/**
	 * 获取回款相关详情页信息
	 * 2016年4月13日
	 * By 陈广鹏
	 * @param backmId
	 * @return 
	 */
	public ItemView getDetail(String backmId) {
		return detailDao.get(backmId);
	}
	
	/**
	 * 检查出借是否正在被内转中，如果正在内转中，return true
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param lendCode
	 * @return boolean
	 */
	public boolean checkLendIsInTransfer(String lendCode){
		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		List<String> statusList = new ArrayList<String>();
		statusList.add(TransferState.YNZ.getKey());
		statusList.add(TransferState.YTH.getKey());
		statusList.add(TransferState.YQX.getKey());
		map.put("statusList", statusList);
		
		int count = detailDao.countTransferRelation(map);
		if (count>0) {
			result = true;
			BackMoneyPool pool = poolDao.getByLendCode(lendCode);
			if (BackType.TQSH.value.equals(pool.getBackMoneyType())) {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 判断 内转中 还是 自转中
	 * @author xurongsheng
	 * @date 2017年2月9日 下午4:53:52
	 * @param lendCode
	 * @return
	 */
	public boolean checkNZorZZ(String lendCode){
		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		List<String> statusList = new ArrayList<String>();
		statusList.add(TransferState.YNZ.getKey());
		statusList.add(TransferState.YTH.getKey());
		statusList.add(TransferState.YQX.getKey());
		map.put("statusList", statusList);
		
		Map<String, Object> resultMap = detailDao.lendPayTransferRelation(map);
		if(resultMap.get("apply_pay") != null && PayMent.ZZ.value.equals(resultMap.get("apply_pay").toString())){
			result = true;
		}
		return result;
	}
	
	/**
	 * 获取导出数据的数量
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public int countExport(ListItemView view){
		if (view == null) {
			view = new ListItemView();
		}
		String city = view.getCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		int result = 0;
		// 检索出列表
		String ids = view.getBackmId();
		if (null != ids && !"".equals(ids)) {
			// 有勾选
			String[] applys = ids.split(",");
			List<String> codes = new ArrayList<String>();
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			ListItemView itemView = new ListItemView();
			itemView.setBackmIds(codes);
			itemView.setIsJZH(view.getIsJZH());
			result = listDao.countExport(itemView);
		}else{
			// 无勾选
			result = listDao.countExport(view);
		}
		
		return result;
	}
	
	/**
	 * 获取客户上次回款的回款平台ID
	 * 2016年5月16日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public String getPreviousBackPlatform(String lendCode){
		// get客户编号
		LoanApply laSearch = new LoanApply();
		laSearch.setApplyCode(lendCode);
		LoanApply la = detailDao.getLoanApply(laSearch);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("customerCode", la.getCustCode());
		map.put("dictBackStatus", BackState.YHK.value);
		String exPlatform = detailDao.getPreviousBackPlatform(map);
		
		return exPlatform;
	}
	
	/**
	 * 获取补息年利率
	 * 2016年11月9日
	 * By 陈广鹏
	 * @return
	 */
	public BigDecimal getSupplementRate(ListItemView view){
		if (ObjectHelper.isEmpty(view.getProductCode())) {
			throw new ServiceException("产品编号不得为空");
		}
		if (ObjectHelper.isEmpty(view.getApplyLendDay())) {
			throw new ServiceException("出借日期不得为空");
		}
		if (ObjectHelper.isEmpty(view.getApplyLendMoney())) {
			throw new ServiceException("出借金额不得为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCode", view.getProductCode());
		map.put("applyLendDay", view.getApplyLendDay());
		map.put("applyLendMoney", view.getApplyLendMoney());
		map.put("xinhebaoType", view.getXinhebaoType());
		InterestRateConfig conf = confDao.getProperRate(map);
		if (conf != null) {
			return conf.getProfitRate();
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 获取补息数据
	 * 2016年11月10日
	 * By 陈广鹏
	 * @param seachView
	 * @return
	 */
	public List<ListItemView> getSuplementList(ListItemView seachView) {
		
		// 设置城市搜索条件
		String city = seachView.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			seachView.setCity(c);
		}
		
		if (StringUtils.isNotEmpty(seachView.getBackmId())) {
			String[] ids = seachView.getBackmId().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			seachView.setBackmIds(idList);
		}
				
		List<ListItemView> suplementList = listDao.getSuplementList(seachView);
		return suplementList;
	}
	
	/**
	 * 修改在职状态
	 * 2016年12月22日
	 * 郭强
	 * @param request
	 * @param response
	 * @param list
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String updateWorkingState(ListItemView  list) {
		String str="";
		int  count = poolDao.updateWorkingState(list);
		if(count==0){
			str="没有可更新的数据";
		}else{
			str="操作成功";
		}
		return str;
	}

	/**
	 * 获取最大回款日期 
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public Date getMaxBackMoneyDay(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		if (StringUtils.isNotEmpty(view.getBackmId())) {
			String[] ids = view.getBackmId().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			view.setBackmIds(idList);
		}
		
		Date maxBackMoneyDay = listDao.getMaxBackMoneyDay(view);
		view.setCity(city);
		return maxBackMoneyDay;
	}
	
	/**
	 * 获取数据不同回款日期的天数
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public int getDiffBackMoneyDays(ListItemView view) {
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		if (StringUtils.isNotEmpty(view.getBackmId())) {
			String[] ids = view.getBackmId().split(",");
			List<String> idList = new ArrayList<String>();
			idList = Arrays.asList(ids);
			view.setBackmIds(idList);
		}
		int days = listDao.getDiffBackMoneyDays(view);
		view.setCity(city);
		return days;
	}
	
	/**
	 * 判断数据的回款日期是否为同一天
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public boolean dataInSameBackMoneyDay(ListItemView view){
		// 设置城市搜索条件
		String city = view.getCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		int days = getDiffBackMoneyDays(view);
		view.setCity(city);
		return days==1;
	}
}
