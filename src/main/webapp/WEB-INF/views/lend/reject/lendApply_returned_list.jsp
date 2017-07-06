<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出借申请退回列表</title>
<meta name="decorator" content="default" />
</head>
<body class="body_r">
    <div class="box1 mb10">
	<form id="searchForm" method="post" action="${ctx}/lend/reject/fetchReturnedTaskItems">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<label class="lab">客户编号：</label>
					<input name="code" value="${lendApply.code }" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">客户姓名：</label>
					<input name="name" value="${lendApply.name}" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">出借编号：</label>
					<input name="lendCode" value="${lendApply.lendCode}" type="text" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">营业部：</label>
					<input name="storeId" value="${lendApply.storeId}" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">出借产品：</label>
					<sys:productselect name="productCode" value="${lendApply.productCode}" />
				</td>
				<td>
					<label class="lab">出借日期：</label>
					<input name="lendDate" value="${fns:getFormatDate(lendApply.lendDate,'yyyy-MM-dd')}" type="text" class="cf_input_text178 Wdate" onfocus="WdatePicker()">
				</td>
			</tr>
			<tr id='T1' style='display:none;'>
				<td>
					<label class="lab">计划划扣日：</label>
					<input name="deductStart" value="${fns:getFormatDate(lendApply.deductStart,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;"> -
					<input name="deductEnd" value="${fns:getFormatDate(lendApply.deductEnd,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;">
				</td>
				<td>
					<label class="lab">计划出借日：</label>
					<input name="lendStart" value="${fns:getFormatDate(lendApply.lendStart,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;"> -
					<input name="lendEnd" value="${fns:getFormatDate(lendApply.lendEnd,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate select55" style="width:100px;">
				</td>
			</tr>
		</table>
		<div class="tright pr30 pb5 pt10">
			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			<input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			<div id="showMore" class="xiala" onclick="javascript:show();"></div>
		</div>
	</form>
	</div>
	<sys:columnCtrl pageToken="customer_workflow_lendApply_returned_list"></sys:columnCtrl>
	<div class='box5'>
		<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
			<thead>
				<tr>
					<th>客户姓名</th>
					<th>客户编号</th>
					<th>出借编号</th>
					<th>计划出借日期</th>
					<th>计划出借金额</th>
					<th>出借产品</th>
					<th>划扣平台</th>
					<th>付款方式</th>
					<th>省份|城市</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(items)>0 }">
					<c:forEach items="${items}" var="item">
						<tr>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${item.customerName}</td> --%>
							<td>${item.customerCode}</td>
							<td>${item.applyCode}</td>
							<td>${fns:getFormatDate(item.lendDate,'yyyy-MM-dd')}</td>
							<td>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
							<td>
								${fns:getProductLabel(item.lendProduct) }
							</td>
							<td>
								${fns:dictName(dicts.tz_deduct_plat,item.deductType,'-') }
							</td>
							<td>
								${fns:dictName(dicts.tz_pay_type,item.payState,'-') }
							</td>
							<td>${fns:getProvinceLabel(item.province) }|${fns:getCityLabel(item.city) }</td>
						    <td>
					        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&wobNum=${item.wobNum}&dealType=0&token=${item.token}">办理</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>