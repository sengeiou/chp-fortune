<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script src="${ctxWebInf }/js/lend/approve/lendApply_approval.js" type="text/javascript"></script>
	<title>出借审批</title>
</head>
<body>
<div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${workItem.bv.customer.custName}&nbsp;|&nbsp;${workItem.bv.lendApply.applyCode}&nbsp;|&nbsp;<fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/>&nbsp;|&nbsp;<fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber>&nbsp;|&nbsp;${fns:getProductLabel(workItem.bv.lendApply.productCode) }&nbsp;|&nbsp;${fns:getDictLabel(workItem.bv.lendApply.deductTypeId,'tz_deduct_plat','') }&nbsp;|&nbsp;${fns:getDictLabel(workItem.bv.lendApply.payType,'tz_pay_type','') }&nbsp;|&nbsp;${fns:getOrgNameById(workItem.bv.lendApply.storeOrgId) }</h2></div>
        <div class="r"><sys:attachment approval="true"></sys:attachment></div>
    </div>
    <div class='body_min' id='body_min'>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
            	<td><label class="lab"><span class="red">*</span>客户姓名：</label>***</td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label>${workItem.bv.customer.custName}</td> --%>
				<td><label class="lab"><span class="red">*</span>性     别：</label>
					<input id="dictCustSex" name="dictCustSex" type="radio" value="1" <c:if test="${workItem.bv.customer.dictCustSex=='1'}">checked=checked</c:if> disabled="disabled">男
					<input id="dictCustSex" name="dictCustSex" type="radio" value="2" <c:if test="${workItem.bv.customer.dictCustSex=='2'}">checked=checked</c:if> disabled="disabled">女
				</td>
                <td><label class="lab"><span class="red">*</span>客户编号：</label>${workItem.bv.customer.custCode}</td>  
            </tr>
            <tr style="display: none;">
                <td><label class="lab"><span class="red">*</span>出借编号：</label>${workItem.bv.lendApply.applyCode}</td>
				<td><label class="lab"><span class="red">*</span>出借金额：</label><fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber></td>
                <td><label class="lab"><span class="red">*</span>出借日期：</label><fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
			<tr style="display: none;">
                <td><label class="lab"><span class="red">*</span>出借方式：</label>${fns:getProductLabel(workItem.bv.lendApply.productCode) }</td>
				<td><label class="lab"><span class="red">*</span>划扣平台：</label>${fns:getDictLabel(workItem.bv.lendApply.deductTypeId,'tz_deduct_plat','') }</td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label>${fns:getDictLabel(workItem.bv.lendApply.payType,'tz_pay_type','') }</td>  
            </tr>
			<tr style="display: none;">
                <td colspan="3"><label class="lab"><span class="red">*</span>营业部：</label>${fns:getOrgNameById(workItem.bv.lendApply.storeOrgId) }</td>
            </tr>
        </table>	
    </div>
    
    <c:if test="${workItem.bv.lendApply.payType!='4' }">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">付款<c:if test="${fn:length(workItem.bv.banks) == 1 }">(回款)</c:if>银行信息&nbsp;&nbsp;</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1">
	            <tr>
	                <td><label class="lab"><span class="red">*</span>开户行：</label>
	                <input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.banks[0].accountBankId,'tz_open_bank','') }" disabled="disabled">
	                <input type="hidden" class="cf_input_text178" id="bankId" value="${workItem.bv.banks[0].accountBankId}">
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>银行所在地：</label>
	                	<input style="width: 100px" type="text" class="cf_input_text178"  value="${fns:getProvinceLabel(workItem.bv.banks[0].accountAddrProvince) }" disabled="disabled">&nbsp;
	                	<input style="width: 100px" type="hidden" class="cf_input_text178" id="provinceCode"" value="${workItem.bv.banks[0].accountAddrProvince}">
	                	<input type="text" class="cf_input_text178" value="${fns:getCityLabel(workItem.bv.banks[0].accountAddrCity) }" disabled="disabled">&nbsp;
	                	<input type="text" class="cf_input_text178" value="${fns:getDistrictLabel(workItem.bv.banks[0].accountAddrDistrict) }" disabled="disabled">
	                </td>    
					<td>
						<label class="lab"><span class="red">*</span>卡或折：</label>
						<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.banks[0].accountCardOrBooklet,'com_card_type','') }" disabled="disabled">
					</td>
				</tr>
	            <tr>
					<td>
						<label class="lab"><span class="red">*</span>具体支行：</label>
						<input type="text" class="cf_input_text178" value="${workItem.bv.banks[0].accountBranch }" disabled="disabled">
					</td>
					<td>
	                	<label class="lab"><span class="red">*</span>开户姓名：</label>
	                	<input type="text" class="cf_input_text178" value="${workItem.bv.banks[0].accountName }" disabled="disabled">
	                </td>    
	                <td>
	                	<label class="lab"><span class="red">*</span>账号：</label>
	                	<input type="text" id ='bankNum' class="cf_input_text178" value="${workItem.bv.banks[0].accountNo }" disabled="disabled">
	                </td>    
	            </tr>
	        </table>
	    </div>
	    
	    <c:if test="${fn:length(workItem.bv.banks) > 1 }">
	    	<div class="title">
		        <div class="l"><h2 class="f14 ml10">回款银行信息&nbsp;&nbsp;</h2></div>
		    </div>
		    <div class="box6">
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>开户行：</label>
		                	<input style="width: 100px" type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.banks[1].accountBankId,'tz_open_bank','') }" disabled="disabled">
		                </td>
		                <td>
		                	<label class="lab"><span class="red">*</span>银行所在地：</label>
		                	<input style="width: 100px" type="text"  class="cf_input_text178" value="${fns:getProvinceLabel(workItem.bv.banks[1].accountAddrProvince) }" disabled="disabled">&nbsp;
		                	<input type="text" class="cf_input_text178" value="${fns:getCityLabel(workItem.bv.banks[1].accountAddrCity) }" disabled="disabled">&nbsp;
		                	<input type="text" class="cf_input_text178" value="${fns:getDistrictLabel(workItem.bv.banks[1].accountAddrDistrict) }" disabled="disabled">
		                </td>    
						<td>
							<label class="lab"><span class="red">*</span>卡或折：</label>
							<input style="width: 100px" type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.banks[1].accountCardOrBooklet,'com_card_type','') }" disabled="disabled">
						</td>
					</tr>
		            <tr>
						<td colspan="2">
							<label class="lab"><span class="red">*</span>具体支行：</label>
							<input type="text" class="cf_input_text178" style="width:300px;" value="${workItem.bv.banks[1].accountBranch }" disabled="disabled">
						</td>
		                <td>
		                	<label class="lab"><span class="red">*</span>账号：</label>
		                	<input type="text" id="bankNum" class="cf_input_text178_24" value="${workItem.bv.banks[1].accountNo }" disabled="disabled">
		                </td>    
		            </tr>
		        </table>
		    </div>
	    </c:if>
    </c:if>
    <c:if test="${workItem.bv.lendApply.payType=='4' }">
    	<div class="title">
	        <div class="l"><h2 class="f14 ml10">金账户银行信息</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td><label class="lab"><span class="red">*</span>开户行：</label>
	                <input style="width: 100px" type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.goldAccount[0].accountBankId,'tz_open_bank','') }" disabled="disabled">
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>银行所在地：</label>
	                	<input style="width: 100px" type="text" class="cf_input_text178" value="${fns:getLabel(workItem.bv.goldAccount[0].accountAddrProvince) }" disabled="disabled">&nbsp;
	                	<input type="text" class="cf_input_text178" value="${fns:getLabel(workItem.bv.goldAccount[0].accountAddrCity) }" disabled="disabled">&nbsp;
	                </td>    
					<td>
						<label class="lab"><span class="red">*</span>卡或折：</label>
						<input style="width: 100px" type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.goldAccount[0].accountCardOrBooklet,'com_card_type','') }" disabled="disabled">
					</td>
				</tr>
	            <tr>
					<td colspan="2">
						<label class="lab"><span class="red">*</span>具体支行：</label>
						<input type="text" class="cf_input_text178" style="width:300px;" value="${workItem.bv.goldAccount[0].accountBranch }" disabled="disabled">
					</td>
	                <td>
	                	<label class="lab"><span class="red">*</span>账号：</label>
	                	<input type="text" class="cf_input_text178_24" value="${workItem.bv.goldAccount[0].accountNo }" disabled="disabled">
	                </td>    
	            </tr>
	        </table>
	    </div>
    </c:if>
    
    <div class="title">
        <div class="l"><h2 class="f14 ml10">申请信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>申请日期：</label><input type="text" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.lendApply.applyDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" class="cf_input_text178" id="deductDate" value="<fmt:formatDate value="${workItem.bv.lendApply.deductDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" id="lendDate" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
            </tr>
			 <tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label>
                	<input type="text" class="cf_input_text178" value="${fns:getProductLabel(workItem.bv.lendApply.productCode) }" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label>
                	<input type="hidden" class="cf_input_text178" id="payType" value="${workItem.bv.lendApply.payType}">
                	<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.payType,'tz_pay_type','') }" disabled="disabled">
                </td>
			     <td><label class="lab"><span class="red">*</span>划扣平台：</label>
			     	<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.deductTypeId,'tz_deduct_plat','') }" disabled="disabled">
			     	<input type="hidden" class="cf_input_text178" id="deductTypeId" value="${workItem.bv.lendApply.deductTypeId}">
			     </td>
            </tr>
            <c:if test="${workItem.bv.lendApply.productCode eq '87' }">
            	<tr style=''>
            		<td colspan="3">
            			<label class="lab"><span class="red">*</span>信和宝类型：</label>
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" <c:if test="${workItem.bv.lendApply.xinhebaoType eq '1' }">checked=checked</c:if> disabled="disabled">满12个月转让一次收益
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" <c:if test="${workItem.bv.lendApply.xinhebaoType eq '2' }">checked=checked</c:if> disabled="disabled">满12个月不转让收益，24个月后一起转让本息
	                </td>
	            </tr>
	        </c:if>
			<tr>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" class="cf_input_text178"  id="lendMoney" value="<fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
				<td>
					<label class="lab"><span class="red">*</span>协议版本：</label>
					<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.protocoEdition,'tz_contract_vesion','-')}" disabled="disabled">
				</td>
                <td><label class="lab"><span class="red">*</span>托管状态：</label><input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.customer.applyHostedStatus,'tz_trust_state','')}" disabled="disabled"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>划扣金额：</label><input type="text" id="deductMoney" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.deductMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
                <td><label class="lab" colspan="3"><span class="red">*</span>合同编号：</label><input type="text" class="cf_input_text178" value="${workItem.bv.lendApply.contractNumber}" disabled="disabled"></td>
			    <td><label class="lab"><span class="red">*</span>内部转账金额：</label><input type="text" id="transferMoney" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.transferMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
            </tr>
            <tr>
                <td colspan="3"><label class="lab">备注：</label><textarea class="textarea_refuse" disabled="disabled">${workItem.bv.lendApply.remark}</textarea></td>
            </tr>
        </table>
        <c:if test="${workItem.bv.lendApply.payType eq '2' or workItem.bv.lendApply.payType eq '6' or workItem.bv.lendApply.payType eq '7'}">
	        <table class="table table-striped table-bordered table-condensed data-list-table">
	        	<thead>
	        		<tr>
	        			<th>序号</th>
	        			<th>出借姓名</th>
		        		<th>出借编号</th>
		        		<th>出借方式</th>
		        		<th>出借金额</th>
		        		<th>出借日期</th>
		        		<th>到期日期</th>
		        		<th>付款方式</th>
		        		<th>内转前实际回款金额</th>
		        		<th>本次内转金额</th>
		        		<th>内转后实际回款金额</th>
		        		<th>出借状态</th>
		        		<th>回款状态</th>
		        		<th>回款类型</th>
	        		<tr>
	        	</thead>
	        	<c:forEach items="${workItem.bv.transferLoanApplyList}" var="item" varStatus="vst">
	        		<tr>
						<td>${vst.index+1}</td>
						<td>${item.custName }</td>
						<td>${item.applyCode }</td>
						<td>
							${fns:getProductLabel(item.productCode) }
						</td>
						<td>${fns:getFormatNumber(item.lendMoney ,'￥#,##0.00')}</td>
						<td>
							<fmt:formatDate value="${item.lendDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<fmt:formatDate value="${item.expireDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${fns:getDictLabel(item.payType,'tz_pay_type','-') }
						</td>
						<td>
							${fns:getFormatNumber(item.actualBackMoney ,'￥#,##0.00')}
						</td>
						<td>
							${fns:getFormatNumber(item.transferMoney ,'￥#,##0.00')}
						</td>
						<td>
							${fns:getFormatNumber(item.actualBackMoney - item.transferMoney ,'￥#,##0.00')}
						</td>
						<td>
							${fns:getDictLabel(item.lendStatus,'tz_lend_state','-') }
						</td>
						<td>
							${fns:getDictLabel(item.backMoneyStatus,'tz_back_state','-') }
						</td>
						<td>
							${fns:getDictLabel(item.backMoneyType,'tz_back_type','-') }
						</td>
	        		<tr>
	        	</c:forEach>
	        </table>
        </c:if>
    </div>
    <form id="inputForm" method="post" action="${ctx}/lend/approve/dispatch">
    	<!-- <div class="title">
	        <div class="l"><h5 class="f14 ml10">附件</h5></div>
	    </div> -->
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
	                	<label class="lab"><span class="red">*</span>审批意见：</label>
	                	<textarea id="checkExamine" class="textarea_refuse" maxlength="2000" name="checkExamine">${workItem.bv.lendApplyLog.checkExamine }</textarea>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                	<label class="lab"><span class="red">*</span>审批结果：</label>
	                	<input type="radio" value="1" name="checkExaminetype" id="pass" onclick="_change(this);" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype==1 }">checked=checked</c:if>/>&nbsp;通过&nbsp;
	                	<input type="radio" value="2" name="checkExaminetype" onclick="_change(this);" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype==2 }">checked=checked</c:if>/>&nbsp;不通过</td>
	            </tr>
				<tr id="_t1" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype==1 or empty workItem.bv.lendApplyLog.checkExaminetype }">style="display:none;"</c:if>>
	                <td>
	                	<label class="lab"><span class="red">*</span>错误类型：</label>
	                	<select class="select78 mr34" name="errorTypeId">
	                		<c:forEach items="${fns:getDictList('tz_error_type') }" var="error">
									<c:if test="${error.value ==23 ||error.value ==24|| error.value ==25 ||error.value ==26 ||error.value ==27 ||error.value ==28}"><option value="${error.value }" <c:if test="${workItem.bv.lendApplyLog.errorTypeId==error.value }">selected=selected</c:if>>${error.label }</option></c:if>
							</c:forEach>
	                	</select>
	                </td>
	            </tr>
	        </table>
	    </div>
	    <input type="hidden" name="flowCode" value="${workItem.flowCode}" ></input>
    	<input type="hidden" name="flowName" value="${workItem.flowName}" ></input>
    	<input type="hidden" name="stepName" value="${workItem.stepName}" ></input>
    	<input type="hidden" name="flowId" value="${workItem.flowId}"></input>
    	<input type="hidden" name="wobNum" value="${workItem.wobNum}"></input>
        <input type="hidden" name="token" value="${workItem.token}"></input>
        <input type="hidden" name="applyId" value="${workItem.bv.applyId}"></input>
        <input type="hidden" name="customerCode" value="${workItem.bv.customer.custCode}"></input>
        <input type="hidden" name="lendCode" value="${workItem.bv.lendApply.applyCode}"></input>
        <input type="hidden" name="id" value="${workItem.bv.lendApply.id}"></input>
        <input type="hidden" id="lendDateHidden">
	    <div class="tright pr30 mb30">
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" value="保 存" />
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
    </form>
    </div>
</div>
</body>
</html>
