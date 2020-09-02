package com.virtusa.society.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.society.dao.UserDAO;

/**
 * Servlet implementation class SendEmailController
 */
@WebServlet("/Remainder")
public class SendEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private UserDAO userDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			List<String> receipents=userDAO.listUserMails();
			String subject=request.getParameter("subject");
			String message=request.getParameter("message");
			PrintWriter out=response.getWriter();
			sendMail(receipents,subject,message,out);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}


	private void sendMail(List<String> receipents, String subject, String message2, PrintWriter out) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Message Sending Statrted");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		final String emailid="your email id";	// xyz@gmail.com
		final String password="your password";	// your email password and enable less secure apps in your google account
		Session session=Session.getDefaultInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(emailid, password);
			}
		});	
		for(String receipent:receipents) {
			Message message= prepareMessage(session,emailid,receipent,subject,message2);
			Transport.send(message);
		}
		System.out.println("Message Sent Successfully");
		
		out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
				"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
				"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
		
		out.print("<script>setTimeout(function () { \r\n" + 
				"swal({\r\n" + 
				"  title: \"Good Job!\",\r\n" + 
				"  text: \"The Emails Has Been Successfully Sent!\",\r\n" + 
				"  type: \"success\",\r\n" + 
				"  confirmButtonText: \"OK\"\r\n" + 
				"},\r\n" + 
				"function(isConfirm){\r\n" + 
				"  if (isConfirm) {\r\n" + 
				"    window.location.href = \"Welcome.jsp\";\r\n" + 
				"  }\r\n" + 
				"}); }, 1000);</script>");
		
		
	}

	private static Message prepareMessage(Session session,String myEmail, String recepient, String subject, String message2) {
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(subject);
			message.setText(message2);
			//message.setSubject("Payment Remainder of Maintainance Bill");
			//message.setText("Dear Customer, You are not paid your maintainance bill.please pay your bill if you are already paid please Ignore this meesage Thank You");
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}