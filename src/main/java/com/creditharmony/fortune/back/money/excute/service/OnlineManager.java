package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.out.jzh.JzhTransferBuOutInfo;
import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.moneyaccount.entity.MoneyAccountInfo;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ext.ExportBean;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.sync.data.remote.FyMoneyAccountService;
import com.creditharmony.fortune.utils.AppPropertiesUtil;
import com.creditharmony.fortune.utils.SmsUtil;

/**
 * 列表相关处理Service
 * @Class Name ListManager
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@Service
public class OnlineManager extends CoreManager<BackMoneyListDao, ListItemView> {

	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private FyMoneyAccountService  fyMoneyAccountService;
	@Autowired
	private DeductReqManager deductReqManager;
	@Autowired
	private CheckManager checkManager;

	
	/**
	 * 单条金账户线上回款
	 * 2016年4月15日
	 * By 朱杰
	 * @param eb
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String jzhExcute(ExportBean eb){
		
		User user = eb.getCurrentUser();
		String userId = user.getId();
		Date date = new Date();
		
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(eb.getBackmId());
		pool.setVerTime(eb.getVerTime());
		BackMoneyPool mp = poolDao.forUpdate(pool);
		if (null==mp || BackResult.DELLING.value.equals(mp.getBackResult())) {
			BmUtils.throwDataOutOfDateException();
		}
		String result = "";
		MoneyAccountInfo moneyAccountInfo = new MoneyAccountInfo();
		BackMoneyLog log = new BackMoneyLog();
		// pool.preUpdate();
		pool.setModifyBy(userId);
		pool.setModifyTime(date);
		// log.preInsert();
		log.setId(IdGen.uuid());
		log.setCreateBy(userId);
		log.setCreateTime(date);
		log.setModifyBy(userId);
		log.setModifyTime(date);
		
		// 获取划扣金额与付款帐户 
		moneyAccountInfo.setOutCustNo(AppPropertiesUtil.getString("jzh_fk_account"));
		// 流水号
		moneyAccountInfo.setMchntTxnSsn(SmsUtil.randomString(30));
		// 收款人帐户 inCustNo
		moneyAccountInfo.setInCustNo(eb.getTrusteeshipNo());
		moneyAccountInfo.setFlag("JzhTransferBuInfo");
		// 划拨金额:实际回款金额-成功金额
		BigDecimal suceessMoney = BigDecimal.ZERO;
		if (eb.getSuceessMoney() != null) {
		suceessMoney = eb.getSuceessMoney();
		}
		BigDecimal backActualbackMoney = BigDecimal.ZERO;
		if (eb.getBackActualbackMoney() != null) {
			backActualbackMoney = eb.getBackActualbackMoney();
		}
		
		BigDecimal amt = backActualbackMoney.subtract(suceessMoney).setScale(2, RoundingMode.HALF_UP);
		// 金额
		moneyAccountInfo.setAmt(amt.multiply(new BigDecimal("100")).toBigInteger().toString());
		// 备注
		moneyAccountInfo.setRem(eb.getLendCode() + "_" + BackType.getBackType(eb.getBackMoneyType()));
		
		JzhTransferBuOutInfo jzht = (JzhTransferBuOutInfo) fyMoneyAccountService.chooseInterface(moneyAccountInfo);
		BackMoneyPool p = poolDao.getByBackmId(eb.getBackmId());
		String lendCode = "";
		if (null != p) {
			lendCode = p.getLendCode();
		}
		
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(new Date());
		log.setBackmId(eb.getBackmId());
		log.setDictBackmStatus(BackState.DHKQR.value);
		if (BmConstant.JZH_SUCCESS.equals(jzht.getRetCode())) {
			pool.setBackResult(BackResult.SUCESS.value);
			pool.setBackReason("");
			pool.setBackDay(new Date());
			pool.setSuccessMoney(backActualbackMoney);
			pool.setFailMoney(BigDecimal.ZERO);
		} else {
			pool.setBackResult(BackResult.FAIL.value);
			pool.setBackReason(jzht.getRetMsg());
			pool.setBackDay(new Date());
			pool.setSuccessMoney(suceessMoney);
			pool.setFailMoney(backActualbackMoney.subtract(suceessMoney));
			result = jzht.getRetMsg();
		}
		pool.setPlatformId(BackMoneyPlat.JZH.value);
		poolDao.update(pool);
		logDao.insert(log);
		// 全程留痕
		String operatorInfo = "金账户线上回款，金额["+amt+"元]，回盘结果[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "金账户线上回款";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode, user);
		return result;
	}

	/**
	 * 非金账户线上回款-单条
	 * 2016年4月15日
	 * By 赵红江
	 * @param view
	 * @param list
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String deductAndUpdatebackmInfo(ExportBean eb) {
		
		User user = eb.getCurrentUser();
		String userId = user.getId();
		Date date = new Date();
		
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(eb.getBackmId());
		pool.setVerTime(eb.getVerTime());
		BackMoneyPool mp = poolDao.forUpdate(pool);
		if (null==mp || BackResult.DELLING.value.equals(mp.getBackResult())) {
			BmUtils.throwDataOutOfDateException();
		}
		String message = "";
		
		if (!PayMent.ZJTG.value.equals(eb.getApplyPay())) {
			// 非资金托管
			FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
			DeResult deResult = new DeResult();
			//组装参数
			DeductReq drp = setDeductReqInfo(eb);
			// 平台金额校验
			message = DeductUtils.platformCheck(eb.getPlatformId(), drp, eb.getLendCode());
			if(StringUtils.isNotEmpty(message)){
				return message;
			}
			try {
				//保存划扣记录
				deductReqManager.addDeductReq(drp,fortuneDeductReq);
				//添加划扣任务
				deResult = TaskService.addTask(drp);
				if(!ResultType.ADD_SUCCESS.getCode().equals(deResult.getReCode())){
					//添加失败
					message = deResult.getReMge();
					deductReqManager.updateStatus(drp,fortuneDeductReq,DeductDataSendState.FSSB.value);
					return message;
				}
				
				BackMoneyLog log = new BackMoneyLog();
				String backmId = eb.getBackmId();// 回款申请Id
				// 更新回款状态
				pool.setBackmId(backmId);
				pool.setDictBackStatus(BackState.DHKQR.value);
				pool.setPlatformId(eb.getPlatformId());
				pool.setBackResult(BackResult.DELLING.value);
				pool.setLendCode(eb.getLendCode());
				pool.setBackMoneyDay(new Date());
				// pool.preUpdate();
				pool.setModifyBy(userId);
				pool.setModifyTime(date);
				poolDao.update(pool);
				// 回款留痕
				log.setBackmId(backmId);
				log.setDictBackmStatus(BackState.DHKQR.value);
				// log.preInsert();
				log.setId(IdGen.uuid());
				log.setCreateBy(userId);
				log.setCreateTime(date);
				log.setModifyBy(userId);
				log.setModifyTime(date);
				logDao.insert(log);	
				
				// 全程留痕
				StringBuffer sb = new StringBuffer().append(BackMoneyPlat.getBackMoneyPlat(eb.getPlatformId()))
						.append("线上回款，金额[").append(drp.getAmount()).append("元]，回盘结果[")
						.append(BackResult.backResultMap.get(pool.getBackResult())).append("]，回款状态更新为[")
						.append(BackState.getBackState(pool.getDictBackStatus())).append("]");
				String operatorInfo = sb.toString();
				String operatorType = "线上回款";
				String operateAffiliated = pool.getBackmId();
				FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
				checkManager.addCheck(eb.getLendCode(), operatorInfo, operatorType, operateAffiliated, fortuneLogNode,user);
				//发送划扣
				message = deductReqManager.commit(drp,fortuneDeductReq, deResult,eb.getLendCode());
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(eb.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
				forException.setImportantLevel(FortuneLogLevel.RED.value);
				forException.setRemark("线上回款(非金账户)失败");
				
				// forException.preInsert();
				forException.setId(IdGen.uuid());
				forException.setCreateBy(userId);
				forException.setCreateBy(userId);
				forException.setCreateTime(date);
				forException.setModifyBy(userId);
				forException.setModifyTime(date);
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				
				logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
				deductReqManager.updateStatus(drp, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
				message = e.getMessage();
			}
			if(StringUtils.isNotEmpty(message) ){
				logger.debug("提交划扣任务commit失败,失败信息为：" + message);
				throw new ServiceException(message);
			}
		} else {
			//资金托管，返回错误
			message = "出借编号为:" + eb.getLendCode() + "的回款请调用金帐户回款接口。<br/>";
			logger.error("请调用金帐户回款接口，出借编号为：" + eb.getLendCode()
					+ "金帐户编号为：" + eb.getTrusteeshipNo());
		}
		return message;
	}
	

	/**
	 * 组装线上回款参数
	 * 2016年2月3日
	 * By 赵红江
	 * @param eb
	 */
	private DeductReq setDeductReqInfo(ExportBean eb) {
		DeductReq drp = new DeductReq();
		drp.setBusinessId(eb.getLendCode());
		drp.setAccountName(eb.getAccountName());// 账户名称
		drp.setAccountNo(eb.getAccountNo());// 账号
		drp.setAccountType(eb.getAccountCardOrBooklet());// 卡或折
		BigDecimal suceessMoney = eb.getSuceessMoney() == null?BigDecimal.ZERO:eb.getSuceessMoney();
		BigDecimal backMoney = eb.getBackActualbackMoney() == null?BigDecimal.ZERO:eb.getBackActualbackMoney();
		BigDecimal tradeAmount = backMoney.subtract(suceessMoney).setScale(2, RoundingMode.HALF_UP);
		drp.setAmount(tradeAmount);// 金额
		drp.setBankCity(eb.getAccountAddrcity());// 城市
		drp.setBankId(eb.getAccountBank());// 银行ID
		drp.setBankName(eb.getAccountBranch());// 支行名
		drp.setBankProv(eb.getAccountAddrprovince());// 省
														// deductFlag:代收，代付
		drp.setSysId(DeductWays.CF_02.getCode());// 业务标识ID
		drp.setRequestId(eb.getLendCode());// 出借编号
		drp.setDeductFlag(DeductFlagType.PAY.getCode());// 划扣标志
														// ：0，代收；1，代付
		drp.setIdType(DeductUtils.isNull(eb
				.getDictCustomerCertType()));// 证件类型
		drp.setIdNo(DeductUtils.isNull(eb.getCustomerCertNum()));// 证件编号
		drp.setMobile(DeductUtils.isNull(eb
				.getCustomerMobilephone()));// 手机号
		drp.setRule(eb.getPlatformId() + ":" + eb.getInterfaceType());// 划扣规则
		drp.setRemarks(eb.getLendCode()+"_回款");
		if (BackMoneyPlat.KL.value.equals(eb.getPlatformId())) {
			// 卡联支付多发送两个字段
			drp.setBranchCode(eb.getBranchCode());
			drp.setBusObjType("00"); // 对私
		}
		
		return drp;
	}

	/**
	 * 线上回款结果数据更新
	 * 2016年2月3日
	 * By 赵红江
	 * @param fortuneDeductResult
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public boolean updateBackMonyResult(FortuneDeductResult fortuneDeductResult){
		logger.debug("回款结果更新..........");
		boolean result=false;
		try {
			// 出借编号
			String lendCode = DeductUtils.isNull(fortuneDeductResult.getLendCode());
			BackMoneyPool bmp = new BackMoneyPool();
			bmp.setLendCode(lendCode);
			// 回款平台
			String plateFormId = DeductUtils.isNull(fortuneDeductResult.getPlateformId());
			if (!plateFormId.isEmpty()) {
				bmp.setPlatformId(plateFormId);
			}
			// 回款成功金额
			String successMony = DeductUtils.isNull(fortuneDeductResult.getDeductSucceedMoney());
			if (!successMony.isEmpty()) {
				bmp.setSuccessMoney(new BigDecimal(successMony));
			}
			// 回款失败金额
			String failMoney = DeductUtils.isNull(fortuneDeductResult.getDeductFailMoney());
			if (!successMony.isEmpty()) {
				bmp.setFailMoney(new BigDecimal(failMoney));
			} else {
				bmp.setFailMoney(BigDecimal.ZERO);
			}
			// 回款结果
			String resultCode = DeductUtils.isNull(fortuneDeductResult.getDeductResultCode());
			// 失败原因
			String failReason = DeductUtils.isNull(fortuneDeductResult.getConfirmOpinion());
			if (ResultType.POXY_SUCCESS.getCode().equals(resultCode)) {
				bmp.setBackResult(BackResult.SUCESS.value);// 回盘结果：1：成功；2：失败
				bmp.setBackReason("");// 失败原因
			} else {
				bmp.setBackResult(BackResult.FAIL.value);// 回盘结果
				bmp.setBackReason(failReason);// 失败原因
			}
			
			BackMoneyPool p = poolDao.getByLendCode(lendCode);
			if (null==p) {
				throw new ServiceException("回款池中没有出借编号为【"+ lendCode + "】的数据");
			}
			if (bmp.getBackResult().equals(p.getBackResult())) {
				// 回盘结果与数据库结果相同，不更新数据
				return true;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstant.yyyy_mm_dd);
			String backday = DeductUtils.isNull(fortuneDeductResult.getDeductTime());
			if (StringUtils.isNotBlank(backday)) {
				// 回盘时间
				bmp.setBackDay(sdf.parse(backday));
			}
			// 回款申请表更新
			bmp.setModifyBy("MQ");
			bmp.setModifyTime(new Date());
			poolDao.updateByLendCode(bmp);
			// 全程留痕
			StringBuffer sb = new StringBuffer().append(BackMoneyPlat.getBackMoneyPlat(fortuneDeductResult.getPlateformId()))
					.append("线上回款结果数据更新，回盘结果[")
					.append(BackResult.backResultMap.get(bmp.getBackResult())).append("]");
			String operatorInfo = sb.toString();
			String operatorType = "线上回款结果更新";
			String operateAffiliated = bmp.getBackmId();
			FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_CONFIRM;
			checkManager.addCheckBatch(fortuneDeductResult.getLendCode(), operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
			result = true;
		} catch (Exception e) {
			FortuneException forException = new FortuneException();
			forException.setLoanCode(fortuneDeductResult.getLendCode());
			forException.setMessage(e.getMessage());
			forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
			forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
			forException.setImportantLevel(FortuneLogLevel.RED.value);
			forException.setRemark("线上回款(非金账户)MQ结果更新失败");
			
//			forException.preInsert();
			forException.setId(IdGen.uuid());
			forException.setCreateBy("MQ");
			forException.setCreateTime(new Date());
			forException.setModifyBy("MQ");
			forException.setModifyTime(new Date());
			FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
			forDao.insert(forException);
			
			logger.error(
					"更新回款结果出错出借编号为：" + fortuneDeductResult.getLendCode(), e);
			return result;
		}
		return result;
	}
	
}
