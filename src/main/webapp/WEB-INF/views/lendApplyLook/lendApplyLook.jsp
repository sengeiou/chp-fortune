<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/lendApplyLook/lendApplyLook.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
        <div class="r">
            <button class="btn btn_sc ml10" id="fullTraceBtn"  value="${llpv.la.applyCode}" >全程留痕</button>
        </div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="custName" value="***" disabled="disabled"/></td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="custName" value="${llpv.ct.custName}" disabled="disabled"/></td> --%>
                <td><label class="lab">客户编号：</label><input type="text"   class="cf_input_text178" name="custCode" value="${llpv.ct.custCode}" disabled="disabled"/></td>
                <td>
                	<label class="lab">证件号码：</label>
                	<input type="text" id="custCertNum" class="cf_input_text178"  name="custCertNum" value="***" disabled="disabled"/>
                	<%-- <input type="text" id="custCertNum" class="cf_input_text178"  name="custCertNum" value="${fns:valueDesensitize(llpv.userManagerId,llpv.ct.custCertNum)} " disabled="disabled"/> --%>
                </td>
            </tr>
        </table>	
    </div>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">付款<c:if test="${fn:length(llpv.list) == 1 }">(回款)</c:if>银行信息&nbsp;&nbsp;</h2></div>
    </div>
    <div class="box6">
       <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
               <td>
               	<label class="lab"><span class="red">*</span>开户行：</label>
               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountBank" value="${fns:dictName(dicts.tz_open_bank, llpv.list[0].accountBankId,'-')}" disabled="disabled"/>
               </td>
               <td>
               	<label class="lab"><span class="red">*</span>银行所在地：</label>
               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountAddrprovince" value="${llpv.list[0].accountAddrProvince}" disabled="disabled">
               	<input type="text"  class="cf_input_text178" name="accountAddrcity" value="${llpv.list[0].accountAddrCity}"  disabled="disabled"/>
               	<c:if test="${llpv.la.payType!='4' }">
               		<input type="text"  class="cf_input_text178" name="accountAddrdistrict"  value="${llpv.list[0].accountAddrDistrict}" disabled="disabled"/>
               	</c:if>
               </td>    
			<td>
				<label class="lab"><span class="red">*</span>卡或折：</label>
				<input type="text" class="cf_input_text178" name="accountCardOrBooklet" value="${fns:dictName(dicts.com_card_type,llpv.list[0].accountCardOrBooklet, '-')}" disabled="disabled"/>
			</td>
		</tr>
           <tr>
			<td colspan="2">
				<label class="lab"><span class="red">*</span>具体支行：</label>
				<input type="text" class="cf_input_text178" style="width:300px;" name="accountBranch" value="${llpv.list[0].accountBranch}" disabled="disabled"/>
			</td>
               <td>
               	<label class="lab"><span class="red">*</span>账号：</label>
               	<input type="text" class="cf_input_text178" name="accountNo" value="${llpv.list[0].accountNo}" disabled="disabled"/>
               </td>    
           </tr>
       </table>
    </div>
    
    <c:if test="${fn:length(llpv.list) > 1 }">
    	<div class="title">
	        <div class="l"><h2 class="f14 ml10">回款银行信息&nbsp;&nbsp;</h2></div>
	    </div>
	    <div class="box6">
	       <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	           <tr>
	               <td>
	               	<label class="lab"><span class="red">*</span>开户行：</label>
	               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountBank" value="${fns:dictName(dicts.tz_open_bank,llpv.list[1].accountBankId, '-')}" disabled="disabled"/>
	               </td>
	               <td>
	               	<label class="lab"><span class="red">*</span>银行所在地：</label>
	               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountAddrprovince" value="${llpv.list[1].accountAddrProvince}" disabled="disabled">
	               	<input type="text"  class="cf_input_text178" name="accountAddrcity" value="${llpv.list[1].accountAddrCity}"  disabled="disabled"/>
	               	<c:if test="${llpv.la.payType!='4' }">
	               		<input type="text"  class="cf_input_text178" name="accountAddrdistrict"  value="${llpv.list[1].accountAddrDistrict}" disabled="disabled"/>
	               	</c:if>
	               </td>    
				<td>
					<label class="lab"><span class="red">*</span>卡或折：</label>
					<input type="text" class="cf_input_text178" name="accountCardOrBooklet" value="${fns:dictName(dicts.com_card_type,llpv.list[1].accountCardOrBooklet, '-')}" disabled="disabled"/>
				</td>
			</tr>
	           <tr>
				<td colspan="2">
					<label class="lab"><span class="red">*</span>具体支行：</label>
					<input type="text" class="cf_input_text178" style="width:300px;" name="accountBranch" value="${llpv.list[1].accountBranch}" disabled="disabled"/>
				</td>
	               <td>
	               	<label class="lab"><span class="red">*</span>账号：</label>
	               	<input type="text" class="cf_input_text178" name="accountNo" value="${llpv.list[1].accountNo}" disabled="disabled"/>
	               </td>    
	           </tr>
	       </table>
	    </div>
    </c:if>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">申请信息</h2></div>
    </div>
    <div class="box6">
    	<input type="hidden" value="${llpv.la.applyCode }" name="applyCode" id="applyCode">
    	<input type="hidden" value="${llpv.la.id }" name="id" id="tableId">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab"><span class="red">*</span>申请日期：</label><input type="text" class="cf_input_text178" name="applyDate" value="<fmt:formatDate value="${llpv.la.applyDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>计划划扣日期：</label><input type="text" class="cf_input_text178" name="deductDate" value="<fmt:formatDate value="${llpv.la.deductDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>计划出借日期：</label><input type="text" class="cf_input_text178"  name="lendDate" value="<fmt:formatDate value="${llpv.la.lendDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/></td>
            </tr>
			<tr>
                <td><label class="lab"><span class="red">*</span>出借模式：</label>
                <input type="text" class="cf_input_text178" name="productCode" value="${llpv.la.productCode}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>付款方式：</label>
                <input type="text" class="cf_input_text178" name="payType" value="${fns:dictName(dicts.tz_pay_type,llpv.la.payType,'-') }" disabled="disabled"/></td>
			     <td><label class="lab"><span class="red">*</span>划扣平台：</label>
			     <input type="text" class="cf_input_text178" name="deductTypeId" value="${fns:dictName(dicts.tz_deduct_plat,llpv.la.deductTypeId,'-') }" disabled="disabled"/></td>
            </tr>
			 <tr>
                <td><label class="lab"><span class="red">*</span>计划出借金额：</label><input type="text" class="cf_input_text178" name="lendMoney" value="${fns:getFormatNumber(llpv.la.lendMoney,'#,##0.00')}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>划扣金额：</label><input type="text" class="cf_input_text178" name="deductMoney" value="${fns:getFormatNumber(llpv.la.deductMoney,'#,##0.00')}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>内部转账金额：</label><input type="text" class="cf_input_text178" name="transferMoney" value="${fns:getFormatNumber(llpv.la.transferMoney,'#,##0.00')}" disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>合同版本：</label>
                <input type="text" disabled style="background-color:gray;" class="cf_input_text178" name="protocoEdition" value="${fns:dictName(dicts.tz_contract_vesion,llpv.la.protocoEdition,'-') }" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>合同编号：</label><input type="text" class="cf_input_text178" name="contractNumber" value="${llpv.la.contractNumber}" disabled="disabled"/></td>
                <td><label class="lab"><span class="red">*</span>折扣率：</label><input type="text" class="cf_input_text178" name="salesDiscount" value="${fns:getFormatNumber(llpv.la.salesDiscount,'#,##0.00')}"  disabled="disabled"/></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>托管状态：</label>
					<input type="text" class="cf_input_text178" name="applyHostedStatus" value="${fns:dictName(dicts.tz_trust_state,llpv.ct.applyHostedStatus,'-') }" disabled="disabled"/></td>
            </tr>
            <tr>
                <td colspan="3"><label class="lab">备注：</label><textarea class="textarea_refuse" name="remark" disabled="disabled"/>${llpv.la.remark}</textarea></td>
            </tr>
        </table>
    </div>
    <c:if test="${llpv.la.payType eq 2 or llpv.la.payType eq 6 or llpv.la.payType eq 7 }">
	  	<div class="title">
	        <div class="l"><h2 class="f14 ml10">内部转账对应出借</h2></div>
	    </div>
	    <div  class="box6">
		    <table class='table table-striped table-bordered table-condensed data-list-table' width="100%">
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
			            <th>出借状态</th>
			            <th>回款状态</th>
			            <th>协议版本号</th>
			            <th>账单日</th>
			    	</tr>
		    	</thead>
		    	<c:forEach items="${llpv.transferList}" var="item" varStatus="status">
		    		<tr>
		    			<td>${status.count}</td>
		    			<td>${item.custName}</td>
			            <td>${item.applyCode}</td>
			            <td>${fns:getProductLabel(item.productCode) }</td>
			            <td>${fns:getFormatNumber(item.lendMoney,'￥#,##0.00')}</td>
			            <td><fmt:formatDate value="${item.lendDate}" pattern="yyyy-MM-dd" /></td>
			            <td><fmt:formatDate value="${item.expireDate}" pattern="yyyy-MM-dd" /></td>
			            <td>${fns:dictName(dicts.tz_pay_type,item.payType,'-') }</td>
			            <td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }</td>
			            <td>${fns:dictName(dicts.tz_back_state,item.backMoneyStatus,'-') }</td>
			            <td>${fns:dictName(dicts.tz_protocol_version,item.protocoEdition,'-') }</td>
			            <td>${item.billDay}</td>
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
		
		<div class="title">
        <div class="l"><h2 class="f14 ml10">第五条    甲方回款风险的处置方式</h2></div>
    </div>
    <div class="box6">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                	<div>
                		<h3>5.1当借款人发生违约时，甲方选择如下之一的方式处置回款风险：</h3>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="check1"  disabled <c:if test="${llpv.check1=='1' }">checked=checked</c:if> value="1" >甲方自行追讨，丙方提供协助，由甲方自行承担损失和风险，同时自行享有借款人违约所支付的罚息、违约金等。</p>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="check1" disabled <c:if test="${llpv.check1=='2' }">checked=checked</c:if> value="2" >由丙方还款保障金专用账户的款项分担借款人回款风险，规则如下：</p>
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
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="check2" disabled <c:if test="${llpv.check2=='1' }">checked=checked</c:if> value="1" >对丙方推荐的借款人如果决定出借，须通过当面签署或授权签署等方式直接与借款人签署《借款协议》，甲方有义务按照《借款协议》的约定及时付款给借款人。</p>
                		<p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="check2"  disabled <c:if test="${llpv.check2=='2' }">checked=checked</c:if> value="2" >对丙方服务中的《借款协议》下的债权债务关系进行受让，将购买债权的款项支付给债权转让方，从而完成资金的出借。</p>
                	</div>
                </td>
            </tr>
        </table>
    </div>
</div>   
    <div class="body_r">
            <div class="tright mt20 pr30"><input type="button" value="返回" class="btn cf_btn-primary" onclick="window.history.back(-1);"/></div>
    </div>
    
    <div id="modal-sub" class="modal fade subwindow" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">全程留痕</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn cf_btn-primary" id="subClose">关闭</button>
			</div>
		</div>
	</div>
</div>
    
</body>
</html>
