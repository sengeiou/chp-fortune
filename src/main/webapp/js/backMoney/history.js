

/**
 * 退回
 */
function returnBack(){
	var ce = $("#checkExamine").val();
	var br = $("#checkReason").val();
	if (ce==null || ce=='') {
		BootstrapDialog.alert("请选择退回原因！");
		return;
	} 
	if (ce=='37') {
		if (br==null || br =="") {
			BootstrapDialog.alert("请输入退回原因！");
			return;
		}
	}
	contents_getJsonForSync(
			ctx+'/myDone/backMoney/returnBackmoney', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/myDone/backMoney/historyList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);
}
 
/**
 * 导出Excel
 * @param url
 */
 function exportExcel(url){
	 var count = $("#hDataAmount").val();
		if (0==count) {
			BootstrapDialog.alert("无可操作数据！");
			return;
		}
		var data = {};
		var itemCount = $("input[name='backmId']:checked").length;
		if (itemCount<1) {
			// 无勾选
			data = $("#searchForm").serialize();
		} else {
			// 有勾选
			data = {"backmId":getBackmIds()};
		}
		contents_getJsonForSync(
				ctx+"/myDone/backMoney/"+ url+"Check",
				data, 
				'post',
				function(result){
					if (result=='0') {
						BootstrapDialog.alert("没有符合条件的数据");
					} else {
						BootstrapDialog.confirm('共'+result+'条符合条件的数据，确定导出？', function(result){
					         if(result){
					        	 if (itemCount<1) {
					        		 postFormReplaceGet($("#searchForm"),ctx+"/myDone/backMoney/"+ url);
					        	 } else {
					        		 postParamReplaceGet(data, ctx+"/myDone/backMoney/"+ url);
					        	 }
					         }
					     });
					}
				},
				function(){},null);
 }
 
/**
 * 批量退回
 */
 function multiReturn(){
	var count = $("input[name='backmId']:checked").length;
	if (count<1) {
		BootstrapDialog.alert("请选择需要操作的数据");
		return;
	}
		
	// 调用子窗口的参数
  	var url, argment, callback;
  	url = "/myDone/backMoney/toMultiReturn";
  	argment = null;
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text('批量退回');
		$('[name="checkExamine"]', iframe).change(function() {
			var val = $("[name='checkExamine']").val();
			if ('37' == val) {
				$("#checkReason").removeAttr("style");
			} else {
				$("#checkReason", iframe).attr("style", "display:none;");
			}
		});
	};
  	close_callback = function(arg,frame){
  		var data = {};
		if ($("input[name='backmId']:checked").length<1) {
			// 无勾选
			data = $(iframe).find('form').serialize();
		} else {
			// 有勾选
			data = {
					"backmId":getBackmIds(),
					"checkExamine":$("[name='checkExamine']").val(),
					"checkReason":$("[name='checkReason']").val()
			};
		}
  		BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
  				function(r){
  					if (r) {
  						contents_getJsonForSync(
  								ctx + "/myDone/backMoney/multiReturn", 
  								data, 
  								"post", 
  								function(result){
  									BootstrapDialog.alert(result.message,function(){
  										loadingMarkShow();
  										$('#search').click();});
  								},
  								function(error){
  									BootstrapDialog.alert(error);
  								},null
  						);
  					}
  			});
  	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var br = $("[name='checkExamine']").val();
    	var ce = $("[name='checkReason']").val();
    	if (br==null || br =="") {
    		BootstrapDialog.alert("请选择退回原因！");
    		return;
    	} 
    	if (br=='37') {
    		if (ce==null || ce =="") {
    			BootstrapDialog.alert("请输入退回原因！");
    			return;
    		}
    	}
  	  sub.closeSubWindow();
    });
 }
