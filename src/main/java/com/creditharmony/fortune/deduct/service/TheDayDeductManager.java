package com.creditharmony.fortune.deduct.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.in.thirdpay.ProtocolLibraryInfo;
import com.creditharmony.adapter.bean.out.thirdpay.ProtocolLibraryReturnInfo;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.creditharmony.fortune.utils.FileUtil;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * @author 韩龙
 * @Create In 2015年11月20日 
 * @version 3.0
 */
@Service
public class TheDayDeductManager extends CoreManager<TheDayDeductDao, ThedayDeductPool>{
	
	@Autowired
	private DeductApplyDao deductApplyDao;
	
	@Autowired
	private TheDayDeductDao theDayDeductDao;
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	@Autowired
	private ContractManager contractManager;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	/**
	 * 
	 * 2016年1月26日
	 * By 韩龙
	 * @param page
	 * @param deductPoolEx
	 * @return
	 */
	public Page<DeductPoolEx> findPage(Page<DeductPoolEx> page, DeductPoolEx deductPoolEx) {
		// 判断是否为空
		if (ObjectHelper.isEmpty(deductPoolEx)) {
			deductPoolEx = new DeductPoolEx();
		}
		// 设置首期标识
		deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<DeductPoolEx> pageList = (PageList<DeductPoolEx>) super.dao.findList(deductPoolEx, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 计算今天划扣总金额和出借总金额
	 * 2016年2月4日
	 * By 周俊
	 * @param deductPoolEx
	 * @return
	 */
	public DeductPoolEx getApplyLendMoneyAndActualDeductMoney(DeductPoolEx deductPoolEx){
		if (ObjectHelper.isEmpty(deductPoolEx)) {
			deductPoolEx = new DeductPoolEx();
		}
		// 设置首期标识
		deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		DeductPoolEx deductPoolExToMoney = theDayDeductDao.getApplyLendMoneyAndActualDeductMoney(deductPoolEx);
		return deductPoolExToMoney;
	}
	
	/**
	 * checkbox 计算金额
	 * 2016年2月4日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public DeductPoolEx checkboxReckonMoney(String id){
		String[] array = id.split(",");
		DeductPoolEx deductPoolEx = theDayDeductDao.checkboxReckonMoney(array);
		return deductPoolEx;
	}

	/**
	 * 提交划扣任务并保存划扣记录
	 * 2016年4月13日
	 * By 韩龙
	 * @param massge
	 * @param code
	 * @param tdp
	 * @param recReq
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public void addTask(StringBuffer massge, String code,
			ThedayDeductPool tdp, DeductReq recReq) {
		ThedayDeductPool forUpdate = dao.getForUpdate(tdp);
		if(forUpdate == null){
			String info = "出借编号【" + code + "】:已被其它业务人员处理过";
			logger.debug(info);
			massge.append(info);
			return;
		}
		FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
		deductReqManager.addDeductReq(recReq, fortuneDeductReq);
		// 添加任务
		DeResult deResult = TaskService.addTask(recReq);
		if(!deResult.getReCode().equals(ResultType.ADD_SUCCESS.getCode())){
			logger.debug("线上划扣失败【" + code + "】,失败信息" + deResult.getReMge());
			massge.append(deResult.getReMge());
		}else{
			DeductPool dp = new DeductPool();
			dp.setDeductApplyId(tdp.getDeductApplyId());
			dp.setDictDeductStatus(DeductState.HKCLZ.value);
			// 更新划扣申请池状态
			dp.preUpdate();
			deductApplyDao.update(dp);
			// 更新分天划扣表状态
			tdp.preUpdate();
			super.dao.update(tdp);
			String reString = "";
			try {
				reString = deductReqManager.commit(recReq, fortuneDeductReq, deResult, code);
			} catch (Exception e) {
				logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
				deductReqManager.updateStatus(recReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
				throw new ServiceException("提交划扣任务commit失败：" + e.getMessage());
			}
			if(StringUtils.isNotEmpty(reString) ){
				logger.debug("提交分天划扣任务commit失败,失败信息为：" + reString);
				throw new ServiceException(reString);
			}
		}
	}
	
	/**
	 * 协议库对接
	 * 2015年12月22日
	 * By 韩龙
	 * @param codes
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public List<String> protocolLibrary(String[] codes) {
		List<String> massage = Lists.newArrayList();
		for (String code : codes) {
			// 参数条件
			Map<String,Object> filter = new HashMap<String,Object>();
			filter.put("codes", new String[]{code} );
			filter.put("dictApplyDeductType", DeductPlat.FYPT.value);
			// 查询协议库对接数据
			List <ProtocolLibraryInfo> listPlInfo = deductApplyDao.getProtocolLibraryInfo(filter);
			ClientPoxy service = new ClientPoxy(ServiceType.Type.FY_SIGN_REQ);	
			for (ProtocolLibraryInfo protocolLibraryInfo : listPlInfo) {
				protocolLibraryInfo.setCredtTp(protocolLibraryInfo.getCredtTp());
				protocolLibraryInfo.setAcntType("01");
				try {
					ProtocolLibraryReturnInfo plrInfo = (ProtocolLibraryReturnInfo) service
							.callService(protocolLibraryInfo);
					if (!plrInfo.getRetCode().equals(ResultType.POXY_SUCCESS.getCode())) {
						massage.add("业务编号【" + code + "】:" + plrInfo.getRetMsg());
					}
				} catch (Exception e) {
					massage.add("业务编号【" + code + "】失败:" + e.getMessage());
					FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, code,
							FortuneLogNode.DEDUCT_SUCCESS.getCode(), FortuneLogLevel.YELLOW.value, 
							"业务编号【" + code + "】失败"));
				}
			}
			checkManager.addCheck(code,"分天划扣协议库对接", "分天划扣",
					code,FortuneLogNode.DEDUCT_THEDAY);
		}
		return massage;
	}

	/**
	 * 批量修改状态
	 * 2016年1月27日
	 * By 韩龙
	 * @param filters
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public void batchUpdateStatus(Map<String, Object> filters,ThedayDeductPool tdp,StringBuffer message) {
			String status = filters.get("dictDeductStatus") + "";
			String id = tdp.getId();
			tdp = super.dao.getForUpdate(tdp);
			if(tdp == null){
				String info = "业务编号【"+id+"】已被其它业务人员处理过";
				logger.debug(info);
				message.append(info).append("</br>");
				return;
			}
			// 判断否是从分天划扣列表的数据
			String success = filters.get("SUCCESS") + "";
			tdp.setDictDeductStatus(status);
			// 分天划扣列表的数据批量修改状态
			if(success.equals(Constant.SUCCESS)){
				if(status.equals(DeductState.HKCG.value)){
					// 如果成功，成功金额为分天划扣金额
					tdp.setDeductSucceedMoney(DeductUtils.isNullBigDecimal(tdp.getActualDeductMoney()));
				}else{
					// 如果失败，失败金额为分天划扣金额
					tdp.setDeductFailMoney(DeductUtils.isNullBigDecimal(tdp.getActualDeductMoney()));
				}
			}else{
				if(status.equals(DeductState.HKCG.value)){
					// 如果成功，成功金额为分天划扣金额;失败金额为0
					tdp.setDeductSucceedMoney(DeductUtils.isNullBigDecimal(tdp.getActualDeductMoney()));
					tdp.setDeductFailMoney(DeductUtils.isNullBigDecimal(null));
				}
			}
			if(filters.get("confirmOpinion") != null && !"".equals(filters.get("confirmOpinion"))){
				tdp.setFailReason(filters.get("confirmOpinion").toString());
			}
			tdp.preUpdate();
			super.dao.update(tdp);
			// 判断是
			Map<String,Object> tpMap = new HashMap<String,Object>();
			List<String> list = Lists.newArrayList();
			list.add(DeductState.DFTHK.value);
			list.add(DeductState.XSHK.value);
			list.add(DeductState.YYHK.value);
			list.add(DeductState.ECXSHK.value);
			list.add(DeductState.ECYYHK.value);
			list.add(DeductState.HKSB.value);
			tpMap.put("deductApplyId", tdp.getDeductApplyId());
			tpMap.put("deductStatusList", list);
			List<ThedayDeductPool> tdplist = super.dao.checkProcessingStatus(tpMap);
			if(tdplist != null && tdplist.size()>0){
				return;
			}else{
				Map<String,Object> tdpMap = new HashMap<String,Object>();
				tdpMap.put("deductApplyId", tdp.getDeductApplyId());
				List<ThedayDeductPool> listTdp = super.dao.checkProcessingStatus(tdpMap);
				DeductPool dp = new DeductPool();
				dp.setDeductApplyId(tdp.getDeductApplyId());
				dp = deductManager.get(dp);
				for (ThedayDeductPool thedayDeductPool : listTdp) {
					// 成功金额
					if(thedayDeductPool.getDictDeductStatus().equals(DeductState.HKCG.value)){
						String money = new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))
							.add(DeductUtils.isNullBigDecimal(thedayDeductPool.getDeductSucceedMoney())).toString();
						dp.setActualDeductMoney(money);
					}else{
						dp.setFailReason(thedayDeductPool.getFailReason());
						// 失败金额
						String money = new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney())).add(DeductUtils.isNullBigDecimal(thedayDeductPool.getDeductFailMoney())).toString();
						dp.setFailMoney(money);
					}
					// 未划金额
					String money = new BigDecimal(DeductUtils.isNullMoney(dp.getNoDeductMoney())).add(new BigDecimal(DeductUtils.isNullMoney(thedayDeductPool.getNoDeductMoney()))).toString();
					dp.setNoDeductMoney(money);
				}
				BigDecimal failMoney = new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney()));
				BigDecimal noDeductMoney = new BigDecimal(DeductUtils.isNullMoney(dp.getNoDeductMoney()));
				// 如果状态是成功并且失败金额与未划扣金额为0分天划扣为成功
				if(DeductState.HKCG.value.equals(status) && 
						failMoney.compareTo(new BigDecimal("0")) == 0 &&
						noDeductMoney.compareTo(new BigDecimal("0")) == 0){
					dp.setDictDeductStatus(DeductState.HKCG.value);
					LoanApply loanApply = new LoanApply();
					loanApply.setApplyCode(dp.getApplyCode());
					// 查询对应出借信息
					loanApply = loanApplyDao.get(loanApply);
					loanApply.setRealDeductTime(new Date());
					// TODO 更新划扣申请的划扣平台与划扣状态
					if (dp.getDictDeductStatus().equals(DeductState.HKCG.value)) {
						// 计算回款金额
						MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
						matchingCreditorEx.setLendCode(dp.getApplyCode());
						matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
						matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
						BigDecimal backMoney = creditorAidManager
								.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
						// 回款池插入数据
						BackMoneyPool backMoneyPool = new BackMoneyPool();
						backMoneyPool.preInsert();
						backMoneyPool.setBackmId(backMoneyPool.getId());
						backMoneyPool.setLendCode(dp.getApplyCode());
						backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
						backMoneyPool.setBackMoneyType(BackType.DQHK.value);
						backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
						backMoneyPool.setBackMoneyDay(DeductUtils
								.getNextWorkingDay(loanApply.getExpireDate()));
						backMoneyPool.setBackMoney(backMoney);
						backMoneyPool.setBackActualbackMoney(backMoney);
						backMoneyPool.setRebackFlag(YoN.FOU.value);
						backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
						// 更新出借状态
						loanApply.setLendStatus(LendState.HKCG.value);
						loanApply.setStatus(ForApplyStatus.CJZ.value);
						
						// 合同使用时间
						Contract contract = new Contract();
						contract.setLendCode(dp.getApplyCode());
						contract.setContUseDay(new Date());
						contract.preUpdate();
						contractManager.updateContractUseDay(contract);
						// 插入
						backMoneyPoolDao.insert(backMoneyPool);
//						// 发送短信
//						commonFacade.smsInfo(loanApply.getCustCode(), DeductState.HKCG.value,loanApply);
//						// 制作文件
//						commonFacade.makeFileInfo(loanApply.getCustCode());
						// 三网首单校
						/*List<IntDeliveryEx> exList = sendTripleInfoFacade.findLendApply(loanApply.getApplyCode());
						if(exList.size() == 0){
							sendTripleInfoFacade.tripleInfo(loanApply.getCustCode());
						}*/
						
					} else {
						// 更新出借状态
						loanApply.setLendStatus(LendState.HKSB.value);
					}
					loanApply.setRealDeductTime(new Date());
					loanApply.preUpdate();
					loanApplyDao.update(loanApply);
				}else{
					dp.setDictDeductStatus(DeductState.HKSB.value);
				}
				dp.preUpdate();
				deductApplyDao.update(dp);
			}
	}
	
	/**
	 * 获得今天划扣的金额和
	 * 2016年2月3日
	 * By 周俊
	 * @param deductApplyId
	 * @return
	 */
	public BigDecimal getActualDeductMoneySum(String[] array){
		BigDecimal actualDeductMoneySum = theDayDeductDao.getActualDeductMoneySum(array);
		return actualDeductMoneySum;
	}
	
	/**
	 * 根据条件查询出列表
	 * 2016年2月2日
	 * By 韩龙	
	 * @param tdp
	 * @return
	 */
	public List<ThedayDeductPool> findAllList(ThedayDeductPool tdp) {
		return super.dao.findAllList(tdp);
	}
	
	/**
	 * 判断是当前划扣是否有划扣处理中和
	 * 二次划扣中的状态（即没有回盘结果）
	 * 2016年2月2日
	 * By 韩龙	
	 * @param tdp
	 * @return
	 */
	public List<ThedayDeductPool> checkProcessingStatus(Map<String,Object> map) {
		return super.dao.checkProcessingStatus(map);
	}

	/**
	 * 根据划扣申请id取消所有预约为失败状态
	 * 2016年2月18日
	 * By 韩龙
	 * @param codes
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public void updateFail(ThedayDeductPool tdp,StringBuffer message) {
		String theDayId = tdp.getDeductApplyId();
		List<ThedayDeductPool> tdpool = dao.findListForUpdate(tdp);
		if(tdpool == null ){
			String info = "此条记录已被其它业务人员处理过";
			logger.debug(info);
			message.append(info).append("</br>");
			return;
		}
		// 失败金额
		BigDecimal failMoney = new BigDecimal("0");
		// 成功金额
		BigDecimal succsseMoney = new BigDecimal("0");
		// 未划金额
		BigDecimal noDeductMoney = new BigDecimal("0");
		for (ThedayDeductPool thedayDeductPool : tdpool) {
			ThedayDeductPool tdp1 = new ThedayDeductPool();
			// 更新分天划扣表状态为失败
			tdp1.setId(thedayDeductPool.getId());
			succsseMoney = succsseMoney.add(DeductUtils.
					isNullBigDecimal(thedayDeductPool.getDeductSucceedMoney()));
			if(thedayDeductPool.getDictDeductStatus().equals(DeductState.HKSB.value)){
				// 撤销状态在此表示确认失败状态
				tdp1.setDictDeductStatus(DeductState.FTQRSB.value);
				noDeductMoney = noDeductMoney.add(new BigDecimal(DeductUtils.
						isNullMoney(thedayDeductPool.getNoDeductMoney())));
			}else if(thedayDeductPool.getDictDeductStatus().equals(DeductState.HKCG.value)){
				// 分天划扣成功的不做任务处理
			}else{
				// 撤销状态在此表示确认失败状态
				tdp1.setDictDeductStatus(DeductState.FTQRSB.value);
				tdp1.setDeductFailMoney(thedayDeductPool.getActualDeductMoney());
				tdp1.setNoDeductMoney(thedayDeductPool.getActualDeductMoney() + "");
				noDeductMoney = noDeductMoney.add(thedayDeductPool.getActualDeductMoney());
			}
			tdp1.preUpdate();
			super.dao.update(tdp1);
		}
		
		// 更新划扣申请表状态为失败
		DeductPool dp = new DeductPool();
		dp.setDeductApplyId(theDayId);
		dp = deductApplyDao.get(dp);
		dp.setActualDeductMoney(succsseMoney.toString());
		dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(dp.getLoanMoney()))
			.subtract(DeductUtils.isNullBigDecimal(succsseMoney)).toString());
		dp.setNoDeductMoney(noDeductMoney.toString());
		dp.setDictDeductStatus(DeductState.HKSB.value);
		dp.preUpdate();
		deductApplyDao.update(dp);
//		Integer.parseInt("aaa");
	}
	
	/**
	 * 导入线下划扣结果 2015年12月21日 By 韩龙
	 * 
	 * @param list
	 * @param map
	 * @throws ParseException 
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveExcel(Map<String, DeductPool> disMap) throws ParseException {
		// 循环更新
		for (String key : disMap.keySet()) {
			DeductPool dp = disMap.get(key);
			ThedayDeductPool tdp = new ThedayDeductPool();
//			tdp.setActualDeductMoney(new BigDecimal(dp.getActualDeductMoney()));
			tdp.setId(dp.getApplyCode());
			tdp = dao.get(tdp);
			tdp.setDictDeductStatus(dp.getDictDeductStatus());
			tdp.setFailReason(dp.getFailReason());
			// 累加成功金额
			tdp.setDeductSucceedMoney(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))
				.add(DeductUtils.isNullBigDecimal(tdp.getDeductSucceedMoney())));
			// 累加失败金额
			tdp.setDeductFailMoney(tdp.getActualDeductMoney().subtract(tdp.getDeductSucceedMoney()));
			tdp.setDeductTime(DateUtils.parseDate(dp.getDealTime()));
			// 更新数据库结果
			tdp.preUpdate();
			super.dao.update(tdp);
			// 判断是否是最后一笔
			ThedayDeductPool thedayDeductPool = dao.get(tdp);
			if(thedayDeductPool != null){
				ThedayDeductPool tdpl = new ThedayDeductPool();
				List<String> status = Lists.newArrayList();
				status.add(DeductState.DFTHK.value);
				status.add(DeductState.XSHK.value);
				status.add(DeductState.YYHK.value);
				status.add(DeductState.ECYYHK.value);
				status.add(DeductState.ECXSHK.value);
				status.add(DeductState.HKSB.value);
				tdpl.setDictDeductStatusList(status);
				tdpl.setDeductApplyId(thedayDeductPool.getDeductApplyId());
				List<ThedayDeductPool> list = super.dao.findAllList(tdpl);
				// 判断是否分天划完成
				if(list != null && list.size() > 0){
					return ;
				}
				// 清空状态，只根据划扣申请id查询
				tdpl.setDictDeductStatusList(null);
				List<ThedayDeductPool> countList = super.dao.findAllList(tdp);
				DeductPool deductPool = new DeductPool();
				deductPool.setDeductApplyId(thedayDeductPool.getDeductApplyId());
				deductPool = deductManager.get(deductPool);
				// 失败金额
				BigDecimal failMoney = new BigDecimal("0");
				// 成功金额
				BigDecimal succeedMoney = new BigDecimal("0");
				// 未划金额
				BigDecimal noDeductMoney = new BigDecimal("0");
				// 统计成功或失败金额
				for (ThedayDeductPool thedayPool : countList) {
					BigDecimal deductFailMoney =  DeductUtils.isNullBigDecimal(thedayPool.getDeductFailMoney());
					BigDecimal deductSucceedMoney = DeductUtils.isNullBigDecimal(thedayPool.getDeductSucceedMoney());
					BigDecimal noDeductMoney1 = new BigDecimal(DeductUtils.isNullMoney(thedayPool.getNoDeductMoney()));
					failMoney = failMoney.add(deductFailMoney);
					succeedMoney = succeedMoney.add(deductSucceedMoney);
					noDeductMoney = noDeductMoney.add(noDeductMoney1);
				}
				deductPool.setFailMoney(failMoney.toString());
				deductPool.setActualDeductMoney(succeedMoney.toString());
				deductPool.setFailReason(dp.getFailReason());
//				Date str = DateUtils.parseDate(dp.getDealTime(),"yyyy-MM-dd");
				deductPool.setDealTime(dp.getDealTime());
				deductPool.setDictDeductStatus(DeductState.HKCG.value);
				// 更新出借申请表
				String lendCode = deductPool.getApplyCode();
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = loanApplyDao.get(loanApply);
				loanApply.setDeductMoney(succeedMoney);
				loanApply.setLendStatus(LendState.HKCG.value);
				loanApply.setRealDeductTime(new Date());
				deductPool.setDictDeductStatus(DeductState.HKCG.value);
				// 判断划扣成功失败
				if(succeedMoney.compareTo(new BigDecimal("0"))== 0 
						|| succeedMoney.compareTo(new BigDecimal("0"))== 0){
					loanApply.setLendStatus(LendState.HKSB.value);
					deductPool.setDictDeductStatus(DeductState.HKSB.value);
				}
				deductPool.preUpdate();
				deductManager.update(deductPool);
				loanApply.preUpdate();
				loanApplyDao.update(loanApply);
				if(deductPool.getDictDeductStatus().equals(DeductState.HKCG.value)){
					// 合同使用时间
					Contract contract = new Contract();
					contract.setLendCode(dp.getApplyCode());
					contract.setContUseDay(new Date());
					contract.preUpdate();
					contractManager.updateContractUseDay(contract);
					logger.debug("划扣插入回款池数据---------->开始");
					saveBackMoney(deductPool, lendCode, loanApply);
					logger.debug("划扣插入回款池数据---------->结束");
					// 发送短信
					commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);
					// 制作文件
					commonFacade.makeFileInfo(lendCode);
					// 三网首单校
					sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(),loanApply.getApplyCode());
				}
			}
			// 全程流痕
			checkManager.addCheck(dp.getApplyCode(), "导入分天线下划扣结果", 
					"分天线下划扣导入",tdp.getId(),FortuneLogNode.DEDUCT_THEDAY);
		}
	}

	/**
	 * 插入回款表
	 * 2016年4月18日
	 * By 韩龙
	 * @param deductPool
	 * @param lendCode
	 * @param loanApply
	 */
	private void saveBackMoney(DeductPool deductPool, String lendCode,
			LoanApply loanApply) {
		// 计算回款金额
		 MatchingCreditorEx matchingCreditorEx = new
		 MatchingCreditorEx();
		 matchingCreditorEx.setLendCode(deductPool.getApplyCode());
		 matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		 matchingCreditorEx =
		 matchingCreditorDao.get(matchingCreditorEx);
		 BigDecimal backMoney =
				 creditorAidManager.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
		// 插入回款表
		BackMoneyPool backMoneyPool = new BackMoneyPool();
		backMoneyPool.preInsert();
		backMoneyPool.setBackmId(backMoneyPool.getId());
		backMoneyPool.setLendCode(lendCode);
		backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
		// 成功回款金额
		backMoneyPool.setBackActualbackMoney(backMoney);
		backMoneyPool.setBackMoney(backMoney);
		backMoneyPool.setBackMoneyType(BackType.DQHK.value);
		backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
		// 回款日期是出借到期日后一天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(loanApply.getExpireDate());
		calendar.add(Calendar.DATE, +1);
		backMoneyPool.setBackMoneyDay(calendar.getTime());
		backMoneyPool.setRebackFlag(YoN.FOU.value);
		backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
		backMoneyPoolDao.insert(backMoneyPool);
	}
	
	/**
	 * 制作收款确认书
	 * 2016年2月2日
	 * By 韩龙
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void makeFile(String lendCode){
		Map<String, Object> filters = new HashMap<String,Object>();
		MatchingCreditorEx mc = new MatchingCreditorEx();
		mc.setLendCode(lendCode);
		// 首期标识
		mc.setMatchingFirstdayFlag(BillState.SQ.value);
		mc = matchingCreditorDao.get(mc);
		LoanApply apply=deductManager.getCustCodeByApplyCode(lendCode);
		mc.setCustomerCode(apply.getCustCode());
		filters.put("attaTableId", mc.getMatchingId());
		filters.put("lendCode", lendCode);
		filters.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
		filters.put("parameter", "matching_id="+mc.getMatchingId());
		filters.put("customerCode", mc.getCustomerCode());
		filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
		// 合成文件
		CERequestContext cERequestContext = CEContextHelper.login(
				Constant.getProperties.get("ceuser"),
				Constant.getProperties.get("cepwd"));
		// ce认证
		filters.put(CERequestContext.DM_FILENET_CONTEXT, cERequestContext);
		FileUtil.compositeFile(filters);
	}
	
	/**
	 * 获取出借编号
	 * 2016年5月17日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public Map<String,String> getTheDaysById (Map<String,Object> map){
		return dao.getTheDaysById(map);
	}
	
	public boolean checkTheDayList(String deductApplyId){
		// 判断是
		Map<String,Object> tpMap = new HashMap<String,Object>();
		List<String> list = Lists.newArrayList();
		list.add(DeductState.DFTHK.value);
		list.add(DeductState.XSHK.value);
		list.add(DeductState.YYHK.value);
		list.add(DeductState.ECXSHK.value);
		list.add(DeductState.ECYYHK.value);
		list.add(DeductState.HKSB.value);
		tpMap.put("deductApplyId", deductApplyId);
		tpMap.put("deductStatusList", list);
		List<ThedayDeductPool> tdplist = super.dao.checkProcessingStatus(tpMap);
		if(tdplist != null && tdplist.size()>0){
			return true;
		}
		return false;
	}
}

