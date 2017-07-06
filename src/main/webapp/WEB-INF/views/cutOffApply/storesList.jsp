<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default">
<script type="text/javascript">

	$(document).ready(function() {
		//定义缓存数组，缓存分页情况下多页选择数据
		var lastStores = $.parseJSON($("#storesSelected").val());
		var stores =new Array();
		if(lastStores != null){
			stores = lastStores;
		}
		
		$("#all").click(function() {
			$('input[name="orgIds"]').prop("checked",this.checked); 
			//全选按钮存储或移除数据
			$('input[name="orgIds"]').each(function(i){
				addOrRemoveCache(this.id,stores);
			});
		});
		
		//为列表中每个复选框添加存入和移除缓存操作
		$('input[name="orgIds"]').each(function(i){
			$("#" + this.id).click(function(){
				addOrRemoveCache(this.id,stores);
			});
		});
		//将已经缓存的数据在选择列表中
		$('input[name="orgIds"]').each(function(i){
			var orgId = this.id;
			$.each(stores,function(i,n){
				if(n.id == orgId){
					$("#" + orgId).attr("checked","checked");
				}
			})
		});
		
	});
	
	//将列表数据进行缓存或者移除缓存
	function addOrRemoveCache(domId,stores){
		var storeId = domId;
		var storeName = $("#" + domId).attr("sname");
		if($("#" + domId).is(":checked")){
			var store = new Object();
			store["id"] = storeId;
			store["sname"] = storeName;
			var hasExist = false;
			$.each(stores,function(i,n){
				if(n.id ==  storeId){
					hasExist = true;
					return false;
				}
			});
			if(!hasExist){
				stores.push(store);
			}
		}else{
			$.each(stores,function(i,n){
				if(n.id ==  storeId){
					stores.splice(i,1);
					return false;
				}
			});
		}
		$("#storesSelected").val(JSON.stringify(stores));
	}
	
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/cutOff/${queryURL }");
		$("#searchForm").submit();
		return false;
	}
</script>
<title>门店单据和组员关系管理</title>
</head>
<body>
	<form:form id="searchForm" action="${ctx}/cutOff/${queryURL }"
		method="post">
		<input type="hidden" id="storesSelected" name="storesSelected" value="${org.storesSelected}" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	    <div class='box1 mb10'>
		<table class="table1">
		   <tr>
			<label class='lab'>门店名称：</label>
			<input name="name" value="${org.name}" class="cf_input_text178" type='text'/> 
			<%-- <label>归属区域：</label><input name="area.name" value="${org.area.name }" class="input-medium" style="width: 90px;" /> &nbsp; --%>
			<input id="btnSubmit" class="btn btn_sc ml10" type="submit" value="查询" />
			</tr>
		</table>
		</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th width="30"><input id="all" type="checkbox" /></th>
			<th>机构名称</th>
			<!-- <th>归属区域</th> -->
		</tr>
		</thead>
		<c:forEach items="${page.list }" var="org">
			<tr>
				<td width="30">
				<c:if test="${org.storesSelected ne 'true' }">
				<input name="orgIds" type="checkbox" id="${org.id}" sname="${org.name}" /></c:if></td>
				<td>${org.name }</td>
				<%-- <td>${org.area }</td> --%>
			</tr>
		</c:forEach>
	</table>
	<div>${page}</div>
</body>
</html>