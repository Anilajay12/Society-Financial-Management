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
td{
	width:180px;
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
                
                <li class="nav-item">
                    <a class="nav-link" style="color: #ffffff" href="UserList.jsp">Manage Users</a>
                  </li>
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
    <c:if test="${role != 'user' }">
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="updateUser" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insertUser" method="post">
				</c:if>
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				<caption>
					<h2 style="text-align: center; text-decoration: underline;">
						<c:if test="${user != null}">
                                    Edit User
                                </c:if>
						<c:if test="${user == null}">
                                    Add New User
                                </c:if>
					</h2>
				</caption>



				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name">
				</fieldset>

				<fieldset class="form-group">
					<label>Email ID</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>User Phone Number: </label> <input type="text"
						value="<c:out value='${user.phone}' />" class="form-control"
						name="phone">
				</fieldset>

<label>Family Count</label>&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
				<select name="familycount" style="width: 250px">
					<%
						for (int i = 1; i <= 25; i++) {
					%>
					<option>
						<%
							out.print(i);
						%>
					</option>
					<%
						}
					%>
				</select>

				<table>
					<tr>
						<td id="q">Plot type</td>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<select name="plottype" id="id" style="width: 250px">
								<option>Select PlotType</option>
								<%
									Connection con = DBConnection.getConnection();
								Statement st = con.createStatement();
								ResultSet rs = st.executeQuery("select plotid,plottype from plotdetails");
								while (rs.next()) {
								%>
								<option value="<%=rs.getInt("plotid")%>"><%=rs.getString("plottype")%></option>
								<%
									}
								%>
						</select></td>
					</tr>
<br>
					<tr>
						<td id="y">Plot Number</td>
						<td id="msg">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<select name="plotno" selected="plotno" id="pid"><option>Select
									Plot Number</option></select></td><tr><td></td>
					</tr>
					<tr>
						<td id="rent">Rent Amount</td>
						<td id="suc_msg">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<select name="rent"><option
									value="${user.rent }">Rent Amount</option></select></td>
					</tr>
					<c:if test="${user==null }">
					<script src="js/jquery.min.js"></script> 
					<script src="js/country.js"></script>
					<tr><td><label>Select Country</label></td>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<select name="country" class="countries" id="countryId" style="width: 250px">
								<option value="">Select Country</option>
						</select></td>
					</tr>
					<tr>
					<td><label>Select State</label></td>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<select name="state" class="states" id="stateId" style="width: 250px">
								<option value="">Select State</option>
						</select></td>
					</tr>
					<tr><td><label>Select District</label></td>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<select name="city" class="cities" id="cityId" style="width: 250px">
						<option value="">Select City</option>
					</select>

					</c:if>
					
				</table>
				<script src="js/jquery.min.js"></script>
				<script src="js/countrystatedistrict.js"></script>

				<div id="error"></div><br>
				<button type="submit" class="btn btn-success"
					style="width: 250px; margin-left: 15px;">Save</button>
				&emsp;&emsp;
				
				<c:if test="${user == null}">
					<button type="reset" class="btn btn-danger" style="width: 250px">Reset</button>
				</c:if>
				
								</form>
			</div>
		</div>
	</div>
	</c:if>
	
	
	
	<c:if test="${role=='user' }">
		<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="updateProfile" method="post">
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				<caption>
					<h2 style="text-align: center; text-decoration: underline">
						<c:if test="${user != null}">
                                    Edit Your Profile
                                </c:if>
					</h2>
				</caption>



				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name">
				</fieldset>

				<fieldset class="form-group">
					<label>Email ID</label> <input type="text" readonly="readonly"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>User Phone Number: </label> <input type="text" 
						value="<c:out value='${user.phone}' />" class="form-control"
						name="phone">
				</fieldset>
					
				<fieldset class="form-group">
					<label>Family Count</label> <input type="text" 
						value="<c:out value='${user.familymembercount}' />" class="form-control"
						name="familycount">
				</fieldset>
				
				<button type="submit" class="btn btn-success" style="width: 250px; margin-left: 15px;">Update Profile</button>
				&emsp;&emsp;
				
				
				</form>
			</div>
		</div>
	</div>
		
	
	</c:if>
	
	
	
	</body>

</html>
<script>
	$(document).ready(function() {
		$("#id").on("change", function() {
			var id = $("#id").val();
			if (id === "") {
				$("#error").html("required");
				return false;
			} else {
				$("#error").html("");
				$.ajax({
					url : "roomtype.jsp",
					data : {
						id : id
					},
					method : "POST",
					success : function(data) {
						$("#msg").html(data);
						//$("#q").css("color", "red") ;
						//$("#y").css("color", "blue") ;
						//$("#c").css("color", "green") ;
					}

				});
			}
		});
	});
</script>

