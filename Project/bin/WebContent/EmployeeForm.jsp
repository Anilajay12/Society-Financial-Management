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
  <h3>ONLINE SOCIETY FINANCIAL MANAGEMENT</h3><h5>Hello ${username}</h5>
  
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
                    
                
                <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="UserList.jsp">Manage Users</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="EmployeeList.jsp">Manage Employees</a>
                  </li>
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
    </nav><br><br><div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${employee != null}">
					<form action="updateEmployee" method="post">
				</c:if>
				<c:if test="${employee == null}">
					<form action="insertEmployee" method="post">
				</c:if>
				<c:if test="${employee != null}">
                    <input type="hidden" name="eid" value="<c:out value='${employee.eid}' />" />
                </c:if>
				<caption>
					<h2 style="text-align: center;text-decoration: underline;">
						<c:if test="${employee != null}">
                                    Edit Employee
                                </c:if>
						<c:if test="${employee == null}">
                                    Add New Employee
                                </c:if>
					</h2>
				</caption>

				
				
				<fieldset class="form-group">
					<label>Employee Name</label> <input type="text"
						value="<c:out value='${employee.ename}' />" class="form-control"
						name="ename">
				</fieldset>

				<fieldset class="form-group">
					<label>Email ID</label> <input type="text"
						value="<c:out value='${employee.email}' />" class="form-control"
						name="email">
				</fieldset>
			
				<c:if test="${employee == null}">
				<fieldset class="form-group">
					<label>Gender: </label> &emsp;&emsp;&emsp;
					<select name="gender">
						<option selected="selected">Male</option>
						<option>Female</option>
						<option>Other</option>
					</select>
				</fieldset>
				</c:if>
				
				<fieldset class="form-group">
					<label>Date Of Birth</label> <input type="date"
						value="<c:out value='${employee.dob}' />" class="form-control"
						name="dob">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Employee Address</label> <input type="text"
						value="<c:out value='${employee.address}' />" class="form-control"
						name="address" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Employee Phone</label> <input type="text"
						value="<c:out value='${employee.phone}' />" class="form-control"
						name="phone">
				</fieldset>

				<fieldset class="form-group">
					<label>Salary: </label> <input type="text"
						value="<c:out value='${employee.salary}' />" class="form-control"
						name="salary">
				</fieldset>
				
				
				<button type="submit" class="btn btn-success" style="width: 250px;margin-left: 15px;">Save</button>&emsp;&emsp;
				<c:if test="${employee==null }">
					<button type="reset" class="btn btn-danger" style="width: 250px">Reset</button>
				</c:if> 
				</form>
			</div>
		</div>
	</div>

</body>
</html>