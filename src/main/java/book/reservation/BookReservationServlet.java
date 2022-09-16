package book.reservation;

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


@WebServlet("/bookReservation")
public class BookReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BookReservationServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BookCatalogue> bookList = null;
		try {
			bookList = new ReturnAndReserveBooksDao().books();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("bookList", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-reservation.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		String ISBN=request.getParameter("ISBN");
		ISBN=HTMLEscape.htmlEncode(ISBN);
		if(NumValidation.isValidnum(ISBN)) {
	
		ReturnAndReserveBooksDao bookCatalogueDao=new ReturnAndReserveBooksDao();
		try {
			flag=bookCatalogueDao.reserveBook(ISBN);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			flag=false;
		}
		if(flag==true) {
		response.sendRedirect("BookReserved.jsp");
		}
		else if(flag==false){
			response.sendRedirect("ReservationUnsuccessful.jsp");
		}
	}

}
