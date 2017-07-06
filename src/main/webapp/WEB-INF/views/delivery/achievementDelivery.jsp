<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>业绩交割</title>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/delivery/achievementDelivery.js" type="text/javascript"></script>
</head>
<body>
<div class="body_top">
    
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户信息</h2></div>
    </div>
	<div class="box6">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178"  disabled value="${deliveryEx.custCode}"></td>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178"  disabled value="***"></td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178"  disabled value="${deliveryEx.custName}"></td> --%>
            </tr>
        </table>
	 </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">理财经理信息</h2></div>
    </div>
	<div class="box6">
    
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                    <input type="hidden" class="noClear" id="nfManagerId" value="${del.nfManagerId}"/>
                    <label class="lab"><span class="red">*</span>员工编号：</label><input type="text" class=" noClear autoComplete cf_input_text178" id="nfManagerCode" name="nfManagerCode" value="${del.nfManagerCode}">
                </td>
                <td><label class="lab"><span class="red">*</span>理财经理：</label><input type="text" class="noClear cf_input_text178" id="nfManagerName" name="nfManagerName" value="${del.nfManagerName}" disabled></td>
                <td><label class="lab"><span class="red">*</span>团队经理：</label><input type="text" class="noClear cf_input_text178" id="nTeamManagerName" name="nTeamManagerName" value="${del.nTeamManagerName}"disabled></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>团队名称：</label><input type="text" class="noClear cf_input_text178" id="teamName" name="teamName" value="${del.teamName}" disabled></td>
                <td><label class="lab"><span class="red">*</span>营业部：</label><input type="text" class="noClear cf_input_text178" id="nOrgName" name="nOrgName" value="${del.nOrgName}"disabled></td>
            </tr>
        </table>
	 </div>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">客户出借信息</h2></div>
    </div>
	<div class="box6">
       <form:form id="searchForm" modelAttribute="deliveryEx" action="${ctx}/delivery/achievement/list" method="post">
          <table class="table table-striped table-bordered table-condensed">
            <tr>
                <td>
                    <label>出借编号：</label>
                    <input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${deliveryEx.lendCode}">
                    <input type="hidden" class="noClear" name="fManagerId" id="fManagerId" value="${deliveryEx.fManagerId}">
                    <input type="hidden" class="noClear" name="custCode" value="${deliveryEx.custCode}"/>
                    <input type="hidden" class="noClear" name="custName" value="${deliveryEx.custName}"/>
                </td>
				<td>
				    <label>出借日期：</label>
				    <input type="text"  class="Wdate input_text70" id="startTime" name="startTime"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'endTime\')}'})" value='<fmt:formatDate value="${deliveryEx.startTime }" pattern="yyyy-MM-dd"/>'> -
				    <input type="text"  class="Wdate input_text70" id="endTime" name="endTime"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" value='<fmt:formatDate value="${deliveryEx.endTime }" pattern="yyyy-MM-dd"/>'>
				</td>
				   <td>
			       <label>出借产品：</label>
				   <sys:productselect id="productCode" name="productCode" value="${deliveryEx.productCode}" multiple="true"/>
			   </td>
			</tr>
        </table>
        <div class="tright pr30">
             <input class="btn cf_btn-primary" type="submit" id="search" value="搜索" />
             <input class="btn cf_btn-primary" type="reset"  value="清除" />
        </div>
       </form:form>
	 </div>
    <div class="pb5 pt10 pr30">
    	<button class="btn btn_sc ml10" id="achievemenDdelivery">批量业绩交割</button>&nbsp;&nbsp;&nbsp;总笔数：${page.count}条；&nbsp;&nbsp;总金额：<fmt:formatNumber value="${totalMoney}" type="currency" pattern="#,#00.00#"/>元
    </div>
    <div class="box5">
    <table class="table table-striped table-bordered table-condensed data-list-table" width="100%">
      <thead> 
        <tr>
            <th><input type="checkbox" id="checkAll" class="checkAll"/></th>
            <th>客户姓名</th>
            <th>出借编号</th>
            <th>出借产品</th>
            <th>计划出借日期</th>
            <th>计划出借金额</th>
            <th>付款方式</th>
            <th>到期日期</th>
            <th>出借状态</th>
            <th>完结状态</th>
            <th>理财经理</th>
            <th>团队经理</th>
            <th>营业部</th>
            <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${page.list}" var="item" varStatus="status">
          <tr>
            <td><input type="checkbox" name="checkbox" value="${item.custCode},${item.lendCode},${item.fManagerId}"/></td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.custName}</td> --%>
            <td>${item.lendCode}</td>
            <td>${item.productName}</td>
            <td><fmt:formatDate value="${item.applyLendDay}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatNumber value="${item.applyLendMoney}" type="currency" pattern="￥#,#00.00#"/></td>
            <td>${fns:getDictLabel(item.applyPay, 'tz_pay_type', '-')}</td>
            <td><fmt:formatDate value="${item.applyExpireDay}" pattern="yyyy-MM-dd"/></td>
            <td>${fns:getDictLabel(item.lendStatus, 'tz_lend_state', '-')}</td>
            <td>${fns:getDictLabel(item.applyEndStatus, 'tz_finish_state', '-')}</td>
            <td>${item.fManagerName}</td>
            <td>${item.teamManagerName}</td>
            <td>${item.orgName}</td>
            <td><input type="button" class="cf_btn_edit" value="办理" onclick="achievementDelivery('${item.custCode}','${item.lendCode}','${item.fManagerId}');"></td>
         </tr>
       </c:forEach>    
      </tbody>
      </table>
    </div>
    <div class="pagination">${page}</div>
	<div class="tright pr30" style="margin-top:20px">
          <a href="${ctx}/delivery/customer/list"><button class="btn cf_btn-primary">返回</button></a> 
	</div>
</div>
</body>
</html>
