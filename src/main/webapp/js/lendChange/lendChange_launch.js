$(function(){
	
	/**
	 * 获得页面的iframe
	 */
	var iframe = getCurrentIframeContents();
	//省市区联动绑定
	fortune.initCity(iframe,'addrProvince','addrCity','addrDistrict');
	fortune.fyInitCity(iframe,'provinceItem','addrCityItem');
	$('#btnSubmit').click(function(){
		var f=false;
		/*$.each($("input[name='addAttachmentId']"), function(n,p) {
			var v=$(p).val();
			$.each($("input[name='deleteAttachmentId']"), function(n1,p1) {
				
				if(v==$(p1).val()){
					v='';
				}
			})
			 if(v!=''){
			    f=true;
			    return false;	
			   }			
		});	
		if(!f){
			BootstrapDialog.alert('附件不能为空');
			return false;
		}*/
		$.each($("a[class='dz-ex-download']"), function(n,p) {
			
			if($(p).html()!=""){
				f=true;
			}
		});
		if(!f){
			BootstrapDialog.alert('请上传附件');
			return false;
		}
		
		$form = $('#searchForm',iframe);
		if(!$form.validate().form()){
			//验证不成功
			return false;
		}else{
			// 截单处理
			contents_getJsonForSync(
				ctx+"/cutOff/ajaxCutoffInfo",
				{'applyDeductDay':$("#deductDate").val()},
				'post',
				function(result){
					if(result!=""){
						BootstrapDialog.alert(result);
						return; 
					}
				} 
			);
			
			var msg="";
			if($("#applyPay").val()=='4'){
				msg='资金托管下的出借申请绑定于此的银行信息也同时改变！'
			}
			
			var branch = $("input[name='countAfter.accountBranch']").val();
			if(branch.indexOf("私人银行")>=0){
				return true;
			}else if(branch.indexOf("银行")>=0){
				BootstrapDialog.alert('具体支行除私人银行外均不可使用‘银行’字段！');
				return false;
			}
			
			var branchCode = $("#branchCode").val();
			if(branchCode==null || branchCode==''){
				BootstrapDialog.alert('具体支行信息未选择！');
				return;
			}
			
			BootstrapDialog.confirm(msg+'确定提交吗？', function(result){
	            if(result){
	            	 var url=ctx+"/lendChange/launchFlow";
	            	if($("#pageType").val()=='lendChangeBackForm'){
	            		url=ctx+"/lendChangeReject/newLaunchFlow";
	            	}
    			     var $tempForm =$('#searchForm');
    			     $tempForm.attr('action',url).attr('target','');
    			     $tempForm.off("submit"); 
    			     $tempForm.submit();
	            	BootstrapDialog.alert('提交成功');
	            	
	            }else{
	            }
	            return;
	        });
		}
	});
	
	
});

//卡联 银行编号枚举
var kalianBankCodeObj = {};

//回款银行 开户行 变更事件
function changeReyBank(bank){
	//console.log("变更前："+bank);
	//console.log(kalianBankCodeObj);
	var val_bankCode = $("#branchCode").val();
	if(kalianBankCodeObj[bank] != undefined && kalianBankCodeObj[bank] != ''){
		val_bankCode = kalianBankCodeObj[bank];
	}
	//console.log("变更后："+val_bankCode);
	$("#branchCode").val(val_bankCode);
}

function openBranchCode(src){
	iframe = getCurrentIframeContents();
	
	$(".modal-title",iframe).html("选择支行代码");
	// 调用子窗口的参数
	var url, argment, callback;
	url = "/lend/lendApplyAid/openBranchCodeDiv";
	bankNameV = '';
	branchNameV = $(src).parent().find("input").val();
	provinceV = '';
	cityV = '';
	districtV = '';
	argment = {bankName:bankNameV,branchName:branchNameV,province:provinceV,city:cityV,district:districtV};
	load_callback = function(arg){
	};
	close_callback = function(arg){
	};
    var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
    // 关闭事件
    $('#subClose').off('click').on('click',function(){sub.closeSubWindow();});
    // 待回事件
    $('#selectedBranchCode').off('click').on('click',function(){
    	// 支行代码
    	var val_bankCode = $('input[name="bankCode"]:checked').val();
    	// 支行名称
    	var val_accountBranch = $('input[name="bankCode"]:checked').parent().parent().find("td:eq(2)").text();
    	if(val_bankCode == null || val_bankCode==''){
    		BootstrapDialog.alert("请选择对应的支行信息");
    		return;
    	}else {
    		// 支行代码存入隐藏域
    		$("#branchCode").val(val_bankCode);
    		// 支行名称更新页面显示
//    		$("input[name='countAfter.accountBranch']").val(val_accountBranch);
    		sub.closeSubWindow();
		}
    	
    });
}

function branchBankCodePaging(n,s){
	if (n)
		$("#pageNo",$("#branchCodePagingForm")).val(n);
	if (s)
		$("#pageSize",$("#branchCodePagingForm")).val(s);
	data = $("#branchCodePagingForm").serialize();
	contents_getJsonForHtml(ctx+"/lend/lendApplyAid/openBranchCodeSubList", data, "post", function(html){
		$(".openBranchCodeDiv").html(html);
	},null,null);
	return false;
}
/**
 * 分页
 * @param n
 * @param s
 * @returns {Boolean}
 */
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}


/**
 * 跳转页面
 * 
 * @param pageURL
 */
function goPage(pageURL) {
	window.location = pageURL;
}

/**
 * 查看变更历史
 * 
 * @param applyId
 */
function openHistoryDetails(applyId) {
	var url="/lendChange/openLendChangeHistoryDetail";
	var arg={'applyId':applyId};
	var sub = SubDialogHandle.create(url,arg,null,null).loadSubWindow();
	$('#subClose').off('click').on('click',function(){sub.closeSubWindow();});

}

/**
 * 页面伸缩
 */
function show() {
	if (document.getElementById("T1").style.display == 'none') {
		document.getElementById("showMore").className = 'xialadown';
		document.getElementById("T1").style.display = '';
		document.getElementById("T2").style.display = '';
	} else {
		document.getElementById("showMore").className = 'xiala';
		document.getElementById("T1").style.display = 'none';
		document.getElementById("T2").style.display = 'none';
	}
}
/**
 * 跳转到变更历史列表
 * @param applyCode
 */
function goLendChangeHistory(applyCode) {
	
	var url=ctx+"/lendChange/goLendChangeHistory?applyCode="+ applyCode;
	
	window.location = url;
}

/**
 * 清空筛选条件(除去下拉框或者机构)
 */
function clearInput(){
	  $("input[type='text']").val(null);
}
/**
 * 下载模板
 * @param custCode
 */
function templates(custCode){
	var url=ctx+"/lendChange/exportExcel?custCode=" + custCode;
	window.location = url;
}

/**
 * 验证具体支行
 * @param custCode
 */
function checkbranch(){
	var branch = $("input[name='countAfter.accountBranch']").val();
	if(branch.indexOf("私人银行")>=0){
		return true;
	}else if(branch.indexOf("银行")>=0){
		BootstrapDialog.alert('具体支行除私人银行外均不可使用‘银行’字段！');
		return false;
	}
}

function sqsPreview()     

{       
 
   var url=ctx+"/lendChange/sqsPreview";
   var $tempForm =$('#searchForm');
   $tempForm.attr('action',url).attr('target',"ff");
   $tempForm.on("submit",function(){ openWindow(); });  
   $tempForm.submit();     
   
}     
  
 
function openWindow()       
  
{       
  
	window.open('about:blank','ff','height=500,width=550,toolbar=yes,resizable=yes,scrollbars=yes')
  
}       


