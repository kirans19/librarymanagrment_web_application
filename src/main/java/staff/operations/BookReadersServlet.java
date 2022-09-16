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

import book.dao.ReturnAndReserveBooksDao;
import catalogue.model.BookCatalogue;
import html.encode.HTMLEscape;
import validations.NumValidation;

/**
 * Servlet implementation class BookReaders
 */
@WebServlet("/BookReaders")
public class BookReadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookReadersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {
		System.out.print(id);
		List<BookCatalogue> returnBooks = null;
		try {
			returnBooks = new ReturnAndReserveBooksDao().booksToBeReturned(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("returnBooks", returnBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookReaders.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id=request.getParameter("id");
		id=HTMLEscape.htmlEncode(id);
		//System.out.print(id);
		doGet(request,response,id);
		
		
	}

}
