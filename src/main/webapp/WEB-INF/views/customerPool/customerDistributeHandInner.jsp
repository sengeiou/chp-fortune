<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<input type="hidden" id="custCode" value="${custCode}" />
	<input type="hidden" id="id" value="${id}" />
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in  active" id="information">
			<c:choose>
				<c:when test="${customer.custState==3 or customer.custState==4 or customer.custState==5 }">
					<%@include file="customerDetail4Tele.jsp" %>
				</c:when>
				<c:when test="${customer.custState==1 or customer.custState==2 }">
					<%@include file="customerInfo4Tele.jsp" %>
				</c:when>
			</c:choose>
		</div>
		<div class="tab-pane fade in advisoryTabDiv" id="advisory">
		</div>
		<c:if test="${dataType == '3' }">
			<div class="tab-pane fade in advisoryTabDiv" id="distribute"></div>
		</c:if>
	</div>
	<div id="modal-sub" class="modal fade subwindow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<input type="button" value="提交" class="btn cf_btn-primary" id="subSubmit"/>
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
