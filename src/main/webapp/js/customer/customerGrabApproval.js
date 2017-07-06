$(function(){
		$("#btnSubmit").click(function(){
			checkVal = $("input[name=checkExamineResult]:checked").val();
			if(checkVal == undefined){
				BootstrapDialog.alert("请选择审批结果");
				return;
			}
			if(checkVal == "2"){
				//不通过，判断审批意见
				checkContent = $("#checkExamineContent").val().replace(/\s/g,'');
				if(checkContent == ""){
					BootstrapDialog.alert("请填写审批意见");
					return;
				}
			}
			$("#searchForm").submit();
		});
	});
