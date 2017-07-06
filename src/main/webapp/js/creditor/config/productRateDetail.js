$(document).ready(function(){
	intform();
	
});
function intform(){
	$("#matchingBillDay").attr("disabled",true);
	$("#matchingInterestStart").attr("disabled",true);
}
function billTypeChange(){
	var billtype =$("#billType").val();
	if(billtype=='1'){
		$("#matchingBillDay").attr("value",'');
		$("#matchingInterestStart").attr("value",'');
		$("#matchingBillDay").attr("disabled",true);
		$("#matchingInterestStart").attr("disabled",true);
	}
	else{
		$("#matchingBillDay").attr("disabled",false);
		$("#matchingInterestStart").attr("disabled",false);
	}
}
function getYearRate(obj,flg){
	var rate =$(obj).val();// 月利率
	var productCode = $('#productCode').val();
	
	if(rate!=''){
		contents_getJsonForStringify(
	 			ctx+'/creditor/config/rate/getYearRate?rate='+rate+'&productCode='+productCode,  
	 			{}, 
				'get', 
				function(json){
	 				if(flg=='1'){
	 					$("#yearRateLower").val(json);
	 				}else{
	 					$("#yearRateUpper").val(json);
	 				}
	 			},
	 			 //失败后执行
				   function(){
	  	    	  alert("程序内部错误");
	  	    	
	  	      }  ,null);
	}else{
		if(flg=='1'){
				$("#yearRateLower").val('');
			}else{
				$("#yearRateUpper").val('');
			}
	}
}
function  prochange(){
	var productCode = $('#productCode').val();
	if("86"==productCode){
		$("#billType").empty();
		 $("#billType").append("<option value='1'>首期</option>").selectpicker('refresh');  
	}else{
		$("#billType").empty();
		$("#billType").append("<option value='1'>首期</option>").append("<option value='2'>非首期</option>").selectpicker('refresh');  
	}
	}