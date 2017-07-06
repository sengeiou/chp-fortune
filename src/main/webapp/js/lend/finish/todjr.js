var file_count = 0;
function _uploadClick(type,attaId){
	$('#file1').click();
	operationType = type;
	operationAttaId = attaId;
}

function _upload(){
	var val= $("#file1").val();
	var k = val.substr(val.lastIndexOf(".")+1);
	if(k!='pdf' && k!='PDF'){
		BootstrapDialog.alert('只能上传pdf文件!');
	}else{
		$.ajaxFileUpload({
			url : ctx+'/common/file/uploadAjax',
			secureuri : false,
			data : {'loanCode':$('#lendCode').val(),'attaFileOwner':'to_djr'},
			fileElementId : 'file1',
			dataType : 'json',
			success : function(data, status){
					var str = '';
						if(operationType=='save'){
							str += '<p class="upload_file">';
						}
						str += '<span class="upload_file_text">《'+data.attaFilename+'》</span>&nbsp;&nbsp;&nbsp;';
						str += '<a class="'+data.attaId+'" href="javascript:_resetUpload(\''+data.attaId+'\');">重新上传</a>&nbsp;&nbsp;';
						str += "<a class='"+data.attaId+"' href='javascript:_remove(\""+data.attaId+"\");'>删除</a>";
						if(operationType=='save'){
							str += '</p>';
						}
						var content = $(str);
				if(operationType=='save'){
					$('#file_upload_div').append(content);
				}else if(operationType=='edit'){
					$('.'+operationAttaId).parent().html(content)
				}
				file_count++;
				_showButton();
			},
			error : function(data, status, e){
				BootstrapDialog.alert('上传异常!');
			}
		});
	}
}

function _showButton(){
	var $size=$('.upload_file').size();
	if($size>=5){
		$('#update_button').hide();
	}else{
		$('#update_button').show();
	}
	if($size>=1){
		$('#save_button').show();
	}else{
		$('#save_button').hide();
	}
}

function _resetUpload(attaId){
	_remove(attaId,'edit');
	_uploadClick('edit',attaId);
}

function _remove(attaId,type){
	$.ajax({
		async : false,
		type : "POST",
		url : ctx+'/common/file/removeAjax',
		data : "&attaId="+attaId,
		dataType : 'json',
		success: function(data,status){
			if(data=="success"){
				if(type==null){
					$('.'+attaId).parent().remove();
				}
				file_count--;

				_showButton();
			}
 		}
 	});
}

function downLoadFile(){
	var url= ctx+"/common/file/downloaddjr";
	window.location.href = url;
}

function todirSubmit(){
	$('#save_button').hide();
	var lendCode = $("#lendCode").val();
	$.ajax({
		type : "POST",
		url: ctx+'/lend/finish/todjrsubmit', 
		data : "&lendCode="+lendCode,
		dataType : 'json',
		success: function(data,status){
			if(data==null){
				BootstrapDialog.alert("查询不到需要转赠数据");
			}else if(data.retCode=='0000'){
				BootstrapDialog.show({
					title : '通知',
					message : '转投成功',
					closable : false,
					buttons : [ {
						label : '确定',
						action : function(dialog) {
							parent.$.colorbox.close();
							window.parent.document.searchForm.submit();
						}
					}]
				});
			}else{
				$('#save_button').show();
				if(data.retMsg!=null){
					BootstrapDialog.alert(data.retMsg);
				}else{
					BootstrapDialog.alert('转投失败，请稍后再试')
				}
			}
 		}
 	});  
}