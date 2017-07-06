<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<script src="${ctxWebInf }/js/borrow/borrow.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<meta name="decorator" content="default"/>
</head>
<body class="body_r">
      <div class="tright pr30 mb10">
		   <button class="btn btn_sc" onclick="goAllot('${borrow. creditValueId}')">分配</button>
		   <button class="btn btn_sc" onclick="his('${borrow. creditValueId}')">分配记录</button>
		   </div>
 <div class="box1 mb10">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">借款人姓名：</label>***</td>
                <%-- <td><label class="lab">借款人姓名：</label>${borrow.loanName }</td> --%>
                <td><label class="lab">借款人身份证号：</label>***</td>
                <%-- <td><label class="lab">借款人身份证号：</label>${fns:formatLoanIdcard(borrow.loanIdcard)}</td> --%>
                <td><label class="lab">职业情况：</label>${fns:dictName(dicts.jk_prof_type,borrow.loanJob,'-') }</td>
            </tr>
            <tr>
                <td><label class="lab">借款产品：</label>${borrow.loanProduct }</td>
                <td><label class="lab">债权来源：</label>${fns:dictName(dicts.tz_credit_src,borrow.dictLoanType,'-') }</td>
                <td><label class="lab">借款用途：</label>${borrow.loanPurpose }</td>
            </tr>
            <tr>
                <td><label class="lab">借款金额：</label>${fns:getFormatNumber(borrow.loanQuota,'￥#,##0.00')}</td>
                <td><label class="lab">借款利率：</label>
                	${fns:formatNumber(borrow.loanMonthRate)}
                </td>
                <td><label class="lab">放款日：</label><fmt:formatDate value="${borrow.loanOutmoneyDay }" pattern="yyyy-MM-dd"/></td>
            </tr>
             <tr>
                <td><label class="lab">可用金额：</label><span id="loanCreditValue">${fns:getFormatNumber(borrow.loanCreditValue,'￥#,##0.00')}</span></td>
                <td><label class="lab">还款日：</label>${fns:dictName(dicts.tz_repay_day,borrow.loanBackmoneyDay,'-') }</td>
                <td><label class="lab">截止日：</label><fmt:formatDate value="${borrow.loanBackmoneyLastday }" pattern="yyyy-MM-dd"/></td>
            </tr>
             <tr>
                <td><label class="lab">借款期数：</label>${borrow.loanMonths }</td>
                <td><label class="lab">可用期数：</label>${borrow.loanMonthsSurplus }</td>
            </tr>
        </table>
	</div>
	<div id="borrowLookListDiv">
		<%@ include file="/WEB-INF/views/borrow/borrowLookList.jsp"%>
	</div>
        <div class="tright pr30">
			<input type="button" value="返回" id="backButton" onclick="backBorrowList()" class="btn cf_btn-primary"/>
		</div>
</div>
<div id="modal-sub" class="modal fade subwindow" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeButton" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
			</div>
		</div>
	</div>
</div>
</body>
</html>