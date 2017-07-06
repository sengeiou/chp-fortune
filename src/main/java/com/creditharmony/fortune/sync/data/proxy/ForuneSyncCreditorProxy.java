package com.creditharmony.fortune.sync.data.proxy;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.claim.dto.SyncClaim;
import com.creditharmony.core.fortune.type.LoanDistinguish;
import com.creditharmony.core.loan.type.LoanUse;
import com.creditharmony.core.sync.data.util.SyncDataTypeUtil;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.service.BorrowManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthableManager;
import com.creditharmony.fortune.sync.data.remote.ForuneSyncCreditorService;

/**
 * 财富债权同步代理类
 * @Class Name ForuneSyncCreditorProxy
 * @author 韩龙
 * @Create In 2015年12月25日
 */
@Component
public class ForuneSyncCreditorProxy implements ForuneSyncCreditorService {

	protected Logger logger = LoggerFactory.getLogger(ForuneSyncCreditorProxy.class); 
	
	private BorrowManager borrowManager = SpringContextHolder.getBean(BorrowManager.class);
	
	private BorrowMonthManager borrowMonthManager = SpringContextHolder.getBean(BorrowMonthManager.class);
	
	private BorrowMonthableManager borrowMonthableManager = SpringContextHolder.getBean(BorrowMonthableManager.class);
	
	// 默认是同步可用债权池表
	private  final String syncTable="borrow";

	/**
	 * 同步可用债权池表或月满盈可用债权池方法
	 */
	@Override
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public boolean executeSyncLoan(SyncClaim loanSync) {
		logger.info("同步可用债权池表或月满盈可用债权池--->开始");
		// 默认为失败
		boolean sussce = false;
		// 同步可用债权池
		try {
			if (loanSync.getSyncTableName().equals(syncTable)) {
				logger.info("同步可用债权池");
				Borrow borrw = new Borrow();
				BeanUtils.copyProperties(loanSync, borrw);
				borrw.setLoanPurpose(LoanUse.parseByCode(borrw.getLoanPurpose()).getName());
				borrw.setLoanQuota(new BigDecimal(loanSync.getLoanQuota()));
				borrw.setLoanCreditValue(new BigDecimal(loanSync
						.getLoanCreditValue()));
				borrw.setLoanMonthRate(new BigDecimal(loanSync
						.getLoanMonthRate()));
				borrw.setLoanValueYear(new BigDecimal(
						String.valueOf((Math.pow(
								(Double.valueOf(loanSync.getLoanMonthRate()) / 100 + 1),
								12) - 1) * 100)));
				borrw.setLoanBackmoneyDay(loanSync.getLoanBakcmoneyDay());
				if (SyncDataTypeUtil.VALUE_ADD.equals(loanSync.getSyncType())) {
					borrw.setCreditValueId(IdGen.uuid());
					borrw.setDicLoanDistinguish(LoanDistinguish.parseByName("HJ").getCode());
					borrowManager.save(borrw);
					sussce = true;
					logger.info("同步可用债权池插入数据成功");
				}
			} else {
				logger.info("月满盈可用债权池");
				// 月满盈可用债权池
				BorrowMonthable borrw = new BorrowMonthable();
				BeanUtils.copyProperties(loanSync, borrw);
				borrw.setLoanAvailabeValue(new BigDecimal(loanSync.getLoanQuota()));
				borrw.setLoanCreditValue(new BigDecimal(loanSync.getLoanCreditValue()));
				borrw.setLoanMonthRate(new BigDecimal(loanSync.getLoanMonthRate()));
				borrw.setLoanValueYear(new BigDecimal(loanSync.getLoanValueYear()));
				// 同步月满盈可用债权
				if (SyncDataTypeUtil.VALUE_ADD.equals(loanSync.getSyncType())){
					borrw.setCreditMonableId(IdGen.uuid());
					borrw.setDicLoanDistinguish(LoanDistinguish.parseByName("HJ").getCode());
					borrowMonthableManager.save(borrw);
					sussce = true;
					logger.info("同步月满盈可用债权插入数据成功");
				} 
			}
		} catch (BeansException e) {
			logger.error("同步失败",e);
			sussce = false;
		}
		logger.info("同步可用债权池表或月满盈可用债权池--->结束");
		return sussce;
	}
	
	/**
	 * 提前结清同步方法
	 */
	public boolean executeSyncEarlySettlement(SyncClaim loanSync) {
		logger.info("提前结清同步方法--->开始");
		// 默认为失败
		boolean sussce = false;
		if(loanSync.getSyncTableName().equals(syncTable)){
			logger.info("更新可用债权池状态为提前结清");
			if (SyncDataTypeUtil.VALUE_MODIFY.equals(loanSync.getSyncType())){
				try {
					borrowManager.executeSyncEarlySettlement(loanSync);
					sussce = true;
					logger.info("更新可用债权池状态为提前结清成功");
				} catch (Exception e) {
					logger.error("更新可用债权池失败"+e.getLocalizedMessage(),e);
				}
			}
		}else{
			logger.info("更新可用债权池失败");
			return sussce;
		}
		logger.info("提前结清同步方法--->结束");
		return sussce;
	}
	
	/**
	 * 测试
	 */
	public String testExecuteSyncLoan(String name){
		System.out.println(name+":你好！");
		return name + ":你好！";
	}
	
}
