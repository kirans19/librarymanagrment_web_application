<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
    .btn btn-danger pull-right{
   margin-left: 50px;
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
			<form action="Logout">
				<input type="submit" class="btn btn-danger pull-right" value="Logout">
				</form>
		</nav>
	</header>
	<c:if test="${usermode<=2}">
	<br>
	<h3 class="text-left"><button class="btn btn-success" onclick="window.location.href='ReaderMenu.jsp';">Back</button></h3>
	<h3 align="center">Reserve Book</h3>
<div class="container col-md-5" align="left">

		<div class="card">
			<div class="card-body">
			
<form action="bookReservation" method="post">
<fieldset class="form-group">
<label for="iSBN">Enter ISBN:</label><br>
  <input type="text" id="lname" name="ISBN" class="form-control" required="required"  pattern="^(0|[1-9][0-9]*)$"><br><br>
  <input type="submit"  class="btn btn-success" value="Submit">
  </fieldset>
</form>
</div>
</div>
</div>
<br>
<form action="bookReservation">
<div class="scroll">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			
			<hr>
			<div class="container text-left">

<table class="table table-bordered">
<thead>
<tr>
					<th>ISBN</th>
						<th>Book Title</th>
						<th>Book Price</th>
						<th>Edition</th>
						<th>Category</th>
						<th>Publisher Id</th>
						<th>Author Number</th>
						<th>Book Description</th>
						<th>Published Year</th>
					</tr>
</thead>
<tbody>
<c:forEach var="bookList" items="${bookList}">
<c:if test="${bookList.count > 0}"> 
<tr>


	
	<td><c:out value="${bookList.ISBN}" /></td>
	<td><c:out value="${bookList.title}" /></td>
	<td><c:out value="${bookList.price}" /></td>
	<td><c:out value="${bookList.edition}" /></td>
	<td><c:out value="${bookList.category}" /></td>
	<td><c:out value="${bookList.publisherId}" /></td>
	<td><c:out value="${bookList.authorNo}" /></td>
	<td><c:out value="${bookList.bookInfo}" /></td>
	<td><c:out value="${bookList.publishedYear}" /></td>

</tr>
</c:if>
</c:forEach>

</tbody></table>

</div>
</div>
</div>
</div>
</form>
</c:if>
</body>
</html>