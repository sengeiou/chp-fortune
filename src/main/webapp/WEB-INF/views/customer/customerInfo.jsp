<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="tright pr30 mb10">
    <input id="eliminateGoldAccount" class="btn btn_sc" type="button" value="金账户销户" style="display: none;"/>
    <c:if test="${customer.custState==1 or customer.custState==5 or customer.custState==2 }">
    	<input id="customerModify" class="btn btn_sc" type="button" value="修改"/>
    </c:if>
    	<input id="customerSave" class="btn btn_sc" style="display:none;" type="button" value="保存"/>
    <c:if test="${customer.custState==1 or customer.custState==5}">
    	<input id="customerTransferLend" class="btn btn_sc" type="button" value="开户"/>
    </c:if>
    <c:if test="${customer.poolStatus == '2' }">  <!-- 1公共池、2分配、3退回电销  -->
    	<input id="sendBack2TeleSale" class="btn btn_sc" type="button" value="退回电销" />
    </c:if>
    	<!-- <input id="goBack" class="btn btn_sc" type="button" value="返 回" onclick="history.go(-1)"/> -->
    	<input id="goBack" class="btn btn_sc" type="button" value="返 回" onclick="goBackWithParams();"/>
</div>
<div class="box1 mb10">
<form id="inputForm" action="${ctx}/customer/investment/add" method="post" class="form-horizontal">
	<input type="hidden" id="id" name="id" value="${customer.id }">
	<input type="hidden" id="custCode" name="custCode" value="${customer.custCode }">
	<input type="hidden" id="address.id" name="address.id" value="${customer.address.id }">
	<input type="hidden" id="currentCustomerPhone" name="currentCustomerPhone" value="${customer.custMobilephone }">
	<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>客户姓名：</label>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<input id="custName" name="custName" type="text" value="***" disabled="disabled" maxlength="50" class="cf_input_text178" ChEn='1'  required>
				
				<%-- <input id="custName" name="custName" type="text" value="${customer.custName }" disabled="disabled" maxlength="50" class="cf_input_text178" ChEn='1'  required>
				 --%>
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>性别：</label>
				<c:forEach items="${dicts.sex }" var="item">
					<input id="dictCustSex" name="dictCustSex" type="radio" value="${item.value }" required <c:if test="${customer.dictCustSex==item.value}">checked=checked</c:if> disabled="disabled">${item.label }
				</c:forEach>
				
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>客户来源：</label>
				<select id="dictCustSource" name="dictCustSource" disabled="disabled" class="select78" select_required="1">
					<c:forEach items="${dicts.tz_customer_src}" var="item">
						<option value="${item.value }" <c:if test="${customer.dictCustSource==item.value}">selected</c:if>>${item.label }</option>
					</c:forEach>
				</select>
				
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'>*</span>移动电话：</label>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<input id="custMobilephone" name="custMobilephone" type="text" maxlength="11" required mobilenum="1" value="***" disabled="disabled" class="cf_input_text178">
				<%-- <input id="custMobilephone" name="custMobilephone" type="text" maxlength="11" required mobilenum="1" value="${customer.custMobilephone }" disabled="disabled" class="cf_input_text178"> --%>
				
			</td>
			<td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<label class="lab"><span class='red'></span>固定电话：</label>
				<input id="custPhone" name="custPhone" type="text" maxlength="15" digits="1" value="***" disabled="disabled" class="cf_input_text178">
				<%-- <input id="custPhone" name="custPhone" type="text" maxlength="15" digits="1" value="${customer.custPhone }" disabled="disabled" class="cf_input_text178"> --%>
				
			</td>
			<td>
				<label class="lab">电子邮箱：</label>
				<input id="custEmail" name="custEmail" type="text" value="${customer.custEmail }" maxlength="50" email='1' disabled="disabled" class="cf_input_text178">
				
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'></span>工作单位：</label>
				<input id="custUnitname" name="custUnitname" type="text" value="${customer.custUnitname }" maxlength="100" disabled="disabled" class="cf_input_text178">
				
			</td>
			<td>
				<label class="lab"><span class='red'></span>行业：</label>
				<input id="custIndustry" name="custIndustry" type="text" maxlength="50" value="${customer.custIndustry }" disabled="disabled" class="cf_input_text178">
				
			</td>
			<td>
				<label class="lab"><span class='red'></span>职务：</label>
				<input id="custPost" name="custPost" type="text" value="${customer.custPost }" maxlength="50" disabled="disabled" class="cf_input_text178">
				
			</td>
		</tr>
		<tr>
			<td>
				<label class="lab"><span class='red'></span>传真：</label>
			    <input id="custFax" name="custFax" type="text" value="${customer.custFax }" maxlength="20" disabled="disabled" class="cf_input_text178">
			 
			</td>
			<td>
				<label class="lab"><span class='red'>*</span>地址：</label>
			    <input id="addr" name="address.addr" type="text" value="${customer.address.addr }" maxlength="100" required disabled="disabled" class="cf_input_text178">
			   
			</td>
			<td>
				<label class="lab"><span class='red'></span>邮编：</label>
			    <input id="addrPostalCode" name="address.addrPostalCode" type="text" maxlength="50" postnum="1" value="${customer.address.addrPostalCode }" disabled="disabled" class="cf_input_text178">
			  
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label class="lab"><span class='red'>*</span>所在城市：</label>
			    <select id="addrProvince" name="address.addrProvince" disabled="disabled" select_required="1">
			    	<option value="">请选择</option>
					<c:forEach items="${provinceList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==provinceId }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
				<select id="addrCity" name="address.addrCity" disabled="disabled" select_required="1">
					<option value="">请选择</option>
					<c:forEach items="${cityList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==cityId }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
				<select id="addrDistrict" name="address.addrDistrict" disabled="disabled" select_required="1">
					<option value="">请选择</option>
					<c:forEach items="${districtList }" var="item">
						<option value="${item.id}" <c:if test="${item.id==districtId }">selected=selected</c:if>>${item.areaName }</option>
					</c:forEach>
				</select>
			   
			</td>
			<td>
				<label class="lab">电销专员：</label>
				<input id="teleManagerName" name="teleManagerName" type="text" value="${customer.teleManagerName }" disabled="disabled" class="cf_input_text178">
			</td>
		</tr>
	</table>
</form>
</div>