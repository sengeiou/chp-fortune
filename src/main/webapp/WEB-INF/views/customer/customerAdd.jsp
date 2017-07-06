<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新增客户</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
	<script src="${ctxWebInf }/js/customer/customerAdd.js" type="text/javascript"></script>
</head>
<body>
	<form id="inputForm" class="form-horizontal">
		<div class="title">
	        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
	    </div>
		<div class="box6">
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td>
						<label class="lab"><span class='red'>*</span>客户姓名：</label>
						<input id="custName" name="customer.custName" type="text" value="" maxlength="50" ChEN="1" required class="cf_input_text178">
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>性别：</label>
						<c:forEach items="${dicts.sex }" var="item">
							<input id="dictCustSex" name="customer.dictCustSex" type="radio" value="${item.value }" required>${item.label }
						</c:forEach>
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>客户来源：</label>
						<select id="dictCustSource" name="customer.dictCustSource" select_required="1" class="select78">
							<c:forEach items="${dicts.tz_customer_src }" var="item">
								<option value="${item.value }">${item.label }</option>
							</c:forEach>
						</select>
						
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab"><span class='red'>*</span>移动电话：</label>
						<input id="custMobilephone" name="customer.custMobilephone" type="text" value="" maxlength="11" required mobilenum="1" class="cf_input_text178" onchange="isChanger('1')">
						<input type="button" style="display: none" id="phoneVerify" class="" value="手机号校验" onclick="Verification()" >
					</td>
					<td>
						<label class="lab"><span class='red'></span>固定电话：</label>
						<input id="custPhone" name="customer.custPhone" type="text" value="" maxlength="15" digits="1" class="cf_input_text178">
						
					</td>
					<td>
						<label class="lab">电子邮箱：</label>
						<input id="custEmail" name="customer.custEmail" type="text" value="" maxlength="50" email='1' class="cf_input_text178">
						
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab"><span class='red'></span>工作单位：</label>
						<input id="custUnitname" name="customer.custUnitname" type="text" value="" maxlength="100" class="cf_input_text178">
					
					</td>
					<td>
						<label class="lab"><span class='red'></span>行业：</label>
						<input id="custIndustry" name="customer.custIndustry" type="text" value="" maxlength="50" class="cf_input_text178">
						
					</td>
					<td>
						<label class="lab"><span class='red'></span>职务：</label>
						<input id="custPost" name="customer.custPost" type="text" value="" maxlength="50" class="cf_input_text178">
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab"><span class='red'></span>传真：</label>
					    <input id="custFax" name="customer.custFax" type="text" value="" maxlength="20" class="cf_input_text178">
					  
					</td>
					<td>
						<label class="lab"><span class='red'>*</span>地址：</label>
					    <input id="addr" name="address.addr" type="text" value="" maxlength="100" required class="cf_input_text178">
					   
					</td>	
					<td>
						<label class="lab"><span class='red'></span>邮编：</label>
					    <input id="addrPostalCode" name="address.addrPostalCode" type="text" value="" maxlength="50" postnum="1" class="cf_input_text178">
					 
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label class="lab"><span class='red'>*</span>所属团队经理：</label>
					    <select id="teamManagerCode" name="customer.teamManagerCode" select_required="1">
							<option value="">请选择</option>
							<c:forEach items="${teamManagerList }" var="item">
							   <option value="${item.id}">${item.roleName } : ${item.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label class="lab"><span class='red'>*</span>所在城市：</label>
					    <select id="addrProvince" name="address.addrProvince" select_required="1">
							<option value="-1">请选择</option>
							<c:forEach items="${provinceList }" var="item">
							   <option value="${item.id}">${item.areaName }</option>
							</c:forEach>
						</select>
						<select id="addrCity" name="address.addrCity" select_required="1">
							<option value="">请选择</option>
						</select>
						<select id="addrDistrict" name="address.addrDistrict" select_required="1">
							<option value="">请选择</option>
						</select>
					   
					</td>
				</tr>
			</table>
		</div>
		<div class="title">
	        <div class="l"><h2 class="f14 ml10">咨询信息</h2></div>
	    </div>
		<div class="box6">
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td>
						<label class="lab"><span class='red'>*</span>沟通日期：</label>
						<input type="text" id="askDate" name="advisory.askDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate cf_input_text178" required>
					</td>
					<td colspan="2">
						<label class="lab"><span class='red'></span>沟通时间：</label>
						<input type="text" id="askBeginDate" name="advisory.askBeginDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'askEndDate\',{});}'})" class="Wdate cf_input_text178 " > -
						<input type="text" id="askEndDate" name="advisory.askEndDate"  value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'askBeginDate\',{});}'})" class="Wdate cf_input_text178"  from-checkDate="#askBeginDate">
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab"><span class='red'>*</span>沟通方式：</label>
						<select id="askType" name="advisory.askType" select_required="1" class='select78' required >
							<option value="">请选择</option>
							<c:forEach items="${dicts.tz_communication_mode }" var="item">
								<option value="${item.value }">
									<c:if test="${advisory.askType==item.value }">selected</c:if>${item.label }
								</option>
							</c:forEach>
						</select>
					   
					</td>
					<td>
						<label class="lab"><span class='red'></span>意向模式：</label>
						<sys:productselect name="advisory.askProduct" value="" />
					   
					</td>
					<td>
						<label class="lab"><span class='red'></span>下次联系方式：</label>
						<input type="text" id="askNextType" name="advisory.askNextType" class='cf_input_text178' maxlength="50">
				    
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab"><span class='red'></span>下次沟通日期：</label>
						<input type="text" id="askNextDate" name="advisory.askNextDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})" class="Wdate cf_input_text178" from-checkDate="#askDate">
				    
					</td>
					<td>
						<label class="lab"><span class='red'></span>意向金额：</label>
						<input type="text" id="askInputMoney" name="advisory.askInputMoney" min="1" value="" maxlength="10" class='cf_input_text178' digits="1">
				    	
					</td>
					<td>
						<label class="lab"><span class='red'></span>出资日期：</label>
						<input type="text" id="askInputDate" name="advisory.askInputDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})" class="Wdate cf_input_text178" >
				    	
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label class="lab"><span class='red'></span>备注：</label>
					    <textarea id="askDes" cols="10" rows="4" name="advisory.askDes" class='textarea_refuse' maxlength="500"></textarea>
			
					</td>
				</tr>
			</table>
		</div>
		<div class='tright pr30'>
			<input id="btnSave" class="btn cf_btn-primary" type="button" value="保 存" />
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">客户理财经理信息</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>