<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/lend/approve/lendApply_approval.js"></script>
	<title>出借明细</title>
</head>
<body>
<div class="">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">详细信息</h2></div>
    </div>
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
            <tr>
                <td><label class="lab"><span class="red">*</span>出借编号：</label>${workItem.bv.lendApply.applyCode}</td>
				<td><label class="lab"><span class="red">*</span>出借金额：</label><fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber></td>
                <td><label class="lab"><span class="red">*</span>出借日期：</label><fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
			<tr>
                <td><label class="lab"><span class="red">*</span>出借方式：</label>${fns:getProductLabel(workItem.bv.lendApply.productCode) }</td>
				<td><label class="lab"><span class="red">*</span>划扣平台：</label>${fns:getDictLabel(workItem.bv.lendApply.deductTypeId,'tz_deduct_plat','') }</td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label>${fns:getDictLabel(workItem.bv.lendApply.payType,'tz_pay_type','') }</td>  
            </tr>
			<tr>
                <td colspan="3"><label class="lab"><span class="red">*</span>营业部：</label>${fns:getOrgNameById(workItem.bv.lendApply.storeOrgId) }</td>
				
            </tr>
        </table>	
    </div>
    
    <c:if test="${workItem.bv.lendApply.payType!='4' }">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">付款<c:if test="${fn:length(workItem.bv.banks) == 1 }">(回款)</c:if>银行信息&nbsp;&nbsp;</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table table-striped table-bordered table-condensed" width="100%" id="bankList">
	            <thead>
	            <tr>
	                <th>开户行</th>
	                <th>开户行所在城市</th>
	                <th>卡或折</th>
	                <th>开户姓名</th>
	                <th>银行账户</th>
	           </tr>
	           </thead>
	           <tbody id="chooseBankAccount">
			           <c:if test="${fn:length(workItem.bv.banks)>0 }">
					           <tr>
					                <input type="hidden" name="paymentBankId" value="${workItem.bv.banks[0].id }" />
									<input type="hidden" name="receiveBankId" value="${workItem.bv.banks[0].id }" />
									<input type="hidden" name="bankProvince" value="${workItem.bv.banks[0].accountAddrProvince }" />
									<td>
										${fns:getDictLabel(workItem.bv.banks[0].accountBankId, 'tz_open_bank', '')} 
										${workItem.bv.banks[0].accountBranch}
									</td>
									<td>
										<c:forEach items="${workItem.bv.provinceList }" var="_province">
											<c:if test="${_province.id==workItem.bv.banks[0].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.cityList[status.index]  }" var="_city">
											<c:if test="${_province.id==workItem.bv.banks[0].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.districtList[status.index] }" var="_district">
											<c:if test="${_province.id==workItem.bv.banks[0].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
									</td>
									<td>
										${fns:getDictLabel(workItem.bv.banks[0].accountCardOrBooklet, 'com_card_type', '')}
										
									</td>
									<td>
										${workItem.bv.banks[0].accountName }
									</td>
									<td>
										<span class="bankNumDetail">${workItem.bv.banks[0].accountNo }</span>
									</td>
					           </tr>
			           </c:if>
			   </tbody>
	        </table>
	    </div>
	    
	    <c:if test="${fn:length(workItem.bv.banks) > 1 }">
	    	<div class="title">
		        <div class="l"><h2 class="f14 ml10">回款银行信息&nbsp;&nbsp;</h2></div>
		    </div>
		    <div class="box6">
		        <table class="table table-striped table-bordered table-condensed" width="100%" id="bankList">
	            <thead>
	            <tr>
	                <th>开户行</th>
	                <th>开户行所在城市</th>
	                <th>卡或折</th>
	                <th>开户姓名</th>
	                <th>银行账户</th>
	           </tr>
	           </thead>
	           <tbody id="chooseBankAccount">
			           <c:if test="${fn:length(workItem.bv.banks)>0 }">
					           <tr>
					                <input type="hidden" name="paymentBankId" value="${workItem.bv.banks[1].id }" />
									<input type="hidden" name="receiveBankId" value="${workItem.bv.banks[1].id }" />
									<input type="hidden" name="bankProvince" value="${workItem.bv.banks[1].accountAddrProvince }" />
									<td>
										${fns:getDictLabel(workItem.bv.banks[1].accountBankId, 'tz_open_bank', '')} 
										${workItem.bv.banks[1].accountBranch}
									</td>
									<td>
										<c:forEach items="${workItem.bv.provinceList }" var="_province">
											<c:if test="${_province.id==workItem.bv.banks[1].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.cityList[status.index]  }" var="_city">
											<c:if test="${_province.id==workItem.bv.banks[1].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.districtList[status.index] }" var="_district">
											<c:if test="${_province.id==workItem.bv.banks[1].accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
									</td>
									<td>
										${fns:getDictLabel(workItem.bv.banks[1].accountCardOrBooklet, 'com_card_type', '')}
										
									</td>
									<td>
										${workItem.bv.banks[1].accountName }
									</td>
									<td>
										<span class="bankNumDetail">${workItem.bv.banks[1].accountNo }</span>
									</td>
					           </tr>
			           </c:if>
			   </tbody>
	        </table>
		    </div>
	    </c:if>
    </c:if>
    
    <c:if test="${workItem.bv.lendApply.payType=='4' }">
    	<div class="title">
	        <div class="l"><h2 class="f14 ml10">金账户银行信息</h2></div>
	    </div>
	    <div class="box6">
			<table  class="table table-striped table-bordered table-condensed" id="bankList">
	         <thead> 
		            <tr>
		                <th>开户行</th>
		                <th>开户行所在城市</th>
		                <th>卡或折</th>
		                <th>开户姓名</th>
		                <th>银行账户</th>
		           </tr>
	           </thead>
	           <tbody id="goldBankAccountTable">
	           <c:if test="${workItem.bv.goldAccount != null  && fn:length(workItem.bv.goldAccount) > 0 }">
	           <c:forEach items="${workItem.bv.goldAccount }" var="item" varStatus="sta">
		           <tr>
	                	<input type="hidden" name="paymentBankId" value="${workItem.bv.goldAccount[0].id }" />
						<input type="hidden" name="receiveBankId" value="${workItem.bv.goldAccount[0].id }" />
						<input type="hidden" name="bankProvince" value="${workItem.bv.goldAccount[0].accountAddrProvince }" />
						<td>
							${fns:getDictLabel(workItem.bv.goldAccount[0].accountBankId, 'tz_open_bank', '')} ${workItem.bv.goldAccount[0].accountBranch}
						</td>
						<td>
							<c:forEach items="${workItem.bv.goldAccountProvinceList }" var="_province">
									<c:if test="${_province.areaCode==workItem.bv.goldAccount[0].accountAddrProvince}">${_province.areaName }</c:if>
							</c:forEach>
							<c:forEach items="${workItem.bv.goldAccountCityList[sta.index] }" var="_city">
									<c:if test="${_city.areaCode==workItem.bv.goldAccount[0].accountAddrCity}">${_city.areaName }</c:if>
							</c:forEach>
							
						</td>
						<td>
							${fns:getDictLabel(workItem.bv.goldAccount[0].accountCardOrBooklet, 'com_card_type', '')}
						</td>
						<td>
							${workItem.bv.goldAccount[0].accountName }
						</td>
						<td>
							${workItem.bv.goldAccount[0].accountNo }
						</td>
		           </tr>
		       </c:forEach>
		       </c:if>
		       </tbody>
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
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.lendApply.deductDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" class="cf_input_text178" value="<fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/>" disabled="disabled"></td>
            </tr>
			 <tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label>
                	<input type="text" class="cf_input_text178" value="${fns:getProductLabel(workItem.bv.lendApply.productCode) }" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label>
                	<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.payType,'tz_pay_type','') }" disabled="disabled">
                </td>
			     <td><label class="lab"><span class="red">*</span>划扣平台：</label>
			     	<input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.deductTypeId,'tz_deduct_plat','') }" disabled="disabled">
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
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
				<td><label class="lab"><span class="red">*</span>协议版本：</label><input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.lendApply.protocoEdition,'tz_contract_vesion','-')}" disabled="disabled"></td>
                <td><label class="lab"><span class="red">*</span>托管状态：</label><input type="text" class="cf_input_text178" value="${fns:getDictLabel(workItem.bv.customer.applyHostedStatus,'tz_trust_state','')}" disabled="disabled"></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>划扣金额：</label><input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.deductMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
                <td><label class="lab" colspan="3"><span class="red">*</span>合同编号：</label><input type="text" class="cf_input_text178" value="${workItem.bv.lendApply.contractNumber}" disabled="disabled"></td>
			    <td><label class="lab"><span class="red">*</span>内部转账金额：</label><input type="text" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.transferMoney}" pattern="#,##0.00"></fmt:formatNumber>" disabled="disabled"></td>
            </tr>
            <tr>
                <td colspan="3"><label class="lab">备注：</label><textarea class="textarea_refuse" disabled="disabled">${workItem.bv.lendApply.remark}</textarea></td>
            </tr>
        </table>
        <c:if test="${workItem.bv.lendApply.payType eq '2' or workItem.bv.lendApply.payType eq '6'}">
	        <table class="table table-striped table-bordered table-condensed data-list-table">
	        	<thead>
	        		<tr>
	        			<th>序号</th>
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
	        		<tr>
	        	</thead>
	        	<c:forEach items="${workItem.bv.transferLoanApplyList}" var="item" varStatus="vst">
	        		<tr>
						<td>${vst.index+1}</td>
						<td>${item.applyCode }</td>
						<td>
							${fns:getProductLabel(item.productCode) }
						</td>
						<td>${item.lendMoney }</td>
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
							${item.transferMoney + item.backMoney}
						</td>
						<td>
							${item.transferMoney }
						</td>
						<td>
							${item.backMoney }
						</td>
						<td>
							${fns:getDictLabel(item.lendStatus,'tz_lend_state','-') }
						</td>
						<td>
							${fns:getDictLabel(item.backMoneyStatus,'tz_back_state','-') }
						</td>
	        		<tr>
	        	</c:forEach>
	        </table>
        </c:if>
    </div>
    <div class="title">
	        <div class="l"><h5 class="f14 ml10">附件</h5></div>
	 </div>
		<table class="table1 lendApply_detail" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
    <c:if test="${workItem.bv.lendApplyLog != null }">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td><label class="lab"><span class="red">*</span>审批意见：</label><textarea class="textarea_refuse" name="checkExamine" disabled="disabled">${workItem.bv.lendApplyLog.checkExamine }</textarea></td>
	            </tr>
	            <tr>
	                <td><label class="lab"><span class="red">*</span>审批结果：</label>
	                <input type="radio" name="checkExaminetype" value="1" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype=='1' }">checked=checked</c:if> disabled="disabled"/>&nbsp;通过&nbsp;
	                <input type="radio" name="checkExaminetype" value="2" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype=='2' }">checked=checked</c:if> disabled="disabled"/>&nbsp;不通过</td>
	            </tr>
				<c:if test="${workItem.bv.lendApplyLog.checkExaminetype==2 }">
	            	<tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>错误类型：</label>
		                	<input type="text" name="errorTypeId" class="cf_input_text178" style="width: 200px;" value="${fns:getDictLabel(workItem.bv.lendApplyLog.errorTypeId,'tz_error_type',workItem.bv.lendApplyLog.errorTypeId) }" disabled="disabled">
		                </td>
		            </tr>
	            </c:if>
	        </table>
	    </div>
	    <input type="hidden" name="flowCode" value="${workItem.flowCode}" ></input>
    	<input type="hidden" name="flowName" value="${workItem.flowName}" ></input>
    	<input type="hidden" name="stepName" value="${workItem.stepName}" ></input>
    	<input type="hidden" name="flowId" value="${workItem.flowId}"></input>
    	<input type="hidden" name="wobNum" value="${workItem.wobNum}"></input>
        <input type="hidden" name="token" value="${workItem.token}"></input>
        <input type="hidden" name="applyId" value="${lendApplyLog.applyId}"></input>
        <input type="hidden" name="customerCode" value="${customer.custCode}"></input>
        
    </c:if>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">第五条    甲方回款风险的处置方式</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                	<div>
                		<h3>5.1当借款人发生违约时，甲方选择如下之一的方式处置回款风险：</h3>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" disabled <c:if test="${workItem.bv.lendApply.check1=='1' }">checked=checked</c:if> value="1" >甲方自行追讨，丙方提供协助，由甲方自行承担损失和风险，同时自行享有借款人违约所支付的罚息、违约金等。</p>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" disabled <c:if test="${workItem.bv.lendApply.check1=='2' }">checked=checked</c:if> value="2" >由丙方还款保障金专用账户的款项分担借款人回款风险，规则如下：</p>
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丙方根据借款人的整体违约状况设定还款保障金的提取比例，并有权进行适当的调整；</p>
						<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丙方以季度为周期披露还款保障金专用账户的整体信息情况；如需要，甲方应协助丙方进行由于借款人违约而产生的相关法律诉讼行为。</p>
                	</div>
                </td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">第九条    甲方的资金出借方式</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                	<div>
                		<h3>9.1甲方可以选择以下任意一种方式，实现出借需求：</h3>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" disabled <c:if test="${workItem.bv.lendApply.check2=='1' }">checked=checked</c:if> value="1" >对丙方推荐的借款人如果决定出借，须通过当面签署或授权签署等方式直接与借款人签署《借款协议》，甲方有义务按照《借款协议》的约定及时付款给借款人。</p>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" disabled <c:if test="${workItem.bv.lendApply.check2=='2' }">checked=checked</c:if> value="2" >对丙方服务中的《借款协议》下的债权债务关系进行受让，将购买债权的款项支付给债权转让方，从而完成资金的出借。</p>
                	</div>
                </td>
            </tr>
        </table>
    </div>
    <input type="hidden" id="theJspName" value="theJspName"></input>
    <input type="hidden" name="id" value="${workItem.bv.lendApply.id}"></input>
    <input type="hidden" name="lendApply.applyCode" value="${workItem.bv.lendApply.applyCode}"></input>
	    <div class="tright pr30">
			<input id="btnCancel" class="btn cf_btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
    
</div>
</body>
</html>
