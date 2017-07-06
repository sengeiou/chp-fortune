
package com.creditharmony.fortune.creditor.matching.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorStatisticsDao;
import com.creditharmony.fortune.creditor.matching.dao.SendCountConfigDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorStatistics;
import com.creditharmony.fortune.creditor.matching.entity.SendCountConfig;

/**
 * 债权操作统计
 * @Class Name CreditorStatisticManager
 * @author 朱杰
 * @Create In 2016年3月20日
 */
@Service
public class CreditorStatisticsManager extends CoreManager<CreditorStatisticsDao, CreditorStatistics> {
	
	@Autowired
	private SendCountConfigDao sendCountConfigDao;
	
	/**
	 * 统计量更新
	 * 2016年3月22日
	 * By 朱杰
	 * @param userId
	 * @param count 数量
	 * @param opeType 操作类型
	 * @param billState 账单类型
	 * @param lastestSqLendCode 出借编号
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public CreditorStatistics updateStatistic(String userId, int count, String opeType, String billState,String lastestSqLendCode){
		//更新统计表
		Map<String, Object> param = new HashMap<String,Object>();
		//统计的用户
		param.put("userId", userId);
		//统计日
		param.put("statisticsDate", new Date());
		List<CreditorStatistics> list = super.dao.selectBySelective(param);
		CreditorStatistics update = new CreditorStatistics();
		if(list.size() ==0){
			//插入操作
			CreditorStatistics insert = new CreditorStatistics();
			insert.setUserId(userId);
			insert.setStatisticsDate(new Date());
			SendCountConfig config = sendCountConfigDao.getSendCountConfig(userId);
			insert.setCreditTaskCnt(config==null ? 0 : config.getLeaderSendCount());
			insert.preInsert();
			super.dao.insert(insert);
			update = insert;
		}else{
			update = list.get(0);
		}
		if(Constant.CREDIT_DONE.equals(opeType)){
			//债权推荐
			if(BillState.SQ.value.equals(billState)){
				//首期
				update.setCreditSqDoneCnt(update.getCreditSqDoneCnt() + count);
				//最后债权推荐出借编号，时间
				if(StringUtils.isNotEmpty(lastestSqLendCode)){
					update.setLastestSqLendCode(lastestSqLendCode);
					update.setLastestSqTime(new Date());
				}
			}else{
				//非首期
				update.setCreditFsqDoneCnt(update.getCreditFsqDoneCnt() + count);
			}			
		}else if(Constant.CREDIT_DISTRIBUTED.equals(opeType)){
			// 派单
			if(BillState.SQ.value.equals(billState)){
				//首期
				update.setCreditSqDistributedCnt(update.getCreditSqDistributedCnt() + count);
			}else{
				//非首期
				update.setCreditFsqDistributedCnt(update.getCreditFsqDistributedCnt() + count);
			}				
		}else if(Constant.CREDIT_DROP.equals(opeType)){
			//弃单
			update.setCreditDropCnt(update.getCreditDropCnt() + count);
		}else if(Constant.CREDIT_CHANGE.equals(opeType)){
			//换单
			update.setCreditChangeCnt(update.getCreditChangeCnt() + count);
		}else{
			return update;
		}
		update.preUpdate();
		super.dao.update(update);
		return update;
	}

	public List<CreditorStatistics> getBySelective(Map<String,Object> param){
		return super.dao.selectBySelective(param);
	}
}
