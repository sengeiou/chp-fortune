package com.creditharmony.fortune.sync.data.remote;

import com.creditharmony.core.threenet.entity.ThreeNetOrg;
import com.creditharmony.core.threenet.entity.ThreeNetUser;

/**
 * 三网同步数据接口
 * @Class Name ThreeNetSyncDataService
 * @author 张永生
 * @Create In 2016年3月1日
 */
public interface ThreeNetSyncDataService {

	/**
	 * 执行同步三网所需的组织机构接口
	 * 2016年3月1日
	 * By 张永生
	 * @param org
	 * @return
	 */
	public boolean syncThreeNetOrg(ThreeNetOrg org);
	
	/**
	 * 执行三网所需的用户：财富的理财经理
	 * 2016年3月1日
	 * By 张永生
	 * @param user
	 * @return
	 */
	public boolean syncThreeNetUser(ThreeNetUser user);
	
}
