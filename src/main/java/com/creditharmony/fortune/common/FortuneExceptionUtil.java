package com.creditharmony.fortune.common;

import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;

/**
 * @Class Name FortuneExceptionUtil
 * @author 孙凯文
 * @Create In 2016年5月5日
 */
public class FortuneExceptionUtil {

	public static FortuneException newException(Exception e) {
		FortuneException forException = new FortuneException();

		forException.setMessage(e.getMessage());
		forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		forException.setImportantLevel(FortuneLogLevel.YELLOW.value);

		return forException;
	}

	public static FortuneException newException(Exception e, String code,
			String node, String importantLevel, String remark) {
		FortuneException forException = newException(e);

		forException.setMessage(e.getMessage());
		forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		forException.setImportantLevel(importantLevel);
		forException.setNode(node);
		forException.setLoanCode(code);
		forException.setRemark(remark);

		return forException;
	}

	public static void insertException(FortuneException forException) {
		forException.preInsert();
		FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(
				FortuneExceptionDao.class);
		forDao.insert(forException);
	}
	
	public static void insertExceptionBatch(FortuneException forException) {
//		forException.preInsert();
		forException.setId(IdGen.uuid());
		forException.setCreateBy("MQ");
		forException.setCreateTime(new Date());
		FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(
				FortuneExceptionDao.class);
		forDao.insert(forException);
	}

}
