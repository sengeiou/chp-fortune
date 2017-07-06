<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form:form id="searchForm" commandName="workItem" action="${ctx}/donationApprove/dispatch" method="post" >	
		 <table class="table1">
            <tr>
                <td><label class="lab"><span class="red">*</span>营业部经理审批结果：</label><input type="radio" id="auditResult" required name="auditResult" value="1" />&nbsp;通过&nbsp;<input type="radio" id="auditResult" required name="auditResult" value="2" />&nbsp;不通过</td>
            </tr>
        </table>
        <div class="tright pr30">
			<shiro:hasPermission name="apply:consult:edit"></shiro:hasPermission>
			<input id="btnSubmit" class="btn cf_btn-primary" type="submit" value="提交"/>
	    </div>
	    <input type="hidden" value="${workItem.bv.CTManagerinfo.applyId}" name="applyId">
	    <input type="hidden" value="${workItem.bv.CTManagerinfo.custCode}" name="custCode">
	    <input type="hidden"  value="${workItem.flowCode}" name="flowCode"></input>
        <input type="hidden"  value="${workItem.flowName}" name="flowName"></input>
        <input type="hidden"  value="${workItem.flowId}" name="flowId"></input>
        <input type="hidden"  value="${workItem.stepName}" name="stepName"></input>
        <input type="hidden"  value="${workItem.wobNum}" name="wobNum"></input>
        <input type="hidden"  value="${workItem.token}" name="token"></input>
   </form:form>
   </div>
   </div>
</body>
