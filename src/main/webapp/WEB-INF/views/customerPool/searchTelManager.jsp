<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<form id="searchTelManagerForm"   >
      		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td><label class="lab">电销专员编号：</label><input type="text" class="cf_input_text178"  id="userCodeCon"   value="${customerPool.userCode}"></td>
                <td><label class="lab">电销专员姓名：</label><input type="text" class="cf_input_text178"  id="userNameCon" value="${customerPool.userName}"></td>
                <td><label class="lab">所属营业部：</label><input type="text" class="cf_input_text178"  id="orgNameCon" value="${customerPool.orgName}"></td>
            </tr>  
        </table>
         <div class="tright pr30">
        	<input type="button" id="searchTelManager" value="搜索"  class="btn cf_btn-primary"/>
        	<input type="button"  id="clearTelCondition" value="清除" class="btn cf_btn-primary"/>
        </div>
     </form>      
      <p class="mb10">
       	<input type="button"   id="checkConfirm4TelManager" class="btn btn_sc ml10" value="分配"/>
      </p>
    <table class="table table-striped table-bordered table-condensed data-list-table" id="telManagerSearchTele">
        <thead>
        <tr>
            <th>选择</th>
            <th>电销专员编号</th>
            <th>电销专员姓名</th>
            <th>所属营业部</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item" >
        <tr>
            <td><input type="radio"  name="userIdR" class="borrowMonthableListCheckbox" value="${item.userId }"  userName="${item.userName }" /></td>
            <td>${item.userCode}</td>
            <td>${item.userName }</td>
            <td>${item.orgName }</td>
        </tr>
        </c:forEach>
    </table>
    <div class = "pagination">${page }</div>