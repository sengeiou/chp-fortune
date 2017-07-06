<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
    <thead>
    <tr>
        <th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
        <th>产品</th>
        <th>初始出借日期</th>
        <th>初始出借金额</th>
        <th>月利率</th>
        <th>调整后债权收益率</th>
        <th>本期出借日期</th>
        <th>账单日</th>
        <th>账单类型</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.list}" var="rate" varStatus="s">
     <tr>
         <td><input type='checkbox' name="select[]" value="${rate.id }"/></td>
         <td>${fns:getProductLabel(rate.productCode)}</td>
         <td>${fns:getFormatDate(rate.applyLenddayLower,'yyyy-MM-dd')}~${fns:getFormatDate(rate.applyLenddayUpper,'yyyy-MM-dd')}</td>
         <td>${fns:getFormatNumber(rate.applyLendMoneyLower ,'￥#,##0.00')}~${fns:getFormatNumber(rate.applyLendMoneyUpper ,'￥#,##0.00')}</td>
         <td>${fns:getFormatNumber(rate.matchingRateLower ,'#,##0.#####')}%~${fns:getFormatNumber(rate.matchingRateUpper ,'#,##0.#####')}%</td>
         <td>${fns:getFormatNumber(rate.expectedYearRateLower ,'#,##0.#####')}%~${fns:getFormatNumber(rate.expectedYearRateUpper ,'#,##0.#####')}%</td>
         <td>${fns:getFormatDate(rate.matchingInterestStart,'yyyy-MM-dd')}</td>
          <td>${rate.matchingBillDay}</td>
         <td>${fns:dictName(dicts.tz_bill_state,rate.billType,'-')}</td>
         <td <c:if test="${fns:dictName(dicts.com_use_flag,rate.useFlag, '-')=='启用'}">style="font-weight: bold;color: black;"</c:if>>${fns:dictName(dicts.com_use_flag,rate.useFlag, '-')}</td>
         <td class="tcenter">
         	<auth:hasPermission key="cf:productrateconfig:transact">
	         	<c:choose>
					<c:when test="${rate.useFlag eq '0'}"><button class=' cf_btn_edit  btn_chgStatus' type="button">启用</button></c:when>
					<c:otherwise><button class='cf_btn_edit btn_chgStatus' type="button">停用</button></c:otherwise>
				</c:choose>
			</auth:hasPermission>
         </td>
     </tr>
 </c:forEach>
</table>
<div class="pagination">${page}</div>
