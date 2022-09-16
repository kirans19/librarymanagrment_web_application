<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="login.dao.LoginDao"%> 
<!DOCTYPE html>
<html>
<head>
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
    <style>
    .btn-primary{
   	    margin-left: 10px;
   	}
   </style>
</head>

<body>


<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("username")==null)
	response.sendRedirect("login.jsp");
%>

<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: purple">
			<div>
				<h4 style="color:#D1D9E1;">Library Management App&nbsp;&nbsp;</h4>
				
			</div>
			<br> <button class="btn btn-primary" onclick="window.location.href='/LibraryManagementSystemWebApplication/Profile';">Profile</button>
			<form action="Logout">
				<input type="submit" class="btn btn-danger pull-right" value="Logout">
				</form>
		</nav>
</header>
<c:if test="${usermode<=2}">


<div class="row">

		<div class="container">
			<br>
			<%if(LoginDao.mode.equals("2")) {%>
			<button class="btn btn-success" onclick="window.location.href='PublisherMenu.jsp';">Back</button>
			<%} %>
			<h3 class="text-center">Reader Menu</h3>
			<hr>
			<br>
	<table class="table table-bordered"> 

	<tbody>
		<tr>
		<td>Reserve Book</td>
		<td style="text-align:center">	
		<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/bookReservation';">CLICK</button>
		</td>
		</tr>
 	
 	<tr>
	<td>Return Book</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/BookReturnServlet';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>Pay Due Amount</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/CalculateDueAmount';">CLICK</button>
 	</td>
 	</tr>
	
	
</tbody>
</table>
</div>
</div>
</c:if>

</body>
</html>