<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxWebInf}/js/maintenance/maintenance.js"></script>
</head>
<body>
    <div class="box1 mb10">
    <form id="searchForm"  action="${ctx}/maintenance/missRelease/settlesHeader" method="post">
        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td>
                	<label class="lab">出借编号：</label>
                	<input type="text" name="lendCode" required="required" value="${MissReleaseView.lendCode}" class="cf_input_text178">
                </td>
                <td>
                	<label class="lab">开始期数：</label>
                	<input type="text" name="startPeriods" required="required" value="${MissReleaseView.startPeriods}" class="cf_input_text178">
                </td>
				<td>
					<label class="lab">结束期数：</label>
					<input type="text" name="endPeriods" required="required" value='${MissReleaseView.endPeriods}' class="cf_input_text178">
				</td>
            </tr>
            <tr> 
			    <td colspan="3">
			    	<label class="lab">备注：</label>
						<textarea disabled="disabled" rows="18" cols="150">
							文字说明
                		</textarea>
			    </td>
            </tr>
        </table>
        <div class='tright pr30 pt10 pb5'>
            <input class="btn cf_btn-primary" type="submit" value="搜索"/>
            <input type="reset" class="btn cf_btn-primary" value="清除">
        </div>
	   </form>
	   </div>
    <sys:message content="${message}"/>
</body>
</html>
