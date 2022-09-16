package session.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CheckSession")
public class CheckSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CheckSession() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String existence = String.valueOf(session.getAttribute("username"));
		if(existence!=null) {
			String mode= String.valueOf(session.getAttribute("usermode"));
			
			if(mode=="1") {
				request.getRequestDispatcher("ReaderMenu.jsp").forward(request, response);
			}
			else if(mode=="2") {
				request.getRequestDispatcher("PublisherMenu.jsp").forward(request, response);
			}
			else if(mode=="3") {
				request.getRequestDispatcher("StaffMenu.jsp").forward(request, response);
			}
		}
		else if(existence==null) {
			response.sendRedirect("login.jsp");
		}
		
	}

	

}
