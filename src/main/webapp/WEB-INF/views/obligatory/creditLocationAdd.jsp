<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>可用债权配置添加</title>
<script type="text/javascript" src="${ctxWebInf}/js/obligatory/obligatory.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>

</head>
<body>
	<div class="body_new">
		<div class="box1 mb10">
			
			<form id="searchForm" action="${ctx}/creditLocation/addPageSearch" method="post">
				<input id="pageNo" name="pageNo" type="hidden" value="${olv.page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${olv.page.pageSize}"/>
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td><label class="lab">借款人姓名：</label>
							<input type="text" name="configLoanName" value="***" class="cf_input_text178" /></td>
						<td><label class="lab">借款人证件号：</label>
							<input type="text" name="configZdr" value="***" class="cf_input_text178" /></td>
						<%-- <td><label class="lab">借款人姓名：</label>
							<input type="text" name="configLoanName" value="${olv.coo.configLoanName}" class="cf_input_text178" /></td>
						<td><label class="lab">借款人证件号：</label>
							<input type="text" name="configZdr" value="${olv.coo.configZdr}" class="cf_input_text178" /></td>
					 --%>
					</tr>
				</table>		
				<div class="tright pr30">
					<input type="button" value="搜索" class="btn cf_btn-primary" onclick="javascript:window.searchForm.submit();" />
					<input type="reset" value="清除" class="btn cf_btn-primary"/>
				</div>
			</form>
		</div>
			<div class='box5'>
				<table cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-condensed" width="100%">
					<thead>
					<tr>
						<th>序号</th>
						<th>借款人</th>
						<th>借款人证件号</th>
						<th>操作</th>
					</tr>
					</thead>
					<c:forEach items="${olv.page.list}" var="item" varStatus="index">
						<tr>
							<td>${index.count}</td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${item.configLoanName}</td> --%>
							<td>***</td>
							<%-- <td>${fns:formatLoanIdcard(item.configZdr)}</td> --%>
							<td><input type="button" value="配置" class="cf_btn_edit" onclick="javascript:window.location.href='${ctx}/creditLocation/openWindow?&name=${olv.coo.configLoanName}'+'&code=${item.creditValueId}'"/></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		<div class="tright mt20 pr30">
			<input type="button" class="btn cf_btn-primary" value="返回" onClick="window.location.href='${ctx}/creditLocation/loadCreditLocationList'"/>
		</div>
		<div class="pagination">${olv.page}</div>
	</div>
</body>
</html>