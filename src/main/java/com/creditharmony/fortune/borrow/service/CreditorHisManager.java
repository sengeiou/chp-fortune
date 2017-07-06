package com.creditharmony.fortune.borrow.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.Node;
import com.creditharmony.core.fortune.type.OperateType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.CreditorHisDao;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthSplitHisEx;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowMonthBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthSplitView;

/**
 * 债权操作记录
 * @Class Name CreditorHisManager
 * @author 周俊
 * @Create In 2015年12月4日
 */
@Service
public class CreditorHisManager extends CoreManager<CreditorHisDao, CreditorHis>{
	
	@Autowired
	private CreditorHisDao creditorHisDao;
	@Autowired
	private BorrowDao borrowDao;
	
	/**
	 * 添加可用债权分配记录
	 * 2015年12月4日
	 * By 周俊
	 * @param allotMoney
	 * @param creditValueId
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveBorrowAllotLog(CreditorHis creditorHis){
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.KYZQ.value);
		creditorHis.setOperateType(OperateType.ZQFP.value);
		creditorHis.setOperateTime(new Date());
		creditorHis.setOperator(UserUtils.getUser(UserUtils.getUserId()).getName());
		creditorHis.preInsert();
		creditorHisDao.insert(creditorHis);
	}
	
	/**
	 * 获取可用债权分配历史列表
	 * 2016年1月6日
	 * By 周俊
	 * @param page
	 * @param creditValueId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<CreditorHis> findBorrowAllotLog(Page<CreditorHis> page,String creditValueId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("dictCheckNode",Node.KYZQ.value);
		map.put("nodeId",creditValueId);
		map.put("operateType",OperateType.ZQFP.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		PageList<CreditorHis> pageList = (PageList<CreditorHis>)creditorHisDao.findBorrowAllotLog(map, pageBounds);
	    PageUtil.convertPage(pageList, page);
		return page;
	} 
	
	/**
	 * 保存拆分记录
	 * 2016年1月6日
	 * By 周俊
	 * @param splitView
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveBorrowMonthSplitLog(BorrowMonthSplitView splitView){
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.YMY.value);
		creditorHis.setNodeId(splitView.getCreditMonId());
		creditorHis.setOperateType(OperateType.ZQCF.value);
		creditorHis.setMatchingId(IdGen.uuid());
		creditorHis.setBeforMoney(splitView.getLoanAvailabeValue());
		creditorHis.setOperateMoney(splitView.getSplitMoney());
		creditorHis.setAfterMoney(splitView.getSurplusSplitMoney());
		creditorHis.setOperator(UserUtils.getUser(UserUtils.getUserId()).getName());
		creditorHis.setOperateTime(new Date());
		creditorHis.preInsert();
		creditorHisDao.insert(creditorHis);
	}
	
	/**
	 * 获取拆分历史列表
	 * 2016年1月6日
	 * By 周俊
	 * @param page
	 * @param creditMonId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<BorrowMonthSplitHisEx> findBorrowMonthSplitLog(Page<BorrowMonthSplitHisEx> page,String creditMonId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("dictCheckNode",Node.YMY.value);
		map.put("nodeId",creditMonId);
		map.put("operateType",OperateType.ZQCF.value);
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
	    PageList<BorrowMonthSplitHisEx> pageList = (PageList<BorrowMonthSplitHisEx>)creditorHisDao.findBorrowMonthSplitLog(map, pageBounds);
	    if (ArrayHelper.isNotEmpty(pageList)) {
	    	for (BorrowMonthSplitHisEx splitHis : pageList) {
	    		BigDecimal splitRate = ReckonUtils.getSplitRate(splitHis);
	    		splitHis.setSplitRate(splitRate);
	    	}
		}
	    PageUtil.convertPage(pageList, page);
		return page;
	} 
	
	/**
	 * 保存月满盈回池记录
	 * 2015年12月2日
	 * By 周俊
	 * @param backTool
	 */
	@Transactional(readOnly=false,value = "fortuneTransactionManager")
	public void saveBorrowBackTool(BorrowMonthBackToolView backTool){
		CreditorHis creditorHis = new CreditorHis();
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.YMY.value);
		creditorHis.setNodeId(backTool.getCreditMonId());
		creditorHis.setOperateType(OperateType.ZQHC.value);
		// 每分配一次就产生一个债权,就得生成债权推荐id
		creditorHis.setMatchingId(IdGen.uuid());
		creditorHis.setBeforMoney(backTool.getLoanAvailabeValue());
		creditorHis.setOperateMoney(backTool.getBackToolMoney());
		creditorHis.setAfterMoney(backTool.getSurplusBorrowCreditValue());
		creditorHis.setOperateTime(new Date());
		creditorHisDao.insert(creditorHis);
	}
	
	/**
	 * 保存提前结清释放债权人匹配金额记录
	 * 2016年2月24日
	 * By 周俊
	 * @param creditorTrade
	 */
	public void saveFreezeBorrowReleaseLog(CreditorHis creditorHis){
		creditorHis.setId(IdGen.uuid());
		creditorHis.setDictCheckNode(Node.KYZQ.value);
		creditorHis.setOperateType(OperateType.TQJQSFZQRPPJE.value);
		creditorHis.setOperateTime(new Date());
		creditorHis.setOrderBy(UserUtils.getUser(UserUtils.getUserId()).getName());
		creditorHis.preInsert();
		creditorHisDao.insert(creditorHis);
	}
}
