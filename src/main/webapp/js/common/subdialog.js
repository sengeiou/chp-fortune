(function($) {
	/**
	 * 子窗口控制(替代window.open)
	 * 
	 * @author 朱杰
	 */
	var SubDialogHandle = {
	
			/**
			 * 子窗口定义
			 *
			 * @param string url URL
			 * @param object argment 参数( 可以是空)
			 * @param function load_callback 加载完后的回调函数
			 * @param function close_callback 关闭弹出窗后的回调函数
			 */
		create: function(url, argment, load_callback, close_callback){
			var sub = {};sub.prop = {};sub.prop.iframe = {};
			sub.prop.argment = argment;
			sub.prop.url = url;
			sub.prop.load_callback = load_callback;
			sub.prop.close_callback = close_callback;
			//弹出框主体ID
			sub.prop.modal_sub_id = "modal-sub";
		    if(argment != null && argment['sub_id'] != null && argment['sub_id'] != ''){
		    	sub.prop.modal_sub_id = argment['sub_id'];
		    }
			
			// 回调函数初始定义，loadSubWindow里可以覆盖
			sub.load_callback = function(argment){};
			// 回调函数初始定义，closeSubWindow里可以覆盖
			sub.close_callback = function(argment){};
			
			/**
			 * 子窗口加载
			 *
			 * @param object argment 参数( 可以是空)
			 * @param function callback 回调函数
			 */
			 sub.loadSubWindow = function() {
				var date = {};
			    if (sub.prop.argment) {
			        // 参数整形
			        $.each(sub.prop.argment, function(key, value) {
			        	if(key =='sub_id'){
			        		return true;
			        	}
			            date[key] = value;
			        });
			    }
			    // URL
			    var url = ctx + sub.prop.url;
			    // 参数拼接
			    var pare = url.split("?");
			    url = pare[0];
			    // url中的参数封装
			    if(pare.length > 1){
			    	pare=pare[1].split("&");
			    	for(var i=0 ;i<pare.length;i++){
				    	var _obj = pare[i].split("=");
				    	date[_obj[0]] = _obj[1];
				    }
			    }
			    
			    // 弹出框主体			    
			    $("#"+sub.prop.modal_sub_id).css('height', '');
			    $modal_body = $('#'+sub.prop.modal_sub_id+' .modal-body');
			    $modal_body.empty().addClass('loading');
			    
			    sub.prop.iframe = $modal_body;
			    
			    contents_getJsonForHtml(url, date, 'post', 
				    function(resultHtml){
				    	//扩展弹出框高度
			    		$modal_body.removeClass('loading');
				    	$("#"+sub.prop.modal_sub_id).animate({height:'90%'},500);
			    		$modal_body.html(resultHtml);
				    	
			    		initAfterLoad();
				    	// 回调函数存在的情况
					    if (typeof sub.prop.load_callback === "function") {
					    	sub.prop.load_callback(sub.prop.iframe);
					    }
				    },
				    function(err){
				    	BootstrapDialog.alert('系统错误');
				    	$('#'+sub.prop.modal_sub_id+' .modal-body').removeClass('loading');				    	
				    },
				    null
			    );
			    
			    $("#"+sub.prop.modal_sub_id).modal({
			        backdrop: "static"
			    });
			    
			    return sub;
			 };
	
			/**
			 * 子窗口关闭
			 *
			 * @param object argment 回调参数
			 */
			sub.closeSubWindow = function(argment){
			    $('#'+sub.prop.modal_sub_id).modal('hide');
			    if (typeof sub.prop.close_callback === "function") {
			    	sub.prop.close_callback(argment,sub.prop.iframe.contents());
			    }			    
			};
	
			/**
			 * 外部直接调用回调函数用
			 *
			 * @param object argment 回调参数
			 */
			sub.callbackInfo = function(argment){
			    // 调用回调函数用
			    sub.callback(argment);
			}
	
			return sub;
		}
	};
	
	SubDialogHandle.init = function() {
        var hasModule = (typeof module !== 'undefined' && module.exports);

        if (hasModule)
            module.exports = SubDialogHandle;
        else if (typeof define === "function" && define.amd)
            define("subdialog", function() {
                return SubDialogHandle;
            });
        else
            window.SubDialogHandle = SubDialogHandle;
    };
    SubDialogHandle.init();
	
})(window.jQuery);
