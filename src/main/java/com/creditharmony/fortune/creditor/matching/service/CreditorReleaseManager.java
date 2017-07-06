package com.creditharmony.fortune.creditor.matching.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.creditor.matching.dao.CreditorReleaseDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorRelease;
import com.creditharmony.fortune.creditor.matching.utils.CreditorReleaseState;
import com.creditharmony.fortune.creditor.matching.utils.CreditorReleaseUtil;
import com.creditharmony.fortune.creditor.matching.vo.CreditorReleaseView;
import com.creditharmony.fortune.creditor.matching.vo.CreditorReleaseVo;

/**
 * 债权到期释放Service
 * @author xurongsheng
 * @date 2016年11月23日 下午2:49:28
 *
 */
@Service
public class CreditorReleaseManager extends CoreManager<CreditorReleaseDao, CreditorRelease> {

	@Autowired 
	private CreditorReleaseDao creditorReleaseDao;
	
	@Autowired
	private BorrowDao borrowDao;
	
	public Page<CreditorReleaseVo> findPage(Page<CreditorReleaseVo> page,CreditorReleaseView creditorReleaseView){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		PageList<CreditorReleaseVo> pageList = (PageList<CreditorReleaseVo>) creditorReleaseDao.findList(paramMap,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	
	
	/**
	 * 检测能否释放
	 * @author xurongsheng
	 * @date 2016年12月27日 下午2:34:31
	 * @param creditorReleaseView
	 * @return
	 */
	public Map<String,Object> checkReleaseCreditor(CreditorReleaseView creditorReleaseView){
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		//检测是否存在转出状态为 '操作中'或'已操作'的
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(creditorReleaseView.getReleaseState() != null 
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.CZZ.value) 
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.YCZ.value)){
			//过滤条件中包含 转出状态,且转出状态 不为 操作中、已操作
			resultMap.put("existOtherState", false);
		}else{
			Map<String,Object> checkMap = new HashMap<String, Object>();
			checkMap.putAll(paramMap);
			checkMap.put("checkReleaseState", (CreditorReleaseState.CZZ.value+","+CreditorReleaseState.YCZ.value).split(","));
			Map<String, Object> checkResultMap = creditorReleaseDao.findCheck(checkMap);
			if(Integer.valueOf(checkResultMap.get("cr_count").toString()).intValue() > 0){
				resultMap.put("existOtherState", true);
			}else{
				resultMap.put("existOtherState", false);
			}
		}
		
		//查询总条数、总金额、总金额(L2)
		Map<String, Object> sumCheckResultMap = creditorReleaseDao.findCheck(paramMap);
		resultMap.put("selectCount", Integer.valueOf(sumCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("selectMoney", new BigDecimal(sumCheckResultMap.get("sum_money").toString()));
		resultMap.put("selectMoneyL2", new BigDecimal(sumCheckResultMap.get("sum_round_money").toString()));
		
		//查询剩余期数为0的总条数、总金额、总金额(L2)
		Map<String,Object> zeroCheckMap = new HashMap<String, Object>();
		zeroCheckMap.putAll(paramMap);
		zeroCheckMap.put("checkLoanMonthsSurplus","0");
		Map<String, Object> zeroCheckResultMap = creditorReleaseDao.findCheck(zeroCheckMap);
		resultMap.put("zeroCount", Integer.valueOf(zeroCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("zeroMoney", new BigDecimal(zeroCheckResultMap.get("sum_money").toString()));
		resultMap.put("zeroMoneyL2", new BigDecimal(zeroCheckResultMap.get("sum_round_money").toString()));
		
		return resultMap;
	}
	
	/**
	 * 释放
	 * @author xurongsheng
	 * @date 2016年11月28日 下午7:51:08
	 * @param ids
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public List<String> releaseCreditor(CreditorReleaseView creditorReleaseView){
		List<String> errorList =  new ArrayList<String>();
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		//待释放列表
		List<CreditorReleaseVo> crList = creditorReleaseDao.findList(paramMap);
		
		//开始释放
		for (CreditorReleaseVo creditorRelease : crList) {
			Date nowDate = new Date();
			
			Borrow borrow = borrowDao.get(creditorRelease.getCreditValueId());
			// 释放以后 可用债权价值>原始债权价值,则不予释放
			if(isOverQuota(borrow.getLoanQuota(),borrow.getLoanCreditValue(),creditorRelease.getReleaseCreditValue())){
				logger.info("债权释放时,到期释放["+creditorRelease.getReleaseId()+"]释放后可用债权价值>原始债权价值,所以不予释放.债权["+borrow.getCreditValueId()+"]");
				String errorStr = "{出借人:"+creditorRelease.getCustomerName()+",借款ID:"+creditorRelease.getLoanCode()+",借款人:"+creditorRelease.getLoanName()+"的到期释放(释放金额:"+creditorRelease.getReleaseCreditValue()+").释放后可用债权价值>原始债权价值.}";
				errorList.add(errorStr);
				// 更新债权到期释放状态
				updateCreditorIsRelease(creditorRelease,nowDate);
				continue;
			}
			
			// 更新可用债权表 
			updateBorrowInfo(creditorRelease,nowDate);
			// 插入债权管理记录表
			insertCreditorHis(creditorRelease,borrow,nowDate);
			// 更新债权到期释放状态
			updateCreditorIsRelease(creditorRelease,nowDate);
		}
		
		return errorList;
	}
	
	/**
	 * 获取总释放金额
	 * @author xurongsheng
	 * @date 2017年1月11日 下午1:18:51
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public BigDecimal getTotalReleaseMoney(Date startTime,Date endTime){
		BigDecimal totalMoney = BigDecimal.ZERO; //总释放金额
		Map<String,Object> getTotalMoneyMap = new HashMap<String,Object>();
		getTotalMoneyMap.put("startTime", DateUtils.formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
		getTotalMoneyMap.put("endTime", DateUtils.formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		Map<String,Object> firstPortMap = creditorReleaseDao.getReleaseFirstPart(getTotalMoneyMap);
		Map<String,Object> secordPortMap = creditorReleaseDao.getReleaseSecordPart(getTotalMoneyMap);
		if(firstPortMap.get("add_money")!=null){
			totalMoney = totalMoney.add(new BigDecimal(firstPortMap.get("add_money").toString()));
		}
		if(secordPortMap.get("add_money")!=null){
			totalMoney = totalMoney.add(new BigDecimal(secordPortMap.get("add_money").toString()));
		}
		return totalMoney;
	}
	
	/**
	 * 新增 债权释放统计
	 * @author xurongsheng
	 * @date 2017年1月11日 上午11:10:11
	 * @param startTime
	 * @param endTime
	 * @param totalMoney
	 * @return
	 */
	public int insertReleaseStat(Date startTime,Date endTime,BigDecimal totalMoney){
		Map<String,Object> releaseStatMap = new HashMap<String,Object>();
		releaseStatMap.put("id", IdGen.uuid());
		releaseStatMap.put("releaseCreditValue", totalMoney);
		releaseStatMap.put("releaseStartTime", startTime);
		releaseStatMap.put("releaseEndTime", endTime);
		releaseStatMap.put("createBy", UserUtils.getUserId());
		releaseStatMap.put("createTime", endTime);
		releaseStatMap.put("modifyBy", UserUtils.getUserId());
		releaseStatMap.put("modifyTime", endTime);
		return creditorReleaseDao.insertReleaseStat(releaseStatMap);
	}
	
	/**
	 * 判断释放以后 可用债权价值 是否 大于 原始债权价值
	 * @author xurongsheng
	 * @date 2016年12月28日 下午2:47:15
	 * @param loanQuota
	 * @param loanCreditValue
	 * @param releaseCreditValue
	 * @return
	 */
	private boolean isOverQuota(BigDecimal loanQuota,BigDecimal loanCreditValue,BigDecimal releaseCreditValue){
		return loanCreditValue.add(releaseCreditValue).compareTo(loanQuota) > 0 ;
	}
	
	/**
	 * 检测能否转出
	 * @author xurongsheng
	 * @date 2016年12月27日 下午2:34:31
	 * @param creditorReleaseView
	 * @return
	 */
	public Map<String,Object> checkTransferOut(CreditorReleaseView creditorReleaseView){
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		//检测是否存在转出状态不为 '未操作'的
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(creditorReleaseView.getReleaseState() != null 
				&& creditorReleaseView.getReleaseState().equals(CreditorReleaseState.WCZ.value)){
			//过滤条件中包含 转出状态,且转出状态 不为 操作中、已操作
			resultMap.put("existOtherState", false);
		}else{
			Map<String,Object> checkMap = new HashMap<String, Object>();
			checkMap.putAll(paramMap);
			checkMap.put("checkReleaseState", (CreditorReleaseState.CZZ.value+","+CreditorReleaseState.YCZ.value+","+CreditorReleaseState.CZSB.value).split(","));
			Map<String, Object> checkResultMap = creditorReleaseDao.findCheck(checkMap);
			if(Integer.valueOf(checkResultMap.get("cr_count").toString()).intValue() > 0){
				resultMap.put("existOtherState", true);
			}else{
				resultMap.put("existOtherState", false);
			}
		}
		
		//查询总条数、总金额、总金额(L2)
		Map<String, Object> sumCheckResultMap = creditorReleaseDao.findCheck(paramMap);
		resultMap.put("selectCount", Integer.valueOf(sumCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("selectMoney", new BigDecimal(sumCheckResultMap.get("sum_money").toString()));
		resultMap.put("selectMoneyL2", new BigDecimal(sumCheckResultMap.get("sum_round_money").toString()));
		
		//查询借款ID为空的总条数、总金额、总金额(L2)
		Map<String,Object> nullCheckMap = new HashMap<String, Object>();
		nullCheckMap.putAll(paramMap);
		nullCheckMap.put("checkCreditValueIdFlag","0");
		Map<String, Object> nullCheckResultMap = creditorReleaseDao.findCheck(nullCheckMap);
		resultMap.put("nullCount", Integer.valueOf(nullCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("nullMoney", new BigDecimal(nullCheckResultMap.get("sum_money").toString()));
		resultMap.put("nullMoneyL2", new BigDecimal(nullCheckResultMap.get("sum_round_money").toString()));
		
		
		//查询提前结清债权的总条数、总金额、总金额(L2)
		if(StringUtils.isNotEmpty(creditorReleaseView.getTjFlag()) && "1".equals(creditorReleaseView.getTjFlag())){
			//过滤条件中包含 提前结清标识,且提前结清标识 为 空
			resultMap.put("tjCount", 0);
			resultMap.put("tjMoney", BigDecimal.ZERO.toString());
			resultMap.put("tjMoneyL2", BigDecimal.ZERO.toString());
		}else{
			Map<String,Object> tjCheckMap = new HashMap<String, Object>();
			tjCheckMap.putAll(paramMap);
			tjCheckMap.put("tjFlag", "0");
			Map<String, Object> tjCheckResultMap = creditorReleaseDao.findCheck(tjCheckMap);
			resultMap.put("tjCount", Integer.valueOf(tjCheckResultMap.get("cr_count").toString()).intValue());
			resultMap.put("tjMoney", new BigDecimal(tjCheckResultMap.get("sum_money").toString()));
			resultMap.put("tjMoneyL2", new BigDecimal(tjCheckResultMap.get("sum_round_money").toString()));
		}
		
		//查询债权情况为负的总条数、总金额、总金额(L2)
		if(StringUtils.isNotEmpty(creditorReleaseView.getZqState()) && "1".equals(creditorReleaseView.getZqState())){
			//过滤条件中包含 债权情况,且债权情况 为 空
			resultMap.put("zqMinusCount", 0);
			resultMap.put("zqMinusMoney", BigDecimal.ZERO.toString());
			resultMap.put("zqMinusMoneyL2", BigDecimal.ZERO.toString());
		}else{
			Map<String,Object> zqCheckMap = new HashMap<String, Object>();
			zqCheckMap.putAll(paramMap);
			zqCheckMap.put("zqState", "0");
			Map<String, Object> zqCheckResultMap = creditorReleaseDao.findCheck(zqCheckMap);
			resultMap.put("zqMinusCount", Integer.valueOf(zqCheckResultMap.get("cr_count").toString()).intValue());
			resultMap.put("zqMinusMoney", new BigDecimal(zqCheckResultMap.get("sum_money").toString()));
			resultMap.put("zqMinusMoneyL2", new BigDecimal(zqCheckResultMap.get("sum_round_money").toString()));
			
			if(Integer.valueOf(zqCheckResultMap.get("cr_count").toString()).intValue() > 0){
				Map<String, Object> zqLoanResultMap = creditorReleaseDao.findZqLoanName(zqCheckMap);
				resultMap.put("zqMinusName", zqLoanResultMap.get("loan_name").toString());
			}
		}
		
		
		return resultMap;
	}
	
	/**
	 * 检测能否确认转出
	 * @author xurongsheng
	 * @date 2016年12月28日 下午3:27:18
	 * @param creditorReleaseView
	 * @return
	 */
	public Map<String,Object> checkConfirmTransferOut(CreditorReleaseView creditorReleaseView){
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		//检测是否存在 转出状态为'未操作'或 '已操作'的
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(creditorReleaseView.getReleaseState() != null 
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.WCZ.value)
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.YCZ.value)){
			//过滤条件中包含 转出状态,且转出状态 不为 操作中、已操作
			resultMap.put("existOtherState", false);
		}else{
			Map<String,Object> checkMap = new HashMap<String, Object>();
			checkMap.putAll(paramMap);
			checkMap.put("checkReleaseState", (CreditorReleaseState.WCZ.value+","+CreditorReleaseState.YCZ.value).split(","));
			Map<String, Object> checkResultMap = creditorReleaseDao.findCheck(checkMap);
			if(Integer.valueOf(checkResultMap.get("cr_count").toString()).intValue() > 0){
				resultMap.put("existOtherState", true);
			}else{
				resultMap.put("existOtherState", false);
			}
		}
		
		//查询总条数、总金额、总金额(L2)
		Map<String, Object> sumCheckResultMap = creditorReleaseDao.findCheck(paramMap);
		resultMap.put("selectCount", Integer.valueOf(sumCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("selectMoney", new BigDecimal(sumCheckResultMap.get("sum_money").toString()));
		resultMap.put("selectMoneyL2", new BigDecimal(sumCheckResultMap.get("sum_round_money").toString()));
		
		//查询提前结清债权的总条数、总金额、总金额(L2)
		if(StringUtils.isNotEmpty(creditorReleaseView.getTjFlag()) && "1".equals(creditorReleaseView.getTjFlag())){
			//过滤条件中包含 提前结清标识,且提前结清标识 为 空
			resultMap.put("tjCount", 0);
			resultMap.put("tjMoney", BigDecimal.ZERO.toString());
			resultMap.put("tjMoneyL2", BigDecimal.ZERO.toString());
		}else{
			Map<String,Object> tjCheckMap = new HashMap<String, Object>();
			tjCheckMap.putAll(paramMap);
			tjCheckMap.put("tjFlag", "0");
			Map<String, Object> tjCheckResultMap = creditorReleaseDao.findCheck(tjCheckMap);
			resultMap.put("tjCount", Integer.valueOf(tjCheckResultMap.get("cr_count").toString()).intValue());
			resultMap.put("tjMoney", new BigDecimal(tjCheckResultMap.get("sum_money").toString()));
			resultMap.put("tjMoneyL2", new BigDecimal(tjCheckResultMap.get("sum_round_money").toString()));
		}		
		
		//查询债权情况为负的总条数、总金额、总金额(L2)
		if(StringUtils.isNotEmpty(creditorReleaseView.getZqState()) && "1".equals(creditorReleaseView.getZqState())){
			//过滤条件中包含 债权情况,且债权情况 为 空
			resultMap.put("zqMinusCount", 0);
			resultMap.put("zqMinusMoney", BigDecimal.ZERO.toString());
			resultMap.put("zqMinusMoneyL2", BigDecimal.ZERO.toString());
			resultMap.put("zqMinusName", "");
		}else{
			Map<String,Object> zqCheckMap = new HashMap<String, Object>();
			zqCheckMap.putAll(paramMap);
			zqCheckMap.put("zqState", "0");
			Map<String, Object> zqCheckResultMap = creditorReleaseDao.findCheck(zqCheckMap);
			resultMap.put("zqMinusCount", Integer.valueOf(zqCheckResultMap.get("cr_count").toString()).intValue());
			resultMap.put("zqMinusMoney", new BigDecimal(zqCheckResultMap.get("sum_money").toString()));
			resultMap.put("zqMinusMoneyL2", new BigDecimal(zqCheckResultMap.get("sum_round_money").toString()));
			
			if(Integer.valueOf(zqCheckResultMap.get("cr_count").toString()).intValue() > 0){
				Map<String, Object> zqLoanResultMap = creditorReleaseDao.findZqLoanName(zqCheckMap);
				resultMap.put("zqMinusName", zqLoanResultMap.get("loan_name").toString());
			}
		}
		return resultMap;
	}
	
	/**
	 * 检测能否 结清
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:37:19
	 * @param creditorReleaseView
	 * @return
	 */
	public Map<String,Object> checkEarlySettlement(CreditorReleaseView creditorReleaseView){
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		//检测是否存在转出状态为 '操作中'或'已操作'的
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(creditorReleaseView.getReleaseState() != null 
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.CZZ.value) 
				&& !creditorReleaseView.getReleaseState().contains(CreditorReleaseState.YCZ.value)){
			//过滤条件中包含 转出状态,且转出状态 不为 操作中、已操作
			resultMap.put("existOtherState", false);
		}else{
			Map<String,Object> checkMap = new HashMap<String, Object>();
			checkMap.putAll(paramMap);
			checkMap.put("checkReleaseState", (CreditorReleaseState.CZZ.value+","+CreditorReleaseState.YCZ.value).split(","));
			Map<String, Object> checkResultMap = creditorReleaseDao.findCheck(checkMap);
			if(Integer.valueOf(checkResultMap.get("cr_count").toString()).intValue() > 0){
				resultMap.put("existOtherState", true);
				
			}else{
				resultMap.put("existOtherState", false);
			}
		}
		
		//查询提前结清债权的总条数、总金额、总金额(L2)
		if(StringUtils.isNotEmpty(creditorReleaseView.getTjFlag()) && "0".equals(creditorReleaseView.getTjFlag())){
			//过滤条件中包含 提前结清标识,且提前结清标识 为 空
			resultMap.put("tjCount", 0);
			resultMap.put("tjMoney", BigDecimal.ZERO.toString());
			resultMap.put("tjMoneyL2", BigDecimal.ZERO.toString());
		}else{
			Map<String,Object> tjCheckMap = new HashMap<String, Object>();
			tjCheckMap.putAll(paramMap);
			tjCheckMap.put("tjFlag", "1");
			Map<String, Object> tjCheckResultMap = creditorReleaseDao.findCheck(tjCheckMap);
			resultMap.put("tjCount", Integer.valueOf(tjCheckResultMap.get("cr_count").toString()).intValue());
			resultMap.put("tjMoney", new BigDecimal(tjCheckResultMap.get("sum_money").toString()));
			resultMap.put("tjMoneyL2", new BigDecimal(tjCheckResultMap.get("sum_round_money").toString()));
		}
		
		//查询总条数、总金额、总金额(L2)
		Map<String, Object> sumCheckResultMap = creditorReleaseDao.findCheck(paramMap);
		resultMap.put("selectCount", Integer.valueOf(sumCheckResultMap.get("cr_count").toString()).intValue());
		resultMap.put("selectMoney", new BigDecimal(sumCheckResultMap.get("sum_money").toString()));
		resultMap.put("selectMoneyL2", new BigDecimal(sumCheckResultMap.get("sum_round_money").toString()));
		
		return resultMap;
	}
	
	
	/**
	 * 更新借款信息
	 * @author xurongsheng
	 * @date 2016年11月29日 上午11:17:47
	 * @param creditorRelease
	 * @return
	 */
	private int updateBorrowInfo(CreditorReleaseVo creditorRelease,Date nowDate){
		Map<String,Object> borrowParamMap = new HashMap<String,Object>();
		borrowParamMap.put("creditValueId", creditorRelease.getCreditValueId());
		borrowParamMap.put("releaseMoney", creditorRelease.getReleaseCreditValue());
		borrowParamMap.put("modifyBy", CreditorReleaseUtil.BATCH_NAME);
		borrowParamMap.put("modifyTime", nowDate);
		return creditorReleaseDao.updateBorrow(borrowParamMap);
	}
	
	/**
	 * 插入债权管理记录表
	 * @author xurongsheng
	 * @date 2016年11月29日 上午11:19:48
	 * @param creditorRelease
	 * @return
	 */
	private int insertCreditorHis(CreditorReleaseVo creditorRelease,Borrow borrow,Date nowDate){
		Map<String,Object> creditorParamMap = new HashMap<String,Object>();
		creditorParamMap.put("id", IdGen.uuid());
		creditorParamMap.put("dictCheckNode", Node.KYZQ.value);// 节点=0:可用债权
		creditorParamMap.put("nodeId", creditorRelease.getCreditValueId());// 节点ID=债权价值id
		creditorParamMap.put("operateType", OperateType.CJDQZQSF.value); // 操作类型为=15:出借到期债权释放
		creditorParamMap.put("beforMoney", borrow.getLoanCreditValue()); // 操作前债权价值=可用债权价值
		creditorParamMap.put("operateMoney", creditorRelease.getReleaseCreditValue()); // 操作的债权价值=应释放金额
		creditorParamMap.put("afterMoney", borrow.getLoanCreditValue().add(creditorRelease.getReleaseCreditValue())); // 操作后债权价值=可用债权价值+应释放金额
		creditorParamMap.put("createBy", UserUtils.getUserId());
		creditorParamMap.put("createTime", nowDate);
		creditorParamMap.put("operateTime", nowDate);
		creditorParamMap.put("modifyBy", UserUtils.getUserId());
		creditorParamMap.put("modifyTime", nowDate);
		creditorParamMap.put("matchingId", creditorRelease.getMatchingId());
		return creditorReleaseDao.insertCreditorHis(creditorParamMap);
	}
	
	/**
	 * 更新 债权到期释放 的 是否已释放 标识
	 * @author xurongsheng
	 * @date 2016年11月29日 下午3:54:06
	 * @param creditorRelease
	 * @return
	 */
	private int updateCreditorIsRelease(CreditorReleaseVo creditorRelease,Date nowDate){
		Map<String,Object> creditorReleaseParamMap = new HashMap<String,Object>();
		creditorReleaseParamMap.put("releaseId", creditorRelease.getReleaseId());
		creditorReleaseParamMap.put("modifyBy", UserUtils.getUserId());
		creditorReleaseParamMap.put("modifyTime", nowDate);
		return creditorReleaseDao.updateCreditorReleaseForRelease(creditorReleaseParamMap);
	}
	
	/**
	 * 转出
	 * @author xurongsheng
	 * @date 2016年11月28日 下午7:51:13
	 * @param targetPlat
	 * @param ids
	 * @return
	 */
	public String transferOut(CreditorReleaseView creditorReleaseView){
		String result = "success";
		
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		List<CreditorReleaseVo> crList = creditorReleaseDao.findList(paramMap);
		for (CreditorReleaseVo creditorRelease : crList) {
			updateCreditorReleaseForOut(creditorRelease.getReleaseId(),creditorReleaseView.getTargetPlat());
		}
		
		return result;
	}
	
	/**
	 * 转出-更新状态
	 * @author xurongsheng
	 * @date 2016年12月28日 下午3:43:15
	 * @param releaseId
	 * @param targetPlat
	 * @return
	 */
	private int updateCreditorReleaseForOut(String releaseId,String targetPlat){
		Map<String,Object> transferOutParamMap = new HashMap<String,Object>();
		transferOutParamMap.put("ids", releaseId.split(","));
		transferOutParamMap.put("releaseTime", new Date());
		transferOutParamMap.put("releaseFlag", targetPlat);
		transferOutParamMap.put("releaseState", CreditorReleaseState.CZZ.value);
		transferOutParamMap.put("modifyBy", UserUtils.getUserId());
		transferOutParamMap.put("modifyTime", new Date());
		return creditorReleaseDao.updateCreditorReleaseForOut(transferOutParamMap);
	}
	
	/**
	 * 确认转出
	 * @author xurongsheng
	 * @date 2016年11月28日 下午7:51:24
	 * @param ids
	 * @return
	 */
	public String confirmTransferOut(CreditorReleaseView creditorReleaseView){
		String result = "success";
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		List<CreditorReleaseVo> crList = creditorReleaseDao.findList(paramMap);
		for (CreditorReleaseVo creditorRelease : crList) {
			updateCreditorReleaseForConfirm(creditorRelease.getReleaseId());
		}
		return result;
	}
	
	/**
	 * 确认转出-更新状态
	 * @author xurongsheng
	 * @date 2016年12月28日 下午3:43:15
	 * @param releaseId
	 * @param targetPlat
	 * @return
	 */
	private int updateCreditorReleaseForConfirm(String releaseId){
		Map<String,Object> transferOutParamMap = new HashMap<String,Object>();
		transferOutParamMap.put("ids", releaseId.split(","));
		transferOutParamMap.put("releaseTime", new Date());
		transferOutParamMap.put("releaseState", CreditorReleaseState.YCZ.value);
		transferOutParamMap.put("modifyBy", UserUtils.getUserId());
		transferOutParamMap.put("modifyTime", new Date());
		return creditorReleaseDao.updateCreditorReleaseForConfirm(transferOutParamMap);
	}
	
	/**
	 * 结清
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:39:35
	 * @param creditorReleaseView
	 * @return
	 */
	public String earlySettlement(CreditorReleaseView creditorReleaseView){
		String result = "success";
		//查询条件
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			paramMap.put("ids", "-1".split(","));
			e.printStackTrace();
		}
		
		List<CreditorReleaseVo> crList = creditorReleaseDao.findList(paramMap);
		for (CreditorReleaseVo creditorRelease : crList) {
			updateCreditorReleaseForEarlySettlement(creditorRelease.getReleaseId());
		}
		return result;
	}
	
	/**
	 * 结清-更新状态
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:40:09
	 * @param releaseId
	 * @return
	 */
	private int updateCreditorReleaseForEarlySettlement(String releaseId){
		Map<String,Object> transferOutParamMap = new HashMap<String,Object>();
		transferOutParamMap.put("ids", releaseId.split(","));
		transferOutParamMap.put("releaseTime", new Date());
		transferOutParamMap.put("releaseState", CreditorReleaseState.YJQ.value);
		transferOutParamMap.put("modifyBy", UserUtils.getUserId());
		transferOutParamMap.put("modifyTime", new Date());
		return creditorReleaseDao.updateCreditorReleaseForEarlySettlement(transferOutParamMap);
	}
	
	/**
	 * 查询 所有金额之和
	 * @author xurongsheng
	 * @date 2016年11月24日 下午4:04:42
	 * @param creditorReleaseView
	 * @return
	 */
	public BigDecimal findAllMoney(CreditorReleaseView creditorReleaseView){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigDecimal allMoney = creditorReleaseDao.findAllMoney(paramMap);
		return allMoney;
	}
	
	/**
	 * 查询 所有金额之和
	 * @author xurongsheng
	 * @date 2016年11月24日 下午4:04:42
	 * @param creditorReleaseView
	 * @return
	 */
	public BigDecimal findAllMoney2(CreditorReleaseView creditorReleaseView){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(creditorReleaseView != null){
				paramMap = objectToMap(creditorReleaseView);
				paramMap = filterMap(paramMap,creditorReleaseView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigDecimal allMoney = creditorReleaseDao.findAllMoney2(paramMap);
		return allMoney;
	}
	
	/**
	 * 对象转换成map
	 * 2016年1月25日
	 * By 柳慧
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private  Map<String, Object> objectToMap(Object obj) throws Exception {
		if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        } 
        return map;  
	}
	
	/**
	 * 过滤前台传来的特殊数据
	 * @author xurongsheng
	 * @date 2016年11月24日 下午4:03:19
	 * @param paramMap
	 * @param creditorReleaseView
	 * @return
	 */
	private Map<String, Object> filterMap(Map<String, Object> paramMap,CreditorReleaseView creditorReleaseView){
		//释放金额
		if (StringHelper.isNotEmpty(creditorReleaseView.getReleaseCreditValueFrom())) {
			if(creditorReleaseView.getReleaseCreditValueFrom().contains(",")){
				paramMap.put("releaseCreditValueFrom",creditorReleaseView.getReleaseCreditValueFrom().replaceAll(",", ""));
			}
		}
		if (StringHelper.isNotEmpty(creditorReleaseView.getReleaseCreditValueTo())) {
			if(creditorReleaseView.getReleaseCreditValueTo().contains(",")){
				paramMap.put("releaseCreditValueTo",creditorReleaseView.getReleaseCreditValueTo().replaceAll(",", ""));
			}
		}
		// 还款日
		if (StringHelper.isNotEmpty(creditorReleaseView.getLoanBackmoneyDay())) {
			List<Integer> list = new ArrayList<Integer>();
			String[] split = creditorReleaseView.getLoanBackmoneyDay().split(",");
			for (String string : split) {
				list.add(StringUtils.toInteger(string));
			}
			paramMap.put("loanBackmoneyDay",list);
		}
		// 债权区分
		if(StringHelper.isNotEmpty(creditorReleaseView.getDicLoanDistinguish())){
			paramMap.put("dicLoanDistinguish",creditorReleaseView.getDicLoanDistinguish().split(","));
		}
		//债权来源
		if (StringHelper.isNotEmpty(creditorReleaseView.getDictLoanType())) {
			paramMap.put("dictLoanType",creditorReleaseView.getDictLoanType().split(","));
		}
		//债权标识
		if (StringHelper.isNotEmpty(creditorReleaseView.getLoanTrusteeFlag())) {
			paramMap.put("loanTrusteeFlag",creditorReleaseView.getLoanTrusteeFlag().split(","));
		}
		//转出状态
		if (StringHelper.isNotEmpty(creditorReleaseView.getReleaseState())) {
			paramMap.put("releaseState",creditorReleaseView.getReleaseState().split(","));
		}
		//转出标识
		if (StringHelper.isNotEmpty(creditorReleaseView.getReleaseFlag())) {
			paramMap.put("releaseFlag",creditorReleaseView.getReleaseFlag().split(","));
		}
		//借款ID
		if (StringHelper.isNotEmpty(creditorReleaseView.getCreditValueIdFlag())) {
			if(creditorReleaseView.getCreditValueIdFlag().split(",").length > 1){
				paramMap.put("creditValueIdFlag","");
			}else{
				paramMap.put("creditValueIdFlag",creditorReleaseView.getCreditValueIdFlag().trim());
			}
		}
		//证件类型
		if (StringHelper.isNotEmpty(creditorReleaseView.getCustomerCertType())) {
			if(creditorReleaseView.getCustomerCertType().split(",").length > 1){
				paramMap.put("customerCertType","");
			}else{
				paramMap.put("customerCertType",creditorReleaseView.getCustomerCertType().trim());
			}
		}
		//选中项ID
		if (StringHelper.isNotEmpty(creditorReleaseView.getIds())) {
			paramMap.put("ids",creditorReleaseView.getIds().split(","));
		}
		//提前结清标识
		if (StringHelper.isNotEmpty(creditorReleaseView.getTjFlag())) {
			if(creditorReleaseView.getTjFlag().split(",").length > 1){
				paramMap.put("tjFlag","");
			}else{
				paramMap.put("tjFlag",creditorReleaseView.getTjFlag().trim());
			}
		}
		return paramMap;
	}
}
