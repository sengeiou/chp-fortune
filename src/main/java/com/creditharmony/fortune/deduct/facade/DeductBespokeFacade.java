package com.creditharmony.fortune.deduct.facade;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.dao.DeductBespokeDao;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.service.DeductBespokeManager;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;
import com.google.common.collect.Lists;

/**
 * 预约划扣
 * @Class Name DeductBespokeManager
 * @author 韩龙
 * @Create In 2016年2月1日
 */
@Service
public class DeductBespokeFacade extends CoreManager<DeductBespokeDao, DeductBespoke>{
	
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
	private DeductBespokeManager deductBespokeManager;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	/**
	 * 单天划扣--->保存预约划扣 2016年2月18日 By 韩龙
	 * 
	 * @param deductBespoke
	 * @return
	 */
	public List<String> saveDeductBespokeFacade(final DeductBespoke deductBespoke, final String status) {
		List<String> message = Lists.newArrayList();
		String[] idArray = deductBespoke.getDayDeductId().split(",");
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (final String lendCode : idArray) {
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					StringBuffer info = new StringBuffer();
					try {
						deductBespokeManager.saveDeductBespoke(deductBespoke, status, lendCode, info);
					} catch (Exception e) {
						info.append("业务编号【"+lendCode+"】保存预约失败");
						logger.error("保存预约划扣失败:"+e.getMessage(),e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
								FortuneLogLevel.ORANGE.value, e.getMessage()));
					}
					return info.toString();
				}
			});
		}
		
		// 组装返回结果
		for(int i =0; i< idArray.length; i++){
			try{
				Future<String> future = completionService.take();
				if(future.get()!=null && !"".equals(future.get())){
					message.add(future.get());
				}
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		return message;
	}
	
	/**
	 * 分天保存预约划扣
	 * 2016年2月16日
	 * By 韩龙
	 * @param id
	 */
	public List<String> saveDeductBespokeTheDayFacade(final DeductBespoke deductBespoke,final String status){
		List<String> messages = Lists.newArrayList();
		String[] idArray = deductBespoke.getDayDeductId().split(",");
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (final String theDayId : idArray) {
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					StringBuffer info = new StringBuffer();
					try {
						deductBespokeManager.saveDeductBespokeTheDay(deductBespoke, status, theDayId, info);
					} catch (Exception e) {
						info.append("业务编号【"+theDayId+"】保存预约划扣失败:"+e.getMessage());
						logger.error("保存分天划扣预约失败："+e.getMessage(), e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, theDayId,
								FortuneLogNode.DEDUCT_SUCCESS.getCode(), FortuneLogLevel.RED.value, 
								"业务编号【"+theDayId+"】保存预约划扣失败"));
					}
					return info.toString();
				}
				
			});
			
		}
		
		// 组装返回结果
		for(int i =0; i< idArray.length; i++){
			try{
				Future<String> future = completionService.take();
				if(future.get()!=null && !"".equals(future.get())){
					messages.add(future.get());
				}
				
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		return messages;
	}
}

