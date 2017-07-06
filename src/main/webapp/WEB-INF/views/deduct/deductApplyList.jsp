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
    <div class="box1 mb10">
    <form id="searchForm" 
	     <c:choose>
	    	<c:when test="${deductPoolExt.applyOrApproveFalg eq 'approve'}">
	    		action="${ctx}/deductApprove/approveList" 
	    	</c:when>
	    	<c:otherwise>
	    		action="${ctx}/deductApply/applyList" 
	    	</c:otherwise>
	    </c:choose>
    method="post">
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
					<label class="lab">账单发送状态：</label>
					<select class="select78" name="dictMatchingFilesendStatus">
						<option value="">请选择</option>
						<c:forEach items="${dicts.com_email_state}" var='d'>
							<option value="${d.value}" 
								<c:if test="${d.value eq deductPoolExt.dictMatchingFilesendStatus }">
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
			   		<sys:orgTree id="org"  name="checkNode" value="${deductPoolExt.checkNode}" labelName="orgName"  labelValue="${deductPoolExt.orgName}" />
			   	</td>
            </tr>
            <tr id="T1" style="display:none">
			    <td>
			    	<label class="lab">计划出借金额：</label>
			    	<input type="text" name="applyLendMoneyStart" value="${deductPoolExt.applyLendMoneyStart}" class="input_text70" onkeyup="this.value=this.value.replace(/\D/g,'')"> -
			    	<input type="text" name="applyLendMoneyEnd" value="${deductPoolExt.applyLendMoneyEnd}" class="input_text70" onkeyup="this.value=this.value.replace(/\D/g,'')">
			    </td>
				<td>
					<label class="lab">计划划扣金额：</label>
					<input type="text" name="applyDeductMoneyStart" value="${deductPoolExt.applyDeductMoneyStart}" class="input_text70" onkeyup="this.value=this.value.replace(/\D/g,'')"> -
					<input type="text" name="applyDeductMoneyEnd" value="${deductPoolExt.applyDeductMoneyEnd}" class="input_text70" onkeyup="this.value=this.value.replace(/\D/g,'')">
				</td>
                <td>
                	<label class="lab">付款方式：</label>
                	<select name="applyPay" class="select78"  multiple="multiple">
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
                	<sys:productselect name="productCode" value="${deductPoolExt.productCode}"  multiple="true"/>
                </td>
				<td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78"  multiple="multiple">
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
					<select name="accountBank" class="select78"  multiple="multiple">
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
        </table>
        <div class='tright pr30 pt10 pb5'>
            <input class="btn cf_btn-primary" type="submit" value="搜索"/>
            <input type="reset" class="btn cf_btn-primary" value="清除">
            <div class="xiala" id="showMore" onclick="javascript:show();"></div>
        </div>
	   </form>
	   </div>
    <sys:message content="${message}"/>
    <p class="mb10">
    <c:choose>
    	<c:when test="${deductPoolExt.applyOrApproveFalg eq 'approve'}">
    		<auth:hasPermission key="cf:deductapprove:batchdeductapprove">
    			<button class="btn btn_sc ml10" id="bathApprove">批量审批</button>
    		</auth:hasPermission>
    		<auth:hasPermission key="cf:deductapprove:exportexcel">
    			<button class="btn btn_sc " id="expExcel">导出excel</button>
    		</auth:hasPermission>
    	</c:when>
    	<c:otherwise>
    		<auth:hasPermission key="cf:deductapply:batchsendborrow">
    			<button class="btn btn_sc ml10" id="bathsendfile">批量发送债权</button>
    		</auth:hasPermission>
    		<auth:hasPermission key="cf:deductapply:batchdeductapply">
    			<button class="btn btn_sc" id="bathdeductapply">批量划扣申请</button>
    		</auth:hasPermission>
    	</c:otherwise>
    </c:choose>
    <input type="hidden" id="oSumCount" value="${page.count }">
    <input type="hidden" id="oLendMoney" value="${fns:getFormatNumber(sumMoney.applylendmoney ,'##0.00')}">
    <input type="hidden" id="oDeductMoney" value="${fns:getFormatNumber(sumMoney.applydeductmoney ,'##0.00')}">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
    &nbsp;&nbsp;总笔数：<label id="lendCount">${page.count }</label>笔&nbsp;&nbsp;&nbsp;&nbsp;
	出借总金额：￥<label id="lendMoney">${fns:getFormatNumber(sumMoney.applylendmoney ,'##0.00')}</label>元&nbsp;&nbsp;&nbsp;&nbsp;
	划扣总金额：￥<label id="deductMoney">${fns:getFormatNumber(sumMoney.applydeductmoney ,'##0.00')}</label>元</p>
    <sys:columnCtrl pageToken="deduct_deductApplyList"></sys:columnCtrl>
    <div class='box5'>
    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
        <thead>
        <tr>
            <th><input type="checkbox" class="ml10 mr10 checkAll" id="checkAll">全选</th>
            <th>出借编号</th>
            <th>客户姓名</th>
            <th>营业部</th>
            <th>出借产品</th>
			<th>账单发送状态</th>
            <th>付款方式</th>
            <th>划扣平台</th>
            <th>出借银行</th>
            <th>计划出借日期</th>
            <th>计划划扣日期</th>
            <th>计划出借金额</th>
            <th>划扣金额</th>
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
				<td>${fns:dictName(dicts.com_email_state,deductPoolEx.dictMatchingFilesendStatus,'-')}</td>
	            <td>${fns:dictName(dicts.tz_pay_type,deductPoolEx.applyPay,'-')}</td>
	            <td>${fns:dictName(dicts.tz_deduct_plat,deductPoolEx.dictApplyDeductType,'-')}</td>
	            <td>${fns:dictName(dicts.tz_open_bank,deductPoolEx.accountBank,'-')}</td>
	            <td>${fns:getFormatDate(deductPoolEx.applyLendDate,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatDate(deductPoolEx.applyDeductDate ,"yyyy-MM-dd")}</td>
	            <td>${fns:getFormatNumber(deductPoolEx.applyLendMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatNumber(deductPoolEx.applyDeductMoney ,'￥#,##0.00')}</td>
	            <td>${fns:getFormatDate(deductPoolEx.applyExpireDate,"yyyy-MM-dd")}</td>
	            <td class="tcenter">
	                <c:choose>
				    	<c:when test="${deductPoolExt.applyOrApproveFalg eq 'approve'}">
				    		<auth:hasPermission key="cf:deductapprove:transact">
					    	 	<a href="${ctx}/deductApprove/conductApprove?applyCode=${ deductPoolEx.applyCode }">
					    	 		<button class="cf_btn_edit" onclick="">办理</button>
			            		</a>
			            	</auth:hasPermission>
				    	</c:when>
				    	<c:otherwise>
				    		<auth:hasPermission key="cf:deductapply:ransact">
				    			<a href="${ctx}/deductApply/conduct?applyCode=${ deductPoolEx.applyCode }">
								    <button class="cf_btn_edit" onclick="">办理</button>
								</a>
				    		</auth:hasPermission>
				    	</c:otherwise>
				    </c:choose>
	            </td>
	        </tr>
        </c:forEach>
    </table>
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
