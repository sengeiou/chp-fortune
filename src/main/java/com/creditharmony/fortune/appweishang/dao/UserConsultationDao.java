package com.creditharmony.fortune.appweishang.dao;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.appweishang.bean.UserConsultation;
import com.creditharmony.fortune.appweishang.bean.EmpUser;
import com.creditharmony.fortune.customer.entity.Customer;

/**
 * 我的微服咨询接口
 * @author colin
 *
 */
@FortuneBatisDao
public interface UserConsultationDao {
	
	/**
	 * 新加新的咨询内容
	 * @param consultationUser
	 */
	 void insertConsultationUserInfo(UserConsultation consultationUser);

	 /**
	  * 查询所有的咨询内容
	  * @param userConsultation
	  * @param pageBounds
	  * @return
	  */
	 PageList<UserConsultation> findConsultationList(UserConsultation userConsultation,PageBounds pageBounds);
}
