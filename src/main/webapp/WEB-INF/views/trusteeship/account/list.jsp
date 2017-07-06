<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<sys:columnCtrl pageToken="trusteeship_account_list"></sys:columnCtrl>
<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
    <thead>
    <tr>
        <th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
        <th>序号</th>
        <!-- <th>出借编号</th> -->
        <th>客户姓名</th>
        <th>客户编号</th>
        <th>证件号码</th>
        <th>开户行</th>
        <th>开户省市</th>
<!--         <th>开户区县</th> -->
        <th>开户支行</th>
        <th>户名</th>
        <th>银行卡号</th>
        <th>手机号码</th>
        <th>邮箱</th>
        <th>托管状态</th>
        <th>失败原因</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.list}" var="account" varStatus="s">
     <tr>
         <td><input type='checkbox' name="select[]" value="${account.customerCode };${account.lendCode };${account.verTime}"/></td>
         <td>${s.index+1}</td>
		 <!-- 出借编号 -->
		 <%-- <td>${account.lendCode }</td> --%>
		 <!-- 客户姓名 -->
		 <!-- 屏蔽客户姓名/手机号/身份证号 -->
		 <td>***</td>
		 <%-- <td>${account.customerName }</td> --%>
         <!-- 客户编号 -->
         <td>${account.customerCode }</td>
         <!-- 证件号码 -->
         <td>***</td>
         <%-- <td>${account.customerCertNum }</td> --%>
         <!-- 开户行 -->
         <td>${fns:dictName(dicts.tz_open_bank,account.bankId,'') }</td>
         <!-- 开户省市 -->
         <td>${account.addrprovince }|${account.addrcity }</td>
         <!-- 开户区县 -->
<%--          <td>${fns:getDistrictLabel(account.addrdistrict) }</td> --%>
         <!-- 开户支行 -->
         <td>${account.branch }</td>
         <!-- 户名 -->
         <td>${account.accountName }</td>
         <!-- 银行卡号 -->
         <td>${account.accountNo }</td>
         <!-- 手机号码 -->
         <td>***</td>
         <%-- <td>${account.mobilephone}</td> --%>
         <!-- 邮箱 -->
         <td>${account.email }</td>
         <!-- 托管状态 -->
         <td>${fns:dictName(dicts.tz_trust_state,account.applyHostedStatus,'-') }</td>
         <!-- 失败原因 -->
         <td>${account.trusteeshipRetCode }:${account.trusteeshipRetMsg }</td>
         <td class="tcenter">
         	<auth:hasPermission key="cf:trusteeshipcreate:edit">
         		<button class=' cf_btn_edit btn_chgStatus' type="button">修改</button>
         	</auth:hasPermission>
         	<%-- 
         	<auth:hasPermission key="cf:trusteeshipcreate:log">
         		<button class=' cf_btn_edit btn_log' type="button" onclick="fullMark('${account.lendCode}')">留痕</button>
         	</auth:hasPermission> 
         	--%>
         </td>
     </tr>
 </c:forEach>
</table>
<div class="pagination">${page}</div>
