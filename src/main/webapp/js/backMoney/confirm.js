

/**
 * 提交回款确认结果
 */
function confirmSubmit(){
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
	var backmId = $("#backmId").val();
	contents_getJsonForSync(
				ctx+'/myTodo/backMoney/confirmListCheck',  
				{'backmId':backmId}, 
				'post',
				function(result){
					if (result.message=='SUCCESS') {
						contents_getJsonForSync(
								ctx+'/myTodo/backMoney/confirm', 
								$("form").serialize(), 
								'post',
								function(rs){
									if (rs.message=='') {
										loadingMarkShow();
										window.location.href=ctx+"/myTodo/backMoney/confirmList";
									} else {
										BootstrapDialog.alert(rs.message);
									}
								},
								function(){},null);
					} else {
						BootstrapDialog.alert(result.message);
					}
				},
				function(){},null);
}

 
/**
 * 批量回款确认
 */
 function multiConfirm(){
	var count = $("input[name='backmId']:checked").length;
	var totalCount = $("#hDataAmount").val();
	if (count<1) {
		count = totalCount;
		if (count<1) {
			BootstrapDialog.alert("没有可以操作的数据！");
			return;
		}
	}
	var args = {};
	if ($("input[name='backmId']:checked").length<1) {
		// 无勾选
		args = $("#searchForm").serializeObject();
		if(args!=null && args!=""){ 
			for(var o in args){ 
				if(args[o] instanceof Array){ 
					args[o]=args[o].toString(); 
				} 
			} 
		}
	} else {
		// 有勾选
		args = {"backmId":getBackmIds()};
	}
	// 调用子窗口的参数
  	var url, argment, callback;
  	url = "/myTodo/backMoney/toMultiConfirm";
  	argment = args;
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text('批量确认');
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
  		var data = {};
		if ($("input[name='backmId']:checked").length<1) {
			// 无勾选
			data = $(iframe).find('form').serialize();
		} else {
			// 有勾选
			data = {
					"backmId":getBackmIds(),
					"backDay":$("#backDay").val(),
					"checkExaminetype":$("input[name='checkExaminetype']:checked").val(),
					"checkExamine":$("[name='checkExamine']").val(),
					"checkReason":$("[name='checkReason']").val()
			};
		}
  		contents_getJsonForSync(
  				ctx+'/myTodo/backMoney/confirmListCheck', 
  				data, 
  				'post',
  				function(result){
  					if (result.message=='SUCCESS') {
  						var backDay = $("#backDay").val();
  						var hMaxDate = $("#hMaxDate").val();
  						var diffDays = $("#diffDays").val();
  						
  						// 显示值未修改且数据回款日期不同
  						if (backDay == hMaxDate && diffDays>1){
  							BootstrapDialog.confirm("回款日期不一致，请确定是否往下一列表流转？",
  								function(r){
	  								if (r) {
	  									contents_getJsonForSync(
	  											ctx + "/myTodo/backMoney/multiConfirm", 
	  											data, 
	  											'post',
	  											function(rs){
	  												BootstrapDialog.alert(rs.message,function(){
	  													loadingMarkShow();
	  													$('#search').click();});
	  											},
	  											function(){},null);
	  								} else {
	  									return;
	  								}
  							});
  						} else {
  							BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
  									function(r){
	  								if (r) {
	  									contents_getJsonForSync(
	  											ctx + "/myTodo/backMoney/multiConfirm", 
	  											data, 
	  											'post',
	  											function(rs){
	  												BootstrapDialog.alert(rs.message,function(){
	  													loadingMarkShow();
	  													$('#search').click();});
	  											},
	  											function(){},null);
  								}
  							});
  						}
  					} else {
  						BootstrapDialog.alert(result.message);
  					}
  				},
  				function(){},null);
  	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var et = $("input[name='checkExaminetype']:checked").val();
    	var ce = $("[name='checkExamine']").val();
    	var br = $("input[name='checkReason']").val();
    	var backDay = $("#backDay").val();
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
    	} else {
    		if (backDay==null || backDay =="") {
    			BootstrapDialog.alert("请选择回款日期！");
    			return;
    		}
		}
  	  sub.closeSubWindow();
    });
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
 
 /**
  * 上传回盘结果
  */
 function uploadExcel(){
	 var count = $("#hDataAmount").val();
		if (0==count) {
			BootstrapDialog.alert("无可操作数据！");
			return;
		}
		// 调用子窗口的参数
	  	var url, argment, callback;
	  	url = "/myTodo/backMoney/toConfirmUpload";
	  	argment = null;
		var iframe = getCurrentIframeContents();
	  	load_callback = function(iframe){
	  		$('.modal-title').text("上传回盘结果");
		};
	  	close_callback = function(arg,frame){
	  		// 异步上传
  			var fd = new FormData($("#uploadForm")[0]);
  			var element = $("#subClose");
	  		contents_getJsonForFileUpload(
	  				element, 
	  				ctx + "/myTodo/backMoney/uploadConfirmResult", 
	  				fd, 
	  				function(result){
	  					if (result !='') {
	  						BootstrapDialog.alert(result,function(){
								loadingMarkShow();
								$('#search').click();});
						} else {
							BootstrapDialog.alert("数据处理完成",function(){
									loadingMarkShow();
									$('#search').click();});
						}
	  				},
	  				function(){
	  					BootstrapDialog.alert("服务器繁忙");
	  					return;
	  				},
	  				false);
	  	};
	    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	    //自定义出发关闭回调函数的动作
	    $('#subClose',iframe).off('click').on('click',function(){
	    	var platform = $("#platformId").val();
	    	if(platform ==null || platform ==''){
	    		BootstrapDialog.alert("请选择回款平台！");
	    		return;
	    	}
	    	
	    	
	    	// 上传时，判断文件类型
    		var filename = $("#fileImport").val();
	    	if(filename ==null || filename ==''){
	    		BootstrapDialog.alert("请选择文件！");
				return;
	    	} else if(!ifExcel(filename)){
	    		return;
	    	}
	    	sub.closeSubWindow();
	    });
 }
 
 /**
  * 退回到待回款列表
  * @param backmId
  * @param verTime
  */
 function backtoExecute(backmId, verTime){
	 $("input[name='backmId']").attr("checked",false);
	 contents_getJsonForSync(
				ctx+'/myTodo/backMoney/confirmListCheck', 
				{'backmId':backmId},  
				'post',
				function(result){
					if (result.message=='SUCCESS') {
						 BootstrapDialog.confirm('确定退回到待回款列表？', function(rs){
					         if(rs){
					             contents_getJsonForSync(
					            		 ctx+"/myTodo/backMoney/backtoExecute", 
					     				{'backmId':backmId,"verTime":verTime},  
					     				'post',
					     				function(r){
					     					if (r.message=='SUCCESS') {
					     						loadingMarkShow();
					     						window.location.href=ctx+"/myTodo/backMoney/confirmList";
					     					} else {
					     						BootstrapDialog.alert(r.message);
					     					}
					     				},
					     				function(){},null);
					         }
					     });
					} else {
						BootstrapDialog.alert(result.message);
					}
				},
				function(){},null);
 }
 
 /**
  * 退回到待回款列表
  */
 function multiBacktoExecute(){
	 var count = $("input[name='backmId']:checked").length;
	if (count<1) {
		BootstrapDialog.alert("请选择需要操作的数据");
		return;
	}
	var data = {};
	if ($("input[name='backmId']:checked").length<1) {
		// 无勾选
		data = $(iframe).find('form').serialize();
	} else {
		// 有勾选
		data = {"backmId":getBackmIds()};
	}
	contents_getJsonForSync(
			ctx+'/myTodo/backMoney/confirmListCheck', 
			data, 
			'post',
			function(result){
				if (result.message=='SUCCESS') {
					BootstrapDialog.confirm("共"+count+"条数据，确定退回到待回款列表？", function(rs){
				        if(rs){
				            contents_getJsonForSync(
				            		ctx+"/myTodo/backMoney/multiBacktoExecute", 
				            		data,  
				     				'post',
				     				function(r){
				     					BootstrapDialog.alert(r.message,function(){
				     						loadingMarkShow();
				     						$("#search").click()});
				     				},
				     				function(){},null);
				        }
				    });
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);
 }
 