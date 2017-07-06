<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>申请审核</title>
<script src="${ctxWebInf}/js/contract/applyApproval.js" type="text/javascript" ></script>	
</head>
<body>

	<form:form id="applyForm" modelAttribute="ca" >
           <table class="table table-striped table-bordered table-condensed">
             <tr>
             	<td>
                	<label class='lab'>申请门店：</label>
                	<input id="contStoresId" type="hidden" name="contractId" class="cf_input_text178" value="${ca.contractId}" >
                	<input id="contStoresId" type="hidden" name="contStoresId" class="cf_input_text178" value="${ca.contStoresId}" >
                	<input id="contStoresId" type="hidden" name="contVersion" class="cf_input_text178" value="${ca.contVersion}" >
                	<input id="org_name" type="text" class="cf_input_text178" disabled="disabled" value="${ca.orgName}" >
                </td>
                <td>
                	<label class='lab'>申请数量：</label>
                	<input id="applyNo" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyNo}">
                </td>
                <td>
					<label class='lab'>推荐数量：</label>
					<input id="applyDistNo" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyDistNo}">
				</td>
            </tr>
            <tr>
             	<td>
                	<label class='lab'>合同版本：</label>
				    <select class="select78" id="contVersion" name="contVersion"  disabled="disabled">
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${ca.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
                </td>
                <td>
                	<label class='lab'>现有库存：</label>
                	<input id="applyInventory" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyInventory}">
                </td>
                <td>
					<label class='lab'>审核状态：</label>
				     <select class="select78" id="applyStatus" name="applyStatus"  disabled="disabled">
						<c:forEach items="${fns:getDictList('tz_appaly_state')}" var="item">
							 <option value="${item.value}" <c:if test="${ca.applyStatus==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
				</td>
            </tr>
            <tr>
             	<td>
                	<label class='lab'>申请人：</label>
                	<c:set value="${fns:getUserById(ca.applyBy)" var="contractApplier"></c:set>
                	<input id="applyBy" type="text" class="cf_input_text178" disabled="disabled" value="${contractApplier==null ? ca.applyBy : contractApplier.name}">
                </td>
                <td>
                	<label class='lab'>联系人：</label>
                	<input id="applyContacts" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyContacts}">
                </td>
                <td>
					<label class='lab'>联系电话：</label>
					<input id="applyTel" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyTel}">
				</td>
            </tr>
             <tr>
                <td>
                	<label class='lab'>收货地址：</label>
                	<input id="applyShippingAddr" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyShippingAddr}">
                </td>
            </tr>
            <tr>
                <td>
                	<label class='lab'>申请说明：</label>
                	<input id="applyExplain" type="text" class="cf_input_text178" disabled="disabled" value="${ca.applyExplain}">
                </td>
             </tr>
             <tr>
                <td>
                	<label class='lab'>审核意见：</label>
                	<input id="applyCheckDesc" type="text" class="cf_input_text178" name="applyCheckDesc" value="${ca.applyCheckDesc}" required>
                </td>
              </tr>
              <tr>
                <td><label class='lab'>审核结果：</label>
                	 <c:if test="${ca.applyCheckResult == 1 or ca.applyCheckResult ==2}">
	           	     <input type='radio' disabled="disabled" <c:if test="${ca.applyCheckResult == 1 }"> checked="checked"</c:if>/>通过
	                 <input type='radio' disabled="disabled" <c:if test="${ca.applyCheckResult == 2 }"> checked="checked" </c:if> />不通过
			       </c:if>
	                <c:if test="${ca.applyCheckResult != 1 and ca.applyCheckResult !=2}">
	           	    <input name="applyCheckResult" type='radio' value="1"/>通过
                	<input name="applyCheckResult" type='radio' value="2" checked="checked"/>不通过
			       </c:if>
                </td>
            </tr>
        </table>
	
		<ul class='tright pr30'>
			<td>
			    <c:if test="${ca.applyCheckResult != 1 and ca.applyCheckResult !=2}">
	           	 <input type="button" opt="save" value="提交" class='btn cf_btn-primary'>
			    </c:if>
	           	 <input type="button" value="返回" onclick="goPage()" class='btn cf_btn-primary'>
	        </td>
	    </tr>
	    
      </form:form>
        
</body>
</html>