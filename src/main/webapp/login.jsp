<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Library Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<script type="text/javascript">
        function preventBack() {
            window.history.forward(); 
        }
          
        setTimeout("preventBack()", 0);
          
        window.onunload = function () { null };
    </script>
</head>
<body >
<c:set var="username" value="${username}" scope="session" />
<c:set var="password1" value="${password1}" scope="session" />
<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color:purple">
			<div>
				<h4 style="color:#D1D9E1;">Library Management App</h4>
			</div>
		</nav>
</header>
<div align="center">
<div class="container col-md-5">
		
<br>
<br>
<br>
<br>
<br>
<br>
<br><div class="card mb-4 border-0">
			<div class="card-body">

			<h3 class="text-center">Enter Email And Password</h3>
		

<form action="login" method="post" >
<p>${message1}</p>
<br>
<table class="table table-bordered">
<tr><td>Email-id: </td><td><input type="email"  class="form-control" name="username" autocomplete="off" value="${username}" /></td></tr>
<tr><td>Password: </td><td><input type="password" class="form-control" name="password"  autocomplete="off" value="${password1}" /></td></tr>
</table>
<input type="submit" class="btn btn-success" value="Login" width="110" height="50">

</form>
<br>
<table> 
	<tbody>
		<tr>
		<td  style="text-align:center">New User ?<a href="registration.jsp">Register</a><br></td>
		</tr>
	</tbody>
</table>


</div>
</div>
</div>
</div>
</body>
</html>


