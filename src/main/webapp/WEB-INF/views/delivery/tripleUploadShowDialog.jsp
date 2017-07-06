<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxWebInf}/js/delivery/tripleUploadShowDialog.js" type="text/javascript"></script>
<form id="form" enctype="multipart/form-data">
<div>
  <table class='mb10'>
    <tr>
        <td>
             &nbsp;&nbsp;&nbsp;选择文件：
            <input name="file" type="file"  accept=".xlsx" value="浏览">	
            <progress style="display:none"></progress>
        </td>
    </tr>
    <tr>
        <td>
            &nbsp;&nbsp;
            <input type="button" id="uploadButton" value="上传" class='btn btn_sc'/>
		    <input type="reset" value="取消" class='btn btn_sc'/>
        </td>
    </tr>
  </table>
</div>
 </form>
