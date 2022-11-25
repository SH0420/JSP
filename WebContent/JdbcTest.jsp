<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>    
<%
	Connection con = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,"musthave","1234");         //	(DB주소,아이디,비번)
		
		con.close();  //DB서버끊기...............................
	}catch(Exception e){
		e.printStackTrace();		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	DB연결 성공
</body>
</html>