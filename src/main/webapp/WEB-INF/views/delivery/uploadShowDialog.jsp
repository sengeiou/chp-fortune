<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxWebInf}/js/delivery/uploadShowDialog.js" type="text/javascript"></script>
<form id="form" enctype="multipart/form-data">
<div>
  <table>
    <tr>
        <td>
            <label>选择文件：</label>
            <input name="file" type="file" value="浏览">	
            <progress style="display:none"></progress>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" id="uploadButton" value="上传"/>
		    <input type="reset" value="取消"/>
        </td>
    </tr>
  </table>
</div>
 </form>
