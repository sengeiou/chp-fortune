<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<table id="showSelectLendTable" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th style="display: none;">操作</th>
			<th>选择</th>
	  		<th>序号</th>
	  		<th>出借姓名</th>
	  		<th>出借编号</th>
	  		<th>出借方式</th>
	  		<th>出借金额</th>
	  		<th>出借日期</th>
	  		<th>到期日期</th>
	  		<th>付款方式</th>
	  		<th>内转前实际回款金额</th>
	  		<th style="display: none;">本次内转金额</th>
	  		<th style="display: none;">内转后实际回款金额</th>
	  		<th>出借状态</th>
	  		<th>回款状态</th>
	  		<th>回款类型</th>
  		<tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(loanApplys)>0 }">
			<c:forEach items="${loanApplys}" var="item" varStatus="vst">
				<tr>
					<td style="display: none;"><a href="#">移除</a></td>
					<td><input type="checkbox"></td>
					<td>${vst.index+1}</td>
					<td>${item.custName }</td>
					<td>${item.applyCode }</td>
					<td>
						${fns:getProductLabel(item.productCode) }
					</td>
					<td>
						<fmt:formatNumber value="${item.lendMoney }" pattern="#,##0.00"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatDate value="${item.lendDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<fmt:formatDate value="${item.expireDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }
					</td>
					<td>
						<fmt:formatNumber value="${item.actualBackMoney }" pattern="#,##0.00"></fmt:formatNumber>
					</td>
					<%-- 可用内转金额 --%>
					<td style="display: none;">
						<fmt:formatNumber value="${item.validateMoney}" pattern="#,##0.00"></fmt:formatNumber>
					</td>
					<td style="display: none;">
						<input type="text" value="0" onkeyup="javascript:this.value=this.value.replace(/[^\d]|^0*/g,'');if(this.value==''){this.value='0'}">
					</td>
					<td style="display: none;">
						<fmt:formatNumber value="${item.actualBackMoney }" pattern="#,##0.00"></fmt:formatNumber>
					</td>
					<td>
						${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }
					</td>
					<td>
						${fns:dictName(dicts.tz_back_state,item.backMoneyStatus,'-') }
					</td>
					<td backType='${item.backMoneyType}'>
						${fns:dictName(dicts.tz_back_type,item.backMoneyType,'-') }
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>