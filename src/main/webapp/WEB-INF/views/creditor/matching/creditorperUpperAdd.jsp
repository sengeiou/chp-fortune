<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/creditor/matching/creditorperUpperAdd.js" type="text/javascript"></script>
<title>新增匹配</title>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
      <form:form id="addForm">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
           <tr>
                <td>
                    <label class="lab">借款类型：</label>
                        <select class="select78" name="dictLoanType" select_required="1">
                           <c:forEach items="${loanTypeMap}" var="item"> 
		            				 <option value="${item.value }" >${item.key }</option>  
        							 </c:forEach>  
                         
                        </select>
                </td>
                <td>
                    <label class="lab">职业情况：</label>
                        <select class="select78" name="proofType" select_required="1">
                            <c:forEach items="${proMap}" var="item"> 
		            				 <option value="${item.value }" >${item.key }</option>  
        							 </c:forEach>  
                        </select>
                </td>
                <td>
                    <label class="lab">上限金额：</label>
                       <input type="text" class="cf_input_text178" id="upperMoney" name="upperMoney" value="" select_required="1" min="1" value="" maxlength="10"
                   onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                    >
                </td>
           </tr>
            <tr>
		                <td>
		                	<label class="lab"><span class="red">*</span>状态：</label>
		                	<select class="select78" name="useFlag" select_required="1">
		                		<option value="">请选择</option>
		                		<c:forEach items="${fns:getDictList('com_use_flag')}" var="item">
		                        	<option value="${item.value }" <c:if test="${item.value == entity.useFlag}">selected</c:if>>${item.label }</option>
		                        </c:forEach>
		                	</select>
		                </td>
		            </tr>		         	      
        </table>
       </form:form>
    </div>
    <div class="tright mt20 pr30">
            <input type="button" class="btn cf_btn-primary" id="submit" value="提交" />
            <input type="reset" class="btn cf_btn-primary" onClick="window.history.back(-1);" value="返回" />
        </div>
</div>
</body>
</html>