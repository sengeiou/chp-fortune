<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<form id="uploadForm" method="post">
		<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr id="upl">
				<td><label class="lab">选择文件：</label>
				<input id="fileImport" type="file" name="file" value="浏览文件" onchange="checkIfExcel(this);"/></td>
			</tr>
		</table>
	</form>

