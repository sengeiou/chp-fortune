<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title></title>
<script src="${ctxWebInf }/js/borrow/borrowCancel.js" type="text/javascript">
var ctx = ${ctx};
</script>

<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form action='${ctx }/borrowCancel/list' method="post" id="searchForm">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="hiddenMoney" type="hidden" value="${fns:getFormatNumber(money,'0.00')}"/>
		<input id="hiddenCount"  type="hidden" value="${page.count}"/>
		<input id="matchingId"  type="hidden" value="${matchingId}" name="matchingId"/>
		<input id="hiddenCCLinesMoney" type="hidden" value="${fns:getFormatNumber(borrowCancelView.sumCurrentCreditLinesMoney,'0.00')}"/>
		<input id="hiddenSearch" type="hidden" value='${search}'  style="width:100%"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
	                <label class="lab">客户姓名：</label>
	                <input type="text" name=customerName class="cf_input_text178" value="${borrowCancelView.customerName }">
                </td>
				<td>
					<label class="lab">账单日：</label>
					<select class="select78 mr34" multiple="multiple" name="applyBillday">
					<c:forEach items="${dicts.tz_bill_day}" var="item">
	                	<option value="${item.value }" <c:if test="${fns:multiOption(borrowCancelView.applyBillday,item.value)}">selected</c:if>>${item.label }</option>
                    </c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">所在城市：</label>
					<sys:cityselect id="addrCity" name="addrCity" value="${borrowCancelView.addrCity}" 
						multiple="true"/>
				</td>
            </tr>
            <tr>
                <td>
                	<label class="lab">计划出借日期：</label>
                	<input type="text"  name = "lendDateFrom" id="lendDateFrom"
                		value="${fns:getFormatDate(borrowCancelView.lendDateFrom ,'yyyy-MM-dd')}" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'lendDateTo\',{});}'})" class="input_text70 Wdate"> -
                	<input type="text"  name = "lendDateTo" id="lendDateTo"
                		value="${fns:getFormatDate(borrowCancelView.lendDateTo ,'yyyy-MM-dd')}" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'lendDateFrom\',{});}'})" class="input_text70 Wdate" from-checkDate ="#lendDateFrom">
               	</td>
                <td>
	                <label class="lab">账单类型：</label>
	                <select class="select78 mr34" multiple="multiple" name="matchingFirstdayFlag">
	          			<c:forEach items="${dicts.tz_bill_state}" var="item">
	                		<option value="${item.value }" 
	                		<c:if test="${fns:multiOption(borrowCancelView.matchingFirstdayFlag,item.value)}">selected</c:if>>${item.label }</option>
	                    </c:forEach>
	                </select>
                </td>
                <td>
	                <label class="lab">出借产品：</label>
	                <sys:productselect name="procuctId" value="${borrowCancelView.procuctId}" multiple="true"/>
                </td>
            </tr>
            <tr id="T1" style="display:none;">
                <td>
	                <label class="lab">营业部：</label>
	                <sys:orgTree id="org" name="orgCode" value="${borrowCancelView.orgCode}"  labelName="orgName" labelValue="${borrowCancelView.orgName}" />
                </td>
                <td>
	                <label class="lab">出借编号:</label>
	                <input type="text" name = "lendCode" value="${borrowCancelView.lendCode }" class="cf_input_text178" maxlength= "32">
                </td>
				<td>
					<label class="lab">替换状态：</label>
					<select class="select78 mr34" multiple="multiple" name="replaceStatus">
					<c:forEach items="${dicts.tz_replace_status}" var="item">
	                    <option value="${item.value }" <c:if test="${fns:multiOption(borrowCancelView.replaceStatus,item.value)}">selected</c:if>>${item.label }</option>${item.label }</option>
	                </c:forEach>
					</select>
				</td>
            </tr>
            	 <td>
            	 	<label class="lab">替换日期：</label>
                	<input type="text" name = "replaceDayFrom" id="replaceDayFrom"
                		value="${fns:getFormatDate(borrowCancelView.replaceDayFrom ,'yyyy-MM-dd')}" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'replaceDayTo\',{});}'})" class="input_text70 Wdate"> -
                	<input type="text"  name = "replaceDayTo" id="replaceDayTo"
                		value="${fns:getFormatDate(borrowCancelView.replaceDayTo ,'yyyy-MM-dd')}" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'replaceDayFrom\',{});}'})" class="input_text70 Wdate" from-checkDate ="#replaceDayFrom">
            	 </td>
            <tr>
            	
            </tr>			
        </table>
				<div class="tright pr30 pb5 pt10" >
					<input type="submit" value="搜索" id = "search" class='btn cf_btn-primary'/>
					<input type="reset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"></div>
				</div>
    </form>      
    </div>
     <p class="mb10 ml10">
     <auth:hasPermission key="cf:borrowcancel:exportexcel">
	    	<button class="btn btn_sc mb10 ml10" onclick="outExcel()">导出EXCEL</button>
	 </auth:hasPermission>
               初始出借总金额：￥<span id="moneySpan">${fns:getFormatNumber(money,'#,##0.00')}</span>元　总笔数：<span id="numSpan">${page.count}</span>笔         本期待替换总金额：￥<span id="sumCCLinesMoneySpan">${fns:getFormatNumber(borrowCancelView.sumCurrentCreditLinesMoney,'#,##0.00')}</span>元
     </p>
      
     <sys:columnCtrl pageToken="borrow_borrowCancelList"></sys:columnCtrl>
     <div class='box5'>
    <table class="table tableCancel table-striped table-bordered table-condensed mainTable data-list-table" width="100%">
        <thead>
        <tr>
            <th><input type='checkbox' class='checkAll'/>全选</th>
            <th>出借编号</th>
            <th>客户姓名</th>
            <th>营业部</th>
			<th>计划出借日期</th>
            <th>初始出借金额</th>
            <th>本期出借日期</th>
            <th>本期待替换金额</th>
            <th>出借到期日期</th>
            <th>账单日</th>
			<th>出借产品</th>
			<th>替换日期</th>
			<th>账单类型</th>
			<th>替换状态</th>
			<th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type='checkbox' id="checkOne"  value="${item.lendCode }"/></td>
	            <td>${item.lendCode }</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerName }</td> --%>
	            <td>${item.orgName }</td>
	            <td>${fns:getFormatDate(item.applyLendDay,'yyyy-MM-dd')}</td>
				<td>${fns:getFormatNumber(item.applyLendMoney,'￥#,##0.00')}</td>
				<td>${fns:getFormatDate(item.matchingInterestStart,'yyyy-MM-dd')}</td>
				<td>${fns:getFormatNumber(item.currentCreditLinesMoney,'￥#,##0.00')}</td>
				<td>${fns:getFormatDate(item.applyExpireDay,'yyyy-MM-dd')}</td>
				<td>${item.applyBillday}</td>
				<td>${item.procuctName }</td>
				<td>${fns:getFormatDate(item.replaceDay,'yyyy-MM-dd')}</td>
				<td>${fns:dictName(dicts.tz_bill_state,item.matchingFirstdayFlag,'-') }</td>
				<td>${fns:dictName(dicts.tz_replace_status,item.toPageReplaceStatus,'-') }</td>
	            <td class="tcenter">
	            	<auth:hasPermission key="cf:borrowCancel:transact">
	            		<button class="cf_btn_edit btn_edit" lendCode_matchingId='${ item.lendCode}:${item.matchingId}'>办理</button>
	            	</auth:hasPermission>
	            </td>
	        </tr>
        </c:forEach>
       
    </table>
    </div>
    </div>
    <div class="pagination">${page }</div>
</body>
</html>