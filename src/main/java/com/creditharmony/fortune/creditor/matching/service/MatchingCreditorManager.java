package com.creditharmony.fortune.creditor.matching.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.TracesType;
import com.creditharmony.core.fortune.type.TransferState;
import com.creditharmony.core.fortune.type.XinhebaoType;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.dao.CreditorHisDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.config.auto.matching.dao.AutoMatchingDao;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorTradeDao;
import com.creditharmony.fortune.creditor.matching.dao.LoanphaseCurDao;
import com.creditharmony.fortune.creditor.matching.dao.LoanphasePeriodDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.dao.TempAutoMatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;
import com.creditharmony.fortune.creditor.matching.entity.LoanphaseCur;
import com.creditharmony.fortune.creditor.matching.entity.LoanphasePeriod;
import com.creditharmony.fortune.creditor.matching.entity.TempAutoMatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.utils.MatchingUtils;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.entity.EmailTemplate;
import com.creditharmony.fortune.obligatory.manager.CreditLocationListManager;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.sms.manager.SmsManager;
import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;
import com.creditharmony.fortune.utils.BackMoneyUtil;
import com.creditharmony.fortune.utils.ConvertUtil;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.FormatUtils;

/**
 * 待债权推荐信息服务层
 * @Class Name MatchingCreditorManager
 * @author 柳慧
 * @Create In 2015年12月15日
 */
 
@Service
public class MatchingCreditorManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;// 待推荐Dao
	
	@Autowired
	private LoanphaseDao LoanphaseDao;// 分期收益Dao
	
	@Autowired
	private CreditorTradeDao creditorTradeDao;// 债务交易Dao
	
	@Autowired
	private BorrowDao borrowDao;// 可用债权Dao
	
	@Autowired
	private BorrowMonthableDao borrowMonthableDao;
	
	@Autowired 
	private DeductApplyDao deductApplyDao; // 划扣申请dao
	
	@Autowired
	private LoanApplyDao loanApplyDao;

	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao; // 回款池Dao
	
	@Autowired
	private TransferRelationDao transferRelationDao;
	
	@Autowired
	private EmailInfoDao emailInfoDao;
	
	@Autowired
	private SmsManager smsManager;
	
	@Autowired
	private SmsDao smsDao;
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired 
	private ProductMatchingRateManager productMatchingRateManager;
	
	@Autowired 
	private CreditorConfigManager creditorConfigManager; //  可用债权交易
	
	@Autowired
	private CreditLocationListManager creditLocationListManager;
	
	@Autowired
	private AttachmentManager attachmentManager;
	
	@Autowired
	private LendApplyManager lendApplyManager;
		
	@Autowired
	private AutoMatchingDao autoMatchingDao;
	
	@Autowired
	private TempAutoMatchingCreditorDao tempAutoMatchingCreditorDao;
	
	@Autowired
	private ContractManager contractManager;
	
	@Autowired
	private CreditorHisDao creditorHisDao;
	
	@Autowired
	private LoanphaseCurDao loanphaseCurDao;
	
	@Autowired
	private LoanphasePeriodDao loanphasePeriodDao;
	
	@Autowired
	private CreditorStatisticsManager creditorStatisticsManager;
	
	@Autowired
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 根据待债权推荐信息条件 查询符合条件的集合，同时分页
	 * 2016年1月18日
	 * By 柳慧
	 * @param page
	 * @param ex
	 * @return
	 */
	public Page<MatchingCreditorView> findPage(Page<MatchingCreditorView> page,MatchingCreditorEx search){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		Map<String,Object> tempex = new HashMap<String,Object>();
		// 参数转换
		tempex = ConvertUtil.ObjectToMap(search);
		
		// ****** 可多选检索条件整形  START ******
		// 产品ID封装
		String productCode = search.getProductCode();
		tempex.put("productCodes", MatchingUtils.mulityStringOptionForSearch(productCode, ","));		
		
		//账单类型
		String matchingFirstdayFlag = search.getMatchingFirstdayFlag();
		if(!StringUtils.isEmpty(matchingFirstdayFlag)){
			tempex.put("matchingFirstdayFlags", MatchingUtils.mulityStringOptionForSearch(matchingFirstdayFlag, ","));			
		}
		// 账单日
		String matchingBillDayStr = search.getMatchingBillDayStr();
		if(!StringUtils.isEmpty(matchingBillDayStr)){
			tempex.put("matchingBillDays", MatchingUtils.mulityIntOptionForSearch(matchingBillDayStr, ","));
		}
		// 付款方式
		String applyPay = search.getApplyPay();
		if (!StringUtils.isEmpty(applyPay)) {
			tempex.put("applyPays", MatchingUtils.mulityStringOptionForSearch(applyPay, ","));
		}
		// 划扣平台
		String deductType = search.getDictApplyDeductType();
		if (!StringUtils.isEmpty(deductType)) {
			tempex.put("dictApplyDeductTypes", MatchingUtils.mulityStringOptionForSearch(deductType, ","));
		}
		// 匹配标识
		String matchingFlagType = search.getDictMatchingFlagType();
		if (!StringUtils.isEmpty(matchingFlagType)) {
			tempex.put("dictMatchingFlagTypes", MatchingUtils.mulityStringOptionForSearch(matchingFlagType, ","));
		}
		// 银行
		String bank = search.getAccountBank();
		if (!StringUtils.isEmpty(bank)) {
			tempex.put("accountBankList", MatchingUtils.mulityStringOptionForSearch(bank, ","));
		}
		// 省市
		String city = search.getCity();
		if (!StringUtils.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			tempex.put("city", c);
		}
		// 待推荐匹配状态
		tempex.put("matchingStatusLst", search.getMatchingStatusLst());
		tempex.put("lendStatusLst", search.getLendStatusLst());// 出借状态集合
		
		// ****** 可多选检索条件整形  END ******
		
		//		/*// 排除正在自动匹配的信息
		//		tempex.put("filtermatchingIds", creditorAidManager.getFiltermatchingIds());*/
		pageBounds.setCountBy("matchingId");
		PageList<MatchingCreditorView> pageList = (PageList<MatchingCreditorView>) matchingCreditorDao.findPage(tempex,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 非月满盈 首期提交
	 * 2016年4月13日
	 * By 柳慧
	 * @param matchingId
	 * @param lendCode
	 * @param creditValueIds
	 * @param tjmoneys
	 * @param ceContext
	 * @throws Exception 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void fymySqMatchingSubmit(String matchingId,
			String lendCode, String creditValueIds, String tjmoneys,CERequestContext ceContext,String verTime,String borrowVerTimes) {
		String [] creditValueIdArray = creditValueIds.split("-"); // 可用债权ID集合
		String [] tjmoneyArray = tjmoneys.split("-");// 推荐额度集合
		String [] borrowVerTimeArray = borrowVerTimes.split(";");
		MatchingCreditorEx  mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		
		User user = UserUtils.getUser(UserUtils.getUserId());
		loanphaseCurDao.deleteByLendCode(lendCode);
		BigDecimal phaseAmount = BigDecimal.ZERO;// 本期应还总本息
		BigDecimal phaseInterestCur = BigDecimal.ZERO; // 本期应还总利息
		String creditIdAll =""; // 可用债权总数
		Map<String,String>  oldCreditValueIdCard= new HashMap<String,String>();//  已匹配的债权的省份证
		Map mFlag = new HashMap();
		for(int i = 0; i<creditValueIdArray.length;i++){
			String creditValueId = creditValueIdArray[i];// 可用债权ID
			String tjmoney = tjmoneyArray[i].replaceAll(",", ""); // 推荐额度
			String borrowVerTime = borrowVerTimeArray[i]; // 债权更新时间
			BigDecimal tjmoneyB = new BigDecimal(tjmoney);
			String id = IdGen.uuid();// 债权交易主键
			Borrow borrow = borrowDao.get(creditValueId);// 通过债权ID  获取可用债权实体
			if(oldCreditValueIdCard.containsKey(borrow.getLoanIdcard())){
				throw new ServiceException(borrow.getLoanName()+"债权存在相同的债权人，债权匹配失败！");
			}else{
				oldCreditValueIdCard.put(borrow.getLoanIdcard(),borrow.getLoanIdcard());
			}
			BigDecimal loanCreditValue = borrow.getLoanCreditValue();
			//如果loanCreditValue.subtract(tjmoneyB) < 0,且绝对值 > 0.005,可用债权不足
			if(loanCreditValue.subtract(tjmoneyB).compareTo(new BigDecimal("0")) == -1 && loanCreditValue.subtract(tjmoneyB).abs().compareTo(new BigDecimal("0.005")) ==1){
				throw new ServiceException(borrow.getLoanName()+"的可用债权不足！债权匹配失败！");
			}
			/** 添加债权交易信息  **/
			
			//设置匹配标识     dict_loan_type：信借(0)、车借(1)、房借(2)
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("0")){
				mFlag.put("0", "0");	
			}
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("1")){
				mFlag.put("1", "1");	
			}
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("2")){
				mFlag.put("2", "2");	
			}
			
			// 债权交易实体
			CreditorTrade ct =new  CreditorTrade();
			ct.setTradeId(id);
			ct.setMatchingId(mc.getMatchingId());
			ct.setLendCode(mc.getLendCode());
			ct.setCreditNode(Node.KYZQ.value);// 债权节点；债权池；月满盈可用债权池
			ct.setCreditCode(creditValueId);// 债权ID
			// 债权交易预计到期时间 、债权交易实际到期时间 等于可用债权中最后一期还款日或待推荐信息表中结束日日期两者选择最小
			if(mc.getMatchingEndday()==null || DateUtils.dateBefore(mc.getMatchingEndday(),borrow.getLoanBackmoneyLastday())){
				ct.setTradeExpectDay(new Timestamp(borrow.getLoanBackmoneyLastday().getTime()));// 债权交易预计到期时间
				ct.setTradeActualDay(new Timestamp(borrow.getLoanBackmoneyLastday().getTime()));// 债权交易实际到期时间
			}else{
				ct.setTradeExpectDay(new Timestamp(mc.getMatchingEndday().getTime()));// 债权交易预计到期时间
				ct.setTradeActualDay(new Timestamp(mc.getMatchingEndday().getTime()));// 债权交易实际到期时间
			}
			
			BigDecimal ppje = new BigDecimal(tjmoney);
			ct.setTradeMateMoney(ppje);// 匹配金额
			ct.setTradeMateMoneyPercent(ppje.divide(new BigDecimal( borrow.getLoanCreditValue().toString()),15,BigDecimal.ROUND_HALF_UP).toString());// 匹配金额所占百分比  匹配金额除以可用债权金额
			ct.setDictTradeCreditStatus(CreditTradestate.KSHKZ.value); // 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
			ct.setCreateBy(user.getId());
			ct.setCreateTime(new Date());
			ct.setModifyBy(user.getId());
			ct.setModifyTime(new Date());
			ct.setTradePassDate(new Timestamp(new Date().getTime()));
			// 债权交易添加
			creditorTradeDao.insert(ct);
			/**　添加分期收益表信息**/
			Loanphase loanphase  = new Loanphase();
			loanphase.setPhaseId(IdGen.uuid());
			loanphase.setMatchingId(matchingId);
			loanphase.setLendCode(lendCode);
			loanphase.setLoanCode(creditValueId);//借款ID
			
			loanphase.setPhaseNumber(mc.getMatchingNow());// 期数 1.2.3以此类推
			int periods = borrow.getLoanMonthsSurplus().intValue();//剩余期数
			try {
				periods = BackMoneyUtil.getMonthSpace(mc.getMatchingInterestStart(), borrow.getLoanBackmoneyFirday(),borrow.getLoanMonths());
			} catch (ParseException e1) {
				throw new ServiceException(mc.getMatchingId()+"计算剩余期数失败,请重新匹配或与维护人员联系");
			}
			//剩余期数为0时,变更为1.防止出现 除法运算错误
			if(0 == periods){
				periods = 1;
			}
			BigDecimal  loanMonthsSurplus = new BigDecimal(String.valueOf(periods));// 剩余期数
			// 本期应还本金 = 推荐金额 /剩余期数 
			BigDecimal bqyhbj = tjmoneyB.divide(loanMonthsSurplus,5,BigDecimal.ROUND_HALF_UP);
			// 匹配利率
			BigDecimal cpll =borrow.getLoanMonthRate();
			// 本期应还利息  = 匹配金额*匹配利率/100/账单日周期天数*计息天数
			int jxts = 0 ;// 计息天数
			int zdrzq = 0;// 账单日周期天数
			try {
				jxts = CreditorUtils.daysBetween(mc.getMatchingInterestStart(),CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()))+1;
				zdrzq =getzqdays(mc.getMatchingInterestStart());				
			} catch (ParseException e) {
				throw new ServiceException(mc.getMatchingId()+"账单周期计算失败,请重新匹配或与维护人员联系");
			}
			// 本期利息
			BigDecimal bqlx = tjmoneyB
							  .multiply(new BigDecimal(cpll.toString()))
							  .multiply(new BigDecimal(String.valueOf(jxts)))
							  .divide(new BigDecimal("100"),5,BigDecimal.ROUND_HALF_UP)
							  .divide(new BigDecimal(String.valueOf(zdrzq)),5 ,BigDecimal.ROUND_HALF_UP);
			loanphase.setPhaseAmount(bqlx.add(bqyhbj));// 本期应还本息
			loanphase.setPhaseInterestCur(bqlx);// 本期应还利息
			loanphase.setPhasePrincipalCur(bqyhbj);// 本期应还本金
			// 本期还款结束后，剩余未还本金 = 推荐金额-本期应还本金
			loanphase.setPhasePrincipalSurplus(tjmoneyB.subtract(bqyhbj));// 本期还款结束后，剩余未还本金
			loanphase.setPhaseRepaySign(Constant.PHASE_REPAY_SIGN);// 是否已还款 默认已还款
			// 真正还款时间 首期还款日 月加（借款期数-剩余借款期数）
			Calendar  firDay = Calendar.getInstance();
			firDay.setTime(borrow.getLoanBackmoneyFirday());
			 firDay.add(Calendar.MONTH, borrow.getHkqs());
			loanphase.setPhaseRepaydateActual(firDay.getTime());
			// 截止到本期已还本息     截止到本期已还本息+本期应还本息
			loanphase.setPhaseBackCount( bqlx.add(bqyhbj));// 截止到本期已还本息
			// 截止到本期已还本金    次期：  截止到本期已还本金+本期应还本金
			loanphase.setPhaseBackPrincipal(bqyhbj);// 截止到本期已还本金
			// 截止到本期已还利息    次期： 截止到本期已还利息+本期应还利息
			loanphase.setPhaseBackInterest( bqlx);// 截止到本期已还利息
			// 本期有效期-开始时间 首期：起息日期     次期 账单日
			loanphase.setPhaseBegindayCur(mc.getMatchingInterestStart());// 本期有效期-开始时间
			// 本期有效期-截止时间  对应账单日 
			loanphase.setPhaseEnddayCur(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));// 本期有效期-截止时间
			loanphase.setPhaseMateId(id);// 匹配id
			// 匹配总期数  可用债权表中剩余借款期数 或待推荐债权信息表中剩余期数 取最小
			int dtjsyqs =  mc.getMatchingTotal()-mc.getMatchingNow();// 待推荐剩余期数
			if(periods-dtjsyqs<0){
				loanphase.setPhaseMateNumber(periods);// 匹配总期数
			}else{
				loanphase.setPhaseMateNumber(dtjsyqs);// 匹配总期数
			}
			// 当前债权人剩余期数 = 可用债权表剩余期数-1
			loanphase.setPhaseNumberSurplus(periods-1);// 当前债权人剩余期数
			loanphase.setPhaseFreezeNextstatus("0");// 下期债权人是否冻结
			loanphase.setCreateBy(user.getId());
			loanphase.setCreateTime( new Date());
			loanphase.setModifyBy(user.getId());
			loanphase.setModifyTime(new Date());
			loanphase.setBillDay(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));
			loanphase.setPhaseDiscardStatus(ScrapState.BFQ.value);// 废弃状态
			loanphase.setPhaseReleaseStatus(com.creditharmony.core.fortune.type.CreditRelease.MSF.value);
			// 分期收益表添加
			LoanphaseDao.insert(loanphase);
			 phaseAmount = phaseAmount.add(loanphase.getPhaseAmount());// 本期应还总本息
			 phaseInterestCur =phaseInterestCur.add(loanphase.getPhaseInterestCur()); // 本期应还总利息
			 creditIdAll =creditIdAll+loanphase.getLoanCode()+",";
			addLoanphaseCur(loanphase);
			/** 更新可用债权列表　**/
			java.math.BigDecimal updateLloanCreditValue =borrow.getLoanCreditValue().subtract(new BigDecimal(tjmoney.toString()));
			borrow.setLoanCreditValue(updateLloanCreditValue);// 可用债权
			borrow.setModifyBy(user.getId());
			borrow.setModifyTime(new Date());
			borrow.setVerTime(borrowVerTime);
			// 更新可用债权
			int updateNum = borrowDao.update(borrow);
			if(updateNum==0){
				throw new ServiceException(borrow.getLoanName()+"的债权已经被更新，请重新操作");
			}
			addCreditorHisBorrow(mv,tjmoneyB,borrow);
		}
		addLoanphasePeriod(mv,phaseAmount,phaseInterestCur,creditIdAll);
		String payType = mv.getApplyPay(); // 支付方式
		String productCode  =  mc.getProductCode(); // 产品编号
		if (!mc.getMatchingStatus().equals(MatchingStatus.CXCP.value)) { // 如果不等于撤销重匹插入划扣、回款信息
			if (payType.equals(PayMent.HK.value)|| payType.equals(PayMent.ZJTG.value)) { // 如果付款方式为划扣或者资金托管
				if (!ProductCode.XHB.value.equals(productCode)
						&& !ProductCode.XHBA.value.equals(productCode)
						&& !ProductCode.XHBC.value.equals(productCode)) {
					/** 划扣插入 **/
					deductPoolAdd(mv, payType);
					/*if (payType.equals(PayMent.HK.value)) {
						Contract contract = new Contract();
						contract.setLendCode(lendCode);
						contractManager.updateContractUseDay(contract);
					}*/
					 
				} else { // 如果是信和宝 信和宝A 信和宝c
					MatchingCreditorEx paramMc = new MatchingCreditorEx();
					paramMc.setLendCode(mv.getLendCode());
					paramMc.setMatchingFirstdayFlag(BillState.SQ.value);
					paramMc.setMatchingStatus(MatchingStatus.YTJ.value);
					int sqcount = matchingCreditorDao.getCountByEx(paramMc);
					if (sqcount == 0) { // 如果是第一个首期 插入划扣信息 反之不插入
						deductPoolAdd(mv, payType);
						/*if (payType.equals(PayMent.HK.value)) {
							Contract contract = new Contract();
							contract.setLendCode(lendCode);
							contractManager.updateContractUseDay(contract);
						}*/
						 
					}
				}
			}
			if (payType.equals(PayMent.NBZZ.value)|| payType.equals(PayMent.CGBFNZ.value)) { // 如果付款方式为内部转账或者成功部分内转
				loanApplyUpdate(lendCode, user);
				if (!ProductCode.XHB.value.equals(productCode)
						&& !ProductCode.XHBA.value.equals(productCode)
						&& !ProductCode.XHBC.value.equals(productCode)) {
					/** 划扣插入 **/
					deductPoolAdd(mv, payType);
					backPoolCz(lendCode, mv, user, payType);
					/** 发送短信 **/
					smsSend(mv);
					Contract contract = new Contract();
					contract.setLendCode(lendCode);
					contractManager.updateContractUseDay(contract);

				} else { // 如果是信和宝 信和宝A 信和宝c
					MatchingCreditorEx paramMc = new MatchingCreditorEx();
					paramMc.setLendCode(mv.getLendCode());
					paramMc.setMatchingFirstdayFlag(BillState.SQ.value);
					paramMc.setMatchingStatus(MatchingStatus.YTJ.value);
					int sqcount = matchingCreditorDao.getCountByEx(paramMc);
					if (sqcount == 0) {
						/** 划扣插入 **/
						deductPoolAdd(mv, payType);
						backPoolCz(lendCode, mv, user, payType);
						/** 发送短信 **/
						smsSend(mv);
						Contract contract = new Contract();
						contract.setLendCode(lendCode);
						contractManager.updateContractUseDay(contract);
					}
				}
			}
			if (payType.equals(PayMent.SHNZ.value)) { // 赎回内转
				loanApplyUpdate(lendCode, user);
				if (!ProductCode.XHB.value.equals(productCode)
						&& !ProductCode.XHBA.value.equals(productCode)
						&& !ProductCode.XHBC.value.equals(productCode)) {
					/** 划扣插入 **/
					deductPoolAdd(mv, payType);
					backPoolCz(lendCode, mv, user, payType);

				} else { // 如果是信和宝 信和宝A 信和宝c
					MatchingCreditorEx paramMc = new MatchingCreditorEx();
					paramMc.setLendCode(mv.getLendCode());
					paramMc.setMatchingFirstdayFlag(BillState.SQ.value);
					paramMc.setMatchingStatus(MatchingStatus.YTJ.value);
					int sqcount = matchingCreditorDao.getCountByEx(paramMc);
					if (sqcount == 0) {
						deductPoolAdd(mv, payType);
						backPoolCz(lendCode, mv, user, payType);
					}
				}
			}
		}
		/** 更新待债权推荐信息表 **/
		mc.setMatchingMatchMoney(mc.getMatchingBorrowQuota());
		mc.setBeforMatchingStatus(mc.getMatchingStatus());	// 修改前匹配状态
		// 更改债权状态  更改为已订购
		mc.setMatchingStatus(MatchingStatus.YTJ.value);
		mc.setDictMatchingFileStatus(FilecpState.WHC.value);
		mc.setMatchingPayStatus(SettleStats.WJS.value);
		mc.setModifyBy(user.getId());
		mc.setMatchingCossTime(new Date());
		mc.setModifyTime(new Date());
		mc.setVerTime(verTime);
		int updateNum = matchingCreditorDao.updateByPrimaryKeySelective(mc);
		if(updateNum==0){
			throw new ServiceException("待推荐债权已被操作或已匹配");
		}
		
		/** 更新待债权推荐统计 **/
		creditorStatisticsManager.updateStatistic(
				UserUtils.getUserId(), 1, Constant.CREDIT_DONE, BillState.SQ.value,lendCode);
		//文件制作
		Attachment disAttachment = new Attachment();
		disAttachment.setAttaTableId(mv.getMatchingId());
		disAttachment.setAttaFileOwner("t_tz_matching_creditor");
		disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
		this.makeFile(mv, ceContext,disAttachment,null);
		checkManager.addCheck(lendCode, "待推荐匹配成功！当前期数为："+mc.getMatchingNow(), TracesType.ZQ_PP.getName());
		
		//内转成单接口,往CRM推送成单记录
		try {
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = (LoanApply) this.loanApplyDao.get(loanApply);
			if(PayMent.NBZZ.value.equals( loanApply.getPayType() )){	//付款方式为内转，不会走划扣，债匹完算成单
				tripleInvestSuccService.investSucc("", lendCode, "I");
			}else{
				logger.debug("其他付款方式，在债匹环节不记录成单！！");
			}
			
			//修改loanApply中MatchingFlag值  开始
			//设置匹配标识     dict_loan_type：信借(0)、车借(1)、房借(2)
			//tz.t_tz_loan_apply表中的matching_flag值：全信借(0)、全车借(1)、混合(2)
			if(loanApply != null){
				//matching_flag为空
				if(loanApply.getMatchingFlag()==null){
					if(mFlag.size()!=0){
						if(mFlag.size()>1){
							loanApply.setMatchingFlag("2");
						}
						
			           if(mFlag.size()==1){
			        	   for(Object o : mFlag.keySet()){    
			    		       if("0".equals(mFlag.get(o).toString())){
			    		    	   loanApply.setMatchingFlag("0");
			    		       }
			    		       if("1".equals(mFlag.get(o).toString())){
			    		    	   loanApply.setMatchingFlag("1");
			    		       }
			    		       if("2".equals(mFlag.get(o).toString())){
			    		    	   loanApply.setMatchingFlag("2");
			    		       }
			    		     }
						}
					}
				}else{
					//matching_flag不为空
					if(!"2".equals(loanApply.getMatchingFlag())){
						if(mFlag.size()!=0){
							if(mFlag.size()>1){
								loanApply.setMatchingFlag("2");
							}
				           if(mFlag.size()==1){
				        	   for(Object o : mFlag.keySet()){    
				    		       if("0".equals(mFlag.get(o).toString())){
				    		    	  if("0".equals(loanApply.getMatchingFlag())){
				    		    		  loanApply.setMatchingFlag("0");
				    		    	  }else{
				    		    		  loanApply.setMatchingFlag("2");
				    		    	  }
				    		       }
				    		       if("1".equals(mFlag.get(o).toString())){
				    		    	   if("1".equals(loanApply.getMatchingFlag())){
				    		    		   loanApply.setMatchingFlag("1");
					    		    	  }else{
					    		    		  loanApply.setMatchingFlag("2");
					    		    	  }
				    		       }
				    		       if("2".equals(mFlag.get(o).toString())){
				    		    	   loanApply.setMatchingFlag("2");
				    		       }
				    		     }
							}
						}
					}else{
						loanApply.setMatchingFlag("2");
					}
				}
				loanApply.preUpdate();
				loanApplyDao.update(loanApply);
				//修改loanApply中MatchingFlag值  结束
				}
			
		} catch (Exception e) {
			logger.debug("内转记录成单失败！！");
		}
	}
	

	/**
	 * 添加债权管理记录 可用债权
	 * 2016年5月16日
	 * By 柳慧
	 * @param mv
	 * @param tjmoneyB
	 * @param borrow
	 */
	private void addCreditorHisBorrow(MatchingCreditorView mv, BigDecimal tjmoneyB,
			Borrow borrow) {
		BigDecimal loanCreditValue = borrow.getLoanCreditValue();
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setMatchingId(mv.getMatchingId());
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.KYZQ.value);
		creditorHis.setNodeId(borrow.getCreditValueId());
		creditorHis.setOperateType(OperateType.PPTJ.value);
		creditorHis.setBeforMoney(loanCreditValue.add(tjmoneyB));
		creditorHis.setOperateMoney(tjmoneyB);
		creditorHis.setOperateTime(new Date());
		//误差
		BigDecimal wucha = new BigDecimal("0.005");
		BigDecimal init = new BigDecimal("0");
		//如果 loanCreditValue 的值为[-0.005,0)时，修改其值为 0
		if(loanCreditValue.compareTo(init) == -1 
				&& (loanCreditValue.abs().compareTo(wucha) == -1 
				|| loanCreditValue.abs().compareTo(wucha) == 0)){
			creditorHis.setAfterMoney(init);
		}else{
			creditorHis.setAfterMoney(loanCreditValue);
		}
		creditorHis.setOperator(UserUtils.getUserId());
		creditorHis.setCreateBy(UserUtils.getUserId());
		creditorHis.setCreateTime(new Date());	
		creditorHisDao.insert(creditorHis);
	}

	/**
	 * 非月满盈 次期提交
	 * 2016年4月13日
	 * By 柳慧
	 * @param matchingId
	 * @param lendCode
	 * @param creditValueIds
	 * @param tjmoneys
	 * @param ceContext
	 * @throws Exception 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void fymyFsqMatchingSubmit(String matchingId,
			String lendCode, String creditValueIds, String tjmoneys,CERequestContext ceContext,String verTime,String borrowVerTimes) {
		String [] creditValueIdArray = creditValueIds.split("-"); // 可用债权ID集合
		String [] tjmoneyArray = tjmoneys.split("-");// 推荐额度集合
		String  borrowVerTimeArray[] =  borrowVerTimes.split(";");
		MatchingCreditorEx  mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		User user = UserUtils.getUser(UserUtils.getUserId());
		loanphaseCurDao.deleteByLendCode(lendCode);
			/** 插入上期分期收益信息    **/
		Loanphase loanphaseparam  = new Loanphase();
		loanphaseparam.setLendCode(lendCode);
		//废弃状态为不废弃    phase_discard_status !=#{phaseDiscardStatus}
		loanphaseparam.setPhaseDiscardStatus(ScrapState.FQ.value);
		loanphaseparam.setPhaseNumber(mc.getMatchingNow()-1);
		// 通过出借编号 查询出历史分期收益
		List<Loanphase> oldLoanphases = LoanphaseDao.findList(loanphaseparam);
		BigDecimal phaseAmount = BigDecimal.ZERO;// 本期应还总本息
		BigDecimal phaseInterestCurAll = BigDecimal.ZERO; // 本期应还总利息
		String creditIdAll =""; // 可用债权总数
		if(oldLoanphases!=null && oldLoanphases.size()>0){
			for(Loanphase oldlp :oldLoanphases){
				Integer LoanMonthsSurplus = oldlp.getPhaseNumberSurplus();// 剩余期数
				if(LoanMonthsSurplus!=0){
					Loanphase loanphase = (Loanphase)SerializationUtils.clone(oldlp);
					Borrow paramBo = new Borrow();
					paramBo.setCreditValueId(loanphase.getLoanCode());
					Borrow borrow = borrowDao.getByEenity(paramBo);
					loanphase.setPhaseId(IdGen.uuid());
					loanphase.setMatchingId(matchingId);
					loanphase.setLendCode(lendCode);
					loanphase.setPhaseNumber(mc.getMatchingNow());// 期数
					// 本期应还本金 = 剩余还款本金 /剩余期数
					BigDecimal bqyhbj = oldlp.getPhasePrincipalSurplus()
							.divide(new BigDecimal(
									String.valueOf(LoanMonthsSurplus)),5,BigDecimal.ROUND_HALF_UP);
					BigDecimal tempmoney = oldlp.getPhasePrincipalSurplus().subtract(bqyhbj); // （剩余资金-本期应还本金）
					BigDecimal cpll =borrow.getLoanMonthRate();
					// 本期应还利息 = 剩余本金*匹配利率/100
					BigDecimal phaseInterestCur =  oldlp.getPhasePrincipalSurplus().multiply(new BigDecimal( String.valueOf(cpll))).divide(new BigDecimal("100"),5,BigDecimal.ROUND_HALF_UP);
					loanphase.setPhaseAmount(phaseInterestCur.add(bqyhbj));// 本期应还本息
					loanphase.setPhaseInterestCur(phaseInterestCur);// 本期应还利息
					loanphase.setPhasePrincipalCur(bqyhbj);// 本期应还本金
					// 本期还款结束后，剩余未还本金 = 推荐金额-本期应还本金
					loanphase.setPhasePrincipalSurplus(tempmoney);// 本期还款结束后，剩余未还本金
					loanphase.setPhaseRepaySign(Constant.PHASE_REPAY_SIGN);// 是否已还款 默认已还款
					// 真正还款时间 首期还款日 月加（借款期数-剩余借款期数）
					Calendar  firDay = Calendar.getInstance();
					firDay.setTime(borrow.getLoanBackmoneyFirday());
					 firDay.add(Calendar.MONTH, borrow.getHkqs());
					loanphase.setPhaseRepaydateActual(firDay.getTime());
					// 真正还款时间
					// 截止到本期已还本息 次期：截止到本期已还本息+本期应还本息
					loanphase.setPhaseBackCount(oldlp.getPhaseBackCount()
							.add(phaseInterestCur.add(bqyhbj)));// 截止到本期已还本息
					// 截止到本期已还本金 首期为0 次期： 截止到本期已还本金+本期应还本金
					loanphase.setPhaseBackPrincipal(oldlp
							.getPhaseBackPrincipal().add(bqyhbj));// 截止到本期已还本金
					// 截止到本期已还利息 次期： 截止到本期已还利息+本期应还利息
					loanphase.setPhaseBackInterest(oldlp
							.getPhaseBackInterest().add(phaseInterestCur));// 截止到本期已还利息
					// 本期有效期-开始时间    起息日期
					loanphase.setPhaseBegindayCur(mc.getMatchingInterestStart());// 本期有效期-开始时间
					// 本期有效期-截止时间 对应账单日
				
					loanphase.setPhaseEnddayCur(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));// 本期有效期-截止时间
					/*// 匹配总期数 分期收益表中剩余借款期数 或待推荐债权信息表中剩余期数 取最小
					int dtjsyqs = mc.getMatchingTotal() - mc.getMatchingNow();// 待推荐剩余期数
					if (oldlp.getPhaseNumberSurplus() - dtjsyqs < 0) {
						loanphase.setPhaseMateNumber(oldlp
								.getPhaseNumberSurplus());// 匹配总期数
					} else {
						loanphase.setPhaseMateNumber(dtjsyqs);// 匹配总期数
					}*/
					//loanphase.setPhaseMateNumber(oldlp.getPhaseNumberSurplus());// 匹配总期数
					// 当前债权人剩余期数 = 可用债权表剩余期数
					loanphase.setPhaseNumberSurplus(oldlp
							.getPhaseNumberSurplus() - 1);// 当前债权人剩余期数
					// 债权是否已经释放
					loanphase.setPhaseFreezeNextstatus("0");// 下期债权人是否冻结
					loanphase.setCreateBy(user.getId());
					loanphase.setCreateTime(new Date());
					loanphase.setModifyBy(user.getId());
					loanphase.setModifyTime(new Date());
					loanphase.setBillDay(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));
					loanphase.setPhaseDiscardStatus(ScrapState.BFQ.value);// 废弃状态
					loanphase.setPhaseReleaseStatus(com.creditharmony.core.fortune.type.CreditRelease.MSF.value);
					// 分期收益表添加
					LoanphaseDao.insert(loanphase);
					phaseAmount = phaseAmount.add(loanphase.getPhaseAmount());// 本期应还总本息
					phaseInterestCurAll =phaseInterestCurAll.add(loanphase.getPhaseInterestCur()); // 本期应还总利息
				    creditIdAll =creditIdAll+loanphase.getLoanCode()+",";
					addLoanphaseCur(loanphase);
				}
			}
		}
		/** 新的匹配人信息插入    **/
		Map<String,String>  oldCreditValueIdCard= new HashMap<String,String>();//  已匹配的债权的省份证
		Map mFlag = new HashMap();
		for (int i = 0; i < creditValueIdArray.length; i++) {
			String creditValueId = creditValueIdArray[i];// 可用债权ID
			String tjmoney = tjmoneyArray[i].replaceAll(",", ""); // 推荐额度
			String borrowVerTime = borrowVerTimeArray[i];// 债权更新时间
			BigDecimal tjmoneyB = new BigDecimal(tjmoney);
			String id = IdGen.uuid();// 债权交易主键
			Borrow borrow = borrowDao.get(creditValueId);// 通过债权ID 获取可用债权实体
			if(oldCreditValueIdCard.containsKey(borrow.getLoanIdcard())){
				throw new ServiceException(borrow.getLoanName()+"债权存在相同的债权人，债权匹配失败！");
			}else{
				oldCreditValueIdCard.put(borrow.getLoanIdcard(),borrow.getLoanIdcard());
			}
			BigDecimal loanCreditValue = borrow.getLoanCreditValue();
			//如果loanCreditValue.subtract(tjmoneyB) < 0,且绝对值 > 0.005,可用债权不足
			if(loanCreditValue.subtract(tjmoneyB).compareTo(new BigDecimal("0")) == -1 && loanCreditValue.subtract(tjmoneyB).abs().compareTo(new BigDecimal("0.005")) ==1){
				throw new ServiceException(borrow.getLoanName()+"的可用债权不足，债权匹配失败！");
			}
			Map<String, String> paramLoanPhase = new HashMap<String, String>();
			paramLoanPhase.put("lendCode", lendCode);
			paramLoanPhase.put("creditValueId", creditValueId);
			/** 添加债权交易信息 **/			
			
			//设置匹配标识     dict_loan_type：信借(0)、车借(1)、房借(2)
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("0")){
				mFlag.put("0", "0");	
			}
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("1")){
				mFlag.put("1", "1");	
			}
			if(!borrow.getDictLoanType().isEmpty() && borrow.getDictLoanType().equals("2")){
				mFlag.put("2", "2");	
			}
			
			// 债权交易实体
			CreditorTrade ct =new  CreditorTrade();
			ct.setTradeId(id);
			ct.setMatchingId(mc.getMatchingId());
			ct.setLendCode(mc.getLendCode());
			ct.setCreditNode(Node.KYZQ.value);// 债权节点；债权池；月满盈可用债权池
			ct.setCreditCode(creditValueId);// 债权ID
			// 债权交易预计到期时间 、债权交易实际到期时间 等于可用债权中最后一期还款日或待推荐信息表中结束日日期两者选择最小
			if(mc.getMatchingEndday()==null || DateUtils.dateBefore(mc.getMatchingEndday(),borrow.getLoanBackmoneyLastday())){
				ct.setTradeExpectDay(new Timestamp(borrow.getLoanBackmoneyLastday().getTime()));// 债权交易预计到期时间
				ct.setTradeActualDay(new Timestamp(borrow.getLoanBackmoneyLastday().getTime()));// 债权交易实际到期时间
			}else{
				ct.setTradeExpectDay(new Timestamp(mc.getMatchingEndday().getTime()));// 债权交易预计到期时间
				ct.setTradeActualDay(new Timestamp(mc.getMatchingEndday().getTime()));// 债权交易实际到期时间
			}
			BigDecimal ppje = new BigDecimal(tjmoney);
			ct.setTradeMateMoney(ppje);// 匹配金额
			ct.setTradeMateMoneyPercent(ppje.divide(new BigDecimal( borrow.getLoanCreditValue().toString()),15,BigDecimal.ROUND_HALF_UP).toString());// 匹配金额所占百分比  匹配金额除以可用债权金额
			ct.setDictTradeCreditStatus(CreditTradestate.KSHKZ.value); // 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
			ct.setCreateBy(user.getId());
			ct.setCreateTime(new Date());
			ct.setModifyBy(user.getId());
			ct.setModifyTime(new Date());
			ct.setTradePassDate(new Timestamp(new Date().getTime()));
			// 债权交易添加
			creditorTradeDao.insert(ct);
			/** 　添加分期收益表信息 **/
			Loanphase loanphase = new Loanphase();
			loanphase.setPhaseId(IdGen.uuid());
			loanphase.setMatchingId(matchingId);
			loanphase.setLendCode(lendCode);
			loanphase.setLoanCode(creditValueId);// 借款ID
			loanphase.setPhaseNumber(mc.getMatchingNow());// 期数
			int periods = borrow.getLoanMonthsSurplus().intValue();//剩余期数
			try {
				periods = BackMoneyUtil.getMonthSpace(mc.getMatchingInterestStart(), borrow.getLoanBackmoneyFirday(),borrow.getLoanMonths());
			} catch (ParseException e1) {
				throw new ServiceException(mc.getMatchingId()+"计算剩余期数失败,请重新匹配或与维护人员联系");
			}
			//剩余期数为0时,变更为1.防止出现 除法运算错误
			if(0 == periods){
				periods = 1;
			}
			BigDecimal  loanMonthsSurplus = new BigDecimal(String.valueOf(periods));// 剩余期数
			// 本期应还本金 = 推荐金额 /剩余期数
			BigDecimal bqyhbj =tjmoneyB
					.divide(loanMonthsSurplus,5,BigDecimal.ROUND_HALF_UP);
			BigDecimal cpll =borrow.getLoanMonthRate();
			// 本期应还利息 = 推荐金额*匹配利率/100
			BigDecimal phaseInterestCur = tjmoneyB.multiply(cpll).divide(new BigDecimal("100"),5,BigDecimal.ROUND_HALF_UP);
			loanphase.setPhaseAmount(phaseInterestCur.add(bqyhbj));// 本期应还本息
			loanphase.setPhaseInterestCur(phaseInterestCur);// 本期应还利息
			loanphase.setPhasePrincipalCur(bqyhbj);// 本期应还本金
			// 本期还款结束后，剩余未还本金 = 推荐金额-本期应还本金
			loanphase.setPhasePrincipalSurplus(tjmoneyB.subtract(bqyhbj));// 本期还款结束后，剩余未还本金
			loanphase.setPhaseRepaySign(Constant.PHASE_REPAY_SIGN);// 是否已还款 默认已还款
			// 真正还款时间 首期还款日 月加（借款期数-剩余借款期数）
			Calendar  firDay = Calendar.getInstance();
			firDay.setTime(borrow.getLoanBackmoneyFirday());
			 firDay.add(Calendar.MONTH, borrow.getHkqs());
			loanphase.setPhaseRepaydateActual(firDay.getTime());
			// 真正还款时间
			// 截止到本期已还本息 次期：截止到本期已还本息+本期应还本息
			loanphase.setPhaseBackCount(phaseInterestCur.add(bqyhbj));// 截止到本期已还本息
			// 截止到本期已还本金   次期： 截止到本期已还本金+本期应还本金
			loanphase.setPhaseBackPrincipal(bqyhbj);// 截止到本期已还本金
			// 截止到本期已还利息 首期：0 次期： 截止到本期已还利息+本期应还利息
			loanphase.setPhaseBackInterest(phaseInterestCur);// 截止到本期已还利息
			// 本期有效期-开始时间 首期：起息日期     次期 账单日
			loanphase.setPhaseBegindayCur(mc.getMatchingInterestStart());// 本期有效期-开始时间
			// 本期有效期-截止时间 对应账单日
			loanphase.setPhaseEnddayCur(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));// 本期有效期-截止时间
			loanphase.setPhaseMateId(id);// 匹配id
			// 匹配总期数  可用债权表中剩余借款期数 或待推荐债权信息表中剩余期数 取最小
			int dtjsyqs =  mc.getMatchingTotal()-mc.getMatchingNow();// 待推荐剩余期数
			if(periods-dtjsyqs<0){
				loanphase.setPhaseMateNumber(periods);// 匹配总期数
			}else{
				loanphase.setPhaseMateNumber(dtjsyqs);// 匹配总期数
			}
			// 当前债权人剩余期数 = 可用债权表剩余期数
			loanphase.setPhaseNumberSurplus(periods-1);// 当前债权人剩余期数
			loanphase.setPhaseDiscardStatus(ScrapState.BFQ.value);// 废弃状态
			loanphase.setPhaseReleaseStatus(com.creditharmony.core.fortune.type.CreditRelease.MSF.value);
			// 债权是否已经释放
			loanphase.setPhaseFreezeNextstatus("0");// 下期债权人是否冻结
			loanphase.setCreateBy(user.getId());
			loanphase.setCreateTime(new Date());
			loanphase.setModifyBy(user.getId());
			loanphase.setModifyTime(new Date());
			// 分期收益表添加
			loanphase.setBillDay(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));
			LoanphaseDao.insert(loanphase);
			addLoanphaseCur(loanphase);
			/** 更新可用债权列表　 **/
			java.math.BigDecimal updateLloanCreditValue =borrow.getLoanCreditValue().subtract(new BigDecimal(tjmoney.toString()));
			borrow.setLoanCreditValue(updateLloanCreditValue);// 可用债权
			borrow.setModifyBy(user.getId());
			borrow.setModifyTime(new Date());
			borrow.setVerTime(borrowVerTime);
			// 更新可用债权
			int updateNum = borrowDao.update(borrow);
			if(updateNum==0){
				throw new ServiceException(borrow.getLoanName()+"的债权已经被更新，请重新操作");
			}
			// 添加债权管理记录
			addCreditorHisBorrow(mv,tjmoneyB,borrow);
			phaseAmount = phaseAmount.add(loanphase.getPhaseAmount());// 本期应还总本息
			phaseInterestCurAll =phaseInterestCurAll.add(loanphase.getPhaseInterestCur()); // 本期应还总利息
			creditIdAll =creditIdAll+loanphase.getLoanCode()+",";
		}
		addLoanphasePeriod(mv,phaseAmount,phaseInterestCurAll,creditIdAll);
		/** 更新待债权推荐信息表 **/
		mc.setMatchingMatchMoney(mc.getMatchingBorrowQuota());
		mc.setBeforMatchingStatus(mc.getMatchingStatus());	// 修改前匹配状态
		// 更改债权状态 更改为已订购
		mc.setMatchingStatus(MatchingStatus.YTJ.value);
		mc.setDictMatchingFileStatus(FilecpState.WHC.value);
		mc.setMatchingPayStatus(SettleStats.WJS.value);
		mc.setModifyBy(user.getId());
		mc.setMatchingCossTime(new Date());
		mc.setModifyTime(new Date());
		mc.setVerTime(verTime);
		mc.setUpdatematchingStatus(MatchingStatus.YTJ.value);
		int updateNum = matchingCreditorDao.updateByPrimaryKeySelective(mc);
		if(updateNum==0){
			throw new ServiceException("待推荐债权已被操作");
		}
		/** 更新待债权推荐统计 **/
		creditorStatisticsManager.updateStatistic(
				UserUtils.getUserId(), 1, Constant.CREDIT_DONE, BillState.FSQ.value,lendCode);
		/*Contract contract = new Contract();
		contract.setLendCode(lendCode);
		contractManager.updateContractUseDay(contract);*/
		// 删除该期过往的债权文件
		Attachment disAttachment = new Attachment();
		disAttachment.setAttaTableId(mv.getMatchingId());
		disAttachment.setAttaFileOwner("t_tz_matching_creditor");
		disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
		this.makeFile(mv, ceContext, disAttachment,null);
		checkManager.addCheck(lendCode, "待推荐匹配成功！当前期数为："+mc.getMatchingNow(), TracesType.ZQ_PP.getName());
		
		//内转成单接口,往CRM推送成单记录
		try {
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = (LoanApply) this.loanApplyDao.get(loanApply);
			if(PayMent.NBZZ.value.equals( loanApply.getPayType() )){	//付款方式为内转，不会走划扣，债匹完算成单
				//根据榕生和马琳反馈 非首批提交债权不向CRM发送成单记录 暂时注释代码 20170302 张新民
				//tripleInvestSuccService.investSucc("", lendCode, "I");
			}else{
				logger.debug("其他付款方式，在债匹环节不记录成单！！");
			}
			
			//修改loanApply中MatchingFlag值  开始
			//设置匹配标识     dict_loan_type：信借(0)、车借(1)、房借(2)
			//tz.t_tz_loan_apply表中的matching_flag值：全信借(0)、全车借(1)、混合(2)
			if(loanApply != null){
			//matching_flag为空
			if(loanApply.getMatchingFlag()==null){
				if(mFlag.size()!=0){
					if(mFlag.size()>1){
						loanApply.setMatchingFlag("2");
					}
					
		           if(mFlag.size()==1){
		        	   for(Object o : mFlag.keySet()){    
		    		       if("0".equals(mFlag.get(o).toString())){
		    		    	   loanApply.setMatchingFlag("0");
		    		       }
		    		       if("1".equals(mFlag.get(o).toString())){
		    		    	   loanApply.setMatchingFlag("1");
		    		       }
		    		       if("2".equals(mFlag.get(o).toString())){
		    		    	   loanApply.setMatchingFlag("2");
		    		       }
		    		     }
					}
				}
			}else{
				//matching_flag不为空
				if(!"2".equals(loanApply.getMatchingFlag())){
					if(mFlag.size()!=0){
						if(mFlag.size()>1){
							loanApply.setMatchingFlag("2");
						}
			           if(mFlag.size()==1){
			        	   for(Object o : mFlag.keySet()){    
			    		       if("0".equals(mFlag.get(o).toString())){
			    		    	  if("0".equals(loanApply.getMatchingFlag())){
			    		    		  loanApply.setMatchingFlag("0");
			    		    	  }else{
			    		    		  loanApply.setMatchingFlag("2");
			    		    	  }
			    		       }
			    		       if("1".equals(mFlag.get(o).toString())){
			    		    	   if("1".equals(loanApply.getMatchingFlag())){
			    		    		   loanApply.setMatchingFlag("1");
				    		    	  }else{
				    		    		  loanApply.setMatchingFlag("2");
				    		    	  }
			    		       }
			    		       if("2".equals(mFlag.get(o).toString())){
			    		    	   loanApply.setMatchingFlag("2");
			    		       }
			    		     }
						}
					}
				}else{
					loanApply.setMatchingFlag("2");
				}
			}
			loanApply.preUpdate();
			loanApplyDao.update(loanApply);
			//修改loanApply中MatchingFlag值  结束
			}	
		} catch (Exception e) {
			logger.debug("内转记录成单失败！！");
		}
	}

	/**
	 * 内部转账操作
	 * 2016年4月30日
	 * By 柳慧
	 * @param lendCode               
	 * @param mv
	 * @param user 
	 * @ payType 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void backPoolCz(String lendCode, MatchingCreditorView mv,
			User user,String payType) {
		if(payType.equals(PayMent.NBZZ.value) || payType.equals(PayMent.CGBFNZ.value)){ // 如果付款方式为内部转账 或者成功部分内转
			/** 回款池数据操作 **/
			HashMap<String,Object> transMap = new HashMap<String,Object>();
			transMap.put("childLendCode", lendCode);
			List<TransferRelation> trLst = transferRelationDao.findList(transMap);
			if(trLst!=null && trLst.size()>0){
				for(TransferRelation trion :trLst){
					String lendCodeNew = trion.getLendCodeParent();// 出借编号
					BigDecimal transferMoney =trion.getTransferMoney(); // 内转金额
					BigDecimal backMoney = trion.getBackMoney();// 回款金额
					String transferType = trion.getBackMoneyType(); // 回款类型
					/** 根据出借编号修改内转金额  **/
					LoanApply loanApplynew = loanApplyDao.getLoanApplyByCode(lendCodeNew);
					loanApplynew.setTransferMoney(transferMoney);
					loanApplynew.setModifyBy(user.getId());
					loanApplynew.setModifyTime(new Date());
					loanApplyDao.update(loanApplynew);
					/** 根据出借编号更新回款池 */
					BackMoneyPool backMoneyPool =  backMoneyPoolDao.getByLendCode(lendCodeNew);
					backMoneyPool.setBackMoney(backMoney);
					backMoneyPool.setBackActualbackMoney(backMoney);
					backMoneyPool.setBackMoneyType(transferType);
					backMoneyPool.setModifyBy(user.getId());
					backMoneyPool.setModifyTime(new Date());
					backMoneyPoolDao.update(backMoneyPool);
					/** 更新内转关系表 **/
					trion.setModifyBy(user.getId());
					trion.setModifyTime(new Date());
					trion.setTransferState(TransferState.YNZ.getKey());
					transferRelationDao.update(trion);
				}
				/** 插入新的回款信息 **/
				backMoneyPoolAdd(lendCode, mv, user);
			}
		}else if(payType.equals(PayMent.SHNZ.value)){ // 赎回内转
			/** 回款池数据操作 **/
			HashMap<String,Object> transMap = new HashMap<String,Object>();
			transMap.put("childLendCode", lendCode);
			List<TransferRelation> trLst = transferRelationDao.findList(transMap);
			if(trLst!=null && trLst.size()>0){
				for(TransferRelation trion :trLst){
					String lendCodeNew = trion.getLendCodeParent();// 出借编号
					String transferMoney =trion.getTransferMoney().toString(); // 内转金额
					/** 根据出借编号修改内转金额  **/
					LoanApply loanApplynew = loanApplyDao.getLoanApplyByCode(lendCodeNew);
					loanApplynew.setTransferMoney(new BigDecimal(transferMoney));
					loanApplynew.setModifyBy(user.getId());
					loanApplynew.setModifyTime(new Date());
					loanApplyDao.update(loanApplynew);
					/** 更新内转关系表 **/
					trion.setModifyBy(user.getId());
					trion.setModifyTime(new Date());
					trion.setTransferState(TransferState.YNZ.getKey());
					transferRelationDao.update(trion);
				}
				backMoneyPoolAdd(lendCode, mv, user);
			}
		}
	}

	/**
	 * 插入回款
	 * 2016年4月30日
	 * By 柳慧
	 * @param lendCode
	 * @param mv
	 * @param user 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void backMoneyPoolAdd(String lendCode, MatchingCreditorView mv,
			User user) {
		BackMoneyPool backMoneyPoolnew = new BackMoneyPool();
		backMoneyPoolnew.setBackmId(IdGen.uuid());
		backMoneyPoolnew.setLendCode(lendCode);
		BigDecimal backMoney = creditorAidManager.getBackMoney(mv);
		backMoneyPoolnew.setBackMoney(backMoney);
		backMoneyPoolnew.setBackActualbackMoney(backMoney);
		backMoneyPoolnew.setFinalLinitDate(mv.getApplyExpireDay());// 到期日期
		backMoneyPoolnew.setBackMoneyDay(DeductUtils.getNextWorkingDay(mv.getApplyExpireDay()));// 回款日期
		backMoneyPoolnew.setDictBackStatus(BackState.DHKSQ.value);
		backMoneyPoolnew.setBackMoneyType(BackType.DQHK.value);
		backMoneyPoolnew.setModifyBy(user.getId());
		backMoneyPoolnew.setModifyTime(new Date());
		backMoneyPoolnew.setCreateBy(user.getId());
		backMoneyPoolnew.setCreateTime(new Date());
		backMoneyPoolnew.setRebackFlag(YoN.FOU.value);
		backMoneyPoolnew.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
		backMoneyPoolDao.insert(backMoneyPoolnew);
	}

	/**
	 * 出借申请更新
	 * 2016年4月30日
	 * By 柳慧
	 * @param lendCode
	 * @param user 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void loanApplyUpdate(String lendCode, User user) {
		LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
		loanApply.preUpdate();
		// 划扣状态：划扣成功
		loanApply.setLendStatus(LendState.HKCG.value);
		// 出借状态：出借中
		loanApply.setStatus(ForApplyStatus.CJZ.value);
		// 实际划扣时间：现在
		loanApply.setRealDeductTime(new Date());
		loanApplyDao.update(loanApply);
	}

	/**
	 * 划扣插入
	 * 2016年4月30日
	 * By 柳慧
	 * @param mv 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void deductPoolAdd(MatchingCreditorView mv,String payType) {
		DeductPool dp = new DeductPool();
		dp.preInsert();
		dp.setDeductApplyId(IdGen.uuid());// 划扣申请ID
		dp.setApplyCode(mv.getLendCode());// 出借编号
		dp.setCustCode(mv.getCustomerCode());// 客户编号
		dp.setSendEmailStatus(EmailState.WFS.value); // 收款确认书发送邮件状态
		dp.setMakeFlieStatus(FilecpState.WHC.value); // 收款确认书合成文件状态
		if(payType.equals(PayMent.HK.value) || payType.equals(PayMent.ZJTG.value) ){// 如果付款方式为划扣或者资金托管
			dp.setActualDeductMoney("0.00"); // 实际划扣金额
			dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
			dp.setFailMoney("0.00");
			dp.setDictDeductStatus(DeductState.DHKSQ.value); // 划扣申请状态（申请；审批；待处理；处理中；完成；失败；部分成功）
		}else if(payType.equals(PayMent.NBZZ.value) || payType.equals(PayMent.CGBFNZ.value)){ // 如果付款方式为内部转账 或者成功部分内转
			dp.setActualDeductMoney("0.00"); // 实际划扣金额
			dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
			dp.setFailMoney("0.00");
			dp.setConfirmResult(DeductState.DHKJS.value);
			dp.setDictDeductStatus(DeductState.HKCG.value); // 划扣申请状态
		} else if(payType.equals(PayMent.SHNZ.value)){ // 赎回内转
			dp.setActualDeductMoney("0.00"); // 实际划扣金额
			dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
			dp.setFailMoney("0.00");
			dp.setDictDeductStatus(DeductState.HKCG.value); // 划扣申请状态
			dp.setConfirmResult(DeductState.DHKJS.value);
		}
		deductApplyDao.insert(dp);
	}

	/**
	 * 发送信息
	 * 2016年4月13日
	 * By 柳慧
	 * @param mv 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void smsSend(MatchingCreditorView mv) {
		SmsTemplate template= smsManager.getSmsTemplate(SmsType.NZCJ.value);//获取模板内容
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String content = template.getTemplateContent();
		content=content.replace("{#Name#}", mv.getCustomerName())
						.replace("{#custom_text_6#}", sdf.format(mv.getApplyLendDay()))
						.replace("{#custom_text_4#}",FormatUtils.getFormatNumber(mv.getMatchingBorrowQuota(),"#000.00"))
						.replace("{#custom_text_3#}", PayMent.payMentMap.get(mv.getApplyPay()));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", mv.getLendCode());
		SmsSendList smsObject = smsDao.getSmsSend(map);
		smsObject.setLendCode(mv.getLendCode());
		smsObject.setCustomerName(mv.getCustomerName());
		smsObject.setProductCode(mv.getProductCode());
		smsObject.setProductName(mv.getProductName());
		smsObject.setCustomerCode(mv.getCustomerCode());
		smsObject.setCustomerPhone(mv.getMobilephone());
		smsObject.setBillDay(mv.getMatchingBillDay());
		smsObject.setLendMoney(mv.getMatchingBorrowQuota());
		smsObject.setLendDay(mv.getApplyLendDay());
		smsObject.setDueDay(mv.getApplyExpireDay());
		smsObject.setDictRepayType(mv.getApplyPay());
		smsObject.setSendStatus(SmsState.DFS.value);
		smsObject.setDictLendType(mv.getApplyPay());
		smsObject.setDictDeductStatus(DeductState.HKCG.value);
		Calendar hkrq =Calendar.getInstance();
		hkrq.setTime( mv.getApplyExpireDay());
		hkrq.set(Calendar.DAY_OF_MONTH, hkrq.get(Calendar.DAY_OF_MONTH)+1);
		smsObject.setBackMoneyDay(hkrq.getTime());
		if(null != template){
			smsObject.setSmsId(template.getTemplateCode());
		}
		smsObject.setSmsMsg(content);
		/*smsObject.setSendDay(null);
		smsObject.setPushDate(null);
		smsObject.setAreaName(null);*/
		
		smsObject.setOrgName(mv.getOrgName());
	/*	
		smsObject.setDictBackStatus(null);*/
		smsObject.setDictRemindType(SmsType.HKCG.value);
		smsObject.setBankName(OpenBank.getOpenBank(smsObject
				.getAccountBank()));
		//smsObject.setOnlyFlag(IdGen.uuid());
		smsObject.preInsert();
		if(mv.getApplyPay().equals(PayMent.NBZZ.value) || mv.getApplyPay().equals(PayMent.CGBFNZ.value)  ){
			smsObject.setSendDay(mv.getApplyLendDay() );	
		}
		smsDao.insert(smsObject);
	}

	/**
	 * 月满盈产品债权匹配提交 
	 * @param matchingId
	 * @param lendCode
	 * @param creditMonableIds
	 * @param tjmoneys
	 * @param tjdays
	 * @param  ceContext FileNet连接
	 * @throws Exception 
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void matchingBorrowMonthSubmit(String matchingId, String lendCode,
			String creditMonableIds, String tjmoneys, String tjdays,CERequestContext ceContext,String verTime,String borrowVerTimes) throws Exception {
		String [] creditMonableIdsArray = creditMonableIds.split("-");// 可用债权ID集合
		String [] tjmoneyArray = tjmoneys.split("-");// 推荐额度集合
		String [] tjdayArray = tjdays.split("-");//实际出借天数
		String [] borrowVerTimeArr = borrowVerTimes.split(";");
		int sjday =0; // 实际出借天数
		MatchingCreditorEx  mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		loanphaseCurDao.deleteByLendCode(lendCode);
		User user = UserUtils.getUser(UserUtils.getUserId());
		BigDecimal tjmoneyTotal = BigDecimal.ZERO;
		String creditSrc = CreditSrc.CJ.value;
		BigDecimal phaseAmount = BigDecimal.ZERO;// 本期应还总本息
		BigDecimal phaseInterestCurAll = BigDecimal.ZERO; // 本期应还总利息
		String creditIdAll =""; // 可用债权总数
		Map<String,String>  oldCreditValueIdCard= new HashMap<String,String>();//  已匹配的债权的省份证
		for(int i = 0; i<creditMonableIdsArray.length;i++){
			String creditMonableId = creditMonableIdsArray[i];// 可用债权ID
			String tjmoney = tjmoneyArray[i].replaceAll(",", ""); // 推荐额度
			String tjday = tjdayArray[i]; // 实际出借天数
			String borrowVerTime = borrowVerTimeArr[i];
			sjday =Integer.valueOf(tjday);
			BigDecimal tjmoneyB = new BigDecimal(tjmoney);
			tjmoneyTotal  = tjmoneyTotal.add(tjmoneyB);
			String id = IdGen.uuid();// 债权交易主键
			BorrowMonthable borrowMonthable = borrowMonthableDao.get(creditMonableId);// 通过债权ID  获取可用债权实体
			if(oldCreditValueIdCard.containsKey(borrowMonthable.getLoanIdcard())){
				throw new ServiceException(borrowMonthable.getLoanName()+"债权存在相同的债权人，债权匹配失败！");
			}else{
				oldCreditValueIdCard.put(borrowMonthable.getLoanIdcard(),borrowMonthable.getLoanIdcard());
			}
			BigDecimal loanCreditValue1 = borrowMonthable.getLoanAvailabeValue();
			//如果loanCreditValue1.subtract(tjmoneyB) < 0,且绝对值 > 0.005,可用债权不足
			if(loanCreditValue1.subtract(tjmoneyB).compareTo(new BigDecimal("0")) == -1 && loanCreditValue1.subtract(tjmoneyB).abs().compareTo(new BigDecimal("0.005")) ==1){
				throw new ServiceException(borrowMonthable.getLoanName()+"的可用债权不足！债权匹配失败！");
			}
			if(!StringUtils.isEmpty(borrowMonthable.getDictLoanType()) && !borrowMonthable.getDictLoanType().equals(creditSrc)){
				creditSrc = borrowMonthable.getDictLoanType();
			}
			/** 添加债权交易信息  **/
			// 债权交易实体
			CreditorTrade ct =new  CreditorTrade();
			ct.setTradeId(id);
			ct.setMatchingId(matchingId);
			ct.setLendCode(lendCode);
			ct.setCreditNode(Node.YMYKY.value);// 债权节点；债权池；月满盈可用债权池
			ct.setCreditCode(creditMonableId);// 债权ID
			BigDecimal ppje = new BigDecimal( tjmoney);
			ct.setTradeMateMoney(ppje);// 匹配金额
			ct.setTradeMateMoneyPercent(ppje.divide(new BigDecimal(String.valueOf(borrowMonthable.getLoanAvailabeValue())),15,BigDecimal.ROUND_HALF_UP).toString());// 匹配金额所占百分比
			ct.setDictTradeCreditStatus(CreditTradestate.KSHKZ.value); // 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
			ct.setTradeBorrowdaysActual(tjday );// 实际出借天数
			ct.setTradeExpectDay(new Timestamp(borrowMonthable.getLoanBackmoneyDay().getTime()));// 债权交易预计到期时间     = 截止还款日期
			ct.setCreateBy(user.getId());
			ct.setCreateTime( new Date());
			ct.setModifyBy(user.getId());
			ct.setModifyTime(new Date());
			// 债权交易添加
			creditorTradeDao.insert(ct);
			/**　添加分期收益表信息**/
			Loanphase loanphase  = new Loanphase();
			loanphase.setPhaseId(IdGen.uuid());
			loanphase.setMatchingId(matchingId);
			loanphase.setLendCode(lendCode);
			loanphase.setLoanCode(creditMonableId);//借款ID
			// 债权月利率
			BigDecimal cpll =borrowMonthable.getLoanMonthRate();
			// 本期应还利息  = 匹配金额*债权月利率/100/出借日所在月天数*推荐天数
			Calendar calendar = new  GregorianCalendar();
			calendar.setTime(mv.getApplyLendDay());
			int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			BigDecimal phaseInterestCur = tjmoneyB.multiply(cpll)
					.multiply(new BigDecimal(tjday))
					.divide(new BigDecimal(String.valueOf(days)),5,BigDecimal.ROUND_HALF_UP)
					.divide(new BigDecimal("100"),5,BigDecimal.ROUND_HALF_UP);
			loanphase.setPhaseAmount(phaseInterestCur.add(tjmoneyB));// 本期应还本息
			loanphase.setPhaseInterestCur(phaseInterestCur);// 本期应还利息
			loanphase.setPhasePrincipalCur(tjmoneyB);// 本期应还本金
			loanphase.setPhaseMateId(id);// 匹配id
			loanphase.setPhaseBackCount(phaseInterestCur.add(tjmoneyB));// 截止到本期已还本息
			loanphase.setPhaseBackPrincipal(tjmoneyB);// 截止到本期已还本金
			loanphase.setPhaseBackInterest(phaseInterestCur);// 截止到本期已还利息
			loanphase.setPhasePrincipalSurplus( BigDecimal.ZERO); // 本期还款结束后，剩余未还本金
			loanphase.setPhaseDiscardStatus(ScrapState.BFQ.value);// 废弃状态
			loanphase.setPhaseReleaseStatus(com.creditharmony.core.fortune.type.CreditRelease.MSF.value);
			loanphase.setPhaseFreezeNextstatus("0");// 下期债权人是否冻结
			loanphase.setPhaseMateNumber(1); // 匹配总期数
			loanphase.setPhaseNumberSurplus(1);  // 匹配 当前债权人剩余期数
			loanphase.setCreateBy(user.getId());
			loanphase.setCreateTime( new Date());
			loanphase.setModifyBy(user.getId());
			loanphase.setModifyTime(new Date());
			loanphase.setBillDay(CreditorUtils.getCreditDaybyLendday(mc.getMatchingInterestStart()));
			// 本期有效期-开始时间
			loanphase.setPhaseBegindayCur(mv.getApplyLendDay());
			// 本期有效期-截止时间
			Calendar cal = new  GregorianCalendar();
			cal.setTime(loanphase.getPhaseBegindayCur());
			cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(tjday));
			loanphase.setPhaseEnddayCur(cal.getTime());
			// 分期收益表添加
			 LoanphaseDao.insert(loanphase);
			 phaseAmount = phaseAmount.add(loanphase.getPhaseAmount());// 本期应还总本息
			 phaseInterestCurAll =phaseInterestCurAll.add(loanphase.getPhaseInterestCur()); // 本期应还总利息
			 creditIdAll =creditIdAll+loanphase.getLoanCode()+",";
			
			 addLoanphaseCur(loanphase);
			/** 更新可用债权列表　**/
			BigDecimal  creditValue = borrowMonthable.getLoanAvailabeValue().subtract(new BigDecimal(tjmoney));// 可用债权
			java.math.BigDecimal loanCreditValue = creditValue;
			borrowMonthable.setLoanAvailabeValue(loanCreditValue);// 可用债权
			borrowMonthable.setVerTime(borrowVerTime);
			borrowMonthable.preUpdate();
			// 更新可用债权
			int updateNum = borrowMonthableDao.update(borrowMonthable);
			if(updateNum==0){
				throw new Exception(borrowMonthable.getLoanName()+"债权信息已被更新");
			}
			addCreditorHisBorrowMonth(mv,borrowMonthable,tjmoneyB);
		}
		addLoanphasePeriod(mv,phaseAmount,phaseInterestCurAll,creditIdAll);
		// 设置债权
		mv.setLoanType(creditSrc);
		String payType = mv.getApplyPay(); // 支付方式
		// 月满盈债权匹配成功后更新出借申请表的到期日=出借日期+实际天数-1,待推荐表的到期日=出借日期+实际天数-1
		Calendar dqrq = Calendar.getInstance();
		dqrq.setTime(mv.getApplyLendDay());
		dqrq.add(Calendar.DAY_OF_MONTH, sjday-1);
		Date EndDate = dqrq.getTime();
		mv.setApplyExpireDay(EndDate);		
		if (!mc.getMatchingStatus().equals(MatchingStatus.CXCP.value)) { // 如果不等于撤销重匹 插入划扣、回款信息
			if (payType.equals(PayMent.HK.value) || payType.equals(PayMent.ZJTG.value)) { // 如果付款方式为划扣或者资金托管
				/** 划扣插入 **/
				DeductPool dp = new DeductPool();
				dp.preInsert();
				dp.setDeductApplyId(IdGen.uuid());// 划扣申请ID
				dp.setApplyCode(mv.getLendCode());// 出借编号
				dp.setCustCode(mv.getCustomerCode());// 客户编号
				dp.setActualDeductMoney(BigDecimal.ZERO.toString()); // 实际划扣金额
				dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
				dp.setDictDeductStatus(DeductState.DHKSQ.value); // 划扣申请状态（申请；审批；待处理；处理中；完成；失败；部分成功）
				deductApplyDao.insert(dp);
				/*
				 * if (payType.equals(PayMent.HK.value)) { Contract contract =
				 * new Contract(); contract.setLendCode(lendCode);
				 * contractManager.updateContractUseDay(contract); }
				 */
			}
			if (payType.equals(PayMent.NBZZ.value)|| payType.equals(PayMent.CGBFNZ.value)) { // 如果付款方式为内部转账或者成功部分内转
				LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
				loanApply.setModifyBy(user.getId());
				loanApply.setModifyTime(new Date());
				loanApply.setLendStatus(LendState.HKCG.value);
				loanApply.setStatus(ForApplyStatus.CJZ.value);
				loanApplyDao.update(loanApply);
				/** 划扣插入 **/
				DeductPool dp = new DeductPool();
				dp.preInsert();
				dp.setDeductApplyId(IdGen.uuid());// 划扣申请ID
				dp.setApplyCode(mv.getLendCode());// 出借编号
				dp.setCustCode(mv.getCustomerCode());// 客户编号
				dp.setActualDeductMoney("0"); // 实际划扣金额
				dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
				dp.setDictDeductStatus(DeductState.HKCG.value); // 划扣申请状态
				deductApplyDao.insert(dp);
				if (payType.equals(PayMent.NBZZ.value)) {
					Contract contract = new Contract();
					contract.setLendCode(lendCode);
					contractManager.updateContractUseDay(contract);
				}
				backPoolCz(lendCode, mv, user, payType);

			}
			if (payType.equals(PayMent.SHNZ.value)) { // 赎回内转
				LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
				loanApply.setModifyBy(user.getId());
				loanApply.setModifyTime(new Date());
				loanApply.setLendStatus(LendState.HKCG.value);
				loanApply.setStatus(ForApplyStatus.CJZ.value);
				loanApplyDao.update(loanApply);
				/** 划扣插入 **/
				DeductPool dp = new DeductPool();
				dp.preInsert();
				dp.setDeductApplyId(IdGen.uuid());// 划扣申请ID
				dp.setApplyCode(mv.getLendCode());// 出借编号
				dp.setCustCode(mv.getCustomerCode());// 客户编号
				dp.setActualDeductMoney("0"); // 实际划扣金额
				dp.setLoanMoney(mv.getMatchingBorrowQuota().toString());
				dp.setDictDeductStatus(DeductState.HKCG.value); // 划扣申请状态
				deductApplyDao.insert(dp);
				/** 回款池数据操作 **/
				HashMap<String, Object> transMap = new HashMap<String, Object>();
				transMap.put("childLendCode", lendCode);
				List<TransferRelation> trLst = transferRelationDao
						.findList(transMap);
				if (trLst != null && trLst.size() > 0) {
					for (TransferRelation trion : trLst) {
						String lendCodeNew = trion.getLendCodeParent();// 出借编号
						String transferMoney = trion.getTransferMoney().toString(); // 内转金额
						/** 根据出借编号修改内转金额 **/
						LoanApply loanApplynew = loanApplyDao.getLoanApplyByCode(lendCodeNew);
						loanApplynew.setTransferMoney(new BigDecimal(transferMoney));
						loanApplynew.setModifyBy(user.getId());
						loanApplynew.setModifyTime(new Date());
						loanApplyDao.update(loanApplynew);
						/** 更新内转关系表 **/
						trion.setModifyBy(user.getId());
						trion.setModifyTime(new Date());
						transferRelationDao.update(trion);
					}
					/** 插入新的回款信息 **/
					BackMoneyPool backMoneyPoolnew = new BackMoneyPool();
					backMoneyPoolnew.setBackmId(IdGen.uuid());
					backMoneyPoolnew.setLendCode(lendCode);
					BigDecimal backMoney = creditorAidManager.getBackMoney(mv);
					backMoneyPoolnew.setBackMoney(backMoney);
					backMoneyPoolnew.setBackActualbackMoney(backMoney);
					backMoneyPoolnew.setFinalLinitDate(mv.getApplyExpireDay());// 到期日期
					backMoneyPoolnew.setBackMoneyDay(DeductUtils.getNextWorkingDay(mv.getApplyExpireDay()));// 回款日期
					backMoneyPoolnew.setDictBackStatus(BackState.DHKSQ.value);
					backMoneyPoolnew.setBackMoneyType(BackType.SHNZ.value);
					backMoneyPoolnew.setModifyBy(user.getId());
					backMoneyPoolnew.setModifyTime(new Date());
					backMoneyPoolnew.setCreateBy(user.getId());
					backMoneyPoolnew.setCreateTime(new Date());
					backMoneyPoolnew.setRebackFlag(YoN.FOU.value);
					backMoneyPoolDao.insert(backMoneyPoolnew);
				}
			}
		}
		/** 更新待债权推荐信息表 **/
		mc.setMatchingMatchMoney(tjmoneyTotal);
		// 更改债权状态  更改为已订购
		mc.setMatchingStatus(MatchingStatus.YTJ.value);
		mc.setDictMatchingFileStatus(FilecpState.WHC.value);
		mc.setModifyBy(user.getId());
		mc.setMatchingCossTime(new Date());
		mc.setMatchingEndday(EndDate);
		mc.setModifyTime(new Date());
		mc.setVerTime(verTime);
		mc.setMatchingExpireDay(EndDate);
		mc.setUpdatematchingStatus(MatchingStatus.YTJ.value);
		int updateNum = matchingCreditorDao.updateByPrimaryKeySelective(mc);
		if(updateNum==0){
			throw new ServiceException("该待推荐信息被更新，请重新操作！");
		}
		/** 更新待债权推荐统计 **/
		creditorStatisticsManager.updateStatistic(
				UserUtils.getUserId(), 1, Constant.CREDIT_DONE, BillState.SQ.value,lendCode);
		//文件制作
		Attachment disAttachment = new Attachment();
		disAttachment.setAttaTableId(mv.getMatchingId());
		disAttachment.setAttaFileOwner("t_tz_matching_creditor");
		disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
		this.makeFile(mv, ceContext,disAttachment,null);
		LoanApply loanApply = loanApplyDao.getLoanApplyByCode(lendCode);
		loanApply.setExpireDate(dqrq.getTime());
		loanApply.setLendDay(sjday);
		loanApplyDao.update(loanApply);
		checkManager.addCheck(lendCode, "待推荐匹配成功！当前期数为："+mc.getMatchingNow(), TracesType.ZQ_PP.getName());
		
		//内转成单接口,往CRM推送成单记录
		try {
			if(PayMent.NBZZ.value.equals( loanApply.getPayType() )){	//付款方式为内转，不会走划扣，债匹完算成单
				tripleInvestSuccService.investSucc("", lendCode, "I");
			}else{
				logger.debug("其他付款方式，在债匹环节不记录成单！！");
			}
		} catch (Exception e) {
			logger.debug("内转记录成单失败！！");
		}
	}
	
	/**
	 * 添加债权管理记录  月满盈
	 * 2016年5月16日
	 * By 柳慧
	 * @param mv
	 * @param borrowMonthable
	 * @param tjmoneyB
	 */
	private void addCreditorHisBorrowMonth(MatchingCreditorView mv,
			BorrowMonthable borrowMonthable, BigDecimal tjmoneyB) {
		BigDecimal loanAvailabeValue = borrowMonthable.getLoanAvailabeValue();
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setMatchingId(mv.getMatchingId());
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.KYZQ.value);
		creditorHis.setNodeId(borrowMonthable.getCreditMonableId());
		creditorHis.setOperateType(OperateType.PPTJ.value);
		creditorHis.setBeforMoney(loanAvailabeValue.add(tjmoneyB));
		creditorHis.setOperateMoney(tjmoneyB);
		creditorHis.setOperateTime(new Date());
		//误差
		BigDecimal wucha = new BigDecimal("0.005");
		BigDecimal init = new BigDecimal("0");
		//如果 loanAvailabeValue 的值为[-0.005,0)时，修改其值为 0
		if(loanAvailabeValue.compareTo(init) == -1 && (loanAvailabeValue.abs().compareTo(wucha) == -1 || loanAvailabeValue.abs().compareTo(wucha) == 0)){
			creditorHis.setAfterMoney(init);
		}else{
			creditorHis.setAfterMoney(loanAvailabeValue);
		}
		creditorHis.setOperator(UserUtils.getUserId());
		creditorHis.setCreateBy(UserUtils.getUserId());
		creditorHis.setCreateTime(new Date());
		creditorHisDao.insert(creditorHis);
	}

	/**
	 * 查询查询列表的总数
	 * 2016年1月19日
	 * By 柳慧
	 * @param search
	 * @return
	 */
	public Map<String, Object> findTotal(MatchingCreditorEx search) {
		/*List<String> filtermatchingIds = creditorAidManager.getFiltermatchingIds();
		if(filtermatchingIds!=null && filtermatchingIds.size()>0){
			search.setFiltermatchingIds(filtermatchingIds);
		}*/
		//划扣平台
		String deductType = search.getDictApplyDeductType();
		if (!StringUtils.isEmpty(deductType)) {
			search.setDictApplyDeductTypes(MatchingUtils.mulityStringOptionForSearch(deductType, ","));
		}		
		// 匹配标识
		String matchingFlagType = search.getDictMatchingFlagType();
		if (!StringUtils.isEmpty(matchingFlagType)) {
			search.setDictMatchingFlagTypes(MatchingUtils.mulityStringOptionForSearch(matchingFlagType, ","));
		}
		//银行
		String bank = search.getAccountBank();
		if (!StringUtils.isEmpty(bank)) {
			search.setAccountBankList(MatchingUtils.mulityStringOptionForSearch(bank, ","));			
		}	
		String city = search.getCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			search.setCity(c);
		}		
		return  matchingCreditorDao.findTotal(search);
	}
	
	/**
	 * 判断页面是通过搜索栏条件导出，还是通过复选框导出
	 * 2016年1月20日
	 * By 柳慧
	 * @param search
	 * @return
	 */
	public MatchingCreditorEx judgeIds(MatchingCreditorEx search) {
		String matchingId = search.getMatchingId();
		if(!StringUtils.isEmpty(matchingId)){
			search.setListMatchingId(MatchingUtils.mulityStringOptionForSearch(matchingId, ";"));
		}
		List<String> filtermatchingIds = creditorAidManager.getFiltermatchingIds();
		if(filtermatchingIds!=null && filtermatchingIds.size()>0){
			search.setFiltermatchingIds(filtermatchingIds);
		}
		return  search;
	}
	
	/**
	 * 待推荐债权信息撤销
	 * 2016年2月18日
	 * By 柳慧
	 * @param matchingId
	 */
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void matchingCancel(String matchingId) {
		User user = UserUtils.getUser(UserUtils.getUserId());
		MatchingCreditorEx  mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		mc.setMatchingStatus(MatchingStatus.CX.value);
		mc.setModifyBy(user.getId());
		mc.setModifyTime(new Date());
		matchingCreditorDao.updateByPrimaryKeySelective(mc);
		LoanApply loanApply = loanApplyDao.getLoanApplyByCode(mc.getLendCode());
		loanApply.setLendStatus(LendState.CX.value);
		// 客户放弃
		loanApply.setApplyEndStatus(FinishState.KHFQ.value);
		loanApply.setModifyBy(user.getId());
		loanApply.setModifyTime(new Date());
		loanApplyDao.update(loanApply);
		String payType = mv.getApplyPay();
		if (payType.equals(PayMent.NBZZ.value)|| payType.equals(PayMent.CGBFNZ.value) || payType.equals(PayMent.SHNZ.value)) { 
			HashMap<String,Object> transMap = new HashMap<String,Object>();
			transMap.put("lendCodeC", mc.getLendCode());
			transMap.put("modifyBy", user.getId());
			transMap.put("transferState", TransferState.YTH.getKey());
			transferRelationDao.updateTransferStatus(transMap);
		}
		// 出借合同作废
		lendApplyManager.caInvalidLendApply(mc.getLendCode());
		
	}
	
	/**
	 * 文件发送
	 * 2016年2月19日
	 * By 柳慧
	 * @param applyCode 出借编号
	 * @param fileType 文件类型
	 * @param sendName 发送名字
	 * @param attaFlag 附件文件类型
	 * @param matchingId 待推荐ID
	 * @param
	 * @return
	**/
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public int sendEamil(String applyCode,String fileType, String sendName,String attaFlag,String templateType,String matchingId){
		logger.info("sendEamil 开始");
		logger.info("applyCode="+applyCode+";fileType="+fileType+";sendName="+sendName+";attaFlag="+attaFlag+";templateType="+templateType+";matchingId="+matchingId);
		logger.debug("出借编号【" + applyCode + "】sendEamil方法-----开始");
		int result = 0;
		MatchingCreditorEx mc = matchingCreditorDao.selectByPrimaryKey(matchingId);
		if(mc==null){
			logger.info("mc==null");
			logger.debug("出借编号【" + applyCode + "】未查到待推荐信息，不发送邮件");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", applyCode);
		map.put("matchingFirstdayFlag", mc.getMatchingFirstdayFlag());logger.info("mc.getMatchingFirstdayFlag()="+mc.getMatchingFirstdayFlag());
		map.put("emailType", templateType);
		map.put("fileType",fileType);
		map.put("attaFlag",attaFlag);
		map.put("isDiscard",EffectiveFlag.yx.value);
		map.put("attaTableId",matchingId);
		map.put("dictStatus",UseFlag.QY.value);
		AttachmentEx attachment = matchingCreditorDao.getAttachmentEx(map);
		// 过虑债权文件发送制作失败和制作中
		if (attachment != null) {
			logger.info("attachment != null");
			logger.debug("出借编号【" + applyCode + "】,过虑债权文件发送制作失败和制作中----开始");
			// EmailInfo对象
			EmailTemplate tempEmail = emailInfoDao.getEmailTemplateByType(templateType);
			logger.info("tempEmail="+tempEmail);
			if(tempEmail==null) return result;
			EmailInfo emailInfo = new EmailInfo();
			//emailInfo.preInsert();
			emailInfo.setId(IdGen.uuid());
			emailInfo.setCreateBy(sendName);
			emailInfo.setModifyBy(sendName);
			Date date = new Date();
			emailInfo.setCreateTime(date);logger.info("date="+date);
			emailInfo.setModifyTime(date);
			emailInfo.setLendCode(applyCode);
			emailInfo.setEmailTemplateId(tempEmail.getId());logger.info("tempEmail.getId()="+tempEmail.getId());
			emailInfo.setEmailSender(sendName);
			emailInfo.setEmailSenderTime(new Date());
			emailInfo.setEmailRecpEmail(attachment.getCustEmail());logger.info("attachment.getCustEmail()="+attachment.getCustEmail());
			emailInfo.setEmailRecpName(attachment.getCustName());logger.info("attachment.getCustName()="+attachment.getCustName());
			emailInfo.setEmailSubject(tempEmail.getDescription());logger.info("tempEmail.getDescription()="+tempEmail.getDescription());
			logger.info("前attachment.getTemplateContent()="+attachment.getTemplateContent());
			logger.info("前attachment.getApplyDeductDay()="+attachment.getApplyDeductDay());
			String contenxt = attachment.getTemplateContent().replace("time", attachment.getApplyDeductDay());
			logger.info("contenxt="+contenxt);
			emailInfo.setEmailMsg(contenxt);
			emailInfo.setPdfType(fileType);
			emailInfo.setCreditId(attachment.getAttaTableId());logger.info("attachment.getAttaTableId()="+attachment.getAttaTableId());
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());logger.info("attachment.getAttaFilepath()="+attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfo.setEmailSendId(emailInfo.getId());logger.info("emailInfo.getId()="+emailInfo.getId());
			emailInfo.setMatchingBillDay(mc.getMatchingBillDay());// 账单日
			logger.info("mc.getMatchingBillDay()="+mc.getMatchingBillDay());
			emailInfo.setEmailType(mc.getMatchingFirstdayFlag());logger.info("mc.getMatchingFirstdayFlag()="+mc.getMatchingFirstdayFlag());
			logger.info("emailInfoDao.insert(emailInfo) 开始");
			emailInfoDao.insert(emailInfo);
			logger.info("emailInfoDao.insert(emailInfo) 结束");
			// 全程流痕
			logger.info("sendEamil 结束");
			checkManager.addCheck(applyCode,"批量发送债权文件", "向邮件待发送表插入数据",sendName);
			logger.debug("出借编号【" + applyCode + "】,过虑债权文件发送制作失败和制作中----结束");
			result =1;
		} 
		logger.info("sendEamil 结束");
		logger.debug("出借编号【" + applyCode + "】sendEamil方法-----结束");
		return result;
	
	}
	
	/**
	 * 文件制作
	 * 2016年3月11日
	 * By 柳慧
	 * @param mview
	 * @param ceContext
	 * @param attachment
	 * @ param from  1表示来自文件合成
	 * 
	 */
	public  void makeFile(MatchingCreditorView mview,CERequestContext ceContext,Attachment attachment,String from){
		String productCode = mview.getProductCode();
		String payType = mview.getApplyPay(); // 支付方式
		
		/**  债权文件制作 **/
		if(!StringUtils.isEmpty(productCode) &&!ProductCode.YMY.value.equals(productCode)){ //  非月满盈产品
			
			/**  债权文件制作  非月满盈**/
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("fileName", mview.getCustomerName()+"债权文件");
			filters.put("lendCode", mview.getLendCode());
			filters.put("attaTableId",mview.getMatchingId() );			
			filters.put("attaFileOwner","t_tz_matching_creditor" );
			filters.put("attaFlag",FileConstants.FILE_TYPE_SR );
			if( BillState.SQ.value.equals(mview.getMatchingFirstdayFlag())){
				// 首期文件
				filters.put("templateName",ReportType.FYMY_SQ.getCode() );
				filters.put("parameter","matching_id="+mview.getMatchingId()+"&phaseNow="+mview.getMatchingNow());
				filters.put("templateType",MailTemplateType.SQ.value);
			}else{
				int phaseFrom=1;
				int phaseTo=mview.getMatchingNow()-1;
				if (ProductCode.XHBA.value.equals(productCode)
						|| (ProductCode.XHB.value.equals(productCode) && XinhebaoType.XHB12.value
								.equals(mview.getBackType()))) {
					if (13 < mview.getMatchingNow()) {
						phaseFrom = 13;

					}
				}
				if (ProductCode.XHBC.value.equals(productCode)) {
					if (mview.getMatchingNow() > 19) {
						phaseFrom = 19;
					} else if (13 < mview.getMatchingNow() && mview.getMatchingNow() < 19) {
						phaseFrom = 13;
					} else if (7 < mview.getMatchingNow() && mview.getMatchingNow() < 13) {
						phaseFrom = 7;
					}

				}
				// 非首期文件
				filters.put("templateName",ReportType.FYMY_FSQ.getCode() );
				// 获取上期matching_id
				String lastMatchingId = this.getLastMatchingId(mview.getLendCode(),mview.getMatchingNow()-1);
				filters.put("parameter",
						"matching_id="+mview.getMatchingId()
						+"&last_matching_id="+lastMatchingId
						+"&lend_code="+mview.getLendCode()
						+"&phaseFrom="+phaseFrom
						+"&phaseTo="+phaseTo);
				filters.put("templateType",MailTemplateType.FSQ.value);
			}
			
			if(!payType.equals(PayMent.SHNZ.value) ){// 赎回内转 则生成债权文件，但不发送	
				if( 1 == mview.getMatchingNow()){
					filters.put("sendFlag",Constant.SEND_FLAG_YES);
				}
			}
			if( BillState.FSQ.value.equals(mview.getMatchingFirstdayFlag())){
				filters.put("sendFlag",Constant.SEND_FLAG_YES);
			}
			filters.put(CERequestContext.DM_FILENET_CONTEXT,ceContext);
			filters.put("customerCode", mview.getCustomerCode());
			filters.put("attachment", attachment);
			filters.put("signType", CASignType.CREDITFILE.value);
			filters.put("from",from);
			FileUtil.compositeFile(filters);			
			
		}else{ // 月满盈 文件生成
			/**  债权文件制作  月满盈**/
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("fileName", mview.getCustomerName()+"债权文件");
			filters.put("lendCode", mview.getLendCode());
			filters.put("attaTableId",mview.getMatchingId() );
			filters.put("parameter","matching_id="+mview.getMatchingId() );
			filters.put("attaFileOwner","t_tz_matching_creditor" );
			if(!payType.equals(PayMent.SHNZ.value) ){ // 赎回内转则生成债权文件，但不发送
				filters.put("sendFlag",Constant.SEND_FLAG_YES);// 发送文件	
				filters.put("templateType",MailTemplateType.SQ.value);
			}
			filters.put("attaFlag",FileConstants.FILE_TYPE_SR );
			if(creditorAidManager.MonthBorrowIsCjByMathing(mview.getMatchingId())){
				// 车借
				filters.put("templateName",ReportType.YMY_CJ.getCode() );
			}else{
				// 其余使用房借模板
				filters.put("templateName",ReportType.YMY_FJ.getCode() );
			}
			filters.put(CERequestContext.DM_FILENET_CONTEXT,ceContext);
			filters.put("customerCode", mview.getCustomerCode());
			filters.put("attachment", attachment);
			filters.put("signType", CASignType.CREDITFILE.value);
			filters.put("from",from);
			FileUtil.compositeFile(filters);
		}
		/** 收款确认书,第一期并且付款方式为成功部分内转或者内部转账的才生成收款确认书 **/
		if( 1 == mview.getMatchingNow()){
			//文件制作
			Attachment disAttachment = new Attachment();
			disAttachment.setAttaTableId(attachment.getAttaTableId());
			disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SKQR);
			
			if(payType.equals(PayMent.CGBFNZ.value)){ // 如果付款方式为成功部分内转
				Map<String, Object> filters = new HashMap<String, Object>();
				/** 收款确认书-划扣、资金托管 **/
				filters.put("lendCode", mview.getLendCode());
				filters.put("parameter","matching_id="+mview.getMatchingId() );
				filters.put("fileName", mview.getCustomerName()+"收款确认书");
				filters.put("sendFlag",Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SKQRS.value);
				filters.put("attaFlag",FileConstants.FILE_TYPE_SKQR );
				filters.put("templateName",ReportType.SKQRS_HK.getCode() );
				filters.put("customerCode", mview.getCustomerCode());
				filters.put("attachment", disAttachment);
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
				filters.put("from",from);
				filters.put("attaTableId",mview.getMatchingId() );
				FileUtil.compositeFile(filters);
				
			} else if(payType.equals(PayMent.NBZZ.value)){ // 如果付款方式为内部转账
				
				Map<String, Object> filters = new HashMap<String, Object>();
				/** 收款确认书-内转、成功部分内转 **/
				filters.put("parameter","matching_id="+mview.getMatchingId() );
				filters.put("fileName", mview.getCustomerName()+"收款确认书");
				filters.put("sendFlag",Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SKQRS.value);
				filters.put("attaFlag",FileConstants.FILE_TYPE_SKQR );
				filters.put("templateName",ReportType.SKQRS_NBZZ.getCode() );
				filters.put("lendCode", mview.getLendCode());
				filters.put("customerCode", mview.getCustomerCode());
				filters.put("attachment", disAttachment);
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
				filters.put("from",from);
				filters.put("attaTableId",mview.getMatchingId() );
				FileUtil.compositeFile(filters);
				
			} else if(payType.equals(PayMent.SHNZ.value)){ // 赎回内转
				//赎回内转无收款确认书
			}
		}	
		
	}
	
	/**
	 * 手动合成文件制作不插入邮件发送表
	 * 2016年3月11日
	 * By 柳慧
	 * @param mview
	 * @param ceContext
	 * @param attachment
	 * @ param from  1表示来自文件合成
	 * 
	 */
	public  void makeFileNoSendEmail(MatchingCreditorView mview,CERequestContext ceContext,Attachment attachment,String from){
		String productCode = mview.getProductCode();
		String payType = mview.getApplyPay(); // 支付方式
		
		/**  债权文件制作 **/
		if(!StringUtils.isEmpty(productCode) &&!ProductCode.YMY.value.equals(productCode)){ //  非月满盈产品
			
			/**  债权文件制作  非月满盈**/
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("fileName", mview.getCustomerName()+"债权文件");
			filters.put("lendCode", mview.getLendCode());
			filters.put("attaTableId",mview.getMatchingId() );			
			filters.put("attaFileOwner","t_tz_matching_creditor" );
			filters.put("attaFlag",FileConstants.FILE_TYPE_SR );
			if( BillState.SQ.value.equals(mview.getMatchingFirstdayFlag())){
				// 首期文件
				filters.put("templateName",ReportType.FYMY_SQ.getCode() );
				filters.put("parameter","matching_id="+mview.getMatchingId()+"&phaseNow="+mview.getMatchingNow());
				filters.put("templateType",MailTemplateType.SQ.value);
			}else{
				int phaseFrom=1;
				int phaseTo=mview.getMatchingNow()-1;
				if (ProductCode.XHBA.value.equals(productCode)
						|| (ProductCode.XHB.value.equals(productCode) && XinhebaoType.XHB12.value
								.equals(mview.getBackType()))) {
					if (13 < mview.getMatchingNow()) {
						phaseFrom = 13;

					}
				}
				if (ProductCode.XHBC.value.equals(productCode)) {
					if (mview.getMatchingNow() > 19) {
						phaseFrom = 19;
					} else if (13 < mview.getMatchingNow() && mview.getMatchingNow() < 19) {
						phaseFrom = 13;
					} else if (7 < mview.getMatchingNow() && mview.getMatchingNow() < 13) {
						phaseFrom = 7;
					}

				}
				// 非首期文件
				filters.put("templateName",ReportType.FYMY_FSQ.getCode() );
				// 获取上期matching_id
				String lastMatchingId = this.getLastMatchingId(mview.getLendCode(),mview.getMatchingNow()-1);
				filters.put("parameter",
						"matching_id="+mview.getMatchingId()
						+"&last_matching_id="+lastMatchingId
						+"&lend_code="+mview.getLendCode()
						+"&phaseFrom="+phaseFrom
						+"&phaseTo="+phaseTo);
				filters.put("templateType",MailTemplateType.FSQ.value);
			}
			// 手动合成不发送邮件表
			/*if(!payType.equals(PayMent.SHNZ.value) ){// 赎回内转 则生成债权文件，但不发送	
				if( 1 == mview.getMatchingNow()){
					filters.put("sendFlag",Constant.SEND_FLAG_YES);
				}
			}
			if( BillState.FSQ.value.equals(mview.getMatchingFirstdayFlag())){
				filters.put("sendFlag",Constant.SEND_FLAG_YES);
			}*/
			filters.put(CERequestContext.DM_FILENET_CONTEXT,ceContext);
			filters.put("customerCode", mview.getCustomerCode());
			filters.put("attachment", attachment);
			filters.put("signType", CASignType.CREDITFILE.value);
			filters.put("from",from);
			FileUtil.compositeFile(filters);			
			
		}else{ // 月满盈 文件生成
			/**  债权文件制作  月满盈**/
			Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("fileName", mview.getCustomerName()+"债权文件");
			filters.put("lendCode", mview.getLendCode());
			filters.put("attaTableId",mview.getMatchingId() );
			filters.put("parameter","matching_id="+mview.getMatchingId() );
			filters.put("attaFileOwner","t_tz_matching_creditor" );
			if(!payType.equals(PayMent.SHNZ.value) ){ // 赎回内转则生成债权文件，但不发送
				// 手动合成不发送邮件表	
//				filters.put("sendFlag",Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SQ.value);
			}
			filters.put("attaFlag",FileConstants.FILE_TYPE_SR );
			if(creditorAidManager.MonthBorrowIsCjByMathing(mview.getMatchingId())){
				// 车借
				filters.put("templateName",ReportType.YMY_CJ.getCode() );
			}else{
				// 其余使用房借模板
				filters.put("templateName",ReportType.YMY_FJ.getCode() );
			}
			filters.put(CERequestContext.DM_FILENET_CONTEXT,ceContext);
			filters.put("customerCode", mview.getCustomerCode());
			filters.put("attachment", attachment);
			filters.put("signType", CASignType.CREDITFILE.value);
			filters.put("from",from);
			FileUtil.compositeFile(filters);
		}
		/** 收款确认书,第一期并且付款方式为成功部分内转或者内部转账的才生成收款确认书 **/
		if( 1 == mview.getMatchingNow()){
			//文件制作
			Attachment disAttachment = new Attachment();
			disAttachment.setAttaTableId(attachment.getAttaTableId());
			disAttachment.setDictAttaFlag(FileConstants.FILE_TYPE_SKQR);
			
			if(payType.equals(PayMent.CGBFNZ.value)){ // 如果付款方式为成功部分内转
				Map<String, Object> filters = new HashMap<String, Object>();
				/** 收款确认书-划扣、资金托管 **/
				filters.put("lendCode", mview.getLendCode());
				filters.put("parameter","matching_id="+mview.getMatchingId() );
				filters.put("fileName", mview.getCustomerName()+"收款确认书");
				// 手动合成不发送邮件表
//				filters.put("sendFlag",Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SKQRS.value);
				filters.put("attaFlag",FileConstants.FILE_TYPE_SKQR );
				filters.put("templateName",ReportType.SKQRS_HK.getCode() );
				filters.put("customerCode", mview.getCustomerCode());
				filters.put("attachment", disAttachment);
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
				filters.put("from",from);
				filters.put("attaTableId",mview.getMatchingId() );
				FileUtil.compositeFile(filters);
				
			} else if(payType.equals(PayMent.NBZZ.value)){ // 如果付款方式为内部转账
				
				Map<String, Object> filters = new HashMap<String, Object>();
				/** 收款确认书-内转、成功部分内转 **/
				filters.put("parameter","matching_id="+mview.getMatchingId() );
				filters.put("fileName", mview.getCustomerName()+"收款确认书");
				// 手动合成不发送邮件表
//				filters.put("sendFlag",Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SKQRS.value);
				filters.put("attaFlag",FileConstants.FILE_TYPE_SKQR );
				filters.put("templateName",ReportType.SKQRS_NBZZ.getCode() );
				filters.put("lendCode", mview.getLendCode());
				filters.put("customerCode", mview.getCustomerCode());
				filters.put("attachment", disAttachment);
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
				filters.put("from",from);
				filters.put("attaTableId",mview.getMatchingId() );
				FileUtil.compositeFile(filters);
				
			} else if(payType.equals(PayMent.SHNZ.value)){ // 赎回内转
				//赎回内转无收款确认书
			}
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
	public  Map<String,BigDecimal> getppRateToMap(String productCode,BigDecimal applyLendMoney,String applyLendDay,String billType,int matchingBillDay,String matchingInterestStart){
		Map<String,BigDecimal> retMap = new HashMap<String, BigDecimal>();
		//获取产品匹配利率配置后的匹配利率
		ProductMatchingRate rate = productMatchingRateManager.getProductMchRate(productCode, applyLendMoney, applyLendDay,billType,matchingBillDay,matchingInterestStart);
		if(rate!=null){
			retMap.put("lower", rate.getMatchingRateLower());
			retMap.put("upper", rate.getMatchingRateUpper());
		}else{
			//获取产品默认匹配利率
			retMap = creditorAidManager.getProductDefaultMchRateByCode(productCode);
		}
		return retMap;
	}
	/**
	 *  根据还款日查询错期配置
	 * 2016年2月17日
	 * By 柳慧
	 * @param matchingBillDay
	 * @return
	 */
	public List<CreditorConfig> getfindByMatchingBillDay(int matchingBillDay, String billType){
		CreditorConfig creditorConfigParam = new CreditorConfig();
		creditorConfigParam.setDictConfigStatus(billType);
		creditorConfigParam.setConfigCreditDay(matchingBillDay);
		creditorConfigParam.setDictConfigZdr(ConfigStatus.QY.value);
		return 	creditorConfigManager.findByMatchingBillDay(creditorConfigParam); 
		
	}
	
	/**
	 * 获取可用债权
	 * 2016年4月13日
	 * By 柳慧
	 * @param mc
	 * @return creditLocationList
	 */
	public List<String> getUseableLoanInfos(MatchingCreditorView mc) {
		/** 可用债权配置列表 **/
		 Map<String ,Object> configParamMap = new HashMap<String,Object>();
		 configParamMap.put("configCity", mc.getCityId());
		 configParamMap.put("configType", mc.getMatchingFirstdayFlag());
		 configParamMap.put("configYy", mc.getStoreOrgId());
		 List<String> creditLocationList =  creditLocationListManager.getloanIdCards(configParamMap);
		return creditLocationList;
	}
	
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public void doEachTempAutoMatchingCreditor(MatchingCreditorEx ex, String cacId ){
		
		MatchingCreditorView mc  =  creditorAidManager.getMatchingCreditorViewByMatchingId(ex.getMatchingId());
		TempAutoMatchingCreditor tempAutoMatchingCreditor = new TempAutoMatchingCreditor();
		// 复制待推荐信息
		MatchingCreditorCopy(ex,tempAutoMatchingCreditor,mc);
		// 设置匹配利率
		Map<String,BigDecimal> ppRateMap =this.getppRateToMap(ex.getProductCode(),mc.getStartApplyLendMoney(),
				DateUtils.formatDate(mc.getApplyLendDay(), "yyyy-MM-dd"),ex.getMatchingFirstdayFlag(),ex.getMatchingBillDay(),DateUtils.formatDate(ex.getMatchingInterestStart(), "yyyy-MM-dd"));
		tempAutoMatchingCreditor.setMatchingRateLower(ppRateMap.get("lower"));
		tempAutoMatchingCreditor.setMatchingRateUpper(ppRateMap.get("upper"));
		// 设置错期匹配
		List<CreditorConfig> creditorConfigs =this.getfindByMatchingBillDay(ex.getMatchingBillDay(),ex.getMatchingFirstdayFlag());
		if(creditorConfigs!= null && creditorConfigs.size()>0){
			String creditorConfigStr ="";
			for(CreditorConfig cc:creditorConfigs){
				creditorConfigStr += cc.getConfigRepayDay()+",";
			}
			tempAutoMatchingCreditor.setLoanBackmoneyDays(creditorConfigStr.substring(0, creditorConfigStr.length()-1));
		 }else{//add by liusl 2016.11.22 控制错期匹配全关闭的情况，条件不能为空
			 tempAutoMatchingCreditor.setLoanBackmoneyDays("99");
		 }
		// 设置错期匹配
		List<String> creditLocationList = this.getUseableLoanInfos(mc);
		 if(creditLocationList!=null && creditLocationList.size()>0){
			String  filterLoanidCards ="";
			for(String loanidCard :creditLocationList){
				filterLoanidCards += loanidCard+",";
			}
			 tempAutoMatchingCreditor.setFilterLoanidCards(filterLoanidCards.substring(0,filterLoanidCards.length()-1));
		 }
		 // 设置匹配规则编号
		 tempAutoMatchingCreditor.setAutoId(cacId);
		 /** 插入临时表中**/
		 tempAutoMatchingCreditorDao.insert(tempAutoMatchingCreditor);
	
	}
	/**
	 * 待推荐信息复制
	 * 2016年5月11日
	 * By 柳慧
	 * @param ex
	 * @param tempAutoMatchingCreditor
	 */
	private void MatchingCreditorCopy(MatchingCreditorEx ex,
			TempAutoMatchingCreditor tempAutoMatchingCreditor,MatchingCreditorView mc) {
		tempAutoMatchingCreditor.setMatchingId(ex.getMatchingId());
		tempAutoMatchingCreditor.setLendCode(ex.getLendCode());
		tempAutoMatchingCreditor.setProductCode(ex.getProductCode());
		tempAutoMatchingCreditor.setMatchingFirstdayFlag(ex.getMatchingFirstdayFlag());
		tempAutoMatchingCreditor.setMatchingInterestStart(ex.getMatchingInterestStart());
		tempAutoMatchingCreditor.setMatchingBillDay(ex.getMatchingBillDay());
		tempAutoMatchingCreditor.setMatchingBorrowQuota(ex.getMatchingBorrowQuota());
		tempAutoMatchingCreditor.setMatchingStatus(ex.getMatchingStatus());
		tempAutoMatchingCreditor.setMatchingEndday(ex.getMatchingEndday());
		tempAutoMatchingCreditor.setMatchingTotal(ex.getMatchingTotal());
		tempAutoMatchingCreditor.setMatchingNow(ex.getMatchingNow());
		tempAutoMatchingCreditor.setMatchingExpireDay(ex.getMatchingExpireDay());
		tempAutoMatchingCreditor.setMatchingCossTime(ex.getMatchingCossTime());
		tempAutoMatchingCreditor.setMatchingMakeDay(ex.getMatchingMakeDay());
		tempAutoMatchingCreditor.setMatchingHkDay(ex.getMatchingHkDay());
		tempAutoMatchingCreditor.setMatchingFirstbillDay(ex.getMatchingFirstbillDay());
		tempAutoMatchingCreditor.setMatchingPayStatus(ex.getMatchingPayStatus());
		tempAutoMatchingCreditor.setCustomerCode(mc.getCustomerCode());
		tempAutoMatchingCreditor.setCreateBy(ex.getCreateBy());
		tempAutoMatchingCreditor.setCreateTime(ex.getCreateTime());
		tempAutoMatchingCreditor.setModifyBy(ex.getModifyBy());
		tempAutoMatchingCreditor.setModifyTime(ex.getModifyTime());
	}
	private void addLoanphaseCur(Loanphase lp){
		LoanphaseCur cur = new LoanphaseCur();
		cur.setPhaseId(lp.getPhaseId());
		cur.setMatchingId(lp.getMatchingId());
		cur.setLoanCode(lp.getLoanCode());
		cur.setLendCode(lp.getLendCode());
		cur.setPhaseNumber(lp.getPhaseNumber());
		cur.setPhaseAmount(lp.getPhaseAmount());
		cur.setPhaseInterestCur(lp.getPhaseInterestCur());
		cur.setPhasePrincipalCur(lp.getPhasePrincipalCur());
		cur.setPhasePrincipalSurplus(lp.getPhasePrincipalSurplus());
		cur.setPhaseRepaySign(lp.getPhaseRepaySign());
		cur.setPhaseRepaydateActual(lp.getPhaseRepaydateActual());
		cur.setPhaseBackCount(lp.getPhaseBackCount());
		cur.setPhaseBackPrincipal(lp.getPhaseBackPrincipal());
		cur.setPhaseBackInterest(lp.getPhaseBackInterest());
		cur.setPhaseBegindayCur(lp.getPhaseBegindayCur());
		cur.setPhaseEnddayCur(lp.getPhaseEnddayCur());
		cur.setPhaseMateId(lp.getPhaseMateId());
		cur.setPhaseMateNumber(lp.getPhaseMateNumber());
		cur.setPhaseNumberSurplus(lp.getPhaseNumberSurplus());
		cur.setPhaseDiscardStatus(lp.getPhaseDiscardStatus());
		cur.setPhaseReleaseStatus(lp.getPhaseReleaseStatus());
		cur.setPhaseFreezeNextstatus(lp.getPhaseFreezeNextstatus());
		cur.setCreateBy(lp.getCreateBy());
		cur.setCreateTime(lp.getCreateTime());
		cur.setModifyBy(lp.getModifyBy());
		cur.setModifyTime(lp.getModifyTime());
		loanphaseCurDao.insert(cur);
	}
	/**
	 *  获取账单周期天数 2016-01-05 前是账单日所在月天数， 之后为账单日周期天数
	 * 2016年5月13日
	 * By 柳慧
	 * @param lendDate
	 * @return
	 */
	private int getzqdays (Date lendDate){
		int ret = 0;
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	    Date temp;
		try {
			temp = sdf.parse("2016-01-05");
			if(MatchingUtils.daysBetween(temp, lendDate)){
				   Calendar cal = Calendar.getInstance();    
				   cal.setTime(temp);    
				   ret = cal.get(Calendar.DATE);  
			}else{
				ret =  CreditorUtils.getDateNumberBylendDay(lendDate);
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return ret;
				
	}
	
	/**
	 * 获取上期matching_id
	 * 2016年5月23日
	 * By 朱杰
	 * @param lendCode
	 * @param lastPhaseNumber
	 * @return
	 */
	private String getLastMatchingId(String lendCode,int lastPhaseNumber){
		Map<String,Object> lastParam = new HashMap<String,Object>();
		lastParam.put("lendCode", lendCode);
		lastParam.put("matchingNow", lastPhaseNumber);
		return LoanphaseDao.getLastMatchingId(lastParam);
	}
	
	/**
	 *  将待推荐添加到待自动匹配临时表中
	 * 2016年6月2日
	 * By 柳慧
	 * @param matchingId 待推荐编号
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void insertTempMatchingCreditor(String matchingId){
		// 查询自动匹配的规则集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("status", UseFlag.QY.value);
		List<CreditorperAutoConfig>  autoConfigLs = autoMatchingDao.findAll(param);
		if(autoConfigLs!= null && autoConfigLs.size()> 0){
				for(CreditorperAutoConfig cac : autoConfigLs){
					boolean stopFlag =false;
					Map<String,Object> autoparam = new HashMap<String, Object>();
					autoparam.put("matchingBillDay", cac.getBillDay());
					List<String> productCodes = new ArrayList<String>();
					productCodes.add(cac.getProductCode());
					autoparam.put("productCodes", productCodes);
					autoparam.put("matchingFirstdayFlag", cac.getBillType());
					List<String> orgCodes = new ArrayList<String>();
					String []strOrgCodes=null;
					if (!StringUtils.isEmpty(cac.getBusinessDepartment())){
						strOrgCodes = cac.getBusinessDepartment().split(",");
					}
					if (strOrgCodes!=null ) {
						for(int ind=0; ind < strOrgCodes.length; ind++){
							orgCodes.add(strOrgCodes[ind]);
						}
					}
					autoparam.put("orgCodes", orgCodes);
					List<String> matchingStatusLst = new ArrayList<String>();
					matchingStatusLst.add(MatchingStatus.DTJ.value);
					matchingStatusLst.add(MatchingStatus.CXCP.value);
					autoparam.put("matchingStatusLst", matchingStatusLst);
					autoparam.put("provinceCity", cac.getProvinceCity());
					List<String> matchingIds = matchingCreditorDao.getautoMatchingIdAll(autoparam); // 获取待自动匹配的集合	
					if(matchingIds!=null && matchingIds.size()>0){
					
						Map<String,List<String>> paramap = new HashMap<String, List<String>>();
						paramap.put("matchingIds", matchingIds);
						List<MatchingCreditorEx> matchingCreditors =  matchingCreditorDao.getMatchingCreditorByMatchingIds(paramap);
						for(MatchingCreditorEx ex: matchingCreditors){
							try{
								if(matchingId.equals(ex.getMatchingId())){
									this.doEachTempAutoMatchingCreditor(ex,cac.getId());
									cac.setInTotalNum(cac.getInTotalNum()+1);
									autoMatchingDao.update(cac);
									stopFlag = true;
									break;
								}else{
									continue;
								}
							}catch(Exception e){
								e.printStackTrace();
								logger.error("插入待自动匹配的待推荐信息",e);
							}
						}
					}
					if(stopFlag){
						break;
					}
				}
		
		}
	}
	
	/**
	 * 添加 分期收益期数数据
	 * 2016年6月11日
	 * By 柳慧
	 * @param mv
	 * @param phaseAmount
	 * @param phaseInterestCur
	 * @param creditIdAll
	 */
	private void addLoanphasePeriod(MatchingCreditorView mv,
			BigDecimal phaseAmount, BigDecimal phaseInterestCur,
			String creditIdAll) {
		LoanphasePeriod lopNew = new LoanphasePeriod();
		lopNew.setMatchingId(mv.getMatchingId());
		lopNew.setPhaseAmount(phaseAmount);
		lopNew.setCreditIdAll(creditIdAll.substring(0, creditIdAll.length()-1));
		lopNew.setPhaseInterestCur(phaseInterestCur);
		BigDecimal totalasset = BigDecimal.ZERO; // 当前累计资产总额
		if(mv.getMatchingFirstdayFlag().equals(BillState.SQ.value)){
			totalasset= mv.getStartApplyLendMoney().add(phaseInterestCur);
		}else{
			LoanphasePeriod lop = loanphasePeriodDao.getLastPeriodByLendCode(mv.getLendCode());
			if(lop!= null){
				totalasset = lop.getTotalasset().add(phaseInterestCur);
			}
		}
		if(!StringUtils.isEmpty(mv.getProductCode()) &&!ProductCode.YMY.value.equals(mv.getProductCode())){ //  非月满盈产品
			lopNew.setCreditNode(Node.KYZQ.value);
		}else{
			lopNew.setCreditNode(Node.YMYKY.value);
		}
		lopNew.setTotalasset(totalasset);
		lopNew.setModifyBy(UserUtils.getUserId());
		lopNew.setModifyTime(new Date());
		lopNew.setLendCode(mv.getLendCode());
		lopNew.setLendMoney(mv.getStartApplyLendMoney());
		lopNew.setReportPeriodStart(mv.getMatchingInterestStart());
		lopNew.setReportPeriodEnd(mv.getMatchingExpireDay());
		lopNew.setCreditIdAll(creditIdAll.substring(0, creditIdAll.length()-1));
		lopNew.setMatchingStatus(mv.getMatchingStatus());
		lopNew.setCustomerCode(mv.getCustomerCode());
		lopNew.setCreateBy(UserUtils.getUserId());
		lopNew.setCreateTime(new Date());
		lopNew.setId(IdGen.uuid());
		loanphasePeriodDao.insertSelective(lopNew);
		
	}
	
	/**
	 * 更新邮件附件路径
	 * 2016年6月26日
	 * By 朱杰
	 * @param attachment
	 * @return
	 */
	public int updateAttachmentFilePath(Attachment attachment){
		return emailInfoDao.updateAttachmentFilePath(attachment);
	}
	/**
	 * 通过待推荐ID获取待推荐信息
	 * 2016年10月18日
	 * By 常亚运
	 * @param matchingId
	 * @return
	 */
	public MatchingCreditorView getMatchingCreditorViewByMatchingId(
			String matchingId) {
		// TODO Auto-generated method stub
		return matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);

	}
}

