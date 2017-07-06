<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借人信息变更已办列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lenderChange/lenderChangeSearch.js"></script>
</head>
<body class="body_r">

	<form:form id="searchForm" modelAttribute="query" action="${ctx}/lenderChange/finish/list" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<div class="box1 mb10">
		    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		    <tr>
			<td>
				<label class='lab'>客户姓名：</label>
				<input type="text" name="custName" value="${query.custName}"  class="cf_input_text178" />
			</td>
			<td><label class='lab'>城市：</label>
				<sys:cityselect name="addrCity" value="${query.addrCity}" multiple="true"  />
			</td>
		
			<td><label class='lab'>客户编号：</label>
				<input type="text" name="custCode" value="${query.custCode}"   class="cf_input_text178" />
			</td>
			</tr>
		</table>
			<div class="tright pr30 pb5 pt10">
			  <input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			  <input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			</div>
	
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<sys:columnCtrl pageToken="lenderChange_lenderChangFinis"></sys:columnCtrl>
	<div class='box5'>
		<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
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
					<th>出借状态</th>
					<th>操作</th>
					<shiro:hasPermission name="apply:consult:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.custName}</td> --%>
					<td>${item.custCode}</td>
					<td>${fns:getProvinceLabel(item.addrProvince)}</td>
					<td>${fns:getCityLabel(item.addrCity)}</td>
					<td>${item.departmentName}</td>
					<td>${item.teamManagerName}</td>
					<td>${item.managerName}</td>
					<td>${item.teamName}</td>
					<td>${fns:dictName(dicts.tz_customer_src,item.dictCustSource,'-')}</td>
					<td>${fns:getFormatDate(item.createTime,"yyyy-MM-dd HH:mm:ss")}</td>
					<td>${item.dictChangeState}</td>
				    <td>
				    	<auth:hasPermission key="cf:lenderchangefinish:transact">
				        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=1">详细</a>
				        </auth:hasPermission>
				       
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>