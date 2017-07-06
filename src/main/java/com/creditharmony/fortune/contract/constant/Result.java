package com.creditharmony.fortune.contract.constant;

/**
 * 状态码:后台业务逻辑判断，用来限制表单提交请求。根据码值的不同。页面友好的提升
 * @author 王鹏飞
 */
public enum Result {

	zero(0),one(1),two(2),three(3),four(4),five(5);
	
	public int value;
	
	Result(int status){
		this.value = status;
	}

}
