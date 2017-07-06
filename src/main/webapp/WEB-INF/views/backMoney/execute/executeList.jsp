<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/backMoneyCommon.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/backMoney/execute.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title>执行回款列表</title>
</head>
<body>
<div class="body_r">
    <form id="searchForm" action="${ctx}/myTodo/backMoney/executeList" method="post">
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
							<option value="${item.value }" <c:if test="${fns:multiOption(view.backMoneyType,item.value) }">selected</c:if>>
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
                	<select class="select78" id='applyPay' name='applyPay' multiple="multiple">
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
						<c:forEach items="${dicts.com_card_type}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.accountCardOrBooklet,item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
                <td><label class="lab">金账户数据：</label>
                	<input type="radio" name="isJZH" <c:if test="${view.isJZH==1}">checked</c:if> value="1">是
                	<input type="radio" name="isJZH" <c:if test="${view.isJZH==2}">checked</c:if> value="2">否
				</td>
            </tr>
			<tr id="T4" style="display:none">
                <td><label class='lab'>回款状态：</label>
                	<select class="select78 mr34" id='dictBackStatus' name='dictBackStatus' multiple="multiple">
                		<!-- 待回款、部分回款成功 -->
						<c:forEach items="${dicts.tz_back_state}" var='item'>
							<c:if test="${fns:multiOption(backStatusOption,item.value) }">
								<option value="${item.value }"<c:if test="${fns:multiOption(view.dictBackStatus,item.value) }">selected</c:if>>
									${item.label }
								</option>
							</c:if>
						</c:forEach>
					</select>
                </td>
                <td>
                	<label class='lab'>开户行：</label>
                	<select class="select78 mr34" name="backBank" multiple="multiple">
						<c:forEach items="${dicts.tz_open_bank}" var="item">
							<option value="${item.value }" <c:if test="${fns:multiOption(view.backBank,item.value)}">selected</c:if>>${item.label }</option>
						</c:forEach>
					</select>
                </td>
                <td>
                	<label class="lab">回款平台：</label>
                	<select class="select78" id='exPlatform' name='exPlatform' multiple="multiple">
						<c:forEach items="${dicts.tz_backMoney_plat}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.exPlatform, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
                </td>
            </tr>
			<tr id="T5" style="display:none">
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
                <td><label class="lab">开户行号匹配标识：</label> 
                	<select class="select78 mr34" id='findFlag' name='findFlag' multiple="multiple">
						<c:forEach items="${dicts.tz_yes_no}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.findFlag, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
                </td>
            </tr>
            <tr id="T5" style="display:none">
            	 <td>
	            	<label class="lab">在职状态：</label> 
	               	<select class="select78 mr34" id='workingState' name='workingState' multiple="multiple">
						<c:forEach items="${dicts.tz_working_state}" var='item'>
							<option value="${item.value }" <c:if test="${fns:multiOption(view.workingState, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
	            </td>
	            <td></td>
	            <td></td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
        	<input type="submit" id="search" onclick="resetPage()" value="搜索" class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        	<div id="showMore" class="xiala" onclick="javascript:show();"></div>
        </div>
        </div>
    </form>
   	<div class="dataList">
	   	<%@ include file="/WEB-INF/views/backMoney/execute/executeDataList.jsp"%>
	</div>
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