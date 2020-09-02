package com.virtusa.society.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.society.dao.UserDAO;
import com.virtusa.society.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet({ "/UserList", "/editUser", "/deleteUser", "/newUser", "/updateUser", "/insertUser","/profile", "/updateProfile","/PastUserList"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDAO userDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/newUser":
				showNewForm(request, response);
				break;
			case "/insertUser":
				insertUser(request, response);
				break;
			case "/deleteUser":
				deleteUser(request, response);
				break;
			case "/editUser":
				showEditForm(request, response);
				break;
			case "/updateUser":
				updateUser(request, response);
				break;
			case "/profile":
				profile(request,response);
				break;
			case "/updateProfile":
				updateUserProfile(request,response);
				break;
			case "/PastUserList":
				listPastUsers(request,response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException | ParseException e) {
			throw new ServletException(e);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<User> listUsers = userDAO.listAllUsers();
		request.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);

	}
	
	private void listPastUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<User> listUsers = userDAO.listPastUsers();
		request.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PastUserList.jsp");
		dispatcher.forward(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email=request.getParameter("email");
        String plottype=request.getParameter("plottype");
        String plotno=request.getParameter("plotno");
        Double rent=Double.parseDouble(request.getParameter("rent"));
        int familyCount=Integer.parseInt(request.getParameter("familycount"));
        String phone=request.getParameter("phone");
        User user=new User(id,name,email,plottype,phone,plotno,rent,familyCount);
        userDAO.updateUser(user);
        response.sendRedirect("UserList");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.getuserDetails(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = new User(id);
        userDAO.deleteUser(user);
        response.sendRedirect("UserList");

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
		String name = request.getParameter("name");
		String email=request.getParameter("email");
        String plottype=request.getParameter("plottype");
        String plotno=request.getParameter("plotno");
        String country=request.getParameter("country");
        String state=request.getParameter("state");
        String district=request.getParameter("city");
        Double rent=Double.parseDouble(request.getParameter("rent"));
        String phone=request.getParameter("phone");
        int familyCount=Integer.parseInt(request.getParameter("familycount"));
        User user=new User(name,email,country,state,district,plottype,phone,plotno,rent,familyCount);
        userDAO.insertUser(user);
        response.sendRedirect("UserList");

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
	     dispatcher.forward(request, response);

	}
	
	private void profile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		String role=(String) session.getAttribute("role");
		User profiles=new User(email, role);
		User profile = userDAO.profile(profiles);
		request.setAttribute("Profile", profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Profile.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	private void updateUserProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email=request.getParameter("email");
		int familyCount=Integer.parseInt(request.getParameter("familycount"));
        String phone=request.getParameter("phone");
        User user=new User(id,name,email,familyCount,phone);
        userDAO.updateUserProfile(user);
        
        
        PrintWriter out=response.getWriter();
		out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
				"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
				"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
		
		out.print("<script>setTimeout(function () { \r\n" + 
				"swal({\r\n" + 
				"  title: \"Done!\",\r\n" + 
				"  text: \"Your Profile Updated Successfully !\",\r\n" + 
				"  type: \"success\",\r\n" + 
				"  confirmButtonText: \"OK\"\r\n" + 
				"},\r\n" + 
				"function(isConfirm){\r\n" + 
				"  if (isConfirm) {\r\n" + 
				"    window.location.href = \"profile\";\r\n" + 
				"  }\r\n" + 
				"}); }, 1000);</script>");
        
        
        
	}
}

