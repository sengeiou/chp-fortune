<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	  <form action="${ctx}/his/borrowAolltHis" method="post" id="searchForm">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input type="hidden" id="creditValueId" name="creditValueId" value="${creditValueId}">
				<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	   </form>
<div id="allotHisListDiv">
  <%@ include file="/WEB-INF/views/borrow/allotBorrowLogList.jsp"%>
</div>