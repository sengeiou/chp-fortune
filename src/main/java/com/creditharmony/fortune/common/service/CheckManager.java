package com.creditharmony.fortune.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.dao.CheckDao;
import com.creditharmony.fortune.common.entity.Check;

/**
 * 全程流程
 * @Class Name CheckManager
 * @author 韩龙
 * @Create In 2015年12月10日
 */
@Service
public class CheckManager extends CoreManager<CheckDao,Check> {
	
	/**
	 * 
	 * 2015年12月23日
	 * By 韩龙
	 * @param lendCode 出借编号
	 * @param operatorInfo 操作内容
	 * @param operatorType 操作类型
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int addCheck(String lendCode,String operatorInfo,String operatorType){
		Date date = new Date();
		String userName=UserUtils.getUser(UserUtils.getUserId()).getName();
		Check check = new Check();
		check.preInsert();
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		return dao.insertSelective(check);
	}
	
	/**
	 * 增加全程留痕
	 * 2016年6月4日
	 * By 陈广鹏
	 * @param lendCode 出借编号
	 * @param operatorInfo 操作信息
	 * @param operatorType 操作类型
	 * @param operateAffiliated 操作主表主键
	 * @param fortuneLogNode 日志节点
	 * @return
	 */
	public int addCheck(String lendCode,String operatorInfo,
			String operatorType, String operateAffiliated, FortuneLogNode fortuneLogNode){
		Date date = new Date();
		String userName = UserUtils.getUser(UserUtils.getUserId()).getName();
		Check check = new Check();
		check.preInsert();
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		check.setOperateAffiliated(operateAffiliated);
		check.setOperateNode(fortuneLogNode.getName());
		return dao.insertSelective(check);
	}
	
	public int addCheck(String lendCode,String operatorInfo,
			String operatorType, String operateAffiliated, FortuneLogNode fortuneLogNode, User user){
		Date date = new Date();
		// String userName=UserUtils.getUser(UserUtils.getUserId()).getName();
		Check check = new Check();
		// check.preInsert();
		check.setApplyCode(lendCode);
		check.setOperator(user.getName());
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		check.setOperateAffiliated(operateAffiliated);
		check.setOperateNode(fortuneLogNode.getName());
		check.setId(IdGen.uuid());
		check.setCreateBy(user.getId());
		check.setCreateTime(new Date());
		check.setModifyBy(user.getId());
		check.setModifyTime(new Date());
		return dao.insertSelective(check);
	}
	
	/**
	 * 增加全程留痕
	 * 2016年6月4日
	 * By 陈广鹏
	 * @param lendCode 出借编号
	 * @param operatorInfo 操作信息
	 * @param operatorType 操作类型
	 * @param operateAffiliated 操作主表主键
	 * @param fortuneLogNode 日志节点
	 * @return
	 */
	public int addCheckBatch(String lendCode,String operatorInfo,
			String operatorType, String operateAffiliated, FortuneLogNode fortuneLogNode){
		Date date = new Date();
		String userName="MQ";
		Check check = new Check();
		check.setId(IdGen.uuid());
		check.setCreateBy("MQ");
		check.setCreateTime(new Date());
		check.setModifyBy("MQ");
		check.setModifyTime(new Date());
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		check.setOperateAffiliated(operateAffiliated);
		check.setOperateNode(fortuneLogNode.getName());
		return dao.insertSelective(check);
	}
	
	public int addCheckDjr(String lendCode, String operatorInfo, String operatorType, String operateAffiliated, String userName) {
		Date date = new Date();
		Check check = new Check();
		check.preInsert();
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		check.setOperateAffiliated(operateAffiliated);
		check.setOperateNode("转投大金融");
		return dao.insertSelective(check);
	}
	
	/**
	 * 
	 * 2015年12月23日
	 * By 韩龙
	 * @param lendCode 出借编号
	 * @param operatorInfo 操作内容
	 * @param operatorType 操作类型
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int addCheck(String lendCode,String operatorInfo,String operatorType,String userId){
		Date date = new Date();
		String userName=null;
		User user =UserUtils.get(userId);
		if(user!=null){
			userName=user.getName();
		}else{
			userName=userId;
		}
		Check check = new Check();
		//check.preInsert();
		check.setId(IdGen.uuid());
		check.setCreateBy(userId);
		check.setModifyBy(userId);
		check.setCreateTime(date);
		check.setModifyTime(date);
		check.setApplyCode(lendCode);
		check.setOperator(userName);
		check.setOperateInfo(operatorInfo);
		check.setOperateType(operatorType);
		check.setOperateTime(date);
		return dao.insertSelective(check);
	}
	
	/**
	 * 获取全程留痕列表
	 * 2016年1月21日
	 * By 陈广鹏
	 * @param page
	 * @param check
	 * @return
	 */
	public Page<Check> findCheckList(Page<Check> page, Check check) {
		String sortString = BmConstant.OPERATE_TIME_DESC;
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(), Order.formString(sortString));
		PageList<Check> dataList = (PageList<Check>)dao.findList(check, pageBounds);
		PageUtil.convertPage(dataList, page);
		return page;
	}
	
	public void updateLendCode(String oldLendCode, String newLendCode){
		dao.updateLendCode(oldLendCode, newLendCode);
	}
	
	public List<Check> findList(Check check){
		return dao.findList(check);
	}
}
