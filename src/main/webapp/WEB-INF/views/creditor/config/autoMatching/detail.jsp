<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/config/autoMatching.js" type="text/javascript"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">
	    	<form action="${ctx}/creditor/config/automatching/save" method="post" id="addForm">
	    		<input type="hidden" value = "${entity.id }" name="id"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>账单日：</label>
		                	<select class="select78" name="billDay" select_required="1">
	            				<option value="">请选择</option>
	            				<c:forEach items="${fns:getDictList('tz_bill_day')}" var="item">
		                        	<option value="${item.value }" <c:if test="${item.value == entity.billDay}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
	            			</select>
		               	</td>
		                <td>
		                	<label class="lab"><span class="red">*</span>产品类型：</label>
		                	<select class="select78" name="productCode" select_required="1">
	            				<option value="">请选择</option>
	            				<c:forEach items="${fns:productOption()}" var="item">
		                        	<option value="${item.productCode }" <c:if test="${item.productCode == entity.productCode}">selected</c:if>>${item.productName }</option>
		                        </c:forEach>
	            			</select>
		                </td>
		                <td>
		                	<label class="lab"><span class="red">*</span>账单类型：</label>
		                	<select class="select78" name="billType" select_required="1">
	            				<option value="">请选择</option>
	            				<c:forEach items="${fns:getDictList('tz_bill_state')}" var="item">
		                        	<option value="${item.value }" <c:if test="${item.value == entity.billType}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
	            			</select>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                	<label class="lab">营业部：</label>
		                	<sys:orgTree id="org" name="businessDepartment" value="${entity.businessDepartment}" labelName="businessDepartmentName"  labelValue="${entity.businessDepartmentName}" />
		                	
		                </td>
		                 <td>
		                	<label class="lab">城市：</label>
		                	<sys:cityselect name="provinceCity" value="${entity.provinceId}_${entity.cityId}" />
		                	
		                </td>		   
		                <td>
		                	<label class="lab"><span class="red">*</span>状态：</label>
		                	<select class="select78" name="status" select_required="1">
		                		<option value="">请选择</option>
		                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
		                        	<option value="${item.value }" <c:if test="${item.value == entity.status}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
		                	</select>
		                </td>
		            </tr>		            
		           	            
		        </table>
			</form>	
			<form id="backForm" action="${ctx}/creditor/config/automatching/back" method="post"></form>        
	    </div>
	       <div class="tright pr30">
		        	<input class="btn cf_btn-primary btnSave" type="button" value="保存" />
	        		<input class="btn cf_btn-primary btnBack" type="button" value="返回" />
		   </div>
	</div>
</body>
</html>
