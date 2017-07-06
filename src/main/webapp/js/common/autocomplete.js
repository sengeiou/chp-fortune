/**
 * 智能检索，输入框更改内容或者双击
 * @param elem 输入框
 * @param source_function 数据源取得函数
 * @param select_function 选择后回调函数
 * 
 * @author 朱杰
 */
function autocompleteSearch(elem,source_function,select_function){
	if($(elem).length == 0 || 
		(source_function != null && typeof source_function != 'function') ||
		(select_function != null && typeof select_function != 'function')
		){
		return ;
	}
	$(elem).autocomplete({
        minLength:0,//最少多少字之后开始检索
        delay:1000,
        autoFocus:true,
        /**
         * 数据源取得
         * @param request 入力文字
         * @param response
         */
        source: function (request, response) {
        	$(elem).addClass("input-loading");
        	source_function(request, response, elem);
        },
        /**
         * 数据源取得前
         */
        response: function(event, ui) {
            // loading取消
            $(elem).removeClass("input-loading");
        },
        /**
         * 选择回调函数
         * @param event 事件
         * @param ui 选择项
         */
        select: function(event, ui) {
        	select_function(event, ui, elem);
        }
    });
	$(elem).on("dblclick", function() {
        $(this).autocomplete('search');
    });
}