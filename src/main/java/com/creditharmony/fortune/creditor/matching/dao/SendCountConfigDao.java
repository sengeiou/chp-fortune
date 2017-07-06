package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.SendCountConfig;
import com.creditharmony.fortune.creditor.matching.view.SendCountConfigView;

/**
 * 派发数量设置表
 * @author 王鹏飞
 * @Class Name SendCountConfigDao
 * @Create In 2016年02月04日
 */
@FortuneBatisDao
public interface SendCountConfigDao extends CrudDao<SendCountConfig> {
	

    /**
     * 添加配置
     * @param record 派发配置
     * @return 影响行数
     */
    public int insertSelective(SendCountConfig record);

    /**
     * 根据主键查询配置
     * @param id 主键
     * @return 配置信息
     */
    public SendCountConfig selectByPrimaryKey(String id);

    /**
     * 更新配置信息
     * @param record 配置信息
     * @return  影响行数
     */
    public int updateByPrimaryKeySelective(SendCountConfig record);

	/**
	 * 查询派发配置
	 * @param userId 用户id
	 * @return 派发信息
	 */
    public SendCountConfig getSendCountConfig(String userId);

	/**
	 * 查询合同制作专员列表
	 * @param map 查询参数
	 * @param pageBounds 分页信息
	 * @return PageList<SendCountConfigView>
	 */
	public PageList<SendCountConfigView> listDistributeConfig(Map<String, Object> map,
			PageBounds pageBounds);
	
	/**
	 * 查询合同制作专员列表(不分页)
	 * 2016年3月18日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<SendCountConfigView> listDistributeConfig(Map<String, Object> map);

	/**
	 * 合同制作专员列表
	 * @param map 查询参数
	 * @return List<SendCountConfigView>
	 */
	public List<SendCountConfigView> listMakeContractStaff(Map<String, Object> map);

	/**
	 * 删除合同制作专员配置
	 * @param map 参数
	 */
	public void delSendCountConfig(Map<String, Object> map);
	
	/**
	 * 更新在岗/离岗
	 * 2016年3月18日
	 * By 朱杰
	 * @param map
	 */
	public void updateStatus(Map<String, Object> map);

}