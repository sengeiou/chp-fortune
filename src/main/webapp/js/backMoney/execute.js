

/**
 * 提交执行回款结果
 */
function executeSubmit(){
	var et = $("input[name='checkExaminetype']:checked").val();
	var platformId = $("input[name='platformId']:checked").val();
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
	} else {
		if (platformId==null || platformId=='') {
			BootstrapDialog.alert("请选择中间人信息！");
			return;
		}
	}
	contents_getJsonForSync(
			ctx+'/myTodo/backMoney/execute', 
			$("form").serialize(), 
			'post',
			function(result){
				if (result.message=='') {
					loadingMarkShow();
					window.location.href=ctx+"/myTodo/backMoney/executeList";
				} else {
					BootstrapDialog.alert(result.message);
				}
			},
			function(){},null);
}
 
/**
  * 批量回款
  */
 function toMultiBackMoney(){
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
  	url = "/myTodo/backMoney/toMultiBackMoney";
  	argment = null;
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text('批量回款');
		$('[name="checkExaminetype"]',iframe).change(function(){
			if($(this).val()=="1"){
				$("#T6",iframe).removeAttr("style");
				$("#T7",iframe).removeAttr("style");
				$("#T8",iframe).attr("style","display:none;");
				$("#checkReason",iframe).attr("style","display:none;");
			}else{
				$("#T6",iframe).attr("style","display:none;");
				$("#T7",iframe).attr("style","display:none;");
				$("#T8",iframe).removeAttr("style");
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
					"platformId":$("input[name='platformId']:checked").val(),
					"checkExaminetype":$("input[name='checkExaminetype']:checked").val(),
					"checkExamine":$("[name='checkExamine']").val(),
					"checkReason":$("[name='checkReason']").val(),
					"isJZH":$("[name='isJZH']:checked").val()
			};
		}
  		contents_getJsonForSync(
  					ctx + "/myTodo/backMoney/multiExecute", 
  					data, 
					'post',
					function(result){
						if (result!=null && result.message!='') {
							BootstrapDialog.alert(result.message+"操作失败",function(){
								loadingMarkShow();
								$("#search").click()});
						} else {
							BootstrapDialog.alert("操作成功",function(){
								loadingMarkShow();
								$("#search").click()});
						}
					},
					function(error){
						BootstrapDialog.alert(error);
					},null);
  	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var et = $("input[name='checkExaminetype']:checked").val();
    	var platformId = $("input[name='platformId']:checked").val();
    	var em = $("[name='checkExamine']").val();
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
    	}else{
    		if (platformId==null || platformId=='') {
    			BootstrapDialog.alert("请选择中间人信息！");
    			return;
    		} 
    	}
  	  sub.closeSubWindow();
    });
 }
 
 /**
  * 线上回款
  */
 function toOnlineBackMoney(){
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
  	var jzhFlag = $("#jzhFlag").val();
  	url = "/myTodo/backMoney/toOnlineBackMoney";
  	argment = {"jzhFlag":jzhFlag};
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text('线上回款');
	};
  	close_callback = function(arg,frame){};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var pf = $("#platformId").val();
    	if (pf==null || pf =="") {
    		BootstrapDialog.alert("请选择回款平台！");
    		return;
    	}
    	var data = {};
		if ($("input[name='backmId']:checked").length<1) {
			// 无勾选
			data = $(iframe).find('form').serialize();
		} else {
			// 有勾选
			data = {
					"backmId":getBackmIds(),
					"platformId":$("[name='platformId']").val(),
					"interfaceType":$("input[name='interfaceType']:checked").val()
			};
		}
    	sub.closeSubWindow();
  		BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
  				function(r){
  					if (r) {
  						contents_getJsonForSync(
  								ctx + "/myTodo/backMoney/onlineBackMoney", 
  								data, 
  								'post',
  								function(result){
  									if (result.count=='0') {
  										BootstrapDialog.alert(result.message);
  									} else {
  										BootstrapDialog.alert(result.message,
  								  			function(){
  												loadingMarkShow();
  												$("#searchForm").submit();
  											});
  									}
  								},
  								function(error){
  									if (error==null || error=="") {
  										error = "连接超时";
									}
  									BootstrapDialog.alert(error);
  								},null);
  					}
  			});
    });
 }
 
 /**
  * 线下回款
  */
 function toOfflineBackMoney(){
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
  	var jzhFlag = $("#jzhFlag").val();
  	url = "/myTodo/backMoney/toOfflineBackMoney";
  	argment = {"jzhFlag":jzhFlag};
	var iframe = getCurrentIframeContents();
  	load_callback = function(iframe){
  		$('.modal-title').text("线下回款");
  		$('[name="tp"]',iframe).change(function(){
			if($(this).val()=="0"){
				$("#upl",iframe).attr("style","display:none;");
				// 中金平台需要选择导出格式
	  			if($('[name="platformId"]').val()=="2"){
	  				$("#et",iframe).removeAttr("style");
	  			}
	  			// 网银需要选择导出是否拆分
	  			if($('[name="platformId"]').val()=="5"){
	  				$("#wySplit",iframe).removeAttr("style");
	  			}
			}else{
  				$("#wySplit",iframe).attr("style","display:none;");
				$("#et",iframe).attr("style","display:none;");
				$("#upl",iframe).removeAttr("style");
			}
		});
  		$('[name="platformId"]',iframe).change(function(){
  			// 中金平台需要选择导出格式
  			if($(this).val()=="2" && $('[name="tp"]:checked',iframe).val()=="0"){
  				$("#et",iframe).removeAttr("style");
  			}else{
  				$("#et",iframe).attr("style","display:none;");
  			}
  			// 网银需要选择导出是否拆分
  			if($(this).val()=="5" && $('[name="tp"]:checked',iframe).val()=="0"){
  				$("#wySplit",iframe).removeAttr("style");
  			}else{
  				$("#wySplit",iframe).attr("style","display:none;");
  			}
  		});
	};
  	close_callback = function(arg,frame){
  		var inOrOut = $(frame).find("input[name='tp']:checked").val();
  		if('0'==inOrOut){
  			// 导出数据
			BootstrapDialog.confirm("共"+count+"条数据，是否继续操作？",
  				function(r){
  					if (r) {
  						var platformId = $("[name='platformId'] option:selected").val();
  						var exportType = $("[name='exportType'] option:selected").val();
  						var isSplit = $("[name='isSplit']:checked").val();
  						var $form = $("#searchForm").clone(true);
  						$form.remove("[name='platformId']");
  						$form.remove("[name='exportType']");
  						$input1 = $("<input type='hidden' name='platformId' value='"+platformId+"'/>");
						$input2 = $("<input type='hidden' name='exportType' value='"+exportType+"'/>");
						$input3 = $("<input type='hidden' name='backmId' value='"+getBackmIds()+"'/>");
						$input4 = $("<input type='hidden' name='isSplit' value='"+isSplit+"'/>");
						$form.append($input1);
						$form.append($input2);
						$form.append($input3);
						$form.append($input4);
						postFormReplaceGet($form,ctx + "/myTodo/backMoney/offlineBackMoney")
  					}
  			});
  		}else {
  			// 上传数据
  			// 异步上传
  			var fd = new FormData($("#offlineForm")[0]);
  			var element = $("#subClose");
	  		contents_getJsonForFileUpload(
	  				element, 
	  				ctx + "/myTodo/backMoney/uploadResult", 
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
		}
  	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    //自定义出发关闭回调函数的动作
    $('#subClose',iframe).off('click').on('click',function(){
    	var pf = $("#platformId").val();
    	if (pf==null || pf =="") {
    		BootstrapDialog.alert("请选择回款平台！");
    		return;
    	}
    	
    	var inOrOut = $("input[name='tp']:checked").val();
    	if (inOrOut == '1') {
			// 上传时，判断文件类型
    		var filename = $("#fileImport").val();
	    	if(filename ==null || filename ==''){
	    		BootstrapDialog.alert("请选择文件！");
				return;
	    	} else if(!ifExcel(filename)){
	    		return;
	    	}
		}
    	sub.closeSubWindow();
    });
 }
 
