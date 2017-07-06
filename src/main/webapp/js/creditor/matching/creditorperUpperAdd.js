/**
 * 提交form表单
 */
$(function(){
	$("#submit").click(function(){
		// $form = $(this).closest('form');
		$form = $("#addForm");
		if($form.validate().form()){
			var url=ctx+"/creditor/CreditorperUpper/add";
			 contents_getJsonForSync(
					 url,
					 $("#addForm").serialize(),
					 "post",
					 function(result){
						 if(result == "OK"){
							 BootstrapDialog.alert('增加成功！',function(){
								 $("#submit").submit();
								 window.location = ctx+"/creditor/CreditorperUpper/list";
							 });
						 }else if(result == "REPEAT"){
							 BootstrapDialog.alert('数据添加失败,该职业的此类型的匹配数据存在!');
						 }else if(result == "NG"){
							 BootstrapDialog.alert('数据添加失败,该职业的此类型的匹配数据存在！');
						 }

					 }
			 );
		}
	});
})
//函数回调callFunction
function callFunction(result,par){
	 if(result == "OK"){
		 BootstrapDialog.alert('增加成功！',function(){
			 $("#submit").submit();
			 window.location = ctx+"/creditor/CreditorperUpper/list";
		 });
	 }else if(result == "REPEAT"){
		 BootstrapDialog.alert('数据添加失败！');
	 }else if(result == "NG"){
		 BootstrapDialog.alert('增加失败！');
	 }
}

function errorCal(){
	BootstrapDialog.alert("系统出错,请稍后……");
}