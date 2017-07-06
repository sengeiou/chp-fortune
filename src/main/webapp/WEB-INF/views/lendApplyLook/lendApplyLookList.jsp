<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style='overflow:-Scroll;overflow-y:hidden'>
<head>
<meta name="decorator" content="default"/>
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/colorbox.css" />
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/lendApplyLook/lendApplyLookList.js"></script>

</head>
<body>
<div class="body_r">
    <div class="box1 mb10">
    <form id="searchForm" name="searchForm" action="${ctx}/lendApplyLook/loadLendApplyLookList" method="post">
    	<input id="pageNo" name="pageNo" type="hidden" value="${llov.page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${llov.page.pageSize}"/>
		<input id="lendCodeHidden" name="lendCodeList" type="hidden"/>
        <input id="hiddenMoney" type="hidden" value="${llov.sum}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${llov.lso.customerCode}"/></td>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName" value="${llov.lso.customerName}"/></td>
                <td><label class="lab">理财经理：</label><input type="text" class="cf_input_text178"name="managerCode" value="${llov.lso.managerCode}"/></td>
            </tr>
            <tr>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" name="lendCode" value="${llov.lso.lendCode}"/></td>
                <td><label class="lab">营业部：</label><sys:orgTree id="org" name="orgCode" value="${llov.lso.orgCode}" labelValue="${llov.lso.orgName}" labelName="orgName"/></td>
                <td><label class="lab">团队经理：</label><input type="text" class="cf_input_text178" name="teamManagerName" value="${llov.lso.teamManagerName}"/></td>									
            </tr>
            <tr id='T1' style='display:none;'>
                <td><label class="lab">城      市：</label><sys:cityselect name="cityId" value="${llov.lso.cityId}" /></td>
                <td><label class="lab">计划出借日：</label>
                	<input type="text" name="applyLendDayFrom" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(llov.lso.applyLendDayFrom,'yyyy-MM-dd')}"/> -
                	<input type="text" name="applyLendDayTo" class="Wdate input_text70" onfocus="WdatePicker()" value="${fns:getFormatDate(llov.lso.applyLendDayTo,'yyyy-MM-dd')}"/></td>                									
            	<td><label class="lab">到期日：</label>
	       			<input type="text" name="applyExpireDayFrom" value="${fns:getFormatDate(llov.lso.applyExpireDayFrom,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker()"/> -
	       			<input type="text" name="applyExpireDayTo" value="${fns:getFormatDate(llov.lso.applyExpireDayTo,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker()"/></td>
            </tr>
            <tr id='T2' style='display:none;'>
                <td><label class="lab">计划划扣日：</label>
					<input type="text" name="applyDeductDayFrom"  class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.applyDeductDayFrom,'yyyy-MM-dd')}" onfocus="WdatePicker()"/> -
					<input type="text" name="applyDeductDayTo" class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.applyDeductDayTo,'yyyy-MM-dd')}" onfocus="WdatePicker()"/></td>
            	<td><label class="lab">出借金额：</label>
                	<input type="text" name="applyLendMoneyFrom"  class="input_text70" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')" value="${llov.lso.applyLendMoneyFrom}"/> -
                	<input type="text" name="applyLendMoneyTo"  class="input_text70" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')" value="${llov.lso.applyLendMoneyTo}"/></td>
            	<td><label class="lab">状   态：</label><select class="select78" id="status" name="status" >
                         <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_for_apply_status}" var="item">
                             <option value="${item.value}" <c:if test="${fns:multiOption(llov.lso.status, item.value)}">selected</c:if>>${item.label}</option>
                        </c:forEach>
                    </select></td>
             </tr>
             <tr id='T3' style='display:none;'>
                <td><label class="lab">完结状态：</label><select class="select78" id="dictApplyEndState" name="dictApplyEndState"  >
                        <option value="">请选择</option>
                        <c:forEach items="${dicts.tz_finish_state}" var="item">
                             <option value="${item.value }" <c:if test="${fns:multiOption(llov.lso.dictApplyEndState, item.value)}">selected</c:if>>${item.label}</option>
                        </c:forEach>
                    </select></td>
               	<td><label class="lab">出借状态：</label><select class="select78" id="lendStatus" name="lendStatus" multiple="multiple">
                        <c:forEach items="${dicts.tz_lend_state}" var="item">
										<option value="${item.value }"
											<c:if test="${fns:multiOption(llov.lso.lendStatus, item.value)}">selected</c:if>>${item.label }</option>
								</c:forEach>
                    </select></td>
				<td><label class="lab">账单日：</label><select class="select78" id="applyBillday" name="applyBillday" multiple="multiple">
                        <c:forEach items="${dicts.tz_bill_day}" var="item">
                             <option value="${item.value }" <c:if test="${fns:multiOption(llov.lso.applyBillday, item.value)}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr id='T4' style='display:none;'>

				<td><label class="lab">付款方式：</label><select class="select78" id="applyPay" name="applyPay" multiple="multiple">
                        <c:forEach items="${dicts.tz_pay_type}" var="item">
                             <option value="${item.value }" <c:if test="${fns:multiOption(llov.lso.applyPay, item.value)}">selected</c:if>>${item.label }</option>
                        </c:forEach>
                    </select></td>
				<td><label class="lab">出借产品：</label>
						<sys:productselect name="productCode" value="${llov.lso.productCode}" multiple="true"/></td>
			    <%-- <td><label class="lab">开户状态：</label>
			    	<select name="hostedStatus" class="select78" multiple="multiple">
							<c:forEach items="${dicts.tz_trust_state}" var="item">
								<c:if test="${item.label == '申请中' or item.label == '开户失败'}">
									<option value="${item.value }" <c:if test="${fns:multiOption(llov.lso.hostedStatus, item.value)}">selected</c:if> >${item.label }</option>
								</c:if>
							</c:forEach>
					</select>
			    </td> --%>
			    <td>
					<label class="lab">回款日期：</label>
					<input type="text" name="backMoneyDayBegin"  class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.backMoneyDayBegin,'yyyy-MM-dd')}" onfocus="WdatePicker()"/> -
					<input type="text" name="backMoneyDayEnd" class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.backMoneyDayEnd,'yyyy-MM-dd')}" onfocus="WdatePicker()"/></td>
				</td>
            </tr>
             <tr id='T5' style='display:none;'>
				<td>
					<label class="lab">划扣平台：</label>
					<select name="dictApplyDeductType" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_deduct_plat}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(llov.lso.dictApplyDeductType,item.value)}">selected</c:if>>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">出借银行：</label>
					<select name="accountBank" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_open_bank}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(llov.lso.accountBank,item.value)}">
									selected
								</c:if>
							>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
                	<label class="lab">划扣状态：</label> 
                	<select name="dictApplyDeductPay" class="select78">
                	<option value="">请选择</option>
                	<c:forEach
								items="${dicts.tz_lend_state}" var="item">
								<c:if
									test="${item.value=='9' || item.value=='10' ||
	                        				item.value=='11'  || item.value=='12'}">
	                        				
										<option value="${item.value }"
											<c:if test="${fns:multiOption(llov.lso.dictApplyDeductPay, item.value)}">
												selected
											</c:if>>${item.label }
										</option>

								</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr  id='T5' style='display:none;'>
				<td><label class="lab">渠道标识：</label> 
					<select name="dictFortunechannelflag" class="select78"  multiple="multiple">
                		<c:forEach items="${dicts.tz_channel_flag}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(llov.lso.dictFortunechannelflag,item.value)}">selected</c:if>>${item.label}</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
					<label class="lab">转投审批：</label>
					<select name="switchApproveStatus" class="select78" multiple="multiple">
                		<c:forEach items="${dicts.tz_switch_approve_status}" var="item">
                			<option value="${item.value}" 
								<c:if test="${fns:multiOption(llov.lso.switchApproveStatus,item.value)}">
									selected
								</c:if>>${item.label}
							</option>
                		</c:forEach>
                	</select>
				</td>
				<td>
                	<label class="lab">在职状态：</label> 
                	<select class="select78" id='workingState' name='workingState' multiple="multiple">
						<c:forEach items="${dicts.tz_working_state}" var='item'>
							<option value="${item.value }" 
								<c:if test="${fns:multiOption(llov.lso.workingState, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
                </td>
			</tr>
			<tr id='T5' style='display:none;'>
				<td>
					<label class="lab">自转审批日期：</label>
					<input type="text" name="zzApproveDateStart" id="zzApproveDateStart" class="Wdate input_text70"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'zzApproveDateEnd\',{d:-1});}'})"
						value="${fns:getFormatDate(llov.lso.zzApproveDateStart,'yyyy-MM-dd')}" />-
					<input type="text" name="zzApproveDateEnd" id="zzApproveDateEnd" from-checkDate="#zzApproveDateStart"
						onblur="dateCheck(this)" class="Wdate input_text70"
						onfocus="WdatePicker({minDate:'#F{$dp.$D(\'zzApproveDateStart\',{});}'})"
						value="${fns:getFormatDate(llov.lso.zzApproveDateEnd,'yyyy-MM-dd')}" />
				</td>
				<td>
                	<label class="lab">优先回款：</label> 
                	<select name="priorityBack" class="select78" multiple="multiple">
					<c:forEach items="${dicts.tz_priority_back}" var='item'>
						<option value="${item.value }" 
							<c:if test="${fns:multiOption(llov.lso.priorityBack, item.value) }">selected</c:if>>
							${item.label }
						</option>
					</c:forEach>
				</td>
                </td>
			</tr>
			<%-- <tr id="T4" style='display:none;'>
				<td>
					<label class="lab">回款日期：</label>
					<input type="text" name="backMoneyDayBegin"  class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.backMoneyDayBegin,'yyyy-MM-dd')}" onfocus="WdatePicker()"/> -
					<input type="text" name="backMoneyDayEnd" class="Wdate input_text70" value="${fns:getFormatDate(llov.lso.backMoneyDayEnd,'yyyy-MM-dd')}" onfocus="WdatePicker()"/></td>
				</td>
				<td>
					<label class="lab">投资状态：</label>
					<select name="customerLendingStatus" class="select78" multiple="multiple">
						<c:forEach items="${dicts.tz_invest_state}" var="item">
                			<option value="${item.value}" 	<c:if test="${fns:multiOption(llov.lso.customerLendingStatus, item.value)}">selected</c:if> >${item.label}</option>
                		</c:forEach>
					</select>
				</td>
				<td>
					<label class="lab">客户来源：</label>
					<select name="customerTerminal" class="select78"  multiple="multiple">
						<c:forEach items="${dicts.tz_data_source}" var="item">
                			<option value="${item.value}"  <c:if test="${fns:multiOption(llov.lso.customerTerminal , item.value)}">selected</c:if> >${item.label}</option>
                		</c:forEach>
					</select>
				</td>
			</tr> --%>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input type="submit" value="搜索" class="btn cf_btn-primary"  id="searchBtn" />
        	<input type="reset" value="清除" class="btn cf_btn-primary"  id="clearCondition" />	
            <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
        </div>
        </form>
        </div>
        	<p class="mb10">
        		<auth:hasPermission key="cf:lendapplylook:exportdeductexcel">
					<input id="exportDeductExcel" class="btn btn_sc ml10" type="button" value="导出划扣Excel" />
				</auth:hasPermission>
				<auth:hasPermission key="cf:lendapplylook:exportlendexcel">
        			<input id="exportLendExcel" class="btn btn_sc " type="button" value="导出出借Excel" />
        		</auth:hasPermission>  
        		<input id="btnCaAgain" class="btn btn_sc" style="display:none;" type="button" value="重新生成出借合同" />
        		<span id="title_msg">总金额：</span>￥<span id="sumMoney">${fns:getFormatNumber(llov.sum,'#,##0.00')}</span>
			</p>
    <sys:columnCtrl pageToken="lendApplyLook_lendApplyLookList"></sys:columnCtrl>
    <div class="box5" style='height:370px;overflow-y:auto;'>
    <table class='table table-striped table-bordered table-condensed data-list-table' id='tableList' width="100%">
        <thead>
        <tr>
        	<th><input type="checkbox" class="checkAll">全选</th>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>出借编号</th>
            <th>计划出借日期</th>
            <th>出借天数</th>
            <th>账单日</th>
            <th>到期日期</th>
            <th>计划出借金额</th>
            <th>出借产品</th>           
            <th>信和宝类型</th>
            
            <th>付款方式</th>
            <th>银行</th>
            <th>划扣平台</th>
            
            <th>省份|城市</th>
            <th>营业部</th>
            <th>团队经理</th>
            <th>理财经理</th>
            <th>开发团队</th>
            <th>出借状态</th>
            <th>状态</th>
            
            <th>划扣状态</th>
            <th>划扣成功金额</th>
            <th>划扣失败金额</th>
            <th>划扣失败原因</th>
            <th>回款状态</th>
            <th>回款日期</th>
            <th>完结状态</th>
            <th>开户失败原因</th>
            <th>托管状态</th>
            <th>渠道标识</th>
            <th>创建日期</th>
            <th>转投审批</th>
            <th>在职状态</th>
            <th>优先回款</th>
            <th>自转审批日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${llov.page.list}" var="item" varStatus="s">
        <tr>
        	<td id="checkboxTd"><input type="checkbox" name="checkbox" id="checkOne" value="${item.lendCode}">${s.count}</td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.customerName}</td> --%>
            <td>${item.customerCode}</td>
            <td> ${item.lendCode}  </td>
            <td>${fns:getFormatDate(item.applyLendDay,'yyyy-MM-dd')}</td>
            <td>${item.applyLendDayNum}</td>
            <td>${item.applyBillday}</td>
            <td>${fns:getFormatDate(item.applyExpireDay,'yyyy-MM-dd')}</td>
            <td id="applyLendMoney" v="${item.applyLendMoney}">${fns:getFormatNumber(item.applyLendMoney,'￥#,##0.00')}</td>
            <td>${item.productName}</td>
            <td>
            	<c:if test="${item.xinhebaoType eq 1}">满12个月转让一次收益</c:if>
            	<c:if test="${item.xinhebaoType eq 2}">满12个月不转让收益，24个月后一起转让本息</c:if>
            </td>
            <td>${fns:dictName(dicts.tz_pay_type,item.applyPay, '-')} </td>  
            <td>${fns:dictName(dicts.tz_open_bank, item.accountBank, '-')}</td> 
            <td>${fns:dictName(dicts.tz_deduct_plat,item.dictApplyDeductType, '-')}</td> 
            <td>${item.provinceName}|${item.cityName}</td>
            <td>${item.orgName}</td>
            <td>${item.teamName}</td>
            <td>${item.managerName}</td>
            <td>${item.developTeam}</td>
            <td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }</td>
            <td>${fns:dictName(dicts.tz_for_apply_status,item.status,'-') }</td>
            <c:choose>
            	<c:when test="${item.lendStatus ne '6' and item.lendStatus ne '9' and item.lendStatus ne '10' and item.lendStatus ne '11' and item.lendStatus ne '12'}">
            		<td>-</td>
            	</c:when>
            	<c:otherwise>
            		<td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-') }</td>
            	</c:otherwise>
            </c:choose>
            <td>${fns:getFormatNumber(item.actualDeductMoney,'￥#,##0.00')}</td>
            <td>${fns:getFormatNumber(item.failMoney,'￥#,##0.00')}</td>
            <td>${item.failReason}</td>
            <td>${fns:dictName(dicts.tz_back_state,item.dictBackStatus,'-') }</td>
            <td>${fns:getFormatDate(item.backMoneyDay,'yyyy-MM-dd')}</td>
            <td>${fns:dictName(dicts.tz_finish_state,item.dictApplyEndState,'-') }</td>
            <td>${item.trusteeshipRetMsg }</td>
            <td>${fns:dictName(dicts.tz_trust_state,item.applyHostedStatus,'-') }</td>
            <td>
	            	${fns:dictName(dicts.tz_channel_flag,item.dictFortunechannelflag,'-') }
	        </td>
            <td>${fns:getFormatDate(item.createTime,'yyyy-MM-dd')}</td>
            <td>${fns:dictName(dicts.tz_switch_approve_status,item.switchApproveStatus,'--')}${tz_switch_approve_status}</td>
            <td>${fns:dictName(dicts.tz_working_state,item.workingState,'-') }</td>
            <td>${fns:dictName(dicts.tz_priority_back,item.priorityBack,'') }</td>
            <td>${fns:getFormatDate(item.zzApproveDate,'yyyy-MM-dd')}</td>
            <td class="tcenter">
            	<auth:hasPermission key="cf:lendapplylook:transact">
            		<input type="button" class="cf_btn_edit" value="办理" onclick="window.location.href='${ctx}/lendApplyLook/goLendApplyLookPage?code=${item.lendCode}&userManagerId=${item.id}'"/>
            	</auth:hasPermission>
            	<c:if test="${item.djrSwitchFlag==1}">
					<input type="button" class='cf_btn_edit' id="_todjr" value="转投大金融" onclick="_todjr('${item.lendCode}')" />
				</c:if>
				<c:if test="${item.switchApproveStatus==1}">
					<input type="button" disabled="disabled" class='cf_btn_edit' id="_todjr" value="转投大金融" />
				</c:if>
				<!-- 控制优先回款按钮的可以性 -->
				<c:if test="${item.switchPriorityState==1 }">
					<auth:hasPermission key="cf:lendapplylook:switchPriorityState">
						<input  class="cf_btn_edit" type="button" value="优先回款" onclick="switchPriority('${item.lendCode}','${item.userManagerId}','${item.priorityId}')"/>
					</auth:hasPermission>
				</c:if>
				<c:if test="${item.switchPriorityState==2 }">
					<auth:hasPermission key="cf:lendapplylook:switchPriorityState">
						<input  disabled="disabled" class="cf_btn_edit" type="button" value="优先回款" />
					</auth:hasPermission>
				</c:if>
            </td>
        </tr> 
        </c:forEach>      
    </table>
    </div>
        <div class="pagination">${llov.page}</div>
</body>

</html>
