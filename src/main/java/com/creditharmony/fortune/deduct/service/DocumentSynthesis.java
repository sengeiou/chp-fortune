package com.creditharmony.fortune.deduct.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.fortune.type.BilltakenMode;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.CallInterface;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorSendDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.deduct.dao.DeductApplyDao;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.utils.FileUtil;

/**
 * 文件合成回调实现类
 * @Class Name DocumentSynthesis
 * @author 韩龙
 * @Create In 2015年12月29日
 */
public class DocumentSynthesis implements CallInterface {
	
	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(DocumentSynthesis.class);
	// 附件表
	private List<Attachment> attachmentLst;
	//
	private String attaTableId;
	// 废弃附件用
	Attachment discardAttachment;
	// 仅合成文件 不重新生成邮件
	private String isOnlyComposite;
	// 附件表dao
	private AttachmentDao attachmentDao = SpringContextHolder
			.getBean(AttachmentDao.class);
	// 待推荐债权dao
	private MatchingCreditorDao matchingCreditorDao =  SpringContextHolder
			.getBean(MatchingCreditorDao.class);
	// 待推荐债权dao
	private MatchingCreditorManager matchingCreditorManager =  SpringContextHolder
			.getBean(MatchingCreditorManager.class);
	
	private DeductApplyDao deductApplyDao = SpringContextHolder.getBean(DeductApplyDao.class);
	
	private CreditorSendDao creditorSendDao = SpringContextHolder.getBean(CreditorSendDao.class);
		
	/**
	 * 构造方法
	 * @param attachment
	 */
	public DocumentSynthesis(List<Attachment>  attachmentLst,String attaTableId,Attachment discardAttachment,String isOnlyComposite){
		logger.debug("初始化构造方法");
		this.attachmentLst=attachmentLst;
		this.attaTableId = attaTableId;
		this.discardAttachment = discardAttachment;
		this.isOnlyComposite = isOnlyComposite;
	}
	
	/**
	 * 回调方法
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public void method(Map<String, Object> map) {
		logger.debug("制作文件回调方法----------开始");
		boolean b = (Boolean) map.get("bool");
		List<String> docId = (List<String>) map.get("docId");
		// 制作文件类型
		String fileType = FileConstants.FILE_TYPE_SKQR;
		
		boolean sendFlag = false; // 文件发送标志
		
		if (b && docId != null && docId.size()==2 && attachmentLst!=null && attachmentLst.size()==2  ) {
			logger.debug("制作文件成功");
			// 如果是文件合成的话
			if (this.discardAttachment != null) {
				List<Attachment> attachmentLst =  attachmentDao.findList(this.discardAttachment);
				// 如果有值 直接设置为废弃
				if(attachmentLst!=null && attachmentLst.size()>0){
					List<String> attIdLst= new ArrayList<String>();
					for(Attachment att :attachmentLst){
						attIdLst.add(att.getAttaId());
					}
					Map<String,Object> param = new HashMap<String, Object>();
					param.put("isDiscard", EffectiveFlag.wx.value);
					param.put("ids", attIdLst);
					attachmentDao.updateSelectiveByIds(param);
				}				
			}
			
			for(int i = 0; i<attachmentLst.size();i++){
				Attachment attachment = attachmentLst.get(i);
				logger.debug("制作文件成功,循环发送,附件内容为：" + attachment.toString());
				// 文件路径(是方法CE系统文档唯一ID)
				attachment.setAttaFilepath(docId.get(i));
				attachment.setIsDiscard(EffectiveFlag.yx.value);
				// 获取制作文件类型
				fileType = attachment.getDictAttaFlag();
				// 保存附件
				attachmentDao.insert(attachment);
				if(!StringUtils.isEmpty(attachment.getSendFlag())){ //文件发送
					if("pdf".equals(attachment.getAttaFileType())){
						logger.debug("文件类型为pdf,出借编号【"+attachment.getLoanCode()+"】");
						if(StringUtils.isNotEmpty(this.isOnlyComposite) 
								&& Constant.FILE_MAKE_FROM_HC.equals(this.isOnlyComposite)){
							logger.debug("仅合成文件 不重新生成邮件,出借编号【"+attachment.getLoanCode()+"】");
							// 只合成文件，更新既有的未发送邮件
							int uptCnt = matchingCreditorManager.updateAttachmentFilePath(attachment);
							if(uptCnt > 0){
								sendFlag = true;
							}
						}else{
							logger.info("合成文件，并发送邮件 开始");
							logger.debug("合成文件，并发送邮件,出借编号【"+attachment.getLoanCode()+"】");
							logger.info("合成文件，并发送邮件,出借编号【"+attachment.getLoanCode()+"】");
							int  insertNum = 0;
							try {
								// 获取账单收取方式
								Map<String,String> billMap = creditorSendDao.getCollectionMethod(attachment.getLoanCode());
								logger.info("billMap="+billMap+";billMap.get('loan_bill_recv')="+billMap.get("loan_bill_recv"));
								if(billMap!= null && BilltakenMode.XJ.value.equals(billMap.get("loan_bill_recv"))){
									logger.info("billMap!= null出借编号【"+attachment.getLoanCode()+"】合成文件，"
											+ "获取账单收取方式为信件方式，不插入并邮件发送表");
									logger.debug("出借编号【"+attachment.getLoanCode()+"】合成文件，"
											+ "获取账单收取方式为信件方式，不插入并邮件发送表");
								}else{
									logger.info("matchingCreditorManager.sendEamil 开始");
									logger.info("attachment.getLoanCode()="+attachment.getLoanCode()+";attachment.getCreateBy()="+attachment.getCreateBy()+";attachment.getDictAttaFlag()="+attachment.getDictAttaFlag()+";attachment.getTemplateType()="+attachment.getTemplateType()+";attachment.getAttaTableId()="+attachment.getAttaTableId());
									insertNum = matchingCreditorManager.sendEamil(attachment.getLoanCode(), "pdf",attachment.getCreateBy() , attachment.getDictAttaFlag(),attachment.getTemplateType(),attachment.getAttaTableId());
									logger.info("insertNum="+insertNum);
									logger.info("matchingCreditorManager.sendEamil 结束");
								}
							} catch (Exception e) {
								FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, attachment.getLoanCode(),
										FortuneLogNode.MATCHING_CREDITOR.getCode(),					
										FortuneLogLevel.YELLOW.value, "制作文件成功，但插入邮件发送表失败"));
								logger.info("制作文件成功，但插入邮件发送表失败,出借编号【"+attachment.getLoanCode()+"】,"
										+ "失败信息："+e.getMessage(),e);
								logger.error("制作文件成功，但插入邮件发送表失败,出借编号【"+attachment.getLoanCode()+"】,"
										+ "失败信息："+e.getMessage(),e);
							}
							if(insertNum!=0){
								sendFlag = true;
							}
							logger.info("合成文件，并发送邮件 结束");
						}
						
					}
						
				}
			}
			
			if (StringUtils.isNotEmpty(attaTableId)) {
				// 获取待推荐债权表
				MatchingCreditorEx mc = matchingCreditorDao.selectByPrimaryKey(attaTableId);
				if(fileType.equals(FileConstants.FILE_TYPE_SKQR)){
					logger.debug("模板类型:收款确认书,出借编号【"+mc.getLendCode()+"】,更新划扣申请实体");
					DeductPool dp = new DeductPool();
					dp.setApplyCode(mc.getLendCode());
					// 设置文件书款确认合成成功
					dp.setMakeFlieStatus(FilecpState.HCCG.value);
					if(sendFlag){
						logger.debug("模板类型:收款确认书,出借编号【"+mc.getLendCode()+"】，发送状态为待发送");
						dp.setSendEmailStatus(EmailState.DFS.value);
					}else{
						logger.debug("模板类型:收款确认书,出借编号【"+mc.getLendCode()+"】，发送状态为未发送");
						dp.setSendEmailStatus(EmailState.WFS.value);
					}
					// 更新划扣申请表合成收款确认书状态
					deductApplyDao.update(dp);
				}else{
					logger.debug("出借编号【"+mc.getLendCode()+"】;模板类型:债权协议，更新待推荐表发送状态，制作文件状态");
					// 设置文件合成成功
					mc.setDictMatchingFileStatus(FilecpState.HCCG.value);
					if(sendFlag){
						logger.debug("模板类型:债权协议设置为,出借编号【"+mc.getLendCode()+"】,发送状态为待发送");
						mc.setDictMatchingFilesendStatus(EmailState.DFS.value);
					}else{
						logger.debug("模板类型:债权协议,出借编号【"+mc.getLendCode()+"】，发送状态为未发送");
						if(StringUtils.isEmpty(this.isOnlyComposite) 
								|| !Constant.FILE_MAKE_FROM_HC.equals(this.isOnlyComposite)){
							mc.setDictMatchingFilesendStatus(EmailState.WFS.value);
						}
					}
					// 更新待推荐债权表
					matchingCreditorDao.updateByPrimaryKeySelective(mc);	
				}
							
			}
		} else {
			logger.debug("制作文件失败，把生成在文件服务器上的文件 删除");
			// 把生成的文档上传到文件服务器
			if(docId != null && docId.size() > 0 ){
				DmService dmService = DmService.getInstance();
				dmService.deleteDocument(docId.get(0));
			}
			
			if (StringUtils.isNotEmpty(attaTableId)) {
				logger.debug("制作文件失败，更新待推荐债权表文件合成失败");
				// 获取待推荐债权表
				MatchingCreditorEx mc = matchingCreditorDao
						.selectByPrimaryKey(attaTableId);
				// 设置文件合成失败
				mc.setDictMatchingFileStatus(FilecpState.HCSB.value);
				// 更新待推荐债权表
				matchingCreditorDao.updateByPrimaryKeySelective(mc);
				
				DeductPool dp = new DeductPool();
				dp.setApplyCode(mc.getLendCode());
				dp = deductApplyDao.get(dp);
				if(dp != null && dp.getMakeFlieStatus()!=null 
						&& dp.getMakeFlieStatus().equals(FilecpState.HCSB.value)){
					// 不做任务操作
					logger.debug("不做任务操作，合成失败");
				}else{
					logger.debug("制作文件失败，更新划扣申请表合成收款确认书状态，设置文件书款确认合成成功，邮件状态为未发送");
					// 设置文件书款确认合成成功
					dp.setMakeFlieStatus(FilecpState.HCSB.value);
					dp.setSendEmailStatus(EmailState.WFS.value);
					// 更新划扣申请表合成收款确认书状态
					deductApplyDao.update(dp);
				}
			}

		}
		logger.debug("制作文件回调方法----------结束");
	}

}
