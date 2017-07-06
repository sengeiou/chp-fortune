<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/redemption/common.js"></script>
<title>提前赎回审批待办</title>
</head>
<body>
	<div class="body_r">
		<div class="box1 mb10">
		<form id="searchForm" action="${ctx}/myApply/redemption/fetchTaskItems" method="post">
	    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td><label class='lab'>客户姓名：</label><input type="text" name="customerName" class="cf_input_text178" value="${entity.customerName}"></td>
						<td><label class='lab'>客户编号：</label><input type="text" name="customerCode" class="cf_input_text178" value="${entity.customerCode}"></td>
						<td><label class='lab'>出借编号：</label><input type="text" name="lendCode" class="cf_input_text178" value="${entity.lendCode}"></td>
					</tr>
					<tr>
						<td><label class="lab">营业部：</label> 
							<sys:orgTree id="org" name="orgID" value="${entity.orgID}" labelName="department" labelValue="${entity.department}" />
						</td>
						<td><label class='lab'>省份|城市：</label><sys:cityselect name="city" value="${entity.city}"/></td>
						<td><label class="lab">付款方式：</label> 
							<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
								<c:forEach items="${dicts.tz_pay_type}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(entity.applyPay,item.value)}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr id="T1" style="display: none;">
						<td><label class='lab'>出借金额：</label>
							<input type="text" name="applyLendMoney" id="applyLendMoney" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" class="input_text70" value="${entity.applyLendMoney}"> -
							<input type="text" name="applyLendMoneyTo" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" from-checkInt="#applyLendMoney" value="${entity.applyLendMoneyTo}" class="input_text70"></td>
						<td><label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${entity.productCode}" multiple="true"/></td>
						<td><label class="lab">账单日：</label> 
							<select class="select78" id='applyBillday' name='applyBillday' multiple="multiple">
								<c:forEach items="${dicts.tz_bill_day}" var='item'>
									<option value="${item.value }" <c:if test="${fns:multiOption(entity.applyBillday, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
					</tr>
				</table>
					 <div class="tright pr30 pb5 pt10">
				     	<input type="submit" onclick="resetPage()" id="search" value="搜索" class="btn cf_btn-primary">
				     	<input type="reset" value="清除" class="btn cf_btn-primary">
				     	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
				     </div>
				</form>
		</div>
		<div class="box5">
			<sys:columnCtrl pageToken="redemption_approvalList"></sys:columnCtrl>
			<table class="table table-striped table-bordered table-condensed data-list-table">
				<thead>
				<tr>
					<th>客户姓名</th>
					<th>客户编号</th>
					<th>出借编号</th>
					<th>计划出借日期</th>
					<th>账单日</th>
					<th>计划出借金额</th>
					<th>出借产品</th>
					<th>划扣平台</th>
					<th>付款方式</th>
					<th>省份|城市</th>
					<th>营业部</th>
					<th>回款日期</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:forEach items="${page.list}" var="item">
				<tr>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.customerName }</td> --%>
					<td>${item.customerCode }</td>
					<td>${item.applyCode }</td>
					<td>${fns:getFormatDate(item.applyLendDate,'yyyy-MM-dd')}</td>
					<td>
						${fns:dictName(dicts.tz_bill_day,item.applyBillday,'-') }
					</td>
					<td>${fns:getFormatNumber(item.applyLendMoney ,'￥#,##0.00')}</td>
					<td>
						
						${fns:getProductLabel(item.productName) }
					</td>
					<td>
						${fns:dictName(dicts.tz_deduct_plat,item.applyDeductType,'-') }
					</td>
					<td>
						${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }
					</td>
					
					<td>${fns:getProvinceLabel(item.province)}|${fns:getCityLabel(item.city) }</td>
					<td>
						${fns:getOrgNameById(item.department) }
					</td>
					<td>${fns:getFormatDate(item.backMoneyDay,'yyyy-MM-dd')}</td>
					<td class="tcenter">
						<auth:hasPermission key="cf:redemptionapprovalstay:transact">
							<button class="cf_btn_edit" onclick="window.location.href='${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&wobNum=${item.wobNum}&token=${item.token}&dealType=0'">办理</button>
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