package com.creditharmony.fortune.creditor.matching.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingBorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeBorrowView;
import com.creditharmony.fortune.creditor.matching.view.CreditorTradeMonthAbleView;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorExcel;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.deduct.entity.ext.AttachmentEx;

/**
 * 
 * @Class Name MatchingCreditorDAO
 * @author 柳慧
 * @Create In 2015年12月11日
 */
@FortuneBatisDao
public interface MatchingCreditorDao extends CrudDao<MatchingCreditorEx> {
	
	/**
	 * * 根据待债权推荐信息条件 查询符合条件的集合，同时分页
	 * 2016年1月18日
	 * By 柳慧
	 * @param ex
	 * @param pageBounds
	 * @return
	 */
	public List<MatchingCreditorView>  findPage(Map<String,Object> ex,PageBounds pageBounds
			);
	
	/**
	 * 通过待推荐ID获取待推荐信息
	 * 2015年12月16日
	 * By 柳慧
	 * @param matchingId 待推荐ID
	 * @return
	 */
	public MatchingCreditorView getMatchingCreditorViewByMatchingId(
			String matchingId);
	
	/**
	 * 根据主键查询待推荐信息
	 * 2015年12月23日
	 * By 周俊
	 * @param matchingId
	 * @return
	 */
	public MatchingCreditorEx selectByPrimaryKey(String matchingId);
	
	/**
	 * 通过过滤条件 查询匹配结果
	 * 2015年12月26日
	 * By 柳慧
	 * @param mbEx
	 * @return
	 */
	public List<Borrow> getsqppByMbEx(MatchingBorrowEx mbEx);
	
	/**
	 * 根据主键修改 待推荐信息
	 * 2015年12月28日
	 * By 柳慧
	 * @param mc
	 */
	public int updateByPrimaryKeySelective(MatchingCreditorEx mc);
	
	/**
	 * 通过出借客户编号获取既有历史债权列表 首期
	 * 2016年1月18日
	 * By 柳慧
	 * @param customerCode
	 * @return
	 */
	public List<CreditorTradeBorrowView> getCreditorTradeBorrowView(
			Map<String,String > hisListParam);

	/**
	 * 通过待推荐债权ID获取既有历史债权列表 非首期
	 * 2015年12月29日
	 * By 柳慧
	 * @param lendCode
	 * @return
	 */
	public List<CreditorTradeBorrowView> getCreditorTradeBorrowViewNOFrist(
			 Map<String,Object> paramShowMap);
	
	/**
	 * 通过出借编号 获取分期收益表中分期收益表该笔出借还款的最大期数
	 * 2015年12月29日
	 * By 柳慧
	 * @param lendCode
	 * @return
	 */
	public Integer getPhasenumberByLendCode(String lendCode);

	/**
	 * 通过出借编号获取既有历史债权列表 月满盈
	 * 2015年12月30日
	 * By 柳慧
	 * @param customerCode
	 * @return
	 */
	public List<CreditorTradeMonthAbleView> getCreditorTradeMonthAbleViews(
			Map<String,String > hisListParam);
	/**
	 * 查询查询列表的总数
	 * 2016年1月19日
	 * By 柳慧
	 * @param search
	 * @return
	 */
	public Map<String, Object> findTotal(MatchingCreditorEx search);

	/**
	 * 导出功能
	 * 2016年1月20日
	 * By 柳慧
	 * @param search
	 * @return
	 */
	public List<MatchingCreditorExcel> outExcel(MatchingCreditorEx search);
	
	/**
	 * 获取匹配记录
	 * 2016年1月20日
	 * By 柳慧
	 * @param param 
	 * @return
	 */
	public Map<String, Long> getppll(Map<String, String> param);

	/**
	 * 通过出借编号获取首期起息日期
	 * 2016年1月21日
	 * By 柳慧
	 * @param param
	 * @return
	 */
	public Date getFristLendDay(Map<String, String> param);
	
	/**
	 * 获取待自动匹配信息集合
	 * 2016年2月16日
	 * By 柳慧
	 * @param param 传入测试 以键-值的方式传值 ，键包括以下：<br>
	 *           matchingBillDay 账单日，  Integer ；<br>
	 *           productCodes 产品编号 ，   字符串List集合；<br>
	 *           matchingFirstdayFlag 账单类型，   字符串；<br>
	 *           orgCode  营业部，       字符串List集合， 可为空；<br>
	 *           matchingStatus 债权状态 ， 字符串 ；<br>
	 *           cityIds 城市编号  字符串List集合       可为空。<br>
	 * @return
	 */
	public List<String> getautoMatchingIdAll(
			Map<String, Object> param);

	/**
	 * 获取用户的派发债券数量
	 * @author 王鹏飞
	 * @param map 查询参数
	 * @return 债券数量
	 */
	public int getCountOfBond(Map<String, Object> map);

	/**
	 * 查询推荐债券列表
	 * @author 王鹏飞
	 * @param map 查询参数
	 * @param pageBounds 分页信息
	 * @return PageList<MatchingCreditorView>
	 */
	public PageList<MatchingCreditorView> listDistributeRecommendation(
			Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 查询一条符合条件的推荐记录
	 * @author 王鹏飞
	 * @param map 查询参数
	 * @return 推荐信息
	 */
	public MatchingCreditor getFirstBondMatching(Map<String, Object> map);

	/**
	 * 修改推荐人信息
	 * @author 王鹏飞
	 * @param matching 推荐信息
	 */
	public void updateMatchingUserID(MatchingCreditor matching);

	/**
	 * 添加匹配人
	 * @param map 参数
	 */
	public int distributeOtherBond(Map<String, Object> map);

	/**
	 * 弃单
	 * @param map 参数
	 */
	public int dropOrders(Map<String, Object> map);

	/**
	 * 当日剩余未匹配账单量
	 * @param map 参数
	 * @return 账单量
	 */
	public int getUnDoCountOfDay(Map<String, Object> map);

	/** 
	 * 查出正在自动匹配待推荐信息的ID集合
	 * 2016年2月17日
	 * By 柳慧
	 * @param filterMap
	 * @return
	 */
	public List<String> getFiltermatchingIds(Map<String, Object> filterMap);

	/**
	 * 查询附件信息
	 * 2016年2月19日
	 * By 柳慧
	 * @param map
	 * @return
	 */
	public AttachmentEx getAttachmentEx(Map<String, Object> map);


	/**
	 * 删除匹配人
	 * 2016年4月9日
	 * By 朱杰
	 * @param map
	 */
	public void delMatchingUser(Map<String, Object> map);

	/**
	 * 获取赎回影响的待推荐债权列表
	 * 2016年2月23日
	 * By 陈广鹏
	 * @param matchingCreditorEx
	 * @return
	 */
	public List<MatchingCreditorEx> findRedeemList(
			MatchingCreditorEx matchingCreditorEx);

	/**
	 * 根据用户弃单
	 * @param matching 
	 */
	public void delUserOrders(MatchingCreditor matching);
	
	/**
	 * 通过产品编号 获取产品默认匹配利率
	 * 2016年3月17日
	 * By 柳慧
	 * @param paramMap
	 * @return
	 */
	public Map<String,BigDecimal>  getProductDefaultMchRateByCode(Map<String, Object> paramMap);

	/**
	 * 通过债权ID 判断债权是否属于同类债权
	 * 2016年3月30日
	 * By 柳慧
	 * @param paramLst
	 * @return
	 */
	public List<String> getDictLoanTypeByCreditMonableIds(String [] paramLst);

	/**
	 * 获取指定日期的上一期债权匹配ID
	 * 2016年3月31日
	 * By 陈广鹏
	 * @param linitDay
	 * @return
	 */
	public MatchingCreditor getLastMatchingCreditor(MatchingCreditor creditor);
	
	/**
	 * 获取非首期待推荐债权数量
	 * 2016年4月8日
	 * By 刘雄武
	 * @return
	 */
	public MatchingCreditor getcountMatching(Map<String, Object> paramMap);
	
	public Integer getMonthBorrowCountByMathingId(Map<String, String> param);

	/**
	 * 排它锁记录
	 * 2016年4月29日
	 * By 韩龙
	 * @param matchingCreditorEx
	 * @return
	 */
	public MatchingCreditorEx getForUpdate(MatchingCreditorEx matchingCreditorEx);

	/**
	 * 通过待推荐债权主键ID获取实体
	 * 2016年5月7日
	 * By 柳慧
	 * @param matchingIds
	 * @return
	 */
	public List<MatchingCreditorEx> getMatchingCreditorByMatchingIds(
			Map<String,List<String>> paramap);

	/**
	 * 获取出借在指定日期前一期推荐债权数据
	 * 2016年5月12日
	 * By 陈广鹏
	 * @param mcSearch
	 * @return
	 */
	public MatchingCreditorEx findPreviousMatchingCreditor(
			MatchingCreditorEx mcSearch);

	public int getCountByEx(MatchingCreditorEx paramMc);
}