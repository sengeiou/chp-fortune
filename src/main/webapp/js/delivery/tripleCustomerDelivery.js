/**
 * 获取页面数据编号
 */
var commonCount = "";// 页面每条数据对应的编号
function getCount(count){
	commonCount = count;
	if($("#newEmpCode"+count).val() == ""){
		$("#newEmpCode"+count).val("");
		$("#newEmpName"+count).val("");
		$("#newTeamManagerCode"+count).val("");
		$("#newTeamManagerName"+count).val("");
		$("#newOrgName"+count).val("");
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
  		 $("#newEmpName"+commonCount).val(ui.item.newEmpName);
  		 $("#newTeamManagerCode"+commonCount).val(ui.item.newTeamManagerCode);
  		 $("#newTeamManagerName"+commonCount).val(ui.item.newTeamManagerName);
  		 $("#newOrgName"+commonCount).val(ui.item.newOrgName);
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
   	   ids=$(this).val();// 获取页面传过来的数据条目编号和客户编号
   	   var id = ids.split(",",9);// 分离获取数据条目编号
   	   var number = id[0];
   	   if($('#form').validate().element('#newEmpCode'+number)){
   		   var orderStatus = id[1];// 获取客户成单状态
   		   var phone = id[2];// 获取页面客户手机号
   		   var empCode = id[3]; // 原理财经理工号
   		   var loginName = id[4];// 登录名称
   		   var osType = id[5]; // 系统类型
   		   var cardType = id[6];// 证件类型
   		   var cardId = id[7]; // 证件号码
   		   var custCode = id[8];// 修改时间
   		   var newEmpCode = $("#newEmpCode"+number).val();// 获取页面输入的新理财经理编号
   		   temp = orderStatus +","+phone+","+empCode+","+newEmpCode+","+loginName+","+osType+","+cardType+","+cardId+","+custCode+","+number;
   		   info = info+temp+";"; 
   	   }else{
   		   info = 1;
   		   return false;
   	   }
   	}) 
   	return info;
    }
     
    /**
     * 跳转业绩交割页面
     */
    function goPage(code,time,loginName,empCode,phone){
    	if(code == "" || code == null || time == null || time == ""){
    		BootstrapDialog.alert("无法进行业绩交割！");
    	}else{
    		var url = ctx+"/delivery/tripleAchievement/list?customerCode="+code+"&loginName="+loginName+"&phone="+phone+"&empCode="+empCode;
 		   window.location.href=url;
    	}
    }
    // 字符串转date
    function getDate(strDate) {
        var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
         function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
        return date;
    }
     
    /**
	 对Date的扩展，将 Date 转化为指定格式的String   
	 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
	 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
	 例子：   
	 (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
	 (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
	*/
	Date.prototype.format = function(fmt) { //
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
	}  
    // 交割回调函数
    function callFunction(result,par){
    	var r = JSON.parse(result);
    	var a = r.result;
		/*$.each(JSON.parse(result).resultList,function(){
			var id = this.id;
			$(".empCode"+id).text(this.empCode);
			$(".empName"+id).text(this.empName);
			$(".orgName"+id).text(this.orgName);
			$("#newEmpCode"+id).val("");
			$("#newEmpName"+id).val("");
			$("#newTeamManagerCode"+id).val("");
			$("#newTeamManagerName"+id).val("");
			$("#newOrgName"+id).val("");
			var date = getDate(this.delDate);
			$(".delDate"+id).text(date.format("yyyy-MM-dd hh:mm") );
			$("[type='checkbox']").removeAttr("checked");
		})*/
    		
    	if(a > 0){
    		BootstrapDialog.alert("成功交割"+a+"条！",function(result){
    			$("#searchForm").submit();
    		});
    	}else if(a == 0){
    		BootstrapDialog.alert("交割失败",function(result){
    			$("#searchForm").submit();
    		});
    	}
    	
    }
    function errorCal(){
    	BootstrapDialog.alert("系统出错,请稍后……");
    }
    
   $(function(){
	   
	   var iframe = getCurrentIframeContents();
	   $('#search',iframe).click(function(){
			 $form = $('#searchForm');
			 $("#pageNo").val("");
			 $form.submit();
		 })
		 
	   autocompleteSearch($('.autoComplete'),source,select); 
	 
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
				        	 var url = ctx+"/delivery/tripleCustomer/batchTripleCustomerDelivery";
				  		   contents_getJsonForHtml(
				  				   url, 
				  				 {'code':code}, 
				  				  "post", 
				  				 callFunction,
				  				   errorCal,
				  				   null);
				         }else{
				        	 return;
				         }
				     });
			  }
			 }) 
	   // 导出列表
	   $("#outExcel",iframe).click(function(){
		   var custCode = $("#customerCode").val();
		   var custName = $("#loginName").val();
		   var empCode = $("#empCode").val();
		   var empName = $("#empName").val();
		   var orgId = $("#orgId").val();
		   if(custCode == "" && custName == "" && empCode == "" && empName == "" && orgId == ""){
			   BootstrapDialog.alert("请输入搜索条件进行导出！");
		   }else{
			   BootstrapDialog.confirm('确定要导出Excel吗？', function(result){
			         if(result){
			        	 var url = ctx+"/delivery/tripleCustomer/outExcel";
			        	 postFormReplaceGet($("#searchForm"),url);
			         }else{
			        	 return;
			         }
			     });
		   }
		 
	   })
	    // 上传按钮对应事件
	   $("#upload",iframe).click(function(){
		    var url, argment, callback;
			url = '/delivery/tripleCustomer/showDialog';
			argment = null;
			load_callback = function(arg){
			};
			close_callback = function(arg){
			};
			var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
			//自定义触发关闭回调函数的动作
			$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
	   });
	   // 全选
	   $("#checkAll").click(function(){
		   $all=$(this);
		   $table = $("#tableList");
		   var $count=-1;
		   $.each($table.find('tr'),function(index,tr){
			   if($(tr).find('.newEmpCode').val()=='' || $(tr).find('.newEmpName').val()==''){
				   $(tr).find('input[name="checkbox"]').prop( "checked", false );
			   }else{
				   $(tr).find('[name="checkbox"]').prop( "checked", $all.is(':checked') );
				   $count++;
			   }
		   });
		   if($count==0 && $("#checkAll").attr('checked')=='checked'){
			   BootstrapDialog.alert("请您至少填写一条要交割的理财经理信息!");
		   }
	   });
	   // 单个checkbox事件
	   $('input[name="checkbox"]').click(function(){
		   if($(this).closest('tr').find('.newEmpCode').val()=='' || $(this).closest('tr').find('.newEmpName').val()==''){
			   $(this).prop( "checked", false );
		   };
	   });
   })