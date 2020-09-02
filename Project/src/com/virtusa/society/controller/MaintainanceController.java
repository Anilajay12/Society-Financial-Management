package com.virtusa.society.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.society.dao.MaintainanceDAO;
import com.virtusa.society.model.Maintainance;

/**
 * Servlet implementation class MaintainanceController
 */
@WebServlet({ "/GenerateMaintainance", "/ViewMaintainance", "/userPaidBill", "/userUnpaidBill", "/PaidBills",
		"/UnpaidBills", "/PayMaintainance", "/Payment", "/Reports", "/totalMaintainance", })
public class MaintainanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MaintainanceDAO maintainDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		maintainDAO = new MaintainanceDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/GenerateMaintainance":
				insert(request, response);
				break;
			case "/userPaidBill":
				userPaid(request, response);
				break;
			case "/userUnpaidBill":
				userUnpaid(request, response);
				break;
			case "/PaidBills":
				listPaidBills(request, response);
				break;
			case "/UnpaidBills":
				listUnpaidBills(request, response);
				break;
			case "/PayMaintainance":
				showPaymentPage(request, response);
				break;
			case "/Payment":
				paymentVerify(request, response);
				break;
			case "/Reports":
				reportForm(request, response);
				break;
			case "/totalMaintainance":
				maintainanceBills(request, response);
				break;
			default:
				listMaintainance(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void maintainanceBills(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String start = request.getParameter("startdate");
		Date startdate = Date.valueOf(start);
		String end = request.getParameter("enddate");
		Date enddate = Date.valueOf(end);
		Maintainance maintainance = new Maintainance();
		maintainance.setStart(startdate);
		maintainance.setEnd(enddate);
		List<Object> result = maintainDAO.report(maintainance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Reports.jsp");
		request.setAttribute("report", result);
		dispatcher.forward(request, response);
	}

	private void reportForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("Reports.jsp");
	}

	@SuppressWarnings("deprecation")
	private void paymentVerify(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String plotno = request.getParameter("plotno");
		double total = Double.parseDouble(request.getParameter("total"));
		double maintainance = Double.parseDouble(request.getParameter("maintainance"));
		double extra = total - maintainance;
		if (validateCreditCardNumber(request.getParameter("cardNumber"))) {
			short cvv = Short.parseShort(request.getParameter("cvv"));
			if (cvv >= 100 && cvv <= 999) {
				if (Integer.parseInt(request.getParameter("cardExpireYear")) > java.util.Calendar.getInstance()
						.getTime().getYear()+1900) {
					Maintainance payment = new Maintainance(id, name, email, plotno, maintainance, extra, total);
					if (maintainDAO.payment(payment)) {
						PrintWriter out = response.getWriter();
						out.println(
								"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
						out.println(
								"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
						out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n"
								+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n"
								+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");

						out.print("<script>setTimeout(function () { \r\n" + "swal({\r\n"
								+ "  title: \"Payment Successful!\",\r\n"
								+ "  text: \"You are Payment has been Successfully completed !\",\r\n"
								+ "  type: \"success\",\r\n" + "  confirmButtonText: \"OK\"\r\n" + "},\r\n"
								+ "function(isConfirm){\r\n" + "  if (isConfirm) {\r\n"
								+ "    window.location.href = \"userPaidBill\";\r\n" + "  }\r\n"
								+ "}); }, 1000);</script>");
					}
				} else if (Integer.parseInt(request.getParameter("cardExpireYear")) == java.util.Calendar.getInstance()
						.getTime().getYear()+1900
						&& Short.parseShort(request.getParameter("cardExpireMonth")) >= java.util.Calendar.getInstance()
								.getTime().getMonth()+1) {
					Maintainance payment = new Maintainance(id, name, email, plotno, maintainance, extra, total);
					if (maintainDAO.payment(payment)) {
						PrintWriter out = response.getWriter();
						out.println(
								"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
						out.println(
								"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
						out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n"
								+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n"
								+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");

						out.print("<script>setTimeout(function () { \r\n" + "swal({\r\n"
								+ "  title: \"Payment Successful!\",\r\n"
								+ "  text: \"You are Payment has been Successfully completed !\",\r\n"
								+ "  type: \"success\",\r\n" + "  confirmButtonText: \"OK\"\r\n" + "},\r\n"
								+ "function(isConfirm){\r\n" + "  if (isConfirm) {\r\n"
								+ "    window.location.href = \"userPaidBill\";\r\n" + "  }\r\n"
								+ "}); }, 1000);</script>");
					}
				}
				else {
					PrintWriter out = response.getWriter();
					out.println(
							"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
					out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
					out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n"
							+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n"
							+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");

					out.print("<script>setTimeout(function () { \r\n" + "swal({\r\n" + "  title: \"OOPS!\",\r\n"
							+ "  text: \"Enter Valid Month & Year !\",\r\n" + "  type: \"error\",\r\n"
							+ "  confirmButtonText: \"OK\"\r\n" + "},\r\n" + "function(isConfirm){\r\n"
							+ "  if (isConfirm) {\r\n" + "    window.location.href = \"userUnpaidBill\";\r\n" + "  }\r\n"
							+ "}); }, 1000);</script>");
				}
			}else {
				PrintWriter out = response.getWriter();
				out.println(
						"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n"
						+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n"
						+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");

				out.print("<script>setTimeout(function () { \r\n" + "swal({\r\n" + "  title: \"OOPS!\",\r\n"
						+ "  text: \"Enter Valid Details !\",\r\n" + "  type: \"error\",\r\n"
						+ "  confirmButtonText: \"OK\"\r\n" + "},\r\n" + "function(isConfirm){\r\n"
						+ "  if (isConfirm) {\r\n" + "    window.location.href = \"userUnpaidBill\";\r\n" + "  }\r\n"
						+ "}); }, 1000);</script>");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println(
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");

			out.print("<script>setTimeout(function () { \r\n" + "swal({\r\n" + "  title: \"OOPS!\",\r\n"
					+ "  text: \"Enter Valid Card Number !\",\r\n" + "  type: \"error\",\r\n"
					+ "  confirmButtonText: \"OK\"\r\n" + "},\r\n" + "function(isConfirm){\r\n"
					+ "  if (isConfirm) {\r\n" + "    window.location.href = \"userUnpaidBill\";\r\n" + "  }\r\n"
					+ "}); }, 1000);</script>");
		}

	}

	private static boolean validateCreditCardNumber(String str) {
		int[] ints = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ints[i] = Integer.parseInt(str.substring(i, i + 1));
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void showPaymentPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Maintainance exisitingBill = maintainDAO.getExistBill(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BillPayment.jsp");
		request.setAttribute("bill", exisitingBill);
		dispatcher.forward(request, response);
	}

	private void listUnpaidBills(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Maintainance> listMaintainance = maintainDAO.listAllMaintainace();
		request.setAttribute("listMaintain", listMaintainance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Unpaid.jsp");
		dispatcher.forward(request, response);

	}

	private void listPaidBills(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Maintainance> listMaintainance = maintainDAO.allPaidBills();
		request.setAttribute("listMaintain", listMaintainance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Paid.jsp");
		dispatcher.forward(request, response);

	}

	private void userUnpaid(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Maintainance m = new Maintainance();
		m.setEmail(email);
		List<Maintainance> list = maintainDAO.userUnPaidMaintainance(m);
		request.setAttribute("listMaintain", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userUnpaid.jsp");
		dispatcher.forward(request, response);
	}

	private void userPaid(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Maintainance m = new Maintainance();
		m.setEmail(email);
		List<Maintainance> list = maintainDAO.userPaidMaintainance(m);
		request.setAttribute("listMaintain", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userPaid.jsp");
		dispatcher.forward(request, response);
	}

	private void listMaintainance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Maintainance> listMaintainance = maintainDAO.listAllMaintainace();
		request.setAttribute("listMaintain", listMaintainance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MaintainanceGeneration.jsp");
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub

		maintainDAO.insertMaintainance();
		response.sendRedirect("ViewMaintainance");

	}
}
