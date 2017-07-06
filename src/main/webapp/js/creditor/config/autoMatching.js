$(document).ready(function(){
	var iframe = getCurrentIframeContents();
	//查询
	$('.search',iframe).click(function(){
		$form = $(this).closest('form');
		var productRate = $('#productRate').val();
		if(productRate!=''){
			if(Number(productRate)<=0){
				alert('利率必须大于0');
				return false;
			}
		}
		if($form.validate().form()){
			//验证成功
			$form.submit();
			//默认第一页
			page(1);
		}
	});
	//新增匹配
	$('.toAdd',iframe).click(function(){
		$form = $(this).closest('form');
		$form.attr('action',ctx + "/creditor/config/automatching/toAdd");
		$form.submit();
	});
	$('.toAuto',iframe).click(function(){
		$form = $(this).closest('form');
		$form.attr('action',ctx + "/creditor/config/automatching/toAutoMatching");
		$form.submit();
	});
	//删除
	$('.toDelete',iframe).click(function(){
		var iframe = getCurrentIframeContents();
		var userFlag = "";
		$("input[name='select[]']:checked").each(function(){
			userFlag = $(this).parent().nextAll("#userFlag").text();
			if(userFlag=='启用'){
				return false;
			}
		});
		if(userFlag=='启用'){
			BootstrapDialog.alert('存在没有停止的匹配,请勿操作');
			return;
		}
		contents_getJsonForSync(
			ctx + "/creditor/config/automatching/delete", 
			$(iframe).find('form').serialize(), //改iframe所有form
			'post', 
			function(result){
				if(result=="0"){
					BootstrapDialog.alert('删除失败');
				}else{
					$("#searchForm").submit();
				}
				
				/*if(result.error != null){
					//更新失败，提示后台的错误消息
					BootstrapDialog.alert(result.error);
				}else{
					//更新成功，iframe里的html刷新
					$(iframe).find('html div.dataList').html(result);
					listInit();
					BootstrapDialog.alert('删除成功');
				}
			*/},
			function(){
				BootstrapDialog.alert('删除失败');
			},
			null
		);
	});
	
	//批量启用停用
	$('.toEnable',iframe).click(function(){
		toEditSta('启用','/creditor/config/automatching/toEnable');
	});
	
	$('.toDisable',iframe).click(function(){
		toEditSta('停用','/creditor/config/automatching/toDisable');
	});
	
	//启用/停用
	$('.btn_chgStatus',iframe).click(function(){
		var iframe = getCurrentIframeContents();
		var tr = $(this).closest('tr');
		var selectId = $(tr).find('[name="select[]"]').prop('checked',true);		
		contents_getJsonForHtml(
				ctx + "/creditor/config/automatching/edit", 
				$(iframe).find('form').serialize(), //改iframe所有form
				'post', 
				function(result){
					if(result=="0"){
						BootstrapDialog.alert('跟新失败');
					}else{
						$("#searchForm").submit();
						BootstrapDialog.alert('操作成功');
					}
					/*if(result.error != null){
						//更新失败，提示后台的错误消息
						BootstrapDialog.alert(result.error);
					}else{
						//更新成功，iframe里的html刷新
						$(iframe).find('html div.dataList').html(result);
						listInit();
						BootstrapDialog.alert('更新成功');
					}*/					
				},
				function(){
					BootstrapDialog.alert('更新失败');
				},
				null
		);
		return false;
	});
	function listInit(){
		//编辑
		$('.btn_detail',iframe).click(function(){
			var iframe = getCurrentIframeContents();
			var configId = $(this).closest('tr').find('[name="select[]"]').val();	
			$form = $(this).closest('form');
			$form.attr('action',ctx + "/creditor/config/automatching/toEdit/"+configId);
			$form.submit();
		});
	}
	listInit();
	
	//保存
	$('.btnSave',iframe).click(function(){
		$form = $("#addForm");
		if($form.validate().form()){
			var url=ctx+"/creditor/config/automatching/save";
			 contents_getJsonForSync(
					 url,
					 $("#addForm").serialize(),
					 "post",
					 function(data){
						 if(data == null){
								BootstrapDialog.confirm('增加成功,是否返回债权自动匹配列表',function(result){
									if(result){
										 window.location = ctx+"/creditor/config/automatching/list";
									}
								});
						 }else if(data != null){
							 BootstrapDialog.alert(data);
						 }
					 }
			 );
		}
	});
	//返回
	$('.btnBack',iframe).click(function(){
		$('#backForm').submit();
	});
});