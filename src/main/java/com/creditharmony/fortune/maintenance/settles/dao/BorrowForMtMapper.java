package com.creditharmony.fortune.maintenance.settles.dao;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.maintenance.settles.entity.BorrowInfo;

/**
 * 可用债权-Dao
 * 
 * @Class Name BorrowForMtMapper
 * @author
 * @Create In 2015年12月22日
 */
@FortuneBatisDao
public interface BorrowForMtMapper {
   
    /**
     * 获取可用债权价值
     * 2016年1月13日
     * By
     * @param key
     * @return BorrowInfo
     */
	public BorrowInfo selBorrow(BorrowInfo borrowInfo);
    
    /**
     * 更新可用债权价值
     * 2016年1月13日
     * By
     * @param key
     * @return int
     */
	public int updBorrow(BorrowInfo borrowInfo);
	
    /**
     * 更新可用债权价值
     * 2016年1月13日
     * By
     * @param key
     * @return int
     */
	public int updBorrowInfo(BorrowInfo borrowInfo);	
}