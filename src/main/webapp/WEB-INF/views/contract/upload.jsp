<%@ page contentType="text/html;charset=UTF-8" %>
<form id="upload"  action="${ctx}/contract/changeApplyList/importExcel" enctype="multipart/form-data" method="post">
<input name="file" id="upFile" type="file" value="浏览" required>	
注：Excel模板中合同编号的单元格必须为“文本”类型
</form>
