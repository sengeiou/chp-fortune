<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/batchEnableDisable.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/config/productRate.js" type="text/javascript"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">      
	    	<form action="${ctx}/creditor/config/rate" method="post" id="searchForm">
		        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		             <tr>
		             	<td>
		             		<label class="lab">产品：</label>
		                	<sys:productselect name="productCode" value="${search.productCode}" multiple="true"/>
		             	</td>	
		             	<td>
	             		<label class="lab">账单类型：</label>
	                	<select class="select78 billType" id="billType" name="billType" multiple="multiple" >
            				<c:forEach items="${dicts.tz_bill_state}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(search.billType,item.value )}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
            			</select>
	                </td>	
	                  <td>
		                	<label class="lab">产品月利率：</label>
		                	<input type="text" class="input_text70" id ="matchingRateLower" name="matchingRateLower" value="${search.matchingRateLower}"  isNumber="1" maxlength="5" greaterThan="0">% -
		                	<input type="text" class="input_text70" id = "matchingRateUpper" name="matchingRateUpper" value="${search.matchingRateUpper}"  isNumber="1" maxlength="5" greaterThan="0" from-checkInt="#matchingRateLower"  >%
		                </td>                
		            </tr>
		            <tr>
		               <td>
		                	<label class="lab">初始出借金额：</label>
		                	<input type="text" name="applyLendMoneyLower" id="applyLendMoneyLower"  value="${search.applyLendMoneyLower}"  isNumber="1" value="1" maxlength="10" class="input_text70"  greaterThan="0"> -
                			<input type="text" id="applyLendMoneyUpper" name="applyLendMoneyUpper" value="${search.applyLendMoneyUpper}"  isNumber="1" greaterThan="0" from-checkInt="#applyLendMoneyLower" maxlength="10" class="input_text70" ></td>
		                 <td>
		                	<label class="lab">本期出借日期：</label>
		                	<input type="text" id="matchingInterestStart" name="matchingInterestStart" value='<fmt:formatDate value="${search.matchingInterestStart}" pattern="yyyy-MM-dd"/>' 
						    	 class="cf_input_text178 Wdate"  onfocus="WdatePicker({})">
						   </td>
						    <td>
              		  <label class="lab">账单日：</label> <select class="select78"
							id='matchingBillDayStr' name='matchingBillDayStr' multiple="multiple">
								<c:forEach items="${dicts.tz_bill_day}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(search.matchingBillDayStr,item.value )}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
						</select>
                </td>
		            </tr>
		             <tr id='T1' style='display:none;'>
		                <td>
		                	<label class="lab">初始出借日期：</label>
		                	<input type="text" id="applyLenddayLower" name="applyLenddayLower" value='<fmt:formatDate value="${search.applyLenddayLower}" pattern="yyyy-MM-dd"/>' 
						    	 class="input_text70 Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLenddayUpper\')}'})" > -
		                	<input type="text" name="applyLenddayUpper" id="applyLenddayUpper" value='<fmt:formatDate value="${search.applyLenddayUpper}" pattern="yyyy-MM-dd"/>'
						    	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLenddayLower\')}'})" class="input_text70 Wdate" >
		                </td>
		                <td>
		                	<label class="lab"><span class="red">*</span>状态：</label>
		                	<select class="select78" name="useFlag" multiple="multiple">
		                		<c:forEach items="${dicts.com_use_flag}" var="item">
		                        	<option value="${item.value }" <c:if test="${fns:multiOption(search.useFlag,item.value )}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
		                	</select>
		                </td>
		            </tr>
		        </table>	
	        	<div class="tright pr30 pt10 pb5">
	        		<button class="btn cf_btn-primary search">搜索</button>
	        		<input class="btn cf_btn-primary" type="reset" value="清除"> 
	        		<div class="xiala"  id="showMore" onclick="javascript:show();"></div>	
	        	</div>
	        </form>
	    </div>
	    <p class="mb10">
	    	<form method="post">
	    		<auth:hasPermission key="cf:productrateconfig:add">
			    	<input type="button" class="btn btn_sc ml10 mb10 toAdd" value="新增"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:productrateconfig:del">
			    	<input type="button" class="btn btn_sc mb10 toDelete" value="删除"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:productrateconfig:enable">
				    <input type="button" class="btn btn_sc ml10 mb10 toEnable" value="启用"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:productrateconfig:disable">
				    <input type="button" class="btn btn_sc ml10 mb10 toDisable" value="停用"/>
			    </auth:hasPermission>
		    	<div class="dataList">
			    	<%@ include file="/WEB-INF/views/creditor/config/rate/list.jsp"%>
			    </div>
		    </form>
		</p>	    
	</div>
</body>
</html>
