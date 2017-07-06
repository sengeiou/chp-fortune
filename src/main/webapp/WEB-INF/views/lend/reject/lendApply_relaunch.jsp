<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lend/apply/lendApplyAid.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lend/reject/lendApplyBank4Reject.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/common/autocomplete.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/lend/reject/lendApply_relaunch.js" charset="utf-8"></script>
</head>
<body>
<form id="inputForm" action="${ctx}/lend/reject/reLaunchFlow" method="post" class="lendApply_relaunch">
	<div class="">
		 <div class="title">
	        <div class="l"><h4 class="f14 ml10">客户信息</h4></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	           <tr>
	                <td>
	                	<label class="lab"><span class="red">*</span>客户姓名：</label>
	                	<!-- 屏蔽客户姓名/手机号/身份证号 -->
	                	<input type="text" readonly="readonly" name="customer.custName" value="***" class="cf_input_text178"></td>
	                	<%-- <input type="text" readonly="readonly" name="customer.custName" value="${workItem.bv.customer.custName}" class="cf_input_text178"></td> --%>
	                <td>
	                	<label class="lab"><span class="red">*</span>客户编号：</label>
	                	<input type="text" readonly="readonly" id="customerCode" name="customer.custCode" value="${workItem.bv.customer.custCode}" class="cf_input_text178">
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>证件号码：</label>
	                	<input type="text" readonly="readonly" name="customer.custCertNum" value="***" class="cf_input_text178">
	                	<%-- <input type="text" readonly="readonly" name="customer.custCertNum" value="${workItem.bv.customer.custCertNum}" class="cf_input_text178"> --%>
	                </td>
	            </tr>
	        </table>
	    </div>
	    
	    <div class="title bankZone" style="display:<c:if test="${workItem.bv.lendApply.payType == '4' }">none;</c:if>">
	        <div class="l"><h2 class="f14 ml10">银行信息<input type="button" class="btn btn_sc ml10" id="openSelectBankDialog" value="选择银行"></h2></div>
	    </div>
	    <div class="box6 bankZone" style="display:<c:if test="${workItem.bv.lendApply.payType == '4' }">none;</c:if>">
	        <table class="table table-striped table-bordered table-condensed" width="100%" id="bankList">
	            <tr>
	                <th>开户行</th>
	                <th>开户行所在城市</th>
	                <th>卡或折</th>
	                <th>开户姓名</th>
	                <th>银行账户</th>
	           </tr>
	           <tbody id="chooseBankAccount">
			           <c:if test="${fn:length(workItem.bv.banks)>0 }">
				           <c:forEach items="${workItem.bv.banks }" var="item" varStatus="status">
					           <tr>
					                <input type="hidden" name="paymentBankId" value="${item.id }" />
									<input type="hidden" name="receiveBankId" value="${item.id }" />
									<input type="hidden" name="bankProvince" value="${item.accountAddrProvince }" />
									<input type="hidden" name="bankNameId" value="${item.accountBankId }" />
									<td>
										${fns:getDictLabel(item.accountBankId, 'tz_open_bank', '')} 
										${item.accountBranch}
									</td>
									<td>
										<c:forEach items="${workItem.bv.provinceList }" var="_province">
											<c:if test="${_province.id==item.accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.cityList[status.index]  }" var="_city">
											<c:if test="${_province.id==item.accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
										<c:forEach items="${workItem.bv.districtList[status.index] }" var="_district">
											<c:if test="${_province.id==item.accountAddrProvince}">${_province.areaName }</c:if>
										</c:forEach>
									</td>
									<td>
										${fns:getDictLabel(item.accountCardOrBooklet, 'com_card_type', '')}
										
									</td>
									<td>
										${item.accountName }
									</td>
									<td>
										${item.accountNo }
									</td>
					           </tr>
					       </c:forEach>
			           </c:if>
			   </tbody>
	        </table>
	    </div>
	    
	    <div class="title goldAccount" style="display:<c:if test="${workItem.bv.lendApply.payType != '4' }">none;</c:if>">
	        <div class="l">
	        	<h2 class="f14 ml10">金账户银行信息 
		        	<c:if test="${workItem.bv.customer.applyHostedStatus !=2 and workItem.bv.customer.applyHostedStatus !=1 and workItem.bv.customer.applyHostedStatus !=4 }">
		        		<input type="button" class="btn btn_sc ml10" id="openGoldBankManageDialog" value="金账户管理">
		        	</c:if>
	        	</h2>
	        </div>
	    </div>
	    <div class="box6 goldAccount" style="display:<c:if test="${workItem.bv.lendApply.payType != '4' }">none;</c:if>">
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
	           <c:forEach items="${workItem.bv.goldAccount }" var="item" varStatus="sta">
		           <tr>
	                	<input type="hidden" name="paymentBankId" value="${item.id }" />
						<input type="hidden" name="receiveBankId" value="${item.id }" />
						<input type="hidden" class="goldAccountBankId" value="${item.accountBankId}" />
						<input type="hidden" name="bankProvince" value="${item.accountAddrProvince }" />
						<td>
							${fns:getDictLabel(item.accountBankId, 'tz_open_bank', '')} ${item.accountBranch}
						</td>
						<td>
							<c:forEach items="${workItem.bv.goldAccountProvinceList }" var="_province">
									<c:if test="${_province.areaCode==item.accountAddrProvince}">${_province.areaName }</c:if>
							</c:forEach>
							<c:forEach items="${workItem.bv.goldAccountCityList[sta.index] }" var="_city">
									<c:if test="${_city.areaCode==item.accountAddrCity}">${_city.areaName }</c:if>
							</c:forEach>
							
						</td>
						<td>
							${fns:getDictLabel(item.accountCardOrBooklet, 'com_card_type', '')}
						</td>
						<td>
							${item.accountName }
						</td>
						<td>
							${item.accountNo }
						</td>
		           </tr>
		       </c:forEach>
		       </tbody>
	        </table>
	    </div>
	    
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
	                	<label class="lab"><span class="red">*</span>申请日期：</label>
	                	<input type="text" name="lendApply.applyDate" id="applyDate" value="<fmt:formatDate value="${workItem.bv.lendApply.applyDate}" pattern="yyyy-MM-dd"/>" class="cf_input_text178 Wdate" onfocus="WdatePicker()" required>
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>计划划扣日期：</label>
	                	<input type="text" id="deductDate" name="lendApply.deductDate" value="<fmt:formatDate value="${workItem.bv.lendApply.deductDate}" pattern="yyyy-MM-dd"/>" class="cf_input_text178 Wdate" onfocus="WdatePicker({minDate:'%y-%M-{%d}',onpicked:onpickedFun})" required>
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>计划出借日期：</label>
	                	<input type="text" id="lendDate" name="lendApply.lendDate" value="<fmt:formatDate value="${workItem.bv.lendApply.lendDate}" pattern="yyyy-MM-dd"/>" class="cf_input_text178 Wdate" onfocus="WdatePicker({minDate:'%y-%M-{%d}'})" from-checkDate="#deductDate" required>
	                </td>
	            </tr>
	            <tr>
	                <td><label class="lab"><span class="red">*</span>出借模式：</label>
		                <select id="lend_product" class="select78 selectpicker bla bla bli" name="lendApply.productCode" data-actions-box="true"  select_required='1'>
							<option value="">请选择</option>
							<c:forEach items="${fns:productOption()}" var="item">
								<c:if test="${fn:contains(workItem.bv.enableProduct, item.productCode) }">
									<option value="${item.productCode }" 
											<c:if test="${fns:multiOption(workItem.bv.lendApply.productCode,item.productCode)}">selected</c:if>>${item.productName }</option>
								</c:if>
							</c:forEach>
						</select>
	                </td>
	                
	                <td><label class="lab"><span class="red">*</span>付款方式：</label>
		                <select id="payType" name="lendApply.payType" class="select78" select_required='1'>
							<c:forEach items="${fns:getDictList('tz_pay_type') }" var="item">
								<c:if test="${item.value == '1' or item.value == '2' or item.value == '4' or item.value == '6' or item.value == '7' }">
									<option value="${item.value }" <c:if test="${workItem.bv.lendApply.payType==item.value }">selected=selected</c:if>>${item.label }</option>
								</c:if>
							</c:forEach>
						</select>
						<input type="button" id="selectLend" value="选择出借" <c:if test="${workItem.bv.lendApply.payType!='2' and workItem.bv.lendApply.payType!='6'}">style="display: none;"</c:if>>
						<input type="button" id="selectLend4ZZ" value="选择出借" <c:if test="${workItem.bv.lendApply.payType!='7'}">style="display: none;"</c:if>>
					</td>
	                <td><label class="lab"><span class="red">*</span>划扣平台：</label>
	                	<select name="lendApply.deductTypeId" class="select78" select_required='1'>
	                		<option value="">请选择</option>
							<c:forEach items="${fns:getDictList('tz_deduct_plat') }" var="item">
								<option value="${item.value }" <c:if test="${workItem.bv.lendApply.deductTypeId==item.value }">selected=selected</c:if>>${item.label }</option>
							</c:forEach>
						</select>
	                </td>
	            </tr>
	            <tr style='<c:if test="${workItem.bv.lendApply.productCode != '87' }">display: none;</c:if>'>
            		<td colspan="3">
            			<label class="lab"><span class="red">*</span>信和宝类型：</label>
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" value="1" <c:if test="${workItem.bv.lendApply.xinhebaoType eq '1' }">checked=checked</c:if>>满12个月转让一次收益
            			<input type="radio" id='xinhebaoType' name="lendApply.xinhebaoType" value="2" <c:if test="${workItem.bv.lendApply.xinhebaoType eq '2' }">checked=checked</c:if>>满12个月不转让收益，24个月后一起转让本息
	                </td>
	            </tr>
	            <tr>
	                <td>
	                	<label class="lab"><span class="red">*</span>计划出借金额：</label>
	                	<input type="text" id="lendMoney" name="lendApply.lendMoney" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.lendMoney}" pattern="#,##0.00"></fmt:formatNumber>" divisible='10000' min="0" between="50000-9999999999" isNumber="1" <c:if test="${workItem.bv.lendApply.payType=='2'}">readonly="readonly"</c:if> required>
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>划扣金额：</label>
	                	<input type="text" id="deductMoney" name="lendApply.deductMoney" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.deductMoney}" pattern="#,##0.00"></fmt:formatNumber>" min="0" isNumber="1" readonly="readonly" required>
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>内部转账金额：</label>
	                	<input type="text" id="transferMoney" name="lendApply.transferMoney" class="cf_input_text178" value="<fmt:formatNumber value="${workItem.bv.lendApply.transferMoney}" pattern="#,##0.00"></fmt:formatNumber>" divisible='10000' min="0" isNumber="1" readonly="readonly" >
	                </td>
	            </tr>
	            <tr>
	                <%-- <td>   
	                	<label class="lab"><span class="red">*</span>协议版本：</label>
	                	<select id="ContractVesion" class="select78" select_required='1' disabled="disabled">
	                		<option value="">请选择</option>
	                		<c:forEach items="${workItem.bv.protocolList}" var="item">
								<option value="${item }" <c:if test="${item==workItem.bv.lendApply.protocoEdition }">selected=selected</c:if>>${item }</option>
							</c:forEach>
							<!-- 出借申请时，可用的合同版本为：1.6.1/1.6/2.3/1.5 -->
							<c:forEach items="${fns:getDictList('tz_contract_vesion') }" var="item">
								<c:if test="${fns:multiOption(workItem.bv.protocals, item.value) }">
									<option value="${item.value }" <c:if test="${item.value==workItem.bv.lendApply.protocoEdition }">selected=selected</c:if>>${item.label }</option>
								</c:if>
							</c:forEach>
						</select>
	                </td> --%>
	                <td>
	                	<label class="lab"><span class="red">*</span>合同编号：</label>
	                	<input id="ContractCode" type="text" name="lendApply.contractNumber" class="cf_input_text178" value="${workItem.bv.lendApply.contractNumber}" maxlength="20" required readonly="readonly">
	                    <input type="button" id="generateNo" class="grey" value="生成" disabled>
	                	<input type="button" id="generateContractNo" class="red" value="生成" hidden>
	                </td>
	                <td>
	                	<label class="lab"><span class="red">*</span>协议版本：</label>
	                	<input id="protocoEdition" type="text" name="lendApply.protocoEdition1" class="cf_input_text178" 
		                	<c:forEach items="${fns:getDictList('tz_contract_vesion') }" var="item">
									<c:if test="${item.value == workItem.bv.lendApply.protocoEdition}">
										value="${item.label }" 
									</c:if>
							</c:forEach>
		                	maxlength="20" required readonly="readonly">
	                	
					</td>
					<td>
	                	<label class="lab"><span class="red"></span>托管状态：</label>
	                	<input type="text" id="trusteeshipStatus" name="customer.applyHostedStatus" class="cf_input_text178" trusteeshipStatus="${workItem.bv.customer.applyHostedStatus }" value="${fns:getDictLabel(workItem.bv.customer.applyHostedStatus,'tz_trust_state','') }" maxlength="20" disabled="disabled">
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan="3">
	                	<label class="lab"><span class="red"></span>备注：</label>
	                	<textarea rows="3" cols="70" name="lendApply.remark" class="textarea_refuse" maxlength="500">${workItem.bv.lendApply.remark }</textarea>
	                </td>
	            </tr>
	        </table>
	        <c:if test="${workItem.bv.lendApply.payType eq '2' or workItem.bv.lendApply.payType eq '6' or workItem.bv.lendApply.payType eq '7'}">
		        <table class="table table-striped table-bordered table-condensed data-list-table" id="selectedLendTable">
		        	<thead>
		        		<tr>
		        			<th>操作</th>
		        			<th>出借姓名</th>
			        		<th>出借编号</th>
			        		<th>出借方式</th>
			        		<th>出借金额</th>
			        		<th>出借日期</th>
			        		<th>到期日期</th>
			        		<th>付款方式</th>
			        		<th>内转前实际回款金额</th>
			        		<th>可内转金额</th>
			        		<th>本次内转金额</th>
			        		<th>内转后实际回款金额</th>
			        		<th>出借状态</th>
			        		<th>回款状态</th>
			        		<th>回款类型</th>
		        		<tr>
		        	</thead>
		        	<c:forEach items="${workItem.bv.transferLoanApplyList}" var="item" varStatus="vst">
		        		<tr>
							<td><a href="#">移除</a></td>
							<td>${item.custName }</td>
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
								<fmt:formatNumber value="${item.actualBackMoney }" pattern="#,##0.00"></fmt:formatNumber>
							</td>
							<td><fmt:formatNumber value="${item.validateMoney }" pattern="#,##0.00"></fmt:formatNumber></td>
							<td>
								<input type="text" value='<fmt:formatNumber value="${item.transferMoney }" pattern="#0.00"></fmt:formatNumber>' onkeyup="javascript:this.value=this.value.replace(/[^\d]|^0*/g,'');if(this.value==''){this.value='0'}">
							</td>
							<td>
								<fmt:formatNumber value="${item.actualBackMoney - item.transferMoney }" pattern="#,##0.00"></fmt:formatNumber>
							</td>
							<td>
								${fns:getDictLabel(item.lendStatus,'tz_lend_state','-') }
							</td>
							<td>
								${fns:getDictLabel(item.backMoneyStatus,'tz_back_state','-') }
							</td>
							<td backType='${item.backMoneyType}'>
								${fns:getDictLabel(item.backMoneyType,'tz_back_type','-') }
							</td>
		        		<tr>
		        	</c:forEach>
		        </table>
	        </c:if>
	    </div>
	    <div class="title">
	        <div class="l"><h5 class="f14 ml10">附件</h5></div>
	    </div>
		<table id="uploadFileZone" class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
	    <div id="scan" style="display:none;">
	    	<iframe name="scaniframe" width="99%" height=930 frameborder=0 scrolling=auto src="http://211.157.161.202:9099/SunIAS/SunIASRequestServlet.do?UID=admin&PWD=111&AppID=AB&UserID=zhangsan&UserName=zhangsan&OrgID=1&OrgName=fenbu&info1=BUSI_SERIAL_NO:400000317152215;OBJECT_NAME:HUIJINSY1;QUERY_TIME:20150611;FILELEVEL:1,3;RIGHT:1111111">空间环境</iframe>
	    </div>
	</div> 
	<div class="title">
        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>审批意见：</label><textarea class="textarea_refuse" name="checkExamine" disabled="disabled">${workItem.bv.lendApplyLog.checkExamine }</textarea></td>
            </tr>
            <tr>
                <td>
                <label class="lab"><span class="red">*</span>审批结果：</label>
                <input type="radio" name="checkExaminetype" value="1" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype==1 }">checked=checked</c:if> disabled="disabled"/>&nbsp;通过&nbsp;
                <input type="radio" name="checkExaminetype" value="2" <c:if test="${workItem.bv.lendApplyLog.checkExaminetype==2 }">checked=checked</c:if> disabled="disabled"/>&nbsp;不通过
                </td>
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
    <div >
    	<div class="title">
	        <div class="l"><h2 class="f14 ml10">第五条    甲方回款风险的处置方式</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	           <tr>
	                <td>
	                	<div>
	                		<h3>5.1当借款人发生违约时，甲方选择如下之一的方式处置回款风险：</h3>
	                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lendApply.check1" <c:if test="${workItem.bv.lendApply.check1=='1' }">checked=checked</c:if> value="1" >甲方自行追讨，丙方提供协助，由甲方自行承担损失和风险，同时自行享有借款人违约所支付的罚息、违约金等。</p>
	                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lendApply.check1" <c:if test="${workItem.bv.lendApply.check1=='2' }">checked=checked</c:if> value="2" >由丙方还款保障金专用账户的款项分担借款人回款风险，规则如下：</p>
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
	                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lendApply.check2" <c:if test="${workItem.bv.lendApply.check2=='1' }">checked=checked</c:if>  value="1" >对丙方推荐的借款人如果决定出借，须通过当面签署或授权签署等方式直接与借款人签署《借款协议》，甲方有义务按照《借款协议》的约定及时付款给借款人。</p>
	                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lendApply.check2" <c:if test="${workItem.bv.lendApply.check2=='2' }">checked=checked</c:if> value="2" >对丙方服务中的《借款协议》下的债权债务关系进行受让，将购买债权的款项支付给债权转让方，从而完成资金的出借。</p>
	                	</div>
	                </td>
	            </tr>
	        </table>
	    </div>
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10"><input type="checkbox" checked="true"  class="ido"  id="readContract">您已经阅读本协议所有条款，同意在必要时由丙方单方加盖作废章，充分了解并清楚知晓相应权利义务，愿意承担相关风险。</h2></div>
	    </div>
    </div>
    <div class="tright pr30">
			<input id="reLaunchFlow" class="btn cf_btn-primary mb30" type="button" value="提交"/>
			<c:if test="${workItem.bv.canceled eq 'true' }">
				<input id="cancelFlow" class="btn cf_btn-primary mb30" type="button" value="撤销"/>
			</c:if>
			<input id="btnCancel" class="btn cf_btn-primary mb30" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	
	<div id="divHideen">
		<input type="hidden" id="originalContractCode" value="${workItem.bv.lendApply.contractNumber}" disabled="disabled">
        <input type="hidden" name="flowCode" value="${workItem.flowCode}" ></input>
    	<input type="hidden" name="flowName" value="${workItem.flowName}" ></input>
    	<input type="hidden" name="flowType" value="${workItem.flowType}" ></input>
    	<input type="hidden" name="stepName" value="${workItem.stepName}" ></input>
    	<input type="hidden" name="wobNum" value="${workItem.wobNum}"></input>
        <input type="hidden" name="token" value="${workItem.token}"></input>
    	<input type="hidden" name="applyId" value="${workItem.bv.lendApplyLog.applyId}"></input>
    	<input type="hidden" name="id" value="${workItem.bv.lendApply.id}"></input>
        <input type="hidden" id="applyCode" name="lendApply.applyCode" value="${workItem.bv.lendApply.applyCode}"></input>
        <input type="hidden" id='transferLendId' name="lendApply.transferLendId" value="" />
        <input type="hidden" id="pageType" value="lendApply_relaunch" />
        <input type="hidden" id="ContractVesion" name="lendApply.protocoEdition"  value="${workItem.bv.lendApply.protocoEdition}" />
        <input type="hidden" name="lendApply.payType"  value="${workItem.bv.lendApply.payType }" />
		<input type="hidden" id="bankProvinceHidden"/>
		<input type="hidden" id="bankNameIdHidden"/>
	</div>	
</form>
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
				<input type="button" value="选择带回" class="btn cf_btn-primary" id="subClose"/>
				<input id="chooseBankConfirm" class="btn cf_btn-primary" type="button" value="确定" style="display:none;" />
				<input id="chooseBankCancel" class="btn cf_btn-primary" type="button" value="取消"  style="display:none;" />
			</div>
		</div>
	</div>
</div>

<!-- Start 用于添加银行账户，模板、隐藏  -->
<table style="display:none;">
	<tr style="display: none;" id="template">
                <input type="hidden" name="paymentBankId" value="" />
				<input type="hidden" name="receiveBankId" value="" />
				<input type="hidden"  name="bankAccountName" value="${workItem.bv.customer.custName }" />
				<td>
                	<input type="radio" name="selectBanckId" value="" />
                </td>
				<td>
					<select style="width: 100px;" name="bankNameId" id="bankNameId">
						<c:forEach items="${fns:getDictList('tz_open_bank') }" var="bank">
							<option value="${bank.value }">${bank.label }</option>
						</c:forEach>
					</select>
					<input type="text" name="branchAddress" checkbranch="1"  maxlength="100" style="width: 120px;" onblur="checkbranch(this)">
				</td>
				<td>
					<select style="width: 100px;" name="bankProvince" id="bankProvinceSelect">
						<option value="">请选择</option>
						<c:forEach items="${workItem.bv.provinceList }" var="item">
						   <option value="${item.id}">${item.areaName }</option>
						</c:forEach>
					</select>
					<select style="width: 100px;" name="bankCity" id="bankCitySelect" class="city-select">
						<option value="">请选择</option>
					</select>
					<select style="width: 100px;" name="bankDistrict" id="bankDistrictSelect">
						<option value="">请选择</option>
					</select>
				</td>
				<td>
					<select style="width: 85px;" name="bankCard_passbook">
						<c:forEach items="${fns:getDictList('com_card_type') }" var="bank">
							<option value="${bank.value }">${bank.label }</option>
						</c:forEach>
					</select>
				</td>
				<td><input type="text" style="width: 120px;" name="bankAccountNum" maxlength="30"/></td>
				<td>
					<input type="button" value="修改"  class="cf_btn_edit" name="modifyBank" />
					<input type="button" value="删除"  class="cf_btn_edit" name="deleteBank"/>
				</td>
           </tr>
</table>
<!-- END  用于添加银行账户，模板、隐藏  -->
</body>
</html>