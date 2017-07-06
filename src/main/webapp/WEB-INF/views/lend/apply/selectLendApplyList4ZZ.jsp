<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="searchForm" action="${ctx}/lend/apply/selectLend4ZZ" method="post" >
	<table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
		    <td>
				<label class='lab'>出借编号：</label>
				<input type="text" name="applyCode" id="_applyCode" class="cf_input_text178" value="${ applyCode}"/>
		    </td>
		    <td>
				<label class='lab'>证件号码：</label>
				<input type="text" name="certNum" id="_certNum" class="cf_input_text178" value="${ certNum}"/>
			</td>
			<td>
				<input type="hidden" name="customerCode" value="${ customerCode}">
				<input type="button" value="搜索" class="btn cf_btn-primary LendListBut" id="search"/>
				<input type="reset" value="清除" class="btn cf_btn-primary">
			</td>
		</tr>
	</table>
</form> 
<div id="listDiv">
<%@ include file="/WEB-INF/views/lend/apply/selectLendApplyList.jsp"%>
</div>