<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html oncontextmenu="return false">

<head>
<title>User Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/fontawesome-all.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.collapse ul li{
	margin-left:10px;
	margin-right: 25px;
}
.fa {
    margin-right: 8px;
}
a{
	color: #ffffff;
}</style>
</head>

<body>
<%
	
//		response.setHeader("Pragma", "no-Cache");

		
		
		if(session.getAttribute("username")==null){
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.sendRedirect("Login.jsp");
		}
	
		//out.print(request.getAttribute("username"));
	%>

    <nav class="navbar navbar-light bg-light">
  <span class="navbar-brand mb-0 h1"><img src="images/logo.png" style="width: 150px;height: 60px"></span>
  <h3>ONLINE SOCIETY FINANCIAL MANAGEMENT</h3><h5>Hello ${username }&emsp;&emsp;</h5>
  
</nav>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>



    <nav class="navbar navbar-expand-lg bg-dark navbar-light" style="color: #ffffff">

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        
            <ul class="navbar-nav mr-auto">
            
                <li class="nv-item active">
                    <a class="nav-link" style="color: #ffffff" href="Welcome.jsp">Home<span class="sr-only">(current)</span></a>
                </li>
                
                <c:if test="${role != 'user'}">
                <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="UserList.jsp">Manage Users</a>
                  </li></c:if><c:if test="${role == 'admin'}">
                  <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="EmployeeList.jsp">Manage Employees</a>
                  </li></c:if>
                  <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="eventList.jsp">Events</a>
                  </li>
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" style="color: #ffffff" href="#" id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Services
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">paid payments</a>
                        <a class="dropdown-item" href="#">unpaid payments</a>
                        <c:if test="${role == 'admin'}"><a class="dropdown-item" href="#">Event Bills</a></c:if></div>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <ul class="nav justify-content-end">
                    <li class="nav-item">
                        <a class="nav-link active" href="Profile.jsp"><i class="fa fa-user" aria-hidden="true"></i>View
                            Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="changePassword.jsp"><i class="fa fa-lock" aria-hidden="true"></i>Chanage Password</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>
                    </li>

                </ul>
            </form>
        </div>
    </nav>
    <div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Events</h3>
			<hr>
			<div class="container text-left">
		<c:if test="${role != 'user'}">
				<a href="<%=request.getContextPath()%>/newEvent" class="btn btn-success">Add
					New Event</a>&emsp;&emsp;&emsp;</c:if>
					<a href="<%=request.getContextPath()%>/Eventlist" class="btn btn-success">Show Events</a>&emsp;&emsp;&emsp;
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Event Name</th>
						<th>Event Location</th>
						<th>Chief Guest</th>
						<th>Amount</th>
						<th>Contact</th>
						<th>Event Date</th>
						<c:if test="${role != 'user'}"><th>Actions</th></c:if>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="event" items="${listEvents}">

						<tr>
							<td><c:out value="${event.eventId}" /></td>
							<td><c:out value="${event.eventName}" /></td>
							<td><c:out value="${event.eventLocation}" /></td>
							<td><c:out value="${event.chiefGuest}" /></td>
							<td><c:out value="${event.amount}" /></td>
							<td><c:out value="${event.contactEmail}" /></td>
							<td><c:out value="${event.dateofevent}" /></td>
							<c:if test="${role != 'user'}">
							<td><a class="btn btn-success" href="editEvent?eventid=<c:out value='${event.eventId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-danger"
								href="deleteEvent?eventid=<c:out value='${event.eventId}' />">Delete</a></td>
								</c:if>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
    
</body>
</html>