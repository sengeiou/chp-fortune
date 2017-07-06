<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxWebInf }/js/borrow/pushBorrow.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<meta name="decorator" content="default" />
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
		<div class="box1 mb10 body_r">
		<form action="${ctx}/borrow/pushBorrowList" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="creditValueIdList" name="creditValueIdList" type="hidden"/>
			<input id="hiddenMoney" type="hidden" value="${fns:getFormatNumber(allMoney,'0.00')}"/>
			<input id="hiddenCount"  type="hidden" value="${page.count}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			
			<input id="pushHiddenMoney" name ="pushHiddenMoney"  type="hidden" />
			<input id="pushHiddenMoneyOneByOne" name = "pushHiddenMoneyOneByOne"  type="hidden"/>
			<input id="pushHiddenCount"  name = "pushHiddenCount" type="hidden"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td><label class="lab">借款人：</label>
						<input type="text" name="borrowerName" class="cf_input_text178" value="${borrowView.borrowerName }"></td>
						<td><label class="lab">推送金额：</label> 
							<input type="text" name="borrowCreditValueFrom" class="input_text70" id = "borrowCreditValueFrom"
								value='${borrowView.borrowCreditValueFrom }'  number='1'  greaterThan="0" > - 
							<input type="text" name="borrowCreditValueTo" class="input_text70" id ="borrowCreditValueTo"
								value='${borrowView.borrowCreditValueTo }' number='1' greaterThan="0"  
								from-checkFloat = "#borrowCreditValueFrom" >
						</td>
						<td><label class="lab">可用期数：</label>
							<input type="text" name="borrowMonthsSurplusFrom" class="input_text70"  id="borrowMonthsSurplusFrom"
								value="${borrowView.borrowMonthsSurplusFrom }"   maxLength="9" positive_integer_number="0"> -
							<input type="text" name="borrowMonthsSurplusTo" class="input_text70" id="borrowMonthsSurplusTo"
								value="${borrowView.borrowMonthsSurplusTo }" 
								from-checkInt = "#borrowMonthsSurplusFrom"    maxLength="9" positive_integer_number="0">
					</td>
				</tr>
				<tr>
					<td> 
					    <label class="lab">还款日：</label>
						<select name="borrowBakcmoneyDay" class="select78 mr34" multiple="multiple">
                            <c:forEach items="${dicts.tz_repay_day }" var="item">
                               <option value="${item.value }" <c:if test="${fns:multiOption(borrowView.borrowBakcmoneyDay,item.value)}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                     </td>
					<td>
						<label class="lab">利率：</label>
						<input type="text" name="borrowMonthRate" value="${borrowView.borrowMonthRate }" class="cf_input_text178" number='1' greaterThan="0">
					</td>
					<td><label class="lab">首次还款日：</label>
						<input type="text" name="borrowBackmoneyFirdayFrom" id ="borrowBackmoneyFirdayFrom"
							value="${fns:getFormatDate(borrowView.borrowBackmoneyFirdayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowBackmoneyFirdayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="borrowBackmoneyFirdayTo" id="borrowBackmoneyFirdayTo"
							value="${fns:getFormatDate(borrowView.borrowBackmoneyFirdayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowBackmoneyFirdayFrom\',{});}'})" class="input_text70 Wdate">
					</td>
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">职业情况：</label>
						<select name="borrowerJob" class="select78 mr34" multiple="multiple" >
	                           <c:forEach items="${dicts.jk_prof_type }" var="item">
	                              <option value="${item.value }" <c:if test="${fns:multiOption(borrowView.borrowerJob,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                    </select>
                    </td>
					<td>
						<label class="lab">债权来源：</label>
						<select  name="borrowType" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_credit_src }" var="item">
	                           	<c:if test="${item.value!='1' }">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(borrowView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
	                           	</c:if>
	                           </c:forEach>
	                     </select>
	                </td>
					<td>
						<label class="lab">债权标识：</label>
						<select name="borrowTrusteeFlag" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_zjtr_mark}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowView.borrowTrusteeFlag,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
				</tr>
				<tr id="T2" style="display: none;">
					<td>
						<label class="lab">债权区分：</label>
						<select name="dicLoanDistinguish" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_loan_distinguish}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowView.dicLoanDistinguish,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
					<td><label class="lab">操作日期：</label>
						<input type="text" name="pushBorrowTimeFrom" id ="pushBorrowTimeFrom"
							value="${fns:getFormatDate(borrowView.pushBorrowTimeFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'pushBorrowTimeTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="pushBorrowTimeTo" id="pushBorrowTimeTo"
							value="${fns:getFormatDate(borrowView.pushBorrowTimeTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'pushBorrowTimeFrom\',{});}'})" class="input_text70 Wdate">
					</td>
					<td>
						<label class="lab">操作状态：</label>
						<select name="pushBorrowStatus" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_push_borrow_djr}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(borrowView.pushBorrowStatus,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
				</tr>
				<tr id="T3" style="display: none;">
					<td><label class="lab">截止还款日期：</label>
						<input type="text" name="loanBackmoneyLastdayFrom" id ="loanBackmoneyLastdayFrom"
							value="${fns:getFormatDate(borrowView.loanBackmoneyLastdayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanBackmoneyLastdayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="loanBackmoneyLastdayTo" id="loanBackmoneyLastdayTo"
							value="${fns:getFormatDate(borrowView.loanBackmoneyLastdayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'loanBackmoneyLastdayFrom\',{});}'})" class="input_text70 Wdate">
					</td>
				</tr>
				</table>
			    <div class="tright pr30 pb5 pt10">
			    	<input type="button" id="search" value="搜索" class="btn cf_btn-primary"/>
					<input type="reset" id="formReset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"> </div>			    
				</div>				
		</form>				
		</div>
		<p class="mb10 ml10">
			<auth:hasPermission key="cf:borrow:pushBorrowList">
			<button id = "pushBorrowButtun" class="btn btn_sc" onclick="pushBorrow()">推送债权至大金融</button>
			</auth:hasPermission>
			
			<auth:hasPermission key="cf:borrow:releaseBorrowList">
			<button id = "releaseBorrowbuttun" class="btn btn_sc" onclick="releaseBorrow()">释放债权</button>
			</auth:hasPermission>
			
			总金额：￥<span id="money">${fns:getFormatNumber(allMoney,'#,##0.00')}</span>元 总笔数：<span id="count">${page.count }</span>笔
		</p>
		<div id="content-body">
		  <div id="reportTableDiv" class="span10">
		  <sys:columnCtrl pageToken="borrow_borrowList"></sys:columnCtrl>
		  <div class='box5'>
			  <table class="reportTable table table-striped table-bordered table-condensed data-list-table" >
			  <thead>
				<tr>
					<th>
					<input type="checkbox" class="checkAll" name="creditValueId"/>
					序号
					</th>
					<th>借款人</th>
					<th>借款人身份证号</th>
					<th>债权来源</th>
					<th>债权标识</th>
					<th>职业情况</th>
					<th>借款产品</th>
					<th>首次还款日</th>
					<th>还款日</th>
					<th>借款期数</th>
					<th>可用期数</th>
					<th>截止还款日期</th>
					<th>月利率</th>
					<th>原始债权价值</th>
					<th>推送债权价值</th>
					<th>年预计债权收益</th>
					<th>债权区分</th>
					<th>操作日期</th>
					<th>推送系统</th>
					<th>操作状态</th>

				</tr>
				</thead>
				<c:forEach items="${page.list }" var="item" varStatus="s">
					<tr>
						<td id="creditValueIdCheckbox">
	           			<input type="checkbox" name='creditValueId' value="${item.borrowPushId}:${item.verTime}:${item.creditMonId}:${item.borrowMonthVerTime}" id="checkOne">
						${s.index+1}
						</td>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>***</td>
						<%-- <td>${item.loanName }</td> --%>
						<td>***</td>
						<%-- <td>${fns:formatLoanIdcard(item.loanIdcard)}</td> --%>
						<td id="loanType">${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') }</td>
						<td>${fns:dictName(dicts.tz_zjtr_mark,item.loanTrusteeFlag,'-') }</td>
						<td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }</td>
						<td>${item.loanProduct }</td>
						<td>${fns:getFormatDate(item.loanBackmoneyFirday,"yyyy-MM-dd")}</td>
						<td>${fns:dictName(dicts.tz_repay_day,item.loanBackmoneyDay,'-') }</td>
						<td>${item.loanMonths }</td>
						<td>${item.loanMonthsSurplus }</td>
						<td>${fns:getFormatDate(item.loanBackmoneyLastday,"yyyy-MM-dd")}</td>
						<td>${fns:formatNumber(item.loanMonthRate)}</td>
						<td>${fns:getFormatNumber(item.loanQuota,'￥#,##0.00')}</td>
						<td id="loanCreditValue">${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}</td>
						<td>${fns:getFormatNumber(item.loanValueYear,'0.00')}</td>
						<td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
						<td>${fns:getFormatDate(item.createTime,"yyyy-MM-dd")}</td>
						<td>${item.pushPlatForm}</td>
						<td>${fns:dictName(dicts.tz_push_borrow_djr,item.pushStatus,'') }</td>
						
					</tr>
				</c:forEach>
			</table>
		    </div>
		</div>
	</div>
	<div class="pagination">${page}</div>
	
</body>
</html>