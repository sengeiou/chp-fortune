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
		    <button class="btn btn_sc ml10" id="fullFlowMark" name="${deductPool.applyCode}">全程留痕</button>
        </div>
    </div>
    <div class="box6">
    	<form method="post" id="deductResult"
	    	<c:choose>
		    	<c:when test="${goldAccountFlag eq 'goldAccoun'}">
		    		action="${ctx}/goldAccoun/conductSubmit" 
		    	</c:when>
		    	<c:otherwise>
		    		action="${ctx}/goldAccounFali/conductSubmit" 
		    	</c:otherwise>
		    </c:choose>
	    >
	    <input type="hidden" name="applyCode" value="${deductPool.applyCode }">
	    <input type="hidden" name="verTime" value="${deductPool.verTime }">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			    <tr>
	                <td>
	                	<label class="lab" id="moneyLabel"><span class="red">*</span>划扣失败金额：</label>
				       <c:choose>
					    	<c:when test="${goldAccountFlag eq 'goldAccoun'}">
					    		<input type="text" name="failMoney"  maxlength="10"  class="cf_input_text178" id="actualDeductMoney" required="required"  onkeyup="this.value=this.value.replace(/\D/g,'')"
	                				value='<fmt:formatNumber value="${deductPool.loanMoney}" type="currency" pattern="##0.00#"/>'>
					    	</c:when>
					    	<c:otherwise>
					    		<input type="text" name="failMoney"  maxlength="10"  class="cf_input_text178" id="actualDeductMoney" required="required"  onkeyup="this.value=this.value.replace(/\D/g,'')"
	                				value='<fmt:formatNumber value="${deductPool.failMoney}" type="currency" pattern="##0.00#"/>'> 
					    	</c:otherwise>
					    </c:choose>

	                </td>
	                <td>
		                <label class="lab"><span class="red">*</span>确认划扣结果：</label>
		                <input type="radio" name="dictDeductStatus" value="5" required="required" class="ml10 mr6" onclick="javascript:show1();">划扣成功
		                <input type="radio" name="dictDeductStatus" value="6" required="required" class="ml10 mr6" checked="checked" onclick="javascript:show1();">划扣失败
	                </td>
	            </tr>
	            <tr>
	            	<td id="T1">
						<label class="lab"><span class="red">*</span>失败原因：</label>
						<input type="text" name="confirmOpinion" required="required"  class="cf_input_text178" value="${deductPool.failReason }">
					</td>
	            </tr>
	        </table>
	     <div class="tright mt20 pr30">
	        <input type="submit" class="btn cf_btn-primary" value="提交">
	        <c:choose>
		    	<c:when test="${goldAccountFlag eq 'goldAccoun'}">
		    		<input class="btn cf_btn-primary" type="button" onclick="go('${ctx}/goldAccoun/goldAccounList')" value="返回">
		    	</c:when>
		    	<c:otherwise>
		    		<input class="btn cf_btn-primary" type="button" onclick="go('${ctx}/goldAccounFali/goldAccounFailList')" value="返回">
		    	</c:otherwise>
	    	</c:choose>
        </div>
        </form>
    </div>
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
