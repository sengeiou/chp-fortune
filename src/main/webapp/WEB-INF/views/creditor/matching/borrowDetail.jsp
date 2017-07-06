<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxWebInf }/js/creditor/matching/borrowDetail.js" type="text/javascript"></script>

<div >
	<div class="box1 mb10" >
		<form action="${ctx}/matchingcreditor/getBorrowDetailList" method="post" id="searchForm">
		
			<input id="matchingId" name="matchingId" value="${matchingId }" type="hidden" class="noClear">
			<input name="creditValueIds" value="${creditValueIds }" type="hidden" class="noClear">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" class="noClear"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" class="noClear"/>
			<input id="lower" name="lower" type="hidden" value="${lower}" class="noClear"/>
			<input id="upper" name="upper" type="hidden" value="${upper}" class="noClear"/>
			<input id="xsjylszq" name="xsjylszq" type="hidden" value="0" class="noClear"/>
			<input id="idsTmp" name="idsTmp" type="hidden" value="" class="noClear"/>
			<input id="yxzz" name="yxzz" type="hidden" value="${yxzz}" />
			<input id="yxzsums" name="yxzsums" type="hidden" value="${yxzsums}" />
			<input id="selectCreditValueId" name="selectCreditValueId"  value="" type="hidden"  class="noClear"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0"  width="100%">
				<tr>
					<td>
						<label class="lab">本期推荐金额：</label><input type="text" name="matchingBorrowQuota" value="${fns:getFormatNumber(mc.matchingBorrowQuota ,'#,##0.00')}" class="cf_input_text178 noClear" value="disabled" disabled />
					</td>
					<td><label class="lab">出借产品：</label><input type="text" name="productName" value="${mc.productName }" class="cf_input_text178 noClear" value="disabled" disabled /></td>
					<td><label class="lab">账单日：</label><input type="text" name="matchingBillDay" value="${mc.matchingBillDay }" class="cf_input_text178 noClear" value="disabled" disabled /></td>
				</tr>
				<tr>
					<td>
						<label class="lab">债权标识：</label> 
						<select class="select78 mr34" id='loanTrusteeFlag' name='loanTrusteeFlag'>
										<option value="">请选择</option>
										<c:forEach items="${dicts.tz_zjtr_mark}" var='item'>
											<option value="${item.value }">
												${item.label }
											</option>
										</c:forEach>
						</select>
					</td>
					<%-- <td><label class="lab">借款人：</label><input type="text" name="loanName" value="${borrow.loanName }" class="cf_input_text178"></td> --%>
					<td><label class="lab">可用金额：</label><input type="text" id="loanCreditValueFrom" name="loanCreditValueFrom" value="${borrow.loanCreditValueFrom }" class="input_text70 noClear"  number="1" greaterThan="0" maxlength="10"> -
                    	<input type="text" id="loanCreditValueTo" name="loanCreditValueTo" value="${borrow.loanCreditValueTo }" class="input_text70"   number="1" greaterThan="0"  from-checkInt="#loanCreditValueFrom"   maxlength="10"/>
                    </td>
					<td><label class="lab">可用期数：</label><input type="text" id="loanMonthsSurplusFrom" name="loanMonthsSurplusFrom" value="${borrow.loanMonthsSurplusFrom }" class="input_text70 noClear"   number="1" greaterThan="0" maxlength="10"> -
                		<input type="text" id ="loanMonthsSurplusTo" name="loanMonthsSurplusTo" value="${borrow.loanMonthsSurplusTo }" class="input_text70" number="1" greaterThan="0"  from-checkInt="#loanMonthsSurplusFrom"   maxlength="10" >
                	</td>
				</tr>
				<tr>
					<td>
							<label class="lab">债权区分：</label> 
							<select class="select78 mr34" id='dicLoanDistinguish' name='dicLoanDistinguish'>
									<option value="">请选择</option>
									<c:forEach items="${dicts.tz_loan_distinguish}" var='item'>
										<option value="${item.value }">
											${item.label }
										</option>
									</c:forEach>
							</select>
					</td>
					<td><label class="lab">利率：</label><input type="text" id="loanMonthRate" name="loanMonthRate" value="<fmt:formatNumber value='${borrow.loanMonthRate }' pattern='#,##0.#####'/>" class="noClear cf_input_text178" number="1"></td>
					<td>
						<label class="lab">债权来源：</label>
						<select  name="dictLoanType" class="select78 mr34" >
								<option value="">请选择</option>
	                           <c:forEach items="${dicts.tz_credit_src}" var="item">
	                           	<c:if test="${item.value!='1' }">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(borrow.dictLoanType,item.value)}">selected</c:if>>${item.label }</option>
	                           	</c:if>
	                           </c:forEach>
	                     </select>
					</td> 
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">职业情况：</label> 
						<select class="select78 mr34"
								id='loanJob' name='loanJob'>
									<option value="">请选择</option>
									<c:forEach items="${dicts.jk_prof_type}" var='item'>
										<option value="${item.value }">
											${item.label }
										</option>
									</c:forEach>
						</select>
					</td>
					<td><label class="lab">还款日：</label>
						 <select class="select78 mr34"
							id='loanBackmoneyDay' name="loanBackmoneyDay">
								<option value="">请选择</option>
								<c:forEach items="${creditorConfigList}" var='item' >
									<option value="${item.configRepayDay }">
										${item.configRepayDay }
									</option>
								</c:forEach>
						</select>
					</td>
					<td><label class="lab">首次还款日：</label><input type="text" id="loanBackmoneyFirdayFrom"  name="loanBackmoneyFirdayFrom" value="${borrow.loanBackmoneyFirdayFrom }" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanBackmoneyFirdayTo\')}'})" class="Wdate input_text70"> -
					                                        <input type="text" id="loanBackmoneyFirdayTo" name=loanBackmoneyFirdayTo value="${borrow.loanBackmoneyFirdayTo }" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'loanBackmoneyFirdayFrom\')}'})" class="Wdate input_text70">
					</td>
					</tr>
					<tr id='T1' style='display:none;'>
						<td><label class="lab">借款人：</label><input type="text" name="loanName" value="${borrow.loanName }" class="cf_input_text178"></td>
					</tr>					
				   </table>
				<div class="tright pr30 pb5 pt10">
							<input type="button" value="搜索" class="btn cf_btn-primary BorrowDetailBut" id="search"/>
							 <input type="reset" value="清除" class="btn cf_btn-primary">
							<button class="btn cf_btn-primary mr10 back" type="button" >选择带回</button>
							 <div class="xiala"  id="showMore" onclick="javascript:show();"> </div>
	            </div>
	             </form>
	             </div>
	             <p class='ml10' style='text-align:left;'>
					已选记录数：<input type="text" value="0" id="yxz" class="input_text50 num" readonly="readonly"/>&nbsp;
					已选债权总金额：<input type="text" value="0.00" id="yxzsum" class="input_text50 money" readonly="readonly"/>&nbsp;
					显示既有债权人：<input type="checkbox" class="borrowClass"  onclick="checkHis(this)"/>
   				 </p>
		 <div id="listDiv">
	    	<%@ include file="/WEB-INF/views/creditor/matching/borrowDetailList.jsp"%>
	    </div>
