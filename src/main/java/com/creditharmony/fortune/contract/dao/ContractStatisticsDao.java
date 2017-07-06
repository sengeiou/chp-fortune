package com.creditharmony.fortune.contract.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.contract.view.ContractStatisticsExcelView;
import com.creditharmony.fortune.contract.view.ContractStatisticsParamView;
import com.creditharmony.fortune.contract.view.ContractStatisticsView;
/**
 * 合同统计
 * @Class Name ContractStatisticsDao
 * @author 李放
 * @Create In 2015年12月7日
 */
@FortuneBatisDao
public interface ContractStatisticsDao extends CrudDao<ContractStatisticsView>{

	/**
	 * 统计列表页
	 * @param contractStatistics 查询条件
	 * @param pageBounds
	 * @return
	 */
	public PageList<ContractStatisticsView> listStatisticsData(
			ContractStatisticsParamView contractStatistics,
			PageBounds pageBounds);

	/**
	 * 导出统计数据
	 * @param contractStatistics 查询条件
	 * @return
	 */
	public List<ContractStatisticsExcelView> listStatisticsExcelData(
			ContractStatisticsParamView contractStatistics);
	
}
