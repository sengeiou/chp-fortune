<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<form id="uploadMoney" action="${ctx}/backReturnInterestApply/uploadIsInterestReturn" enctype="multipart/form-data" method="post">	
		<div class='body_r'>
			<table class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
				<div class='box1 mb10'>
					<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
	               		<td>
	               			<input type="hidden" id="mesg" value="${mesg}"/>
		               		<label class="lab">上传文件：</label>
			         		<input type="file" id="fe" name="file"/>
			         	</td>
			        </table>
				</div>
			</table>
		</div>
	</form>
</body>