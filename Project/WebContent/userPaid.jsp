<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html oncontextmenu="return false">

<head>
<title>Your Paid Bills</title>
<link rel="icon" href="https://mhgbtcuwzn-2-flywheel.netdna-ssl.com/wp-content/uploads/2019/12/cropped-virtusa-favicon-32x32.png">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/fontawesome-all.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
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
.breadcrumb{
	width: 100%;
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
    </nav>
    <br>

<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Your Paid Bills</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/userPaidBill" class="btn btn-success">Show My Paid Bills</a>&emsp;&emsp;&emsp;
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Transaction Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Plot No</th>
						<th width="auto">Maintainance Charges</th>
						<th>Fine</th>
						<th>Total</th>
						<th>Paid Time</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="maintain" items="${listMaintain}">

						<tr>
							<td><c:out value="${maintain.id }"/></td>
							<td><c:out value="${maintain.name}" /></td>
							<td><c:out value="${maintain.email}" /></td>
							<td><c:out value="${maintain.plotno}" /></td>
							<td><c:out value="${maintain.maintainace}" /></td>
							<td><c:out value="${maintain.extra}" /></td>
							<td><c:out value="${maintain.total}" /></td>
							<td><c:out value="${maintain.paidtime}" /></td>
							
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
    
</body>
</html>