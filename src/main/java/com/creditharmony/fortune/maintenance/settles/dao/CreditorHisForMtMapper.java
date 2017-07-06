package com.creditharmony.fortune.maintenance.settles.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.CreditorHis;

/**
 * 债权管理记录
 * @Class Name CreditorHisMapper
 * @author 武文涛
 * @Create In 2016年1月6日
 */
@FortuneBatisDao
public interface CreditorHisForMtMapper {
	
    /**
     * 添加债权管理记录
     * 
     * @param record
     * @return int
     */
    public int insCreditorHis(CreditorHis record);
    
    /**
     * 查询匹配金额
     * 2016年1月6日
     * By 武文涛
     * @param record
     * @return List<CreditorHis>
     */
    public CreditorHis selectOperateMoney(CreditorHis record);
    
    /**
     * 查询释放金额
     * 2016年1月6日
     * By 武文涛
     * @param nodeId
     * @param beforDate
     * @param dictCheckNode
     * @param list
     * @return List<CreditorHis>
     */
    public CreditorHis selectOperateMoneyT(
		@Param(value="nodeId")String nodeId, 
		@Param(value="beforDate")String beforDate,
		@Param(value="dictCheckNode")String dictCheckNode, 
		@Param(value="list")List<String> list
	);
}