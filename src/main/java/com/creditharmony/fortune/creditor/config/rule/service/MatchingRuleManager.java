package com.creditharmony.fortune.creditor.config.rule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.config.rule.dao.MatchingRuleDao;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleConfig;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleLower;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleProporti;
import com.creditharmony.fortune.creditor.config.rule.view.CreditorperRuleConfigView;
/**
 * 债权匹配规则管理
 * 
 * @Class Name MatchingRuleManager
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@Service
@Transactional(readOnly = true, value = "fortuneTransactionManager")
public class MatchingRuleManager extends CoreManager<MatchingRuleDao, CreditorperRuleConfig>{
	
	@Autowired
	private MatchingRuleDao matchingRuleDao;
	/**
	 * 查询匹配规则
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param page
	 * @param search
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<CreditorperRuleConfig>  getRuleList(Page<CreditorperRuleConfig> page,CreditorperRuleConfigView search){
		Map<String, Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(search.getUseFlag())){
			map.put("useFlag",search.getUseFlag());
		}
		if(StringUtils.isNotBlank(search.getBillType())){
			map.put("billType",search.getBillType());
		}
		
		List<CreditorperRuleConfig> list  = matchingRuleDao.find(map);
		return list;
	}
	
	/**
	 * 查询匹配规则
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param page
	 * @param search
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<CreditorperRuleConfig>  getRuleList(CreditorperRuleConfigView search){
		Map<String, Object> map = new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(search.getBillType())){
			map.put("billType",search.getBillType());
		}
		List<CreditorperRuleConfig> list  = matchingRuleDao.find(map);
		return list;
	}
	/**
	 * 删除
	 * 
	 * 2015年12月25日
	 * By 朱杰
	 * @param deleteIds
	 * @return
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public int deleteByIds(String[] deleteIds){
		int deleteCount = matchingRuleDao.deleteByIds(deleteIds);
		return deleteCount;
	}

	/**
	 * 更新状态
	 * 
	 * 2015年12月25日
	 * By 朱杰
	 * @param updateId
	 * @return
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public int updateStatusByIds(String[] updateIds){
		CreditorperRuleConfig creditorperRuleConfig = new CreditorperRuleConfig();
		creditorperRuleConfig.setId(updateIds[0]);
		List<CreditorperRuleConfig> list = matchingRuleDao.findAllList(creditorperRuleConfig);
		if (list.get(0).getUseFlag().equals(ConfigStatus.QY.value)) {// 点击对象为启用,现在改为停用
			return matchingRuleDao.updateStatusByIds(updateIds);
		}
		
		// 点击对象为停用,现在改为启用
		creditorperRuleConfig.setId(null);
		creditorperRuleConfig.setBillType(list.get(0).getBillType());
		List<CreditorperRuleConfig> allList = matchingRuleDao.findAllList(creditorperRuleConfig);
		int count =0;
		for (CreditorperRuleConfig temp : allList) {
			if (temp.getUseFlag().equals(ConfigStatus.QY.value)||temp.getId().equals(updateIds[0])) {
				String[] array = {temp.getId()};
				count = count+matchingRuleDao.updateStatusByIds(array);
			}
		}
		return count;
	}
	

	/**
	 * 切换启用状态
	 * 2016年11月4日
	 * By 陈广鹏
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public String updateStatusById(String id) {
		
		String result = "SUCCESS";
		CreditorperRuleConfig creditorperRuleConfig = new CreditorperRuleConfig();
		creditorperRuleConfig.setId(id);
		List<CreditorperRuleConfig> list = matchingRuleDao.findAllList(creditorperRuleConfig);
		CreditorperRuleConfig rule = list.get(0);
		
		if (rule.getUseFlag().equals(ConfigStatus.TY.value)) {
			// 点击对象为停用，先校验，在决定是否启用
			if (dataCheck(rule)) {
				// 校验通过，更新状态为启用
				updateBatchStatusByIds(new String[]{id}, UseFlag.QY.value);
			} else {
				result = "启用该配置条目会出现配置冲突，请再次确认后重新操作";
			}
		} else {
			// 点击对象为启用，直接停用
			matchingRuleDao.updateStatusByIds(new String[]{id});
		}
		
		return result;
	}
	
	/**
	 * 保存
	 * 2016年1月15日
	 * By 朱杰
	 * @param ct
	 * @return
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public int saveData(CreditorperRuleConfig ct){
		//插入
		//规则配置表
		//ct.setUseFlag(UseFlag.QY.value);
		if(StringUtils.isBlank(ct.getDefaultFlag())){
			ct.setDefaultFlag(YoN.FOU.value);
		}
		if(YoN.SHI.value.equals(ct.getDefaultFlag())){
			//改改账单类型的默认匹配利率
			this.resetRuleDefaultFlag(ct);
		}
		ct.preInsert();
		matchingRuleDao.insertRule(ct);
		//投资比例和投资下限插入
		insertProportionAndLower(ct);		
		
		return 0;
	}
	
	/**
	 * 设置默认标识，并将其他的相同账单类型的规则设为非默认
	 * 
	 * 2016年2月4日
	 * By 朱杰
	 * @param ct
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public void resetRuleDefaultFlag(CreditorperRuleConfig ct){
		Map<String, Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(ct.getId())){
			return;
		}
		map.put("billType",ct.getBillType());
		map.put("excludeId",ct.getId());
		matchingRuleDao.resetRuleDefaultFlag(map);
	}
	
	/**
	 * 投资比例和投资下限插入操作
	 * 
	 * 2016年1月9日
	 * By 朱杰
	 * @param ct
	 */
	private void insertProportionAndLower(CreditorperRuleConfig ct){
		
		String ruleId = ct.getId();
		//投资比例表
		List<CreditorperRuleProporti> proportionList = ct.getProporti();
		int sort = 1;
		for (CreditorperRuleProporti proportion : proportionList) {
			proportion.setRuleId(ruleId);
			proportion.setSort(sort++);
			proportion.setUseFlag(UseFlag.QY.value);
			proportion.preInsert();
			matchingRuleDao.insertProportion(proportion);
			//下限比例表
			int lowerSort = 1;
			List<CreditorperRuleLower> lowerList = proportion.getLower();
			for (CreditorperRuleLower lower : lowerList) {
				lower.setProportionId(proportion.getId());
				lower.setSort(lowerSort++);
				lower.preInsert();
				matchingRuleDao.insertLower(lower);
			}
		}
	}
	
	/**
	 * 获取账单类型的默认投资比例
	 * 2016年2月10日
	 * By 朱杰
	 * @param billType
	 * @return
	 */
	public CreditorperRuleConfig getDefaultRuleConfig(String billType){
		if(StringUtils.isBlank(billType)){
			return new CreditorperRuleConfig();
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("billType", billType);
		map.put("useFlag", UseFlag.QY);
		map.put("defaultFlag", YoN.SHI);
		
		List<CreditorperRuleConfig> list = matchingRuleDao.find(map);
		return list.size() > 0 ? list.get(0) : new CreditorperRuleConfig();
	}
	
	/**
	 * 批量启用配置
	 * 2016年11月3日
	 * By 陈广鹏
	 * @param ids
	 * @return
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public String enableConfig(String[] ids){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		param.put("useFlag", UseFlag.TY.value);
		// 获取选中数据中的停用数据进行操作
		List<CreditorperRuleConfig> ruleConfigLs = matchingRuleDao.getByIds(param);
		
		for (CreditorperRuleConfig rule : ruleConfigLs) {
			if (dataCheck(rule)) {
				// 校验通过，更新状态为启用
				updateBatchStatusByIds(new String[]{rule.getId()}, UseFlag.QY.value);
			} else {
				logger.error("冲突CreditorperRuleConfig ID:\t"+rule.getId());
				// 一条校验不通过，全部操作回滚
				throw new ServiceException("全部启用所选条目会出现配置冲突，请再次确认后重新操作");
			}
		}
		return "SUCCESS";
	}

	/**
	 * 批量启用/停用
	 * 2016年09月21日
	 * By 陈晓强
	 * @param updateIds
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public String updateBatchStatusByIds(String[] updateIds, String useFlag) {
		if (ArrayHelper.isNotNull(updateIds)) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("updateIds", updateIds);
				map.put("useFlag", useFlag);
				matchingRuleDao.updateBatchStatusByIds(map);
				return "SUCCESS";
			} catch (Exception e) {
				return "批量启用停用异常,请稍后再试!";
			}
		}
		return "批量启用停用失败!";
	}

	/**
	 * 保存前数据检查
	 * 2016年10月19日
	 * By 陈广鹏
	 * @param rule
	 * @return
	 */
	public String saveCheck(CreditorperRuleConfig rule) {
		if (UseFlag.TY.value.equals(rule.getUseFlag())) {
			// 新增停用配置，不做校验
			return "SUCCESS";
		} else {
			return dataCheck(rule)?"SUCCESS":"当前账单类型和启用状态下，输入的本期推荐金额区间与已有规则有重复";
		}
	}
	
	/**
	 * 数据检查，保证启用的配置互相不冲突
	 * 2016年11月3日
	 * By 陈广鹏
	 * @param rule
	 * @return
	 */
	public boolean dataCheck(CreditorperRuleConfig rule) {
		// 同时启用的相同账单类型，金额范围区间不可交叉
		rule.setUseFlag(UseFlag.QY.value);
		int count = matchingRuleDao.countRuleConfig(rule);
		if (count > 0 ) {
			return false;
		}
		return true;
	}
}
