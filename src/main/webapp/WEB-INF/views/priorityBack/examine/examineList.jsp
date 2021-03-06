<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style='overflow:-Scroll;overflow-y:hidden'>
<head>
<meta name="decorator" content="default"/>
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/colorbox.css" />
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/priorityBack/examine/examineList.js"></script>
<title>优先回款审批列表</title>
</head>
<body>
<div class="body_r">
    <form id="searchForm" action="${ctx}/myPriority/examine/examineList" method="post">
    <div class="box1 mb10">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class='lab'>客户姓名：</label><input type="text" name="customerName" value="${view.customerName }" class="cf_input_text178"></td>
                <td><label class='lab'>客户编号：</label><input type="text" name="customerCode" value="${view.customerCode }" class="cf_input_text178"></td>
				<td><label class='lab'>出借编号：</label><input type="text" name="lendCode" value="${view.lendCode }" class="cf_input_text178"></td>
            </tr>
            <tr> 
				<td><label class="lab">营业部：</label> 
					<sys:orgTree id="org" name="orgID" value="${view.orgID}" labelName="orgName" labelValue="${view.orgName}" />
				</td>
                <td><label class='lab'>出借日期：</label>
                	<input type="text" name="applyLendDay" id="applyLendDay" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDayTo\',{d:-1});}'})" value="${fns:getFormatDate(view.applyLendDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyLendDayTo" id="applyLendDayTo" from-checkDate="#applyLendDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDay\',{});}'})" value="${fns:getFormatDate(view.applyLendDayTo,'yyyy-MM-dd')}"/>
                </td>
                <td><label class="lab">付款方式：</label> 
                	<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
						<c:forEach items="${dicts.tz_pay_type}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.applyPay, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
            <tr id="T1" style="display:none">
                
                <td><label class='lab'>到期日：</label>
                	<input type="text" name="applyExpireDay" id="applyExpireDay" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{d:-1});}'})" value="${fns:getFormatDate(view.applyExpireDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyExpireDayTo" id="applyExpireDayTo" from-checkDate="#finalLinitDate" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDay\',{});}'})" value="${fns:getFormatDate(view.applyExpireDayTo,'yyyy-MM-dd')}"/>
                </td>
                <td><label class='lab'>出借金额：</label>
                	<input type="text" name="applyLendMoney" id="applyLendMoney" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" value="${view.applyLendMoney }" class="input_text70"> -
                	<input type="text" name="applyLendMoneyTo" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" from-checkInt="#applyLendMoney" value="${view.applyLendMoneyTo }" class="input_text70">
                </td>
				<td><label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${view.productCode}" multiple="true"/></td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input type="submit" id="search" onclick="resetPage()" value="搜索" class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
        </div>
    </div>
    </form>
    
	<div class='box5'>	    	
	<sys:columnCtrl pageToken="myPriority_examine_examineList"></sys:columnCtrl>
    <table id="listTable" class="table table-striped table-bordered table-condensed data-list-table">
    	<thead>
    	<tr>
            <th>
            	<span>
			    	<input type="checkbox" id="checkAll" class="checkAll">全选
			    </span>
            </th>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>出借编号</th>
            <th>计划出借日期</th>
            <th>账单日</th>
            <th>到期日期</th>
			<th>计划出借金额</th>
            <th>出借产品</th>
            <th>客户经理</th>
            <th>付款方式</th>
            <th>省份|城市</th>
            <th>营业部</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
	        <tr>
	            <td><input type="checkbox" id="dataCheck" name="priorityId" value="${item.priorityId }" onclick="checkOne(this)"></td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	         <%--    <td>${item.customerName }</td> --%>
	            <td>***</td>
	            <td>${item.customerCode }</td>
	            <td>${item.lendCode }</td>
	            <td>${fns:getFormatDate(item.applyLendDay,"yyyy-MM-dd")}</td>
	            <td>${item.applyBillDay}</td>
	            <td>${fns:getFormatDate(item.applyExpireDay,"yyyy-MM-dd")}</td>
	            <td>
	            	${fns:getFormatNumber(item.applyLendMoney ,'￥#,##0.00')}
	            </td>
	            <td>${item.productName }</td>
	            <td>${item.manager }</td>
	            <td>
	            	${fns:dictName(dicts.tz_pay_type,item.applyPay,'-') }
	            </td>
	            <td>${item.accountAddrprovince}|${item.accountAddrcity}</td>
	            <td>${item.orgName }</td>
	            <td class="tcenter">
	            	<auth:hasPermission key="cf:priority:examine">
						<a href="${ctx}/myPriority/examine/examineDetail?priorityId=${item.priorityId }" class='cf_btn_edit'>
		            		办理
		            	</a>
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
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn cf_btn-primary" id="subClose">提交</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>