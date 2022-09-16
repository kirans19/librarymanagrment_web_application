<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

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
			<form action="Logout">
				<input type="submit" class="btn btn-danger pull-right" value="Logout">
				</form>
		</nav>
	</header>
<c:if test="${usermode==3}">
	<br><button class="btn btn-success" onclick="window.location.href='StaffMenu.jsp';">Back</button>
	</c:if>
	<c:if test="${usermode==2}">
	<br><button class="btn btn-success" onclick="window.location.href='PublisherMenu.jsp';">Back</button>
	</c:if>
	<c:if test="${usermode==1}">
	<br><button class="btn btn-success" onclick="window.location.href='ReaderMenu.jsp';">Back</button>
	</c:if>
	
	<br>
	<h3 align="center">Profile</h3>
<br>
<form action="Profile">
<div class="scroll">
		<div class="container">
			<hr>
			<div class="container text-left">

<table class="table table-bordered">

<c:forEach var="userProfile" items="${userProfile}">
<tr>


	<td style="background-color:#F2F2F2"><b>User Id:</b> </td><td><c:out value="${userProfile.id}" /></td>
	</tr>
	<tr><td style="background-color:#F2F2F2"><b>E-mail:</b></td><td><c:out value="${userProfile.email}" /></td></tr>
	<tr><td style="background-color:#F2F2F2"><b>Name:</b></td><td><c:out value="${userProfile.first_name}" /> <c:out value="${userProfile.last_name}" /></td></tr>
	<tr><td style="background-color:#F2F2F2"><b>Mobile No:</b></td><td><c:out value="${userProfile.mobile_no}" /></td></tr>
	<tr><td style="background-color:#F2F2F2"><b>Address:</b></td><td><c:out value="${userProfile.address}" /></td></tr>



</c:forEach>


</table>



</div>
</div>
</div>
</form>
<div class="scroll">
		<div class="container">
			<hr>
			<div class="container text-left">
<table class="table table-bordered"  ><tr><td align="center" ><button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/SessionServlet';">User Activity</button></td></tr></table>

</div>
</div>
</div>
</body>
</html>