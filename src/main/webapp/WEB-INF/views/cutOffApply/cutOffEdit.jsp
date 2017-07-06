<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    	<form:form id="formStoresMemberSave" action="${ctx}/cutOff/edit">
    		 <input type="hidden" value="${cutOff.id }" name="id">
    		 <div class='box1 mb10'>
    		 <table class="table1">
    		    <tr>
    		    <td colspan='3'>
				<label class="lab"><span class='red'>*</span>截单时间：</label>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="cf_input_text178"
				value="${cutOff.endTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});"/>
				</td>
			    </tr>
			    <tr>
			    <td colspan='3'>
	             <label class="lab"><span class='red'>*</span>状态：</label>
	                	<select class="select78" name='delFlag' select_required="1">
	                		<option value="">请选择</option>
	                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
	                        	<option value="${item.value }" <c:if test="${cutOff.delFlag==item.value}">selected</c:if>>${item.label }</option>
	                        </c:forEach>
	                	</select>
	              </td>
	          </tr>
			<tr>
			<td colspan='3'>
			<label class='lab'>门店：</label>
			<input type="text" value="${ cutOff.orgName}" readonly="true" class='cf_input_text178'>
			</td>
			</tr>
			</table>
			</div>
				<div class="tright pr30">
					<input id="btnSaveSubmit" class="btn cf_btn-primary" type="submit" value="保 存"/>
				</div>
		 </form:form>

