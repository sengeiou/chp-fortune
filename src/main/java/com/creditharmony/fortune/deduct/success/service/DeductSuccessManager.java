package com.creditharmony.fortune.deduct.success.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.common.type.SmsState;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.BilltakenMode;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;
import com.creditharmony.fortune.deduct.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.manager.EmailInfoManager;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.sms.entity.SmsSendList;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.template.entity.DeductSuccessExportGoldModel;
import com.creditharmony.fortune.template.entity.DeductSuccessExportModel;
import com.creditharmony.fortune.template.entity.DeductSuccessPayExportModel;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.MailUtil;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductSuccessManager extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private CheckManager checkManager;

	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private SmsDao smsDao;

	@Autowired
	private EmailInfoDao emailInfoDao;

	@Autowired
	private CreditorSendDao creditorSendDao;
	
	@Autowired
	private EmailInfoManager emailInfoManager;
	/**
	 * 办理详细 2015年12月24日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public DeductPoolEx conduct(Map<String, Object> map) {
		// 检索债权价值信息
		// Map<String,Object>map=new HashMap<String,Object>();
		// map.put("applyCode", applyCode);
		String applyCode = (String) map.get("applyCode");
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		DeductPoolEx deductPoolExt = super.dao.getDeductPoolExt(map);
		if (deductPoolExt == null) {
			return new DeductPoolEx();
		}
		// 债权推荐列表 creditNode　债权交易表
		List<CreditorTradeEx> creditorTradeExtList = super.dao
				.getListOfClaims(applyCode);

		// 预借出借收益列表查询待推荐债权表
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(applyCode);
		matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
		Map<String, Object> loanphaseMap = new HashMap<String, Object>();
		loanphaseMap.put("matchingId", matchingCreditorEx.getMatchingId());
		loanphaseMap.put("lendCode", matchingCreditorEx.getLendCode());
		Map<String, Object> loanphase = super.dao.getLoanphase(loanphaseMap);
		if(loanphase != null){
			deductPoolExt.setNextBorrowMoney(StringUtils.toString(loanphase
					.get("phaseamount")));
			String sumMoney = new BigDecimal(DeductUtils.isNullMoney(deductPoolExt.getApplyDeductMoney()))
					.add(new BigDecimal(StringUtils.toString(loanphase
							.get("asphaseinterestcur")))).toString();
			deductPoolExt.setNextSumMoney(sumMoney);
		}else{
			// 首期提前赎回收益为0
			deductPoolExt.setNextBorrowMoney("0");
			deductPoolExt.setNextSumMoney("0");
		}
		// deductPoolExt.setNextBillDate(StringUtils.toString(loanphase.get("billDay")));
		// 根据状态：债权节点；债权池；月满盈可用债权池
		for (CreditorTradeEx creditorTradeExt : creditorTradeExtList) {
			if (creditorTradeExt != null
					&& Node.KYZQ.value.equals(creditorTradeExt
							.getCreditNode())) {
				if (creditorTradeExt.getTradeId() != null) {
					// 检索债权节点；债权池表
					CreditorTradeEx cext = super.dao.getBorrow(creditorTradeExt
							.getCreditCode());
					if (cext != null) {
						// 设置属性
						creditorTradeExt
								.setBorrowerName(cext.getBorrowerName());
						creditorTradeExt.setBorrowerIdcard(cext
								.getBorrowerIdcard());
						creditorTradeExt.setBorrowerJob(cext.getBorrowerJob());
						creditorTradeExt.setBorrowPurpose(cext
								.getBorrowPurpose());
						creditorTradeExt.setBorrowBackmoneyFirday(cext
								.getBorrowBackmoneyFirday());
						creditorTradeExt
								.setBorrowMonths(cext.getBorrowMonths());
						creditorTradeExt.setBorrowMonthsSurplus(cext
								.getBorrowMonthsSurplus());
						creditorTradeExt.setBorrowValueYear(cext
								.getBorrowValueYear());
						creditorTradeExt.setBorrowCreditValue(cext
								.getBorrowCreditValue());
						continue;
					}
				}
			}
			CreditorTradeEx cext = super.dao
					.getBorrowMonthable(creditorTradeExt.getCreditCode());
			if (cext != null) {
				// 设置属性
				creditorTradeExt.setBorrowerName(cext.getBorrowerName());
				creditorTradeExt.setBorrowerIdcard(cext.getBorrowerIdcard());
				creditorTradeExt.setBorrowerJob(cext.getBorrowerJob());
				creditorTradeExt.setBorrowPurpose(cext.getBorrowPurpose());
				creditorTradeExt.setBorrowBackmoneyFirday(cext
						.getBorrowBackmoneyFirday());
				creditorTradeExt.setBorrowDays(cext.getBorrowDays());
				creditorTradeExt.setBorrowAvailableDays(cext
						.getBorrowAvailableDays());
				creditorTradeExt.setBorrowValueYear(cext.getBorrowValueYear());
				creditorTradeExt.setBorrowCreditValue(cext
						.getBorrowCreditValue());
				// creditorTradeExt.setBorrowValueYear(yearRate(cext,CreditNode.YMYKYZQC.value));
			}
		}
		// 债权推荐列表
		deductPoolExt.setCreditorTradeExtList(creditorTradeExtList);
		return deductPoolExt;
	}

	/**
	 * 获取一批次出借对应的收款确认书列表 2015年12月9日 By 韩龙
	 * 
	 * @param map
	 * @return
	 */
	public List<Attachment> getAttachment(Map<String, Object> map) {
		return super.dao.getAttachment(map);
	}

	/**
	 * 已出借列表-->划扣已出借列表导出 2015年12月23日 By 韩龙
	 * 
	 * @param filter
	 * @return
	 */
	public List<DeductSuccessExportModel> getDeductSuccessExportModel(
			Map<String, Object> filter) {
		// 参数是否为空
		if (filter.get("ids") != null) {
			String ids = filter.get("ids").toString();
			// 设置参数集合
			filter.put("lendCodes", ids.split(","));
		}
		return super.dao.getDeductSuccessExportModel(filter);
	}

	/**
	 * 已划扣回访列表导出 2015年12月24日 By 韩龙
	 * 
	 * @param filter
	 * @return
	 */
	public List<DeductSuccessPayExportModel> getDeductSuccessPayExportModel(
			Map<String, Object> filter) {
		// 参数是否为空
		if (filter.get("ids") != null) {
			String ids = filter.get("ids").toString();
			// 设置参数集合
			filter.put("lendCodes", ids.split(","));
		}
		List<DeductSuccessPayExportModel> list = super.dao
				.getDeductSuccessPayExportModel(filter);
		if (list == null) {
			return Lists.newArrayList();
		}
		return list;
	}

	/**
	 * 合成收款确认书 2015年12月26日 By 韩龙
	 * 
	 * @param filters
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false,
			propagation=Propagation.REQUIRES_NEW)
	public int compositeFile(Map<String, Object> filters) {
		int result = 0;
		// 获取出借编号
		String applyCode = StringUtils.toString(filters.get("applyCode"));
		if (applyCode != null) {
			String[] applyCodes = applyCode.split(",");
			// 根据出借编号与首期查出待推荐债权表的id
			for (String code : applyCodes) {
				MatchingCreditorEx mc = new MatchingCreditorEx();
				// mc.setMatchingId(code);
				mc.setLendCode(code);
			
				// 首期标识
				mc.setMatchingFirstdayFlag(BillState.SQ.value);
				mc = matchingCreditorDao.get(mc);
				LoanApply apply = getCustCodeByApplyCode(code);
				mc.setCustomerCode(apply.getCustCode());
				// 制作内部转账收款确认书
				if(apply.getPayType().equals(PayMent.NBZZ.value)){
					filters.put("templateName",ReportType.SKQRS_NBZZ.getCode());
					filters.put("fileName", getByLendCode(code,ReportType.SKQRS_NBZZ.getName()));
				}else{
					// 制作划扣收款确认书
					filters.put("fileName", getByLendCode(code,ReportType.SKQRS_HK.getName()));
				}
				filters.put("attaTableId", mc.getMatchingId());
				filters.put("lendCode", code);
				filters.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
//				filters.put("fileName", getByLendCode(code));
				filters.put("parameter", "matching_id=" + mc.getMatchingId());
				filters.put("customerCode", mc.getCustomerCode());
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
//				filters.put("sendFlag", com.creditharmony.fortune.creditor.matching
//						.constant.Constant.SEND_FLAG_YES);
				filters.put("templateType",MailTemplateType.SKQRS.value);
//				if (mc.getDictMatchingFileStatus() != null
//						&& mc.getDictMatchingFileStatus().equals(
//								FilecpState.HCCG.value)) {
				// 把生成的修改为废弃重新合成
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("lendCode", code);
				map.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
				map.put("discard", EffectiveFlag.yx.value);
				map.put("isDiscard", EffectiveFlag.wx.value);
				attachmentDao.updateSelectiveByIds(map);
//				}
				// 合成文件
				FileUtil.compositeFile(filters);
				result++;
			}
		}
		return result;
	}

	/**
	 * 获取收款确认书 2016年2月27日 By 韩龙
	 * 
	 * @param lendCode
	 * @return
	 */
	public String getByLendCode(String lendCode,String label) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		Map<String, Object> pmap = dao.getByLendCode(map);
		String fileName = pmap.get("orgname") + label
				+ pmap.get("customer_name");
		return fileName;
	}
	
	/**
	 * 发送短信 2016年2月2日 By 韩龙
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false,
			propagation=Propagation.REQUIRES_NEW)
	private void sendSms(String lendCode, String deductStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		SmsSendList smsSendList = smsDao.getSmsSend(map);
		smsSendList.setId(IdGen.uuid());
		smsSendList.setBankName(OpenBank.getOpenBank(smsSendList
				.getAccountBank()));
		String type = "";
		if (DeductState.HKCG.value.equals(deductStatus)) {
			type = SmsType.HKCG.value;
			smsSendList.setDictDeductStatus(DeductState.HKCG.value);
		} else if (DeductState.HKSB.value.equals(deductStatus)) {
			type = SmsType.HKSB.value;
			smsSendList.setDictDeductStatus(DeductState.HKSB.value);
		} else {
			// type = SmsType.b.value;
		}
		SmsTemplate smsTemplate = smsDao.getSmsTemplate(type);
		smsSendList.setSmsId(smsTemplate.getTemplateCode());
		String templateContent = smsTemplate.getTemplateContent();
		templateContent = templateContent.replace("{#Name#}",
				smsSendList.getCustomerName());
		templateContent = templateContent.replace("{#custom_text_4#}",
				smsSendList.getLendMoney().toString());
		smsSendList.setSmsMsg(templateContent);
		smsSendList.setSendStatus(SmsState.DFS.value);
		// smsSendList.setOnlyFlag(IdGen.uuid());
		// smsSendList.设置短信待发送状态
		smsDao.insert(smsSendList);
		// 更新待推荐债权表中的文件发送状态
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setLendCode(lendCode);
		matchingCreditorEx.setMatchingFirstdayFlag(BillState.SQ.value);
		matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
		matchingCreditorEx.setDictMatchingFilesendStatus("3");// 已发送
		matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
	}

	/**
	 * 导出金帐户 2016年3月14日 By 韩龙
	 * 
	 * @param filters
	 * @return
	 */
	public List<DeductSuccessExportGoldModel> getDeductSuccessExportGoldModel(
			Map<String, Object> filter) {
		// 参数是否为空
		if (filter.get("ids") != null) {
			String ids = filter.get("ids").toString();
			// 设置参数集合
			filter.put("lendCodes", ids.split(","));
		}
		return super.dao.getDeductSuccessExportGoldModel(filter);
	}

	/**
	 * 发送收款确认书--插入待发送列表
	 * 2016年3月29日
	 * By 韩龙
	 * @param lendCode
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String sendEmailFile(String lendCode,String type) {
		// 收款确认书
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", lendCode);
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("emailType", MailTemplateType.SKQRS.value);
		map.put("dictAttaFlag", FileConstants.FILE_TYPE_SKQR);
		map.put("attaFileType", type);
		map.put("isDiscard", EffectiveFlag.yx.value);
		AttachmentEx attachment = super.dao.getAttachmentEx(map);
		// 过虑债权文件发送制作失败和制作中
		if (attachment != null) {
			if (StringUtils.isNotEmpty(attachment.getMatchingFileStatus())
					&& !FilecpState.HCCG.value.equals(attachment
							.getMatchingFileStatus())) {
				logger.info("DeductApplyService.batchSendFile-->收款确认书失败-->出借编码："
						+ lendCode);
				return "收款确认书发送失败出借编号【"+lendCode+"】";
			}
			 String templateContent = attachment.getTemplateContent();
			// EmailInfo对象
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.preInsert();
			emailInfo.setLendCode(lendCode);
			emailInfo.setEmailTemplateId(attachment.getId());
			emailInfo.setEmailSender(UserUtils.getUser(UserUtils.getUserId()).getName());
			emailInfo.setEmailSenderTime(new Date());
			emailInfo.setEmailRecpEmail(attachment.getCustEmail());
			emailInfo.setEmailRecpName(attachment.getCustName());
//			emailInfo.setEmailSubject(Constant.getProperties.get("mailTheme"));
			emailInfo.setEmailSubject(attachment.getMailTheme());
//			emailInfo.setEmailSubject(templateContent);
			emailInfo.setEmailMsg(templateContent);
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfo.setEmailType(BillState.SQ.value);
			emailInfo.setEmailSendId(emailInfo.getId());
			emailInfo.setPdfType(type);
			emailInfoDao.insert(emailInfo);
			// 更新划扣表发送收款确认书状态
			DeductPool dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setSendEmailStatus(EmailState.FSCG.value);
			super.dao.update(dp);
			// 全程流痕
			checkManager.addCheck(lendCode, "批量发送收款确认书文件",
					"向邮件待发送表插入数据",lendCode,FortuneLogNode.DEDUCT_SUCCESS);
		}else{
			return "出借编号【"+lendCode+"】"+"收款确认书发送失败;收款确认书没有生成。";
		}
		return null;
	}
	
	/**
	 * 发送收款确认书--实时发送
	 * 2016年3月29日
	 * By 韩龙
	 * @param lendCode
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String sendEmailFileConstantly(String lendCode,String type) {
		Map<String,String> billMap = creditorSendDao.getCollectionMethod(lendCode);
		if(billMap!= null && BilltakenMode.XJ.value.equals(billMap.get("loan_bill_recv"))){
			// 更新划扣表发送收款确认书状态
			DeductPool dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setSendEmailStatus(EmailState.WFS.value);
			super.dao.update(dp);
			return "出借编号【"+lendCode+"】收款确认书发送失败,失败原因为:获取账单收取方式是【信件】";
		}
		// 收款确认书
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", lendCode);
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("emailType", MailTemplateType.SKQRS.value);
		map.put("dictAttaFlag", FileConstants.FILE_TYPE_SKQR);
		map.put("attaFileType", type);
		map.put("isDiscard", EffectiveFlag.yx.value);
		AttachmentEx attachment = super.dao.getAttachmentEx(map);
		// 过虑债权文件发送制作失败和制作中
		if (attachment != null) {
			if (StringUtils.isNotEmpty(attachment.getMakeFlieStatus())
					&& !FilecpState.HCCG.value.equals(attachment
							.getMakeFlieStatus())) {
				logger.info("DeductApplyService.batchSendFile-->收款确认书失败-->出借编码："
						+ lendCode);
				return "收款确认书发送失败出借编号【"+lendCode+"】";
			}
			// 邮件内容
			String templateContent = attachment.getTemplateContent();
			MailUtil.sendMail(attachment.getCustEmail(), attachment.getAttaFilepath(), attachment.getMailTheme(), templateContent);
			// 删除email表中待发送状态的记录
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfoManager.delete(emailInfo);
			// 更新划扣表发送收款确认书状态
			DeductPool dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setSendEmailStatus(EmailState.FSCG.value);
			super.dao.update(dp);
			// 全程流痕
			checkManager.addCheck(lendCode, "批量发送收款确认书文件",
					"向邮件待发送表插入数据",lendCode,FortuneLogNode.DEDUCT_SUCCESS);
		}else{
			return "出借编号【"+lendCode+"】"+"收款确认书发送失败;收款确认书没有生成。";
		}
		return null;
	}
	
	/**
	 * 更据出借编号获取客户编号
	 * 2016年4月8日
	 * By 郭才林
	 * @param applyCode
	 * @return
	 */
	public LoanApply getCustCodeByApplyCode(String applyCode){
	  return dao.getCustCodeByApplyCode(applyCode);
	}
}
