<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loadingMarkShow();
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					loadingMarkHide();
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<div class="container">
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal"><%--
		<form:hidden path="email" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		<sys:ckfinder input="email" type="files" uploadPath="/mytask" selectMultiple="false"/> --%>
		<sys:message content="${message}"/>
		<div class="form-group">
			<label class="lab" for="form-field-1">归属部门：</label>
			<div>
				<label class="lbl">${user.department.name}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">姓名：</label>
			<div>
				<form:input path="name" htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="true"/>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">邮箱：</label>
			<div>
				<form:input path="email" htmlEscape="false" maxlength="50" class="cf_input_text178"/>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">电话：</label>
			<div>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="cf_input_text178"/>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">手机：</label>
			<div>
				<form:input path="mobile" htmlEscape="false" maxlength="50" class="cf_input_text178"/>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">备注：</label>
			<div>
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="cf_input_text178"/>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">用户类型：</label>
			<div>
				<label class="lbl">${fns:dictName(dicts.t_gl_user_type,user.userType, '无')}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">用户角色：</label>
			<div>
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="lab">上次登录：</label>
			<div>
				<label class="lbl">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></label>
			</div>
		</div>
		<div class="clearfix form-actions">
			<div class="tright pr30">
				<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="保 存"/>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>