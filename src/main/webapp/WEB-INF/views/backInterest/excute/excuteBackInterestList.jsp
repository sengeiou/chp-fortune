<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<title>执行回息列表</title>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/backInterestCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/exportExcel.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backInterest/excuteBackInterest.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">	
	    	<form id="searchForm" action="${ctx}/excuteBackInterest/loadExcuteBackInterestList" method="post">
		        <input id="pageNo" name="pageNo" type="hidden" value="${bibv.page.pageNo}" />
						<input id="pageSize" name="pageSize" type="hidden" value="${bibv.page.pageSize}" />
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		             <tr>
		                <td><label class="lab">客户编号：</label><input type="text" name="customerCode" value="${bibv.so.customerCode}" class="cf_input_text178"></td>
		                <td><label class="lab">客户姓名：</label><input type="text" name="customerName" value="${bibv.so.customerName}" class="cf_input_text178"></td>
						<td><label class="lab">出借编号：</label><input type="text" name="lendCode" value="${bibv.so.lendCode}" class="cf_input_text178"></td>
		            </tr>
		            <tr> 
					   	<td><label class="lab">营业部：</label><sys:orgTree id="org" name="orgId" value="${bibv.so.orgId}" labelValue="${bibv.so.orgName}" labelName="orgName"/></td>
						<td><label class='lab'>城市：</label><sys:cityselect name="addrCity" value="${bibv.so.addrCity}" multiple="true"/></td>
					    <td><label class="lab">账单日：</label>
						    	<input type="text" id="currentBillday" name="currentBillday" class="Wdate cf_input_text178" value="${fns:getFormatDate(bibv.so.currentBillday,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker()"/>
						</td>
		            </tr>
		            <tr id="T1" style="display:none">
		                <td><label class="lab">计划出借日：</label>
		                	<input type="text" id="applyLendDayFrom" name="applyLendDayFrom" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyLendDayTo\',{d:-1});}'})" value="${fns:getFormatDate(bibv.so.applyLendDayFrom,'yyyy-MM-dd')}"/> -
                			<input type="text" id="applyLendDayTo" from-checkDate="#applyLendDayFrom" name="applyLendDayTo" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyLendDayFrom\',{});}'})" value="${fns:getFormatDate(bibv.so.applyLendDayTo,'yyyy-MM-dd')}"/>
		                </td>
		                <td><label class="lab">到期日：</label>
		                	<input type="text" id="applyExpireDayFrom" name="applyExpireDayFrom" value="${fns:getFormatDate(bibv.so.applyExpireDayFrom,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyExpireDayTo\',{d:-1});}'})"/> -
	                		<input type="text" id="applyExpireDayTo" from-checkDate="#applyExpireDayFrom" name="applyExpireDayTo" value="${fns:getFormatDate(bibv.so.applyExpireDayTo,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyExpireDayFrom\',{});}'})"/></td>
		                </td>
						<td><label class="lab">回息日期：</label>
							<input type="text" id="backMoneyDayFrom" name="backMoneyDayFrom" value="${fns:getFormatDate(bibv.so.backMoneyDayFrom,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'backMoneyDayTo\',{d:-1});}'})"> -
							<input type="text" id="backMoneyDayTo" from-checkDate="#backMoneyDayFrom" name="backMoneyDayTo" value="${fns:getFormatDate(bibv.so.backMoneyDayTo,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'backMoneyDayFrom\',{});}'})">
						</td>
		            </tr>
		            <tr id="T2" style="display:none">
		                <td><label class="lab">计划划扣日：</label>
		                	<input type="text" id="applyDeductDayFrom" name="applyDeductDayFrom" value="${fns:getFormatDate(bibv.so.applyDeductDayFrom,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'applyDeductDayTo\',{d:-1});}'})"/> -
	                		<input type="text" id="applyDeductDayTo" from-checkDate="#applyDeductDayFrom" name="applyDeductDayTo" value="${fns:getFormatDate(bibv.so.applyDeductDayTo,'yyyy-MM-dd')}" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'applyDeductDayFrom\',{});}'})"/></td>
		                </td>
		                <td><label class="lab">出借金额：</label>
		                	<input type="text" id="applyLendMoneyFrom" number="1" name="applyLendMoneyFrom"  class="input_text70" value="${bibv.so.applyLendMoneyFrom}"/> -
                			<input type="text" number="1" from-checkInt="#applyLendMoneyFrom" name="applyLendMoneyTo"  class="input_text70" value="${bibv.so.applyLendMoneyTo}"/>
		                </td>
		                <td><label class="lab">付款方式：</label>
		                	<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
								<c:forEach items="${dicts.tz_pay_type}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.applyPay, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
		            </tr>
		            <tr id="T3" style="display:none">
		                <td><label class='lab'>出借产品：</label>
		                	<select name="productCode" id="productCode"class="select78" multiple="multiple">
								<c:forEach items="${bibv.productList}" var="item" >
									<option value="${item.productCode}" <c:if test="${fn:contains(bibv.so.productCode, item.productCode) }">selected</c:if>>${item.productName}</option>
								</c:forEach>
							</select>
		                	</td>
						<td><label class="lab">开户行：</label>
		                	<select class="select78" id="accountBank" name='accountBank' multiple="multiple">
								<c:forEach items="${dicts.tz_open_bank}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.accountBank, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
						<td><label class="lab">卡/折：</label>
		                	<select class="select78" id='dictValue' name='dictValue' multiple="multiple">
								<c:forEach items="${dicts.com_card_type}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.dictValue, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
					</tr>
					<tr id="T3" style="display:none">
						<td><label class="lab">平台标识：</label>
		                	<select class="select78" id='platFlag' name='platFlag' multiple="multiple">
								<c:forEach items="${dicts.tz_backInterest_plat}" var='item'>
									<option value="${item.value }" <c:if test="${fn:contains(bibv.so.platFlag, item.value) }">selected</c:if>>
										${item.label }
									</option>
								</c:forEach>
							</select></td>
		        		<td><label class="lab">合同版本号：</label> 
		                	<select class="select78 mr34" id='applyAgreementEdition' name='applyAgreementEdition' multiple="multiple">
									<c:forEach items="${dicts.tz_contract_vesion}" var='item'>
										<option value="${item.value }" <c:if test="${fns:multiOption(bibv.so.applyAgreementEdition, item.value) }">selected</c:if>>
											${item.label }
										</option>
									</c:forEach>
							</select>
						</td>
						<td><label class="lab">回息状态：</label>
							<select class="select78" id='backMoneyStatus' name='backMoneyStatus' multiple="multiple">
								<c:forEach items="${dicts.tz_backsms_state}" var='item'>
									<c:if test="${item.value == '9' or item.value=='10'}">
										<option value="${item.value }" <c:if test="${fn:contains(bibv.so.backMoneyStatus, item.value) }">selected</c:if>>${item.label }</option>
		        					</c:if>
		        				</c:forEach>
		        			</select>
	        			</td>
					</tr>
					<tr id="T3" style="display:none">
						<td><label class="lab">金账户标识：</label>
							<input type="radio" id="trusteeshipFlag" name="trusteeshipFlag" value="1" <c:if test="${bibv.so.trusteeshipFlag==1}">checked</c:if>>金账户
							<input type="radio" id="trusteeshipFlag" name="trusteeshipFlag" value="2" <c:if test="${bibv.so.trusteeshipFlag==2}">checked</c:if>>非金账户
						</td>
						<td><label class="lab">匹配标识：</label>
		                	<select class="select78" id="matchingFlag" name="matchingFlag">
		                		<option value="">请选择</option>
								<option value="1" <c:if test="${bibv.so.matchingFlag == 1}">selected</c:if>>是</option>
								<option value="2" <c:if test="${bibv.so.matchingFlag == 2}">selected</c:if>>否</option>
							</select></td>
					</tr>
		        </table>
				<div class="tright pr30 pb5 pt10">
			       <input type="submit" id="search" class="btn cf_btn-primary" value="搜索"/>
			       <input type="reset" value="清除" class="btn cf_btn-primary" onclick="javascript:void(0)"/>
			       <div class="xiala"  id="showMore" onclick="javascript:show();"></div>
				</div>
		    </form>
		</div>
	    <p class="mb10">
	    	<auth:hasPermission key="cf:backinterestexcute:upbackinterest">
				<input type="button" class="btn btn_sc ml10" value="线上回息" onclick="onlineWindow()"/>
			</auth:hasPermission>
			<auth:hasPermission key="cf:backinterestexcute:downbackinterest">
				<input type="button" id="lbtn" class="btn btn_sc" value="线下回息" onclick="lineWindow();"/>
			</auth:hasPermission>
			<auth:hasPermission key="cf:backinterestexcute:batchbackinterest">
				<input type="button" id="pbtn" class="btn btn_sc" value="批量回息" onclick="batchExcuteWindow()"/>&nbsp;&nbsp;
			</auth:hasPermission>
			累计实际回息金额: ￥<label id="sum">${bibv.listTotalMoney}</label>元&nbsp;&nbsp;&nbsp;
    		总笔数：<label id="count">${bibv.page.count}</label>笔 
    		<input type="hidden" id="pageTotal" value="${bibv.pageTotalMoney}"/>
    		<input type="hidden" id="totalMoney" value="${bibv.listTotalMoney}"/>
    		<input type="hidden" id="totalCount" value="${bibv.page.count}"/>
    	</p>
    	<sys:columnCtrl pageToken="backInterest_excute_excuteBackInterestList"></sys:columnCtrl>
	    <div class='box5'>	    	
			<table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
		        <input type="hidden" id="sl" value="${sl}"/>
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
					<th>合同版本号</th>
					<th>付款方式</th>
		            <th>客户开户行</th>
		            <th>卡/折</th>
		            <th>所在省</th>
		            <th>所在城市</th>
		            <th>应回息日期</th>
		            <th>成功金额</th>
		            <th>失败金额</th>
		            <th>回息金额</th>
		            <th>平台标识</th>
		            <th>退回重放标识</th>
		            <th>回息状态</th>
		            <th>操作</th>
		        </tr>
		        </thead>
		        <c:forEach items="${bibv.page.list}" var="back" varStatus="status">
		        <tr <c:if test="${back.rebackFlag==1}">style="color:red"</c:if>>
			            <td><input type="checkbox" id="ids" name="ids" value="${back.backiId}" onclick="selects()"></td>
			            <td align="center">${status.count}</td>
			            <td align="center">${back.lendCode}</td>
			            <td align="center">${back.customerCode}</td>
			            <!-- 屏蔽客户姓名/手机号/身份证号 -->
						<td>***</td>
			            <%-- <td align="center">${back.customerName}</td> --%>
			            <td align="center">${fns:getFormatDate(back.currentBillday,"yyyy-MM-dd")}</td>
			            <td align="center">${fns:getProductLabel(back.productCode)}</td>
			            <td align="center">${fns:dictName(dicts.tz_contract_vesion,back.applyAgreementEdition,'-') }</td>
			            <td align="center">${fns:dictName(dicts.tz_pay_type,back.applyPay,'-') }</td>
			            <td align="center">${fns:dictName(dicts.tz_open_bank,back.accountBank,'-')}</td>
			            <td align="center">${fns:dictName(dicts.com_card_type,back.accountCardOrBooklet,'-')}</td>
			            <td align="center">${back.province}</td>
			            <td align="center">${back.city}</td>
			            <td align="center">${fns:getFormatDate(back.backMoneyDay,"yyyy-MM-dd")}</td>
			            <td align="center">${fns:getFormatNumber(back.successMoney ,'￥#,##0.00')}</td>
		            	<td align="center">${fns:getFormatNumber(back.failMoney ,'￥#,##0.00')}</td>
			            <td align="center">
			            <input type="hidden" name="mmy" value="${back.backRealMoney}">
			            ${fns:getFormatNumber(back.backRealMoney ,'￥#,##0.00')}</td>
			            <td align="center">${fns:dictName(dicts.tz_backInterest_plat,back.platFlag,'-')}</td>
			            <td align="center"><c:choose>
			            					<c:when test="${back.rebackFlag==1}">退回重放</c:when>
			            					<c:otherwise>-</c:otherwise></c:choose></td>
			            <td align="center">${fns:dictName(dicts.tz_backsms_state,back.backMoneyStatus,'-')}</td>
			            <td align="center">
			            	<auth:hasPermission key="cf:backinterestexcute:transact">
			            		<input type="button" value="办理" class="cf_btn_edit"" onclick="singleExcute('${back.backiId}');"/>
			            	</auth:hasPermission>
			            </td>
		        </tr>
		        </c:forEach>
		    </table>
			<div class="pagination">${bibv.page}</div>
	    </div>
	</div>
	
	<div id="modal-sub" class="modal fade subwindow" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<input type="hidden" id="hi" value="1"/>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">线下回息</h4>                                                                      
				</div>
				<div class="modal-body">
				</div>
				<div class="tright pr30">
			    	<input type="button" class="btn cf_btn-primary"  value="确认" onclick="branchExcuteBackInterest('excuteBackInterest/batchExcute','excuteBackInterest/loadExcuteBackInterestList')"/>
			    	<input type="button" class="btn cf_btn-primary"  value="关闭" id="subClose" />
				</div>
			</div>	
		</div>
	</div>
</body>
</html>