<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出借申请已办列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/lend/finish/lendApply_finish_list.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/colorbox.css" />
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.colorbox.js"></script> 
</head>
<body class="body_r">
    <div class="box1 mb10">
	<form id="searchForm" method="post" action="${ctx}/lend/finish/fetchFinishItems?menuId=${menuId}">
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
					<sys:productselect name="productCode" value="${lendApply.productCode}" multiple="true"/>
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
					<select id="lendStatus" name="lendStatus" class="select78" multiple="multiple">
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
								</c:if>>${item.label}
							</option>
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
								</c:if>>${item.label}
							</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">回款日期：</label>
					<input type="text" name="backMoneyDayBegin"  class="Wdate input_text70" value="<fmt:formatDate value='${lendApply.backMoneyDayBegin}'  pattern="yyyy-MM-dd"/>" onfocus="WdatePicker()"/> -
					<input type="text" name="backMoneyDayEnd" class="Wdate input_text70" value="<fmt:formatDate value='${lendApply.backMoneyDayEnd}'  pattern="yyyy-MM-dd"/>" onfocus="WdatePicker()"/></td>
				</td>
			</tr>
			<tr>
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
			</tr>
		</table>
		<div class="tright pr30 pb5 pt10">
			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			<input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			<div id="showMore" class="xiala" onclick="javascript:show();"></div>
		</div>
	</form>
	</div>
	<div>
		<p class="mb10 ml10">
			<span>总金额：${fns:getFormatNumber(totalAmount,'￥#,##0.00')}</span>
		</p>
	</div>
	<sys:message content="${content }"></sys:message>
	<sys:columnCtrl pageToken="customer_workflow_lendApply_finish_list"></sys:columnCtrl>
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
				<th>出借产品</th>
				<th>划扣平台</th>
				<th>付款方式</th>
				<th>省份|城市</th>
				<th>出借状态</th>
				<th>渠道标识</th>
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
							<td>${fns:getProductLabel(item.productCode)}</td>
							<td>${fns:dictName(dicts.tz_deduct_plat,item.deductTypeId,'')}</td>
							<td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'')}</td>
							<td>${fns:getProvinceLabel(item.provinceId) }|${fns:getCityLabel(item.cityId)}</td>
							<td>${fns:dictName(dicts.tz_lend_state,item.lendStatusName,'')}</td>
							<td>${fns:dictName(dicts.tz_channel_flag,item.channelMarking,'')}</td>
						    <td>
							    <c:if test="${item.payType==SHNZ }">
		 					        <a href="${ctx}/lend/finish/goDetail?pageFlag=finishDetail&lendCode=${item.applyCode }&customerCode=${item.customer.custCode }">详细</a>
							    </c:if>
							    <c:if test="${item.payType!=SHNZ }">
							        <a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=1">详细</a>
							    </c:if>
							    <c:if test="${item.lendStatus==15 or item.lendStatus==14}">
									<input type="button" class='cf_btn_edit' id="makingFile" value="合成申请书" onclick="_makingFile('${item.applyCode}','${item.applyId}')">
								</c:if>
								 <c:if test="${item.tuoguanStatus==3 and item.lendStatus ==8 }">
									 <a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=2">修改</a>
								</c:if>
								<c:if test="${item.lendStatus==1}">
									 <a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&dealType=3">上传附件</a>
								</c:if>
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