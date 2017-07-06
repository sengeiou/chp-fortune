<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxWebInf }/js/borrow/borrowMonth.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function keydown(e) {
	    var e=e||event;  
	    if(e.keyCode==13){
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
		<div class="box1 mb10">
		<form action="${ctx}/borrowMonth/list" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0"
				width="100%">
				<tr>
					<td><label class="lab">借款人：</label><input type="text" name="borrowerName" class="cf_input_text178" value="${borrowMonthView.borrowerName }"></td>
					<td><label class="lab">可用金额：</label>
						<input type="text" name="borrowAvailabeValueFrom" id ="borrowAvailabeValueFrom"
							value="${borrowMonthView.borrowAvailabeValueFrom }" class="input_text70" number='1' greaterThan="0"> -
						<input type="text" name="borrowAvailabeValueTo" id="borrowAvailabeValueTo";
							value="${borrowMonthView.borrowAvailabeValueTo }" class="input_text70" 
							from-checkInt="#borrowAvailabeValueFrom" number='1' greaterThan="0">
					</td>
					<td><label class="lab">可用期数：</label>
						<input type="text" name="borrowDaysSurplusFrom" id="borrowDaysSurplusFrom"
							value="${borrowMonthView.borrowDaysSurplusFrom }" class="input_text70" digits='1' maxLength="9"> -
						<input type="text" name="borrowDaysSurplusTo" id="borrowDaysSurplusTo"
							value="${borrowMonthView.borrowDaysSurplusTo }" class="input_text70"
							from-checkInt="#borrowDaysSurplusFrom" digits='1' maxLength="9">
					</td>
				</tr>
				<tr>
					<td><label class="lab">利率：</label>
						<input type="text" name="borrowMonthRate"  class="cf_input_text178" value="${borrowMonthView.borrowMonthRate }" number='1' greaterThan="0">
					</td>
					<td><label class="lab">首次还款日：</label>
						<input type="text" name="borrowBackmoneyFirdayFrom" id="borrowBackmoneyFirdayFrom"
							value = "${fns:getFormatDate(borrowMonthView.borrowBackmoneyFirdayFrom,'yyyy-MM-dd')}"
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowBackmoneyFirdayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="borrowBackmoneyFirdayTo" id="borrowBackmoneyFirdayTo"
							value = "${fns:getFormatDate(borrowMonthView.borrowBackmoneyFirdayTo,'yyyy-MM-dd')}"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowBackmoneyFirdayFrom\',{});}'})" class="input_text70 Wdate" >
					</td>
					<td>
						<label class="lab">职业情况：</label>
						<select name="borrowerJob" class="select78 mr34" multiple="multiple" >
	                           <c:forEach items="${dicts.jk_prof_type }" var="item">
	                              <option value="${item.value }" <c:if test="${fns:multiOption(borrowView.borrowerJob,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                    </select>
                    </td>
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">债权标识：</label>
						<select name="borrowTrusteeFlag" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_zjtr_mark}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowMonthView.borrowTrusteeFlag,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
					<td>
						<label class="lab">债权区分：</label>
						<select name="dicLoanDistinguish" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_loan_distinguish}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowMonthView.dicLoanDistinguish,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
				</tr>
				</table>
				<div class="tright pr30 pb5 pt10">
				       <input type="button" value="搜索" id = "search" class="btn cf_btn-primary"/>
					   <input type="reset" value="清除" class="btn cf_btn-primary"/>
					   <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
			    </div>
		</form>			
		</div>
		<p class="mb10 ml10">
			<auth:hasPermission key="cf:borrowmonth:exportexcel">
				<button class="btn btn_sc ml10" onclick="outExcel()">导出EXCEL</button>
			</auth:hasPermission>
			总金额：￥${fns:getFormatNumber(allMoney,'#,##0.00')}元 总笔数：${page.count }笔
		</p>		
		<sys:columnCtrl pageToken="borrow_borrowMonthList"></sys:columnCtrl>
		<div class='box5'>
		<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		    <thead>
		    <tr>
	            <th>序号</th>
	            <th>借款人</th>
	            <th>借款人身份证号</th>
	            <th>借款产品</th>
	            <th>债权标识</th>
	            <th>借款用途</th>
	            <th>职业情况</th>
	            <th>首次还款日</th>
	            <th>还款日</th>
	            <th>借款期数</th>
	            <th>可用期数</th>
	            <th>截止还款日期</th>
	            <th>月利率</th>
	            <th>分配金额</th>
	            <th>可拆分金额</th>
	            <th>年预计债权收益</th>
	            <th>债权区分</th>
	            <th>操作</th>
	        </tr>
	        </thead>
        <c:forEach items="${page.list }" var="item" varStatus="s">
        <tr>
        	<td>${s.index+1}</td>
        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.loanName }</td> --%>
            <td>***</td>
            <%-- <td>${fns:formatLoanIdcard(item.loanIdcard)}</td> --%>
            <td>${item.loanProduct }</td>
           	<td>${fns:dictName(dicts.tz_zjtr_mark,item.loanTrusteeFlag,'-') }</td>
            <td>${item.loanPurpose }</td>
            <td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }</td>
            <td>${fns:getFormatDate(item.loanBackmoneyFirday,'yyyy-MM-dd')}</td>
            <td>${fns:dictName(dicts.tz_repay_day,item.loanBackmoneyDay,'-') }</td>
            <td>${item.loanMonths }</td>
            <td>${item.loanMonthsSurplus }</td>
            <td>${fns:getFormatDate(item.loanEndmoneyDay,'yyyy-MM-dd')}</td>
            <td>${fns:formatNumber(item.loanMonthRate)}</td>
            <td>${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}</td>
            <td>${fns:getFormatNumber(item.loanAvailabeValue,'￥#,##0.00')}</td>
            <td>${fns:getFormatNumber(item.loanValueYear,'0.00')}</td>
            <td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
			<td>
				<auth:hasPermission key="cf:borrowmonth:transact">
					<input type="button" value="办理" class="cf_btn_edit" onclick="goPage('${ctx}/borrowMonth/goBorrowMonthLook?creditMonId=${item.creditMonId}');">
				</auth:hasPermission>
			</td>
		</tr>
        </c:forEach>
	</table>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>