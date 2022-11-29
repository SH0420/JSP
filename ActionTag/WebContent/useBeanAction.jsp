<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
// 	String name =request.getParameter("name");
// 	String age =request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
	<h3>액션 태그로 폼값 한 번에 받기</h3>
	<jsp:useBean id="person" class="common.Person" />
<%-- 	<jsp:setProperty property="name" name="person" value=""/> --%>
<%-- 	<jsp:setProperty property="age" name="person" value=""/> --%>
<%-- 	<jsp:setProperty property="name" name="person" param="name"/> --%>
<%-- 	<jsp:setProperty property="age" name="person" param="age"/> --%>

		<jsp:setProperty property="*" name="person" />

	<ul>
		<li>이름 : <jsp:getProperty property="name" name="person"/> </li>
		<li>나이 : <jsp:getProperty property="age" name="person"/> </li>
	</ul>
</body>
</html>