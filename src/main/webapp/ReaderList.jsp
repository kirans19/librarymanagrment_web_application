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
	<br>
	<h3 align="center">Readers List</h3>
<br>
<form action="ReaderListServlet">
<div class="scroll">
		<div class="container">
			<hr>
			<div class="container text-left">

<table class="table table-bordered">
<thead>
<tr>
					<th>Reader ID</th>
					<th>Email</th>
						<th>Reader Name</th>
						<th>Mobile No</th>
						<th>Address</th>
					
</tr>
</thead>
<tbody>
<c:forEach var="readerList" items="${readerList}">
<tr>


	<td><c:out value="${readerList.id}" /></td>
	<td><c:out value="${readerList.email}" /></td>
	<td><c:out value="${readerList.first_name}" /><c:out value="${readerList.last_name}" /></td>
	<td><c:out value="${readerList.mobile_no}" /></td>
	<td><c:out value="${readerList.address}" /></td>

</tr>

</c:forEach>

</tbody>
</table>


</div>
</div>
</div>
</div>
</form>
</c:if>
</body>
</html>