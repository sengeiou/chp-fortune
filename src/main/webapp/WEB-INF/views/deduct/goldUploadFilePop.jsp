<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
	$(function(){
		$('[name="file"]').change(function(){
            var file = this.files[0];
            var name = file.name;
            var size = file.size;
            var type = file.type;
            //Your validation
            ifExcel(name);
        });
        $('#button1').click(function(){
        	var filename = $("input[name='file']").val();
        	if(filename ==null || filename ==''){
	    		BootstrapDialog.alert("请选择文件！");
				return;
        	}
        	if(!ifExcel(filename)){
        		return ;
        	}
       		 var formData = new FormData($('form')[1]);
       		 contents_getJsonForFileUpload(this,ctx+'/goldAccoun/importExcel',formData, 
    				 function(result){
       					 BootstrapDialog.alert(result);
                    	 sub.closeSubWindow();
                     },
                     function(){
                    	 BootstrapDialog.alert("上传失败");
                    	 sub.closeSubWindow();
                     },true);
        });		
	})
	
		/**
	 * 文件名验证
	 * @param filename
	 * @returns {Boolean}
	 */
	function ifExcel(filename){
		var suffix = filename.substr(filename.lastIndexOf(".")).toLowerCase();//获得文件后缀名
		
		if (suffix != '.xls' && suffix !='.xlsx') {
			BootstrapDialog.alert("文件格式错误，请上传Excel文件");
			return false;
		}
		return true;
	}
</script>
<div>
	<form id="offLineForm"  method="post" >
		<table id="offLineTable" class='table1'>
			<tr>
				<td>
					<label class='lab'>选择文件：</label>
					<input name="file" type="file" value="浏览">	
					<progress style="display:none"></progress>
					<input type="button" id="button1" value="上传"/>
	             	<input type="reset" value="取消"/>
				</td>
			</tr>
		</table>
		
	</form>
	 <sys:message content="${message}"/>
</div>

