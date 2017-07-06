/**
 * 提交form表单
 */
$(function(){
	$("#submit").click(function(){
		if($("#searchForm").validate().form()){
			var url=ctx+"/creditor/creditorConfig/add";
			 contents_getJsonForSync(
					 url,
					 $("#searchForm").serialize(),
					 "post",
					 callFunction,
					 errorCal,
					 null
			 );
			 return false;
		}
	});
})
//函数回调callFunction
function callFunction(result,par){
	 if(result == "OK"){
		 BootstrapDialog.alert('增加成功！',function(){
			 window.location = ctx+"/creditor/creditorConfig/list";
		 });
	 }else if(result == "REPEAT"){
		 BootstrapDialog.alert('错期匹配已存在！');
	 }else if(result == "NG"){
		 BootstrapDialog.alert('增加失败！');
	 }
}

function errorCal(){
	BootstrapDialog.alert("系统出错,请稍后……");
}