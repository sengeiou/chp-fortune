package com.creditharmony.fortune.creditor.config.rule.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleConfig;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleLower;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleProporti;
/**
 * 债权匹配规则管理Dao
 * 
 * @Class Name MatchingRuleDao
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@FortuneBatisDao
public interface MatchingRuleDao extends CrudDao<CreditorperRuleConfig>{
	/**
	 * 匹配规则列表获取
	 * 2015年12月22日
	 * By 朱杰
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<CreditorperRuleConfig> find(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 匹配规则列表获取
	 * 2015年12月22日
	 * By 朱杰
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<CreditorperRuleConfig> find(Map<String, Object> map);
	
	/**
	 * 删除
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param deleteIds
	 * @return
	 */
	public int deleteByIds(String[] deleteIds);
	
	/**
	 * 更新规则状态
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param updateIds
	 * @return
	 */
	public int updateStatusByIds(String[] updateIds);
	
	/**
	 * 规则配置插入
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param cr
	 * @return
	 */
	public int insertRule(CreditorperRuleConfig cr);
	
	/**
	 * 投资比例插入
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param proportion
	 * @return
	 */
	public int insertProportion(CreditorperRuleProporti proportion);
	
	/**
	 * 投资比例停用
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param ids
	 * @return
	 */
	public int stopProportion(String id);
	
	/**
	 * 投资下限插入
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param lower
	 * @return
	 */
	public int insertLower(CreditorperRuleLower lower);
	
	/**
	 * 将参数中账单类型的id以外的对着设置为非默认，并且将该id的规则设置为默认
	 * 2016年1月15日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public int resetRuleDefaultFlag(Map<String, Object> map);
	
	/**
	 * 根据账单类型修改
	 * 2016年3月17日
	 * By 周俊
	 * @param id
	 * @return
	 */
	public int updateUseFlag(String updateUseFlag);

	/**
	 * 批量启用停用
	 * 2016年09月21日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public int updateBatchStatusByIds(Map<String, Object> map);

	/**
	 * 查看同账单类型、本期推荐金额重叠的规则数量
	 * 2016年10月19日
	 * By 陈广鹏
	 * @param rule
	 * @return
	 */
	public int countRuleConfig(CreditorperRuleConfig rule);

	/**
	 * 根据主键id获取数据
	 * 2016年11月3日
	 * By 陈广鹏
	 * @param param
	 * @return
	 */
	public List<CreditorperRuleConfig> getByIds(Map<String, Object> param);
	
}
