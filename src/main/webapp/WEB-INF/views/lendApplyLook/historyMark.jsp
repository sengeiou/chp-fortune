<%@ page   contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>全程留痕</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div class="diabox_c">
    <h3 class="pt10 pb10">${apply_code}的历史留痕</h3>
    <table id="contentTable" class="table table-striped table-bordered table-condensed data-list-table">
	    <thead>
	    <tr>
	        <th>操作人</th>
	        <th>操作内容</th>
	        <th>操作时间</th>
	        <th>操作类型</th>
	    </tr>
	    </thead>
	    <c:forEach items="${page.list}" var="back">
	    	<td align="center">${back.apply_code}</td>
			<td align="center">${back.cust_name}</td>
			<td align="center">${back.org_name}</td>
			<td align="center">${back.apply_lend_money}</td>
	    </c:forEach>
	</table>
    <div class="pagination">${page}</div>
</body>
</html>
