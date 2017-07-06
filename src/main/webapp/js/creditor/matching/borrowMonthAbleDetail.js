/**
 * 分页设置
 * @param n
 * @param s
 * @returns {Boolean}
 */
function subPage(n,s){
	if (n)
		$("#pageNo").val(n);
	if (s)
		$("#pageSize").val(s);
	var url = $("#searchForm").attr('action');
	contents_getJsonForHtml(
		url,
		$("#searchForm").serialize(),
		'post',
		function(result){
			$('#borrowMonthAbleList').html(result);
			subCheckboxEventInit();
		}
	);
	
	return false;
}

//在弹出框加载后,注册事件
function subCheckboxEventInit(){
	$("input[type='checkbox'][class!='borrowMonthClass'][id!='SelectAll']").change(function(){
		var url = ctx+"/matchingcreditor/ajaxMoney?creditMonableId=";
		var obj = $(this);
		checkboxFunMonth(url,obj,false);
	});
	$('#SelectAll').click(function(){
		if ($("#SelectAll").attr("checked")) {  
		    $(".table :checkbox ").attr("checked", true);  
		} else {  
		    $(".table :checkbox ").attr("checked", false);  
		}  
		var url = ctx+"/matchingcreditor/ajaxMoney?creditMonableId=";
		var obj = $(this);
		checkboxAllFunMonth(url,obj,false);
	});
	inTableCheck();
}
function checkHis(objparm){
	if($(objparm).is(':checked')){
		$("#xsjylszq").val("1");
	}else{
		$("#xsjylszq").val("0");
	}
}
	
function inTableCheck(){// 初始化选中
	var selectCreditMonableIds = $("#selectCreditMonableIds").val(); 
	var objArr =  $("input[type='checkbox'][class!='borrowMonthClass'][id!='SelectAll']");
	var selectNum= 0;
	if(typeof(selectCreditMonableIds)!='undefined' &&  selectCreditMonableIds!=null && selectCreditMonableIds!=''){
		var selectCreditMonableIdsArr = selectCreditMonableIds.split(','); 
		 $.each(selectCreditMonableIdsArr, function(i,val){  // 循环已选中    
			if(objArr!=null &&objArr.length>0){
				 $.each(objArr, function(childi,childVal){
					 if($(childVal).val()==val){
						 $(childVal).attr("checked", true);
						 selectNum = selectNum+1;
					 }
				 });
				 
			}
		  }); 
	}
	if(objArr.length==selectNum){
		$('#SelectAll').attr("checked", true);
	}
}