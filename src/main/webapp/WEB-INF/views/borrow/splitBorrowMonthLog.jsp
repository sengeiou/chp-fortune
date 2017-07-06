<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form action="${ctx}/his/borrowMonthSplitHis" method="post" id="subSearchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input type="hidden" id="creditMonId" name="creditMonId" value="${creditMonId}">
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
   </form>
  <div id="splitHisListDiv">
  <%@ include file="/WEB-INF/views/borrow/splitBorrowMonthLogList.jsp"%>
</div>