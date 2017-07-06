<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>批量不通过</title>
</head>
<body>
  <div class='diabox1'>
    <table cellspacing="0" cellpadding="0" border="0"  class="table2" width="100%">
    <tr>
        <th>批量不通过</th>
    </tr>
    <table class="table1" cellpadding="0" cellspacing="0" border="0" width="100%">
             
             <tr>
                <td><label class="lab">审核意见：</label>
                	<select class="select180"><option>请选择</option><option>1、合同暂时无法审批，不通过。（仅供参考）</option></select>
                </td>
              
            </tr>
            
        </table>
     <div class="tcenter pr30 mt20">
             <button class="btn cf_btn-primary">提交</button>
             <button class="btn cf_btn-primary">取消</button>
        </div>
</div>
</body>
</html>