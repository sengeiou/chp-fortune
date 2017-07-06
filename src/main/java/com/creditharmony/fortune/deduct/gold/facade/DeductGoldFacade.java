package com.creditharmony.fortune.deduct.gold.facade;

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
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.gold.service.DeductGoldManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductGoldFacade extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private DeductGoldManager deductGoldManager;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);

	/**
	 * 划拨 2016年2月23日 By 韩龙
	 * 
	 * @param deductPoolExt
	 * @return
	 */
	public String transferFacade(DeductPoolEx deductPoolExt, final CERequestContext ce) {
		StringBuffer message = new StringBuffer();
		List<String> list = deductPoolExt.getApplyCodes();
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		// strCode包含lendCode与verTime
		for (final String strCode : list) {
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					StringBuffer info = new StringBuffer();
					String[] strArray = strCode.split("_");
					String lendCode = strArray[0];
					String verTime = strArray[1];
					DeductPool dp = new DeductPool();
					dp.setApplyCode(lendCode);
					dp.setVerTime(verTime);
					try {
						int result = deductGoldManager.transfer(ce, dp, info);
						if(result == 999){
							LoanApply loanApply = new LoanApply();
							loanApply.setApplyCode(lendCode);
							loanApply = loanApplyManager.get(loanApply);
						/*	// 发送短信 资金托管不发短信
							commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);*/
							// 制作文件
							commonFacade.makeFileInfo(lendCode);
							// 三网首单校
							sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(), loanApply.getApplyCode());
						}
					} catch (Exception e) {
						info.append("业务编号【"+strCode+"】划拨失败").append("</br>");
						logger.error("业务编号【"+strCode+"】划拨失败,失败信息为："+e.getMessage(), e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.GOLD_ACCOUNT_DEDUCT.getCode(),
								FortuneLogLevel.RED.value, "业务编号【"+strCode+"】划拨失败"));
					}
					return info.toString();
				}
			});
		}
		// 组装返回结果
		for(int i =0; i< list.size(); i++){
			try{
				Future<String> future = completionService.take();
				if(future.get() != null && !"".equals(future.get())){
					message.append(future.get());
				}
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		return message.toString();
	}
}
