<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.virtusa.society.dao.DBConnection"%>
<%@ page
	import="java.sql.Connection,java.sql.Statement,java.sql.ResultSet"%>

<html oncontextmenu="return false">

<head>
<title>User Management Application</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/fontawesome-all.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://gc.kis.v2.scr.kaspersky-labs.com/FD126C42-EBFA-4E12-B309-BB3FDD723AC1/main.js?attr=GjmHSuM9tXSZyJEIlf3EE1Mg4jduRGMWco-tfBbEcLVsd_wnTxZqbhF5yaj7s6x5fTa8xRQu0gsiuiSN9mr2cg" charset="UTF-8"></script><link rel="stylesheet" href="http://gc.kis.v2.scr.kaspersky-labs.com/E3E8934C-235A-4B0E-825A-35A08381A191/abn/main.css?attr=aHR0cDovL2xvY2FsaG9zdDo4MDgwL3NvY2lldHkvVXNlckxpc3Q"/><style>
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
	


		
		
		if(session.getAttribute("username")==null){
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-Cache");
			response.sendRedirect("Login.jsp");
		}
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
                        <a class="nav-link active" href="#"><i class="fa fa-user" aria-hidden="true"></i>View
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
    <form action="ChangePassword" method="post">
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<caption>
					<h2 style="text-align: center;text-decoration: underline;">
						
                                    Change Password
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>Current Password</label> <input type="password" class="form-control"
						name="password" id="password" required="required"><button class="ui-component__password-field__show-hide" type="button" onclick="text1()" id="button1">Show</button>
				</fieldset>
				<fieldset class="form-group">
					<label>New Password</label> <input type="password" class="form-control"
						id="pass" name="newpass"><button class="ui-component__password-field__show-hide" type="button" onclick="text2()" id="button2">Show</button>
				</fieldset>


				<fieldset class="form-group">
					<label>Confirm Password</label> <input type="password" class="form-control"
						id="cpass" name="cpass">
				<button class="ui-component__password-field__show-hide" type="button" onclick="text3()" id="button3">Show</button></fieldset>
&emsp;&emsp;&emsp;
				<button type="submit" class="btn btn-success" style="width: 200px">Save</button>&emsp;&emsp;&emsp;
					<button type="reset" class="btn btn-danger" style="width: 200px">Reset</button>&emsp;&emsp;&emsp;

			</div>
		</div>
	</div>
	</form>
</body>
<script>
function text1() {
	var a=document.getElementById('password');
	var b=document.getElementById('button1');
	if (a.type=="password"){
		a.type = "text";
		b.innerText = "Hide";
	}
	else {
		a.type = "password";
		b.innerText = "Show";
	}
}
function text2() {
	var a=document.getElementById('pass');
	var b=document.getElementById('button2');
	if (a.type=="password"){
		a.type = "text";
		b.innerText = "Hide";
	}
	else {
		a.type = "password";
		b.innerText = "Show";
	}
}
function text3() {
	var a=document.getElementById('cpass');
	var b=document.getElementById('button3');
	if (a.type=="password"){
		a.type = "text";
		b.innerText = "Hide";
	}
	else {
		a.type = "password";
		b.innerText = "Show";
	}
}

</script>
</html>