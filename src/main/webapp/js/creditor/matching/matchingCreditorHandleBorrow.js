$(document).ready(function(){
	tjmoneyonchange();
}) 
/**
	 * 可用债权查询弹出框
	 */
	function recommendBorrow(matchingId,matchingBorrowQuota){
		var ylmoney = $("#ylmoney").val().replace(/,/g,'');
		if(Number(ylmoney)-Number(matchingBorrowQuota)>=0){
			alert('已选债权总金额已满，无需再手动匹配！');
			return false;
		}
		// 已匹配可用债权
		var creditMonableIdArray =  $("#tjzq input[name='creditMonableId']");//可用债权ID
		var creditMonableIds="";
		if(typeof(creditMonableIdArray) != undefined){
			for (var i=0; i<creditMonableIdArray.length;i++){
				creditMonableIds = creditMonableIds+$(creditMonableIdArray[i]).val()+"-";
			}
		}
    	// 调用子窗口的参数
    	var url, argment, callback;
    	url = '/matchingcreditor/getborrowMonthAbleDetail';
    	argment = {'matchingId':matchingId,'creditMonableIds':creditMonableIds};
    	load_callback = function(iframe){
    		subCheckboxEventInit();
    	    $('.back',iframe).off('click').on('click',function(){
    	 	   sub.closeSubWindow(iframe);//关闭事件
    	 	  return false;
    	     });
    	    /**
			 * 点击搜索
			 */
			$(".matchingBorrowBut").click(function(){
				if ($("#searchForm",iframe).validate().form()) {
					var lower =   Number($('#lower',iframe).val());
					var upper =  Number( $('#upper',iframe).val());
					var borrowMonthRate = $('#borrowMonthRate',iframe).val();
					if(borrowMonthRate == ''){
						BootstrapDialog.alert("利率不能为空！");
					}else{
						borrowMonthRate = Number(borrowMonthRate);
						if(borrowMonthRate>=lower && borrowMonthRate<=upper){
							contents_getJsonForHtml(
									ctx+"/matchingcreditor/getborrowMonthAbleDetailList",
									$("#searchForm",iframe).serialize(),
									"POST",
									function(html){
										$('#listDiv').html(html);
										$(".num",iframe).val(0);
										$(".money",iframe).val(Number(0).toFixed(2));
										$("#selectCreditMonableIds",iframe).val('');
										subCheckboxEventInit();
										}
							);	
						}else{
							BootstrapDialog.alert("利率必须在"+lower+"~"+upper+"之间！");
						}
						
					}
					
				
			
				}
				
				
			});
    	};
    	//回调函数
    	close_callback = function(iframe){
    		var $table = $('#borrowDetail',iframe);
    		var creditMonableIds =$("#selectCreditMonableIds",iframe).val();
  	 	 /*  var creditValueId = $table.find('[name="creditMonableId"]:checked');
  	 	    for(var i =0; i<creditValueId.length;i++){
  	 	    	creditMonableIds = creditMonableIds+$(creditValueId[i]).val()+',';
  	 	    }*/
  	 	   // 通过选择的的可用债权带回主页面
  	 	   contents_getJsonForSync(
  	 			   ctx+'/matchingcreditor/getBorrowByCreditMonableId?creditMonableIds='+creditMonableIds+'&ylmoney='+ylmoney+'&matchingBorrowQuota='+matchingBorrowQuota, 
  	 			   {}, 
  	 			   'get', 
  	 			   //成功后执行
	  	 		   function(json){
  	 				   if(json != null && typeof json == 'object'){
	  			  	    	var trs='';//动态添加tr
	  			  	    	var tempRowNumber =0;
	  			  	    	var tempmoney=0;
	  			  	    	if(json.length==0){
	  			  	    	BootstrapDialog.alert("当前并未选择任何数据！");
	  			  	    	}
	  			  	    	for(var i=0; i<json.length; i++)  
	  			  	    		{  
	  			  	    			var loanMiddleMan = json[i].loanMiddleMan;
			  			  	    	if(typeof(loanMiddleMan) =="undefined"){
				  	    				loanMiddleMan='';
				  	    			}
				  			  	    var upperMoney =json[i].upperMoney; 
				  	    			if(upperMoney =="undefined"){
				  	    				upperMoney='';
				  	    			}
	  			  	    			trs = trs + '<tr>';
	  			  	    			trs = trs + '<td onClick="getDel(this)" id="creditMonableIdTd">'+'<a href="#">移除</a><input type="hidden" name="verTime"  value="'+json[i].verTime+'">  <input type="hidden" name="creditMonableId"  value="'+json[i].creditMonableId+'">'+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanName+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].dictLoanTypeStr+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanJobStr+'</td>';
	  			  	    			trs = trs + '<td >'+loanMiddleMan+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanPurpose+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanBackmoneyFirdayStr+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanBackmoneyDayStr +'</td>';
	  			  	    			trs = trs + '<td >  <input type="hidden" name="loanAvailableDays"  value="'+json[i].loanAvailableDays+'">'+json[i].loanAvailableDays+'</td>';
	  			  	    			trs = trs + '<td > <input type="text" name="tjday" value="30" ';
	  			  	    			trs = trs + 'onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\D/g,\'\')}"';
	  			  	    			trs = trs + 'onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,\'\')}else{this.value=this.value.replace(/\D/g,\'\')}"';
	  			  	    			trs = trs + 'value="30"  onchange="tjdaychange(this)"/>'+'</td>';
	  			  	    			trs = trs + '<td >'+json[i].loanCreditValue.toFixed(2)+'</td>';
	  			  	    			trs = trs + '<td > <input type="hidden" name="loanAvailabeValue"  value="'+json[i].loanAvailabeValue.toFixed(2)+'">￥'+json[i].loanAvailabeValue.toFixed(2)+'</td>';
	  	 		  	    			if(Number(json[i].loanMonthRate) % 1 == 0){
	  	 		  	    				trs = trs + '<td >'+Number(json[i].loanMonthRate).toFixed(1)+'%</td>';
	  	 		  	    			}else{
	  	 		  	    				trs = trs + '<td >'+json[i].loanMonthRate+'%</td>';
	  	 		  	    			}
	  			  	    			trs = trs + '<td id = "tjmoneyTd"> <input type="hidden" name="upperMoney"  value="'+upperMoney+'">';
	  			  	    			trs = trs + '<input type="text" id="tjmoneyInput" name="tjmoney" onblur="clearNoNum(this)"  value="'+json[i].tjMomey.toFixed(2)+'" />'+'</td>';
  			  	    		    
	  			  	    			trs = trs + '<td >'+json[i].dicLoanDistinguish+'</td>';
	  			  	    			trs = trs + '</tr>';
	  			  	    			tempRowNumber=tempRowNumber+1;
	  			  	    			tempmoney = Number(tempmoney)+Number(json[i].tjMomey.toFixed(2));
	  			  	    		    
	  			  	    	  } 
	  			  	    	$("#tjzq").append(trs); 
	  			  	    	var ylnumber = $("#ylnumber").val();
	  			  			var ylmoney = $("#ylmoney").val().replace(/,/g,'');
	  			  			 $("#ylnumber").val(Number(ylnumber)+Number(tempRowNumber));
	  			  			 ylmoney = Number(ylmoney)+Number(tempmoney);
	  			  			 $("#ylmoney").val(ylmoney.toFixed(2));
  	 				   }else{
  	 					BootstrapDialog.alert("当前并未选择任何数据！");
  	 				   }
	  			  	     },
	  	 			//失败后执行
	  	 			   function(){
	 		  	    	  alert("程序内部错误");
	 		  	      }  ,null);
  	 	   
    	};
    	
        var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
}
	//删除tr 事件
	function getDel(k){
		$tr = $(k).closest('tr');
		var tjmoney = $('[name="tjmoney"]',$tr).val().replace(/,/g,'');
		var ylnumber = $("#ylnumber").val();
		var ylmoney = $("#ylmoney").val().replace(/,/g,'');
		 $("#ylnumber").val(ylnumber-1);
		 $("#ylmoney").val((Number(ylmoney)-Number(tjmoney)).toFixed(2));
	    $(k).parent().remove();     
	}
	// 移除所有匹配债权
	function removeAll(){
		 $("#ylnumber").val(0);
		 $("#ylmoney").val(Number(0).toFixed(2));
		$("#tjzq tr:gt(0)").remove();
		
	}
	// 修改推荐金额时，修改总金额
	function tjmoneyonchange(){
		var tjmoneyArray = $("input[name='tjmoney']");
		var tjmoneys =0.00;
		var tjmoney = 0.00;
		for (var i=0; i<tjmoneyArray.length;i++){
			tjmoney = $(tjmoneyArray[i]).val().replace(/,/g,'');
			if( tjmoney > 0){
				tjmoneys = Number(tjmoneys)+Number(tjmoney);
			}	
		}
		 $("#ylmoney").val(tjmoneys.toFixed(2));
	}
	// 修改天数时 ，修改天数一致
	function tjdaychange(object){
		var tjdayNumber= $(object).val();
		var tjdayArray = $("input[name='tjday']");
		for (var i=0; i<tjdayArray.length;i++){
			$(tjdayArray[i]).val(tjdayNumber);
		}
	}
	//匹配提交
	function matchingSubmit(matchingId,lendCode,totalMoney,distributedFlag,verTime){
		var tjdayValue = $("input[name='tjday']").val();
		var from = $("#pageFrom").val();
		var ylmoney = $('#ylmoney').val().replace(/,/g,'');
		
	var creditMonableIdArray =  $("#tjzq input[name='creditMonableId']");//可用债权ID
	var loanAvailabeValueArray = $("#tjzq input[name='loanAvailabeValue']");//可用债权
	var tjmoneyArray = $("#tjzq input[name='tjmoney']");//推荐额度
	var loanAvailableDaysArray = $("#tjzq input[name='loanAvailableDays']") ;  //可用天数
	var tjdayArray = $("#tjzq input[name='tjday']") ;  //实际借出天数
	var  verTimeArr = $("#tjzq input[name='verTime']");// 可用债权的更新时间
	var  upperMoneyArr = $("#tjzq input[name='upperMoney']");// 金额限额
	var creditMonableIds="";
	var tjmoneys ="";
	var tjdays ="";
	var borrowVerTimes ="";
	for (var i=0; i<creditMonableIdArray.length;i++){
		var temploanAvailabeValue = Number($(loanAvailabeValueArray[i]).val());
		if(Number($(tjmoneyArray[i]).val())-temploanAvailabeValue>0){
			BootstrapDialog.alert('推荐额度必须小于等于剩余债权价值！');
			return false;
		}
		if(Number($(tjmoneyArray[i]).val())-Number(0)<=0){
			BootstrapDialog.alert('推荐额度必须大于0！');
			return false;
		}
		if(Number($(tjdayArray[i]).val())-Number(0)<=0){
			BootstrapDialog.alert('实际出借天数必须大于0！');
			return false;
		}
		var tempUpperMoney = $(upperMoneyArr[i]).val();
		if(tempUpperMoney!=''){
			if(Number(tempUpperMoney)-Number($(tjmoneyArray[i]).val())<0){
				BootstrapDialog.alert('剩余推荐额度为'+temploanAvailabeValue+'的债权，推荐额度必须小于等于限额'+tempUpperMoney+'！');
				return false;
			}
		}
		if(Number($(tjdayArray[i]).val())-Number($(loanAvailableDaysArray[i]).val())>0){
			if(Number($(loanAvailableDaysArray[i]).val()) - 30 > 0){
				BootstrapDialog.alert('实际出借天数必须小于等于可用天数！');
				return false;
			}
		}
		creditMonableIds = creditMonableIds+$(creditMonableIdArray[i]).val()+"-";
		tjmoneys = tjmoneys + $(tjmoneyArray[i]).val()+"-";
		tjdays = tjdays + $(tjdayArray[i]).val()+"-";
		borrowVerTimes = borrowVerTimes+ $(verTimeArr[i]).val()+";";
	}
	//if( Math.abs(Number(ylmoney)-Number(totalMoney)).toFixed(3) > Number(0.001)){
	if( Number(ylmoney)-Number(totalMoney) != 0 ){
		alert('推荐总金额与本期待推荐金额不一致，请修改推荐额度！');
		return false;
	}
	if((+tjdayValue)<15){
		BootstrapDialog.alert("实际出借天数必须大于等于15天！");
		return;
	}
	// 判断提交的债权是否有不同的债权
	 contents_getJsonForSync(
 			   ctx+'/matchingcreditor/getDictLoanTypeByCreditMonableIds?creditMonableIds='+creditMonableIds, 
 			   {}, 
 			   'get', 
 			   //成功后执行
	 		   function(json){
			  	    	if(json==0){
			  	    		loadingMarkShow();
			  	    		
			  	    		var subUrl =ctx+'/matchingcreditor/matchingBorrowMonthSubmit?matchingId='+matchingId+'&lendCode='+lendCode+'&creditMonableIds='+creditMonableIds+'&tjmoneys='+tjmoneys+'&tjdays='+tjdays+'&distributedFlag='+distributedFlag+'&from='+from+'&verTime='+verTime+'&borrowVerTimes='+borrowVerTimes;
			  	    		contents_getJsonForHtml(
			  	    				subUrl,
			  	    				null,
			  	    				'post',
			  	    				function(result){
			  	    					if(distributedFlag == '1'){ //如果从分派待推荐债权列表来
					  	    				if(result=='0'){
					  	    					 BootstrapDialog.alert('匹配成功！');
					  	    				 	 window.location =ctx+'/matchingcreditor/distributeRecommendationList';
					  	    				}else{
					  	    					 BootstrapDialog.alert('匹配失败！具体原因如下：\n'+result);
					  	    					 if(result.indexOf("待推荐债权已被操作")!=-1){
					  	    						 window.location =ctx+'/matchingcreditor/distributeRecommendationList';
					  	    					 }
					  	    				}
			  	    					}else{ //如果从待推荐债权列表来
					  	    				if(result=='0'){
					  	    					 BootstrapDialog.alert('匹配成功！');
					  	    				 	 window.location =ctx+'/matchingcreditor/list?returnFlag=1';
					  	    				}else{
					  	    					 BootstrapDialog.alert('匹配失败！具体原因如下：\n'+result);
					  	    					 if(result.indexOf("待推荐债权已被操作")!=-1){
					  	    						 window.location =ctx+'/matchingcreditor/list?returnFlag=1';
					  	    					 }
					  	    				}
			  	    					}

			  	    				}
			  	    			);
			  	    	}else{
			  	    		BootstrapDialog.alert("车借不能跟别的类型混匹，请修改！");
			  	    	}
			  	     },
	 			//失败后执行
	 			   function(){
		  	    	  alert("程序内部错误");
		  	      }  ,null);
	
}
	// 匹配撤销
	function matchingCancel(matchingId,lendCode){
	        	 BootstrapDialog.confirm_my = new BootstrapDialog({
			            title: '撤销',
			            message: "<form id='sel'><span>撤销原因:</span><input type='text' name='note'  value=''/> " +
			            		"<input type='hidden' name='matchingId'  value='"+matchingId+"'/>" +
			            		"<input type='hidden' name='lendCode'  value='"+lendCode+"'/>" +
			            				"</form>",
			            closable: false,
			            data: {},
			            buttons:[{
			                cssClass: 'cf_btn-primary',
			                label: '提交',
			                action: function(dialogItSelf) {
			                	dialogItSelf.close();
			                	//check
			                	contents_getJsonForSync(ctx+'/matchingcreditor/matchingCancel',$('#sel').serialize(),'post',
			                		 //成功取得后的处理
			       					 function(data){
			                		window.location.href = document.referrer;
			                		},
			       		        	 //取得失败后的处理
			       		        	 function(error){
			       		        		 BootstrapDialog.alert('系统错误');
			       		        	 }
			         		    );
			                }
			            },
			            {
			                label: '取消',
			                action: function(dialogItSelf) {
			                	dialogItSelf.close();
			                    return false;
			                }
			            }] 
			        }).open();
	}	
	
	/**
	 * 弹出子窗口分页方法
	 * @param n
	 * @param s
	 * @returns {Boolean}
	 */
	function subPage(n,s){
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		var url = $("#searchForm").attr('action');
		contents_getJsonForHtml(
			url,
			$("#searchForm").serialize(),
			'post',
			function(result){
				$('#listDiv').html(result);	
			}
		);
		return false;
	}
	/**
	 * 判断输入的字符是否符合规则
	 * @param obj
	 */
	function clearNoNum(obj) {
			 obj.value = obj.value.replace(/[^\d.]/g, "");//清除“数字”和“.”以外的字符
			 obj.value = obj.value.replace(/^\./g, "");//验证第一个字符是数字而不是.
			 obj.value = obj.value.replace(/\.{2,}/g, ".");//只保留第一个. 清除多余的.
			 obj.value = obj.value.replace(".", "$#$").replace(/\./g,"").replace("$#$", ".");
			 var reg = new RegExp("^[0-9]+(\.[0-9]{1,2})?$", "g"); 

			 if (!reg.test(obj.value)) { 
				 if(obj.value != ""){
					 alert("请输入一个数字，最多只能有两位小数！");  
				 }
		         obj.value = Number(0).toFixed(2);
		     } 
			 tjmoneyonchange();
	}
	
	/**
	 * 封装多选框计算
	 * @param url
	 * @param obj
	 */
	function checkboxFunMonth(url,obj,boo){
		var selectCreditMonableIds = $("#selectCreditMonableIds").val(); // 选中的债权编号
		 if (obj.prop("checked")) {
				num = $(".num").val();
				$(".num").val(+num+1);
				if(typeof(selectCreditMonableIds)!='undefined' && selectCreditMonableIds!=null && selectCreditMonableIds!=''){
					selectCreditMonableIds= selectCreditMonableIds +","+obj.val();
				}else{
					selectCreditMonableIds= obj.val();
				}
				$("#selectCreditMonableIds").val(selectCreditMonableIds);
			 contents_getJsonForSync(
				url+selectCreditMonableIds,
				null,
				'get',
				function(result) {
					var result = Number(result).toFixed(2);
					$(".money").val(result);
					
				}
			);
		}else{
			num = $(".num").val();
			$(".num").val(+num - 1);
			var selectCreditMonableIdsArr = null;
			if(typeof(selectCreditMonableIds)!='undefined' && selectCreditMonableIds!=null && selectCreditMonableIds!=''){
				selectCreditMonableIdsArr = selectCreditMonableIds.split(',');
			}
			if(selectCreditMonableIdsArr!=null && selectCreditMonableIdsArr.length>0){
				selectCreditMonableIds = removeStrInArr(selectCreditMonableIdsArr,obj.val());
				$("#selectCreditMonableIds").val(selectCreditMonableIds);
			}else{
				$("#selectCreditMonableIds").val('');
				selectCreditMonableIds='';
			}
			contents_getJsonForSync(
					url+selectCreditMonableIds,
					null,
					'get',
					function(result) {
						var result = Number(result).toFixed(2);
							$(".money").val(result);
					}
				);
		}
	}




	/**
	 * checkbox计算全选中金额
	 */
	function checkboxAllFunMonth(url,obj,boo){
		var selectCreditMonableIds = $("#selectCreditMonableIds").val(); 
		if(obj.is(':checked')){
			var lendCode = obj.closest('table').find(':checked[value]').map(function() {
				return this.value;
			}).get();
			if(typeof(selectCreditMonableIds)!='undefined' && selectCreditMonableIds!=null && selectCreditMonableIds!=''){
				 $.each(lendCode, function(i,val){  // 循环已
						if(selectCreditMonableIds.indexOf(val)==-1){ // 已经有的值不在放入
							selectCreditMonableIds = selectCreditMonableIds +','+ val;
						}
				 });
			}else{
				selectCreditMonableIds = lendCode.join(',');
			}
			 $("#selectCreditMonableIds").val(selectCreditMonableIds);
			contents_getJsonForSync(
					url+selectCreditMonableIds,
					{},
					'get',
					function(result) {
						var result = Number(result).toFixed(2);
						$(".money").val(result);
					}
			);
			
		}else{ // 取消
			var creditMonableIdArr  = obj.closest('table').find("input[name='creditMonableId']").map(function() {
				return this.value;
			}).get();
			var selectCreditMonableIdsArr =null;
			if(typeof(selectCreditMonableIds)!='undefined' && selectCreditMonableIds!=null && selectCreditMonableIds!=''){
				selectCreditMonableIdsArr = selectCreditMonableIds.split(',');// 已选择的债权数组
			}
			if(selectCreditMonableIdsArr!=null && selectCreditMonableIdsArr.length>0){
				for(var i=0; i<creditMonableIdArr.length;i++){
					if(selectCreditMonableIds.indexOf(creditMonableIdArr[i])!=-1){
						selectCreditMonableIds = removeStrInArr(selectCreditMonableIdsArr,creditMonableIdArr[i]);	
					}
				}
				
				$("#selectCreditMonableIds").val(selectCreditMonableIds);
			}
			contents_getJsonForSync(
					url+selectCreditMonableIds,
					{},
					'get',
					function(result) {
						var result = Number(result).toFixed(2);
						$(".money").val(result);
						
					}
			);
		}
		if(typeof(selectCreditMonableIds)!='undefined' && selectCreditMonableIds!=null && selectCreditMonableIds!=''){
			$(".num").val(selectCreditMonableIds.split(",").length);
		}else{
			$(".num").val(0);
		}
	}
