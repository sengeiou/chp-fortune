<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>债权冻结列表</title>
<script src="${ctxWebInf }/js/borrow/borrowFreez.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
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
		<form action="${ctx}/borrowFreeze/list" method="post" id="searchForm">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0"
				width="100%">
				<tr>
					<td>
						<label class="lab">借款人：</label>
						<input type="text" name="borrowerName" value="${borrowView.borrowerName }" class="cf_input_text178">
					</td>
					<td>
						<label class="lab">可用金额：</label>
							<input type="text" name="borrowCreditValueFrom" id="borrowCreditValueFrom"
								value="${borrowView.borrowCreditValueFrom }" class="input_text70" number='1' greaterThan="0"> -
							<input type="text" name="borrowCreditValueTo" id="borrowCreditValueTo"
								value="${borrowView.borrowCreditValueTo }" class="input_text70" from-checkInt ="#borrowCreditValueFrom" number='1' greaterThan="0">
					</td>
						<td>
						<label class="lab">可用期数：</label>
							<input type="text" name="borrowMonthsSurplusFrom" id="borrowMonthsSurplusFrom"
								value="${borrowView.borrowMonthsSurplusFrom }" class="input_text70" positive_integer_number="0" maxlength='10'> -
							<input type="text" name="borrowMonthsSurplusTo" id="borrowMonthsSurplusTo"
								value="${borrowView.borrowMonthsSurplusTo }" class="input_text70" positive_integer_number="0" from-checkInt = "#borrowMonthsSurplusFrom" maxlength='10'>
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
					<td>
						<label class="lab">首次还款日：</label>
						<input type="text" name="borrowBackmoneyFirdayFrom" id="borrowBackmoneyFirdayFrom"
							value="${fns:getFormatDate(borrowView.borrowBackmoneyFirdayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'borrowBackmoneyFirdayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="borrowBackmoneyFirdayTo" id="borrowBackmoneyFirdayTo"
							value="${fns:getFormatDate(borrowView.borrowBackmoneyFirdayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'borrowBackmoneyFirdayFrom\',{});}'})" class="input_text70 Wdate" from-checkDate ="#borrowBackmoneyFirdayFrom">
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
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(borrowView.borrowType,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                     </select>
	                </td>
	                <td>
						<label class="lab">冻结时间：</label>
						<input type="text" name="loanFreezeDayFrom" id="loanFreezeDayFrom"
							value="${fns:getFormatDate(borrowView.loanFreezeDayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanFreezeDayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="loanFreezeDayTo" id="loanFreezeDayTo"
							value="${fns:getFormatDate(borrowView.loanFreezeDayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'loanFreezeDayFrom\',{});}'})" class="input_text70 Wdate" from-checkDate ="#loanFreezeDayFrom">
					</td>
				</tr>
		   </table>
				<div class="tright pr30 pb5 pt10">
					<input type="button" value="搜索" id="search" class="btn cf_btn-primary"/>
					<input type="reset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"> </div>
				</div>
			</form>			
		</div>
		<p class="mb10 ml10">
			<auth:hasPermission key="cf:borrowfreeze:exportexcel">
				<button class='btn btn_sc ml10' onclick="outExcel()">导出EXCEL</button>
			</auth:hasPermission>
			总金额：￥${fns:getFormatNumber(money,'#,##0.00')}元 总笔数：${page.count }笔
		</p>
		
		<sys:columnCtrl pageToken="borrow_borrowFreezList"></sys:columnCtrl>
		<div class='box5'>
		<table class='table table-striped table-bordered table-condensed data-list-table' width="100%">
			<thead>
				<tr>
		            <th>序号</th>
		            <th>借款人</th>
		            <th>身份证号</th>
		            <th>债权来源</th>
		            <th>借款产品</th>
		            <th>借款用途</th>
		            <th>职业情况</th>
		            <th>首次还款日</th>
		            <th>还款日</th>
		            <th>借款期（天）数</th>
		            <th>可用期（天）数</th>
		            <th>截止还款日期</th>
		            <th>月利率</th>
		            <th>原始债权价值</th>
		            <th>可用债权价值</th>
		            <th>债权持有比例</th>
		            <th>年预计债权收益</th>
		            <th>冻结时间</th>
		            <th>操作</th>
		        </tr>
	        </thead>
        <c:forEach items="${page.list }" var="item" varStatus="s">
        <tr>
            <td>${s.index+1}</td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.loanName }</td> --%>
            <td>***</td>
            <%-- <td>${fns:formatLoanIdcard(item.loanIdcard)}</td> --%>
			<td>${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') }</td>
            <td>${item.loanProduct }</td>
            <td>${item.loanPurpose }</td>
			<td>${fns:dictName(dicts.jk_prof_type,item.loanJob,'-') }</td>
            <td>${fns:getFormatDate(item.loanBackmoneyFirday ,'yyyy-MM-dd')}</td>
			<td>${fns:dictName(dicts.tz_repay_day,item.loanBackmoneyDay,'-') }</td>
			<c:choose>
				<c:when test="${item.dictLoanType=='1' }">
		            <td>${item.loanMonths }(天)</td>
					<td>${item.loanMonthsSurplus }(天)</td>
				</c:when>
				<c:otherwise> 
				 	<td>${item.loanMonths }</td>
					<td>${item.loanMonthsSurplus }</td>
				</c:otherwise>
			</c:choose>
            <td>${fns:getFormatDate(item.loanBackmoneyLastday ,'yyyy-MM-dd')}</td>
            <td>${fns:formatNumber(item.loanMonthRate)}</td>
            <td>${fns:getFormatNumber(item.loanQuota,'￥#,##0.00')}</td>
			<td>${fns:getFormatNumber(item.loanCreditValue,'￥#,##0.00')}</td>
			<%-- <c:choose>
				<c:when test="${item.ratio eq '0.00%' }">
					<td>${item.ratio }</td>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> --%>
			<td>${fns:getFormatNumber(item.ratio,'0.00%')}</td>
            <td>${fns:getFormatNumber(item.loanValueYear,'0.00')}</td>
            <td>${fns:getFormatDate(item.loanFreezeDay ,'yyyy-MM-dd')}</td>
            <td class="tcenter">
            	<auth:hasPermission key="cf:borrowfreeze:transact">
            		<button class="cf_btn_edit" onclick="goLook('${item.creditValueId }')">办理</button>
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
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
</body>
</html>