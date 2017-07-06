<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/deduct.js"></script>
</head>
<body>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">债权价值信息</h2></div>
        <div class="r">
	        <c:if test="${deductPoolExt.applyOrApproveFalg ne 'approve'}">
	            <input type="button" class="btn btn_sc ml10" id="sendfile" applycode="${deductPoolExt.applyCode}" value="发送首期债权" />
	        </c:if>
        </div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
	                <label class="lab">出借编号：</label>
	                <input type="text" class="cf_input_text178" value="${deductPoolExt.applyCode }" disabled="disabled">
                </td>
                <td>
                	<label class="lab">客户姓名：</label>
                	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                	<input type="text" class="cf_input_text178" value="***" disabled="disabled">
                	<%-- <input type="text" class="cf_input_text178" value="${deductPoolExt.custName}" disabled="disabled"> --%>
                </td>
				<td>
					<label class="lab">营业部：</label>
					<input type="text" class="cf_input_text178" value="${deductPoolExt.orgName}" disabled="disabled">
				</td>
            </tr>
            <tr>
                <td>
                	<label class="lab">产品类型：</label>
                	<input type="text" class="cf_input_text178" value="${deductPoolExt.productName}" disabled="disabled">
                </td>
                <td>
                	<label class="lab">账单日：</label>
                	<input type="text" class="cf_input_text178" value="${deductPoolExt.matchingBillDate}" disabled="disabled">
                </td>
				<td>
					<label class="lab">账单类型：</label>
					<input type="text" class="cf_input_text178" value="${fns:dictName(dicts.tz_bill_state,deductPoolExt.matchingFirstdayFlag,'-')}" disabled="disabled">
				</td>
            </tr>
            <tr>
                <td>
                	<label class="lab">计划出借日期：</label>
                	<input type="text" class="cf_input_text178" value='<fmt:formatDate value="${deductPoolExt.applyLendDate}" pattern="yyyy-MM-dd" />' disabled="disabled">
                </td>
                <td>
                	<label class="lab">计划出借金额：</label>
                	<input type="text" class="cf_input_text178" 
                	value="${fns:getFormatNumber(deductPoolExt.applyLendMoney ,'#,##0.00')}" disabled="disabled">
                	(${fns:getFormatNumber(deductPoolExt.applyLendMoney ,'￥#,##0.00')})
                </td>
				<td>
					<label class="lab">计划划扣金额：</label>
					<input type="text" class="cf_input_text178" value="${fns:getFormatNumber(deductPoolExt.applyDeductMoney,'#,##0.00')}" disabled="disabled">
				</td>
            </tr>
			<tr>
                <td width="31%">
                	<label class="lab">到期日期：</label>
                	<input type="text" class="cf_input_text178" value='<fmt:formatDate value="${deductPoolExt.applyExpireDate}" pattern="yyyy-MM-dd"/>' disabled="disabled">
                </td>
            </tr>
        </table>
    </div>
     <div class="title" style='font-style:bold;'>
    	<div class="l"><h2 class="f14 ml10">预借出借收益</h2></div>
    </div>
    <div class="box6">
		 <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed" width="100%">
	        <thead>
	        <tr>
	            <th>出借编号</th>
	            <th>资金出借及回收方式</th>
	            <th>初始出借日日期</th>
	            <th>初始出借金额</th>
	            <th>下一个报告日</th>
	            <th>下一个报告期借款人应还</th>
	            <th>账户管理费</th>
	            <th>预计下一个报告日您的资产总额</th>
	        </tr>
	        </thead>
	        <tr>
	            <td>${deductPoolExt.applyCode }</td>
	            <td>${deductPoolExt.productName }</td>
	            <td><fmt:formatDate value="${deductPoolExt.applyLendDate }" pattern="yyyy-MM-dd"/></td>
	            <td>${fns:getFormatNumber(deductPoolExt.applyLendMoney ,'￥#,##0.00')}</td>
	            <td><fmt:formatDate value="${deductPoolExt.nextBillDate }" pattern="yyyy-MM-dd"/></td>
	            <td>${fns:getFormatNumber(deductPoolExt.nextBorrowMoney,'￥#,##0.00')}</td>
	            <td>￥00.00</td>
	           	<td>${fns:getFormatNumber(deductPoolExt.nextSumMoney,'￥#,##0.00')}</td>
	        </tr>
	    </table>
    </div>
     <div class="title">
        <div class="l"><h2 class="f14 ml10">债权推荐列表</h2></div>
     </div>
     <div class="box6">
	     <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed" width="100%">
	        <thead>
		        <tr>
		            <th>借款人姓名</th>
		            <th>借款人身份证</th>
		            <th>本次转让债权价值</th>
		            <th>需支付对价</th>
		            <th>职业情况</th>
		            <th>借款用途</th>
		            <th>还款起始日期</th>
		            <th>还款期数（月）</th>
		            <th>剩余还款期数</th>
		            <th>预计债权收益率</th>
		        </tr>
	        </thead>
	      	<c:forEach items="${deductPoolExt.creditorTradeExtList }" var="creditorTrade">
		        <tr>
		        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
		            <td>***</td>
		            <%-- <td>${creditorTrade.borrowerName }</td> --%>
		            <td>***</td>
		            <%-- <td>${fns:formatLoanIdcard(creditorTrade.borrowerIdcard)}</td> --%>
		            <td>${fns:getFormatNumber(creditorTrade.tradeMateMoney ,'￥#,##0.00')}</td>
	            	<td>${fns:getFormatNumber(creditorTrade.tradeMateMoney ,'￥#,##0.00')}</td>
		            <td>${fns:dictName(dicts.jk_prof_type,creditorTrade.borrowerJob,'-')}</td>
		            <td>${creditorTrade.borrowPurpose }</td>
		            <td>${creditorTrade.borrowBackmoneyFirday }</td>
		            <td>${creditorTrade.borrowMonths }</td>
		            <td>${creditorTrade.borrowMonthsSurplus }</td>
		            <td><fmt:formatNumber value="${creditorTrade.borrowValueYear }" type="currency" pattern="#,#00.0#"/>%</td>
		        </tr>
	       </c:forEach>
	    </table>
    </div>
     <c:choose>
     	<c:when test="${deductPoolExt.applyOrApproveFalg eq  'approve' }">
	     	<div class="title">
	        	<div class="l"><h2 class="f14 ml10">审批信息</h2></div>
	     	</div>
     		<form action="${ctx}/deductApprove/approve" method="post" id="deductApproveForm">
     			<input type="hidden" name="applyCode" value="${ deductPoolExt.applyCode }_${ deductPoolExt.verTime }">
	     		<div class="box6">
			        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			            <tr>
			                <td colspan="2">
			                	<label class="lab">
			                		<span class="red">*</span>审批结果：
			                	</label>
			                	<input type="radio" name="approveResult" required value="3" class="ml10 mr6">通过
			                	<input type="radio" name="approveResult" required value="9" class="ml10 mr6">不通过
			                	<input type="radio" name="approveResult" required value="8" class="ml10 mr6">撤销
			                </td>
			                <td>
			                	<label class="lab">审批意见：</label>
			                	<input type="text" name="approveAdvice" class="cf_input_text178">
			                </td>
			            </tr>
			        </table>
	    		</div>
	   		    <div class="tright pr30 mt20">
	    		    <input type="submit" class="btn cf_btn-primary" value="提交"/>
<%-- 			    	<input type="reset" class="btn cf_btn-primary"  value="返回" onclick="javascript:go('${ctx}/deductApprove/approveList');" /> --%>
			    	<input type="button" class="btn cf_btn-primary" onclick="window.history.back()" value="返回">
				</div>
			</form>
     	</c:when>
     	<c:otherwise>
     		<div class="tright pr30 mt20">
			    <button class="btn cf_btn-primary" onclick="javascript:go('${ctx}/deductApply/applyConduct?applyCode=${ deductPoolExt.applyCode }_${ deductPoolExt.verTime }');">提交</button>
<%-- 			    <button class="btn cf_btn-primary" onclick="javascript:go('${ctx}/deductApply/applyList');">返回</button> --%>
			    <button class="btn cf_btn-primary" onclick="window.history.back()">返回</button>
    		</div>
     	</c:otherwise>
     </c:choose>
</div>
<sys:message content="${message}"/>
</body>
</html>
