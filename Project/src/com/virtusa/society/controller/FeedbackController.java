package com.virtusa.society.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.society.dao.FeedbackDAO;
import com.virtusa.society.model.Feedback;

/**
 * Servlet implementation class FeedbackController
 */
@WebServlet({ "/FeedbackList", "/FeedbackCreate", "/FeedbackDelete" })
public class FeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeedbackDAO feedbackDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		feedbackDAO = new FeedbackDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			case "/FeedbackCreate":
				insertFeedback(request, response);
				break;
			case "/FeedbackDelete":
				deleteFeeback(request, response);
				break;
			default:
				listFeedback(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void listFeedback(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Feedback> listFeedback = feedbackDAO.listAllFeedbacks();
		request.setAttribute("listFeedback", listFeedback);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FeedbackList.jsp");
		dispatcher.forward(request, response);

	}
	private void insertFeedback(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        String message=request.getParameter("message");
        Feedback feed=new Feedback(name, email, message);
        
        feedbackDAO.insertFeedback(feed);
        
        PrintWriter out=response.getWriter();
		out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
				"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
				"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
		
		out.print("<script>setTimeout(function () { \r\n" + 
				"swal({\r\n" + 
				"  title: \"Good Job!\",\r\n" + 
				"  text: \"Your Request has been Successfully sent We will contact you soon!\",\r\n" + 
				"  type: \"success\",\r\n" + 
				"  confirmButtonText: \"OK\"\r\n" + 
				"},\r\n" + 
				"function(isConfirm){\r\n" + 
				"  if (isConfirm) {\r\n" + 
				"    window.location.href = \"index.jsp\";\r\n" + 
				"  }\r\n" + 
				"}); }, 1000);</script>");
	}
	private void deleteFeeback(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
        Feedback feed = new Feedback(id);
        feedbackDAO.deleteFeedback(feed);
        response.sendRedirect("FeedbackList");

	}

}
