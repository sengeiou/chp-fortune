<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/redemption/common.js" type="text/javascript"></script>
<title>特殊提前赎回管理列表</title>
</head>
<body>
<div class="body_new">
    <form id="searchForm" action="${ctx}/myApply/redemption/pushList" method="post">
    <div class="box1 mb10">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class='lab'>客户姓名：</label><input type="text" name="customerName" value="${rdm.customerName }" class="cf_input_text178"></td>
                <td><label class='lab'>客户编号：</label><input type="text" name="customerCode" value="${rdm.customerCode }" class="cf_input_text178"></td>
                <td><label class='lab'>出借编号：</label><input type="text" name="lendCode" value="${rdm.lendCode }" class="cf_input_text178"></td>
            </tr>
            <tr>
                <td><label class="lab">营业部：</label> 
                	<sys:orgTree id="org" name="orgID" value="${rdm.orgID}" labelName="orgName" labelValue="${rdm.orgName}"/>
                </td>
                <td><label class='lab'>城市：</label><sys:cityselect name="addrCity" value="${rdm.addrCity}" multiple="true"/></td>
                <td><label class="lab">付款方式：</label> 
                	<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
						<c:forEach items="${fns:getDictList('tz_pay_type')}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(rdm.applyPay,item.value)}">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
			<tr  id="T1" style="display:none;">
                <td><label class='lab'>审批日期：</label>
                	<input type="text" name="checkTime" id="checkTime" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'checkTimeTo\',{d:-1});}'})" 
                		value="${fns:getFormatDate(rdm.checkTime,'yyyy-MM-dd')}"/> -
                	<input type="text" name="checkTimeTo" id="checkTimeTo" from-checkDate="#checkTime" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'checkTime\',{});}'})" value="${fns:getFormatDate(rdm.checkTimeTo,'yyyy-MM-dd')}"/>
                <td><label class='lab'>出借金额：</label>
                	<input type="text" name="applyLendMoney" id="applyLendMoney" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" value="${rdm.applyLendMoney }" class="input_text70"> -
                	<input type="text" name="applyLendMoneyTo" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" from-checkInt="#applyLendMoney" value="${rdm.applyLendMoneyTo }" class="input_text70"></td>
                <td><label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${rdm.productCode}" multiple="true"/></td>
            </tr>
			<tr  id="T2" style="display:none;">
				<td><label class='lab'>到期日期：</label>
                	<input type="text" name="applyExpireDay" id="applyExpireDay" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{d:-1});}'})" 
                		value="${fns:getFormatDate(rdm.applyExpireDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyExpireDayTo" id="applyExpireDayTo" from-checkDate="#applyExpireDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDay\',{});}'})" value="${fns:getFormatDate(rdm.applyExpireDayTo,'yyyy-MM-dd')}"/>
                </td>
				<td><label class="lab">账单日：</label> 
					<select class="select78" id='applyBillday' name='applyBillday' multiple="multiple">
						<c:forEach items="${fns:getDictList('tz_bill_day')}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(rdm.applyBillday, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
                <td>
	                <label class='lab'>回款日期：</label>
	                <input type="text" name="backMoneyDay" id="backMoneyDay" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'backMoneyDayTo\',{d:-1});}'})" 
	                	value="${fns:getFormatDate(rdm.backMoneyDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="backMoneyDayTo" id="backMoneyDayTo" from-checkDate="#backMoneyDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'backMoneyDay\',{});}'})" value="${fns:getFormatDate(rdm.backMoneyDayTo,'yyyy-MM-dd')}"/>
                </td>
			</tr>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input type="submit" onclick="resetPage()" id="search" value="搜索" class="btn cf_btn-primary">
        	<input type="reset" value="清除" class="btn cf_btn-primary">
        	<input type="hidden" id="dataAmount" value="${page.count}">
        	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
        </div>
    </div>
	<p>
		<auth:hasPermission key="cf:redemptionspecial:batchpush">
			<input type="button" id="multiPush" value="批量推送" class="btn btn_sc ml10" onclick="multiPush1()"/>
		</auth:hasPermission>
	</p>
	<div class="box5">
		<sys:columnCtrl pageToken="redemption_pushList"></sys:columnCtrl>
	    <table class="table table-striped table-bordered table-condensed data-list-table">
	    	<thead>
	    	<tr>
	            <th>
	            <span onclick="checkAll();">
			   		<input type="checkbox" id="checkAll" class="checkAll">全选
			   	</span>
	            </th>
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
	            <th>审批日期</th>
	            <th>赎回类型</th>
	            <th>扣费标准</th>
	            <!-- <th>回款期限</th> -->
	            <th>是否特批</th>
	            <th>状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type="checkbox" id="dataCheck" name="redemptionId" value="${item.redemptionId }" onclick="checkOne(this)"></td>
		        <!-- 屏蔽客户姓名/手机号/身份证号 -->
		        <td>***</td>
		        <%-- <td>${item.customerName }</td> --%>
	            <td>${item.customerCode }</td>
	            <td>${item.lendCode }</td>
	            <td>${fns:getFormatDate(item.applyLendDay,'yyyy-MM-dd')}</td>
	            <td>${item.applyBillday }</td>
	            <td>${fns:getFormatNumber(item.applyLendMoney ,'￥#,##0.00')}</td>
	            <td>${item.productName }</td>
	            <td>${item.userName }</td>
	            <td align="center">${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }</td>
	            <td>${item.addrProvince }|${item.addrCity }</td>
	            <td>${item.orgName }</td>
	            <td>${fns:getFormatDate(item.checkTime ,'yyyy-MM-dd')}</td>
	           	<td align="center">${fns:dictName(dicts.tz_redeem_type,item.redemptionType,'-') }</td>
	            <td>${fns:getFormatNumber(item.redeemCost,'#,##0.00%')}</td>
	            <%-- <td>${item.redeemBackDeadline }</td> --%>
	            <td>
	            	${fns:dictName(dicts.tz_yes_no,item.checkSp,'-') }
	            </td>
	            <td>
	            	${fns:dictName(dicts.tz_redemption_status,item.redemptionStatus,'-') }
	            </td>
	            <td class="tcenter">
	            	<auth:hasPermission key="cf:redemptionspecial:transact">
	            		<input type="button" class="cf_btn_edit" value="办理" onclick="window.location.href='${ctx}/myApply/redemption/pushDetail?lendCode=${item.lendCode}'">
	            	</auth:hasPermission>
	            </td>
	        </tr>
	        </c:forEach>
	    </table>
	   </div>
    </form>
</div>
    <div class="pagination">${page}</div>
</body>
</html>