<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<title>公共池列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<div class="body_r">
    <div class="box1 mb10">
    	<form id="searchForm"  action="${ctx}/customerPoolController/listDistributeCustomer" method="post">
    	  <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
                <td><label class="lab">客户姓名：</label><input type="text" class="cf_input_text178" name="customerName"  value="${customerPoolEx.customerName}" ></td>
                <td><label class="lab">身份证号：</label><input type="text" class="cf_input_text178" name="card"   value="${customerPoolEx.card}" ></td>
				<td><label class='lab'>联系电话：</label><input type="text" class="cf_input_text178" name="customerTel"   value="${customerPoolEx.customerTel}" ></td>
            </tr>
            <tr> 
			   	<td><label class="lab">电销专员姓名：</label><input type="text" class="cf_input_text178"  name="teleManagerName"  value="${customerPoolEx.teleManagerName}"  ></td>
				<td><label class="lab">分配状态：</label><select class="select180"  name="dataType" >
				    <option value="">请选择</option>
				    <c:forEach items='${dicts.tz_customer_pool_type}' var="item" >
				    	<c:if test="${item.value != '1' }">
							<option value="${item.value }"  <c:if test="${customerPoolEx.dataType == item.value }">selected</c:if>    >${item.label }</option>		
						</c:if>			    
				    </c:forEach>
					</select>
				</td>
			    <td><label class="lab">理财经理：</label><input type="text" class="cf_input_text178"  name="managerName"   value="${customerPoolEx.managerName}" ></td>
            </tr>
        </table>
        
         <div class="tright pr30">
             <input type="submit" id="search" value="搜索"  class="btn cf_btn-primary"/>
             <input type="reset" value="清除" class="btn cf_btn-primary"/>
        </div>
      </div>        
    </form>
    <table class="table table-striped table-bordered table-condensed data-list-table">
    <thead>
        <tr>
            <th>客户编号</th>
            <th>客户姓名</th>
            <th>联系电话</th>
            <th>证件号码</th>
            <th>营业部</th>
            <th>理财经理</th>
            <th>电销专员编号</th>
            <th>电销专员姓名</th>
            <th>团队经理</th>
            <th>现场经理</th>
            <th>操作时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.customerCode }</td>
            <!-- 屏蔽客户姓名/手机号/身份证号 -->
            <td>***</td>
            <%-- <td>${item.customerName }</td> --%>
            <td>***</td>
            <%-- <td>${item.customerTel}</td> --%>
            <td>***</td>
            <td>${item.card}</td>
            <td>${item.storeName}</td>
            <td>${item.managerName }</td>
            <td>${item.teleManagerCode }</td>
            <td>${item.teleManagerName }</td>
            <td>${item.teamManagerName }</td>
            <td>${item.storeManagerName }</td>
            <td>${fns:getFormatDate(item.modifyTime,'yyyy-MM-dd')}</td>
            <td>
            	<c:forEach items='${dicts.tz_customer_pool_type}' var="itemType" >
						<c:if test="${item.dataType == itemType.value }">${itemType.label }</c:if>		    
				 </c:forEach>
            	<%-- <c:if test="${item.dataType == '1' }">在池中</c:if>
            	<c:if test="${item.dataType == '2' }">已分配</c:if>
            	<c:if test="${item.dataType == '3' }">退回</c:if> --%>
			</td>
            <td>
            	<auth:hasPermission key="cf:elecpoolcustomer:transact">
            		<a href="${ctx}/customerPoolController/handleCustomerDistibute?custCode=${item.customerCode}&dataType=${item.dataType}&id=${item.id}&flag=1" >办理</a>
            	</auth:hasPermission>
            	<%-- <a href="${ctx}/customerPoolController/getCustomerInfo?custCode=${item.customerCode}" >查看</a>
            	<a href="${ctx}/customerPoolController/advisoryList?custCode=${item.customerCode}" >留痕</a> --%>
            	<%-- <c:if test="${item.dataType == '3' }">
            		<a href="${ctx}/customerPoolController/distributePage?id=${item.id}" >分配</a>
            	</c:if> --%>
            	 
            </td>
        </tr>
        </c:forEach>
    </table>
    <div class="pagination">${page}</div>      
</div>
</body>

</html>