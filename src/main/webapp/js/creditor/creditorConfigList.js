
/**
 * 列表页操作事件
 * @param status
 */
function handle(id,status){
	var url=ctx+"/creditor/creditorConfig/updateStatus";
	contents_getJsonForSync(url,{'id':id,'dictConfigZdr':status},"post",callFunction,errorCal,null);
}

$(function(){
	var iframe = getCurrentIframeContents();
	
	//批量启用停用
	$('.toEnable',iframe).click(function(){
		toEditSta('启用','/creditor/creditorConfig/toEnable','id');
	});
	
	$('.toDisable',iframe).click(function(){
		toEditSta('停用','/creditor/creditorConfig/toDisable','id');
	});
})

//函数回调
function callFunction(result,par){
	 if(result == 'OK'){
		 BootstrapDialog.alert('修改成功！',function(){
			 location.reload();
		 });
	 }else{
		 BootstrapDialog.alert('修改失败！');
	 }
}

function errorCal(){
	BootstrapDialog.alert("系统出错,请稍后……");
}

$(function(){
	// 删除 
	$('#removeButton').click(function(){
		 BootstrapDialog.confirm('确定要删除吗？', function(result){
	         if(result){
	        	 var userFlag = "";
	     		var id = "";
	     		$("input[name='id']:checked").each(function(){
	     			userFlag = $(this).parent().nextAll("#userFlag").text();
	     			if(userFlag=='启用'){
	     				return false;
	     			}
	     			id = $(this).val()+","+id;
	     		});
	     		if(userFlag=='启用'){
	     			BootstrapDialog.alert('存在没有停止的匹配,请勿操作');
	     			return;
	     		}
	     		contents_getJsonForSync(
	     			ctx + "/creditor/creditorConfig/updateStatus", 
	     			{"id":id},
	     			'post', 
	     			function(result) {
	     					if (result == "NG") {
	     						BootstrapDialog.alert('删除失败');
	     					} else {
	     						$("#searchForm").submit();
	     					}
	     				},
	     			function(){
	     				BootstrapDialog.alert('删除失败');
	     			},
	     			null
	     		);}
	     });
});
});
