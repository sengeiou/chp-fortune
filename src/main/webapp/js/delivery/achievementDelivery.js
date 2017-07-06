
 /**
  * 业绩交割
  * @param custCode
  * @param fManagerCode
  */
 function achievementDelivery(custCode,lendCode,fManagerId){
	 BootstrapDialog.confirm('确定要交割此客户吗？', function(result){
         if(result){
        	 var nfManagerId = $("#nfManagerId").val();
        	 var url = ctx+"/delivery/achievement/achievementDelivery";
  		     contents_getJsonForSync(
  		    		 url,
  		    		 {'custCode':custCode,'lendCode':lendCode,'fManagerId':fManagerId,'nfManagerId':nfManagerId},
  		    		 "post",
  		    		 callFunction,
  		    		 errorCal,
  		    		 null
  		    		 );
         }else{
        	 return;
         }
     });
 }

 /**
  * 获取选取的客户的对应信息
  * @returns {String}
  */
  function getInfo(){
 	var ids="";
 	var info="";
 	var temp="";
 	$("input[name='checkbox']:checked").each(function(){
 	   ids=$(this).val();// 获取页面传过来的客户编号,出借编号,原理财经理编号,
 	   var id = ids.split(",");// 分离获取数据条目编号
 	   var custCode = id[0];
 	   var lendCode = id[1];
 	   var fManagerId = id[2];
 	   var nfManagerId = $("#nfManagerId").val();
 	   temp = custCode +","+lendCode+","+fManagerId+","+nfManagerId;
 	   info = info+temp+";";
 	}) 
 	return info;
  }
//交割回调函数
  function callFunction(result,par){
		 if(result == "OK"){
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
                          "label": '登录名：'+this.loginName+' | 理财经理：'+ this.nfManagerName + ' | 营业部：'+this.nOrgName,
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
    		 $("#nfManagerCode").val(ui.item.nfManagerCode);
    		 $("#nfManagerName").val(ui.item.nfManagerName);
    		 $("#nTeamManagerName").val(ui.item.nTeamManagerName);
    		 $("#teamName").val(ui.item.teamName);
    		 $("#nOrgName").val(ui.item.nOrgName);
    		 $("#nfManagerId").val(ui.item.nfManagerId);
    	 }
     }
  /**
   * 批量业绩交割
   */
  $(function(){
	  autocompleteSearch($('.autoComplete'),source,select); 
	  var iframe = getCurrentIframeContents();
		//查询
		$('#search',iframe).click(function(){
			//默认第一页
			page(1);
		});
	  // 批量交割
	  $("#achievemenDdelivery",iframe).click(function(){
		  var code = getInfo();
		  if(code.length == 0){
			  BootstrapDialog.alert('请选择要交割的客户！');
		  }else{
			  BootstrapDialog.confirm('确定要交割选择的客户吗？', function(result){
			         if(result){
			        	 var url = ctx+"/delivery/achievement/batchAchievementDelivery";
			  		     contents_getJsonForSync(
			  		    		 url,
			  		    		 {'code':code},
			  		    		 "post",
			  		    		 callFunction,
			  		    		 errorCal,
			  		    		 null
			  		    		 );
			         }else{
			        	 return;
			         }
			     });
		  }
		 }
	    )
	 }
	)