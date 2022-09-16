package book.retaliate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

@WebServlet("/BookReturnServlet")
public class BookReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BookReturnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BookCatalogue> returnBooks = null;
		try {
			returnBooks = new ReturnAndReserveBooksDao().booksToBeReturned();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("returnBooks", returnBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-return.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method  
		boolean flag=false;
		String ISBN=request.getParameter("ISBN");
		HTMLEscape.htmlEncode(ISBN);
		if(NumValidation.isValidnum(ISBN)) {
		
			try {
				ReturnAndReserveBooksDao bookCatalogueDao=new ReturnAndReserveBooksDao();
				flag=bookCatalogueDao.returnBook(ISBN);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			flag=true;
		}
			if(flag==true) {
		response.sendRedirect("BookReturn.jsp");
			}
			else if(flag==false) {
				response.sendRedirect("ReturnUnsuccessful.jsp");
			}
	}

}
