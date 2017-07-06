<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/public01.css" />
<title>上传文件</title>
</head>
<body style="width:100%;height:400px;padding:0;margin:0 auto;margin-top: 0px;">
<!--分配外访弹出框表-->
<div class="dialogbox" style="background:#fff;padding:0;width:100%;height:100%;">
    <div class="diabox_cell">
        <div class="diabox">
        	<button style="text-align:left">选择文件</button>提示信息:..................................
            <div style="width:400px; height:300px; border:1px #ccc solid; margin:5px 0; text-align:center;"></div>
        	<button style="text-align:left">上传</button>&nbsp;<button style="text-align:left" onclick="opener.location='04出借申请待办.html';window.opener=null;window.open('','_self');window.close();">取消</button>&nbsp;<button style="text-align:left" onclick="opener.location='04出借申请待办.html';window.opener=null;window.open('','_self');window.close();">提交</button>
        	
        </div>
    </div>
</div>
</body>
</html>
