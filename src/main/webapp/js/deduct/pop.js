
/**
 * 弹出框js
 */
jQuery(document).ready(function($){
	// 添加划扣平台
	var index=1;
	$("#addhkpt").click(function(){
		var selectFirstValue = $("select[name='deductPlatFormID']:first").val();
		var selectSize = $("select[name='deductPlatFormID']").size();
		if (selectFirstValue == '1'||selectFirstValue == '3') {
			if(selectSize-1>0){
				BootstrapDialog.alert('你的跳转平台过多');
				return;
			}
		}else if(selectFirstValue == '2'){
			if(selectSize-2>0){
				BootstrapDialog.alert('你的跳转平台过多');
				return;
			}
		}
		// 动态创建td
		var $td=$("<td></td>");
		// 动态创建删除按钮
		var $input=$('<input class="btn btn_sc ml10" type="button" value="删除" id="removeTr">');
		// 增加节点
		$td.append($input)
		// 复制tr
		var $tr=$("#template").clone(true).show().removeAttr('id').attr('id','deductRuleTr');
		$tr.find('select').selectpicker({}).attr('name','deductPlatFormID');
		// 获取tr中第二个td的元素
		var $tdinput=$tr.find("td:eq(1)").children();
		var mydate=new Date();
		var time=mydate.getTime();
		// 遍历元素
		$.each($tdinput,function(){
			var $obj=$(this);
			$obj.attr("name","deductInterfaceType"+"["+index+"]");
		})
		// 动态添加时间戳隐藏框
//			$('<input type="hidden" name="time" value='+time+'>').appendTo($tdinput);
		$tr.append($td).appendTo("#onLineTable");
		index++;
	});
	
	// 删除划扣平台
	$("#removeTr").live("click",function(){
		$(this).parent().parent().remove();
	})
})