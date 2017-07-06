
/**
 * 获取页面数据编号
 */
var commonCount = "";// 页面每条数据对应的编号
function getCount(count){
	commonCount = count;
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
	   ids=$(this).val();// 获取页面传过来的数据条目编号和客户编号
	   var id = ids.split(",");// 分离获取数据条目编号
	   var number = id[0];
	   if($('#form').validate().element('#nfManagerCode'+number)){
		   var custCode = id[1];// 获取页面客户编号
		   var fManagerCode = id[2];// 获取页面客户对应的理财经理工号
		   var delDate = id[3];// 获取页面已有的交割时间
		   var nfManagerCode = $("#nfManagerId"+number).val();// 获取页面输入的新理财经理编号
		   var toDelDate = $("#delDate"+number).val();// 获取页面输入的交割时间
		   var delId = $("#delId"+number).val();
		   temp = custCode +","+fManagerCode+","+nfManagerCode+","+delDate+","+toDelDate+","+delId;
		   info = info+temp+";"; 
	   }else{
		   info = 1;
	   }
	}) 
	return info;
 }
 
 /**
  * 点击页面上自动带出按钮时
  * 获取页面输入的理财经理编号
  * @returns {String}
  */
 var numberTotal = [];// 用来存放页面每条数据对应的行号
  function getManager(){
 	var ids="";
 	var code="";
 	$("input[name='checkbox']:checked").each(function(){
 	   ids=$(this).val();// 获取页面传过来的数据条目编号和客户编号
 	   var id = ids.split(",");// 分离获取数据条目编号
 	   var number = "";
 	   number = id[0];
 	   numberTotal.push(number);
 	   var nfManagerCode = "";
 	   nfManagerCode = $("#nfManagerCode"+number).val();// 获取页面输入的新理财经理编号
 	   code = code+nfManagerCode+",";
 	})
 	return code;
  } 
  
// 交割回调函数
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
 * 客户交割
 * @param count
 * @param custCode
 * @param fManagerCode
 */
function customerDelivery(count,custCode,fManagerCode,delDate){
		if($('#form').validate().element('#nfManagerCode'+count)){
			 BootstrapDialog.confirm('确定要交割此客户吗？', function(result){
		         if(result){
		        	 var nfManagerCode = "";
		     	     var toDelDate = "";
		     	     nfManagerCode = $("#nfManagerId"+count).val();// 获取页面输入的新理财经理编号
		     	     toDelDate = $("#delDate"+count).val();// 获取页面输入的交割时间
		     	     var delId = $("#delId"+count).val();
		        	 var url = ctx+"/delivery/customer/custDelivery";
		  		     contents_getJsonForSync(
		  		    		 url,
		  		    		 {'custCode':custCode,'fManagerCode':fManagerCode,'nfManagerCode':nfManagerCode,'delDate':delDate,'toDelDate':toDelDate,'delId':delId},
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
                        "nTeamManagerCode":this.nTeamManagerCode,
                        "nTeamManagerName":this.nTeamManagerName,
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
  		 $("#nfManagerName"+commonCount).val(ui.item.nfManagerName);
  		 $("#nTeamManagerCode"+commonCount).val(ui.item.nTeamManagerCode);
  		 $("#nTeamManagerName"+commonCount).val(ui.item.nTeamManagerName);
  		 $("#nOrgName"+commonCount).val(ui.item.nOrgName);
  		 $("#nfManagerId"+commonCount).val(ui.item.nfManagerId);
  	 }
   }
 
  $(function(){
	  autocompleteSearch($('.autoComplete'),source,select); 
	  var iframe = getCurrentIframeContents();
		//查询
		$('#search',iframe).click(function(){
			//默认第一页
			page(1);
		});
	  // 批量交割
	  $("#delivery",iframe).click(function(){
		  var code = getInfo();
		  if(code == 1){
			  return;
		  }else if(code.length == 0){
			  BootstrapDialog.alert('请选择要交割的客户！');
		  }else{
			  BootstrapDialog.confirm('确定要交割选择的客户吗？', function(result){
			         if(result){
			        	 var url = ctx+"/delivery/customer/batchCustomerDelivery";
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
		 }) 
	   // 导出列表
	   $("#outExcel",iframe).click(function(){
		   BootstrapDialog.confirm('确定要导出EXCEL吗？', function(result){
		         if(result){
		        	 var url = ctx+"/delivery/customer/outRemindExcel?"+$("#searchForm").serialize();
					 window.location=url;
		         }else{
		        	 return;
		         }
		     });
	   })
	   // 批量带出
	   $("#loadManagerInfo",iframe).click(function(){
		   var ids = getManager();
		   if(ids.length == 0){
			   BootstrapDialog.alert('请选择带出的信息！');
		   }else{
			   var url = ctx + "/delivery/customer/loadManagerInfo";
			   contents_getJsonForSync(
	  		    		 url,
	  		    		 {'ids':ids},
	  		    		 "post",
	  		    		 function(json){
	  		    			 for(var i = 0;i<json.length;i++){
	  		    				 $("#nfManagerId"+numberTotal[i]).val(json[i].nfManagerId);
	  		    				 $("#nfManagerName"+numberTotal[i]).val(json[i].nfManagerName);
	  		    				 $("#nTeamManagerCode"+numberTotal[i]).val(json[i].nTeamManagerCode);
	  		    				 $("#nTeamManagerName"+numberTotal[i]).val(json[i].nTeamManagerName);
	  		    				 $("#nOrgName"+numberTotal[i]).val(json[i].nOrgName);
	  		    			 }
	  		    			numberTotal.length=0;
	  		    		 },
	  		    		 errorCal,
	  		    		 null
	  		    		 ); 
		   }
	   })
	   
	 })
	