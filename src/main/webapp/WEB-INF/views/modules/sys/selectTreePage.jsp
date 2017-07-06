<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/search-tree.js"></script>
<div>
	<div class="form-group">
	    <input id="search_condition" type="text" placeholder="请输入搜索条件" class="form-control" />
	    <input id="returnP" value="" type="hidden" label="">
	</div>
	<div id="ztree" class="ztree"></div>
<!-- 	<div id="divTool" class="clearfix form-actions"> -->
<!-- 		<div class="col-md-offset-3 col-md-9"> -->
<!-- 			<button type="button" class="btn cf_btn-primary btn-sm" onclick="ok_Btn();">保存 </button>&nbsp; &nbsp; &nbsp; -->
<!-- 			<button type="button" class="btn cf_btn-primary btn-sm" onclick="cancel();">关闭</button> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>


