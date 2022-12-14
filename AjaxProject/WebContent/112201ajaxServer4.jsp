<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String name = request.getParameter("name");
	String ph_number = request.getParameter("ph_number");
	String address = request.getParameter("address");

%>
{ 
 	"name" : "<%=name%>",
 	"ph_number" : "<%=ph_number%>",
 	"address" : "<%=address%>"
}