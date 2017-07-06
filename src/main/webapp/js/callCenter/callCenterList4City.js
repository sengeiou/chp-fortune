/**
 * 呼叫中心，城市客户列表
 */


$(function() {
//	alert($);
	//弹出窗口
	var iframe = getCurrentIframeContents();
	$('.distributeClass').click(function() {
		var custId = $(this).attr("custId");
		var sub;
		$(".modal-title").html("指定门店");
//		$('#subSubmit').show();
		
		var url = '/callCenterController/getCityDistributePage';
		var argment = { 
				'id' : custId
		};
		
		var load_callback = function() {
			$('#subSubmit').show();  //隐藏弹出框的提交按钮
			//提交
			$('#subSubmit', iframe).click(function(){
				var idP = $('#cityDistributeForm').find('#id').val();
				var storeOrgId =  $('#cityDistributeForm').find('#storeOrgId').val();
				if(! storeOrgId) {
					BootstrapDialog.alert('请选择门店');
					return false;
				}
				var data={};
				data.id = idP;
				data.storeOrgId = storeOrgId;
				$.ajax({
			        type: 'post',
			        url:  ctx + "/callCenterController/distributeStore",
			        dataType: "json",
			        data: data,
			        async: false,
			        success: function (obj) {
			        	if(obj.result == 'true') {
			        		BootstrapDialog.alert(obj.msg);
			        		sub.closeSubWindow();
			        		refreshPage();
			        	} else {
			        		BootstrapDialog.alert('分配失败');
			        	}
			        },
			        error: function (data, errorThrown, options, error) {
			        	BootstrapDialog.alert('分配失败');
			        }
			    });
				
				
			});
		};
		var close_callback = function(){};
		sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
		$('#subClose').off('click').on('click',function(){  sub.closeSubWindow();});
		
		
	});
	 //刷新
	var refreshPage = function() {
		$('#searchForm').submit();
	}
});