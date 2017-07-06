<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/borrow/common.js" type="text/javascript"></script>
<script src="${ctxWebInf }/js/creditor/matching/creditorReleaseList.js" type="text/javascript"></script>
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
		<form id="searchForm" action="${ctx}/creditor/creditorRelease/list" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input id="creditValueIdList" name="creditValueIdList" type="hidden"/>
			<input id="hiddenMoney" type="hidden" value="${fns:getFormatNumber(allMoney,'0.00')}"/>
			<input id="hiddenMoneyL2" type="hidden" value="${fns:getFormatNumber(allMoney2,'0.00')}"/>
			<input id="hiddenCount"  type="hidden" value="${page.count}"/>
			<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td>
						<label class="lab">借款人：</label>
						<input type="text" name="loanName" class="cf_input_text178" value="${creditorReleaseView.loanName }">
					</td>
					<td>
						<label class="lab">释放金额：</label> 
						<input type="text" name="releaseCreditValueFrom" class="input_text70" id = "releaseCreditValueFrom"
							value='${creditorReleaseView.releaseCreditValueFrom }'  number='1'  greaterThan="0" > - 
						<input type="text" name="releaseCreditValueTo" class="input_text70" id ="releaseCreditValueTo"
							value='${creditorReleaseView.releaseCreditValueTo }' number='1' greaterThan="0"  
							from-checkFloat = "#releaseCreditValueFrom" >
					</td>
					<td>
						<label class="lab">剩余期数：</label>
						<input type="text" name="loanMonthsSurplusFrom" class="input_text70" id="loanMonthsSurplusFrom"
							value='${creditorReleaseView.loanMonthsSurplusFrom }'  number='1'  greaterThan="0" > - 
						<input type="text" name="loanMonthsSurplusTo" class="input_text70" id ="loanMonthsSurplusTo"
							value='${creditorReleaseView.loanMonthsSurplusTo }' number='1' greaterThan="0"  
							from-checkFloat = "#loanMonthsSurplusFrom" >
					</td>
				</tr>
				<tr>
					<td> 
					    <label class="lab">还款日：</label>
						<select name="loanBackmoneyDay" class="select78 mr34" multiple="multiple">
                            <c:forEach items="${dicts.tz_repay_day }" var="item">
                               <option value="${item.value }" <c:if test="${fns:multiOption(creditorReleaseView.loanBackmoneyDay,item.value)}">selected</c:if>>${item.label }</option>
                            </c:forEach>
                        </select>
                     </td>
					<td>
						<label class="lab">月利率：</label>
						<input type="text" name="loanMonthRate" value="${creditorReleaseView.loanMonthRate }" class="cf_input_text178" number='1' greaterThan="0">
					</td>
					<td>
						<label class="lab">首次还款日：</label>
						<input type="text" name="loanBackmoneyFirdayFrom" id="loanBackmoneyFirdayFrom"
							value="${fns:getFormatDate(creditorReleaseView.loanBackmoneyFirdayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanBackmoneyFirdayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="loanBackmoneyFirdayTo" id="loanBackmoneyFirdayTo"
							value="${fns:getFormatDate(creditorReleaseView.loanBackmoneyFirdayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'loanBackmoneyFirdayFrom\',{});}'})" class="input_text70 Wdate">
					</td>
				</tr>
				<tr id="T1" style="display: none;">
					<td>
						<label class="lab">债权区分：</label>
						<select name="dicLoanDistinguish" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_loan_distinguish}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(creditorReleaseView.dicLoanDistinguish,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
					<td>
						<label class="lab">债权来源：</label>
						<select  name="dictLoanType" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_credit_src }" var="item">
	                           	<c:if test="${item.value!='1' }">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(creditorReleaseView.dictLoanType,item.value)}">selected</c:if>>${item.label }</option>
	                           	</c:if>
	                           </c:forEach>
	                     </select>
	                </td>
					<td>
						<label class="lab">债权标识：</label>
						<select name="loanTrusteeFlag" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_zjtr_mark}" var="item">
	                           	  <option value="${item.value }"<c:if test="${fns:multiOption(creditorReleaseView.loanTrusteeFlag,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
						</select>
					</td>
				</tr>
				<tr id="T2" style="display: none;">
					<td>
						<label class="lab">出借到期日：</label>
						<input type="text" name="applyExpireDayFrom" id ="applyExpireDayFrom"
							value="${fns:getFormatDate(creditorReleaseView.applyExpireDayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="applyExpireDayTo" id="applyExpireDayTo"
							value="${fns:getFormatDate(creditorReleaseView.applyExpireDayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDayFrom\',{});}'})" class="input_text70 Wdate">
					</td>
					<td>
						<label class="lab">转出状态：</label>
						<select name="releaseState" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_creditor_release_state }" var="item">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(creditorReleaseView.releaseState,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                     </select>
	                </td>
	                <td>
						<label class="lab">转出日期：</label>
						<input type="text" name="releaseTimeFrom" id ="releaseTimeFrom"
							value="${fns:getFormatDate(creditorReleaseView.releaseTimeFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'releaseTimeTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="releaseTimeTo" id="releaseTimeTo"
							value="${fns:getFormatDate(creditorReleaseView.releaseTimeTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'releaseTimeFrom\',{});}'})" class="input_text70 Wdate">
					</td>
				</tr>
				<tr>
					<td>
						<label class="lab">转出标识：</label>
						<select name="releaseFlag" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_creditor_release_flag }" var="item">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(creditorReleaseView.releaseFlag,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                     </select>
	                </td>
	                <td>
						<label class="lab">借款ID：</label>
	                     <select name="creditValueIdFlag" class="select78 mr34" multiple="multiple">
	                           <c:forEach items="${dicts.tz_creditor_release_loan_flag }" var="item">
	                           	  <option value="${item.value }" <c:if test="${fns:multiOption(creditorReleaseView.creditValueIdFlag,item.value)}">selected</c:if>>${item.label }</option>
	                           </c:forEach>
	                     </select>
	                </td>
	                <td>
	                	<label class="lab">证件类型：</label>
	                	<select name="customerCertType" class="select78 mr34" >
	                		<option value="">请选择</option>
	                		<option value="0" <c:if test="${creditorReleaseView.customerCertType == 0}">selected</c:if>>身份证号</option>
	                		<option value="1" <c:if test="${creditorReleaseView.customerCertType == 1}">selected</c:if>>其他</option>
	                     </select>
	                </td>
				</tr>
				<tr>
					<td>
	                	<label class="lab">提前结清标识：</label>
	                	<select name="tjFlag" class="select78 mr34" multiple="multiple">
	                		<option value="">请选择</option>
	                		<option value="0" <c:if test="${creditorReleaseView.tjFlag == 0}">selected</c:if>>TJ</option>
	                		<option value="1" <c:if test="${creditorReleaseView.tjFlag == 1}">selected</c:if>>空</option>
	                     </select>
	                </td>
	                <td>
						<label class="lab">提前结清日期：</label>
						<input type="text" name="loanFreezeDayFrom" id ="loanFreezeDayFrom"
							value="${fns:getFormatDate(creditorReleaseView.loanFreezeDayFrom ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loanFreezeDayTo\',{});}'})" class="input_text70 Wdate"> -
						<input type="text" name="loanFreezeDayTo" id="loanFreezeDayTo"
							value="${fns:getFormatDate(creditorReleaseView.loanFreezeDayTo ,'yyyy-MM-dd')}" 
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'loanFreezeDayFrom\',{});}'})" class="input_text70 Wdate">
					</td>
					<td>
	                	<label class="lab">债权情况：</label>
	                	<select name="zqState" class="select78 mr34" multiple="multiple">
	                		<option value="">请选择</option>
	                		<option value="0" <c:if test="${creditorReleaseView.zqState == 0}">selected</c:if>>-</option>
	                		<option value="1" <c:if test="${creditorReleaseView.zqState == 1}">selected</c:if>>    </option>
	                     </select>
	                </td>
				</tr>
				</table>
			    <div class="tright pr30 pb5 pt10">
			    	<input type="button" id="search" value="搜索" class="btn cf_btn-primary"/>
					<input type="reset" id="formReset" value="清除" class="btn cf_btn-primary"/>
					<div class="xiala"  id="showMore" onclick="javascript:show();"> </div>			    
				</div>	
				<input type="hidden" name="isSearch" value="1">			
		</form>				
		</div>
		<p class="mb10 ml10">
			<auth:hasPermission key="cf:creditorrelease:release">
				<button class="btn btn_sc" onclick="releaseCreditor();">释放</button>
			</auth:hasPermission>
			<auth:hasPermission key="cf:creditorrelease:transferout">
				<button class="btn btn_sc" onclick="transferOut();">转出</button>
			</auth:hasPermission>
			<auth:hasPermission key="cf:creditorrelease:confirmtransferout">
				<button class="btn btn_sc" onclick="confirmTransferOut();">确认转出</button>
			</auth:hasPermission>
			<auth:hasPermission key="cf:creditorrelease:earlySettlement">
				<button class="btn btn_sc" onclick="earlySettlement();">结清</button>
			</auth:hasPermission>
			
			总金额：￥<span id="money">${fns:getFormatNumber(allMoney,'#,##0.00')}</span>元  总金额(L2)：￥<span id="moneyL2">${fns:getFormatNumber(allMoney2,'#,##0.00')}</span>元  总笔数：<span id="count">${page.count }</span>笔
		</p>
		<div id="content-body">
		  <div id="reportTableDiv" class="span10">
		  <sys:columnCtrl pageToken="creditor_release_creditor_release"></sys:columnCtrl>
		  <div class='box5'>
			  <table class="reportTable table table-striped table-bordered table-condensed data-list-table" >
			  <thead>
				<tr>
					<th>
						<input type="checkbox" class="checkAll" name="releaseIds"/>
						序号
					</th>
					<th>出借人</th>
					<th>出借人身份证号</th>
					<th>出借编号</th>
					<th>到期释放金额</th>
					<th>出借到期日</th>
					<th>借款ID</th>
					<th>借款人</th>
					<th>借款人身份证号</th>
					<th>债权来源</th>
					<th>债权标识</th>
					<th>债权区分</th>
					<th>借款产品</th>
					<th>借款用途</th>
					<th>借款期数</th>
					<th>剩余期数</th>
					<th>首次还款日</th>
					<th>原始债权价值</th>
					<th style="display: none;">释放金额</th>
					<th>释放金额</th>
					<th>还款日</th>
					<th>月利率</th>
					<th>提前结清标识</th>
					<th>转出状态</th>
					<th>转出标识</th>
					<th>转出日期</th>
					<th>提前结清日期</th>
					<th>债权情况</th>
				</tr>
				</thead>
				<c:forEach items="${page.list }" var="item" varStatus="s">
					<tr>
						<td id="releaseIdCheckbox">
		           			<input type="checkbox" name='releaseId' value="${item.releaseId}_${item.releaseCreditValue}_${item.loanMonthsSurplus}_${item.loanCode}_${item.releaseState}_${item.tjFlag}_${item.zqState}_${item.loanName }" id="checkOne">
							${s.index+1}
						</td>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>***</td>
						<%-- <td>${item.customerName }</td> --%>
						<td>***</td>
						<%-- <td>${fns:formatLoanIdcard(item.customerCertNum)}</td> --%>
						<td>${item.lendCode }</td>
						<td>${fns:getFormatNumber(item.lendReleaseCreditValue,'￥#,##0.00')}</td>
						<td>${fns:getFormatDate(item.applyExpireDay,"yyyy-MM-dd")}</td>
						<td>
							<c:choose> 
								<c:when test="${empty item.loanCode}"> 
									无
								</c:when> 
								<c:otherwise> 
									${item.loanCode}
								</c:otherwise> 
								</c:choose> 
						</td>
						<!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>***</td>
						<%-- <td>${item.loanName }</td> --%>
						<td>***</td>
						<%-- <td>${fns:formatLoanIdcard(item.loanIdcard)}</td> --%>
						<td>${fns:dictName(dicts.tz_credit_src,item.dictLoanType,'-') }</td>
						<td>${fns:dictName(dicts.tz_zjtr_mark,item.loanTrusteeFlag,'-') }</td>
						<td>${fns:dictName(dicts.tz_loan_distinguish,item.dicLoanDistinguish,'-') }</td>
						<td>${item.loanProduct }</td>
						<td>${item.loanPurpose }</td>
						<td>${item.loanMonths }</td>
						<td>${item.loanMonthsSurplus }</td>
						<td>${fns:getFormatDate(item.loanBackmoneyFirday,"yyyy-MM-dd")}</td>
						<td>${fns:getFormatNumber(item.loanQuota,'￥#,##0.00')}</td>
						<td id="loanCreditValue" style="display: none;">${item.releaseCreditValue}</td>
						<td>
							${fns:getFormatNumber(item.releaseCreditValue,'￥#,##0.00')}
						</td>
						<td>${fns:dictName(dicts.tz_repay_day,item.loanBackmoneyDay,'-') }</td>
						<td>${fns:formatNumber(item.loanMonthRate)}</td>
						<td>${item.tjFlag}</td>
						<td>${fns:dictName(dicts.tz_creditor_release_state,item.releaseState,'-') }</td>
						<td>${fns:dictName(dicts.tz_creditor_release_flag,item.releaseFlag,'-') }</td>
						<td>${fns:getFormatDate(item.releaseTime,"yyyy-MM-dd")}</td>
						<td>${fns:getFormatDate(item.loanFreezeDay,"yyyy-MM-dd")}</td>
						<td>
							<c:choose>
								<c:when test="${item.zqState == '' or item.zqState == null}">
									${item.zqState }
								</c:when>
								<c:otherwise>
									${fns:getFormatNumber(item.zqState,'0.00')}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		    </div>
		</div>
	</div>
	<div class="pagination">${page}</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="提交" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>