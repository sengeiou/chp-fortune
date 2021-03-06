<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/countMoney.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/goldAccoun.js"></script>
</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form id="searchForm" action="${ctx}/goldAccoun/goldAccounList" method="post">
        	    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">出借编号：</label>
                	<input type="text" name="applyCode" value="${deductPoolExt.applyCode}" class="cf_input_text178">
                </td>
                <td>
                	<label class="lab">客户姓名：</label>
                	<input type="text" name="custName" value="${deductPoolExt.custName}" class="cf_input_text178">
                </td>
			    <td>
			    	<label class="lab">计划出借日：</label>
			    	<input type="text" name="applyLendDateStart" id="applyLendDateStart" 
			    		value="${fns:getFormatDate(deductPoolExt.applyLendDateStart,'yyyy-MM-dd')}"
			    		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDateEnd\',{d:-1});}'})"> -
			    	<input type="text" name="applyLendDateEnd" id="applyLendDateEnd" 
			    		value="${fns:getFormatDate(deductPoolExt.applyLendDateEnd,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDateStart\',{});}'})">
			    </td>
            </tr>
            <tr> 
			   	<td>
			   		<label class="lab">营业部：</label>
			   		<sys:orgTree id="org"  name="checkNode" value="${deductPoolExt.checkNode}" labelName="orgName"  labelValue="${deductPoolExt.orgName}" />
			   	</td>
			    <td>
			    	<label class="lab">计划出借金额：</label>
			    	<input type="text" name="applyLendMoneyStart" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${deductPoolExt.applyLendMoneyStart}" class="input_text70"> -
			    	<input type="text" name="applyLendMoneyEnd" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${deductPoolExt.applyLendMoneyEnd}" class="input_text70">
			    </td>
        		<td>
                	<label class="lab">出借产品：</label>
                	<sys:productselect name="productCode" value="${deductPoolExt.productCode}"  multiple="true"/>
                </td>
            </tr>
        </table>
        <div class='tright pr30'>
             <input class="btn cf_btn-primary" type="submit" value="搜索"/>
             <input type="reset" class="btn cf_btn-primary" value="清除"></div>
	</form>
	</div>
    <sys:message content="${message}"/>
    <p class="mb10">
    	<auth:hasPermission key="cf:trusteeshipaccount:exportexcel">
			<button class="btn btn_sc ml10" id="outExcel">导出Excel</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:trusteeshipaccount:intoexcel">
			<button class="btn btn_sc" id="goldUploadFile">导入Excel</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:trusteeshipaccount:deduct">
			<button class="btn btn_sc" id="transfer">划拨</button>
		</auth:hasPermission>
    <input type="hidden" id="oSumCount" value="${page.count }">
    <input type="hidden" id="oLendMoney" value="<fmt:formatNumber value='${sumMoney.applylendmoney }' pattern='0.00'/>">
    <input type="hidden" id="oDeductMoney" value="<fmt:formatNumber value='${sumMoney.applydeductmoney }' pattern='0.00'/>">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
    &nbsp;&nbsp;总笔数：<label id="lendCount">${page.count }</label>笔&nbsp;&nbsp;&nbsp;&nbsp;
	出借总金额：￥<label id="lendMoney">${fns:getFormatNumber(sumMoney.applylendmoney ,'#0.00')}</label>元&nbsp;&nbsp;&nbsp;&nbsp;
	划扣总金额：￥<label id="deductMoney">${fns:getFormatNumber(sumMoney.applydeductmoney ,'#0.00')}</label>元</p>
    <sys:columnCtrl pageToken="deduct_goldAccounList"></sys:columnCtrl>
    <div class="box5">
	    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
	        <thead>
	        <tr>
	            <th><input type="checkbox" class="ml10 mr10 checkAll" id="checkAll">全选</th>
	            <th>出借编号</th>
	            <th>客户姓名</th>
	            <th>营业部</th>
	            <th>出借产品</th>
	<!-- 			<th>账单发送状态</th> -->
	<!--             <th>付款方式</th> -->
	            <th>划扣平台</th>
	            <th>出借银行</th>
	            <th>计划出借日期</th>
	<!--             <th>计划划扣日期</th> -->
	            <th>计划出借金额</th>
	<!--             <th>划扣金额</th> -->
	            <th>到期日期</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list }" var="deductPoolEx" varStatus="status">
		        <tr>
		            <td>
		            	<input type="checkbox" name="checkbox" value="${deductPoolEx.applyCode}_${deductPoolEx.verTime}">
		            	${status.count }
			            <input id="lendMoneyH" type="hidden" value="${deductPoolEx.applyLendMoney }">
			            <input id="deductMoneyH" type="hidden" value="${deductPoolEx.applyDeductMoney }">
		            </td>
		            <td>${deductPoolEx.applyCode }</td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->
		            <td>***</td>
		            <%-- <td>${deductPoolEx.custName }</td> --%>
		            <td>${deductPoolEx.orgName }</td>
		            <td>${deductPoolEx.productName }</td>
	<%-- 				<td>${deductPoolEx.dictMatchingFilesendStatus}</td> --%>
	<%-- 	            <td>${fns:getDictLabel(deductPoolEx.applyPay,'tz_pay_type','-')}</td> --%>
		            <td>${fns:dictName(dicts.tz_deduct_plat,deductPoolEx.dictApplyDeductType,'-')}</td>
		            <td>${fns:dictName(dicts.tz_open_bank,deductPoolEx.accountBank,'-')}</td>
		            <td>${fns:getFormatDate(deductPoolEx.applyLendDate ,'yyyy-MM-dd')}</td>
	<%-- 	            <td${fns:getFormatDate(deductPoolEx.applyDeductDate ,'yyyy-MM-dd')}</td> --%>
		            <td>${fns:getFormatNumber(deductPoolEx.applyLendMoney ,'￥#,##0.00')}</td>
	<%-- 	            <td>${fns:getFormatNumber(deductPoolEx.applyDeductMoney ,'￥#,##0.00')}</td> --%>
		            <td>${fns:getFormatDate(deductPoolEx.applyExpireDate ,'yyyy-MM-dd')}</td>
		            <td class="tcenter">
		            	<auth:hasPermission key="cf:trusteeshipaccount:transact">
			    		 	<a href="${ctx}/goldAccoun/conduct?applyCode=${ deductPoolEx.applyCode }_${ deductPoolEx.verTime }">
			    		 		<button class="cf_btn_edit" onclick="">办理</button>
		          			 </a>
		          		</auth:hasPermission>
		            </td>
		        </tr>
	        </c:forEach>
	    </table>
	</div>
</div>
    <div class="pagination">${page}</div>
    <div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">机构</h4>
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
