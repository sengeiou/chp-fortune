<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同派发</title>
<script src="${ctxWebInf}/js/contract/distribute.js" type="text/javascript" ></script>	
</head>
<body>
<div class="r">
<!-- 	<input type="button" value="签收" opt="sign"/> -->
</div>
    <form:form id="searchForm" modelAttribute="cd" method="post" >
        <table class="table table-striped table-bordered table-condensed">
            <tr>
                <td>
                	<label class="lab">申请门店：</label>
                	<input type="hidden" class="cf_input_text178" id="disId" value="${cd.id}">
                	<input type="text" class="cf_input_text178" disabled="disabled" id="orgName" value="${cd.orgName}">
                </td>
                <td>
                	<label class="lab">起始编号：</label>
                	<input type="text" class="cf_input_text178" disabled="disabled" id="distStartNo" value="${cd.distStartNo}">
                </td>
                <td>
	                <label class="lab">物流编号：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="distExpressNo" value="${cd.distExpressNo}">
	            </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">合同版本：</label>
				    <select class="select78" id="contVersion" name="contVersion" disabled="disabled" >
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${cd.contVersion==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
               	</td>
                <td>
	                <label class="lab">终止编号：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="distEndNo" value="${cd.distEndNo}">
                </td>
                <td>
	                <label class="lab">合同箱数：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="distBox" value="${cd.distBox}">
                </td>
            </tr>
            <tr>
                <td>
	                <label class="lab">合同套数：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyNo" value="${cd.applyNo}">
                </td>
                <td>
	                <label class="lab">申请日期：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="apply_day" readonly="readonly" value="<fmt:formatDate value="${cd.applyDay}" pattern="yyyy-MM-dd"/>"/>
                </td>
                <td>
	                <label class="lab">发货日期：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="distDate" readonly="readonly" value="<fmt:formatDate value="${cd.distDate}" pattern="yyyy-MM-dd"/>">
                </td>
            </tr>
			<tr>
                <td>
	                <label class="lab">签收状态：</label>
				    <select class="select78" id="signStatus" name="signStatus" disabled="disabled" >
					<c:forEach items="${fns:getDictList('tz_singn_state')}" var="item">
						 <option value="${item.value}" <c:if test="${cd.signStatus==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
                </td>
            </tr>
        </table>
     </form:form>
	 	<div class="tright pr30">
	 		<input type="button" class="btn cf_btn-primary" value="返回" onclick="goPage()">
	 	</div>

</body>
</html>