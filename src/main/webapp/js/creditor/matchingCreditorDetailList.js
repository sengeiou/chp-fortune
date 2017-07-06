//撤销记录弹框
 $(function(){
  var iframe = getCurrentIframeContents();
  $("#cancelHistory",iframe).click(function(){
	var matchingId = $("#matchingId").val();
	var lendCode = $("#lendCode").val();
	var url, argment, callback;
	url = '/creditor/matchingCreditorDetail/cancelHistory';
	argment = {'matchingId':matchingId,'lendCode':lendCode};
	load_callback = function(arg){
	};
	close_callback = function(arg){
	};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
})
}
)
