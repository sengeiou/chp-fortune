package com.creditharmony.fortune.look.apply.manager;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.fortune.type.ApplyStatus;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.BaseDeptOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.priority.common.service.PriorityLogManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.look.apply.dao.LendApplyLookDao;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportDecutExcelEx;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportLendExcelEx;
import com.creditharmony.fortune.look.apply.entity.LendSearchObj;
import com.creditharmony.fortune.look.apply.view.LendApplyLookListView;
import com.creditharmony.fortune.look.apply.view.LendLookListView;
import com.creditharmony.fortune.look.apply.view.LendLookPageView;
import com.creditharmony.fortune.look.apply.view.PriorityResultView;
import com.creditharmony.fortune.look.approve.constants.ApproveLendStateConstant;
import com.creditharmony.fortune.look.util.LendLookUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 出借申请查看
 * @Class Name LendApplyLookManager 
 * @author 李志伟
 * @Create In 2015年12月24日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class LendApplyLookManager{
	private Logger logger = LoggerFactory.getLogger(LendApplyLookManager.class);
	
	@Autowired
	private LendApplyLookDao lendApplyLookDao;
	@Autowired
	private LoanApplyDao loanApplyDao;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private CheckManager  checkmanager;
	@Autowired 
	private PriorityLogManager priorityLogManager;
	
	/**
	 * 出借申请查看列表检索
	 * 2015年12月26日
	 * by 李志伟
	 * @param page
	 * @param lso
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public LendLookPageView loadLendApplyLookList(HttpServletRequest request, HttpServletResponse response, LendSearchObj lso) {
		
		LendLookPageView llov = new LendLookPageView();
		Page<LendApplyLookListView> page = new Page<LendApplyLookListView>(request, response);
		page.setFuncName("submitSql");
		Map<String, Object> map = new HashMap<String, Object>();
		
		//城市
		String city = lso.getCityId();
		if (StringUtils.isNotBlank(city)) {
			String c = "%" + city.replace(",", "%|%") + "%";
			lso.setCityId(c);	
		}
		LendLookUtil.getConditions(map, lso); //设置查询条件
		
		//获取数据权限
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		logger.info("加载出借申请查看列表，所赋予权限是：" + dataRights);
		if(StringUtils.isNotEmpty(dataRights)){
			lso.setDataRights(dataRights);
		}
		lso.setDefaultOrderSql(CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("customer_code");
		
		PageList<LendApplyLookListView> pageList  = (PageList<LendApplyLookListView>) lendApplyLookDao.loadLendApplyLookList(map, pageBounds);
		lso.setCityId(city);
		if (null != pageList) {
			
			for (LendApplyLookListView lalv : pageList) {
				
				Date lendDate = null;
				Date currentDate = new Date();
				double interval = 0;
				int lendDay = 0;
				
				lendDate = lalv.getApplyLendDay();
				if(null == lalv.getApplyExpireDay()){
					// 到期日为空，出借天数为空
					lalv.setApplyLendDayNum(null);
					continue;
				}
				if( !LendState.HKCG.value.equals(String.valueOf(lalv.getLendStatus()))){
					// 出借状态不是划扣成功的，出借天数为空
					lalv.setApplyLendDayNum(null);
					continue;
				}
				if(DateUtils.dateAfter(new Date(),lalv.getApplyExpireDay())){
					currentDate = lalv.getApplyExpireDay();
				}
				interval = DateUtils.getDistanceOfTwoDate(lendDate,
						currentDate);
				//出借天数
				if (interval <= 0 || !lalv.getLendStatus().equals(LendState.HKCG.value)) {
					lendDay = 0;
					lalv.setApplyLendDayNum(lendDay);
				} else {
					lendDay = Double.valueOf(interval + "").intValue();
					lalv.setApplyLendDayNum(lendDay+1);
				}
			}
		}
		PageUtil.convertPage(pageList, page);

		User currentUser = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		String orgId = currentUser.getDepartment().getId();
		Org org = orgDao.get(orgId);
		if (!BaseDeptOrgType.LENDER_DEPT.key.equals(org != null ? org.getType() : "")) {
			djrSwitchIsShow(page.getList());
		}
		switchPriorityState(page.getList());
		llov.setPage(page);
		llov.setLso(lso);
		llov.setSum(lendApplyLookDao.sumLendAmount(map, pageBounds));

		return llov;
	}

	//判断优先回款按钮是否显示
	private void switchPriorityState(List<LendApplyLookListView> list) {
		if (ArrayHelper.isNotEmpty(list)) {
			for (LendApplyLookListView loanApply : list) {
				try {
					// 只有划扣成功&未完结
					if (LendState.HKCG.value.equals(loanApply.getLendStatus()) && FinishState.WWJ.value.equals(loanApply.getDictApplyEndState())) {
						//【到期日期】是系统当前日期之前（包含系统当期日期）
						if(DateUtils.dateAfter(loanApply.getApplyExpireDay(),new Date())){
							continue;
						}
						// 【回款类型】是“到期回款”、“赎回内转”
						boolean  backTypelogic =  BackType.DQHK.value.equals(loanApply.getBackMoneyType()) ||
												  BackType.SHNZ.value.equals(loanApply.getBackMoneyType());
						//【回款状态】是“待回款申请”、“待回款申请确认退回”
						boolean  backStatelogic = BackState.DHKSQ.value.equals(loanApply.getDictBackStatus()) ||
												  BackState.DHKSQQRTH.value.equals(loanApply.getDictBackStatus());
						boolean  applyStatus= ApplyStatus.TQSH.value.equals(loanApply.getStatus());
						if (backTypelogic && backStatelogic && !applyStatus) {
							/**
							 *控制优先回款按钮的可用性
							 *转投大金融状态为  待审批，审批成功，转投成功  优先回款按钮不可用
							 *审批过程中的；审批未通过，且未撤销的；审批通过的；
							 */
							if((FortuneSwitchApproveStatus.DSP.value.equals(loanApply.getSwitchApproveStatus()) || 
							   FortuneSwitchApproveStatus.SPCG.value.equals(loanApply.getSwitchApproveStatus()) ||
							   FortuneSwitchApproveStatus.ZTCG.value.equals(loanApply.getSwitchApproveStatus())) 
							   && (loanApply.getSwitchApproveStatus() != null && loanApply.getSwitchApproveStatus() !="")){
								//不显示按钮
								continue;
							}else if(PriorityBackState.DSP.value.equals(loanApply.getPriorityBackState()) ||
									 PriorityBackState.SPTG.value.equals(loanApply.getPriorityBackState()) ||
									 PriorityBackState.SPWTG.value.equals(loanApply.getPriorityBackState()) ){
								
								loanApply.setSwitchPriorityState("2");
							}else if(checkLendIsInTransfer(loanApply.getLendCode())){
								//不显示按钮(内转和自转中不可以进行优先回款的操作)
								continue;
							}else{
								loanApply.setSwitchPriorityState("1");
							}
						}
					}
				} catch (Exception e) {
					logger.error("优先回款按钮显示判断出错!出借编号为："+loanApply.getLendCode(), e);
				}
			}
		}
	}
	
	
	private void djrSwitchIsShow(List<LendApplyLookListView> list) {
		if (ArrayHelper.isNotEmpty(list)) {
			for (LendApplyLookListView loanApply : list) {
				try {
					// 只有划扣成功&未完结
					if (LendState.HKCG.value.equals(loanApply.getLendStatus()) && FinishState.WWJ.value.equals(loanApply.getDictApplyEndState())) {
						// 不是提前赎回&没有流转到“待回款申请确认列表”后的所有状态
						if (!BackType.TQSH.value.equals(loanApply.getStatus()) && dhkFlag(loanApply.getDictBackStatus())) {
							//优先回款状态(已撤销的显示转投大金融的按钮)
							if((loanApply.getPriorityBackState() != null && loanApply.getPriorityBackState() != "")){
								if(!PriorityBackState.YCX.value.equals(loanApply.getPriorityBackState())){
									continue;
								}
							}
							// 未内转&到期前二十天生成转投大金融按钮,到期日当天按钮消失
							// 增加转投限制2017年3月31之前包括31号到期数据一直有按钮（不变）
							int count = transferRelationDao.getTransCount(loanApply.getLendCode());
							if (count == 0) {
								Date expireDate = loanApply.getApplyExpireDay();
								String switchApproveStatus = loanApply.getSwitchApproveStatus();
								if (expireDate != null) {
									if (switchApproveStatusValidation("1", expireDate, switchApproveStatus)) {
										loanApply.setDjrSwitchFlag("1");
									} else {
										if (switchApproveStatusValidation("2", null, switchApproveStatus)) {
											Date beforeDate = addDay(-20, expireDate);
											Date today = DateUtils.parseDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
											if ((today.getTime() >= beforeDate.getTime() && today.getTime() < expireDate.getTime())) {
												loanApply.setDjrSwitchFlag("1");
											}
										}
									}
								} else if (switchApproveStatusValidation("2", null, switchApproveStatus)) {
									loanApply.setDjrSwitchFlag("1");
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("转投大金融显示判断出错!", e);
				}
			}
		}
	}

	private boolean switchApproveStatusValidation(String type, Date expireDate, String switchApproveStatus) throws ParseException {
		if ("1".equals(type)) {
			Date date = DateUtils.parseDate("2017-06-30", "yyyy-MM-dd");
			return expireDate.getTime() <= date.getTime() && (FortuneSwitchApproveStatus.WZT.value.equals(switchApproveStatus) || switchApproveStatus == null || FortuneSwitchApproveStatus.SPSB.value.equals(switchApproveStatus) || FortuneSwitchApproveStatus.CXZT.value.equals(switchApproveStatus));
		}
		return FortuneSwitchApproveStatus.WZT.value.equals(switchApproveStatus) || switchApproveStatus == null || FortuneSwitchApproveStatus.SPSB.value.equals(switchApproveStatus) || FortuneSwitchApproveStatus.CXZT.value.equals(switchApproveStatus);
	}

	private boolean dhkFlag(String state) {
		if (StringUtils.isEmpty(state)) {
			return true;
		}
		List<String> list = new ArrayList<String>();
		BackState[] values = BackState.values();
		for (BackState backState : values) {
			String val = backState.value;
			if (!BackState.DHKSQ.value.equals(val) && !BackState.DHKSQQRTH.value.equals(val)) {
				list.add(val);
			}
		}
		return !list.contains(state);
	}

	private Date addDay(int n, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}

	/**
	 * 查找提前赎回信息
	 * 2016年3月1日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LendLookListView> findRedeemLendMesg(Map<String, Object> map) {
		
		String str = (String) map.get("code");
		String[] spt = str.split("-");
		str = spt[0];
		map.put("str", str);
		List<LendLookListView> redeemLendlist = lendApplyLookDao.findRedeemLendMesg(map);
		return redeemLendlist;
	}

	/**
	 * 查询出借信息，用于导出出借excel
	 * 2016年3月17日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LendApplyLookExportLendExcelEx> getExportLendExcelList(Map<String, Object> exportListParam, Page page, Map<String, Map<String, String>> catchMap) {
		
		String dataRights = DataScopeUtil.getDataScope("app", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
		exportListParam.put("pageSize", page.getPageSize());
		exportListParam.put("startRow",  (page.getPageNo() - 1) * page.getPageSize());
		List<LendApplyLookExportLendExcelEx> list = lendApplyLookDao.getExportLendExcelList(exportListParam);
		// 组装导出出借excel 字典数据
		setDictLabel4Lend(list, catchMap);
		return list;
	}

	/**
	 * 查询数量
	 * 2016年4月25日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	public int getTotalCntOfExportLendExcelList(Map<String, Object> exportListParam) {
		String dataRights = DataScopeUtil.getDataScope("app", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
		return lendApplyLookDao.getTotalCntOfExportLendExcelList(exportListParam);
	}
	
	/**
	 * 获取缓存
	 * 2016年4月25日
	 * By 肖长伟
	 * @param cacheMap
	 * @param key
	 * @return
	 */
	private Map<String, String> getCacheMap(Map<String, Map<String, String>> cacheMap, String key) {
		Map<String, String> tempMap = cacheMap.get(key);
		if (tempMap == null) {
			tempMap = new HashMap<String, String>();
			cacheMap.put(key, tempMap);
		}
		return tempMap;
	}
	
	/**
	 * 组装导出出借excel 字典数据
	 * 2016年3月17日
	 * By 肖长伟
	 * @param list
	 */
	private void setDictLabel4Lend(List<LendApplyLookExportLendExcelEx> list, Map<String, Map<String, String>> catchMap) {
		//TODO --导出出借excel加缓存
		
		Map<String, String> fortunCenterMap = getCacheMap(catchMap, "fortunCenterMap"); 
		Map<String, String> storeMap = getCacheMap(catchMap,"storeMap");
		Map<String, String> payTypeMap = getCacheMap(catchMap,"payTypeMap");
		Map<String, String> openBankMap = getCacheMap(catchMap,"openBankMap");
		Map<String, String> cardTypeMap = getCacheMap(catchMap,"cardTypeMap");
//		Map<String, String> cityMap = getCacheMap(catchMap,"cityMap");
		Map<String, String> appStatusMap = getCacheMap(catchMap,"appStatusMap");
		Map<String, String> finishStateMap = getCacheMap(catchMap,"finishStateMap");
		Map<String, String> xinhebaoMap = getCacheMap(catchMap,"xinhebaoMap");
		Map<String, String> takenMap = getCacheMap(catchMap,"takenMap");
		
		for (LendApplyLookExportLendExcelEx item : list) {
			
//			System.out.println("==" + new Date());
			//计算第一个账单日
			if(item.getApplyLendDay() != null) {
				item.setFirstBillDate(CreditorUtils.getCreditDaybyLendday(item.getApplyLendDay()));
			}
			
			//财富中心
			if (! fortunCenterMap.containsKey(item.getFortunCenter())) {
				fortunCenterMap.put(item.getFortunCenter(), OptionUtil.getOrgNameById(item.getFortunCenter()));
			}
			item.setFortunCenter(fortunCenterMap.get(item.getFortunCenter()));
			//门店
			if (! storeMap.containsKey(item.getStoreName())) {
				storeMap.put(item.getStoreName(), OptionUtil.getOrgNameById(item.getStoreName()));
			}
			item.setStoreName(storeMap.get(item.getStoreName()));
			//付款方式  tz_pay_type
			if (! payTypeMap.containsKey(item.getPayType())) {
				payTypeMap.put(item.getPayType(), DictUtils.getDictLabel(item.getPayType(), "tz_pay_type", ""));
			}
			item.setPayType(payTypeMap.get(item.getPayType())) ;   
			//划扣行别   tz_open_bank
			if (! openBankMap.containsKey(item.getOpenBank())) {
				openBankMap.put(item.getOpenBank(), DictUtils.getDictLabel(item.getOpenBank(), "tz_open_bank", "" ));
			}
			item.setOpenBank(openBankMap.get(item.getOpenBank()));
			//回款行别   tz_open_bank
			if (! openBankMap.containsKey(item.getBackOpenBank())) {
				openBankMap.put(item.getBackOpenBank(), DictUtils.getDictLabel(item.getBackOpenBank(), "tz_open_bank", "" ));
			}
			item.setBackOpenBank(openBankMap.get(item.getBackOpenBank()));
			//账号类型   com_card_type
			if (! cardTypeMap.containsKey(item.getAccountCardOrBooklet())) {
				cardTypeMap.put(item.getAccountCardOrBooklet(), DictUtils.getDictLabel(item.getAccountCardOrBooklet(), "com_card_type", "" ));
			}
			item.setAccountCardOrBooklet(cardTypeMap.get(item.getAccountCardOrBooklet()));
//			//所在城市
//			if (! cityMap.containsKey(item.getAccountAddrcity())) {
//				cityMap.put(item.getAccountAddrcity(), OptionUtil.getCityLabel(item.getAccountAddrcity()));
//			}
//			item.setAccountAddrcity(OptionUtil.getCityLabel(item.getAccountAddrcity()));
			//状态  tz_for_apply_status
			if (! appStatusMap.containsKey(item.getStatus())) {
				appStatusMap.put(item.getStatus(), DictUtils.getDictLabel(item.getStatus(), "tz_for_apply_status", "" ));
			}
			item.setStatus(appStatusMap.get(item.getStatus()));
			//完结状态   tz_finish_state
			if (! finishStateMap.containsKey(item.getDictApplyEndState())) {
				finishStateMap.put(item.getDictApplyEndState(), DictUtils.getDictLabel(item.getDictApplyEndState(), "tz_finish_state", "" ));
			}
			item.setDictApplyEndState(finishStateMap.get(item.getDictApplyEndState()));
			//信和宝类型  tz_xinhebao_type
			if (! xinhebaoMap.containsKey(item.getXinhebaoType())) {
				xinhebaoMap.put(item.getXinhebaoType(), DictUtils.getDictLabel(item.getXinhebaoType(), "tz_xinhebao_type", "" ));
			}
			item.setXinhebaoType(xinhebaoMap.get(item.getXinhebaoType()));
			// 账单收取方式   tz_taken_mode
			if (! takenMap.containsKey(item.getLoanBillRecv())) {
				takenMap.put(item.getLoanBillRecv(), DictUtils.getDictLabel(item.getLoanBillRecv(), "tz_taken_mode", "" ));
			}
			item.setLoanBillRecv(takenMap.get(item.getLoanBillRecv()));
			
		}
		
	}

	/**
	 * 查询划扣信息，用于导出划扣excel
	 * 2016年3月17日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LendApplyLookExportDecutExcelEx> getExportDeductExcelList(Map<String, Object> exportListParam, Page page) {
		String dataRights = DataScopeUtil.getDataScope("app", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			exportListParam.put("sqlMap", sqlMap);
		}
		exportListParam.put("pageSize", page.getPageSize());
		exportListParam.put("startRow",  (page.getPageNo() - 1) * page.getPageSize());
		List<LendApplyLookExportLendExcelEx> list = lendApplyLookDao.getExportLendExcelList(exportListParam);
		List<LendApplyLookExportDecutExcelEx> listRs = setDictLabel4Deduct(list);
		return listRs;
	}

	/**
	 *  组装导出划扣excel 字典数据
	 * 2016年3月17日
	 * By 肖长伟
	 * @param list
	 * @return
	 */
	private List<LendApplyLookExportDecutExcelEx> setDictLabel4Deduct(List<LendApplyLookExportLendExcelEx> list) {
		List<LendApplyLookExportDecutExcelEx> listRs = new ArrayList<LendApplyLookExportDecutExcelEx>();
		Map<String, String> fortunCenterMap = new HashMap<String, String>();
		Map<String, String> storeMap = new HashMap<String, String>();
		Map<String, String> payTypeMap = new HashMap<String, String>();
		Map<String, String> openBankMap = new HashMap<String, String>();
		Map<String, String> deductPlateMap = new HashMap<String, String>();
		
		for (LendApplyLookExportLendExcelEx item : list) {
			LendApplyLookExportDecutExcelEx dItem = new LendApplyLookExportDecutExcelEx();
			listRs.add(dItem);
			//拷贝成LendApplyLookExportDecutExcelEx 对象
			try {
				BeanUtils.copyProperties(dItem, item);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			//财富中心
			if (! fortunCenterMap.containsKey(item.getFortunCenter())) {
				fortunCenterMap.put(item.getFortunCenter(), OptionUtil.getOrgNameById(item.getFortunCenter()));
			} 
			dItem.setFortunCenter(fortunCenterMap.get(item.getFortunCenter()));
			//门店
			if (! storeMap.containsKey(item.getStoreName())) {
				storeMap.put(item.getStoreName(), OptionUtil.getOrgNameById(item.getStoreName()));
			} 
			dItem.setStoreName(storeMap.get(item.getStoreName()));
			
			//付款方式  tz_pay_type
			if (!payTypeMap.containsKey(item.getPayType())) {
				payTypeMap.put(item.getPayType(), DictUtils.getDictLabel(item.getPayType(), "tz_pay_type", ""));
			}
			dItem.setPayType(payTypeMap.get(item.getPayType())) ;   
			//划扣行别   tz_open_bank
			if (! openBankMap.containsKey(item.getOpenBank())) {
				openBankMap.put(item.getOpenBank(), DictUtils.getDictLabel(item.getOpenBank(), "tz_open_bank", "" ));
			}
			dItem.setOpenBank(openBankMap.get(item.getOpenBank()));
			//划扣平台  tz_deduct_plat
			if (! deductPlateMap.containsKey(dItem.getDictApplyDeductType())) {
				deductPlateMap.put(dItem.getDictApplyDeductType(), DictUtils.getDictLabel(dItem.getDictApplyDeductType(), "tz_deduct_plat", "" ));
			}
			dItem.setDictApplyDeductType(deductPlateMap.get(dItem.getDictApplyDeductType()));
		}
		return listRs;
	}

	
	/**
	 * 查询统计
	 * 2016年3月18日
	 * By 肖长伟
	 * @param dateTime
	 * @return
	 */
	public Map<String, Object> getApplyStatistics(String dateTime) {
//		DCJSP("2"), // 待出借审批
//		SPTG("3"), // 审批通过
//		SPBTG("4"), // 审批不通过
//		WJHCSB("15"); // 文件合成失败
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DCJSP", ApproveLendStateConstant.approveLendState.get(LendState.DCJSP.value));
		params.put("SPTG", ApproveLendStateConstant.approveLendState.get(LendState.SPTG.value));
		params.put("SPBTG", ApproveLendStateConstant.approveLendState.get(LendState.SPBTG.value));
		params.put("WJHCSB", LendState.WJHCSB.value);
		params.put("dateTime", dateTime);
		
		return lendApplyLookDao.getApplyStatistics(params);
	}
	
	/**
	 * 去出借申请查看详情页
	 * 2016年6月17日
	 * by Mr
	 * @param code
	 * @param userManagerId
	 * @return
	 */
	public LendLookPageView goLendApplyPage(String code, String priorityId) {
		
		LendLookPageView llov = new LendLookPageView();
		
		// 查询出借信息
		LoanApply la = lendApplyLookDao.findLendMesg(code);
		llov.setLa(la);

		// 查询客户信息
		Customer cust = lendApplyLookDao.findCustomerMesg(la.getCustCode());
		llov.setCt(cust);
		
		// 查询银行信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("payType", la.getPayType());
		
		//优先回款的Id
		map.put("priorityId", priorityId);
		
		// 查询银行信息
		if (la.getReceiveBankId().equals(la.getPaymentBankId())) {

			String[] id = { la.getPaymentBankId() };
			map.put("id", id);
			llov.setList(lendApplyLookDao.findBankMesg(map));
		} else {

			String[] id = { la.getPaymentBankId(),la.getReceiveBankId()};
			map.put("id", id);
			map.put("id_order", la.getPaymentBankId() + "," + la.getReceiveBankId());
			llov.setList(lendApplyLookDao.findBankMesg(map));
		}
		
//		List<LendLookListView> redeemLendlist = lendApplyLookManager
//		.findRedeemLendMesg(map);

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("childLendCode", code);
		List<TransferRelation> transferList = transferRelationDao.findList(paramMap);
		List<LoanApply> applyList = new ArrayList<LoanApply>();
		if (ArrayHelper.isNotEmpty(transferList)) {
			LoanApply a = null;
			for (TransferRelation item : transferList) {
				a = loanApplyDao.getLoanApplyByCode(item.getLendCodeParent());
				if (null != a) {
					a.setTransferMoney(item.getTransferMoney());
					applyList.add(a);
					llov.setTransferList(applyList);
				}
			}
		}
		// 合同选项
		if (StringUtils.isNotBlank(la.getOptions())) {
			String[] options = la.getOptions().split("_");
			if (options.length == 2) {
				llov.setCheck1(options[0]);
				llov.setCheck2(options[1]);
			}
		}
		return llov;
	}
	
	/**
	 * 去优先回款查看详情页
	 * 2017年3月24日
	 * by 郭强
	 * @param code
	 * @param userManagerId
	 * @return
	 */
	public LendLookPageView goPriorityBackPage(String code, String userManagerId,String priorityId) {
		
		LendLookPageView llov = new LendLookPageView();
		llov.setPriorityId(priorityId);
		
		// 查询出借信息
		LoanApply la = lendApplyLookDao.findLendMesg(code);
		llov.setLa(la);

		// 查询客户信息
		Customer cust = lendApplyLookDao.findCustomerMesg(la.getCustCode());
		llov.setCt(cust);
		
		// 查询银行信息
		Map<String, Object> map = new HashMap<String, Object>();
		//出借编号
		map.put("code", code);
		//付款方式
		map.put("payType", la.getPayType());
		// 查询银行信息
		//回款银行id，							付款银行id
		if (la.getReceiveBankId().equals(la.getPaymentBankId())) {

			String[] id = { la.getPaymentBankId() };
			map.put("id", id);
			llov.setList(lendApplyLookDao.findBankMesg(map));
		} else {

			String[] id = { la.getPaymentBankId(),la.getReceiveBankId()};
			map.put("id", id);
			map.put("id_order", la.getPaymentBankId() + "," + la.getReceiveBankId());
			llov.setList(lendApplyLookDao.findBankMesg(map));
		}
		
//		List<LendLookListView> redeemLendlist = lendApplyLookManager
//		.findRedeemLendMesg(map);

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("childLendCode", code);
		List<TransferRelation> transferList = transferRelationDao.findList(paramMap);
		List<LoanApply> applyList = new ArrayList<LoanApply>();
		if (ArrayHelper.isNotEmpty(transferList)) {
			LoanApply a = null;
			for (TransferRelation item : transferList) {
				a = loanApplyDao.getLoanApplyByCode(item.getLendCodeParent());
				if (null != a) {
					a.setTransferMoney(item.getTransferMoney());
					applyList.add(a);
					llov.setTransferList(applyList);
				}
			}
		}
		// 合同选项
		if (StringUtils.isNotBlank(la.getOptions())) {
			String[] options = la.getOptions().split("_");
			if (options.length == 2) {
				llov.setCheck1(options[0]);
				llov.setCheck2(options[1]);
			}
		}
		return llov;
	}

	/**
	 * 校验这条出借的状态
	 * 2017年3月29日
	 * 郭强
	 * @param lendCode
	 * @return
	 */
	public String searchLendState(String lendCode) {
		LendApplyLookListView  view = lendApplyLookDao.searchLendState(lendCode);
		StringBuffer sbf = new StringBuffer();
		//【出借状态】是“划扣成功”，【完结状态】是“未完结”
		if( !(LendState.HKCG.value.equals(view.getLendStatus())&&FinishState.WWJ.value.equals(view.getDictApplyEndState() ) ) ){
			sbf.append("【出借状态】不是回扣成功或者【完结状态】不是未完结<br/>");
		}
		//回款状态】是“待回款申请”、“待回款申请确认退回”，【回款类型】是“到期回款”、“赎回内转”
		// 【回款类型】是“到期回款”、“赎回内转”
		boolean  backType =  BackType.DQHK.value.equals(view.getBackMoneyType()) ||
								  BackType.SHNZ.value.equals(view.getBackMoneyType());
		//【回款状态】是“待回款申请”、“待回款申请确认退回”
		boolean  backState = BackState.DHKSQ.value.equals(view.getDictBackStatus()) ||
								  BackState.DHKSQQRTH.value.equals(view.getDictBackStatus());
		if(! (backType && backState)){
			sbf.append("【回款状态】不是待回款申请，待回款申请确认退回 或者【回款类型】不是到期回款，赎回内转<br/>");
		}
		//【到期日期】是系统当前日期之前（包含系统当期日期）
		if(DateUtils.dateAfter(view.getApplyExpireDay(),new Date())){
			sbf.append("该笔出借未到期，暂时不能执行此操作<br/>");
		}
		//转投大金融
		if(FortuneSwitchApproveStatus.DSP.value.equals(view.getSwitchApproveStatus()) || 
				   FortuneSwitchApproveStatus.SPCG.value.equals(view.getSwitchApproveStatus()) ||
				   FortuneSwitchApproveStatus.ZTCG.value.equals(view.getSwitchApproveStatus()) ){
			sbf.append("该笔出借已操作转投大金融，暂时不能执行此操作<br/>");
		}
		//优先回款的状态
		if(PriorityBackState.DSP.value.equals(view.getPriorityBackState()) ||
				 PriorityBackState.SPTG.value.equals(view.getPriorityBackState()) ||
				 PriorityBackState.SPWTG.value.equals(view.getPriorityBackState()) ){
			sbf.append("该笔出借已操作优先回款，暂时不能执行此操作<br/>");
		}
		return sbf.toString();
	}

	/**
	 * 优先回款申请
	 * 2017年3月30日
	 * 郭强
	 * @param view
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String apply(PriorityResultView prview) {
		String userId = UserUtils.getUserId();
		String  sbf = searchLendState(prview.getLendCode());
		if(sbf.length() > 0){
			return sbf.toString();
		}
		int i=0;
		if(StringUtils.isNotEmpty(prview.getPriorityId())){
			prview.setPriorityBackState(PriorityBackState.DSP.value);
			i=loanApplyDao.updatePriority(prview);
		}else{
			//初始化数据
			//设置为待审批
			prview.setPriorityBackState(PriorityBackState.DSP.value);
			String  priorityId = IdGen.uuid();
			prview.setPriorityId(priorityId);
			i = loanApplyDao.addPriorityAlone(prview);
			logger.info("出借编号为:"+prview.getLendCode()+"操作了优先回款申请");
		}
		
		if(i>0){
			PriorityBackLog log = new PriorityBackLog();
			log.setPriorityId(prview.getPriorityId());
			log.setPriorityBackState(prview.getPriorityBackState());
			log.setApplyBy(prview.getApplyBy());
			log.setCreateBy(userId);
			priorityLogManager.addPriorityLog(log);
			
			if(StringUtils.isNotEmpty(prview.getAddAttachmentId())){
				//同时更新附件表
				List<String>  IdList = Arrays.asList(prview.getAddAttachmentId().split(","));
				Map<String ,Object> map = new HashMap<String, Object>();
				map.put("atta_file_owner", "tz.t_tz_priority_back_pool");
				map.put("atta_table_id", prview.getPriorityId());
				map.put("loan_code", prview.getLendCode());
				
				map.put("IdList", IdList);
				
				loanApplyDao.updateFile(map);
			}
			
			//全程留痕
			checkmanager.addCheck(prview.getLendCode(), "优先回款申请", "优先回款申请",userId );
		} else{
			sbf+="出借编号为:"+prview.getLendCode()+"申请失败,请重新操作<br/>";
			logger.error("出借编号为:"+prview.getLendCode()+"优先回款申请失败");
		}
		
		return sbf.toString();
		
	}
	/**
	 * 检查出借是否正在被内转中，如果正在内转中，return true
	 * @param lendCode
	 * @return
	 */
	private boolean checkLendIsInTransfer(String lendCode){
		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		List<String> statusList = new ArrayList<String>();
		statusList.add(TransferState.YNZ.getKey());
		statusList.add(TransferState.YTH.getKey());
		statusList.add(TransferState.YQX.getKey());
		map.put("statusList", statusList);
		
		int count = transferRelationDao.countTransferRelation(map);
		if (count>0) {
			result = true;
			BackMoneyPool pool = transferRelationDao.getByLendCode(lendCode);
			if (BackType.TQSH.value.equals(pool.getBackMoneyType())) {
				result = false;
			}
		}
		return result;
	}
}