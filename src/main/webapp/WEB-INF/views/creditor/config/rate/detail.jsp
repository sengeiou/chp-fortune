<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/config/productRate.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/config/productRateDetail.js" type="text/javascript">var ctx =${ctx};</script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">
	    	<form id="saveForm" action="${ctx}/creditor/config/rate/save" method="post">
	    		<input type="hidden" value = "${rate.id }" name="id"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>产品类型：</label>
		                	<select class="select78" id="productCode" name="productCode" select_required="1" onchange="prochange()">
	            				<option value="">请选择</option>
	            				<c:forEach items="${fns:productOption()}" var="item">
		                        	<option value="${item.productCode }" <c:if test="${item.productCode == entity.productCode}">selected</c:if>>${item.productName }</option>
		                        </c:forEach>
	            			</select>
		                </td>
		            </tr>
		            <tr>
		              
		                <td>
		                	<label class="lab"><span class="red">*</span>产品月利率：</label>
		                	<input type="text" class="input_text70" id ="matchingRateLower" name="matchingRateLower" value="${rate.matchingRateLower}" required number="1" maxlength="5" greaterThan="0" onblur="getYearRate(this,'1')">% -
		                	<input type="text" class="input_text70" id = "matchingRateUpper" name="matchingRateUpper" value="${rate.matchingRateUpper}" required number="1" maxlength="5" greaterThan="0" from-checkInt="#matchingRateLower"  onblur="getYearRate(this,'2')">%
		                </td>
		                  <td>
		                <label class="lab">调整后债权收益率：</label>
		                	<input type="text" class="input_text70" id ="yearRateLower" name="yearRateLower" value="" disabled="disabled"  >% -
		                	<input type="text" class="input_text70" id = "yearRateUpper" name="yearRateUpper" value="" disabled="disabled"  >%
		                	
		                </td>
		            </tr>
		            <tr>
	             	<td>
	             		<label class="lab"><span class="red">*</span>账单类型：</label>
	                	<select class="select78 billType" id="billType" name="billType" select_required="1" onchange="billTypeChange()">
            				<option value="">请选择</option>
            				<c:forEach items="${fns:getDictList('tz_bill_state')}" var="item">
	                        	<option value="${item.value }" <c:if test="${entity.billType==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
            			</select>
	                </td>
	             </tr> 
		            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>初始出借日期：</label>
		                	<input type="text" id="applyLenddayLower" name="applyLenddayLower" value='<fmt:formatDate value="${rate.applyLenddayLower}" pattern="yyyy-MM-dd"/>' 
						    	 class="input_text70 Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLenddayUpper\')}'})" required> -
		                	<input type="text" name="applyLenddayUpper" id="applyLenddayUpper" value='<fmt:formatDate value="${rate.applyLenddayUpper}" pattern="yyyy-MM-dd"/>'
						    	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLenddayLower\')}'})" class="input_text70 Wdate" required>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>初始出借金额：</label>
		                	<input type="text" name="applyLendMoneyLower" id="applyLendMoneyLower" required number="1" value="1" maxlength="10" class="input_text70" number='1' greaterThan="0"> -
                			<input type="text" id="applyLendMoneyUpper" name="applyLendMoneyUpper" required number="1" from-checkInt="#applyLendMoneyLower" value="99999999" maxlength="10" class="input_text70" number='1' greaterThan="0"></td>
		                </td>
		            </tr>
		           	 <tr>
		                <td>
		                	<label class="lab">本期出借日期：</label>
		                	<input type="text" id="matchingInterestStart" name="matchingInterestStart" value='<fmt:formatDate value="${rate.matchingInterestStart}" pattern="yyyy-MM-dd"/>' 
						    	 class="cf_input_text178 Wdate"  onfocus="WdatePicker({})">
						    	  </td>
		            </tr>
		             <tr>
		             <td>
               			 <label class="lab">账单日：</label> 
               			 <select class="select78" id='matchingBillDay' name='matchingBillDay' select_required="1">
               			 <option value="">请选择</option>
								<c:forEach items="${dicts.tz_bill_day}" var='item'>
									<option value="${item.value }">
										${item.label }
									</option>
								</c:forEach>
						</select>
						</td>
		            </tr>
		             <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>状态：</label>
		                	<select class="select78" name="useFlag" select_required="1">
		                		<option value="">请选择</option>
		                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
		                        	<option value="${item.value }" <c:if test="${item.value == entity.useFlag}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
		                	</select>
		                </td>
		            </tr>		         	            
		        </table>
			</form>	
			<form id="backForm" action="${ctx}/creditor/config/rate/back" method="post"></form>        
	    </div>
	    <div class="tright pr30">
        	<input class="btn cf_btn-primary btnSave" type="button" value="保存" />
       		<input class="btn cf_btn-primary btnBack" type="button" value="返回" />
		</div>
	 </div>
</body>
</html>
