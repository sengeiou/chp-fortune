<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>多天划扣已处理</title>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/theDayAlreadyProt.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/countMoney.js"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
</head>
<body>
<div class="body_r">
<div class="box1 mb10">
    <form id="searchForm" action="${ctx}/theDayAlreadPor/theDayDeductList" method="post">
        	    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td >
                	<label class="lab">出借编号：</label>
                	<input type="text" name="applyCode" value="${deductPoolExt.applyCode}" class="cf_input_text178">
                </td>
                <td >
                	<label class="lab">客户姓名：</label>
                	<input type="text" name="custName" value="${deductPoolExt.custName}" class="cf_input_text178">
                </td>
				<td>
					<label class="lab">到期日：</label>
			    	<input type="text" name="applyExpireDateStart" id="applyExpireDateStart" 
			    		value="${fns:getFormatDate(deductPoolExt.applyExpireDateStart,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDateEnd\',{d:-1});}'})"> -
			    	<input type="text" name="applyExpireDateEnd" id="applyExpireDateEnd" 
			    		value="${fns:getFormatDate(deductPoolExt.applyExpireDateEnd,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDateStart\',{});}'})">
				</td>
            </tr>
            <tr> 
			    <td>
			    	<label class="lab">计划出借日：</label>
			    	<input type="text" name="applyLendDateStart" id="applyLendDateStart" 
			    		value="${fns:getFormatDate(deductPoolExt.applyLendDateStart,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDateEnd\',{d:-1});}'})"> -
			    	<input type="text" name="applyLendDateEnd" id="applyLendDateEnd" 
			    		value="${fns:getFormatDate(deductPoolExt.applyLendDateEnd,'yyyy-MM-dd')}" 
			    		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDateStart\',{});}'})">
			    </td>
                <td>
                	<label class="lab">分天划扣日期：</label>
                	<input type="text"  name="applyDeductDateStart" id="applyDeductDateStart" 
                		value="${fns:getFormatDate(deductPoolExt.applyDeductDateStart,'yyyy-MM-dd')}" 
                		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDateEnd\',{d:-1});}'})"> -
                	<input type="text" name="applyDeductDateEnd" id="applyDeductDateEnd" 
                		value="${fns:getFormatDate(deductPoolExt.applyDeductDateEnd,'yyyy-MM-dd')}" 
                		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDateStart\',{});}'})">
                </td>
			   	<td>
			   		<label class="lab">营业部：</label>
			   		<sys:orgTree id="org" name="checkNode" value="${deductPoolExt.checkNode}" labelName="orgName"  labelValue="${deductPoolExt.orgName}" 
	                		cssClass="cf_input_text178"/>
			   	</td>
            </tr>
            <tr id="T1" style="display:none">
			    <td>
			    	<label class="lab">计划出借金额：</label>
			    	<input type="text" name="applyLendMoneyStart" value="${deductPoolExt.applyLendMoneyStart}" class="input_text70"> -
			    	<input type="text" name="applyLendMoneyEnd" value="${deductPoolExt.applyLendMoneyEnd}" class="input_text70">
			    </td>
				<td>
					<label class="lab">计划划扣金额：</label>
					<input type="text" name="applyDeductMoneyStart" value="${deductPoolExt.applyDeductMoneyStart}" class="input_text70"> -
					<input type="text" name="applyDeductMoneyEnd" value="${deductPoolExt.applyDeductMoneyEnd}" class="input_text70">
				</td>
                <td>
                	<label class="lab">付款方式：</label>
                	<select name="applyPay" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_pay_type}" var = "di">
                			<option value="${di.value }"
                				<c:if test="${fns:multiOption(deductPoolExt.applyPay,di.value)}">
									selected
								</c:if>
                			>${di.label}
                			</option>
                		</c:forEach>
                	</select>
                </td>
            </tr>
            <tr id="T2" style="display:none">
                <td>
                	<label class="lab">出借产品：</label>
                	<sys:productselect name="productCode" value="${deductPoolExt.productCode}" multiple="true"/>
                </td>
				<td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_deduct_plat}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${fns:multiOption(deductPoolExt.dictApplyDeductType,d.value)}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${fns:multiOption(deductPoolExt.accountBank,d.value)}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
			</tr>
			<tr id="T3" style="display:none">
				<td>
					<label class="lab">当日是否跳转：</label>
					<select name="thedayJumpFlag" class="select78" multiple="multiple">
                		<option value="">请选择</option>
                		<option value="0" <c:if test="${deductPoolExt.thedayJumpFlag eq '0'}">selected</c:if>>是</option>
                		<option value="1" <c:if test="${deductPoolExt.thedayJumpFlag eq '1'}">selected</c:if>>否</option>
                	</select>
				</td>
			</tr>
        </table>
        <div class="tright pr30 pb5 pt10">
	        <input type="submit" class="btn cf_btn-primary" value="搜索"/>
	        <input type="reset" class="btn_qx btn cf_btn-primary" value="清除"/>
	        <div class="xiala"  id="showMore" onclick="javascript:show();">
        	</div>
        </div>
	</form>
	</div>
    <sys:message content="${message}"/>
    <p class="mb10">
    	<auth:hasPermission key="cf:deductthedayfail:updeduct">
    		<button class="btn btn_sc ml10" id="upLineDeduct">线上划扣</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductthedayfail:downdeduct">
    		<button class="btn btn_sc<c:if test='${deductPoolExt.dictDeductStatus ne "3" }'></c:if>" id="downLineDeduct">线下划扣</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductthedayfail:join">
    		<button class="btn btn_sc" id="protocolLibrary">协议库对接</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductthedayfail:batchedit">
    		<button class="btn btn_sc" id="bathDeductBalance">批量修改状态</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductfail:bespokededuct">
    		<button class="btn btn_sc" id="bathDeductTheDay">预约划扣</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductthedayfail:deductfail">
    		<button class="btn btn_sc" id="confirmFail">确认划扣失败</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductthedayfail:qxbespokededuct">
    		<button class="btn btn_sc" id="cancelDeductBespoke">取消预约划扣</button>
    	</auth:hasPermission>
       <input type="hidden" id="oSumCount" value="${page.count }">
    <input type="hidden" id="oLendMoney" value="${fns:getFormatNumber(applyLendMoney ,'#0.00')}">
    <input type="hidden" id="oDeductMoney" value="${fns:getFormatNumber(actualDeductMoney ,'#0.00')}">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
  	&nbsp;&nbsp;总笔数：<label id="lendCount">${page.count }</label>笔&nbsp;&nbsp;&nbsp;&nbsp;
	出借总金额：￥<label id="lendMoney">${fns:getFormatNumber(applyLendMoney ,'#0.00')}</label>元&nbsp;&nbsp;&nbsp;&nbsp;
	划扣总金额：￥<label id="deductMoney">${fns:getFormatNumber(actualDeductMoney ,'#0.00')}</label>元</p>
   	<sys:columnCtrl pageToken="deduct_theDayProList1"></sys:columnCtrl>
   	<div class='box5'>
    <table class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
	        <tr>
	            <th><input type="checkbox" class="checkAll ml10 mr10" id="checkAll">全选</th>
	            <th>出借编号</th>
	            <th>客户姓名</th>
	            <th>营业部</th>
	            <th>出借产品</th>
	            <th>付款方式</th>
	            <th>划扣平台</th>
	            <th>出借银行</th>
	            <th>银行卡号</th>
	            <th>计划出借日期</th>
	            <th>计划划扣日期</th>
	            <th>分天划扣日期</th>
	            <th>计划出借金额</th>
	            <th>分天划扣金额</th>
	            <th>分天划扣失败金额</th>
	            <th>分天划扣成功金额</th>
	            <th>分天划扣未划金额</th>
				<th>划扣状态</th>
				<th>失败原因</th> 
				<th>预约日</th>
	            <th>到期日期</th>
	            <th>累计失败金额</th>
	            <th>当日跳转金额</th>
	            <th>操作</th>
	        </tr>
        </thead>
        <c:forEach items="${page.list }" var="deductPoolExt" varStatus="status">
	        <tr>
	            <td>
	            	<c:if test="${deductPoolExt.dictDeductStatus ne '16' and deductPoolExt.dictDeductStatus ne '17' and deductPoolExt.dictDeductStatus ne '5' and deductPoolExt.dictDeductStatus ne '18'}">
	            		<input type="checkbox" name="checkbox" id="checkOne" value="${deductPoolExt.id}_${deductPoolExt.verTime}" deductid="${deductPoolExt.deductApplyId}_${deductPoolExt.verTime}" lendcode="${deductPoolExt.applyCode }_${deductPoolExt.verTime}">
	            	</c:if>
	            	${status.count }
	            	<input id="lendMoneyH" type="hidden" value="${deductPoolExt.applyLendMoney }">
		            <input id="deductMoneyH" type="hidden" value="${deductPoolExt.actualDeductMoney }">
	            </td>
	            <td>${deductPoolExt.applyCode }</td>
	            <!-- 屏蔽客户姓名/手机号/身份证号 -->
	            <td>***</td>
	            <%-- <td>${deductPoolExt.custName }</td> --%>
	            <td>${deductPoolExt.orgName }</td>
	            <td>${deductPoolExt.productName }</td>
				<td>${fns:dictName(dicts.tz_pay_type,deductPoolExt.applyPay,'-')}</td>
	            <td>${fns:dictName(dicts.tz_deduct_plat,deductPoolExt.dictApplyDeductType,'-')}</td>
	            <td>${fns:dictName(dicts.tz_open_bank,deductPoolExt.accountBank,'-')}</td>
	            <td>${deductPoolExt.accountNo }</td>
	            <td>${fns:getFormatDate(deductPoolExt.applyLendDate,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatDate(deductPoolExt.applyDeductDate,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatDate(deductPoolExt.deductValidityDate,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.applyLendMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.actualDeductMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.failMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.deductSucceedMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.noDeductMoney ,'￥#,##0.00')}</td>
	            <td>${fns:dictName(dicts.tz_deduct_state,deductPoolExt.dictDeductStatus,'-') }</td>
	            <td>${deductPoolExt.confirmOpinion}</td>
	            <td>${fns:getFormatDate(deductPoolExt.bespokeDate,'yyyy-MM-dd')}</td>
	            <td>${fns:getFormatDate(deductPoolExt.applyExpireDate,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.thedayJumpFlag ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolExt.jumpAmount ,'￥#,##0.00')}</td>
	            <td class="tcenter">
	            	<auth:hasPermission key="cf:deductthedayfail:log">
			            <a href="javascript:void();">
			            	<input class="cf_btn_edit" type="button" id="fullFlowMark" name="${deductPoolExt.applyCode }" value="查看留痕">
			            </a>
			        </auth:hasPermission>
	            </td>
	        </tr>
       </c:forEach>
    </table>
</div>
    <div class="pagination">${page}</div>
    </div> 
    <div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">线下划扣</h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" class="btn cf_btn-primary" id="subClose" value="关闭">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
