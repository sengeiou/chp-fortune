<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/borrow/borrowMonth.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<title></title>
</head>
<body>
    <div class="tright pr30 mb10">
		<button class="btn btn_sc ml10" onclick="splitHis('${borrowMonth. creditMonId}')">拆分记录</button>
		<button class="btn btn_sc" onclick="backTool('${borrowMonth. creditMonId}')">回池</button></div>		
	<div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<td style="display:none" class="creditMonId">${borrowMonth. creditMonId}</td>
            </tr>
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">借款人姓名：</label>***</td>
                <%-- <td><label class="lab">借款人姓名：</label>${borrowMonth.loanName }</td> --%>
                <td><label class="lab">借款人证件号：</label>***</td>
                <%-- <td><label class="lab">借款人证件号：</label>${fns:formatLoanIdcard(borrowMonth.loanIdcard)}</td> --%>
                <td><label class="lab">借款人职业：</label>${fns:dictName(dicts.jk_prof_type,borrowMonth.loanJob,'-') }</td>
            </tr>
            <tr>
                <td><label class="lab">借款产品：</label>${borrowMonth.loanProduct }</td>
                <td><label class="lab">借款用途：</label>${borrowMonth.loanPurpose }</td>
                <td><label class="lab">借款来源：</label>${fns:dictName(dicts.tz_credit_src,borrowMonth.dictLoanType,'-') }</td>
            </tr>
            <tr>
            	<td><label class="lab">首次还款日：</label><td>${fns:getFormatDate(borrowMonth.loanBackmoneyFirday,"yyyy-MM-dd")}</td></td>
                <td><label class="lab">还款日：</label>${fns:dictName(dicts.tz_repay_day,borrowMonth.loanBackmoneyDay,'-') }</td>
                <td><label class="lab">分配金额：</label>${fns:getFormatNumber(borrowMonth.loanCreditValue,'0.00')}</td>
            </tr>
             <tr>
                <td><label class="lab">可用金额：</label><span id = "loanAvailabeValue">${fns:getFormatNumber(borrowMonth.loanAvailabeValue,'0.00')}</span></td>
                <td ><label class="lab">月利率：</label>
                	 <span id ="monthRate">${fns:formatNumber(borrowMonth.loanMonthRate)}</span>
                </td>
            </tr>
        </table>
	</div>
	<div class='title'>
          <div class='l'><h2 class='f14 ml10'>拆分信息</h2></div>
    </div>
	<div class="tright pr30">
		<form targer="_blank" id="searchForm"action="${ctx}/borrowMonth/saveBorrowMonthSplit" method="post" >
			<input type="hidden"  id="creditMonId" name ="creditMonId" value="${borrowMonthSplitView.creditMonId}"/>
			<input type="hidden" name="verTime" id="verTime" value="${borrowMonthSplitView.verTime }"/>
		   <table class="table1">
			<tr>
				<td>
					<label class='lab'>原拆分金额：</label>
					<input type="text" id = "loanAvailabeValue" name="loanAvailabeValue" value="${fns:getFormatNumber(borrowMonthSplitView.loanAvailabeValue,'0.00')}" readonly="true" htmlEscape="false" 
						maxlength="50" class="cf_input_text178" number='1'/>
				</td>
				<td>
					<label class='lab'>拆分利率：</label>
					<input type="text" id="splitRate" name="splitRate" htmlEscape="false" maxlength="50" 
					<c:choose>
					   <c:when test="${borrowMonth.dictLoanType eq '0' }">
					   	readonly="true" value="${fns:formatNumber(borrowMonth.loanMonthRate)}"
					   </c:when>
					   <c:otherwise> 
					   	 value="${borrowMonthSplitView.splitRate }"
					   </c:otherwise>
					</c:choose>
					class="cf_input_text178" number='1' greaterThan="0" required/>
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>拆分金额：</label>
					<input type="text" id="splitMoney" name="splitMoney" value="${fns:getFormatNumber(borrowMonthSplitView.splitMoney,'0.00')}" htmlEscape="false" maxlength="50" 
						class="cf_input_text178" number='1' greaterThan="0" required />
				</td>
				<td>
					<label class='lab'>拆分后剩余金额：</label>    
					<input type="text" id = "surplusSplitMoney" name="surplusSplitMoney" value="${fns:getFormatNumber(borrowMonthSplitView.surplusSplitMoney,'0.00')}" htmlEscape="false" 
						maxlength="50" class="cf_input_text178" number='1' money_validator="0" required/>
				</td>
			</tr>
			</table>
			<div class="tright pr30 mt10">
			  <input id="splitSubmit" class="btn cf_btn-primary splitBorrowMonth" type="button"  value="拆分"/>
			  <input id="btnSubmit" class="btn cf_btn-primary backBorrowMonthList" type="button"  value="返回" />
			</div>
		</form>
	</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeButton" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
</body>
</html>