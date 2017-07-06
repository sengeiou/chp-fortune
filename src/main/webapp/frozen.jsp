<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
  var result = window.confirm("提示用户已被冻结，无权限进行操作");
  window.location.href = "${pageContext.request.contextPath}/ibm_security_logout";
</script>