<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html oncontextmenu="return false">

<head>
<title>User Management Application</title>
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
						
					<li class="nav-item"><a class="nav-link"
						style="color: #ffffff" href="MaintainanceGeneration.jsp">Generate Maintainance</a></li>
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
						<a class="dropdown-item" href="#">paid payments</a> <a
							class="dropdown-item" href="#">unpaid payments</a> <a
							class="dropdown-item" href="#">Event Bills</a>
					</div></li>

			</ul>
			<form class="form-inline my-2 my-lg-0">
				<ul class="nav justify-content-end">
					<li class="nav-item"><a class="nav-link active" href="#"><i
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
<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Events</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/GenerateMaintainance" class="btn btn-success">Generate Maintainance</a>&emsp;&emsp;&emsp;
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Plot No</th>
						<th width="auto">Maintainance Charges</th>
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
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
    
</body>
</html>