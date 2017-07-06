package com.creditharmony.fortune.change.lend.apply.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.change.lend.apply.entity.BranchBankCode;

@FortuneBatisDao
public interface BranchBankCodeDao extends CrudDao<BranchBankCode>{
	
	public List<BranchBankCode> selectBranchCode(Map<String, Object> params, PageBounds pageBounds);
	
}
