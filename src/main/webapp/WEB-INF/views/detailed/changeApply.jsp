<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>变更申请</title>
<script src="${ctxWebInf}/js/contract/changeApply.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.cookie.js"></script>
</head>
<body>
   <div class="box1 mb10">
   <form:form id="searchForm" modelAttribute="cf" action="${ctx}/contract/changeApplyList" method="post" >
   		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
   			<tr>
                <td>
	                <label class="lab">合同编号：</label>
	                <input type="hidden" name="contCode" id="contCode" value="${cf.contCode}" class="cf_input_text178">
	                <input type="text" name="contCode" disabled="disabled" id="contCode" value="${cf.contCode}" class="cf_input_text178">
                </td>
                <td>
	                <label class="lab">合同版本：</label>
	                <select id="contVersion" name="contVersion"  disabled="disabled" value="${cf.contVersion}" class="cf_input_text178">
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${cf.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
                </td>
				<td>
					<label class='lab'>合同状态：</label>
					<select id="dictContStatus" name="dictContStatus" disabled="disabled" class="cf_input_text178">
						<c:forEach items="${fns:getDictList('tz_contract_state')}" var="item">
							 <option value="${item.value}" <c:if test="${cf.dictContStatus==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
				</td>
          </tr>
            <tr>
				<td>
					<label class="lab">所属门店：</label>
					<input type="hidden" name="contStoresId" disabled="disabled" id="contStoresId" value="${cf.contStoresId}" class="cf_input_text178">
					<input type="text" name="name" disabled="disabled" id="name" value="${cf.name}" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">入库日期：</label>
					<input type="text" name="contIncomeDay" class="cf_input_text178" disabled="disabled" id="contIncomeDay" value="<fmt:formatDate value="${cf.contIncomeDay}" pattern="yyyy-MM-dd"/>">
				</td>
				<td>
					<label class="lab">申请人：</label>
					<input type="text" name="changeApply" disabled="disabled" id="changeApply" value="${cf.changeApply}" class="cf_input_text178">
				</td>
          </tr>
            <tr>
                <td>
	                <label class="lab"><span class="red">*</span>变更类型：</label> 
	                <select id="dictChangeType" name="dictChangeType" select_required="1" class="cf_input_text178">
	                    <option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_change_type')}" var="item">
							<c:if test="${item.value==2}">
								<option value="${item.value}" <c:if test="${cf.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
							</c:if>
						</c:forEach>
				   </select>
                </td>
           </tr>
             <tr>
                <td style="display:none" id="financial_li">
	                <label class="lab">理财经理：</label>
	                <input class="cf_input_text178" type="text" value="${fns:getUserById(cf.contBelongEmpid).name}" readonly="readonly">
             	 	<input name="contBelongEmpid" type="hidden" value="${cf.contBelongEmpid}">
                </td>
                <%-- <td style="display:none" id="file_li">
	               <!--  <label class="lab">上传文件：</label>
	                <input class="btn btn_sc SunIAS" type="button" value="扫描控件" /></td> -->
	                <sys:attachment></sys:attachment>
                </td> --%>
                <td style="display:none" id="store_li">
					<sys:orgTree id="org" name="changeInStoresId" value="${deductPoolExt.checkNode}" labelName="changeInStoresId_label"  labelValue="${deductPoolExt.id}"/>
                </td>
          </tr>
             <tr>
                <td colspan="3">
	                <label class="lab"><span class="red">*</span>申请说明：</label>
	                <input type="text" name="changeExplain" class="cf_input_text178" id="changeExplain" value="${cf.changeExplain}" required>
                </td>
          </tr>
          <tr>
          		<td style="display:none" id="file_li"  colspan="4">
	                <sys:attachment></sys:attachment>
                </td>
          </tr>
        </table>
    </form:form>
    </div>
	 	<div class="tright pr30">
	 		<input type="button" value="提交" opt="changeApply" class='btn cf_btn-primary'>
	 		<input type="button" value="返回" onclick="window.history.back()" class='btn cf_btn-primary'>
	 	</div>
	
	<div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn cf_btn-primary" id="subClose"></button>
				</div>
			</div>
		</div>
	</div>
	
	 <div id="scan" style="display:none;">
        <iframe name="scaniframe" width="99%" height=930 frameborder=0 scrolling=auto></iframe>
	 </div>
	 
</body>
</html>