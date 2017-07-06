package com.creditharmony.fortune.maintenance.missrelease.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.fortune.type.CreditRelease;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.creditor.matching.dao.CreditorTradeDao;
import com.creditharmony.fortune.maintenance.missrelease.dao.MatchingCreditorReleaseMapper;
import com.creditharmony.fortune.maintenance.missrelease.entity.ReleaseCreditorInfo;
import com.creditharmony.fortune.maintenance.settles.dao.BorrowForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.CreditorHisForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.LoanphaseForMtMapper;
import com.creditharmony.fortune.maintenance.settles.entity.BorrowInfo;
import com.creditharmony.fortune.maintenance.settles.entity.LoanphaseInfo;

/**
 * 债权匹配结算日批处理-Service
 * 
 * @Class Name StManager
 * @author
 * @Create In 2015年12月11日
 */
@Service
public class MissReleaseManager {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** 待债权推荐信息表-Dao */
	@Autowired
	private MatchingCreditorReleaseMapper daoMc;

	/** 分期收益表-Dao */
	@Autowired
	private LoanphaseForMtMapper daoLp;

	/** 可用债权表-Dao */
	@Autowired
	private BorrowForMtMapper daoB;

	/** 债权管理记录表-Dao */
	@Autowired
	private CreditorHisForMtMapper daoCh;
	
	/** 债权交易表Dao **/
	@Autowired
	private CreditorTradeDao creditorTradeDao;

	/**
	 * 撤销错误匹配释放债权 2016年2月18日 By
	 * 
	 * @param lendCode
	 *            出借编号
	 * @param startPeriods
	 *            开始期数
	 * @param endPeriods
	 *            截止期数
	 * @return none
	 */
	public String releaseCreditor(String lendCode, Integer startPeriods,
			Integer endPeriods) {
		String info = "";
		// 查询 出借申请表\待债权推荐信息表\债权交易表：出借状态=划扣成功,出借编号=lendCode,产品!=月满盈 的出借，
		// 开始期数<=当前期数<=截止期数，债权状态=已推荐
		ReleaseCreditorInfo keyMc = new ReleaseCreditorInfo();
		keyMc.setLendCode(lendCode); // 出借编号
		keyMc.setLendStatus(LendState.HKCG.value); // 出借状态=划扣成功
		keyMc.setProductCode(ProductCode.YMY.value); // 产品=月满盈
		keyMc.setMatchingStatus(MatchingStatus.CX.value); // 债权状态=已推荐
		keyMc.setStartPeriods(startPeriods);
		keyMc.setEndPeriods(endPeriods);
		//List<ReleaseCreditorInfo> lsMc = daoMc.selReleaseCreditorInfos(keyMc);
		List<ReleaseCreditorInfo> lsMc = daoMc.selRlCreditorInfos(keyMc);
		String userName = UserUtils.getUser(UserUtils.getUserId()).getName();// 登陆人姓名
		try {
			// 释放债权
			release(lsMc, userName, endPeriods);
		} catch (Exception e) {
			info = "出借编号：" + lendCode + "失败 " + "第" + "期到" + "第" + "期的债权撤销失败</br>";
			logger.error(info,e);
		}
		logger.debug(info);
		return info;
	}

	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	private void release(List<ReleaseCreditorInfo> lsMc, String userName, Integer endPeriods) {
		List<String> lsMatchingId = new ArrayList<String>();
		//将匹配错的债权（结算日已经释放的已经加到可用债权上面去了所以要减去）从可用债权中减去
		if (lsMc != null && lsMc.size() > 0) {
			for (ReleaseCreditorInfo recMc : lsMc) {
				if (!lsMatchingId.contains(recMc.getMatchingId())) {
					lsMatchingId.add(recMc.getMatchingId());
					LoanphaseInfo lp = new LoanphaseInfo();
					lp.setMatchingId(recMc.getMatchingId());
					lp.setPhaseDiscardStatus(ScrapState.BFQ.value); // 废弃状态=未废弃
					lp.setPhaseReleaseStatus(CreditRelease.MSF.value); // 债权是否已经释放=没释放
					List<LoanphaseInfo> recLpinfos = daoLp.selLoanPhase(lp);
					String settelState = recMc.getMatchingPayStatus();
					for(LoanphaseInfo li : recLpinfos){
						// 更新可用债权表 (根据匹配ID、借款ID)
						BorrowInfo updB = new BorrowInfo();
						updB.setCreditValueId(li.getLoanCode());
						if(SettleStats.WJS.value.equals(settelState)){
							updB.setLoanCreditValue(BigDecimal.ZERO);// 本期应还本金+剩余未还本金
						}else{
							updB.setLoanCreditValue(li.getPhasePrincipalCur());// 本期应还本金
						}
						updB.setModifyBy(userName);
						updB.setModifyTime(new Date());
						daoB.updBorrowInfo(updB);
					}
					
					// 更新待债权推荐信息表
					ReleaseCreditorInfo updMc = new ReleaseCreditorInfo();
					updMc.setMatchingId(recMc.getMatchingId());
					updMc.setMatchingStatus(MatchingStatus.CX.value); // 债权状态=撤销
					updMc.setModifyBy(userName);// 登陆人姓名
					updMc.setModifyTime(new Date());
					daoMc.updateMatchingCreditorInfo(updMc);
					//TODO 删除交易表中交易数据
					CreditorTrade ct = new CreditorTrade();
					ct.setMatchingId(recMc.getMatchingId());
					ct.setDictTradeCreditStatus(CreditTradestate.WKSBGB.value);
					creditorTradeDao.updateByMatchingId(ct);
				
					// 删除分期收益表数据
					daoLp.delLoanPhase(recMc.getMatchingId());
				}
			}
		}
		/*if (lsMatchingId.size() > 0) {
			for (String matchingId : lsMatchingId) {
				// 更新待债权推荐信息表
				ReleaseCreditorInfo updMc = new ReleaseCreditorInfo();
				updMc.setMatchingId(matchingId);
				updMc.setMatchingStatus(MatchingStatus.CX.value); // 债权状态=撤销
				updMc.setModifyBy(userName);// 登陆人姓名
				updMc.setModifyTime(new Date());
				daoMc.updateMatchingCreditorInfo(updMc);
				//TODO 删除交易表中交易数据
				CreditorTrade ct = new CreditorTrade();
				ct.setMatchingId(matchingId);
				ct.setDictTradeCreditStatus(CreditTradestate.WKSBGB.value);
				creditorTradeDao.updateByMatchingId(ct);
			
				// 删除分期收益表数据
				daoLp.delLoanPhase(matchingId);
			}
		}*/
	}
}

