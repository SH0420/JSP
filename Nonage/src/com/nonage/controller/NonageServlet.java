package com.nonage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;


@WebServlet("/NonageServlet")
public class NonageServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public NonageServlet() {	
        super();
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.클라이언트로부터  command요청을 받는다.  
	   	 String command = request.getParameter("command");
         System.out.println("NonageServlet에서 요청을 받음을 확인 : " + command);
         
         //2.ActionFactory에 요청전달
         
         ActionFactory af = ActionFactory.getInstance();
         Action action = af.getAction(command);
         
         //3.전달받은 actionController를 실행한다.
         if(action != null) {
        	 action.execute(request, response);
         }
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      doGet(request,response);
      
   }

}