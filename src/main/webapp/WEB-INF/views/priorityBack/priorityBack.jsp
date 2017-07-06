<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/priorityBack/priorityBack.js"></script>
<title>优先回款详情页</title>
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
                <td><label class="lab">客户编号：</label><input type="text"   class="cf_input_text178" name="custCode" id="custCode" value="${llpv.ct.custCode}" disabled="disabled"/></td>
                <td>
                	<label class="lab">证件号码：</label>
                	<input type="text" id="custCertNum" class="cf_input_text178"  name="custCertNum" value="***" disabled="disabled"/>
                </td>
                <%-- <td>
                	<label class="lab">证件号码：</label>
                	<input type="text" id="custCertNum" class="cf_input_text178"  name="custCertNum" value="${fns:valueDesensitize(llpv.userManagerId,llpv.ct.custCertNum)} " disabled="disabled"/>
                </td> --%>
            </tr>
        </table>	
    </div>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">回收资金银行账户&nbsp;&nbsp;</h2></div>
    </div>
    <div class="box6">
       <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
               <td>
	               	<label class="lab">开户行：</label>
	               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountBank" value="${fns:dictName(dicts.tz_open_bank, llpv.list[0].accountBankId,'-')}" disabled="disabled"/>
               </td>
               <td>
	               	<label class="lab">银行所在地：</label>
	               	<input type="text" style="width: 100px" class="cf_input_text178" name="accountAddrprovince" value="${llpv.list[0].accountAddrProvince}" disabled="disabled">
	               	<input type="text"  class="cf_input_text178" name="accountAddrcity" value="${llpv.list[0].accountAddrCity}"  disabled="disabled"/>
	               	<c:if test="${llpv.la.payType!='4' }">
	               		<input type="text"  class="cf_input_text178" name="accountAddrdistrict"  value="${llpv.list[0].accountAddrDistrict}" disabled="disabled"/>
	               	</c:if>
               </td>    
				<td>
					<label class="lab">卡或折：</label>
					<input type="text" class="cf_input_text178" name="accountCardOrBooklet" value="${fns:dictName(dicts.com_card_type,llpv.list[0].accountCardOrBooklet, '-')}" disabled="disabled"/>
				</td>
			</tr>
        	<tr>
				<td>
					<label class="lab">具体支行：</label>
					<input type="text" class="cf_input_text178" style="width:100px;" name="accountBranch" value="${llpv.list[0].accountBranch}" disabled="disabled"/>
				</td>
				<td>
					<label class="lab">开户姓名：</label>
					<input type="text" class="cf_input_text178" style="width:100px;" name="accountName" value="${llpv.list[0].accountName}" disabled="disabled"/>
				</td>	
                <td>
	               	<label class="lab">账号：</label>
	               	<input type="text" class="cf_input_text178" name="accountNo" value="${llpv.list[0].accountNo}" disabled="disabled"/>
                </td>    
           </tr>
       </table>
    </div>
	<div class="body_r">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">出借信息</h2></div>
	    </div>
	    <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
	                	<label class="lab">出借编号：</label>
	                	<input type="text" class="cf_input_text178" id="applyCode" name="applyCode" value="${llpv.la.applyCode}"  disabled="disabled"/>
	                </td>
	                <td>
	                	<label class="lab">计划划扣日期：</label>
	                	<input type="text" class="cf_input_text178" name="deductDate" value="<fmt:formatDate value="${llpv.la.deductDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/>
	                </td>
	                <td>
	                	<label class="lab">计划出借日期：</label>
	                	<input type="text" class="cf_input_text178"  name="lendDate" value="<fmt:formatDate value="${llpv.la.lendDate}"  pattern="yyyy-MM-dd"/>" disabled="disabled"/>
	                </td>
	            </tr>
				<tr>
	                <td>
	                	<label class="lab">合同编号：</label>
	                	<input type="text" class="cf_input_text178" name="contractNumber" value="${llpv.la.contractNumber}" disabled="disabled"/>
	                </td>
	                <td>
	                	<label class="lab">付款方式：</label>
	                	<input type="text" class="cf_input_text178" name="payType" value="${fns:dictName(dicts.tz_pay_type,llpv.la.payType,'-') }" disabled="disabled"/>
	                </td>
				    <td>
				    	<label class="lab">计划出借金额：</label>
				     	<input type="text" class="cf_input_text178" name="lendMoney" value="${fns:getFormatNumber(llpv.la.lendMoney,'#,##0.00')}" disabled="disabled"/>
				     </td>
	            </tr>
				 <tr>
	                <td>
	                	<label class="lab">资金出借及回收方式：</label>
	                	<input type="text" class="cf_input_text178" name="lendMoney" value="${fns:getFormatNumber(llpv.la.lendMoney,'#,##0.00')}" disabled="disabled"/>
	                </td>
	                <td>
	                	<label class="lab">划扣金额：</label>
	                	<input type="text" class="cf_input_text178" name="deductMoney" value="${fns:getFormatNumber(llpv.la.deductMoney,'#,##0.00')}" disabled="disabled"/>
	                </td>
	            </tr>
	            
	        </table>
	    </div>
	</div>
    <div class="title">
        <div class="l"><h5 class="f14 ml10">附件  </h5></div>
    </div>
    <form id="priorityForm" action="${ctx}/lendApplyLook/applyPriorityBack" method="post" class="redeem_form">
		<!-- 优先回款的ID -->
		<input type="hidden" value="${llpv.priorityId }" name="priorityId" id="priorityId">
		<!-- 出借编号 -->
		<input type="hidden" value="${llpv.la.lendCode }" name="lendCode" id="lendCode">
		<!-- 理财经理 -->
		<input type="hidden" value="${llpv.la.managerCode }" name="createBy" id="createBy">
		<!-- 客户编号 -->
		<input type="hidden" value="${llpv.la.custCode }" name="applyBy" id="applyBy">
	    <input type="hidden" value="${llpv.la.id }" name="id" id="tableId">
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<sys:attachment></sys:attachment>
				</td>
			</tr>
		</table>
	</form> 
    <div class="body_r">
            <div class="tright mt20 pr30">
            	<button id="applybtn" class="btn cf_btn-primary">提交</button>
				<button onclick="window.history.back()" class="btn cf_btn-primary"  onclick="window.history.back(-1);">返回</button>
           </div>
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
