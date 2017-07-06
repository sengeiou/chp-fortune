package com.creditharmony.fortune.obligatory.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.common.entity.Org;
import com.creditharmony.fortune.obligatory.contants.GlobarContants;
import com.creditharmony.fortune.obligatory.dao.CreditLocationListDao;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;
import com.creditharmony.fortune.obligatory.service.ObligatoryTransactionManager;
import com.creditharmony.fortune.obligatory.view.ObligatoryListView;

/**
 * 可用债权配置列表
 * @Class Name CreditLocationListManager 
 * @author 李志伟
 * @Create In 2015年12月15日
 */
@Service
public class CreditLocationListManager {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private  CreditLocationListDao creditLocationListDao;
	@Autowired
	private  ObligatoryTransactionManager obligatoryTransactionManager;
	
	/**
	 * 初始化可用债权配置列表
	 * 2016年5月10日
	 * by 李志伟
	 * @param coo
	 * @param loanBackmoneyDay
	 * @param loanMonthRate
	 * @param request
	 * @param response
	 */
	public ObligatoryListView loadCreditLocationList(CreditOkListObj coo, String loanBackmoneyDay, BigDecimal loanMonthRate,  HttpServletRequest request, HttpServletResponse response){
		
		Page<CreditOkListObj> page = new Page<CreditOkListObj>(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		coo.setDelFlag(GlobarContants.NODEL);
		map.put("coo", coo);
		if (StringHelper.isNotEmpty(loanBackmoneyDay)) {
			List<Integer> list = new ArrayList<Integer>();
			String[] split = loanBackmoneyDay.split(",");
			for (String temp : split) {
				list.add(StringUtils.toInteger(temp));
			}
			map.put("loanBackmoneyDay",list);// 还款日
		}
		if (StringHelper.isNotEmpty(coo.getConfigType())) {
					
			map.put("billType", coo.getConfigType().split(","));// 账单类型
		}
		if (!ObjectHelper.isEmpty(loanMonthRate)) {
					
			map.put("loanMonthRate",String.valueOf(loanMonthRate));// 月利率
		
		}
		
		page = findList(map, page);
		ObligatoryListView olv = new ObligatoryListView();
		olv.setPage(page);
		olv.setLoanBackmoneyDay(loanBackmoneyDay);
		olv.setLoanMonthRate(loanMonthRate);
		olv.setCoo(coo);
		return olv;
	}
	
	/**
	 * 可用债权配置列表检索
	 * 2016年1月11日
	 * by 李志伟
	 * @param map
	 * @param page
	 * @return
	 */
	public Page<CreditOkListObj> findList(Map<String, Object> map, Page<CreditOkListObj> page) {
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<CreditOkListObj> pageList  = (PageList<CreditOkListObj>) creditLocationListDao.loadCreditLocationList(map, pageBounds);
		
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 添加页面检索
	 * 2016年5月10日
	 * by 李志伟
	 * @param coo
	 * @param request
	 * @param response
	 */
	public ObligatoryListView loadAddPage(CreditOkListObj coo, HttpServletRequest request, HttpServletResponse response){
		
		Page<CreditOkListObj> page = new Page<CreditOkListObj>(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		ObligatoryListView olv = new ObligatoryListView();
		map.put("coo", coo);
		map.put("delFlag", GlobarContants.NODEL);
		map.put("flag", GlobarContants.DEL);
		if((!coo.getConfigZdr().equals("") && coo.getConfigZdr() != null) || 
				(!coo.getConfigLoanName().equals("") && coo.getConfigLoanName() != null) ){
			
			page = this.addPageSearch(page, map);
			olv.setPage(page);
		}
		olv.setCoo(coo);
		return olv;
	}
	
	/**
	 * 可用债权配置添加页面检索
	 * 2015年12月17日
	 * by 李志伟
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<CreditOkListObj> addPageSearch(Page<CreditOkListObj> page,
			Map<String, Object> map) {
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("credit_value_id");
		PageList<CreditOkListObj> pageList  =  (PageList<CreditOkListObj>)creditLocationListDao.addPageSearch(map, pageBounds);
		PageUtil.convertPage(pageList, page);
	
		return page;
	}
	
	/**
	 * 添加新的可用债权配置
	 * 2015年12月19日
	 * by 李志伟
	 * @param coo
	 */
	public String addCreditConfig(CreditOkListObj coo) {
		
		Borrow bro = creditLocationListDao.selectLoanMsg(coo.getCreditValueId());
		List<String> li = new ArrayList<String>();
		
		if(coo.getConfigType() != null && !("").equals(coo.getConfigType())){
			// 账单类型
			li = Arrays.asList(coo.getConfigType().split(","));
		}else{
			li.add(YoN.SHI.value);
			li.add(YoN.FOU.value);
		}
		
		// 城市
		String[] cc = StaticMethodUtil.filterNullStr(coo.getConfigCity().split(","));
		List<String> city = Arrays.asList(cc);
		// 营业部
		String[] orgId = StaticMethodUtil.filterNullStr(coo.getConfigYy().split(","));
		List<String> org = Arrays.asList(orgId);
		// 隶属城市ID
		String orgCode = "";
		// 错误信息
		String mesg = "";
		
		for (String s : li) {// 遍历首期\非首期
			for (String str : city){// 遍历城市
				if(str != null && !(str.equals(""))){// 扫除城市空值
					for(String og : org){// 遍历查找隶属当前城市的营业部
						if((og != null && !og.equals(""))){// 此处扫除空值，防止角标越界
							String ts = og.split("_")[1];
							if(str.equals(ts)){// 匹配城市和营业部
								if(orgCode.equals("")){
									orgCode = og.split("_")[0];
								}else{
									orgCode = orgCode+","+og.split("_")[0];
								}
							}
						}
					}
				}
				coo.setConfigType(s);
				coo.setConfigCity(str);
				coo.setId(IdGen.uuid());
				coo.setConfigLoanName(bro.getLoanName());
				coo.setConfigLoanCode(bro.getLoanCode());
				coo.setConfigZdr(bro.getLoanIdcard());
				coo.setDelFlag(GlobarContants.NODEL);
				coo.setConfigYy(orgCode);
				coo.preInsert();
				orgCode = "";
				
				try {
					creditLocationListDao.add(coo);
				} catch (Exception e) {
					logger.error("添加失败", e);
					mesg = mesg +"新增可用债权配置失败</br>"+e.getMessage();
					e.printStackTrace();
				}
			}
		}
		if(("").equals(mesg)){
			return "true";
		}
		return mesg;
	}
	
	/**
	 * 初始化配置页面数据
	 * 2015年12月19日
	 * by 李志伟
	 * @param loanCode
	 * @return
	 */
	public CreditOkListObj goEdit(String loanCode){
		// 初始化配置页面数据
		CreditOkListObj coo = creditLocationListDao.goEdit(loanCode);
		String[] sso = coo.getConfigYy().split(",");
		String str = "";
		for (int i = 0; i < sso.length; i++) {
			String orgName = this.selectNameById(sso[i]);
			if(str.equals("")){
				str = orgName;
			}else{
				str = str+","+orgName;
			}
		}
	    coo.setConfigYy(str);
	    return coo;
	}
	
	/**
	 * 批量删除
	 * 2015年12月22日
	 * by 李志伟
	 * @param map
	 */
	public void creditBatchDel(String inst) {
		
		String[] code = inst.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("del", GlobarContants.DEL);
		creditLocationListDao.batchCreditDel(map);
	}
	
	/**
	 * 查询须过滤的的债权人列表
	 * 2016年2月29日
	 * By 柳慧
	 * @param map
	 * @return
	 */
	public List<String> getloanIdCards(Map<String, Object> map){
		return creditLocationListDao.getloanIdCards(map);
	}
	
	/**
	 * 根据省市Id查找营业部__三级联动使用
	 * 2016年3月8日
	 * by 李志伟
	 * @param prctId
	 * @return
	 */
	public List<Org> findOrgList(Map<String, Object> map) {
		List<Org> list = creditLocationListDao.findOrgList(map);
		return list;
	}
	
	/**
	 * 根据营业部ID获取营业部
	 * 2016年3月12日
	 * by 李志伟
	 * @param string
	 * @return
	 */
	public String selectNameById(String string) {
		String str = creditLocationListDao.selectNameById(string);
		return str;
	}
	
	/**
	 * 根据省份ID获取营业部
	 * 2016年3月31日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<Org> getOrgByProvince(Map<String, Object> map) {
		List<Org> list = creditLocationListDao.getOrgByProvince(map);
		return list;
	}
}