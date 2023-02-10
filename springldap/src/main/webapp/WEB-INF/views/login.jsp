<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="today" class="java.util.Date" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Applied Bank Portal</title>
<link rel="shortcut icon" type="image/x-icon" href="resources/images/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="https://content.appliedbank.com/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/login.css">

<script type="application/javascript" src="https://content.appliedbank.com/vendor/jquery/jquery.min.js"></script>
<script type="application/javascript" src="https://content.appliedbank.com/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="application/javascript" src="https://content.appliedbank.com/vendor/jquery.moment/moment.js"></script>

</head>
<body id="LoginForm">
<div class="container">
	<div class="login-form">
		<div class="main-div">
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">Applied Bank Portal Login</div>
				</div>
				<c:if test = "${errorMessage == ''}">
					<p>Please enter your user name and password</p>
				</c:if>
				<c:if test = "${errorMessage != ''}">
					<span class="error-message">${errorMessage}</span><br/>
				</c:if>
			</div>
			<form id="Login" action="login" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="username" name="username" placeholder="User Name" value="">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password" name="password" autocomplete="off" placeholder="Password" value="">
				</div>
<!-- 				<div class="form-group">
					<select class="form-control" id="domain" name="domain">
					  <option value="acsbnt">ACSBNT</option>
					  <option value="acsnt">ACSNT</option>
					  <option value="ccbbank">CCB</option>
					  <option value="acsfo">ACSFO</option>
					</select>
				</div> -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<br>
				<button type="submit" class="btn btn-default submitBtn">Login</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>