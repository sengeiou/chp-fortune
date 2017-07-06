<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div id="dataList">
	<form id="traceForm" method="post" action="${ctx}/contract/changeApplyList/fullTrace">
	    <div>
		 	<input id="pageNo1" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize1" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="applyCode" name="applyCode" type="hidden" value="${check.applyCode }"/>
	    </div>
    </form>
    <div id="traceList"><%@ include file="/WEB-INF/views/deduct/fullFlowMarkList.jsp"%></div>
</div>
