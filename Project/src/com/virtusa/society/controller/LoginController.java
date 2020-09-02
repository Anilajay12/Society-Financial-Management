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

import com.virtusa.society.dao.LoginDAO;
import com.virtusa.society.model.Login;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDAO loginDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		loginDAO = new LoginDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String emailid=request.getParameter("email");
		String password=request.getParameter("password");
		
		Login login=new Login(emailid,password);
		
		try {
			if(loginDAO.customerLogin(login)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", login.getUsername());
				request.setAttribute("username", login.getUsername());
				session.setAttribute("role", "user");
				request.setAttribute("role", "user");
				session.setAttribute("email", login.getEmail());
				PrintWriter out=response.getWriter();
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
				
				out.print("<script>setTimeout(function () { \r\n" + 
						"swal({\r\n" + 
						"  title: \"Nice!\",\r\n" + 
						"  text: \"You have been Successfully Logged in!\",\r\n" + 
						"  type: \"success\",\r\n" + 
						"  confirmButtonText: \"OK\"\r\n" + 
						"},\r\n" + 
						"function(isConfirm){\r\n" + 
						"  if (isConfirm) {\r\n" + 
						"    window.location.href = \"Welcome.jsp\";\r\n" + 
						"  }\r\n" + 
						"}); }, 1000);</script>");
			}
			else if(loginDAO.employeeLogin(login)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", login.getUsername());
				request.setAttribute("username", login.getUsername());
				session.setAttribute("role", "employee");
				request.setAttribute("role", "employee");
				session.setAttribute("email", login.getEmail());
				
				PrintWriter out=response.getWriter();
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
				
				out.print("<script>setTimeout(function () { \r\n" + 
						"swal({\r\n" + 
						"  title: \"Nice!\",\r\n" + 
						"  text: \"You have been Successfully Logged in!\",\r\n" + 
						"  type: \"success\",\r\n" + 
						"  confirmButtonText: \"OK\"\r\n" + 
						"},\r\n" + 
						"function(isConfirm){\r\n" + 
						"  if (isConfirm) {\r\n" + 
						"    window.location.href = \"Welcome.jsp\";\r\n" + 
						"  }\r\n" + 
						"}); }, 1000);</script>");
			}
			else if(loginDAO.adminLogin(login)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", login.getUsername());
				request.setAttribute("username", login.getUsername());
				session.setAttribute("role", "admin");
				request.setAttribute("role", "admin");
				session.setAttribute("email", login.getEmail());
				PrintWriter out=response.getWriter();
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
				
				out.print("<script>setTimeout(function () { \r\n" + 
						"swal({\r\n" + 
						"  title: \"Nice!\",\r\n" + 
						"  text: \"You have been Successfully Logged in!\",\r\n" + 
						"  type: \"success\",\r\n" + 
						"  confirmButtonText: \"OK\"\r\n" + 
						"},\r\n" + 
						"function(isConfirm){\r\n" + 
						"  if (isConfirm) {\r\n" + 
						"    window.location.href = \"Welcome.jsp\";\r\n" + 
						"  }\r\n" + 
						"}); }, 1000);</script>");
			}
			else {
				PrintWriter out=response.getWriter();
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
						"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
						"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
				
				out.print("<script>setTimeout(function () { \r\n" + 
						"swal({\r\n" + 
						"  title: \"OOPS!\",\r\n" + 
						"  text: \"Invalid Credentials Enter valid credentials!\",\r\n" + 
						"  type: \"error\",\r\n" + 
						"  confirmButtonText: \"OK\"\r\n" + 
						"},\r\n" + 
						"function(isConfirm){\r\n" + 
						"  if (isConfirm) {\r\n" + 
						"    window.location.href = \"Login.jsp\";\r\n" + 
						"  }\r\n" + 
						"}); }, 1000);</script>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
