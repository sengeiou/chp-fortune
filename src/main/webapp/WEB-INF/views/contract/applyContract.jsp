<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同申请</title>
<script src="${ctxWebInf}/js/contract/applyContract.js" type="text/javascript" ></script>	
</head>
<body>
 <div class="box1 mb10">
	<form:form id="applyForm" modelAttribute="contractApply"  method="post">
	
		 <c:set value="${fns:getUserById(contractApply.applyBy)" var="contractApplier"></c:set>
		 <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
			    <td>
				<label class='lab'>申请门店：</label>
					<input type="text" id="orgName" htmlEscape="false" maxlength="50"  readonly="value" value="${contractApply.orgName}" class='cf_input_text178'/>
				</td>
				<td>
					<label class='lab'><span class="red">*</span>申请数量：</label>
					<input type="text" id="applyNo" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyNo" value="${contractApply.applyNo}" required digits="1"/>
				</td>
				<td>
					<label class='lab'>推荐数量：</label>
					<input type="text" id="applyDistNo" htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="value" name="applyDistNo" value="${contractApply.applyDistNo}"/>
					<!--上月使用*2-当前库存=推荐数量  -->
				</td>
		  </tr>
			<tr>
			    <td>
				<label class='lab'><span class="red">*</span>合同版本：</label>
				<select class="select78" id="contVersion" name="contVersion" select_required="1" opt="contversion">
				    <option value="">请选择</option>
					<c:forEach items="${fns:getDictList('tz_contract_vesion')}" var="item">
						<!-- 可申请的合同版本暂时为： 1.6.1/2.3，下拉框选项默认1.6.1 -->
						<c:if test="${fns:multiOption('10,14', item.value) }">
							<c:if test="${not empty contractApplyView.contVersion}">
							 <option value="${item.value}" <c:if test="${contractApplyView.contVersion==item.value}">selected</c:if>>${item.label}</option>
							</c:if>
							<c:if test="${empty contractApplyView.contVersion}">
							 <option value="${item.value}" <c:if test="${'14'==item.value}">selected</c:if>>${item.label}</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>
			    </td>
		        <td>
				<label class='lab'>上月使用：</label>
				<input type="text" id="applyAlreadyuse" htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="value" name="applyAlreadyuse" value="${contractApply.applyAlreadyuse}"/>
				<!-- -->
		        </td>
		        <td>
				<label class='lab'>现有库存：</label>
				<input type="text" id="applyInventory" htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="value" name="applyInventory" value="${contractApply.applyInventory}"/>
				<!-- -->
				</td>
            <tr>			
			<tr>
			    <td>
				<label class='lab'><span class="red">*</span>申请人：</label>
				<input type="text" id="applyBy" htmlEscape="false" maxlength="50" class="cf_input_text178" readonly="value" value="${contractApplier==null ? contractApply.applyBy : contractApplier.name}"/>
			    </td>
	            <td>
				<label class='lab'><span class="red">*</span>联系人：</label>
				<input type="text" id="applyContacts" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyContacts" value="${contractApply.applyContacts}" required/>
			    </td>
			    <td>
			    <!-- 屏蔽客户姓名/手机号/身份证号 -->
				<label class='lab'><span class="red">*</span>联系电话：</label>
				<input type="text" id="applyTel" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyTel" value="***" required digits="1"/>
				<%-- <input type="text" id="applyTel" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyTel" value="${contractApply.applyTel}" required digits="1"/> --%>
		        </td>
		     </tr>
			<tr>
			    <td>
				<label class='lab'><span class="red">*</span>收货地址：</label>
				<input type="text" id="applyShippingAddr" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyShippingAddr" value="${contractApply.applyShippingAddr}" required/>
		        </td>	
			    <td>
				<label class='lab'><span class="red">*</span>申请说明：</label>
				<input type="text" id="applyExplain" htmlEscape="false" maxlength="50" class="cf_input_text178" name="applyExplain" required/>
		        </td>
		     </tr>
		     </table>
	 </form:form>
	 </div>
	 <div class=" tright pr30">
			<input type="button" value="提交" opt="save" class='btn cf_btn-primary'/>
			<input type="button" value="取消" opt="clean" class='btn cf_btn-primary' onclick="history.go(-1)"/>
		</div>
</body>
</html>