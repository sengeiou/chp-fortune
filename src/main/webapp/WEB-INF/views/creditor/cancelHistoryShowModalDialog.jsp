<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	    <table class="table table-striped table-bordered table-condensed" width="100%">
	      <thead>
	        <tr>
	            <th>序号</th>
	            <th>操作人</th>
	            <th>操作内容</th>
	            <th>操作时间</th>
	           
	        </tr>
	      </thead>
	      <tbody>
            <c:forEach items="${list}" var="item" varStatus="status">
		        <tr>
		            <td>${status.count}</td>
		            <td>${item.createBy}</td>
		            <td>${item.operateInfo}</td>
		            <td>${fns:getFormatDate(item.createTime,'yyyy-MM-dd')}</td>
			    </tr>
			  </c:forEach>
    </table>