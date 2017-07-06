function _change(src){
			var val = src.value;
			if(val=='1'){
				document.getElementById("_t1").style.display="none";
			}else{
				document.getElementById("_t1").style.display="";
			}
}
$(function(){
	
	if($("#theJspName").val()=='theJspName'){
		$(".bankNumDetail").text($(".bankNumDetail").text().replace(/\s/g,'').replace(/(.{4})/g, "$1 "));
	}else{
		$("#bankNum").val($("#bankNum").val().replace(/\s/g,'').replace(/(.{4})/g, "$1 "));
	}

	$("#btnSubmit").click(function(){
				checkVal = $("input[name=checkExaminetype]:checked").val();
				if(checkVal == undefined){
					BootstrapDialog.alert("请选择审批结果");
					return;
				}
				if( checkVal=='1' && $("#payType").val()=="1"||$("#payType").val()=="4"){
					 if($("#lendMoney").val() != $("#deductMoney").val()){
					    	BootstrapDialog.alert("你的出借金额与划扣金额不相等");
							return;
					    }
					}else if("1" == checkVal && ($("#payType").val()=="6"||$("#payType").val()=="2")){
							 if($("#lendMoney").val() != $("#transferMoney").val()){
							    	BootstrapDialog.alert("你的出借金额与内转总金额不相等");
							    	return;
							    }
					}
				if(checkVal == "2"){
					//不通过，判断审批意见
					checkContent = $("#checkExamine").val().replace(/\s/g,'');
					if(checkContent == ""){
						BootstrapDialog.alert("请填写审批意见");
						return;
					}
				}
				if("1" == checkVal && "1" == $("#payType").val()){
					var platId = $("#deductTypeId").val();// 平台id
					var provinceCode = $("#provinceCode").val(); // 银行所在省
					var bankId = $("#bankId").val(); // 开户行
					var deductDate = $("#deductDate").val();
					var lendMoney = $("#lendMoney").val();
					if(deductDate==""||lendMoney==""){
						BootstrapDialog.alert("请填写计划划扣日期或计划出借金额");
						return;
					}
					contents_getJsonForSync(
					ctx+"/lend/lendApplyAid/ajaxGetlendDate",
					{"platId":platId,"provinceCode":provinceCode,"bankId":bankId,"deductDate":deductDate,"lendMoney":lendMoney},
					"post",
					function(data){
						if(data==""){
							BootstrapDialog.alert("不支持该平台划扣,或该平台已被停用,请退回");
							return;
						}else if(data.exception!=null && data.exception!=''){
							BootstrapDialog.alert(data.exception);
							return;
						}
						else{
							//$("#lendDateHidden").val(data.lendDate);
							var arr1 = $("#lendDate").val().split("-");
						    var arr2 = data.lendDate.split("-");
						    var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0); 
						    var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
						    var time = date1.getTime()-date2.getTime();
						    if(0 != time ){
						    	BootstrapDialog.confirm('此计划出借日期与系统默认不符，确定or取消',function(result){
						    	
						    		if(result){
						    			isCas();
									}});
						    }else{
						    	isCas();
						    }
						    /* if(time<0){
						    	BootstrapDialog.confirm('你输入的出借日期小于实际出借日期'+data.lendDate,function(result){
									if(result){
										$("#inputForm").submit();
									}
								});
							}else if(time>0){
								BootstrapDialog.confirm('你输入的出借日期大于实际出借日期'+data.lendDate+',可以作为出借日期.你确定将此日期作为计划出借日',function(result){
									if(result){
										$("#inputForm").submit();
									}
								});
							} */
						}
					});
				}else if("1" == checkVal && "7" == $("#payType").val()){
					$("#inputForm").submit();
				}else if("1" == checkVal){
					isCas();
				}else{
					$("#inputForm").submit();
				}
	});
	if($("#theJspName").val()=='theJspName'){

		// 我的已办----->出借审批附件加载
		contents_getJsonForSync(
				ctx+'/common/file/getAttachmentAjax', 
				{'code':$('input[name="lendApply.applyCode"]').val(),'tableId':$('input[name="id"]').val(),'tableName':'t_tz_loan_apply'}, 
				'post',
				function(result){
					DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
						hideDrop:true,
						addRemoveLinks:false
					});
				},
				function(){
					BootstrapDialog.alert("加载附件发生错误");
					return;
		 },null);
	}else{
		//我的待办--->出借审批附件加载
		contents_getJsonForSync(
				ctx+'/common/file/getAttachmentAjax', 
				{'code':$('[name="lendCode"]').val(),'tableId':$('[name="id"]').val(),'tableName':'t_tz_loan_apply'}, 
				'post',
				function(result){
					DropzoneHandle.initDropZone('#my-awesome-dropzone',result,{
						hideDrop:true,
						addRemoveLinks:false
					});
				},
				function(){
					BootstrapDialog.alert("加载附件发生错误");
					return;
		 },null);
	}
			
});

/**
 * 判断是否签章成功
 * @returns
 */
function isCas() {

	var lendCode=$('input[name="lendCode"]').val();
	contents_getJsonForSync(
			ctx + "/lend/approve/makingFileCAS", 
			{"lendCode":lendCode}, 
			"post", 
			function (obj) {
		        
	        	if (obj == "true") {
	        		$("#inputForm").submit();
	    		}else{
	    			BootstrapDialog.alert(obj);
	    			return false;
	    		}
	        },
	        function (data, errorThrown, options, error) {
	        	BootstrapDialog.alert("合同申请书签章，请求异常！");
	        	return false;
	        },
	        null
	);
}