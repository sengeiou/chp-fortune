<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<sys:columnCtrl pageToken="trusteeship_change_list"></sys:columnCtrl>
<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
    <thead>
    <tr>
        <th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
        <th>客户姓名</th>
        <th>客户编号</th>
        <th>证件号码</th>
        <th>开户行</th>
        <th>开户省市</th>
        <th>开户区县</th>
        <th>开户支行名</th>
        <th>户名</th>
        <th>银行卡号</th>
        <th>变更类型</th>
        <th>变更状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
		<c:forEach items="${itemList}" var="item">
     <tr>
         <td><input type='checkbox' name="dcheckbox" value="${item.wobNum }" flowName ="${item.flowName }" stepName="${item.stepName }" token="${item.token }" applyId=${item.applyId } changerTypeVal=${item.changerTypeVal } /></td>
      
		 <!-- 客户姓名 -->
		 <!-- 屏蔽客户姓名/手机号/身份证号 -->
		 <td>***</td>
		 <%-- <td>${item.customerName }</td> --%>
         <!-- 客户编号 -->
         <td>${item.customerCode }</td>
         <!-- 证件号码 -->
         <td>***</td>
         <%-- <td>${item.custCertNum }</td> --%>
         <!-- 开户行 -->
         <td>${fns:dictName(dicts.tz_open_bank,item.accountBankId,'') }</td>
         <!-- 开户省市 -->
         <td>${fns:getLabel(item.accountAddrProvince) }</td>
         <!-- 开户区县 -->
         <td>${fns:getLabel(item.accountAddrCity)}</td>
          <!-- 开户支行 -->
         <td>${item.accountBranch }</td>
         <!-- 户名 -->
         <td>${item.accountName }</td>
          <!-- 银行卡号 -->
         <td>${item.accountNo }</td>
         <td>${item.changerTypeName }</td>
         <td>${item.changerState }</td>
       
         <td class="tcenter">
         	 <a href="javascript:void" onclick="failList('${item.applyId }')">留痕</a>
         </td>
     </tr>
 </c:forEach>
 </tbody>
</table>
<div class="pagination">${page}</div>
