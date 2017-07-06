<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>分派待推荐债权列表</title>
<script src="${ctxWebInf }/js/creditor/matching/distributeRecommendationList.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/matching/matchingCommon.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/common/subdialog.js" type="text/javascript"></script>
</head>
<body>
<sys:message content="${message }"/>
<div class="body_r">
      <form:form id="searchForm" action="${ctx}/matchingcreditor/distributeRecommendationList" method="post" >
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
      </form:form>
      <input id="distCount" type="hidden" value="${distCount }" />
      <p class="mb10 pt10 ml10">
      	<auth:hasPermission key="cf:distributerecommendation:exportexcel">
      		<input type="button" class="btn btn_sc ml10" value="导出EXCEL" onclick="outExcel()">
      	</auth:hasPermission>
<!--    <input type="button" value="导出Excel" class="btn btn_sc"/> -->
	<c:if test="${not empty status and status != '0' }">
        <input type="button" value="换单" opt="change" class="btn btn_sc"/>
    	<input type="button" value="弃单" opt="dropOrder" class="btn btn_sc"/>
    <c:if test="${empty fsq or scpf==0}">
    	<input type="button" value="首次派发" opt="firstDistribute" class="btn btn_sc"/>
    </c:if>
        <input type="button" value="再次派发"  opt="distributeAgain" class="btn btn_sc" 
        	<c:if test="${empty distributeAgain }">disabled="disabled"</c:if> />
    	<input type="button" value="立即分派" opt="distributeRightNow" class="btn btn_sc"/>
	</c:if>
                      当日任务量：${distCount}&nbsp;&nbsp;当日剩余未匹配账单量：${unDoCount}&nbsp;&nbsp;当日已匹配账单量：${orderCount}
      </p>        
    <sys:columnCtrl pageToken="creditor_matching_distributeRecommendationList"></sys:columnCtrl>
    <div class='box5'>
	    <table class="table table-striped table-bordered table-condensed data-list-table">
	        <thead>
	        <tr>
	            <th><input type='checkbox' class="checkAll"/></th>
	            <th>出借编号</th>
	            <th>客户姓名</th>
	            <th>营业部</th>
	            <th>出借产品</th>
	            <th>初始出借日期</th>
	            <th>初始出借金额</th>
	            <th>本期出借日期</th>
	            <th>本期推荐金额</th>
	            <th>账单类型</th>
	            <th>到期日期</th>
	            <th>付款方式</th>
	            <th>匹配标识</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list}" var="item">
		        <tr>
		            <td>
		            	<input type='checkbox' opt="records" 
		            	data-id="${item.matchingId}" data-type="${item.matchingFirstdayFlag}"/>
		            </td>
		            <td>${item.lendCode}</td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
		            <%-- <td>${item.customerName}</td> --%>
		            <td>${item.orgName}</td>
		            <td>${item.productName}</td>
		            <td>${fns:getFormatDate(item.startApplyLendDay,'yyyy-MM-dd')}</td>
		            <td>${fns:getFormatNumber(item.startApplyLendMoney,'#,#00.00#')}</td>
		            <td>${fns:getFormatDate(item.matchingInterestStart,'yyyy-MM-dd')}</td>
		            <td>${fns:getFormatNumber(item.matchingBorrowQuota,'#,#00.00#')}</td>
		            <td>${fns:dictName(dicts.tz_bill_state,item.matchingFirstdayFlag, '-')}</td>
		            <td>${fns:getFormatDate(item.applyExpireDay,'yyyy-MM-dd')}</td>
		            <td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'-')}</td>
		            <td>${fns:dictName(dicts.tz_matching_flag,item.matchingFlag,'-') }</td>
		            <td>
		            	<auth:hasPermission key="cf:distributerecommendation:transact">
		            		<button class="cf_btn_edit" onclick="banli('${item.matchingId}','${item.matchingFirstdayFlag }','1')">办理</button>
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
