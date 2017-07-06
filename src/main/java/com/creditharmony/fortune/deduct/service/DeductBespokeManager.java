package com.creditharmony.fortune.deduct.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.dao.DeductBespokeDao;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;

/**
 * 预约划扣
 * @Class Name DeductBespokeManager
 * @author 韩龙
 * @Create In 2016年2月1日
 */
@Service
public class DeductBespokeManager extends CoreManager<DeductBespokeDao, DeductBespoke>{
	
	@Autowired
	private DeductApplyDao deductApplyDao;
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private CustomerAccountDao customerAccountDao;
	
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	
	@Autowired
	private TheDayDeductDao theDayDeductDao;
	
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 单天划扣--->保存预约划扣 2016年2月18日 By 韩龙
	 * 
	 * @param deductBespoke
	 * @return
	 *//*
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveDeductBespoke(DeductBespoke deductBespoke,String status,
			String applyCode, List<String> message) {
		String [] strArray = applyCode.split("_");
		// 出借编号
		String lendCode = strArray[0];
		// 更新时间（排它标识）
		String verTime = strArray[1];
		// 排它
		DeductPool checkDp = new DeductPool();
		checkDp.setApplyCode(lendCode);
		checkDp.setVerTime(verTime);
		checkDp = deductApplyDao.getForUpdate(checkDp);
		if (checkDp == null) {
			String info = "出借编号【" + lendCode + "】:已被其它用户处理过";
			logger.debug(info);
			message.add(info);
			return;
		}

		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		CustomerAccount customerAccount = customerAccountDao.get(loanApply
				.getPaymentBankId());
		String temp = platformGotoRuleManager.getDeductRule(
				DeductUtils.isNull(loanApply.getDeductTypeId()),
				DeductUtils.isNull(customerAccount.getAccountAddrProvince()),
				DeductUtils.isNull(customerAccount.getAccountBankId()));
		String rule = DeductUtils.getDeductRule(temp);

		// 组装划扣预约对象
		DeductBespoke tempDeductBespoke = dao
				.findDeductBespokeByLendCode(lendCode);
		tempDeductBespoke.setId(IdGen.uuid());
		tempDeductBespoke.setBespokeDate(deductBespoke.getBespokeDate());
		tempDeductBespoke.setExecutionTimeSegment(DeductUtils
				.isNull(deductBespoke.getExecutionTimeSegment()));
		tempDeductBespoke.setDictDeductPlatformId(DeductUtils
				.isNull(deductBespoke.getDictDeductPlatformId()));
		tempDeductBespoke.setDictDeductRule(rule);
		tempDeductBespoke.setAccountProperty("1");
		tempDeductBespoke.setDictCustomerCertType(tempDeductBespoke
				.getDictCustomerCertType());
		tempDeductBespoke.setLendCode(DeductUtils.isNull(lendCode));
		tempDeductBespoke.setDictDeductStatus(DeductState.YYY.value);
		tempDeductBespoke.setCreateBy(UserUtils.getUser().getName());
		tempDeductBespoke.setCreateTime(new Date());
		tempDeductBespoke.setLendCode(lendCode);
		tempDeductBespoke.setForeginId(DeductUtils.isNull(lendCode));
		// 组装对象
		DeductReq deductReq = DeductUtils.header(tempDeductBespoke);
		// check对象
		String msg = TaskService.validate(deductReq);
		if (StringUtils.isNotBlank(msg) && msg.length() > 0) {
			logger.debug("check失败信息:" + msg);
			message.add(msg);
			return;
		}
		// 修改划扣状态为已预约
		DeductPool dp = new DeductPool();
		dp.setApplyCode(lendCode);
		if (status.equals(DeductState.DHKJS.value)) {
			// 划扣结算列表预约划扣
			dp.setDictDeductStatus(DeductState.YYHK.value);
		} else {
			// 划扣失败列表二次预约划扣
			dp.setDictDeductStatus(DeductState.ECYYHK.value);
		}
		// 预约划扣金额
		BigDecimal big = loanApply.getLendMoney().subtract(
				new BigDecimal(DeductUtils.isNullMoney(dp
						.getActualDeductMoney())));
		tempDeductBespoke.setBespokeDeductMoney(big);
		dao.insert(tempDeductBespoke);
		// 更新状态
		dp.preUpdate();
		deductApplyDao.update(dp);
		// 全程流痕
		checkManager.addCheck(dp.getApplyCode(), "单天预约划扣",
				DeductState.getDeductState(status),
				dp.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
	}*/
	
	/**
	 * 多线程
	 * 单天划扣--->保存预约划扣 
	 * 2016年2月18日 
	 * By 韩龙
	 * @param deductBespoke
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveDeductBespoke(DeductBespoke deductBespoke,String status,
			String applyCode, StringBuffer message) {
		String [] strArray = applyCode.split("_");
		// 出借编号
		String lendCode = strArray[0];
		// 更新时间（排它标识）
		String verTime = strArray[1];
		// 排它
		DeductPool checkDp = new DeductPool();
		checkDp.setApplyCode(lendCode);
		checkDp.setVerTime(verTime);
		checkDp = deductApplyDao.getForUpdate(checkDp);
		if (checkDp == null) {
			String info = "出借编号【" + lendCode + "】:已被其它用户处理过";
			logger.debug(info);
			message.append(info);
			return;
		}

		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		CustomerAccount customerAccount = customerAccountDao.get(loanApply
				.getPaymentBankId());
		String temp = platformGotoRuleManager.getDeductRule(
				DeductUtils.isNull(loanApply.getDeductTypeId()),
				DeductUtils.isNull(customerAccount.getAccountAddrProvince()),
				DeductUtils.isNull(customerAccount.getAccountBankId()));
		String rule = DeductUtils.getDeductRule(temp);

		// 组装划扣预约对象
		DeductBespoke tempDeductBespoke = dao
				.findDeductBespokeByLendCode(lendCode);
		tempDeductBespoke.setId(IdGen.uuid());
		tempDeductBespoke.setBespokeDate(deductBespoke.getBespokeDate());
		tempDeductBespoke.setExecutionTimeSegment(DeductUtils
				.isNull(deductBespoke.getExecutionTimeSegment()));
		tempDeductBespoke.setDictDeductPlatformId(DeductUtils
				.isNull(deductBespoke.getDictDeductPlatformId()));
		tempDeductBespoke.setDictDeductRule(rule);
		tempDeductBespoke.setAccountProperty("1");
		tempDeductBespoke.setDictCustomerCertType(tempDeductBespoke
				.getDictCustomerCertType());
		tempDeductBespoke.setLendCode(DeductUtils.isNull(lendCode));
		tempDeductBespoke.setDictDeductStatus(DeductState.YYY.value);
		tempDeductBespoke.setCreateBy(UserUtils.getUser(UserUtils.getUserId()).getName());
		tempDeductBespoke.setCreateTime(new Date());
		tempDeductBespoke.setLendCode(lendCode);
		tempDeductBespoke.setForeginId(DeductUtils.isNull(lendCode));
		// 组装对象
		DeductReq deductReq = DeductUtils.header(tempDeductBespoke);
		// check对象
		String msg = TaskService.validate(deductReq);
		if (StringUtils.isNotBlank(msg) && msg.length() > 0) {
			logger.debug("check失败信息:" + msg);
			message.append(msg);
			return;
		}
		// 修改划扣状态为已预约
		DeductPool dp = new DeductPool();
		dp.setApplyCode(lendCode);
		if (status.equals(DeductState.DHKJS.value)) {
			// 划扣结算列表预约划扣
			dp.setDictDeductStatus(DeductState.YYHK.value);
		} else {
			// 划扣失败列表二次预约划扣
			dp.setDictDeductStatus(DeductState.ECYYHK.value);
		}
		// 预约划扣金额
		BigDecimal big = loanApply.getLendMoney().subtract(
				new BigDecimal(DeductUtils.isNullMoney(dp
						.getActualDeductMoney())));
		tempDeductBespoke.setBespokeDeductMoney(big);
		dao.insert(tempDeductBespoke);
		// 更新状态
		dp.preUpdate();
		deductApplyDao.update(dp);
		// 全程流痕
		checkManager.addCheck(dp.getApplyCode(), "单天预约划扣",
				DeductState.getDeductState(status),
				dp.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
	}

	/**
	 * 单天划扣--->预约划扣取消 2016年2月19日 By 韩龙
	 * 
	 * @param id
	 * @param value
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int cancelDeductBespoke(String id, String status) {
		int result = 0;
		String[] arrayId = id.split(",");
		for (String str : arrayId) {
			String [] strArray = str.split("_");
			// 出借编号
			String lendCode = strArray[0];
			// 更新时间(排它标识)
			String verTime = strArray[1];
			DeductPool dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setVerTime(verTime);
			// 排它
			dp = deductApplyDao.getForUpdate(dp);
			if(dp == null){
				logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理过");
				continue;
			}
			if (status.equals(DeductState.DHKJS.value)) {
				// 设置待划扣结算状态
				dp.setDictDeductStatus(DeductState.DHKJS.value);
			} else {
				// 设置待划扣失败状态
				dp.setDictDeductStatus(DeductState.HKSB.value);
			}
			// 更新状态
			dp.preUpdate();
			deductApplyDao.update(dp);
			DeductBespoke deductBespoke = new DeductBespoke();
			deductBespoke.setForeginId(lendCode);
			deductBespoke.setDictDeductStatus(DeductState.QXYY.value);
			deductBespoke.preUpdate();
			dao.update(deductBespoke);
			// 全程流痕
			checkManager.addCheck(lendCode, "单天预约划扣取消",
					DeductState.getDeductState(status),
					dp.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
			result++;
		}
		return result;
	}
	
	/**
	 * 分天保存预约划扣
	 * 2016年2月16日
	 * By 韩龙
	 * @param id
	 */
	/*@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveDeductBespokeTheDay(DeductBespoke deductBespoke,String status,
		String theDayIds,List<String> messages){
		// 组装分天划扣对象
		ThedayDeductPool tdpool = new ThedayDeductPool();
		String[] strArray = theDayIds.split("_");
		String id = strArray[0];
		String verTime = strArray[1];
		tdpool.setId(id);
		tdpool.setVerTime(verTime);
		// 获取出借信息
		Map<String,Object> par = new HashMap<String,Object>();
		par.put("id", id);
		Map<String,String> map = theDayDeductDao.getTheDaysById(par);
		LoanApply loanApply = new LoanApply();
		String lendCode = map.get("lend_code");
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		// 排它
		tdpool = theDayDeductDao.getForUpdate(tdpool);
		if(tdpool == null){
			String info = "出借编号【" + lendCode + "】的分天划扣ID【"+id+"】:已被其它业务处理过";
			logger.debug(info);
			messages.add(info);
			return;
		}
		// 获取银行
		CustomerAccount customerAccount = customerAccountDao.get(loanApply.getPaymentBankId());
//		String temp = platformGotoRuleManager.getDeductRule(DeductUtils.isNull(loanApply.getDeductTypeId()), 
//				DeductUtils.isNull(customerAccount.getAccountAddrProvince()), 
//				DeductUtils.isNull(customerAccount.getAccountBankId()));
//		String rule = DeductUtils.getDeductRule(temp);
		String rule = tdpool.getJumpRule();
		
		DeductBespoke tempDeductBespoke = theDayDeductDao.findDeductBespokeByDayDeductId(id);
		tempDeductBespoke.setId(IdGen.uuid());
		tempDeductBespoke.setBespokeDate(deductBespoke.getBespokeDate());
		tempDeductBespoke.setExecutionTimeSegment(deductBespoke.getExecutionTimeSegment());
		tempDeductBespoke.setDictDeductPlatformId(deductBespoke.getDictDeductPlatformId());
		tempDeductBespoke.setDictDeductRule(rule);
		tempDeductBespoke.setDayDeductId(id);
		tempDeductBespoke.setDictDeductStatus(DeductState.YYY.value);
		tempDeductBespoke.setAccountProperty("1");
		tempDeductBespoke.setCreateBy(UserUtils.getUser().getName());
		tempDeductBespoke.setCreateTime(new Date());
		tempDeductBespoke.setForeginId(id);
		// 组装对象
		DeductReq deductReq = DeductUtils.header(tempDeductBespoke);
		// check对象
		String msg = TaskService.validate(deductReq);
		if(StringUtils.isNotBlank(msg) && msg.length() >0){
			logger.debug("check失败信息:" + msg);
			messages.add(msg);
			return;
		}
		// 验证通过保存
		dao.insert(tempDeductBespoke);
		if(status.equals(DeductState.YYHK.value)){
			tdpool.setDictDeductStatus(DeductState.YYHK.value);
		}else{
			tdpool.setDictDeductStatus(DeductState.ECYYHK.value);
		}
		tdpool.preUpdate();
		theDayDeductDao.update(tdpool);
		// 全程流痕
		checkManager.addCheck(lendCode, "分天预约划扣id【"+tempDeductBespoke.getId()+"】",
				DeductState.getDeductState(status),tempDeductBespoke.getId()
				,FortuneLogNode.DEDUCT_THEDAY);
	}*/
	
	/**
	 * 分天保存预约划扣
	 * 2016年2月16日
	 * By 韩龙
	 * @param id
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void saveDeductBespokeTheDay(DeductBespoke deductBespoke,String status,
		String theDayIds,StringBuffer messages){
		// 组装分天划扣对象
		ThedayDeductPool tdpool = new ThedayDeductPool();
		String[] strArray = theDayIds.split("_");
		String id = strArray[0];
		String verTime = strArray[1];
		tdpool.setId(id);
		tdpool.setVerTime(verTime);
		// 获取出借信息
		Map<String,Object> par = new HashMap<String,Object>();
		par.put("id", id);
		Map<String,String> map = theDayDeductDao.getTheDaysById(par);
		LoanApply loanApply = new LoanApply();
		String lendCode = map.get("lend_code");
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		// 排它
		tdpool = theDayDeductDao.getForUpdate(tdpool);
		if(tdpool == null){
			String info = "出借编号【" + lendCode + "】的分天划扣ID【"+id+"】:已被其它业务处理过";
			logger.debug(info);
			messages.append(info);
			return;
		}
		// 获取银行
		CustomerAccount customerAccount = customerAccountDao.get(loanApply.getPaymentBankId());
//		String temp = platformGotoRuleManager.getDeductRule(DeductUtils.isNull(loanApply.getDeductTypeId()), 
//				DeductUtils.isNull(customerAccount.getAccountAddrProvince()), 
//				DeductUtils.isNull(customerAccount.getAccountBankId()));
//		String rule = DeductUtils.getDeductRule(temp);
		String rule = tdpool.getJumpRule();
		
		DeductBespoke tempDeductBespoke = theDayDeductDao.findDeductBespokeByDayDeductId(id);
		tempDeductBespoke.setId(IdGen.uuid());
		tempDeductBespoke.setBespokeDate(deductBespoke.getBespokeDate());
		tempDeductBespoke.setExecutionTimeSegment(deductBespoke.getExecutionTimeSegment());
		tempDeductBespoke.setDictDeductPlatformId(deductBespoke.getDictDeductPlatformId());
		tempDeductBespoke.setDictDeductRule(rule);
		tempDeductBespoke.setDayDeductId(id);
		tempDeductBespoke.setDictDeductStatus(DeductState.YYY.value);
		tempDeductBespoke.setAccountProperty("1");
		tempDeductBespoke.setCreateBy(UserUtils.getUser(UserUtils.getUserId()).getName());
		tempDeductBespoke.setCreateTime(new Date());
		tempDeductBespoke.setForeginId(id);
		// 组装对象
		DeductReq deductReq = DeductUtils.header(tempDeductBespoke);
		// check对象
		String msg = TaskService.validate(deductReq);
		if(StringUtils.isNotBlank(msg) && msg.length() >0){
			logger.debug("check失败信息:" + msg);
			messages.append(msg);
			return;
		}
		// 验证通过保存
		dao.insert(tempDeductBespoke);
		if(status.equals(DeductState.YYHK.value)){
			tdpool.setDictDeductStatus(DeductState.YYHK.value);
		}else{
			tdpool.setDictDeductStatus(DeductState.ECYYHK.value);
		}
		tdpool.preUpdate();
		theDayDeductDao.update(tdpool);
		// 全程流痕
		checkManager.addCheck(lendCode, "分天预约划扣id【"+tempDeductBespoke.getId()+"】",
				DeductState.getDeductState(status),tempDeductBespoke.getId()
				,FortuneLogNode.DEDUCT_THEDAY);
	}
	
	/**
	 * 分天预约划扣取消
	 * 2016年2月19日
	 * By 韩龙
	 * @param id
	 * @param status
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void cancelDeductBespokeTheDay(ThedayDeductPool thedayDeductPool,String status,
			StringBuffer message){
		String id = thedayDeductPool.getId();
		thedayDeductPool = theDayDeductDao.getForUpdate(thedayDeductPool);
		if(thedayDeductPool == null){
			String info = "分天划扣业务编号【"+id+"】已被其它业务人员处理";
			logger.debug(info);
			message.append(info).append("</br>");
			return;
		}
		if(status.equals(DeductState.YYHK.value)){
			thedayDeductPool.setDictDeductStatus(DeductState.DFTHK.value);
		}else{
			thedayDeductPool.setDictDeductStatus(DeductState.HKSB.value);
		}
		thedayDeductPool.preUpdate();
		theDayDeductDao.update(thedayDeductPool);
		DeductBespoke deductBespoke = new DeductBespoke();
		deductBespoke.setForeginId(thedayDeductPool.getId());
		deductBespoke.setDictDeductStatus(DeductState.QXYY.value);
		deductBespoke.preUpdate();
		dao.update(deductBespoke);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Map<String, String> lend_code = theDayDeductDao.getTheDaysById(map);
		// 全程流痕
		checkManager.addCheck(lend_code.get("lend_code"), "分天预约划扣取消id【"+id+"】",
				DeductState.getDeductState(status),id,FortuneLogNode.DEDUCT_THEDAY);
	}
	
}

