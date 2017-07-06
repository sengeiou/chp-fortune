<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


	<script type="text/javascript" src="${ctxWebInf}/js/lendChange/lendChangeHistory.js"></script>	

<div class="body_r">
    <div class='pb5 pt10 pr30'>
    <h4 class="f14 ml10">银行信息</h4>
    </div>
    <div class="box6">
       
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td style="width:50%;">
					<table style="width:100%;" id="chengeTable">
                      <tr>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class='red'>变更前：</span></label>
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td class='tcenter' style="width:25%;" colspan="2">
								<label class="lab"><span class="red">变更后：</span><br /></label>
							</td>
						</tr>
                        <tr><td colspan="5" style='height:0px;'><p  style='width:100%;border-bottom:1px solid #e5c4a1;'></p></td></tr>
						<tr>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text" value="${fns:dictName(dicts.tz_open_bank,bv.changeBegin.customerAccount.accountBankId,'-')}"  readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
							<c:if test="${bv.changeBegin.lendloanapply.applyPay==4}">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getLabel(bv.changeBegin.customerAccount.accountAddrProvince)}&nbsp;${fns:getLabel(bv.changeBegin.customerAccount.accountAddrCity)}" readonly="readonly" class="cf_input_text178">
							</c:if>
							<c:if test="${bv.changeBegin.lendloanapply.applyPay!=4}">
								<label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getProvinceLabel(bv.changeBegin.customerAccount.accountAddrProvince)}&nbsp;${fns:getCityLabel(bv.changeBegin.customerAccount.accountAddrCity)}&nbsp;${fns:getDistrictLabel(bv.changeBegin.customerAccount.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							</c:if>							
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td style="width:25%;">
								<label class="lab"><span class="red">*</span>开户行：</label><input type="text"  value="${fns:dictName(dicts.tz_open_bank,bv.changeAfter.countAfter.accountBankId,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="width:25%;">
							  <c:if test="${bv.changeAfter.lendloanapply.applyPay==4}">
							   <label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getLabel(bv.changeAfter.countAfter.accountAddrProvince)}&nbsp;${fns:getLabel(bv.changeAfter.countAfter.accountAddrCity)}" readonly="readonly" class="cf_input_text178">
							  </c:if>
							   <c:if test="${bv.changeAfter.lendloanapply.applyPay!=4}">
							   <label class="lab"><span class="red">*</span>银行卡所在城市：</label><input type="text" value="${fns:getProvinceLabel(bv.changeAfter.countAfter.accountAddrProvince)}&nbsp;${fns:getCityLabel(bv.changeAfter.countAfter.accountAddrCity)}&nbsp;${fns:getDistrictLabel(bv.changeAfter.countAfter.accountAddrDistrict)}" readonly="readonly" class="cf_input_text178">
							  </c:if>
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(dicts.com_card_type,bv.changeBegin.customerAccount.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${bv.changeBegin.customerAccount.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>卡或折：</label><input type="text" value="${fns:dictName(dicts.com_card_type,bv.changeAfter.countAfter.accountCardOrBooklet,'-')}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>具体支行：</label><input type="text" value="${bv.changeAfter.countAfter.accountBranch}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>
						<tr>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${bv.changeBegin.customerAccount.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${bv.changeBegin.customerAccount.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
							<td style="min-height:0px; border-left:1px solid #DFDFDF;"></td>
							<td>
								<label class="lab"><span class="red">*</span>开户姓名：</label><input type="text" value="${bv.changeAfter.countAfter.accountName}" readonly="readonly" class="cf_input_text178">
							</td>
							<td>
								<label class="lab"><span class="red">*</span>银行账号：</label><input type="text" value="${bv.changeAfter.countAfter.accountNo}" readonly="readonly" class="cf_input_text178">
							</td>
						</tr>					
					</table>
				</td>				      
            </tr>
        </table>
    </div>  
</div>	
