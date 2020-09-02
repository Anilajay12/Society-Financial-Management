package com.virtusa.society.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.society.dao.ChangePasswordDAO;
import com.virtusa.society.model.ChangePassword;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet({ "/ChangePassword", "/ViewProfile" })
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ChangePasswordDAO changeDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		changeDAO = new ChangePasswordDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("email");
		
		String role=(String) session.getAttribute("role");
		
		String password=request.getParameter("password");
		String newpassword=request.getParameter("newpass");
		String confirmpassword=request.getParameter("cpass");
		
		
		 if(newpassword.length()<8 || confirmpassword.length()<8) {
			PrintWriter out=response.getWriter();
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
			
			out.print("<script>setTimeout(function () { \r\n" + 
					"swal({\r\n" + 
					"  title: \"oops!\",\r\n" + 
					"  text: \"New Password length must be 8 digits!\",\r\n" + 
					"  type: \"error\",\r\n" + 
					"  confirmButtonText: \"OK\"\r\n" + 
					"},\r\n" + 
					"function(isConfirm){\r\n" + 
					"  if (isConfirm) {\r\n" + 
					"    window.location.href = \"changePassword.jsp\";\r\n" + 
					"  }\r\n" + 
					"}); }, 1000);</script>");
		}else if(!newpassword.equals(confirmpassword)) {
			PrintWriter out=response.getWriter();
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
			
			out.print("<script>setTimeout(function () { \r\n" + 
					"swal({\r\n" + 
					"  title: \"oops!\",\r\n" + 
					"  text: \"New Password and confirm password should match!\",\r\n" + 
					"  type: \"error\",\r\n" + 
					"  confirmButtonText: \"OK\"\r\n" + 
					"},\r\n" + 
					"function(isConfirm){\r\n" + 
					"  if (isConfirm) {\r\n" + 
					"    window.location.href = \"changePassword.jsp\";\r\n" + 
					"  }\r\n" + 
					"}); }, 1000);</script>");
		}else if(newpassword.equals(confirmpassword) && !newpassword.equals(password)) {
			ChangePassword cp=new ChangePassword(password, newpassword, role, name);
			try {
				if(changeDAO.changePassword(cp)) {
					PrintWriter out=response.getWriter();
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
					out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
					out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
							"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
							"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
					
					out.print("<script>setTimeout(function () { \r\n" + 
							"swal({\r\n" + 
							"  title: \"Good Job!\",\r\n" + 
							"  text: \"Your Password Updated Successfully!\",\r\n" + 
							"  type: \"success\",\r\n" + 
							"  confirmButtonText: \"OK\"\r\n" + 
							"},\r\n" + 
							"function(isConfirm){\r\n" + 
							"  if (isConfirm) {\r\n" + 
							"    window.location.href = \"changePassword.jsp\";\r\n" + 
							"  }\r\n" + 
							"}); }, 1000);</script>");
				}else {
					PrintWriter out=response.getWriter();
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
					out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
					out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
							"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
							"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
					
					out.print("<script>setTimeout(function () { \r\n" + 
							"swal({\r\n" + 
							"  title: \"oops!\",\r\n" + 
							"  text: \"Entreed Wrong Password!\",\r\n" + 
							"  type: \"error\",\r\n" + 
							"  confirmButtonText: \"OK\"\r\n" + 
							"},\r\n" + 
							"function(isConfirm){\r\n" + 
							"  if (isConfirm) {\r\n" + 
							"    window.location.href = \"changePassword.jsp\";\r\n" + 
							"  }\r\n" + 
							"}); }, 1000);</script>");
				}
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			PrintWriter out=response.getWriter();
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
			
			out.print("<script>setTimeout(function () { \r\n" + 
					"swal({\r\n" + 
					"  title: \"oops!\",\r\n" + 
					"  text: \"Entered same password as before!\",\r\n" + 
					"  type: \"error\",\r\n" + 
					"  confirmButtonText: \"OK\"\r\n" + 
					"},\r\n" + 
					"function(isConfirm){\r\n" + 
					"  if (isConfirm) {\r\n" + 
					"    window.location.href = \"changePassword.jsp\";\r\n" + 
					"  }\r\n" + 
					"}); }, 1000);</script>");
		}
		
		
		//doGet(request, response);
	}

}
