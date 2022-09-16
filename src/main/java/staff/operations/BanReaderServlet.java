package staff.operations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import html.encode.HTMLEscape;
import validations.NumValidation;

@WebServlet("/BanReaderList")
public class BanReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BanReaderServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<LibraryUsers> bannedReaderList = null;
		try {
			bannedReaderList = new LibraryUserDao().readersToBeBanned();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("bannedReaderList", bannedReaderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BanReader.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		String id=request.getParameter("ISBN");
		id=HTMLEscape.htmlEncode(id);
		if(NumValidation.isValidnum(id)) {
		try {
			flag=new LibraryUserDao().banReader(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			flag=false;
		}
		if(flag) { 
			response.sendRedirect("BanSuccessful.jsp");
		}
		else if(!flag) {
			response.sendRedirect("BanUnsuccessful.jsp");
		}
		
	}

}
