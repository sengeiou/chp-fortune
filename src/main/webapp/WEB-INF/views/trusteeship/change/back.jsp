<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<div class="body_r">
    <form:form id="backForm">
    <div class="box1 mb10">
        <table class="table1">
            <tr>
                <td style="width:50%;"><label class="lab"><span class="red">*</span>退回原因：</label><textarea class="textarea_refuse" id="auditCheckExamine"  name="auditCheckExamine"></textarea></td>
            </tr>
        </table>
    </div>
	<div class="tright pr30">
			<shiro:hasPermission name="apply:consult:edit"></shiro:hasPermission>
			<input id="btnSubmit" class="btn cf_btn-primary" type="button" onclick="backSub()" value="提交"/>
	</div>
</form:form>
</div>
