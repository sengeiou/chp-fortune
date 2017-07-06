jQuery(document).ready(function($){
	// 表单提交校验
	$("input[type='submit']").click(function() {
		$form = $('#searchForm', getCurrentIframeContents());
		if ($form.length > 0 && !$form.validate().form()) {
			// 验证不成功
			return false;
		}
		$(this).closest('form').submit();
		return false;
	});
})