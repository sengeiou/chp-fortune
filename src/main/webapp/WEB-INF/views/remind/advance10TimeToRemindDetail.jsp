<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
</head>
<body>
<div class="body_r">
     <div class="title ">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
     </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td>
                	<label class="lab">客户姓名：</label>
                	<input type="text"  value="***" class="cf_input_text178" disabled="disabled" />
                	<%-- <input type="text"  value="${smsCfSendListExt.customerName }" class="cf_input_text178" disabled="disabled" /> --%>
                </td>
                <td>
                	<label class="lab">客户编号：</label>
                	<input type="text"  value="${smsCfSendListExt.customerCode }" class="cf_input_text178" disabled="disabled" />
                </td>
                <td>
                	<label class="lab">证件号码：</label>
                	<input type="text"  value="***" class="cf_input_text178" disabled="disabled"/>
                	<%-- <input type="text"  value="${fns:valueDesensitize(smsCfSendListExt.managerCode,smsCfSendListExt.custCertNum)}" class="cf_input_text178" disabled="disabled"/> --%>
                </td> 
            </tr>
          
        </table>
    </div>
      <div class="title">
        <div class="l"><h2 class="f14 ml10">付款银行信息（付款与回款银行信息一致）</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">开户行：</label>
	                <select id="accountBank" name="smsCfSendListExt.accountBank" class="input-medium" disabled="disabled">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_open_bank}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${d.value eq smsCfSendListExt.accountBank}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
					</select>
                </td>
                <td> 
                	<label class="lab">银行卡所在城市：</label>
					     <input type="text"  value="${fns:getProvinceLabel(smsCfSendListExt.accountAddrprovince)}" class="cf_input_text178" disabled="disabled" />
					     
					     <input type="text"  value="${fns:getCityLabel(smsCfSendListExt.accountAddrcity)}" class="cf_input_text178" disabled="disabled" />

					     <input type="text"  value="${fns:getDistrictLabel(smsCfSendListExt.accountAddrdistrict)}" class="cf_input_text178" disabled="disabled" />
				
                </td>
                <td>
                	<label class="lab">卡或折：</label>
					<input type="text"  value="${fns:dictName(dicts.com_card_type,smsCfSendListExt.accountCardOrBooklet,'')}" class="cf_input_text178" disabled="disabled">
                </td>
            </tr>
             <tr>
                <td>
                	<label class="lab">具体支行：</label>
                	<input type="text"  value="${smsCfSendListExt.accountBranch}" class="cf_input_text178" disabled="disabled" />
                </td>
                <td>
                	<label class="lab">开户名称：</label>
                	<input type="text"  value="${smsCfSendListExt.accountName}" class="cf_input_text178" disabled="disabled" />
                </td>
                <td>
                	<label class="lab">银行账户：</label>
                	<input type="text"  value="${smsCfSendListExt.accountNo}" class="cf_input_text178" disabled="disabled" />
                </td>
            </tr>
        </table>
    </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">出借申请日期：</label>
                	<input type="text"  value="${fns:getFormatDate(smsCfSendListExt.applyDate,'yyyy-MM-dd')}" class="cf_input_text178" disabled="disabled" />
                </td>
                <td>
                	<label class="lab">出借划扣日期：</label>
                	<input type="text"  value="${fns:getFormatDate(smsCfSendListExt.applyDuctDate,'yyyy-MM-dd')}" class="cf_input_text178" disabled="disabled"/>
                </td>
                <td>
                	<label class="lab">计划出借日期：</label>
                	<input type="text"  value="${fns:getFormatDate(smsCfSendListExt.applyLendDate,'yyyy-MM-dd')}" class="cf_input_text178" disabled="disabled" />
                </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">出借模式：</label>
					<input name="productCode" type='text' value="${smsCfSendListExt.productCode}" class="cf_input_text178" disabled="disabled">
                </td>
                <td>
                	<label class="lab">协议版本号：</label>
					<input type="text"  value="${fns:dictName(dicts.tz_protocol_version,smsCfSendListExt.applyAgreementEition,'')}" class="cf_input_text178" disabled="disabled">
                </td>
                <td>
                	<label class="lab">销售折扣率（%）：</label>
                	<input type="text"  value="${smsCfSendListExt.applySalesDiscount}" class="cf_input_text178" disabled="disabled" />
                </td>
            </tr>
            <c:if test="${remindFlag eq 'das'}">
	            <tr>
	                <td colspan="3">
	                	<label class="lab">信和宝类型：</label>
	                	<input type="radio" name="name"   <c:if test="${smsCfSendListExt.xinhebao_type==1}">checked="checked"</c:if> disabled="disabled"/>满12个月转让一个收益　　
	                	<input type="radio" name="name"   <c:if test="${smsCfSendListExt.xinhebao_type==2}">checked="checked"</c:if> disabled="disabled"/>满12个月不转让一个收益，24个月后一起转让本息
	                </td>
	            </tr>
            </c:if>
            <tr>
                <td>
                	<label class="lab">合同编号：</label>
                	<input type="text"  value="${smsCfSendListExt.applyContractNo}" class="cf_input_text178" disabled="disabled"/>
                </td>
                <td>
	                <label class="lab">划扣平台：</label>
	                <input type="text"  value="${fns:dictName(dicts.tz_deduct_plat,smsCfSendListExt.dictApplyDeductType,'')}" class="cf_input_text178" disabled="disabled">
                </td>
            </tr>
             <tr>
                <td>
                	<label class="lab">备注：</label>
                	<textarea class='textarea_refuse' disabled="disabled">${smsCfSendListExt.applyRemarks}</textarea>
                </td>
            </tr>
             
        </table>
    </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">选择出借信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>付款方式：
                	</label>
                    <select id="payType" name="smsCfSendListExt.payType" class="input-medium" disabled="disabled">
                		<option value="">请选择</option>
                		<c:forEach items="${dicts.tz_pay_type}" var = "di">
                			<option value="${di.value }"
                				<c:if test="${di.value eq smsCfSendListExt.payType }">
									selected
								</c:if>
                			>${di.label}
                			</option>
                		</c:forEach>
                	</select>
                </td>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>计划出借金额：
                	</label>
                	<input type="text"  value="${smsCfSendListExt.applyLendMoney}" class="cf_input_text178" disabled="disabled">
                </td>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>划扣金额：
                	</label>
                	<input type="text"  value="${smsCfSendListExt.applyDeductMoney}" class="cf_input_text178" disabled="disabled">
                </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>内部转账总金额：
                	</label>
                	<input type="text"  value="${smsCfSendListExt.applyTransferMoney}" class="cf_input_text178" disabled="disabled">
                </td>
            </tr>
        </table>
    </div>
    <c:if test="${remindFlag eq 'das'}">
	   	 <div class="box6">
	          <div class="title">
	               <div class="l"><h2 class="f14 ml10">内部转账对应出借</h2></div>
	          </div>
	        <table class='table table-striped table-bordered table-condensed' cellspacing="0" cellpadding="0" border="0"  class="table2" width="100%">
	        <thead>
	        <tr>
	            <th>序号</th>
	            <th>出借申请编号</th>
	            <th>出借方式</th>
	            <th>计划出借金额</th>
	            <th>出借日期</th>
	            <th>到期日期</th>
	            <th>付款方式</th>
	            <th>内转前金额</th>
	            <th>本次内转金额</th>
	            <th>内转后实际回款金额</th>
	            <th>出借状态</th>
	            <th>回款状态</th>
	        </tr>
	        </thead>
	     <c:forEach items="${smsCfSendListExt.smsSendListEx}" var="sendList" varStatus="status">
	        <tr>
	            <td>${status.index }</td>
	            <td>${sendList.loanCode }</td>
	            <td>${sendList.lendType }</td>
	            <td>${sendList.applyLendMoney }</td>
	            <td>${sendList.lendDay }</td>
	            <td>${sendList.dueDay }</td>
	            <td>${fns:dictName(dicts.tz_pay_type,sendList.payType,'-')}</td>
	            <td>${sendList.applyDeductMoney }</td>
	            <td>${sendList.applyTransferMoney }</td>
	            <td>${sendList.applyLendMoney }</td>
	            <td>${sendList.lendStatus }</td>
	            <td>${fns:dictName(dicts.tz_back_state,sendList.backStatus,'-')}</td>
	        </tr>
	     </c:forEach>
	    </table>
	    </div>
    </c:if>
     <div class="title">
        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>审批意见：
                	</label>
                	<textarea class='textarea_refuse' disabled="disabled">${smsCfSendListExt.checkEamine}</textarea>
                </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">
                		<span class='red'>*</span>审批结果 ：
                	</label>
                	<input type="radio" name="auditResult" value="1" disabled="disabled" <c:out value="${smsCfSendListExt.checkExaminetype ==1?'checked':'' }"/> />&nbsp;通过&nbsp;<input type="radio" name="auditResult" disabled="disabled" value="2" <c:out value="${smsCfSendListExt.checkExaminetype ==2?'checked':'' }"/> />&nbsp;不通过</td>
<%--                 	<form:radiobutton  path="smsCfSendListExt.checkExaminetype" value="${smsCfSendListExt.checkExaminetype }" disabled="disabled"/>通过 --%>
<%--                 	<form:radiobutton  path="smsCfSendListExt.checkExaminetype" value="${smsCfSendListExt.checkExaminetype }" disabled="disabled"/>不通过 --%>
                </td>
            </tr>
        </table>
    </div>
    </div>
     <div class="tright mb10 pr30">
<%-- 	            <c:choose> --%>
<%-- 	            	<c:when test="${remindFlag eq 'das'}"> --%>
<%-- 	            		<button class="btn cf_btn-primary mr10" onclick="window.location='${ctx}/remindDays/timeToRemindList'">返回</button> --%>
<%-- 	            	</c:when> --%>
<%-- 	            	<c:when test="${remindFlag eq '30das'}"> --%>
<%-- 	            		<button class="btn cf_btn-primary mr10" onclick="window.location='${ctx}/remindBefore30Days/advance30TimeToRemindList'">返回</button> --%>
<%-- 	            	</c:when> --%>
<%-- 	            	<c:otherwise> --%>
<%-- 	            		<button class="btn cf_btn-primary mr10" onclick="window.location='${ctx}/remindBefore10Days/advance10TimeToRemindList'">返回</button> --%>
<%-- 	            	</c:otherwise> --%>
<%-- 	            </c:choose>      --%>
	            <button class="btn cf_btn-primary mr10" onclick="window.history.back()">返回</button>
     </div>
</body>
</html>
