<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="common.EmpConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	
	<%
		EmpConnect emp = new EmpConnect();
		
		String sql ="select * from emp01";
		Statement stmt = emp.con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			String empno = rs.getString("empno"); 	//테이블의 컬럼명  rs.getString(1)
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			String mgr = rs.getString("mgr");
			java.sql.Date hiredate = rs.getDate("hiredate");
			String sal = rs.getString("sal");
			String comm = rs.getString("comm");
			String deptno = rs.getString("deptno");
			
			out.println(String.format("%s %s %s %s %s %s %s %s", empno, ename, job, mgr, hiredate, sal, comm, deptno ) + "<br/>");
		}
		emp.close();
	%>
<body>

</body>
</html>