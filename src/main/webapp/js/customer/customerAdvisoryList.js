$(document).ready(function(){
	// 新增客户咨询
	iframe = getCurrentIframeContents();

	$("#btnAddAdvisory",iframe).click(function(){
		$(".modal-title",iframe).html("新增客户咨询");
		$('#subSubmit').show();
    	// 调用子窗口的参数
    	var url, argment, callback,custCode;
    	custCode = $("#custCode").val();
    	url = "/customer/investment/goAddAdvisory";
    	argment = {customerCode:custCode};
    	load_callback = function(arg){
    		//BootstrapDialog.alert('这是个例子');
    	};
    	close_callback = function(arg){
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
        
        $('#subSubmit').off('click').on('click',function(){
        	form = $(".modal-body",iframe).find("[id=addAdvisoryForm]");
    		if(!form.validate().form()){
    			//验证不成功
    			return false;
    		}
    		
    		inputMoney = $("#askInputMoney",iframe).val();
    		if(inputMoney != '' && (!(/^\d+$/.test(inputMoney)))){
    			BootstrapDialog.alert("咨询意向金额只能为数字");
    			return false;
    		}
    		
        	data = form.serialize();
        	contents_getJsonForSync(ctx+"/customer/investment/addAdvisory", data, "post", function(res){
        		if(res.result=='true'){
        			BootstrapDialog.alert('添加成功');
        			// 重新加载咨询列表
            		showTab(custCode,'advisory');
            		sub.closeSubWindow();
            		$('#subSubmit').off('click');
        		}
        	},null,{});// contents_getJsonForSync
        });
    });
	
	// 显示咨询详细
	$("input[id=advisoryDetail]",iframe).click(function(e){
		$(".modal-title",iframe).html("客户咨询详细");
		$('#subSubmit').hide();
    	// 调用子窗口的参数
    	var url, argment, callback,advisoryId;
    	advisoryId = $(e.target).attr("advisoryId");
    	url = "/customer/investment/advisoryDetail";
    	argment = {'advisoryId':advisoryId};
    	load_callback = function(arg){
    		//BootstrapDialog.alert('这是个例子');
    	};
    	close_callback = function(arg){
    	};
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
        //自定义出发关闭回调函数的动作
        $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
    });
	
});

