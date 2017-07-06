package com.creditharmony.fortune.maintenance.settles.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CreditRelease;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.fortune.type.XinhebaoType;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.maintenance.settles.dao.BorrowForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.CreditorHisForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.LoanphaseForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.MatchingCreditorForMtMapper;
import com.creditharmony.fortune.maintenance.settles.entity.BorrowInfo;
import com.creditharmony.fortune.maintenance.settles.entity.LoanphaseInfo;
import com.creditharmony.fortune.maintenance.settles.entity.MatchingCreditorInfo;

/**
 * 债权匹配结算日批处理-Service
 * 
 * @Class Name StManager
 * @author
 * @Create In 2015年12月11日
 */
@Service
public class StManager {


	/** 待债权推荐信息表-Dao */
	@Autowired
	private MatchingCreditorForMtMapper daoMc;

	/** 分期收益表-Dao */
	@Autowired
	private LoanphaseForMtMapper daoLp;

	/** 可用债权表-Dao */
	@Autowired
	private BorrowForMtMapper daoB;

	/** 债权管理记录表-Dao */
	@Autowired
	private CreditorHisForMtMapper daoCh;

	/**
	 * 债权匹配 2016年2月18日 By
	 * 
	 * @param none
	 * @return none
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void exeCm(MatchingCreditorInfo recMc) {
		Date endDay = null;
		endDay = recMc.getApplyExpireDay(); // 出借正常到期日
		String userName =UserUtils.getUser(UserUtils.getUserId()).getName();//登陆人姓名
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = sdf.parse("2099-12-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(null==endDay){
			endDay=dt;
		}
		// 本期到期日<到期日
		if (recMc.getMatchingExpireDay().compareTo(endDay) < 0) {
			String matchingId = recMc.getMatchingId();
			// 取得分期收益 （债权推荐ID，出借编号，借款编号，本期应还本息，本期应还本金，剩余期数）
			LoanphaseInfo keyLp = new LoanphaseInfo();
			keyLp.setMatchingId(matchingId); 			// 债权推荐ID
			keyLp.setLendCode(recMc.getLendCode()); 				// 出借编号
			keyLp.setPhaseDiscardStatus(ScrapState.BFQ.value); 		// 废弃状态=未废弃
			LoanphaseInfo recLp = daoLp.selSumLoanPhaseInfo(keyLp);

			recMc.setMatchingId(IdGen.uuid()); 						// 债权推荐ID
			recMc.setMatchingBorrowQuota(recLp.getPhaseAmount());	// 出借金额=本期应还本息的合计值
			recMc.setMatchingFirstdayFlag(BillState.FSQ.value); 	// 首期非首期标志位-> 1:非首期
			
			// 月息通、信和月增的场合，下期带推荐金额=本期应还本金的和
			if(ProductCode.YXT.value.equals(recMc.getProductCode()) || 
			   ProductCode.XHYZ.value.equals(recMc.getProductCode())
			) {
				recMc.setMatchingBorrowQuota(recLp.getPhasePrincipalCur());	// 出借金额=本期应还本的合计值
			}
			
			boolean prdFlag = false;
			// 出借产品为信和宝, 当前期数=12时, 出借金额=本笔出借的计划出借金额
			if ((ProductCode.XHB.value.equals(recMc.getProductCode()) && 
				 XinhebaoType.XHB12.value.equals(recMc.getXinhebaoType())) || 
				 ProductCode.XHBA.value.equals(recMc.getProductCode())
			) {
				if (recMc.getMatchingNow() == 12) {
					recMc.setMatchingBorrowQuota(recMc.getApplyLendMoney());// 出借金额=本笔出借的计划出借金额
					recMc.setMatchingFirstdayFlag(BillState.SQ.value);
					
					prdFlag = true;
				}
			}
			
			// 出借产品为信和宝C, 当前期数=6、12、18时, 出借金额=本笔出借的计划出借金额
			if (recMc.getProductCode().equals(ProductCode.XHBC.value)) {
				if (recMc.getMatchingNow() == 6 || 
					recMc.getMatchingNow() == 12 || 
					recMc.getMatchingNow() == 18
				) {
					recMc.setMatchingBorrowQuota(recMc.getApplyLendMoney());// 出借金额=本笔出借的计划出借金额
					recMc.setMatchingFirstdayFlag(BillState.SQ.value);
					
					prdFlag = true;
				}
			}
			
			//if (prdFlag == true) {
			// 根据待推荐ID,废弃状态，0：不废弃，1：废弃,债权是否已经释放，0：没释放，1：已释放
			// 取得分期收益信息 （债权推荐ID，出借编号，债权匹配ID，本期应还本金，剩余未还金额)
			keyLp.setPhaseReleaseStatus(CreditRelease.MSF.value); // 债权是否已经释放=没释放
			keyLp.setPhaseDiscardStatus(ScrapState.BFQ.value); // 废弃状态=未废弃
			List<LoanphaseInfo> recLpinfos = daoLp.selLoanPhase(keyLp);
			for (LoanphaseInfo recLpinfo : recLpinfos) {
				// 取得可用债权
				BorrowInfo keyB = new BorrowInfo();
				keyB.setCreditValueId(recLpinfo.getLoanCode());
				BorrowInfo recB = daoB.selBorrow(keyB);

				BigDecimal operateMoney = BigDecimal.ZERO;// 释放债权
				BigDecimal afterLoanCreditValue = BigDecimal.ZERO;// 释放后可用债权
				if (prdFlag == true) {
					operateMoney = recLpinfo.getPhasePrincipalSurplus().add(recLpinfo.getPhasePrincipalCur());//操作金额=截止本期未还金额+本期应还本金
					afterLoanCreditValue = recB.getLoanCreditValue()
							.add(recLpinfo.getPhasePrincipalSurplus())
							.add(recLpinfo.getPhasePrincipalCur());// 操作后债权价值=剩余可用债权价值+截止本期未还金额+本期应还本金
				} else {
					operateMoney = recLpinfo.getPhasePrincipalCur();//操作金额=本期应还本金
					afterLoanCreditValue = recB.getLoanCreditValue().add(
							recLpinfo.getPhasePrincipalCur());// 操作后债权价值=剩余可用债权价值+本期应还本金
				}

				// 插入债权管理表
				CreditorHis insCh = new CreditorHis();
				insCh.setId(IdGen.uuid()); // ID=uuid
				insCh.setDictCheckNode(Node.KYZQ.value); // 节点=0:可用债权
				insCh.setNodeId(recB.getCreditValueId()); // 节点ID=债权价值id
				insCh.setOperateType(OperateType.JSSFDQYHB.value); // 操作类型为=7:结算释放当期月还本
				insCh.setBeforMoney(recB.getLoanCreditValue()); // 操作前债权价值=可用债权价值
				insCh.setOperateMoney(operateMoney); // 操作的债权
				// insCh.setAfterMoney(recB.getLoanCreditValue().add(recLpinfo.getPhasePrincipalSurplus()));//
				// 操作后债权价值=截止本期未还金额
				insCh.setAfterMoney(afterLoanCreditValue);
				insCh.setCreateBy(userName);
				insCh.setCreateTime(new Date());
				insCh.setOperateTime(new Date());
				insCh.setModifyBy(userName);
				insCh.setModifyTime(new Date());
				insCh.setMatchingId(matchingId);
				daoCh.insCreditorHis(insCh);

				// 更新可用债权表 (根据匹配ID、借款ID)
				BorrowInfo updB = new BorrowInfo();
				updB.setCreditValueId(recLpinfo.getLoanCode());
				// updB.setLoanCreditValue(recB.getLoanCreditValue().add(recLpinfo.getPhasePrincipalSurplus()));
				updB.setLoanCreditValue(afterLoanCreditValue);
				updB.setModifyBy(userName);
				updB.setModifyTime(new Date());
				daoB.updBorrow(updB);

				// 更新分期收益表
				LoanphaseInfo updLp = new LoanphaseInfo();
				updLp.setPhaseId(recLpinfo.getPhaseId());
				updLp.setPhaseReleaseStatus(CreditRelease.YSF.value); // 债权是否已经释放=已释放
				updLp.setModifyBy(userName);
				updLp.setModifyTime(new Date());
				daoLp.updLoanPhase(updLp);
			}
			
			Date currentYMD = new Date();
			Calendar cl = Calendar.getInstance();
			int oldMatchingNow = recMc.getMatchingNow().intValue();
			if(prdFlag){
				cl.setTime(recMc.getApplyLendDay());
				cl.add(Calendar.MONTH, oldMatchingNow);
				currentYMD = cl.getTime();						
			}else{
				cl.setTime(recMc.getMatchingExpireDay());
				cl.add(Calendar.DAY_OF_MONTH, 1);
				currentYMD = cl.getTime();
			}
			recMc.setMatchingInterestStart(currentYMD);  // 起息日->出借日期+当前期数个月（信和宝A、信和宝12月回息+12，信和宝C+6、12、18）,其他产品为本期到期日 + 1天
			
			
			recMc.setMatchingNow(recMc.getMatchingNow() + 1); 							// 当前期数->当前期数 + 1
			recMc.setMatchingStatus(MatchingStatus.DTJ.value); 							// 债权状态->待匹配推荐
			recMc.setMatchingMatchMoney(BigDecimal.ZERO); 								// 已匹配金额->0
			recMc.setDictMatchingFileStatus(FilecpState.WHC.value); 					// 文件制作状态->0:未制作
			recMc.setDictMatchingFilesendStatus(EmailState.WFS.value); 					// 文件发送状态->0:未发送
			recMc.setMatchingExpireDay(CreditorUtils.getCreditDaybyLendday(currentYMD));// 本期到期日期-> 账单日YMD
			recMc.setMatchingCossTime(null); 											// 匹配审批通过时间-> null
			recMc.setMatchingMakeDay(null); 											// 制作协议时间-> null
			recMc.setMatchingOrderStatus(0); 											// 债权订购流程状态标识-> 0
			recMc.setMatchingPayStatus(SettleStats.WJS.value); 							// 结算状态->0:未结算
			recMc.setCreateBy(userName);
			recMc.setCreateTime(new Date());
			recMc.setModifyBy(userName);
			recMc.setModifyTime(new Date());
			// 插入待债权推荐信息表
			daoMc.insMatchingCreditorInfo(recMc);
			
			// 更新待债权推荐信息表
			MatchingCreditorInfo updMc = new MatchingCreditorInfo();
			updMc.setMatchingId(matchingId);
			updMc.setMatchingPayStatus(SettleStats.YJS.value); // 结算状态=已结算
			updMc.setModifyBy(userName);// 登陆人姓名
			updMc.setModifyTime(new Date());
			daoMc.updateMatchingCreditorInfo(updMc);
		}
	}
}

