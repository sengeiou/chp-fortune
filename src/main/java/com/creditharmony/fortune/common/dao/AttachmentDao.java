package com.creditharmony.fortune.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendFileBean;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Attachment;

/**
 * 附件操作Dao
 * @Class Name AttachmentDao
 * @author 朱杰
 * @Create In 2016年1月6日
 */
@FortuneBatisDao
public interface AttachmentDao extends CrudDao<Attachment> {
	
	/**
	 * 删除文件
	 * 
	 * 2016年1月31日
	 * By 朱杰
	 * @param ids 删除的文件ids
	 */
	public void deleteByIds(Map<String, Object> map);
	
	/**
	 * 更新所有map.get('ids')的项;
	 * 2016年1月31日
	 * By 朱杰
	 * @param map
	 */
	public void updateSelectiveByIds(Map<String, Object> map);

	public List<Attachment> findAllList(Attachment attachment);

	/**
	 * 附件更新
	 * 2016年4月11日
	 * By 郭才林
	 * @param file
	 */
	public void updateAttachment(Attachment file);
	
	public void updateLendCode(@Param("oldLendCode")String oldLendCode, @Param("newLendCode")String newLendCode);
	
	public List<DjrSwitchSendFileBean> getAttachmentDJR(String lendCode);

	public void deleteAttachmentDJR(String lendCode);
}
