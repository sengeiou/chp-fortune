<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form id="branchCodePagingForm" action="${ctx}/lend/lendApplyAid/openBranchCodeSubList" method="post">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
    <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
    <input type="hidden" id="branchName" name="branchName" value="${params.branchName}"/>
	<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		<thead>
			<tr>
				<th>选择</th>
		        <th>支行代码</th>
		        <th>支行名称</th>
			</tr>
		</thead>
	    <c:forEach items="${page.list}" var="item">
			<tr>
				<td><input type="radio" name="bankCode" value=${item.bankCode}></td>
		        <td>${item.bankCode}</td>
		        <td>${item.bankName}</td>
		    </tr>
	    </c:forEach>
	</table>
</form>
<div class="page">
   	<div class="pagination">${page}</div>
</div>