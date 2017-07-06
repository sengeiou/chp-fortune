<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	
<div class="body_r">
    <div class="title bankZone" >
        <div class="l">
           <h2 class="f14 ml10">开户姓名：${customer.custName} </h2>
            <c:if test="${canAdd == 1 }">
           		<input type="button" class="btn btn_sc ml10" id="addBankInWindow" value="添加银行"  >
           </c:if>
        </div>
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
		           		 <input type="hidden" name="isAdd" value="${isAdd }" />
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
							<select name="bankProvince" style="width: 60px;" disabled="disabled" id="fy_bankProvinceSelect">
								<option value="">请选择</option>
								<c:forEach items="${provinceList }" var="_province">
									<option value="${_province.areaCode}" <c:if test="${_province.areaCode==item.accountAddrProvince}">selected=selected</c:if>>${_province.areaName }</option>
								</c:forEach>
							</select>
							<select name="bankCity" style="width: 60px;" disabled="disabled" id="fy_bankCitySelect" class="city-select">
								<option value="">请选择</option>
								<c:forEach items="${cityList[sta.index] }" var="_city">
									<option value="${_city.areaCode}" <c:if test="${_city.areaCode==item.accountAddrCity}">selected=selected</c:if>>${_city.areaName }</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="bankCard_passbook" style="width: 20px;" disabled="disabled">
								<c:forEach items="${fns:getDictList('com_card_type') }" var="bank">
									<c:if test="${bank.label == '卡' }">
										<option value="${bank.value }" selected>${bank.label }</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td>
							<input type="text" name="bankAccountNum" value="${item.accountNo }" maxlength="30" style="width: 120px;" disabled="disabled"/>
						</td>
						<td>
							<input type="button" value="修改" class="cf_btn_edit" name="modifyBank" />
							<input type="button" value="删除" class="cf_btn_edit" name="deleteBank"/>
						</td>
						<input type="hidden"  name="editFlg" />
		           </tr>
		       </c:forEach>
           </c:if>
          
           </tbody>
        </table>
        
    </div>
</div>  
