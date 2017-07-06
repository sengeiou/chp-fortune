
package com.creditharmony.fortune.creditor.matching.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorHistoryDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;

/**
 * @Class Name MatchingCreditorHistoryManager
 * @author 胡体勇
 * @Create In 2015年12月10日
 */
@Service
public class MatchingCreditorHistoryManager extends CoreManager<MatchingCreditorHistoryDao, CreditorTradeEx> {
	
	/**
	 * 分页查询
	 * 2015年12月9日
	 * By 胡体勇
	 * @param page
	 * @param creditorTradeEx
	 * @return
	 */
	public Page<CreditorTradeEx> findPage(Page<CreditorTradeEx> page,CreditorTradeEx creditorTradeEx){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		pageBounds.setCountBy("matchingId");
		// 转换产品查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getProduct())){
			String[] str = creditorTradeEx.getProduct().split(",");
			creditorTradeEx.setProductCode(Arrays.asList(str));
		}
		// 转换营业部查询条件
		if(!StringUtils.isEmpty(creditorTradeEx.getOrg())){
			String[] str = creditorTradeEx.getOrg().split(",");
			creditorTradeEx.setOrgCode(Arrays.asList(str));
		}
		if(!StringUtils.isEmpty(creditorTradeEx.getMatchingBillDayStr())){
			String [] str = creditorTradeEx.getMatchingBillDayStr().split(",");
			List<Integer> mblst = new ArrayList<Integer>();
			for(int i=0; i<str.length;i++){
				mblst.add(Integer.valueOf(str[i]));
			}
			creditorTradeEx.setMatchingBillDays(mblst);
		}
		List<String> lendStatusLst = new ArrayList<String>();
		lendStatusLst.add(LendState.CX.value);
		lendStatusLst.add(LendState.KHFQ.value);
		lendStatusLst.add(LendState.HKSB.value);
		creditorTradeEx.setLendStatusLst(lendStatusLst);
		creditorTradeEx.setMatchingStatus(MatchingStatus.YTJ.value);
		
		if(!"".equals(creditorTradeEx.getSelStatus()) &&  null != creditorTradeEx.getSelStatus()){
			if(creditorTradeEx.getSelStatus().equals(MatchingStatus.CXCP.value)){
				creditorTradeEx.setCxStatus(MatchingStatus.CXCP.value);
			}else{
				creditorTradeEx.setNcxStatus(MatchingStatus.CXCP.value);
			}
		}
		
		PageList<CreditorTradeEx> pageList = (PageList<CreditorTradeEx>) this.dao.findList(creditorTradeEx,pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

}
