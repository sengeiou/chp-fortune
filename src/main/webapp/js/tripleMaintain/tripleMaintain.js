$(function(){
	$("#managerChange").click(function(){
		window.location = ctx+"/maintenance/tripleSend/managerChange";
	});

	$("#orgChange").click(function(){
		window.location = ctx+"/maintenance/tripleSend/orgChange";
	});
	
	$("#userSynchro").click(function(){
		window.location = ctx+"/maintenance/tripleSend/userSynchro";
	});
	
	// 交割回调函数
    function callFunction(result,par){
    	if(result > 0){
    		BootstrapDialog.alert("成功同步"+result+"条！");
    	}else{
    		BootstrapDialog.alert("无数据需要同步！");
    	}
    }
    function errorCal(){
    	BootstrapDialog.alert("系统出错,请稍后……");
    }
    
    
    
	$("#syncAppDate").click(function(){
		 var url = ctx+"/maintenance/tripleMaintain/appSyncDate";
		   contents_getJsonForHtml(
				   url, 
				  null, 
				  "post", 
				 callFunction,
				   errorCal,
				   null);
	})
	
	$("#updateUniqueNum").click(function(){
		var unm = $("#uniqueNum").val();
		var type = $("#osId").val();
		var status = $("#sendStatus").val();
		 var url = ctx+"/maintenance/tripleMaintain/updateUnm";
		   contents_getJsonForHtml(
				   url, 
				   {'osId':type,'uniqueNum':unm,'sendStatus':status}, 
				  "post", 
				 callFunction,
				   errorCal,
				   null);
	})

});

// 查看交割履历
function record(phone,urlTemp) {
	// 调用子窗口的参数
	var url, argment, callback;
	url = "/maintenance/tripleMaintain/"+urlTemp;
	argment = {'phone':phone};
	close_callback = function(ifame){};
	load_callback = function(iframe){};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){
		sub.closeSubWindow();
	});
}

// 子页面分页
function subPage(n,s){
	if (n)
		$("#subPageNo").val(n);
	if (s)
		$("#subPageSize").val(s);
	var url = $("#subSearchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#subSearchForm").serialize(),
		'post',
		function(result){
			$('#recordDiv').html(result);
		}
	);
	return false;
}





