package com.creditharmony.fortune.creditor.matching.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.out.ca.Ca_SignOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.entity.CaCustomerSign;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ProductStatus;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.job.dao.ProductProfitDao;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.config.auto.matching.dao.AutoMatchingDao;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.matching.dao.LoanphasePeriodDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.LoanphasePeriod;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingBorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeBorrowView;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeMonthAbleView;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.utils.BackMoneyUtil;
import com.creditharmony.fortune.utils.CaUtil;

/**
 * 债权辅助
 * @Class Name CreditorAidManager
 * @author 柳慧
 * @Create In 2016年4月13日
 */
@Service
public class CreditorAidManager {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AutoMatchingDao autoMatchingDao; 
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;// 待推荐Dao
	
	@Autowired
	private ProductMatchingRateManager productMatchingRateManager;
	
	@Autowired
	private LendApplyManager lendApplyManager;
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private AttachmentManager attachmentManager;
	
	@Autowired
	private LoanphasePeriodDao loanphasePeriodDao;
	
	@Autowired
	private LoanphaseDao LoanphaseDao;// 分期收益Dao
	
	@Autowired
	private ProductProfitDao profitDao;// 收益调整Dao
	
	/**
	 * 查出正在执行自动匹配的待推荐债权信息ID集合
	 * 2016年2月16日
	 * By 柳慧
	 * @param cafls
	 * @return
	 */
	public List<String> getFiltermatchingIds(){
		
		List<String> returnList = new ArrayList<String>();		
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("status", UseFlag.QY.value);
		List<CreditorperAutoConfig>  cafls = autoMatchingDao.findAll(param);
		if(cafls==null || cafls.size()==0){
			return null;
		}else{
			List<String> productCodes = new ArrayList<String>();
			List<Integer> matchingBillDays = new ArrayList<Integer>();
			List<String> matchingFirstdayFlags = new ArrayList<String>();
			List<String> orgCodes = new ArrayList<String>();
			
			String []strOrgCodes=null;
			
			Map<String,Object> filterMap;
			List<String> oneList = new ArrayList<String>();	
			
			for(CreditorperAutoConfig caf :cafls){
				productCodes.add(caf.getProductCode());
				matchingBillDays.add(caf.getBillDay());
				matchingFirstdayFlags.add(caf.getBillType());
				
				// 拆分business_department:营业部id,营业部id,营业部id
				strOrgCodes=null;
				
				if (!StringUtils.isEmpty(caf.getBusinessDepartment())){
					strOrgCodes = caf.getBusinessDepartment().split(",");
				}
				
				if (strOrgCodes!=null ) {
					for(int ind=0; ind < strOrgCodes.length; ind++){
						orgCodes.add(strOrgCodes[ind]);
					}
				}
				
				//orgCodes.add(caf.getBusinessDepartment());							
				//cityIds.add(caf.getCityId());
				filterMap = new HashMap<String, Object>();
				filterMap.put("matchingBillDays", matchingBillDays);
				filterMap.put("productCodes", productCodes);
				filterMap.put("matchingFirstdayFlags", matchingFirstdayFlags);
				filterMap.put("orgCodes", orgCodes);
				filterMap.put("provinceCity", caf.getProvinceCity());	
				
				//一次查询
				oneList = matchingCreditorDao.getFiltermatchingIds(filterMap);
				
				//加入结果中
				if (oneList!=null && oneList.size()>0) {
					returnList.addAll(oneList);
				}
			}						
			
			return returnList;
		}
	}
	
	/**
	 * 通过待推荐ID获取待推荐实体信息
	 * 2015年12月16日
	 * By 柳慧
	 * @param matchingId 待推荐ID
	 * @return
	 */
	public MatchingCreditorView getMatchingCreditorViewByMatchingId(
			String matchingId) {
		return matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
	}
	
	/**
	 * 通过出借日期获取首期期限日期
	 * 2016年1月21日
	 * By 柳慧
	 * @param param
	 * @return
	 */
	public Date getFristLendDay(Map<String, String> param) {
		return matchingCreditorDao.getFristLendDay(param);
	}
	
	/**
	 * 通过产品Code获取该产品默认利率
	 * 2016年3月17日
	 * By 柳慧
	 * @param productCode
	 * @return
	 */
	public Map<String,BigDecimal>  getProductDefaultMchRateByCode(String productCode) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("productStatus", ProductStatus.SY.value);
		return matchingCreditorDao.getProductDefaultMchRateByCode(paramMap);
	}
	
	/**
	 * 通过债权ID 判断债权是否属于同类债权
	 * 2016年3月30日
	 * By 柳慧
	 * @param paramLst
	 * @return
	 */
	public List<String> getDictLoanTypeByCreditMonableIds(String [] paramLst) {
		return matchingCreditorDao.getDictLoanTypeByCreditMonableIds(paramLst);
	}
	/**
	 * 判断该待推荐月满盈是否车借
	 * 2016年4月30日
	 * By 柳慧
	 * @param matchingId
	 * @return
	 */
	public  boolean MonthBorrowIsCjByMathing(String matchingId){
		Map <String,String> param = new HashMap<String, String>();
		param.put("creditNode",Node.YMYKY.value);
		param.put("matchingId", matchingId);
		param.put("tradeCreditStatus", CreditTradestate.WKSBGB.value);
		param.put("dictLoanType", CreditSrc.CJ.value);
		int  count =  matchingCreditorDao.getMonthBorrowCountByMathingId(param);
		if (count > 0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 通过待推荐主键获取回款金额 
	 * 2016年3月11日
	 * By 柳慧
	 * @param matchingId
	 * @return
	 */
	public BigDecimal getBackMoneyCommon(String matchingId){
		MatchingCreditorView mv = matchingCreditorDao.getMatchingCreditorViewByMatchingId(matchingId);
		return this.getBackMoney(mv);
	}
	
	/**
	 * 回款金额获取
	 * 2016年3月7日
	 * By 柳慧
	 * @param mv
	 * @return
	 */
	public BigDecimal getBackMoney(MatchingCreditorView mv){
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal matchingRate = BigDecimal.ZERO;
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		 ProductMatchingRate rate = productMatchingRateManager.getProductMchRate(mv.getProductCode(),mv.getMatchingBorrowQuota(),
					sdf.format(mv.getMatchingInterestStart()),mv.getMatchingFirstdayFlag(),mv.getMatchingBillDay(),sdf.format(mv.getMatchingInterestStart()));
			if(rate!=null){ // 如果匹配利率有用匹配
				if(rate.getMatchingRateLower()!=null){
					matchingRate = rate.getMatchingRateLower();
			}else{ // 如果匹配利率没有 用产品匹配利率
				Map<String,BigDecimal>  rateMap = this.getProductDefaultMchRateByCode(mv.getProductCode());
				if(rateMap!=null){
					if(rateMap.get("lower")!=null){
						matchingRate = rateMap.get("lower");
					}
				}
			}
		}
		
		LoanApply  paramlendApply = new LoanApply(); 
		paramlendApply.setProductCode(mv.getProductCode());
		paramlendApply.setLendDate(mv.getMatchingInterestStart());
		paramlendApply.setLendMoney(mv.getStartApplyLendMoney());
		BigDecimal productRate =lendApplyManager.getRate(paramlendApply);
		
		String productCode = mv.getProductCode();
		// 月息通、信和月增，20160801后，利率取收益调整表中利率
		if (productCode.equals(ProductCode.YXT.value) || productCode.equals(ProductCode.XHYZ.value)) {
			Date date20160801 = DateUtils.parseDate(BmConstant.YXT_DATE);
			if (date20160801.after(mv.getApplyLendDay())) {
				// 20160801前，公式不变
			} else {
				// 20160801后，利率取收益调整表中利率
				ProductProfit profit = profitDao.getByLoanApply(paramlendApply);
				if (profit != null) {
					matchingRate = profit.getProductYearRate();
					if (matchingRate == null) {
						matchingRate = productRate.multiply(BigDecimal.valueOf(12));
					}
					productRate = profit.getProductRate();
				} else {
					matchingRate = productRate.multiply(BigDecimal.valueOf(12));
				}
			}
		}
		
		ret = BackMoneyUtil.getBackMoney(mv.getProductCode(), 
				mv.getApplyLendDay(),
				mv.getMatchingBillDay(),
				mv.getApplyExpireDay(),
				mv.getMatchingBorrowQuota(),
				productRate,
				matchingRate,
				mv.getBackType(),
				mv.getMatchingTotal().toString());
		return ret;
	}
	
	/**
	 * 获取匹配记录
	 * 2016年1月20日
	 * By 柳慧
	 * @param param 
	 * @return
	 */
	public Map<String, Long> getppll(Map<String, String> param) {
		Map <String, Long> ret =  matchingCreditorDao.getppll(param);
		if(ret==null || ret.size() <= 0){
			ret = new HashMap<String, Long>();
			ret.put("ytjsqnum", Long.valueOf(0));
			ret.put("ytjfsqnum", Long.valueOf(0));
			ret.put("wtjsqnum", Long.valueOf(0));
			ret.put("wtjfsqnum", Long.valueOf(0));
		}
		return ret;
	}
	
	/**
	 * 通过出借客户编号获取既有历史债权列表 首期
	 * 2015年12月28日
	 * By 柳慧
	 * @param Map<String,String > hisListParam
	 * @return
	 */
	public List<CreditorTradeBorrowView> getCreditorTradeBorrowView(
			Map<String,String > hisListParam) {
		return matchingCreditorDao.getCreditorTradeBorrowView(hisListParam);
	}
	
	/**
	 * 通过待推荐债权ID获取既有历史债权列表 非首期
	 * 2015年12月29日
	 * By 柳慧
	 * @param lendCode
	 * @return
	 */
	public List<CreditorTradeBorrowView> getCreditorTradeBorrowViewNOFrist(
			 Map<String,Object> paramShowMap) {
		return matchingCreditorDao.getCreditorTradeBorrowViewNOFrist(paramShowMap);
	}

	/**
	 * 通过出借编号获取既有历史债权列表 月满盈
	 * 2015年12月30日
	 * By 柳慧
	 * @param customerCode
	 * @return
	 */
	public List<CreditorTradeMonthAbleView> getCreditorTradeMonthAbleViews(
			Map<String,String > hisListParam) {
		return matchingCreditorDao.getCreditorTradeMonthAbleViews(hisListParam);
	}
	
	/** 
	 *  获取待推荐实体
	 * 2016年3月11日
	 * By 柳慧
	 * @param mex
	 * @return
	 */
	public MatchingCreditorEx get(MatchingCreditorEx mex) {
		return matchingCreditorDao.get(mex);
	}

	
	/**
	 * 通过过滤条件 查询匹配结果
	 * 2015年12月26日
	 * By 柳慧
	 * @param mbEx
	 * @return
	 */
	public List<Borrow> getsqppByMbEx(MatchingBorrowEx mbEx) {
		return matchingCreditorDao.getsqppByMbEx(mbEx);
	}
	
	/**
	 * 债权协议加盖作废章
	 * 2016年5月11日
	 * By 朱杰
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void caInvalidCreditorFile(String matchingId){
		LoanApply loanApply = new LoanApply();
		Attachment atta = new Attachment();
		atta.setAttaFileOwner("t_tz_matching_creditor");
		atta.setAttaTableId(matchingId);
		atta.setAttaFileType("pdf");
		atta.setDictAttaFlag(FileConstants.FILE_TYPE_SR);
		List<Attachment> attachment = attachmentManager.findList(atta);
		
		for(Attachment elem : attachment){
			String lendCode = elem.getLoanCode();				
			loanApply.setApplyCode(lendCode);
			loanApply = loanApplyDao.get(loanApply);
			
			if (loanApply == null) {
				loanApply = new LoanApply();
				continue;
			}
			
			DmService dmService = DmService.getInstance();	
			// 出借合同作废签章
			CaCustomerSign param = new CaCustomerSign();
			param.setBatchNo(loanApply.getCustCode());
			param.setSubType(loanApply.getApplyCode());
			Ca_SignOutBean out = CaUtil.sign(CASignType.ZF_CREDITFILE.value, elem.getAttaFilepath(), param);
			if (!ReturnConstant.SUCCESS.equals(out.getRetCode())) {
				throw new ServiceException(out.getRetCode()+":"+out.getRetMsg());
			}
			//将文件移动至固定文件夹内
			//dmService.moveDocument(out.getDocId(), loanApply.getCustCode() + "/" + loanApply.getApplyCode(), DmService.BUSI_TYPE_FORTUNE);
		    // 更新成已经签章的附件
			atta.setAttaId(elem.getAttaId());
			atta.setAttaFilepath(out.getDocId());
			atta.preUpdate();
			attachmentManager.updateAttachment(atta);
		}
		
	}
	public void updatetLoanphasePeriodBymatchingId(MatchingCreditorView mv){
		Loanphase lpParam = new Loanphase();
		lpParam.setMatchingId(mv.getMatchingId());
		lpParam.setPhaseDiscardStatus(ScrapState.BFQ.value);// 不废弃状态
		List<Loanphase> los = LoanphaseDao.getLoanphasebyMatchingId(lpParam);
		if(los!=null && los.size()>0){
			BigDecimal phaseAmount = BigDecimal.ZERO;// 本期应还总本息
			BigDecimal phaseInterestCur = BigDecimal.ZERO; // 本期应还总利息
			BigDecimal totalasset = BigDecimal.ZERO; // 当前累计资产总额
			String creditIdAll ="";
			for(Loanphase lo :los){
				phaseAmount = phaseAmount.add(lo.getPhaseAmount());
				phaseInterestCur = phaseInterestCur.add(lo.getPhaseInterestCur());
				creditIdAll = creditIdAll+lo.getLoanCode()+",";
			}
			LoanphasePeriod lop = loanphasePeriodDao.getLastPeriodByLendCode(mv.getLendCode());
			if(lop!= null){
				totalasset = lop.getTotalasset().add(phaseInterestCur);
			}else{
				totalasset = phaseInterestCur.add(mv.getStartApplyLendMoney());
			}
		LoanphasePeriod lopNew = new LoanphasePeriod();
		lopNew.setMatchingId(mv.getMatchingId());
		lopNew.setPhaseAmount(phaseAmount);
		lopNew.setCreditIdAll(creditIdAll.substring(0, creditIdAll.length()-1));
		lopNew.setPhaseInterestCur(phaseInterestCur);
		lopNew.setTotalasset(totalasset);
		lopNew.setModifyBy(UserUtils.getUserId());
		lopNew.setModifyTime(new Date());
		loanphasePeriodDao.updateByPrimaryKeySelective(lopNew);
		}
	}
}
