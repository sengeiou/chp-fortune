<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>转投大金融查看列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/lend/finish/lendApply_finish_list.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/colorbox.css" />
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.colorbox.js"></script> 
</head>
<body class="body_r">
    <div class="box1 mb10">
	<form id="searchForm" method="post" action="${ctx}/lend/finish/todjrcklist?menuId=${menuId}">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<label class="lab">客户姓名：</label>
					<input name="name" value="${lendApply.name}" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">到期日期：</label>
					<input type="text" name="finalLinitDate" id="finalLinitDate" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'finalLinitDateTo\',{d:-1});}'})"
						value="${fns:getFormatDate(lendApply.finalLinitDate,'yyyy-MM-dd')}" />-
					<input type="text" name="finalLinitDateTo" id="finalLinitDateTo" from-checkDate="#finalLinitDate"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'finalLinitDate\',{});}'})"
						value="${fns:getFormatDate(lendApply.finalLinitDateTo,'yyyy-MM-dd')}" />
				</td>
				<td>
					<label class="lab">出借编号：</label>
					<input name="lendCode" value="${lendApply.lendCode}" type="text" class="cf_input_text178">
				</td>
			</tr>
			<tr>
				<td>
					<label class="lab">计划出借日期：</label>
					<input type="text" name="lendStart" id="lendStart" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'lendEnd\',{d:-1});}'})"
						value="${fns:getFormatDate(lendApply.lendStart,'yyyy-MM-dd')}" /> - 
					<input type="text" name="lendEnd" id="lendEnd" from-checkDate="#lendStart"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'lendStart\',{});}'})"
						value="${fns:getFormatDate(lendApply.lendEnd,'yyyy-MM-dd')}" />
				</td>
				<td>
					<label class="lab">出借产品：</label>
					<sys:productselect name="productCode" value="${lendApply.productCode}" multiple="true"/>
				</td>
				<td>
					<label class="lab">付款方式：</label>
					<select id="payType" name="applyPay" class="select78" multiple="multiple">
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
			<tr id='T1' style='display:none;'>
				<td>
					<label class="lab">营业部：</label>
					<sys:orgTree id="org"  name="storeId" value="${lendApply.storeId}" labelName="orgName"  labelValue="${lendApply.orgName}" />
				</td>
				<td>
					<label class='lab'>渠道标识：</label>
					<select id="channelMarking" name="channelMarking" class="select78" multiple="multiple">
						<c:forEach items="${dicts.tz_channel_flag}" var="item">
							<option value="${item.value }"
								<c:if test="${fns:multiOption(lendApply.channelMarking,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">转投审批：</label>
					<select name="switchApproveStatus" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_switch_approve_status}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.switchApproveStatus,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr id="T2" style="display: none;">
				<td>
					<label class="lab">大金融审核日期：</label>
					<input type="text" name="approveDateStart" id="approveDateStart" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'approveDateEnd\',{d:-1});}'})"
						value="${fns:getFormatDate(lendApply.approveDateStart,'yyyy-MM-dd')}" />-
					<input type="text" name="approveDateEnd" id="approveDateEnd" from-checkDate="#approveDateStart"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'approveDateStart\',{});}'})"
						value="${fns:getFormatDate(lendApply.approveDateEnd,'yyyy-MM-dd')}" />
				</td>
				<td>
					<label class="lab">补息天数：</label>
					<input name="interestDay" value="${lendApply.interestDay}" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">完结状态：</label>
					<select class="select78" id="dictApplyEndStatus" name="dictApplyEndStatus">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_finish_state}" var="item">
							<option value="${item.value}"
								<c:if test="${lendApply.dictApplyEndStatus==item.value}">selected</c:if>>${item.label}</option>
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
		<div class='mb10 ml10'>
			<auth:hasPermission key="cf:lendfinishdjr:exportexcel">
				<input type="button" value="导出EXCEL" class="btn btn_sc ml10" onclick="_exportExcel();"/>
			</auth:hasPermission> 
		</div>
	</form>
	</div>
	<div class='box5'>
		<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
			<thead>
			<tr>
				<th>
					<input type="checkbox" class="ml10 mr10 checkAll">全选
				</th>
				<th>客户姓名</th>
				<th>到期日期</th>
				<th>出借编号</th>
				<th>计划出借日期</th>
				<th>计划出借金额</th>
				<th>出借产品</th>
				<th>付款方式</th>
				<th>营业部</th>
				<th>实际回款金额</th>
				<th>渠道标识</th>
				<th>转投审批</th>
				<th>大金融审核日期</th>
				<th>补息天数</th>
				<th>完结状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(page.list)>0 }">
					<c:forEach items="${page.list}" var="item">
						<tr>
							<td><input type="checkbox" id="ids" name="ids" value="${item.applyCode}"></td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${item.customer.custName}</td> --%>
							<td>${fns:getFormatDate(item.expireDate,'yyyy-MM-dd')}</td>
							<td>${item.applyCode}</td>
							<td>${fns:getFormatDate(item.lendDate,'yyyy-MM-dd')}</td>
							<td>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
							<td>${fns:getProductLabel(item.productCode) }</td>
							<td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'--')}</td>
							<td>${item.storesName}</td>
							<td>${fns:getFormatNumber(item.actualBackMoney,'￥#,##0.00')}</td>
							<td>${fns:dictName(dicts.tz_channel_flag,item.channelMarking,'--')}</td>
							<td>${fns:dictName(dicts.tz_switch_approve_status,item.switchApproveStatus,'--')}</td>
							<td>${fns:getFormatDate(item.approveDate,'yyyy-MM-dd')}</td>
							<td>${item.interestDay}</td>
							<td>${fns:dictName(dicts.tz_finish_state,item.applyEndStatus,'--')}</td>
							<td>
		 						<a href="${ctx}/lend/finish/goDetailDJR?pageFlag=finishDetail&lendCode=${item.applyCode }&customerCode=${item.customer.custCode }">详情</a>
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