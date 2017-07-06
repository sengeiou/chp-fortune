
function getCount(object){
	if($("#empCode").val() == ""){
		$("#empName").val("");
		$("#teamManagerName").val("");
		$("#teamName").val("");
		$("#orgName").val("");
	}
}

/**
   * 页面输入自动获取搜索结果
   */
  var source = function(request, response, elem){
    	 contents_getJsonForSync(ctx + '/delivery/tripleCustomer/findInfo',{"code":request.term},'POST',
     	 // 成功取得后的处理
		 function(json){                 
              var val = [];
     		 if(json != null && json.length > 0){
     			 $.each(json,function(){
     				 val.push({
     					"label":'登录名：'+(this.loginName == null ? '' : this.loginName)+' | 理财经理：'+ (this.newEmpName == null ? '':this.newEmpName) +' | 团队经理：'+(this.newTeamManagerName == null ? '' : this.newTeamManagerName)+' | 营业部：'+(this.newOrgName == null ? '' : this.newOrgName),
                        "value": this.newEmpCode,
                        "newEmpName":this.newEmpName,
                        "newTeamManagerCode":this.newTeamManagerCode,
                        "newTeamManagerName":this.newTeamManagerName,
                        "newOrgName":this.newOrgName,
                        "newTeamName":this.newTeamName,
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
		 $("#empCode").val(ui.item.newEmpCode);
		 $("#empName").val(ui.item.newEmpName);
		 $("#teamManagerName").val(ui.item.newTeamManagerName);
		 $("#teamName").val(ui.item.newTeamName);
		 $("#orgName").val(ui.item.newOrgName);
	 }
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
 	   var lendCode = id[0];
 	   var empCode = id[1];
 	   var modifyTime = id[2];
 	   var newEmpCode = $("#empCode").val();
 	   var phone = $("#phone").val();
 	   var loginName = $("#loginName").val();
 	   var customerCode = $("#customerCode").val();
 	   temp = lendCode+","+empCode+","+newEmpCode+","+phone+","+loginName+","+customerCode+","+modifyTime;
 	   info = info+temp+";";
 	}) 
 	return info;
  }
  
//交割回调函数
 function callFunction(result,par){
		 if(result > 0){
			 BootstrapDialog.alert('成功交割：'+result+'条！',function(){
				 $("#searchForm").submit();
			 });
		 }else{
			 BootstrapDialog.alert('交割失败！');
		 }
	}
 function errorCal(){
 	BootstrapDialog.alert("系统出错,请稍后……");
 }
 
 /**
  * 批量业绩交割
  */
 $(function(){
	  autocompleteSearch($('.autoComplete'),source,select); 
	  var iframe = getCurrentIframeContents();
	  $('#search',iframe).click(function(){
			 $form = $('#searchForm');
			 $("#pageNo").val("");
			 $form.submit();
		 })
	  // 批量交割
	  $("#achievemenDdelivery",iframe).click(function(){
		  var code = getInfo();
		  if(code.length == 0){
			  BootstrapDialog.alert('请选择要交割的业绩！');
		  }else{
			  BootstrapDialog.confirm('确定要交割选择的业绩吗？', function(result){
			         if(result){
			        	 var url = ctx+"/delivery/tripleAchievement/batchTripleAchievementDelivery";
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