<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出借申请审批待办列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/lend/approve/lendApply_fetch_list.js"> </script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
	<form id="searchForm" method="post" action="${ctx}/lend/approve/fetchTaskItems">
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
					<sys:orgTree id="org"  name="storeId" value="${lendApply.storeId}" labelName="orgName"  labelValue="${lendApply.orgName}"/>
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
					<input name="deductStart" value="${fns:getFormatDate(lendApply.deductStart,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate input_text70" > -
					<input name="deductEnd" value="${fns:getFormatDate(lendApply.deductEnd,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate input_text70" >
				</td>
				<td>
					<label class="lab">计划出借日：</label>
					<input name="lendStart" value="${fns:getFormatDate(lendApply.lendStart,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate input_text70" > -
					<input name="lendEnd" value="${fns:getFormatDate(lendApply.lendEnd,'yyyy-MM-dd')}" type="text" onfocus="WdatePicker()" class="Wdate input_text70" >
				</td>
				  <td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_deduct_plat}" var="d">
                			<option value="${d.value}" 
								<c:if test="${fns:multiOption(lendApply.dictApplyDeductType,d.value)}">
									selected
								</c:if>
							>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>  
				
			</tr>
			  <tr id='T2' style='display:none;'>
				<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="d">
                			<option value="${d.value}" 
								<c:if test="${fns:multiOption(lendApply.accountBank,d.value)}">
									selected
								</c:if>
							>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">付款方式：</label>
					<select id="payType" name="payType" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_pay_type}" var="item">
							<option value="${item.value}" 
								<c:if test="${fns:multiOption(lendApply.payType,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">审核人：</label>
					<select id="auditor" name="auditor" class="select78">
						<option value="">请选择</option>
						<c:forEach items="${auditors}" var="item">
							<option value="${item.id}" 
								<c:if test="${item.id == lendApply.auditor}">
									selected
								</c:if>>${item.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>  
		</table>
		<div class="tright pr30 pb5 pt10">
			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="搜索" />
			<input id="btnSubmit" class="btn cf_btn-primary" type="reset" value="清除" />
			<div class="xiala"  id="showMore" onclick="javascript:show();"></div>
		</div>
	</form>
	</div>
	<p class='ml10'><span id="msg1">当前页面总数据条数：</span>
	  <span id="totalCount" totalCount=${totalCount }>${totalCount }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="msg2">当前页面总出借金额：</span>￥<span id="totalMoney" totalMoney=<fmt:formatNumber value="${totalMoney }" pattern="#,##0.00"></fmt:formatNumber>><fmt:formatNumber value="${totalMoney }" pattern="#,##0.00"></fmt:formatNumber></span>
	</p>
	<sys:message content="${content }"></sys:message>
	<sys:columnCtrl pageToken="customer_workflow_lendApply_fetch_list"></sys:columnCtrl>
	<div class='box5'>
		<table id="lenderTable" class="table table-striped table-bordered table-condensed data-list-table">
			<thead>
				<tr>
					<th><input type='checkbox'  id="checkAll" class="checkAll"/></th>
					<th>客户姓名</th>
					<th>客户编号</th>
					<th>营业部</th>
					<th>出借编号</th>
					<th>计划出借日期</th>
					<th>计划出借金额</th>
					<th>出借方式</th>
					<th>划扣平台</th>
					<th>划扣银行</th>
					<th>付款方式</th>
					<th>省份|城市</th>
					<th>审批人</th>
					<th>操作</th>
				</tr>
		    </thead>
			<tbody>
				<c:if test="${fn:length(items)>0 }">
					<c:forEach items="${items}" var="item">
						<tr>
							<td><input type="checkbox"  name="id" class="borrowMonthableListCheckbox" value="${item.applyId }" /></td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${item.customerName}</td> --%>
							<td>${item.customerCode}</td>
							<td>${fns:getOrgNameById(item.department) }</td>
							<td>${item.applyCode}</td>
							<td>${fns:getFormatDate(item.lendDate,'yyyy-MM-dd')}</td>
							<td lend_money='${item.lendMoney }'>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
							<td>
								${fns:getProductLabel(item.lendProduct) }
							</td>
							<td>
								${fns:dictName(dicts.tz_deduct_plat,item.deductType,'-') }
							</td>
							<td>
								${fns:dictName(dicts.tz_open_bank,item.deductBank,'-') }
							</td>
							<td>
								${fns:dictName(dicts.tz_pay_type,item.payState,'-') }
							</td>
							<td>${fns:getProvinceLabel(item.province) }|${fns:getCityLabel(item.city) }</td>
							<td>${item.auditor == '' ? '-' : item.auditor}</td>
						    <td>
						    	<auth:hasPermission key="cf:lendapprove:transact">
						        	<a href="${ctx}/bpm/flowController/openForm?applyId=${item.applyId}&wobNum=${item.wobNum}&dealType=0&token=${item.token}">办理</a>
						        </auth:hasPermission>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>	
	<div class="pagination">${page}</div>
</body>
</html>