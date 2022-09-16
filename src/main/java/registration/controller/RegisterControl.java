package registration.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import html.encode.HTMLEscape;
import registration.dao.RegistrationDao;
import registration.model.Register;
import validations.MailValidation;
import validations.MobileNumberValidation;
import validations.NameValidation;


@WebServlet("/register")
public class RegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private RegistrationDao registerDao=new RegistrationDao();
       
   
    public RegisterControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.sendRedirect("registration.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.removeAttribute("message2");
		String email=request.getParameter("email");
		email=HTMLEscape.htmlEncode(email);
		String password=request.getParameter("password");
		password=HTMLEscape.htmlEncode(password);
		String first_name=request.getParameter("first_name").toUpperCase();
		first_name=HTMLEscape.htmlEncode(first_name);
		String last_name=request.getParameter("last_name").toUpperCase();
		last_name=HTMLEscape.htmlEncode(last_name);
		String mobile_no=request.getParameter("mobile_no");
		mobile_no=HTMLEscape.htmlEncode(mobile_no);
		String address=request.getParameter("address").toUpperCase();
		address=HTMLEscape.htmlEncode(address);
		String user_type=request.getParameter("user_type");
		user_type=HTMLEscape.htmlEncode(user_type);
		
		Register register=new Register();
		
		try {
			if(!(email.equals("")&& !MailValidation.isValidMail(email)&&password.equals("")&&first_name.equals("")&&!NameValidation.isValidname(first_name)&&last_name.equals("")&&!NameValidation.isValidname(last_name)&&mobile_no.equals("")&&!MobileNumberValidation.isValidMobileNumber(mobile_no)&&address.equals("")&&user_type.equals("")&&email.equals(null)&&password.equals(null)&&first_name.equals(null)&&last_name.equals(null)&&mobile_no.equals(null)&&address.equals(null) &&user_type.equals(null) )){
			register.setEmail(email);
			register.setPassword(password);
			register.setFirst_name(first_name);
			register.setLast_name(last_name);
			register.setMobile_no(mobile_no);
			register.setAddress(address);
			register.setUser_type(user_type);
			try {
				if(registerDao.registerEmployee(register)>0) {
					response.sendRedirect("login.jsp");
				}
				else {
					String message2 = "Already a User? Or been Banned!!";
					request.setAttribute("message2", message2);
					request.getRequestDispatcher("registration.jsp").forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			}else {
				String message2 = "Fill the fields properly!!";
				request.setAttribute("message2", message2);
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

}
}
