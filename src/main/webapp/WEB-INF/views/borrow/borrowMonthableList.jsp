<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/borrow/borrowMonthable.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title></title>
<script type="text/javascript">
	function keydown(e) {
	    var e=e||event;  
	    if(e.keyCode==13){
	    	 $("#searchForm").submit();
	    }
	}
	
	window.onload=function(){
		var inputs = document.getElementsByTagName("input");
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].type=="text"){
				inputs[i].onkeydown=keydown;
			}
		}
	}
</script>
</head>
<body>
	<div class="body_r">
		<div class="box1 mb10">
		<form action="${ctx}/borrowMonthable/list" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="creditMonableIdList" name="creditMonableIdList" type="hidden"/>
			<input id="hiddenMoney" type="hidden" value="${fns:getFormatNumber(allMoney,'0.00')}"/>
			<input id="hiddenCount"  type="hidden" value="${page.count}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0"
				width="100%">
				<tr>
					<td><label class="lab">借款人：</label><input type="text" name="borrowerName" class="cf_input_text178" value="${borrowMonthableView.borrowerName }"></td>
					<td><label class="lab">可用金额：</label>
						<input type="text" name="borrowCreditValueFrom" id="borrowCreditValueFrom"
							value="${borrowMonthableView.borrowCreditValueFrom }" class="input_text70" number='1' greaterThan="0"> -
						<input type="text" name="borrowCreditValueTo" id="borrowCreditValueTo"
							value="${borrowMonthableView.borrowCreditValueTo }" class="input_text70"
							from-checkFloat = "#borrowCreditValueFrom" number='1' greaterThan="0">
					</td>
					<td><label class="lab">可用天数：</label>
						<input type="text" name="borrowDaysSurplusFrom" id="borrowDaysSurplusFrom"
							 value="${borrowMonthableView.borrowDaysSurplusFrom }" class="input_text70" digits='1' greaterThan="0" maxLength="9"> -
					    <input type="text" name="borrowDaysSurplusTo" 
					    	 value="${borrowMonthableView.borrowDaysSurplusTo }" class="input_text70"
					    	 from-checkInt = "#borrowDaysSurplusFrom" digits='1' greaterThan="0" maxLength="9">
				    </td>
				</tr>
				<tr>
				    <td><label class="lab">债权可用日期：</label>
				    <input type="text" name="borrowCreditDateUsableFrom" id="borrowCreditDateUsableFrom"
				    	value="${fns:getFormatDate(borrowMonthableView.borrowCreditDateUsableFrom,'yyyy-MM-dd')}"
				    	onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowCreditDateUsableTo\',{});}'})" class="input_text70 Wdate" > -
				    <input type="text" name="borrowCreditDateUsableTo" id="borrowCreditDateUsableTo"
				    	value="${fns:getFormatDate(borrowMonthableView.borrowCreditDateUsableTo,'yyyy-MM-dd')}"
				    	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowCreditDateUsableFrom\',{});}'})" class="input_text70 Wdate">
				    </td>
					<td><label class="lab">利率：</label>
						<input type="text" name="borrowMonthRate" value="${borrowMonthableView.borrowMonthRate}" class="cf_input_text178" number="1" greaterThan="0"></td>
					<td><label class="lab">首次还款日：</label>
						<input type="text" name="borrowBackmoneyFirdayFrom" id="borrowBackmoneyFirdayFrom" 
						value="${fns:getFormatDate(borrowMonthableView.borrowBackmoneyFirdayFrom,'yyyy-MM-dd')}"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowBackmoneyFirdayTo\',{});}'})" class="input_text70 Wdate" > -
					<input type="text" name="borrowBackmoneyFirdayTo" id="borrowBackmoneyFirdayTo"
						value="${fns:getFormatDate(borrowMonthableView.borrowBackmoneyFirdayTo,'yyyy-MM-dd')}"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowBackmoneyFirdayFrom\',{});}'})" class="input_text70 Wdate">
						 
					</td>
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">职业情况：</label>
						<select name="borrowerJob" class="select78 mr34" multiple="multiple" >
	                           <c:forEach items="${dicts.jk_prof_type }" var="item">
	                              <option value="${item.value }" <c:if test="${fns:multiOption(borrowMonthableView.borrowerJob,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                    </select>
                    </td>
					<td>
						<label class="lab">债权来源：</label>
						<select  name="borrowType" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_credit_src }" var="item">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(borrowMonthableView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                     </select>
	                </td>
	                <td>
						<label class="lab">债权区分：</label>
						<select name="dicLoanDistinguish" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_loan_distinguish}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowMonthableView.dicLoanDistinguish,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
				</tr>
				</table>
				<div class="tright pr30 pb5 pt10">
					<input type="button" value="搜索" id="search" class="btn cf_btn-primary"/>
					<input type="reset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"></div>	
			    </div>			
		   </form>				
		</div>
		<p class="mb10 ml10">
			<auth:hasPermission key="cf:borrowmonthable:exportexcel">
				<button class="btn btn_sc ml10" onclick="outExcel()">导出EXCEL</button>
			</auth:hasPermission>
			<auth:hasPermission key="cf:borrowmonthable:batchbacktool">
				<button class="btn btn_sc" onclick="borrowMonthableBatchBackTool()">批量回池</button>
			</auth:hasPermission>
			总金额：￥<span id="money">${fns:getFormatNumber(allMoney,'#,##0.00')}</span>元 总笔数：<span id="count">${page.count }</span>笔
		</p>
		<div class='box5 borrowMonthableListDiv' >
		<sys:columnCtrl pageToken="borrow_borrowMonthableList"></sys:columnCtrl>
		<div class='box5'>
			<table class="reportTable table table-striped table-bordered table-condensed borrowMonthableListTable data-list-table" width="100%">
				<thead>
					<tr>
						<th><input type="checkbox" class="checkAll" name='creditMonableId'/></th>
			            <th>序号</th>
			            <th>借款人</th>
			            <th>借款人身份证号</th>
			            <th>债权来源</th>
			            <th>借款用途</th>
			            <th>借款产品</th>
			            <th>职业情况</th>
			            <th>首次还款日</th>
			            <th>债权可用日期</th>
			            <th>借款天数</th>
			            <th>可用天数</th>
			            <th>截止还款日期</th>
			            <th>月利率</th>
			            <th>原始债权价值</th>
			            <th>可用债权价值</th>
			            <th>年预计债权收益</th>
			            <th>债权区分</th>
			            <th>操作</th>
			        </tr>
		        </thead>
	        <c:forEach items="${page.list }" var="item" varStatus="s">
	        <tr>
	         	<td id="creditMonableIdCheckbox">
	           		<input type="checkbox" name='creditMonableId' value="${item.creditMonableId}:${item.verTime}:${item.creditMonId}:${item.borrowMonthVerTime}" id="checkOne">
	          	</td>
	        	<td>${s.index+1}</td>
	        	<!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${item.loanName }</td> --%>
	            <td>***</td>
	            <%-- <td>${fns:formatLoanIdcard(item.loanIdcard)}</td> --%>
	            <td id="loanType">${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') }</td>
	            <td>${item.loanPurpose }</td>
	            <td>${item.loanProduct }</td>
	            <td>
	            	${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }
	            	<%-- <c:choose>
	            		<c:when test="${item.dictLoanType eq '1' }">
	            			抵押车
	            		</c:when>
	            		<c:otherwise>
	            		</c:otherwise>
	            	</c:choose> --%>
	            </td>
	            <td>${fns:getFormatDate(item.loanBackmoneyFirday,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(item.loanCreditDayUsable,'yyyy-MM-dd')}</td>
	            <td>${item.loanDay }</td>
	            <td>${item.loanAvailableDays }</td>
	            <td>${fns:getFormatDate(item.loanBackmoneyDay,'yyyy-MM-dd')}</td>
	            <td>${fns:formatNumber(item.loanMonthRate)}</td>
	            <td>${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}</td>
				<td id="loanAvailabeValue">${fns:getFormatNumber(item. loanAvailabeValue,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(item.loanValueYear,'0.00')}</td>
	            <td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
				<td>
					<auth:hasPermission key="cf:borrowmonthable:transact">
						<input type="button" value="办理" class="cf_btn_edit" onclick="goPage('${item.creditMonableId}')">
					</auth:hasPermission>
				</td>
			</tr>
	        </c:forEach>
		</table>
		</div>
	</div>
	<div class="pagination">${page}</div>
	  <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</body>
</html>