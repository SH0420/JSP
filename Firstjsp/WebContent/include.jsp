<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="includeFile.jsp" %>  

<%
	// http://localhost:8081/FirstJsp/  => 절대경로
	// 3대 지시어: page , include , taglib 앞에<%@쓴다
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		out.println("오늘 날짜 :" + today);           //includeFile.jsp에서 선언한 변수사용하기때문에 문제없음
		out.println("<br/>");
		out.println("내일 날짜 :" + tomorrow);
	%>
</body>
</html>