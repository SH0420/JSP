package utils;

import javax.servlet.jsp.JspWriter;

public class JSFunction2 {

	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
						  +"<script>"
						  +"    alert('" + msg + "');"
						  +"    locatiion.href='" + url + "';"
						  +"</script>";
			out.println(script);
		}
		catch (Exception e) {
			
		}
	}	
	public static void alertBack(String msg, JspWriter out)	{
		try {
			String script = ""
						  + "<script>"
						  + "   alert('" + msg + "');"
						  +"</script>";
			out.println(script);
		}
		catch (Exception e) {
			
		}
	}
}
	

