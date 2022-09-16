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

/**
 * Servlet implementation class DeletePublisherServlet
 */

@WebServlet("/DeletePublisherServlet")
public class DeletePublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePublisherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<LibraryUsers> noBorrowPublisherList = null;
		try {
			noBorrowPublisherList = new LibraryUserDao().publishersToBeDeleted();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("noBorrowPublisherList", noBorrowPublisherList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeletePublisher.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag=false;
		String id=request.getParameter("ISBN");
		id=HTMLEscape.htmlEncode(id);
		try {
			flag=new LibraryUserDao().deletePublishers(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			response.sendRedirect("PublisherDelSuccesful.jsp");
		}
		else if(!flag) {
			response.sendRedirect("PublisherDelUnsuccessful.jsp");
		}
	}

}
