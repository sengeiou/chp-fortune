/**
 * 金账户开户页面
 */
$(function(){
	
});
var sub ;
/**
 * 成功按钮操作
 */ 
function approve(){
	
	var flowInfo=getFlowInfo();
	if(flowInfo==''){
		return ;
	}
	contents_getJsonForSync(
		ctx+'/trusteeship/change/dispatch', 
		{'flowInfo':flowInfo,'oper':'1'}, 
		'post', 
		function(data){
			if(data.message == 'true'){
				BootstrapDialog.alert("操作成功",function(){$(".search").click();});
			}else{
				BootstrapDialog.alert("操作失败：" + data.message);
			}
		},null,null
	);

}

/**
 * 退回操作按钮操作
 */ 
function backSub(){
	
	var auditCheckExamine=$("#auditCheckExamine").val();
	if(auditCheckExamine==''){
		BootstrapDialog.alert("退回原因不能为空");
		return ;
	}
	var flowInfo=getFlowInfo();
	if(flowInfo==''){
		return ;
	}
	
	var auditCheckExamine=$("#auditCheckExamine").val();
	contents_getJsonForSync(
		ctx+'/trusteeship/change/dispatch',
		{'flowInfo':flowInfo,'oper':'2','auditCheckExamine':auditCheckExamine},
		'post', 
		function(data){
			if(data.message == 'true'){
				BootstrapDialog.alert("退回成功",function(){$(".search").click();});
			}else{
				BootstrapDialog.alert("退回失败:" + data.message);
			}
		},null,null
	);
}

/**
 * 获取选中ids
 */
function getIds(){
	 var ids="";
	 $("input[type='checkbox'][name='dcheckbox']:checked:not(.checkAll)").each(function(){
		 if($(this).val()!='on'){
			 
			 if($(this).val()!=''){
				 ids=ids+$(this).attr("applyId")+",";
			 }
		 }
	 }) 

	 ids=ids.substring(0,ids.length-1)
	 
	 return ids;
}

/**
 * 获取选中据工作流信息
 */
function getFlowInfo(){
	
	 var json=""
	 $("input[type='checkbox'][name='dcheckbox']:checked:not(.checkAll)").each(function(){
		    
		 if($(this).val()!=""){
			 
			 var wobNum=$(this).val();
			 var flowName=$(this).attr("flowName");
			 var stepName=$(this).attr("stepName");
			 var token=$(this).attr("token")
			 var flowId=$(this).attr("applyId")
			 var flowType=$(this).attr("changerTypeVal")	
			 json=json+"{\"token\":\""+token+"\",\"wobNum\":\""+wobNum+"\",\"flowName\":\""+flowName+"\",\"stepName\":\""+stepName+"\",\"flowId\":\""+flowId+"\",\"flowType\":\""+flowType+"\"},"
		 }
		 
		
	 }) 
	
	 if(json==""){
		 BootstrapDialog.alert("请选择数据");
		 return '';
	 }
	json="["+json.substring(0,json.length-1)+"]";
	return json;
}

/**
 * 打开留痕
 * @param custCode
 */
function failList(applyId) {

	var argment={};
	argment.applyCode=applyId;
	 var url="/trusteeship/change/failList";
	 sub = SubDialogHandle.create(url,argment,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}

/**
 * 打开退回窗口
 * @param applyId
 */
function back(applyId) {

	var url="/trusteeship/change/back";
	var arg={'applyCode':applyId};
	var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
}

function chengeFileDown(changeType) {

	var applyIds=getIds();
	var search="";
	if(applyIds==''){
		search=$("#searchForm").serialize()
	}
	var url=ctx+"/trusteeship/change/chengeFileDown?applyIds=" + applyIds+"&downFileType="+changeType+"&"+search;
	window.location.href = url;
}

function page(n,s){
	
	if($("#modal-sub").is(":hidden")){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").submit();
	}else{
		var f=$("#modal-sub")
		if(n) $("#pageNo",f).val(n);
		if(s) $("#pageSize",f).val(s);
		var argment=sub.prop.argment;
		argment.pageNo=n;
		argment.pageSize=s;
		sub.prop.argment=argment;
		sub.loadSubWindow()
	}
	return false;
}


