jQuery(document).ready(function($){
	initAfterLoad();
});

/**
 * 检索条件隐藏和显示
 */
function show(){
	if( $("#T1").is(':hidden')){		
		$("#showMore").addClass("xialadown").removeClass("xiala");
		$("#T1,#T2,#T3,#T4,#T5").show(500);
	}else{		
		$("#showMore").addClass("xiala").removeClass("xialadown");
		$("#T1,#T2,#T3,#T4,#T5").hide(500);
	}
}

/**
 * loading浮层显示，画面不可操作
 */
function loadingMarkShow(unix){
	if(unix!=null){
		loadingMarkShow.id=unix;
	}
    // 遮盖层    
    loadingMarkShow.protectDiv = $('<div></div>').prependTo('body').css({
        'position'        : 'fixed',
        'top'             : '0px',
        'bottom'          : '0px',
        'right'           : '0px',
        'left'            : '0px',
        'zIndex'          : 0x7FFFFFFE,
        'backgroundColor' : 'black',
        'margin'          : '0px',
        'border'          : '0px none',
        'padding'         : '0px'
    }).fadeTo(0,0.3);

    // loading层
    gif=ctxWebInf+"/img/loading1.gif";
    loadingMarkShow.loadingDiv =
        $('<div id="loading-wait-mark"><div id="zhezhao"><img src='+gif+'></div></div>');
    loadingMarkShow.loadingDiv.prependTo('body').attr('id','loading-wait').css({
        'position' : 'fixed',
        'top'      :
            ($(window).height() - loadingMarkShow.loadingDiv.height()) / 2,
        'left'     :
            ($(window).width() - loadingMarkShow.loadingDiv.width()) / 2,
        'zIndex'   : 0x7FFFFFFF
    });

    return(true);
}

/**
 * loading隐藏，画面可操作
 */
function loadingMarkHide(unix){
	if(unix!=null && loadingMarkShow.id != unix){
		return;
	}
    // loading层删除
    loadingMarkShow.loadingDiv.remove();

    // 遮盖层删除
    loadingMarkShow.protectDiv.remove();

    return(true);
}

/**
 * 页面跳转
 * @param n 第几页
 * @param s 每页显示条数
 * @returns {Boolean}
 */
function page(n, s) {
	if (n)
		$("#pageNo").val(n);
	if (s)
		$("#pageSize").val(s);
	$("#searchForm").submit();
	loadingMarkShow();
	return false;
}

function goPage(pageURL){
	window.location.href = pageURL;
}

/**
 * 重置PageNo
 */
function resetPage(){
	$("#pageNo").val("");
}

/**
 * 页面加载后注册的脚本事件
 * 1.全选 2.清空检索条件 3.select样式以及校验
 */
function initAfterLoad(){
	/**
	 * select2样式
	 */
	$.extend($.fn.selectpicker.defaults,{
		style: 'bootstrap-select',
		noneSelectedText : '请选择',
        noneResultsText : '没有匹配的项',
	});
	/**
	 * select校验
	 */
	$('select:visible').on('change',function(){
		if($(this).hasClass('error') || $(this).hasClass('valid')){
			var $form = $(this).closest('form');
			$form.validate().element(this);
		}		
	});
	$('select:not(#template select):not(#template)')
		.attr('data-actions-box',true)
		.attr('data-live-search',true)
		.selectpicker({
			selectAllText: '全选',
		    deselectAllText: '全取消'
		});
	
	/**
	 * 表头的checkbox全选/全不选
	 * 规范：<th><input type="checkbox" class="checkAll">全选</th>
	 */
	$('table th input[type="checkbox"].checkAll').click(function(){
		//表对象
		$table = $(this).closest('table');
		if($table.is('div.fixed-table-header table')){
			// 使用bootstrap-table的情况下
			$table = $table.closest('.fixed-table-container').find('.fixed-table-body table');
		}
		
		//th对象
		$th = $(this).closest('th');
		//表的列index
		var columnIndex = $th.index();
		
		if($(this).is(':checked')){
			//全选
			$table.find('tr').each(function(){
				$(this).find('td:eq(' + columnIndex + ') [type="checkbox"]').prop( "checked", true );
			});
		}else{
			//全不选
			$table.find('tr').each(function(){
				$(this).find('td:eq(' + columnIndex + ') [type="checkbox"]').prop( "checked", false );
			});
		}
	});
	
	/**
	 * 清空检索条件，以下条件忽略
	 * 包含class【noClear】
	 */
	$('input[type=reset]').off('click').on('click',function(){
		$form = $(this).closest("form");
		$form.find('input:not([type=button],[type=submit], [type=reset]):not(.noClear)').val('');
		$form.find('select:not([disabled])').selectpicker('deselectAll');
		return false;
	});
	
	/**
	 * 表头固定
	 */
//	if($('.strap-table').length > 0){
//		$('.strap-table').bootstrapTable({
//			height: document.body.clientHeight - $('.strap-table').offset().top - $('.pagination').outerHeight() - 22,
//	        formatNoMatches: function(){
//	        	return '无符合条件的记录';
//	        }
//		});
//	}
	
	$(".Wdate").attr("readonly","readonly");
	
	/**
	 * 检索设置pageNo为1
	 */
	$('input[value="搜索"],button:contains("搜索")').on('click',function(){
		$('#pageNo').val('1');
	});
}
