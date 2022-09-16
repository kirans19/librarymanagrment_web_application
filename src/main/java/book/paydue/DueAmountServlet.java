package book.paydue;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.ReturnAndReserveBooksDao;

/**
 * Servlet implementation class DueAmountServlet
 */
@WebServlet("/CalculateDueAmount")
public class DueAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public DueAmountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = 0;
		try {
			amount = new ReturnAndReserveBooksDao().calculateDueAmount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("amount", amount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DueAmount.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("val");
		//System.out.println(id);
		try {
			new ReturnAndReserveBooksDao().updateDue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("PaymentSucessful.jsp");
		
	}

}
