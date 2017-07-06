// 提交回息申请确认验证
function confrim(purl,surl){
	
	iframe = getCurrentIframeContents();
	$form = $('#applyForm', iframe);
	var forms = $(iframe).find('#applyForm').serialize();
	$radiochk = $("input#rar");
	if($form.validate().element($radiochk)){
		var item = $('[name="checkExaminetype"]:checked').val();
		
		$inpDay = $("input#day");//获取回息日期输入元素
		$inpMoney = $("input#bm");// 获取回息金额输入元素
		$selectRs = $("select#cemine");// 获取退回原因下拉元素
		$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
		
		if(item=="1"){// 通过
			$("#cemine").removeAttr("select_required");
			$("#tar").removeAttr("textarea_required");
			$("#tar").removeAttr("required");
			if($form.validate().element($inpDay) && $form.validate().element($inpMoney)){
				//验证成功
				loadingMarkShow();
				limitCommit(forms,purl,surl);
			}
			return false;
		}
		if(item=="2"){// 不通过
			$("#day").removeAttr("required");
			$("#bm").removeAttr("required");
			if($form.validate().element($selectRs)){
				var s = $("#cemine").val();
				if(s=="6"){
					if($form.validate().element($textA)){
						loadingMarkShow();
						limitCommit(forms,purl,surl);
					}
					return false;
				}else{
					//验证成功
					loadingMarkShow();
					limitCommit(forms,purl,surl);
				}
			}
			return false
		}
	}
	return false;
}

// 回息申请确认多功能方法(批量回息申请确认和上传回息金额)
function applyConfrim(purl,surl){
	
	iframe = getCurrentIframeContents();
	var forms = $(iframe).find('#searchForm').serialize();
	var s = $("#hi").val();
	var data = null;
	$form = $('#winForm',getCurrentIframeContents());
	
	if(s=="1"){
		// 获取页面上需要提交的数据
		var checkExamine = $('#cemine').val();
		var backiId = $('[name="ids"]:checked').map(function() {
			return this.value;
		}).get().join(',');
		
		var backMoneyDay = $('#backMoneyDay').val();
		var item = $('[name="checkExaminetype"]:checked').val();
		var textAre = $("#tar").val();
	
		$radioChk = $("input#cet");//获取单选按钮元素
		BootstrapDialog.confirm("确认提交吗?",function(result){
			if(result){
				if($form.validate().element($radioChk)){
			
					var item = $('[name="checkExaminetype"]:checked').val();
					$selectRs = $("select#cemine");// 获取退回原因下拉元素
					$textA = $("textarea#tar");// 获取退回原因其他原因下拉元素
					
					if(item=="1"){// 通过
						$("#cemine").removeAttr("select_required");
						$("#tar").removeAttr("textarea_required");
						$("#tar").removeAttr("required");
						$("#plt").removeAttr("required");
						$bmad = $("input#backMoneyDay");
						if($form.validate().element($bmad)){
							//验证成功
							//异步提交批量回息申请确认
							forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&backMoneyDay="+backMoneyDay;
							loadingMarkShow();
							submits(purl,surl,forms);
						}
						return false;
					}
					if(item=="2"){// 不通过
						$("#plt").removeAttr("required");
						$("#backMoneyDay").removeAttr("required");
						if($form.validate().element($selectRs)){
							var s = $("#cemine").val();
							if(s=="6"){
								if($form.validate().element($textA)){
									forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&textAre="+textAre;
									loadingMarkShow();
									submits(purl,surl,forms);
									
								}
								return false;
							}else{
								//验证成功
								forms = forms +"&checkExaminetype="+item+"&backiId="+backiId+"&checkExamine="+checkExamine;
								loadingMarkShow();
								submits(purl,surl,forms);
							}
						}
						return false;
					}
				}
				return false;
			}
		});
	}else if(s=="3"){// 上传回息金额
		
		var s = $("#fe").val();
		if(s != null && s != ""){
			form = $(".modal-body").find("[id=uploadMoney]");
	    	var formData = new FormData(form[0]);
	    	loadingMarkShow();
	    	upload(formData);
	    	
		}else{
			BootstrapDialog.alert("你没有选择文件！请选择文件！");
		}
	}
}
