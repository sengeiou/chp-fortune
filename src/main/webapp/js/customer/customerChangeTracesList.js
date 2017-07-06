$(function(){
	iframe = getCurrentIframeContents();
	
	$(".changeTracesDetail",iframe).click(function(e){
		$(".modal-title",iframe).html("客户修改详细");
		$('#subSubmit').hide();
    	// 调用子窗口的参数
    	var url, argment, callback,changeTracesId;
    	changeTracesId = $(e.target).attr("changeTracesId");
    	url = "/customer/investment/changeTracesDetail";
    	argment = {'changeTracesId':changeTracesId};
    	load_callback = function(arg){
    		$table = $("#changeDetail");
    		$table.find("input:lt(16)").each(function(){
    			_index = $("input",$table).index($(this));
    			_nextIndex = _index + 16;
    			$nextInput = $table.find("input:eq("+_nextIndex+")");
    			if($(this).val() != $nextInput.val()){
    				$(this).css("background-color","#BFEFFF !important");
    				$nextInput.css("background-color","#BFEFFF !important");
    			}
    		});
    	};
    	close_callback = function(arg){
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
    });
});
