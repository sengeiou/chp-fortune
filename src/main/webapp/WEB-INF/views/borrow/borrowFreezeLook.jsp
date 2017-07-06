<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form action="${ctx}/borrowFreeze/goBorrowFreezeLook" method="post" id="subSearchForm">
			<input id="subPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="subPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input type="hidden" id="creditValueId" name="creditValueId" value="${creditValueId}">
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
   </form>
<div id="borrowFreezeLookListDiv">
  <%@ include file="/WEB-INF/views/borrow/borrowFreezeLookList.jsp"%>
</div>
