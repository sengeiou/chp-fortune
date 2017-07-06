
// 交割回调函数
function callFunction(result,par){
	 if(result=='OK'){
		 BootstrapDialog.alert('交割成功！',function(){
			 location.reload();
		 });
	 }else{
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
  	 contents_getJsonForSync(ctx + '/delivery/customer/findInfo/'+ request.term,null,'GET',
       	 // 成功取得后的处理
			 function(json){
                var val = [];
	       		 if(json != null && json.length > 0){
	       			 $.each(json,function(){
	       				 val.push({
	                        "label":'登录名：'+this.loginName+' | 理财经理：'+ this.nfManagerName + ' | 营业部：'+this.nOrgName,
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
			        	 var fManagerCode = $("#fManagerId").val();
			        	 var nfManagerCode = $("#nfManagerId").val();
			        	 var url = ctx+"/delivery/manager/managerDelivery";
			  		     contents_getJsonForSync(
			  		    		 url,
			  		    		{'fManagerCode':fManagerCode,'nfManagerCode':nfManagerCode},
			  		    		 "post",
			  		    		 callFunction,
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
		$("#lendTime").show();
		 if($('#searchForm').validate().form() && $('#form').validate().form()){
			 BootstrapDialog.confirm('确定要交割全部业绩吗？', function(result){
		         if(result){
		        	 var fManagerCode = $("#fManagerId").val();
		        	 var nfManagerCode = $("#nfManagerId").val();
		        	 var startTime = $("#startTime").val();
		        	 var endTime = $("#endTime").val();
		        	 var url = ctx+"/delivery/manager/managerAchievementDelivery";
		  		     contents_getJsonForSync(
		  		    		 url,
		  		    		{'fManagerCode':fManagerCode,'nfManagerCode':nfManagerCode,'startTime':startTime,'endTime':endTime},
		  		    		 "post",
		  		    		 callFunction,
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