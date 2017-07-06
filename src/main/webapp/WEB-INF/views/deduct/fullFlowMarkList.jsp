<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
	<thead>
	<tr>
	    <th>操作人</th>
	    <th>操作内容</th>
	    <th>操作时间</th>
	    <th>操作类型</th>
	</tr>
	</thead>
	<c:forEach items="${page.list}" var="check" >
	<tr>
	    <td>${ check.operator}</td>
	    <td>${ check.operateInfo}</td>
	    <td><fmt:formatDate value="${ check.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    <td>${ check.operateType}</td>
	</tr>
	</c:forEach>
</table>
<div class="pagination">${page}</div>
