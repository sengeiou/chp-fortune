package com.creditharmony.fortune.maintenance.settles.facade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.ForApplyStatus;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.fortune.maintenance.settles.dao.MatchingCreditorForMtMapper;
import com.creditharmony.fortune.maintenance.settles.entity.MatchingCreditorInfo;
import com.creditharmony.fortune.maintenance.settles.service.StManager;
import com.creditharmony.fortune.utils.SmsUtil;


/**
 * 出借申请管理类
 * 
 * @Class Name LendApplyManager
 * @author
 * @Create In 2015年12月22日
 */
@Service
public class SettlesManager {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/** 待债权推荐信息表-Dao */
	@Autowired
	private MatchingCreditorForMtMapper daoMc;
	
	@Autowired
	private StManager sm;

	public String reSettle(Integer billday, Date billDate, String lendCode) {
		StringBuffer buff = new StringBuffer();
		// 查询 出借申请表：
		// 出借状态=划扣成功,完结状态=未完结,产品!=月满盈 的出借，
		// 本期账单日(billDate)<=到期日(若状态未提前赎回取回款表的到期日期/否则取出借的到期日)
		// 查询 待债权推荐信息表：
		// 结算状态=未结算,本期到期日期=本期账单日(billDate),债权状态=已推荐
		MatchingCreditorInfo keyMc = new MatchingCreditorInfo();
		keyMc.setMatchingBillDay(billday); // 账单日(日数)
		keyMc.setMatchingExpireDay(billDate); // 本期账单日
		keyMc.setLendCode(lendCode); // 出借编号
		keyMc.setStatus(ForApplyStatus.TQSH.value); // 状态!=提前赎回
		keyMc.setLendState(LendState.HKCG.value); // 出借状态=划扣成功
		keyMc.setDictApplyEndState(FinishState.WWJ.value); // 完结状态=未完结
		keyMc.setProductCode(ProductCode.YMY.value); // 产品=月满盈
		keyMc.setMatchingPayStatus(SettleStats.WJS.value); // 结算状态=未结算
		keyMc.setMatchingStatus(MatchingStatus.YTJ.value); // 债权状态=已推荐
		List<MatchingCreditorInfo> lsMc = daoMc.selMatchingInfos(keyMc);
		if (lsMc != null && lsMc.size() > 0) {
			for (MatchingCreditorInfo recMc : lsMc) {
				String info;
				String mId=recMc.getMatchingId();
				try {
					sm.exeCm(recMc);
					info = "债权匹配结算处理！债匹ID：" + mId
							+ "成功 " + SmsUtil.nowTime("yyyy/MM/dd HH:mm:ss");
					logger.debug(info);
//					buff.append(info).append("<br>");
					return  info;
				} catch (Exception e) {
					info = "债权匹配结算处理！债匹ID：" + mId
							+ "失败 " + SmsUtil.nowTime("yyyy/MM/dd HH:mm:ss");
					logger.error(info,e);
					buff.append(info).append("<br>");
					continue;
				}
			}
		}
		return buff.toString();
	}
}
