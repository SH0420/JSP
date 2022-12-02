<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.HashMap"%>
<%@ page import ="java.util.Map"%>
<%@ page import ="model1.board.boardDAO"%>
<%@ page import ="model1.board.boardDTO"%>
<%
	boardDAO dao = new boardDAO(application);

	Map<String, Object> param = new HashMap<String, Object>();
	
	String searchField =request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	if(searchWord !=null){
		param.put("searchField", searchfield);
		param.put("searchWord", searchWord);
	}
			
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./Common/Link.jsp" />
	<h2>목록보기</h2>
	<form method="get">
	<table border="1" width="90%">
	<tr>
		<td align="center">
		<select name=
	</table>
	</form>
</body>
</html>