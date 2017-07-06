package com.creditharmony.fortune.common.entity;


/**
 * 返回Json消息
 * @Class Name ReturnMsg
 * @author 朱杰
 * @Create In 2016年4月14日
 */
public class ReturnMsg {
   
	private String message;
	
	public ReturnMsg(){};
	
	public ReturnMsg(String message){
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}