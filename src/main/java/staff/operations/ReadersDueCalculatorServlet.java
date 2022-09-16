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

/**
 * Servlet implementation class BookReaders
 */
@WebServlet("/ReaderDueSlip")
public class ReadersDueCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadersDueCalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {
		System.out.print(id);
		List<LibraryUsers> dueSlip = null;
		try {
			dueSlip = new LibraryUserDao().readersDueAmount(id);
			System.out.print(dueSlip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("dueSlip", dueSlip);
		RequestDispatcher dispatcher = request.getRequestDispatcher("dueSlip.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id=request.getParameter("id");
		System.out.print(id);
		id=HTMLEscape.htmlEncode(id);
		doGet(request,response,id);
		
	}

}
