<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class='ppllmain subDiv'>
	<table cellspacing="0" cellpadding="0" border="0" id="ppll" class="table table-striped table-bordered table-condensed" width="100%">
		<tr>
			<td>日期</td>
			<td colspan="2">${day }</td>
		</tr>
		<tr>
			<th>类别</th>
			<th>首期</th>
			<th>非首期</th>
		</tr>
			<tr>
				<td>已推荐数</td>
				<td>${retMap["ytjsqnum"]}</td>
				<td>${retMap["ytjfsqnum"]}</td>
			</tr>
			<tr>
				<td>待推荐数</td>
				<td>${retMap["wtjsqnum"]}</td>
				<td>${retMap["wtjfsqnum"]}</td>
			</tr>
	</table>
</div>
