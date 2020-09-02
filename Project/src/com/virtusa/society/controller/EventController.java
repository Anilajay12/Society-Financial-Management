package com.virtusa.society.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.society.dao.EventDAO;
import com.virtusa.society.model.Event;



/**
 * Servlet implementation class EventController
 */
@WebServlet({ "/Eventlist", "/editEvent", "/deleteEvent", "/newEvent", "/updateEvent", "/insertEvent" })
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EventDAO eventDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		eventDAO = new EventDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			case "/newEvent":
				showNewForm(request, response);
				break;
			case "/insertEvent":
				insertEvent(request, response);
				break;
			case "/deleteEvent":
				deleteEvent(request, response);
				break;
			case "/editEvent":
				showEditForm(request, response);
				break;
			case "/updateEvent":
				updateEvent(request, response);
				break;
			default:
				listEvent(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void listEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Event> listEvent = eventDAO.listAllEvents();
		request.setAttribute("listEvents", listEvent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("eventList.jsp");
		dispatcher.forward(request, response);

	}

	private void updateEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("eventid"));
		String eventName = request.getParameter("eventName");
		String location=request.getParameter("eventLocation");
        String chiefGuest = request.getParameter("chiefGuest");
        Double price = Double.parseDouble(request.getParameter("amount"));
        String contact=request.getParameter("contactEmail");
        String dob=request.getParameter("eventdate");
        Date eventdate=Date.valueOf(dob);
        Event event = new Event(id,eventName,location,chiefGuest,price,contact,eventdate);
        eventDAO.updateEvent(event);
        response.sendRedirect("Eventlist");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("eventid"));
        Event existingEvent = eventDAO.getEvent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
        request.setAttribute("event", existingEvent);
        dispatcher.forward(request, response);

	}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("eventid"));
		 
        Event event = new Event(id);
        eventDAO.deleteEvent(event);
        response.sendRedirect("Eventlist");

	}

	private void insertEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String eventName = request.getParameter("eventName");
        String chiefGuest = request.getParameter("chiefGuest");
        Double price = Double.parseDouble(request.getParameter("amount"));
        String dob=request.getParameter("eventdate");
        Date eventdate=Date.valueOf(dob);
        Event newEvent = new Event(eventName, chiefGuest, price,eventdate);
        eventDAO.insertEvent(newEvent);
        response.sendRedirect("Eventlist");

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("eventForm.jsp");
	     dispatcher.forward(request, response);

	}
}
