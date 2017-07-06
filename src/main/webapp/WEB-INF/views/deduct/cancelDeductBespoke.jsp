<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
function ajaxPage(n, s) {
	$("#pageNo2").val(n);
	$("#pageSize2").val(s);
	var url,data,type;
	url = ctx + '${pageUrl}';
	data = $("#searchForm2").serialize();
	type = 'post'
	contents_getJsonForHtml(url, data, type, function(result){
		//覆盖列表+page
		$('#theDayList').html(result);
//		initAfterLoad();
	},function(){
		
	},null);
	return false;
}
</script>
<div class="body_r">
    <div class="box1 mb10">
    <form action="" id="searchForm2">
            	<input id="pageNo2" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize2" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!--     	<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%"> -->
<!--             <tr> -->
<!--                 <td> -->
<!--                 	<label class="lab">出借编号：</label> -->
<%--                 	<input type="text" name="applyCode" value="${deductPoolExt.applyCode}" class="cf_input_text178"> --%>
<!--                 </td> -->
<!--                 <td> -->
<!--                 	<label class="lab">客户姓名：</label> -->
<%--                 	<input type="text" name="custName" value="${deductPoolExt.custName}" class="cf_input_text178"> --%>
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr>  -->
<!--             	<td> -->
<!-- 					<label class="lab">计划划扣日期：</label> -->
<!--                 	<input type="text"  name="applyDeductDateStart" id="applyDeductDateStart"  -->
<%--                 		value='<fmt:formatDate value="${deductPoolExt.applyDeductDateStart}" pattern="yyyy-MM-dd"/>'  --%>
<!--                 		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDateEnd\',{d:-1});}'})"> - -->
<!--                 	<input type="text" name="applyDeductDateEnd" id="applyDeductDateEnd"  -->
<%--                 		value='<fmt:formatDate value="${deductPoolExt.applyDeductDateEnd}" pattern="yyyy-MM-dd"/>'  --%>
<!--                 		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDateStart\',{});}'})"> -->
<!-- 				</td> -->
<!-- 			   	<td> -->
<!-- 			   		<label class="lab">营业部：</label> -->
<%-- 			   		<sys:orgTree id="org"  name="checkNode" value="${deductPoolExt.checkNode}" labelName="orgName"  labelValue="${deductPoolExt.orgName}" /> --%>
<!-- 			   	</td> -->
<!--             </tr> -->
<!--         </table> -->
<!--         <div class='tright pr30 pb5 pt10'> -->
<!--             <input class="btn cf_btn-primary" type="submit" value="搜索"/> -->
<!--             <input type="reset" class="btn cf_btn-primary" value="清除"> -->
<!--         </div> -->
    </form>
    <div id="theDayList">
    	<%@ include file="/WEB-INF/views/deduct/cancelDeductBespokeList.jsp"%>
    </div>
</div>
</div>
	
