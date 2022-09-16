package catalogue.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalogue.dao.BookCatalogueDAO;
import catalogue.model.BookCatalogue;
import html.encode.HTMLEscape;
import staff.operations.LibraryUserDao;
import staff.operations.LibraryUsers;
import validations.NameValidation;
import validations.NumValidation;


@WebServlet("/")
public class BookCatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookCatalogueDAO bookCatalogueDAO;
	
	public void init() {
		bookCatalogueDAO = new BookCatalogueDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			case "/ReaderListServlet":
				getReaderList(request,response);
				break;
			case "/PublisherListServlet":
				getPublisherList(request,response);
				break;
			case "/list":
				listBooks(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<BookCatalogue> listBooks = bookCatalogueDAO.selectAllBooks();
		request.setAttribute("listBooks", listBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<LibraryUsers> publisherList = null;
		try {
			publisherList = new LibraryUserDao().publishers()   ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("publisherList", publisherList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String ISBN=request.getParameter("ISBN");
		HTMLEscape.htmlEncode(ISBN);
		BookCatalogue existingBook = null;
		if(NumValidation.isValidnum(ISBN)) {
			existingBook = bookCatalogueDAO.selectBooks(ISBN);
		}
		List<LibraryUsers> publisherList = null;
		try {
			publisherList = new LibraryUserDao().publishers()   ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("publisherList", publisherList);
		request.setAttribute("book", existingBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		String ISBN=request.getParameter("ISBN");
		ISBN=HTMLEscape.htmlEncode(ISBN);
		String title=request.getParameter("title");
		title=HTMLEscape.htmlEncode(title);
		String price_str=request.getParameter("price");
		price_str=HTMLEscape.htmlEncode(price_str);
		String edition=request.getParameter("edition");
		edition=HTMLEscape.htmlEncode(edition);
		String category=request.getParameter("category");
		category=HTMLEscape.htmlEncode(category);
		String publisherId=request.getParameter("publisherId");
		publisherId=HTMLEscape.htmlEncode(publisherId);
		String authorNo=request.getParameter("authorNo");
		authorNo=HTMLEscape.htmlEncode(authorNo);
		String bookInfo=request.getParameter("bookInfo");
		bookInfo=HTMLEscape.htmlEncode(bookInfo);
		String publishedYear=request.getParameter("publishedYear");
		publishedYear=HTMLEscape.htmlEncode(publishedYear);
		String count_str=request.getParameter("count");
		count_str=HTMLEscape.htmlEncode(count_str);
		
		
		BookCatalogue newBook=null;
		if((NumValidation.isValidnum(ISBN) && NameValidation.isValidname(edition)  && NumValidation.isValidnum(publisherId) && NumValidation.isValidnum(publishedYear) && NumValidation.isValidnum(count_str) && NumValidation.isValidnum(price_str))) {
			int price=Integer.parseInt(price_str);
			int count=Integer.parseInt(count_str);
		newBook = new BookCatalogue(ISBN,title,price,edition,category,publisherId,authorNo,bookInfo,publishedYear,count);
		if(bookCatalogueDAO.insertBook(newBook)) {
		response.sendRedirect("list");
		}
		else {
			String message = "Addition of New Books Failed!! Check Details And Try Again!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("list").forward(request, response);
		}
		}else {
			String message = "Addition of New Books Failed!! Check Details And Try Again!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("list").forward(request, response);
			
		}
		
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String ISBN=request.getParameter("ISBN");
		ISBN=HTMLEscape.htmlEncode(ISBN);
		String title=request.getParameter("title");
		title=HTMLEscape.htmlEncode(title);
		String price_str=request.getParameter("price");
		price_str=HTMLEscape.htmlEncode(price_str);
		String edition=request.getParameter("edition");
		edition=HTMLEscape.htmlEncode(edition);
		String category=request.getParameter("category");
		category=HTMLEscape.htmlEncode(category);
		String publisherId=request.getParameter("publisherId");
		publisherId=HTMLEscape.htmlEncode(publisherId);
		String authorNo=request.getParameter("authorNo");
		authorNo=HTMLEscape.htmlEncode(authorNo);
		String bookInfo=request.getParameter("bookInfo");
		bookInfo=HTMLEscape.htmlEncode(bookInfo);
		String publishedYear=request.getParameter("publishedYear");
		publishedYear=HTMLEscape.htmlEncode(publishedYear);
		String count_str=request.getParameter("count");
		count_str=HTMLEscape.htmlEncode(count_str);
		
		BookCatalogue book=null;
		if((NumValidation.isValidnum(ISBN) && NameValidation.isValidname(edition)  && NumValidation.isValidnum(publisherId) && NumValidation.isValidnum(publishedYear) && NumValidation.isValidnum(count_str) && NumValidation.isValidnum(price_str))) {
		int price=Integer.parseInt(price_str);
		int count=Integer.parseInt(count_str);
		book = new BookCatalogue(ISBN,title,price,edition,category,publisherId,authorNo,bookInfo,publishedYear,count);
		if(!bookCatalogueDAO.updateBook(book)) {
			String message = "Update Failed!! Check Details And Try Again!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("list").forward(request, response);
		}
		else {
			response.sendRedirect("list");
		}
		}
		
		else {
			String message = "Update Failed!! Check Details And Try Again!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("list").forward(request, response);
		}
		
}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String ISBN=request.getParameter("ISBN");
		if(bookCatalogueDAO.deleteBook(ISBN)) {
		response.sendRedirect("list");
		}
		else {
		String message = "Deletion Of Book Failed!! Some Copies of Book have been still reserved by the readers. Try to use Update Books !!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("list").forward(request, response);
		}

	}
	protected void getReaderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<LibraryUsers> readerList = null;
		try {
			readerList = new LibraryUserDao().readers()   ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("readerList", readerList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ReaderList.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void getPublisherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<LibraryUsers> publisherList = null;
		try {
			publisherList = new LibraryUserDao().publishers()   ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("publisherList", publisherList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PublisherList.jsp");
		dispatcher.forward(request, response);
	}
}