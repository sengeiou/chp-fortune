package com.creditharmony.fortune.creditor.config.rate.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.config.rate.dao.ProductMatchingRateDao;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.utils.ConvertUtil;
/**
 * 债权匹配规则管理
 * 
 * @Class Name MatchingRuleManager
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@Service
@Transactional(readOnly = true, value = "fortuneTransactionManager")
public class ProductMatchingRateManager extends CoreManager<ProductMatchingRateDao, ProductMatchingRate>{
	
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
	public Page<ProductMatchingRate> getList(Page<ProductMatchingRate> page,ProductMatchingRate search){
		Map<String, Object> map = new HashMap<String,Object>();
		map = ConvertUtil.ObjectToMap(search);
		if(StringUtils.isNotBlank(search.getProductCode()) && search.getProductCode().split(",").length > 0){
			map.put("productCode", search.getProductCode().split(","));
		}
		if(StringUtils.isNotBlank(search.getBillType()) && search.getBillType().split(",").length > 0){
			map.put("billType", search.getBillType().split(","));
		}
		if(StringUtils.isNotBlank(search.getMatchingBillDayStr()) && search.getMatchingBillDayStr().split(",").length > 0){
			map.put("matchingBillDayStr", search.getMatchingBillDayStr().split(","));
		}
		if(StringUtils.isNotBlank(search.getUseFlag()) && search.getUseFlag().split(",").length > 0){
			map.put("useFlag", search.getUseFlag().split(","));
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
	    PageList<ProductMatchingRate> pageList = (PageList<ProductMatchingRate>)super.dao.findList(map,pageBounds);
	    PageUtil.convertPage(pageList, page);
		return page;
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
		int deleteCount = 0;
		if(deleteIds.length > 0){
			deleteCount = super.dao.deleteByIds(deleteIds);
		}
		
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
		int updateCount = super.dao.updateStatusByIds(updateIds);
		return updateCount;
	}
	
	/**
	 * 保存
	 * 2016年1月15日
	 * By 朱杰
	 * @param ct
	 * @return
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public int saveData(ProductMatchingRate ct){
		ct.preInsert();
		super.dao.insert(ct);	
		
		return 0;
	}
	
	/**
	 * 根据产品编号，计划出借金额，计划出借时间获取该匹配利率
	 * 2016年2月10日
	 * By 朱杰
	 * @param productCode 产品编号
	 * @param applyLendMoney 计划出借金额
	 * @param applyLendDay 计划出借时间
	 * @param billType 账单类型
	 * @param matchingBillDay 账单日
	 * @param matchingInterestStart  本期出借日期
	 * @return
	 */
	public ProductMatchingRate getProductMchRate(String productCode,BigDecimal applyLendMoney,String applyLendDay, String billType,int matchingBillDay,String matchingInterestStart){
		ProductMatchingRate rate = null;
		if(StringUtils.isBlank(productCode) || applyLendMoney== null || StringUtils.isBlank(applyLendDay) || StringUtils.isBlank(billType)){
			return null;
		}
		
		if(billType.equals(BillState.SQ.value)){  // 首期
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("productCode", productCode);
			map.put("applyLendMoney", applyLendMoney);
			map.put("applyLendDay", applyLendDay);
			map.put("billType", billType);
			map.put("useFlag", UseFlag.QY.value);
			rate = super.dao.getProductMatchRate(map);
		}else{ // 非首期
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("productCode", productCode);
			map.put("applyLendMoney", applyLendMoney);
			map.put("applyLendDay", applyLendDay);
			map.put("billType", billType);
			map.put("matchingBillDay", matchingBillDay);
			map.put("useFlag", UseFlag.QY.value);
			map.put("matchingInterestStart", matchingInterestStart);
			rate = super.dao.getProductMatchRate(map); 
			if(rate==null){ //  如果本期没查到的  用该账单日的
				map.put("matchingInterestStart", null);
				rate = super.dao.getProductMatchRate(map); 
				if(rate==null){ // 如果账单日没有的   按首期的使用
					map.put("billType", BillState.SQ.value);
					map.put("matchingBillDay", null);
					map.put("matchingInterestStart", null);	
					rate = super.dao.getProductMatchRate(map);
				}
			}
		}
		return  rate;
		
	
	}
	/**
	 * 查询出待删除的数量（并且是启用）
	 * 2016年3月21日
	 * By 柳慧
	 * @param deleteIds
	 * @return
	 */
	public int beforDelete(String[] deleteIds) {
		return super.dao.beforDelete(deleteIds);
	}

	/**
	 * 保存前看是否已经存在数据
	 * 2016年3月21日
	 * By 柳慧
	 * @param param
	 * @return
	 */
	public int beforSaveCheck(Map<String, Object> param) {
		return super.dao.beforSaveCheck(param);
	}

	/**
	 * 批量停用
	 * 2016年09月21日
	 * By 陈晓强
	 * @param updateIds
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public String updateBatchStatusByIds(String[] updateIds, String useFlag) {
		if (ArrayHelper.isNotNull(updateIds)) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("updateIds", updateIds);
				map.put("useFlag", useFlag);
				super.dao.updateBatchStatusByIds(map);
				return "SUCCESS";
			} catch (Exception e) {
				return "批量启用停用异常,请稍后再试!";
			}
		}
		return "批量启用停用失败!";
	}
}
