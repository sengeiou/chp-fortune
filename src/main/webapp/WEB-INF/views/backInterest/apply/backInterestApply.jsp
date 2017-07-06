<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<meta name="decorator" content="default"/>
		<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
		<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestApply.js"></script>
		<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
		<title>回息申请</title>
	</head>
	<body>
    	<div class="title">
        	<div class="l"><h2 class="f14 ml10">回息信息</h2></div>
        	<div class="r">
           		<input type="button" value="全程留痕"  class="btn btn_sc" onclick="fullMark('${dtp.lendCode}')"/>
        	</div>
    	</div>
	    <div class="box6">
	    	<form id="applyForm" type="post">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	        	<input type="hidden" id="ht" name="backiId" value="${dtp.backiId}"/>
	        	<input type="hidden" id="ld" name="lendCode" value="${dtp.lendCode}"/>
	        	<input type="hidden" id="verTime" name="verTime" value="${dtp.verTime}"/>
	        	<input type="hidden" id="verState" name="verState" value="${dtp.verState}"/>
	            <tr>
	            	<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="***" disabled="true"></td>
	            	<%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="${dtp.customerName}" disabled="true"></td> --%>
	                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${dtp.customerCode}" disabled="true"></td>
	            </tr>
	            <tr>
	                <td><label class="lab">当期账单日：</label><input type="text" class="cf_input_text178" name="currentBillday" value="<fmt:formatDate value='${dtp.currentBillday}'  pattern='yyyy-MM-dd'/>" disabled="true"></td>
	                <td><label class="lab">营业部：</label><input type="text" class="cf_input_text178" name="orgName" value="${dtp.orgName}" disabled="true"></td>
	            </tr>
	            <tr>
	                <td><label class="lab">当期应回金额：</label><input type="text" class="cf_input_text178" name="backMoney" value="<fmt:formatNumber value="${dtp.backMoney}" type="currency" pattern="￥#,##0.00"/>" disabled="true"></td>
	                <td><label class="lab"><span class="red">*</span>实际回息金额：</label><input type="text" class="cf_input_text178" name="backRealMoney" value="<fmt:formatNumber value="${dtp.backRealMoney}" type="currency" pattern="##0.00"/>" disabled="true">(￥<fmt:formatNumber value="${dtp.backRealMoney}" type="currency" pattern="##0.00"/>)</td>
	            </tr>
	        </table>
	        </form>
	    </div>
    	<div class="tright pr30 mt20">
    			<input type="button" class="btn cf_btn-primary" value="提交" onclick="javascript:apply('backInterestApply/toSubmit','backInterestApply/loadBackInterestApplylist');"/>
    			<input type="button" class="btn cf_btn-primary" value="返回" onclick="javascript:history.back(-1);"/></div>

	<div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">${lendCode}的历史留痕</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="关闭"  class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
