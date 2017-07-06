<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 
<form id="hisForm" action="${ctx}/backInterestCommon/toHistory" method="post">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
    <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
    <input type="hidden" name="lendCode" value="${lendCode}"/>
    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
			<thead>
				<tr>
			        <th>操作人</th>
			        <th>操作内容</th>
			        <th>操作时间</th>
			        <th>操作类型</th>
				</tr>
			</thead>
		    <c:forEach items="${page.list}" var="item">
				<tr>
			        <td>${item.operator}</td>
			        <td>${item.operateInfo}</td>
			        <td>${fns:getFormatDate(item.operateTime,"yyyy-MM-dd HH:mm:ss")}</td>
			        <td>${item.operateType}</td>
			    </tr>
		    </c:forEach>
	</table>
</form>

<div class="page">
   	<div class="pagination">${page}</div>
</div>