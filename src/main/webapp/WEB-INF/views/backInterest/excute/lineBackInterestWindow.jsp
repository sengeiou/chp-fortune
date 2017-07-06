<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<form id="winForm" method="post">
		<div class='box1 mb10'>	
			<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr> 
				   	<td><label class="lab"><span class="red">*</span>操作：</label>
						<input type="radio" id="tp" name="tp" value="1" onchange="showc();" required>导出
						<input type="radio" id="tp" name="tp" value="2" onchange="showc();">上传</td>
	            
	                <td><label class="lab"><span class="red">*</span>回息平台：</label>
	                		<select class="select78" id='blt' name="platformId" select_required="1">
								<option value="">请选择</option>
								<c:forEach items="${dicts.tz_backInterest_plat}" var='item'>
									<c:if test="${flag==1 and item.label eq '金帐户'}">
										<option value="${item.value }">${item.label }</option>
									</c:if>
									<c:if test="${flag==2 and item.label != '金帐户' and item.label != '卡联平台'}">
										<option value="${item.value }">${item.label }</option>
									</c:if>
								</c:forEach>
							</select></td>
			            
	            </tr>
	            <tr>
	            	<td><label class="lab"><span class="red">*</span>文件格式：</label>
	            		<select class="select78" id='ftp' name="fileType" select_required="1">
						</select></td>
	            </tr>
	            <tr class="his" style="display: none">
	              		<td>
	               		<label class="lab"><span class="red">*</span>上传文件：</label>
		         		<input type="file" id="line" name="file" class='btn cf_btn-primary mr10'/>
		         	</td>
	            </tr>
			</table>
		</div>
	</form>