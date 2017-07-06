<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/deduct.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/countMoney.js"></script>
<script type="text/javascript">
	var remindFlag = "${remindFlag}";
	$("input[type='submit']").click(function(){
		$form = $('#searchForm',iframe);
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}
	});
</script>
</head>
<body>
<div class="body_r">
		<form id="searchForm" 
		 <c:choose>
           	<c:when test="${remindFlag eq 'das'}">
           		action="${ctx}/remindDays/timeToRemindList"  
           	</c:when>
           	<c:when test="${remindFlag eq '30das'}">
           		action="${ctx}/remindBefore30Days/advance30TimeToRemindList"  
           	</c:when>
           	<c:otherwise>
           		action="${ctx}/remindBefore10Days/advance10TimeToRemindList"  
           	</c:otherwise>
	     </c:choose>
		method="post" >
		<div class="box1 mb10">
	          	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="lendCodes" name="lendCodes" type="hidden" />
	          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	            <tr>
	                <td>
		                <label class="lab">出借编号：</label>
		                <input type="text" name="loanCode" value="${smsCfSendListExt.loanCode}" class="cf_input_text178">
		            </td>
	                <td>
	                	<label class="lab">客户姓名：</label>
	                	<input type="text" name="customerName" value="${smsCfSendListExt.customerName }" class="cf_input_text178">
	                </td>
	                <td>
	                	<label class="lab">所在城市：</label>
	                	<sys:cityselect name="areaNames" groupDisabled="true" value="${smsCfSendListExt.areaNames}" />
	                </td>
	            </tr>
	            <tr>
	                <td>
	                	<label class="lab">出借金额：</label>
	                	<input type="text" name="lendMoney" number="1" value="${smsCfSendListExt.lendMoney }" class="cf_input_text178"></td>
	                <td>
		               	<label class="lab">营业部：</label>
				   		<sys:orgTree id="org"  name="orgId" value="${smsCfSendListExt.orgId}" labelName="orgName"  labelValue="${smsCfSendListExt.orgName}" />
	                </td>
	                <td>
	                	<label class="lab">到期日：</label>
		                <input type="text" name="dueDayStart" id="dueDayStart" class="Wdate input_text70" 
			                onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'dueDayEnd\',{d:-1});}'})" 
			                value="${fns:getFormatDate(smsCfSendListExt.dueDayStart ,'yyyy-MM-dd')}"/> -
		                <input type="text" name="dueDayEnd" class="Wdate input_text70" id="dueDayEnd" 
		                	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'dueDayStart\',{});}'})" 
		                	value="${fns:getFormatDate(smsCfSendListExt.dueDayEnd, 'yyyy-MM-dd')}" />
	                </td>
	            </tr>
	            <tr id="T1" style="display:none">
	                <td>
	                	<label class="lab">付款方式：</label>
	                	<select name="dictPayType" class="select78" multiple="multiple">
	                		<c:forEach items="${dicts.tz_pay_type}" var = "di">
	                			<option value="${di.value }"
									<c:if test="${fns:multiOption(smsCfSendListExt.dictPayType,di.value)}">
										selected
									</c:if>
	                			>${di.label}
	                			</option>
	                		</c:forEach>
	                	</select>
	                </td>
	                <td>
	                	<label class="lab">出借产品：</label>
	                	<sys:productselect name="productCode" value="${smsCfSendListExt.productCode}" multiple="true"/>
	                </td>
	                <c:if test="${remindFlag eq '30das'}">
		                <td>
		                	<label class="lab">封闭期满日：</label>
			                <input type="text" name="productClosedateStart" id="productClosedateStart" 
			                class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'productClosedateEnd\',{d:-1});}'})" 
			                value="${fns:getFormatDate(smsCfSendListExt.productClosedateStart ,'yyyy-MM-dd')}"> -
			                <input type="text" name="productClosedateEnd" class="Wdate input_text70" 
			                id="productClosedateEnd" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'productClosedateStart\',{});}'})" 
			                value="${fns:getFormatDate(smsCfSendListExt.productClosedateEnd ,'yyyy-MM-dd')}" />
		                </td>
	                </c:if>
	            
	             <c:if test="${remindFlag eq 'das'}">
	                <td>
					<label class="lab">回款状态：</label> 
                	<select class="select78 mr34" id='dictBackStatus' name='backStatus' multiple="multiple">
                		<!-- 待回款申请确认、待回款审批退回、待回款退回、待回款确认退回、已回款退回 -->
						<c:forEach items="${dicts.tz_back_state}" var='item'>
								<option value="${item.value }"<c:if test="${fns:multiOption(smsCfSendListExt.backStatus,item.value) }">selected</c:if>>
									${item.label }
								</option>
						</c:forEach>
					</select>
					</td>
	               </c:if> 
	                </tr>
	             </table>
	           <div class='tright pr30 pb5 pt10'>
	               <input type="submit" class="btn cf_btn-primary" value="搜索"/>
	               <input type="reset" class="btn cf_btn-primary" value="清除"/>
	               <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
             </div>
             </div>
	    </form>
	    <c:choose>
	    	<c:when test="${remindFlag eq 'das'}">
			    <auth:hasPermission key="cf:remind:exportdetail">
			    	<button class="btn btn_sc ml10 mb10" id="outExcel">导出明细</button>
			    </auth:hasPermission>
			</c:when>
			<c:when test="${remindFlag eq '30das'}">
			    <!-- 30天到期提醒 -->
			    <auth:hasPermission key="cf:remind30:exportdetail">
			    	<button class="btn btn_sc ml10 mb10" id="outExcel">导出明细</button>
			    </auth:hasPermission>
			</c:when>
			<c:otherwise>
			    <!-- 10天到期提醒 -->
			    <auth:hasPermission key="cf:remind10:exportdetail">
			    	<button class="btn btn_sc ml10 mb10" id="outExcel">导出明细</button>
			    </auth:hasPermission>
			</c:otherwise>
		</c:choose>
	<input type="hidden" id="oSumCount" value="${page.count }">
<%--     <input type="hidden" id="oLendMoney" value="${sumMoney.applylendmoney }"> --%>
    <input type="hidden" id="oLendMoney" value="${sumMoney.lendmoney }">
    <input type="hidden" id="oDeductMoney" value="${sumMoney.applydeductmoney }">
    
    <input type="hidden" id="totalCount" value="0">
    <input type="hidden" id="totalLendMoney" value="0">
    <input type="hidden" id="totalDeductMoney" value="0">
    	&nbsp;&nbsp;累计借出金额：￥<label id="lendMoney">${sumMoney.lendmoney }</label>元
 
    <div >
    	<c:if test="${pageTokenId==10 }">   
    		<sys:columnCtrl pageToken="remind_advance10TimeToRemindList10"></sys:columnCtrl>    
   		</c:if> 
   		<c:if test="${pageTokenId==30 }">   
    		<sys:columnCtrl pageToken="remind_advance10TimeToRemindList30"></sys:columnCtrl>   
   		</c:if> 
   		<c:if test="${pageTokenId==1  }">   
    		<sys:columnCtrl pageToken="remind_advance10TimeToRemindList1"></sys:columnCtrl> 
    	</c:if>
    	<div class="box5">
		    <table cellspacing="0" cellpadding="0" border="0"  class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		        <thead>
		        <tr>
		            <th><input type="checkbox" class="checkAll" id="checkAll"/>全选</th>
		            <th>客户编号</th>
		            <th>客户姓名</th>
		            <th>出借编号</th>
		            <th>计划出借日期</th>
		            <th>计划出借金额</th>
		            <th>出借方式</th>
		            <th>付款方式</th>
		            <th>所在城市</th>
		            <th>营业部</th>
		            <th>到期日期</th>
		            <c:if test="${remindFlag eq '30das'}">
		             	<th>封闭期满日</th>
		            </c:if>
		            <th>创建时间</th>
		            <th>理财经理</th>
		            <c:if test="${remindFlag eq 'das'}">
		             	<th>回款状态</th>
		            </c:if>
		            <th>操作</th>
		        </tr>
		        </thead>
		        <c:forEach items="${page.list}" var="smsCfSendListExt" varStatus="status">
			        <tr>
			            <td>
			            	<input type="checkbox" name="checkbox" value="${smsCfSendListExt.loanCode}"/>
			            	${status.count }
			            	<input type="hidden" id="lendMoneyH" value="${smsCfSendListExt.lendMoney}"/>
			            	<input id="deductMoneyH" type="hidden" value="0">
			            </td>
			            <td>${smsCfSendListExt.customerCode }</td>
			            <!-- 屏蔽客户姓名/手机号/身份证号 -->
			            <td>***</td>
			            <%-- <td>${smsCfSendListExt.customerName }</td> --%>
			            <td>${smsCfSendListExt.loanCode }</td>
			            <td>${fns:getFormatDate(smsCfSendListExt.lendDay,'yyyy-MM-dd')}</td>
			            <td>${fns:getFormatNumber(smsCfSendListExt.lendMoney,'￥#,##0.00')}</td>
			            <td>${smsCfSendListExt.lendType }</td>
			            <td>${fns:dictName(dicts.tz_pay_type,smsCfSendListExt.payType,'-')}</td>
			            <td>${fns:getCityLabel(smsCfSendListExt.areaName)}</td>
			            <td>${smsCfSendListExt.orgName }</td>
			            <td>${fns:getFormatDate(smsCfSendListExt.dueDay,'yyyy-MM-dd')}</td>
			            <c:if test="${remindFlag eq '30das'}">
		             		<td>${fns:getFormatDate(smsCfSendListExt.productClosedates,'yyyy-MM-dd')}</td>
		            	</c:if>
			            <td>${fns:getFormatDate(smsCfSendListExt.createTime,"yyyy-MM-dd")}</td>
			            <td>${smsCfSendListExt.wealthManager }</td>
		  	            <c:if test="${remindFlag eq 'das'}">
						<td>${fns:dictName(dicts.tz_back_state,smsCfSendListExt.backStatus,'-')}</td>
		            	</c:if>  
			            <td>
			            <c:choose>
			            	<c:when test="${remindFlag eq 'das'}">
			            		<auth:hasPermission key="cf:remind:transact">
			            			<button class="cf_btn_edit" onclick="window.location='${ctx}/remindDays/timeToRemindDetail?loanCode=${smsCfSendListExt.loanCode}'">详细</button>
			            		</auth:hasPermission>
			            	</c:when>
			            	<c:when test="${remindFlag eq '30das'}">
			            		<auth:hasPermission key="cf:remind30:transact">
			            			<button class="cf_btn_edit" onclick="window.location='${ctx}/remindBefore30Days/advance30TimeToRemindDetail?loanCode=${smsCfSendListExt.loanCode}'">详细</button>
			            		</auth:hasPermission>
			            	</c:when>
			            	<c:otherwise>
			            		<auth:hasPermission key="cf:remind10:transact">
			            			<button class="cf_btn_edit" onclick="window.location='${ctx}/remindBefore10Days/advance10TimeToRemindDetail?loanCode=${smsCfSendListExt.loanCode}'">详细</button>
			            		</auth:hasPermission>
			            	</c:otherwise>
			            </c:choose>
			            </td>
			        </tr>
		        </c:forEach>
		    </table>
		</div>
    </div>
    
	<div class="pagination">${page}</div>
</div>
</body>
</html>
