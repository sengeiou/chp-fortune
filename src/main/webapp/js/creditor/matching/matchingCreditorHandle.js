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
		// 已选择的 的债权
		var creditValueIds="";
		var creditValueIdArray =  $("#tjzq input[name='creditValueId']");//可用债权ID
		if(typeof(creditValueIdArray) != undefined){
			for (var i=0; i<creditValueIdArray.length;i++){
				creditValueIds = creditValueIds+$(creditValueIdArray[i]).val()+"-";
			}
		}
    	// 调用子窗口的参数
    	var url, argment, callback;
    	url = '/matchingcreditor/getBorrowDetail';
    	argment = {'matchingId':matchingId,'creditValueIds':creditValueIds};
    	load_callback = function(iframe){
    		subCheckboxEventInit();
    	    $('.back',iframe).off('click').on('click',function(){
    	 	   sub.closeSubWindow(iframe);//关闭事件
    	 	   return false;
    	     });
    	    /**
			 * 点击搜索
			 */
			$(".BorrowDetailBut").click(function(){
				var selectBeforeValue = $("#selectCreditValueId").val();
				var ids="";
				/*$("[name='creditValueId']:checkbox:checked").each(function(){
					ids+="," + $(this).val();
				});*/
				ids = selectBeforeValue+ids;
				document.getElementById('idsTmp').value = ids;
				document.getElementById('yxzsums').value = $("#yxzsum").val();
				document.getElementById('yxzz').value=$("#yxz").val();
				document.getElementById('yxz').value=$("#yxz").val();
				var yxzsum=$("#yxzsum").val();
				
				if ($("#searchForm",iframe).validate().form()) {
					var lower =   Number($('#lower',iframe).val());
					var upper =  Number( $('#upper',iframe).val());
					var loanMonthRate = $('#loanMonthRate',iframe).val();
					if(loanMonthRate == ''){
						BootstrapDialog.alert("利率不能为空！");
					}else{
						loanMonthRate = Number(loanMonthRate);
						if(loanMonthRate>=lower && loanMonthRate<=upper){
							contents_getJsonForHtml(
									ctx+"/matchingcreditor/getBorrowDetailList",
									$.param({'ids':ids}) + '&' + $("#searchForm",iframe).serialize(),
									"POST",
									function(html){
										$('#listDiv').html(html);
										$(".num",iframe).val($('#yxzz',iframe).val());
										$(".money",iframe).val(Number($('#yxzsums',iframe).val()).toFixed(2));
										$("#selectCreditValueId",iframe).val(ids);
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
    		var creditValueIds =$("#selectCreditValueId",iframe).val();
  	 	   /*var creditValueId = $table.find('[name="creditValueId"]:checked');
  	 	    for(var i =0; i<creditValueId.length;i++){
  	 	    	creditValueIds = creditValueIds+$(creditValueId[i]).val()+',';
  	 	    }*/
  	 	 contents_getJsonForSync(
  	 			ctx+'/matchingcreditor/getBorrowByCreditValueIds?creditValueIds='+creditValueIds+'&ylmoney='+ylmoney+'&matchingBorrowQuota='+matchingBorrowQuota,  
  	 			{}, 
	 			'get', 
	 			//成功后执行
	 			function(json){
 		  	    	var trs='';//动态添加tr
 		  	    	var tempRowNumber =0;
 		  	    	var tempmoney=0;
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
 		  	    			trs = trs + '<td onClick="getDel(this)">'+'<a href="#">移除</a>  <input type="hidden" name="verTime"  value="'+json[i].verTime+'"> <input type="hidden" name="creditValueId"  value="'+json[i].creditValueId+'">'+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanName+'</td>';
 		  	    			trs = trs + '<td >'+json[i].dictLoanTypeStr+'<input type="hidden" name="dictLoanType"  value="'+json[i].dictLoanType+'"></td>';
 		  	    			trs = trs + '<td >'+json[i].loanProduct+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanJobStr+'</td>';
 		  	    			trs = trs + '<td >'+loanMiddleMan+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanPurpose+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanBackmoneyFirdayStr +'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanBackmoneyDay+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanMonths+'</td>';
 		  	    			trs = trs + '<td >'+json[i].loanMonthsSurplus+'</td>';
 		  	    			trs = trs + '<td >￥'+json[i].loanQuota.toFixed(2)+'</td>';
 		  	    			trs = trs + '<td > <input type="hidden" name="upperMoney"  value="'+upperMoney+'">';
 		  	    			trs = trs + ' <input type="hidden" name="loanCreditValue"  value="'+json[i].loanCreditValue.toFixed(2)+'">￥'+json[i].loanCreditValue.toFixed(2)+'</td>';
 		  	    			
 		  	    			if(Number(json[i].loanMonthRate) % 1 == 0){
 		  	    				trs = trs + '<td >'+Number(json[i].loanMonthRate).toFixed(1)+'%</td>';
 		  	    			}else{
 		  	    				trs = trs + '<td >'+json[i].loanMonthRate+'%</td>';
 		  	    			}
 		  	    			trs = trs + '<td > <input type="text" name="tjmoney"  value="'+json[i].tjmoney.toFixed(2)+'" onblur="clearNoNum(this)"   />'+'</td>';
 		  	    			trs = trs + '<td >'+json[i].dicLoanDistinguish+'</td>';
 		  	    			
 		  	    			trs = trs + '</tr>';
 		  	    			tempRowNumber=tempRowNumber+1;
 		  	    			tempmoney = Number(tempmoney)+Number(json[i].tjmoney.toFixed(2));
 		  	    	  } 
 		  	    	$("#tjzq").append(trs); 
 		  	    	var ylnumber = $("#ylnumber").val();
 		  	    	var ylmoney = $("#ylmoney").val().replace(/,/g,'');
 		  			 $("#ylnumber").val(Number(ylnumber)+Number(tempRowNumber));
 		  			 ylmoney = Number(ylmoney)+Number(tempmoney);
 		  			 $("#ylmoney").val(ylmoney.toFixed(2));
 		  	    	
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
	//匹配提交
	function matchingSubmit(matchingId,lendCode,totalMoney,distributedFlag,verTime){
		var from = $("#pageFrom").val();
		var isOnlyXinjieFlag = $("#isOnlyXinjieFlag").val();
		var matchingBeforeDifferent = $("#matchingBeforeDifferent").val();
		var isnotHege= false;
		var ylmoney = $('#ylmoney').val().replace(/,/g,'');
		var creditValueIdArray =  $("#tjzq input[name='creditValueId']");//可用债权ID
		var dictLoanTypeArray = $("#tjzq input[name='dictLoanType']");// 债权类型
		var tjmoneyArray = $("#tjzq input[name='tjmoney']");//推荐额度
		var  loanCreditValueArray = $("#tjzq input[name='loanCreditValue']");//可用债权
		var  verTimeArr = $("#tjzq input[name='verTime']");// 可用债权的更新时间
		var  upperMoneyArr = $("#tjzq input[name='upperMoney']");// 金额限额
		var creditValueIds="";
		var tjmoneys ="";
		var   borrowVerTimes ="";
		for (var i=0; i<creditValueIdArray.length;i++){
			if($(tjmoneyArray[i]).val()==undefined)break;
			var tempCreditValue = Number($(loanCreditValueArray[i]).val());
			if(Number($(tjmoneyArray[i]).val())-tempCreditValue>0){
				BootstrapDialog.alert('推荐额度必须小于等于剩余债权价值！');
				return false;
			}
			if(Number($(tjmoneyArray[i]).val())-Number(0)<=0){
				BootstrapDialog.alert('推荐额度必须大于0！');
				return false;
			}
			var tempUpperMoney = $(upperMoneyArr[i]).val();
			if(tempUpperMoney!=''){
				if(Number(tempUpperMoney)-Number($(tjmoneyArray[i]).val())<0){
					BootstrapDialog.alert('剩余推荐额度为'+tempCreditValue+'的债权，推荐额度必须小于等于限额'+tempUpperMoney+'！');
					return false;
				}
			}
			if(isOnlyXinjieFlag=='0'){
				if($(dictLoanTypeArray[i]).val() != '0'){
					isnotHege= true;
				}
			}
			creditValueIds = creditValueIds+$(creditValueIdArray[i]).val()+"-";
			tjmoneys = tjmoneys + $(tjmoneyArray[i]).val()+"-";
			borrowVerTimes = borrowVerTimes+ $(verTimeArr[i]).val()+";";
		}
		if(Number(ylmoney)-Number(totalMoney)!=0){
			BootstrapDialog.alert('推荐总金额与本期待推荐金额不一致，请修改推荐额度！');
			return false;
		}
		if(isnotHege && matchingBeforeDifferent == '0'){	//matchingBeforeDifferent  为0：表示历史数据全是信借，为1：标识历史数据存在车借或混借
			 if (confirm("您选择的债权有借款类型包含信借以外的债权，是否继续提交？")) {  
				 tijiao(matchingId,lendCode,creditValueIds,tjmoneys,distributedFlag,from,verTime,borrowVerTimes);
		    } 
		}else{
			 tijiao(matchingId,lendCode,creditValueIds,tjmoneys,distributedFlag,from,verTime,borrowVerTimes);
		}
		
		
}
	// 匹配撤销
	function matchingCancel(matchingId){
		window.location.href=ctx+'/matchingcreditor/matchingCancel?matchingId='+matchingId;
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
		         obj.value = Number(0).toFixed(2);;
		     } 
			 tjmoneyonchange();
	}
function tijiao(matchingId,lendCode,creditValueIds,tjmoneys,distributedFlag,from,verTime,borrowVerTimes){
	loadingMarkShow();
	var subUrl = ctx+'/matchingcreditor/matchingSubmit?matchingId='+matchingId+'&lendCode='+lendCode+'&creditValueIds='+creditValueIds+'&tjmoneys='+tjmoneys+'&ymyType=2&distributedFlag='+distributedFlag+'&from='+from+'&verTime='+verTime+'&borrowVerTimes='+borrowVerTimes;
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
}