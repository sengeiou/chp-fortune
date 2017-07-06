<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/lendApplyLook/lendApprovalListLook.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>

<title>出借申请审批查看</title>
</head>
<body>
	<div class="body_r">
		<div class="box1 mb10">
			<form id="searchForm" action="${ctx}/lendApplyApprovalLook/loadLendApplyApprovalLookList" method="post">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
<!-- 				<input id="lendCodeHidden" name="lendCodeList" type="hidden"/> -->
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td><label class="lab">客户编号：</label>
							<input type="text" class="cf_input_text178" name="customerCode" value="${lso.customerCode}" /></td>
						<td><label class="lab">客户姓名：</label>
							<input type="text" class="cf_input_text178" name="customerName" value="${lso.customerName}" /></td>
						<td><label class="lab">出借编号：</label>
							<input type="text" class="cf_input_text178" name="lendCode" value="${lso.lendCode}" /></td>
					</tr>
					<tr>
						<td><label class="lab">营业部：</label>
						<sys:orgTree id="org" name="orgCode" value="${lso.orgCode}" labelValue="${lso.orgName}" labelName="orgName"/>
						</td>
						<td><label class="lab">合同编号：</label>
							<input type="text" class="cf_input_text178" name="applyContractNo" value="${lso.applyContractNo}" /></td>
						<td><label class="lab">审核人：</label>
							<input type="text" class="cf_input_text178" name="checkByName" value="${lso.checkByName}" /></td>

					</tr>
					<tr id="T1" style="display: none;">
						<td><label class="lab">出借产品：</label>
							<sys:productselect name="productCode" value="${lso.productCode}" multiple="true"/></td>
						<td><label class="lab">出借状态：</label>
							<select class="select78" id="lendStatus" name="lendStatus" multiple="multiple">
                        		<option value="">请选择</option>
                        		<option value="2" <c:if test="${fn:contains(lso.lendStatus,'2')}">selected</c:if>>待出借审批</option>
                        		<option value="3" <c:if test="${fn:contains(lso.lendStatus,'3')}">selected</c:if>>审批通过</option>
                        		<option value="4" <c:if test="${fn:contains(lso.lendStatus,'4')}">selected</c:if>>审批不通过</option>
                    		</select></td>
						<td><label class="lab">付款方式：</label>
							<select class="select78" id="applyPay" name="applyPay" multiple="multiple">
                       			<c:forEach items="${dicts.tz_pay_type}" var="item">
                            		<option value="${item.value }" <c:if test="${lso.applyPay==item.value}">selected</c:if>>${item.label }</option>
                      			</c:forEach>
                   			</select></td>

					</tr>
					<tr id="T2" style="display: none;">
						<td><label class="lab">计划出借日：</label>
                			<input type="text" name="applyLendDayFrom" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(lso.applyLendDayFrom,'yyyy-MM-dd')}"/> -
                			<input type="text" name="applyLendDayTo" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(lso.applyLendDayTo,'yyyy-MM-dd')}"/></td>
						<td><label class="lab">计划划扣日：</label>
							<input type="text" name="applyDeductDayFrom"  class="Wdate input_text70" value="${fns:getFormatDate(lso.applyDeductDayFrom,'yyyy-MM-dd')}" onfocus="WdatePicker()"/> -
							<input type="text" name="applyDeductDayTo" class="Wdate input_text70" value="${fns:getFormatDate(lso.applyDeductDayTo,'yyyy-MM-dd')}" onfocus="WdatePicker()"/></td>
						<td>
							<label class="lab">划扣平台：</label>
							<select name="dictApplyDeductType" class="select78"  multiple="multiple">
		                		<c:forEach items="${dicts.tz_deduct_plat}" var="item">
		                			<option value="${item.value}" 
										<c:if test="${fns:multiOption(lso.dictApplyDeductType,item.value)}">
											selected
										</c:if>
									>${item.label}</option>
		                		</c:forEach>
		                	</select>
						</td>
				</tr>
				<tr id="T3" style='display:none;'>
				<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(lso.accountBank,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
			</tr>
				</table>
				<div class="tright pr30 pb5 pt10">
					<input type="submit" value="搜索" class="btn cf_btn-primary" onclick="javascript:window.searchForm.submit();" />
					<input type="reset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"></div>
				</div>
			</form>

		</div>
		<div class='mb10'>
			<auth:hasPermission key="cf:lendapprovellook:exportexcel">
				<input id="exportExcel" type="button" value="导出EXCEL" class="btn btn_sc ml10"	 />
			</auth:hasPermission> 
			<!-- <input id="exportApprovalPassDetail" type="button" value="导出审核通过明细" class="btn btn_sc"   /> -->
			<auth:hasPermission key="cf:lendapprovellook:count">
				<input id="statistics" class="btn btn_sc "  type="button" value="统计" />
			</auth:hasPermission>  
			<span>总金额：${fns:getFormatNumber(totalAmount,'￥#,##0.00')}</span>
		</div>
		<div class="box5">
			<table class='table table-striped table-bordered table-condensed data-list-table' width="100%">
				<thead>
				<tr>
				   <!--  <th><input type="checkbox" class="checkAll">全选</th> -->
					<th>客户姓名</th>
					<th>客户编号</th>
					<th>出借编号</th>
					<th>计划出借日期</th>
					<th>计划出借金额</th>
					<th>出借方式</th>
					<th>划扣平台</th>
					<th>付款方式</th>
					<th>省份|城市</th>
					<th>营业部</th>
					<th>出借状态</th>
					<th>审核人</th>
					<th>操作</th>
				</tr>
				</thead>
				<c:forEach items="${page.list}" var="item" varStatus="s">
					<tr>
					   <%--  <td id="checkboxTd"><input type="checkbox" id="checkOne" value="${item.lend_code}">${s.index+1}</td> --%>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td align="center">***</td>
						<%-- <td align="center">${item.customer_name}</td> --%>
						<td align="center"> ${item.customer_code}  </td>
						<td align="center">${item.lend_code}</td>
						<td align="center">${item.apply_lend_day}</td>
						<td align="center">${fns:getFormatNumber(item.apply_lend_money,'￥#,##0.00')}</td>
						<td align="center">${item.product_name}</td>
						<td align="center">${fns:dictName(dicts.tz_deduct_plat,item.dict_apply_deduct_type, '-')}</td>
						<td align="center">${fns:dictName(dicts.tz_pay_type,item.apply_pay, '-')}</td>
						<td align="center">${item.province_name}|${item.city_name}</td>
						<td align="center">${item.storesnames}</td>
						<td align="center">${fns:dictName(dicts.tz_lend_state,item.lend_status, '-')}</td>
						<td align="center">${item.check_by_name}</td>
						<td align="center">
							<auth:hasPermission key="cf:lendapprovellook:transact">
								<input type="button" class="cf_btn_edit" value="办理"
								onclick="window.location.href='${ctx}/lendApplyApprovalLook/goLendApplyApprovalPage?code=${item.lend_code}&managerId=${item.uid}'" />
							</auth:hasPermission>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page">
				 <div class="pagination">${page}</div>
			</div>
		</div>
	</div>
	
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