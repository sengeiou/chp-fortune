package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeView;
/**
 * 债权交易表 持久层
 * @Class Name CreditorTradeDao
 * @author 柳慧
 * @Create In 2015年12月17日
 */
@FortuneBatisDao
public interface CreditorTradeDao extends CrudDao<CreditorTrade>{
    /**
     * 通过债权推荐ID 获取债权交易数量
     * 2015年12月17日
     * By 柳慧
     * @param matchingId 债权推荐ID
     * @return 债权交易数量
     */
	public Integer getcountByMatchingId(String matchingId);
	/**
	 * 通过主键获取一条债权交易数据
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param tradeId
	 * @return
	 */
	public CreditorTrade selectByPrimaryKey(String tradeId);
	/**
	 * 通过债权推荐Id获取已推荐债权列表
	 * 2015年12月17日
	 * By 柳慧
	 * @param matchingId 债权推荐Id
	 * @return 已推荐债权列表集合
	 */
	public List<CreditorTradeView> getytjzqlbByMatchingId(String matchingId);
	
	/**
	 * 通过待推荐债权更新债权交易表
	 * 2016年5月25日
	 * By 柳慧
	 * @param ct 债权交易实体
	 */
	public void updateByMatchingId(CreditorTrade ct );
}