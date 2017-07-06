package com.creditharmony.fortune.borrow.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.borrow.dao.LoanphaseDao;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.creditor.matching.view.LoanphaseBorrowView;
/**
 * 分期收益service
 * @Class Name LoanphaseManager
 * @author 柳慧
 * @Create In 2015年12月16日
 */
 
@Service
public class LoanphaseManager extends CoreManager<LoanphaseDao,Loanphase>{
	
	@Autowired
	private LoanphaseDao LoanphaseDao;
	
	/**
	 * 通过待推荐ID获取借款人编号集合
	 * 2015年12月16日
	 * By 柳慧
	 * @param matchingId 待推荐ID
	 * @return
	 */
	public List<String> getLoanIdsbyMatchingId(String matchingId){
		return LoanphaseDao.getLoanIdsbyMatchingId(matchingId);
	}
	
	/**
	 * 通过债权推荐ID获取既有及历史债权列表 非月满盈首期
	 * 2015年12月17日
	 * By 柳慧
	 * @param matchingId 债权推荐ID
	 * @return
	 */
	public List<LoanphaseBorrowView> getLoanphaseBorrow(String matchingId) {
		return LoanphaseDao.getLoanphaseBorrow(matchingId);
	}
	
	/**
	 * 通过债权推荐ID获取既有及历史债权列表 非月满盈非首期
	 * 2015年12月24日
	 * By 柳慧
	 * @param matchingId
	 * @return
	 */
	public List<LoanphaseBorrowView> getLoanphaseBorrowNoFrist(String matchingId) {
		return LoanphaseDao.getLoanphaseBorrowNoFrist(matchingId);
	}
}






