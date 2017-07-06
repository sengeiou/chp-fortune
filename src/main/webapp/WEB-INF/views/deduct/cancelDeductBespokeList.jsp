<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='box5 subDiv' id="borrowList">
    <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed" width="100%">
        <thead>
        <tr>
            <th><input type="checkbox" class="checkAll ml10 mr10" id="checkAll" name="checkboxName">全选</th>
            <th>出借编号</th>
            <th>客户姓名</th>
            <th>营业部</th>
            <th>出借产品</th>
            <th>付款方式</th>
            <th>划扣平台</th>
            <th>出借银行</th>
            <th>银行卡号</th>
            <th>计划出借日期</th>
            <th>计划划扣日期</th>
            <th>计划出借金额</th>
            <th>分天划扣金额</th>
			<th>划扣状态</th> 
			<th>预约日</th>
            <th>到期日期</th>
        </tr>
        </thead>
        <c:forEach items="${page.list }" var="deductPoolExt" varStatus="s">
	        <tr id = "theDaydeductListTr">
	            <td>
	            	<input type="checkbox" name="checkbox2" value="${deductPoolExt.id}_${deductPoolExt.verTime }" deductId="${deductPoolExt.deductApplyId}" lendcode="${deductPoolExt.applyCode }_${deductPoolExt.verTime }" id="checkOne">
	            	${s.index+1}
	            </td>
	            <td>${deductPoolExt.applyCode }</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <td>${deductPoolExt.custName }</td>
	            <td>${deductPoolExt.orgName }</td>
	            <td>${deductPoolExt.productName }</td>
				<td>${fns:dictName(dicts.tz_pay_type,deductPoolExt.applyPay,'-')}</td>
	            <td>${fns:dictName(dicts.tz_deduct_plat,deductPoolExt.dictApplyDeductType,'-')}</td>
	            <td>${fns:dictName(dicts.tz_open_bank,deductPoolExt.accountBank,'-')}</td>
	            <td>${deductPoolExt.accountNo }</td>
	            <td><fmt:formatDate value="${deductPoolExt.applyLendDate }" pattern="yyyy-MM-dd"/></td>
	            <td><fmt:formatDate value="${deductPoolExt.applyDeductDate }" pattern="yyyy-MM-dd"/></td>
	            <td><fmt:formatNumber value="${deductPoolExt.applyLendMoney }" type="currency" pattern="￥#,#00.00#"/></td>
	            <td><fmt:formatNumber value="${deductPoolExt.actualDeductMoney }" type="currency" pattern="￥#,#00.00#"/></td>
	            <td>${fns:dictName(dicts.tz_deduct_state,deductPoolExt.dictDeductStatus,'-')}</td>
	            <td>${fns:getFormatDate(deductPoolExt.bespokeDate,'yyyy-MM-dd')}</td>
	            <td><fmt:formatDate value="${deductPoolExt.applyExpireDate }" pattern="yyyy-MM-dd"/></td>
	        </tr>
        </c:forEach>
    </table>
    <div class="pagination">${page} 
<!-- <input type="button" value="取消预约" class="btn cf_btn-primary" /> -->
	</div>
	
</div>
