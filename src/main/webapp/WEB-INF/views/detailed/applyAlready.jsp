<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同申请已办</title>
<script type="text/javascript">
	function goPage(){
		window.location = ctx+'/contract/applyAlreadyList';
	}  
</script>
</head>
<body>
   <div class="box1 mb10">
   <form:form id="searchForm" modelAttribute="ct" action="${ctx}/contract/applyAlready" method="post" >
   		<table class="table1" cellpadding="0" cellspacing="0" border="0" width='100%'>
   			<tr>
               <td>
	                <label class="lab">申请门店：</label>
	                <input type="text"class="cf_input_text178" disabled="disabled" id="orgName" value="${ct.orgName}">
                </td>
                <td>
	                <label class="lab">申请数量：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyNo" value="${ct.applyNo}">
                </td>
				<td>
					<label class='lab'>推荐数量：</label>
					<input type="text" class="cf_input_text178" disabled="disabled" id="applyDistNo" value="${ct.applyDistNo}">
				</td>
            </tr>
            <tr>
				<td>
					<label class="lab">合同版本：</label>
				    <select class="select78" id="contVersion" name="contVersion" disabled="disabled">
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${ct.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
				</td>
				<td>
					<label class="lab">现有库存：</label>
					<input type="text" class="cf_input_text178" disabled="disabled" id="applyInventory" value="${ct.applyInventory}">
				</td>
				<td>
					<label class="lab">申请状态：</label>
				    <select class="select78" id="applyStatus" name="applyStatus" disabled="disabled">
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						 <option value="${item.value}" <c:if test="${ct.applyStatus==item.value}">selected</c:if>>${item.label}</option>
					</c:forEach>
				</select>
				</td>
            </tr>
            <tr>
                <td>
	                <label class="lab">申请人：</label>
	                <c:set value="${fns:getUserById(ct.applyBy)" var="contractApplier"></c:set>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyBy" value="${contractApplier==null ? ct.applyBy : contractApplier.name}">
                </td>
                <td>
	                <label class="lab">联系人：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyContacts" value="${ct.applyContacts}">
                </td>
                <td>
	                <label class="lab">联系电话：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyTel" value="${ct.applyTel}">
                </td>
            </tr>
             <tr>
                <td>
	                <label class="lab">收货地址：</label>
	                <input type="text" class="cf_input_text178" disabled="disabled" id="applyShippingAddr" value="${ct.applyShippingAddr}">
                </td>
            </tr>
             <tr>
                <td>
	                <label class="lab">申请说明：</label>
	                <input type="text" disabled="disabled" id="applyExplain" class="cf_input_text178"  value="${ct.applyExplain}"></input>
                </td>
            </tr>
             <tr>
                <td>
	                <label class="lab">审核意见：</label>
	                <input type="text" disabled="disabled" id="applyCheckDesc" class="cf_input_text178"  value="${ct.applyCheckDesc}"></input>
                </td>
            </tr>
             <tr>
                <td>
                <label class="lab">审核结果：</label>
	                <input type='radio' disabled="disabled" <c:if test="${ct.applyCheckResult == 1 }"> checked="checked"</c:if>/>通过
	                <input type='radio' disabled="disabled" <c:if test="${ct.applyCheckResult == 2 }"> checked="checked" </c:if> />不通过
                </td>
            </tr>
        </table>
    </form:form>
    </div>
	 	<div class="tright pr30">
	 		<input type="button" value="返回" onclick="goPage()" class='btn cf_btn-primary'>
	 	</div>
</body>
</html>