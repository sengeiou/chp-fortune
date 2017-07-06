<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户详细</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/customerPoolHandle.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	
<%-- 	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/teleSaleManager.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/distributeManager.js"></script> --%>
	
</head>
<body>
	<ul class="nav nav-tabs" id="myTab">
		<li class="active"><a href="#distribute" data-toggle="tab">客户分配</a></li>
		<c:if test="${isTeleManager == '1' }"></c:if> <!-- 电销理财专员时显示  -->
			<li class=""><a href="#advisory" data-toggle="tab">咨询信息</a></li>
		
	</ul><br/>
	<input type="hidden" id="custCode" value="${custCode}" />
	<input type="hidden" id="id" value="${id}" />
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in  active" id="distribute">
			<%@include file="distributeManager.jsp" %>
		</div>
		<c:if test="${isTeleManager == '1' }"></c:if> <!-- 电销理财专员时显示  -->
			<div class="tab-pane fade in advisoryTabDiv" id="advisory"></div>
		
	</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="提交" class="btn cf_btn-primary" id="subSubmit"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>