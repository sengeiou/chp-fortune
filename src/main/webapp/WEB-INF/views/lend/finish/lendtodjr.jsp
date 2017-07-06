<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>转投大金融</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxWebInf}/js/common/subdialog.js"></script>
<script src="${ctxWebInf}/js/common/autocomplete.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.min.js" ></script> 
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/colorbox.css" />
<link rel="stylesheet" type="text/css" href="${ctxWebInf}/css/pop.css" />
<script type="text/javascript" src="${ctxWebInf}/js/common/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/ajaxfileupload.js"></script>
<style type="text/css">
*{
	font-size: 14px;
}
</style>
</head>
<body>
<form id="lenderinfo" action="${ctx}/lend/finish/todjrsubmit?lendCode=${lendCode}" method="post">
<input type="hidden" id="lendCode" name="lendCode" value="${lendCode}" />
<div class="X_layer">
	<div class="X_layer_content"><span>通知</span></div>
	<div class="X_input">
		<div class="X_box">
				<p>..........................................................................................................................................</p>
					<span>《授权委托书（充值授权信和财富）》、《授权委托书（开户授权信和上融）》</span>
					<a onclick="downLoadFile();" href="javascript:void(0);">下载</a>
				<p>..........................................................................................................................................</p>
				<div style="height: 145px;" id="file_upload_div"></div>
				<p style="display: none;">
					<input type="file" id="file1" name="file" accept="application/pdf" onchange="_upload();" />
				</p>
				<a href="javascript:_uploadClick('save');" id="update_button">点击此处上传</a>
				<div style="margin-bottom: 5px;margin-top: 8px;">
					<font color="#FF0000">*</font>
					下载 授权委托书（充值授权信和财富）、授权委托书（开户授权信和上融）、客户手持委托书照片、身份证正/反面，后需填写完成并且上传才可进行转投大金融操作
				</div>
				<div class="Unity_btn">
					<ul>
						<li><button type="button" id="save_button" name="save" onclick="todirSubmit();" hidden>提交</button></li>
						<li><button type="button"  onclick="$(parent.document.body).find('#cboxClose')[0].click()">取消</button></li>
					</ul>
				</div>
		</div>
	</div>
</div>
</form>
<script type="text/javascript" src="${ctxWebInf}/js/lend/finish/todjr.js" charset="utf-8"></script>
</body>
</html>