<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同调拨</title>
<script src="${ctxWebInf}/js/contract/transfer.js" type="text/javascript" ></script>
</head>
<body>
    <div class="box1 mb10">
    <form:form id="searchForm" modelAttribute="contract" action="${ctx}/contract/contractChangeApplyList" method="post" >
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                	<label class="lab">合同编号：</label>
                	<input type="hidden" class="cf_input_text178"  maxlength="12" id="contCode" name="contCode" value="${contract.contCode}">
                	<input type="text" class="cf_input_text178"  maxlength="12"  disabled="disabled"  value="${contract.contCode}" required digits="1">
                </td>
                <td>
                	<label class="lab">合同版本：</label>
                	<select class="select78" id="contVersion" name="contVersion" value="${contract.contVersion}" disabled="disabled" >
                	    <option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${contract.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
                </td>
                <td>
	                <label class="lab">合同状态：</label>
	                <select class="select78" id="dictContStatus" name="dictContStatus" value="${contract.dictContStatus}" disabled="disabled" >
					    <option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_contract_state')}" var="item">
							 <option value="${item.value}" <c:if test="${contract.dictContStatus==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				    </select>
	            </td>
           </tr>
           <tr>
                <td>
                	<label class="lab">入库日期：</label>
                	 <input type="text" class="cf_input_text178" onfocus="WdatePicker()" id="contIncomeDay" readonly="readonly" value="<fmt:formatDate value="${contract.contIncomeDay}" pattern="yyyy-MM-dd"/>"/>
               	</td>
                <td>
	                <label class="lab">申请人：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyBy" value="${userName}">
                </td>
                <td>
	                <label class="lab">转入方：</label>
	                <select class="select78" id="contBelongEmpid" name="contBelongEmpid" value="${contract.dictContStatus}" select_required="1">
					    <option value="">请选择</option>
						<c:forEach items="${list}" var="item">
						     <c:if test="${mangerId != item.userId}">
							   <option value="${item.userId}">${item.userName}</option>
						     </c:if>
						</c:forEach>
				    </select>
                </td>
           </tr>
           <tr>
                <td>
	                <label class="lab">转出方：</label>
	                <input type="text" class="cf_input_text178" id="applyTo" disabled="disabled" value="${mangerName}">
                </td>
                <td>
	                <label class="lab">调拨日期：</label>
	                <input type="text" class="cf_input_text178" onfocus="WdatePicker()" disabled="disabled" id="contTranferDay" readonly="readonly" value="<fmt:formatDate value="${contract.contTranferDay}" pattern="yyyy-MM-dd"/>"/>
                </td>
           </tr>
        </table>
     </form:form>
     </div>
	 	<div class="tright pr30">
	 		<input type="submit" value="提交" opt="transfer" class='btn cf_btn-primary'>
	 		<input type="button" value="返回" class='btn cf_btn-primary' onclick="window.location.href='${ctx}/contract/changeApplyList';">
	 	</div>
</body>
</html>