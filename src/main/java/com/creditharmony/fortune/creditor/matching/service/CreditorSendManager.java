
package com.creditharmony.fortune.creditor.matching.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.CreditRelease;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.fortune.type.ScrapState;
import com.creditharmony.core.fortune.type.TracesType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.dao.CreditorHisDao;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.LoanphasePeriodDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.maintenance.settles.dao.BorrowForMtMapper;
import com.creditharmony.fortune.maintenance.settles.dao.LoanphaseForMtMapper;
import com.creditharmony.fortune.maintenance.settles.entity.BorrowInfo;
import com.creditharmony.fortune.maintenance.settles.entity.LoanphaseInfo;

/**
 * @Class Name CreditorSendManager
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
@Service
public class CreditorSendManager extends CoreManager<CreditorSendDao,CreditorTradeEx>{
	
	@Autowired
	private DeductApplyDao deductApplyDao;
	@Autowired
	private CreditorHisDao creditorHisDao;
	@Autowired
	private EmailInfoDao emailInfoDao;
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;// 待推荐Dao
	
	@Autowired
	private TransferRelationDao transferRelationDao;
	
	@Autowired
	private LoanphaseDao loanphaseDao;
	
	/** 分期收益表-Dao */
	@Autowired
	private LoanphaseForMtMapper daoLp;

	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private LoanphasePeriodDao loanphasePeriodDao;
	
	/** 可用债权表-Dao */
	@Autowired
	private BorrowForMtMapper daoB;
	
	/**
	 * 分页查询
	 * 2015年12月9日
	 * By 胡体勇
	 * @param page
	 * @param creditorTradeEx
	 * @return
	 */
	public Page<CreditorTradeEx> findPage(Page<CreditorTradeEx> page,CreditorTradeEx creditorTradeEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		pageBounds.setCountBy("matchingId");
		List<String> list = new ArrayList<String>();
		/*// 转换城市的查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getAddrCity())){
		    String[] str = creditorTradeEx.getAddrCity().split(",");
		    for(int i=0;i<str.length;i++){
		    	String[] s = str[i].split("_",Constant.SIZE);
		    	list.add(s[1]);
		    }
		    creditorTradeEx.setCity(list);
		}*/
		// 省市
		String city = creditorTradeEx.getAddrCity();
		if (!StringUtils.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			  creditorTradeEx.setCity(c);
		}
		// 转换产品查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getProduct())){
			String[] str = creditorTradeEx.getProduct().split(",");
			creditorTradeEx.setProductCode(Arrays.asList(str));
		}
		
		// 转换匹配标识（sql使用）查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getDictMatchingFlagType())){
			String[] str = creditorTradeEx.getDictMatchingFlagType().split(",");
			creditorTradeEx.setDictMatchingFlagTypes(Arrays.asList(str));
		}
				
		// 转换营业部查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getOrg())){
			String[] str = creditorTradeEx.getOrg().split(",");
			creditorTradeEx.setOrgCode(Arrays.asList(str));
		}
		creditorTradeEx.setMatchingStatus(MatchingStatus.YTJ.value);
		PageList<CreditorTradeEx> pageList = (PageList<CreditorTradeEx>) this.dao.findList(creditorTradeEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 批量发送协议
	 * 2015年12月23日
	 * By 胡体勇
	 * @param ids
	 * @return
	 */
/*    public int sendProtocol(String ids){
    	int result = 0;
    	List<String> arrayList = new ArrayList<String>();
    	// 分离出推荐id.spl
    	String[] id = ids.split(";");
    	for(int j=0;j<id.length;j++){
    		String[] str = id[j].split(",");
    		arrayList.add(str[0]);
    	}
    	for(int i = 0;i<arrayList.size();i++){
    		try {
				result = emailAdd(result, arrayList, id, i);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
    	}
		return result;
    }*/

	/**
	 * 2016年4月22日
	 * By 柳慧
	 * @param result
	 * @param arrayList
	 * @param id
	 * @param i
	 * @return 
	 */
	/*@Transactional(value="fortuneTransactionManager",readOnly = false)
	private int emailAdd(int result, List<String> arrayList, String[] id, int i) {
		// 查询推荐id下是否有协议
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchingId", arrayList.get(i));
		map.put("matchingId", arrayList.get(i));
		map.put("attafiletype", "pdf");
		map.put("emailId",Constant.EMAIL_TEMPEMPLT_PROTOCOL_ID_VALUE);
		AttachmentEx attachment = super.dao.getAttachmentExInfo(map);
		if(attachment != null){
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.setEmailSendId(IdGen.uuid());
			emailInfo.setLendCode(attachment.getLoanCode());
		    emailInfo.setCreditId(id[i]);
			emailInfo.setEmailTemplateId(Constant.EMAIL_TEMPEMPLT_PROTOCOL_ID_VALUE);
			emailInfo.setEmailSender(UserUtils.getUser().getId());
			emailInfo.setEmailSenderTime(new Date());
			emailInfo.setEmailRecpName(attachment.getCustName());
			emailInfo.setEmailRecpEmail(attachment.getCustEmail());
			emailInfo.setEmailSubject(Constant.SEND_MAIL_SUBJECT_PROTOCOL_VALUE);
			emailInfo.setEmailMsg(attachment.getTemplateContent());
			emailInfo.setEmailType(attachment.getTemplateType());
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfo.setPdfType(attachment.getAttaFlag());
			emailInfo.setCreateBy(UserUtils.getUser().getId());
			emailInfo.setCreateTime(new Date());
			emailInfoDao.insert(emailInfo);
			result++;
		}
		return result;
	}*/
	
	/**
	 * 撤销债权
	 * 2015年12月24日
	 * By 胡体勇
	 * @param creditorTrade
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int cancelCreditor(CreditorTrade creditorTrade){
		int result = 0;
		creditorTrade.setDictTradeCreditStatus(CreditTradestate.WKSBGB.value);
		// 查询推荐id下的所有借款人的匹配金额
		List<CreditorTrade> list = super.dao.findCreditorTradeInfo(creditorTrade);
		if(ArrayHelper.isNotEmpty(list)){
			// 设置写入债权管理记录表公共数据
			CreditorHis creditorHis = new CreditorHis();
			
			creditorHis.setMatchingId(creditorTrade.getMatchingId());
			creditorHis.setOperator(UserUtils.getUserId());
			creditorHis.setCreateBy(UserUtils.getUserId());
			creditorHis.setCreateTime(new Date());
			for(int i = 0;i<list.size();i++){
				// 根据不同的节点修改可用债权表和月满盈可用债权表
				if(Node.KYZQ.value.equals(list.get(i).getCreditNode())){
					Borrow borrow = new Borrow();
					borrow.setCreditValueId(list.get(i).getCreditCode());
					// 查询可用债权表里的可用债权价值
					Borrow bw = super.dao.findCreditValue(borrow);
					borrow.setLoanCreditValue(bw.getLoanCreditValue().add(list.get(i).getTradeMateMoney()));
					borrow.setModifyBy(UserUtils.getUserId());
					borrow.setModifyTime(new Date());
					// 修改可用债权表可用债权
					super.dao.updateBorrow(borrow);
					// 设置不同节点写入债权管理记录表数据
					creditorHis.setId(IdGen.uuid());
					creditorHis.setDictCheckNode(Node.KYZQ.value);
					creditorHis.setNodeId(list.get(i).getCreditCode());
					creditorHis.setOperateType(OperateType.ZQGL.value);
					creditorHis.setBeforMoney(bw.getLoanCreditValue());
					creditorHis.setOperateMoney(list.get(i).getTradeMateMoney());
					creditorHis.setAfterMoney(bw.getLoanCreditValue().add(list.get(i).getTradeMateMoney()));
					creditorHisDao.insert(creditorHis);
				}else if(Node.YMYKY.value.equals(list.get(i).getCreditNode())){
					BorrowMonthable borrowMonthable = new BorrowMonthable();
					borrowMonthable.setCreditMonableId(list.get(i).getCreditCode());
					// 查询月满盈可用债权表里的可用债权价值
					BorrowMonthable bm = super.dao.findAvailabevValue(borrowMonthable);
					borrowMonthable.setLoanAvailabeValue(bm.getLoanAvailabeValue().add(list.get(i).getTradeMateMoney()));
					borrowMonthable.preUpdate();
					super.dao.updateBorrowMonthable(borrowMonthable);
					// 设置不同节点写入债权管理记录表数据
					creditorHis.setId(IdGen.uuid());
					creditorHis.setDictCheckNode(Node.YMYKY.value);
					creditorHis.setNodeId(list.get(i).getCreditCode());
					creditorHis.setOperateType(OperateType.ZQGL.value);
					creditorHis.setBeforMoney(bm.getLoanAvailabeValue());
					creditorHis.setOperateMoney(list.get(i).getTradeMateMoney());
					creditorHis.setAfterMoney(bm.getLoanAvailabeValue().add(list.get(i).getTradeMateMoney()));
					creditorHisDao.insert(creditorHis);
				}
				result++;
			}
			// 撤销债权时修改待推荐列表里的匹配金额和匹配债权状态
			MatchingCreditor matchingCreditor = new MatchingCreditor();
			matchingCreditor.setMatchingId(creditorTrade.getMatchingId());
			matchingCreditor.setMatchingMatchMoney( BigDecimal.valueOf(Constant.MATCHING_CREDITOR_MATCHING_MONEY));
			matchingCreditor.setMatchingStatus(MatchingStatus.CXCP.value);
			matchingCreditor.setModifyBy(UserUtils.getUserId());
			matchingCreditor.setModifyTime(new Date());
			super.dao.updateMatchingCreditor(matchingCreditor);
			Loanphase loanphase = new Loanphase();
			loanphase.setMatchingId(creditorTrade.getMatchingId());
			loanphase.setLendCode(creditorTrade.getLendCode());
			loanphaseDao.delete(loanphase);
			// 撤销债权时修改债权交易表里的债权交易状态
			creditorTrade.setDictTradeCreditStatus(CreditTradestate.WKSBGB.value);
			creditorTrade.setModifyBy(UserUtils.getUserId());
			creditorTrade.setModifyTime(new Date());
			super.dao.updateTradeCreditStatus(creditorTrade);
			
			//债权撤销重匹，把邮件状态为待发送改为撤销
			Map<String,String> emailInfoParam = new HashMap<String, String>();
			emailInfoParam.put("creditId", creditorTrade.getMatchingId());
			emailInfoParam.put("whereEmailSendStatus", EmailState.DFS.value);
			emailInfoParam.put("emailSendStatus", EmailState.CX.value);
			/*EmailTemplate tempEmail = emailInfoDao.getEmailTemplateByType(mc.getMatchingFirstdayFlag());
			emailInfoParam.put("templateId", tempEmail.getId());*/
			emailInfoDao.updateByMap(emailInfoParam);
			// 债权撤销
			checkManager.addCheck(creditorTrade.getLendCode(), "待推荐撤销成功！", TracesType.ZQ_CX.getName(),creditorTrade.getMatchingId(),FortuneLogNode.BORROW_CANCEL);
			loanphasePeriodDao.deleteByMatchingId(creditorTrade.getMatchingId());
		}	
		creditorAidManager.caInvalidCreditorFile(creditorTrade.getMatchingId());
		return result;
	}
	
	/**
	 * 撤销债权 主要用于漏批造成超过出借到期日，债权释放
	 * 2015年12月24日
	 * By 胡体勇
	 * @param creditorTrade
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void cancelMRCreditor(MatchingCreditorEx mc){
		LoanphaseInfo keyLp = new LoanphaseInfo();
		keyLp.setMatchingId(mc.getMatchingId()); 		        // 债权推荐ID
		keyLp.setPhaseDiscardStatus(ScrapState.BFQ.value);	    // 废弃状态=未废弃
		keyLp.setPhaseReleaseStatus(CreditRelease.MSF.value);	// 债权是否已经释放=没释放
		List<LoanphaseInfo> lsLp = daoLp.selLoanPhase(keyLp);
		if(lsLp!=null && lsLp.size()>0){
			for (int j = 0; j < lsLp.size(); j++) {
				LoanphaseInfo recLp = lsLp.get(j);
				// 查询可用债权表 (根据匹配ID、借款ID)
				BorrowInfo keyB = new BorrowInfo();
				keyB.setCreditValueId(recLp.getLoanCode());
				BigDecimal releaseMoney = BigDecimal.ZERO;
				String matchingPayStatus = mc.getMatchingPayStatus();
				if(SettleStats.WJS.value.equals(matchingPayStatus)){
					//应释放金额=本期应还金额+剩余金额
					releaseMoney = recLp.getPhasePrincipalCur().add(recLp.getPhasePrincipalSurplus());
				}else{
					releaseMoney = recLp.getPhasePrincipalSurplus();
				}
				BorrowInfo recB = daoB.selBorrow(keyB);
				
				// 更新可用债权表 (根据匹配ID、借款ID)
				//已结算的场合、应该释放金额=剩余未还金额；未结算的场合、应释放金额=本期应还金额+剩余金额
				BorrowInfo updB = new BorrowInfo();
				updB.setCreditValueId(recB.getCreditValueId());
				updB.setLoanCreditValue(recB.getLoanCreditValue().add(releaseMoney));
				updB.setModifyBy(UserUtils.getUserId());
				updB.setModifyTime(new Date());
				daoB.updBorrow(updB);

				// 插入债权管理记录表
				CreditorHis insCh = new CreditorHis();
				insCh.setId(IdGen.uuid()); // ID=uuid
				insCh.setDictCheckNode(Node.KYZQ.value); // 节点=0:可用债权
				insCh.setNodeId(recB.getCreditValueId()); // 节点ID=债权价值id
				insCh.setOperateType(OperateType.CJDQZQSF.value); // 操作类型为=15:出借到期债权释放
				insCh.setBeforMoney(recB.getLoanCreditValue()); // 操作前债权价值=可用债权价值
				insCh.setOperateMoney(releaseMoney); // 操作的债权价值=应释放金额
				insCh.setAfterMoney(insCh.getBeforMoney().add(releaseMoney));// 操作后债权价值=可用债权价值+应释放金额
				insCh.setCreateBy(UserUtils.getUserId());
				insCh.setCreateTime(new Date());
				insCh.setModifyBy(UserUtils.getUserId());
				insCh.setModifyTime(new Date());
				insCh.setMatchingId(mc.getMatchingId());
				creditorHisDao.insert(insCh);

				// 更新分期收益表
				LoanphaseInfo updLp = new LoanphaseInfo();
				updLp.setPhaseId(recLp.getPhaseId());
				updLp.setPhaseReleaseStatus(CreditRelease.YSF.value); // 债权是否已经释放=已释放
				updLp.setModifyBy(UserUtils.getUserId());
				updLp.setModifyTime(new Date());
				daoLp.updLoanPhase(updLp);
			}	
		}

		
	}
	
	
	/**
	 * 判断页面是通过搜索栏导出，还是通过复选框导出
	 * 2015年12月25日
	 * By 高士芳
	 * @param creditorTradeEx
	 * @return
	 */
	public CreditorTradeEx judgeIds(CreditorTradeEx search){
		if(!StringUtils.isEmpty(search.getMatchingId())){
			// 筛选出推荐id
			String[] array = search.getMatchingId().split(";");
			List<String> listMatchingID = new ArrayList<String>();
			for(int i=0;i<array.length;i++){
				String[] a = array[i].split(",");
				listMatchingID.add(a[0]);
			}
			search.setListMatchingId(listMatchingID);
		}
		return search;
	}
	
	/**
	 * 批量下载协议word/pdf
	 * 2015年12月28日
	 * By 胡体勇
	 * @param map
	 * @return
	 */
	public  List<Attachment> batchDownload(Map<String,Object> map){
		// 设置查询参数
		map.put("attaFlag", FileConstants.FILE_TYPE_SR);
		map.put("isDiscard", EffectiveFlag.yx.value);
		return super.dao.getAttachment(map);
	}
	
	/**
	 * 统计页面显示总金额
	 * 2016年1月13日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public CreditorTradeEx countMoney(CreditorTradeEx creditorTradeEx){
		/*// 转换城市的查询条件
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(creditorTradeEx.getAddrCity())){
		    String[] str = creditorTradeEx.getAddrCity().split(",");
		    for(int i=0;i<str.length;i++){
		    	String[] s = str[i].split("_",Constant.SIZE);
		    	list.add(s[1]);
		    }
		    creditorTradeEx.setCity(list);
		}*/
		// 省市
				String city = creditorTradeEx.getAddrCity();
				if (!StringUtils.isEmpty(city)) {
					String c = "%" + city.replace(",", "%|%") +"%";
					  creditorTradeEx.setCity(c);
				}
		// 转换产品查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getProduct())){
			String[] str = creditorTradeEx.getProduct().split(",");
			creditorTradeEx.setProductCode(Arrays.asList(str));
		}
		// 转换营业部查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getOrg())){
			String[] str = creditorTradeEx.getOrg().split(",");
			creditorTradeEx.setOrgCode(Arrays.asList(str));
		}
		creditorTradeEx.setMatchingStatus(MatchingStatus.YTJ.value);
		return super.dao.countMoney(creditorTradeEx);
	}
	
	/**
	 * 获取账单收取方式
	 * 2016年7月15日
	 * By 韩龙
	 * @param customerCode
	 * @return
	 */
	public Map<String,String> getCollectionMethod(String lendCode){
		return super.dao.getCollectionMethod(lendCode);
	}
}
