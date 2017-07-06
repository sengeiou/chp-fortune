
package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;

/**
 * 债权发送列表
 * @Class Name creditorSendDao
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
@FortuneBatisDao
public interface CreditorSendDao extends CrudDao<CreditorTradeEx> {
	
	/**
	 * 债权发送列表分页查询
	 * 2015年12月9日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param pageBounds
	 * @return
	 */
	public List<CreditorTradeEx> findList(CreditorTradeEx creditorTradeEx,PageBounds pageBounds);
	
	/**
	 * 批量发送协议时查询推荐id下是否有已发送
	 * 2015年12月23日
	 * By 胡体勇
	 * @param map
	 * @return
	 */
	public AttachmentEx getAttachmentExInfo(Map<String,Object> map);
	
	/**
	 * 获取文件附件的列表
	 * 2015年12月28日
	 * By 胡体勇
	 * @param map
	 * @return
	 */
	public List<Attachment> getAttachment(Map<String,Object> map);
	
	/**
     * 查询推荐id下的所有借款人的匹配金额
     * 2015年12月24日
     * By 胡体勇
     * @param creditorTrade
     * @return
     */
	public List<CreditorTrade> findCreditorTradeInfo(CreditorTrade creditorTrade);
    
	/**
     * 修改债权交易状态
     * 2015年12月24日
     * By 胡体勇
     * @param creditorTrade
     * @return
     */
	public int updateTradeCreditStatus(CreditorTrade creditorTrade);

	/**
	 * 修改分期收益表中的废止状态
	 * 2015年12月24日
	 * By 胡体勇
	 * @param loanphase
	 * @return
	 */
	public int updatePhaseDiscardStatus(Loanphase loanphase);
	
	/**
	 * 撤销债权时修改待推荐债权表里的对应信息
	 * 2015年12月24日
	 * By 胡体勇
	 * @param matchingCreditor
	 * @return
	 */
	public int updateMatchingCreditor(MatchingCreditor matchingCreditor);
    
	/**
	 *撤销债权时修改可用债权表里的可用债权价值
	 * 2015年12月24日
	 * By 胡体勇
	 * @param borrow
	 * @return
	 */
	public int updateBorrow(Borrow borrow);
	
	/**
	 * 撤销债权时修改月满盈可用债权池里的可用债权价值
	 * 2015年12月24日
	 * By 胡体勇
	 * @param borrowMonthable
	 * @return
	 */
	public int updateBorrowMonthable(BorrowMonthable borrowMonthable);
	
	/**
	 * 可用债权表里的可用债权价值
	 * 2015年12月24日
	 * By 胡体勇
	 * @param borrow
	 * @return
	 */
	public Borrow findCreditValue(Borrow borrow);
	
	/**
	 * 查询月满盈可用债权池里的可用债权价值
	 * 2015年12月24日
	 * By 胡体勇
	 * @param borrowMonthable
	 * @return
	 */
	public BorrowMonthable findAvailabevValue(BorrowMonthable borrowMonthable);
	
	/**
	 * 统计页面显示金额总数
	 * 2016年1月13日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public CreditorTradeEx countMoney(CreditorTradeEx creditorTradeEx);

	/**
	 * 获取账单收取方式
	 * 2016年7月15日
	 * By 韩龙
	 * @param customerCode
	 * @return
	 */
	public Map<String, String> getCollectionMethod(String customerCode);
}
