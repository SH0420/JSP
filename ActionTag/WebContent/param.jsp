<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<jsp:forward page="paramForward.jsp"> --%>
<%-- 		<jsp:param value="임꺽정" name="param1"/> --%>
<%-- 		<jsp:param value="10" name="param2"/> --%>
<%-- <%-- 	<jsp:param value="임꺽정" name="param1"></jsp:param> --%> --%>

<%-- 	</jsp:forward> --%>

	<jsp:include page="inc/paramInclude.jsp">
		<jsp:param value="강원도" name="Loc1"/>
		<jsp:param value="부산" name="Loc2"/>
	</jsp:include>	
</body>
</html>