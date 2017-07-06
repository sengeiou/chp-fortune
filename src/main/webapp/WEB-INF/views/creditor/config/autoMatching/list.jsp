<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
    <thead>
    <tr>
        <th><input type="checkbox" class=" ml10 mr10 checkAll">全选</th>
        <th>序号</th>
        <th>账单日</th>
        <th>账单类型</th>
        <th>营业部</th>
        <th>省份|城市</th>
        <th>产品</th>
        <th>本次待推荐总数</th>
        <th>已推荐条目</th>
        <th>剩余待推荐条</th>
        <th>推荐未通过条目</th>
        <th>进度</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.list}" var="config" varStatus="s">
     <tr>
         <td><input type='checkbox' name="select[]" value="${config.id }"/></td>
         <td>${s.index+1}</td>
         <td>${config.billDay}日</td>
         <td>${fns:dictName(dicts.tz_bill_state,config.billType,'-')}</td>
         <td>${config.businessDepartmentName}</td>
         <td>${fns:getProvinceLabel(config.provinceId)}|${fns:getCityLabel(config.cityId)}</td>
         <td>${fns:getProductLabel(config.productCode)}</td>
          <td>${config.inTotalNum}</td>
           <td>${config.toMatchingNum}</td>
            <td>${config.inTotalNum-config.toMatchingNum-config.notMatchingNum}</td>
            <td>${config.notMatchingNum}</td>
             <td>${config.progress}</td> 
         <td id="userFlag" <c:if test="${fns:dictName(dicts.com_use_flag,config.status, '-')=='启用'}">style="font-weight: bold;color: black;"</c:if>>${fns:dictName(dicts.com_use_flag,config.status, '-')}</td>
         <td class="tcenter">
         	<!-- <button class='cf_btn_edit btn_detail' type="button">编辑</button> -->
         	<auth:hasPermission key="cf:automacthingconfig:transact">
	         	<c:choose>
					<c:when test="${config.status eq '0'}"><button class=' cf_btn_edit btn_chgStatus' type="button">启用</button></c:when>
					<c:otherwise><button class='cf_btn_edit btn_chgStatus' type="button">停用</button></c:otherwise>
				</c:choose>
			</auth:hasPermission>
         </td>
     </tr>
 </c:forEach>
</table>
<div class="pagination">${page}</div>
