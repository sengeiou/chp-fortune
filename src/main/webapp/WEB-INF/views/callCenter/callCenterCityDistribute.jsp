<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="body_r">
    <div class="box1 mb10">
    <form id="cityDistributeForm" action="${ctx}/callCenterController/distributeStore" method="post" >
         	<input type="hidden"  id="id" name="id" value="${customer.id }" />
          <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             <tr>
             	<!-- 屏蔽客户姓名/手机号/身份证号 -->
				<td>
					<label class="lab">客户姓名：</label><input type="text"  class="cf_input_text178" name="customerName"  value="***">
				</td>
                <%-- <td>
                	<label class="lab">客户姓名：</label><input type="text"  class="cf_input_text178" name="customerName"  value="${customer.customerName}">
                </td> --%>
                </tr>
                <tr>
                <!-- 屏蔽客户姓名/手机号/身份证号 -->
                <td>
                	<label class="lab">手机号码：</label><input type="text" class="cf_input_text178"  name="customerMobilephone"  value="***">
                </td>
                <%-- <td>
                	<label class="lab">手机号码：</label><input type="text" class="cf_input_text178"  name="customerMobilephone"  value="${customer.customerMobilephone}">
                </td> --%>
                 </tr>
                <tr>
                <td>
                	<label class="lab">分配到：</label>
                	<select id="storeOrgId" name="storeOrgId"  class="cf_input_text178">
                		<option value="">请选择</option>
                		<c:forEach items="${storeList}" var="item">
								<option value="${item.id }" >${item.name }</option>
						</c:forEach>
                	</select>
                </td>
            </tr>  
        </table>
        </form>
      </div>        
</div>
