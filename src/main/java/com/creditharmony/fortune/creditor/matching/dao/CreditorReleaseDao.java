package com.creditharmony.fortune.creditor.matching.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorRelease;
import com.creditharmony.fortune.creditor.matching.vo.CreditorReleaseVo;

/**
 * 债权到期释放Dao
 * @author xurongsheng
 * @date 2016年11月23日 下午2:40:52
 *
 */
@FortuneBatisDao
public interface CreditorReleaseDao extends CrudDao<CreditorRelease>{
	/**
	 * 分页查询 债权到期释放列表
	 * @author xurongsheng
	 * @date 2016年11月23日 下午3:14:15
	 * @param paramMap
	 * @param pageBounds
	 * @return
	 */
	public PageList<CreditorReleaseVo> findList(Map<String, Object> paramMap, PageBounds pageBounds);
	
	/**
	 * 查询 债权到期释放列表 总金额
	 * @author xurongsheng
	 * @date 2016年11月24日 下午2:57:16
	 * @param paramMap
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String, Object> paramMap);
	
	/**
	 * 查询 债权到期释放列表 总金额[先四舍五入后求和]
	 * @author xurongsheng
	 * @date 2016年11月24日 下午2:57:16
	 * @param paramMap
	 * @return
	 */
	public BigDecimal findAllMoney2(Map<String, Object> paramMap);
	
	/**
	 * 根据ids,查询 债权到期释放列表
	 * @author xurongsheng
	 * @date 2016年11月29日 上午11:07:51
	 * @param ids
	 * @return
	 */
	public List<CreditorReleaseVo> findListByIds(Map<String, Object> paramMap);
	
	/**
	 * 查询 债权到期释放列表
	 * @author xurongsheng
	 * @date 2016年12月28日 下午1:51:18
	 * @param paramMap
	 * @return
	 */
	public List<CreditorReleaseVo> findList(Map<String, Object> paramMap);
	
	/**
	 * 更新 债权
	 * @author xurongsheng
	 * @date 2016年11月29日 上午11:04:52
	 * @param paramMap
	 * @return
	 */
	public int updateBorrow(Map<String, Object> paramMap);
	
	/**
	 * 新增 债权管理记录表
	 * @author xurongsheng
	 * @date 2016年11月29日 上午11:05:33
	 * @param paramMap
	 * @return
	 */
	public int insertCreditorHis(Map<String, Object> paramMap);
	
	/**
	 * 更新 到期债权释放信息(释放)
	 * @author xurongsheng
	 * @date 2016年11月29日 下午3:48:14
	 * @param paramMap
	 * @return
	 */
	public int updateCreditorReleaseForRelease(Map<String, Object> paramMap);
	
	/**
	 * 更新 到期债权释放信息(转出)
	 * @author xurongsheng
	 * @date 2016年11月29日 下午2:52:55
	 * @param paramMap
	 * @return
	 */
	public int updateCreditorReleaseForOut(Map<String, Object> paramMap);
	
	/**
	 * 更新 到期债权释放信息(确认转出)
	 * @author xurongsheng
	 * @date 2016年11月29日 下午2:52:55
	 * @param paramMap
	 * @return
	 */
	public int updateCreditorReleaseForConfirm(Map<String, Object> paramMap);
	
	/**
	 * 更新 到期债权释放信息(结清)
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:42:48
	 * @param paramMap
	 * @return
	 */
	public int updateCreditorReleaseForEarlySettlement(Map<String, Object> paramMap);
	
	/**
	 * 检测能否释放-查询
	 * @author xurongsheng
	 * @date 2016年12月27日 下午2:59:37
	 * @return
	 */
	public Map<String, Object> findCheck(Map<String, Object> paramMap);
	
	/**
	 * 获取 债权情况为负的 借款人姓名
	 * @author xurongsheng
	 * @date 2017年4月20日 下午2:27:57
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findZqLoanName(Map<String, Object> paramMap);
	
	/**
	 * 获取 债权释放金额 第一部分:释放前为负,释放后为正.(取正数部分的总额)
	 * @author xurongsheng
	 * @date 2017年1月11日 下午1:47:33
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> getReleaseFirstPart(Map<String, Object> paramMap);
	
	/**
	 * 获取 债权释放金额 第二部分:释放前后均为正.(取变更金额的总额)
	 * @author xurongsheng
	 * @date 2017年1月11日 下午1:47:33
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> getReleaseSecordPart(Map<String, Object> paramMap);
	
	/**
	 * 新增 债权释放统计
	 * @author xurongsheng
	 * @date 2017年1月11日 上午11:08:18
	 * @param paramMap
	 * @return
	 */
	public int insertReleaseStat(Map<String, Object> paramMap);
	
}
