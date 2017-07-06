package com.creditharmony.fortune.deduct.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.CASignType;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.MailTemplateType;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;
import com.creditharmony.fortune.mail.dao.EmailInfoDao;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.sms.dao.SmsDao;
import com.creditharmony.fortune.utils.FileUtil;

/**
 * 划扣业务Service
 * 
 * @author 韩龙
 * @Create In 2015年11月20日
 * @version 3.0
 */
@Service
public class DeductFileManager extends CoreManager<DeductApplyDao, DeductPool> {

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
	
	/**
	 * 批量发送债权文件 2015年11月27日 By 韩龙
	 * 
	 * @param applyCodes
	 * @param sendName
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int batchSendFile(String[] applyCodes, String sendName) {
		int result = 0;
		for (String code : applyCodes) {
			// 查询出第个出借的债权文件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applyCode", code);
			map.put("matchingFirstdayFlag", BillState.SQ.value);
			map.put("emailType", MailTemplateType.SQ.value);
			map.put("dictAttaFlag", FileConstants.FILE_TYPE_SR);
			map.put("attaFileType", "doc");
			AttachmentEx attachment = super.dao.getAttachmentEx(map);
			// 过虑债权文件发送制作失败和制作中
			if (attachment != null) {
				if (StringUtils.isNotEmpty(attachment.getMatchingFileStatus())
						&& !FilecpState.HCCG.value.equals(attachment
								.getMatchingFileStatus())) {
					logger.info("DeductApplyService.batchSendFile-->首期债权制作失败-->出借编码："
							+ code);
					continue;
				}
//				String templateContent = attachment.getTemplateContent();
				// EmailInfo对象
				EmailInfo emailInfo = new EmailInfo();
				emailInfo.preInsert();
				emailInfo.setLendCode(code);
				emailInfo.setEmailTemplateId(Constant.getProperties
						.get("emailTemplateId"));
				emailInfo.setEmailSender(sendName);
				emailInfo.setEmailSenderTime(new Date());
				emailInfo.setEmailRecpEmail(attachment.getCustEmail());
				emailInfo.setEmailRecpName(attachment.getCustName());
				emailInfo.setEmailSubject(Constant.getProperties
						.get("mailTheme"));
				emailInfo.setEmailMsg(attachment.getTemplateContent());
				emailInfo.setAttachmentPath(attachment.getAttaFilepath());
				emailInfo.setEmailSendStatus(EmailState.DFS.value);
				emailInfo.setEmailType(BillState.SQ.value);
				emailInfo.setEmailSendId(emailInfo.getId());
				emailInfoDao.insert(emailInfo);
				// 全程流痕
//				checkManager.addCheck(code, "批量发送债权文件", "向邮件待发送表插入数据");
				checkManager.addCheck(code, "批量发送债权文件", "向邮件待发送表插入数据",
						code, FortuneLogNode.DEDUCT_BALANCE);
				result++;
			}
		}
		return result;
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
				LoanApply apply=getCustCodeByApplyCode(code);
				mc.setCustomerCode(apply.getCustCode());
				filters.put("attaTableId", mc.getMatchingId());
				filters.put("lendCode", code);
				filters.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
				filters.put("fileName", getByLendCode(code));
				filters.put("parameter", "matching_id=" + mc.getMatchingId());
				filters.put("customerCode", mc.getCustomerCode());
				filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
				if (mc.getDictMatchingFileStatus() != null
						&& mc.getDictMatchingFileStatus().equals(
								FilecpState.HCCG.value)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("lendCode", code);
					map.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
					map.put("discard", EffectiveFlag.yx.value);
					map.put("isDiscard", EffectiveFlag.wx.value);
					attachmentDao.updateSelectiveByIds(map);
				}
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
	public String getByLendCode(String lendCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lendCode", lendCode);
		Map<String, Object> pmap = dao.getByLendCode(map);
		String fileName = pmap.get("orgname") + "_收款确认书_划扣_"
				+ pmap.get("customer_name");
		return fileName;
	}

	/**
	 * 发送收款确认书
	 * 2016年3月29日
	 * By 韩龙
	 * @param lendCode
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String sendEmailFile(String lendCode,String type) {
		//收款确认书
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyCode", lendCode);
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("emailType", MailTemplateType.SKQRS.value);
		map.put("dictAttaFlag", FileConstants.FILE_TYPE_SKQR);
		map.put("attaFileType", type);
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
			 // TODO 邮件内容没有替换
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
			emailInfo.setEmailSubject(attachment.getTemplateName());
			emailInfo.setEmailMsg(templateContent);
			emailInfo.setAttachmentPath(attachment.getAttaFilepath());
			emailInfo.setEmailSendStatus(EmailState.DFS.value);
			emailInfo.setEmailType(BillState.SQ.value);
			emailInfo.setEmailSendId(emailInfo.getId());
			emailInfo.setPdfType(type);
			emailInfoDao.insert(emailInfo);
			// 全程流痕
			checkManager.addCheck(lendCode, "批量发送收款确认书文件",
					"向邮件待发送表插入数据",lendCode,FortuneLogNode.DEDUCT_SUCCESS);
		}else{
			return "出借编号【"+lendCode+"】"+"收款确认书发送失败;收款确认书没有生成。";
		}
		return null;
	}
	
	/**
	 * 制作债权文件
	 * 2016年2月2日
	 * By 韩龙
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public void makeFile(String lendCode){
		Map<String, Object> filters = new HashMap<String,Object>();
		MatchingCreditorEx mc = new MatchingCreditorEx();
		mc.setLendCode(lendCode);
		// 首期标识
		mc.setMatchingFirstdayFlag(BillState.SQ.value);
		mc = matchingCreditorDao.get(mc);
		LoanApply apply=getCustCodeByApplyCode(lendCode);
		mc.setCustomerCode(apply.getCustCode());
		filters.put("attaTableId", mc.getMatchingId());
		filters.put("lendCode", lendCode);
		filters.put("attaFlag", FileConstants.FILE_TYPE_SKQR);
		filters.put("parameter", "matching_id="+mc.getMatchingId());
		filters.put("customerCode", mc.getCustomerCode());
		filters.put("signType", CASignType.PAYMENTCONFIRMATION.value);
		// 合成文件
		CERequestContext cERequestContext = CEContextHelper.login(
				Constant.getProperties.get("ceuser"),
				Constant.getProperties.get("cepwd"));
		// ce认证
		filters.put(CERequestContext.DM_FILENET_CONTEXT, cERequestContext);
		FileUtil.compositeFile(filters);
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
