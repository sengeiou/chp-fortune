package com.creditharmony.fortune.document.dao;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.document.entity.DocumentBean;

/**
 * 根据档案对接处理结果修改表状态
 * @Class Name DocumentDao
 * @author 胡体勇
 * @Create In 2016年3月14日
 */
@FortuneBatisDao
public interface DocumentDao {

	/**
	 * 修改文件备份表
	 * 2016年3月5日
	 * By 胡体勇
	 * @param document
	 * @return
	 */
	public int updateDocument(DocumentBean documentBean);

}
