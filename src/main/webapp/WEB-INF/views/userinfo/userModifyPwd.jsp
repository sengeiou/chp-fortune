<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#oldPassword").focus();
			$("#inputForm").validate({
				rules: {
				},
				messages: {
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
		<li><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<div class="container">
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPwd" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="form-group">
			<label class="lab"><span class="red">*</span>旧密码：</label>
			<div>
				<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3" class="cf_input_text178"/>
				
			</div>
		</div>
		<div class="form-group">
			<label class="lab"><span class="red">*</span>新密码：</label>
			<div>
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="cf_input_text178"/>
				
			</div>
		</div>
		<div class="form-group">
			<label class="lab"><span class="red">*</span>确认新密码：</label>
			<div>
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="cf_input_text178" equalTo="#newPassword"/>
				
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