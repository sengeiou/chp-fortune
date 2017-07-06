<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-- <script type="text/javascript" src="${ctxWebInf}/js/deduct/deduct.js"></script> --%>
<div id="diabox_c">
	<form id="searchForm1" action="${ctx}/deductFail/failList" method="post">
	 	<input id="pageNo1" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize1" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="applyCode" name="applyCode" type="hidden" value="${check.applyCode }"/>
	    <br><h3 class="pt10 pb10">${check.applyCode }的历史留痕</h3><br>
	    <div id="dataList">
	    	<%@ include file="/WEB-INF/views/deduct/fullFlowMarkList.jsp"%>
	    </div>
    </form>
</div>
