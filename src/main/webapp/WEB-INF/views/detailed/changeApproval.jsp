<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>状态变更审核</title>
<script src="${ctxWebInf}/js/contract/changeApproval.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.cookie.js"></script>
</head>
<body>
    <div class="box1 mb10">
	<form:form id="searchForm" modelAttribute="ch" method="post" >
           <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
             	<td>
                	<label class='lab'>申请门店：</label>
                	<input type="hidden" id="id" name="id" type="text" value="${ch.id}" class="cf_input_text178">
                	<input type="hidden" id="contCode" name="contCode" type="text"  value="${ch.contCode}" class="cf_input_text178">
                	<input type="hidden" id="dictChangeType" type="text"  value="${ch.dictChangeType}" class="cf_input_text178">
                	
                	<input type="text" id="name" name="name" type="text" disabled="disabled" value="${ch.name}" class="cf_input_text178">
                </td>
                <td>
	                <label class="lab">变更类型：</label>
	                <select class="select78" id="dictChangeType" name="dictChangeType" disabled="disabled" class="select78">
						<c:forEach items="${fns:getDictList('tz_change_type')}" var="item">
							 <option value="${item.value}" <c:if test="${ch.dictChangeType==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
                </td>
<!--                 <td> -->
<!-- 					<label>联系电话：</label> -->
<%-- 					<input id="applyTel" name="applyTel" type="text" disabled="disabled" value="${ch.applyTel}"> --%>
<!-- 				</td> -->
             	<td>
                	<label class='lab'>申请人：</label>
                	<c:set value="${fns:getUserById(ch.applyBy)" var="contractApplier"></c:set>
                	<input id="applyBy" name="applyBy" type="text" disabled="disabled" value="${contractApplier==null ? ch.applyBy : contractApplier.name}" class="cf_input_text178">
                </td>
              </tr>
<!--                 <td> -->
<!--                 	<label>联系人：</label> -->
<%--                 	<input id="applyContacts" name="applyContacts" type="text" disabled="disabled" value="${ch.applyContacts}"> --%>
<!--                 </td> -->
             <tr>
                <td>
					<label class='lab'>合同版本：</label>
					<select class="select78" id="contVersion" name="contVersion" disabled="disabled" value="${ch.contVersion}">
						<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
							 <option value="${item.value}" <c:if test="${ch.contVersion==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				   </select>
				</td>

             	<td>
                	<label class='lab'>审核状态：</label>
                	<select class="select78" id="dictChangeStatus" name="dictChangeStatus"  disabled="disabled" value="${ch.dictChangeStatus}">
						<c:forEach items="${fns:getDictList('tz_appaly_state')}" var="item">
							 <option value="${item.value}" <c:if test="${ch.dictChangeStatus==item.value}">selected</c:if>>${item.label}</option>
						</c:forEach>
				     </select>
                <c:if test="${ch.dictChangeType != 3}">
	                	<sys:attachment approval="true"></sys:attachment>
	                	<!-- <label class='lab'>下载附件：</label>
	                	<input class="btn btn_sc SunIAS" type="button" value="扫描控件" /></td> -->
	                </td>
                </c:if>
                <c:if test="${ch.dictChangeType == 3}">
	                <td>
	                	<label class='lab'>转借门店：</label>
	                	<input id="changeOutStoresId" name="changeOutStoresId" type="text" disabled="disabled" value="${ch.changeInName}" class="cf_input_text178">
	                </td>
                </c:if>
               </tr>
               <tr colspan='2'>
               <td>
                	<label class='lab'>合同编号：</label>
    
                	<input type="text" type="text" disabled="disabled" value="${ch.contCode}" class='cf_input_text178'>
                </td>
                <td>
                	<label class='lab'>申请说明：</label>
                	<input id="changeExplain" class='textarea_min' name="changeExplain" type="text" disabled="disabled" value="${ch.changeExplain}">
                </td>
                </tr>
                <tr colspan='3' class='mt10'>
                <td>
                	<label class='lab'><span class='red'>*</span>审核意见：</label>
                	<input id="changeCheckDesc" class='textarea_refuse' name="changeCheckDesc" type="text" value="${ch.changeCheckDesc}">
                </td>
                </tr>
                <tr colspan='3'>
                <td><label class='lab'>审核结果：</label>
                	<input id="changeCheckResult" name="changeCheckResult" value="1" type='radio'/>通过
                	<input id="changeCheckResult" name="changeCheckResult" type='radio' checked="checked" value="2"/>不通过
                </td>
            </tr>
        </table>
      </form:form>
      </div>
       <div class="tright pr30">	
           	 <input type="button" value="提交" class='btn cf_btn-primary' opt="changeApply">
           	 <input type="button" value="返回" class='btn cf_btn-primary' onclick="window.history.back()">        
    </div>
    <div id="scan" style="display:none;">
        <iframe name="scaniframe" width="99%" height=930 frameborder=0 scrolling=auto></iframe>
	 </div>
	
</body>
</html>