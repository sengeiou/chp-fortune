<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class='box4 subDiv' id="borrowList">
	<table cellspacing="0" cellpadding="0" border="0" id="borrowDetail" class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		<thead>
		<tr>
			<th><input type="checkbox" id="SelectAll" ><input id="checkid" name="checkid" type="hidden" value="${ids}"/></th>
			<th>序号</th>
			<th>借款人</th>
			<th>借款人身份证号</th>
			<th>债权来源</th>
			<th>债权标识</th>
			<th>借款类型</th>
			<th>职业情况</th>
			<th>首次还款日</th>
			<th>还款日</th>
			<th>借款期数</th>
			<th>可用期数</th>
			<th>截止还款日期</th>
			<th>月利率</th>
			<th>原始债权价值</th>
			<th>可用债权价值</th>
			<th>债权区分</th>
		
		</tr>
		</thead>
		<c:forEach items="${page.list }" var="borrow" varStatus="s">
			<tr>
				<td>
					<input id="creditValueId${s.index }" type="checkbox" name="creditValueId" value="${borrow.creditValueId }" class="table" ${fn:contains(id,borrow.creditValueId) ? 'checked="checked"' : ''}/>
				</td>
				<td>${s.index+1}</td>
				<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>***</td>
				<%-- <td>${borrow.loanName }</td> --%>
				<td>***</td>
				<%-- <td>${fns:formatLoanIdcard(borrow.loanIdcard)}</td> --%>
				<td>${fns:dictName(dicts.tz_credit_src,borrow.dictLoanType,'-') }</td>
				<td>${fns:dictName(dicts.tz_zjtr_mark,borrow.loanTrusteeFlag,'-') }</td>
				<td>${borrow.loanProduct }</td>
				  <td>${fns:dictName(dicts.jk_prof_type,borrow.loanJob,'-') }</td>
				<td><fmt:formatDate value="${borrow.loanBackmoneyFirday}" pattern="yyyy-MM-dd" /></td>
				<td>${fns:dictName(dicts.tz_repay_day,borrow.loanBackmoneyDay,'-') }</td>
				<td>${borrow.loanMonths }</td>
				<td>${borrow.loanMonthsSurplus }</td>
				<td><fmt:formatDate value="${borrow.loanBackmoneyLastday}" pattern="yyyy-MM-dd" /></td>
				<td>
				${fns:getFormatNumber(borrow.loanMonthRate,'#,##0.0####')}%
				<%-- <fmt:formatNumber value="${borrow.loanMonthRate }" pattern="#,##0.#####"/> --%>
				</td>
				<td>${fns:getFormatNumber(borrow.loanQuota ,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(borrow.loanCreditValue ,'￥#,##0.00')}</td>
				<td>${fns:dictName(dicts.tz_loan_distinguish,borrow.dicLoanDistinguish,'-') }</td>
				
			</tr>
		</c:forEach>
	</table>
		<div class="pagination sub_pagination">${page}</div>
</div>
