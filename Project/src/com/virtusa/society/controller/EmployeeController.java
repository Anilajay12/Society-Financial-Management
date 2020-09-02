package com.virtusa.society.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.virtusa.society.dao.EmployeeDAO;
import com.virtusa.society.model.Employee;




/**
 * Servlet implementation class Employee
 */
@WebServlet({ "/EmployeeList", "/editEmployee", "/deleteEmployee", "/newEmployee", "/updateEmployee", "/insertEmployee","/Employeeprofile","/pastEmployeeList" })
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EmployeeDAO empDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		empDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			case "/newEmployee":
				showNewForm(request, response);
				break;
			case "/insertEmployee":
				insertEmployee(request, response);
				break;
			case "/deleteEmployee":
				deleteEmployee(request, response);
				break;
			case "/editEmployee":
				showEditForm(request, response);
				break;
			case "/updateEmployee":
				updateEmployee(request, response);
				break;
			case "/Employeeprofile":
				profiles(request,response);
				break;
			case "/pastEmployeeList":
				listPastEmployees(request,response);
				break;
			default:
				listEmployee(request, response);
				break;
			}
		} catch (SQLException | ParseException e) {
			throw new ServletException(e);
		}
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Employee> listEmployee = empDAO.listAllEmployees();
		request.setAttribute("listEmployees", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
		dispatcher.forward(request, response);

	}
	
	private void listPastEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Employee> listPastEmployees = empDAO.listPastEmployees();
		request.setAttribute("listPastEmployees", listPastEmployees);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PastEmployees.jsp");
		dispatcher.forward(request, response);

	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
		int eid = Integer.parseInt(request.getParameter("eid"));
		String Name = request.getParameter("ename");
		String email=request.getParameter("email");
        String address = request.getParameter("address");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String contact=request.getParameter("phone");
        String dob=request.getParameter("dob");
        Date dateOfBirth=Date.valueOf(dob);
        Employee emp = new Employee(eid,Name,email,address,contact,salary,dateOfBirth);
        empDAO.updateEmployee(emp);
        HttpSession session=request.getSession();
        String role=(String) session.getAttribute("role");
        if(role.equals("employee"))
        	response.sendRedirect("Employeeprofile");
        else
        	response.sendRedirect("EmployeeList");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("eid"));
        Employee existingEmployee = empDAO.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("eid"));
		
        Employee emp = new Employee(id);
        empDAO.deleteEmployee(emp);
        response.sendRedirect("EmployeeList");

	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
		String ename = request.getParameter("ename");
        String email = request.getParameter("email");
        String gender=request.getParameter("gender");
        String gender1=String.valueOf(gender.charAt(0));
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        String dob=request.getParameter("dob");
        Date date=Date.valueOf(dob);
        Employee emp=new Employee(ename,email,address,gender1,phone,salary,date);
        empDAO.insertEmployee(emp);
        response.sendRedirect("EmployeeList");

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
	     dispatcher.forward(request, response);

	}
	
	private void profiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		String role=(String) session.getAttribute("role");
		Employee profiles=new Employee(email, role);
		Employee profile = empDAO.profile(profiles);
		request.setAttribute("Profile", profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Profile.jsp");
		dispatcher.forward(request, response);
		
	}
}

