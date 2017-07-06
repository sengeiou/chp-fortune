
package com.creditharmony.fortune.creditor.matching.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.fortune.type.CreditTradestate;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDetailDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.BorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;

/**
 * 已推荐债权查看
 * @Class Name MatchingCreditorManager
 * @author 胡体勇
 * @Create In 2015年12月11日
 */
@Service
public class MatchingCreditorDetailManager extends CoreManager<MatchingCreditorDetailDao, BorrowEx> {
	
    /**
     * 根据推荐id查询对应的出借申请信息
     * 2015年12月15日
     * By 胡体勇
     * @param creditorTradeEx
     * @return
     */
	public CreditorTradeEx findloanInfoByMatchingId(CreditorTradeEx creditorTradeEx){
		CreditorTradeEx creditor = this.dao.findloanInfoByMatchingId(creditorTradeEx);
		return creditor;
	}
	
	/**
	 * 根据不同的节点去匹配不同的表（债权池；月满盈可用债权池）
	 * 2015年12月15日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public List<BorrowEx> joinTable(CreditorTradeEx creditorTradeEx){
		List<BorrowEx> list = new ArrayList<BorrowEx>();
		BorrowEx borrowEx = new BorrowEx();
		creditorTradeEx.setTradeCreditStatus(CreditTradestate.WKSBGB.value);
		List<BorrowEx> borrowExList = super.dao.findTradeidByMatchingId(creditorTradeEx);
		if(ArrayHelper.isNotEmpty(borrowExList)){
			for(int i = 0; i<borrowExList.size();i++){
				// 根据不同的债权节点查询不同的债权池
				if(Node.KYZQ.value.equals(borrowExList.get(i).getCreditNode())){
					borrowEx.setTradeId(borrowExList.get(i).getTradeId());
					borrowEx.setMarchingId(borrowExList.get(i).getMarchingId());
					// 查询可用债权表对应数据 
					
					BorrowEx returnBorrowEx = super.dao.joinBorrowForFinishedMc(borrowEx);
					if(returnBorrowEx!=null){
						list.add(returnBorrowEx);
					}
				}else if(Node.YMYKY.value.equals(borrowExList.get(i).getCreditNode())){
					borrowEx.setTradeId(borrowExList.get(i).getTradeId());
					borrowEx.setMarchingId(borrowExList.get(i).getMarchingId());
					// 查询月满盈可用债权池表对应数据
					borrowEx = super.dao.joinBorrowMonthAbleForFinishedMc(borrowEx);
					list.add(borrowEx);
				}
			}
		}
		return list;
	}
	
	/**
	 *  债权撤销记录列表及分页
	 * 2016年1月15日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public List<BorrowEx> findPage(CreditorTradeEx creditorTradeEx){
		List<BorrowEx> borrowList = new ArrayList<BorrowEx>();
		BorrowEx borrowEx = new BorrowEx();
		// 根据推荐ID查询是否有撤销记录
		List<BorrowEx> list = super.dao.isCancel(creditorTradeEx);
		if(ArrayHelper.isNotEmpty(list)){
			creditorTradeEx.setTradeCreditStatus(CreditTradestate.WKSBGB.value);
			List<BorrowEx> borrowExList = super.dao.findTradeidByMatchingId(creditorTradeEx);
			if(ArrayHelper.isNotEmpty(borrowExList)){
				for(int i = 0; i<borrowExList.size();i++){
					// 根据不同的债权节点查询不同的债权池
					if(Node.KYZQ.value.equals(borrowExList.get(i).getCreditNode())){
						borrowEx.setTradeId(borrowExList.get(i).getTradeId());
						// 查询可用债权表对应数据
						borrowEx = super.dao.joinBorrow(borrowEx); 
						borrowList.add(borrowEx);
					}else if(Node.YMYKY.value.equals(borrowExList.get(i).getCreditNode())){
						borrowEx.setTradeId(borrowExList.get(i).getTradeId());
						// 查询月满盈可用债权池表对应数据
						borrowEx = super.dao.joinBorrowMonthAble(borrowEx);
						borrowList.add(borrowEx);
					}
				}
			}
		}
		return borrowList;
	}
}
