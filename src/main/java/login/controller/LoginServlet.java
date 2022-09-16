package login.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import html.encode.HTMLEscape;
import login.dao.LoginDao;
import login.model.LoginBean;
import password.hash.PasswordHashing;
import session.dao.SessionDao;
import session.model.Session;
import validations.MailValidation;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String LoginSuccessfull = String.valueOf(session.getAttribute("username"));
		System.out.println(LoginSuccessfull);
		
		String username=request.getParameter("username");
		username=HTMLEscape.htmlEncode(username);
		String password1=request.getParameter("password");
		password1=HTMLEscape.htmlEncode(password1);
		String password=PasswordHashing.doHashing(password1);
		
		LoginBean loginBean=new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		
		LoginDao loginDao=new LoginDao();
		if(loginDao.validate(loginBean).equals("3")) {
			int usermode=3;
			//HttpSession session=request.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("usermode", usermode);
			session.setAttribute("password1", password1);
			try {
				saveSessionDetails(request,response,session,username);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("StaffMenu.jsp");
		}
		else if(loginDao.validate(loginBean).equals("1")){
			int usermode=1;
			//HttpSession session=request.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("usermode", usermode);
			session.setAttribute("password1", password1);
			try {
				saveSessionDetails(request,response,session,username);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("ReaderMenu.jsp");
		}
		else if(loginDao.validate(loginBean).equals("2")){
			int usermode=2;
			//HttpSession session=request.getSession(true);
			session.setAttribute("username", username);  
			session.setAttribute("usermode", usermode);
			session.setAttribute("password1", password1);
			try {
				saveSessionDetails(request,response,session,username);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("PublisherMenu.jsp");
			
		}
		else if(loginDao.validate(loginBean).equals("0")) {
			String message1 = "You Have Been Banned!!";
			request.setAttribute("message1", message1);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			String message1 = "Invalid Login!!";
			request.setAttribute("message1", message1);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	protected void saveSessionDetails(HttpServletRequest request, HttpServletResponse response,HttpSession session,String username) throws ServletException, IOException, SQLException {
		
		String session_id=request.getSession().getId();
		String creation_time=new Time(session.getCreationTime()) +" "+new Date(session.getCreationTime());
		String browser_details;
		
        
        String userAgent = "Unknown";
        String osType = "Unknown";
        String osVersion = "Unknown";
        String browserType = "Unknown";
        String browserVersion = "Unknown";

        try {
            userAgent = request.getHeader("User-Agent");
            boolean exceptionTest = false;
            if(exceptionTest) throw new Exception("EXCEPTION TEST");

            if (userAgent.indexOf("Windows NT") >= 0) {
                osType = "Windows";
                osVersion = userAgent.substring(userAgent.indexOf("Windows NT ")+11, userAgent.indexOf(";"));

            } else if (userAgent.indexOf("Mac OS") >= 0) {
                osType = "Mac";
                osVersion = userAgent.substring(userAgent.indexOf("Mac OS ")+7, userAgent.indexOf(")"));

            } else if (userAgent.indexOf("X11") >= 0) {
                osType = "Unix";
                osVersion = "Unknown";

            } else if (userAgent.indexOf("android") >= 0) {
                osType = "Android";
                osVersion = "Unknown";
            }
            System.out.println("end of os section");

            if (userAgent.contains("Edge/")) {
                browserType = "Edge";
                browserVersion = userAgent.substring(userAgent.indexOf("Edge")).split("/")[1];

            } else if (userAgent.contains("Safari/") && userAgent.contains("Version/")) {
                browserType = "Safari";
                browserVersion = userAgent.substring(userAgent.indexOf("Version/")+8).split(" ")[0];

            } else if (userAgent.contains("Chrome/")) {
                browserType = "Chrome"; 
                browserVersion = userAgent.substring(userAgent.indexOf("Chrome")).split("/")[1];
                browserVersion = browserVersion.split(" ")[0];

            } else if (userAgent.contains("Firefox/")) {
                browserType = "Firefox"; 
                browserVersion = userAgent.substring(userAgent.indexOf("Firefox")).split("/")[1];
            }            
            System.out.println("end of browser section");

        } catch (Exception ex) {
        	System.out.println("ERROR: " +ex);            
        }
        
       browser_details=osType+" "+osVersion+" "+browserType+" "+browserVersion;
       String id=LoginDao.id;
       Session sessionObj=new Session(id,session_id,creation_time,browser_details);
       new SessionDao().addUserSession(sessionObj);

	}

}
