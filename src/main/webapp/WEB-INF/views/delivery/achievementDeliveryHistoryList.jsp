<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf}/js/delivery/achievementDeliveryHistory.js" type="text/javascript"></script>
<title>业绩交割历史查询</title>
</head>
<body>
<div class="body_top body_r">
    <div class="box1 mb10">
        <form:form id="searchForm" modelAttribute="deliveryEx" action="${ctx}/delivery/achievement/history/list" method="post">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" id="custName" name="custName" value="${deliveryEx.custName}"></td>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" id="custCode" name="custCode" value="${deliveryEx.custCode}"></td>
                <td><label class="lab">出借编号：</label><input type="text" class="cf_input_text178" id="lendCode" name="lendCode" value="${deliveryEx.lendCode}"></td>
            </tr>
            <tr>
                <td><label class="lab">理财经理工号：</label><input type="text" class="cf_input_text178" id="nfManagerCode" name="nfManagerCode" value="${deliveryEx.nfManagerCode}"></td>
                <td><label class="lab">理财经理：</label><input type="text" class="cf_input_text178" id="nfManagerName" name="nfManagerName" value="${deliveryEx.nfManagerName}"></td>
                <td><label class="lab">团队经理工号：</label><input type="text" class="cf_input_text178" id="nTeamManagerCode" name="nTeamManagerCode" value="${deliveryEx.nTeamManagerCode}"></td>
            </tr>
            <tr id="T1" style="display:none">
                <td><label class="lab">团队经理：</label><input type="text" class="cf_input_text178" id="nTeamManagerName" name="nTeamManagerName" value="${deliveryEx.nTeamManagerName}"></td>
                <td><label class="lab">营业部：</label>
                   <sys:orgTree id="nOrgCode" name="nOrgCode" value="${deliveryEx.nOrgCode}" labelName="nOrgName" labelValue="${deliveryEx.nOrgName}"/>
                </td>
                <td colspan="3"><label class="lab">交割时间：</label>
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" id="startTime" name="startTime" value="${fns:getFormatDate(deliveryEx.startTime,'yyyy-MM-dd')}"/> -
                  <input type="text" class="Wdate input_text70" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" id="endTime" name="endTime" value="${fns:getFormatDate(deliveryEx.endTime,'yyyy-MM-dd')}"/>        
                </td>
            </tr>
        </table>
        <div class="tright pr30 pb5 pt10">
             <input class="btn cf_btn-primary" id = "search" type="submit" value="搜索" />
			 <input class="btn cf_btn-primary" id = "reset" type="reset"  value="清除" />
			 <div class="xiala" id="showMore" onclick="javascript:show();"></div>
        </div>
        </form:form>
    </div>
    
    <div class="mb10">
    	<auth:hasPermission key="cf:achievementdeliveryhistory:exportexcel">
    		<button class="btn btn_sc ml10" id="outExcel" type="button">导出Excel</button>
    	</auth:hasPermission>
    	&nbsp;&nbsp;总笔数：${page.count}条
    </div>
    
    <div class="box5" style="padding-right:10px;height:100%;">
      <table id='tableList' class="table table-striped table-bordered table-condensed data-list-table">
        <thead>
          <tr>
            <th>客户姓名</th>
            <th>客户编号</th>
            <th>出借编号</th>
            <th>出借产品</th>
            <th>出借金额</th>
            <th>出借时间</th>
            <th>付款方式</th>
            <th>到期时间</th>
            <th>出借状态</th>
            <th>完结状态</th>
            <th>理财经理工号</th>
            <th>理财经理</th>
            <th>团队经理工号</th>
            <th>团队经理</th>
            <th>营业部</th>
            <th>原理财经理工号</th>
            <th>原理财经理</th>
            <th>原团队经理工号</th>
            <th>原团队经理</th>
            <th>原营业部</th>
            <th>交割时间</th>
            <th>操作人</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.list}" var="item" varStatus="status">
                  <tr> 
                  	  <!-- 屏蔽客户姓名/手机号/身份证号 -->
                      <td>***</td>
                      <%-- <td>${item.custName}</td> --%>
                      <td>${item.custCode}</td>
                      <td>${item.lendCode}</td>
                      <td>${item.productName}</td>
                      <td><fmt:formatNumber value="${item.applyLendMoney}" type="currency" pattern="￥#,#00.00#"/></td>
                      <td>${fns:getFormatDate(item.applyLendDay,'yyyy-MM-dd')}</td>
                      <td>${fns:dictName(dicts.tz_pay_type,item.applyPay,'-')}</td>
                      <td>${fns:getFormatDate(item.applyExpireDay,'yyyy-MM-dd')}</td>
                      <td>${fns:dictName(dicts.tz_lend_state,item.lendStatus,'-')}</td>
                      <td>${fns:dictName(dicts.tz_finish_state,item.applyEndStatus,'-')}</td>
                      <td>${item.nfManagerCode}</td>
                      <td>${item.nfManagerName}</td>
                      <td>${item.nTeamManagerCode}</td>
                      <td>${item.nTeamManagerName}</td>
                      <td>${item.nOrgName}</td>
                      <td>${item.fManagerCode}</td>
                      <td>${item.fManagerName}</td>
                      <td>${item.teamManagerCode}</td>
                      <td>${item.teamManagerName}</td>
                      <td>${item.orgName}</td>
                      <td>${fns:getFormatDate(item.delDate,"yyyy-MM-dd HH:mm")}</td>
                      <td>${item.modifyByName}</td>
                  </tr>
            </c:forEach>
        </tbody>
      </table>
      </div>
      <div class="pagination">${page}</div>
     <script type="text/javascript">
		$("#tableList").wrap('<div id="content-body"><div id="reportTableDiv"></div></div>');
		$('#tableList').bootstrapTable({
		method: 'get',
		cache: false,
		height: $(window).height()-220,
		
		pageSize: 20,
		pageNumber:1
		});
		$(window).resize(function () {
		$('#tableList').bootstrapTable('resetView');
		});
	</script>
</div>
</body>
</html>