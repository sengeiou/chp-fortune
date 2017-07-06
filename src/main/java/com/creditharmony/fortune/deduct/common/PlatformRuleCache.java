package com.creditharmony.fortune.deduct.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.fortune.type.DeductinterType;
import com.creditharmony.fortune.deduct.dao.PlatformRuleDao;
import com.creditharmony.fortune.deduct.entity.PlatformRule;

/**
 * 三方平台规则表缓存类
 * @Class Name PlatformRuleCache
 * @author 韩龙
 * @Create In 2015年12月18日
 */
public class PlatformRuleCache {

	/**
	 * cache缓存
	 */
	private static Map<String, Map<String, PlatformRule>> deductPlatBatchCache = new HashMap<String, Map<String, PlatformRule>>();
	private static Map<String, Map<String, PlatformRule>> deductPlatRealtimeCache = new HashMap<String, Map<String, PlatformRule>>();
	

	/**
	 * 获取platformRuleDao对象
	 */
	private static PlatformRuleDao platformRuleDao = SpringContextHolder
			.getBean(PlatformRuleDao.class);

	/**
	 * 获取三方平台规则表批量 2015年12月18日 By 韩龙
	 * 
	 * @param dictDeductPlatformId
	 *            划扣平台id
	 * @param dictBankId
	 *            划扣银行id
	 * @return 三方平台规则实体
	 */
	public static PlatformRule getBatch(String dictDeductPlatformId,
			String dictBankId) {
		PlatformRule platformRule = new PlatformRule();
		try {
			platformRule = deductPlatBatchCache.get(dictDeductPlatformId).get(
					dictBankId);
		} catch (Exception e) {
			updateCache(DeductinterType.PL.value);
			platformRule = deductPlatBatchCache.get(dictDeductPlatformId).get(
					dictBankId);
		}
		if (platformRule == null) {
			updateCache(DeductinterType.PL.value);
			platformRule = deductPlatBatchCache.get(dictDeductPlatformId).get(
					dictBankId);
		}
		return platformRule;
	}
	
	/**
	 * 获取三方平台规则表实时 2015年12月18日 By 韩龙
	 * 
	 * @param dictDeductPlatformId
	 *            划扣平台id
	 * @param dictBankId
	 *            划扣银行id
	 * @return 三方平台规则实体
	 */
	public static PlatformRule getRealtime(String dictDeductPlatformId,
			String dictBankId) {
		PlatformRule platformRule = null;
		try {
			platformRule = deductPlatRealtimeCache.get(dictDeductPlatformId).get(
					dictBankId);
		} catch (Exception e) {
			updateCache(DeductinterType.SS.value);
			platformRule = deductPlatRealtimeCache.get(dictDeductPlatformId).get(
					dictBankId);
		}
		if (platformRule == null) {
			updateCache(DeductinterType.SS.value);
			platformRule = deductPlatRealtimeCache.get(dictDeductPlatformId).get(
					dictBankId);
		}
		return platformRule;
	}

	/**
	 * 更新缓存 2015年12月18日 By 韩龙
	 * 
	 * @param type
	 */
	private static void updateCache(String type) {
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deductInterfaceType", type);
		// 获取集合
		List<PlatformRule> list = platformRuleDao.getPlatformRuleList(map);
		// 遍历集合放入缓存
		for (PlatformRule platformRule : list) {
			if(type.equals(DeductinterType.PL.value)){
				if (!deductPlatBatchCache.containsKey(platformRule.getDictDeductPlatformId())) {
					System.out.println("批量缓存加入新的平台,平台ID为:"+platformRule.getDictDeductPlatformId());
					Map<String, PlatformRule> mapRule = new HashMap<String, PlatformRule>();
					mapRule.put(platformRule.getDictBankId(), platformRule);
					deductPlatBatchCache.put(platformRule.getDictDeductPlatformId(),mapRule);
				}else{
					Map<String, PlatformRule> bankIdMap = deductPlatBatchCache.get(platformRule.getDictDeductPlatformId());
					if(!bankIdMap.containsKey(platformRule.getDictBankId())){
						System.out.println("批量缓存加入新银行,平台ID为:"+platformRule.getDictDeductPlatformId()+";银行ID为:"+platformRule.getDictBankId());
						Map<String, PlatformRule> mapRule = new HashMap<String, PlatformRule>();
						mapRule.put(platformRule.getDictBankId(), platformRule);
						deductPlatBatchCache.put(platformRule.getDictDeductPlatformId(),mapRule);
					}
				}
				continue;
			}
			// 实时
			if (!deductPlatBatchCache.containsKey(platformRule.getDictDeductPlatformId())) {
				System.out.println("实时缓存加入新的平台,平台ID为:"+platformRule.getDictDeductPlatformId());
				Map<String, PlatformRule> mapRule = new HashMap<String, PlatformRule>();
				mapRule.put(platformRule.getDictBankId(), platformRule);
				deductPlatBatchCache.put(platformRule.getDictDeductPlatformId(),mapRule);
			}else{
				Map<String, PlatformRule> bankIdMap = deductPlatBatchCache.get(platformRule.getDictDeductPlatformId());
				if(!bankIdMap.containsKey(platformRule.getDictBankId())){
					System.out.println("实时缓存加入新银行,平台ID为:"+platformRule.getDictDeductPlatformId()+";银行ID为:"+platformRule.getDictBankId());
					Map<String, PlatformRule> mapRule = new HashMap<String, PlatformRule>();
					mapRule.put(platformRule.getDictBankId(), platformRule);
					deductPlatBatchCache.put(platformRule.getDictDeductPlatformId(),mapRule);
				}
			}
		}
	}
}
