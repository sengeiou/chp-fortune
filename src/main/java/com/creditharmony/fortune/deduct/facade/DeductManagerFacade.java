package com.creditharmony.fortune.deduct.facade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.google.common.collect.Lists;

/**
 * 划扣业务转换层
 * @Class Name DeductBalanceFacade
 * @author 张永生
 * @Create In 2016年4月27日
 */
@Service
public class DeductManagerFacade {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DeductApplyDao deductApplyDao;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private LoanApplyDao loanApplyDao;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	/**
	 * 单线程
	 * 划扣结算--线上划扣 
	 * 2015年12月3日 
	 * By 韩龙
	 * @param deductPoolExt
	 * @param ids
	 *//*
	public List<String> insertTask(DeductPoolEx deductPoolExt, String ids,String type) {
		logger.debug("线上划扣------->开始");
		List<String> massge = Lists.newArrayList();
		if (ids == null) {
			return null;
		}
		String[] applyCodes = ids.split(",");
		for (String codes : applyCodes) {
			String[] arrayStr = codes.split("_");
			// 出借编号
			String lendCode = arrayStr[0];
			// 更新时间（排它标识）
			String verTime = arrayStr[1];
			// 获取划扣申请信息
			DeductPool pool = new DeductPool();
			pool.setApplyCode(lendCode);
			pool.setVerTime(verTime);
			pool = deductApplyDao.get(pool);
			// 如果为空则该记录被其它业务人员处理过
			if(pool == null){
				String info = "业务编号【"+lendCode+"】被其它业务人员处理过";
				logger.debug(info);
				massge.add(info);
				continue;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lendCode", lendCode);
			BaseExportModel baseExportModel = deductApplyDao.getBaseExportModel(map);
			if (baseExportModel == null) {
				logger.debug("出借内容空出借编号为【"+lendCode+"】");
				continue;
			}
			try {
				// 组装划扣请求参数
				DeductReq recReq = deductReqManager.setDeductReqInfo(baseExportModel,pool.getActualDeductMoney(),"_划扣");
				// 提交划扣任务并保存划扣记录
				deductManager.addTask(type, massge, lendCode, pool, recReq);
				
			} catch (Exception e) {
				massge.add(e.getMessage());
				logger.error("提交划扣任务并保存划扣记录失败：" + e.getMessage(), e);
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
						FortuneLogLevel.RED.value, e.getMessage()));
			}
			// 全程流痕
			checkManager.addCheck(lendCode, "划扣线上划扣", "线上划扣",
					pool.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
		}
		logger.debug("线上划扣------->开始");
		return massge;
	}*/
	
	/**
	 * 划扣结算--线上划扣 多线程
	 * 2015年12月3日 
	 * By 韩龙
	 * @param deductPoolExt
	 * @param ids
	 */
	public List<String> insertTaskThread(DeductPoolEx deductPoolExt, String ids,final String type) {
		logger.debug("线上划扣------->开始");
		List<String> massge = Lists.newArrayList();
		if (ids == null) {
			return null;
		}
		String[] applyCodes = ids.split(",");
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (final String codes : applyCodes) {
			// 开启线程
			completionService.submit(new Callable<String>(){
				// 回调
				public String call() throws Exception {
					StringBuffer info = new StringBuffer();
					String[] arrayStr = codes.split("_");
					// 出借编号
					String lendCode = arrayStr[0];
					// 更新时间（排它标识）
					String verTime = arrayStr[1];
					// 获取划扣申请信息
					DeductPool pool = new DeductPool();
					pool.setApplyCode(lendCode);
					pool.setVerTime(verTime);
					pool = deductApplyDao.get(pool);
					// 如果为空则该记录被其它业务人员处理过
					if(pool == null){
						String error = "业务编号【"+lendCode+"】被其它业务人员处理过";
						logger.debug(error);
						info.append(error);
						return info.toString();
					}
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("lendCode", lendCode);
					BaseExportModel baseExportModel = deductApplyDao.getBaseExportModel(map);
					if (baseExportModel == null) {
						logger.debug("出借内容空出借编号为【"+lendCode+"】");
						info.append("出借内容空出借编号为【"+lendCode+"】");
						return info.toString();
					}
					try {
						// 组装划扣请求参数
						DeductReq recReq = deductReqManager.setDeductReqInfo(baseExportModel,pool.getActualDeductMoney(),"_划扣");
						// 提交划扣任务并保存划扣记录
						deductManager.addTask(type, info, lendCode, pool, recReq);
						
					} catch (Exception e) {
						info.append(e.getMessage());
						logger.error("提交划扣任务并保存划扣记录失败：" + e.getMessage(), e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
								FortuneLogLevel.RED.value, e.getMessage()));
					}
					// 全程流痕
					checkManager.addCheck(lendCode, "划扣线上划扣", "线上划扣",
							pool.getDeductApplyId(),FortuneLogNode.DEDUCT_BALANCE);
					return info.toString();
				}
			});
		}
		// 组装返回结果
		for(int i =0; i< applyCodes.length; i++){
			try{
				Future<String> future = completionService.take();
				if(future.get()!=null && !"".equals(future.get())){
					massge.add(future.get());
				}
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		logger.debug("线上划扣------->开始");
		return massge;
	}
	
	/**
	 * 批量或单个债权释放 2015年12月8日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public String batchUpdate(Map<String, Object> map) {
		StringBuffer message = new StringBuffer();
		String code = (String) map.get("ids");
		if (code == null) {
			logger.debug("业务编号不能为空");
			message.append("业务编号不能为空").append("</br>");
			return message.toString();
		}
		String[] lendCodes = code.split(",");
		DeductPool dp = null;
		for (String strCode : lendCodes) {
			String[] strArray = strCode.split("_");
			// 出借编号
			String lendCode = strArray[0];
			// 更新时间(排它标识)
			String verTime = strArray[1];
			dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setVerTime(verTime);
			try {
				int result = deductManager.batchUpdate(dp, map,message);
				if(result > 0){
//					checkManager.addCheck(lendCode, "划扣失败/资金托管划职失败释放债权","释放债权");
					checkManager.addCheck(lendCode, "划扣失败/资金托管划职失败释放债权","释放债权",
							lendCode,FortuneLogNode.DEDUCT_FAIL);
				}
			} catch (Exception e) {
				logger.error("业务编号【"+lendCode+"】释放债权失败，失败信息："+e.getMessage(), e);
				message.append("业务编号【"+lendCode+"】释放债权失败").append("</br>");
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
						FortuneLogLevel.ORANGE.value, e.getMessage()));
			}
		}
		
		return message.toString();
	}
	
	/**
	 * 导入线下划扣结果 2015年12月21日 By 韩龙
	 * 
	 * @param list
	 * @param map
	 */
	public void saveExcel(Map<String, DeductPool> disMap,StringBuffer message) {
		// 循环更新
		for (String key : disMap.keySet()) {
			DeductPool dp = disMap.get(key);
			// 获取划扣申请
			DeductPool pool = new DeductPool();
			pool.setApplyCode(dp.getApplyCode());
			pool = deductApplyDao.get(pool);
			if(pool == null){
				return;
			}
			// 累加成功金额
			dp.setActualDeductMoney(new BigDecimal(DeductUtils.isNullMoney(pool.getActualDeductMoney()))
				.add(new BigDecimal(DeductUtils.isNullMoney(dp.getActualDeductMoney()))).toString());
			// 累加失败金额
			dp.setFailMoney(new BigDecimal(DeductUtils.isNullMoney(dp.getFailMoney())).toString());
			// 出借申请表
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(key);
			loanApply = loanApplyDao.get(loanApply);
			if(dp.getDictDeductStatus().equals(DeductState.HKCG.value)){
				try {
					deductManager.importSaveExcel(dp,loanApply);
				} catch (Exception e) {
					logger.error("业务编号【"+key+"】导入失败", e);
					message.append("业务编号【"+key+"】导入失败").append("</br>");
				}
				// 发送短信
				commonFacade.smsInfo(key,dp.getDictDeductStatus(),loanApply);
				// 制作文件
				commonFacade.makeFileInfo(loanApply.getApplyCode());
				// 划扣成功三网校验首单
				sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(), loanApply.getApplyCode());
			}else{
				try {
					deductManager.importSaveExcel(dp,loanApply);
				} catch (Exception e) {
					logger.error("业务编号【"+key+"】导入失败", e);
					message.append("业务编号【"+key+"】导入失败").append("</br>");
				}
			}
		}
	}
}
