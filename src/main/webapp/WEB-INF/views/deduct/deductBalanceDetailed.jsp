<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/deduct.js"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<div class="body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">修改划扣状态</h2></div>
        <div class="r">
        	<c:choose>
		    	<c:when test="${failOrBalanceFalg eq 'fail'}">
		    		<button class="btn btn_sc ml10" id="fullFlowMark" name="${deductPool.applyCode}">全程留痕</button>
<!-- 		    		<button class="btn btn_sc ml10" id="" name="">撤消</button> -->
		    	</c:when>
		    	<c:otherwise>
		    		<button class="btn btn_sc ml10" id="updateRevoke" name="${deductPool.applyCode}_${deductPool.verTime}">撤消</button>
<%-- 		    		 <button class="btn btn_sc ml10" onclick="go('${ctx}/deductBalance/goBack?lendCode=${deductPool.applyCode}')">退回</button> --%>
		    	</c:otherwise>
		    </c:choose>
        </div>
    </div>
    	<form method="post" id="deductResult"
	    	<c:choose>
		    	<c:when test="${failOrBalanceFalg eq 'fail'}">
		    		action="${ctx}/deductFail/saveFailConduct" 
		    	</c:when>
		    	<c:otherwise>
		    		action="${ctx}/deductBalance/saveBalanceConduct" 
		    	</c:otherwise>
		    </c:choose>
	    >
	    <input type="hidden" name="applyCode" value="${deductPool.applyCode }">
	    <input type="hidden" name="verTime" value="${deductPool.verTime }">
	     <div class="box6">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			    <tr>
	                <td>
	                	<label class="lab" id="moneyLabel"><span class="red">*</span>划扣失败金额：</label>
<!-- 	                	<input type="hidden" required="required" name="failMoney" onkeyup="this.value=this.value.replace(/\D/g,'')" id="failMoney"  -->
<%-- 	                		class="cf_input_text178" value="${deductPool.failMoney }"> --%>
<%-- 	                	<input type="text" name="actualDeductMoney" required="required" id="actualDeductMoney" class="cf_input_text178" value="${deductPool.actualDeductMoney }"> --%>
	                	<c:choose>
					    	<c:when test="${failOrBalanceFalg eq 'fail'}">
					    		<input type="text" name="actualDeductMoney"  maxlength="10" required="required" id="actualDeductMoney"  class="cf_input_text178" 
					    		value='<fmt:formatNumber value="${deductPool.failMoney}" type="currency" pattern="##0.00#"/>'> 
					    	</c:when>
					    	<c:otherwise>
					    		<input type="text" name="actualDeductMoney" maxlength="10" required="required" id="actualDeductMoney" class="cf_input_text178" 
					    		value='<fmt:formatNumber value="${deductPool.loanMoney}" type="currency" pattern="##0.00#"/>'>
					    	</c:otherwise>
					    </c:choose>
	                </td>
					<td>
						<label class="lab"><span class="red">*</span>划扣平台：</label>
						<select name="dictApplyDeductType" select_required="-1" class="select78">
							<option value="">请选择</option>
	                		<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="d">
	                			<option value="${d.value}" 
	                				<c:if test="${d.value eq deductPoolExt.dictApplyDeductType}">
	                					selected
	                				</c:if> 
	                			>${d.label}</option>
	                		</c:forEach>
                		</select>
					</td>
	            </tr>
	            <tr>
	                <td>
		                <label class="lab"><span class="red">*</span>确认划扣结果：</label>
		                <input type="radio" name="dictDeductStatus" required="required" value="5" class="ml10 mr6"  onclick="javascript:show1();">划扣成功
		                <input type="radio" name="dictDeductStatus" required="required" value="6" checked class="ml10 mr6"  onclick="javascript:show1();">划扣失败
	                </td>
					<td id="T1">
						<label class="lab"><span class="red">*</span>失败原因：</label>
						<input type="text" name="confirmOpinion" required="required" id="confirmOpinion"  class="cf_input_text178" value="${deductPool.failReason }">
					</td>
	            </tr>
	        </table>
	        </div>
	        <div class="tright pr30 mt20">
		        <input type="submit" class="btn cf_btn-primary" value="提交">
		        
		        <input class="btn cf_btn-primary" type="button" onclick="window.history.back()" value="返回">
		        
<%-- 		        <c:choose> --%>
<%-- 			    	<c:when test="${failOrBalanceFalg eq 'fail'}"> --%>
<%-- 			    		<input class="btn cf_btn-primary" type="button" onclick="go('${ctx}/deductFail/failList')" value="返回"> --%>
<%-- 			    	</c:when> --%>
<%-- 			    	<c:otherwise> --%>
<%-- 			    		<input class="btn cf_btn-primary" type="button" onclick="go('${ctx}/deductBalance/balanceList')" value="返回"> --%>
<%-- 			    	</c:otherwise> --%>
<%-- 		    </c:choose> --%>
	        </div>
        </form>
</div>
   <div id="modal-sub" class="modal fade subwindow" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">线下划扣</h4>
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
<script>

	function f(){
        var doc;
        if (document.all){//IE
                doc = document.frames["MyIFrame"].document;
        }else{//Firefox    
                doc = document.getElementById("MyIFrame").contentDocument;
        }
        doc.getElementById("s").style.color="blue";
	}
</script>
</html>
