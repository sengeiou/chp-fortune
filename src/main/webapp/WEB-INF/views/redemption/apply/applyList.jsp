<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>

<script type="text/javascript"  src="${ctxWebInf}/static/jqGrid/4.6/plugins/jquery.contextmenu.js"></script> 
<title>提前赎回申请列表</title>
</head>
<body>
<div class=body_r>
    <div class="box1 mb10">
    <form id="searchForm" action="${ctx}/myApply/redemption/applyList">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class='lab'>客户姓名：</label><input type="text" name="customerName" value="${rdm.customerName}" class="cf_input_text178"></td>
                <td><label class='lab'>客户编号：</label><input type="text" name="customerCode" value="${rdm.customerCode}" class="cf_input_text178"></td>
                <td><label class='lab'>出借编号：</label><input type="text" name="lendCode" value="${rdm.lendCode}" class="cf_input_text178"></td>
            </tr>
            <tr>
				<td><label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${rdm.productCode}" multiple="true"/></td>
                <td><label class="lab">营业部：</label>
                	<sys:orgTree id="org" name="orgID" value="${rdm.orgID}" labelName="orgName" labelValue="${rdm.orgName}"/>
                </td>
                <td><label class='lab'>城市：</label><sys:cityselect name="addrCity" value="${rdm.addrCity}" multiple="true"/></td>
            </tr>
            <tr id="T1" style="display:none;">
                <td><label class='lab'>计划出借日：</label>
                	<input type="text" id="applyLendDay" name="applyLendDay" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDayTo\',{d:-1});}'})" 
                	onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyLendDay,'yyyy-MM-dd' )}"/> -
                	<input type="text" id="applyLendDayTo" name="applyLendDayTo" class="Wdate input_text70" from-checkDate="#applyLendDay" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDay\',{});}'})" 
                	onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyLendDayTo,'yyyy-MM-dd')}"/>
                </td>
				<td><label class='lab'>计划划扣日：</label>
                	<input type="text" name="applyDeductDay" id="applyDeductDay" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDayTo\',{d:-1});}'})" onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyDeductDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyDeductDayTo" id="applyDeductDayTo" class="Wdate input_text70" from-checkDate="#applyDeductDay" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDay\',{});}'})" onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyDeductDayTo,'yyyy-MM-dd')}"/>
				</td>
				<td><label class="lab">账单日：</label> 
					<select class="select78" id='applyBillday' name='applyBillday' multiple="multiple">
						<c:forEach items="${dicts.tz_bill_day}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(rdm.applyBillday, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select></td>
            </tr>
			<tr id="T2" style="display:none;">
                <td><label class='lab'>出借金额：</label>
                	<input type="text" id="applyLendMoney" name="applyLendMoney" value="${rdm.applyLendMoney}" class='input_text70' numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)"> -
                	<input type="text" name="applyLendMoneyTo" value="${rdm.applyLendMoneyTo}" class='input_text70' numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" from-checkInt="#applyLendMoney"></td>
				<td><label class='lab'>到期日：</label>
                	<input type="text" name="applyExpireDay" id="applyExpireDay" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{d:-1});}'})" onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyExpireDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyExpireDayTo" id="applyExpireDayTo" class="Wdate input_text70" from-checkDate="#applyExpireDay" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDay\',{});}'})" onblur="dateCheck(this)" value="${fns:getFormatDate(rdm.applyExpireDayTo,'yyyy-MM-dd')}"/>
                </td>
				<td><label class="lab">付款方式：</label> 
					<select class="select78" id='payType' name='applyPay' multiple="multiple">
						<c:forEach items="${dicts.tz_pay_type}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(rdm.applyPay,item.value)}">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select></td>
            </tr>
			<tr id="T3" style="display:none;">
                <td><label class='lab'>理财经理：</label><input type="text" name="managerCode" value="${rdm.managerCode}" class='cf_input_text178'></td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input id="search" onclick="resetPage()" type="submit" value="搜索" class="btn cf_btn-primary">
        	<input type="reset" value="清除" class="btn cf_btn-primary">
        	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
        </div>
	   </form>
    </div>
    
	
		<div class="box5">
		<sys:columnCtrl pageToken="redemption_applyList"></sys:columnCtrl>
		<table class="table table-striped table-bordered table-condensed data-list-table ">
			<thead>
			<tr>
				<th>客户姓名</th>
				<th>客户编号</th>
				<th>出借编号</th>
				<th>计划出借日期</th>
				<th>账单日</th>
				<th>计划出借金额</th>
				<th>出借方式</th>
				<th>客户经理</th>
				<th>付款方式</th>
				<th>省份|城市</th>
				<th>营业部</th>
				<th>操作</th>
			</tr>
			</thead>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.customerName}</td> --%>
					<td>${item.customerCode}</td>
					<td>${item.lendCode}</td>
					<td>${fns:getFormatDate(item.applyLendDay,"yyyy-MM-dd")}</td>
					<td align="center">${fns:dictName(dicts.tz_bill_day,item.applyBillday,'-') }</td>
					<td>${fns:getFormatNumber(item.applyLendMoney,'￥#,##0.00')}</td>
					<td>${item.productName}</td>
					<td>${item.userName}</td>
					<td>
						${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }
					</td>
					<td>${item.addrProvince}|${item.addrCity}</td>
					<td>${item.orgName}</td>
					<td class="tcenter">
						<auth:hasPermission key="cf:redemptionapply:transact">
							<button class="cf_btn_edit" onclick="window.location.href='${ctx}/bpm/flowController/openLaunchForm?flowCode=redemptionfl&lendCode=${item.lendCode}'">办理</button>
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