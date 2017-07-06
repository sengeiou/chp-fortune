<%@ page language="java"  
import="java.util.*,
java.sql.*,
org.apache.ibatis.session.*,
java.sql.*,
com.creditharmony.common.util.SpringContextHolder
" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		String erroInfo = null;
		List<String> thead = new ArrayList<String>();
		List<List<String>> dateList = new ArrayList<List<String>>();
		String batisSql = request.getParameter("batisSql");
		String pageSize = request.getParameter("pageSize");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		if (batisSql != null && !batisSql.equals("")) {
			try {
				SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
						.getBean("sqlSessionFactory");
				SqlSession sqlSession = sqlSessionFactory.openSession();
				connection = sqlSession.getConnection();
				ps = connection.prepareStatement(batisSql + " limit " + pageSize);
				resultSet = ps.executeQuery();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					thead.add(rsmd.getColumnName(i));
				}
				while (resultSet.next()){
					List<String> list = new ArrayList<String>();
					for(String colmName:thead){
						list.add(resultSet.getString(colmName));
					}
					dateList.add(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				erroInfo = e.getMessage();
				out.println("<div style='color:#F00'>"+erroInfo+"</div><hr>");
			} finally {
				resultSet.close();
				ps.close();
				connection.close();
				request.setAttribute("thead", thead);
				request.setAttribute("batisSql", batisSql);
				request.setAttribute("dateList", dateList);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("erroInfo", erroInfo);
			}
		}
	%>
	<form action="queryDate.jsp" method="post">
		<span style="vertical-align:top;">查询sql语句：</span>
		<textarea name="batisSql" style="width:90%;height:350px;">${batisSql}</textarea>
		<p> <div style="float:left;">
			分页条数：
			<input type="text" name="pageSize" value="${pageSize eq null ? 20 : pageSize}" style="width:40px;height:18px;">　
			默认为20条数据
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="query">
	</form>
	<table border="1">
		<thead>
			<tr>
				<th>NO</th>
				<c:forEach items="${thead}" var="varThead">
					<th>${varThead}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dateList}" var="varList" varStatus="index">
				<tr>
					<td>${index.index+1}</td>
					<c:forEach items="${varList}" var="varSubThead">
						<td><c:out value="${varSubThead}"></c:out></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>