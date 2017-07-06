<%@ page contentType="text/html;charset=UTF-8" %> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的微服列表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
	<script src="${ctxWebInf }/js/customer/customerTransfer.js" type="text/javascript"></script>
</head>
<body>
	<div class="body_r">
	<div class="box1 mb10">
	<form id="searchForm" action="${ctx}/consultationUser/consultationInfoList" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="menuId" name="menuId" type="hidden" value="${param.menuId}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<sys:message content="${message}"/>
		 <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			    <td>
					<label class='lab'>客户姓名：</label>
					<input type="text" name="name" maxlength="50" class="cf_input_text178" value="${userConsultation.name }"/>
			    </td>
			    <td>
					<label class='lab'>咨询时间：</label>
					<input type="text" name="searchDate" maxlength="50" class="cf_input_text178 Wdate" onfocus="WdatePicker()" value="${fns:getFormatDate(userConsultation.searchDate,'yyyy-MM-dd')}"/>
				</td>
				<td>
					<label class='lab'>客户手机号：</label>
					<input type="text" name="mobile" maxlength="11" class="cf_input_text178" value="${userConsultation.mobile }"/>
				</td>
			</tr>
			</table>
			<div class="tright pr30 pb5 pt10">
			    <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			    <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
	</form>
	</div>
	<div class='box5'>
	<table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
		<thead>
			<tr>
				<th>咨询时间</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>咨询内容</th>
			</tr>
		</thead>
		<c:forEach items="${page.list}" var="item" varStatus="sta">
			<tr>
				<td>${fns:getFormatDate(item.consultationDate,'yyyy-MM-dd HH:mm:ss')}</td>
				<%-- <td>${item.name }</td> --%>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.mobile }</td> --%>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<td id = "${sta.index+1}"></td>
				<script>
					document.getElementById('${sta.index+1}').innerText='${item.consultContent}';
				</script>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div class="pagination">${page}</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">理财经理信息（注：只有理财经理和工号对应上才能查询出结果）</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>