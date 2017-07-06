package com.creditharmony.fortune.deduct.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.excute.service.ExcuteBackInterestManager;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.excute.service.OnlineManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.DeductUtils.ThedayJump;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.dao.DeductBespokeDao;
import com.creditharmony.fortune.deduct.dao.DeductResultDao;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.DeductResult;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductResultMQManager extends CoreManager<DeductApplyDao, DeductPool> {

	// 划扣业务Manager
	@Autowired
	private DeductManager deductManager;
	// 划扣业务Manager
	@Autowired
	private DeductApplyDao deductApplyDao;
	// 分天划扣业务Manager
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	// 出借
	@Autowired
	private LoanApplyDao loanApplyDao;
	// 回款
	@Autowired
	private OnlineManager listManager;
	// 回息
	@Autowired
	private ExcuteBackInterestManager excuteBackInterestManager;
	// 回款
	@Autowired
	private BackMoneyPoolDao backMoneyPoolDao;
	// 短信
	@Autowired
	private SmsDao smsDao;
	@Autowired
	private CreditorAidManager creditorAidManager;
	// 
	@Autowired
	private DeductResultDao deductResultDao;
	// 待推荐债权dao
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	// 合同
	@Autowired
	private ContractManager contractManager;
	// 分天划扣dao
	@Autowired
	private TheDayDeductDao theDayDeductDao;
	// 规则
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	// 预约
	@Autowired
	private DeductBespokeDao deductBespokeDao;
	// 流痕
	@Autowired
	private CheckManager checkManager;
	
	@Transactional(readOnly = false ,value="fortuneTransactionManager" )
	public void executeBatch(FortuneDeductResult fortuneDeductResult,StringBuffer flag){
		// 系统功能ID
		String deductSysIdType = fortuneDeductResult.getDeductSysIdType();
		// 财富回款
		if(DeductWays.CF_02.getCode().equals(deductSysIdType)){
		   if(!listManager.updateBackMonyResult(fortuneDeductResult)){
//			  list.add(fortuneDeductResult);
			  updateDeductResult(fortuneDeductResult);
		   }else{
			  deleteDeductResult(fortuneDeductResult);
		   }
		}else if(DeductWays.CF_03.getCode().equals(deductSysIdType)){
		   if(!excuteBackInterestManager.updateBackInterestResult(fortuneDeductResult)){//财富回息
//			  list.add(fortuneDeductResult);
			 updateDeductResult(fortuneDeductResult);
		   }else{
			 deleteDeductResult(fortuneDeductResult);
		   }
		}else  if(DeductWays.CF_01.getCode().equals(deductSysIdType)){
			logger.debug("财富划扣-------->开始");
		   if(!header(fortuneDeductResult,flag)){//财富划扣
			  logger.debug("财富划扣失败");
			  flag.append("false");
			  updateDeductResult(fortuneDeductResult);
		   }else{
			   deleteDeductResult(fortuneDeductResult);
		   }
		   logger.debug("财富划扣-------->结束");
		}
	}
	
	/**
	 * 处理成功时，删除推送结果
	 * 2016年4月1日
	 * By 陈广鹏
	 * @param fortuneDeductResult
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void deleteDeductResult(FortuneDeductResult fortuneDeductResult) {
		DeductResult deductResult = new DeductResult();
		   deductResult.setRequestId(fortuneDeductResult.getRequestId());
		   deductResultDao.delete(deductResult);
	}
	
	/**
	 * 处理成功时，更新推送结果
	 * 2016年4月1日
	 * By 陈广鹏
	 * @param fortuneDeductResult
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void updateDeductResult(FortuneDeductResult fortuneDeductResult) {
		DeductResult deductResult = new DeductResult();
		deductResult.setRequestId(fortuneDeductResult.getRequestId());
		deductResult.setFailFlag(YoN.SHI.value);
		deductResultDao.update(deductResult);
	}
	
	/**
	 * 处理划扣结果
	 * 2016年1月31日
	 * By 韩龙
	 * @param fortuneDeductResult
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean header(FortuneDeductResult fortuneDeductResult,StringBuffer flag) {
		boolean result = false;
//		Exception exception = null;
		String lendCode = "";
		// 如果分天划扣id空为返回结果是单天划扣则更新划扣申请表
		if (fortuneDeductResult.getTheDayId() == null) {
			try {
				lendCode = DeductUtils.isNull(fortuneDeductResult.getLendCode());
				logger.debug("执行划扣结果更新，业务编号【" + lendCode + "】---------->开始");
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				dp = deductManager.get(dp);
				// 划扣之前是否业务人员手动处理结果，如果手动处理就按手动结果，返之则按自动结果
				if(dp.getDictDeductStatus().equals(DeductState.HKCG.value)){
					return result;
				}
				dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNull(fortuneDeductResult
						.getDeductSucceedMoney())).add(new BigDecimal(dp.getActualDeductMoney())).toString());
				dp.setFailMoney(DeductUtils.isNull(fortuneDeductResult.getDeductFailMoney()));
				dp.setDealTime(DeductUtils.isNull(fortuneDeductResult.getDeductTime()));
				dp.setNoDeductMoney(fortuneDeductResult.getUnDeductMoney());
				// 失败原因
				String fileReason = DeductUtils.isNull(fortuneDeductResult
						.getConfirmOpinion());
				if (ResultType.POXY_SUCCESS.getCode().equals(fortuneDeductResult.getDeductResultCode())) {
					dp.setFailReason(fileReason);// 回盘结果：1：成功；2：失败
					dp.setDictDeductStatus(DeductState.HKCG.value);
					logger.debug("执行划扣结果为："+DeductState.getDeductState(DeductState.HKCG.value));
					// 出借申请表更新  插入回款表
					result = deductUpdateStatus(fortuneDeductResult);
					// 三网检验首单  发送短信  制作文件信息 标识
					flag.append("true");
				} else {
					dp.setFailReason(fileReason);// 回盘结果
					dp.setDictDeductStatus(DeductState.HKSB.value);
					logger.debug("执行划扣结果为："+DeductState.getDeductState(DeductState.HKSB.value));
				}
				logger.debug("执行划扣结果为："+DeductState.getDeductState(DeductState.HKSB.value)+"状态---开始");
//				dp.preUpdate();
				dp.setModifyBy("MQ");
				dp.setModifyTime(new Date());
				deductApplyDao.update(dp);
				String content = DeductState.getDeductState(dp
						.getDictDeductStatus());
				// 全程流痕
				checkManager.addCheckBatch(dp.getApplyCode(), "状态修改" + content, content,
						dp.getApplyCode(),FortuneLogNode.DEDUCT_BALANCE);
				result = true;
				logger.debug("执行划扣结果为："+DeductState.getDeductState(DeductState.HKSB.value)+"状态---结束");
			} catch (Exception e) {
				result = false;
				logger.error(
						"更新划扣结果出错出借编号为：" + fortuneDeductResult.getLendCode(), e);
//				exception = e;
				throw new ServiceException("更新划扣结果失败");
			}/*finally{
				if(!result){
					FortuneExceptionUtil.insertExceptionBatch(FortuneExceptionUtil.newException(exception, lendCode, 
							FortuneLogNode.DEDUCT_BALANCE.getCode(),
							FortuneLogLevel.YELLOW.value, exception.getMessage()));
				}
				logger.debug("执行划扣结果更新---------->结束");
				return result;
			}*/
			logger.debug("执行划扣结果更新，业务编号【" + lendCode + "】---------->结束");
			return result;
		}else{
			try {
				// 分天划扣id不为空则更新分天划扣表
				ThedayDeductPool tdp = new ThedayDeductPool();
				String theDayId = fortuneDeductResult.getTheDayId();
				logger.debug("执行分天划扣划扣结果更新,业务编号【"+theDayId+"】---------->开始");
				tdp.setId(theDayId);
				tdp = theDayDeductManager.get(tdp);
				if(tdp == null){
					this.logger.debug("执行分天划扣划扣结果更新没有找到相应的记录");
					result = true;
					return result;
				}
//				// 划扣之前是否业务人员手动处理结果，如果手动处理就按手动结果，返之则按自动结果
//				if(tdp.getDictDeductStatus().equals(DeductState.HKCG.value)){
//					return result;
//				}
				tdp.setDeductSucceedMoney(new BigDecimal(DeductUtils.isNullMoney(fortuneDeductResult
						.getDeductSucceedMoney())).add(DeductUtils.isNullBigDecimal(tdp.getDeductSucceedMoney())));
				tdp.setDeductFailMoney(new BigDecimal(DeductUtils.isNullMoney(fortuneDeductResult
						.getDeductFailMoney())));
				SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstant.yyyy_mm_dd);
				tdp.setDeductTime(sdf.parse(fortuneDeductResult.getDeductTime()));
				tdp.setNoDeductMoney(DeductUtils.isNullMoney(fortuneDeductResult.getUnDeductMoney()));
				String fileReason = DeductUtils.isNull(fortuneDeductResult.getConfirmOpinion());
				tdp.setFailReason(fileReason);// 回盘结果：1：成功；2：失败
				// 累计跳转失败金额
				tdp.setAllDeductFailMoney(new BigDecimal(DeductUtils.
						isNullMoney(fortuneDeductResult.getAllDeductFailMoney())));
				
				if (ResultType.POXY_SUCCESS.getCode().equals(fortuneDeductResult.getDeductResultCode())) {
					tdp.setDictDeductStatus(DeductState.HKCG.value);
					tdp.setModifyTime(new Date());
					tdp.setModifyBy("MQ");
					result = true;
					theDayDeductDao.update(tdp);
				} else {
					// 根据分天划扣id获取平台限额信息
					Map<String,Object> mapResult = new HashMap<String,Object>();
					mapResult.put("theDayId", theDayId);
					BaseExportModel baseExportModel = theDayDeductDao.getBaseExportModel(mapResult);

					LoanApply loanApply = new LoanApply();
					loanApply.setApplyCode(baseExportModel.getLendCode());
					loanApply = loanApplyDao.get(loanApply);
					// 获取
					PlatformBankEntity platformBankEntity = platformGotoRuleManager.getPlatformBankEntity(DeductUtils
							.isNull(loanApply.getDeductTypeId()), DeductUtils
							.isNull(baseExportModel.getAccountAddrProvince()),
							DeductUtils.isNull(baseExportModel.getAccountBank()));
					// 出借金额
					BigDecimal lendMoney = loanApply.getLendMoney();
					// 分天划扣金额
					BigDecimal actualDeductMoney = tdp.getActualDeductMoney();
					// 富有单日限额
					Long dayLimitMoney = platformBankEntity.getDayLimitMoney()/100;
					// 分天划扣天数
					int theDayCount = loanApply.getApplyDeductDays();
					// 总限额
					BigDecimal sumMoney = actualDeductMoney.multiply(new BigDecimal(theDayCount));
					// 跳转中金平台总限额(最大划扣金额)
					BigDecimal zjMoney = sumMoney.subtract(new BigDecimal(dayLimitMoney*theDayCount));
					// 剩余未划金额
					BigDecimal residualGold = lendMoney.subtract(new BigDecimal(DeductUtils.
							isNullMoney(fortuneDeductResult.getDeductSucceedMoney())));
					// 单天总限额
					BigDecimal theDayZjMoney = zjMoney.divide(new BigDecimal(theDayCount));
					
					// 获取当笔出借所有分天划扣记录
					ThedayDeductPool tdpl = new ThedayDeductPool();
					tdpl.setDeductApplyId(tdp.getDeductApplyId());
					List<ThedayDeductPool> theList = theDayDeductManager.findAllList(tdpl);
					// 如果富有平台未签约
					if("100011".equals(fortuneDeductResult.getDeductResultCode()) 
							|| "100012".equals(fortuneDeductResult.getDeductResultCode())
							|| "100013".equals(fortuneDeductResult.getDeductResultCode())
							|| "100014".equals(fortuneDeductResult.getDeductResultCode())
							|| "9990".equals(fortuneDeductResult.getDeductResultCode())){
						/*剩余未划金额<=最大划扣金额总和时，再划扣当天中金的限额，
						 划扣成功后，将剩余金额在第二天和第三天最大限额划扣完*/
						// 最大划扣金额大于剩余金额则重新计算
						if(zjMoney.compareTo(residualGold) == 1){
							// 重新计算更新分天划扣金额
							for (int i = 0;i<theList.size();i++) {
								ThedayDeductPool thedayDeductPool = theList.get(i);
								// 跳转规则
								String rule = tdp.getJumpRule();
								if(residualGold.compareTo(theDayZjMoney) == 1){
									if(thedayDeductPool.getId().equals(tdp.getId())){
										// 当日跳转金额
										tdp.setJumpAmount(tdp.getActualDeductMoney().subtract(theDayZjMoney));
										// 分天划扣金额
										tdp.setActualDeductMoney(theDayZjMoney);
										// 更新规则
										tdp.setJumpRule(rule.substring(rule.indexOf(",")+1));
										// 设置当日是否跳转，是
										tdp.setThedayJumpFlag(ThedayJump.YES.value);
										tdp.setDictDeductStatus(DeductState.HKSB.value);
										// 保存
//										tdp.preUpdate();
										tdp.setModifyBy("MQ");
										tdp.setModifyTime(new Date());
										theDayDeductDao.update(tdp);
									}else{
										// 重新计算并保存
										recalculate(tdp, theDayZjMoney,
												thedayDeductPool, rule);
									}
									residualGold = residualGold.subtract(theDayZjMoney);
								}else{
									// 重新计算并保存
									recalculate(tdp, residualGold,
											thedayDeductPool, rule);
								}
							}
						}else{
							/*剩余未划金额与最大划扣金额总和进行比较,
							     剩余未划金额>最大划扣金额总和时,第二天及第三天也自动变为划扣失败
							   （若第二天第三天已进行了预约则取消预约变为划扣失败）,跳转金额为0；*/
							for (ThedayDeductPool thedayDeductPool : theList) {
//								String rule = tdp.getJumpRule();
								if(thedayDeductPool.getId().equals(tdp.getId())){
									// 当日跳转金额
//									tdp.setJumpAmount(tdp.getActualDeductMoney().subtract(theDayZjMoney));
									// 分天划扣金额
//									tdp.setActualDeductMoney(theDayZjMoney);
//									// 更新规则
//									tdp.setJumpRule(rule.substring(rule.indexOf(",")+1));
									// 设置当日是否跳转，是
//									tdp.setThedayJumpFlag(ThedayJump.YES.value);
									tdp.setDictDeductStatus(DeductState.HKSB.value);
									tdp.setFailReason(fileReason);// 回盘结果
									tdp.setJumpAmount(BigDecimal.ZERO);
									theDayDeductDao.update(tdp);
								}else{
									thedayDeductPool.setDictDeductStatus(DeductState.HKSB.value);
									thedayDeductPool.setDeductFailMoney(thedayDeductPool.getActualDeductMoney());
									thedayDeductPool.setNoDeductMoney(thedayDeductPool.getActualDeductMoney() + "");
//									thedayDeductPool.preUpdate();
									thedayDeductPool.setModifyBy("MQ");
									thedayDeductPool.setModifyTime(new Date());
									theDayDeductDao.update(thedayDeductPool);
									// 判断状态是否是预约中或者二次预约
									if(thedayDeductPool.getDictDeductStatus().equals(DeductState.YYHK.value) 
											|| thedayDeductPool.getDictDeductStatus().equals(DeductState.ECYYHK.value)){
										DeductBespoke dbk = new DeductBespoke();
										dbk.setDayDeductId(tdp.getId());
										dbk.setDictDeductStatus(DeductState.YYY.value);
										dbk = deductBespokeDao.get(dbk);
										if(dbk !=null){
											dbk.setDictDeductStatus(DeductState.QXYY.value);
//											dbk.preUpdate();
											dbk.setModifyBy("MQ");
											dbk.setModifyTime(new Date());
											deductBespokeDao.update(dbk);
										}
									}
								}
							}
						}
						result = true;
						// 余额不足
					}else if("3008".equals(fortuneDeductResult.getDeductResultCode())
							|| "1015".equals(fortuneDeductResult.getDeductResultCode())
							|| "100017".equals(fortuneDeductResult.getDeductResultCode())){
						tdp.setFailReason(fileReason);// 回盘结果
						tdp.setDictDeductStatus(DeductState.HKSB.value);
						// 保存
//						tdp.preUpdate();
						tdp.setModifyBy("MQ");
						tdp.setModifyTime(new Date());
						theDayDeductDao.update(tdp);
						result = true;
					}else{
						// 获取
						String temp = platformGotoRuleManager.getDeductRule(DeductUtils
								.isNull(loanApply.getDeductTypeId()), DeductUtils
								.isNull(baseExportModel.getAccountAddrProvince()),
								DeductUtils.isNull(baseExportModel.getAccountBank()));
						
						ThedayDeductPool thedayDeductPool  = new ThedayDeductPool();
						thedayDeductPool.setDeductApplyId(tdp.getDeductApplyId());
						thedayDeductPool = theDayDeductDao.getLastThedayDeductPool(thedayDeductPool);
						// 累加金额
						BigDecimal actualMoney = thedayDeductPool.getActualDeductMoney()
							.add(new BigDecimal(DeductUtils.isNullMoney(fortuneDeductResult.getDeductFailMoney())));
						// 平台总单日限额
						BigDecimal money = DeductUtils.getMoney(temp).divide(new BigDecimal(100));
						tdp.setFailReason(fileReason);// 回盘结果
						// 判断如果小于单日限额则把失败金额累加到分天最后一天
						if(actualMoney.compareTo(money) == -1){
							tdp.setDictDeductStatus(DeductState.HKCG.value);
							// 设置跳转平台金额
							tdp.setJumpAmount(new BigDecimal(DeductUtils.
									isNullMoney(fortuneDeductResult.getDeductFailMoney())));
							// 设置当日是否跳转，是
							tdp.setThedayJumpFlag(ThedayJump.YES.value);
							// 当天分天划扣状态
//							tdp.preUpdate();
							tdp.setModifyBy("MQ");
							tdp.setModifyTime(new Date());
							theDayDeductDao.update(tdp);
							// 设置分天划扣最后一天的划扣金额
							thedayDeductPool.setActualDeductMoney(actualMoney);
//							thedayDeductPool.preUpdate();
							thedayDeductPool.setModifyBy("MQ");
							thedayDeductPool.setModifyTime(new Date());
							theDayDeductDao.update(thedayDeductPool);
						}else{
							for (ThedayDeductPool thedayDeductPool2 : theList) {
								if(thedayDeductPool2.getId().equals(tdp.getId())){
									tdp.setDictDeductStatus(DeductState.HKSB.value);
									tdp.setModifyBy("MQ");
									tdp.setModifyTime(new Date());
//									tdp.preUpdate();
									theDayDeductDao.update(tdp);
								}else if(!thedayDeductPool2.getDictDeductStatus().
										equals(DeductState.HKCG.value)){
									thedayDeductPool2.setNoDeductMoney(thedayDeductPool2.getActualDeductMoney() + "");
									thedayDeductPool2.setDeductFailMoney(thedayDeductPool2.getActualDeductMoney());
									thedayDeductPool2.setDictDeductStatus(DeductState.HKSB.value);
//									thedayDeductPool2.preUpdate();
									thedayDeductPool2.setModifyBy("MQ");
									thedayDeductPool2.setModifyTime(new Date());
									theDayDeductDao.update(thedayDeductPool2);
									// 判断状态是否是预约中或者二次预约,则取消预约划扣
									if(thedayDeductPool2.getDictDeductStatus().equals(DeductState.YYHK.value) 
											|| thedayDeductPool2.getDictDeductStatus().equals(DeductState.ECYYHK.value)){
										DeductBespoke dbk = new DeductBespoke();
										dbk.setDayDeductId(tdp.getId());
										dbk.setDictDeductStatus(DeductState.YYY.value);
										dbk = deductBespokeDao.get(dbk);
										if(dbk !=null){
											dbk.setDictDeductStatus(DeductState.QXYY.value);
//											dbk.preUpdate();
											dbk.setModifyBy("MQ");
											dbk.setModifyTime(new Date());
											deductBespokeDao.update(dbk);
										}
									}
								}else{
									// 成功不做处理
								}
							}
							result = true;
						}
					}
				}
				
				// 判断是否成功
				tdp.setVerTime(null);
				ThedayDeductPool thedayDeductPool = theDayDeductManager.get(tdp);
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
					List<ThedayDeductPool> list = theDayDeductManager.findAllList(tdpl);
					// 判断是否分天划完成
					if(list != null && list.size() > 0){
						return true;
					}
					tdpl.setDictDeductStatusList(null);
					List<ThedayDeductPool> countList = theDayDeductManager.findAllList(tdpl);
					DeductPool deductPool = new DeductPool();
					deductPool.setDeductApplyId(thedayDeductPool.getDeductApplyId());
					deductPool = deductManager.get(deductPool);
					// 失败金额
					BigDecimal failMoney = new BigDecimal("0");
					// 成功能金额
					BigDecimal succeedMoney = new BigDecimal("0");
					// 未划金额
					BigDecimal noDeductMoney = new BigDecimal("0");
					// 统计成功或失败金额
					for (ThedayDeductPool thedayPool : countList) {
						failMoney = failMoney.add(thedayPool.getDeductFailMoney());
						succeedMoney = succeedMoney.add(thedayPool.getDeductSucceedMoney());
						noDeductMoney = noDeductMoney.add(new BigDecimal(thedayPool.getNoDeductMoney()));
					}
					deductPool.setFailMoney(failMoney.toString());
					deductPool.setActualDeductMoney(succeedMoney.toString());
					deductPool.setFailReason(fortuneDeductResult.getConfirmOpinion());
					deductPool.setDealTime(fortuneDeductResult.getDeductTime());
					// 更新出借申请表
					lendCode = deductPool.getApplyCode();
					LoanApply loanApply = new LoanApply();
					loanApply.setApplyCode(lendCode);
					loanApply = loanApplyDao.get(loanApply);
					loanApply.setDeductMoney(succeedMoney);
					loanApply.setRealDeductTime(new Date());
					// 判断划扣成功失败
					if(succeedMoney.compareTo(new BigDecimal("0"))== 0 
							|| succeedMoney.compareTo(new BigDecimal("0"))== 0){
						loanApply.setLendStatus(LendState.HKSB.value);
						deductPool.setDictDeductStatus(DeductState.HKSB.value);
					}else{
						loanApply.setStatus(ForApplyStatus.CJZ.value);
						loanApply.setLendStatus(LendState.HKCG.value);
						deductPool.setDictDeductStatus(DeductState.HKCG.value);
					}
//					loanApply.preUpdate();
					loanApply.setModifyBy("MQ");
					loanApply.setModifyTime(new Date());
					loanApplyDao.update(loanApply);
					deductPool.setModifyBy("MQ");
					deductPool.setModifyTime(new Date());
//					deductPool.preUpdate();
					deductApplyDao.update(deductPool);
					String content = DeductState.getDeductState(deductPool
							.getDictDeductStatus());
					// 全程流痕
					checkManager.addCheckBatch(deductPool.getApplyCode(), "状态修改" + content, content,
							deductPool.getApplyCode(),FortuneLogNode.DEDUCT_THEDAY);
					if(DeductState.HKCG.value.equals(deductPool.getDictDeductStatus())){
						logger.debug("划扣插入回款池数据,出借编号【"+deductPool.getApplyCode()+"】---------->开始");
						// 成功回款金额
						BigDecimal backMoney = getBackMoney(deductPool.getApplyCode());
						// 插入回款表
						BackMoneyPool backMoneyPool = new BackMoneyPool();
//						backMoneyPool.preInsert();
						backMoneyPool.setCreateBy("MQ");
						backMoneyPool.setCreateTime(new Date());
						backMoneyPool.setModifyBy("MQ");
						backMoneyPool.setModifyTime(new Date());
						backMoneyPool.setBackmId(IdGen.uuid());
						backMoneyPool.setLendCode(lendCode);
						backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
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
						// 合同使用时间
						Contract contract = new Contract();
						contract.setLendCode(lendCode);
						contract.setContUseDay(new Date());
//						contract.preUpdate();
						contract.setModifyBy("MQ");
						contract.setModifyTime(new Date());
						contractManager.updateContractUseDay(contract);
						logger.debug("划扣插入回款池数据,出借编号【"+deductPool.getApplyCode()+"】---------->结束");
						// 三网检验首单  发送短信  制作文件信息 标识
						flag.append("true");
					}
					result = true;
				}
			} catch (Exception e) {
				result = false;
				logger.error("更新划扣结果分天划扣id为：" + fortuneDeductResult.getTheDayId(),e);
//				exception = e;
				throw new ServiceException("更新划扣结果失败,业务编号【"+fortuneDeductResult.getTheDayId()+"】");
			}/*finally{
				if(!result){
					FortuneExceptionUtil.insertExceptionBatch(FortuneExceptionUtil.newException(exception, lendCode, 
							FortuneLogNode.DEDUCT_THEDAY.getCode(),
							FortuneLogLevel.YELLOW.value, exception.getMessage()));
				}
				logger.debug("执行分天划扣划扣结果更新---------->结束");
				return result;
			}*/
			logger.debug("执行分天划扣划扣结果更新,业务编号【"+fortuneDeductResult.getTheDayId()+"】---------->结束");
			return result;
		}
		
	}
	
	/**
	 * 重新计算并保存
	 * 2016年6月4日
	 * By 韩龙
	 * @param tdp
	 * @param theDayZjMoney
	 * @param thedayDeductPool
	 * @param rule
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void recalculate(ThedayDeductPool tdp, BigDecimal theDayZjMoney,
			ThedayDeductPool thedayDeductPool, String rule) {
		// 当日跳转金额
		thedayDeductPool.setJumpAmount(tdp.getActualDeductMoney().subtract(theDayZjMoney));
		// 分天划扣金额
		thedayDeductPool.setActualDeductMoney(theDayZjMoney);
		// TODO 是否跳转标识与累计划扣
		thedayDeductPool.setJumpRule(rule.substring(rule.indexOf(",")+1));
		// TODO 是否跳转标识与累计划扣
		
		// 更新
//		thedayDeductPool.preUpdate();
		thedayDeductPool.setModifyBy("MQ");
		thedayDeductPool.setModifyTime(new Date());
		theDayDeductDao.update(thedayDeductPool);
		
		// 判断状态是否是预约中或者二次预约
		if(thedayDeductPool.getDictDeductStatus().equals(DeductState.YYHK.value) 
				|| thedayDeductPool.getDictDeductStatus().equals(DeductState.ECYYHK.value)){
			DeductBespoke dbk = new DeductBespoke();
			dbk.setDayDeductId(tdp.getId());
			dbk.setDictDeductStatus(DeductState.YYY.value);
			dbk = deductBespokeDao.get(dbk);
			if(dbk !=null){
				dbk.setBespokeDeductMoney(theDayZjMoney);
				dbk.setDictDeductRule(rule.substring(rule.indexOf(",")+1));
//				dbk.preUpdate();
				dbk.setModifyBy("MQ");
				dbk.setModifyTime(new Date());
				deductBespokeDao.update(dbk);
			}
		}
	}

	/**
	 * 计算回款金额
	 * 2016年4月15日
	 * By 韩龙
	 * @param deductPool
	 * @return
	 */
	private BigDecimal getBackMoney(String lendCode) {
		BigDecimal backMoney = BigDecimal.ZERO;
		// 计算回款金额
		 MatchingCreditorEx matchingCreditorEx = new
		 MatchingCreditorEx();
		 matchingCreditorEx.setLendCode(lendCode);
		 matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		 matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
		 if(matchingCreditorEx != null){
			 backMoney = creditorAidManager.getBackMoneyCommon(matchingCreditorEx.getMatchingId());
		 }else{
			 this.logger.error("待推荐匹配表没有检索到相应的数据,业务编号【"+lendCode+"】");
		 }
		return backMoney;
	}
	
	/**
	 * 出借申请表更新  插入回款表
	 * 2016年2月2日
	 * By 韩龙
	 * @param fortuneDeductResult
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean deductUpdateStatus(FortuneDeductResult fortuneDeductResult){
		boolean result = false;
		// 更新出借申请表  
		String lendCode = DeductUtils.isNull(fortuneDeductResult.getLendCode());
		String successAmount = DeductUtils.isNull(fortuneDeductResult.getDeductSucceedMoney());
		BigDecimal bd = new BigDecimal(StringUtils.isBlank(successAmount) ? "0" : successAmount);
		BigDecimal backMoney = getBackMoney(lendCode);
		LoanApply loanApply = new LoanApply();
		loanApply.setApplyCode(lendCode);
		loanApply = loanApplyDao.get(loanApply);
		loanApply.setDeductMoney(bd);
		loanApply.setLendStatus(LendState.HKCG.value);
		loanApply.setStatus(ForApplyStatus.CJZ.value);
		// 判断划扣成功失败
		if(bd.compareTo(new BigDecimal("0"))== 0){
			loanApply.setLendStatus(LendState.HKSB.value);
		}
		loanApply.setModifyBy("MQ");
		loanApply.setModifyTime(new Date());
		loanApplyDao.update(loanApply);
		// 插入回款表
		BackMoneyPool backMoneyPool = new BackMoneyPool();
		backMoneyPool.setBackmId(IdGen.uuid());
		backMoneyPool.setCreateBy("MQ");
		backMoneyPool.setCreateTime(new Date());
		backMoneyPool.setModifyBy("MQ");
		backMoneyPool.setModifyTime(new Date());
//		backMoneyPool.setBackmId(backMoneyPool.getId());
		backMoneyPool.setLendCode(lendCode);
		backMoneyPool.setFinalLinitDate(loanApply.getExpireDate());
		backMoneyPool.setBackActualbackMoney(backMoney);
		backMoneyPool.setBackMoney(backMoney);
		backMoneyPool.setBackMoneyType(BackType.DQHK.value);
		backMoneyPool.setDictBackStatus(BackState.DHKSQ.value);
		backMoneyPool.setRebackFlag(YoN.FOU.value);
		backMoneyPool.setDictFortunechannelflag(FortuneChannelFlag.XX.value);
		// 回款日期是出借到期日后一天
		if(loanApply.getExpireDate() != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(loanApply.getExpireDate());
			calendar.add(Calendar.DATE, +1);
			backMoneyPool.setBackMoneyDay(calendar.getTime());
		}else{
			this.logger.error("产品到期日为空,业务编号【"+lendCode+"】");
		}
		
		backMoneyPoolDao.insert(backMoneyPool);
		result = true;
		logger.debug("向回款表里面插入数据,业务编号【"+lendCode+"】---------->结束");
		return result;
	}
	
	/**
	 * 保存回来结果
	 * 2016年4月1日
	 * By 韩龙
	 * @param fortuneDeductResultList
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void saveFortuneDeductResult(FortuneDeductResult fortuneDeductResult){
		if(fortuneDeductResult == null){
			return;
		}
		
		logger.debug("处理业务：" + fortuneDeductResult.getLendCode());
		logger.debug("保存MQ返回的结果信息------开始");
		// 保存结果
		fortuneDeductResult.setRequestId(IdGen.uuid());
		DeductResult deductResult = new DeductResult();
//			    deductResult.preInsert();
		deductResult.setCreateBy("MQ");
		deductResult.setId(IdGen.uuid());
		deductResult.setCreateTime(new Date());
		deductResult.setModifyBy("MQ");
		deductResult.setModifyTime(new Date());
		BeanUtils.copyProperties(fortuneDeductResult, deductResult);
		deductResult.setThedayId(fortuneDeductResult.getTheDayId());
		deductResult.setFailFlag(YoN.FOU.value);
		deductResultDao.insert(deductResult);
		logger.debug("保存MQ返回的结果信息------结束");
	}
}
