<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/exportExcel.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/finishBackInterest.js"></script>
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
<title>已回息列表</title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">
		    <form id="searchForm" action="${ctx}/finishBackInterest/loadFinishBackInterestList" method="post">
		        <input id="pageNo" name="pageNo" type="hidden" value="${bibv.page.pageNo}" />
				<input id="pageSize" name="pageSize" type="hidden" value="${bibv.page.pageSize}" />
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
		                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${bibv.so.customerCode}"/></td>
		                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="${bibv.so.customerName}"/></td>
						<td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" name="lendCode" value="${bibv.so.lendCode}"/></td>
		            </tr>
		            <tr> 
						<td><label class="lab">营业部：</label><sys:orgTree id="org" name="orgId" value="${bibv.so.orgId}" labelValue="${bibv.so.orgName}" labelName="orgName"/></td>
						<td><label class='lab'>城市：</label><sys:cityselect name="addrCity" value="${bibv.so.addrCity}" multiple="true"/></td>
					    <td><label class="lab">账单日：</label>
					    		<input type="text" id="currentBillday" name="currentBillday" class="Wdate cf_input_text178" value="${fns:getFormatDate(bibv.so.currentBillday,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker()"/></td>
		            </tr>
		            <tr id="T1" style="display:none">
		               <td><label class="lab">计划出借日：</label>
                			<input type="text" id="applyLendDayFrom" name="applyLendDayFrom" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDayTo\',{d:-1});}'})" value="<fmt:formatDate value='${bibv.so.applyLendDayFrom}' pattern='yyyy-MM-dd'/>"/> -
                			<input type="text" id="applyLendDayTo" from-checkDate="#applyLendDayFrom" name="applyLendDayTo" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDayFrom\',{});}'})" value="<fmt:formatDate value='${bibv.so.applyLendDayTo}' pattern='yyyy-MM-dd'/>"/>
	                	</td>
		                <td><label class="lab">到期日：</label>
		                	<input type="text" id="applyExpireDayFrom" name="applyExpireDayFrom" value="<fmt:formatDate value='${bibv.so.applyExpireDayFrom}' pattern="yyyy-MM-dd"/>" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{d:-1});}'})"/> -
		                	<input type="text" id="applyExpireDayTo" from-checkDate="#applyExpireDayFrom" name="applyExpireDayTo" value="<fmt:formatDate value='${bibv.so.applyExpireDayTo}' pattern="yyyy-MM-dd"/>" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDayFrom\',{});}'})"/></td>
		                <td><label class="lab">计划划扣日：</label>
		                	<input type="text" id="applyDeductDayFrom" name="applyDeductDayFrom" value="<fmt:formatDate value='${bibv.so.applyDeductDayFrom}' pattern='yyyy-MM-dd'/>" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDayTo\',{d:-1});}'})"/> -
		                	<input type="text" id="applyDeductDayTo" from-checkDate="#applyDeductDayFrom" name="applyDeductDayTo" value="<fmt:formatDate value='${bibv.so.applyDeductDayTo}' pattern="yyyy-MM-dd"/>" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDayFrom\',{});}'})"/></td>
		            </tr>
		            <tr id="T2" style="display:none">
		            	<td><label class="lab">回息日期：</label>
							<input type="text" id="backMoneyDayFrom" name="backMoneyDayFrom" value="${fns:getFormatDate(bibv.so.backMoneyDayFrom,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'backMoneyDayTo\',{d:-1});}'})"/> -
							<input type="text" id="backMoneyDayTo" from-checkDate="#backMoneyDayFrom" name="backMoneyDayTo" value="${fns:getFormatDate(bibv.so.backMoneyDayTo,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'backMoneyDayFrom\',{});}'})"/>
						</td>
		                <td><label class="lab">出借金额：</label>
		                	<input type="text" id="applyLendMoneyFrom" number="1" name="applyLendMoneyFrom" class="input_text70" value="${bibv.so.applyLendMoneyFrom}"/> -
                			<input type="text" number="1" from-checkInt="#applyLendMoneyFrom" name="applyLendMoneyTo" class="input_text70" value="${bibv.so.applyLendMoneyTo}"/>
		                </td>
		                <td><label class="lab">付款方式：</label>
		                		<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
									<c:forEach items="${dicts.tz_pay_type}" var='item'>
										<option value="${item.value }" <c:if test="${fn:contains(bibv.so.applyPay, item.value) }">selected</c:if>>
											${item.label }
										</option>
									</c:forEach>
							</select>
						</td>
		            </tr>
		            <tr id="T3" style="display:none">
		                <td><label class='lab'>出借产品：</label>
		                	<select name="productCode" id="productCode"class="select78" multiple="multiple">
								<c:forEach items="${bibv.productList}" var="item" >
									<option value="${item.productCode}" <c:if test="${fn:contains(bibv.so.productCode, item.productCode) }">selected</c:if>>${item.productName}</option>
								</c:forEach>
							</select>
		                </td>
						<td><label class="lab">卡/折：</label>
							<select class="select78" id='dictValue' name='dictValue' multiple="multiple">
								<c:forEach items="${dicts.com_card_type}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.dictValue, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
						<td><label class="lab">回息平台：</label>
							<select class="select78" id='platformId' name='platformCode' multiple="multiple">
								<c:forEach items="${dicts.tz_backInterest_plat}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.platformCode, item.value)}">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr id="T4" style="display:none">
		        		<td><label class="lab">合同版本号：</label> 
		                	<select class="select78 mr34" id='applyAgreementEdition' name='applyAgreementEdition' multiple="multiple">
									<c:forEach items="${dicts.tz_contract_vesion}" var='item'>
										<option value="${item.value }" <c:if test="${fns:multiOption(bibv.so.applyAgreementEdition, item.value) }">selected</c:if>>
											${item.label }
										</option>
									</c:forEach>
							</select>
						</td>
	        		</tr>
		        </table>
		        <div class="tright pr30 pb5 pt10">
		            <input type="button" id="search" class="btn cf_btn-primary" value="搜索" onclick="javascript:void(0);"/>
		            <input type="reset" value="清除" class="btn cf_btn-primary" onclick="javascript:void(0);"/>
		            <div class="xiala"  id="showMore" onclick="javascript:show();"></div>	    
		        </div>
			</form>
	    </div>
	    <p class="mb10 ml10">
	    	<auth:hasPermission key="cf:backinteresthistory:exportexect">
		    	<input type="button" value="导出EXCEL" class="btn btn_sc ml10" onclick="applyExport('finishBackInterest/exportExl')"/>
		    </auth:hasPermission>
		    <auth:hasPermission key="cf:backinteresthistory:batchback">
		    	<input type="button" value="批量退回" class="btn btn_sc" onclick="returnWindow()"/>
		    </auth:hasPermission>
			累计实际回息金额: ￥<label id="sum">${bibv.listTotalMoney}</label>元&nbsp;&nbsp;&nbsp;
    		总笔数：<label id="count">${bibv.page.count}</label>笔 
    		<input type="hidden" id="pageTotal" value="${bibv.pageTotalMoney}"/>
    		<input type="hidden" id="totalMoney" value="${bibv.listTotalMoney}"/>
			<input type="hidden" id="totalCount" value="${bibv.page.count}"/>
		</p>
		<sys:columnCtrl pageToken="backInterest_finish_finishBackInterestList"></sys:columnCtrl>
		<div class='box5'>
		    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		        <thead>
		        <tr>
		        	<th>
		            	<span onclick="checkAll();" onclick="checkAll();">
		            		<input type="checkbox" id="checkAll" class="ml10 mr10 checkAll" onclick="checkAll()">全选
		            	</span>
		            </th>
		            <th>序号</th>
		            <th>出借编号</th>
		            <th>客户编号</th>
		            <th>客户姓名</th>
		            <th>账单日</th>
					<th>出借方式</th>
					<th>付款方式</th>
					<th>合同版本号</th>
		            <th>客户开户行</th>
		            <th>卡/折</th>
		            <th>所在省</th>
		            <th>所在城市</th>
		            <th>回息平台</th>
		            <th>回息日期</th>
		            <th>实际回息金额</th>
		            <th>回息状态</th>
		            <th>是否回息</th>
		            <th>操作</th>
		        </tr>
		        </thead>
		        <c:forEach items="${bibv.page.list}" var="item" varStatus="status">
		        <tr>
		        	<td><input type="checkbox"  id="ids" name="ids" value="${item.backiId}" onclick="selects()"></td>
		            <td align="center">${status.count}</td>
		            <td align="center">${item.lendCode}</td>
		            <td align="center">${item.customerCode}</td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
		            <%-- <td align="center">${item.customerName}</td> --%>
		            <td align="center">${fns:getFormatDate(item.currentBillday,"yyyy-MM-dd")}</td>
		            <td align="center">${fns:getProductLabel(item.productCode)}</td>
		            <td align="center">${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }</td>
		            <td align="center">${fns:dictName(dicts.tz_contract_vesion,item.applyAgreementEdition,'-') }</td>
		            <td align="center">${fns:dictName(dicts.tz_open_bank,item.accountBank,'-')}</td>
		            <td align="center">${fns:dictName(dicts.com_card_type,item.accountCardOrBooklet,'-')}</td>
		            <td align="center">${item.province}</td>
		            <td align="center">${item.city}</td>
		            <td align="center">${fns:dictName(dicts.tz_backInterest_plat,item.platformId,'-')}</td>
		            <td align="center">${fns:getFormatDate(item.backMoneyDay,'yyyy-MM-dd')}</td>
		            <td align="center">
		            <input type="hidden" name="mmy" value="${item.backRealMoney}">
		            ${fns:getFormatNumber(item.backRealMoney ,'￥#,##0.00')}</td>
		            <td align="center">${fns:dictName(dicts.tz_backsms_state,item.backMoneyStatus,'-')}</td>
		            <td align="center">${fns:dictName(dicts.tz_back_interest_return,back.isInterest,'否')}</td>
		            <td class="center">
		            	<auth:hasPermission key="cf:backinteresthistory:transact">
		            		<input type="button" value="办理" class="cf_btn_edit" onclick="singleFinish('${item.backiId}');"/>
		            	</auth:hasPermission>
		            </td>
		        </tr>
		        </c:forEach>
		    </table>
	    </div>
	</div>
    <div class="page">
        <div class="pagination">${bibv.page}</div>
    </div>
    <div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">批量退回</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="tright pr30">
					<input type="button" value="提交" id="brh" class="btn cf_btn-primary"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
