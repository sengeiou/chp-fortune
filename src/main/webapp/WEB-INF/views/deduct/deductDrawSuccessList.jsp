<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/deduct.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/countMoney.js"></script>
</head>
<body>
<div class="body_r">
    <form id="searchForm" action="${ctx}/deductSuccess/deductSuccessList" method="post">
       <div class="box1 mb10"> 	 
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
                	<label class="lab">计划划扣日期：</label>
                	<input type="text"  name="applyDeductDateStart" id="applyDeductDateStart" 
                		value="${fns:getFormatDate(deductPoolExt.applyDeductDateStart,'yyyy-MM-dd')}" 
                		class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDateEnd\',{d:-1});}'})"> -
                	<input type="text" name="applyDeductDateEnd" id="applyDeductDateEnd" 
                		value="${fns:getFormatDate(deductPoolExt.applyDeductDateEnd,'yyyy-MM-dd')}" 
                		class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDateStart\',{});}'})">
                </td>
			   	<td>
			   		<label class="lab">营业部：</label>
			   		<sys:orgTree id="org"  name="checkNode" value="${deductPoolExt.checkNode}" labelName="orgName"  labelValue="${deductPoolExt.orgName}"/>
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
					<label class="lab">分天状态：</label>
					<select name="dayDeductFlag" class="select78">
						<option value="">请选择</option>
						<c:if test="${deductPoolExt.dayDeductFlag eq 1}"> selected</c:if>
						<option value="1" <c:if test="${deductPoolExt.dayDeductFlag eq '1'}"> selected</c:if>>分天划扣</option>
						<option value="0" <c:if test="${deductPoolExt.dayDeductFlag eq '0'}"> selected</c:if>>单天划扣</option>
                	</select>				
				</td>
			</tr>
			<tr id="T3" style="display:none">
				<td>
					<label class="lab">收款确认书发送状态：</label>
					<select name="sendEmailStatus" class="select78">
						<option value="">请选择</option>
                		<c:forEach items="${dicts.com_email_state}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${fns:multiOption(deductPoolExt.sendEmailStatus,d.value)}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">收款确认书合成状态：</label>
					<select name="makeFlieStatus" class="select78">
						<option value="">请选择</option>
                		<c:forEach items="${dicts.tz_filecp_state}" var="d">
                			<option value="${d.value}" 
                				<c:if test="${fns:multiOption(deductPoolExt.makeFlieStatus,d.value)}">
                					selected
                				</c:if> 
                			>${d.label}</option>
                		</c:forEach>
                	</select>		
				</td>
				<td>
                </td>
			</tr>
        </table>
        <div class="tright pr30 pb5 pt10">
	        <input type="submit" class="btn cf_btn-primary" value="搜索"/>
	        <input type="reset" class="btn cf_btn-primary"  value="清除"/>
	        <div class="xiala" id="showMore" onclick="javascript:show();">
        	</div>
        </div>
        </div>
	</form>
    <sys:message content="${message}"/>
    <p class="mb10 ml10">
    	<!-- 非数据管理部  start-->
    	<auth:hasPermission key="cf:deductsuccess.batchsend">
    		<button class="btn btn_sc"  type="pdf" id="batchSendFilePdf">批量发送收款确认书</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductsuccess.batchdownloadpdf">
			<button class="btn btn_sc"  type="pdf" id="batchFileDownload">批量下载收款确认书pdf</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccess.batchdownloadword">
			<button class="btn btn_sc"  type="doc" id="batchFileDownload">批量下载收款确认书word</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccess.exportexcel">
			<button class="btn btn_sc"  id="exportExcel">导出excel</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccess.exportback">
			<button class="btn btn_sc"  id="exportTableExcel">导出回访表</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccess.textcompose">
	        <button class="btn btn_sc"  id="FilSynthesis">文件合成</button>
	    </auth:hasPermission>
	    <auth:hasPermission key="cf:deductsuccess.exportgold">
	        <button class="btn btn_sc"  id="exporExcelGold">导出金账户</button>
	    </auth:hasPermission>
	    <!-- 非数据管理部  end-->
	    <!-- 数据管理部  start-->
    	<auth:hasPermission key="cf:deductsuccessmath.batchsend">
    		<button class="btn btn_sc"  type="pdf" id="batchSendFilePdf">批量发送收款确认书</button>
    	</auth:hasPermission>
    	<auth:hasPermission key="cf:deductsuccessmath.batchdownloadpdf">
			<button class="btn btn_sc"  type="pdf" id="batchFileDownload">批量下载收款确认书pdf</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccessmath.batchdownloadword">
			<button class="btn btn_sc"  type="doc" id="batchFileDownload">批量下载收款确认书word</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccessmath.exportexcel">
			<button class="btn btn_sc"  id="exportExcel">导出excel</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccessmath.exportback">
			<button class="btn btn_sc"  id="exportTableExcel">导出回访表</button>
		</auth:hasPermission>
		<auth:hasPermission key="cf:deductsuccessmath.textcompose">
	        <button class="btn btn_sc"  id="FilSynthesis">文件合成</button>
	    </auth:hasPermission>
	    <auth:hasPermission key="cf:deductsuccessmath.exportgold">
	        <button class="btn btn_sc"  id="exporExcelGold">导出金账户</button>
	    </auth:hasPermission>
	    <!-- 数据管理部  end-->
	    
    	<input type="hidden" id="oSumCount" value="${page.count }">
    	<input type="hidden" id="oLendMoney" value="${fns:getFormatNumber(sumMoney.applylendmoney ,'#0.00')}">
		<input type="hidden" id="oDeductMoney" value="${fns:getFormatNumber(sumMoney.applydeductmoney ,'#0.00')}">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
    &nbsp;&nbsp;总笔数：<label id="lendCount">${page.count }</label>笔&nbsp;&nbsp;&nbsp;&nbsp;
	出借总金额：<label id="lendMoney">${fns:getFormatNumber(sumMoney.applylendmoney ,'#0.00')}</label>元&nbsp;&nbsp;&nbsp;&nbsp;
	划扣总金额：<label id="deductMoney">${fns:getFormatNumber(sumMoney.applydeductmoney ,'#0.00')}</label>元</p>
    
	<sys:columnCtrl pageToken="deduct_deductDrawSuccessList"></sys:columnCtrl>
	<div class="box5">
	    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
	        <thead>
	        <tr>
	            <th><input type="checkbox" class="checkAll ml10 mr10" id="checkAll">全选</th>
	            <th>出借编号</th>
	            <th>客户姓名</th>
	            <th>营业部</th>
	            <th>金帐户名</th>
	            <th>出借产品</th>
	            <th>付款方式</th>
	            <th>划扣平台</th>
	            <th>出借银行</th>
	            <th>出借日期</th>
	            <th>划扣日期</th>
	            <th>邮件状态</th>
	            <th>合成状态</th>
	            <th>出借金额</th>
	            <th>划扣金额</th>
	            <th>到期日期</th>		
	            <th>实际划扣日</th>
	            <th>划扣状态</th>
	            <th>分天状态</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list }" var="deductPoolExt" varStatus="status">
		        <tr>
			        <td>
<%-- 		            	<c:if test="${deductPoolExt.dictDeductStatus eq '6' }"> --%>
		            		<input type="checkbox" name="checkbox" value="${deductPoolExt.applyCode}">
<%-- 		            	</c:if> --%>
		            	${status.count }
		            <input id="lendMoneyH" type="hidden" value="${deductPoolExt.applyLendMoney }">
		            <input id="deductMoneyH" type="hidden" value="${deductPoolExt.applyDeductMoney }">
		            </td>
		            <td>${deductPoolExt.applyCode }</td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->
		            <td>***</td>
		            <%-- <td>${deductPoolExt.custName }</td> --%>
		            <td>${deductPoolExt.orgName }</td>
		            <td>${fns:formatPhone(deductPoolExt.goldAccounName)}</td>
		            <td>${deductPoolExt.productName }</td>
		            <td>${fns:dictName(dicts.tz_pay_type,deductPoolExt.applyPay,'-')}</td>
		            <td>${fns:dictName(dicts.tz_deduct_plat,deductPoolExt.dictApplyDeductType,'-')}</td>
		            <td>${fns:dictName(dicts.tz_open_bank,deductPoolExt.accountBank,'-')}</td>
		            <td>${fns:getFormatDate(deductPoolExt.applyLendDate ,'yyyy-MM-dd')}</td>
		            <td>${fns:getFormatDate(deductPoolExt.applyDeductDate ,'yyyy-MM-dd')}</td>
		            
		            <td>${fns:dictName(dicts.com_email_state,deductPoolExt.sendEmailStatus,'-')}</td>
		            <td>${fns:dictName(dicts.tz_filecp_state,deductPoolExt.makeFlieStatus,'-')}</td>
		            
		            <td>${fns:getFormatNumber(deductPoolExt.applyLendMoney ,'￥#,##0.00')}</td>
		            <td>${fns:getFormatNumber(deductPoolExt.actualDeductMoney ,'￥#,##0.00')}</td>
		            <td>${fns:getFormatDate(deductPoolExt.applyExpireDate ,'yyyy-MM-dd')}</td>
		            <td>${fns:getFormatDate(deductPoolExt.dealTime ,'yyyy-MM-dd')}</td>
		            <td>${fns:dictName(dicts.tz_deduct_state,deductPoolExt.dictDeductStatus,'-')}</td>
			            <c:choose>
			            	<c:when test="${deductPoolExt.dayDeductFlag eq '1' }">
			            		<td>分天划扣</td>
			            	</c:when>
			            	<c:otherwise>
			            		<td>单天划扣</td>
			            	</c:otherwise>
			            </c:choose>
		            <td class="tcenter">
		            	<!-- 非数据管理部 -->
			            <auth:hasPermission key="cf:deductsuccess.transact">
				            <a href="${ctx}/deductSuccess/conductSuccess?applyCode=${deductPoolExt.applyCode }">
				            	<button class="cf_btn_edit" onclick="">办理</button>
				            </a>
				        </auth:hasPermission>
				        <!-- 数据管理部 -->
				        <auth:hasPermission key="cf:deductsuccessmath.transact">
				            <a href="${ctx}/deductSuccess/conductSuccess?applyCode=${deductPoolExt.applyCode }">
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
</body>
</html>
