<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借人信息变更退回列表</title>
	<meta name="decorator" content="default"/>
</head>
<body class="body_r">
    <div class="box1 mb10">
	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/lenderChange/reject/backList" method="post" >	
	    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label class='lab'>客户姓名：</label>
				<input type="text" name="custName" value="${customer.custName}"  class="cf_input_text178" />
			</td>
			<td><label class='lab'>城市：</label>
				<sys:cityselect name="addrCity" value="${customer.addrCity}"  />
			</td>
			<td><label class='lab'>客户编号：</label>
				<input type="text" name="custCode" value="${customer.custCode}"  class="cf_input_text178" />
			</td>
		</tr>
		</table>
			<div class="tright pr30 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
	</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="lenderTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户姓名</th>
				<th>客户编号</th>
				<th>省份</th>
				<th>城市</th>
				<th>营业部</th>
				<th>团队经理</th>
				<th>理财经理</th>
				<th>开发团队</th>
				<th>客户来源</th>
				<th>创建日期</th>
				<th>操作</th>
				<shiro:hasPermission name="apply:consult:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${itemList}" var="item">
			<tr>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${item.customerName}</td> --%>
				<td>${item.customerCode}</td>
				<td>${fns:getProvinceLabel(item.province)}</td>
				<td>${fns:getCityLabel(item.city)}</td>
				<td>${fns:getOrgNameById(item.department)}</td>
				<td>${item.teamManager}</td>
				<td>${item.financing}</td>
				<td>${fns:getOrgNameById(item.team)}</td>
				<td>${fns:dictName(dicts.tz_customer_src,item.customerSource,'-')}</td>
				<td>${fns:getFormatDate(item.applyDate,"yyyy-MM-dd HH:mm:ss")}</td>	
			    <td>
		        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&wobNum=${item.wobNum}&dealType=0&token=${item.token}">办理</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>