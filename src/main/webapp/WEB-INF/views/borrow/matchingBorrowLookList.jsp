<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='box5 subDiv'>
    <table class='table table-striped table-bordered table-condensed table2 subTable data-list-table' width="100%">
        <thead>
        <tr>
            <th><input type="checkbox" class="checkAll"/></th>
            <th>序号</th>
            <th>借款人姓名</th>
            <th>债权标识</th>
            <th>中间人</th>
            <th>还款起始日期</th>
            <th>还款末期日期</th>
            <th>还款期数</th>
            <th>可用期数</th>
            <th>借款用途</th>
            <th>债权来源</th>
            <th>职业情况</th>
            <th>月利率</th>
            <th>原始债权价值</th>
            <th>可推荐债权价值</th>
        </tr>
        </thead>
        <c:forEach items="${page.list }" var="item" varStatus="s">
				<tr>
					<input type="hidden" value="${creditValueIds }" class="creditValueIds"/>
					<td><input type="checkbox" id="subCheckOne" name="creditValueId" value="${item.creditValueId }" /></td>
					<td>${s.index+1}</td>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
					<%-- <td>${item.loanName }</td> --%>
					<td>${fns:dictName(dicts.tz_zjtr_mark,item.loanTrusteeFlag,'-') }</td>
					<td>${item.loanMiddleMan }</td>
					<td>${fns:getFormatDate(item.loanBackmoneyFirday,'yyyy-MM-dd')}</td>
					<td>${fns:getFormatDate(item.loanBackmoneyLastday,'yyyy-MM-dd')}</td>
					<td>${item.loanMonths }</td>
					<td>${item.loanMonthsSurplus }</td>
					<td>${item.loanPurpose }</td>
					<td>${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') }</td>
					<td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }</td>
					<td>${fns:getFormatNumber(item.loanMonthRate,'0.00')}</td>
					<td>${fns:getFormatNumber(item.loanQuota,'0.00')}</td>
					<td id="loanCreditValue">${fns:getFormatNumber(item.loanCreditValue,'0.00')}</td>
				</tr>
			</c:forEach>
    </table>
    </div>
<div class="pagination">${page } </div>
