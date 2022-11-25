<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="common.JDBConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	<h2>회원 추가 테스트(executeUpdate() 사용)</h2> -->
<%
// 	JDBConnect jdbc = new JDBConnect();
	
// 	String sql = "insert into member values(?,?,?,sysdate)"; //값을 미리정하지않는 동적 쿼리문
// 	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
// 	psmt.setString(1, "test1"); //(자리 , 자리의 값)
// 	psmt.setString(2, "1111");
// 	psmt.setString(3, "회원1");
	
// 	int result =psmt.executeUpdate();
// 	if(result !=0){
// 		out.print("쿼리문 실행 완료");
// 	}else{
// 		out.print("쿼리문 실행 실패");
// 	}
	

// 	jdbc.close();
%>
	
	<h2>회원 추가 테스트(executeQuery() 사용)</h2>
	<%
		JDBConnect jdbc = new JDBConnect();
		
		String sql = "select * from member"; //정적 쿼리문
		Statement stmt = jdbc.con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			String id = rs.getString("id"); 	//테이블의 컬럼명  rs.getString(1)
			String pw = rs.getString("pass");
			String name = rs.getString("name");
			java.sql.Date regidate = rs.getDate("regidate");
			
			out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>");
		}
		
 		//rs-> psmt-> stmt ->con
		
		jdbc.close();
	%>
</body>
</html>