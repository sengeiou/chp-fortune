<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同调拨</title>
<script type="text/javascript">
	function page(){
		$("#searchForm").attr("action","${ctx}/contract/changeApplyList");
		$("#searchForm").submit();
		return false;
	}
	function goPage(pageURL){
		window.location = pageURL;
	}
</script>
</head>
<body>
    <form:form id="searchForm" modelAttribute="cd" action="${ctx}/contract/changeApplyList" method="post" >
        <table class="table table-striped table-bordered table-condensed">
            <tr>
                <td>
                	<label class="lab">合同编号：</label>
                	<input type="text" name="contCode" disabled="disabled" id="contCode" value="${cd.cont_code}" class="cf_input_text178">
                </td>
                <td>
                	<label class="lab">合同版本：</label>
                	<input type="text" name="contVersion" disabled="disabled" id="contVersion" value="${cd.cont_version}" class="cf_input_text178">
                </td>
                <td>
	                <label class="lab">合同状态：</label>
	                <input type="text" name="dictContStatus" disabled="disabled" id="dictContStatus" value="${cd.dict_cont_status}" class="cf_input_text178">
	            </td>
            </tr>
            <tr>
                <td>
                	<label class="lab">入库日期：</label>
                	<input type="text" name="contIncomeDay" disabled="disabled" id="contIncomeDay" value="${cd.cont_income_day}" class="cf_input_text178">
               	</td>
                <td>
	                <label class="lab">申请人：</label>
	                <c:set value="${fns:getUserById(cd.apply_by)" var="contractApplier"></c:set>
	                <input type="text" name="applyBy" disabled="disabled" id="applyBy" value="${contractApplier==null ? cd.apply_by : contractApplier.name}" class="cf_input_text178">
                </td>
                <td>
	                <label class="lab">转入方：</label>
	                <input type="text" name="changeInStoresId" id="changeInStoresId" value="${cd.change_in_stores_id}" class="cf_input_text178">
                </td>
            </tr>
            <tr>
                <td>
	                <label class="lab">转出方：</label>
	                <input type="text" name="changeOutStoresId" disabled="disabled" id="changeOutStoresId" value="${cd.change_out_stores_id}" class="cf_input_text178">
                </td>
                <td>
	                <label class="lab">调拨日期：</label>
	                <input type="text" name="contTranferDay" disabled="disabled" id="contTranferDay" value="${cd.cont_tranfer_day}" pattern="yyyy-MM-dd" class="cf_input_text178">
                </td>
             </tr>
             <tr>
                <td>
	                <label class="lab">申请说明：</label>
	                <input type="text" name="applyExplain" disabled="disabled" id="applyExplain" value="${cd.apply_explain}" class="cf_input_text178">
                </td>
            </tr>
        </table>
     </form:form>
	 	<div class='tright pr30'>
	 		<input type="button" class='btn cf_btn-primary' value="提交" onclick="window.location.href='${ctx}/contract/distributeList';">
	 		<input type="button" class='btn cf_btn-primary' value="返回" onclick="window.history.back()">
	 	</div>
</body>
</html>