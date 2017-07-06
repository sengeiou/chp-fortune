<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/excuteBackInterest.js"></script>
<title>执行回息</title>
</head>
<body>
	<div class="body_r">
	    <div class="title">
	        <div class="l"><h2 class="f14 ml10">回息信息</h2></div>
	        <div class="r">
	            <input type="button" class="btn btn_sc ml10"  value="全程留痕" onclick="fullMark('${bibv.detailsPage.lendCode}')"/>
	        </div>
	    </div>								
	    <form id="applyForm" action="" method="post">
		    <div class="box6">
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		           <input type="hidden" id="backiId" name="backiId" value="${bibv.detailsPage.backiId}"/>
		           <input type="hidden" id="ld" name="lendCode" value="${bibv.detailsPage.lendCode}"/>
		           <input type="hidden" id="verTime" name="verTime" value="${bibv.detailsPage.verTime}"/>
		           <tr>
		           		<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="***" disabled="true"></td>
		                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="${bibv.detailsPage.customerName}" disabled="true"></td> --%>
		                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${bibv.detailsPage.customerCode}" disabled="true"></td>
		            </tr>
		            <tr>
		                <td><label class="lab">当期账单日：</label><input type="text" class="cf_input_text178" name="currentBillday" value="<fmt:formatDate value='${bibv.detailsPage.currentBillday}'  pattern='yyyy-MM-dd'/>" disabled="true"></td>
		                <td><label class="lab">营业部：</label><input type="text" class="cf_input_text178" name="orgName" value="${bibv.detailsPage.orgName}" disabled="true"></td>
		            </tr>
		            <tr>
		                <td><label class="lab">当期应回金额：</label><input type="text" class="cf_input_text178" name="backMoney" value="<fmt:formatNumber value='${bibv.detailsPage.backMoney}' type='currency' pattern='##0.00'/>" disabled="true"></td>
		                <td><label class="lab">实际回息金额：</label><input type="text" class="cf_input_text178" name="backMoney" value="<fmt:formatNumber value='${bibv.detailsPage.backMoney}' type='currency' pattern='##0.00'/>" disabled="true">(￥<fmt:formatNumber value="${bibv.detailsPage.backMoney}" type="currency" pattern="##0.00"/>)</td>
		            </tr>
					<tr>
		                <td><label class="lab">初始出借日期：</label><input type="text" class="cf_input_text178" name="applyLendDay" value="<fmt:formatDate value="${bibv.detailsPage.applyLendDay}" pattern="yyyy-MM-dd"/>" disabled="true"></td>
		                <td><label class="lab">初始出借金额：</label><input type="text" class="cf_input_text178" name="applyLendMoney" value="<fmt:formatNumber value='${bibv.detailsPage.applyLendMoney}' type='currency' pattern='##0.00'/>" disabled="true"></td>
		            </tr>
		        </table>
		    </div>
		    <div class="title">
				<div class="l">
					<h2 class="f14 ml10">审批信息</h2>
				</div>
			</div>
			<div class="box6">
				<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td colspan="2"><label class="lab">
							<span class="red">*</span>审批结果：</label>
								<input id="rar" name="checkExaminetype" type="radio" value="1" class="ml10 mr6" onchange="showbk()" required/>通过
								<input id="rar" name="checkExaminetype" value="2" type="radio" class="ml10 mr6" onchange="showbk()"/>不通过
						</td>
						<td>
							<div class="backReason" style="display: none">
								<label class="lab"><span class="red">*</span>回息退回原因：</label> 
									<select class="select180" id="cemine" name="checkExamine" onchange="javascript:showbk();" select_required="1">
										<option value="">请选择</option>
										<c:forEach items="${fns:getDictList('tz_backsms_reason')}" var='item'>
											<option value="${item.value }">
												${item.label}
											</option>
										</c:forEach>
									</select><textarea id="tar" name="textAre" style="display: none;" textarea_required="1" required></textarea>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="title">
		        <div class="l"><h2 class="f14 ml10">中间人信息</h2></div>
		    </div>
	     	<div id="bak" class='box5' style="display:none;">
		        <table class="table table-striped table-bordered table-condensed" cellpadding="0" cellspacing="0" border="0" width="100%">
				    <thead>
				    <tr>
					    <th></th>
						<th>中间人</th>
						<th>证件号码</th>
						<th>开户行</th>
						<th>银行账号</th>
					</tr>
					</thead>
					<c:forEach items="${bibv.platformMesglist}" var="p">
						<tr>
						    <td><input id="bak" type="radio" name="platformId" value="${p.platformId}_${p.id}" required></td>
							<!-- 屏蔽客户姓名/手机号/身份证号 -->
							<td>***</td>
							<%-- <td>${p.name}</td> --%>
							<td>***</td>
							<%-- <td>${p.certNo}</td> --%>
							<td>${p.bank}</td>
							<td>${p.bankCode}</td>
						</tr>
					</c:forEach>
		        </table>
	        </div>
		</form>
	    <div class="tright pr30 mt20">
	    	<input type="button" value="提交" class="btn cf_btn-primary" onclick="javascript:excuteComit('excuteBackInterest/excuteOperation','excuteBackInterest/loadExcuteBackInterestList');"/>
	    	<input type="button" class="btn cf_btn-primary" value="返回" onclick="javascript:history.back(-1);"/>
	    </div>
		
	</div>
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