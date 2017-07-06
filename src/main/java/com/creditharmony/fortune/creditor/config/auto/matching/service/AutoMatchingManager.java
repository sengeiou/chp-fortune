package com.creditharmony.fortune.creditor.config.auto.matching.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.OrgCache;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.fortune.creditor.config.auto.matching.dao.AutoMatchingDao;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
import com.creditharmony.fortune.creditor.config.auto.matching.view.CreditorperAutoConfigView;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.dao.TempAutoMatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.service.CreditorAidManager;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
/**
 * 自动匹配配置
 * @Class Name AutoMatchingManager
 * @author 朱杰
 * @Create In 2015年12月24日
 */
@Service
public class AutoMatchingManager extends CoreManager<AutoMatchingDao, CreditorperAutoConfig>{
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(AutoMatchingManager.class);
	
	@Autowired
	private AutoMatchingDao autoMatchingDao;
	
	
	@Autowired
	private MatchingCreditorManager matchingCreditorManager;
	
	@Autowired
	private TempAutoMatchingCreditorDao tempAutoMatchingCreditorDao;
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;// 待推荐Dao
	
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	/**
	 * 查询自动匹配列表
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param page
	 * @param search
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public Page<CreditorperAutoConfig> getAutoMatchingList(Page<CreditorperAutoConfig> page,CreditorperAutoConfigView search){
		Map<String, Object> map = new HashMap<String,Object>();
		//营业部
		if(StringUtils.isNotEmpty(search.getBusinessDepartment())){
			map.put("businessDepartment",search.getBusinessDepartment());
		}		
		//城市
		if(StringUtils.isNotEmpty(search.getProvinceCity())){
			map.put("provinceCity","("+search.getProvinceCity().replaceAll(",", "|") +")%");
		}
		//产品
		if(StringUtils.isNotEmpty(search.getProductCode())){
			map.put("productCode",StringUtils.split(search.getProductCode(),','));
		}
		if (StringUtils.isNotBlank(search.getBillType())) {
			map.put("billType",search.getBillType().split(","));//账单类型
		}
		if (StringUtils.isNotBlank(search.getBillDayEx())) {
			String[] array = search.getBillDayEx().split(",");
			List<Integer> billDayEx = new ArrayList<Integer>();
			for (String temp : array) {
				billDayEx.add(StringUtils.toInteger(temp));
			}
			map.put("billDayEx", billDayEx);
		}
		map.put("productRate",search.getProductRate());//利率
		if (StringUtils.isNotBlank(search.getStatus())) {
			map.put("status",search.getStatus().split(","));//状态
		}
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
	    PageList<CreditorperAutoConfig> pageList = 
	    		(PageList<CreditorperAutoConfig>)autoMatchingDao.findPages(map,pageBounds);
	    for (CreditorperAutoConfig creditorperAutoConfig : pageList) {
	    	// 营业部名称
			if(StringUtils.isNotEmpty(creditorperAutoConfig.getBusinessDepartment())){
				String departmentName = "";
				for (String departmentId : creditorperAutoConfig.getBusinessDepartment().split(",")) {
					Org org = OrgCache.getInstance().get(departmentId);
					departmentName += "," + org.getName();
				}
				departmentName = departmentName.replaceFirst(",", "");
				creditorperAutoConfig.setBusinessDepartmentName(departmentName);
			}
			// 省市
			if(StringUtils.isNotEmpty(creditorperAutoConfig.getProvinceCity())){
				String[] pId_cId = creditorperAutoConfig.getProvinceCity().split("_");
				creditorperAutoConfig.setProvinceId(pId_cId[0]);
				if(pId_cId.length==2){
					creditorperAutoConfig.setCityId(pId_cId[1]);
				}
			}
		}
	    PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 保存
	 * 2015年12月25日
	 * By 周俊
	 * @param creditorperAutoConfig
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void save(CreditorperAutoConfig elem){
		// 账单日	账单类型	营业部	省份|城市	产品
		elem.preInsert();
		autoMatchingDao.insert(elem);
	}
	
	/**
	 * 删除
	 * 
	 * 2015年12月25日
	 * By 朱杰
	 * @param deleteIds
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public int deleteByIds(String[] deleteIds){
		int deleteCount = autoMatchingDao.deleteByIds(deleteIds);
		return deleteCount;
	}
	
	/**
	 * 状态更新
	 * 
	 * 2015年12月25日
	 * By 朱杰
	 * @param updateId
	 * @return
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public int updateStatusByIds(String[] updateIds){
		int updateCount = autoMatchingDao.updateStatusByIds(updateIds);
		return updateCount;
	}
	
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void insertAutoMatching(CreditorperAutoConfig cac){
		Map<String,Object> autoparam = new HashMap<String, Object>();
		autoparam.put("matchingBillDay", cac.getBillDay());
		List<String> productCodes = new ArrayList<String>();
		productCodes.add(cac.getProductCode());
		autoparam.put("productCodes", productCodes);
		autoparam.put("matchingFirstdayFlag", cac.getBillType());
		
		
		// 拆分business_department:营业部id,营业部id,营业部id
		List<String> orgCodes = new ArrayList<String>();
		//orgCodes.add(cac.getBusinessDepartment());
		String []strOrgCodes=null;
		
		if (!StringUtils.isEmpty(cac.getBusinessDepartment())){
			strOrgCodes = cac.getBusinessDepartment().split(",");
		}
		
		if (strOrgCodes!=null ) {
			for(int ind=0; ind < strOrgCodes.length; ind++){
				orgCodes.add(strOrgCodes[ind]);
			}
		}

		autoparam.put("orgCodes", orgCodes);
		
		List<String> matchingStatusLst = new ArrayList<String>();
		matchingStatusLst.add(MatchingStatus.DTJ.value);
		matchingStatusLst.add(MatchingStatus.CXCP.value);
		autoparam.put("matchingStatusLst", matchingStatusLst);
		autoparam.put("provinceCity", cac.getProvinceCity());
		List<String> lendStatusLst = new ArrayList<String>();
		lendStatusLst.add(LendState.SPTG.value);
		lendStatusLst.add(LendState.DHK.value);
		lendStatusLst.add(LendState.HKCLZ.value);
		lendStatusLst.add(LendState.HKCG.value);
		autoparam.put("lendStatusLst", lendStatusLst);// 出借状态集合
		int updateCnt =0;
		List<String> matchingIds = matchingCreditorDao.getautoMatchingIdAll(autoparam); // 获取待自动匹配的集合	
		if(matchingIds!=null && matchingIds.size()>0){
		
			Map<String,List<String>> paramap = new HashMap<String, List<String>>();
			paramap.put("matchingIds", matchingIds);
			List<MatchingCreditorEx> matchingCreditors =  matchingCreditorDao.getMatchingCreditorByMatchingIds(paramap);
			for(MatchingCreditorEx ex: matchingCreditors){
				try{
					matchingCreditorManager.doEachTempAutoMatchingCreditor(ex,cac.getId());
					updateCnt =updateCnt+ 1;
				}catch(Exception e){
					e.printStackTrace();
					logger.error("插入待自动匹配的待推荐信息",e);
				}
			}
		}
//		cac.setStatus(UseFlag.TY.value);
		cac.setInTotalNum(updateCnt);
		autoMatchingDao.update(cac);
	}
	
	@Transactional(readOnly = false,value = "fortuneTransactionManager")
	public int deleteAll(){
		return tempAutoMatchingCreditorDao.deleteAll();
	}
	
	/**
	 * 更新自动匹配过滤条件的物化视图
	 * 2016年5月20日
	 * By 柳慧
	 */
	public void updateAutoMatching(){
		try{
			autoMatchingDao.updateAutoMatching();
		}catch(Exception e){
			logger.error("物化视图更新出错");
		}
		
	}

	/**
	 * 批量停用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param updateIds
	 * @param useFlag
	 */
	@Transactional(readOnly=false, value = "fortuneTransactionManager")
	public String updateBatchStatusByIds(String[] updateIds, String status) {
		if (ArrayHelper.isNotNull(updateIds)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("updateIds", updateIds);
			map.put("status", status);
			autoMatchingDao.updateBatchStatusByIds(map);
			return "SUCCESS";
		}
		return "批量启用停用失败!";
	}
}
