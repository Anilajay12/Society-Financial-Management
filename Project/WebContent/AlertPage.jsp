<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html oncontextmenu="return false">

<head>
<title>Send Alert</title>
<link rel="icon" href="https://mhgbtcuwzn-2-flywheel.netdna-ssl.com/wp-content/uploads/2019/12/cropped-virtusa-favicon-32x32.png">
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
	
//		response.setHeader("Pragma", "no-Cache");

		
		
		if(session.getAttribute("username")==null){
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.print("<script src=\"https://code.jquery.com/jquery-2.1.3.min.js\"></script>\r\n" + 
					"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert-dev.js\"></script>\r\n" + 
					"  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css\">");
			
			out.print("<script>setTimeout(function () { \r\n" + 
					"swal({\r\n" + 
					"  title: \"oops!\",\r\n" + 
					"  text: \"Please Login Again!\",\r\n" + 
					"  type: \"warning\",\r\n" + 
					"  confirmButtonText: \"OK\"\r\n" + 
					"},\r\n" + 
					"function(isConfirm){\r\n" + 
					"  if (isConfirm) {\r\n" + 
					"    window.location.href = \"Login.jsp\";\r\n" + 
					"  }\r\n" + 
					"}); }, 1000);</script>");
			//response.sendRedirect("Login.jsp");
		}
	
		//out.print(request.getAttribute("username"));
	%>

    <nav class="navbar navbar-light bg-light">
  <span class="navbar-brand mb-0 h1"><img src="images/logo.png" style="width: 150px;height: 60px"></span>
  <h3>ONLINE SOCIETY FINANCIAL MANAGEMENT</h3><h5>Hello ${username}&emsp;&emsp;</h5>
  
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
                
                <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="UserList.jsp">Manage Users</a>
                  </li>
                  <c:if test="${role == 'admin'}">
                  <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="EmployeeList.jsp">Manage Employees</a>
                  </li></c:if>
                  <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="Eventlist">Events</a>
                  </li>
                
                 <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: #ffffff" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Services </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<c:if test="${role=='user' }"><a class="dropdown-item" href="userPaidBill">Your paid bills</a> <a
							class="dropdown-item" href="userUnpaidBill">Your unpaid bills</a></c:if>
						<c:if test="${role!='user' }"><a class="dropdown-item" href="PaidBills">Paid bills</a> <a
							class="dropdown-item" href="UnpaidBills">Unpaid bills</a></c:if>
							 <c:if test="${role=='admin' }"><a class="dropdown-item" href="Reports">Reports</a></c:if>
							<c:if test="${role=='employee' }">
							<a class="dropdown-item" href="FeedbackList">Feedbacks</a>
							<a class="dropdown-item" href="MaintainanceGeneration.jsp">Maintainance</a>
							<a class="dropdown-item" href="AlertPage.jsp">Send Alert</a>
							</c:if>
							
					</div></li>


            </ul>
            <form class="form-inline my-2 my-lg-0">
                <ul class="nav justify-content-end"><c:if test="${role== 'user'}">
                    <li class="nav-item">
                        <a class="nav-link active" href="profile"><i class="fa fa-user" aria-hidden="true"></i>View
                            Profile</a>
                    </li></c:if>
                    <c:if test="${role== 'employee'}">
                    <li class="nav-item">
                        <a class="nav-link active" href="Employeeprofile"><i class="fa fa-user" aria-hidden="true"></i>View
                            Profile</a>
                    </li></c:if>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="Remainder" method="post">
						<caption>
							<h2 style="text-align: center;text-decoration: underline;">Send Email</h2>
						</caption>

						<fieldset class="form-group">
							<label>Enter the Subject</label> <input type="text" class="form-control" name="subject" required="required">
						</fieldset>
					

						<fieldset class="form-group">
							<label>Event Message</label> <textarea name="message" rows="10" cols="25" class="form-control" required="required"></textarea>
						</fieldset>
						
						<button type="submit" class="btn btn-success" style="width: 350px">Send</button>&emsp;&emsp;&emsp;
						
					</form>
				
			</div>
		</div>
	</div>
</body>
</html>