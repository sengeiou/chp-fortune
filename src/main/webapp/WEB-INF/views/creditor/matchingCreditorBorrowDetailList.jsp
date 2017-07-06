<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/creditor/matchingCreditorDetailList.js" type="text/javascript"></script>
<title>已推荐债权查看</title>
</head>
<body>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">出借申请信息</h2></div>
        <div class="r">
	    <input type="hidden" id="matchingId" value="${creditor.matchingId}" />
	    <input type="hidden" id="lendCode" value="${creditor.lendCode}" />
	    <button class="btn btn_sc" id="cancelHistory">撤销记录</button>
	</div>
    </div>
   
	<div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label>${creditor.lendCode }</td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label>***</td>
                <%-- <td><label class="lab">客户姓名：</label>${creditor.customerName}</td> --%>
                 <td><label class="lab">营业部：</label>${creditor.org}</td>
            </tr>
            <tr>
                <td><label class="lab">出借产品：</label>${creditor.productName}</td>
                <td><label class="lab">账单日：</label>${creditor.matchingBillDay}日</td>
                <td><label class="lab">账单类型：</label>${fns:getDictLabel(creditor.matchingFirstdayFlag, 'tz_bill_state', '-')}</td>
            </tr>
            <tr>
                <td><label class="lab">初始出借日期：</label><fmt:formatDate value="${creditor.startApplyLendDay}" pattern="yyyy-MM-dd"/></td>
                <td><label class="lab">初始出借金额：</label><fmt:formatNumber value="${creditor.startApplyLendMoney}" type="currency" pattern="￥#,#00.00#"/></td>
                <td><label class="lab">本期出借日期：</label><fmt:formatDate value="${creditor.matchingInterestStart}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td><label class="lab">本期需推荐金额：</label>${fns:getFormatNumber(creditor.matchingBorrowQuota,'￥#,##0.00')}
                </td>
            </tr>
        </table>
	 </div>
	 <div class="title">
        <div class="l pr30 "><h2 class="f14 ml10">债权推荐列表</h2></div>
     </div>
    <table cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-condensed" width="100%">
      <thead>
        <tr>
            <th>借款人</th>
            <th>债权来源</th>
            <th>借款类型</th>
            <th>职业情况</th>
            <th>中间人</th>
            <th>借款用途</th>
            <th>起始还款日期</th>
            <th>还款日</th>
            <th>借款期数</th>
            <th>剩余还款期数</th>
            <th>原始债权价值</th>
            <th>剩余债权价值</th>
            <th>月利率</th>
            <th>推荐额度</th>
            <th>债权区分</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="item" varStatus="status">
	        <tr>
	        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${item.loanName}</td> --%>
	            <td>${fns:getDictLabel(item.dictLoanType, 'tz_credit_src', '-')}</td>
	            <td>${item.loanProduct}</td>
	            <td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-')}</td>
	            <td>${item.loanMiddleMan}</td>
	            <td>${item.loanPurpose}</td>
	            <td><fmt:formatDate value="${item.loanBackmoneyFirday}" pattern="yyyy-MM-dd"/></td>
	            <td>${item.loanBackmoneyDay}</td>
	            <td>${item.loanMonths}</td>
	            <td>${item.phaseNumberSurplus}</td>
	            <td>
	            ${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}
	           <%--  <fmt:formatNumber value="${item.loanCreditValue}" type="currency" pattern="￥#,#00.00#"/> --%>
	            </td>
	            <td>
	          <%--   <fmt:formatNumber value="${item.syzqjz}" type="currency" pattern="￥#,#00.00#"/> --%>
	               <c:if test="${fns:getFormatNumber(item.syzqjz,'￥#,##0.00') == '-￥0.00'}"> 
	               ￥0.00 
	               </c:if>
	                <c:if test="${fns:getFormatNumber(item.syzqjz,'￥#,##0.00') != '-￥0.00'}"> 
	               ${fns:getFormatNumber(item.syzqjz,'￥#,##0.00')}
	               </c:if>
	            </td>
	            <td>
	            ${fns:getFormatNumber(item.loanMonthRate,'#,##0.0####')}%
	            </td>
	            <td>${fns:getFormatNumber(item.tradeMateMoney,'￥#,##0.00')}
	           <%--  <fmt:formatNumber value="${item.tradeMateMoney}" type="currency" pattern="￥#,#00.00#"/> --%>
	            </td>
	            <td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
	        </tr>
	     </c:forEach>
      </tbody>
    </table>
    <div class="tright pr30" style="margin-top:20px">
          <button class="btn cf_btn-primary" onclick="javascrtpt:history.go(-1);">返回</button>
    </div>	
    <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">债权撤销记录</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn cf_btn-primary" id="subClose">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>