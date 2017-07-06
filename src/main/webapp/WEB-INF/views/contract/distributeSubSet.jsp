<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${ctxWebInf}/js/contract/distributeSubSet.js" type="text/javascript" ></script>	
 <table class="table table-striped table-bordered table-condensed">
        <thead>       
        <tr>
            <th>营业部</th>
			<th>物流编号</th>
            <th>起始编号</th>
            <th>终止编号</th>
			<th>合同数量（箱）</th>
            <th>合同申请数量（套）</th>
            <th>合同版本</th>
<!--        <th>申请日期</th> -->
            <th>派发日期</th>
            <th>派发状态</th>
            <th>签收状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${ContractDistribute}" var="item" varStatus="status">
        	<tr>
	            <td>${item.orgName}</td>
	            <td>${item.distExpressNo}</td>
	            <td>${item.distStartNo}</td>
				<td>${item.distEndNo}</td>
				<td>${item.distBox}</td>
	            <td>${item.distContractNo}</td>
	            <td>${fns:getDictLabel(item.contVersion,'tz_contract_vesion','-')}</td>
<%-- 	        <td><fmt:formatDate value="${item.applyDay}" pattern="yyyy-MM-dd"/></td> --%>
	            <td><fmt:formatDate value="${item.distDate}" pattern="yyyy-MM-dd"/></td>
	            <td>${fns:getDictLabel(item.distStatus,'tz_dispath_status','-')}</td>
	            <td opt="signStatus_${status.index}">${fns:getDictLabel(item.signStatus,'tz_singn_state','-')}</td>
	            <td class="tcenter">
	               <input type="button" class='cf_btn_edit' value="办理" onclick="goPage('${ctx}/contract/distributeContract?id=${item.id}');">
	               <input type="button" class='cf_btn_edit' value="签收" opt="sign" data-id="${item.id}" data-index="${status.index}">
		    	</td>
		    </tr>
	    </c:forEach>
</table>