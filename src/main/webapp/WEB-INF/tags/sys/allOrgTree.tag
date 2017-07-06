<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp" %>

<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="false" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="other" type="java.lang.String" required="false" description="其他"%>

<div class="input-append">
	<input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>
	<input id="${id}Name" <c:if test="${not empty labelName}">name="${labelName}"</c:if> type="text" readonly="readonly" value="${labelValue}" <c:if test="${not empty other}">${other }</c:if>
		class="cf_input_text178 ${cssClass}" style="${cssStyle}"/>
	<a id="${id}Button" href="javascript:" class="btn ${disabled} ${hideBtn ? 'hide' : ''}" style="${smallBtn?'padding:4px 2px;':''}">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;
</div>
<div id="modal-org-sub" class="modal fade subwindow">
	<div class="modal-dialog">	
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">营业部</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="org_subClose" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		 $('#${id}Button').click(function(){
         	// 调用子窗口的参数
         	var url, argment, callback;
         	url = '/common/roleOrg/treeselect';
         	argment = {'sub_id':'modal-org-sub'};
         	load_callback = function(arg){
         		var zNodes;
     			$.ajax({
	    	   		type: "POST",
	    	   		url: "${ctx}/common/roleOrg/treeOrgjudgeAll",
	    	   		data: null,	
	    	   		async:false,
	    	   		success : function(data) {
	    	   			zNodes=data;	    
    	   			}
     			})
         		var setting = {
         				check: {
         					enable: true,
         					chkboxType: {"Y": "s", "N": "s"},
         					nocheck : true,
         					nocheckInherit : false
         				},
         				callback : {
         					onClick : function(event, treeId, treeNode) {
         						tree.expandNode(treeNode);
         					},
         					onCheck : function(e, treeId, treeNode) {
         						var nodes = tree.getCheckedNodes(true);
         						for (var i = 0, l = nodes.length; i < l; i++) {
         							tree.expandNode(nodes[i], true, false, false);
         						}
         						return false;
         					},
        				},
         				data: {
	         				key : {
	    						name : "name",
	    						title : "name"
	    					},
	    					simpleData : {
	    						enable : true,
	    						idKey : "id",
	    						pIdKey : "pId"
	    					}
         				},
         				view: { showIcon: false, selectedMulti: false, nameIsHTML: true, fontCss: setFontCss_ztree }
         			};
     			
     			repairUnchecked();
     			var tree = $.fn.zTree.init($("#ztree"), setting, zNodes);
     			sub.prop.tree = tree;
        		var treeObj = $.fn.zTree.getZTreeObj("ztree");
        		var cval = $("#${id}Id").val();
        		if(cval != ''){
        			var sval = cval.split(",");
        			for(var i=0;i<sval.length;i++){
        				var node = treeObj.getNodeByParam("id", sval[i], null);
        				if(node != null){
        					tree.expandNode(node, true, false, false);
        					tree.checkNode(node, true, true);
        					tree.selectNode(node, false);
        				}
        			}
        		}
        		
         	};
         	close_callback = function(arg){
         		$("#${id}Id").val("");
         		$("#${id}Name").val("");
         		var nodes = sub.prop.tree.getCheckedNodes(true);
         		var ids="",names="";first=true;
         		$.each(nodes,function(i){
         			if(this.type==true){//营业部节点
         				if(first){
         					first = false;
         				}else{
         					ids = ids + ",";
         					names = names + ",";
         				}
         				ids = ids + this.id;
         				names = names + this.name;
         			}
         		});
         		$("#${id}Id").val(ids);
 				$("#${id}Name").val(names);
         	};
         	
             var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
             //自定义出发关闭回调函数的动作
             $('#org_subClose').off('click').on('click',function(){sub.closeSubWindow();});
         });
	})
	
	//修复zTree无法取消选择
	function repairUnchecked(){
		var zTree_view = $.fn.zTree._z.view;
		$.fn.zTree._z.view.setChkClass = function(setting, obj, node) {
			if (!obj) return;
			if (node.nocheck === true) {
				obj.hide();
			} else {
				obj.show();
			}
			obj.removeClass();
			obj.removeAttr('class');
			obj.addClass(zTree_view.makeChkClass(setting, node));
		};
	}
</script>
	