<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户详细</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/customerDistributeHandle.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	<%-- <script type="text/javascript" src="${ctxWebInf}/js/customerPool/teleSaleManager.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/distributeManager.js"></script> --%>
</head>
<body>
	<ul class="nav nav-tabs" id="myTab">
		<li class="active"><a href="#information" data-toggle="tab">查看</a></li>
		<li class=""><a href="#advisory" data-toggle="tab">咨询信息</a></li>
		<c:if test="${dataType == '3' }">
			<li class=""><a href="#distribute" data-toggle="tab">客户分配</a></li>
		</c:if>
	</ul><br/>
	<div class='dataDiv' id="containerDiv">
		<%@include file="customerDistributeHandInner.jsp" %>
	</div>
	
</body>
</html>



