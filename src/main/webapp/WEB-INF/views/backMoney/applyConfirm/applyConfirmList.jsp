<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/backMoneyCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/applyConfirm.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title>回款申请确认列表</title>
</head>
<body>
<div class="body_r">
    <form id="searchForm" action="${ctx}/myTodo/backMoney/applyConfirmList" method="post">
    <div class="box1 mb10">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class='lab'>客户编号：</label><input type="text" name="customerCode" value="${view.customerCode }" class="cf_input_text178"></td>
                <td><label class='lab'>客户姓名：</label><input type="text" name="customerName" value="${view.customerName }" class="cf_input_text178"></td>
				<td><label class='lab'>出借编号：</label><input type="text" name="lendCode" value="${view.lendCode }" class="cf_input_text178"></td>
            </tr>
            <tr> 
			    <td><label class="lab">营业部：</label> 
					<sys:orgTree id="org" name="orgID" value="${view.orgID}" labelName="orgName" labelValue="${view.orgName}" />
				</td>
                <td><label class='lab'>城市：</label><sys:cityselect name="city" value="${view.city}" multiple="true"/></td>
                <td><label class="lab">回款类型：</label> 
                	<select class="select78 mr34" id='backMoneyType' name='backMoneyType' multiple="multiple">
						<c:forEach items="${dicts.tz_back_type}" var='item'>
							<option value="${item.value }"<c:if test="${fns:multiOption(view.backMoneyType,item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
            <tr id="T1" style="display:none">
                <td><label class='lab'>计划出借日：</label>
                	<input type="text" name="applyLendDay" id="applyLendDay" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDayTo\',{d:-1});}'})" value="${fns:getFormatDate(view.applyLendDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyLendDayTo" id="applyLendDayTo" from-checkDate="#applyLendDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDay\',{});}'})" value="${fns:getFormatDate(view.applyLendDayTo,'yyyy-MM-dd')}"/>
                </td>
                <td><label class='lab'>到期日：</label>
                	<input type="text" name="finalLinitDate" id="finalLinitDate" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'finalLinitDateTo\',{d:-1});}'})" value="${fns:getFormatDate(view.finalLinitDate,'yyyy-MM-dd')}"/> -
                	<input type="text" name="finalLinitDateTo" id="finalLinitDateTo" from-checkDate="#finalLinitDate" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'finalLinitDate\',{});}'})" value="${fns:getFormatDate(view.finalLinitDateTo,'yyyy-MM-dd')}"/>
                </td>
				<td><label class='lab'>出借产品：</label><sys:productselect name="productCode" value="${view.productCode}" multiple="true"/></td>
            </tr>
            <tr id="T2" style="display:none">
                <td><label class='lab'>计划划扣日：</label>
                	<input type="text" name="applyDeductDay" id="applyDeductDay" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDayTo\',{d:-1});}'})" value="${fns:getFormatDate(view.applyDeductDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyDeductDayTo" id="applyDeductDayTo" from-checkDate="#applyDeductDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDay\',{});}'})" value="${fns:getFormatDate(view.applyDeductDayTo,'yyyy-MM-dd')}"/>	
                </td>
                <td><label class='lab'>出借金额：</label>
                	<input type="text" name="applyLendMoney" id="applyLendMoney" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" value="${view.applyLendMoney }" class="input_text70"> -
                	<input type="text" name="applyLendMoneyTo" numberWithoutComma="1" greaterThan="0" onblur="dateCheck(this)" from-checkInt="#applyLendMoney" value="${view.applyLendMoneyTo }" class="input_text70"></td>
                <td><label class="lab">付款方式：</label> 
                	<select class="select78 mr34" id='applyPay' name='applyPay' multiple="multiple">
						<c:forEach items="${dicts.tz_pay_type}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.applyPay,item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
			<tr id="T3" style="display:none">
                <td><label class='lab'>回款日期：</label>
                	<input type="text" name="backMoneyDay" id="backMoneyDay" class="Wdate input_text70" 
                		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'backMoneyDayTo\',{d:-1});}'})" value="${fns:getFormatDate(view.backMoneyDay,'yyyy-MM-dd')}"/> -
                	<input type="text" name="backMoneyDayTo" id="backMoneyDayTo" from-checkDate="#backMoneyDay" onblur="dateCheck(this)" class="Wdate input_text70" 
                		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'backMoneyDay\',{});}'})" value="${fns:getFormatDate(view.backMoneyDayTo,'yyyy-MM-dd')}"/>
                </td>
                <td><label class="lab">卡/折：</label> 
                	<select class="select78 mr34" id='accountCardOrBooklet' name='accountCardOrBooklet' multiple="multiple">
						<c:forEach items="${dicts.com_card_type}"
							var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.accountCardOrBooklet,item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">回款状态：</label> 
                	<select class="select78 mr34" id='dictBackStatus' name='dictBackStatus' multiple="multiple">
                		<!-- 待回款申请确认、待回款审批退回、待回款退回、待回款确认退回、已回款退回 、回款补息退回-->
						<c:forEach items="${dicts.tz_back_state}" var='item'>
							<c:if test="${fns:multiOption(backStatusOption,item.value) }">
								<option value="${item.value }"<c:if test="${fns:multiOption(view.dictBackStatus,item.value) }">selected</c:if>>
									${item.label }
								</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
            </tr>
			<tr id="T4" style="display:none">
                <td><label class="lab">合同版本号：</label> 
                	<select class="select78 mr34" id='applyAgreementEdition' name='applyAgreementEdition' multiple="multiple">
							<c:forEach items="${dicts.tz_contract_vesion}" var='item'>
								<option value="${item.value }" <c:if test="${fns:multiOption(view.applyAgreementEdition, item.value) }">selected</c:if>>
									${item.label }
								</option>
							</c:forEach>
					</select>
				</td>
                <td><label class="lab">渠道标识：</label> 
                	<select class="select78 mr34" id='dictFortunechannelflag' name='dictFortunechannelflag' multiple="multiple">
						<c:forEach items="${dicts.tz_channel_flag}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.dictFortunechannelflag, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">大金融审核日期：</label>
					<input type="text" name="approveDateStart" id="approveDateStart" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'approveDateEnd\',{d:-1});}'})"
						value="${fns:getFormatDate(view.approveDateStart,'yyyy-MM-dd')}" />-
					<input type="text" name="approveDateEnd" id="approveDateEnd" from-checkDate="#approveDateStart"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'approveDateStart\',{});}'})"
						value="${fns:getFormatDate(view.approveDateEnd,'yyyy-MM-dd')}" />
				</td>
            </tr>
            <tr id="T5" style="display:none">
            	<td>
					<label class="lab">补息天数：</label>
					<input name="interestDay" value="${view.interestDay}" type="text" class="cf_input_text178">
				</td>
				<td>
					<label class="lab">在职状态</label>
					<select class="select78 mr34" id='workingState' name='workingState' multiple="multiple">
						<c:forEach items="${dicts.tz_working_state}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.workingState,item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">自转审批日期：</label>
					<input type="text" name="zzApproveDateStart" id="zzApproveDateStart" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'zzApproveDateEnd\',{d:-1});}'})"
						value="${fns:getFormatDate(view.zzApproveDateStart,'yyyy-MM-dd')}" />-
					<input type="text" name="zzApproveDateEnd" id="zzApproveDateEnd" from-checkDate="#zzApproveDateStart"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'zzApproveDateStart\',{});}'})"
						value="${fns:getFormatDate(view.zzApproveDateEnd,'yyyy-MM-dd')}" />
				</td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input type="submit" id="search" onclick="resetPage()" value="搜索" class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
        </div>
    </div>
    <p class="mb10 ml10">
    	<auth:hasPermission key="cf:backmoneyapplyconfirm:batchbackmoneyapplyconfirm">
       		<input type="button" onclick="multiApplyConfirm();" class="btn btn_sc ml10" value="批量回款确认"/>
       	</auth:hasPermission>
       	<auth:hasPermission key="cf:backmoneyapplyconfirm:backmoneyapplydetail">
       		<input type="button" onclick="javascript:exportExcel('${eUrl}');" class="btn btn_sc" value="导出回款明细"/>
       	</auth:hasPermission>
       	<auth:hasPermission key="cf:backmoneyapplyconfirm:goldbackmoneyapplydetail">
       		<input type="button" onclick="javascript:exportExcel('${ejUrl}');" class="btn btn_sc" value="待金账户回款明细"/>
       	</auth:hasPermission>
       	<auth:hasPermission key="cf:backmoneyapplyconfirm:uploadbackmoney">
       		<input type="button" onclick="javascript:uploadExcel();" class="btn btn_sc" value="上传回款金额"/>
       	</auth:hasPermission>
       	
       	<input type="hidden" id="hTotalActualMoney" value="${fns:getFormatNumber(totalABM,'#0.00')}"/>
       	
       	<input type="hidden" id="hTotalSupplementedMoney" value="${fns:getFormatNumber(totalBXHM,'#0.00')}"/>
       	
       	<input type="hidden" id="hDataAmount" value="${page.count}"/>
	<label>
    	累计实际回款金额 ：￥ <label id="TotalActualMoney">${fns:getFormatNumber(totalABM,'#,##0.00')}</label>元    
    	累计补息后回款金额 ：￥ <label id="TotalSupplementedMoney">${fns:getFormatNumber(totalBXHM,'#,##0.00')}</label>元    
    	累计数量 ： <label id="dataAmount">${page.count}</label>条  
   	</label>
   	</p>
	<div class='box5'>	    	
		<sys:columnCtrl pageToken="backMoney_applyConfirm_applyConfirmList"></sys:columnCtrl>
	    <table id="listTable" class="table table-striped table-bordered table-condensed data-list-table">
	        <thead>
	        <tr>
	            <th>
	            	<span onclick="checkAll();" onclick="checkAll();">
				    	<input type="checkbox" id="checkAll" class=" checkAll">全选
				    </span>
				</th>
	            <th>客户编号</th>
	            <th>客户姓名</th>
	            <th>出借编号</th>
	            <th>计划出借日期</th>
	            <th>计划出借金额</th>
	            <th>出借方式</th>
	            <th>合同版本</th>
	            <th>账户省份|城市</th>
	            <th>营业部</th>
	            <th>理财经理</th>
            	<th>渠道标识</th>
	            <th>应回款金额</th>
				<th>实际回款金额</th>
				<th>补息后回款金额</th>
	            <th>回款状态</th>
	            <th>到期日期</th>
	            <th>回款日期</th>
	            <th>回款类型</th>
	            <th>失败原因</th>
	            <th>大金融审核日期</th>
				<th>补息天数</th>
				<th>在职状态</th>
				<th>自转审批日期</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <c:forEach items="${page.list}" var="item">
		        <tr>
		            <td><input type="checkbox" id="dataCheck" name="backmId" value="${item.backmId }" onclick="checkOne(this)"></td>
		            <td>${item.customerCode }</td>
		            <!-- 屏蔽客户姓名/手机号/身份证号 -->
					<td>***</td>
		            <%-- <td>${item.customerName }</td> --%>
		            <td>${item.lendCode }</td>
		            <td>${fns:getFormatDate(item.applyLendDay,"yyyy-MM-dd")}</td>
		            <td>${fns:getFormatNumber(item.applyLendMoney ,'￥#,##0.00')}</td>
		            <td>${item.productName }</td>
		            <td>${fns:dictName(dicts.tz_contract_vesion,item.applyAgreementEdition,'-') }</td>
		            <td>${item.accountAddrprovince}|${item.accountAddrcity}</td>
		            <td>${item.orgName }</td>
		            <td>${item.manager }</td>
		            <td>
		            	${fns:dictName(dicts.tz_channel_flag,item.dictFortunechannelflag,'-') }
		            </td>
		            <td>${fns:getFormatNumber(item.backMoney ,'￥#,##0.00')}</td>
		            <td>
		            	${fns:getFormatNumber(item.backActualbackMoney ,'￥#,##0.00')}
		            	<input type="hidden" name="itemBackActualbackMoney" value="${fns:getFormatNumber(item.backActualbackMoney ,'#0.00')}"/>
		            </td>
		            <!-- 补息后回款金额  -->
		            <td>
		            	${fns:getFormatNumber(item.supplementedMoney ,'￥#,##0.00')}
		            	<input type="hidden" name="itemSupplementedMoney" value="${fns:getFormatNumber(item.supplementedMoney ,'#0.00')}"/>
		            </td>
		            
		            <td>
		            	${fns:dictName(dicts.tz_back_state,item.dictBackStatus,'-') }
		            </td>
		            <td>${fns:getFormatDate(item.finalLinitDate ,"yyyy-MM-dd")}</td>
		            <td>${fns:getFormatDate(item.backMoneyDay,"yyyy-MM-dd")}</td>
		            <td>
		            	${fns:dictName(dicts.tz_back_type,item.backMoneyType,'-') }
		            </td>
		            <td>${fns:dictName(dicts.tz_back_reason,item.dictBackMoneyError,'-') }</td>
		            <td>${fns:getFormatDate(item.approveDate,"yyyy-MM-dd")}</td>
					<td>${item.interestDay}</td>
					<td>${fns:dictName(dicts.tz_working_state,item.workingState,'-' ) }</td>
		            <td>${fns:getFormatDate(item.zzApproveDate ,"yyyy-MM-dd")}</td>
		            <td class="tcenter">
		            	<auth:hasPermission key="cf:backmoneyapplyconfirm:transact">
			            	<a href="${ctx}/myTodo/backMoney/applyConfirmDetail?backmId=${item.backmId }">
			            		办理
			            	</a>
			            </auth:hasPermission>
		            </td>
		        </tr>
	        </c:forEach>
	    </table>
	   </div>
   </form>
    <div class="pagination">${page}</div>
 </div>
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