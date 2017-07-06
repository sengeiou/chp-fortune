
package com.creditharmony.fortune.creditor.matching.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.ConfigStatus;
import com.creditharmony.core.fortune.type.ProductStatus;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorConfigDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;

/**
 * @Class Name CreditorConfigManager
 * @author 胡体勇
 * @Create In 2015年12月21日
 */
@Service
public class CreditorConfigManager extends CoreManager<CreditorConfigDao, CreditorConfig> {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 错期匹配列表及分页查询
	 * 2015年12月9日
	 * By 胡体勇
	 * @param page
	 * @param creditorConfig
	 * @return
	 */
	public Page<CreditorConfig> findPage(Page<CreditorConfig> page,CreditorConfig creditorConfig){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		
		//creditorConfig.setDictConfigZdr(ProductStatus.SY.value);
		
		PageList<CreditorConfig> pageList = (PageList<CreditorConfig>) this.dao.findList(creditorConfig,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 新增错期匹配
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int add(CreditorConfig creditorConfig){
		creditorConfig.setId(IdGen.uuid());
		
//		creditorConfig.setConfigIsUse(ProductStatus.SY.value);
//		creditorConfig.setConfigIsFirst(creditorConfig.getDictConfigZdr());
		
		creditorConfig.setDictConfigZdr(ProductStatus.SY.value);
		
		creditorConfig.setCreateBy(UserUtils.getUserId());
		creditorConfig.setCreateTime(new Date());
		creditorConfig.setModifyBy(UserUtils.getUserId());
		creditorConfig.setModifyTime(new Date());
		List<CreditorConfig> list = super.dao.checkRepeat(creditorConfig);
		if(list.size() > 0){
			return Constant.REPEAT_NUMBER;
		}else{
			return super.dao.add(creditorConfig);
		}
	}
	
	/**
	 * 修改错期匹配的状态
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	public int updateStatus(CreditorConfig creditorConfig){
		String[] splitArray = creditorConfig.getId().split(",");
		if(splitArray.length==1&&StringUtils.isNotBlank(creditorConfig.getDictConfigZdr())){
			if(ConfigStatus.QY.value.equals(creditorConfig.getDictConfigZdr())){
				creditorConfig.setDictConfigZdr(ConfigStatus.TY.value);
			}else if(ConfigStatus.TY.value.equals(creditorConfig.getDictConfigZdr())){
				creditorConfig.setDictConfigZdr(ConfigStatus.QY.value);
			}
			creditorConfig.setModifyBy(IdGen.uuid());// 获取当前操作人id
			creditorConfig.setModifyTime(new Date());
			int result = super.dao.updateStatus(creditorConfig);
			return result;
		}
		int result = 0;
		for (String id : splitArray) {
			try {
				result = updateCreditorConfig(result, id);	
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 2016年4月22日
	 * By 柳慧
	 * @param result
	 * @param id
	 * @return 
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	private int updateCreditorConfig(int result, String id) {
		CreditorConfig temp = new CreditorConfig();
		temp.setId(id);
		temp.setDictConfigZdr(String.valueOf(BorrowConstant.NEGATIVE_ONE));
		temp.setModifyBy(UserUtils.getUser(UserUtils.getUserId()).getName());
		temp.setModifyTime(new Date());
		int count = super.dao.updateStatus(temp);
		result = result+count;
		return result;
	}
	
	/**
	 * 通过账单日查询错期集合
	 * 2015年12月26日
	 * By 柳慧
	 * @param matchingBillDay 账单日
	 * @return
	 */
	public List<CreditorConfig> findByMatchingBillDay(CreditorConfig creditorConfig) {
		return super.dao.findByMatchingBillDay(creditorConfig);
	}

	/**
	 * 批量停用启用 2016年09月22日 
	 * By 陈晓强
	 * @param updateIds
	 * @param useFlag
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String updateBatchStatusByIds(String[] updateIds, String dictConfigZdr) {
		if (ArrayHelper.isNotNull(updateIds)) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("updateIds", updateIds);
				map.put("dictConfigZdr", dictConfigZdr);
				super.dao.updateBatchStatusByIds(map);
				return "SUCCESS";
			} catch (Exception e) {
				return "批量启用停用异常,请稍后再试!";
			}
		}
		return "批量启用停用失败!";
	}
}
