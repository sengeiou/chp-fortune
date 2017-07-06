<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <table class='table table-striped table-bordered table-condensed table2 subTable data-list-table' width="100%">   
	    <thead>
	    <tr>
	        <th>序号</th>
	        <th>出借人</th>
	        <th>匹配金额</th>
	        <th>还款期数</th>
	        <th>剩余资金</th>
	    </tr>
	    </thead>
	    <c:forEach items="${page.list}" var="item" varStatus="s">
	    <tr>
	        <th>${s.index+1}</th>
	        <!-- 屏蔽客户姓名/手机号/身份证号 -->
	        <th>***</th>
	        <%-- <th>${item.name }</th> --%>
	        <th>${fns:getFormatNumber(item.tradeMoney,'￥#,##0.00')}</th>
	        <th>${item.backMoneyNum }</th>
	        <th>${fns:getFormatNumber(item.surplusMoney,'￥#,##0.00')}</th>
	    </tr>
    </c:forEach>
</table>
<div class="pagination">${page}</div>
