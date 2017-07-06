package com.creditharmony.fortune.mail.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.mail.entity.EmailInfo;
import com.creditharmony.fortune.mail.entity.EmailTemplate;

/**
 * 邮件操作
 * @Class Name EmailInfoDao
 * @author 朱杰
 * @Create In 2016年3月7日
 */
@FortuneBatisDao
public interface EmailInfoDao extends CrudDao<EmailInfo> {

    int insert(EmailInfo record);

    EmailInfo select(String emailSendId);

    int update(EmailInfo record);
    
    EmailTemplate getEmailTemplateByType(String templateCode);

    /**
     * 撤销邮件发送
     * 2016年6月3日
     * By 柳慧
     * @param emailInfoParam
     * @return
     */
	int updateByMap(Map<String, String> emailInfoParam);
	
	/**
	 * 更新邮件附件路径
	 * 2016年6月26日
	 * By 朱杰
	 * @param attachment
	 * @return
	 */
	int updateAttachmentFilePath(Attachment attachment);
}