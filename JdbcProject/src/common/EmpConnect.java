package common;

import java.sql.*;

import javax.servlet.ServletContext;

public class EmpConnect {
		public Connection con;
		public Statement stmt;
		public PreparedStatement psmt;
		public ResultSet rs;	
		
		public EmpConnect() {
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,"scott","tiger");
			}catch(Exception e){
				e.printStackTrace();		
			}
		}
		
		public EmpConnect(String driver, String url, String id, String pwd) {
			try{
				Class.forName(driver);
				con = DriverManager.getConnection(url,id,pwd);
			}catch(Exception e){
				e.printStackTrace();		
			}
		}
		
		public EmpConnect(ServletContext application) {
			try {
				String driver = application.getInitParameter("OracleDriver");
				
				Class.forName(driver);
				
				String url = application.getInitParameter("OracleURL");
				String id = application.getInitParameter("OracleId");
				String pwd = application.getInitParameter("OraclePwd");
				
				con = DriverManager.getConnection(url,id,pwd);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		public void close() {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (psmt != null) psmt.close();
				if (con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
