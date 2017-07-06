
/**
 * 配置js
 */
jQuery(document).ready(function($){
	var iframe = getCurrentIframeContents();
	$("#submitButton").click(function(){
		$form = $('#addOrEdit',iframe);
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}
//		if($("select[name='platformId']")!=""){
//			contents_getJsonForSync(
//					ctx+"/platformRule/checkPlatform",
//					{"platformId":$("select[name='platformId']").val()},
//					"post",
//					function(result){
//						if(result == 'false'){
//							BootstrapDialog.alert("你选中的平台,已经存在");	
//							 return;
//						}else{
//							$form.submit();
//						}
//					}
//				)
//		}
		$form.submit();
	})
	
	$("#add").click(function(){
		window.location.href = ctx + "/platformRule/addOrEdit";
	})
	
	// 校验平台的重复
	$("select[name='platformId']").change(function(){
		if($(this).val()!=""){
			contents_getJsonForSync(
					ctx+"/platformRule/checkPlatform",
					{"platformId":$(this).val()},
					"post",
					function(result){
						if(result == 'false'){
							BootstrapDialog.alert("你选中的平台,已经存在,请修改或删除后再新增",function(){
								self.location=document.referrer;
							});
							return;
						}
					}
				)
		}
	});
	
	// 添加划扣平台
	var index=$("select").not("#hiddenSelect").size();
	$("#addhkpt").click(function(){
		// 动态创建td
		var $td=$("<td></td>");
		// 动态创建删除按钮
		var $input=$('<input class="btn btn_sc ml10" type="button" value="删除" id="removeTr">');
		// 增加节点
		$td.append($input)
		// 复制tr
		var $tr=$("#template").clone(true).show().removeAttr('id').attr('id','deductRuleTr');
		
		var optionId = $("input[name='platformRule']").val().split(",");
		$.each(optionId,function(i,item){
			$tr.find('select').find("option[value='"+item+"']").remove();
		})
		$tr.find('select').selectpicker({}).attr('name','platformIdList').removeAttr('id');
		$tr.append($td);
		$("#onLineTable tr:eq("+index+")").after($tr);
		$tr.find('select').trigger("change");
		index++;
	});
	// 删除划扣平台
	$("#removeTr").live("click",function(){
		$(this).parent().parent().remove();
	})
	
	$("select[name='platformId']").change(function(){
		$("input[name='platformRuleText']").val($(this).find("option:selected").text())
		$("input[name='platformRule']").val($(this).val())
		$("input[name='platformRuleName']").val($(this).find("option:selected").text())
//		$("input[name='id']").val($(this).val())
	})
	
	$("select[name='platformIdList']").live("change",function(){
		var text ="";
		var platformRule = "";
		$("select:not(#hiddenSelect)").each(function(index){
			if($("select:not(#hiddenSelect)").size()-1 == index){
				text = text + $(this).find("option:selected").text();
				platformRule = platformRule +  $(this).val();
			}else{
				text = text + $(this).find("option:selected").text() + "-->";
				platformRule = platformRule +  $(this).val() + ",";
			}
			
			
		})
		$("input[name='platformRuleText']").val(text);
		$("input[name='platformRule']").val(platformRule);
	})
	
	$("#delete").live("click",function(){
		var url = $(this).attr("url");
		BootstrapDialog.confirm("您确定要删除该条规则吗？",function(result) {
			if(result){
				window.location.href = url;
			}
		})
	});
});

function go(){
	window.location.href = ctx + "/platformRule/list";
}