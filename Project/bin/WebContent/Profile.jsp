<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="icon"
	href="https://mhgbtcuwzn-2-flywheel.netdna-ssl.com/wp-content/uploads/2019/12/cropped-virtusa-favicon-32x32.png">
<link rel="stylesheet" href="css/bootstrap.min.css"">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
}

.collapse ul li {
	margin-left: 10px;
	margin-right: 25px;
}

nav {
	background-color: #e3f2fd;
}

a {
	color: #fff;
}

.fa {
	margin-right: 8px;
}

nav li {
	padding-left: 5px;
}

.footer {
	text-align: center;
	background-color: black;
	color: white;
	padding-top: 10px;
	padding-bottom: 10px;
}

.footertext {
	text-transform: uppercase;
}
</style>
<title>Admin HomePage</title>
</head>

<body>
	<%
		//		response.setHeader("Pragma", "no-Cache");

	if (session.getAttribute("username") == null) {
		response.setHeader("Cache-Control", "no-cache,no-store,no-transform,private,must-revalidate");
		response.sendRedirect("Login.jsp");
	}

	//out.print(request.getAttribute("username"));
	%>

	<nav class="navbar navbar-light bg-light">
		<span class="navbar-brand mb-0 h1"><img src="images/logo.png"
			style="width: 150px; height: 60px"></span>
		<h3>ONLINE SOCIETY FINANCIAL MANAGEMENT</h3>
		<h5>Hello ${username }&emsp;&emsp;</h5>

	</nav>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>



	<nav class="navbar navbar-expand-lg bg-dark navbar-light"
		style="color: #ffffff">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nv-item active"><a class="nav-link"
					style="color: #ffffff" href="Welcome.jsp">Home<span
						class="sr-only">(current)</span></a></li>

				<c:if test="${role!='user' }">
					<li class="nav-item"><a class="nav-link"
						style="color: #ffffff" href="UserList.jsp">Manage Users</a></li>
						
				</c:if>
				<c:if test="${role == 'admin'}">
					<li class="nav-item"><a class="nav-link"
						style="color: #ffffff" href="EmployeeList.jsp">Manage
							Employees</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" style="color: #ffffff"
					href="eventList.jsp">Events</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: #ffffff" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Services </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="getbills">paid bills</a> <a
							class="dropdown-item" href="paybill">unpaid bills</a>
							 <c:if test="${role=='admin' }"><a class="dropdown-item" href="EventBills">Event Bills</a></c:if>
							<c:if test="${role=='employee' }">
							<a class="dropdown-item" href="FeedbackList.jsp">Feedbacks</a>
							<a class="dropdown-item" href="MaintainanceGeneration.jsp">Generate Maintainance</a>
							<a class="dropdown-item" href="Remainder">Payment Remainder</a>
							</c:if>
							
					</div></li>

			</ul>
			<form class="form-inline my-2 my-lg-0">
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link active" href="Profile.jsp"><i
							class="fa fa-user" aria-hidden="true"></i>View Profile</a></li>
					<li class="nav-item"><a class="nav-link"
						href="changePassword.jsp"><i class="fa fa-lock"
							aria-hidden="true"></i>Chanage Password</a></li>

					<li class="nav-item"><a class="nav-link" href="Logout"><i
							class="fa fa-sign-out" aria-hidden="true"></i>Logout</a></li>

				</ul>
			</form>
		</div>
	</nav>
<c:if test="${role=='user' }">
<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container" style="width: 800px">
			<h3 class="text-center">View Profie</h3>
			<hr>
			<div class="container text-left">

					<a href="<%=request.getContextPath()%>/profile" class="btn btn-primary">Show my Profile</a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				
			</div>
			<br>
			
			<fieldset class="form-group" hidden="">
					<label>id</label> <input type="text"
						value="<c:out value='${Profile.id}' />" class="form-control"
						name="id">
				</fieldset>
							

				
				
				<table class="table table-bordered transparent">
				
				<tr>
				<td colspan="2" style="padding-right: 35px;"><label>Name</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.name}' />" class="form-control"
						name="name"></td>
						<td><label>Phone</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.phone}' />" class="form-control"
						name="name"></td>
				</tr>
				<tr>
				<td colspan="3"><label>Email ID</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.email}' />" class="form-control"
						name="email"></td>
				</tr>
				<tr>
				<td style="padding-right: 35px;"><label>Family Count: </label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.familymembercount}' />" class="form-control"
						name="phone"></td>
						<td colspan="2"><label>Checkin Time</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.checkin}' />" class="form-control"
						name="name"></td>
						</tr>
				<tr>
				<td style="padding-right: 35px;"><label>Country</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.country}' />" class="form-control"
						name="country"></td>
					<td style="padding-right: 35px;"><label>State</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.state}' />" class="form-control"
						name="email"></td>
					<td><label>District </label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.district}' />" class="form-control"
						name="phone"></td>
				</tr>
				<tr>
				<td style="padding-right: 35px;"><fieldset class="form-group">
					<label>Plot Type: </label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.plotType}' />" class="form-control"
						name="phone">
				</fieldset></td>
				<td style="padding-right: 35px;">
				<fieldset class="form-group">
					<label>Plot Number</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.plotno}' />" class="form-control"
						name="name">
				</fieldset></td>
					<td>
				<fieldset class="form-group">
					<label>Rent Amount</label> <input type="text" readonly="readonly"
						value="<c:out value='${Profile.rent}' />" class="form-control"
						name="email">
				</fieldset>
				</td>
				</tr>
				<tr><td colspan="3"><a class="btn btn-success" style="width: 250px;margin-left: 100px" href="editUser?id=<c:out value='${Profile.id}'/>">Edit Profile</a></td></tr>
				</table>
		</div>
	</div>
	</c:if>
</body>

</html>

