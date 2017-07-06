package com.creditharmony.fortune.customer.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.service.CustomerManager;


/**
 * 权限标签
 * @Class Name PermissionTag
 * @author 张永生
 * @Create In 2016年1月19日
 */
public class CustomerTag extends BodyTagSupport {

	private static final long serialVersionUID = -4229214871614309228L;
	private String managerCode;
	
	public boolean hasPermission(String resourceKey) {
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		if(null==managerCode  || "".equals(managerCode)){
			return false;
		}
		if(null==user){
			return false;
		}
		if(managerCode.equals(user.getId())){
			return true;
		}else {
			return false;
		}
	}
	public int doStartTag() throws JspException {
		if(hasPermission(managerCode)){
			return Tag.EVAL_BODY_INCLUDE;
		}else{
			return Tag.SKIP_BODY;
		}
	}
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
}
