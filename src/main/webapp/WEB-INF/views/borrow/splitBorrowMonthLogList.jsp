<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
   <div class='box5'>
   <table id="contentTable" class="table table-striped table-bordered table-condensed">
    	<thead>
        <tr>
            <th>序号</th>
            <th>借款人</th>
            <th>可用金额</th>
            <th>原拆分金额</th>
            <th>分配时间</th>
            <th>月利率</th>
            <th>拆分利率</th>
            <th>操作人</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list }" var="item" varStatus="s">
        <tr>
        	<td>${s.index+1}</td>
        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
        	<td>***</td>
        	<%-- <td>${item.borrowerName }</td> --%>
        	<td>${item.afterMoney }</td>
        	<td>${item.beforMoney }</td>
            <td>${fns:getFormatDate(item.operateTime,'yyyy-MM-dd :HH:mm:dd')}</td>
        	<td>${fns:getFormatNumber(item.borrowMonthRate,'0.00')}</td>
        	<td>${fns:getFormatNumber(item.splitRate,'0.00')}</td>
			<td>${item.operator }</td>
		</tr>
        </c:forEach>
	</tbody>
	</table>
	</div>	
	<div class="pagination">${page}</div>
