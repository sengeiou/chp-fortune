package com.creditharmony.fortune.borrow.facade;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.fortune.borrow.dao.BorrowDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthDao;
import com.creditharmony.fortune.borrow.dao.BorrowMonthableDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.service.BorrowMonthManager;
import com.creditharmony.fortune.borrow.service.BorrowMonthableManager;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.borrow.view.BorrowMonthableBackToolView;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.entity.FortuneException;

/**
 * borrow模块中不带事务的业务层
 * @Class Name BorrowFacade
 * @author 周俊
 * @Create In 2016年4月28日
 */
@Service
public class BorrowFacade {
	
	@Autowired
	private BorrowMonthManager borrowMonthManager;
	@Autowired
	private BorrowMonthableManager borrowMonthableManager;
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private BorrowMonthableDao borrowMonthableDao;
	@Autowired
	private BorrowMonthDao borrowMonthDao;

	/**
	 * 将可用债权批量分配到月满盈债权池并拆分到月满盈可用债权池
	 * 2015年12月2日
	 * By 周俊
	 * @param batchBorrowInfo
	 */
	public void batchSaveBorrowAllotAndSplit(String batchBorrowInfo){
		if(StringUtils.isNotBlank(batchBorrowInfo)){
			String[] batchBorrowInfoArray = batchBorrowInfo.split(",");
			if(ArrayHelper.isNotNull(batchBorrowInfoArray)){
			String message = "";
				for (String borrowInfo : batchBorrowInfoArray) {
					if (StringUtils.isNotBlank(borrowInfo)) {
						Borrow borrow = new Borrow();
						String[] borrowInfoArray = borrowInfo.split(":");
						borrow = borrowDao.get(borrowInfoArray[0]);
						borrow.setVerTime(borrowInfoArray[1]);
						try {
							borrow.setCreditMonId(borrowInfoArray[2]);
							borrow.setBorrowMonthVerTime(borrowInfoArray[3]);
						} catch (ArrayIndexOutOfBoundsException e) {
							borrow.setCreditMonId("");
							borrow.setBorrowMonthVerTime("");
						}
					try {
						borrowMonthManager.borrowAllotAndSplit(borrow);
						} catch (ServiceException e) {
							message = message + e.getMessage();
							FortuneException fortuneException = FortuneExceptionUtil.newException(e,
									borrowInfoArray[0], FortuneLogNode
											.parseByName("可用债权").getCode(),
									FortuneLogLevel.YELLOW.value,"可用债权批量分配时,发生冲突抛的异常");
							FortuneExceptionUtil.insertException(fortuneException);
						}catch (Exception e) {
							message = message + "可用债权池:"+FormatUtils.messageTemplates(borrow.getLoanName(),borrow.getLoanIdcard());
							FortuneException fortuneException = FortuneExceptionUtil.newException(e,
									borrowInfoArray[0], FortuneLogNode
											.parseByName("可用债权").getCode(),
									FortuneLogLevel.YELLOW.value,"可用债权批量分配时,可用债权信息异常");
							FortuneExceptionUtil.insertException(fortuneException);
						}
					}
				}
				if(StringUtils.isNotBlank(message)){
					throw new ServiceException(message);
				}
			}
		}
	}
	
	/**
	 * 月满盈可用债权批量回池
	 * 2016年1月13日
	 * By 周俊
	 * @param creditMonableIdBatch
	 */
	public void borrowMonthableBatchBackTool(String batchBorrowMonthableInfo){
		if (StringUtils.isNotBlank(batchBorrowMonthableInfo)) {
			String[] batchBorrowMonthableInfoArray = batchBorrowMonthableInfo.split(",");
			BorrowMonthableBackToolView toolView = new BorrowMonthableBackToolView();
			String message = "";
			for (String borrowMonthableInfo : batchBorrowMonthableInfoArray) {
				if (StringUtils.isNotBlank(borrowMonthableInfo)) {
					String[] borrowMonthableInfoArray = borrowMonthableInfo.split(":");
					BorrowMonthable borrowMonthable = new BorrowMonthable();
					borrowMonthable.setCreditMonableId(borrowMonthableInfoArray[0]);
					borrowMonthable = borrowMonthableDao.get(borrowMonthable);
					BorrowMonth borrowMonth = new BorrowMonth();
					borrowMonth.setCreditMonId(borrowMonthable.getCreditMonId());
					borrowMonth = borrowMonthDao.get(borrowMonth);
					BigDecimal afterReleaseBorrow = ReckonUtils.getAfterReleaseBorrow(null, borrowMonth, borrowMonthable);
					toolView.setAfterReleaseBorrow(afterReleaseBorrow);
					toolView.setAfterReleaseBorrowRate(borrowMonth.getLoanMonthRate());
					toolView.setBeforeReleaseBorrow(borrowMonthable.getLoanAvailabeValue());
					toolView.setBeforeReleaseBorrowRate(borrowMonthable.getLoanMonthRate());
					toolView.setCreditMonableId(borrowMonthable.getCreditMonableId());
					toolView.setCreditMonId(borrowMonth.getCreditMonId());
					toolView.setBorrowMonthableVerTime(borrowMonthableInfoArray[1]);
					toolView.setBorrowMonthVerTime(borrowMonthableInfoArray[3]);
					try {
						borrowMonthableManager.borrowMonthableBackTool(toolView, borrowMonth, borrowMonthable);
					} catch (ServiceException e) {
						FortuneExceptionUtil.newException(e,
								borrowMonthableInfoArray[0], FortuneLogNode
										.parseByName("月满盈可用债权").getCode(),
								FortuneLogLevel.YELLOW.value, "月满盈可用债权批量分配时,发生冲突抛出异常");
						message = message+e.getMessage();
					}
				}
			}
			if(StringUtils.isNotBlank(message)){
				throw new ServiceException(message);
			}
		}
	}
}
