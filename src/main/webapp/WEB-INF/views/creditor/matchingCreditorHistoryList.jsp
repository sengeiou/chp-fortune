<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/creditor/matchingCreditorHistoryList.js" type="text/javascript"></script>
<title>已推荐债权查看列表</title>
</head>
<body>
<div class='body_top'>
	<div class="box1 mb10">
	  <form:form id="searchForm" modelAttribute="creditorTradeEx" action="${ctx}/creditor/matchingCreditorHistory/list" method="post">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
             <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" id="customerName" name="customerName" value="${creditorTradeEx.customerName}"></td>
                <td>
                    <label class="lab">初始出借金额：</label>
                    <input type="text" class="input_text70" id="littleMoney" name="littleMoney" value="${creditorTradeEx.littleMoney}" digits="1"/> -
                    <input type="text" class="input_text70" id="bigMoney" from-checkInt="#littleMoney" name="bigMoney" value="${creditorTradeEx.bigMoney}" digits="1"/>
                </td>
                <td>
                <label class="lab">初始出借日期：</label> 
                	<input type="text" class="Wdate input_text70" id="startApplyLendDayStart" name="startApplyLendDayStart"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startApplyLendDayEnd\')}'})" readonly="readonly" value="${creditorTradeEx.startApplyLendDayStart}"> -
                	<input type="text" class="Wdate input_text70" id="startApplyLendDayEnd" name="startApplyLendDayEnd"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startApplyLendDayStart\')}'})" readonly="readonly" value="${creditorTradeEx.startApplyLendDayEnd}">
                </td>
            </tr>
            <tr>
                <td>
                <label class="lab">账单日：</label> <select class="select78"
							id='matchingBillDayStr' name='matchingBillDayStr' multiple="multiple">
								<c:forEach items="${dicts.tz_bill_day}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(creditorTradeEx.matchingBillDayStr,item.value )}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
						</select>
                </td>
                <td>
                    <label class="lab">账单类型：</label>
                    <select class="select78" id="matchingFirstdayFlag" name="matchingFirstdayFlag">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_bill_state}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.matchingFirstdayFlag==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <label class="lab">营业部：</label>
                    <sys:orgTree id="org" name="org" value="${creditorTradeEx.org}"  labelName="orgName" labelValue="${creditorTradeEx.orgName}" />
                </td>
            </tr>
            <tr id="T1" style="display:none">
                <td>
                    <label class="lab">出借产品：</label>
                     <sys:productselect id="product" name="product" value="${creditorTradeEx.product}" multiple="true"/>
                </td>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${creditorTradeEx.lendCode}"></td>
                <td>
                    <label class="lab">付款方式：</label>
                    <select class="select78" id="applyPay" name="applyPay">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_pay_type}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.applyPay==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr  id="T4" style="display:none"> 
               <td>
                	<label class="lab">本期出借日期：</label> 
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartStart" name="matchingInterestStartStart"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'matchingInterestStartEnd\')}'})" readonly="readonly" value="${creditorTradeEx.matchingInterestStartStart}"> -
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartEnd" name="matchingInterestStartEnd"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'matchingInterestStartStart\')}'})" readonly="readonly" value="${creditorTradeEx.matchingInterestStartEnd}">
                
                </td>
                <td>
                    <label class="lab">撤销重匹：</label>
                    <select class="select78" id="selStatus" name="selStatus">
                        <option id="op1" value="">请选择</option>
                        <option id="op2" value="2">-</option>
                        <option id="op3" value="103" >CX</option>
                    </select>
                    <script type="text/javascript">
                    	var selSt = ${creditorTradeEx.selStatus};
                    	if(selSt == '103'){
                    		$("#op3").attr('selected','selected');
                    	}else if (selSt == '2'){
                    		$("#op2").attr('selected','selected');
                    	}else{
                    		$("#op1").attr('selected','selected');
                    	}
                    </script>
                </td>
            </tr>
        </table>
		 <div class="tright pr30 pb5 pt10">
		     <input class="btn cf_btn-primary" type="submit" id="search" value="搜索" />
		     <input  class="btn cf_btn-primary" type="reset" id="reset" value="清除" />
		     <div class="xiala" id="showMore" onclick="javascript:show();"></div>
		 </div>
	   </form:form>
	   </div>
	   
	   <sys:columnCtrl pageToken="creditor_matchingCreditorHistoryList"></sys:columnCtrl>
	   
     <div class="box5 mb10">
     <table class="table table-striped table-bordered table-condensed data-list-table">
       <thead>
        <tr>
            <th>序号</th>
            <th>出借编号</th>
            <th>客户姓名</th>
            <th>营业部</th>
            <th>出借产品</th>
            <th>付款方式</th>
            <th>初始出借日期</th>
            <th>初始出借金额</th>
            <th>本期出借日期</th>
            <th>本期推荐金额</th>
            <th>账单类型</th>
            <th>到期日期</th>
            <th>撤销重匹</th>
            <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.list}" var="item" varStatus="status">
	        <tr>
	            <td>${status.count}</td>
	            <td>${item.lendCode}</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${item.customerName}</td> --%>
	            <td>${item.org}</td>
	            <td>${item.productName}</td>
	            <td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'-')}</td>
	            <td>${fns:getFormatDate(item.startApplyLendDay,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatNumber(item.startApplyLendMoney,'￥#,##0.00')}</td>
	            <td>${fns:getFormatDate(item.matchingInterestStart,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatNumber(item.matchingBorrowQuota,'￥#,##0.00')}</td>
	            <td>${fns:dictName(dicts.tz_bill_state,item.matchingFirstdayFlag,'-')}</td>
	            <td>${fns:getFormatDate(item.applyExpireDay,'yyyy-MM-dd')}</td>
	            <td>${item.beforMatchingStatus}</td>
	            <td>
	            	<auth:hasPermission key="cf:matchingcreditorhistory:transact">
	            		<a href="${ctx}/creditor/matchingCreditorDetail/list?matchingId=${item.matchingId}&productCode=${item.productCode}"><button class="cf_btn_edit">办理</button></a>
	            	</auth:hasPermission>
	            </td>
	        </tr>
	    </c:forEach>
      </tbody>
     </table>
     </div>
     </div> 
     <div class="pagination">${page}</div>
</body>
</html>