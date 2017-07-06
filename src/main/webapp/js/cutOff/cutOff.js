    	function deltr(arg){
    		var trId = "tr"+arg;
    		$("#"+trId).remove();
    		var num = $("#storeNumSpn").text();
    		$("#storeNumSpn").text(+num-1);
    	}
    	// 跳转编辑页面
	    function toEditPage(id){
	    	 // 调用子窗口的参数
	    	var url, argment, callback;
	    	url = "/cutOff/goEdit";
	    	argment = {'id':id};
	    	close_callback = function(ifame){};
	    	load_callback = function(iframe){};
	    	var sub = SubDialogHandle.create(url,argment,load_callback,close_callback).loadSubWindow();
	    	//自定义出发关闭回调函数的动作
	    	$('#subClose').off('click').on('click',function(){
	    		sub.closeSubWindow();
	    	}); 
	    }
	    // 删除
 	   function removeCutOff(arg) {
 	    	var delFlag = $(arg).parent("#edit_removeTd").prevAll("#delFlagTd").text();
 	    	var message = "";
 	    	if(delFlag == '启用'){
 	    		message = "你删除的信息是启用状态,你确定删除吗";
 	    	}else {
 	    		message = "你确定删除此信息吗";
				}
 	    	BootstrapDialog.confirm(message,function(result){
 	    		if(result){
 	    			var cutOffId = $(arg).parent("#edit_removeTd").prevAll("#cutOffId").val();
 	    			window.location = ctx+"/cutOff/delete/"+cutOffId;
 	    		}
 	    	});
 	    }
		$(document).ready(function() {
			var iframe = getCurrentIframeContents;
    	    $("#btnSaveSubmit").click(function(){
    	    	var data = new Array();
    	    	var index = 0;
    	    	$("#storeTable tr").each(function (i){
    				if(i>0){
    					data[index++] = $("#storeTable tr:eq("+i+") input[type=hidden]").val();
    				}
                });
    	    	$("#storesIdList").val(data.join(','));
    	    	if($("#storesIdList").val()==""){
    	    		BootstrapDialog.alert("请选择营业部");
    	    		return;
    	    	}
   	    		$("#formStoresMemberSave").submit();
    	    });
    	    
    	    $("#btnBack").click(function(){
    	    	$('#myTabs a:first').tab('show');
    	    });
    	    $("#formStoresMemberSave").validate();
    	    // 主页面弹出门店信息
    	    $("#selectStoresBtn").click(function(){
    	    	top.$.jBox.open("iframe:"+ctx+"/cutOff/selectOrgList", "选择门店", 660, 450, {
    				ajaxData:{},buttons:{"确定":"ok"}, submit:function(v, h, f){
    					if (v=="ok"){
    						var stores_selected = $("#storesSelected",h.find("iframe")[0].contentDocument).val();
    						var stores = $.parseJSON(stores_selected);
    						$.each(stores,function(i,n){
    							var orgId = n.id;
   								var orgName = n.sname;
    							if(""!= orgId && !$("#tr"+orgId)[0]){
    								$("#storeTable").append("<tr id=\"tr"+orgId+"\"><td>"+orgName+"<input type=\"hidden\" value=\""+orgId+"\"</td></td><td><button onclick='deltr("+orgId+");' type=\"button\" class=\"cf_btn_edit\">删除</button></td></tr>");
    							}
    						});
    						$("#storeNumSpn").html($("#storeTable tr").length-1);
    					}
    				}, loaded:function(h){
    					$(".jbox-content", top.document).css("overflow-y","hidden");
    				}
    			});
    		});
    	    var iframe = getCurrentIframeContents;
    	    // 子页面弹出门店信息
    	    $("#selectStoresBtnSub",iframe).click(function(){
    	    	top.$.jBox.open("iframe:${ctx}/cutOff/selectOrgList", "选择门店", 660, 450, {
    				ajaxData:{},buttons:{"确定":"ok"}, submit:function(v, h, f){
    					if (v=="ok"){
    						var stores_selected = $("#storesSelected",h.find("iframe")[0].contentDocument).val();
    						var stores = $.parseJSON(stores_selected);
    						$.each(stores,function(i,n){
    							var orgId = n.id;
   								var orgName = n.sname;
    							if(""!= orgId && !$("#tr"+orgId)[0]){
    								$("#storeTable").append("<tr id=\"tr"+orgId+"\"><td>"+orgName+"<input type=\"hidden\" value=\""+orgId+"\"</td></td><td><button onclick='deltr("+orgId+");' type=\"button\" class=\"cf_btn_edit\">删除</button></td></tr>");
    							}
    						});
    						$("#storeNumSpn").html($("#storeTable tr").length-1);
    					}
    				}, loaded:function(h){
    					$(".jbox-content", top.document).css("overflow-y","hidden");
    				}
    			});
    		});
    	    
    	    // 批量修改截单时间
    	    $("#editButton").click(function(){
    	    	var array = new Array();
    	    	var index = 0;
    	    	$("#checkboxId:checked").each(function(){
    	    		array[index++] = $(this).val();
    			});
    	    	if(array.length==0){
    	    		BootstrapDialog.alert('请选择数据后再修改');
    	    		return;
    	    	}
    	    	BootstrapDialog.confirm_my = new BootstrapDialog({
		            title: '批量修改截单时间',
		            message: "<form id='dateForm'><input id='endTimeBoot' name = 'endTime' type='text' readonly='readonly' required class='cf_input_text178 Wdate required' onclick=\"WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});\"/></form>",
		            closable: false,
		            data: {},
		            buttons:[{
		                cssClass: 'cf_btn-primary',
		                label: '提交',
		                action: function(dialogItSelf) {
		                	if(!$("#dateForm").validate().form()){
		            			//验证不成功
		            			return false;
		            	    }
		                	dialogItSelf.close();
		                	$("#dateForm").attr("action",ctx+"/cutOff/edit");
		                	$("#dateForm").append("<input name='array' type='hidden' value="+array+">");
		                	$("#dateForm").submit();
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
    	    });
    	    
    	    // 停用
    	    $("#delFlag_cease").click(function(){
    	    	var array = new Array();
    	    	var index = 0;
    	    	var flag = false;
    	    	 $("#checkboxId:checked").each(function(){
    	    		array[index++] = $(this).val();
    	    		var delFlag = $(this).parent("#checkboxTd").nextAll("#delFlagTd").text();
    	    		if(delFlag == "停用"){
    	    			flag = true;
        	    		return false;
    	    		}
    			});
    	    	if(array.length==0){
    	    		BootstrapDialog.alert('请选择数据后再修改');
    	    		return;
    	    	}
    	    	if(flag){
    	    		BootstrapDialog.alert('你选中的数据中存在已停用的数据,请核对后再操作');
    	    		return false;
    	    	}
    	    	$("#home").append("<form id='delFlagForm'></form>");
    	    	$("#delFlagForm").attr("action",ctx+"/cutOff/edit");
            	$("#delFlagForm").append("<input name='array' type='hidden' value="+array+">");
            	$("#delFlagForm").append("<input name='delFlag' type='hidden' value='0'>");
            	$("#delFlagForm").submit();
    	    });
    	    
    	    // 启用
    	    $("#delFlag_start").click(function(){
    	    	var array = new Array();
    	    	var index = 0;
    	    	var flag = false;
    	    	$("#checkboxId:checked").each(function(){
    	    		array[index++] = $(this).val();
    	    		var delFlag = $(this).parent("#checkboxTd").nextAll("#delFlagTd").text();
    	    		if(delFlag == "启用"){
    	    			flag = true;
        	    		return false;
    	    		}
    			});
    	    	if(array.length==0){
    	    		BootstrapDialog.alert('请选择数据后再修改');
    	    		return;
    	    	}
    	    	if(flag){
    	    		BootstrapDialog.alert('你选中的数据中存在已启用的数据,请核对后再操作');
    	    		return false;
    	    	}
    	    	$("#home").append("<form id='delFlagForm'></form>");
    	    	$("#delFlagForm").attr("action",ctx+"/cutOff/edit");
            	$("#delFlagForm").append("<input name='array' type='hidden' value="+array+">");
            	$("#delFlagForm").append("<input name='delFlag' type='hidden' value='1'>");
            	$("#delFlagForm").submit();
    	    });
    	    
    	    // 批量删除
    	    $("#batchRemoveButton").click(function(){
    	    	var batchCutOffId="";
    	    	var index = 0;
    	    	var flag = false;
    	    	$("#checkboxId:checked").each(function(){
    	    		batchCutOffId = $(this).val()+","+batchCutOffId;
    	    		var delFlag = $(this).parent("#checkboxTd").nextAll("#delFlagTd").text();
    	    		if(delFlag == "启用"){
    	    			flag = true;
        	    		return false;
    	    		}
    			});
    	    	if(batchCutOffId==""){
    	    		BootstrapDialog.alert('请选择数据后再进行删除');
    	    		return;
    	    	}
    	    	if(flag){
    	    		BootstrapDialog.alert('你选中的数据中存在启用的数据,请核对后再操作');
    	    		return false;
    	    	}
    	    	BootstrapDialog.confirm("你确定删除选中的数据吗",function(result){
     	    		if(result){
     	    			window.location = ctx+"/cutOff/delete/"+batchCutOffId;
     	    		}
     	    	});
    	    });
		});
