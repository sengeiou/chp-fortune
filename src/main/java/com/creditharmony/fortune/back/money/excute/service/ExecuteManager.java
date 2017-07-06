package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyLogDao;
import com.creditharmony.fortune.back.money.common.dao.BackMoneyPoolDao;
import com.creditharmony.fortune.back.money.common.dao.DetailDao;
import com.creditharmony.fortune.back.money.common.dao.ThirdPlatformDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ThirdPlatform;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteJzhImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteTl1stImportModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteWyModel;
import com.creditharmony.fortune.template.entity.backmoney.ExecuteZjImportModel;
import com.creditharmony.fortune.template.entity.backmoney.FyImportModel;


/**
 * 执行回款Service
 * @Class Name ExecuteManager
 * @author 陈广鹏
 * @Create In 2016年4月13日
 */
@Service
public class ExecuteManager extends CoreManager<BackMoneyListDao, ListItemView>{
	
	@Autowired
	private BackMoneyPoolDao poolDao;
	@Autowired
	private BackMoneyLogDao logDao;
	@Autowired
	private ThirdPlatformDao platDao;
	@Autowired
	private DetailDao detailDao;
	@Autowired
	private BmManager bmManager;
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 处理执行回款结果
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param view
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void execute(ResultView view) {
//		BackMoneyPool tempP = poolDao.getByLendCode(view.getLendCode());
//		BigDecimal backActualbackMoney = tempP.getBackActualbackMoney();
		
		// 1.更新回款信息
		BackMoneyPool pool = new BackMoneyPool();
		pool.setBackmId(view.getBackmId());
		pool.setLendCode(view.getLendCode());
		pool.setVerTime(view.getVerTime());
		pool.setBackMoneyRemarks(view.getBackMoneyRemarks());
		pool.preUpdate();
		
		String userId = UserUtils.getUserId();
		Date date = new Date();
		BackMoneyLog log = new BackMoneyLog();
		log.setBackmId(view.getBackmId());
		log.setCheckExaminetype(view.getCheckExaminetype());
		log.setCheckById(userId);
		log.setCheckTime(date);
		log.preInsert();
		
		if (YoN.SHI.value.equals(view.getCheckExaminetype())) {
			// 通过
			pool.setDictBackStatus(BackState.DHKQR.value);
			pool.setDictBackMoneyError("");
			ThirdPlatform plat = new ThirdPlatform();
			plat.setId(view.getPlatformId());
			ThirdPlatform platform = platDao.get(plat);
			pool.setPlatformId(platform.getPlatformId());
			pool.setBackBank(platform.getBank());
			pool.setBackMoneyDay(new Date());
			pool.setBackResult(BackResult.SUCESS.value);
//			pool.setSuccessMoney(backActualbackMoney);
//			pool.setFailMoney(BigDecimal.ZERO);
			
			log.setDictBackmStatus(BackState.DHKQR.value);
		} else if (YoN.FOU.value.equals(view.getCheckExaminetype())) {
			// 不通过
			pool.setDictBackStatus(BackState.DHKTH.value);
			pool.setDictBackMoneyError(view.getCheckExamine());
			pool.setRebackFlag(YoN.SHI.value);
			
			BackMoneyPool p = detailDao.getBackMoneyPoolById(pool.getBackmId());
			if (YoN.SHI.value.equals(p.getIsSupplemented())) {
				// 补息数据流转，交换金额字段
				pool.setBackActualbackMoney(p.getSupplementedMoney());
				pool.setSupplementedMoney(p.getBackActualbackMoney());
			}
			
			log.setDictBackmStatus(BackState.DHKTH.value);
			log.setCheckExamine(view.getCheckExamine());
			log.setCheckReason(view.getCheckReason());
		} else {
			return;
		}
		int count = poolDao.update(pool);
		if (count==0) {
			BmUtils.throwDataOutOfDateException();
		}
		
		// 2.增加审批记录
		logDao.insert(log);
		
		// 3.全程留痕
		bmManager.insertCheck(pool, log);
	}

	/**
	 * 富友上传，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param map
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void fyUpload(Map<String, FyImportModel> map, String lendCode) {
		BackMoneyPool bmp = poolDao.getByLendCode(lendCode);
		if (bmp==null || !BmConstant.EXECUTE_STATUS_LIST.contains(bmp.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
			return;
		}
		BackMoneyLog log = new BackMoneyLog();
		BackMoneyPool pool = new BackMoneyPool();
		pool.setLendCode(lendCode);
		
		FyImportModel im = map.get(lendCode);
		if (BackResult.SUCESS.value.equals(im.getFinalResult())) {
			pool.setBackResult(BackResult.SUCESS.value);
			pool.setBackReason("");
			pool.setSuccessMoney(bmp.getBackActualbackMoney());
			pool.setFailMoney(BigDecimal.ZERO);
		} else {
			pool.setBackResult(BackResult.FAIL.value);
			pool.setBackReason(im.getBackRemark());
			pool.setFailMoney(BigDecimal.valueOf(im.getAmount()));
		}
		
		pool.setBackDay(DateUtils.parseDate(im.getTradeTime()));
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(new Date());
		pool.setPlatformId(BackMoneyPlat.FYPT.value);;
		pool.preUpdate();
		poolDao.updateByLendCode(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传富友平台回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = bmp.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 金账户上传，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param im
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void jzhUpload(ExecuteJzhImportModel im, String lendCode) {
		BackMoneyPool pool = poolDao.getByLendCode(lendCode);
		BackMoneyLog log = new BackMoneyLog();
		
		if (pool==null || !BmConstant.EXECUTE_STATUS_LIST.contains(pool.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
				return;
		}
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(new Date());
		pool.setPlatformId(BackMoneyPlat.JZH.value);
		pool.setBackResult(BackResult.SUCESS.value);
		pool.setBackDay(new Date());
		pool.setSuccessMoney(pool.getBackActualbackMoney());
		pool.setFailMoney(BigDecimal.ZERO);
		pool.preUpdate();
		poolDao.update(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传金账户回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 网银上传，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param im
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void wyUpload(ExecuteWyModel im) {
		if (ObjectHelper.isEmpty(im.getLendCode())) {
				return;
		}
		BackMoneyLog log = new BackMoneyLog();
		BackMoneyPool pool = poolDao.getByLendCode(im.getLendCode());
		if (pool==null || !BmConstant.EXECUTE_STATUS_LIST.contains(pool.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
				return;
		}
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(im.getFkDate());
		String bankId = platDao.getIdByBankCode(im.getFkAccountNo().trim());
		pool.setPlatformId(BackMoneyPlat.WY.value);
		pool.setBackResult(BackResult.SUCESS.value);
		pool.setBackDay(new Date());
		pool.setSuccessMoney(pool.getBackActualbackMoney());
		pool.setFailMoney(BigDecimal.ZERO);
		pool.setBackBank(bankId);
		pool.preUpdate();
		poolDao.update(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传网银回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(im.getLendCode(), operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}
	
	/**
	 * 网银上传，支持拆分后上传
	 * 2017年2月28日
	 * By 陈广鹏
	 * @param im
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void wyUploadNew(ExecuteWyModel im) {
		if (ObjectHelper.isEmpty(im.getLendCode())) {
			return;
		}
		BackMoneyLog log = new BackMoneyLog();
		BackMoneyPool pool = poolDao.getByLendCode(im.getLendCode());
		if (pool==null || !BmConstant.EXECUTE_STATUS_LIST.contains(pool.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
			return;
		}
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(im.getFkDate());
		String bankId = platDao.getIdByBankCode(im.getFkAccountNo().trim());
		pool.setPlatformId(BackMoneyPlat.WY.value);
		pool.setBackDay(new Date());
		// 成功金额
		pool.setSuccessMoney(new BigDecimal(im.getSuceessMoney()));
		// 失败金额
		BigDecimal failMoney = pool.getBackActualbackMoney().subtract(pool.getSuccessMoney());
		pool.setFailMoney(failMoney);
		
		if (failMoney.abs().compareTo(BigDecimal.valueOf(0.01))==1) {
			pool.setBackResult(BackResult.FAIL.value);
		} else {
			pool.setBackResult(BackResult.SUCESS.value);
		}
		pool.setBackBank(bankId);
		pool.preUpdate();
		poolDao.update(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传网银回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(im.getLendCode(), operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 中金上传，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param im
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void zjUpload(ExecuteZjImportModel im, String lendCode) {
		BackMoneyLog log = new BackMoneyLog();
		BackMoneyPool pool = poolDao.getByLendCode(lendCode);
		if (pool==null || !BmConstant.EXECUTE_STATUS_LIST.contains(pool.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
			return;
		}
		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(im.getBankPayTime());
		pool.setPlatformId(BackMoneyPlat.ZJPT.value);
		
		if (BmConstant.ZJ_SUCCESS.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.SUCESS.value);
			pool.setBackReason("");
			pool.setSuccessMoney(pool.getBackActualbackMoney());
			pool.setFailMoney(BigDecimal.ZERO);
		} else if (BmConstant.ZJ_DEALING.equals(im.getTradeStatus())
				|| BmConstant.ZJ_DEALING_2.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.DELLING.value);
			pool.setBackReason("");
		} else if (BmConstant.ZJ_FAIL.equals(im.getTradeStatus())) {
			pool.setBackResult(BackResult.FAIL.value);
			pool.setSuccessMoney(BigDecimal.ZERO);
			pool.setFailMoney(pool.getBackActualbackMoney());
			pool.setBackReason(im.getBankResponseMsg());
		} else {
			return;
		}
		
		pool.setBackDay(im.getBankPayTime());
		
		pool.preUpdate();
		poolDao.update(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传中金平台回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

	/**
	 * 通联上传，单条处理，更新数据库
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param map
	 * @param lendCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void tlUpload(Map<String, ExecuteTl1stImportModel> map,
			String lendCode) {
		BackMoneyLog log = new BackMoneyLog();
		BackMoneyPool pool = poolDao.getByLendCode(lendCode);
		if (pool==null || !BmConstant.EXECUTE_STATUS_LIST.contains(pool.getDictBackStatus())) {
			// 【没有对应数据】或【数据不在待回款列表】不更新数据
			return;
		}
		
		ExecuteTl1stImportModel model = map.get(lendCode);

		pool.setDictBackStatus(BackState.DHKQR.value);
		pool.setBackMoneyDay(model.getFinishDay());
		pool.setPlatformId(BackMoneyPlat.TL.value);
		pool.setBackResult(model.getFinalResult());
		pool.setBackDay(model.getFinishDay());
		pool.setSuccessMoney(pool.getBackActualbackMoney().subtract(BigDecimal.valueOf(model.getMoney())));
		pool.setFailMoney(BigDecimal.valueOf(model.getMoney()));
		if (BackResult.FAIL.value.equals(model.getFinalResult())) {
			pool.setBackReason(model.getReason());
		} else if (BackResult.DELLING.value.equals(model.getFinalResult())) {
			// 不更新成功金额、失败金额
			pool.setSuccessMoney(null);
			pool.setFailMoney(null);
		} else {
			pool.setBackReason("");
		}
		pool.preUpdate();
		poolDao.update(pool);
		
		log.setDictBackmStatus(BackState.DHKQR.value);
		log.setCheckExaminetype(YoN.SHI.value);
		log.setCheckTime(new Date());
		log.setCheckById(UserUtils.getUserId());
		log.preInsert();
		logDao.insert(log);
		
		// 全程留痕
		String operatorInfo = "上传通联平台回盘结果，更新为[" + BackResult.backResultMap.get(pool.getBackResult()) 
				+"]，回款状态更新为[" + BackState.getBackState(pool.getDictBackStatus()) + "]";
		String operatorType = "上传回盘结果";
		String operateAffiliated = pool.getBackmId();
		FortuneLogNode fortuneLogNode = FortuneLogNode.MONEY_EXCUTE;
		checkManager.addCheck(lendCode, operatorInfo, operatorType, operateAffiliated, fortuneLogNode);
	}

}
