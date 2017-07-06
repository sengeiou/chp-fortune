
 
/**
 * 批量回款审批
 */
 function multiApproval(){
	var count = $("input[name='backmId']:checked").length;
	var totalCount = $("#hDataAmount").val();
	if (count<1) {
		count = totalCount;
		if (count<1) {
			BootstrapDialog.alert("没有可以操作的数据！");
			return;
		}
	}
	// 调用子窗口的参数
  	var url, argment, callback;
  	url = "/myTodo/backMoney/toMmultiApproval";
  	argment = null;
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text('批量审批');
  		$('[name="checkExaminetype"]',iframe).change(function(){
			if($(this).val()=="1"){
				$("#checkExamine",iframe).attr("style","display:none;");
				$("#checkReason",iframe).attr("style","display:none;");
			}else{
				$("#checkExamine",iframe).removeAttr("style");
				var val = $("[name='checkExamine']").val();
				if ('37'==val) {
					$("#checkReason").removeAttr("style");
				}
			}
		});
	};
  	close_callback = function(arg,frame){
  		BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
  				function(r){
  					if (r) {
  						var data = {};
						if ($("input[name='backmId']:checked").length<1) {
							// 无勾选
							data = $("form").serialize();
						} else {
							// 有勾选
							data = {
									"backmId":getBackmIds(),
									"checkExaminetype":$("input[name='checkExaminetype']:checked").val(),
									"checkExamine":$("[name='checkExamine']").val(),
									"checkReason":$("[name='checkReason']").val()
							};
						}
  						contents_getJsonForSync(
  								ctx + "/myTodo/backMoney/multiApproval", 
  								data, 
  								"post", 
  								function(result){
  									BootstrapDialog.alert(result.message,function(){
  										loadingMarkShow();
  										$('#search').click()});
  								},
  								function(error){
  									BootstrapDialog.alert(error);
  								},null);
  					}
  			});
  	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var et = $("input[name='checkExaminetype']:checked").val();
    	var ce = $("[name='checkExamine']").val();
    	var br = $("input[name='checkReason']").val();
    	if (et==null) {
    		BootstrapDialog.alert("请选择审批结果！");
    		return;
    	} 
    	if (et=='2') {
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
    	}
  	  sub.closeSubWindow();
    });
  }

 /**
  * 提交审批结果
  */
 function approvalSubmit(){
 	var et = $("input[name='checkExaminetype']:checked").val();
 	var em = $("#checkExamine").val();
 	var br = $("#checkReason").val();
 	if (et==null) {
 		BootstrapDialog.alert("请选择审批结果！");
 		return;
 	} 
 	if (et=='2') {
 		if (em==null || em =="") {
 			BootstrapDialog.alert("请选择不通过原因！");
 			return;
 		}
 		if (em=='37') {
 			if (br==null || br =="") {
 				BootstrapDialog.alert("请输入具体原因！");
 				return;
 			}
 		}
 	}
 	contents_getJsonForSync(
			ctx+'/myTodo/backMoney/approval', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/myTodo/backMoney/approvalList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);
 }

 /**
  * 导出Excel
  * @param url 不同导出对应的url
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
				ctx+"/myTodo/backMoney/"+ url+"Check",
				data, 
				'post',
				function(result){
					if (result=='0') {
						BootstrapDialog.alert("没有符合条件的数据");
					} else {
						BootstrapDialog.confirm('共'+result+'条符合条件的数据，确定导出？', function(result){
					         if(result){
					        	 if (itemCount<1) {
					        		 postFormReplaceGet($("#searchForm"),ctx+"/myTodo/backMoney/"+ url);
					        	 } else {
					        		 postParamReplaceGet(data, ctx+"/myTodo/backMoney/"+ url);
								}
					         }
					     });
					}
				},
				function(){},null);
 }