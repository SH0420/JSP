<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.member.Member" %>
<%
	request.setCharacterEncoding("utf-8");

	String userid = request.getParameter("userid");
	String userpwd = request.getParameter("userpwd");
	
	String uid = "admin";
	
	
	Gson gson = new Gson();
	Member m;
	
	if(uid.equals(userid)){
		m = new Member(userid,userpwd);		
	}else{
		m = new Member();
	}
	
	out.print(gson.toJson(m)); //문자열 json객체 구성후 클라인어트로 전송
%>