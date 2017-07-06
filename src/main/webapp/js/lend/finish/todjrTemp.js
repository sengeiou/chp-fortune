var doc_file_count = 0,xls_file_count=0,textFileType='';
function _uploadClick(type,attaId){
	$('#file1').click();
	operationType = type;
	operationAttaId = attaId;
}

function _upload(){
	if(operationType=='save'){
		textFileType = '';
	}
	var $display=$(".ykh").css('display');
	var $fileName=$('#file1').val();
	var $fileType=$fileName.substring($fileName.lastIndexOf('\.')+1);
	if($display=='block'){/*已开户*/
		if((doc_file_count<1 && ($fileType=='doc' || $fileType=='docx')) || (($fileType=='doc'||$fileType=='docx')&&(textFileType=='doc'||textFileType=='docx'))){
			if(textFileType=='doc'||textFileType=='docx'){
				doc_file_count--;
			}else if(textFileType=='xls'||textFileType=='xlsx'){
				xls_file_count--;
			}
			_uploadAjax();
			doc_file_count++;
		}else if((xls_file_count<1 && ($fileType=='xls' || $fileType=='xlsx'))|| (($fileType=='xls'||$fileType=='xlsx')&&(textFileType=='xls'||textFileType=='xlsx'))){
			if(textFileType=='doc'||textFileType=='docx'){
				doc_file_count--;
			}else if(textFileType=='xls'||textFileType=='xlsx'){
				xls_file_count--;
			}
			_uploadAjax();
			xls_file_count++;
		}else{
			BootstrapDialog.alert('只能上传一个doc文档和一个xls文件!');
		}
	}
	if($display=='none'){/*未开户*/
		if((doc_file_count<2 && ($fileType=='doc' || $fileType=='docx'))|| (($fileType=='doc'||$fileType=='docx')&&(textFileType=='doc'||textFileType=='docx'))){
			if(textFileType=='doc'||textFileType=='docx'){
				doc_file_count--;
			}else if(textFileType=='xls'||textFileType=='xlsx'){
				xls_file_count--;
			}
			_uploadAjax();
			doc_file_count++;
		}else if((xls_file_count<1 && ($fileType=='xls' || $fileType=='xlsx'))|| (($fileType=='xls'||$fileType=='xlsx')&&(textFileType=='xls'||textFileType=='xlsx'))){
			if(textFileType=='doc'||textFileType=='docx'){
				doc_file_count--;
			}else if(textFileType=='xls'||textFileType=='xlsx'){
				xls_file_count--;
			}
			_uploadAjax();
			xls_file_count++;
		}else{
			BootstrapDialog.alert('只能上传两个doc文档和一个xls文件!');
		}
	}
}

function _uploadAjax(){
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
			_showButton();
		},
		error : function(data, status, e){
			BootstrapDialog.alert('上传异常!');
		}
	});
}

function _showButton(){
	var $size=$('.upload_file').size();
	var $display=$(".ykh").css('display');
	if(($display=='block' && $size>=2)||($display=='none' && $size>=3)){
		$('#update_button').hide();
	}else{
		$('#update_button').show();
	}
	if($("#update_button").is(":hidden")){
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
				var $parent=$('.'+attaId).parent();
				var $pText=$parent.find('.upload_file_text').text();
				textFileType=$pText.substring($pText.lastIndexOf('\.')+1,$pText.lastIndexOf('》'));
				if(type==null){
					$parent.remove();
					if(textFileType=='doc'||textFileType=='docx'){
						doc_file_count--;
					}else if(textFileType=='xls'||textFileType=='xlsx'){
						xls_file_count--;
					}
				}

				_showButton();
			}
 		}
 	});
}

$(".KHState").click(function(){
	var $selectedvalue = $("input[name='state']:checked").val(); 
	if ($selectedvalue == 1) { 
		$(".ykh").show();
		$(".wkh").hide();
	} 
	else { 
		$(".wkh").show();
		$(".ykh").hide();
	}
	$("#update_div").show();
	_showButton();
});

function downLoadFileKh(){
	var url= ctx+"/common/file/downloaddjr?type=YKH";
	window.location.href = url;
}
function downLoadFileWkh(type){
	var url= ctx+"/common/file/downloaddjr?type=WKH";
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
				parent.$.colorbox.close();
				BootstrapDialog.alert("转投成功");
				window.parent.document.searchForm.submit();
			}else{
				if(data.retMsg!=null){
					BootstrapDialog.alert(data.retMsg);
				}else{
					BootstrapDialog.alert('转投失败，请稍后再试')
				}
			}
 		}
 	});  
}