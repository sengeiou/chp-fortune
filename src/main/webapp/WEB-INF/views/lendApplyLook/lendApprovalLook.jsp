<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/lendApplyLook/lendApprovalLook.js"></script>
<title>出借审批</title>
</head>
<body>
		<input type="hidden" value="${la.applyCode }" name="applyCode" id="applyCode">
    	<input type="hidden" value="${la.id }" name="id" id="tableId">
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab"><span class="red">*</span>客户姓名：</label>***</td>
                <%-- <td><label class="lab"><span class="red">*</span>客户姓名：</label>${cs.custName}</td> --%>
				<td><label class="lab"><span class="red">*</span>性     别：</label>${fns:dictName(dicts.sex, cs.dictCustSex, '-')}</td>
                <td><label class="lab"><span class="red">*</span>客户编号：</label>${cs.custCode}</td>  
            </tr>
			<tr>
                <td colspan="3"><label class="lab"><span class="red">*</span>营业部：</label>${la.storeOrgId}</td>
            </tr>
        </table>	
    </div>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">付款银行(回款银行)信息</h2></div>
    </div>
    <div class="box6">
    	<c:forEach items="${list}" var="item">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>开户行：</label><input type="text" style="width: 100px" class="cf_input_text178" name="accountBank" value="${fns:dictName(dicts.tz_open_bank, item.account_bank, '-')}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>银行所在地：</label><input type="text" style="width: 100px" class="cf_input_text178" name="accountAddrprovince" value="${item.account_addrprovince}" disabled="disabled">
                																		&nbsp;
                																<input type="text" class="cf_input_text178" name="accountAddrcity" value="${item.account_addrcity}"  disabled="disabled"/>
                																		&nbsp;
                																<input type="text" class="cf_input_text178" name="accountAddrdistrict"  value="${item.account_addrdistrict}" disabled="disabled"/></td>    
				<td><label class="lab"><span class="red">*</span>卡或折：</label><input type="text" class="cf_input_text178" name="accountCardOrBooklet" value="${fns:dictName(dicts.com_card_type, item.account_card_or_booklet, '-')}" disabled="disabled"/></td>
			</tr>
            <tr>
				<td colspan="2"><label class="lab"><span class="red">*</span>具体支行：</label><input type="text" class="cf_input_text178" style="width:300px;" name="accountBranch" value="${item.account_branch}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>账号：</label><input type="text" class="cf_input_text178" name="accountNo" value="${item.account_no}" disabled="disabled"/></td>    
            </tr>
        </table>
        </c:forEach>
    </div>

<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">申请信息</h2></div>
    </div>
    <div class="box6">
    	<input type="hidden" value="${la.applyCode }" name="applyCode">
    	<input type="hidden" value="${la.id }" name="id">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>申请日期：</label><input type="text" class="cf_input_text178" name="applyDate" value="<fmt:formatDate value="${la.applyDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" class="cf_input_text178" name="deductDate" value="<fmt:formatDate value="${la.deductDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" class="cf_input_text178"  name="lendDate" value="<fmt:formatDate value="${la.lendDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
            </tr>
			<tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label><input type="text" class="cf_input_text178" name="productCode" value="${fns:getProductLabel(la.productCode)}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label><input type="text" class="cf_input_text178" name="payType" value="${fns:dictName(dicts.tz_pay_type, la.payType, '-') }" disabled="disabled"/></td>                
			    <td><label class="lab"><span class="red">*</span>划扣平台：</label><input type="text" class="cf_input_text178" name="deductTypeId" value="${fns:dictName(dicts.jk_deduct_plat, la.deductTypeId, '-') }" disabled="disabled"/></td>
            </tr>
			 <tr>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" class="cf_input_text178" name="lendMoney" value="${fns:getFormatNumber(la.lendMoney ,'#,##0.00')}" disabled="disabled"/></td>               
				<td><label class="lab"><span class="red">*</span>合同版本：</label><input type="text" class="cf_input_text178" disabled style="background-color:gray;" name="protocoEdition"  value="${fns:dictName(dicts.tz_contract_vesion, la.protocoEdition, '-') }" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>托管状态：</label><input type="text" class="cf_input_text178" name="applyHostedStatus" value="${fns:dictName(dicts.tz_trust_state, cs.applyHostedStatus,'-') }" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>划扣金额：</label><input type="text" class="cf_input_text178" name="deductMoney" value="${fns:getFormatNumber(la.deductMoney ,'#,##0.00')}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>合同编号：</label><input type="text" class="cf_input_text178" name="contractNumber" value="${la.contractNumber}" disabled="disabled"/></td>
				<td><label class="lab"><span class="red">*</span>内部转账金额：</label><input type="text" class="cf_input_text178" name="transferMoney" value="${fns:getFormatNumber(la.transferMoney,'#,##0.00')}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab">备注：</label><textarea class="textarea_refuse" name="remark" disabled="disabled"/>${la.remark}</textarea></td>
            </tr>
        </table>
    </div>
    <c:if test="${la.payType eq 2 or la.payType eq 6 }">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">内部转账对应出借</h2></div>
	    </div>
	    <div  class="box6">
		    <table class='table table-striped table-bordered table-condensed data-list-table' width="100%">
		        <thead>
			        <tr>
			            <th>序号</th>
			            <th>出借编号</th>
			            <th>出借方式</th>
			            <th>出借金额</th>
			            <th>出借日期</th>
			            <th>到期日期</th>
			            <th>付款方式</th>
			            <th>出借状态</th>
			            <th>回款状态</th>
			            <th>协议版本号</th>
			            <th>账单日</th>
			    	</tr>
		    	</thead>
		    	<c:forEach items="${transferLendList}" var="item" varStatus="status">
		    		<tr>
		    			<td>${status.count}</td>
			            <td>${item.applyCode}</td>
			            <td>${fns:getProductLabel(item.productCode) }</td>
			            <td>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
			            <td><fmt:formatDate value="${item.lendDate}" pattern="yyyy-MM-dd" /></td>
			            <td><fmt:formatDate value="${item.expireDate}" pattern="yyyy-MM-dd" /></td>
			            <td>${fns:dictName(dicts.tz_pay_type,item.payType,'-') }</td>
			            <td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }</td>
			            <td>${fns:dictName(dicts.tz_back_state,item.backMoneyStatus,'-') }</td>
			            <td>${fns:dictName(dicts.tz_protocol_version,item.protocoEdition,'-') }</td>
			            <td>${ item.billDay }</td>
		    		</tr>
		    	</c:forEach>
		    </table>
		</div>
	</c:if>
    <div class="title">
	        <div class="l"><h5 class="f14 ml10">附件</h5></div>
	    </div>
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
</div>
     
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">审批信息</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>审批结果：</label>
                <input type="radio" name="checkExaminetype"  <c:if test="${log.checkExaminetype==1}">checked="true"</c:if> onclick="_change(this);" disabled="disabled"/>&nbsp;通过&nbsp;
                <input type="radio" name="checkExaminetype"  <c:if test="${log.checkExaminetype==0}">checked="true"</c:if>  onclick="_change(this);" disabled="disabled"/>&nbsp;不通过</td>
            </tr>
            <c:if test="${log.checkExaminetype==2 }">
				<tr id="_t1" style="">
	                <td><label class="lab"><span class="red">*</span>错误类型：</label>
	                <input type="text" name="errorTypeId" class="cf_input_text178" style="width: 200px;" value="${fns:getDictLabel(log.errorTypeId,'tz_error_type',log.errorTypeId) }" disabled="disabled">
	            </tr>
            </c:if>
            <tr>
                <td><label class="lab"><span class="red">*</span>审批意见：</label><textarea class="textarea_refuse" disabled="disabled">${log.checkExamine}</textarea></td>
            </tr>
        </table>
    </div>
            <div class="tright pr30 mt20"><input type="button" value="返回" class="btn cf_btn-primary mr10" onclick="javascript:history.back(-1);"/></div>
</div>
</body>
<script>
	function _change(src){
		var val = src.value;
		if(val=='0'){
			document.getElementById("_t1").style.display="none";
		}else{
			document.getElementById("_t1").style.display="";
		}
	}
</script>
</html>
