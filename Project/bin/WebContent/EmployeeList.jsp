<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.virtusa.society.dao.DBConnection"%>
<%@ page
	import="java.sql.Connection,java.sql.Statement,java.sql.ResultSet"%>

<html oncontextmenu="return false">

<head>
<title>User Management Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/fontawesome-all.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://gc.kis.v2.scr.kaspersky-labs.com/FD126C42-EBFA-4E12-B309-BB3FDD723AC1/main.js?attr=GjmHSuM9tXSZyJEIlf3EE1Mg4jduRGMWco-tfBbEcLVsd_wnTxZqbhF5yaj7s6x5fTa8xRQu0gsiuiSN9mr2cg" charset="UTF-8"></script><link rel="stylesheet" crossorigin="anonymous" href="http://gc.kis.v2.scr.kaspersky-labs.com/E3E8934C-235A-4B0E-825A-35A08381A191/abn/main.css?attr=aHR0cDovL2xvY2FsaG9zdDo4MDgwL3NvY2lldHkvVXNlckxpc3Q"/><style>
* {
	padding: 0;
	margin: 0;
	max-width: 100%;
}
.collapse ul li{
	margin-left:10px;
	margin-right: 25px;
}
.fa {
    margin-right: 8px;
}
a{
	color: #ffffff;
}
</style>
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
                <li class="nav-item active">
                    <a class="nav-link" style="color: #ffffff" href="Welcome.jsp">Home<span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${role != 'user'}">
                <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="UserList.jsp">Manage Users</a>
                  </li></c:if>
                  <c:if test="${role == 'admin'}">
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
                        <a class="dropdown-item" href="#">Event Bills</a></div>
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
    </nav><br><br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Employees</h3>
			<hr>
			<div class="container text-left">
				&emsp;&emsp;&emsp;&emsp;
				<a href="<%=request.getContextPath()%>/newEmployee" class="btn btn-success">Add
					New Employee</a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<a href="<%=request.getContextPath()%>/EmployeeList" class="btn btn-primary">Show Current Employees</a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				<a href="<%=request.getContextPath()%>/EmployeeList" class="btn btn-success">Show Past Employees</a>
			</div>
			<br>
			<table class="table table-bordered" style="text-align: center;">
				<thead>
					<tr>
						<th>Employee Id</th>
						<th>Employee Name</th>
						<th>EmailId</th>
						<th width="180px">DOB(YYYY-MM-DD)</th>
						<th>Address</th>
						<th>Contact</th>
						<th>Gender</th>
						<th>Salary</th>
						<th width="250px">Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="emp" items="${listEmployees}">

						<tr>
							<td><c:out value="${emp.eid}" /></td>
							<td style="text-transform: uppercase;"><c:out value="${emp.ename}" /></td>
							<td><c:out value="${emp.email}" /></td>
							<td><c:out value="${emp.dob}" /></td>
							<td style="text-transform: capitalize;"><c:out value="${emp.address}" /></td>
							<td><c:out value="${emp.phone}" /></td>
							<td><c:out value="${fn:toUpperCase(emp.gender)}" /></td>
							<td><c:out value="${emp.salary}" /></td>
							
							<td><a class="btn btn-success" href="editEmployee?eid=<c:out value='${emp.eid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-danger"
								href="deleteEmployee?eid=<c:out value='${emp.eid}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	
</body>
</html>