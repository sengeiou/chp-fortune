
/**
 *  出借审批查询列表 js
 */
	
$(function() {
	var iframe = getCurrentIframeContents();
	//绑定统计按钮事件
	$('#statistics', iframe).click(function(){
				var url =  '/lendApplyApprovalLook/getApplyStatistics';
				var argment = null;
				$(".modal-title",iframe).html("统计");
				var load_callback = function() {
					$('#subSubmit', iframe).hide();  //隐藏弹出框的提交按钮
				};
				var close_callback = function(){};
				
				var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
				$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
	 });
	//导出Excel按钮事件
	$('#exportExcel').click(function(){
//		var i=0;
//		var data = new Array();
//		$("#checkOne:checked").each(function(){
//			data[i++] = $(this).val();
//		})
//		if(data.length>0){
//			BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
//				if(result){
//					$("#lendCodeHidden").val(data);
//					 var url = ctx + "/lendApplyApprovalLook/exportExcel";
//					window.location=url+"?"+$("#searchForm").serialize();
//				}
//			});
//		}else{
//			$("#lendCodeHidden").val("");
//		   BootstrapDialog.confirm("您确定要导出划扣excel吗？", function(result) {
//	            if (result) {
//	                var url = ctx + "/lendApplyApprovalLook/exportExcel";
//	                window.location = url + "?"  + $("#searchForm").serialize();
//	            }
//	        });
//		}
	        BootstrapDialog.confirm("您确定要导出excel吗？", function(result) {
	            if (result) {
	                var url = ctx + "/lendApplyApprovalLook/exportExcel";
	                postFormReplaceGet($("#searchForm"),url);
	                
	            }
	        });
	 });
	
	 //导出审核通过明细按钮事件
	 $('#exportApprovalPassDetail').click(function(){
		 
//		 var i=0;
//			var data = new Array();
//			$("#checkOne:checked").each(function(){
//				data[i++] = $(this).val();
//			})
//			if(data.length>0){
//				BootstrapDialog.confirm('您确定要导出选中的数据吗？', function(result){
//					if(result){
//						$("#lendCodeHidden").val(data);
//						 var url = ctx + "/lendApplyApprovalLook/exportApprovalPassDetailExcel";
//						window.location=url+"?"+$("#searchForm").serialize();
//					}
//				});
//			}else{
//				$("#lendCodeHidden").val("");
//			   BootstrapDialog.confirm("您确定要导出审核通过明细excel吗？", function(result) {
//		            if (result) {
//		                var url = ctx + "/lendApplyApprovalLook/exportApprovalPassDetailExcel";
//		                window.location = url + "?"  + $("#searchForm").serialize();
//		            }
//		        });
//			}
	        BootstrapDialog.confirm("您确定要导出审核通过明细excel吗？", function(result) {
	            if (result) {
	                var url = ctx + "/lendApplyApprovalLook/exportApprovalPassDetailExcel";
	                window.location = url + "?"  + $("#searchForm").serialize();
	            }
	        });
	 });

});