<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/creditor/creditorSendList.js" type="text/javascript">var ctx =${ctx};</script>
<title>债权发送列表</title>
</head>
<body>
<sys:message content="${message}" />
<div class="body_top">
	<div class="box1 mb10">
	  <form:form id="searchForm" modelAttribute="creditorTradeEx" action="${ctx}/creditor/creditorSend/list" method="post">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="matchingId" name="matchingId" type="hidden"/>
				<td>
                    <label class="lab">产品名称：</label>
                    <sys:productselect id="product" name="product" value="${creditorTradeEx.product}" multiple="true"/>
                </td>
          		<td>
                    <label class="lab">账单日：</label>
                    <select class="select78" id="matchingBillDay" name="matchingBillDay">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_bill_day}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.matchingBillDay==item.value}">selected</c:if>>${item.label }</option>
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
            <tr>
            	<td>
                	<label class="lab">本期出借日期：</label> 
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartStart" name="matchingInterestStartStart"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'matchingInterestStartEnd\')}'})" readonly="readonly" value="${creditorTradeEx.matchingInterestStartStart}"> -
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartEnd" name="matchingInterestStartEnd"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'matchingInterestStartStart\')}'})" readonly="readonly" value="${creditorTradeEx.matchingInterestStartEnd}">
                
                </td>
                <td>
                    <label class="lab">制作状态：</label>
                    <select class="select78" id="matchingFileStatus" name="matchingFileStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_filecp_state}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.matchingFileStatus==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <label class="lab">账单发送状态：</label>
                    <select class="select78" id="matchingFileSendStatus" name="matchingFileSendStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.com_email_state}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.matchingFileSendStatus==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr id="T1" style="display:none">
                <td><label class="lab">初始出借日期：</label><input type="text" class="Wdate cf_input_text178" onfocus="WdatePicker()" id="applyLendDay" name="applyLendDay" value="${fns:getFormatDate(creditorTradeEx.applyLendDay,'yyyy-MM-dd')}"/></td>
                <td><label class="lab">划扣日期：</label><input type="text" class="Wdate cf_input_text178" onfocus="WdatePicker()" id="applyDeductDay" name="applyDeductDay" value="${fns:getFormatDate(creditorTradeEx.applyDeductDay,'yyyy-MM-dd')}"></td>
                <td><label class="lab">所在城市：</label><sys:cityselect id="addrCity" name="addrCity" value="${creditorTradeEx.addrCity}" multiple="true"/></td>
            </tr>
            <tr id="T2" style="display:none">
                <td>
                    <label class="lab">结算状态：</label>
                    <select class="select78" id="matchingPayStatus" name="matchingPayStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_settle_status}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.matchingPayStatus==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>  
                </td>
                <td>
                    <label class="lab">账单收取方式：</label>
                    <select class="select78" id="loanBillRecv" name="loanBillRecv"/>
                    <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_taken_mode}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.loanBillRecv==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                </td>
                <td>
                	<label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" id="customerName" name="customerName" value="${creditorTradeEx.customerName}">
                </td>
            </tr>
            <tr id="T3" style="display:none">
            	<td>
                    <label class="lab">出借完结状态：</label>
                    <select class="select78" id="applyEndStatus" name="applyEndStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_finish_state}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.applyEndStatus==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
                <td><label class="lab">邮箱地址：</label><input type="text" class="cf_input_text178" id="customerEmail" name="customerEmail" value="${creditorTradeEx.customerEmail}"></td>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" id="lendCode" name="lendCode" maxlength="50" value="${creditorTradeEx.lendCode}"></td>
            </tr>
            <tr id="T4" style="display:none">
                <td>
                    <label class="lab">营业部：</label>
                    <sys:orgTree id="org" name="org" value="${creditorTradeEx.org}" labelName="orgName" labelValue="${creditorTradeEx.orgName}" />
                </td>
                <td>
                    <label class="lab">付款方式：</label>
                    <select class="select78" id="applyPay" name="applyPay">
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_pay_type}" var="item">
                             <option value="${item.value }" <c:if test="${creditorTradeEx.applyPay==item.value}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
					<label class="lab">匹配标识：</label>
					<select name="dictMatchingFlagType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_matching_flag}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(creditorTradeEx.dictMatchingFlagType,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
            <input class="btn cf_btn-primary" type="submit" id="search" value="搜索"/>
            <input class="btn cf_btn-primary" type="reset" id="reset" value="清除"/>
            <div class="xiala" id="showMore" onclick="javascript:show();"></div>
        </div>
       </form:form>
	 </div>
	 <div class="pb5 pt10 pr30">
	 	<auth:hasPermission key="cf:borrowsend.batchsend">
			<button class="btn btn_sc ml10" id="sendProtocol">批量发送协议</button>&nbsp;
		</auth:hasPermission>
		<auth:hasPermission key="cf:borrowsend.batchdownloadpdf">
			<button class="btn btn_sc" id="batchDownloadWord">批量下载协议WORD</button>&nbsp;
		</auth:hasPermission>
		<auth:hasPermission key="cf:borrowsend.batchdownloadword">
			<button class="btn btn_sc" id="batchDownloadPdf">批量下载协议PDF</button>&nbsp;
		</auth:hasPermission>
		<auth:hasPermission key="cf:borrowsend.exportexcel">
			<button class="btn btn_sc" id="outExcel">批量导出Excel</button>&nbsp;
		</auth:hasPermission>
		<auth:hasPermission key="cf:borrowsend.textcompose">
			<button class="btn btn_sc" id="handelDocument">批量合成文件</button>&nbsp;　
		</auth:hasPermission>
			总笔数：<lable id="totalCount">${page.count}</lable>条&nbsp;&nbsp;&nbsp;总金额：<label id="totalMoney">${fns:getFormatNumber(totalMoney,'￥#,##0.00')}</label>元
            <input type="hidden" id="count" value="${page.count}"/>
            <input type="hidden" id="tMoney" value="${fns:getFormatNumber(totalMoney,'0.00')}"/>
     </div>
      <sys:columnCtrl pageToken="creditor_creditorSendList"></sys:columnCtrl>
    <div class="box5">
    <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="checkAll" class="checkAll"/></th>
            <th>序号</th>
            <th>出借编号</th>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>所在城市</th>
            <th>营业部</th>
            <th>本期出借日期</th>
            <th>初始出借日期</th>
            <th>初始出借金额</th>
            <th>出借产品</th>
            <th>付款方式</th>
            <th>匹配标识</th>
            <th>出借完结状态</th>
            <th>结算状态</th>
            <th>账单类型</th>
            <th>已推荐金额</th>
            <th>账单发送状态</th>
            <th>账单收取方式</th>
            <th>制作状态</th>
            <th>邮箱地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item" varStatus="status">
	        <tr>
	            <td><input type="checkbox" id="checkBoxOne" value="${item.matchingId},${item.lendCode}" onclick="changeMoney(this);"/></td>
	            <td>${status.count}</td>
	            <td>${item.lendCode}</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerName}</td> --%>
	            <td>${item.customerCode}</td>
	         	<td>${fns:getCityLabel(item.cityId) }</td>
	            <td>${item.org}</td>
	            <td>${fns:getFormatDate(item.applyLendDay,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(item.startApplyLendDay,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatNumber(item.startApplyLendMoney,'￥#,##0.00')}</td>
	            <td>${item.productName}</td>
	            <td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'-')}</td>
	            <td>${fns:dictName(dicts.tz_matching_flag,item.matchingFlag,'-') }</td>
	            <td>${fns:dictName(dicts.tz_finish_state,item.applyEndStatus,'-')}</td>
	            <td>${fns:dictName(dicts.tz_settle_status,item.matchingPayStatus,'-')}</td>
	            <td>${fns:dictName(dicts.tz_bill_state,item.matchingFirstdayFlag,'-')}</td>
	            <td>
	                ${fns:getFormatNumber(item.matchingMatchMoney,'￥#,##0.00')}
	                <input type="hidden" id="money" name="money" value="${item.matchingMatchMoney}"/>
	            </td>
	            <td>${fns:dictName(dicts.com_email_state,item.matchingFileSendStatus,'-')}</td>
	            <td>${fns:dictName(dicts.tz_taken_mode,item.loanBillRecv,'-')}</td>
	            <td>${fns:dictName(dicts.tz_filecp_state,item.matchingFileStatus,'-')}</td>
	            <td>${item.customerEmail}</td>
	            <td>
	            	<auth:hasPermission key="cf:borrowsend.transact">
	            		<button class="cf_btn_edit" onclick="handle('${item.matchingId}','${item.matchingFileStatus}','${item.matchingFileSendStatus}','${item.lendCode}','${item.matchingPayStatus}');">办理</button>
	            	</auth:hasPermission>
	            </td>
	        </tr>
	      </c:forEach>
    </table>
    </div>
    <div class="pagination">${page}</div>
    </div>
     <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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