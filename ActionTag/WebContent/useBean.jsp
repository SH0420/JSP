<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="Person" class="common.Person" scope="request"></jsp:useBean>
	
	<jsp:setProperty property="name" name="Person" value="임꺽정"/>
	<jsp:setProperty property="age" name="Person" value="10"/>
	
	<ul>
		<li><jsp:getProperty property="name" name="Person"/></li>
		<li><jsp:getProperty property="age" name="Person"/></li>
	</ul>
</body>
</html>