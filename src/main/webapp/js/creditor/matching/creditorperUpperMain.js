$(document).ready(function(){
	var iframe = getCurrentIframeContents();
	//查询
	$('.search',iframe).click(function(){
		//默认第一页
		page(1);
	});
	//新增匹配
	$('.toAdd',iframe).click(function(){
		$form = $(this).closest('form');
		$form.attr('action',ctx + "/creditor/CreditorperUpper/goAdd");
		$form.submit();
	});
	//删除
	$('.toDelete',iframe).click(function(){
		var iframe = getCurrentIframeContents();
		if($("input[name='select[]']:checked").length == 0){
			BootstrapDialog.alert("请选择要删除的配置！");
		}else{
			BootstrapDialog.confirm('你确定删除配置',function(result){
				if(result){
					contents_getJsonForHtml(
							ctx + "/creditor/CreditorperUpper/delete", 
							$(iframe).find('form').serialize(), //改iframe所有form
							'post', 
							function(result){
								if(result=='NG'){
									BootstrapDialog.alert('删除失败');
									return;
								}else{
									$("#searchForm").submit();
								}	
								/*if(result.error != null){
									//更新失败，提示后台的错误消息
									BootstrapDialog.alert(result.error);
								}else{
									BootstrapDialog.alert('删除成功');
									window.location.href=ctx+'/creditor/CreditorperUpper';
								}*/
							},
							function(){
								BootstrapDialog.alert('删除失败');
							},
							null
						);
				}
			});
		}
	});
	
	//批量启用停用
	$('.toEnable',iframe).click(function(){
		toEditSta('启用','/creditor/CreditorperUpper/toEnable');
	});
	
	$('.toDisable',iframe).click(function(){
		toEditSta('停用','/creditor/CreditorperUpper/toDisable');
	});
	
	function listInit(){
		//启用/停用
		$('.btn_chgStatus',iframe).click(function(){
			var iframe = getCurrentIframeContents();
			var tr = $(this).closest('tr');
			var selectId = $(tr).find('[name="select[]"]').prop('checked',true);		
			contents_getJsonForHtml(
				ctx + "/creditor/CreditorperUpper/edit", 
				$(iframe).find('form').serialize(), //改iframe所有form
				'post', 
				function(result){
					if(result=='0'){
						BootstrapDialog.alert('操作失败');
						return;
					}else{
						$("#searchForm").submit();
					}	
				},
				function(){
					BootstrapDialog.alert('更新失败');
				},
				null
			);
			return false;
		});
	}
	listInit();
function selectAll(){  
	if ($("#checkAll").attr("checked")) {  
		    $(":checkbox").attr("checked", true);  
	} else {  
		    $(":checkbox").attr("checked", false);  
		}  
}	
	
});