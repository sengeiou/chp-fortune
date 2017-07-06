<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="matchingBorrowLookDiv" style="width:100%;">
	<div class="box1 mb10">
		<form action="${ctx}/matchingcreditor/getborrowMonthAbleDetailList" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="matchingId" name="matchingId" type="hidden" value="${matchingId}" class="noClear"/>
			<input id="creditMonableIds" name="creditMonableIds" type="hidden" value="${creditMonableIds}" class="noClear"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="lower" name="lower"  type="hidden" value="${lower}" class="noClear"/>
			<input id="upper" name="upper" type="hidden" value="${upper}" class="noClear"/>
			<input id="xsjylszq" name="xsjylszq" type="hidden" value="0" class="noClear"/>
			<input id="selectCreditMonableIds" name="selectCreditMonableIds" type="hidden"  class="noClear"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td><label class="lab">借款人：</label><input type="text" name="borrowerName" class="cf_input_text178" value="***"></td>
					<%-- <td><label class="lab">借款人：</label><input type="text" name="borrowerName" class="cf_input_text178" value="${borrowMonthableView.borrowerName }"></td> --%>
					<td><label class="lab">可用金额：</label>
					<input type="text" name="borrowCreditValueFrom" id="borrowCreditValueFrom" class="input_text70 noClear" value="${borrowMonthableView.borrowCreditValueFrom }" number="1" greaterThan="0" maxlength="10"> -
					 <input type="text" name="borrowCreditValueTo" id="borrowCreditValueTo" class="input_text70" value="${borrowMonthableView.borrowCreditValueTo }" number="1" greaterThan="0"  from-checkInt="#borrowCreditValueFrom"   maxlength="10" ></td>
					<td><label class="lab">可用天数：</label>
					<input type="text" name="borrowDaysSurplusFrom" id="borrowDaysSurplusFrom" class="input_text70 noClear" value="${borrowMonthableView.borrowDaysSurplusFrom }" number="1" greaterThan="0" maxlength="10"> -
					<input type="text" name="borrowDaysSurplusTo" id="borrowDaysSurplusTo" class="input_text70" value="${borrowMonthableView.borrowDaysSurplusTo }" number="1" greaterThan="0"  from-checkInt="#borrowDaysSurplusFrom"   maxlength="10" ></td>
				</tr>
				<tr>
				    <td><label class="lab">债权可用日期：</label><input type="text" name="borrowCreditDateUsableFrom" onfocus="WdatePicker()" class="Wdate input_text70" value="${borrowMonthableView.borrowCreditDateUsableFrom }"> -
				                                             <input type="text" name="borrowCreditDateUsableTo" class="Wdate input_text70" value="${borrowMonthableView.borrowDaysSurplusTo }" onfocus="WdatePicker()"></td>
					<td><label class="lab">利率：</label><input type="text" id="borrowMonthRate" name="borrowMonthRate"  class="cf_input_text178 noClear" value="<fmt:formatNumber value='${borrowMonthableView.borrowMonthRate }' pattern='#,##0.#####'/>" number="1"></td>
					<td><label class="lab">首次还款日：</label><input type="text" name="borrowBackmoneyFirdayFrom" id="borrowBackmoneyFirdayFrom" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowBackmoneyFirdayTo\')}'})" class="Wdate input_text70" value="${borrowMonthableView.borrowBackmoneyFirdayFrom }"> -
					                                        <input value="${borrowMonthableView.borrowBackmoneyFirdayTo }" type="text" id="borrowBackmoneyFirdayTo"  name="borrowBackmoneyFirdayTo"   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowBackmoneyFirdayFrom\')}'})" class="Wdate input_text70"></td>
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">职业情况：</label>
						<select name="borrowerJob" class="select78 mr34" multiple="multiple" value="${borrowMonthableView.borrowerJob }">
	                           <c:forEach items="${dicts.jk_prof_type}" var="item">
	                              <option value="${item.value }"><c:if test="${fns:multiOption(borrowMonthableView.borrowerJob,item.value)}">selected</c:if>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
					<td>
						<label class="lab">债权来源：</label>
						<select name="borrowType" class="select78 mr34">
						<option value="">请选择</option>
                           <c:forEach items="${dicts.tz_credit_src}" var="item">
						       <c:if test="${item.value!='0' }">
						         <option value="${item.value }" <c:if test="${fns:multiOption(borrowMonthableView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
						       </c:if>
                           </c:forEach>
					</select>
					</td>
					<td>
						<label class="lab">债权区分：</label>
						<select class="select78 mr34" id='dicLoanDistinguish' name='dicLoanDistinguish'>
						<option value="">请选择</option>
							<c:forEach items="${dicts.tz_loan_distinguish}" var='item'>
								<option value="${item.value }">${item.label }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				</table>
                <div class="tright pr30 pb5 pt10">
						<input type="button" value="搜索" class="btn cf_btn-primary matchingBorrowBut"/>
						 <input type="reset" value="清除" class="btn cf_btn-primary">
						<button class="btn cf_btn-primary back" type="button">选择带回</button>
						<div class="xiala"  id="showMore" onclick="javascript:show();"> </div>
			   </div>	
	           </div>
		 	</form>
		 	 <p class='ml10' style='text-align:left;'>
		已选记录数：<input type="text" value="0" id ="num" class="input_text50 num" readonly="readonly"/>&nbsp;
		已选债权总金额：<input type="text" value="0.00" class="input_text50 money" readonly="readonly"/>&nbsp;
		显示既有债权人：<input type="checkbox" class="borrowMonthClass"  onclick="checkHis(this)"/>
		  <div id="listDiv">
    		<%@ include file="/WEB-INF/views/creditor/matching/borrowMonthAbleDetailList.jsp"%>
    	</div>
</div>


