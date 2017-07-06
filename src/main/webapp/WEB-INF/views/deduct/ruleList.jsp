<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/deduct/platformRule.js"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<title>划扣限额配置</title>
</head>
<body>
<div class="body_r">
    <form id="searchForm" action="${ctx}/deduct/platform/listRules" method="post">
    <div class="box1 mb10">
    	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
					<label class='lab'>划扣平台：</label> 
					<select name="dictDeductPlatformId" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="item">
							<option value="${item.value}">item{item.label}</option>
						</c:forEach>
					</select>
				</td>
				<td><label class='lab'>拆分基数：</label>
                	<input type="text" name="singleLimitMoney" id="singleLimitMoney" number="1" onblur="dateCheck(this)" value="${view.singleLimitMoney }" class="input_text70"> -
                	<input type="text" name="singleLimitMoneyTo" number="1" onblur="dateCheck(this)" from-checkInt="#singleLimitMoney" value="${view.singleLimitMoneyTo }" class="input_text70"></td>
				<td>
					<label class='lab'>状态：</label>
					<select class="select78 mr34" id='status' name='status' multiple="multiple">
						<c:forEach items="${fns:getDictList('tz_platform_rule')}" var='item'>
							<option value="${item.value }" <c:if test="${fn:contains(rule.status, item.value) }">selected</c:if>>
								${item.label }
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
            <tr> 
				<td><label class="lab">开户银行：</label> 
					<select name="dictBankId" class="cf_input_text178 ">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_open_bank') }" var="item">
							<option value="${item.value}" <c:if test="${fn:contains(rule.dictBankId, item.value) }">selected</c:if>>
								${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
                <td><label class='lab'>每日限额：</label>
                	<input type="text" name="dayLimitMoney" id="dayLimitMoney" number="1" onblur="dateCheck(this)" value="${view.dayLimitMoney }" class="input_text70"> -
                	<input type="text" name="dayLimitMoneyTo" number="1" onblur="dateCheck(this)" from-checkInt="#dayLimitMoney" value="${view.dayLimitMoneyTo }" class="input_text70"></td>
                <td><label class="lab">类型：</label> 
                	<select class="select78 mr34" id='dictDeductInterfaceType' name='dictDeductInterfaceType' multiple="multiple">
						<c:forEach items="${fns:getDictList('tz_deductinter_type')}" var='item'>
							<option value="${item.value}" <c:if test="${fn:contains(rule.dictDeductInterfaceType, item.value) }">selected</c:if>>
								${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
        </table>
        <div class="tright pr30">
        	<input type="button" id="search" value="搜索"  onclick="javascript:search()" class="btn cf_btn-primary"/>
        	<input type="reset" value="清除" class="btn cf_btn-primary"/>
        </div>
    </div>
    <p>
	    <input type="button" onclick="javascript:addRule();" class="btn btn_sc ml10" value="新增">
	    <input type="button" onclick="javascript:examine();" class="btn btn_sc" value="审核">
	    <input type="button" onclick="javascript:startUse();" class="btn btn_sc" value="启用">
	    <input type="button" onclick="javascript:stopUse();" class="btn btn_sc" value="停用">
	</p>
    <table id="listTable" class="table table-striped table-bordered table-condensed data-list-table">
    	<thead>
    	<tr>
            <th>
            	<span onclick="checkAll();">
			    	<input type="checkbox" id="checkAll" class="checkAll">全选
			    </span>
            </th>
            <th>序号</th>
            <th>第三方平台</th>
            <th>划扣银行</th>
            <th>拆分基数(元)</th>
            <th>每日限额(元)</th>
            <th>是否发送第一笔</th>
			<th>审核状态</th>
            <th>限额类型</th>
            <th>审核不通过原因</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item" varStatus="vs">
	        <tr>
	            <td><input type="checkbox" id="dataCheck" name="id" value="${item.id }" onclick="checkOne(this)"></td>
	            <td>${vs.index+1}</td>
	            <td>${fns:getDictLabel(item.dictDeductPlatformId,'tz_deduct_plat','-')}</td>
	            <td>${fns:getDictLabel(item.dictBankId,'tz_open_bank','-')}</td>
	            <td>
	            	<fmt:formatNumber value="${item.singleLimitMoney }" type="currency" pattern="￥#,#00.00"/>
	            </td>
	            <td>
	            	<fmt:formatNumber value="${item.dayLimitMoney}" type="currency" pattern="￥#,#00.00"/>
	            </td>
	            <td>${fns:getDictLabel(item.isFirst,'tz_yes_no','-')}</td>
	            <td>
	            	${fns:getDictLabel(item.status,'tz_pay_type','-')}
	            </td>
	            <td>${fns:getDictLabel(item.dictDeductInterfaceType,'tz_deductinter_type','-')}</td>
	            <td>${item.backReason }</td>
	            <td class="tcenter">
	            	<input type="button" onclick="javascript:editRule('${item.id}');" class="cf_btn_edit" value="修改">
	            </td>
	        </tr>
        </c:forEach>
    </table>
</form>

</div>
    <div class="pagination">${page}</div>
    <div id="modal-sub" class="modal fade subwindow">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">银行限额配置</h4>
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