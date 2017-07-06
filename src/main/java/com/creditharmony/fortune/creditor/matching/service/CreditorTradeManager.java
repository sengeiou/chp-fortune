package com.creditharmony.fortune.creditor.matching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.fortune.creditor.matching.dao.CreditorTradeDao;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeView;

/**
 * 债权交易服务层
 * @Class Name CreditorTradeManager
 * @author 柳慧
 * @Create In 2015年12月15日
 */
 
@Service
public class CreditorTradeManager {
	@Autowired
	private CreditorTradeDao creditorTradeDao;

	/**
	 * 通过债权推荐ID 获取债权交易数量
	 * 2015年12月17日
	 * By 柳慧
	 * @param matchingId
	 * @return
	 */
	public Integer getcountByMatchingId(String matchingId) {
		return creditorTradeDao.getcountByMatchingId(matchingId);
	}
	
	/**
	 * 通过债权推荐Id获取已推荐债权列表
	 * 2015年12月17日
	 * By 柳慧
	 * @param matchingId 债权推荐Id
	 * @return
	 */
	public List<CreditorTradeView> getytjzqlbByMatchingId(String matchingId) {
		return creditorTradeDao.getytjzqlbByMatchingId(matchingId);
	}
	
	
}
