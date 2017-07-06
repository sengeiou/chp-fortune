package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowPush;
import com.creditharmony.fortune.borrow.entity.ex.BorrowLookEx;
import com.creditharmony.fortune.borrow.view.BorrowAllotView;
import com.creditharmony.fortune.template.entity.BorrowOutExcel;

/**
 * 可用债权
 * @Class Name BorrowDao
 * @author 周俊
 * @Create In 2015年12月3日
 */
@FortuneBatisDao
public interface BorrowDao extends CrudDao<Borrow>{
	/**
	 * 更新提前结清
	 * @param pushborrow
	 */
	public void updatePushBorrowEarly(Map<String,Object> map);
	
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 */
	public int updatePushBorrowAddIsUpdate(Map<String,Object> map);
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 */
	public int updateBorrowAdd(Map<String,Object> map);
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 */
	public int updateBorrowPushBatchNo(Map<String,Object> map);
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 */
	public List<Borrow> findBorrowByDjrByMap(Map<String,Object> map);
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 */
	public void insertBorrowPushMap(Map<String,Object> map);
	/**
	 * 2016-12-24 16:44:27
	 * By 高旭
	 * @param map债权查询条件
	 */
	public List<Borrow> findBorrowByDjr(Map<String,Object> map);
	/**
	 *	2017年1月4日13:46:13
	 * By 高旭
	 * @param map债权查询条件
	 */
	public List<Borrow> findPushBorrowRelease(Map<String,Object> map);
	
	/**
	 * 查询总金额数 和总条数  IsNull
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getPushBorrowAddIsUpdate(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数  IsNull
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowMoneyAndCountContractNoIsNull(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数 IsNotNull
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowMoneyAndCountContractNoIsNotNull(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数 Is
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowPushMoneyAndCountIs(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowMoneyAndCount(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowPushMoneyAndCountNot(Map<String, Object> map);
	/**
	 * 查询总金额数 和总条数
	 * 2016-12-24 14:54:57
	 * By 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getBorrowPushMoneyAndCount(Map<String, Object> map);
	/**
	 * 查询总金额数   每条四舍五入在累加 
	 * 2016-12-16 10:51:25
	 * By 
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoneyOneByOne(Map<String, Object> map);
	/**
	 * 分页查询债权推送列表
	 * 2016-12-13 11:28:18
	 * By 高旭
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<Borrow> findPushBorrow(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询总金额数债权推送列表
	 * 2016-12-13 11:31:41
	 * By 高旭
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoneyByPushBorrow(Map<String, Object> map);
	
	/**
	 * 插入推送大金融记录表
	 * 2016-12-15 14:15:02
	 * By 高旭
	 * @return
	 */
	public int insertBorrowPush(Map<String,Object> borrowPushs);
	/**
	 * 更新borrow债权价值
	 * 2016-12-15 14:15:02
	 * By 高旭
	 * @return
	 */
	public int updateBorrowByBorrowPush(Map<String, Object> borrowbyPush);
	/**
	 *	更新borrow_pish推送债权记录
	 * 2016-12-15 14:15:02
	 * By 高旭
	 * @return
	 */
	public int updatePushBorrowStatus(Map<String, Object> borrowbyPush);
	
	/**
	 * 查看可用债权信息
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<BorrowLookEx> borrowLook(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 分页查询
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<Borrow> findBorrow(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询所有
	 * 2016年4月20日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public PageList<Borrow> findBorrow(Map<String, Object> map);
	
	/**
	 * 查询总金额数
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String, Object> map);
	
	/**
	 * 获取匹配符合条件的可用债权
	 * 2015年12月17日
	 * By 柳慧
	 * @param ppMap 匹配条款集合
	 * @return
	 */
	public List<Borrow> getsqppByMap(Map<String, Object> ppMap);
	
	/**
	 * 通过实体查询可用债权集合 （分页）
	 * 2015年12月19日
	 * By 柳慧
	 * @param 
	 * @param pageBounds
	 * @return
	 */
	public PageList<Borrow> findBorrowByEx(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 通过债权ID集合 获取符合条件的可用债权集合
	 * 2015年12月21日
	 * By 柳慧
	 * @param creditValueIds 债权ID集合 
	 */
	public List<Borrow> getBorrowByCreditValueIds(List<String> creditValueIds);
	
	/**
	 * 导Excel
	 * 2015年12月22日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowOutExcel> findOutExcel(Map<String, Object> map);
	
	/**
	 * 通过实体参数获取满足条件的数据
	 * 2016年1月7日
	 * By 柳慧
	 * @param paramBo
	 * @return
	 */
	public Borrow getByEenity(Borrow paramBo);
	
	/**
	 * 根据可用债权价值获取债权分配信息
	 * 2016年4月28日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	public BorrowAllotView getBorrowAllot(String creditValueId);
	
	/**
	 * 计算月满盈或月满盈可用债权回池的数量
	 * 2016年6月27日
	 * By 周俊
	 * @param creditValueId
	 * @return
	 */
	public int countBackTool(String creditValueId);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 * by 高士芳
	 */
	public int updateBorrow(Borrow entity);
	
}