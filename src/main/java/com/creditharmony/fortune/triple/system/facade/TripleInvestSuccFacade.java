package com.creditharmony.fortune.triple.system.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.creditharmony.fortune.triple.system.service.TripleInvestSuccService;

/**
 * @Class Name SendTripleInfo
 * @author 胡体勇
 * @Create In 2016年5月16日
 */
@Service
public class TripleInvestSuccFacade {
	
	/**日志对象*/
	protected Logger logger = LoggerFactory.getLogger(TripleInvestSuccFacade.class);
	
	private TripleInvestSuccService tripleInvestSuccService ;
	
	/**
	 * 根据客户编号、出借编号查出出借申请记录
	 * 2016年07月27日
	 * By ceh
	 * @param customerCode
	 * @param lendCode
	 * @param type [I:成单	O:到期	D:业绩交割]
	 */
	public void investSucc(String customerCode, String lendCode, String type){
		tripleInvestSuccService.investSucc( customerCode, lendCode, type);
	}
}
