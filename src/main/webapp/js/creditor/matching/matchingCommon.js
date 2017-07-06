/**
 * 待推荐办理事件
 * @param matchingId 待推荐债权主键
 * @param matchingFirstdayFlag 首期非首期标识
 * @param distributedFlag 分派债权页面标识
 */
function banli(matchingId,matchingFirstdayFlag,distributedFlag){
	contents_getJsonForSync(
 			ctx+'/matchingcreditor/getCreditorConfig?matchingId='+matchingId,  
 			{}, 
			'get', 
			//成功后执行
			function(json){
 				if(json==0){// 如果没有匹配规则
 					alert('没有设置错期匹配规则，请设置！');
 				}else{
 					checkppbl(matchingId,matchingFirstdayFlag,distributedFlag);
 				}
 			},
 			 //失败后执行
			   function(){
  	    	  alert("程序内部错误");
  	    	
  	      }  ,null);
}
//匹配投资比例
function checkppbl(matchingId,matchingFirstdayFlag,distributedFlag){
	contents_getJsonForSync(
 			ctx+'/matchingcreditor/getCreditorperRuleConfig?matchingFirstdayFlag='+matchingFirstdayFlag,  
 			{}, 
			'get', 
			//成功后执行
			function(json){
 				if(json==0){// 如果没有匹配规则
 					alert('没有该产品的投资比例规则，请设置！');
 				}else{
 					window.location.href=ctx+'/matchingcreditor/toHandleFrist?matchingId='+matchingId+'&distributedFlag='+distributedFlag;
 				}
 			},
 			 //失败后执行
			   function(){
  	    	  alert("程序内部错误");
  	      }  ,null);
}
/**
 * 封装多选框计算
 * @param url
 * @param obj
 */
function checkboxFun(url,obj,boo){
	var money=Number($(".money").val());
	var selectCreditValueId = $("#selectCreditValueId").val(); // 选中的债权编号
	 if (obj.prop("checked")) { // 选中
		num = Number($(".num").val());
		$(".num").val(+num+1);
		if(typeof(selectCreditValueId)!='undefined' && selectCreditValueId!=null && selectCreditValueId!=''){
			selectCreditValueId= selectCreditValueId +","+obj.val();
		}else{
			selectCreditValueId= obj.val();
		}
	
		$("#selectCreditValueId").val(selectCreditValueId);
		contents_getJsonForSync(
			url+selectCreditValueId,
			null,
			'get',
			function(result) {
				var result = Number(result).toFixed(2);
				$(".money").val(result);
			}
		);
	}else{ // 取消
		num = $(".num").val();
		$(".num").val(+num - 1);
		var selectCreditValueIdArr = null;
		if(typeof(selectCreditValueId)!='undefined' && selectCreditValueId!=null && selectCreditValueId!=''){
			 selectCreditValueIdArr = selectCreditValueId.split(',');
		}
		if(selectCreditValueIdArr!=null && selectCreditValueIdArr.length>0){
			selectCreditValueId = removeStrInArr(selectCreditValueIdArr,obj.val());
			$("#selectCreditValueId").val(selectCreditValueId);
		}else{
			$("#selectCreditValueId").val('');
			selectCreditValueId='';
		}
		contents_getJsonForSync(
				url+selectCreditValueId,
				null,
				'get',
				function(result) {
					var result = Number(result).toFixed(2);
					$(".money").val(result);
				}
			);
	}
}




/**
 * checkbox计算全选中金额
 */
function checkboxAllFun(url,obj,boo){
	var selectCreditValueId = $("#selectCreditValueId").val(); 
	num = Number($(".num").val());
	if(obj.is(':checked')){ // 选中
		var lendCode = obj.closest('table').find(':checked[value]').map(function() {
			return this.value;
		}).get();
		if(typeof(selectCreditValueId)!='undefined' && selectCreditValueId!=null && selectCreditValueId!=''){
			 $.each(lendCode, function(i,val){  // 循环已
					if(selectCreditValueId.indexOf(val)==-1){ // 已经有的值不在放入
					selectCreditValueId = selectCreditValueId +','+ val;
					}
			 });
		}else{
			selectCreditValueId = lendCode.join(',');
		}
		 $("#selectCreditValueId").val(selectCreditValueId);
		contents_getJsonForSync(
				url+selectCreditValueId,
				{},
				'get',
				function(result) {
					var result = Number(result).toFixed(2);
					$(".money").val(result);
				}
		);
		
	}else{ // 取消
		var creditValueIdArr = obj.closest('table').find("input[name='creditValueId'][id!='SelectAll']").map(function() {
			return this.value;
		}).get();
		var selectCreditValueIdArr =null;
		if(typeof(selectCreditValueId)!='undefined' && selectCreditValueId!=null && selectCreditValueId!=''){
			 selectCreditValueIdArr = selectCreditValueId.split(',');// 已选择的债权数组
		}
		if(selectCreditValueIdArr!=null && selectCreditValueIdArr.length>0){
			for(var i=0; i<creditValueIdArr.length;i++){
				if(selectCreditValueId.indexOf(creditValueIdArr[i])!=-1){
					selectCreditValueId = removeStrInArr(selectCreditValueIdArr,creditValueIdArr[i]);	
				}
			}
			
			$("#selectCreditValueId").val(selectCreditValueId);
		}else{
			$(".money").val(Number(0).toFixed(2));
			$(".num").val(num);
		}
		contents_getJsonForSync(
				url+selectCreditValueId,
				{},
				'get',
				function(result) {
					var result = Number(result).toFixed(2);
					$(".money").val(result);
					
				}
		);
	}
	if(typeof(selectCreditValueId)!='undefined' && selectCreditValueId!=null && selectCreditValueId!=''){
		$(".num").val(selectCreditValueId.split(",").length);
	}else{
		$(".num").val(0);
	}
}

/**
 * 删除 数字中某项 同时返回 删除后的数组
 * @param arr
 * @param delStr
 */
function removeStrInArr(arr,delStr){
	arr.splice($.inArray(delStr,arr),1);
	if(arr!=null && arr.length>0){
		return arr.join(',');
	}else{
		return '';
	}
}

function round(number, precision) {
	 if (isNaN(number))
	 number = 0;
	 var prec = Math.pow(10, precision);
	 var result = Math.round(number * prec);
	 result = result / prec;
	 return result;
	 } 