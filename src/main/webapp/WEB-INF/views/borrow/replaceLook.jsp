<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/borrow/borrowCancel.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<div class="body_new">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">出借申请信息</h2></div>
    </div>
	<div class="box6">
    
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">出借编号：</label>
                <span id="lendCode">${borrowCanceEx.loanApply.applyCode}</span>
                </td>
                <td style="display:none" id="matchingId">${borrowCanceEx.matchingId }</td>
                <!-- <td style="display:none" id="phaseMateId"></td> -->
                <input type="hidden" id="oldCreditValueIds"/>
                <input type="hidden" id="oldDictLoanTypeArray"/>
                <input id="hiddenSearch"  type="hidden" value='${search}' style="width:100%;"/>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td><label class="lab">客户姓名：</label>***</td>
                <%-- <td><label class="lab">客户姓名：</label>${borrowCanceEx.loanApply.custName }</td> --%>
                <td><label class="lab">营业部：</label>${borrowCanceEx.loanApply.orgName }</td>
            </tr>
            <tr>
                <td><label class="lab">产品类型：</label>${borrowCanceEx.loanApply.procuctName }</td>
               	<td><label class="lab">计划出借日期：</label><fmt:formatDate value="${borrowCanceEx.loanApply.lendDate}" pattern="yyyy-MM-dd"/></td>
                <td><label class="lab">计划出借金额：</label>￥${fns:getFormatNumber(borrowCanceEx.loanApply.lendMoney,'#,##0.00')}</td>
            </tr>
            <tr>
            
                <td><label class="lab">本期待推荐金额：</label><span class="nowSpan">￥${fns:getFormatNumber(borrowCanceEx.currentCreditLines,'#,##0.00')}</span></td>
                <td><label class="lab">本期推荐金额：</label><span class="primarySpan">￥${fns:getFormatNumber(borrowCanceEx.primaryCreditLines,'#,##0.00')}</span></td>
            </tr>
        </table>
        <div class="tright pr30"><button class="btn saveNew cf_btn-primary" >提交</button>
         <button class="btn cf_btn-primary" onclick="window.history.back()">取消</button>
        <!--<button class="btn cancel cf_btn-primary">取消</button> -->
        </div>
	 </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">本期推荐债权列表</h2></div>
        <div class='tright pr30'>
        <button class="btn btn_sc" onclick="recommendBorrow()">查看可用债权</button>
        <button class="btn btn_sc btnRemove">全部移除</button>
        </div>
    </div>
    <div class="box5">
	    <table class="tb_freez table table-striped table-bordered table-condensed mt10" id="bqtjzqlb"  width="100%">
	       <thead> 
	        <tr>
	        	<td  style="display:none"></td>
	            <th>借款人</th>
	            <th>债权来源</th>
	            <th>职业情况</th>
	            <th>中间人</th>
	            <th>借款用途</th>
	            <th>还款起始日期</th>
	            <th>还款末期日期</th>
	            <th>可用期数</th>
	            <th>实际出借期数</th>
	            <th>原始债权价值（￥）</th>
	            <th>剩余债权价值（￥）</th>
	            <th>月利率</th>
	            <th>推荐额度（￥）</th>
	        </tr>
	        </thead>
	        <c:forEach items="${borrowCanceEx.list }" var="item">
	        <tr class="trClass">
	            <td  class ="th1" style="display:none">
	            <input type="hidden" name="creditValueId" value="${item.creditValueId}"/> 
	            <input type="hidden" name="dictLoanFreeFlag" value="${item.dictLoanFreeFlag }"/>
	            <input type="hidden" name="dictLoanType" value="${item.dictLoanType }"/>
	            </td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${item.loanName }</td> --%>
	            <td> ${fns:getDictLabel(item.dictLoanType, 'tz_credit_src', '-')}</td>
	            <td>${fns:getDictLabel(item.loanJob, 'jk_prof_type', '-')}</td>
	            <td>${item.loanMiddleMan }</td>
	            <td>${item.loanPurpose }</td>
	            <td>${fns:getFormatDate(item.loanBackmoneyFirday,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatDate(item.loanBackmoneyLastday,"yyyy-MM-dd")}</td>
	            <td>${item.loanMonths}</td>
	            <td>${item.loanMonthsSurplus}</td>
	            <td>${fns:getFormatNumber(item.loanQuota,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}</td>
	            <td>${fns:formatNumber(item.loanMonthRate)}</td>
	            <td ><input class ="th2" type="text" value="${fns:getFormatNumber(item.creditLines,'￥#,##0.00')}" digits='1'/></td>
	        </tr>
	        </c:forEach>
	    </table>
	</div>
</div>
	<div id="modal-sub" class="modal fade subwindow" align="center">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" style='text-align:left;'>待推荐可用债权列表</h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</body>
</html>