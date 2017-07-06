<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="box1 mb10">
	<form id="inputForm" action="${ctx}/trusteeship/account/importExcel" method="post" enctype="multipart/form-data" class="form-horizontal">
		<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
				    <input type="file" name="file" value="浏览">
				    <progress style="display:none"></progress>
				</td>
			</tr>
		</table>
</div>