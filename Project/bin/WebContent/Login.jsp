<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS 
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	-->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/fontawesome-all.css" rel="stylesheet">
    <link rel="icon" href="images/favicon.png">
	
    
<style>
* {
	padding: 0;
	margin: 0;
}

.row {
	width: 100%;
}

.carousel-item img {
	height: 300px;
	width: 100%;
}



.col {
	padding-top:30px;
	padding-bottom: 100px;
}

.left {
	margin-top: 50px;
	width: 500px !important;
	margin-right: 80px;
	border: 3px solid black;
	margin-left: 150px;
	/*margin-left: 200px;*/
}

.right {
	background-color: #efefee;
	height: 400px;
	padding-left: 50px;
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

h2 {
	padding-top: 35px;
	margin-left:160px;
	text-transform: uppercase;
	text-decoration: underline;
}

#username {
	width: 30px;
}

ol li {
	padding-left: 50px;
}

.btn {
	width: 200px;
}
.breadcrumb-item{
	font-size: 20px;
}
.home{
	font-size: 25px;
}
</style>
<title>Financial Management Login Page</title>





<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>

<body>

	<nav class="navbar navbar-light bg-light">
  <span class="navbar-brand mb-0 h1"><img src="images/logo.png" style="width: 150px;height: 60px"></span>
  <h3>ONLINE SOCIETY FINANCIAL MANAGEMENT</h3><h5>Hi,Guest&emsp;&emsp;</h5>
  
</nav>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="color: black">
  <ol class="home" style="list-style: none;color: white;">
    <li>Home</li>
  </ol>
</nav>
	<nav aria-label="breadcrumb" class="menubar2" >
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp"><strong>Home</strong></a></li>
			<li class="breadcrumb-item active" aria-current="page">Login</li>
		</ol>
	</nav>

	<div class="row">
		<div class="col">
			<div class="left">

				<div id="carouselExampleInterval" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner" data-interval="10">
						<div class="carousel-item active">
							<img src="images/1.jpeg" class="d-block w-100" alt="Image1">
						</div>
						<div class="carousel-item">
							<img src="images/3.jpg" class="d-block w-100" alt="Image2">
						</div>
						<div class="carousel-item">
							<img src="images/img3.jpg" class="d-block w-100" alt="Image3">
						</div>
						<div class="carousel-item">
							<img src="images/img5.jpg" class="d-block w-100" alt="Image4">
						</div>
						<div class="carousel-item">
							<img src="images/img6.jpg" class="d-block w-100" alt="Image5">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleInterval"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleInterval"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>


			</div>
		</div>
		<div class="col">
			<div class="right">

				<form method="post" action="Login">
					<h2>Login Form</h2><br>
					<div class="form-group">
						<label style="margin-left: 30px">Email address</label> <input name="email" required class="form-control-input"
						 placeholder="Enter EmailId" oninvalid="this.setCustomValidity('Email cannot be empty or Enter valid Email ID')" 
onchange="this.setCustomValidity('')" type="email" style="width: 80%;margin-left: 25px" />
					</div>
					<div class="form-group">
						<label style="margin-left: 30px">Enter Password</label> <input name="password" required class="form-control-input"
						 placeholder="Enter Password" oninvalid="this.setCustomValidity('Enter correct password')" 
onchange="this.setCustomValidity('')" type="password" style="width: 80%;margin-left: 25px" />
					</div>
					<br>&emsp;&emsp;
					<button type="submit" class="btn btn-primary" id="submit">Login</button>
					&emsp;&emsp;&emsp;&emsp;&emsp;
					<button type="reset" class="btn btn-primary" id="reset">Reset</button>
				</form>


			</div>
		</div>
	</div>
	<footer class="footer">
		<p>
			All Copyrights <i class="fa fa-copyright" aria-hidden="true"></i> are Reserved for
		<p class="footertext">Online Society Financial Management</p>
	</footer>


</body>

</html>