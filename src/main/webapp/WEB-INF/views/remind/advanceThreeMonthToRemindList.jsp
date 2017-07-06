<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/remind/remindThreeMonth.js"></script>
<script type="text/javascript">

	function doSubmit(){
		document.getElementById("firstShow").value = 'true';
		$("#searchForm").action = '${ctx}/remindBeforeThreeMonth/advanceThreeMonthToRemindList';
   		$("#searchForm").submit();
	}
	
	//键盘事件
	function keydown(e) {
	    var e=e||event;  
	    if(e.keyCode==13){
	    	document.getElementById("firstShow").value = 'true';
	    	 $("#searchForm").action = '${ctx}/remindBeforeThreeMonth/advanceThreeMonthToRemindList';
	    	 $("#searchForm").submit();
	    }
	}
	
	window.onload=function(){
		var inputs = document.getElementsByTagName("input");
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].type=="text"){
				inputs[i].onkeydown=keydown;
			}
		}
	}
	
</script>
</head>
<body>
<div class="body_r">
		<form id="searchForm"  action=""  method="post" >
		<div class="box1 mb10">
	          	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="lendCodes" name="lendCodes" type="hidden" />
				<input type="hidden" id="firstShow" name="firstShow" value="true">
	          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
		                <label class="lab">出借编号：</label>
		                <input type="text" name="loanCode" value="${smsCfSendListExt.loanCode}" class="cf_input_text178">
		            </td>
	                <td>
	                	<label class="lab">客户姓名：</label>
	                	<input type="text" name="customerName" value="${smsCfSendListExt.customerName }" class="cf_input_text178">
	                </td>
	                <td>
	                	<label class="lab">所在城市：</label>
	                	<sys:cityselect name="areaNames" groupDisabled="true" value="${smsCfSendListExt.areaNames}" />
	                </td>
	            </tr>
	            <tr>
	                <td>
	                	<label class="lab">出借金额：</label>
	                	<input type="text" name="lendMoney" number="1" value="${smsCfSendListExt.lendMoney }" class="cf_input_text178"></td>
	                <td>
		               	<label class="lab">营业部：</label>
				   		<sys:orgTree id="org"  name="orgId" value="${smsCfSendListExt.orgId}" labelName="orgName"  labelValue="${smsCfSendListExt.orgName}" />
	                </td>
	                <td>
	                	<label class="lab">到期日：</label>
		                <input type="text" name="dueDayStart" id="dueDayStart" class="Wdate input_text70" 
			                onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'dueDayEnd\',{d:-1});}'})" 
			                value="${fns:getFormatDate(smsCfSendListExt.dueDayStart ,'yyyy-MM-dd')}"/> -
		                <input type="text" name="dueDayEnd" class="Wdate input_text70" id="dueDayEnd" 
		                	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'dueDayStart\',{});}'})" 
		                	value="${fns:getFormatDate(smsCfSendListExt.dueDayEnd, 'yyyy-MM-dd')}" />
	                </td>
	            </tr>
	            <tr id="T1" style="display:none">
	                <td>
	                	<label class="lab">付款方式：</label>
	                	<select name="dictPayType" class="select78" multiple="multiple">
	                		<c:forEach items="${dicts.tz_pay_type}" var = "di">
	                			<option value="${di.value }"
									<c:if test="${fns:multiOption(smsCfSendListExt.dictPayType,di.value)}">
										selected
									</c:if>
	                			>${di.label}
	                			</option>
	                		</c:forEach>
	                	</select>
	                </td>
	                <td>
	                	<label class="lab">出借产品：</label>
	                	<sys:productselect name="productCode" value="${smsCfSendListExt.productCode}" multiple="true"/>
	                </td>
	              
	                </tr>
	             </table>
	           <div class='tright pr30 pb5 pt10'>
	               <input type="button" class="btn cf_btn-primary" value="搜索" onclick="doSubmit()"/>
	               <input type="reset" class="btn cf_btn-primary" value="清除" />
<!-- 	                <input type="button" class="btn cf_btn-primary" value="清除" onclick="doDelete()"/> -->
	               <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
             </div>
             </div>
	    </form>
	    <auth:hasPermission key="cf:remindThreeMonth:exportdetail">
	    	<button class="btn btn_sc ml10 mb10" id="outExcel">导出明细</button>
	    </auth:hasPermission>
	<input type="hidden" id="oSumCount" value="${page.count }">
    <input type="hidden" id="oLendMoney" value="${fns:getFormatNumber(sumMoney.lendmoney,'#,##0.00')}">
    <input type="hidden" id="oDeductMoney" value="${fns:getFormatNumber(sumMoney.applydeductmoney,'#,##0.00')}">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
    	&nbsp;&nbsp;累计借出金额：￥<label id="lendMoney">${fns:getFormatNumber(sumMoney.lendmoney,'#,##0.00')}</label>元
 
    <div >
   		<sys:columnCtrl pageToken="remind_advanceThreeMonthToRemindList"></sys:columnCtrl>    
    	<div class="box5">
		    <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		        <thead>
		        <tr>
		            <th><input type="checkbox" class="checkAll" id="checkAll"/>全选</th>
		            <th>客户编号</th>
		            <th>客户姓名</th>
		            <th>出借编号</th>
		            <th>计划出借日期</th>
		            <th>计划出借金额</th>
		            <th>出借方式</th>
		            <th>付款方式</th>
		            <th>所在城市</th>
		            <th>营业部</th>
		            <th>到期日期</th>
		            <th>创建时间</th>
		            <th>理财经理</th>
		        </tr>
		        </thead>
		        <c:forEach items="${page.list}" var="smsCfSendListExt" varStatus="status">
			        <tr>
			            <td>
			            	<input type="checkbox" id="dataCheck" name="checkbox" value="${smsCfSendListExt.loanCode}"/>
			            	${status.count }
			            	<input type="hidden" id="lendMoneyH" name="lendMoneyH" value="${fns:getFormatNumber(smsCfSendListExt.lendMoney ,'##0.00')}"/>
			            	<input id="deductMoneyH" type="hidden" value="0">
			            </td>
			            <td>${smsCfSendListExt.customerCode }</td>
			            <!-- 屏蔽客户姓名/手机号/身份证号 -->
			            <td>***</td>
			            <%-- <td>${smsCfSendListExt.customerName }</td> --%>
			            <td>${smsCfSendListExt.loanCode }</td>
			            <td>${fns:getFormatDate(smsCfSendListExt.lendDay,'yyyy-MM-dd')}</td>
			            <td>${fns:getFormatNumber(smsCfSendListExt.lendMoney,'￥#,##0.00')}</td>
			            <td>${smsCfSendListExt.lendType }</td>
			            <td>${fns:dictName(dicts.tz_pay_type,smsCfSendListExt.payType,'-')}</td>
			            <td>${fns:getCityLabel(smsCfSendListExt.areaName)}</td>
			            <td>${smsCfSendListExt.orgName }</td>
			            <td>${fns:getFormatDate(smsCfSendListExt.dueDay,'yyyy-MM-dd')}</td>
			            <td>${fns:getFormatDate(smsCfSendListExt.createTime,"yyyy-MM-dd")}</td>
			            <td>${smsCfSendListExt.wealthManager }</td>
			        </tr>
		        </c:forEach>
		    </table>
		</div>
    </div>    
	<div class="pagination">${page}</div>
</div>
</body>
</html>