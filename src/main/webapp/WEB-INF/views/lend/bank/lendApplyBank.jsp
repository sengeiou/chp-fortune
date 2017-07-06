<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
	<div class="">
	    <div class="title bankZone" >
	        <div class="l"><h2 class="f14 ml10">开户姓名：${customer.custName} </h2><input type="button" class="btn btn_sc ml10" id="addBankInWindow" value="添加银行"></div>
	    </div>
	    <div class="box6 bankZone" >
	        <table  class="table table-striped table-bordered table-condensed" id="bankListInWindow">
	            <thead> 
		            <tr>
		                <th>选银行</th>
		                <th>开户行</th>
		                <th>开户行所在城市</th>
		                <th>卡或折</th>
		                <th>银行账户</th>
		                <th>操作</th>
		           </tr>
	           </thead>
	           <tbody id="bankTBodyInWindow">
	           <c:if test="${fn:length(bankList)>0 }">
		           <c:forEach items="${bankList }" var="item" varStatus="sta">
			           <tr>
			           		 <input type="hidden" name="canModify" value="${item.canModify }" />
			           		 <input type="hidden" name="paymentBankId" value="${item.id }" />
							 <input type="hidden" name="receiveBankId" value="${item.id }" />
							 <input type="hidden"  name="bankAccountName" value="${customer.custName }" />
			                <td>
			                	<input type="radio" name="selectBanckId" value="${item.id }" />
			                </td>
							<td>
								<select style="width: 80px;" name="bankNameId" disabled="disabled">
									<c:forEach items="${fns:getDictList('tz_open_bank') }" var="bank">
										<option value="${bank.value }" <c:if test="${bank.value==item.accountBankId}">selected</c:if>>${bank.label }</option>
									</c:forEach>
								</select>
								<input type="text" name="branchAddress" value="${item.accountBranch }" maxlength="100" style="width: 120px;margin-left:10px;" disabled="disabled">
							</td>
							<td>
								<select name="bankProvince" style="width: 60px;" disabled="disabled" id="bankProvinceSelect">
									<c:forEach items="${provinceList }" var="_province">
										<option value="${_province.id}" <c:if test="${_province.id==item.accountAddrProvince}">selected=selected</c:if>>${_province.areaName }</option>
									</c:forEach>
								</select>
								<select name="bankCity" style="width: 60px;" disabled="disabled" id="bankCitySelect" class="city-select">
									<option value="">请选择</option>
									<c:forEach items="${cityList[sta.index] }" var="_city">
										<option value="${_city.id}" <c:if test="${_city.id==item.accountAddrCity}">selected=selected</c:if>>${_city.areaName }</option>
									</c:forEach>
								</select>
								<select name="bankDistrict" style="width: 60px;" disabled="disabled" id="bankDistrictSelect">
									<option value="">请选择</option>
									<c:forEach items="${districtList[sta.index] }" var="_district">
										<option value="${_district.id}" <c:if test="${_district.id==item.accountAddrDistrict}">selected=selected</c:if>>${_district.areaName }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select name="bankCard_passbook" style="width: 50px;" disabled="disabled">
									<c:forEach items="${fns:getDictList('com_card_type') }" var="bank">
										<option value="${bank.value }" <c:if test="${bank.value==item.accountCardOrBooklet}">selected</c:if>>${bank.label }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="text" name="bankAccountNum" value="${item.accountNo }" maxlength="30" style="width: 120px;" disabled="disabled"/>
							</td>
							<td>
								<c:if test="${item.canModify == 0 or item.canModify == -1 }">
									<input type="button" value="修改" class="cf_btn_edit" name="modifyBank" />
								</c:if>
								<c:if test="${item.canModify == -1 }">
									<input type="button" value="删除" class="cf_btn_edit" name="deleteBank"/>
								</c:if>
							</td>
			           </tr>
			       </c:forEach>
	           </c:if>
	           </tbody>
	        </table>
	    </div>
	</div>  
	
	
