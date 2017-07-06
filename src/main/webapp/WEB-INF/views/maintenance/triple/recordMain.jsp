<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:choose>
     <c:when test="${html eq 'deliveryRecord'}">
	  <form action="${ctx}/maintenance/tripleMaintain/deliveryRecord" method="post" id="subSearchForm">
     </c:when>
     <c:when test="${html eq 'sendRecord'}">
	  <form action="${ctx}/maintenance/tripleMaintain/sendRecord" method="post" id="subSearchForm">
	</c:when>
     <c:otherwise>
     <form id="searchForm" action="${ctx }/maintenance/tripleSend/userSynchro" method="post">
     </c:otherwise>
</c:choose>
		<input id="subPageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="subPageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="phone" value="${phone }">
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
   </form>
<div id="recordDiv">
<c:choose>
     <c:when test="${html eq 'deliveryRecord'}">
        <%@ include file="/WEB-INF/views/maintenance/triple/deliveryRecordList.jsp"%>
     </c:when>
     <c:when test="${html eq 'sendRecord'}">
		<%@ include file="/WEB-INF/views/maintenance/triple/sendRecordList.jsp"%>     
	</c:when>
     <c:otherwise>
     <form id="searchForm" action="${ctx }/maintenance/tripleSend/userSynchro" method="post">
     </c:otherwise>
</c:choose>
</div>