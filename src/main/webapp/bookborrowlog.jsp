<%@ page import="java.sql.*"%>  
<%@ page import="database.connection.DBConnection"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Libray Management Application</title>
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
<%
try{
Connection con=DBConnection.getConnection();
PreparedStatement ps=con.prepareStatement("SELECT user_data.id,user_data.first_name,user_data.last_name,book_borrowlog.ISBN,book_catalogue.title,book_borrowlog.issue_date,book_borrowlog.return_date,book_borrowlog.due_amount,book_borrowlog.status FROM user_data INNER JOIN book_borrowlog INNER JOIN book_catalogue on book_borrowlog.ISBN=book_catalogue.ISBN WHERE user_data.id=book_borrowlog.id ORDER BY issue_date DESC;");
ResultSet resultset=ps.executeQuery();
%>

<div class="row">

		<div class="container">
			<br>
			<h3 class="text-center">Book Borrow Report</h3>
			<hr>
			<br>
			<table class="table table-bordered"> 
<thead>
     <tr>
		<th>Reader ID</th>
		<th>Name</th>
		<th>ISBN</th>
		<th>Book Name</th>
		<th>Issue Date</th>
		<th>Return Date</th>
		<th>Due Amount</th>
		<th>Book Returned/Reserved</th>
	</tr>
</thead>
<tbody>
<% while(resultset.next()){ %>

 <TR>
     <TD> <%= resultset.getString(1) %></TD>
     <TD> <%= resultset.getString(2) %> <%= resultset.getString(3) %></TD>
     <TD> <%= resultset.getString(4) %></TD>
     <TD> <%= resultset.getString(5) %></TD>
     <TD> <%= resultset.getString(6) %></TD>
   
       <TD><%= resultset.getString(7) %></TD>
     <% if(resultset.getString(8).equals("0")){%>
     <TD> - </TD>
     <%} else { %>
     <TD> <%= resultset.getString(8) %></TD>
     <% } %>
     
     <% if(resultset.getString(9).equals("0")){%>
     <TD> RESERVED </TD>
     <%} else { %>
     <TD> RETURNED</TD>
     <% } %>
     
 </TR> 
  
<% } %>
</tbody>	
</table>
</div>
</div>
<% con.close();
}
catch(SQLException e){
	System.out.println("Error");
	e.printStackTrace();
}
%>
</c:if>
 </body>
 </html>