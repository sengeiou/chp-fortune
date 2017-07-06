package com.creditharmony.fortune.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendFileBean;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;

/**
 * 附件操作类
 * @Class Name AttachmentService
 * @author 朱杰
 * @Create In 2016年1月6日
 */
@Service
public class AttachmentManager extends CoreManager<AttachmentDao,Attachment>{
	
	@Autowired
	private AttachmentDao attachmentDao;

	
	/**
	 * 附件保存
	 * 2016年1月6日
	 * By 朱杰
	 * @param fileLst
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveFile(Attachment file) {
		
		//共通部分内容写入
		file.preInsert();
		file.setIsDiscard(EffectiveFlag.yx.value);
		//保存
		attachmentDao.insert(file);
		
	}

	/**
	 * 附件列表查询
	 * 2016年1月6日
	 * By 朱杰
	 * @param selective
	 * @return
	 */
	public List<Attachment> findSelective(Attachment selective) {
		if(StringUtils.isEmpty(selective.getLoanCode()) 
				|| StringUtils.isEmpty(selective.getAttaTableId()) 
				|| StringUtils.isEmpty(selective.getAttaFileOwner())){
			return new ArrayList<Attachment>();
		}
		return attachmentDao.findAllList(selective);		
		
	}
	
	/**
	 * 附件更新
	 * 2016年4月11日
	 * By 郭才林
	 * @param file
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void updateAttachment(Attachment file) {
		
		attachmentDao.updateAttachment(file);
		
	}
	
	/**
	 * 使附件无效，并将附件移至 客户编号/deleted
	 * 2016年4月12日
	 * By 朱杰
	 * @param attachment
	 * @param customerCode
	 */
	public void invalidAttachment(Attachment attachment,String customerCode){
		List<Attachment> list = attachmentDao.findList(attachment);
		for (Attachment move : list) {
			//移除附件
			DmService dmService = DmService.getInstance();
			//将文件移动至固定文件夹内
			dmService.moveDocument(move.getAttaFilepath(), customerCode + "/" + "deleted", DmService.BUSI_TYPE_FORTUNE);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(attachment.getLoanCode())){
			map.put("lendCode", attachment.getLoanCode());
		}
		if(StringUtils.isNotEmpty(attachment.getDictAttaFlag())){
			map.put("attaFlag", attachment.getDictAttaFlag());
		}
		if(StringUtils.isNotEmpty(attachment.getModifyBy())){
			map.put("modifyBy", attachment.getModifyBy());
		}
		if(attachment.getModifyTime() != null){
			map.put("modifyTime", attachment.getModifyTime());
		}
		map.put("discard", EffectiveFlag.yx.value);
		map.put("isDiscard", EffectiveFlag.wx.value);
		attachmentDao.updateSelectiveByIds(map);
	}
	
	/**
	 * 附件列表查询(转投大金融)
	 */
	public List<DjrSwitchSendFileBean> getAttachmentDJR(String lendCode) {
		if (StringUtils.isEmpty(lendCode)) {
			return null;
		}
		return attachmentDao.getAttachmentDJR(lendCode);
	}

	public void deleteById(Attachment attachment) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> ids = new ArrayList<String>();
		ids.add(attachment.getAttaId());
		map.put("ids", ids);
		attachmentDao.deleteByIds(map);
	}

	public void deleteAttachmentDJR(String lendCode) {
		attachmentDao.deleteAttachmentDJR(lendCode);
	}
}
