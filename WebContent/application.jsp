<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "common.Person"%>

<%
	Map<String,Person> maps = new HashMap<>();
	maps.put("actor1",new Person("홍길동",20));
	maps.put("actor2",new Person("이병일",30));
	application.setAttribute("maps", maps);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="ApplicationResult.jsp">ApplicationResult.jsp 바로가기</a>
</body>
</html>