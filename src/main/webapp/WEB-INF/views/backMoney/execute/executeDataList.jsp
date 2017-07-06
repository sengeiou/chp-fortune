<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<p class='mb10 ml10'>
	<auth:hasPermission key="cf:backmoneyexcute:batchbackmoney">
		<input type="button" class="btn btn_sc ml10" id="upLineDeduct" onclick="toMultiBackMoney();" value="批量回款"/>
	</auth:hasPermission>
	<auth:hasPermission key="cf:backmoneyexcute:upbackmoney">
		<input type="button" class="btn btn_sc" onclick="toOnlineBackMoney();" value="线上回款"/>
	</auth:hasPermission>
	<auth:hasPermission key="cf:backmoneyexcute:downbackmoney">
		<input type="button" class="btn btn_sc" onclick="toOfflineBackMoney();" value="线下回款"/>
	</auth:hasPermission>
  	<input type="hidden" id="hTotalActualMoney" value="${fns:getFormatNumber(totalABM,'#0.00')}"/>
  	<input type="hidden" id="hDataAmount" value="${page.count}"/>
  	<input type="hidden" id="jzhFlag" value="${view.isJZH}"/>
	<label>
	  	累计实际回款金额 ： ￥<label id="TotalActualMoney">${fns:getFormatNumber(totalABM,'#0.00')}</label>元    
	  	累计数量 ： <label id="dataAmount">${page.count}</label>条  
	</label>
</p>
<div class='box5'>
<sys:columnCtrl pageToken="backMoney_execute_executeDataList"></sys:columnCtrl>
<table id="listTable" class="table table-striped table-bordered table-condensed data-list-table">
	<thead>
	<tr>
		<th>
			<span onclick="checkAll();" onclick="checkAll();">
				<input type="checkbox" id="checkAll" class="checkAll">全选
			</span>
		</th>
		<th>客户编号</th>
		<th>客户姓名</th>
		<th>出借编号</th>
		<th>计划出借日期</th>
		<th>计划出借金额</th>
		<th>出借方式</th>
        <th>合同版本</th>
		<th>账户省份/城市</th>
		<th>营业部</th>
		<th>理财经理</th>
		<th>员工编号</th>
        <th>渠道标识</th>
		<th>应回款金额</th>
		<th>实际回款金额</th>
		<th>成功金额</th>
		<th>失败金额</th>
		<th>回款状态</th>
		<th>到期日期</th>
		<th>回款日期</th>
		<th>回款类型</th>
		<th>回款平台</th>
		<th>在职状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<form id="listForm" action="${ctx}/myApply/backMoney/multiApply"
		method="post">
		<c:forEach items="${page.list}" var="item">
			<tr <c:if test="${item.rebackFlag==rebackYes}">style="color:red"</c:if>>
				<td><input type="checkbox" id="dataCheck" name="backmId"
					value="${item.backmId }" onclick="checkOne(this)"></td>
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
				<td>${item.userCode }</td>
	            <td>
	            	${fns:dictName(dicts.tz_channel_flag,item.dictFortunechannelflag,'-') }
	            </td>
				<td>${fns:getFormatNumber(item.backMoney ,'￥#,##0.00')}</td>
				<td>
					${fns:getFormatNumber(item.backActualbackMoney ,'￥#,##0.00')}
	            	<input type="hidden" name="itemBackActualbackMoney" value="${fns:getFormatNumber(item.backActualbackMoney-item.successMoney ,'#0.00')}"/>
				</td>
				<td>${fns:getFormatNumber(item.successMoney ,'￥#,##0.00')}</td>
				<td>${fns:getFormatNumber(item.failMoney ,'￥#,##0.00')}</td>
				<td>
					${fns:dictName(dicts.tz_back_state,item.dictBackStatus,'-') }
				</td>
				<td>${fns:getFormatDate(item.finalLinitDate ,"yyyy-MM-dd" )}</td>
				<td>${fns:getFormatDate(item.backMoneyDay,"yyyy-MM-dd")}</td>
				<td>
					${fns:dictName(dicts.tz_back_type,item.backMoneyType,'-') }
				</td>
				<td>
					${fns:dictName(dicts.tz_backMoney_plat,item.exPlatform,'-') }
				</td>
				<td>
					${fns:dictName(dicts.tz_working_state,item.workingState,'-') }
				</td>
				<td class="tcenter">
					<auth:hasPermission key="cf:backmoneyexcute:transact">
						<a href="${ctx}/myTodo/backMoney/executeDetail?backmId=${item.backmId }">办理 </a>
					</auth:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</form>
</table>
</div>