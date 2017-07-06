/**
 * 提交检索条件
 */
$(document).ready(function() {
	$('#search').click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			$form.submit();
		}
	});
});

// 控制审批不通过原因中其他选项的附加文本框
function showTextArea(){
	var value= $("#cemine").val();
	if(value=="6"){
		$("#tar").show();
	}else{
		$("#tar").hide();
	}
	//通过不通过
	if($('[name="checkExaminetype"]:checked').val()=="1"){
		
		$('.backReason').hide();
		
		if($("#flag").val() !='' && $("#flag").val() !=null){
			// 回息日期的显示
			$("#dt").show();
			//对回息日期进行校验
			var backMoneyDay = $("#backMoneyDay").val();
			var hMaxDate = $("#hMaxDate").val();
			var diffDays = $("#diffDays").val();
			if(backMoneyDay==hMaxDate && diffDays != 1){
				BootstrapDialog.confirm("回息日期不一致，请确定是否往下一列表流转？ ", function(result){
					if(!result){
						$('#subClose').click();
					}
				});
			}
		}
	}else{
		
		$('.backReason').show();
		$("#dt").hide();
		
	}
		
//		if($('[name="checkExaminetype"]:checked').val()=="1"){
//			
//			$('.backReason').hide();
//			
//			if($("#flag").val() !='' && $("#flag").val() !=null){// 回息日期的显示
//				$("#dt").show();
//			}
//		}else{
//			
//			$('.backReason').show();
//			$("#dt").hide();
//			
//		}
	showBank();
}

/*
 * 批量回息审批弹窗
 */
function batchApprovalWindow(){
	
	var s = $("#ids").length;
	if(s == 0){
		BootstrapDialog.alert("页面中没有数据可以操作！！");
	}else{
		iframe = getCurrentIframeContents();
		$(".modal-title",iframe).html("批量回息操作");
		$("#hi").val("1");
		//弹出框参数
		var url, argment, callback;
    	argment = null;
    	load_callback = function(arg){
    		
    	};
    	close_callback = function(arg){
    	};
        
		if($('[name="ids"]:checked').length == 0){
				BootstrapDialog.confirm("您没有选择任何一条数据，系统将默认选中全部数据并提交,是否确认提交全部？",function(result){
					if(result){
						url = "/backInterestCommon/batchWindow";
						var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
						//自定义出发关闭回调函数的动作
			            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
					}
				});
				function dialogConfirm(){
			}
		}else{
			BootstrapDialog.confirm("确认批量提交吗?",function(result){
    			if(result){
    				url = "/backInterestCommon/batchWindow";
    				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    				//自定义出发关闭回调函数的动作
    	            $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
    			}
    		});
		}
	}
}

//提交批量
function submits(purl,surl,forms){
	
	url = ctx+"/"+purl;
	dataType= "json";
	successCal = function(result){
		loadingMarkHide();
       	 if(result=='true'){
       		BootstrapDialog.alert("操作成功",function(){loadingMarkShow(); $('#search').click()});
       	 }else{
       		 BootstrapDialog.alert(result,function(){loadingMarkShow();$('#search').click()});
       	 }
	};
	errorCal = function (){
		loadingMarkHide();
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, forms, 'post', successCal,errorCal,null);
}

//执行回息线下弹窗
function showc(){
	
	if($('[name="tp"]:checked').val()=="1"){
		$(".his").hide();
	}else{
		$(".his").show();
	}
}

// 全程留痕弹窗
function fullMark(backiId) {
	// 调用子窗口的参数
	var url, argment, callback;
	url = '/backInterestCommon/toHistory';
	argment = {'lendCode':backiId};
	load_callback = function(arg){
	};
	close_callback = function(arg){
	};
	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		//自定义出发关闭回调函数的动作
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});	
}

// 全选/全不选 checkbox(累计实际回息金额)
function checkAll(){
	
	var checkedLen = $('[name="ids"]:checked').length;
	if(checkedLen>0){
		// 累计金额
		var pt = $("#pageTotal").val();
		var hao = $("#sum");
		hao.html(pt);
		
		var count = $("#count");
		count.html(checkedLen);
	}else{
		// 累计金额
		var tm = $("#totalMoney").val();
		var hao = $("#sum");
		hao.html(tm);
		// 总笔数
		var count = $("#count");
		var lenth = $("#totalCount").val();
		count.html(lenth);
	}
	
}
// 单选
function selects(){
	var leng = $('[name="ids"]:checked').length;
	var money = 0;
	if(leng != 0){
		
		$("[name=ids]:checked").each(function(){
			
				var s = $(this).parent().parent().find("input[name=mmy]").val();
				money = money*1+s*1;
				money = money.toFixed(2);
		});
		
		var hao = $("#sum");
		hao.html(money);
		
		var count = $("#count");
		count.html(leng);
		
	}else{
		
		$("#ids").attr("checked",false);
		var tm = $("#totalMoney").val();
		var hao = $("#sum");
		hao.html(tm);
		var count = $("#count");
		var le = $("#totalCount").val();
		count.html(le);
	}
}

//执行回息批量回息 操作选择中间人信息
function showBank(){
	
	var a = $('#sl').val();
	if(a!=null && a!=''){
		var s = $('[name="checkExaminetype"]:checked').val();
		if(s==1){
			$('.mola').show();
		}else{
			$('.mola').hide();
		}
	}
}

// 历史留痕
function his_page(n, s) {
	if (n)
		$("#pageNo",$("#hisForm")).val(n);
	if (s)
		$("#pageSize",$("#hisForm")).val(s);
	data = $("#hisForm").serialize();
	contents_getJsonForHtml(ctx+"/backInterestCommon/goHistory", data, "post", function(html){
		$(".advisoryTabDiv").html(html);
	},null,null);
	return false;

}

/*
 * 查询当前列表中数据有没有回盘结果为处理中的
 */
function findBackResult(){
	
	iframe = getCurrentIframeContents();
	backiId = $('[name="ids"]:checked').map(function() {
		return this.value;
	}).get().join(',');
	inst = encodeURI(encodeURI(backiId));
	var forms = $(iframe).find('#searchForm').serialize();
	
	data = {"backiId":backiId};
	url = ctx+"/backInterestConfrim/findBackResult?&"+forms;
	timeout = 10000;
	dataType= "json";
	successCal = function(result){
       	 if(result>0){
       		 BootstrapDialog.alert("批量提交数据中含有回盘结果为“处理中”的数据,不能进行批量操作");
       	 }else{
       		batchConfrimWindow();
       	 }
	};
	errorCal = function (){
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, data, 'post', successCal,errorCal,null);
}


// 单条提交
function limitCommit(formdate,purl,surl){
	url = ctx+"/"+purl;
	data = formdate;
	type = 'post';
	successCal = function(result){
		loadingMarkHide();
		 if(result == 'true'){
			BootstrapDialog.alert("操作成功",function(){loadingMarkShow();window.location.href=ctx+"/"+surl});
		}else{
			BootstrapDialog.alert(result,function(){loadingMarkShow();window.location.href=ctx+"/"+surl});
		}
	};
	errorCal = function (){
		loadingMarkHide();
		BootstrapDialog.alert("服务器忙碌中······");
	};
	contents_getJsonForSync(url, data, type, successCal,errorCal,null);
}

