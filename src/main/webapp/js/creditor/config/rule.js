/**
 * 匹配规则管理/新增
 */
$(document).ready(
		function() {

			var iframe = getCurrentIframeContents();
			// 查询
			$('.search', iframe).on('click', function() {
				// 默认第一页
				page(1);
			});
			// 新增匹配
			$('.toAdd', iframe).on('click', function() {
				$form = $(this).closest('form');
				$form.attr('action', ctx + "/creditor/config/rule/ruleToAdd");
				$form.submit();
			});
			// 删除
			$('.toDelete', iframe).on('click',function() {
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
				BootstrapDialog.confirm('你确定要删除数据吗？', function(result){
					if(result){
						var iframe = getCurrentIframeContents();
						contents_getJsonForSync(
								ctx+ "/creditor/config/rule/ruleToDelete", 
								$(iframe).find('form').serialize(), // 改iframe所有form
						'post', function(result) {
								if(result=='0'){
									BootstrapDialog.alert('删除失败');
									return;
								}else{
									$("#searchForm").submit();
								}	
							/*if (result.error != null) {
								// 更新失败，提示后台的错误消息
								BootstrapDialog.alert(result.error);
							} else {
								// 更新成功，iframe里的html刷新
								$(iframe).find('html div.dataList')
										.html(result);
								listInit();
								BootstrapDialog.alert('删除成功');
							}*/
						
						}, function() {
							BootstrapDialog.alert('删除失败');
						}, null);
					}})});
			
			//批量启用停用
			$('.toEnable',iframe).click(function(){
				toEditSta('启用','/creditor/config/rule/toEnable');
			});
			
			$('.toDisable',iframe).click(function(){
				toEditSta('停用','/creditor/config/rule/toDisable');
			});

			// 保存
			$('.btnSave', iframe).on('click', function() {
				$form = $('#addForm');
				if ($form.validate().form()) {
					//投资比例总和100验证
					var proporti = 0;
					$('input.proporti:visible').each(function(){
						proporti += parseInt($(this).val());
					});
					if(proporti != 100){
						BootstrapDialog.alert('投资比例之和必须100');
						return false;
					}
					var lineContent = $("textarea[name='remark']").val().split('\n');
					var remark = "";
				    for(var i= 0; i<lineContent.length;i++){
				        var line = lineContent[i];
				        if(line!=""){
				        	remark = line+remark;
				        }
				    }
				    $("textarea[name='remark']").val(remark.replace(/\s+/g,""));
				    
				    var billType = $("[name=billType]").val();
				    var lowerLimit = $("#lowerLimit").val();
				    var upperLimit = $("#upperLimit").val();
				    var useFlag = $("[name='useFlag']").val();
				    if (lowerLimit == null || lowerLimit == "") {
						if (upperLimit != null && upperLimit != "") {
							BootstrapDialog.alert("请输入本期推荐金额下限");
							return;
						}
					} else {
						if (upperLimit == null || upperLimit == "") {
							BootstrapDialog.alert("请输入本期推荐金额上限");
							return;
						}
					}
				    
				    var data = {"billType":billType,
				    		"lowerLimit":lowerLimit,
				    		"upperLimit":upperLimit,
				    		"useFlag":useFlag};
					contents_getJsonForSync(
							ctx+'/creditor/config/rule/saveCheck', 
							data,//$("#addForm").serialize(), 
							'post',
							function(result){
								if (result=='SUCCESS') {
									// 验证成功
									//name属性重置
									resetName();
									$form.submit();
								} else {
									BootstrapDialog.alert(result);
								}
							},
							function(error){alert(error);},null);
				}
			});
			// 返回
			$('.btnBack', iframe).on('click', function() {
				$('#backForm').submit();
			});
			// 规则添加
			$('.mark', iframe).on('click', function() {
				$tr = $(this).closest('tr');
				if ($(this).hasClass('add')) {
					if ($('.mark:visible').length >= 5) {
						BootstrapDialog.alert('投资比例最多五个');
						return false;
					}
					$copyTr = $('.proportiTrTemplate').clone(true);
					$copyTr.show();
					//模板类删除
					$copyTr.removeClass('proportiTrTemplate').addClass('proportiTr');
					// 删除按钮显示
					$copyTr.find('.add_proporti').removeClass('add').addClass('minus');
					$('.proportiTr:last').after($copyTr);
					resetName();
				} else {
					$tr.remove();
				}
			});
			// 投资下限添加
			$('.addLower', iframe).on('click', function() {
				if(!$(this).hasClass('minus')){
					//添加的区域
					var $p = $(this).closest('tr').find('p');
					//添加的的输入框
					var addInput = $($p.find('input')[0]).clone(true);
					addInput.removeAttr('required');
					$p.append(addInput);
					// 删除按钮
					$lowerBtn=$('.proportiTrTemplate').find('.addLower').clone(true);
					$lowerBtn.removeClass('add').addClass('minus');
					$p.append($lowerBtn);
					//校验的原因,重置name
					resetName();
				}else{
					$(this).prev().remove();
					$(this).remove();
				}
			});
	});

function resetName(){
	$('.proportiTr').each(function(index){
		//投资比例
		$(this).find('input.proporti').attr('name','proporti['+index+'].proportion');
		//投资下限
		var lowerIndex = 0;
		$(this).find('input.lower').each(function(lowerIndex){
			$(this).attr('name','proporti['+index+'].lower['+lowerIndex+'].lower');
		});
		
	});
}

function changeStatus(id) {
	contents_getJsonForSync(
			ctx+ "/creditor/config/rule/changeStatus", 
			{"id":id}, 
			'post', 
			function(result) {
				if(result=='SUCCESS'){
					$("#searchForm").submit();
				}else{
					BootstrapDialog.alert(result);
					return;
				}	
			}, 
			function() {
				BootstrapDialog.alert("更新失败");
			}, null);
}