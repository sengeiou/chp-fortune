<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script>
	function show1(){
			$("#T11").removeAttr("style");
			$("#T22").attr("style","display:none;");
	}
	function hide1(){
			$("#T11").attr("style","display:none;");
			$("#T22").removeAttr("style");
	}
	
	$(function(){
		$("input[name='tp']").click(function(){
			var $obj=$(this);
			$("#button1").attr("value",$obj.attr("buttonName"));
			if($obj.val()=='0'){
				$("#offLineForm").attr("action","${ctx}/theDayDeduct/exportOffLineDeduct").attr("enctype","");
			}else{
				$("#offLineForm").attr("action","${ctx}/theDayDeduct/importOffLineDeduct").attr("enctype","multipart/form-data");
			}
		})
		
		$('[name="file"]').change(function(){
            var file = this.files[0];
            var name = file.name;
            var size = file.size;
            var type = file.type;
            ifExcel(name);
        });
        $('#button1').click(function(){
        	var filename = $("input[name='file']").val();
        	var obj = $("input[name='tp']:checked").val();
			$("#lendCodes").val(getIds());
        	if(obj == '1'){
            	if(filename ==null || filename ==''){
    	    		BootstrapDialog.alert("请选择文件！");
    				return;
            	}
            	if(!ifExcel(filename)){
            		return ;
            	}
        		 var formData = new FormData($('form')[1]);
                 $.ajax({
                     url: ctx+'/theDayDeduct/importOffLineDeduct',
                     type: 'POST',
                     xhr: function() {  // Custom XMLHttpRequest
                         var myXhr = $.ajaxSettings.xhr();
                         if(myXhr.upload){ // Check if upload property exists
                             myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // For handling the progress of the upload
                         }
                         return myXhr;
                     },
                     //Ajax events
                     success: function(result){
                    	 alert(result)
                     },
                     error: function(){
                    	 alert("上传失败")
                     },
                     // Form data
                     data: formData,
                     //Options to tell jQuery not to process data or worry about content-type.
                     cache: false,
                     contentType: false,
                     processData: false
                 });
        	}else if(obj == '0'){
        		$("#offLineForm").submit();
        	}else {
        		BootstrapDialog.alert('没有选择要做的操作');
        	}
           
        });
        function progressHandlingFunction(e){
            if(e.lengthComputable){
                $('progress').show().attr({value:e.loaded,max:e.total});
            }
        }
		
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
		<input type="hidden" value="${lendCodes}" name="lendCodes">
		<table id="offLineTable" class='table1'>
			<tr>
				<td><label class='lab'>操作方式：</label> 
					<input buttonName="下载" type="radio" name="tp" value="0"	onclick="javascript:hide1();">导出&nbsp;&nbsp;&nbsp; 
					<input buttonName="上传" type="radio"  name="tp" value="1" onclick="javascript:show1();">上传&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>划扣平台：</label> 
					<select name="exportPtId" class="cf_input_text178">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('tz_deduct_plat')}" var="d">
							<option value="${d.value}">${d.label}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class='lab'>文件格式：</label> 
					<select name="exportType" class="cf_input_text178">
						<option value="">请选择</option>
						<option value="0">Excel</option>
						<option value="1">txt</option>
					</select>
				</td>
			</tr>
			<tr id="T11">
				<td>
					<label class='lab'>选择文件：</label>
					<input name="file" type="file" value="浏览">	
					<progress style="display:none"></progress>
				</td>
			</tr>
		</table>
		<div class='tright pr30'>
		<input type="button" id="button1" value="上传"/>
		<input type="reset" value="取消"/>
		</div>
	</form>
	 <sys:message content="${message}"/>
</div>

