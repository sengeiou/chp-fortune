<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script src="${ctxWebInf }/js/customer/customerTransfer.js" type="text/javascript"></script>
<div class="body_r">
    <div class="box1 mb10">
    <form:form id="searchForm1" modelAttribute="query" action="${ctx}/customerTransfer/searchCustomerManager" method="post" >
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">理财经理工号：</label>
                	<input type="text" id="managerCode"  name="managerCode" value="${query.managerCode}"   class="cf_input_text178 managerCode" />
                </td>
                <td>
                	<label class="lab">理财经理姓名：</label>
                	<input type="text" id="managerName" name="managerName" value="${query.managerName}"   class="cf_input_text178 managerName" />
                </td>
            </tr>
            <!-- <tr>
                <td>
                	<label class="lab">所属营业部：</label>
				<sys:orgTree id="org"  name="departmentId" value="${query.departmentId}" labelName="departmentName"  labelValue="${query.departmentName}" />
                </td>
                <td></td>
            </tr> -->
			</table>
			<div class="tright pr30" >
				<input id="btnSubmit" class="btn cf_btn-primary subSearch" type="button" value="搜索" />
			    <input  class="btn cf_btn-primary" type="reset" value="清除" />
	        </div>
	        </form:form>
	        </div>
    <sys:message content="${message}"></sys:message>
     <form:form id="searchForm" modelAttribute="workItem" action="${ctx}/customerTransfer/launchFlow" method="post" >
    <div class='box5 listDiv'>
    	<%@ include file="/WEB-INF/views/donation/customerTransferLaunchSub.jsp"%>
    </div>
    <input type="hidden" value="${workItem.bv.CTManagerinfo.managerName}" name="CTManagerinfo.managerName">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.custName}" name="CTManagerinfo.custName">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.custCode}" name="CTManagerinfo.custCode">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.managerCode}" name="CTManagerinfo.managerCode">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.departmentName}" name="CTManagerinfo.departmentName">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.oldmanagerId}" name="CTManagerinfo.oldmanagerId">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.olddepartmentId}" name="CTManagerinfo.olddepartmentId">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.branchCompanyId}" name="CTManagerinfo.branchCompanyId">
    <input type="hidden" value="${workItem.bv.CTManagerinfo.districtCompanyId}" name="CTManagerinfo.districtCompanyId">
    <input type="hidden"  value="${workItem.flowCode}" name="flowCode"></input>
    <input type="hidden"  value="${workItem.flowName}" name="flowName"></input>
    <input type="hidden"  value="${workItem.stepName}" name="stepName"></input>
    <input type="hidden"  value="${workItem.flowType}" name="flowType"></input>
    </form:form>
    </div>