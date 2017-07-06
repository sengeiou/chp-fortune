$(document).ready(function(){
	var iframe = getCurrentIframeContents();
	//查询
	$('.search',iframe).click(function(){
		$form = $('#searchForm');
		if ($form.validate().form()) {
			//默认第一页
			page(1);
		}
	});
	//新增匹配
	$('.toAdd',iframe).click(function(){
		$form = $(this).closest('form');
		$form.attr('action',ctx + "/creditor/config/rate/toAdd");
		$form.submit();
	});
	//删除
	$('.toDelete',iframe).click(function(){
		if($('[name="select[]"]:checked').length==0){
			BootstrapDialog.alert('请选择要删除的数据');
			return false;
		}
		
		BootstrapDialog.confirm('你确定要删除数据吗？', function(result){
			if(result){
				var iframe = getCurrentIframeContents();
				contents_getJsonForHtml(
						ctx + "/creditor/config/rate/delete", 
						$(iframe).find('form').serialize(), //改iframe所有form
						'post', 
						function(result){
							if(result=='0'){
								BootstrapDialog.alert('删除失败');
								return;
							}else{
								$("#searchForm").submit();
							}
						},
						function(){
							BootstrapDialog.alert('删除失败');
						},
						null
					);
			}})});
	
	//批量启用停用
	$('.toEnable',iframe).click(function(){
		toEditSta('启用','/creditor/config/rate/toEnable');
	});
	
	$('.toDisable',iframe).click(function(){
		toEditSta('停用','/creditor/config/rate/toDisable');
	});
	
	function listInit(){
		//启用/停用
		$('.btn_chgStatus',iframe).click(function(){
			var iframe = getCurrentIframeContents();
			var tr = $(this).closest('tr');
			var selectId = $(tr).find('[name="select[]"]').prop('checked',true);		
			contents_getJsonForHtml(
				ctx + "/creditor/config/rate/edit", 
				$(iframe).find('form').serialize(), //改iframe所有form
				'post', 
				function(result){
					if(result=='0'){
						BootstrapDialog.alert('操作失败');
						return;
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
						BootstrapDialog.alert('更新成功');
					}	*/				
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
	
	//保存
	$('.btnSave',iframe).click(function(){
		$form = $('#saveForm',iframe);
		
		if($form.validate().form()){
			//验证成功
			var  productCode =$('#productCode').val();
			var  billType =$('#billType').val();
			var applyLenddayLower = $('#applyLenddayLower').val(); // 出借开始日期
			var applyLenddayUpper = $('#applyLenddayUpper').val(); // 出借结束日期
			var applyLendMoneyLower = $('#applyLendMoneyLower').val();
			var applyLendMoneyUpper = $('#applyLendMoneyUpper').val();
			var matchingInterestStart = $('#matchingInterestStart').val();
			if(billType=='1'){// 首期
				contents_getJsonForHtml(
						ctx + "/creditor/config/rate/beforSaveCheck?applyLenddayLower="+applyLenddayLower+"&applyLenddayUpper="+applyLenddayUpper+"&productCode="+productCode+"&applyLendMoneyLower="+applyLendMoneyLower+"&applyLendMoneyUpper="+applyLendMoneyUpper+"&billType="+billType,
						null, 
						'post', 
						function(result){
							if(result=='0'){
								$form.submit();
							}else{
								BootstrapDialog.alert('数据已存在或数据有交叉，请重新填写！');
							}		
						},
						function(){
							BootstrapDialog.alert('操作失败！');
						},
						null
						);
			}else{ // 非首期
				var matchingBillDay = $('#matchingBillDay').val();
				if(matchingBillDay==''){
					BootstrapDialog.alert('账单日不能为空！');
				}else{
					contents_getJsonForHtml(
								ctx + "/creditor/config/rate/beforSaveCheck?applyLenddayLower="+applyLenddayLower+"&applyLenddayUpper="+applyLenddayUpper+"&productCode="+productCode+"&applyLendMoneyLower="+applyLendMoneyLower+"&applyLendMoneyUpper="+applyLendMoneyUpper+"&matchingInterestStart="+matchingInterestStart+"&matchingBillDay="+matchingBillDay+"&billType"+billType,
								null, 
								'post', 
								function(result){
									if(result=='0'){
										$form.submit();
									}else{
										BootstrapDialog.alert('数据已存在或数据有交叉，请重新填写！');
									}		
								},
								function(){
									BootstrapDialog.alert('操作失败！');
								},
								null
								);
				}
				
			}
			
		
		}
			
		
			
			
			
});
	//返回
	$('.btnBack',iframe).click(function(){
		$('#backForm').submit();
	});
});