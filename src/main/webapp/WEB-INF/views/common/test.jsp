<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/test.js" type="text/javascript" ></script>
<title>test</title>

</head>

<body>
     <div width="50%">
     	<form action="${ctx}/common/test/go" method="post" style="width:45%;heigth:150px;float:left" id="form1">
     	<br/>
     	<br/>
     	 <textarea  name="sql" style="width:80%"></textarea>
     	 <input type="button" value="提交" class="go">
     	 <input type="button" value="清空" class="clear">
     	 <div class="result"></div>
     	 
     	</form>
     	
     </div>
      <div width="50%">
     	<form action="${ctx}/common/test/go" method="post" style="width:45%;heigth:150px;float:left" id="form2">
     	<br/>
     	<br/>
     	 <textarea  name="sql" style="width:75%"></textarea>
     	 <input type="button" value="提交" class="go">
     	 <input type="button" value="比较" class="compare">
     	 <input type="button" value="清空" class="clear">
     	 <div class="result"></div>
     	 
     	</form>
     	
     </div>
</body>
</html>