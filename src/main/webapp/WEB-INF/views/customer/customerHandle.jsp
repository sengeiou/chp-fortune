<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户详细</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript" src="${ctxWebInf}/js/customer/customerInfo.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/customer/customerDetail.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/customer/customerHandle.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/static/jquery-plugin/jquery.json-2.4.js" charset="utf-8"></script>
</head>
<body>
	
	<form id="searchFormParams" action="" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${pageSearch.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${pageSearch.pageSize}"/>
		<input type="hidden" name="custName" maxlength="50" class="cf_input_text178" value="${customerSearch.custName }"/>
		<input type="hidden" name="custCode" maxlength="50" class="cf_input_text178" value="${customerSearch.custCode }"/>
		<input type="hidden" name="custMobilephone" maxlength="11" class="cf_input_text178" value="${customerSearch.custMobilephone }"/>
		<input type="hidden" name="dictCustSource"  value="${customerSearch.dictCustSource}">
		<input type="hidden" name="custState"  value="${customerSearch.custState}">
		<input type="hidden" name="custLending"  value="${customerSearch.custLending}">
	</form>

	<ul class="nav nav-tabs" id="myTab">
		<c:if test="${belongToCurrentUser }">
			<li class="active" ><a href="#information" data-toggle="tab">客户基本信息</a></li>
		</c:if>
		<li class="<c:if test="${not belongToCurrentUser }">active</c:if>"><a href="#advisory" data-toggle="tab">咨询信息</a></li>
		<li class=""><a href="#investment" data-toggle="tab">投资信息</a></li>
		<c:if test="${belongToCurrentUser }">
			<li class="" ><a href="#modify" data-toggle="tab">修改历史</a></li>
		</c:if>
	</ul><br/>
	
	<div id="myTabContent" class="tab-content">
		<c:if test="${belongToCurrentUser }">
			<div class="tab-pane fade in active" id="information" >
				<c:choose>
					<c:when test="${customer.custState==3 or customer.custState==4 or customer.custState==5 }">
						<%@include file="customerDetail.jsp" %>
					</c:when>
					<c:when test="${customer.custState==1 or customer.custState==2 }">
						<%@include file="customerInfo.jsp" %>
					</c:when>
				</c:choose>
			</div>
		</c:if>
		<div class="tab-pane fade in advisoryTabDiv <c:if test="${not belongToCurrentUser }">active</c:if>" id=advisory>
			<%@include file="customerAdvisoryList.jsp" %>
		</div>
		<div class="tab-pane fade in investmentTabDiv" id=investment>
		</div>
		<c:if test="${belongToCurrentUser }">
			<div class="tab-pane fade in modifyTabDiv" id=modify >
			</div>
		</c:if>
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