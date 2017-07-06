<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>债权推荐-非首期</title>
<script src="${ctxWebInf }/js/creditor/matching/matchingCreditorHandle.js" type="text/javascript">
var ctx =${ctx};
</script>
<script src="${ctxWebInf }/js/creditor/matching/borrowDetail.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/matching/matchingCommon.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>

</head>
<body>
<div class="body_new">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">出借申请信息</h2></div>
	    </div>
	<div class="box6">   
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label>${mc.lendCode }</td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td><label class="lab">客户姓名：</label>***</td>
                <%-- <td><label class="lab">客户姓名：</label>${mc.customerName }</td> --%>
                <td><label class="lab">营业部：</label>${mc.orgName }</td>
            </tr>
            <tr>
                <td><label class="lab">出借产品：</label>${mc.productName }</td>
                <td><label class="lab">账单日：</label>${mc.matchingBillDay }日</td>
                 <td><label class="lab">账单类型：</label>${fns:dictName(dicts.tz_bill_state,mc.matchingFirstdayFlag,'-') }</td>
            </tr>
            <tr>
                <td><label class="lab">初始出借日期：</label><fmt:formatDate value="${mc.startApplyLendDay }" pattern="yyyy-MM-dd"/></td>
                <td><label class="lab">初始出借金额：</label>
                 ${fns:getFormatNumber(mc.startApplyLendMoney,'￥#,##0.00')}
                </td>
                <td><label class="lab">本期出借日期：</label><fmt:formatDate value="${mc.matchingInterestStart }" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td><label class="lab">本期需推荐金额：</label>
                  ${fns:getFormatNumber(mc.matchingBorrowQuota,'￥#,##0.00')}
                </td>
            </tr>
       	 </table>
        <div class="tright pr30">
            <input type="hidden" id="pageFrom" value="${from}">
             <input type="hidden" id="isOnlyXinjieFlag" value="${isOnlyXinjieFlag}">
             <input type="hidden" id="matchingBeforeDifferent" value="${matchingBeforeDifferent}">
	        <button class="btn cf_btn-primary" onclick="matchingSubmit('${mc.matchingId }','${mc.lendCode }','${MatchingBorrowQuota }','${distributedFlag }','${mc.verTime }')">提交</button>
	        <button class="btn cf_btn-primary" onclick="javascrtpt:history.go(-1);">取消</button>
	        </div>
	 </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">已推荐债权列表</h2></div>  &nbsp;  &nbsp;
         <label >已选记录数：</label><input type="text" readonly="readonly" name="ylnumber" id="ylnumber" value="${ylnumber }" class="input_text50"/>
        <label >已选债权总金额：</label><input type="text" name="ylmoney" readonly="readonly"  id="ylmoney" value="<fmt:formatNumber value="${ylmoney }" minFractionDigits="2"/>" class="input_text70"/>
        <div class='r'>
        <button class="btn btn_sc" onclick="recommendBorrow('${mc.matchingId }',${MatchingBorrowQuota });">查看可用债权</button>
    	</div>
    </div>
        <div class="box4 mb10" style='height:121px;overflow-y:auto;'>
        <table cellspacing="0" cellpadding="0" border="0"  id="tjzq" class="table table-striped table-bordered table-condensed" width="100%">
        <thead> 
        <tr>
            <th><button class='btn btn_sc ml10' onclick="removeAll()">全部移除</button></th>
            <th>借款人</th>
            <th>债权来源</th>
		    <th>借款类型</th>
            <th>职业情况</th>
            <th>中间人</th>
            <th>借款用途</th>
            <th>还款起始日期</th>
            <th>还款日</th>
            <th>借款期数</th>
            <th>剩余还款期数</th>
            <th>原始债权价值（￥）</th>
            <th>剩余债权价值（￥）</th>
            <th>月利率</th>
            <th>推荐额度</th>
            <th>债权区分</th>
        </tr>
        </thead>
        <c:forEach items="${borrows}" var="item" varStatus="status">
        <tr>
         
            <td  onClick="getDel(this)"><a href='#'>移除</a> 
            <input type="hidden" name="creditValueId"  value="${item.creditValueId }">
              <input type="hidden" name="verTime"  value="${item.verTime }">
            </td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.loanName }</td> --%>
            <td>${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') } <input type="hidden" name="dictLoanType"  value="${item.dictLoanType}"></td>
		    <td>${item.loanProduct }</td>
             <td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }</td>
            <td>${item.loanMiddleMan }</td>
            <td>${item.loanPurpose }</td>
            <td><fmt:formatDate value="${item.loanBackmoneyFirday }" pattern="yyyy-MM-dd"/></td>
            <td>${item.loanBackmoneyDay }</td>
             <td>${item.loanMonths }</td>
            <td>${item.loanMonthsSurplus }</td>
            <td>
             ${fns:getFormatNumber(item.loanQuota,'￥#,##0.00')}
            </td>
            <td><input type="hidden" name=upperMoney  value="">
            <input type="hidden" name="loanCreditValue"  value="${fns:getFormatNumber(item.loanCreditValue ,'#,##0.00')}"> ${fns:getFormatNumber(item.loanCreditValue ,'￥#,##0.00')}</td>
            <td>
            ${fns:getFormatNumber(item.loanMonthRate,'#,##0.0####')}%
          <%--   <fmt:formatNumber value="${item.loanMonthRate }" pattern="#,##0.#####"/> --%>
            </td>
            <td><input type="text" name="tjmoney" onblur="clearNoNum(this)"  value="${fns:getFormatNumber(item.tjmoney ,'#,##0.000')}"  /></td>
        	<td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
        </tr>
        </c:forEach>
    </table>
    </div>
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">既有及历史债权列表</h2></div>
	    </div>
        <div class="box5" style='height:280px;overflow-y:auto;'>
		  <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed" width="100%">
		         <thead>
		         <tr>
		            <th colspan="7">债权基本信息</th>
		            <th colspan="5">借款人如约还款情况下债权权益信息</th>
		        </tr>
		        <tr >
		            <th>序号</th>
		            <th>借款人</th>
		            <th>借款人身份证</th>
		            <th>初始受让债权价值</th>
		            <th>年月日持有债权价值</th>
		            <th>职业情况</th>
		            <th>借款用途</th>
		            <th>还款起始日期</th>
		            <th>本期还款金额</th>
		            <th>还款期限（月）</th>
		            <th>剩余还款月数</th>
		            <th>预计债权收益率（年）</th>
		        </tr>
		        </thead>
		        <c:forEach items="${creditorTradeBorrowViews}" var="item1" varStatus="status">
		        <tr>
		         	<td>${status.index+1}</td>
		         	<!-- 屏蔽客户姓名/手机号/身份证号 -->
		            <td>***</td>
		            <%-- <td>${item1.loanName }</td> --%>
		            <td>***</td>
		            <%-- <td>${item1.loanIdcard }</td> --%>
		            <td>
		              ${fns:getFormatNumber(item1.tradeMateMoney,'￥#,##0.00')}
		            </td>
		             <td>
		              ${fns:getFormatNumber(item1.nyrcyzq,'￥#,##0.00')}
		             </td>
		            <td>${fns:dictName(dicts.jk_prof_type,item1.loanJob,'-') }</td>
		            <td>${item1.loanPurpose }</td>
		             <td><fmt:formatDate value="${item1.loanBackmoneyFirday }" pattern="yyyy-MM-dd"/></td>
		            <td>
		             ${fns:getFormatNumber(item1.bqkgje,'￥#,##0.00')}
		            </td>
		            
		             <td>${item1.loanMonths }</td>
		            <td>${item1.loanMonthsSurplus }</td>
		            <td><fmt:formatNumber value="${item1.loanValueYear }" pattern="#,##0.00"/>%</td>
		        </tr>
		        </c:forEach>
		    </table>
    </div>    
</div>
<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">可用债权列表</h4>
				</div>
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
