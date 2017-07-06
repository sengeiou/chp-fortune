<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出借审核已办列表</title>
<meta name="decorator" content="default" />
	<script type="text/javascript" src="${ctxWebInf}/js/lend/finish/lendApply_approval_finish_list.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body class="body_r">
    <div class="box1 mb10">
	<form id="searchForm" method="post" action="${ctx}/lend/finish/fetchApprovalFinishItems">
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
					<sys:orgTree id="org"  name="storeId" value="${lendApply.storeId}" labelName="orgName"  labelValue="${lendApply.orgName}" />
				</td>
				<td>
					<label class="lab">出借产品：</label>
					<sys:productselect name="productCode" value="${lendApply.productCode}"/>
				</td>
				<td>
					<label class="lab">申请日期：</label>
					<input name="applyDate" value="${fns:getFormatDate(lendApply.applyDate,'yyyy-MM-dd')}" type="text" class="cf_input_text178 Wdate" onfocus="WdatePicker()">
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
				<td>
					<label class="lab">出借状态：</label>
					<select id="lendStatus" name="lendStatus" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_lend_state}" var="item">
							<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.lendStatus,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id="T2" style="display:none">
				<td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_deduct_plat}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.dictApplyDeductType,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.accountBank,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">付款方式：</label>
					<select id="payType" name="applyPay" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_pay_type}" var="item">
							<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.applyPay,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
						</c:forEach>
					</select>
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
		<div class='mb10 ml10'>
			<auth:hasPermission key="cf:lendapprovefinish:exportexcet">
				<input id="exportExcel" type="button" value="导出EXCEL" class="btn btn_sc ml10"	 />
			</auth:hasPermission> 
				<!-- <input id="exportApprovalPassDetail" type="button" value="导出审核通过明细" class="btn btn_sc"   /> -->
			<auth:hasPermission key="cf:lendapprovefinish:count">
				<input id="statistics" class="btn btn_sc "  type="button" value="统计" />
			</auth:hasPermission> 
				<span>总金额：￥<fmt:formatNumber  type="number"  value="${totalAmount }" pattern="#,##0.00"/></span>
		</div>
	<sys:columnCtrl pageToken="customer_workflow_lendApply_approval_finish_list"></sys:columnCtrl>
	<div class='box5'>
		<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
			<thead>
			<tr>
				<th>客户姓名</th>
				<th>客户编号</th>
				<th>营业部</th>
				<th>出借编号</th>
				<th>计划出借日期</th>
				<th>计划出借金额</th>
				<th>出借方式</th>
				<th>划扣平台</th>
				<th>付款方式</th>
				<th>省份|城市</th>
				<th>出借状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(page.list)>0 }">
					<c:forEach items="${page.list}" var="item">
						<tr>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${item.customer.custName}</td> --%>
							<td>${item.customer.custCode}</td>
							<td>${item.storesName }</td>
							<td>${item.applyCode}</td>
							<td>${fns:getFormatDate(item.lendDate,'yyyy-MM-dd')}</td>
							<td>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
							<td>${fns:getProductLabel(item.productCode) }</td>
							<td>${fns:dictName(dicts.tz_deduct_plat,item.deductTypeId,'') }</td>
							<td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'') }</td>
							<td>${fns:getProvinceLabel(item.provinceId) }|${fns:getCityLabel(item.cityId) }</td>
							<td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'') }</td>
						    <td>
						    	<auth:hasPermission key="cf:lendapprovefinish:transact">
						        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=1">详细</a>
						        </auth:hasPermission>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="pagination">${page}</div>
	<!-- 弹出框 -->
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="提交" class="btn cf_btn-primary" id="subSubmit"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>