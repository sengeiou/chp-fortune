package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorStatistics;

/**
 * 债权操作统计持久层
 * @Class Name CreditorStatisticsDao
 * @author 朱杰
 * @Create In 2016年3月20日
 */
@FortuneBatisDao
public interface CreditorStatisticsDao extends CrudDao<CreditorStatistics>{

    int insert(CreditorStatistics record);

    List<CreditorStatistics> selectBySelective(Map<String,Object> param);

    int update(CreditorStatistics record);
}