<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default"/>
<script src="${ctxWebInf }/js/trusteeship/trusteeshipChange.js" type="text/javascript"></script>
<script src="${ctxWebInf}/js/common/subdialog.js" type="text/javascript"></script>
<title></title>
</head>
<body>
	<div class="body_r">
	    <div class="box1 mb10">      
	    	<form action="${ctx}/trusteeship/change/list" method="post" id="searchForm">
		        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		        <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
		             <tr>
		             	<td>
		             		<label class="lab">客户姓名：</label>
		                	<input type="text" name="customerName" class="cf_input_text178" value="${search.customerName }">
		             	</td>
		                <td>
			                <label class="lab">客户编号：</label>
			                <input type="text" name=customerCode class="cf_input_text178" value="${search.customerCode }">
		                </td>
		            </tr>
		            <tr>
		             	<td>
		             		<label class="lab">开户行：</label>
			                <select name="bankId" multiple="multiple">
								<c:forEach items="${fns:getDictList('tz_open_bank') }" var="bank">
									<option value="${bank.value }" <c:if test="${fns:multiOption(search.bankId,bank.value)}">selected</c:if> >${bank.label }</option>
								</c:forEach>
							</select>
		             	</td>
		             	<td>
			                <label class="lab">变更类型：</label>
			                <select class="select78" name="changeType">
                	  		  <option value="">请选择</option>
                      			<c:forEach items="${fns:getDictList('tz_trusteeship_change_state')}" var ="d" >
                	   			 <option value="${d.value }" <c:if test="${d.value eq search.changeType}">
									selected
								   </c:if> >${d.label }
								</option>
                      			</c:forEach>      
							</select>
		                </td>
		            </tr>
		        </table>	
	        	<div class="tright pr30">
	        		<button class="btn cf_btn-primary search">搜索</button>
	        		<input class="btn cf_btn-primary" type="reset" value="清除">
	        	</div>
	        </form>
	    </div>
	    <p class="mb10 ml10">
	    	<form method="post">
	    		<auth:hasPermission key="cf:trusteeshipchang:phone">
			    	<input type="button" class="btn btn_sc ml10 mb10 output" value="手机号变更信息下载" onclick="chengeFileDown('4')"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipchang:bankcard">
			    	<input type="button" class="btn btn_sc mb10 input" value="银行卡信息变更下载" onclick="chengeFileDown('5')"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipchang:closing">
			    	<input type="button" class="btn btn_sc mb10 protocol" value="金账户销户信息下载" onclick="chengeFileDown('6')"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipchang:success">
			    	<input type="button" class="btn btn_sc mb10 bulk_account" onclick="approve()" value="成功"/>
			    </auth:hasPermission>
			    <auth:hasPermission key="cf:trusteeshipchang:back">
			    	<input type="button" class="btn btn_sc toAdd mb10 protocol_docking" onclick="back()" value="退回"/>
			    </auth:hasPermission>
		    	<div class="dataList">
			    	<%@ include file="/WEB-INF/views/trusteeship/change/list.jsp"%>
			    </div>
		    </form>
		</p>	    
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
					<input type="button" value="关闭" class="btn cf_btn-primary" id="subClose"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
