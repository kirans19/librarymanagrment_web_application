<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
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
<body>
<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: purple">
			<div>
				<h4 style="color:#D1D9E1;">Library Management App&nbsp;&nbsp;</h4>
			</div>
		</nav>
</header><br>
<h3 class="text-left"><button class="btn btn-success" onclick="window.location.href='login.jsp';">Back</button></h3>
 <div align="center">
 
<br>

  <div class="container">
			<h3 class="text-center">Registration</h3>
			<p style="text-align:center">${message2}</p>

  <form action="<%= request.getContextPath() %>/register" method="post">
   <table class="table table-bordered">
    <tr>
     <td style="text-align:center">First Name</td>
     <td><input type="text" class="form-control" name="first_name" pattern="^[A-Za-z]+}" /></td>
    </tr>
    <tr>
     <td style="text-align:center">Last Name</td>
     <td><input type="text" class="form-control" name="last_name" pattern="^[A-Za-z]+"/></td>
    </tr>
    <tr>
     <td style="text-align:center">Mail ID</td>
     <td><input type="email" class="form-control" name="email" /></td>
    </tr>
    <tr>
     <td style="text-align:center">Password</td>
     <td><input type="password" class="form-control" name="password" /></td>
    </tr>
    <tr>
     <td style="text-align:center">Address</td>
     <td><input type="text" class="form-control" name="address"/></td>
    </tr>
    
    <tr>
     <td style="text-align:center">Mobile No</td>
     <td><input type="tel" class="form-control" name="mobile_no"  pattern="^[0-9]{10}$" /></td>
    </tr>
  
    <tr>
    <td style="text-align:center">User Type</td>
    <td>
    <select id="mySelect" name="user_type">
    
    <option value="1">Reader</option>
    <option value="2">Publisher</option>
    <option value="3">Staff</option>
  </select>
  </td>
    </tr>
   </table>
   <input type="submit"   class="btn btn-success" value="SignUp" />
  </form>
 </div>
 </div>
</body>
</html>