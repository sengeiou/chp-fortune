<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div id="dataList">
	<form id="traceForm" method="post">
	    <div>
		 	<input id="pageNo1" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize1" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="pageSize1" name="applyCode" type="hidden" value="${check.applyCode }"/>
			<input id="pageSize1" name="operateAffiliated" type="hidden" value="${check.operateAffiliated }"/>
		    <h3 class="pt10 pb10">${check.applyCode }的历史留痕</h3><br/>
	    	<%@ include file="/WEB-INF/views/deduct/fullFlowMarkList.jsp"%>
	    </div>
    </form>
</div>
