package com.creditharmony.fortune.deduct.apply.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.BilltakenMode;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.deduct.common.DeductUtils.SendAttachmentFileType;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.manager.EmailInfoManager;
import com.creditharmony.fortune.utils.MailUtil;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductApplyManager extends CoreManager<DeductApplyDao, DeductPool> {

	@Autowired
	private CheckManager checkManager;

	@Autowired
	private MatchingCreditorDao matchingCreditorDao;

	@Autowired
	private EmailInfoDao emailInfoDao;
	
	@Autowired
	private CreditorSendDao creditorSendDao;
	
	@Autowired
	private EmailInfoManager emailInfoManager;
	
	/**
	 * 根据条件查询
	 * 2016年4月19日
	 * By 韩龙
	 * @param deductPoolEx
	 * @return
	 */
	public List<DeductPoolEx> findList(DeductPoolEx deductPoolEx){
		return dao.findList(deductPoolEx);
	}

	/**
	 * 发送首期债权文件--插入邮件待发送列表
	 * 2015年11月27日 
	 * By 韩龙
	 * @param applyCodes
	 * @param sendName
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int batchSendFile(String applyCodes, String sendName) {
		int result = 0;
		// 查询出第个出借的债权文件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", applyCodes);
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("emailType", MailTemplateType.SQ.value);
		map.put("dictAttaFlag", FileConstants.FILE_TYPE_SR);
		map.put("attaFileType", SendAttachmentFileType.PDF.value);
		map.put("isDiscard", EffectiveFlag.yx.value);
		AttachmentEx attachment = super.dao.getAttachmentEx(map);
		// 过虑债权文件发送制作失败和制作中
		if (attachment != null) {
			if (StringUtils.isNotEmpty(attachment.getMatchingFileStatus())
					&& !FilecpState.HCCG.value.equals(attachment
							.getMatchingFileStatus())) {
				logger.info("DeductApplyService.batchSendFile-->首期债权制作失败-->出借编码："
						+ applyCodes);
				return result;
			}
			String templateContent = attachment.getTemplateContent();
			templateContent = templateContent.replace("time", attachment.getApplyDeductDay());
			// EmailInfo对象
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.preInsert();
			emailInfo.setLendCode(applyCodes);
			emailInfo.setEmailTemplateId(attachment.getId());
			emailInfo.setEmailSender(sendName);
			emailInfo.setEmailSenderTime(new Date());
			emailInfo.setEmailRecpEmail(attachment.getCustEmail());
			emailInfo.setEmailRecpName(attachment.getCustName());
			emailInfo.setEmailSubject(attachment.getMailTheme());
			emailInfo.setEmailMsg(templateContent);
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfo.setEmailType(BillState.SQ.value);
			emailInfo.setEmailSendId(IdGen.uuid());
			emailInfo.setMatchingBillDay(attachment.getMatchingBillDay());
			emailInfo.setCreditId(attachment.getAttaTableId());
			emailInfo.setPdfType(SendAttachmentFileType.PDF.value);
			emailInfoDao.insert(emailInfo);
			MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			matchingCreditorEx.setMatchingId(attachment.getAttaTableId());
			matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			matchingCreditorEx.setDictMatchingFilesendStatus(EmailState.FSCG.value);
			matchingCreditorEx.setModifyTime(new Date());
			matchingCreditorEx.setModifyBy(UserUtils.getUser(UserUtils.getUserId()).getName());
			matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
			// 全程流痕
//			checkManager.addCheck(applyCodes, "批量发送债权文件", "向邮件待发送表插入数据");
			checkManager.addCheck(applyCodes, "批量发送债权文件", "向邮件待发送表插入数据",
					applyCodes,FortuneLogNode.DEDUCT_APPLY);
			
			result++;
		}
		return result;
	}
	
	/**
	 * 发送首期债权文件--实时发送
	 * 2015年11月27日 
	 * By 韩龙
	 * @param applyCodes
	 * @param sendName
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int batchSendFileConstantly(String applyCodes, String sendName) {
		int result = 0;
		// 获取账单收取方式
		Map<String,String> billMap = creditorSendDao.getCollectionMethod(applyCodes);
		if(billMap!= null && BilltakenMode.XJ.value.equals(billMap.get("loan_bill_recv"))){
			return result++;
		}
		// 查询出第个出借的债权文件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", applyCodes);
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("emailType", MailTemplateType.SQ.value);
		map.put("dictAttaFlag", FileConstants.FILE_TYPE_SR);
		map.put("attaFileType", SendAttachmentFileType.PDF.value);
		map.put("isDiscard", EffectiveFlag.yx.value);
		AttachmentEx attachment = super.dao.getAttachmentEx(map);
		// 过虑债权文件发送制作失败和制作中
		if (attachment != null) {
			if (StringUtils.isNotEmpty(attachment.getMatchingFileStatus())
					&& !FilecpState.HCCG.value.equals(attachment
							.getMatchingFileStatus())) {
				logger.info("DeductApplyService.batchSendFile-->首期债权制作失败-->出借编码："
						+ applyCodes);
				return result;
			}
			// 组装邮件内容
			String templateContent = attachment.getTemplateContent();
			templateContent = templateContent.replace("time", attachment.getApplyDeductDay());
			// 调邮件发送
			MailUtil.sendMail(attachment.getCustEmail(), attachment.getAttaFilepath(), 
					attachment.getMailTheme(), templateContent);
			// 删除email表中待发送状态的记录
			EmailInfo emailInfo = new EmailInfo();
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfoManager.delete(emailInfo);
			// 更新待推荐状态
			MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
			matchingCreditorEx.setMatchingId(attachment.getAttaTableId());
			matchingCreditorEx = matchingCreditorDao.get(matchingCreditorEx);
			matchingCreditorEx.setDictMatchingFilesendStatus(EmailState.FSCG.value);
			matchingCreditorEx.setModifyTime(new Date());
			matchingCreditorEx.setModifyBy(UserUtils.getUser(UserUtils.getUserId()).getName());
			matchingCreditorDao.updateByPrimaryKeySelective(matchingCreditorEx);
			// 全程流痕
//			checkManager.addCheck(applyCodes, "批量发送债权文件", "向邮件待发送表插入数据");
			checkManager.addCheck(applyCodes, "批量发送债权文件", "向邮件待发送表插入数据",
					applyCodes,FortuneLogNode.DEDUCT_APPLY);
			
			result++;
		}
		return result;
	}

	/**
	 * 批量划扣状态修改 2015年12月7日 By 韩龙
	 * 
	 * @param applyCodes
	 * @param deductFalg
	 * @param batchId
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void batchDeductApplyUpdate(DeductPool dp) {
		logger.info("批量划扣申请-------开始");
		dp.preUpdate();
		int updatedp = dao.update(dp);
		if(updatedp==0){
			logger.debug("该批量数据更新出错，请详查个别数据是否已有人操作，请勿重复！");
			throw new ServiceException("该批量数据更新出错，请详查个别数据是否已有人操作，请勿重复！");
		}
		String content = DeductState.getDeductState(DeductState.DHKSQ.value);
		// 全程流痕
//		checkManager.addCheck(dp.getApplyCode(),"批量"+content, content);
		checkManager.addCheck(dp.getApplyCode(),"批量"+content, content,dp.getApplyCode(),FortuneLogNode.DEDUCT_APPROVE);
		logger.info("批量划扣申请-------结束");
	}
	
}
