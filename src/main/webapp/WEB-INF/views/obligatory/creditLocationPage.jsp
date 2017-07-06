<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<script type="text/javascript" src="${ctxWebInf}/js/obligatory/obligatory.js"></script>
<meta name="decorator" content="default"/>
<title>可用债权配置修改</title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10" >
	    <form id="creditEditForm" action="${ctx}/creditLocation/updateMesg" method="post">
	        <table  id="from" class="table1" cellpadding="0" cellspacing="0" border="0" width="100%" >
	            <input type="hidden" name="id" value="${coo.id}" />
	            <tr>
	            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
	                <td><label class="lab">借款人姓名：</label><input type="text" class='cf_input_text178' name="configLoanName" value="***" disabled="true"/></td>
	                <%-- <td><label class="lab">借款人姓名：</label><input type="text" class='cf_input_text178' name="configLoanName" value="${coo.configLoanName}" disabled="true"/></td> --%>
	                <td><label class="lab">借款人证件：</label>
	                	<select class="select78" name="dictConfigStatus" disabled="true">
			                <c:forEach items="${fns:getDictList('jk_certificate_type')}" var='item'>
			      				<option value="${item.value}" <c:if test='${item.value == coo.dictConfigStatus}'>selected=selected</c:if>>
			      					${item.label}
			      				</option>
			      			</c:forEach>
		                </select></td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->    
	                <td><label class="lab">借款人证件号：</label><input type='text' class='cf_input_text178' name="configZdr" value="***"  disabled="true"/></td>
	                <%-- <td><label class="lab">借款人证件号：</label><input type='text' class='cf_input_text178' name="configZdr" value="${coo.configZdr}"  disabled="true"/></td> --%>
	            </tr>
				<tr>
	                <td><label class="lab">类型：</label>
	                	<select class="select78"  name="configType" id="c" disabled="true">
	                		<c:forEach items="${fns:getDictList('tz_bill_state')}" var='item'>
		      					<option value="${item.value}" <c:if test='${item.value==coo.configType}'>selected=true</c:if>>${item.label}</option>
		      				</c:forEach>
	                	</select>
	                </td>
					<td><label class="lab">省份：</label><input type='text' id ="a" class='cf_input_text178' name="configProvince" value="${coo.configProvince}"  disabled="true"/></td>
					<td><label class="lab">城市：</label><input type='text' id ="a" class='cf_input_text178' name="configCity" value="${coo.configCity}"  disabled="true"/></td>
				</tr>
				<tr>
					<td><label class="lab">营业部：</label><textarea  id="orgId" cols="50" rows="10" name="orgName"  disabled="true"/>${coo.configYy}</textArea>
				</tr>
			</table>
		</form>
	</div>
	<div class='tright pr30'>
	<input type="button" class="btn cf_btn-primary" value="返回" onClick="window.history.back(-1);"/></div>
</body>
</html>
