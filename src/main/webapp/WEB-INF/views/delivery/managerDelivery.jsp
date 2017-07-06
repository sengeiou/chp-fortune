<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>客户经理交割</title>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/delivery/managerDelivery.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/delivery/common.js" type="text/javascript"></script>
</head>
<body>
<div class="body_top body_r">
    <div class="title">
        <div class="l"><h2 class="f14 ml10">原理财经理信息</h2></div>
    </div>
    <form:form id="searchForm">
	<div class="box6">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                    <label class="lab"><span class="red">*</span>员工编号：</label>
                    <input type="text" class="autoComplete cf_input_text178" id="fManagerCode" required>
                    <input type="hidden" id="fManagerId"/>
                </td>
                <td><label class="lab"><span class="red">*</span>理财经理：</label><input type="text" class="cf_input_text178" id="fManagerName" disabled></td>
                <td><label class="lab"><span class="red">*</span>团队经理：</label><input type="text" class="cf_input_text178" id="teamManagerName" disabled></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>团队名称：</label><input type="text" class="cf_input_text178" id="teamName" disabled></td>
                <td><label class="lab"><span class="red">*</span>营业部：</label><input type="text" class="cf_input_text178" id="orgName" disabled></td>
                 <td id="lendTime" style="display:none;padding-left:45px">
                    <label><span class="red">*</span>交割的出借时间：</label>
                    <input type="text" class="Wdate input_text70" id="startTime" name="startTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"  value='<fmt:formatDate value="${deliveryEx.startTime }" pattern="yyyy-MM-dd"/>' required/> -
                    <input type="text" class="Wdate input_text70" id="endTime" name="endTime"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" value='<fmt:formatDate value="${deliveryEx.endTime }" pattern="yyyy-MM-dd"/>' required/>
                </td>
            </tr>
            <tr >
               
            </tr>
        </table>
	 </div>
	 </form:form>
    <div class="title">
        <div class="l"><h2 class="f14 ml10">新理财经理信息</h2></div>
    </div>
    <form:form id="form">
	<div class="box6">
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                    <label class="lab"><span class="red">*</span>员工编号：</label>
                    <input type="text" class="auto cf_input_text178" id="nfManagerCode" required />
                    <input type="hidden" id="nfManagerId"/>
                </td>
                <td><label class="lab"><span class="red">*</span>理财经理：</label><input type="text" class="cf_input_text178" id="nfManagerName" disabled></td>
                <td><label class="lab"><span class="red">*</span>团队经理：</label><input type="text" class="cf_input_text178" id="nTeamManagerName" disabled></td>
            </tr>
            <tr>
                <td><label class="lab"><span class="red">*</span>团队名称：</label><input type="text" class="cf_input_text178" id="nTeamName" disabled></td>
                <td><label class="lab"><span class="red">*</span>营业部：</label><input type="text" class="cf_input_text178" id="nOrgName" disabled></td>
            </tr>
        </table>
	 </div>
	  </form:form>
       <div class="tright pr30">
          
               <input class="btn cf_btn-primary" type="button" id="delivery" value="客户全部交割" />
         
               <input class="btn cf_btn-primary" type="button" id="achievement" value="业绩全部交割" />

               <input class="btn cf_btn-primary" type="reset" id="reset" value="取消" />
       </div>
      
</div>
</body>
</html>
