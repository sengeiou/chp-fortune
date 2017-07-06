package com.creditharmony.fortune.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.fortune.common.dao.AttachmentDao;

/**
 * 附件操作工具类
 * 
 * @Class Name AttachmentUtil
 * @author 朱杰
 * @Create In 2016年1月31日
 */
public class AttachmentUtil {
	
	private static AttachmentDao attachmentDao = SpringContextHolder.getBean(AttachmentDao.class);
	
	/**
	 * 附件的更新/删除操作
	 * 
	 * 2016年1月31日
	 * By 朱杰
	 * @param addAttachmentIds 更新的文件ids
	 * @param deleteAttachmentIds 删除的附件ids
	 * @param code 出借编号/合同编号/客户编号
	 * @param tableId 在所属表中的id
	 * @param tableName 所属表名
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public static void updateAndDeleteAttchment(
			List<String> addAttachmentIds,List<String> deleteAttachmentIds,String code,String tableId,String tableName){
		Map<String, Object> map = null;
		if(addAttachmentIds != null && addAttachmentIds.size() > 0){
			//更新上传的文件
			//附件保存
			map = new HashMap<String,Object>();
			map.put("ids", addAttachmentIds);
			map.put("attaFileOwner", tableName);//所属表
			map.put("attaTableId", tableId);//在该表中的ID
			map.put("loanCode", code);//出借编号/客户编号...
			map.put("isDiscard", EffectiveFlag.yx.value); // 附件状态
			attachmentDao.updateSelectiveByIds(map);
		}
		if(deleteAttachmentIds != null && deleteAttachmentIds.size() > 0){
			//更新上传的文件
			//附件保存
			map = new HashMap<String,Object>();
			map.put("ids", deleteAttachmentIds);
			attachmentDao.deleteByIds(map);
		}
	}
	
	/**
	 * 返回文件名，取第一个点的文件名
	 * 2016年6月23日
	 * By 朱杰
	 * @param filename
	 * @return
	 */
	public static String getFileName(String filename){
		if(StringUtils.isBlank(filename)){
			return null;
		}
		return filename.split(".")[0];
	}
}
