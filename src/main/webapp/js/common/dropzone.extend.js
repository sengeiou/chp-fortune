/**
 * 附件操作[dropzone]扩展
 * 
 * @author 朱杰
 */
(function($) {
	
	var DropzoneHandle = {
		/**
		 * 初始化dropzone
		 * @param selector jquery选择 例如#id 或者 .class
		 * @param mockfileArr array 显示已上传服务器的文件 [{id:文件名,name:显示的文件名,size:文件大小}]
		 * @param options 扩展或者覆盖用，例如 options={url: 'XXX'},则url会替换成options中的内容
		 * 
		 * @author 朱杰
		 */
		initDropZone :function (selector,mockfileArr,options){
			Dropzone.autoDiscover = false;
			Dropzone.confirm = function(question, accepted, rejected) {
				BootstrapDialog.confirm(question, function(result){
	                 if(result){
	                	 return accepted();
	                 }else if (rejected != null) {
	                     return rejected();
	                 }
			  });
			};
			if(options == null || typeof options != 'object'){
				options = {};
			}
			var myDropzone = new Dropzone(selector,$.extend({
				getFileBase:ctx+'/common/file/download/',
				url: ctx+'/common/file/uploadAjax',
				method: "post",
				withCredentials: false,
				parallelUploads: 2,
				uploadMultiple: false,
				paramName: "file",//上传的文件以什么属性获取
				maxFilesize:10,// MB单位，最大上传文件的大小
				createImageThumbnails: true,
				maxThumbnailFilesize: 10,
				thumbnailWidth: 120,//宽
				thumbnailHeight: 120,//高
				filesizeBase: 1000,
				maxFiles: null,
				params: {},
				clickable: true,
				ignoreHiddenFiles: true,
				acceptedMimeTypes: null,
				autoProcessQueue: true,
				autoQueue: true,
				addRemoveLinks: true,
				previewsContainer: '.dropzone-previews',//预览的div
				hiddenInputContainer: "body",
				capture: null,
				addRemoveLinks: true,
				dictRemoveLinks: "x",
				dictCancelUpload: "x",
				createImageThumbnails: true,
				addExPreviewLinks: true,
				addExDownloadLinks: true,
				dictDefaultMessage: "",
				dictFallbackMessage: "你的浏览器不支持拖入文件",
				dictFileTooBig: "该文件太大 ({{filesize}}M). 最大支持: {{maxFilesize}}M.",
				dictInvalidFileType: "你不能上传该类型文件",
				dictCancelUpload: "取消上传",
				dictRemoveFile: "移除",
				dictRemoveFileConfirmation: "确定删除？",
				dictMaxFilesExceeded: "你不能再上传更多文件",
				dictExPreviewFile:"预览",
				dictExDownloadFile:"下载",
				
				init: function() {
					//上传成功后事件
			        this.on("success", function(file, result) {
			        	try{
			        		//保存上传的附件ID
			        		var attachment = JSON.parse(result);
			        		file.id = attachment.attaId;
			        		$hid = $('<input type="hidden" value="'+file.id+'" name="addAttachmentId"/>')
			        		$('.dropzone_result').append($hid);
			        		//扩展_预览
			                if (this.options.addExPreviewLinks) {
			                	if(file.name.toLowerCase().match(/\.(jpg|jpeg|png|gif|pdf|html)$/) ){
				                	file._exPreviewLink = Dropzone.createElement("<a class=\"dz-ex-preview\" href=\"javascript:void(window.open('"+this.options.getFileBase+file.id+"/preview'),'preview');\">" + this.options.dictExPreviewFile + "</a>");
				      	          	file.previewElement.appendChild(file._exPreviewLink);
			                	}
			      	      	}
			                //扩展_下载
			                if (this.options.addExDownloadLinks) {
			      	          	file._exDownloadLink = Dropzone.createElement("<a class=\"dz-ex-download\" href=\"javascript:void(window.open('"+this.options.getFileBase+file.id+"/download'),'download');\">" + this.options.dictExDownloadFile + "</a>");
			      	          	file.previewElement.appendChild(file._exDownloadLink);
			      	      	}
			        	}catch (e){}
			        });
			        this.on("removedfile", function(file) {
			        	$hid = $('<input type="hidden" value="'+file.id+'" name="deleteAttachmentId"/>')
		        		$('.dropzone_result').append($hid);
			        });
			        this.on("addedfile", function(file) {
			        	if(file.status == null || file.status == ''){
			        		//已经上传到服务器的文件
			        		//扩展_预览
			                if (this.options.addExPreviewLinks) {
			                	if(file.name.toLowerCase().match(/\.(jpg|jpeg|png|gif|pdf|html)$/) ){
			                		if(this.options.hideDrop){
			                			//审批
			                			 var url=ctx+"/common/file/preview?attaId="+file.id;
			                			 
			                			 file._exPreviewLink = Dropzone.createElement("<a class=\"dz-ex-preview\" href=\"javascript:void(window.open('"+url +"','_blank','预览','height=500,width=550,toolbar=yes,resizable=yes,scrollbars=yes'),'preview');\">" + this.options.dictExPreviewFile + "</a>");
			                		}else{
			                			//申请
			                			file._exPreviewLink = Dropzone.createElement("<a class=\"dz-ex-preview\" href=\"javascript:void(window.open('"+this.options.getFileBase+file.id+"/preview'),'preview');\">" + this.options.dictExPreviewFile + "</a>");
			                			
			                		}
			                		file.previewElement.appendChild(file._exPreviewLink);
								}
			      	      	}
			                //扩展_下载
			                if (this.options.addExDownloadLinks) {
			      	          	file._exDownloadLink = Dropzone.createElement("<a class=\"dz-ex-download\" href=\"javascript:void(window.open('"+this.options.getFileBase+file.id+"/download'),'download');\">" + this.options.dictExDownloadFile + "</a>");
			      	          	file.previewElement.appendChild(file._exDownloadLink);
			      	      	}
			        	}
			        	
			        });
			    }
			},options,false));
			
			if($.isArray(mockfileArr)){
				$.each(mockfileArr,function(i,file){
					if(file != null && file.attaId != null){
						file.id=file.attaId;
						file.name=file.attaFilename;
						file.path=file.attaFilepath;
						file.accepted=true;
						myDropzone.files.push(file);
						myDropzone.emit("addedfile", file);
//						if(file.name.toLowerCase().match(/\.(jpg|jpeg|png|gif)$/) ){
//							myDropzone.emit("thumbnail", file, ctxWebInf+'/'+file.path);
//						}
						myDropzone.emit("complete", file);
					}
				});
			}
			//禁止上传文件
			if(myDropzone.options.hideDrop){
				$(selector).remove();
			}
			return myDropzone;
		}
	}
	DropzoneHandle.init = function() {
        var hasModule = (typeof module !== 'undefined' && module.exports);

        if (hasModule)
            module.exports = DropzoneHandle;
        else if (typeof define === "function" && define.amd)
            define("dropzone", function() {
                return DropzoneHandle;
            });
        else
            window.DropzoneHandle = DropzoneHandle;
    };
    DropzoneHandle.init();
}(jQuery));
