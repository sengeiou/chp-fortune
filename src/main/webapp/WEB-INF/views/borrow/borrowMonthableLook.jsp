<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form action="${ctx}/borrowMonthable/goBorrowMonthableLook" method="post" id="subSearchForm">
	<input id="subPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="subPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
 	<input type="hidden" name="creditMonableId" value="${borrowMonthable.creditMonableId }" id="creditMonableId">
	<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
   </form>
<div id="borrowMonthableLookListDiv">
  <%@ include file="/WEB-INF/views/borrow/borrowMonthableLookList.jsp"%>
</div>