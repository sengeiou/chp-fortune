<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="body_r">
    <div class="box1 mb10">
    	<form method="post" id="searchForm1"
	    	<c:choose>
	    		<c:when test="${failOrBalanceFalg eq 'theDay'}">
		    		action="${ctx}/theDayAlreadPor/theayBatchStatus" 
		    	</c:when>
		    	<c:when test="${failOrBalanceFalg eq 'Balance'}">
		    		action="${ctx}/deductBalance/batchDeductStatus" 
		    	</c:when>
		    	<c:otherwise>
		    		action="${ctx}/deductFail/theayBatchStatus" 
		    	</c:otherwise>
		    </c:choose>
	    >
	    <input type="hidden" name="applyCodeSub" value="${deductPool.applyCode }">
	    <input type="hidden" name="theDayId" value="${deductPool.deductApplyId }">
	        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
		                <label class="lab">划扣平台：</label>
						<select name="dictApplyDeductType" class="select78">
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
		                <label class="lab">确认划扣结果：</label>
		                <input type="radio" name="dictDeductStatus" value="5" required class="ml10 mr6" onclick="javascript:show1();">划扣成功
		                <input type="radio" name="dictDeductStatus" value="6" checked required class="ml10 mr6" onclick="javascript:show1();">划扣失败
	                </td>
	            </tr>
	            <tr>
					<td id="T11">
						<label class="lab"><span class="red">*</span>失败原因：</label>
						<input type="text" name="confirmOpinion" required  class="cf_input_text178" value="${deductPool.confirmOpinion }">
					</td>
	            </tr>
	        </table>
        </form>
    </div>
</div>
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
  
	function show1(){
		var radio = $("input[name='dictDeductStatus']:checked").val();
		if(radio == '5'){
			$("#T11").attr("style","display:none;");
			$("input[name='confirmOpinion']").attr("required",false)
		}else{
			$("#T11").attr("style","display:block");
			$("input[name='confirmOpinion']").attr("required",true)
		}
	}
</script>
