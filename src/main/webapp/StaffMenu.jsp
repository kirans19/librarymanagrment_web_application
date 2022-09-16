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
<br>
<c:if test="${usermode==3}">

<div class="row">

		<div class="container">
			
			<h3 class="text-center">Staff Menu</h3>
			<hr>
			<br>
	<table class="table table-bordered"> 

	<tbody>
		<tr>
		<td>Access Book Catalogue</td>
		<td style="text-align:center">	
		<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/list';">CLICK</button>
		</td>
		</tr>
		
		<tr>
	<td>View Borrow Report</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='bookborrowlog.jsp';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>View Readers List</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/ReaderListServlet';">CLICK</button>
 	</td>
 	</tr>
 	
 	
 	<tr>
	<td>Books Reserved By Readers</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='bookReaders.jsp';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>Due Amount</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='dueSlip.jsp';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>Ban Readers</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/BanReaderList';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>View Publishers List</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/PublisherListServlet';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>Delete Publishers</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/DeletePublisherServlet';">CLICK</button>
 	</td>
 	</tr>
 	
 	
 	
	
	
</tbody>
</table>
</c:if>

</body>
</html>