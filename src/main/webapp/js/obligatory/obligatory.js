// 可用债权配置
$(document).ready(function() {
	
	// 可用债权配置验证
	var iframe = getCurrentIframeContents();
	$('#cdit',iframe).click(function(){
		
		var city = $("#configCity").val();
		var org = $("#og").val();
		$form = $(iframe).find('#credit');
		if($form.validate().form()){
			if(city != '' && city != null){
				if(org !='' && org != null){
					//验证成功
					creditAdd();
				}else{
					BootstrapDialog.alert("营业部不能为空！！");
				}
			}else{
				BootstrapDialog.alert("城市不能为空！！");
			}
		}
		return false;
	});
	
	fortune.initCity(iframe,'configProvince','configCity','orgId');
	
	$(".bs-select-all").click(function(){// 三级联动全选
		
		var s = $("#configCity").val();
		var org = $("#configYy").val();
		if((s==null || s=="") || (s != null && s !="") && (org== null || org == '')){// 第一次点击没有值，需要用第一级的值进行查找
			var pro = $("#configProvince").val();
			$.ajax({
	       		type : "POST",
	       		url : ctx + "/provinceCityOrg/getOrgByProvince",
	       		data: {"configProvince": pro,"configCity": s},	
	       		success : function(data) {
	       			var resultObj = eval("("+data+")");
		       			
		                $.each(resultObj,function(i,item){
		                	$org.append("<option value="+item.id+">"+item.areaName+"</option>");
		                });
		                $org.trigger("change");
		                $org.attr("disabled", false);
		                $org.selectpicker('refresh');
	       		}
			});
		}
	});
	
});


//删除
function optDelete(){
	
	var inst="";//存放可用债权ID
	if($('[name="ids"]:checked').length == 0){
		BootstrapDialog.alert("请至少选择一条数据");
	}else{
		BootstrapDialog.confirm("确认删除选中的数据吗?",function(result){
			if(result){
				inst = $('[name="ids"]:checked').map(function() {
					return this.value;
				}).get().join(',');
				
				//异步提交批量删除
				url = ctx+'/creditLocation/creditBatchDel';
				data = {"inst":inst};
				type = 'post';
				successCal = function(result){
					 if(result=='true'){
		       			BootstrapDialog.alert("批量删除成功");
		       			window.location.href=ctx+"/creditLocation/loadCreditLocationList";
					}else{
						BootstrapDialog.alert("系统错误");
					}
				};
				errorCal = function (){
					BootstrapDialog.alert("系统错误");
				};
				contents_getJsonForSync(url, data, type, successCal,errorCal,null);
			}
		});
	}	
}

// 异步提交可用债权配置
function creditAdd(){
	
	iframe = getCurrentIframeContents();
	$form = $('#credit', iframe);
	var forms = $(iframe).find('#credit').serialize();
	url = ctx+'/creditLocation/addCreditConfig';
	type = 'post';
	successCal = function(result){
		 if(result=='true'){
   			BootstrapDialog.alert("操作成功", function(){window.location.href=ctx+"/creditLocation/loadCreditLocationList"});
		}else{
			BootstrapDialog.alert(result);
		}
	};
	errorCal = function (){
		BootstrapDialog.alert("系统错误");
	};
	contents_getJsonForSync(url, forms, type, successCal,errorCal,null);
}

// 可用配置债权修改
$(function(){
	$("#btn").click(function(){
			$("#a").removeAttr("disabled");
			$("#b").removeAttr("disabled");
			$("#c").removeAttr("disabled");
		});
})

// 全选/反选
function checkAll(){
	
	var len = $('[name="configType"]:checked').length;
	if(len==0){
		$("[name='configType']").attr("checked",true); 
	}else{
		$("[name='configType']").attr("checked",false); 
	}
}

/**
 * 省市营业部联动
 */
fortune.initCity = function(iframe,provinceId,cityId,orgId){
	$("[id="+provinceId+"]",iframe).change(function() { 
		 var provinceId = $(this).val();
		 $city = $(this).closest('tr').find("#"+cityId);
		 $org = $("select#og");
		 if(provinceId==""){
			 $city.empty();
			 $city.trigger("change");
			 $city.selectpicker('refresh');
			 $org.empty();
			 $org.trigger("change");
			 $org.selectpicker('refresh');
		 }else{
		     $.ajax({
	       		type : "POST",
	       		url : ctx + "/provinceCityOrg/getCitys",
	       		data: {"configProvince": provinceId},	
	       		success : function(data) {
	       			var resultObj = eval("("+data+")");
		       			$city.empty();
		                $.each(resultObj,function(i,item){
		                	$city.append("<option value="+item.id+">"+item.areaName+"</option>");
		                });
		                $city.trigger("change");
		                $city.attr("disabled", false);
		                $city.selectpicker('refresh');
		                $org.empty();
		                $org.trigger("change");
		   			 	$org.selectpicker('refresh');
	       		}
		    });
		 }
	});
	$("[id="+cityId+"]",iframe).change(function() { 
		var ctId = $(this).val()+"";
		$org = $("select#og");
		 if(ctId==""){
			 $org.empty();
			 $org.trigger("change");
			 $org.selectpicker('refresh');
		 }else{
			 $org.empty();
			 $org.trigger("change");
			 $org.selectpicker('refresh');
		     $.ajax({
	       		type : "POST",
	       		url: ctx + "/provinceCityOrg/getOrg",
	       		data: {"configCity": ctId},	
	       		success : function(data) {
	       			var resultObj = eval("("+data+")");
	       		    $org.empty();
	                $.each(resultObj,function(i,item){
	                   $org.append("<option value="+item.id+"_"+item.cityId+">"+item.name+"</option>");
	                });
	                $org.trigger("change");
	                $org.attr("disabled", false);
	                $org.selectpicker('refresh');
	       		}
		    });
		}
	});
};