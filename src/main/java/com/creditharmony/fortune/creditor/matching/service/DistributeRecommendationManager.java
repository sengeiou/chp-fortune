package com.creditharmony.fortune.creditor.matching.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.FileMakerJobStatus;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.dao.CreditorStatisticsDao;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.dao.SendCountConfigDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorStatistics;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.entity.SendCountConfig;
import com.creditharmony.fortune.creditor.matching.view.MatchingCreditorView;
import com.creditharmony.fortune.creditor.matching.view.SendCountConfigView;

/**
 * 派发数量配置
 * @Class Name DistributeRecommendationManager
 * @author 王鹏飞
 * @Create In 2016年02月04日
 */
 
@Service
public class DistributeRecommendationManager {

	@Autowired
	private SendCountConfigDao sendCountConfigDao;
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;
	@Autowired
	private CreditorStatisticsDao creditorStatisticsDao;
	@Autowired
	private CreditorStatisticsManager creditorStatisticManager;
	@Autowired
	private CreditorAidManager creditorAidManager;
	
	
	/**
	 * 查询派发配置
	 * @param userId 用户id
	 * @return 配置信息
	 */
	public SendCountConfig getSendCountConfig(String userId) {
		return sendCountConfigDao.getSendCountConfig(userId);
	}

	/**
	 * 查询派发配置
	 * @param id 主键id
	 * @return 配置信息
	 */
	public SendCountConfig selectByPrimaryKey(String id) {
		return sendCountConfigDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 文件制作配置列表
	 * @param page 分页信息
	 * @param map 查询参数
	 * @return Page<SendCountConfigView>
	 */
	public Page<SendCountConfigView> listDistributeConfig(
			Page<SendCountConfigView> page, Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("1");
		PageList<SendCountConfigView> dataList = (PageList<SendCountConfigView>) sendCountConfigDao
				.listDistributeConfig(map, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}
	
	/**
	 * 文件制作配置列表（无分页）
	 * 
	 * 2016年3月18日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<SendCountConfigView> listDistributeConfig(Map<String, Object> map) {
		return sendCountConfigDao.listDistributeConfig(map);
	}

	/**
	 * 合同制作专员列表
	 * @param map 查询参数
	 * @return List<SendCountConfigView>
	 */
	public List<SendCountConfigView> listMakeContractStaff(
			Map<String, Object> map) {
		return sendCountConfigDao.listMakeContractStaff(map);
	}

	/**
	 * 添加合同制作专员配置
	 * @param send 合同制作专员配置
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void insertSendCountConfig(SendCountConfig send) {
		sendCountConfigDao.insertSelective(send);
	}

	/**
	 * 删除合同制作专员配置
	 * @param id 主键集合
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void delSendCountConfig(String[] id) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		sendCountConfigDao.delSendCountConfig(map);
	}

	/**
	 * 修改文件制作专员派发数量及在岗状态
	 * @param sendCountConfig 合同信息
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateSendCountConfig(SendCountConfig sendCountConfig) {
		sendCountConfigDao.updateByPrimaryKeySelective(sendCountConfig);
		// 更新工作量统计表
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", sendCountConfig.getUserId());
		param.put("statisticsDate", new Date());
		List<CreditorStatistics> list = creditorStatisticsDao.selectBySelective(param);
		if(list.size()>0){
			CreditorStatistics current = list.get(0);
			current.setCreditTaskCnt(sendCountConfig.getLeaderSendCount());
			creditorStatisticsDao.update(current);
			MatchingCreditor matching = new  MatchingCreditor();
			matching.preUpdate();
			matching.setMatchingUserId(sendCountConfig.getUserId());
			matching.setDropTime(matching.getModifyTime());
			 
			matchingCreditorDao.delUserOrders(matching);
		}	
		// 离岗 
		if(FileMakerJobStatus.LG.value.equals(String.valueOf(sendCountConfig.getStatus()))){
			MatchingCreditor matching = new  MatchingCreditor();
			matching.preUpdate();
			matching.setMatchingUserId(sendCountConfig.getUserId());
			matching.setDropTime(matching.getModifyTime());
			 
			matchingCreditorDao.delUserOrders(matching);
		}
	}
	
	/**
	 * 批量在岗/离岗
	 * 2016年3月18日
	 * By 朱杰
	 * @param ids
	 * @param status
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void updateStatus(String[] ids,int status) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		//获取选中的配置列表
		List<SendCountConfigView> list = sendCountConfigDao.listDistributeConfig(map);
		
		map.put("status", status);
		//更新在岗/离岗
		sendCountConfigDao.updateStatus(map);
		
		// 离岗 
		if(FileMakerJobStatus.LG.value.equals(String.valueOf(status))){
			for (SendCountConfigView elem : list) {
				MatchingCreditor matching = new  MatchingCreditor();
				matching.preUpdate();
				matching.setMatchingUserId(elem.getUserId());
				matching.setDropTime(matching.getModifyTime());
				 
				matchingCreditorDao.delUserOrders(matching);
			}			
		}
	}
	
	/**
	 * 获取用户的派发债券数量
	 * @author 王鹏飞
	 * @param userId 用户id
	 * @return 派发数量
	 */
	public int getCountOfBond(Map<String, Object> map) {
		return matchingCreditorDao.getCountOfBond(map);
	}

	/**
	 * 用户派发首单
	 * @author 王鹏飞
	 * @param userId 用户id
	 * @return 派发条数
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int distributeFirstBond() {
		Map<String,Object> map = new HashMap<String,Object>();
		MatchingCreditor matching = new MatchingCreditor();
		matching.preUpdate();
		matching.setMatchingUserId(UserUtils.getUserId());
		List<String> notIn = creditorAidManager.getFiltermatchingIds();
		if(notIn != null && notIn.size() > 0){
			map.put("notIn", notIn);
		}
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("opeType", Constant.CREDIT_DISTRIBUTED);
		map.put("filtermatchingIds", creditorAidManager.getFiltermatchingIds());
		//分配一条待推荐债权
		map.put("count", 1);
		map.put("mc", matching);
		return this.distributeOtherBond(map);
	}

	/**
	 * 查询推荐债券列表
	 * @author 王鹏飞
	 * @param page 分页信息
	 * @param map 查询参数
	 * @return Page<MatchingCreditorView>
	 */
	public Page<MatchingCreditorView> listDistributeRecommendation(
			Page<MatchingCreditorView> page, Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		pageBounds.setCountBy("matching_id");
		PageList<MatchingCreditorView> dataList = (PageList<MatchingCreditorView>) matchingCreditorDao.listDistributeRecommendation(map, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}

	/**
	 * 换单(首期)
	 * @param matchingId 推荐记录id
	 * @param userId 匹配人id
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void changeOrder(String userId) {
		//放弃首单
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("matchingFirstdayFlag", BillState.SQ.value);
		map.put("matchingStatus", new String[]{MatchingStatus.DTJ.value,MatchingStatus.CXCP.value});
		MatchingCreditor matching = new  MatchingCreditor();
		matching.setMatchingUserId(userId);
		matching.preUpdate();
		matching.setDropTime(matching.getModifyTime());
		map.put("mc", matching);
		
		matchingCreditorDao.delMatchingUser(map);
		//派发新的首单
		int cnt = this.distributeFirstBond();
		//换单统计更新
		creditorStatisticManager.updateStatistic(
				userId, 
				cnt,
				Constant.CREDIT_CHANGE, 
				BillState.SQ.value,
				null
		);
	}

	/**
	 * 派发债权
	 * @param map 参数
	 * @return 
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int distributeOtherBond(Map<String, Object> map) {
		// 更新债权数量
		int updateCnt = matchingCreditorDao.distributeOtherBond(map);
		// 更新统计
		creditorStatisticManager.updateStatistic(
				UserUtils.getUserId(), 
				updateCnt,
				map.get("opeType").toString(), 
				map.get("matchingFirstdayFlag").toString(),
				null
		);
		return updateCnt;
	}

	/**
	 * 弃单
	 * @param map 参数
	 * @return 无返回
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void dropOrders(Map<String, Object> map) {
		int updateCnt = matchingCreditorDao.dropOrders(map);
		creditorStatisticManager.updateStatistic(
				UserUtils.getUserId(), 
				updateCnt, 
				Constant.CREDIT_DROP, 
				null,
				null
		);
	}

	/**
	 * 当日剩余未匹配账单量
	 * @param map 参数
	 * @return 账单量
	 */
	public int getUnDoCountOfDay(Map<String, Object> map) {
		return matchingCreditorDao.getUnDoCountOfDay(map);
	}
	
	/**
	 * 获取非首期待推荐债权数量
	 * 2016年4月8日
	 * By 刘雄武
	 * @return
	 */
	public MatchingCreditor getcountMatching(Map<String,Object> map){
		return matchingCreditorDao.getcountMatching(map);
	}
}
