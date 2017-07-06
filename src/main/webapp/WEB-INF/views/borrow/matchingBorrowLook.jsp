<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="matchingBorrowLookDiv" style="width:100%;">
  <div class="box1 mb10">
    <form action="${ctx}/borrowCancel/matchingBorrowLook" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="matchingId" name="matchingId" type="hidden" value="${matchingBorrowLookView.matchingId }"/>
			<input id="lendCode" name="lendCode" type="hidden" value="${matchingBorrowLookView.lendCode }"/>
			<input id="creditValueIds" name="creditValueIds" type="hidden" value="${matchingBorrowLookView.creditValueIds }" />
			<input id="productMatchingRateUpper" type="hidden" value="${productMatchingRateUpper }">
			<input id="productMatchingRateLower" type="hidden" value="${productMatchingRateLower }">
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
	                <label class="lab">待匹配金额：</label>
	                <input readonly="readonly" type="text" class="cf_input_text178 creditLines" name="creditLines" value="${matchingBorrowLookView.creditLines }">
                </td>
                <td>
	                <label class="lab">计划出借日期：</label>
	                <input readonly="readonly" type="text" name = "lendDate" class="cf_input_text178" value="${fns:getFormatDate(matchingBorrowLookView.lendDate,'yyyy-MM-dd')}">
                </td>
                <td>
	                <label class="lab">产品类型：</label>
	                <input readonly="readonly" type="text" class="cf_input_text178" name = "procuctName" value="${matchingBorrowLookView.procuctName}">
                </td>
            </tr>
            <tr>
                <td>
                	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                	<label class="lab">借款人：</label>
                	<input type="text" class="cf_input_text178" name="borrowerName" value="***">
                	<%-- <input type="text" class="cf_input_text178" name="borrowerName" value="${matchingBorrowLookView.borrowerName}"> --%>
                </td>
                <td>
                	<label class="lab">可用期数：</label>
                	<input type="text" class="input_text70" name="borrowDaysSurplusFrom" value="${matchingBorrowLookView .borrowDaysSurplusFrom}"> -
                	<input type="text" class="input_text70" name="borrowDaysSurplusTo" value="${matchingBorrowLookView. borrowDaysSurplusTo}">
                </td>
               <td>
				<label class="lab">债权来源：</label>
				<select  name="borrowType" class="select78 mr34" multiple="multiple">
                    <c:forEach items="${dicts.tz_credit_src }" var="item">
                    	<c:if test="${item.value!='1' }">
                    		<c:choose> 
                    		 	<c:when  test="${matchingBorrowLookView.borrowType == '0' || matchingBorrowLookView.borrowType == '2'}" >
                    	      	 	<option value="${item.value }" <c:if test="${fns:multiOption(matchingBorrowLookView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
                    			</c:when> 
                    			<c:otherwise>
                    				<option value="${item.value }" <c:if test="${fns:multiOption(matchingBorrowLookView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
                    			</c:otherwise> 
                    		</c:choose> 
                    	</c:if>
                    </c:forEach>
                  </select>
	          </td>
            </tr>
            <tr id="T1" style="display:none">
                <td>
                    <label class="lab">月利率：</label>
                	<input type="text" class="cf_input_text178" name="borrowMonthRate" value="${fns:getFormatNumber(matchingBorrowLookView.borrowMonthRate,'0.00') }">
                </td>
                <td>
                <label class="lab">可用债权价值：</label>
                	<input type="text" class="input_text70" name="borrowCreditValueFrom" value="${matchingBorrowLookView.borrowCreditValueFrom }"> -
                	<input type="text" class="input_text70" name="borrowCreditValueTo" value="${matchingBorrowLookView.borrowCreditValueTo }">
                </td>
                <td>
                	<label class="lab">还款末期日期：</label>
                	<input type="text" class="cf_input_text178" name="loanBackmoneyDay" onfocus="WdatePicker()" value="<fmt:formatDate value='${matchingBorrowLookView.loanBackmoneyDay }' pattern='yyyy-MM-dd' />"> 
                </td>
            </tr>
            </table>	
				<div class='tright pr30 pb5 pt10'>
				    <input type="button" class="btn cf_btn-primary matchingBorrowBut"  value="搜索">
					<button type="button" class="btn cf_btn-primary leadBackBut" >选择带回</button>
					<div class="xiala"  id="showMore" onclick="javascript:show();"></div>
				</div>
        </form>
    </div>
	<p class='ml10' style='text-align:left;'>
		已选记录数：<input type="text" id="subBorrowNum" value="0" class="input_text50"/>&nbsp;
		已选债权总金额：<input type="text" id="subBorrowMoney" value="0.00" class="input_text50"/>&nbsp;
		显示既有债权人：<input type="checkbox" class="borrowClass"/>
    </p>
    <div id="listDiv">
    	<%@ include file="/WEB-INF/views/borrow/matchingBorrowLookList.jsp"%>
    </div>
</div>	
