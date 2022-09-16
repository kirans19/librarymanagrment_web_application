package session.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import session.dao.SessionDao;
import session.model.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Session> userSessionList=null;
		try {
			userSessionList = new SessionDao().userSession();
			System.out.println("Hi"+userSessionList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("userSessionList", userSessionList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("session-viewer.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
