<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript" src="${ctxWebInf}/js/customerPool/teleSaleManager.js"></script>
	<script type="text/javascript" src="${ctxWebInf}/js/customerPool/distributeManager4List.js"></script>
	<script src="${ctxWebInf }/js/common/jquery.validate.extend.js" type="text/javascript"></script>
	<div class="body_r">
    <div class="box1 mb10">
    	<form id="distributeForm" action="${ctx}/customerPoolController/distributeManager" >
    		<input type="hidden"   name="id" value="${id}">
    	 	<input type="hidden"   name="customerCode" value="${customerCode}">
            <input type="hidden" id="userId" name="userId" value="${userId}">
            <input type="hidden" id="isTeleManager" name="isTeleManager" value="${isTeleManager}">
            <input type="hidden" id="isListFlag" name="isListFlag" value="1">
            
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
             	<!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178"  name="customerName" value="***" disabled="true"></td>
                <%-- <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178"  name="customerName" value="${customerPool.customerName}" disabled="true"></td> --%>
                <td><label class="lab">客户编号：</label><input type="text" class="cf_input_text178" name="customerCode" value="${customerPool.customerCode}" disabled="true"></td>
                <c:if test="${isTeleManager == '0' }"></c:if> <!-- 判断，若是电销理财团队主管、电销理财现场经理、电销数据主管、电销部门负责人，则显示  -->
	                <td><label class="lab">电销理财专员：</label><input type="text" class="cf_input_text178" id="userName" name="userName" value="${userName}" disabled="true"  required>
	                    <input type="button" id="chooseUser"  class="btn btn_sc" value="分配"/>
	                </td>
                
            </tr>  
            <c:if test="${isTeleManager == '1' }"> </c:if><!-- 若是电销理财专员，则显示 -->
	            <tr>
	                <td><label class="lab">理财经理：</label>
	                    <input type="text" class="cf_input_text178"  id="managerName"  name="managerName" value="${managerName}" disabled="true"  required />
	                    <input type="hidden" id="managerId" name="managerId" value="${managerId}"  required />
	                    <input type="button" id="chooseManager" class="btn btn_sc" value="分配" /> 
	                </td>
	                <td><label class="lab">原理财经理：</label>
	                    <input type="text" class="cf_input_text178"   value="${oldManagerName}" disabled="true" >
	                </td>
	            </tr>   
        </table>
        </form>
      </div>  
       <div class="tright pr30">
        	<input type="button" id="saveDistribute"   value="保存"  class="btn cf_btn-primary"/>
        	<input type="button" onclick="history.go(-1)" value="返回" class="btn cf_btn-primary"/>
        </div>      
	</div>
