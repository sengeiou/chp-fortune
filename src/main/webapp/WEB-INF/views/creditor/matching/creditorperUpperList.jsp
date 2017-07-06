<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxWebInf}/js/creditor/matching/creditorperUpperList.js" type="text/javascript"></script>
<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
    <thead>
    <tr>
        <th><input type="checkbox"  id="checkAll" onclick="selectAll()" class=" ml10 mr10 checkAll">全选</th>
        <th>序号</th>
        <th>借款类型</th>
        <th>职业类型</th>
        <th>上限金额</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.list}" var="item" varStatus="s">
     <tr>
         <td><input type='checkbox' name="select[]" value="${item.id }"/></td>
         <td>${s.index+1}</td>
         <td>${item.dictLoanTypeStr}</td>
         <td>${item.proofTypeStr}</td>
         <td>${fns:getFormatNumber(item.upperMoney,'#,#00.00#')}</td>
         <td <c:if test="${fns:dictName(dicts.com_use_flag,item.useFlag, '-')=='启用'}">style="font-weight: bold;color: black;"</c:if>>${fns:dictName(dicts.com_use_flag,item.useFlag, '-')}</td>
         <td class="tcenter">
         	
	         	<c:choose>
					<c:when test="${item.useFlag eq '0'}"><button class=' cf_btn_edit  btn_chgStatus' type="button">启用</button></c:when>
					<c:otherwise><button class='cf_btn_edit btn_chgStatus' type="button">停用</button></c:otherwise>
				</c:choose>
			
         </td>
          
     </tr>
 </c:forEach>
</table>
<div class="pagination">${page}</div>
