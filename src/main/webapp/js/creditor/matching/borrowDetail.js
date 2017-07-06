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
	var ids="";
	var selectBeforeValue = $("#selectCreditValueId").val();
	$("[name='creditValueId']:checkbox:checked").each(function(){
		if(selectBeforeValue.indexOf($(this).val()) <= 0 ){
			ids+="," + $(this).val();
		}
	});
	ids = selectBeforeValue+ids;
	contents_getJsonForHtml(
		url,
		$.param({'ids':ids}) + '&' +$("#searchForm").serialize(),
		'post',
		function(result){
			$('#borrowList').html(result);
			subCheckboxEventInit();
		}
	);
	
	return false;
}
//点击搜索按钮触发回车键搜索
document.onkeydown = function (e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		$("#search").click();
	}
}

//在弹出框加载后,注册事件
function subCheckboxEventInit(){
	$("input[type='checkbox'][class!='borrowClass'][id!='SelectAll']").change(function(){
		var url = ctx+"/borrow/ajaxMoney?creditValueId=";
		var obj = $(this);
		checkboxFun(url,obj,false);
	});
	$('#SelectAll').click(function(){
		if ($("#SelectAll").attr("checked")) {  
		    $(".table :checkbox ").attr("checked", true);  
		} else {  
		    $(".table :checkbox ").attr("checked", false);  
		}  
		var url = ctx+"/borrow/ajaxMoney?creditValueId=";
		var obj = $(this);
		checkboxAllFun(url,obj,false);
	});
	inTableCheck();// 初始复选框
}
function checkHis(objparm){
	if($(objparm).is(':checked')){
		$("#xsjylszq").val("1");
	}else{
		$("#xsjylszq").val("0");
	}
}
function inTableCheck(){// 初始化选中
	var selectCreditValueId = $("#selectCreditValueId").val(); 
	var objArr =  $("input[type='checkbox'][class!='borrowClass'][id!='SelectAll']");
	var selectNum= 0;
	if(typeof(selectCreditValueId)!='undefined' &&  selectCreditValueId!=null && selectCreditValueId!=''){
		var selectCreditValueIdArr = selectCreditValueId.split(','); 
		 $.each(selectCreditValueIdArr, function(i,val){  // 循环已选中    
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
