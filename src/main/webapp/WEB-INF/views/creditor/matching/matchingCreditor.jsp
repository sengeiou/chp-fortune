<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/creditor/matching/matchingCreditor.js" type="text/javascript">
var ctx =${ctx};
</script>
<script src="${ctxWebInf }/js/creditor/matching/matchingCommon.js" type="text/javascript">
var ctx =${ctx};
</script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title>待债权推荐信息主页面</title>
</head>
<body>
<div class="body_r">
	<div class="box1 mb10">
	  <form id="searchForm" action="${ctx}/matchingcreditor/list" method="post" >
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr> 
                <td>
                 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="matchingId" name="matchingId" type="hidden"/>
				<label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${matchingCreditorEx.productCode}" multiple="true"/>
                </td>
                <td nowrap="nowrap">
                    <label class="lab">初始出借金额：</label>
                    <input type="text" class="input_text70" id="startApplyLendMoneyFrom" name="startApplyLendMoneyFrom" number="1" greaterThan="0"  value="${matchingCreditorEx.startApplyLendMoneyFrom}" 
                   maxlength="10"> -
                    <input type="text" class="input_text70" id="startApplyLendMoneyTo" name="startApplyLendMoneyTo" value="${matchingCreditorEx.startApplyLendMoneyTo}" 
                   number="1" greaterThan="0"  from-checkInt="#startApplyLendMoneyFrom"   maxlength="10" >
                </td>
                <td>
                	<label class="lab">初始出借日期：</label> 
                	<input type="text" class="Wdate input_text70" id="startApplyLendDayStart" name="startApplyLendDayStart"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startApplyLendDayEnd\')}'})" readonly="readonly" value="${matchingCreditorEx.startApplyLendDayStart}"> -
                	<input type="text" class="Wdate input_text70" id="startApplyLendDayEnd" name="startApplyLendDayEnd"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startApplyLendDayStart\')}'})" readonly="readonly" value="${matchingCreditorEx.startApplyLendDayEnd}">
                </td>
            </tr>
            <tr>
                <td><label class="lab">账单日：</label> <select class="select78"
							id='matchingBillDayStr' name='matchingBillDayStr' multiple="multiple">
								<c:forEach items="${dicts.tz_bill_day}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(matchingCreditorEx.matchingBillDayStr,item.value )}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
						</select>
				</td>
				<td nowrap="nowrap">
                    <label class="lab">本期推荐金额：</label>
                    <input type="text" class="input_text70" id="matchingBorrowQuotaFrom" name="matchingBorrowQuotaFrom" number="1" greaterThan="0"  value="${matchingCreditorEx.matchingBorrowQuotaFrom}" 
                   maxlength="10"> -
                    <input type="text" class="input_text70" id="matchingBorrowQuotaTo" name="matchingBorrowQuotaTo" value="${matchingCreditorEx.matchingBorrowQuotaTo}" 
                   number="1" greaterThan="0"  from-checkInt="#matchingBorrowQuotaFrom"   maxlength="10" >
                </td>
                <td>
                	<label class="lab">本期出借日期：</label> 
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartStart" name="matchingInterestStartStart"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'matchingInterestStartEnd\')}'})" readonly="readonly" value="${matchingCreditorEx.matchingInterestStartStart}"> -
                	<input type="text" class="Wdate input_text70" id="matchingInterestStartEnd" name="matchingInterestStartEnd"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'matchingInterestStartStart\')}'})" readonly="readonly" value="${matchingCreditorEx.matchingInterestStartEnd}">
                </td>
            </tr>
            <tr id="T1" style="display:none">
            	<td><label class="lab">账单类型：</label> <select class="select78"
							id='matchingFirstdayFlag' name='matchingFirstdayFlag' multiple="multiple">
								<c:forEach items="${dicts.tz_bill_state}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(matchingCreditorEx.matchingFirstdayFlag,item.value )}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
						</select>
				</td>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${matchingCreditorEx.lendCode}"></td>
                <td><label class="lab">付款方式：</label> <select class='select78 mr34'
							id='applyPay' name='applyPay' multiple="multiple">
								<c:forEach items="${dicts.tz_pay_type}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(matchingCreditorEx.applyPay,item.value )}"> selected</c:if>>${item.label }
									</option>
								</c:forEach>
						</select>
				</td>
            </tr>
             <tr id="T2" style="display:none">
                <td>
                    <label class="lab">匹配人：</label>
	                <input type="text" class="cf_input_text178" id="matchingUserName" name="matchingUserName" value="${matchingCreditorEx.matchingUserName}">
                </td>
                <td>
					<label class="lab">计划划扣日：</label>
					<input name="deductStart" value="${matchingCreditorEx.deductStart}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;"> -
					<input name="deductEnd" value="${matchingCreditorEx.deductEnd}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;">
				</td>
				<td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_deduct_plat}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(matchingCreditorEx.dictApplyDeductType,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
            </tr>
             <tr  id="T3" style="display:none"> 
                <td><label class='lab'>城市：</label><sys:cityselect name="city" value="${matchingCreditorEx.city}" multiple="true"/></td>
            	<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${fns:multiOption(matchingCreditorEx.accountBank,d.value)}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">客户姓名：</label>
	                <input type="text" class="cf_input_text178" id="customerName" name="customerName" value="${matchingCreditorEx.customerName}">
				</td>
            </tr>
            <tr  id="T4" style="display:none"> 
               <td><label class="lab">营业部：</label>
				 <sys:orgTree id="orgCode" name="orgCode"  value="${matchingCreditorEx.orgCode}" labelValue="${matchingCreditorEx.orgName}" labelName="orgName"/>
				</td>
                <td>
					<label class="lab">匹配标识：</label>
					<select name="dictMatchingFlagType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_matching_flag}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(matchingCreditorEx.dictMatchingFlagType,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
            </tr>
        </table>
		 <div class="tright pr30 pb5 pt10">
		     <input class="btn cf_btn-primary" type="submit" id="search" value="搜索" />
		     <input type="reset" value="清除" class="btn cf_btn-primary">
		     <div class="xiala" id="showMore" onclick="javascript:show();"></div>
		 </div>
	   </form>
	   </div>
	   <p class='ml10'>
	   	<auth:hasPermission key="cf:matchingcreditor:exportexcel">
	    	<button class="btn btn_sc ml10" onclick="outExcel()">导出EXCEL</button>
	    </auth:hasPermission>
	    <auth:hasPermission key="cf:matchingcreditor:matchingcount">
	    	<button class="btn btn_sc" onclick="ppjl()">匹配统计</button>
	    </auth:hasPermission>
	    	总笔数：<lable id="numberTotal">${numberTotal}</lable>笔&nbsp;&nbsp;&nbsp;&nbsp;
	    	初始出借总金额:<lable id="lendMoneyTotal">
	    	 ${fns:getFormatNumber(lendMoneyTotal,'￥#,##0.00')}
	    	<%-- <fmt:formatNumber value="${lendMoneyTotal }" /> --%>
	    	</lable>&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;
	    	本期推荐总金额 :<lable id="borrowQuotaTotal">
	    	 ${fns:getFormatNumber(borrowQuotaTotal,'￥#,##0.00')}
	    <%-- 	<fmt:formatNumber value="${borrowQuotaTotal }" /> --%>
	    	</lable>&nbsp;元
	    	<input type="hidden" id="numberCount" value="${numberTotal}"/>
            <input type="hidden" id="lendMoneyCount" value="${lendMoneyTotal }"/>
            <input type="hidden" id="borrowQuoCount" value="${borrowQuotaTotal }"/>
    </p>     
     <sys:columnCtrl pageToken="creditor_matching_matchingCreditor"></sys:columnCtrl>
     <div class='box5'>
     <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
        <tr>
         <th><input type="checkbox" id="checkAll" class="checkAll" onclick="checkAll(this);"/></th>
            <th>序号</th>
            <th>出借编号</th>
            <th>营业部</th>
            <th>省份</th>
			<th>城市</th>
            <th>客户姓名</th>
            <th>出借产品</th>
            <th>初始出借日期</th>
            <th>初始出借金额</th>
            <th>本期出借日期</th>
            <th>本期推荐金额</th>
            <th>账单类型</th>
            <th>账单日</th>
            <th>到期日期</th>
            <th>划扣平台</th>
	        <th>出借银行</th>
	        <th>计划划扣日期</th>
            <th>匹配人</th>
            <th>匹配标识</th>
            <th>付款方式</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item" varStatus="status">
	        <tr>
	         <td><input type="checkbox" id="checkBoxOne" value="${item.matchingId}" onclick="changeMoney(this);"/></td>
	           
	            <td>${status.count}</td>
	            <td>${item.lendCode}</td>
	            <td>${item.orgName}</td>
	            <td>${fns:getProvinceLabel(item.provinceId) }</td>
				<td>${fns:getCityLabel(item.cityId) }</td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
	            <%-- <td>${item.customerName}</td> --%>
	            <td>${item.productName}</td>
	            <td><fmt:formatDate value="${item.startApplyLendDay}" pattern="yyyy-MM-dd"/></td>
	            <td>
	           	 ${fns:getFormatNumber(item.startApplyLendMoney,'￥#,##0.00')}
	            	<%-- fmt:formatNumber value="${item.startApplyLendMoney}"  groupingUsed="true"  maxFractionDigits="2" minIntegerDigits="2"/>
	            	 --%><input type="hidden" id="lendMoney" name="lendMoney" value="${item.startApplyLendMoney}"/>
	            </td>
	            <td><fmt:formatDate value="${item.matchingInterestStart}" pattern="yyyy-MM-dd"/></td>
	            <td>
	            	 ${fns:getFormatNumber(item.matchingBorrowQuota,'￥#,##0.00')}
	            	<%-- <fmt:formatNumber value="${item.matchingBorrowQuota}"  groupingUsed="true"  maxFractionDigits="2" minIntegerDigits="2"/>
	            	 --%><input type="hidden" id="borrowQuota" name="borrowQuota" value="${item.matchingBorrowQuota}"/>
	            </td>
	            <td>${fns:dictName(dicts.tz_bill_state,item.matchingFirstdayFlag,'-') }</td>
	            <td>${item.matchingBillDay }</td>
	            <td><fmt:formatDate value="${item.applyExpireDay}" pattern="yyyy-MM-dd"/></td>
	            <td>${fns:dictName(dicts.tz_deduct_plat,item.dictApplyDeductType,'-')}</td>
		        <td>${fns:dictName(dicts.tz_open_bank,item.accountBank,'-')}</td>
	            <td><fmt:formatDate value="${item.applyDeductDay}" pattern="yyyy-MM-dd"/></td>
	            <td>${item.matchingUserName}</td>
	             <td>${fns:dictName(dicts.tz_matching_flag,item.matchingFlag,'-') }</td>
	             <td>
	            	${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }
	            </td>
	             <td>
	            	${fns:dictName(dicts.tz_matching_status,item.matchingStatus,'-') }
	            </td>
	            <td>
	            	<auth:hasPermission key="cf:matchingcreditor:transact">
	            		<button class="cf_btn_edit" onclick="banli('${item.matchingId}','${item.matchingFirstdayFlag }')">办理</button>
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
					<h4 class="modal-title">匹配规则列表</h4>
				</div>
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
</body>
</html>