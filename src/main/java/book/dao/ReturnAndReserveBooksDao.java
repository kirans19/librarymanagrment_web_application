package book.dao;


import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;



import catalogue.model.BookCatalogue;
import login.dao.LoginDao;
import database.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ReturnAndReserveBooksDao {

	String getBookSql="SELECT * FROM book_catalogue";
	
	Connection connection=DBConnection.getConnection();
	
	String id=LoginDao.id;
		
	public List<BookCatalogue> books() throws SQLException{
		List<BookCatalogue> booklist=new ArrayList<>();
		
		PreparedStatement preparedStatement = connection.prepareStatement(getBookSql);
		
		ResultSet result_1 = preparedStatement.executeQuery();

		
		while (result_1.next()) {
			String ISBN=result_1.getString("ISBN");
			String title=result_1.getString("title");
			int price=result_1.getInt("price");
			String edition=result_1.getString("edition");
			String category=result_1.getString("category");
			String publisherId=result_1.getString("publisherId");
			String authorNo=result_1.getString("authorNo");
			String bookInfo=result_1.getString("bookInfo");
			String publishedYear=result_1.getString("publishedYear");
			int count=result_1.getInt("count");
			booklist.add(new BookCatalogue(ISBN,title,price,edition,category,publisherId,authorNo,bookInfo,publishedYear,count));
		}
		return booklist;
	}
	
	public boolean reserveBook(String ISBN) throws SQLException{
		
		boolean check=false;
		String id=LoginDao.id;
		System.out.println(id);
		HashMap<String,String> ISBNandPublisherMapper=new HashMap<>();
		String bookPublisherQuery="SELECT ISBN,publisherId FROM book_catalogue";
		PreparedStatement statement = connection.prepareStatement(bookPublisherQuery);
		ResultSet result=statement.executeQuery();
		while(result.next()) {
			ISBNandPublisherMapper.put(result.getString("ISBN"), result.getString("publisherId"));
		}
		
		
		String checkSameBook="SELECT COUNT(*) FROM book_borrowlog WHERE id=? AND ISBN=? AND status='0'";
		PreparedStatement statement_3 = connection.prepareStatement(checkSameBook);
		statement_3.setString(1,id);
		statement_3.setString(2, ISBN);
		ResultSet result_3=statement_3.executeQuery();
		int sameBookCount=0;
		while(result_3.next()) {
			sameBookCount=result_3.getInt("COUNT(*)");
		}
		
		//System.out.println(sameBookCount);
		
		if(ISBNandPublisherMapper.containsKey(ISBN)) {
		if(!ISBNandPublisherMapper.get(ISBN).equals(id) && sameBookCount==0) {
		
		
		String statusQuery="UPDATE book_catalogue SET count=count-1 WHERE ISBN=?";
		PreparedStatement statement_1 = connection.prepareStatement(statusQuery);
		statement_1.setString(1,ISBN);
		int flag=statement_1.executeUpdate();
		
		
		if(flag>0) {
 		String logQuery="INSERT INTO book_borrowlog(id,ISBN,issue_date,due_amount,status) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement statement_2=connection.prepareStatement(logQuery);
		java.util.Date date=new java.util.Date();
		java.sql.Date issueDate=new java.sql.Date(date.getTime());
		statement_2.setString(1,id);
		statement_2.setString(2,ISBN);
		statement_2.setDate(3,issueDate);
		//statement_2.setString(4,"");
		statement_2.setInt(4,0);
		statement_2.setString(5,"0");
		statement_2.executeUpdate();
		check=true;
		}
		}
		}
		return check;
	}
	
	public List<BookCatalogue> booksToBeReturned() throws SQLException{
		String id=LoginDao.id;
		List<BookCatalogue> returnBooks=new ArrayList<>();
		String statusQuery="select book_borrowlog.ISBN,book_catalogue.title from book_borrowlog,book_catalogue where book_borrowlog.ISBN=book_catalogue.ISBN AND id=? and status=?";
		PreparedStatement statement_3 = connection.prepareStatement(statusQuery);
		statement_3.setString(1,id);
		statement_3.setString(2, "0");
		ResultSet rs=statement_3.executeQuery();
		while(rs.next()) {
			String ISBN=rs.getString("ISBN");
			String title=rs.getString("title");
			returnBooks.add(new BookCatalogue(ISBN,title));
		}
		return returnBooks;
	}
	
	public List<BookCatalogue> booksToBeReturned(String id) throws SQLException{
		System.out.println("List"+id);
		List<BookCatalogue> returnBooks=new ArrayList<>();
		String statusQuery="select book_borrowlog.ISBN,book_catalogue.title from book_borrowlog,book_catalogue where book_borrowlog.ISBN=book_catalogue.ISBN AND id=? and status=?";
		PreparedStatement statement_3 = connection.prepareStatement(statusQuery);
		statement_3.setString(1,id);
		statement_3.setString(2, "0");
		ResultSet rs=statement_3.executeQuery();
		while(rs.next()) {
			String ISBN=rs.getString("ISBN");
			String title=rs.getString("title");
			returnBooks.add(new BookCatalogue(ISBN,title));
		}
		System.out.print(returnBooks);
		return returnBooks;
	}
	
	public boolean returnBook(String ISBN)throws SQLException, ParseException{
		
		String id=LoginDao.id;
		boolean check=false;
		//System.out.println(id);
		String dueDateQuery="SELECT issue_date FROM book_borrowlog where id=? AND ISBN=?";
		java.util.Date date=new java.util.Date();
		java.sql.Date returnDate=new java.sql.Date(date.getTime());
		PreparedStatement statement_2 = connection.prepareStatement(dueDateQuery);
		statement_2.setString(1, id);
		statement_2.setString(2, ISBN);
		ResultSet result_1 = statement_2.executeQuery();
		int dueAmount = 0;
		while(result_1.next()) {
			java.sql.Date dueDate=addDays(result_1.getDate("issue_date"),60);
			if(returnDate.compareTo(result_1.getDate("issue_date"))>=0 && returnDate.compareTo(dueDate)<=0){
				dueAmount=0;
			}else {
				dueAmount=(int) (dateDiff(dueDate,returnDate)*20);
			}
		}
		
		String updateBorrowLog="UPDATE book_borrowlog SET due_amount=?,return_date=?,status='1' WHERE ISBN=? AND id=? AND status='0'";
		PreparedStatement statement_3 = connection.prepareStatement(updateBorrowLog);
		statement_3.setFloat(1,dueAmount);
		statement_3.setString(3,ISBN);
		statement_3.setDate(2,returnDate);
		statement_3.setString(4,id);
		int flag=statement_3.executeUpdate();
		
		if(flag>0) {
			String updateCatalogue="UPDATE book_catalogue SET count=count+1 WHERE ISBN=?";
		PreparedStatement statement_4 = connection.prepareStatement(updateCatalogue);
		statement_4.setString(1,ISBN);
		statement_4.executeUpdate();
		check=true;
		}
		
	return check;
	
	}
	
	
	
	public int calculateDueAmount()throws SQLException{
		String id=LoginDao.id;
		
		 int amount=0;
		 String dueAmountQuery="SELECT SUM(due_amount)FROM book_borrowlog WHERE id=?";
		 PreparedStatement statement_1 = connection.prepareStatement(dueAmountQuery);
		 statement_1.setString(1, id);
		 ResultSet result_1 = statement_1.executeQuery();
		 while(result_1.next()) {
			 amount=result_1.getInt(1);
		 }
		
		return amount;
	}
	
	public void updateDue() throws SQLException{
		String id=LoginDao.id;
		String clearDueAmount="UPDATE book_borrowlog SET due_amount=0 WHERE id=?";
		PreparedStatement statement_2 = connection.prepareStatement(clearDueAmount);
		statement_2.setString(1,id);
		statement_2.executeUpdate();
	}
	
	 public Date addDays(Date date, int days) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.DATE, days);
	        return new Date(c.getTimeInMillis());
	    }
	 
	 public long dateDiff(Date firstDate,Date secondDate) throws ParseException {

		        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		        return diff;
	}
	 
	
	
}
