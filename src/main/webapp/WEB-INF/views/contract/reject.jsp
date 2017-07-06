<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<span>
审核意见:<select class="select78 editable" id="dictApplyRefuseDemo" name="dictApplyRefuseDemo">
        <c:forEach items="${fns:getDictList('tz_contract_passCause')}" var="item">
             <option value="${item.value}">${item.label}</option>
        </c:forEach>
        <option value='-1' class=\"customval\">新编辑</option>
</select>
</span>

<script src="${ctxWebInf}/js/contract/selectEditable.js" type="text/javascript" ></script>	
<script>

$(document).ready(function() { 
	$(".editable").editable();
});
</script>