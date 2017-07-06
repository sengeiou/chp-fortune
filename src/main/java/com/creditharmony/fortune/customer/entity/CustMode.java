package com.creditharmony.fortune.customer.entity;

import java.util.List;

/**
 * 查询客户信息model
 * 2016年12月24日
 * @author 郭强
 */
public class CustMode {

	private   String   rtn;
	private   List<String>  list;
	private   Integer   num;
	private   Integer   count;
	public String getRtn() {
		return rtn;
	}
	public void setRtn(String rtn) {
		this.rtn = rtn;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
