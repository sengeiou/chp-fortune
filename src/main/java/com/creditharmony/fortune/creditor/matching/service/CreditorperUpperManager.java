
package com.creditharmony.fortune.creditor.matching.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.matching.dao.CreditorperUpperDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorperUpper;

/**
 * 
 * @Class Name CreditorperUpperManager
 * @author 柳慧
 * @Create In 2016年1月27日
 */
@Service
public class CreditorperUpperManager extends CoreManager<CreditorperUpperDao, CreditorperUpper> {
	
	@Autowired 
	private CreditorperUpperDao creditorperUpperDao;
	
	/**
	 * 查询方法 分页
	 * 2016年1月28日
	 * by liu hui
	 * @param page
	 * @param creditorperUpper
	 * 
	 */
	public Page<CreditorperUpper> findPage(Page<CreditorperUpper> page,CreditorperUpper creditorperUpper){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		Map<String,Object> tempex = new HashMap<String,Object>();
		try {
			tempex =objectToMap(creditorperUpper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!StringUtils.isEmpty(creditorperUpper.getDictLoanType())){
			String dictLoanTypeArr[] = creditorperUpper.getDictLoanType().split(",");
			List<String> dictLoanTypes = new ArrayList<String>();
			for(String dictLoanTy: dictLoanTypeArr){
				dictLoanTypes.add(dictLoanTy);
			}
			tempex.put("dictLoanTypes", dictLoanTypes);
		}
		if(!StringUtils.isEmpty(creditorperUpper.getProofType())){
			String proofTypeArr[] = creditorperUpper.getProofType().split(",");
			List<String> proofTypes = new ArrayList<String>();
			for(String proofType: proofTypeArr){
				proofTypes.add(proofType);
			}
			tempex.put("proofTypes", proofTypes);
		}
		PageList<CreditorperUpper> pageList = (PageList<CreditorperUpper>) creditorperUpperDao.findList(tempex,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 添加方法
	 * 2016年1月28日
	 * By 柳慧
	 * @param creditorperUpper
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int add(CreditorperUpper creditorperUpper) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> proofTypes = new ArrayList<String>();
		proofTypes.add(creditorperUpper.getProofType());
		map.put("proofTypes", proofTypes);
		map.put("useFlag", UseFlag.QY.value);
		List<CreditorperUpper> list = this.dao.findList(map);
		for (CreditorperUpper temp : list) {
			if (temp.getDictLoanType().equals(creditorperUpper.getDictLoanType())) {
				return 0;
			}
		}
		creditorperUpper.preInsert();
		return this.dao.insert(creditorperUpper);
	}
	/**
	 *  通过主键软删除
	 * 2016年1月28日
	 * By 柳慧
	 * @param deleteIds
	 */
	@Transactional(value="fortuneTransactionManager",readOnly = false)
	public int  delete(String[] deleteIds) {
		return this.dao.deleteByIds(deleteIds);
		
	}
	

	/**
	 * 对象转换成map
	 * 2016年1月25日
	 * By 柳慧
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private  Map<String, Object> objectToMap(Object obj) throws Exception {
		if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
        return map;  
	}
	
	/**
	 *  通过条件查询 集合
	 * 2016年1月28日
	 * By 柳慧
	 * @param tempex
	 * @return
	 */
	public  List<CreditorperUpper> findList(Map<String,Object> tempex){
		return this.dao.findList(tempex);
	}

	public int updateStatusByIds(String[] updateIds) {
		return  this.dao.updateStatusByIds(updateIds);
	}

	public String updateBatchStatusByIds(String[] updateIds, String useFlag) {
		if (ArrayHelper.isNotNull(updateIds)) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("updateIds", updateIds);
				map.put("useFlag", useFlag);
				this.dao.updateBatchStatusByIds(map);
				return "SUCCESS";
			} catch (Exception e) {
				return "批量启用停用异常,请稍后再试!";
			}
		}
		return "批量启用停用失败!";
	}
}
