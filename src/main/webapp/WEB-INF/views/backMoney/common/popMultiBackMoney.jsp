<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div >
    <form id="popForm" method="post">
    <div>
       <table id="multiTable" class="f14 table4" cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr> 
			   	<td><label class="lab">审批结果：</label>
					<input name="checkExaminetype" type="radio" class="ml10 mr6" value="1" checked="checked">通过
                	<input name="checkExaminetype" type="radio" class="ml10 mr6" value="2">不通过</td>
            </tr>
            <tr id="T8" style="display:none"> 
				<td>
					<label class="lab">退回原因：</label> 
					<select class="cf_input_text178 mr34" id='checkExamine' name='checkExamine' onchange="addReason();">
						<option value="">请选择</option>
						<c:forEach items="${dicts.tz_back_reason}" var='item'>
							<option value="${item.value }">
								${item.label}
							</option>
						</c:forEach>
					</select>
				</td>
            </tr>
            <tr> 
				<td>
					<label class="lab">&nbsp;</label> 
					<input type="text" id="checkReason" class="cf_input_text178" name="checkReason" style="display: none;" maxlength='500'>
				</td>
            </tr>
        </table>

    <h3 id="T6" class="pop_title">中间人信息</h3>
    <div id="T7" class="box1 mb10">
       <table cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-condensed" width="100%">
		    <thead>
		    <tr>
			    <th></th>
				<th>中间人</th>
				<th>证件号码</th>
				<th>开户行</th>
				<th>银行账号</th>
			</tr>
			</thead>
			<c:forEach items="${platformList}" var="item">
				<tr>
				    <td><input type="radio" class="ml10 mr6" name="platformId" value="${item.id }" ></td>
					<td>${item.name }</td>
					<td>${item.certNo }</td>
					<td>${fns:dictName(dicts.tz_open_bank,item.bank,item.bank) }</td>
					<td>${item.bankcode }</td>
				</tr>
			</c:forEach>
        </table>
    </div>
    </form>
</div>
