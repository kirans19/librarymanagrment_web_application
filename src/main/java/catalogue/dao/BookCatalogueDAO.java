package catalogue.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import catalogue.model.BookCatalogue;
import database.connection.DBConnection;


public class BookCatalogueDAO {
	private static final String INSERT_BOOKS =" INSERT INTO  book_catalogue VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BOOKS_BY_ISBN = "SELECT * FROM book_catalogue WHERE ISBN=?";
	private static final String SELECT_ALL_BOOKS = "select * from book_catalogue";
	private static final String DELETE_BOOKS = "UPDATE book_catalogue SET count='-1' WHERE ISBN=?";
	private static final String UPDATE_BOOKS = "UPDATE book_catalogue set title=?,price=?,edition=?,category=?,publisherId=?,authorNo=?,bookInfo=?,publishedYear=?,count=? where ISBN=?";

	public BookCatalogueDAO() {
	}

	

	public boolean insertBook(BookCatalogue bookCatalogue) throws SQLException {
		int flag=0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_BOOKS)) {
			statement.setString(1,bookCatalogue.getISBN());
			statement.setString(2,bookCatalogue.getTitle());
			statement.setInt(3,bookCatalogue.getPrice());
			statement.setString(4,bookCatalogue.getEdition());
			statement.setString(5,bookCatalogue.getCategory());
			statement.setString(6,bookCatalogue.getPublisherId());
			statement.setString(7,bookCatalogue.getAuthorNo());
			statement.setString(8,bookCatalogue.getBookInfo());
			statement.setString(9,bookCatalogue.getPublishedYear());
			statement.setInt(10,bookCatalogue.getCount());
			//System.out.println(statement);
			flag=statement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		if(flag>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public BookCatalogue selectBooks(String ISBN) {
		BookCatalogue bookCatalogue = null;
		
		try (Connection connection = DBConnection.getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_ISBN);) {
			preparedStatement.setString(1, ISBN);
		
			ResultSet result_1 = preparedStatement.executeQuery();

			while (result_1.next()) {
				String title=result_1.getString("title");
				int price=result_1.getInt("price");
				String edition=result_1.getString("edition");
				String category=result_1.getString("category");
				String publisherId=result_1.getString("publisherId");
				String authorNo=result_1.getString("authorNo");
				String bookInfo=result_1.getString("bookInfo");
				String publishedYear=result_1.getString("publishedYear");
				int count=result_1.getInt("count");
				bookCatalogue = new BookCatalogue(ISBN,title,price,edition,category,publisherId,authorNo,bookInfo,publishedYear,count);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bookCatalogue;
	}

	public List<BookCatalogue> selectAllBooks() {

		List<BookCatalogue> bookCatalogues = new ArrayList<>();
		
		try (Connection connection = DBConnection.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);) {
			//System.out.println(preparedStatement);
		
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
				bookCatalogues.add(new BookCatalogue(ISBN,title,price,edition,category,publisherId,authorNo,bookInfo,publishedYear,count));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bookCatalogues;
	}

	public boolean deleteBook(String ISBN) throws SQLException {
		boolean rowDeleted = false;
		Connection connection = DBConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement(DELETE_BOOKS);
		statement.setString(1,ISBN);
		rowDeleted = statement.executeUpdate() > 0;
	
		return rowDeleted;
		}
		
	

	public boolean updateBook(BookCatalogue bookCatalogue) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKS);) {
			statement.setString(1,bookCatalogue.getTitle());
			statement.setInt(2,bookCatalogue.getPrice());
			statement.setString(3,bookCatalogue.getEdition());
			statement.setString(4,bookCatalogue.getCategory());
			statement.setString(5,bookCatalogue.getPublisherId());
			statement.setString(6,bookCatalogue.getAuthorNo());
			statement.setString(7,bookCatalogue.getBookInfo());
			statement.setString(8,bookCatalogue.getPublishedYear());
			statement.setInt(9,bookCatalogue.getCount());
			statement.setString(10,bookCatalogue.getISBN());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
