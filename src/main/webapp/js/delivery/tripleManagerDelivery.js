function getCount(object){
	if(object == 1){
		if($("#fManagerCode").val() == ""){
			$("#fManagerName").val("");
			$("#teamManagerName").val("");
			$("#teamName").val("");
			$("#orgName").val("");
		}
	}else if(object == 2){
		if($("#nfManagerCode").val() == ""){
			$("#nfManagerName").val("");
			$("#nTeamManagerName").val("");
			$("#nTeamName").val("");
			$("#nOrgName").val("");
		}
	}
	
}


// 客户交割回调函数
function custCallFunction(result,par){
	 if(result=='OK'){
		 BootstrapDialog.alert('交割成功！');
		 $("#lendTime,#achDelivery").show(500);
		 $("#custDelivery").hide(500);
	 }else if(result == "NU"){
		 BootstrapDialog.alert('原理财经理不存在可以交割的客户！');
	 }else if(result == "NG"){
		 BootstrapDialog.alert('交割失败！');
	 }
}
//理财经理业绩交割回调函数
function acheCallFunction(result,par){
	 if(result=='OK'){
		 BootstrapDialog.alert('交割成功！');
		 $("#lendTime,#achDelivery").hide();
		 $("#custDelivery").show();
	 }else if(result == "NU"){
		 BootstrapDialog.alert('原理财经理不存在可以交割的业绩！');
	 }else if(result == "NG"){
		 BootstrapDialog.alert('交割失败！');
	 }
}
function errorCal(){
	BootstrapDialog.alert("系统出错,请稍后……");
}

/**
 * 页面输入自动获取搜索结果
 */
var source = function(request, response, elem){
  	 contents_getJsonForSync(ctx + '/delivery/customer/findInfo',{"code":request.term},'POST',
       	 // 成功取得后的处理
		 function(json){
            var val = [];
       		 if(json != null && json.length > 0){
       			 $.each(json,function(){
       				 val.push({
                        "label":'登录名：'+(this.loginName == null ? '' : this.loginName)+' | 理财经理：'+ (this.nfManagerName == null ? '' : this.nfManagerName) +' | 团队经理：'+ (this.nTeamManagerName == null ? '' : this.nTeamManagerName) + ' | 营业部：'+ (this.nOrgName == null ? '' : this.nOrgName),
                        "value": this.nfManagerCode,
                        "nfManagerName":this.nfManagerName,
                        "nTeamManagerName":this.nTeamManagerName,
                        "teamName":this.teamName,
                        "nOrgName":this.nOrgName,
                        "nfManagerId":this.nfManagerId,
                    });
       			 });
       		 }else{
       			 val.push({
                        "label": "您所查询的信息不存在！",
                        "value": "",
                        "id": ""
                    });
       		 }
   		  response(val, request.term);
   	 },
   	 // 取得失败后的处理
   	 function(error){}
  	 );
   };
   var select = function(event, ui, elem){
  	 if (ui && typeof ui.item == "object"){
  		 $("#fManagerCode").val(ui.item.value);
  		 $("#fManagerName").val(ui.item.nfManagerName);
  		 $("#teamManagerName").val(ui.item.nTeamManagerName);
  		 $("#teamName").val(ui.item.teamName)
  		 $("#orgName").val(ui.item.nOrgName);
  		 $("#fManagerId").val(ui.item.nfManagerId);
  	 }
   }
   
   
   $(function(){
	   autocompleteSearch($('.autoComplete'),source,select);
	   var iframe = getCurrentIframeContents();
	   // 理财经理交割
	   $("#delivery",iframe).click(function(){
		   $("#lendTime").hide();
		   if($('#searchForm').validate().form() && $('#form').validate().form()){
				 BootstrapDialog.confirm('确定要交割此客户吗？', function(result){
			         if(result){
			        	 var empCode = $("#fManagerCode").val();
			        	 var newEmpCode = $("#nfManagerCode").val();
			        	 var url = ctx+"/delivery/tripleCustomer/tripleManagerCustomerDelivery";
			  		     contents_getJsonForSync(
			  		    		 url,
			  		    		{'empCode':empCode,'newEmpCode':newEmpCode},
			  		    		 "post",
			  		    		 custCallFunction,
			  		    		 errorCal,
			  		    		 null
			  		    		 );
			         }else{
			        	 return;
			         }
			     });
		}else{
			return;
		}
	   })
	 // 清除页面输入项 
	$("#reset").click(function(){
		$("input[type='text']").val("");
		$("input[type='hidden']").val("");
		return false;
	})
	// 理财经理业绩交割
	$("#achievement").click(function(){
		 if($('#searchForm').validate().form() && $('#form').validate().form()){
			 BootstrapDialog.confirm('确定要交割全部业绩吗？', function(result){
		         if(result){
		        	 var empCode = $("#fManagerCode").val();
		        	 var newEmpCode = $("#nfManagerCode").val();
		        	 var startTime = $("#startTime").val();
		        	 var endTime = $("#endTime").val();
		        	 var url = ctx+"/delivery/tripleCustomer/tripleManagerAchievementDelivery";
		  		     contents_getJsonForSync(
		  		    		 url,
		  		    		{'empCode':empCode,'newEmpCode':newEmpCode,'startTime':startTime,'endTime':endTime},
		  		    		 "post",
		  		    		 acheCallFunction,
		  		    		 errorCal,
		  		    		 null
		  		    		 );
		         }else{
		        	 return;
		         }
		     });
	}else{
		return;
	}
  })
	
   })